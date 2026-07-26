@api
Feature: DummyAPI user and tag management
  As an API consumer
  I want to validate DummyAPI resources

  Scenario: Get the public list of tags
    Given the DummyAPI service is available
    When I request the public tag list
    Then the API response status should be 200
    And the tag response should contain a non-empty data list

  Scenario: Create, update, and delete a user
    Given the DummyAPI service is available
    When I create a DummyAPI user with first name "Automation" and last name "Tester"
    Then the API response status should be 200
    And the created user should have first name "Automation"
    When I retrieve the created user
    Then the API response status should be 200
    And the retrieved user should have last name "Tester"
    When I update the created user's first name to "UpdatedAutomation"
    Then the API response status should be 200
    And the updated user should have first name "UpdatedAutomation"
    When I delete the created user
    Then the API response status should be 200
    And the deletion response should confirm the user id
