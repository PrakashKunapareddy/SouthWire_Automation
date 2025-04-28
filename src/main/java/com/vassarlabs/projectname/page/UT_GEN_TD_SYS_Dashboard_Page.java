package com.vassarlabs.projectname.page;

import com.vassarlabs.projectname.utils.CommonMethods;
import com.vassarlabs.projectname.utils.Xpaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.*;

public class UT_GEN_TD_SYS_Dashboard_Page {
    WebDriver driver;
    WebDriverWait wait;
    Xpaths x_paths = new Xpaths();
    CommonMethods commonMethods = new CommonMethods();


    public UT_GEN_TD_SYS_Dashboard_Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void validate_section_names(String Names) throws InterruptedException {
        String[] sec = Names.split(",");
        ArrayList<String> section_names = new ArrayList<>(Arrays.asList(sec));
        wait.until(ExpectedConditions.presenceOfElementLocated(x_paths.utility_spend_section));
        String Utility_spend = driver.findElement(x_paths.utility_spend_section).getText();
        wait.until(ExpectedConditions.presenceOfElementLocated(x_paths.Generation_spend_section));
        String Generation_spend = driver.findElement(x_paths.Generation_spend_section).getText();
        wait.until(ExpectedConditions.presenceOfElementLocated(x_paths.T_D_spend_section));
        String T_D_spend = driver.findElement(x_paths.T_D_spend_section).getText();
        Assert.assertTrue(section_names.contains(Utility_spend) & section_names.contains(Generation_spend) & section_names.contains(T_D_spend));
    }

    public void verify_filter_data(String data) {
        Map<String, Object> filter_data = commonMethods.extract_filter_data(data);
        driver.findElement(x_paths.select_utility_dropdown).click();
        String all_utilities = driver.findElement(x_paths.all_utilities).getText().trim();
        if (filter_data.containsKey("Select Utility")) {
            Object selectUtilityObj = filter_data.get("Select Utility");
            if (selectUtilityObj instanceof LinkedHashMap) {
                LinkedHashMap<String, List<String>> selectUtilityMap = (LinkedHashMap<String, List<String>>) selectUtilityObj;
                List<String> allUtilitiesFromFilterData = selectUtilityMap.get("All Utilities");
                if (allUtilitiesFromFilterData != null) {
                    String expectedAllUtilities = "";
                    if (allUtilitiesFromFilterData.size() > 1){
                         expectedAllUtilities = String.join(", ", allUtilitiesFromFilterData);
                    }
                    else{
                         expectedAllUtilities = allUtilitiesFromFilterData.get(0);
                    }
                    Assert.assertEquals(all_utilities, expectedAllUtilities);
                } else {
                    System.out.println("No data found for 'All Utilities'");
                }
            } else {
                System.out.println("'Select Utility' is not a LinkedHashMap as expected");
            }
        } else {
            System.out.println("'Select Utility' key not found in filter data");
        }
    }


    public void alter_filters(String select_utility_filter, String select_years_filter, String select_territory_filter) {
    }

    public void click_Report_Search_Button() {
    }

    public void validate_data_for_utility_spend(String utility_spend_data_heatmap, String utility_spend_data_yearwise, String utility_spend_data_pareto_chart) {
        HashMap<String, HashMap<String, HashMap<String, Double>>> heatMapData = new HashMap<>(commonMethods.extract_data_for_heatmap(utility_spend_data_heatmap));
        HashMap<Integer, Integer> year_wisedata = new HashMap<>(commonMethods.extract_yearwise_data(utility_spend_data_yearwise));
        HashMap<String, Integer> paretoChartData = new HashMap<>(commonMethods.extract_paretoChart_data(utility_spend_data_pareto_chart));

    }


}
