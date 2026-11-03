package com.qa.retail.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.retail.base.BaseTest;

public class RegisterPageTest extends BaseTest {


	@BeforeClass
	public void regSetup() {
		registerPage = loginPage.navigateToRegisterPage();
	}
	
	public String getRandomEmalID(String userFirstName) {
		 return userFirstName + "dmv" + System.currentTimeMillis() + "@gmail.com";
		// UUID Class
		// return "Selenium" + UUID.randomUUID() + "@gmail.com";
	}
	
	@DataProvider
	public Object[][] getUserRegData() {
		return new Object[][] {
			{"Dave", "Smith", getRandomEmalID("Dave"), "Test123"},
			{"John", "Bruce", getRandomEmalID("John"), "Test123"},
			{"Sarah", "Mcternan", getRandomEmalID("Sarah"), "Test123"},
			{"Brishna", "Nazari", getRandomEmalID("Brishna"), "Test123"},
			{"AM", "AM", getRandomEmalID("AM"), "Test123"},
			{"Ezat", "Masiha", getRandomEmalID("Ezat"), "Test123"},
			{"Sahar", "Sahar", getRandomEmalID("Sahar"), "Test123"}
		};
	}
	
	@Test(dataProvider = "getUserRegData")
	public void userRegTest(String firstName, String lastName, String email, String password) {
		boolean isRegDone = registerPage.userRegistration(firstName, lastName, getRandomEmalID(firstName), password);
		Assert.assertTrue(isRegDone);
	}
	
	
	
	
	
	
	
//	@Test
//	public void userRegTest() {
//		boolean isRegDone=registerPage.userRegisteration("dave", "Smit", getRandomEmailId("dave"),"Test123");
//		Assert.assertTrue(isRegDone);
//	}
	 
	}


