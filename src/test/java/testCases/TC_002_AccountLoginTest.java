package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AccountLoginPage;
import pageObject.HomePage;
import pageObject.MyAccountPage;
import testBase.BaseClass;

public class TC_002_AccountLoginTest extends BaseClass {

	@Test(groups = {"Sanity","Master"})
	public void verify_login() {
		logger.info("***********************Starting TC_002_AccountLoginTest***********************");
		try {
			// Home Page
			HomePage homepage = new HomePage(driver);
			homepage.clickMyAccount();
			homepage.clickLogin();

			// Login Page
			AccountLoginPage loginPage = new AccountLoginPage(driver);
			loginPage.setEmail(prop.getProperty("email"));
			loginPage.setPassword(prop.getProperty("password"));
			loginPage.clickLogin();

			// My Account Page
			MyAccountPage myAccountPage = new MyAccountPage(driver);
			boolean targetPage = myAccountPage.isMyAccountPageExists();

			Assert.assertTrue(targetPage);
		} catch (Exception e) {
			Assert.fail();
		}

		logger.info("***********************Finished TC_002_AccountLoginTest***********************");

	}

}
