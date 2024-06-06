package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class SearchPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By searchProductResults = By.cssSelector("div#content div.product-layout");
	
	public SearchPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
		
	}
	
	public int getSeachProductCount() {
		
		int productCount= eleUtil.waitForElementsVisible(searchProductResults, AppConstants.DEFAULT_MEDIUM_TIMEOUT).size();
		System.out.println("Searched Product Count is -"+productCount);
		return productCount;
	}
	
	public ProductInfoPage selectTheProduct(String productName) {
		By productLocator = By.linkText(productName);
		eleUtil.waitForElementVisible(productLocator, AppConstants.DEFAULT_MEDIUM_TIMEOUT).click();
		return new ProductInfoPage(driver);
	}
	
}
