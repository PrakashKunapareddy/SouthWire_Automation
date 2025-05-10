package stepdefinitions;

import com.vassarlabs.projectname.driver.WebdriverInitializer;
import com.vassarlabs.projectname.page.validate_energy_sources_data;
import com.vassarlabs.projectname.utils.CommonMethods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.util.LinkedHashMap;
import java.util.Map;

public class validate_energy_sources_data_SD {
    CommonMethods commonMethods = new CommonMethods(WebdriverInitializer.getDriver());
    validate_energy_sources_data validationClass = new validate_energy_sources_data(WebdriverInitializer.getDriver());


    @And("Select {string} and select {string}")
    public void selectDashboardAndSelectCompany_group(String dashboard, String company_group) {
        commonMethods.navigate_to_given_dashboards(dashboard);
        commonMethods.select_company_group(company_group);
    }


    @Then("validate the dashboard data against excel data {string} {string}")
    public void validateTheDashboardDataAgainstExcelData(String company_group, String dashboard) throws Throwable {
        if(dashboard.equals("GEN Energy Sources")) {
            LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Double>>> result = commonMethods.read_energy_data_from_excel(company_group);
            validationClass.validate_dashboard_data_with_excel_data(result);
        } else if (dashboard.equals("GEN Growth Top 10, Region")) {
            LinkedHashMap<String, LinkedHashMap<String, Object>> excel_result = commonMethods.read_generation_data_from_excel();
            LinkedHashMap<String, LinkedHashMap<String, String>> ui_result = validationClass.extract_UI_generation_data();
            validationClass.compare_generation_data(excel_result,ui_result);
        }
    }
}
