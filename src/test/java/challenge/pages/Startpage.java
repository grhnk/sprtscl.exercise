package challenge.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import challenge.utilities.Driver;

public class Startpage {

	WebDriver driver;
	
	public Startpage(){
		driver = Driver.getInstance();
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "signin-email")
	public WebElement email;
	
	@FindBy(id = "signin-pw")
	public WebElement password;
	
	@FindBy(xpath = "//button[1]")
	public WebElement loginBtn;
	
	public void login(String emailData, String pwdData) throws InterruptedException{
		email.sendKeys(emailData);
		password.sendKeys(pwdData);
		Thread.sleep(500);
		loginBtn.click();
	}

	
}
