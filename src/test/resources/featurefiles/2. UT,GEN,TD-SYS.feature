Feature: Dashboard UT, GEN, TD-SYS Functionality

  Scenario Outline: Data in the dashboard UT, GEN, TD-SYS with multiple filters should match the given data
    Given Entered a valid <username> <password>
    When Clicked on the sign in button
    And

    Examples:
      | username        | password         |
      | "southwireuser" | "Southwire@123"  |