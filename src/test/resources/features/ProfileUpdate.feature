@functional
Feature: Update the user profile

  @profile
  Scenario: Verify user can make Profile changes and Save them sucessfully
    Given User logged into the application
      | username     | password    |
      | testuser2023 | Welcome123! |
    And Profile option is displayed on the Home page
    When User clicks on Profile button
    Then User should be able to update profile
