package com.qa.retail.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.retail.base.BaseTest;
import com.qa.retail.constants.AppConstants;

public class AccountsPageTest extends BaseTest {
	
	
	@BeforeClass
	public void accsetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority=1)
	public void accPageTitleTest() {
		Assert.assertEquals(accPage.getAccountPageTitle(), "My Account");
	}
	
	@Test(priority=2)
	public void accPageUrlTest() {
		Assert.assertTrue(accPage.getAccountPageUrl().contains("com/retail/index.php"));
	}
	
	@Test(priority=3)
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test (priority=4)
	public void isSearchBoxExistTest() {
		Assert.assertTrue(accPage.isSearchBoxExist());
	}
	
	@Test (priority=5)
	public void accPageHeadersCountTest() {
		Assert.assertEquals(accPage.getAccountsPageHeader().size(), AppConstants.ACCOUNT_PAGE_HEADERS_COUNT);
		
	}
	@Test(priority=6)
	public void accPageHeadersTest() {
		List<String> accountsPageHeader = accPage.getAccountsPageHeader();
		System.out.println(accountsPageHeader);
		Assert.assertEquals(accountsPageHeader, AppConstants.ACCOUNT_PAGE_HEADERS_LIST);
	}
	
	@Test(priority=7)
	public void searchTest() {
		SearchResultsPage = accPage.doSearch("MacBook");
		ProductInfoPage = SearchResultsPage.selectProducts("MacBook Pro");
		String ActualProductHeader = ProductInfoPage.getProductsHeaderName();
		Assert.assertEquals(ActualProductHeader, "MacBook Pro");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
