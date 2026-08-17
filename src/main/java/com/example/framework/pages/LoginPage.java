package com.example.framework.pages;

import org.openqa.selenium.By;

/**
 * Page Object representing the Login page.
 *
 * Contains locators and actions required to perform a login.
 */
public class LoginPage extends BasePage {

    // Locators (use id/name/css as per strategy)
    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton   = By.id("loginBtn");

    public LoginPage open() {
        driver.get(com.example.framework.config.ConfigReader.getInstance().getUrl());
        return this;
    }

    public LoginPage enterUsername(String username) {
        type(usernameInput, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(passwordInput, password);
        return this;
    }

    public DashboardPage clickLogin() {
        click(loginButton);
        return new DashboardPage();
    }
}