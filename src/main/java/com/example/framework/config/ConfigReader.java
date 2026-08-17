package com.example.framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Singleton utility that reads {@code config.properties} from the classpath.
 * Provides typed getters for all framework configuration values.
 *
 * The class loads the properties lazily on first access and caches them.
 */
public class ConfigReader {

    private static final ConfigReader INSTANCE = new ConfigReader();

    private final Properties properties = new Properties();

    private ConfigReader() {
        try (InputStream is = getClass().getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (is == null) {
                throw new IllegalStateException("config.properties not found on classpath");
            }
            properties.load(is);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load config.properties", e);
        }
    }

    public static ConfigReader getInstance() {
        return INSTANCE;
    }

    /**
     * Retrieves a raw property value by its key.
     *
     * @param key property name as defined in {@code config.properties}
     * @return the property value or {@code null} if not present
     */
    public String get(String key) {
        return properties.getProperty(key);
    }

    // Typed convenience methods -------------------------------------------------

    public BrowserType getBrowser() {
        String value = get("browser");
        return BrowserType.valueOf(value.toUpperCase());
    }

    public String getUrl() {
        return get("url");
    }

    public String getUsername() {
        return get("username");
    }

    public String getPassword() {
        return get("password");
    }

    public int getTimeout() {
        return Integer.parseInt(get("timeout"));
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(get("headless"));
    }

    public String getEnvironment() {
        return get("environment");
    }

    public boolean isParallel() {
        return Boolean.parseBoolean(get("parallel"));
    }

    public int getRetryCount() {
        return Integer.parseInt(get("retryCount"));
    }

    public String getWindowSize() {
        return get("windowSize");
    }

    public String getExecutionMode() {
        return get("executionMode");
    }
}