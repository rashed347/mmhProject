Feature: As a patient, I can complete the selfassessment by using mskbot and get the report according to that assessment

  @AutoTest @Test-004
  Scenario: Verify that the existing patient can complete the self assessment
    Given I am on the "patient" portal
    Given I navigate to Login page
    Given I login as a "Patient"
    When I initiate "MSK Bot" from patient dashboard

