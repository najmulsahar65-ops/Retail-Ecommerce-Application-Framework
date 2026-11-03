package com.qa.retail.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.retail.constants.AppConstants;
import com.qa.retail.utils.ElementUtils;

public class productInfoPage  {
	
	private WebDriver driver;
	private ElementUtils eleUtils;
	
	public productInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtils = new ElementUtils(this.driver);
	}
	
	//Locators
	private By productHeader = By.cssSelector("div#content h1");
    private By productImages = By.cssSelector("div.image.magnific-popup img");
    private By productMetaData = By.xpath("(//div[@class='col-sm']//descendant::ul[@class='list-unstyled'])[1]//child::li");
    private By productPriceData = By.xpath("(//div[@class='col-sm']//descendant::ul[@class='list-unstyled'])[2]//child::li");
    
    
    
    
    
    //Methods
	public String getProductsHeaderName() {
		String productHeaderValue = eleUtils.doElementGetText(productHeader);
		System.out.println("The product header is: " + productHeaderValue);
		return productHeaderValue;
		
	}
	
	
	
	public int getProductImageCount() {
		int imagesCount = eleUtils.waitForVisibilityOfElements(productImages, AppConstants.MEDIUM_DEFAULT_WAIT).size();
		System.out.println("Product: " + getProductsHeaderName() + "Image Count: " + imagesCount);
		return imagesCount;
	}
	
	
	
    private Map<String, String> ProductMap = new HashMap<String, String>();
    
//    Brand: Apple
//    Product Code: Product 18
//    Reward Points: 800
//    Availability: In Stock
    
    private void getProductMetaData() {
    	List<WebElement> metaDataList = eleUtils.waitForVisibilityOfElements(productMetaData, AppConstants.MEDIUM_DEFAULT_WAIT);
    	for(WebElement e : metaDataList) {
    		String metaData = e.getText();
    		String MetaKey = metaData.split(":")[0].trim();
    		String MetaValue = metaData.split(":")[1].trim();
    		
    		ProductMap.put(MetaKey, MetaValue);
    		
    	}
    }
    	private void getProductPriceData() {
    		List<WebElement> metaPriceList = eleUtils.waitForVisibilityOfElements(productPriceData, AppConstants.MEDIUM_DEFAULT_WAIT);
    		String productPrice = metaPriceList.get(0).getText();
    		String exProductTax = metaPriceList.get(1).getText().split(":")[1].trim();
    		
    		ProductMap.put("price", productPrice);
    		ProductMap.put("External tax price", exProductTax);
    	}
    	
    	public Map<String, String> getProductDetail() {
    		ProductMap.put("Product Name: ", getProductsHeaderName());
    		getProductMetaData();
    		getProductPriceData();
    		System.out.println(ProductMap);
    		return ProductMap;
    	}
    	
    	
    	
    	
    	
    	
    


}
