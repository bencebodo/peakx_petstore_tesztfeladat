package org.peakx.service;

import io.restassured.response.Response;
import org.peakx.api.PetStoreClient;
import org.peakx.models.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    private final PetStoreClient client;

    @Autowired
    public PetService(PetStoreClient client) {
        this.client = client;
    }

    public Pet createPet(Pet pet, int expectedStatusCode) {
        Response response = client.postPet(pet);

        response.then().assertThat().statusCode(expectedStatusCode);

        return response.as(Pet.class);
    }

    public Pet getPet(long petId, int expectedStatusCode) {
        Response response = client.getPet(petId);

        response.then().assertThat().statusCode(expectedStatusCode);

        return response.as(Pet.class);
    }

    public Pet updatePet(Pet pet, int expectedStatusCode) {
        Response response = client.updatePet(pet);

        response.then().assertThat().statusCode(expectedStatusCode);

        return response.as(Pet.class);
    }

    public void deletePet(long petId, int expectedStatusCode) {
        Response response = client.deletePet(petId);
        response.then().assertThat().statusCode(expectedStatusCode);
    }
}
