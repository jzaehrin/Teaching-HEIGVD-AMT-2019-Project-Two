Feature: watched@movie-api

  Scenario: querying a watched page as legit user
    Given I am logged in as "pete842@mail.com" with password "totem"
    When I GET the route "/movie/users/1/watched" with token
    Then I receive a 200 status code
    And I receive a watched page of size 20

  Scenario: querying a watched page with specific size as legit user
    Given I am logged in as "pete842@mail.com" with password "totem"
    When I GET the route "/movie/users/1/watched?pageSize=10" with token
    Then I receive a 200 status code
    And I receive a watched page of size 10

  Scenario: querying a watched page
    When I GET the route "/movie/users/1/watched"
    Then I receive a 401 status code

  Scenario: adding a watched relation as legit user
    Given I am logged in as "pete842@mail.com" with password "totem"
    And I have a well-formed watched payload between userId 1 and mediaId 1
    When I POST it to the route "/movie/users/1/watched" with token
    Then I receive a 200 status code

  Scenario: adding a watched relation as legit user for another user
    Given I am logged in as "pete842@mail.com" with password "totem"
    And I have a well-formed watched payload between userId 1 and mediaId 1
    When I POST it to the route "/movie/users/2/watched" with token
    Then I receive a 401 status code

  Scenario: deleting a watched relation as legit user
    Given I am logged in as "pete842@mail.com" with password "totem"
    When I DELETE the route "/movie/users/1/watched/1" with token
    Then I receive a 200 status code