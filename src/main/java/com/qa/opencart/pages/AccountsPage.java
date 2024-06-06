package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By logoutLink=By.linkText("Logout");
	private By accsHeaders= By.cssSelector("div#content h2");
	private By searchField= By.name("search");
	private By searchIcon= By.cssSelector("#search button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getAccPageTitle() {
		String title = eleUtil.waitForTitleContainsAndFetch(10, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
		//String title = driver.getTitle();
		System.out.println("Accounts page title  is-"+title);
		return title;
	}
	
	public String getAccPageURL() {
		String url = eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIMEOUT,AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE );
		//String url = driver.getCurrentUrl();
		System.out.println("Accounts page url  is-"+url);
		return url;
	}
	
	public boolean isLogoutLinkExist() {
		return eleUtil.waitForElementPresence(logoutLink, AppConstants.DEFAULT_MEDIUM_TIMEOUT).isDisplayed();
		//return driver.findElement(logoutLink).isDisplayed();
	}
	
	public boolean isSearchExist() {
		return eleUtil.waitForElementPresence(searchField, AppConstants.DEFAULT_MEDIUM_TIMEOUT).isDisplayed();
		//return driver.findElement(searchField).isDisplayed();
	}
	
	public List<String> getAccountPageHeadersList() {
		 List<WebElement>accHeadersList=eleUtil.waitForElementsVisible(accsHeaders, AppConstants.DEFAULT_MEDIUM_TIMEOUT);
		// List<WebElement>accHeadersList=driver.findElements(accsHeaders);
		 List<String>accValueList=new ArrayList<>();
		 for(WebElement e:accHeadersList) {
			String text =  e.getText();
			 accValueList.add(text);
		 }
		 return accValueList;
	}

	public SearchPage performSearch(String productsearchKey) {
		if(isSearchExist()) {
			eleUtil.doSendKeys(searchField, productsearchKey);
			eleUtil.doClick(searchIcon);
			return new SearchPage(driver);
		}else {
			System.out.println("Search key doesnot exist");
			return null;
		}
		
	}


}
