package org.peakx.tests.support;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

public class LoggingTestWatcher implements TestWatcher {

    private static final Logger logger = LoggerFactory.getLogger(LoggingTestWatcher.class.getName());

    @Override
    public void testSuccessful(ExtensionContext context) {
        String testName = context.getDisplayName();
        String message = "[PASS] Test '" + testName + "' passed successfully.";
        logger.info(message);
        Allure.step(message);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        String testName = context.getDisplayName();
        String message = "[FAIL] Test '" + testName + "' failed. Error: " + cause.getMessage();
        logger.error(message);
        Allure.step(message);
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        logger.info("[DISABLED] Test '{}' is disabled.", context.getDisplayName());
    }
}
