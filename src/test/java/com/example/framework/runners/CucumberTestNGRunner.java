package com.example.framework.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Cucumber TestNG runner that integrates Cucumber feature files with TestNG execution.
 * <p>
 * This class is referenced from <code>testng.xml</code>. It picks up all feature files
 * under {@code src/test/resources/features} and looks for step definitions in the
 * {@code com.example.framework.stepdefinitions} package.
 * </p>
 *
 * @author 
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.example.framework.stepdefinitions",
        plugin = {"pretty",
                  "json:target/cucumber-reports/CucumberReport.json",
                  "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"},
        monochrome = true,
        tags = "@smoke or @regression"
)
public class CucumberTestNGRunner extends AbstractTestNGCucumberTests {
    // No additional code required; inherits run logic from AbstractTestNGCucumberTests.
}