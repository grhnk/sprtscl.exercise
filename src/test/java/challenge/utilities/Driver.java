package challenge.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import challenge.utilities.Config;

public class Driver {
	
	private static WebDriver driver;
	public static WebDriver getInstance() {

		if (driver == null || ((RemoteWebDriver) driver).getSessionId() == null) {
			switch (Config.getProperty("browser")) {
			case "firefox":
				System.setProperty("webdriver.gecko.driver", "src/test/resources/com/sprt/drivers/geckodriver");
				driver = new FirefoxDriver();
				break;

			case "chrome":
				System.setProperty("webdriver.chrome.driver", "src/test/resources/com/sprt/drivers/chromedriver");
				driver = new ChromeDriver();
				break;

			default:
				System.setProperty("webdriver.chrome.driver", Config.getProperty("chrome"));
				driver = new ChromeDriver();
			}
		}
		return driver;
	}
	public static void closeDriver() {
		if (driver != null) {
			driver.quit();
		}	
	}

}
