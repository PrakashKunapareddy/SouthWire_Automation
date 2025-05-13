package com.vassarlabs.projectname.page;

import com.vassarlabs.projectname.utils.CommonMethods;
import com.vassarlabs.projectname.utils.Xpaths;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class validate_utility_data {
    WebDriver driver;
    WebDriverWait wait;
    Xpaths x_paths = new Xpaths();
    CommonMethods commonMethods;

    public validate_utility_data(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.commonMethods = new CommonMethods(driver);
    }

    public void select_utility_and_select_years(String company) throws InterruptedException {
        log.info("Entered select_utility_and_select_years");
        Thread.sleep(10000);
        wait.until(ExpectedConditions.elementToBeClickable(x_paths.years_dropdown));
        wait.until(ExpectedConditions.elementToBeClickable(x_paths.select_15_years_years_dropdown)).click();
        Thread.sleep(10000);
        wait.until(ExpectedConditions.elementToBeClickable(x_paths.select_utility_dropdown)).click();
        String[] com_cat = company.split(",");
        driver.findElement(By.xpath("//div[text()=' " + com_cat[0] + " ']/parent::span/parent::span/parent::div/button")).click();
        System.out.println("selected section");
        Thread.sleep(10000);
        driver.findElement(By.xpath("//div[text()=' " + com_cat[1] + " ']/parent::span/parent::span/parent::div/parent::li/parent::p-treenode")).click();

    }

    public void save_data_to_map(String dashboards, LinkedHashMap<Integer, LinkedHashMap<String, Object>> excelDataMap) throws Throwable {
        log.info("Entered save_data_to_map");
        String[] dashboardGroups = dashboards.split("@");
        for (int i = 0; i < dashboardGroups.length; i++) {
            String trimmedGroup = dashboardGroups[i];
            validate_data_for_sections_in_the_page(trimmedGroup, excelDataMap);
            if (i + 1 < dashboardGroups.length) {
                commonMethods.navigate_to_given_dashboards(dashboardGroups[i + 1]);
            }
        }
    }


    public void validate_data_for_sections_in_the_page(String dashboardGroup, LinkedHashMap<Integer, LinkedHashMap<String, Object>> excelDataMap) throws Throwable {
        log.info("Validating data for dashboard group: {}", dashboardGroup);

        if (dashboardGroup.equals("UT, GEN, TD-SYS")) {
            validateSection(Xpaths.DASHBOARD1_SECTION_X_PATHS.get(0), "Total utility spend", excelDataMap);
            validateSection(Xpaths.DASHBOARD1_SECTION_X_PATHS.get(1), "Generation", excelDataMap);
            validateSection(Xpaths.DASHBOARD1_SECTION_X_PATHS.get(2), "Transmission & Distribution", excelDataMap);
        } else if (dashboardGroup.equals("WC, T-WC, TOH, TUG")) {
            validateSection(Xpaths.DASHBOARD2_SECTION_X_PATHS.get(0), "Wires and Cables Spend", excelDataMap);
            validateSection(Xpaths.DASHBOARD2_SECTION_X_PATHS.get(1), "Transmission W&C spend", excelDataMap);
            validateSection(Xpaths.DASHBOARD2_SECTION_X_PATHS.get(2), "Overhead Transmission WC", excelDataMap);
            validateSection(Xpaths.DASHBOARD2_SECTION_X_PATHS.get(3), "Underground Transmission W&C", excelDataMap);
        } else if (dashboardGroup.equals("D-WC, DOH, MV, 600V")) {
            validateSection(Xpaths.DASHBOARD3_SECTION_X_PATHS.get(0), "Distribution W&C spend", excelDataMap);
            validateSection(Xpaths.DASHBOARD3_SECTION_X_PATHS.get(1), "Distribution Overhead W&C spend", excelDataMap);
            validateSection(Xpaths.DASHBOARD3_SECTION_X_PATHS.get(2), "Distribution Overhead MV W&C spend", excelDataMap);
            validateSection(Xpaths.DASHBOARD3_SECTION_X_PATHS.get(3), "Distribution Overhead 600V W&C spend", excelDataMap);
        } else if (dashboardGroup.equals("DUG, MV, 600V")) {
            validateSection(Xpaths.DASHBOARD4_SECTION_X_PATHS.get(0), "Distribution Underground W&C spend", excelDataMap);
            validateSection(Xpaths.DASHBOARD4_SECTION_X_PATHS.get(1), "Distribution Underground MV W&C spend", excelDataMap);
            validateSection(Xpaths.DASHBOARD4_SECTION_X_PATHS.get(2), "Distribution Underground 600V W&C spend", excelDataMap);
        } else {
            log.warn("Unknown dashboard group: {}", dashboardGroup);
        }
    }

    private void validateSection(String sectionXPath, String spendType, LinkedHashMap<Integer, LinkedHashMap<String, Object>> utilityData) throws Throwable {
        System.out.println(sectionXPath);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(sectionXPath)));
        if (!(sectionXPath.equals(Xpaths.DASHBOARD1_SECTION_X_PATHS.get(0)) || sectionXPath.equals(Xpaths.DASHBOARD1_SECTION_X_PATHS.get(1)) || sectionXPath.equals(Xpaths.DASHBOARD1_SECTION_X_PATHS.get(2)))) {
            Thread.sleep(13000);
        } else {
            Thread.sleep(7000);
        }
        Thread.sleep(5000);
        driver.findElement(By.xpath(sectionXPath)).click();
        validateSpend(spendType, utilityData);
        driver.navigate().back();
    }

    private void validateSpend(String spendType, LinkedHashMap<Integer, LinkedHashMap<String, Object>> utilityData) {
        String[] spendParts = spendType.split(",");
        System.out.println(utilityData);
        for (Map.Entry<Integer, LinkedHashMap<String, Object>> entry : utilityData.entrySet()) {
            Integer year = entry.getKey();
            Map<String, Object> yearData = entry.getValue();

            double expectedSpend = 0.00;
            double value = 0;
            for (String type : spendParts) {
                Object valObj = yearData.getOrDefault(type, "");
                if (valObj instanceof String) {
                    value = Double.parseDouble((String) valObj);
                } else {
                    value = (Double) yearData.get(type);


                }
                expectedSpend += value;
            }
            WebElement yearCell = driver.findElement(By.xpath("//td/span[text()=' " + year + " ']"));
            String actualSpendText = yearCell.findElement(By.xpath("parent::td/following-sibling::td[1]/span")).getText();
            double actualSpend = Double.parseDouble(actualSpendText.replace(",", ""));

            log.info("Year: {}, SpendType: {}, Expected: {}, Actual: {}", year, spendType, expectedSpend, actualSpend);
            Assert.assertEquals(actualSpend, expectedSpend, "Mismatch in spend for year " + year + ", spend type: " + spendType);
        }
    }
}



