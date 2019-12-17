Feature: the user can log in
  Scenario: user makes call to POST  /users/login
    Given user's email is in the database
    And user's password's hash correspond to the associated hash
    When the client calls /users/login
    Then the client receives status code of 200
    And the client receives jwt in the Authorization header