Feature: eTicaret_search
  Scenario: user_search_bilgisayar
    Given user go to the trendyol
    When user search the bilgisayar
    When user click the first item
    Then user verify the item name contains the bilgisayar