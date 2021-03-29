package com.homework.pageobjectmanager;

import org.openqa.selenium.WebDriver;

import com.homework.pages.WP_Login_Page;
import com.homework.pages.WP_Me_Page;

public class PageObjectManager {
	private WebDriver driver;
	
	private WP_Login_Page loginPage;
	private WP_Me_Page mePage;
	
	public PageObjectManager(WebDriver driver) {
		this.driver=driver;
	}
	
	public WP_Login_Page getLoginPage() {
		return (loginPage==null)? loginPage=new WP_Login_Page(driver):loginPage;
	}
	
	public WP_Me_Page getMePage() {
		return (mePage==null)? mePage=new WP_Me_Page(driver):mePage;
	}


}
