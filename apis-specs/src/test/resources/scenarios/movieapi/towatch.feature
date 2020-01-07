Feature: ToWatch@movie-api

  Scenario: querying a towatch page as legit user
    Given I am logged in as "pete842@mail.com" with password "totem"
    When I GET the route "/movie/users/1/towatch" with token
    Then I receive a 200 status code
    And I receive a towatch page of size 20

  Scenario: querying a towatch page with specific size as legit user
    Given I am logged in as "pete842@mail.com" with password "totem"
    When I GET the route "/movie/users/1/towatch?pageSize=10" with token
    Then I receive a 200 status code
    And I receive a towatch page of size 10

  Scenario: querying a towatch page
    When I GET the route "/movie/users/1/towatch"
    Then I receive a 401 status code

  Scenario: adding a towatch relation as legit user
    Given I am logged in as "pete842@mail.com" with password "totem"
    And I have a well-formed towatch payload between userId 1 and mediaId 1
    When I POST it to the route "/movie/users/1/towatch" with token
    Then I receive a 200 status code

  Scenario: deleting a towatch relation as legit user
    Given I am logged in as "pete842@mail.com" with password "totem"
    When I DELETE the route "/movie/users/1/towatch/1" with token
    Then I receive a 200 status code

  Scenario: adding a towatch relation as legit user for another user
    Given I am logged in as "pete842@mail.com" with password "totem"
    And I have a well-formed towatch payload between userId 1 and mediaId 1
    When I POST it to the route "/movie/users/2/towatch" with token
    Then I receive a 401 status code

  Scenario: upgrading a towatch relation to a watched one as legit user
    Given I am logged in as "pete842@mail.com" with password "totem"
    And I have a well-formed towatch relation between userId 1 and mediaId 1
    And I have a well-formed watched payload between userId 1 and mediaId 1
    When I PUT it to the route "/movie/users/1/towatch/1" with token
    Then I receive a 200 status code
    And I DELETE the route "/movie/users/1/watched/1" with token
    And I receive a 200 status code

  Scenario: upgrading a towatch relation to a watched one as legit user for another user
    Given I am logged in as "pete842@mail.com" with password "totem"
    And I have a well-formed watched payload between userId 2 and mediaId 1
    When I PUT it to the route "/movie/users/2/towatch/1" with token
    Then I receive a 401 status code