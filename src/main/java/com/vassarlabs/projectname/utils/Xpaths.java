package com.vassarlabs.projectname.utils;

import org.openqa.selenium.By;

import java.util.ArrayList;

public class Xpaths {
    //      Login Page XPaths
    public By Username = By.xpath("//input[@placeholder='Username or Email']");
    public By Password = By.xpath("//input[@placeholder='Password']");
    public By LoginButton = By.xpath("//input[@value='Sign In']");
    public By errorMessageIncorrectCredentials = By.xpath("//div[text()='Invalid Username or Password']");
    public By continueButton = By.xpath("//span[text()='Continue']");

    //    Filter XPaths
    public By select_utility_dropdown = By.xpath("//input[@aria-label='All Utilities']/parent::div/parent::div//div[@role='button']");
    public By all_utilities = By.xpath("//li[@class='p-treenode p-treenode-leaf ng-star-inserted']//div[text()=' All Utilities ']");
    public By years_dropdown = By.xpath("//span[text()='5 Years']/parent::div/div[@class='p-dropdown-trigger']");
    public By select_10_years_years_dropdown = By.xpath("//span[text()='5 Years']/parent::div//ul//li/span[text()='10 Years']");
    public By select_15_years_years_dropdown = By.xpath("//span[text()='5 Years']/parent::div//ul//li/span[text()='15 Years']");

    public By Hamburger_x_path = By.xpath("//button[@class='p-ripple p-element p-button p-component p-button-icon-only p-button-contrast p-button-text btn-header text-3xl']");

    //    Dashboard 1 XPaths
    public By utility_spend_section = By.xpath("//div/h4[text()='Utility Spend']");
    public By Generation_spend_section = By.xpath("//div/h4[text()='Generation Spend']");
    public By T_D_spend_section = By.xpath("//div/h4[text()='Transmission & Distribution System Spend']");
    public By energy_sources_select_company_group_dropdown = By.xpath("//chevrondownicon[@class = 'p-element p-icon-wrapper ng-star-inserted']/parent::div/parent::div/parent::p-treeselect[@placeholder='Select Utility']");
    public By top_10_utilities_gw_table = By.xpath("//table[@class='p-datatable-table ng-star-inserted']/parent::div//parent::div//parent::p-table/parent::app-prime-table//h3[text()='Top 10 Utility (GW)']");
    public By utility_names_for_energy_source = By.xpath("//h3[text()='Top 10 Utility (GW)']//parent::div/parent::div/parent::app-prime-table/p-table//table//tr//td[1]");
    public By generation_section_data_table = By.xpath("//h3[text()='Top 10 Utilities']/ancestor::app-prime-table//table[@class='p-datatable-table ng-star-inserted']//tbody//tr");

    //  Data View Tables X Paths
    public static final ArrayList<String> DASHBOARD1_SECTION_X_PATHS = new ArrayList<String>() {{
        add("//h4[text()='Utility Capex ($ billions)']//parent::div//parent::div//parent::div//parent::div//parent::p-card//parent::app-map//parent::div//parent::div/div/div//button[@aria-label='View Data tables']");
        add("//h4[text()='Generation Capex ($ billions)']//parent::div//parent::div//parent::div//parent::div//parent::p-card//parent::app-map//parent::div//parent::div/div/div//button[@aria-label='View Data tables']");
        add("//h4[text()='Transmission & Distribution System Capex ($ billions)']//parent::div//parent::div//parent::div//parent::div//parent::p-card//parent::app-map//parent::div//parent::div/div/div//button[@aria-label='View Data tables']");
    }};
    public static final ArrayList<String> DASHBOARD2_SECTION_X_PATHS = new ArrayList<String>() {{
        add("//h4[text()='Wire & Cable Capex ($ billions)']//parent::div//parent::div//parent::div//parent::div//parent::p-card//parent::app-map//parent::div//parent::div/div/div//button[@aria-label='View Data tables']");
        add("//h4[text()='Transmission Wire & Cable Capex ($ billions)']//parent::div//parent::div//parent::div//parent::div//parent::p-card//parent::app-map//parent::div//parent::div/div/div//button[@aria-label='View Data tables']");
        add("//h4[text()='Overhead Transmission Wire & Cable Capex ($ billions)']//parent::div//parent::div//parent::div//parent::div//parent::p-card//parent::app-map//parent::div//parent::div/div/div//button[@aria-label='View Data tables']");
        add("//h4[text()='Underground High Voltage - \"70KV and above\" Wire & Cable Capex ($ billions)']//parent::div//parent::div//parent::div//parent::div//parent::p-card//parent::app-map//parent::div//parent::div/div/div//button[@aria-label='View Data tables']");
    }};
    public static final ArrayList<String> DASHBOARD3_SECTION_X_PATHS = new ArrayList<String>() {{
        add("//h4[text()='Distribution System Wire & Cable Capex ($ billions)']//parent::div//parent::div//parent::div//parent::div//parent::p-card//parent::app-map//parent::div//parent::div/div/div//button[@aria-label='View Data tables']");
        add("//h4[text()='Distribution Overhead Wire & Cable Capex ($ billions)']//parent::div//parent::div//parent::div//parent::div//parent::p-card//parent::app-map//parent::div//parent::div/div/div//button[@aria-label='View Data tables']");
        add("//h4[text()='Distribution Overhead MV Wire & Cable Capex ($ billions)']//parent::div//parent::div//parent::div//parent::div//parent::p-card//parent::app-map//parent::div//parent::div/div/div//button[@aria-label='View Data tables']");
        add("//h4[text()='Distribution Overhead 600V Wire & Cable Capex ($ billions)']//parent::div//parent::div//parent::div//parent::div//parent::p-card//parent::app-map//parent::div//parent::div/div/div//button[@aria-label='View Data tables']");
    }};
    public static final ArrayList<String> DASHBOARD4_SECTION_X_PATHS = new ArrayList<String>() {{
        add("//h4[text()='Distribution Underground Wire & Cable Capex ($ billions)']//parent::div//parent::div//parent::div//parent::div//parent::p-card//parent::app-map//parent::div//parent::div/div/div//button[@aria-label='View Data tables']");
        add("//h4[text()='Distribution Underground MV Wire & Cable Capex ($ billions)']//parent::div//parent::div//parent::div//parent::div//parent::p-card//parent::app-map//parent::div//parent::div/div/div//button[@aria-label='View Data tables']");
        add("//h4[text()='Distribution Underground 600V Wire & Cable Capex ($ billions)']//parent::div//parent::div//parent::div//parent::div//parent::p-card//parent::app-map//parent::div//parent::div/div/div//button[@aria-label='View Data tables']");
    }};


}
