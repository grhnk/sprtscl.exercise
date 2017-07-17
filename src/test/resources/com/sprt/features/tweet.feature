Feature: Tweeting
 
  Background: 
    Given I am logged into SproutSocial

  Scenario: Send message verification
    Then I should be able to send a message to my twitter account
    And I logout from SproutSocial

  Scenario: Incoming message verification
    Then I should be able to see incoming tweets and retweet or reply to them
    And I logout from SproutSocial

  Scenario: Schedule tweet verification
    Then I should be able to schedule a tweet for "2017-07-27" and see that tweet on my Sprout calendar
    And I logout from SproutSocial
