Feature: google_search
  Scenario: Search_the_openai
    Given user go to the google
    When user search the openai
    When user verify the search page
    Then user close the page