package com.qa.retail.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.aventstack.chaintest.service.ChainPluginService;
import com.qa.retail.factory.DriverFactory;
import com.qa.retail.pages.AccountsPage;
import com.qa.retail.pages.LoginPage;
import com.qa.retail.pages.RegisterPage;
import com.qa.retail.pages.productInfoPage;
import com.qa.retail.pages.searchResultsPage;

//@Listeners(ChainTestListener.class)
public class BaseTest {

	protected WebDriver driver;
	DriverFactory df;
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected Properties prop;
	protected searchResultsPage SearchResultsPage;
	protected productInfoPage ProductInfoPage;
	protected SoftAssert softAssert;
	protected RegisterPage registerPage;

	@BeforeTest
	public void setup() {

		// ChainTest Custom System Information
		ChainPluginService.getInstance().addSystemInfo("Build: ", "1.0");
        ChainPluginService.getInstance().addSystemInfo("Owner Name: ", "Sahar");
        ChainPluginService.getInstance().addSystemInfo("Senior Tester: ", "Ahmad");
        ChainPluginService.getInstance().addSystemInfo("Manager: " , "Muzhda");
        ChainPluginService.getInstance().addSystemInfo("Team lead: ", "Ali");
        
        
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
