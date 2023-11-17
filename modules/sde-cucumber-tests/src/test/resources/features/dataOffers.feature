Feature: Handle data offers

  Scenario: Query data offers from provider
    Given I am logged into SDE_A application
    When I query the data offers from "endpoint"
    Then data offers should be returned

  Scenario: Subscribe data offers from provider
    Given I am logged into SDE_A application
    When I subscribe the data offers
      | providerUrl | endpoint         |
      | connectorId | Test324893245634 |
    Then a process id should be returned

  Scenario: Download Data Offers
    Given I am logged into SDE_A application
    When I subscribe the data offers
      | providerUrl | endpoint         |
      | connectorId | Test324893245634 |
    When I download data offers
    Then I check the download history