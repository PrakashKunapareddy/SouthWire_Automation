Feature: Login Functionality for SouthWire Login

  Scenario Outline: Successful Login with valid username and password
    Given Entered a valid <username> <password>
    When Clicked on the sign in button
    And Validate login <expected_output>

    Examples:
      | username        | password         | expected_output                |
#      Valid Credentials
      | "southwiremain" | "Southwire@123"  | "utilityDashbord"              |
#      InValid Credentials
      | "vassaruser"    | "wrongPassword"  | "Invalid Username or Password" |
      | "user!@#123"    | "Vassarlabs@123" | "Invalid Username or Password" |
      | ""              | "password123"    | "Invalid Username or Password" |
      | "user"          | ""               | "Invalid Username or Password" |
      | ""              | ""               | "Invalid Username or Password" |


