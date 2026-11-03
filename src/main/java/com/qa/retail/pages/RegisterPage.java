package com.qa.retail.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.retail.constants.AppConstants;
import com.qa.retail.utils.ElementUtils;

public class RegisterPage {
	private WebDriver driver;
	private ElementUtils eleUtil;
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(this.driver);
	}
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By password = By.id("input-password");
	
	private By subscribe = By.id("input-newsletter");
	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//button[text()='Continue']");
	private By successMessage = By.xpath("//h1[text()='Your Account Has Been Created!']");
	private By logoutLink = By.linkText("Logout");
	
	private By registerLink = By.linkText("Register");
	
	public boolean userRegistration(String firstName, String lastName, String email, String password) {
		eleUtil.waitForVisibilityOfElement(this.firstName, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.password, password);
		
		// eleUtil.doClick(subscribe);
		eleUtil.clickElementByJS(agreeCheckBox);
        eleUtil.clickElementByJS(continueButton);

		String successMessg = eleUtil.waitForVisibilityOfElement(successMessage, AppConstants.MEDIUM_DEFAULT_WAIT).getText();
		System.out.println(successMessg);
		
		if(successMessg.contains(AppConstants.USER_REGISTER_SUCCESS_MESSAGE)) {
			eleUtil.clickElementByJS(logoutLink);
			eleUtil.clickElementByJS(registerLink);
			return true;
		} else {
			return false;
		}
	}
}
