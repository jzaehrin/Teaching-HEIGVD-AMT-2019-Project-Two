Feature: Users

  Scenario: changing the password of a legit user
    Given I am logged in as "pete842@mail.com" with password "totem"
    And I have a well-formed password payload
    When I POST it to the route "/users/1/password" with token
    Then I receive a 200 status code
    And I prepare a rollback
    And I POST it to the route "/users/1/password" with token

  Scenario: changing the password of another user
    Given I am logged in as "pete842@mail.com" with password "totem"
    And I have a well-formed password payload
    When I POST it to the route "/users/2/password" with token
    Then I receive a 401 status code

  Scenario: changing the password of a user without any token in request
    And I have a well-formed password payload
    When I POST it to the route "/users/1/password"
    Then I receive a 500 status code
