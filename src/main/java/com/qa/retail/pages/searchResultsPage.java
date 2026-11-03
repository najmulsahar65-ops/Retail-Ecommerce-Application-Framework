package com.qa.retail.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.retail.constants.AppConstants;
import com.qa.retail.utils.ElementUtils;

public class searchResultsPage {

	private WebDriver driver;
	private ElementUtils eleUtils;

	public searchResultsPage(WebDriver driver) {
		this.driver = driver;

		eleUtils = new ElementUtils(this.driver);
	}
	//Locators
//	private By product = By.xpath("//a[text()='MacBook Pro']");
	
	
	//Methods
	public productInfoPage selectProducts(String productName) {
//		By productNameLocator = By.linkText(productName);
		WebElement productElement = eleUtils.waitForVisibilityOfElement(By.linkText(productName), AppConstants.MEDIUM_DEFAULT_WAIT);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView(true);", productElement);
	    js.executeScript("arguments[0].click();", productElement);
	    
	    return new productInfoPage(driver);
	}
	
	

}
