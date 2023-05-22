@functional
Feature: Popular Model page features

	@voting
  Scenario: Popular Model page - Vote and comment
    Given User navigates to Register page
    And User registers successfully
      | username     | firstName | lastName       | password    | confirmPassword |
      | random | Test      | UserExercise | Welcome123! | Welcome123!     |
    When User logs into the application
    And navigate to Popular Model page
    Then User should be able to vote
    And User comments should be updated in the table
