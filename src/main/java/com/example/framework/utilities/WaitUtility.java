package com.example.framework.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Centralised explicit wait utility.
 * All waiting operations delegate to this class to avoid {@code Thread.sleep()}.
 */
public class WaitUtility {

    private final WebDriver driver;
    private final int timeoutSeconds;

    public WaitUtility(WebDriver driver, int timeoutSeconds) {
        this.driver = driver;
        this.timeoutSeconds = timeoutSeconds;
    }

    public WebElement waitForVisibility(By locator) {
        return new WebDriverWait(driver, timeoutSeconds)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickable(By locator) {
        return new WebDriverWait(driver, timeoutSeconds)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean waitForInvisibility(By locator) {
        return new WebDriverWait(driver, timeoutSeconds)
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public Alert waitForAlert() {
        return new WebDriverWait(driver, timeoutSeconds)
                .until(ExpectedConditions.alertIsPresent());
    }
}