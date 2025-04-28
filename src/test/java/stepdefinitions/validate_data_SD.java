package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class validate_data_SD {


    @And("Select {string} from the select Utility dropdown")
    public void selectCompanyFromTheSelectUtilityDropdown(String company) {
    }

    @And("Navigate to different Dashboards")
    public void navigateToDifferentDashboards() {
    }

    @Then("validate the dashboard data with expected values from Excel {string}")
    public void validateTheDashboardDataWithExpectedValuesFromExcelCompany(String company) {
    }
}
