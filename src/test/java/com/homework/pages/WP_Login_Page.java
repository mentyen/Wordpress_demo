package com.homework.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.homework.utils.GenericMethods;

public class WP_Login_Page extends GenericMethods{
	public WebDriver driver;
	
	public WP_Login_Page(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	By username=By.id("usernameOrEmail");
	By password=By.id("password");
	By loginButton=By.cssSelector("button[type='submit']");

	public void setUserName(String asString) {
		setUserName(username,asString);		
		
	}
	public void tapSubmitButton() {
		clickElement(loginButton,"Login button");		
	}

	public void setPassword(String asString) {
		setPassword(password,asString);		
	}

	public void tapLoginButton() {
		clickElement(loginButton,"Login button");		
	}	
	
	
}