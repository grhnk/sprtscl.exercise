package challenge.step_definitions;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.support.ui.WebDriverWait;
import challenge.pages.Homepage;
import challenge.pages.Startpage;
import challenge.utilities.Config;
import challenge.utilities.Driver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class Tweet_Step_Def {
	WebDriverWait wait = new WebDriverWait(Driver.getInstance(), 10);
	@Given("^I am logged into SproutSocial$")
	public void as_a_user_I_should_be_able_to_sign_in_using_and() throws Throwable {
	  
		Startpage user = new Startpage();
	
		Driver.getInstance().get(Config.getProperty("baseurl"));
		user.login(Config.getProperty("email"), Config.getProperty("password"));
	   
	}

	@Then("^I should be able to send a message to my twitter account$")
	public void as_a_user_I_would_like_to_be_able_to_send_a_message_to_my_twitter_account() throws Throwable {
	    Homepage home = new Homepage();
	    String message = "Hello All!";
	    home.tweet(message);
	    Thread.sleep(2000);
	    home.messagesBtn.click();
	    home.filters.click();
	    boolean verified = checkSentTweet(message);
	    if(!verified){
		    	Driver.getInstance().navigate().refresh();
		    	assertTrue(checkSentTweet(message));
	    }
	    
	    
	}

	private boolean checkSentTweet(String message) {
		Homepage home = new Homepage();
		return home.messageList.get(0).getText().contains(message);
		
	}
	
	@Then("^I should be able to see incoming tweets and retweet or reply to them$")
	public void as_a_user_I_would_like_to_see_incoming_tweets_and_be_able_to_retweet_or_reply_to_them() throws Throwable {
	    Homepage home = new Homepage();
	    home.mentionsCheckbox.click();
	    
	    assertTrue(home.noMessage.isDisplayed());
	    
	}
	
	@Then("^I should be able to schedule a tweet for \"([^\"]*)\" and see that tweet on my Sprout calendar$")
	public void as_a_user_I_want_to_schedule_a_tweet_for_a_future_date_and_see_that_tweet_on_my_Sprout_calendar(String dateStamp) throws Throwable {
	    Homepage home = new Homepage();
	    home.scheduleMessage("Twitter scheduling Automation test",dateStamp);
	    home.verifyScheduled();
	    
	}
	
	@Then("^I logout from SproutSocial$")
	public void i_logout_from_SproutSocial() throws Throwable {
		 Driver.closeDriver();
	}
	
}





