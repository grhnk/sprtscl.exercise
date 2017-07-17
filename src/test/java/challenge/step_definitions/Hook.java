package challenge.step_definitions;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import challenge.utilities.Driver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hook {

	@Before
	public void setUp() {
		Driver.getInstance().manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
		Driver.getInstance().manage().timeouts().pageLoadTimeout(12, TimeUnit.SECONDS);
	}

	@After
	public void tearDown(Scenario scenario) throws InterruptedException {
		if (scenario.isFailed()) {
			final byte[] screenshot = ((TakesScreenshot) Driver.getInstance()).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
		}
		Thread.sleep(1000);
		//Driver.getInstance().quit();

	}

}
