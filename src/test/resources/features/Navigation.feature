@functional
Feature: Navigate between cards and Home page

  Background: 
    Given User logged into the application
      | username     | password    |
      | testuser2023 | Welcome123! |

  @navigation
  Scenario: Navigate to 'Popular Make' page and return back to home page
    Given User clicks on Popular Make card
    When User clicks on Popular Make page header
    Then User is navigated back to Home page

  @navigation
  Scenario: Navigate to 'Popular Model' page and return back to home page
    Given User clicks on Popular Model card
    When User clicks on Popular Model page header
    Then User is navigated back to Home page

  @navigation
  Scenario: Navigate to 'Overall Rating' page and return back to home
    Given User clicks on Overall Rating card
    When User clicks on Overall Rating page header
    Then User is navigated back to Home page
