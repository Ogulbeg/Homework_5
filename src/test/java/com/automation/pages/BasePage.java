package com.automation.pages;

import com.automation.utilities.BrowserUtilities;
import com.automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

private WebDriver driver= Driver.getDriver();
protected WebDriverWait wait = new WebDriverWait(driver,25);

@FindBy(css="#user-menu > a")
protected WebElement  currentUser;

public BasePage(){
    PageFactory.initElements(driver,this);
}
public String getCurrentUserName(){
    BrowserUtilities.waitForPageToLoad(10);
    wait.until(ExpectedConditions.visibilityOf(currentUser));
    return currentUser.getText().trim();
}
/**
 * Method for vytrack navigation. Provide tab name and module name to navigate
 * @param tabName, as Dashboard, Fleet or Customers
 * @param moduleName, as Vehicles, Vehicles Odometer and Vehicles Costs
 */
public void navigateTo(String tabName,String moduleName){
    String tabNameXpath = "//span[@class='title title-level-1' and contains(text(),'" + tabName + "')]";
    String moduleXpath = "//span[@class='title title-level-2' and text()='" + moduleName + "']";

    WebElement tabElement= driver.findElement(By.xpath(tabNameXpath));
    WebElement moduleElement= driver.findElement(By.xpath(moduleXpath));

    Actions actions = new Actions(driver);
    BrowserUtilities.wait(4);
    actions.moveToElement(tabElement).pause(2000).click(moduleElement).build().perform();

    BrowserUtilities.wait(4);
}




}
