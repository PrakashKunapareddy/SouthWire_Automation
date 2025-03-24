package com.vassarlabs.projectname.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;

    private By Username = By.xpath("//input[@placeholder='Enter your username']");
    private By Password = By.xpath("//input[@placeholder='Enter your password']");
    private By LoginButton = By.xpath("//span[text()='Login']/parent::button");
    private By LoginButtonDisabled = By.xpath("//span[text()='Login']/parent::button[@disabled]");
    private By errorMessageIncorrectCredentials = By.xpath("//div[text()='Invalid Username or Password']");
    private boolean flag = true;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) throws InterruptedException {
        WebElement un = driver.findElement(Username);
        un.sendKeys(username);
    }

    public void enterPassword(String password) throws InterruptedException {
        WebElement pw = driver.findElement(Password);
        pw.sendKeys(password);
    }

    public void clickSignInButton() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(3000);
        if (driver.findElements(LoginButtonDisabled).size()>0) {
            flag = false;
        } else {
            driver.findElement(LoginButton).click();
        }
    }

    public void validateLogin(String expected_output) throws Throwable {
        if (flag) {
            Thread.sleep(2000);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            if (driver.findElements(errorMessageIncorrectCredentials).size() > 0) {
                String Failed = driver.findElement(errorMessageIncorrectCredentials).getText();
                Assert.assertEquals(expected_output, Failed, "Expected Error Message " + Failed + " But Found : " + expected_output);
            }
            else{
                String validateUrl = driver.getCurrentUrl();
                Assert.assertTrue(validateUrl.contains(expected_output));
            }
        } else {
            System.out.println("Empty Username Or Password field");
        }
    }
}
