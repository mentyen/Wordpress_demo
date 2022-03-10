package com.homework.stepdefinition;

import com.homework.utils.ConfigsReader;
import com.homework.utils.Constants;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class WP_StepDefinition extends AbstractSteps{

	@Given("User launch Application URL")
	public void user_launch_Application_URL() {
	    startDriver();
	    getDriver().get(ConfigsReader.getAsString("ApplicationUrl"));
	}

	@Given("User set username")
	public void user_set_username() {
		pageObjectManager.getLoginPage().setUserName(ConfigsReader.getAsString("UserName"));
		pageObjectManager.getLoginPage().tapSubmitButton();
	}

	@Given("User set password")
	public void user_set_password() {
		pageObjectManager.getLoginPage().setPassword(ConfigsReader.getAsString("Password"));
	}

	@Given("User click login button")
	public void user_click_login_button() {
		pageObjectManager.getLoginPage().tapLoginButton();
	}

	@Then("Verify user lands on home page")
	public void verify_user_lands_on_home_page() {
		pageObjectManager.getMePage().isUserLandsOnMePage();		
	}
	
	@Then("Verify all elements populating on Master Bar")
	public void verify_master_bar() {
		pageObjectManager.getMePage().isMasterBar();		
	}
	
	@Given("User set first name")
	public void user_set_first_name() {
		pageObjectManager.getMePage().setFirstName(DefaultStepDefinition.currentIterationMap.get("FirstName"));
	}

	@Given("User set last name")
	public void user_set_last_name() {
		pageObjectManager.getMePage().setLastName(DefaultStepDefinition.currentIterationMap.get("LastName"));
	}

	@Given("User set Public display name")
	public void user_set_Public_display_name() {
		pageObjectManager.getMePage().setDisplayName(DefaultStepDefinition.currentIterationMap.get("DisplayName"));
	}

	@Given("User set About me")
	public void user_set_About_me() {
		pageObjectManager.getMePage().setAboutMe(DefaultStepDefinition.currentIterationMap.get("AboutMe"));
	}

	@Given("User tap Save profile details button")
	public void user_tap_Save_profile_details_button() {
		pageObjectManager.getMePage().tapSaveProfileDetailsButton();
	}

	@Then("Verify entered records populates on account profile page")
	public void verify_entered_records_populates_on_account_profile_page() {
		pageObjectManager.getMePage().isSettingsSavedSuccessfully();
	}
	
	@Given("User tap Click to change photo")
	public void user_tap_Click_to_change_photo() {
		pageObjectManager.getMePage().tapClickToChangePhoto();
	    
	}

	@Given("User set file path and tap open")
	public void user_set_file_path_and_tap_open() {		
		pageObjectManager.getMePage().setFilePath(Constants.AVATAR);
		pageObjectManager.getMePage().tapOpenButton();
	    
	}

	@Given("User tap Change My Photo")
	public void user_tap_Change_My_Photo() {
		pageObjectManager.getMePage().tapChangeMyPhoto();
	    
	}

	@Then("Verify You successfully uploaded a new profile photo text populates on My Profile page")
	public void verify_You_successfully_uploaded_a_new_profile_photo_text_populates_on_My_Profile_page() {
		pageObjectManager.getMePage().isPhotoUpdated();
	    
	}

	@Given("User tap Account Settings")
	public void user_tap_Account_Settings() {
		pageObjectManager.getMePage().tapAccountSettings();
	    
	}

	@Then("Verify user lands on Account settings page")
	public void verify_user_lands_on_Account_settings_page() {
		pageObjectManager.getMePage().isAccountSettingsPopulatesOnMePage();
	    
	}

	@Given("User tap Purchases")
	public void user_tap_Purchases() {
		pageObjectManager.getMePage().tapPurchases();
	    
	}

	@Then("Verify user lands on Purchases page")
	public void verify_user_lands_on_Purchases_page() {
		pageObjectManager.getMePage().isPurchasesPagePopulates();
	    
	}

	@Given("User tap Security")
	public void user_tap_Security() {
		pageObjectManager.getMePage().tapSecurity();
	    
	}

	@Then("Verify user lands on Security page")
	public void verify_user_lands_on_Security_page() {
		pageObjectManager.getMePage().isSecurityPopulatesOnMePage();
	    
	}

	@Given("User tap Privacy")
	public void user_tap_Privacy() {
		pageObjectManager.getMePage().tapPrivacy();
	    
	}

	@Then("Verify user lands on Privacy page")
	public void verify_user_lands_on_Privacy_page() {
		pageObjectManager.getMePage().isPrivacyPagePopulatesOnMePage();
	    
	}

	@Given("User tap Manage Blogs")
	public void user_tap_Manage_Blogs() {
		pageObjectManager.getMePage().tapManageBlogs();
	    
	}

	@Then("Verify user lands on Manage Blogs page")
	public void verify_user_lands_on_Manage_Blogs_page() {
		pageObjectManager.getMePage().isUserLandsOnManageBlogsPage();
	    
	}

	@Given("User tap Notification Settings")
	public void user_tap_Notification_Settings() {
		pageObjectManager.getMePage().tapNotificationSettings();
	    
	}

	@Then("Verify user lands on Notification Settings page")
	public void verify_user_lands_on_Notification_Settings_page() {
		pageObjectManager.getMePage().isNotificationSettingsPopulatesOnMePage();
	    
	}

	@Given("User tap Blocked Sites")
	public void user_tap_Blocked_Sites() {
		pageObjectManager.getMePage().tapBlockedSites();
	    
	}

	@Then("Verify user lands on Blocked Sites page")
	public void verify_user_lands_on_Blocked_Sites_page() {
		pageObjectManager.getMePage().isBlockedSitesPopulatesOnMePage();
	    
	}

	@Given("User tap Get Apps")
	public void user_tap_Get_Apps() {
		pageObjectManager.getMePage().tapGetApps();
	    
	}

	@Then("Verify user lands on Get Apps page")
	public void verify_user_lands_on_Get_Apps_page() {
		pageObjectManager.getMePage().isGetAppsPopulatesOnMePage();
	    
	}
	
	@Then("Validate all broken links")
	public void verify_broken_links() {
		pageObjectManager.getMePage().isBrokenLinks();
	    
	}
}
