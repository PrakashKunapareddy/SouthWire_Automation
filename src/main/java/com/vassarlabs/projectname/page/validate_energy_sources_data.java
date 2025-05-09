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
        // Retrieve the rows of the table using XPath from x_paths
        List<WebElement> rows = driver.findElements(x_paths.top_10_utilities_gw_table);

        for (WebElement row : rows) {
            // Extract the utility name from the first column using XPath
            String utilityName = row.findElement(x_paths.utility_names_for_energy_source).getText().trim();

            // Iterate through the years (2030 and 2035)
            for (String year : data.keySet()) {
                // Retrieve the data for the specific utility and year from the input data
                LinkedHashMap<String, Double> yearData = data.get(year).get(utilityName);

                // If no data is found for the current year and utility, log it and continue
                if (yearData == null) {
                    System.out.println("Data not available for utility " + utilityName + " for year " + year);
                    continue;
                }

                // List of energy sources to validate
                String[] energySources = {"Solar", "Wind", "Battery Storage", "Natural Gas", "Nuclear"};

                for (int i = 0; i < energySources.length; i++) {
                    String energySource = energySources[i];

                    // Updated XPath: Column number should be year-dependent
                    int columnIndex = (year.equals("2030")) ? i + 2 : i + 7; // Assuming 2030 starts from index 2 and 2035 starts from 7 (adjust as needed)

                    // Retrieve the table cell for the current energy source using XPath for the specific year
                    WebElement tableCell = row.findElement(By.xpath("//h3[text()='Top 10 Utility (GW)']//parent::div/parent::div/parent::app-prime-table/p-table//table//tr//td[" + columnIndex + "]"));

                    // Extract the table value
                    String tableValueText = tableCell.getText().trim();
                    double tableValue = tableValueText.equals("-") ? 0.0 : Double.parseDouble(tableValueText);

                    // Retrieve the expected value from the JSON data
                    double expectedValue = yearData.getOrDefault(energySource, 0.0);

                    // Debugging: Print values for comparison
                    System.out.println("Year: " + year + " | Utility: " + utilityName + " | Energy Source: " + energySource);
                    System.out.println("Column Index: " + columnIndex);
                    System.out.println("Expected Value: " + expectedValue + " | Table Value: " + tableValue);

                    // Assert the expected value matches the actual value from the table
//                    try {
                        Assert.assertEquals(expectedValue, tableValue,
                                "Mismatch for " + energySource + " in " + utilityName + " for year " + year + ": Expected " + expectedValue + " but found " + tableValue);
//                    } catch (AssertionError e) {
//                        // Log detailed mismatch for debugging purposes
//                        System.out.println(e.getMessage());
//                    }
                }
            }
        }
    }
}

