package org.peakx.service;

import io.restassured.response.Response;
import org.peakx.api.PetStoreClient;
import org.peakx.models.Pet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {
    private static final Logger logger  = LoggerFactory.getLogger(PetService.class);

    private final PetStoreClient client;

    @Autowired
    public PetService(PetStoreClient client) {
        this.client = client;
    }

    public Pet createPet(Pet pet, int expectedStatusCode) {
        logger.info("Attempting to create pet (ID: {})", pet.getId());
        Response response = client.postPet(pet);

        response.then().assertThat().statusCode(expectedStatusCode);

        return response.as(Pet.class);
    }

    public Pet getPet(long petId, int expectedStatusCode) {
        logger.info("Fetching pet details (ID: {})", petId);

        Response response = client.getPet(petId);

        response.then().assertThat().statusCode(expectedStatusCode);

        if (expectedStatusCode == 200) {
            return response.as(Pet.class);
        }

        return null;
    }

    public Pet updatePet(Pet pet, int expectedStatusCode) {
        logger.info("Updating pet information (ID: {})", pet.getId());
        Response response = client.updatePet(pet);

        response.then().assertThat().statusCode(expectedStatusCode);

        return response.as(Pet.class);
    }

    public void deletePet(long petId, int expectedStatusCode) {
        logger.info("Removing pet from system (ID: {})", petId);

        Response response = client.deletePet(petId);
        response.then().assertThat().statusCode(expectedStatusCode);
    }
}
