package com.example.framework.pages;

import org.openqa.selenium.By;

/**
 * Simple representation of a Dashboard page used for verification.
 */
public class DashboardPage extends BasePage {

    private final By titleHeader = By.cssSelector("h1.dashboard-title");

    public String getTitle() {
        return getText(titleHeader);
    }
}