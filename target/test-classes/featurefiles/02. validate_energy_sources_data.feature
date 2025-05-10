Feature: Utility Dashboard Data Validation For First Four Dashboards

  Scenario Outline: Validate Energy Sources dashboard data for selected company group against Excel Data
    Given Entered a valid <username> <password>
    When Clicked on the sign in button
    And Select <dashboard> and select <company_group>
    Then validate the dashboard data against excel data <company_group> <dashboard>

    Examples:
      | username      | password     | dashboard                   | company_group      |
#      | "vassaradmin" | "Vassar@123" | "GEN Energy Sources"        | "TOP 10 Utilities" |
      | "vassaradmin" | "Vassar@123" | "GEN Growth Top 10, Region" | "TOP 10 Utilities" |






