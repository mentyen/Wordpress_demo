package com.homework.pages;

import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.homework.utils.ConfigsReader;
import com.homework.utils.GenericMethods;
import com.homework.utils.WaitUtils;

public class WP_Me_Page extends GenericMethods {
	public WebDriver driver;

	public WP_Me_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	By username = By.xpath("//h2[text()='anatolytetenkin']");
	By profileHeader = By.xpath("//header[@class='formatted-header is-without-subhead is-left-align']");

	By firstName = By.id("first_name");
	By lastName = By.id("last_name");
	By displayName = By.id("display_name");
	By aboutMe = By.id("description");
	By saveProfileDetailsButton = By.xpath("//button[@type='submit']");
	By settingsSavedSuccessfully = By.xpath("//span[@class='notice__text' and text()='Settings saved successfully!']");
	By noticeDismissButton = By.xpath("//button[@class='notice__dismiss']");

	// CHANGE PFOTO
	By changePhoto = By.className("edit-gravatar__label");
	By changeMyPhoto = By.xpath("//div[@class='image-editor__content']/div[3]");
	By cancelButton = By.xpath("//button[text()='Cancel']");
	By resetButton = By.className("//button[text()='Reset']");
	By rotate = By.xpath("//div[@class='image-editor__toolbar']/button[1]");
	By flip = By.xpath("//div[@class='image-editor__toolbar']/button[2]");
	By lookingSharp = By.xpath("//span[text()='You successfully uploaded a new profile photo — looking sharp!']");

	// SIDEBAR
	By myProfile = By.xpath("//span[@class='sidebar__menu-link-text menu-link-text' and text()='My Profile']");
	By accountSettings = By
			.xpath("//span[@class='sidebar__menu-link-text menu-link-text' and text()='Account Settings']");
	By accountInformationHeader = By
			.xpath("//span[@class='section-header__label-text' and text()='Account Information']");

	By purchases = By.xpath("//span[@class='sidebar__menu-link-text menu-link-text' and text()='Purchases']");
	By purchasesHeader = By.xpath("//h1[@class='formatted-header__title wp-brand-font' and text()='Purchases']");

	By security = By.xpath("//span[@class='sidebar__menu-link-text menu-link-text' and text()='Security']");
	By securityHeader = By.xpath("//h1[@class='formatted-header__title wp-brand-font' and text()='Security']");

	By privacy = By.xpath("//span[@class='sidebar__menu-link-text menu-link-text' and text()='Privacy']");
	By privacyHeader = By.xpath("//h1[@class='formatted-header__title wp-brand-font' and text()='Privacy']");

	By ManageBlogs = By.xpath("//span[@class='sidebar__menu-link-text menu-link-text' and text()='Manage Blogs']");
	// lands on child page
	By myBlogsHeader = By.xpath("//h2[text()='My Blogs']");

	By notificationSettings = By
			.xpath("//span[@class='sidebar__menu-link-text menu-link-text' and text()='Notification Settings']");
	By notificationSettingsHeader = By.xpath("//header[@class='formatted-header is-without-subhead is-left-align']/h1");

	By blockedSites = By.xpath("//span[@class='sidebar__menu-link-text menu-link-text' and text()='Blocked Sites']");
	By blockedSitesHeader = By.xpath("//header[@class='formatted-header is-without-subhead is-left-align']/h1");

	By getApps = By.xpath("//span[ text()='Get Apps']");
	By getAppsHeader = By.xpath("//div[ @class='get-apps__illustration']");

	// MASTER BAR
	By mySite = By.xpath("//span[@class='masterbar__item-content' and text()='My Site']");
	By reader = By.xpath("//span[@class='masterbar__item-content' and text()='Reader']");
	By write = By.xpath("//span[@class='masterbar__item-content' and text()='Write']");
	By avatar = By.xpath("//img[@class='gravatar' and @alt='My Profile']");
	By gridIconBell = By.xpath("//a[@class='masterbar__item masterbar__item-notifications']");

	@SuppressWarnings("deprecation")
	public void isUserLandsOnMePage() {
		Assert.assertTrue(isDisplayed(profileHeader));

	}

	public void setFirstName(String value) {
		setInputValue(firstName, value + "_" + getRandom(5));
	}

	public void setLastName(String value) {
		setInputValue(lastName, value + "_" + getRandom(5));
	}

	public void setDisplayName(String value) {
		setInputValue(displayName, value + "_" + getRandom(5));
	}

	public void setAboutMe(String value) {
		setInputValue(aboutMe, value + "_" + getRandom(5));
	}

	public void tapSaveProfileDetailsButton() {
		clickElement(saveProfileDetailsButton, "Save Profile Details Button");
	}

	public void isSettingsSavedSuccessfully() {
		if (isDisplayed(settingsSavedSuccessfully)) {
			log("Settings Saved Successfully", false);
			scrollintoview(noticeDismissButton);
			clickElement(noticeDismissButton, "Notice Dismiss Button");
		} else {
			logFail("Fail to save Settings", false);
		}
		;
	}

