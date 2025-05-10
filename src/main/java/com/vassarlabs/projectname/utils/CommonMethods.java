package com.vassarlabs.projectname.utils;

import com.google.gson.Gson;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.*;

import static com.vassarlabs.projectname.utils.Constants.*;

public class CommonMethods {
    WebDriverWait wait;
    WebDriver driver;
    Xpaths x_paths = new Xpaths();
    DataFormatter formatter = new DataFormatter();

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

    public LinkedHashMap<Integer, LinkedHashMap<String, Object>> read_data_from_excel(String company) throws Throwable {
        LinkedHashMap<Integer, LinkedHashMap<String, Object>> yearlyData = new LinkedHashMap<>();

        FileInputStream file = new FileInputStream(EXCEL_PATH_FOR_DATA_VALIDATION);
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheet(company);

        if (sheet == null) {
            throw new RuntimeException("No sheet found with name: " + company);
        }

        Row headerRow = sheet.getRow(0);
        List<String> headers = new ArrayList<>();
        for (int i = 1; i < headerRow.getLastCellNum(); i++) {
            headers.add(headerRow.getCell(i).getStringCellValue().trim());
        }

        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row == null) continue;

            Cell yearCell = row.getCell(0);
            if (yearCell == null || yearCell.getCellType() != CellType.NUMERIC) continue;

            Integer year = (int) yearCell.getNumericCellValue();
            LinkedHashMap<String, Object> yearData = new LinkedHashMap<>();

            for (int colNum = 1; colNum < headerRow.getLastCellNum(); colNum++) {
                String metricName = headers.get(colNum - 1);
                Cell valueCell = row.getCell(colNum);
                String metricValue = "";
                if (valueCell != null) {
//                    switch (valueCell.getCellType()) {
//                        case NUMERIC:
//                            double doubleVal = valueCell.getNumericCellValue();
//                            if (metricName.equalsIgnoreCase("energy Sources")) {
//                                yearData.put("energy Sources raw", String.valueOf(doubleVal));
//                            } else {
//                                yearData.put(metricName, doubleVal);  // ✅ Now using Double
//                            }
//                            break;
//                        case STRING:
//                            metricValue = valueCell.getStringCellValue().trim();
//                            if (metricName.equalsIgnoreCase("energy Sources")) {
//                                yearData.put("energy Sources raw", metricValue);
//                            } else {
//                                try {
//                                    yearData.put(metricName, Double.parseDouble(metricValue));
//                                } catch (NumberFormatException e) {
//                                    yearData.put(metricName, 0.0);
//                                }
//                            }
//                            break;
//                        default:
//                            if (!metricName.equalsIgnoreCase("energy Sources")) {
                    String cellValue = formatter.formatCellValue(valueCell);
                    yearData.put(metricName, cellValue);
//                            }
//                            break;
//                    }
                }
            }

            yearlyData.put(year, yearData);
        }

        workbook.close();
        file.close();
        Gson gson = new Gson();
        System.out.println(gson.toJson(yearlyData));
        return yearlyData;
    }

    public void navigate_to_given_dashboards(String DashboardName) {
        System.out.println(DashboardName);
        driver.findElement(x_paths.Hamburger_x_path).click();
        driver.findElement(By.xpath("//a[@data-pc-section='headeraction']/span[text()='" + DashboardName + "']")).click();
        driver.findElement(x_paths.Hamburger_x_path).click();
    }

    public void select_company_group(String company_group) {
        System.out.println(company_group);
        driver.findElement(x_paths.energy_sources_select_company_group_dropdown).click();
        driver.findElement(By.xpath("//li[@aria-label='" + company_group + "']/div/button")).click();
        driver.findElement(x_paths.Hamburger_x_path).click();
    }

    public LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Double>>> read_energy_data_from_excel(String company_group) throws Throwable {
        LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Double>>> energyData = new LinkedHashMap<>();
        loadDataFromExcel(energyData, company_group);
        Gson g =new Gson();
        System.out.println(g.toJson(energyData));
        return energyData;
    }

    private void loadDataFromExcel(LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Double>>> energyData,
                                   String company_group) throws Throwable {
        FileInputStream file = new FileInputStream(Constants.EXCEL_PATH_FOR_ENERGY_SOURCE_VALIDATION);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet(company_group);
        DataFormatter formatter = new DataFormatter();

        // First row contains company names starting from cell 1
        Row headerRow = sheet.getRow(0);
        int lastColumn = headerRow.getLastCellNum();

        List<String> companies = new ArrayList<>();
        for (int col = 1; col < lastColumn; col++) {
            companies.add(formatter.formatCellValue(headerRow.getCell(col)).trim());
        }

        // Each row from index 1 contains energy source name and its values for each company
        for (int rowIndex = 1; rowIndex <= 5; rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row == null) continue;

            String energySource = formatter.formatCellValue(row.getCell(0)).trim();

            for (int col = 1; col < lastColumn; col++) {
                String company = companies.get(col - 1);
                String value = formatter.formatCellValue(row.getCell(col)).trim();
                addEnergyData(energyData, company, energySource, value);
            }
        }

        workbook.close();
        file.close();
    }

    private void addEnergyData(LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Double>>> energyData,
                               String company, String energySource, String values) {
        String[] valueParts = values.split(",");
        double value2030 = parseDoubleOrZero(valueParts.length > 0 ? valueParts[0] : "0");
        double value2035 = parseDoubleOrZero(valueParts.length > 1 ? valueParts[1] : "0");

        energyData.computeIfAbsent(YEAR_2030, k -> new LinkedHashMap<>())
                .computeIfAbsent(company, k -> new LinkedHashMap<>())
                .put(energySource, value2030);

        energyData.computeIfAbsent(YEAR_2035, k -> new LinkedHashMap<>())
                .computeIfAbsent(company, k -> new LinkedHashMap<>())
                .put(energySource, value2035);
    }

    private double parseDoubleOrZero(String s) {
        try {
            return s == null || s.trim().isEmpty() ? 0.0 : Double.parseDouble(s.trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public LinkedHashMap<String, LinkedHashMap<String, Object>> read_generation_data_from_excel() throws Throwable {
        FileInputStream fis = new FileInputStream(EXCEL_PATH_FOR_GENERATION_DATA_VALIDATION);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        LinkedHashMap<String, LinkedHashMap<String, Object>> dataMap = new LinkedHashMap<>();

        Row headerRow = sheet.getRow(0);
        int numColumns = headerRow.getPhysicalNumberOfCells();

        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            String companyName = formatter.formatCellValue(row.getCell(0)).trim();
            LinkedHashMap<String, Object> rowData = new LinkedHashMap<>();
            for (int colIndex = 1; colIndex < numColumns; colIndex++) {
                rowData.put(formatter.formatCellValue(headerRow.getCell(colIndex)).trim(), formatter.formatCellValue(row.getCell(colIndex)).trim());
            }
            dataMap.put(companyName, rowData);
        }
        workbook.close();
        return dataMap;
    }

}

