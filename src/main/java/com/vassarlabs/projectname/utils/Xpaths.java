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


    //    Dashboard 1 XPaths
    public By utility_spend_section = By.xpath("//div/h4[text()='Utility Spend']");
    public By Generation_spend_section = By.xpath("//div/h4[text()='Generation Spend']");
    public By T_D_spend_section = By.xpath("//div/h4[text()='Transmission & Distribution System Spend']");
    public By utility_spend_section_data_table = By.xpath("");
    public By generation_spend_section_data_table = By.xpath("");
    public By t_d_spend_section_data_table = By.xpath("");
    public By wire_and_cable_spend_section_data_table = By.xpath("");
    public By t_wire_and_cable_spend_section_data_table = By.xpath("");
    public By d_wire_and_cable_spend_section_data_table = By.xpath("");
    public By toh_spend_section_data_table = By.xpath("");
    public By tug_spend_section_data_table = By.xpath("");
    public By doh_spend_section_data_table = By.xpath("");
    public By dmv_spend_section_data_table = By.xpath("");
    public By d600v_spend_section_data_table = By.xpath("");
    public By dug_spend_section_data_table = By.xpath("");
    public By dug_mv_spend_section_data_table = By.xpath("");
    public By dug_600v_spend_section_data_table = By.xpath("");
    public By gen_t10_spend_section_data_table = By.xpath("");
    public By energy_sources_spend_section_data_table = By.xpath("//h3[text()='Top 10 Utility (GW)']//parent::div//parent::div//parent::app-prime-table//tbody/parent::table/thead/tr/th/div[text()=' Utility Name ']//parent::th//parent::tr//parent::thead//parent::table[1]//tbody/tr/td[1]/span");

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
