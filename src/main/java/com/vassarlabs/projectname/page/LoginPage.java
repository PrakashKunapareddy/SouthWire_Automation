package com.vassarlabs.projectname.page;

import com.vassarlabs.projectname.utils.Xpaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;
    Xpaths x_paths = new Xpaths();
    private boolean flag = true;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterUsername(String username) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(x_paths.Username));
        WebElement un = driver.findElement(x_paths.Username);
        un.sendKeys(username);
        wait.until(ExpectedConditions.presenceOfElementLocated(x_paths.continueButton));
        driver.findElement(x_paths.continueButton).click();
    }

    public void enterPassword(String password) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(x_paths.Password));
        WebElement pw = driver.findElement(x_paths.Password);
        pw.sendKeys(password);
    }

    public void clickSignInButton() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(x_paths.LoginButton));
        driver.findElement(x_paths.LoginButton).click();

    }

    public void validateLogin(String expected_output) throws Throwable {
        if (flag) {
            wait.until(ExpectedConditions.presenceOfElementLocated(x_paths.errorMessageIncorrectCredentials));
            if (driver.findElements(x_paths.errorMessageIncorrectCredentials).size() > 0) {
                String Failed = driver.findElement(x_paths.errorMessageIncorrectCredentials).getText();
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
