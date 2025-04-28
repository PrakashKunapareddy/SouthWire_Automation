package com.vassarlabs.projectname.utils;

import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.*;

import static com.vassarlabs.projectname.utils.Constants.EXCEL_PATH_FOR_DATA_VALIDATION;

public class CommonMethods {
    WebDriverWait wait;
    WebDriver driver;
    public CommonMethods(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public HashMap<String, HashMap<String, HashMap<String, Double>>> extract_data_for_heatmap(String input) {
        HashMap<String, HashMap<String, HashMap<String, Double>>> utilityData = new HashMap<>();
        String[] entries = input.split(",");
        for (String entry : entries) {
            String[] parts = entry.split("@");

            String utilityRegionState = parts[0];
            double value;
            value = Double.parseDouble(parts[1]);

            String[] utilityRegionStateParts = utilityRegionState.split("-");

            String utility_and_region = utilityRegionStateParts[0];
            String state = utilityRegionStateParts[1];

            String utility = utility_and_region.split("_")[0];
            String region = utility_and_region.split("_")[1];

            utilityData
                    .computeIfAbsent(utility, k -> new HashMap<>())
                    .computeIfAbsent(region, k -> new HashMap<>())
                    .put(state, value);
        }
        return utilityData;
    }

    public Map<String, Object> extract_filter_data(String input) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        String[] sections = input.split("%");
        for (String section : sections) {
            String[] parts = section.split("-");
            String key = parts[0].trim();
            String values = parts[1].trim();

            if (key.equals("Select Utility")) {
                Map<String, List<String>> utilityMap = new LinkedHashMap<>();
                String[] utilityTypes = values.split(",");
                for (String utilityType : utilityTypes) {
                    String[] utilityParts = utilityType.split("@");
                    if (utilityParts.length > 1) {
                        String[] utilityValues = utilityParts[1].split("_");
                        utilityMap.put(utilityParts[0].trim(), Arrays.asList(utilityValues));
                    } else {
                        utilityMap.put(utilityParts[0].trim(), Arrays.asList(utilityParts[0].trim()));
                    }
                }
                resultMap.put(key, utilityMap);
            } else if (key.equals("Select Territory")) {
                Map<String, Object> territoryMap = new LinkedHashMap<>();
                String[] territoryParts = values.split(",");
                for (String territoryPart : territoryParts) {
                    String[] territorySections = territoryPart.split("@");
                    if (territorySections.length > 1) {
                        String[] territoryValues = territorySections[1].split("_");
                        territoryMap.put(territorySections[0].trim(), Arrays.asList(territoryValues));
                    } else {
                        territoryMap.put(territorySections[0].trim(), Arrays.asList(territorySections[0].trim()));
                    }
                }
                resultMap.put(key, territoryMap);
            } else {
                resultMap.put(key, Arrays.asList(values.split(",")));
            }
        }
        return resultMap;
    }

    public HashMap<Integer, Integer> extract_yearwise_data(String input) {
        HashMap<Integer, Integer> data = new HashMap<>();
        return data;
    }

    public HashMap<String, Integer> extract_paretoChart_data(String input) {
        HashMap<String, Integer> data = new HashMap<>();
        return data;
    }

    public Map<Integer, Map<String, Integer>> read_data_from_excel(String company) throws Throwable {
        Map<Integer, Map<String, Integer>> yearlyData = new LinkedHashMap<>();
        FileInputStream file = new FileInputStream(EXCEL_PATH_FOR_DATA_VALIDATION);
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheet(company);
        Row headerRow = sheet.getRow(0);
        List<String> headers = new ArrayList<>();
        for (int i = 1; i < headerRow.getLastCellNum(); i++) {
            headers.add(headerRow.getCell(i).getStringCellValue());
        }
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            Cell yearCell = row.getCell(0);
            Integer year = Integer.valueOf(String.valueOf((int) yearCell.getNumericCellValue()));
            Map<String, Integer> yearData = new HashMap<>();
            for (int colNum = 1; colNum < headerRow.getLastCellNum(); colNum++) {
                String metricName = headers.get(colNum - 1);
                Cell valueCell = row.getCell(colNum);
                String metricValue = valueCell != null ? (valueCell.getCellType() == CellType.NUMERIC ? String.valueOf((int) valueCell.getNumericCellValue()) : valueCell.getStringCellValue()) : "";
                yearData.put(metricName, Integer.parseInt(metricValue));
            }
            yearlyData.put(year, yearData);
        }
        workbook.close();
        file.close();
        return yearlyData;
    }

    public void navigate_to_given_dashboards(String DashboardName){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-pc-section='headeraction']")));
        if(!(DashboardName.equals("UT, GEN, TD-SYS"))){
            driver.findElement(By.xpath("//a[@data-pc-section='headeraction']/span[text()='"+DashboardName+"']")).click();
        }
    }

}

