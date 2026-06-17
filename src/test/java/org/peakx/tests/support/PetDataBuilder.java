package org.peakx.tests.support;

import net.datafaker.Faker;
import org.peakx.models.Category;
import org.peakx.models.Pet;
import org.peakx.models.PetStatus;
import org.peakx.models.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PetDataBuilder {

    private static final Logger logger = LoggerFactory.getLogger(PetDataBuilder.class);

    private static final Faker faker = new Faker();

    private PetDataBuilder() {}

    public static Pet createRandomAvailablePet() {
         Pet pet = new Pet(
                faker.number().randomNumber(10, true),
                new Category(faker.number().randomNumber(3, true), faker.animal().name()),
                faker.dog().name(),
                new String[]{faker.internet().url()},
                new Tag[]{new Tag(1L, faker.dog().breed())},
                PetStatus.AVAILABLE
        );
        logger.debug("Generated random test pet: ID={}, Name={}, Breed={}",
                pet.getId(), pet.getName(), pet.getTags()[0].getName());

        return pet;
    }

    public static String generateRandomName() {
        String randomName = faker.dog().name();

        logger.debug("Generated random test pet name: Name={}",
                randomName);

        return randomName;
    }
}