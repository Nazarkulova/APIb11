Feature: Test all pet operations

  Scenario: Create pet

    Given I have valid url to create a pet
    When I send POST request to create a pet
    Then status code should be 200
    And response should be in json format

    Scenario: Get a pet
      Given I have valid url to get a pet
      When I send GET request to retieve a pet with 2223334 id
      Then status code  200
      And reponse body should contain 2223334 id

      Scenario: Update a pet
        Given I have a valid url to update a pet
        When I send PUT request to update a pet
        Then status should be 200
        And response body should be application/json

        Scenario: Delete  a pet
          Given I send DELETE request to delete a pet with id 2223334
          Then  status should be 200
          And pet with id 2223334 should not exist