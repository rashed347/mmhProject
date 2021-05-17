Feature: As a user, I can login with the valid credential so that I can access the MMH as a registered user

  @AutoTest @Test-001
  Scenario: Verify that user can login with valid credential
    Given I am on the "provider" portal
    Given I navigate to Login page
    Given I login as a "Provider"
    Given I click on Invite a Patient link
    Given I select the invitation type "Assessment"
    Given I select the body region "Full Body"
    Given I enter the patient email "qatester347@gmail.com"
    When I click on send button
    When I click on Invite for Telemedicine button from confirmation modal
    When I create room with the name "mmhRoom"
    And I go to the room
