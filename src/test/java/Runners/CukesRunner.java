package Runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"html:target/cucumber, json:target/report.json"},
		features="src/test/resources/com/sprt/features",
		glue = "challenge/step_definitions",
		//tags =  "@run",
		dryRun = false
		)
public class CukesRunner {

	
}
