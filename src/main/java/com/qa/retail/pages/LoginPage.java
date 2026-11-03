package com.qa.retail.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.retail.constants.AppConstants;
import com.qa.retail.utils.ElementUtils;

import io.qameta.allure.Step;

public class LoginPage {

	// Private WebDriver driver reference
	private WebDriver driver;
	private ElementUtils eleUtil;

	//Locators
	private By username = By.cssSelector("#input-email");
	private By password = By.id("input-password");
	private By login = By.xpath("//button[text()='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By logoImage = By.cssSelector("img.img-fluid");
	private By AddToCart = By.xpath("//button[@class='btn btn-lg btn-dark d-block dropdown-toggle']");
	private By NewcustomerText = By.xpath("//h2[text()='New Customer']");
	private By FooterLinks = By.xpath("//div[@class = 'container']/div[@class='row']/div[@class='col-sm-3']");
	private By registerLink = By.linkText("Register");

	//Constractor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(this.driver);
		
	}
	
	@Step("Getting Login Page Title")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleContains(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT );
		System.out.println("The login page title is: " + title);
		return title;
	}
	
	@Step("Getting Login Page URL")
	public String getLoginPageUrl() {
		String Url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("The login page URl is: " + Url);
		return Url;
	}
	
	@Step("Checking Forgot Password Link exist")
	public boolean isForgottenPasswordExist() {
		return eleUtil.waitForVisibilityOfElement(forgotPwdLink, AppConstants.LONG_DEFAULT_WAIT).isDisplayed();
	}
	
	@Step("Checking Application Logo exist")
	public boolean LogoImage() {
		return eleUtil.waitForVisibilityOfElement(logoImage, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	@Step("User name is: *** and the user password is: ****")
	public AccountsPage doLogin(String Username, String pwd) {
		System.out.println("Login credintials are: " + Username + ":" + pwd);
		eleUtil.doSendKeysWithWait(username, Username, AppConstants.MEDIUM_DEFAULT_WAIT);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(login);
	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    return new AccountsPage(driver);
	}
	
	public boolean isAddToCartVisible() {
		return eleUtil.waitForVisibilityOfElement(AddToCart, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	public boolean isNewCustomerTextExist() {
		return eleUtil.waitForVisibilityOfElement(NewcustomerText, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	public List<WebElement> isFooterLinksExist() {
		return eleUtil.waitForVisibilityOfElements(FooterLinks, AppConstants.SHORT_DEFAULT_WAIT);
	}
	
	@Step("Navigating to the register page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForVisibilityOfElement(registerLink, AppConstants.MEDIUM_DEFAULT_WAIT).click();
		return new RegisterPage(driver);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
