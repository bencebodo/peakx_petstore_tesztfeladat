package org.peakx.tests.support;

import net.datafaker.Faker;
import org.peakx.models.Category;
import org.peakx.models.Pet;
import org.peakx.models.PetStatus;
import org.peakx.models.Tag;

public class PetDataBuilder {

    private static final Faker faker = new Faker();

    private PetDataBuilder() {}

    public static Pet createRandomAvailablePet() {
        return new Pet(
                faker.number().randomNumber(10, true),
                new Category(faker.number().randomNumber(3, true), faker.animal().name()),
                faker.dog().name(),
                new String[]{faker.internet().url()},
                new Tag[]{new Tag(1L, faker.dog().breed())},
                PetStatus.AVAILABLE
        );
    }

    public static String generateRandomName() {
        return faker.dog().name();
    }
}