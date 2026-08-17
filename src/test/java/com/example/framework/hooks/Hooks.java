package com.example.framework.hooks;

import com.example.framework.driver.DriverManager;
import com.example.framework.config.ConfigReader;
import com.example.framework.utilities.ScreenshotUtility;
import io.cucumber.java.*;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Cucumber hook implementation.
 *
 * Responsibilities:
 * - Initialise driver before each scenario.
 * - Load configuration and logger.
 * - Initialise Allure environment (handled automatically by Allure annotations).
 * - Capture screenshot on scenario failure.
 * - Quit driver after each scenario.
 */
public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before(order = 0)
    public void beforeScenario() {
        logger.info("=== Scenario Setup ===");
        DriverManager.getInstance().setDriver();

        // Example: navigate to base URL if defined
        String baseUrl = ConfigReader.getInstance().getUrl();
        if (baseUrl != null && !baseUrl.isBlank()) {
            DriverManager.getInstance().getDriver().get(baseUrl);
        }
    }

    @After(order = 0)
    public void afterScenario(Scenario scenario) {
        logger.info("=== Scenario Teardown ===");
        if (scenario.isFailed()) {
            // Capture screenshot and attach to Allure
            String screenshotPath = ScreenshotUtility.captureScreenshot(
                    DriverManager.getInstance().getDriver(),
                    scenario.getName().replaceAll("[^a-zA-Z0-9_-]", "_"));
            io.qameta.allure.Allure.addAttachment("Screenshot on Failure",
                    "image/png",
                    screenshotPath);
        }
        DriverManager.getInstance().quitDriver();
    }
}