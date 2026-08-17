@smoke
Feature: Login Functionality

  Background:
    Given the user navigates to the login page

  Scenario Outline: Successful login with valid credentials
    When the user enters username "<username>"
    And the user enters password "<password>"
    And the user clicks the login button
    Then the user should be redirected to the dashboard
    And the dashboard title should be "Dashboard"

    Examples:
      | username | password |
      | admin    | admin123 |