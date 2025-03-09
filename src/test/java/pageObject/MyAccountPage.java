package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	public MyAccountPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//h2[text()='My Account']") //MyAccount page heading
	WebElement msgHandling;
	
	@FindBy(xpath="//div[@class='list-group']//a[text()='Logout']")
	WebElement linkLogout;
	
	public boolean isMyAccountPageExists() {
		try {
			return(msgHandling.isDisplayed());
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public void clickLogout() {
		linkLogout.click();
	}

}
