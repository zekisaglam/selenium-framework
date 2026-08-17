package com.example.framework.tests;

import com.example.framework.driver.DriverManager;
import com.example.framework.config.ConfigReader;
import org.testng.annotations.*;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Base TestNG test class.
 *
 * Handles driver lifecycle, logging and provides common test utilities.
 * All test classes (including Cucumber runners) should extend this class.
 */
public abstract class BaseTest {

    protected Logger logger = LogManager.getLogger(this.getClass());

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        logger.info("=== Test Class Setup ===");
        DriverManager.getInstance().setDriver();

        // Apply global configuration (e.g., window size, timeouts)
        if (ConfigReader.getInstance().isHeadless()) {
            // headless configuration already applied in driver creation
        }

        String windowSize = ConfigReader.getInstance().getWindowSize();
        if (windowSize != null && !windowSize.isBlank()) {
            String[] parts = windowSize.split(",");
            int width = Integer.parseInt(parts[0].trim());
            int height = Integer.parseInt(parts[1].trim());
            DriverManager.getInstance().getDriver().manage().window().setSize(new org.openqa.selenium.Dimension(width, height));
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        logger.info("=== Test Class Teardown ===");
        DriverManager.getInstance().quitDriver();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        logger.info("=== Test Method Start ===");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        logger.info("=== Test Method End ===");
    }
}