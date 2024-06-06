package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	ElementUtil eleUtil;
	
	private By productHeader = By.tagName("h1");
	private By productImages = By.xpath("//ul[@class='thumbnails']//img");
	private By productMetaData = By.xpath("//div[@id = 'content']//ul[@class='list-unstyled'][1]//li");
	private By productPricingData = By.xpath("//div[@id = 'content']//ul[@class='list-unstyled'][2]//li");
	private By quantity = By.id("input-quantity");
	private By btn_addToCart = By.xpath("//button[text()='Add to Cart']");
	private By cartSuccessMessage = By.cssSelector("div.alert.alert-success");
	private Map<String, String> productInfoMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getProductHeaderValue() {
		String productHeaderval=eleUtil.doElementGetText(productHeader);
		return productHeaderval;
	}
	
	public int getProductImageCount() {
		int imagesCount = eleUtil.waitForElementsVisible(productImages, AppConstants.DEFAULT_MEDIUM_TIMEOUT).size();
		System.out.println("Product Images Count - "+imagesCount);
		return imagesCount;
	}
	
	public Map<String, String> getProductinfo() {
		productInfoMap= new LinkedHashMap<String,String>();
		//Product Header
		productInfoMap.put("Product Name", getProductHeaderValue());
		getProductMetaData();
		getProductPricingData();
		System.out.println(productInfoMap);
		
		return productInfoMap;
	}
	
	//fetching the meta data
	private void getProductMetaData() {
		List<WebElement>metaList = eleUtil.getElements(productMetaData);
		for(WebElement e : metaList) {
			String meta = e.getText();
			String metaInfo[]=meta.split(":");
			String key =  metaInfo[0].trim();
			String value =  metaInfo[1].trim();
			productInfoMap.put(key, value);
		}
	}
	
	//fetching pricingData
	private void getProductPricingData() {
		List<WebElement>priceList = eleUtil.getElements(productPricingData);
		String price=priceList.get(0).getText();
		String extTax=priceList.get(1).getText();
		String extTaxVal = extTax.split(":")[1].trim();
		productInfoMap.put("Product Price", price);
		productInfoMap.put("extTax", extTaxVal);
	}
	
	public void enterTheQuantity(int qty) {
		System.out.println("Product Quantity is "+qty);
		eleUtil.doSendKeys(quantity, String.valueOf(qty));
	}
	public String addProductToCart() {
		eleUtil.doClick(btn_addToCart);
		String successMsg =  eleUtil.waitForElementVisible(cartSuccessMessage, AppConstants.DEFAULT_MEDIUM_TIMEOUT).getText();
		StringBuilder sb = new StringBuilder(successMsg);
		String mesg =sb.substring(0,successMsg.length()-1).replace("\n", "").toString();
		System.out.println("Cart Success message" +mesg);
		return mesg;
	}

}
