Feature: Role management

  Scenario: Get all user roles
    Given I am logged into SDE_A application
    When I request all user roles

  Scenario: Get permissions from User role
    Given I am logged into SDE_A application
    When i request the user permissions

  Scenario: Get permissions from User role
    Given I am logged into SDE_A application
    When i request the creator permissions

  Scenario: Get permissions from User role
    Given I am logged into SDE_A application
    When i request the admin permissions