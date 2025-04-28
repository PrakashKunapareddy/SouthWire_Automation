package com.vassarlabs.projectname.page;

import com.vassarlabs.projectname.driver.WebdriverInitializer;
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
import java.util.List;
import java.util.Map;


@Slf4j
public class validate_utility_data {
    WebDriver driver;
    WebDriverWait wait;
    Xpaths x_paths = new Xpaths();
    CommonMethods commonMethods = new CommonMethods(WebdriverInitializer.getDriver());

    public validate_utility_data(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void select_utility_and_select_years(String company) throws Throwable {
        wait.until(ExpectedConditions.presenceOfElementLocated(x_paths.years_dropdown));
        driver.findElement(x_paths.years_dropdown).click();
        driver.findElement(x_paths.select_15_years_years_dropdown).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(x_paths.select_utility_dropdown));
        driver.findElement(x_paths.select_utility_dropdown).click();
        String[] com_cat = company.split(",");
        driver.findElement(By.xpath("//li[@class='p-treenode p-treenode-leaf ng-star-inserted']//div[text()=' " + com_cat[0] + " ']")).click();
        driver.findElement(By.xpath("//li[@class='p-treenode p-treenode-leaf ng-star-inserted']//div[text()=' " + com_cat[1] + " ']")).click();
    }


    public void save_data_to_map(String dashboards, Map<Integer, Map<String, Integer>> excelDataMap) {
        String[] items = dashboards.split("@");
        for (String dashboardName : items) {
            commonMethods.navigate_to_given_dashboards(dashboardName);
            validate_data_for_sections_in_the_page(dashboardName, excelDataMap);
        }
    }

    public void validate_data_for_sections_in_the_page(String dashboardName, Map<Integer, Map<String, Integer>> excelDataMap) {
        switch (dashboardName) {
            case "UT, GEN, TD-SYS":
                validateSection(Xpaths.DASHBOARD1_SECTION_X_PATHS.get(0), "Total utility spend", excelDataMap);
                validateSection(Xpaths.DASHBOARD1_SECTION_X_PATHS.get(1), "Generation", excelDataMap);
                validateSection(Xpaths.DASHBOARD1_SECTION_X_PATHS.get(2), "Transmission,Distribution", excelDataMap);
                break;

            case "WC, T-WC, TOH, TUG":
                validateSection(Xpaths.DASHBOARD2_SECTION_X_PATHS.get(0), "Wires and Cables Spend", excelDataMap);
                validateSection(Xpaths.DASHBOARD2_SECTION_X_PATHS.get(1), "Transmission W&C spend", excelDataMap);
                validateSection(Xpaths.DASHBOARD2_SECTION_X_PATHS.get(2), "Underground Transmission W&C", excelDataMap);
                validateSection(Xpaths.DASHBOARD2_SECTION_X_PATHS.get(3), "Overhead Transmission W&C", excelDataMap);
                break;

            case "D-WC, DOH, MV, 600V":
                validateSection(Xpaths.DASHBOARD3_SECTION_X_PATHS.get(0), "Distribution W&C spend", excelDataMap);
                validateSection(Xpaths.DASHBOARD3_SECTION_X_PATHS.get(1), "Distribution Overhead W&C spend", excelDataMap);
                validateSection(Xpaths.DASHBOARD3_SECTION_X_PATHS.get(2), "Distribution Overhead MV W&C spend", excelDataMap);
                validateSection(Xpaths.DASHBOARD3_SECTION_X_PATHS.get(3), "Distribution Overhead 600V W&C spend", excelDataMap);
                break;

            case "DUG, MV, 600V":
                validateSection(Xpaths.DASHBOARD4_SECTION_X_PATHS.get(0), "Distribution Underground W&C spend", excelDataMap);
                validateSection(Xpaths.DASHBOARD4_SECTION_X_PATHS.get(1), "Distribution Underground MV W&C spend", excelDataMap);
                validateSection(Xpaths.DASHBOARD4_SECTION_X_PATHS.get(2), "Distribution Underground 600V W&C spend", excelDataMap);
                break;

            case "GEN Energy Sources":
                validate_data_energy_sources(excelDataMap);
                break;

            default:
                System.out.println("The dashboard is not available: " + dashboardName);
        }
    }

    private void validateSection(String sectionName, String spendType, Map<Integer, Map<String, Integer>> utilityData) {
        driver.findElement(By.xpath(sectionName)).click();
        validateSpend(spendType, utilityData);
        driver.navigate().back();
    }

