package com.qa.retail.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.retail.constants.AppConstants;
import com.qa.retail.utils.ElementUtils;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtils eleUtils;

	private By logoutLink = By.xpath("//a[text()='Logout' and @class='list-group-item']");
	private By SearchBox = By.name("search");
	private By HeadersLink = By.cssSelector("div#content > h1, div#content>h2");
	private By searchIcon = By.xpath("//button[@class='btn btn-light btn-lg']");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtils = new ElementUtils(this.driver);
	}

	public String getAccountPageTitle() {
		String actualPageTitle = eleUtils.waitForTitleCompleteValue(AppConstants.ACCOUNT_PAGE_TITLE, AppConstants.MEDIUM_DEFAULT_WAIT);
		System.out.println("The actual page title is: " + actualPageTitle);
		return actualPageTitle;
	}

	public String getAccountPageUrl() {
		String actualPageUrl = eleUtils.waitForURLContains(AppConstants.ACCOUNT_PAGE_URL_FRACTION, AppConstants.MEDIUM_DEFAULT_WAIT);
		System.out.println(actualPageUrl);
		return actualPageUrl;
	}

	public boolean isLogoutLinkExist() {
		return eleUtils.waitForVisibilityOfElement(logoutLink, AppConstants.MEDIUM_DEFAULT_WAIT).isDisplayed();
	}

	public void clickLogout() {
		if (isLogoutLinkExist()) {
			eleUtils.doClick(logoutLink);
		}
	}

	public boolean isSearchBoxExist() {
		return eleUtils.waitForVisibilityOfElement(SearchBox, AppConstants.MEDIUM_DEFAULT_WAIT).isDisplayed();
	}

	public List<String> getAccountsPageHeader() {
		List<WebElement> headerElementsList = eleUtils.waitForVisibilityOfElements(HeadersLink, AppConstants.MEDIUM_DEFAULT_WAIT);
		List<String> HeaderValueList = new ArrayList<>();
		for (WebElement e : headerElementsList) {
			String text = e.getText();
			HeaderValueList.add(text);
		}
		return HeaderValueList;
	}
	
	public searchResultsPage doSearch(String searchKey) {
		eleUtils.waitForVisibilityOfElement(SearchBox, AppConstants.MEDIUM_DEFAULT_WAIT).clear();
		eleUtils.waitForVisibilityOfElement(SearchBox, AppConstants.LONG_DEFAULT_WAIT).sendKeys(searchKey);
		eleUtils.doClick(searchIcon);
		return new searchResultsPage(driver);

	}

}
