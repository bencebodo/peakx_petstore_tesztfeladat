package org.peakx.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.peakx.models.Pet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PetStoreClient {

    @Value("${petstore.base-url}")
    private String baseUrl;

    private final String petEndpoint = "/pet";

    private RequestSpecification getBaseSpec() {
        return RestAssured.given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .filter(new AllureRestAssured());
    }

    public Response postPet(Pet pet) {
        return getBaseSpec().body(pet).when().post(petEndpoint).then().extract().response();
    }

    public Response getPet(Long petId) {
        return getBaseSpec().pathParam("petId", petId).when().when().get(petEndpoint + "/{petId}").then().extract().response();
    }

    public Response updatePet(Pet pet) {
        return getBaseSpec().body(pet).when().put(petEndpoint).then().extract().response();
    }

    public Response deletePet(Long petId) {
        return getBaseSpec().pathParam("petId", petId).when().delete(petEndpoint + "/{petId}").then().extract().response();
    }
}
