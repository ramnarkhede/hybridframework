package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AccountRegistrationPage;
import pageObject.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups = {"Regression","Master"})
	public void verify_account_registration() {
		
		
		try {
			
	
				logger.info("**********Starting TC001_AccountRegistrationTest***********");
				HomePage hp=new HomePage(driver);
				hp.clickMyAccount();
				logger.info("Clicked on myAccount link");
				hp.clickRegister();
				logger.info("Clicked on register link");
				
				AccountRegistrationPage arp=new AccountRegistrationPage(driver);
				
				logger.info("Providing customer details");
				arp.setFirstName("Sarika");
				arp.setLastName("Das");
				arp.setEmail("sarika21@gmail.com");
				arp.setTelephone("9975646473");
				arp.setPassword("Sarika@123");
				arp.setConfirmPassword("Sarika@123");
				arp.setPrivacyPolicy();
				arp.clickContinue();
				
				
				logger.info("validating expected message");
				String cnfMessage=arp.getConfirmationMsg();
				
				Assert.assertEquals(cnfMessage, "Your Account Has Been Created!");
				
		}catch(Exception e) {
			logger.error("Test Failed...");
			logger.debug("Debug logs...");
			Assert.fail();
		}
		
		logger.info("**********Finished TC001_AccountRegistrationTest***********");
		
	}

}
