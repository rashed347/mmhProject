Feature: As a provider, I can complete the assessment for a new in-clinic patient and get the report according to that assessment

  @AutoTest @Test-004
  Scenario Outline: Verify that the provider can complete the inclinic assessment
    Given I am on the provider portal
    Given I navigate to Login page
    Given I login as a "Provider"
    When I present the "Assessment Type > Inclinic Assessment > <Body Region>" page
    When I fillup the "demographics information" form in assessment with the following information
      | Field Name            | Field Type   | Field Value         |
      | Assessment Type       | RadioButton  | New Assessment      |
      | First Name            | TextField    | testFirstName       |
      | Last Name             | TextField    | testLastName        |
      | Email                 | TextField    | testmmh@yopmail.com |
      | Date Of Birth         | DatePicker   | 04/10/1986          |
      | Height                | HeightPicker | 5' 7"               |
      | Weight                | TextField    | 180                 |
      | Gender                | RadioButton  | Male                |
      | Ethnic Identification | Dropdown     | Asian               |
      | Dominant Hand         | RadioButton  | Right               |
      | Submission Date       | ReadOnly     | currentDate         |
      | Employer              | TextField    | My Medical Hub      |
      | Occupation            | TextField    | QA                  |
      | Place of Service      | Dropdown     | My MedicalHub       |
    When I move to the next tab
    And I fillup the "ADL" form in assessment with the following information
    And I fillup the "Medical History" form in assessment with the following information
    And I fillup the chief complaints for the following body parts
      | Body |



    Examples:
      | Body Region |
      | Full Body   |

  @AutoTest @Test-003
  Scenario: Fillup Intake Form
    Given I am on the provider portal
    Given I navigate to Login page
    Given I fillup the intake registration information form with the following information
      | Field Name       | Field Type | Field Value                |
      | Email            | ReadOnly   | intakeformtest@yopmail.com |
      | Password         | TextField  | One_2_three                |
      | Confirm Password | TextField  | One_2_three                |
    Given I fillup the intake information form with the following information
      | Field Name    | Field Type   | Field Value                |
      | First Name    | TextField    | testFirstName              |
      | Last Name     | TextField    | testLastName               |
      | Email         | ReadOnly     | intakeformtest@yopmail.com |
      | Date Of Birth | DatePicker   | 04/10/1986                 |
      | Height        | HeightPicker | 5' 7"                      |
      | Weight        | TextField    | 180                        |
      | Gender        | RadioButton  | Male                       |