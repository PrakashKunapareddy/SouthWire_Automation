Feature: Utility Dashboard Data Validation

  Scenario Outline: Validate utility dashboard data for selected company against Excel Data
    Given Entered a valid <username> <password>
    When Clicked on the sign in button
    And Select <company> from the select Utility dropdown
    Then Select sections of data tables from various dashboards <dashboards> and validate the dashboard data with expected values from Excel <company>

    Examples:
      | username      | password     | dashboards                                                                                | company                     |
      | "vassaradmin" | "Vassar@123" | "UT, GEN, TD-SYS@WC, T_WC, TOH, TUG@D-WC, DOH, MV, 600V@DUG, MV, 600V@GEN Energy Sources" | "TOP 10 Utilities,AEP"      |
      | "vassaradmin" | "Vassar@123" | "UT, GEN, TD-SYS@WC, T_WC, TOH, TUG@D-WC, DOH, MV, 600V@DUG, MV, 600V@GEN Energy Sources" | "TOP 10 Utilities,CONED"    |
      | "vassaradmin" | "Vassar@123" | "UT, GEN, TD-SYS@WC, T_WC, TOH, TUG@D-WC, DOH, MV, 600V@DUG, MV, 600V@GEN Energy Sources" | "TOP 10 Utilities,DOMINION" |
      | "vassaradmin" | "Vassar@123" | "UT, GEN, TD-SYS@WC, T_WC, TOH, TUG@D-WC, DOH, MV, 600V@DUG, MV, 600V@GEN Energy Sources" | "TOP 10 Utilities,DTE"      |
      | "vassaradmin" | "Vassar@123" | "UT, GEN, TD-SYS@WC, T_WC, TOH, TUG@D-WC, DOH, MV, 600V@DUG, MV, 600V@GEN Energy Sources" | "TOP 10 Utilities,DUKE"     |
      | "vassaradmin" | "Vassar@123" | "UT, GEN, TD-SYS@WC, T_WC, TOH, TUG@D-WC, DOH, MV, 600V@DUG, MV, 600V@GEN Energy Sources" | "TOP 10 Utilities,EXELON"   |
      | "vassaradmin" | "Vassar@123" | "UT, GEN, TD-SYS@WC, T_WC, TOH, TUG@D-WC, DOH, MV, 600V@DUG, MV, 600V@GEN Energy Sources" | "TOP 10 Utilities,NEXTERA"  |
      | "vassaradmin" | "Vassar@123" | "UT, GEN, TD-SYS@WC, T_WC, TOH, TUG@D-WC, DOH, MV, 600V@DUG, MV, 600V@GEN Energy Sources" | "TOP 10 Utilities,PG&E"     |
      | "vassaradmin" | "Vassar@123" | "UT, GEN, TD-SYS@WC, T_WC, TOH, TUG@D-WC, DOH, MV, 600V@DUG, MV, 600V@GEN Energy Sources" | "TOP 10 Utilities,SCE"      |


