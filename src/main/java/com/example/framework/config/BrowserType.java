package com.example.framework.config;

/**
 * Enum representing supported browsers. The value is read from {@code config.properties}
 * using {@link ConfigReader#getBrowser()} and drives the choice of WebDriver
 * implementation in {@link DriverFactory}.
 */
public enum BrowserType {
    CHROME,
    FIREFOX,
    EDGE,
    HEADLESS_CHROME
}