package challenge.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import challenge.utilities.Driver;

public class Page {
	
	public static void scrollToElement(WebElement element) {
		 
		JavascriptExecutor js = (JavascriptExecutor) Driver.getInstance();
		 
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		 

		}
	
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
