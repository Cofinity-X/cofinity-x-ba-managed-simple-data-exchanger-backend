Feature: Manage contract agreements (/contract-agreements/)

  Scenario: Get consumer contract agreements
    Given I am logged into SDE_A application
    When I search for the consumer contract agreements

  Scenario: Decline provider contract
    Given I am logged into SDE_A application
    When I decline the provider contract with the id "TestID2358274"

  Scenario: Cancel provider contract
    Given I am logged into SDE_A application
    When I cancel the provider contract with the id "TestID2358278"

  Scenario: Cancel consumer contract
    Given I am logged into SDE_A application
    When I cancel the consumer contract with the id "TestID2358279"

  Scenario: Decline consumer contract
    Given I am logged into SDE_A application
    When I decline the consumer contract with the id "TestID2358271"
