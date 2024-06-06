package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic-100 Design Login for OpenCart application")
@Story("Login: 101: design login page features for open cart")
public class LoginPageTest extends BaseTest {
	
	@Severity(SeverityLevel.TRIVIAL)
	@Description("-Getting the title of the page-")
	@Test(priority = 1)
	public void loginPageTitleTest(){
		String actualTitle=loginPage.getTitle();
		Assert.assertEquals(actualTitle, "Account Login");
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description("....checking the url of the page....")
	@Test(priority = 2)
	public void loginPageURLTest() {
		String actualURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actualURL.contains("route=account/login"));
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("....checking forgot pwd link exist...")
	@Test(priority = 3)
	public void forgotPwdLinkExitTest() {
		Assert.assertTrue(loginPage.isForgotPwdExist());
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Description("....checking user is able to login to app with correct username and password....")
	@Test(priority = 4)
	public void loginTest() {
		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		//accPage=loginPage.doLogin("w.prateek469@gmail.com", "Welcome.1");
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
}
