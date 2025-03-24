package com.vassarlabs.projectname.utils;

import com.vassarlabs.projectname.driver.WebdriverInitializer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CommonMethods {

    public  void verifyData(WebDriver driver,By ele,String input){
        String actualData = driver.findElement(ele).getText();
        Assert.assertEquals(input,actualData,"Expected Data : "+input+" But Found : "+actualData);
    }
    public void enterData(WebDriver driver,By ele,String input){
        driver.findElement(ele).sendKeys(input);
    }
    public void selectOptionByText(WebDriver driver,By ele,String input){
        Select sl = new Select(driver.findElement(ele));
        sl.selectByVisibleText(input);
    }
    public void selectOptionByIndex(WebDriver driver,By ele,int i){
        Select sl = new Select(driver.findElement(ele));
        sl.selectByIndex(i);
    }
    public void clickElement(WebDriver driver,By ele) throws InterruptedException {
        driver.findElement(ele).click();
        Thread.sleep(500);
    }
    public void verifyCheckedOrNot(WebDriver driver,By ele){
        Boolean flag = driver.findElement(ele).isSelected();
        Assert.assertEquals(flag,true,"WebElement is not Selected or Checked");
    }
    public void verifyEnabledOrNot(WebDriver driver,By ele){
        Boolean flag = driver.findElement(ele).isEnabled();
        Assert.assertEquals(flag,true,"WebElement is not Enabled");
    }
    public void verifyDisplayedOrNot(WebDriver driver,By ele){
        Boolean flag = driver.findElement(ele).isDisplayed();
        Assert.assertEquals(flag,true,"WebElement is not Displayed");
    }
    public void waitTillDisplayed(WebDriver driver,By ele){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(ele));
    }
    public void waitTillClickable(WebDriver driver,By ele){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(ele));
    }
    public void waitSelectedOrNot(WebDriver driver,By ele) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeSelected(ele));
    }
}
