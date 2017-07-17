package challenge.pages;

import java.time.LocalDate;

/*
 * Homepage.java is storing all the related WebElements and reusable methods for the HomePage 
 * of SproutSocial application
 * 
 */
import java.time.Period;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import challenge.utilities.Driver;

public class Homepage {
	LocalDate futureDate;
	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(Driver.getInstance(), 10);
	private String scheduledMessage;

	public Homepage() {
		driver = Driver.getInstance();
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[.='Filters']")
	public WebElement filters;
	
	@FindBy(xpath = "//span[@class='nm']")
	public WebElement composeBtn;

	@FindBy(xpath = "//div[@class='actions']/button")
	public WebElement sendBtn;

	@FindBy(xpath = "//div[@class='compose-window-styles']//div[2]/textarea")
	public WebElement textBox;

	@FindBy(xpath = "//a[@class='menu-item-link messages']")
	public WebElement messagesBtn;

	@FindBy(xpath = "//div[@class='js-exoSubBucket messages messages-bucket']//section")
	public List<WebElement> messageList;

	@FindBy(xpath = "//*[@id='filter-types']/div/div[2]/div/ul/li[6]/label/span[2]")
	public WebElement sentMessageCheckbox;

	@FindBy(xpath = "//*[@id='filter-types']/div/div[2]/div/ul/li[1]/label/span[1]/span")
	public WebElement mentionsCheckbox;

	@FindBy(xpath = "//*[@id='recent_msgs']/div/h3")
	public WebElement noMessage;

	@FindBy(xpath = "//*[@id='primary-menu']/li[4]/a")
	public WebElement publishingBtn;

	@FindBy(xpath = "//*[@id='actions']/ul/li[2]/a/div[2]")
	public WebElement scheduling;

	@FindBy(xpath = "//*[@id='details']/div/div[4]/a[2]")
	public WebElement scheduleMessage;

	@FindBy(css = ".ui-datepicker-calendar")
	public List<WebElement> calendars;
	
	@FindBy(css = ".ui-datepicker-calendar")
	public WebElement publishingCalendar;

	@FindBy(xpath = "(//a[.='Next'])[2]")
	public WebElement schedulerCalNextMonth;

	@FindBy(xpath = "(//a[.='Next'])[1]")
	public WebElement publishingCalNextMonth;

	
	/*
	 * "tweet" method => reusable method will accept a String argument,
	 *  and send a tweet everytime it is called
	 */
	public void tweet(String message) {
		wait.until(ExpectedConditions.visibilityOf(composeBtn));
		composeBtn.click();
		textBox.clear();
		textBox.sendKeys(message);
		sendBtn.click();
	}

	
	/*
	 *  "scheduleMessage" method accepts two Strings parameters.
	 *  
	 *  this method is also calling "selectDateSchedulerCal" method within itself.
	 */
	public void scheduleMessage(String scheduledMessage, String dateStamp) {
		
		this.scheduledMessage=scheduledMessage;
		
		publishingBtn.click();
		scheduling.click();
		scheduleMessage.click();
		textBox.clear();
		textBox.sendKeys(scheduledMessage);

		futureDate = LocalDate.parse(dateStamp);
		// Ensure it is future date
		Assert.assertTrue(futureDate.isAfter(LocalDate.now()));

		selectDateSchedulerCal(futureDate);

		sendBtn.click();
	}

	
	
	public void selectDateSchedulerCal(LocalDate date) {

		wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".ui-datepicker-calendar"), 2));

		/*
		 * Select month Determine if month is current month or future month
		 * ASSUMPTION: SproutSocial only allows future date scheduling for 6
		 * month ahead from today's month (until Jan, 2018)
		 */
		LocalDate now = LocalDate.now();

		// Select month or day
		Period period = Period.between(now, futureDate);

		int monthDifference = (int) period.toTotalMonths();
		Assert.assertTrue("Cannot schedule a tweet more than 6 month ahead in SproutSocial", monthDifference < 7);

		if (monthDifference > 0) {
			for (int i = 1; i <= monthDifference; i++)
				schedulerCalNextMonth.click();
		}

		// Click on Day
		String day = String.valueOf(futureDate.getDayOfMonth());
		WebElement calendar = calendars.get(1);
		WebElement dayElem = calendar.findElement(By.linkText(day));
		dayElem.click();

	}
	
	
	
	public void selectDatePublishingCal(LocalDate date) {

		LocalDate now = LocalDate.now();

		// Select month or day
		Period period = Period.between(now, futureDate);

		int monthDifference = (int) period.toTotalMonths();

		if (monthDifference > 0) {
			for (int i = 1; i <= monthDifference; i++)
				publishingCalNextMonth.click();
		}

		// Click on Day
		String day = String.valueOf(futureDate.getDayOfMonth());
		WebElement dayElem = publishingCalendar.findElement(By.linkText(day));
		dayElem.click();

	}
	
	/*
	 * "verifyScheduled" has an explicit wait within itself.
	 * This method is checking if the 'scheduledTweets' size is bigger than zero.
	 * 
	 */
	public void verifyScheduled() {
		selectDatePublishingCal(futureDate);
		wait.until(
		   ExpectedConditions
			.numberOfElementsToBeMoreThan(By.xpath("//span[.='"+scheduledMessage+"']"), 0));
		List<WebElement> scheduledTweets=driver.findElements(By.xpath("//span[.='"+scheduledMessage+"']"));
		
		Assert.assertTrue("Verify that scheduled tweet with msg: "+scheduledMessage+" is displayed",
				scheduledTweets.size() > 0 );
	}
	

}
