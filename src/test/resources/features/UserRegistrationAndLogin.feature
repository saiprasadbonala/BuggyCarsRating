@functional
Feature: User Registration and Login Validation

  @registration
  Scenario: Verify registration of user with valid data
    Given User is on register page
    When User enters all the required details
      | username | firstName | lastName       | password     | confirmPassword |
      | random   | Test      | UserExercise01 | Welcome@1234 | Welcome@1234    |
    And Click on Register button
    Then User should be registered successfully
    And User should login with registered credentials successfully

  @registration
  Scenario: Verify registrating an existing user
    Given User is on register page
    When User enters all the required details
      | username     | firstName | lastName       | password    | confirmPassword |
      | testuser2023 | Test      | UserExercise01 | Welcome123! | Welcome123!     |
    And Click on Register button
    Then User already exits error is shown

  @login
  Scenario Outline: Verify login and logout with valid credentials
    Given User enters username "<username>" and password "<password>"
    When User clicks on Login button
    Then User should be logged in successfully
    And User logs out successfully

    Examples: 
      | username     | password     |
      | testuser2023 | Welcome123!  |
      | testuser1005 | Welcome@1234 |

  @login
  Scenario Outline: Verify login with invalid credentials
    Given User enters username "<username>" and password "<password>"
    When User clicks on Login button
    Then User login should fail with error message

    Examples: 
      | username        | password    |
      | invalidtestuser | Welcome123! |
