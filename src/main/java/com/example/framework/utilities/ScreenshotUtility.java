package com.example.framework.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Captures screenshots and stores them under {@code target/screenshots}.
 * Returns the absolute path of the saved PNG file.
 */
public class ScreenshotUtility {

    private static final String SCREENSHOT_DIR = System.getProperty("user.dir") + File.separator + "target"
            + File.separator + "screenshots";

    static {
        // Ensure directory exists
        new File(SCREENSHOT_DIR).mkdirs();
    }

    /**
     * Captures a screenshot with a timestamped filename.
     *
     * @param driver WebDriver instance
     * @param name   logical name for the screenshot (e.g., scenario name)
     * @return absolute path to the saved PNG file
     */
    public static String captureScreenshot(WebDriver driver, String name) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver is null");
        }
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filename = name + "_" + timestamp + ".png";
        File dest = new File(SCREENSHOT_DIR, filename);
        try {
            Files.copy(src.toPath(), dest.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot", e);
        }
        return dest.getAbsolutePath();
    }
}