    private void validateSpend(String spendType, Map<Integer, Map<String, Integer>> utilityData) {
        for (Map.Entry<Integer, Map<String, Integer>> entry : utilityData.entrySet()) {
            Integer year = entry.getKey();
            Integer expectedSpend;
            String[] spendTypeParts = spendType.split(",");
            if (spendTypeParts.length > 1) {
                expectedSpend = entry.getValue().get(spendTypeParts[0]) + entry.getValue().get(spendTypeParts[1]);
            } else {
                expectedSpend = entry.getValue().get(spendType);
            }
            WebElement yearCell = driver.findElement(By.xpath("//td/span[text()='" + year + "']"));
            String actualSpendText = yearCell.findElement(By.xpath("parent::td/following-sibling::td[1]/span")).getText().trim();
            Integer actualSpend = Integer.parseInt(actualSpendText);
            Assert.assertEquals(actualSpend, expectedSpend, "Expected " + spendType + " for year " + year + " is " + expectedSpend + ", but found: " + actualSpend);
        }
    }

    public void validate_data_energy_sources(Map<Integer, Map<String, Integer>> utilityData) {
        Map<String, Map<String, Map<String, Double>>> energySourcesData = validateEnergySources();

        Map<String, Integer> utility2030 = utilityData.get(2030);
        if (utility2030 != null) {
            for (Map.Entry<String, Map<String, Map<String, Double>>> entry : energySourcesData.entrySet()) {
                String utilityName = entry.getKey();
                Map<String, Double> sources2030 = entry.getValue().get("By 2030");

                for (Map.Entry<String, Double> sourceEntry : sources2030.entrySet()) {
                    String source = sourceEntry.getKey();
                    Double expectedValue = sourceEntry.getValue();
                    Integer actualValue = utility2030.get(source);

                    if (actualValue != null && expectedValue != null) {
                        Assert.assertEquals(expectedValue,actualValue.doubleValue());
                    }
                }
            }
        } else {
            Assert.fail("No utility data found for 2030");
        }

        Map<String, Integer> utility2035 = utilityData.get(2035);
        if (utility2035 != null) {
            for (Map.Entry<String, Map<String, Map<String, Double>>> entry : energySourcesData.entrySet()) {
                String utilityName = entry.getKey();
                Map<String, Double> sources2035 = entry.getValue().get("By 2035");

                for (Map.Entry<String, Double> sourceEntry : sources2035.entrySet()) {
                    String source = sourceEntry.getKey();
                    Double expectedValue = sourceEntry.getValue();
                    Integer actualValue = utility2035.get(source);

                    if (actualValue != null && expectedValue != null) {
                        Assert.assertEquals(expectedValue, actualValue.doubleValue());
                    }
                }
            }
        } else {
            Assert.fail("No utility data found for 2035");
        }
    }

    @FindBy(css = "tbody tr")
    public List<WebElement> tableRows;

    public Map<String, Map<String, Map<String, Double>>> validateEnergySources() {
        Map<String, Map<String, Map<String, Double>>> energyData = new HashMap<>();
        for (WebElement row : tableRows) {
            String utilityName = row.findElement(x_paths.energy_sources_spend_section_data_table).getText().trim();
            List<WebElement> cells = row.findElements(By.cssSelector("td.text-right span"));
            Map<String, Double> by2030 = new HashMap<>();
            by2030.put("Solar", parseValue(cells.get(0).getText()));
            by2030.put("Wind", parseValue(cells.get(1).getText()));
            by2030.put("Battery Storage", parseValue(cells.get(2).getText()));
            by2030.put("Natural Gas", parseValue(cells.get(3).getText()));
            by2030.put("Nuclear", parseValue(cells.get(4).getText()));

            Map<String, Double> by2035 = new HashMap<>();
            by2035.put("Solar", parseValue(cells.get(5).getText()));
            by2035.put("Wind", parseValue(cells.get(6).getText()));
            by2035.put("Battery Storage", parseValue(cells.get(7).getText()));
            by2035.put("Natural Gas", parseValue(cells.get(8).getText()));
            by2035.put("Nuclear", parseValue(cells.get(9).getText()));

            Map<String, Map<String, Double>> yearlyData = new HashMap<>();
            yearlyData.put("By 2030", by2030);
            yearlyData.put("By 2035", by2035);

            energyData.put(utilityName, yearlyData);
        }

        return energyData;
    }

    private Double parseValue(String text) {
        if (text.equals("-") || text.equalsIgnoreCase("N/A") || text.isEmpty()) {
            return 0.0;
        }
        else {
            return null;
        }
    }
}



    


