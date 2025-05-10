package com.vassarlabs.projectname.page;

import com.vassarlabs.projectname.utils.CommonMethods;
import com.vassarlabs.projectname.utils.Xpaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;

public class validate_energy_sources_data {
    WebDriver driver;
    WebDriverWait wait;
    Xpaths x_paths = new Xpaths();
    CommonMethods commonMethods;

    public validate_energy_sources_data(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.commonMethods = new CommonMethods(driver);
    }

    public void validate_dashboard_data_with_excel_data(LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Double>>> data) {
        List<WebElement> rows = driver.findElements(x_paths.top_10_utilities_gw_table);
        for (WebElement row : rows) {
            String utilityName = row.findElement(x_paths.utility_names_for_energy_source).getText().trim();
            for (String year : data.keySet()) {
                LinkedHashMap<String, Double> yearData = data.get(year).get(utilityName);
                if (yearData == null) {
                    System.out.println("Data not available for utility " + utilityName + " for year " + year);
                    continue;
                }
                String[] energySources = {"Solar", "Wind", "Battery Storage", "Natural Gas", "Nuclear"};
                for (int i = 0; i < energySources.length; i++) {
                    String energySource = energySources[i];
                    int columnIndex = (year.equals("2030")) ? i + 2 : i + 7;

                    WebElement tableCell = row.findElement(By.xpath("//h3[text()='Top 10 Utility (GW)']//parent::div/parent::div/parent::app-prime-table/p-table//table//tr//td[" + columnIndex + "]"));
                    String tableValueText = tableCell.getText().trim();
                    double tableValue = tableValueText.equals("-") ? 0.0 : Double.parseDouble(tableValueText);
                    double expectedValue = yearData.getOrDefault(energySource, 0.0);

                    System.out.println("Year: " + year + " | Utility: " + utilityName + " | Energy Source: " + energySource);
                    System.out.println("Column Index: " + columnIndex);
                    System.out.println("Expected Value: " + expectedValue + " | Table Value: " + tableValue);

                    Assert.assertEquals(expectedValue, tableValue,
                            "Mismatch for " + energySource + " in " + utilityName + " for year " + year + ": Expected " + expectedValue + " but found " + tableValue);
                }
            }
        }
    }

    public LinkedHashMap<String, LinkedHashMap<String, String>> extract_UI_generation_data() {
        LinkedHashMap<String, LinkedHashMap<String, String>> tableData = new LinkedHashMap<>();
        List<WebElement> rows = driver.findElements(x_paths.generation_section_data_table);
        for (WebElement row : rows) {
            String companyName = row.findElement(By.xpath("./td[1]")).getText().trim();
            String value2025 = row.findElement(By.xpath("./td[2]")).getText().trim();
            String value2030 = row.findElement(By.xpath("./td[3]")).getText().trim();
            String actualGWGrowth2030 = row.findElement(By.xpath("./td[4]")).getText().trim();
            String growthPercentage2030 = row.findElement(By.xpath("./td[5]")).getText().trim();
            String value2035 = row.findElement(By.xpath("./td[6]")).getText().trim();
            String actualGWGrowth2035 = row.findElement(By.xpath("./td[7]")).getText().trim();
            String growthPercentage2035 = row.findElement(By.xpath("./td[8]")).getText().trim();

            LinkedHashMap<String, String> companyData = new LinkedHashMap<>();
            companyData.put("2025", value2025);
            companyData.put("2030", value2030);
            companyData.put("Actual GW Growth of 2030", actualGWGrowth2030);
            companyData.put("2030 Growth percentage", growthPercentage2030);
            companyData.put("2035", value2035);
            companyData.put("Actual GW Growth of 2035", actualGWGrowth2035);
            companyData.put("2035 Growth percentage", growthPercentage2035);

            tableData.put(companyName, companyData);
        }
        return tableData;
    }

    public void compare_generation_data(LinkedHashMap<String, LinkedHashMap<String, Object>> excel_data, LinkedHashMap<String, LinkedHashMap<String, String>> UI_data) throws Throwable {
        for (String companyName : excel_data.keySet()) {
            if (UI_data.containsKey(companyName)) {
                LinkedHashMap<String, Object> excelRow = excel_data.get(companyName);
                LinkedHashMap<String, String> uiRow = UI_data.get(companyName);
                for (String key : excelRow.keySet()) {
                    if (uiRow.containsKey(key)) {
                        Object excelValue = excelRow.get(key);
                        String uiValue = uiRow.get(key);
                        System.out.println(uiValue+"----->"+excelValue);
                        Assert.assertEquals(excelValue.toString().replaceAll("%",""), uiValue.replaceAll("%",""), "Mismatch found for company: " + companyName + " and key: " + key);
                    }
                }
            }
        }
    }
}

