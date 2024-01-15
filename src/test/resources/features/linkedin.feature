Feature: LinkedIn
  Background: go to the linkedin home page
    Given go_to_the_linkedin

  Scenario: sign_in
    When click the sign in button
    When enter the e-mail and password
    Then verify the account was create succesfuly


  Scenario: login_in
    When enter the mail and password
    When search the @user
    When click the first person
    Then verify
