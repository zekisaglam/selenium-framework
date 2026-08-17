package com.example.framework.utilities;

import com.example.framework.config.ConfigReader;

/**
 * Helper utility to access configuration properties in a static way.
 */
public class PropertyUtility {

    /**
     * Shortcut to fetch a property by key.
     *
     * @param key property key as defined in {@code config.properties}
     * @return property value or {@code null}
     */
    public static String get(String key) {
        return ConfigReader.getInstance().get(key);
    }
}