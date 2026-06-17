package org.peakx.tests.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.peakx.PetStoreTestApplication;
import org.peakx.service.PetService;
import org.peakx.tests.support.LoggingTestWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import net.datafaker.Faker;

@SpringBootTest(classes = PetStoreTestApplication.class)
@ExtendWith(LoggingTestWatcher.class)
public abstract class BaseTest {

    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected Faker faker;

    @Autowired
    protected PetService petService;

    @BeforeEach
    public void setup(TestInfo testInfo) {
        faker = new Faker();
        logger.info("Starting test: {}", testInfo.getDisplayName());
    }
}
