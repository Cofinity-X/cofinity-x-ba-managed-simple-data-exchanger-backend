Feature: Handle data offers

  Scenario: Query data offers from provider
    Given I am logged into SDE_A application
    When I query the data offers from "http://test.com"

  Scenario: Subscribe data offers from provider
    Given I am logged into SDE_A application
    When I subscribe the data offers
    | providerUrl    |  http://test.com    |
    | connectorId    |  Test324893245634   |
