@functional
Feature: Verify Overall Rating page functionality

  Background: 
    Given User logged into the application
      | username     | password    |
      | testuser2023 | Welcome123! |

  @overallrating
  Scenario: Verify page next button is disabled in the pagination once user reaches last page
    Given User navigates to Overall Rating page
    When User goes to last page of the cars list from pagination
    Then Page Next button should be disabled

  @overallrating
  Scenario Outline: Verify user can navigate to Car Make pages from Overall rating page
    Given User navigates to Overall Rating page
    When User selects the "Make" name "<CarMake>"
    Then User should be navigated to Make "<CarMake>" page

    Examples: 
      | CarMake    |
      | Alfa Romeo |
      | Pagani     |

  @overallrating
  Scenario Outline: Verify user can navigate to Car Model pages from Overall rating page
    Given User navigates to Overall Rating page
    When User selects the "Model" name "<CarModel>"
    Then User should be navigated to Model "<CarModel>" page

    Examples: 
      | CarModel            |
      | Guilia Quadrifoglio |
      | Huayra              |
