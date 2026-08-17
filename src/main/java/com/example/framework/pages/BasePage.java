package com.example.framework.pages;

import com.example.framework.driver.DriverManager;
import com.example.framework.utilities.WaitUtility;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

/**
 * Base class for all Page Objects.
 *
 * Provides common Selenium actions wrapped with explicit waits.
 * Each concrete page extends this class and inherits the driver and utilities.
 */
public abstract class BasePage {

    protected final WebDriver driver;
    protected final WaitUtility wait;
    protected final Actions actions;
    protected final JavascriptExecutor js;

    protected BasePage() {
        this.driver = DriverManager.getInstance().getDriver();
        // Timeout retrieved from ConfigReader (default 30 seconds)
        int timeout = com.example.framework.config.ConfigReader.getInstance()
                .getTimeout();
        this.wait = new WaitUtility(driver, timeout);
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
    }

    // --------------------------------------------------------------------
    // Basic element interactions
    // --------------------------------------------------------------------
    protected WebElement find(By locator) {
        return wait.waitForVisibility(locator);
    }

    protected void click(By locator) {
        wait.waitForClickable(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement el = wait.waitForClickable(locator);
        el.clear();
        el.sendKeys(text);
    }

    protected String getText(By locator) {
        return find(locator).getText();
    }

    protected void hover(By locator) {
        actions.moveToElement(find(locator)).perform();
    }

    protected void scrollIntoView(By locator) {
        js.executeScript("arguments[0].scrollIntoView(true);", find(locator));
    }

    protected void executeJs(String script, Object... args) {
        js.executeScript(script, args);
    }

    // --------------------------------------------------------------------
    // Alert handling
    // --------------------------------------------------------------------
    protected Alert waitForAlert() {
        return wait.waitForAlert();
    }

    protected void acceptAlert() {
        waitForAlert().accept();
    }

    protected void dismissAlert() {
        waitForAlert().dismiss();
    }

    protected String getAlertText() {
        return waitForAlert().getText();
    }

    // --------------------------------------------------------------------
    // Window handling
    // --------------------------------------------------------------------
    protected void switchToWindow(String title) {
        String original = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            if (driver.getTitle().equals(title)) {
                return;
            }
        }
        driver.switchTo().window(original);
        throw new NoSuchWindowException("Window with title '" + title + "' not found");
    }

    protected void closeCurrentWindow() {
        driver.close();
    }
}