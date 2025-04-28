Feature: Utility Dashboard Data Validation

  Scenario Outline: Validate utility dashboard data for selected company against Excel Data
    Given Entered a valid <username> <password>
    When Clicked on the sign in button
    And Validate login <expected_output>
    And Select <company> from the select Utility dropdown
    And Navigate to different Dashboards
    Then validate the dashboard data with expected values from Excel <company>

    Examples:
    Examples:
      | username      | password     | expected_output   | company |
      | "vassaradmin" | "vassar@123" | "utilityDashbord" | "AEP"   |