	// MASTER BAR
	public void isMasterBar() {
		Map<String, By> elements = new LinkedHashMap<>();
		elements.put("My Site", mySite);
		elements.put("Reader", reader);
		elements.put("Write", write);
		elements.put("Avatar", avatar);
		elements.put("Grid Icon Bell", gridIconBell);
		elements.forEach((k, v) -> {
			WaitUtils.sleep(100);
			if (isDisplayed(v)) {
				log(k + " populates on Master Bar", false);
			} else {
				logFail(k + " fail to populate on master bar", false);
			}

		});

	}

	public void isBlockedSitesPopulatesOnMePage() {
		if (isDisplayed(blockedSitesHeader)) {
			log("Blocked Sites Details Populates On Me Page", false);
		} else {
			logFail("FBlocked Sites Details Fail to populates On Me Page", false);
		}
		;

	}

	public void tapGetApps() {
		clickElement(getApps, "Get Apps Button from left menue");

	}

	public void isGetAppsPopulatesOnMePage() {
		if (isDisplayed(getAppsHeader)) {
			log("Get Apps Header Details populates on Me Page", false);
		} else {
			logFail("Get Apps Header Details Fail to populates on Me Page", false);
		}
		;

	}

	public void tapBlockedSites() {
		clickElement(blockedSites, "Blocked Sites Button from left menue");
	}

	public void isNotificationSettingsPopulatesOnMePage() {
		if (isDisplayed(notificationSettingsHeader)) {
			log("Notification Settings Details Populates On Me Page", false);
		} else {
			logFail("Notification Settings Details Fail to Populates On Me Page", false);
		}
		;

	}

	public void tapNotificationSettings() {
		scrollBottom();
		clickElement(notificationSettings, "Notification Settings Button from left menue");
	}

	public void isUserLandsOnManageBlogsPage() {
		String childWindowsId = switchWindow("child");
		if (isDisplayed(myBlogsHeader)) {
			log("User Lands On Manage Blogs Page", false);
			closeWindow(childWindowsId);
		} else {
			logFail("User Fail to Lands On Manage Blogs Page", false);
		}
		;

	}

	public void tapManageBlogs() {
		clickElement(ManageBlogs, "Manage Blogs Button from left menue");
	}

	public void isPrivacyPagePopulatesOnMePage() {
		if (isDisplayed(privacyHeader)) {
			log("Privacy Details Populates On Me Page", false);
		} else {
			logFail("Privacy Details Fail to Populates On Me Page", false);
		}
		;

	}

	public void tapPrivacy() {
		clickElement(privacy, "Privacy Button from left menue");
	}

	public void isSecurityPopulatesOnMePage() {
		if (isDisplayed(securityHeader)) {
			log("Security Details Populates On Me Page", false);
		} else {
			logFail("Security Details Fail to Populates On Me Page", false);
		}
		;

	}

	public void tapSecurity() {
		clickElement(security, "Security Button from left menue");
	}

	public void isPurchasesPagePopulates() {
		if (isDisplayed(purchasesHeader)) {
			log("Purchases Details Populates on Me Page", false);
		} else {
			logFail("Purchases Details Fail to Populates on Me Page", false);
		}
		;

	}

	public void tapPurchases() {
		clickElement(purchases, "Purchases Button from left menue");

	}

	public void isAccountSettingsPopulatesOnMePage() {
		if (isDisplayed(accountInformationHeader)) {
			log("Account Settings Details Populates On Me Page", false);
		} else {
			logFail("Account Settings Details Fail to Populates On Me Page", false);
		}
		;

	}

	public void tapAccountSettings() {
		clickElement(accountSettings, "Account Settings Button from left menue");

	}

	public void tapClickToChangePhoto() {
		scrollTop();
		clickElement(changePhoto, "Click to change photo");
		WaitUtils.sleep(500);
	}

	public void tapChangeMyPhoto() {
		// clickElementJS(changeMyPhoto,"Change My Photo Button");
		for (int i = 0; i < 4; i++) {
			hitTab();
		}
		hitEnter();

	}

	public void isPhotoUpdated() {
		if (isDisplayed(lookingSharp)) {
			log("You successfully uploaded a new profile photo — looking sharp! Populates On Me Page", false);
			clickElement(noticeDismissButton, "Notice Dismiss Button");
		} else {
			logFail("You successfully uploaded a new profile photo — looking sharp! Fail to Populates On Me Page",
					false);
		}
		;

	}

	public void setFilePath(String string) {
		setInputValueRobot(string);

	}

	public void tapOpenButton() {
		hitEnter();
		WaitUtils.sleep(1000);

	}

	public void isBrokenLinks() {
		isAnyBrokenLinks(ConfigsReader.getAsString("ApplicationUrl"));
		
	}
}