Feature: Role management

  Scenario: Get all user roles
    Given I am logged into SDE_A application
    When i request all user roles
    Then i verify that all permissions are returned

  Scenario: Get permissions from user role
    Given I am logged into SDE_A application
    When i request the User permissions
    Then i verify that the User permissions are returned

  Scenario: Get permissions from creator role
    Given I am logged into SDE_A application
    When i request the Creator permissions
    Then i verify that the Creator permissions are returned

  Scenario: Get permissions from admin role
    Given I am logged into SDE_A application
    When i request the Admin permissions
    Then i verify that the Admin permissions are returned

  Scenario: Create a new role with permissions and delete it afterwards
    Given I am logged into SDE_A application
    When i create "e2eRole" as a new  role
    When i add permissions to "e2eRole"
    Then i verify that the  role "e2eRole" is created
    When i delete the "e2eRole" role
    Then i verify that the "e2eRole" role is deleted
