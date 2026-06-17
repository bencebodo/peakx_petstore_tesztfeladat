package org.peakx.tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.peakx.models.Pet;
import org.peakx.tests.base.BaseTest;
import org.peakx.tests.support.PetDataBuilder;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("regression")
@Tag("api")
@Feature("Pet Store - Pet endpoint CRUD operations test")
public class PetApiTests extends BaseTest {

    @Test
    @Story("Complete Pet CRUD Journey")
    @DisplayName("End-to-End: Create, Read, Update, Get and Delete a Pet")
    public void e2ePetCrudJourneyTest() {

        Pet requestPet = PetDataBuilder.createRandomAvailablePet();

        //1. Post

        Pet createdPet = Allure.step("Create pet using Post request", () ->
                petService.createPet(requestPet, 200)
        );

        assertThat(createdPet)
                .as("Created pet should exactly match the requested data")
                .usingRecursiveComparison()
                .isEqualTo(requestPet);

        //2. Get

        Pet retrievedPet = Allure.step("Fetching pet using Get request", () ->
                petService.getPet(createdPet.getId(), 200)
                );

        assertThat(retrievedPet)
                .as("Retrieved pet should match the created data")
                .usingRecursiveComparison()
                .isEqualTo(requestPet);

        //3. Update

        requestPet.setName(PetDataBuilder.generateRandomName());

        Pet updatedPet = Allure.step("Changing pet's name using PUT request", () ->
                petService.updatePet(requestPet, 200)
        );
        assertThat(updatedPet)
                .as("Updated pet should reflect the new name and match request")
                .usingRecursiveComparison()
                .isEqualTo(requestPet);

        //4. Get

        Pet retrievedUpdatedPet = Allure.step("Retrieving changed pet variables using GET request", () ->
                petService.getPet(requestPet.getId(), 200)
        );

        assertThat(retrievedUpdatedPet)
                .as("Retrieved pet should match the updated data")
                .usingRecursiveComparison()
                .isEqualTo(requestPet);

        //5. Delete

        Allure.step("Deleting pet using DELETE request", () ->
                petService.deletePet(requestPet.getId(), 200)
        );

        logger.info("Verifying deletion by expecting a 404 Not Found response...");
        Allure.step("Verifying deletion by expecting a 404 Not Found response", () ->{
                    logger.info("Executing BL: Verifying deletion by expecting a 404 Not Found response...");
                    petService.getPet(requestPet.getId(), 404);
                });
    }
}
