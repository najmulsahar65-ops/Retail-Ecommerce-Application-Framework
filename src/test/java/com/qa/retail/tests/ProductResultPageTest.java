package com.qa.retail.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.retail.base.BaseTest;

public class ProductResultPageTest extends BaseTest  {
	
	@BeforeClass
	
	public void productInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] {
			{"MacBook", "MacBook Pro", 4},
			{"MacBook", "MacBook Air", 4},
			{"iMac", "iMac", 3},
			{"Samsung", "Samsung SyncMaster 941BW", 1}
		};
	}
		
		@Test(dataProvider = "getSearchData")
		public void productImagesTest(String searchKey, String productName, int ImageCount) {
			SearchResultsPage = accPage.doSearch(searchKey);
			ProductInfoPage = SearchResultsPage.selectProducts(productName);
			Assert.assertEquals(ProductInfoPage.getProductImageCount(), ImageCount);
	}
		
		@Test(priority = 2)
		public void productInfoTest() {
			SearchResultsPage = accPage.doSearch("MacBook");
			ProductInfoPage = SearchResultsPage.selectProducts("MacBook Pro");
			Map<String, String> ProductDetailsMap = ProductInfoPage.getProductDetail();
			
			
//			Brand: Apple
//			Product Code: Product 18
//			Reward Points: 800
//			Availability: In Stock
			
			softAssert.assertEquals(ProductDetailsMap.get("Brand"), "Apple");
			softAssert.assertEquals(ProductDetailsMap.get("Product Code"), "Product 18");
			softAssert.assertEquals(ProductDetailsMap.get("Reward Points"), "800");
			softAssert.assertEquals(ProductDetailsMap.get("Availability"), "In Stock");
			
			//$2,000.00
			//Ex Tax: $2,000.00
			softAssert.assertEquals(ProductDetailsMap.get("price"), "$2,000.00");
			softAssert.assertEquals(ProductDetailsMap.get("External tax price"), "$2,000.00");
			
		    softAssert.assertAll();

		}

}
