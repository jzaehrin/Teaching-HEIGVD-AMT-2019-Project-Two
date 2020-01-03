Feature: Login@auth-api

  Scenario: connecting a legit user with the right password as json
    Given I have the user "pete842@mail.com"
    And I have the password "totem"
    When I POST it to the route "/auth/login" as "application/json"
    Then I receive a 200 status code
    And I receive a response with a jwt in Authorization header
    And I am logged in as "pete842"

  Scenario: connecting a legit user with a wrong password as json
    Given I have the user "pete842@mail.com"
    And I have the password "not-totem"
    When I POST it to the route "/auth/login" as "application/json"
    Then I receive a 403 status code
    And I receive a response without Authorization header

  Scenario: connecting a legit user with the right password as text
    Given I have the user "pete842@mail.com"
    And I have the password "totem"
    When I POST it to the route "/auth/login" as "text/plain"
    Then I receive a 415 status code
    And I receive a response without Authorization header

  Scenario: connecting a legit user with no password as json
    Given I have the user "pete842@mail.com"
    When I POST it to the route "/auth/login" as "application/json"
    Then I receive a 400 status code
    And I receive a response without Authorization header

  Scenario: connecting a user without an email as json
    Given I have a user without email
    And I have the password "totem"
    When I POST it to the route "/auth/login" as "application/json"
    Then I receive a 400 status code
    And I receive a response without Authorization header

  Scenario: connecting a legit user with right password as malformed json
    Given I have the user "pete842@mail.com"
    And I have the password "totem"
    When I POST it to the route "/auth/login" as malformed "application/json"
    Then I receive a 400 status code
    And I receive a response without Authorization header
