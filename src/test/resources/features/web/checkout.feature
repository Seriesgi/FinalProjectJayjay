@web
Feature: Demoblaze checkout
  As a shopper
  I want to buy a product
  So that I can complete my order successfully

  Scenario: Guest completes an end-to-end checkout
    Given I open the Demoblaze store
    When I add the first available product to the cart
    And I complete checkout with customer name "Automation Tester"
    Then the order should be placed successfully
