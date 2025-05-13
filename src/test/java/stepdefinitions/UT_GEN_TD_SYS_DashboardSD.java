package stepdefinitions;

import com.vassarlabs.projectname.driver.WebdriverInitializer;
import com.vassarlabs.projectname.page.UT_GEN_TD_SYS_Dashboard_Page;
import io.cucumber.java.en.And;

public class UT_GEN_TD_SYS_DashboardSD {
    UT_GEN_TD_SYS_Dashboard_Page UT_GEN_TD_SYS_Dashboard = new UT_GEN_TD_SYS_Dashboard_Page(WebdriverInitializer.getDriver());

    @And("Validate the {string}")
    public void validateTheSection_names(String section_names) throws InterruptedException {
        UT_GEN_TD_SYS_Dashboard.validate_section_names(section_names);
    }

    @And("Validate the {string} {string} {string} {string}")
    public void validateTheSelect_utility_filterSelect_years_filterSelect_territory_filter(String select_utility_filter, String select_years_filter, String select_territory_filter, String filter_data) {
        UT_GEN_TD_SYS_Dashboard.verify_filter_data(filter_data);
        UT_GEN_TD_SYS_Dashboard.alter_filters(select_utility_filter, select_years_filter, select_territory_filter);
    }

    @And("Validate the Report Search Button for utility spend with {string} {string} {string}")
    public void validateTheReportSearchButtonForUtilitySpendWithUtility_spend_data(String utility_spend_data_heatmap, String utility_spend_data_yearwise, String utility_spend_data_pareto_chart) {
        UT_GEN_TD_SYS_Dashboard.click_Report_Search_Button();
        UT_GEN_TD_SYS_Dashboard.validate_data_for_utility_spend(utility_spend_data_heatmap,utility_spend_data_yearwise,utility_spend_data_pareto_chart);
    }

    @And("Validate the Report Search Button for Generation spend with {string} {string} {string}")
    public void validateTheReportSearchButtonForGenerationSpendWithGeneration_spend_data(String generation_spend_data_heatmap, String generation_spend_data_yearwise, String generation_spend_data_pareto_chart) {

    }

    @And("Validate the Report Search Button for T&D system spend with {string} {string} {string}")
    public void validateTheReportSearchButtonForTDSystemSpendWithT_D_systems_spend_data(String T_D_systems_spend_data_heatmap, String T_D_systems_spend_data_yearwise, String T_D_systems_spend_data_pareto_chart) {

    }

}
