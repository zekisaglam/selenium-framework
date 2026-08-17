package com.example.framework.stepdefinitions;

import com.example.framework.pages.DashboardPage;
import com.example.framework.pages.LoginPage;
import io.cucumber.java.en.*;

import org.testng.Assert;

/**
 * Step definitions for the login feature.
 */
public class LoginSteps {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @Given("the user navigates to the login page")
    public void navigateToLogin() {
        loginPage = new LoginPage().open();
    }

    @When("the user enters username {string}")
    public void enterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @When("the user enters password {string}")
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("the user clicks the login button")
    public void clickLogin() {
        dashboardPage = loginPage.clickLogin();
    }

    @Then("the user should be redirected to the dashboard")
    public void verifyDashboard() {
        Assert.assertNotNull(dashboardPage);
    }

    @Then("the dashboard title should be {string}")
    public void verifyTitle(String expectedTitle) {
        String actual = dashboardPage.getTitle();
        Assert.assertEquals(expectedTitle, actual);
    }
}