Feature: Creation of fruits
  Scenario: connecting a user
    Given I have a user login payload
    When I POST it to the /login endpoint
    Then I receive a 200 status code
    And I receive a response with a jwt in Authorization header