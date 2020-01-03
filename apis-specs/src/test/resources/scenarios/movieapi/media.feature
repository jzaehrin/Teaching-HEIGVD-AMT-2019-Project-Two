Feature: Media@movie-api

  Scenario: querying a media page as legit user
    Given I am logged in as "pete842@mail.com" with password "totem"
    When I GET the route "/movie/media" with token
    Then I receive a 200 status code
    And I receive a media page of size 20

  Scenario: querying a media page as legit user
    Given I am logged in as "pete842@mail.com" with password "totem"
    When I GET the route "/movie/media?pageSize=30" with token
    Then I receive a 200 status code
    And I receive a media page of size 30

  Scenario: querying a page with invalid size as legit user
    Given I am logged in as "pete842@mail.com" with password "totem"
    When I GET the route "/movie/media?pageSize=-10" with token
    Then I receive a 500 status code

  Scenario: querying a invalid page as legit user
    Given I am logged in as "pete842@mail.com" with password "totem"
    When I GET the route "/movie/media?pageNumber=-10" with token
    Then I receive a 500 status code

  Scenario: querying a media page
    When I GET the route "/movie/media"
    Then I receive a 401 status code

  Scenario: adding a media as legit admin
    Given I am logged in as "pete842@mail.com" with password "totem"
    And I have a well-formed media payload
    When I POST it to the route "/movie/media" with token
    Then I receive a 200 status code

  Scenario: adding a media as legit user
    Given I am logged in as "capito27@mail.com" with password "totem"
    And I have a well-formed media payload
    When I POST it to the route "/movie/media" with token
    Then I receive a 200 status code

  Scenario: adding a media
    Given I have a well-formed media payload
    When I POST it to the route "/movie/media"
    Then I receive a 401 status code
