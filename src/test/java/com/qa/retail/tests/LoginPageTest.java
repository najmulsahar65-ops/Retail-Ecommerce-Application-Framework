package com.qa.retail.tests;

import java.io.File;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.retail.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic 119: Retail Login Page Feature")
@Story("User Story 101: Registration Flow")
@Story("User Story 102: Front End UI Style")
@Feature("F12: Login Page main Feature")

public class LoginPageTest extends BaseTest {
	 
	
	@Description("Login Page Title Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	
	public void loginPageTitleTest() {
		String acutalPageTitle = loginPage.getLoginPageTitle();
		ChainTestListener.log("The actual page title is: " + acutalPageTitle);
		Assert.assertEquals(acutalPageTitle, "Account Login");
		ChainTestListener.embed(new File("Screen Shot 2026-11-04 at 8.05.15 AM.png"), "image/png");
	}
    
	@Description("Login Page URL Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 2)
	
	public void loginPageUrlTest() {
		String actualPage = loginPage.getLoginPageUrl();
		ChainTestListener.log("Checking if the page URl exist or not" + "\nThe actual page URL is: " + actualPage);
		Assert.assertTrue(actualPage.contains("com/retail/index."));
		// ChainTestListener.embed(new File("C:/Users/DMVTEK/Downloads/Listener.png"), "image/png");
	}
	
	@Description("Verifying Forgot Password Link Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test (priority = 3)
	public void forgottenPasswordTest() {
		Assert.assertTrue(loginPage.isForgottenPasswordExist());
	}
	
	
	@Description("Verifying Application Logo exist Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 4)
	public void IsLogoDisplayedTest() {
		ChainTestListener.log("Checking if the Application logo exist or not");
		Assert.assertTrue(loginPage.LogoImage());
//		boolean logoTest= loginPage.LogoImage();
//		Assert.assertTrue(true);
	}

	
	@Description("Verifying that User is able to login with correct credentials test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 5)
	public void doLoginTest() {
//		Assert.assertTrue(loginPage.doLogin("Sahar@gmail.com", "12345678"));
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutLinkExist());
		
	}
//	@Test(priority = 8)
//	public void isAddToCartExist() {
//		Assert.assertTrue(loginPage.isAddToCartVisible());
//	}
//	
//	@Test (priority = 6)
//	public void IsNewCustomerTextVisible() {
//		Assert.assertTrue(loginPage.isNewCustomerTextExist());
//	}
//	
//	@Test (priority = 7)
//	public void IsFooterLinksExist() {
//		List<WebElement> footerLinks = loginPage.isFooterLinksExist();
//		Assert.assertFalse(footerLinks.isEmpty(), "The element list is empty!");
//		
//		for(WebElement footer : footerLinks) {
//			Assert.assertTrue(footer.isDisplayed(),"Footer links are not visible!");
//		}
//		
//	}
}
