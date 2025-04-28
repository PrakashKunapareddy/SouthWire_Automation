package com.vassarlabs.projectname.utils;

import org.openqa.selenium.By;

public class Xpaths {
//      Login Page XPaths
    public By Username = By.xpath("//input[@placeholder='Username or Email']");
    public By Password = By.xpath("//input[@placeholder='Password']");
    public By LoginButton = By.xpath("//input[@value='Sign In']");
    public By LoginButtonDisabled = By.xpath("//span[text()='Login']/parent::button[@disabled]");
    public By errorMessageIncorrectCredentials = By.xpath("//div[text()='Invalid Username or Password']");
    public By continueButton = By.xpath("//span[text()='Continue']");

//    Filter XPaths
    public By select_utility_dropdown = By.xpath("//input[@aria-label='All Utilities']/parent::div/parent::div//div[@role='button']");
    public By all_utilities = By.xpath("//li[@role='treeitem']//div[text()=' All Utilities ']");

//    Dashboard 1 XPaths
    public By utility_spend_section = By.xpath("//div/h4[text()='Utility Spend']");
    public By Generation_spend_section = By.xpath("//div/h4[text()='Generation Spend']");
    public By T_D_spend_section = By.xpath("//div/h4[text()='Transmission & Distribution System Spend']");


}
