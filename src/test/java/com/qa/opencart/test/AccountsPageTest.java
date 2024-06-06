package com.qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.LoginPage;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accPageSetup() {
		
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password")	);
	}
	
	@Test(priority=1)
	public void accPageAccTitleTest() {
		String actTitle = accPage.getAccPageTitle();
		Assert.assertEquals(actTitle,AppConstants.ACCOUNTS_PAGE_TITLE_VALUE );
	}
	@Test(priority=2)
	public void accPageAccURLTest() {
		String actURL = accPage.getAccPageURL();
		Assert.assertTrue(actURL.contains("route=account/account"));
	}
	
	@Test(priority=3)
	public void isLogoutLinkExist() {
		
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test(priority=4)
	public void accPageHeaderTest() {
		List<String>actHeadersList=accPage.getAccountPageHeadersList();
		System.out.println("Actual Headers values are: "+actHeadersList);
		System.out.println("Expected Headers Values are "+ AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
		Assert.assertEquals(actHeadersList.size(), AppConstants.ACCOUNTS_PAGE_HeadersCount);
		//Assert.assertEquals(actHeadersList, AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
		Assert.assertEquals(actHeadersList,  AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"MacBook"},{"IMac"},{"Apple"},{"Samsung"}
		};
	}
	
	@Test(priority=5,dataProvider ="getProductData")
	public void SearchProductCountTest(String search) {
		searchPage=accPage.performSearch(search);
		Assert.assertTrue(searchPage.getSeachProductCount()>0);
	}
	
	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] {
			{"MacBook","MacBook Pro"},{"IMac","iMac"},{"Apple","Apple Cinema 30\""},{"Samsung","Samsung Galaxy Tab 10.1"}
		};
	}
	
	@Test(priority=6,dataProvider = "getProductTestData")
	public void SearchProductTest(String searchKey,String procuctName) {
		searchPage=accPage.performSearch(searchKey);
		if(searchPage.getSeachProductCount()>0) {
			productInfoPage=searchPage.selectTheProduct(procuctName);
			String productHeader = productInfoPage.getProductHeaderValue();
			Assert.assertEquals(productHeader, procuctName);
			
		}
		
	}
	
	
	

}
