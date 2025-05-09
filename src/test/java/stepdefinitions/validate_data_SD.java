package stepdefinitions;

import com.vassarlabs.projectname.driver.WebdriverInitializer;
import com.vassarlabs.projectname.page.validate_utility_data;
import com.vassarlabs.projectname.utils.CommonMethods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.util.LinkedHashMap;
import java.util.Map;

public class validate_data_SD {

    CommonMethods commonMethods = new CommonMethods(WebdriverInitializer.getDriver());
    validate_utility_data validationClass = new validate_utility_data(WebdriverInitializer.getDriver());

    @And("Select {string} from the select Utility dropdown")
    public void selectCompanyFromTheSelectUtilityDropdown(String company) throws Throwable {
        validationClass.select_utility_and_select_years(company);
    }

    @Then("Select sections of data tables from various dashboards {string} and validate the dashboard data with expected values from Excel {string}")
    public void selectSectionsOfDataTablesFromVariousDashboardsDashboardsAndValidateTheDashboardDataWithExpectedValuesFromExcelCompany(String dashboards, String company) throws Throwable {
        LinkedHashMap<Integer, LinkedHashMap<String, Object>> result = commonMethods.read_data_from_excel(company.split(",")[1]);
        validationClass.save_data_to_map(dashboards, result);
    }
}
