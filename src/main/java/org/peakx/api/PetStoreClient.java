package org.peakx.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.peakx.models.Pet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PetStoreClient {

    private static final Logger logger = LoggerFactory.getLogger(PetStoreClient.class);

    private final String baseUrl;
    private final String petEndpoint = "/pet";

    public PetStoreClient(@Value("${petstore.base-url}") String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private RequestSpecification getBaseSpec() {
        return RestAssured.given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .filter(new AllureRestAssured());
    }

    public Response postPet(Pet pet) {
        logger.debug("Sending POST request to endpoint: {} with payload ID: {}", petEndpoint, pet.getId());

        return getBaseSpec().body(pet).when().post(petEndpoint).then().extract().response();
    }

    public Response getPet(Long petId) {
        logger.debug("Sending GET request to endpoint: {}/{}", petEndpoint, petId);

        return getBaseSpec().pathParam("petId", petId).when().get(petEndpoint + "/{petId}").then().extract().response();
    }

    public Response updatePet(Pet pet) {
        logger.debug("Sending PUT request to endpoint: {} with updated payload ID: {}", petEndpoint, pet.getId());

        return getBaseSpec().body(pet).when().put(petEndpoint).then().extract().response();
    }

    public Response deletePet(Long petId) {
        logger.debug("Sending DELETE request to endpoint: {}/{}", petEndpoint, petId);

        return getBaseSpec().pathParam("petId", petId).when().delete(petEndpoint + "/{petId}").then().extract().response();
    }
}
