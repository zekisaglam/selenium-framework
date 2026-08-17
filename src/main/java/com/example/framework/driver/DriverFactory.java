package com.example.framework.driver;

import com.example.framework.config.BrowserType;
import com.example.framework.config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;

/**
 * Factory responsible for creating {@link WebDriver} instances based on the
 * {@link BrowserType} defined in {@code config.properties}.
 *
 * The factory uses WebDriverManager to resolve driver binaries automatically.
 */
public class DriverFactory {

    /**
     * Creates a new WebDriver instance according to the configured browser.
     *
     * @return a fresh WebDriver instance
     */
    public static WebDriver createDriver() {
        BrowserType browser = ConfigReader.getInstance().getBrowser();

        switch (browser) {
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                return new org.openqa.selenium.chrome.ChromeDriver();
            }
            case HEADLESS_CHROME -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                return new org.openqa.selenium.chrome.ChromeDriver(options);
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                return new org.openqa.selenium.firefox.FirefoxDriver();
            }
            case EDGE -> {
                WebDriverManager.edgedriver().setup();
                return new org.openqa.selenium.edge.EdgeDriver();
            }
            default -> throw new IllegalStateException("Unsupported browser: " + browser);
        }
    }
}