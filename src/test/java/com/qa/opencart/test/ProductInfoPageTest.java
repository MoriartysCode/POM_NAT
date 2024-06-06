package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {
	@BeforeClass
	public void productInfoPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	@DataProvider
	public Object[][] getProductImageCountTest() {
		return new Object[][] {
			{"MacBook","MacBook Pro",4},{"IMac","iMac",3},{"Apple","Apple Cinema 30\"",6},{"Samsung","Samsung Galaxy Tab 10.1",7}
		};
	}
	@Test(dataProvider = "getProductImageCountTest")
	public void productImagesCountTest(String productKey,String productName,int ImageCount) {
		searchPage = accPage.performSearch(productKey);
		productInfoPage=searchPage.selectTheProduct(productName);
		int actImagesCount = productInfoPage.getProductImageCount();
		Assert.assertEquals(actImagesCount, ImageCount);
	}
	@Test
	public void productInfoTest() {
		searchPage = accPage.performSearch("MacBook");
		productInfoPage=searchPage.selectTheProduct("MacBook Pro");
		 Map<String,String>actProductInfoMap = productInfoPage.getProductinfo();
		 softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		 softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		 softAssert.assertEquals(actProductInfoMap.get("Product Name"), "MacBook Pro");
		 softAssert.assertEquals(actProductInfoMap.get("Product Price"), "$2,000.00");
		 softAssert.assertAll();
	}
	
	@Test
	public void addToCartTest() {
		searchPage = accPage.performSearch("MacBook");
		productInfoPage=searchPage.selectTheProduct("MacBook Pro");
		productInfoPage.enterTheQuantity(2);
		String actCartMsg=productInfoPage.addProductToCart();
		softAssert.assertTrue(actCartMsg.indexOf("Success")>=0);
		softAssert.assertTrue(actCartMsg.indexOf("MacBook Pro")>=0);
		softAssert.assertEquals(actCartMsg,"Success: You have added MacBook Pro to your shopping cart!");
		softAssert.assertAll();
		
		
		
	}

}
