Feature: Create Data (/{submodel}/upload)

  Scenario: Create data with submodel batch
    Given I am logged into SDE_A application
    When I upload Data with the batch submodel
      | "uuid" | "urn:uuid:8eea5f45-0823-48ce-a4fc-c3bf1cdfa4c9" |
    Then I check if the batch data with the uuid: "urn:uuid:8eea5f45-0823-48ce-a4fc-c3bf1cdfa4c9" is uploaded
    When I delete the checked batch data
    Then I check if the batch data with the uuid: "urn:uuid:8eea5f45-0823-48ce-a4fc-c3bf1cdfa4c9" is deleted


