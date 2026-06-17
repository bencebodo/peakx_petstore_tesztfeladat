package org.peakx.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.peakx.models.Pet;
import org.peakx.tests.base.BaseTest;
import org.peakx.tests.support.PetDataBuilder;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("Pet Store endpoint tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PetApiTests extends BaseTest {

    private static Long sharedPetId;
    private static Pet sharedPetRequest;

    @BeforeAll
    public static void prepareTestData(){
        sharedPetRequest = PetDataBuilder.createRandomAvailablePet();
        sharedPetId = sharedPetRequest.getId();
    }

    @Test
    @Order(1)
    @Story("Create a new test pet")
    @DisplayName("1. POST - Should create a pet successfully")
    public void createPetTest() {
        logger.info(String.format("Create Pet - Should create a pet successfully"));

        Pet createdPet = petService.createPet(sharedPetRequest, 200);

        assertAll(
                () -> assertEquals(sharedPetRequest.getId(), createdPet.getId(), "ID mismatch"),
                () -> assertEquals(sharedPetRequest.getName(), createdPet.getName(), "Name mismatch")
        );
    }

    @Test
    @Order(2)
    @Story("Retrieve test pet")
    @DisplayName("2. GET - Should return the previously created pet successfully")
    public void getPetTest() {
        logger.info(String.format("Get Pet - Should return the previously created pet successfully"));

        Pet retrievedPet = petService.getPet(sharedPetId, 200);

        assertAll(
                () -> assertEquals(sharedPetRequest.getId(), retrievedPet.getId(),"ID mismatch"),
                () -> assertEquals(sharedPetRequest.getName(), retrievedPet.getName(), "Name mismatch")
        );
    }

    @Test
    @Order(3)
    @Story("Update test pet information")
    @DisplayName("Update Pet - Should update the previously created pet successfully")
    public void updatePetTest() {
        logger.info(String.format("Update Pet - Should update the pet successfully"));

        sharedPetRequest.setName(PetDataBuilder.generateRandomName());

        Pet updatedPet = petService.updatePet(sharedPetRequest, 200);

        assertAll(
                () -> assertEquals(sharedPetRequest.getId(), updatedPet.getId(), "ID mismatch"),
                () -> assertEquals(sharedPetRequest.getName(), updatedPet.getName(), "Name mismatch")
        );
    }

    @Test
    @Order(4)
    @Story("Retrive test pet with updated information")
    @DisplayName("Get Pet - Should return previously updated pet succesfully")
    public void getPetWithUpdatedTest() {
        logger.info(String.format("Get Pet - Should return the previously updated pet successfully"));

        Pet retrievedPet = petService.getPet(sharedPetId, 200);

        assertAll(
                () -> assertEquals(sharedPetRequest.getId(), retrievedPet.getId(),"ID mismatch"),
                () -> assertEquals(sharedPetRequest.getName(), retrievedPet.getName(), "Name mismatch")
        );
    }

    @Test
    @Order(5)
    @Story("Delete test pet")
    @DisplayName("Delete Pet - Should delete test pet succesfully")
    public void deletePetTest() {
        logger.info(String.format("Delete Pet - Should delete test pet successfully"));

        petService.deletePet(sharedPetId, 200);

        logger.info("Verifying deletion by expecting a 404 Not Found response...");
        petService.getPet(sharedPetId, 404);
    }

}
