Feature: Admin@auth-api

  Scenario: adding a correct user as admin
    Given I am logged in as "pete842@mail.com" with password "totem"
    And I have a well-formed user payload
    When I POST it to the route "/auth/admin/users" with token
    Then I receive a 200 status code

  Scenario: adding a correct user as non-admin
    And I have a well-formed user payload
    When I POST it to the route "/auth/admin/users"
    Then I receive a 401 status code
