package com.example.framework.listeners;

import com.example.framework.driver.DriverManager;
import com.example.framework.utilities.ScreenshotUtility;
import org.testng.*;
import org.testng.reporters.XMLReporterConfig;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * TestNG listener that logs test execution and captures screenshots on failure.
 * Integrates with Allure by attaching the screenshot file.
 */
public class TestListener implements ITestListener, ISuiteListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onStart(ISuite suite) {
        logger.info("=== Suite {} STARTED ===", suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        logger.info("=== Suite {} FINISHED ===", suite.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("=== Test {} STARTED ===", result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("=== Test {} PASSED ===", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("=== Test {} FAILED ===", result.getMethod().getMethodName(),
                result.getThrowable());

        // Capture screenshot
        try {
            String screenshotPath = ScreenshotUtility.captureScreenshot(
                    DriverManager.getInstance().getDriver(),
                    result.getMethod().getMethodName());
            // Attach to Allure
            io.qameta.allure.Allure.addAttachment("Screenshot on Failure",
                    "image/png",
                    screenshotPath);
        } catch (Exception e) {
            logger.warn("Failed to capture screenshot on test failure", e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("=== Test {} SKIPPED ===", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not used
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("=== Test Context {} STARTED ===", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("=== Test Context {} FINISHED ===", context.getName());
    }
}