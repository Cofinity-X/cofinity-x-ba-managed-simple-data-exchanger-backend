Feature: Manage contract agreements (/contract-agreements/)

  Scenario: Get consumer contract agreements
    Given I am logged into SDE_A application
    When I search for the consumer contract agreements
    Then I check if contracts are returned

  Scenario: Decline provider contract
    Given I am logged into SDE_A application
    When I decline the provider contract with the id "TestID2358274"
    # check if contract agreement still exists

  Scenario: Cancel provider contract
    Given I am logged into SDE_A application
    When I cancel the provider contract with the id "TestID2358278"
    # check if contract agreement still exists


  Scenario: Cancel consumer contract
    Given I am logged into SDE_A application
    When I cancel the consumer contract with the id "TestID2358279"
    # check if contract agreement still exists


  Scenario: Decline consumer contract
    Given I am logged into SDE_A application
    When I decline the consumer contract with the id "TestID2358271"
    # check if contract agreement still exists