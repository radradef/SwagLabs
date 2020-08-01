Feature: Login

  Background:
    Given the user is on the login page

  Scenario: The user can see the login form
    Then he should see the login form

    Scenario Outline: The user successfully logs in with their credentials
      When Koko logs in with credentials:
        |username  |password  |
        |<username>|<password>|
      Then he should see the product page
      And the product page header should say: "Products"
      Examples:
        |username       |password    |
        |standard_user  |secret_sauce|
        |locked_out_user|secret_sauce|

      Scenario Outline: The user tries to log in with wrong credentials
        When Lidia logs in with credentials:
          |username  |password  |
          |<username>|<password>|
        Then she should see error message
        And the error message should say:
        |Username and password do not match any user in this service|
        Examples:
          |username       |password      |
          |standard_user  |wrong_password|
          |wrong_user     |secret_sauce  |