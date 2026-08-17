package com.example.framework.driver;

import org.openqa.selenium.WebDriver;

/**
 * Manages a {@link ThreadLocal} {@link WebDriver} instance.
 *
 * <p>
 * Each test thread gets its own driver, supporting parallel execution.
 * The class follows the Singleton pattern – a single global accessor
 * {@code getInstance()} returns the manager, while the actual driver
 * is stored per thread.
 * </p>
 */
public class DriverManager {

    private static final DriverManager INSTANCE = new DriverManager();

    // Thread‑local storage of WebDriver
    private final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager() {
        // private constructor to enforce singleton
    }

    public static DriverManager getInstance() {
        return INSTANCE;
    }

    /**
     * Creates and stores a driver for the current thread using {@link DriverFactory}.
     */
    public void setDriver() {
        driverThreadLocal.set(DriverFactory.createDriver());
    }

    /**
     * Retrieves the driver bound to the current thread.
     *
     * @return WebDriver instance for this thread
     */
    public WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    /**
     * Quits the driver for the current thread and removes it from storage.
     */
    public void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}