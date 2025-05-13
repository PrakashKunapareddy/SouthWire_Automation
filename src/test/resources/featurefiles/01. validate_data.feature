Feature: Utility Dashboard Data Validation

  Scenario Outline: Validate utility dashboard data for selected company against Excel Data
    Given Entered a valid <username> <password>
    When Clicked on the sign in button
    And Select <company> from the select Utility dropdown
    Then Select sections of data tables from various dashboards <dashboards> and validate the dashboard data with expected values from Excel <company>

    Examples:
      | username      | password     | dashboards                                                             | company                     |
      | "vassaradmin" | "Vassar@123" | "UT, GEN, TD-SYS@WC, T-WC, TOH, TUG@D-WC, DOH, MV, 600V@DUG, MV, 600V" | "TOP 10 Utilities,AEP"      |
