package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	//private WebDriver
	private WebDriver driver;
	private ElementUtil eleUtil;
	//private By locators
	private By emailID=By.id("input-email");
	private By password=By.id("input-password");
	private By loginButton=By.xpath("//input[@value = 'Login']");
	private By forgotPwdLink=By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	
	//page constructor
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	//page actions/methods
	@Step("Getting the loging page title...")
	public String getTitle() {
		String title = eleUtil.waitForTitleContainsAndFetch(AppConstants.DEFAULT_LONG_TIMEOUT, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		//String title = driver.getTitle();
		return title;
	}
	
	@Step("....getting the login page url.....")
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContainsAndFetch(5, AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
		//String url = driver.getCurrentUrl();
		return url;
	}
	
	@Step("....getting the forgot pwd link.....")
	public boolean isForgotPwdExist() {
		return eleUtil.waitForElementPresence(forgotPwdLink, AppConstants.DEFAULT_MEDIUM_TIMEOUT).isDisplayed();
		//driver.findElement(forgotPwdLink).isDisplayed();
	}
	
	@Step("login with username : {0} and password: {1}")
	public AccountsPage doLogin(String un,String pwd) {
		eleUtil.waitForElementPresence(emailID, AppConstants.DEFAULT_MEDIUM_TIMEOUT).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginButton);
		
		return new AccountsPage(driver);
	}
	
	@Step("navigating to register page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	

}
