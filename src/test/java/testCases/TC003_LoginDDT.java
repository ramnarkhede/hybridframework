package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AccountLoginPage;
import pageObject.HomePage;
import pageObject.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass = DataProviders.class,groups = "Datadriven")
	public void verify_loginDDT(String email,String pwd,String exp) {
		
		logger.info("************Started TC003_LoginDDT **************");
		try {
			// Home Page
			HomePage homepage = new HomePage(driver);
			homepage.clickMyAccount();
			homepage.clickLogin();

			// Login Page
			AccountLoginPage loginPage = new AccountLoginPage(driver);
			loginPage.setEmail(email);
			loginPage.setPassword(pwd);
			loginPage.clickLogin();

			// My Account Page
			MyAccountPage myAccountPage = new MyAccountPage(driver);
			boolean targetPage = myAccountPage.isMyAccountPageExists();
			
			/*data is valid - login success - test pass   - logout
							login failed - test failed
							
			data is invalid - login success - test fail - logout
								login failed -test passed*/

			if(exp.equalsIgnoreCase("Valid")) {
				if(targetPage==true) {
					myAccountPage.clickLogout();
					Assert.assertTrue(true);
					
				}else {
					Assert.assertTrue(false);
				}
			}else {
				if(targetPage==true) {
					myAccountPage.clickLogout();
					Assert.assertTrue(false);
					
				}else {
					Assert.assertTrue(true);
				}
			}
			
			
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("************Finished TC003_LoginDDT **************");
	}

}
