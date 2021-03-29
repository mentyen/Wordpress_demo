package com.homework.stepdefinition;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.homework.pageobjectmanager.PageObjectManager;
import com.homework.utils.ConfigsReader;
import com.homework.utils.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class AbstractSteps {
	
	protected static PageObjectManager pageObjectManager ;
	public static WebDriver driver;	


	public static void startDriver() {

		ConfigsReader.readProperties(Constants.CREDENTIALS_FILEPATH);
		String browser = ConfigsReader.getAsString("browser");

		if (browser.equalsIgnoreCase("chrome")) {			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver");
			driver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "src/test/resources/drivers/IEDriverServer");
			driver = new InternetExplorerDriver();
		} 	
		
		driver.manage().timeouts().pageLoadTimeout(ConfigsReader.getAsInt("PAGE_LOAD"), TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(ConfigsReader.getAsInt("IMPLICIT_WAIT"), TimeUnit.SECONDS);		
		driver.manage().window().maximize();		
		pageObjectManager=new PageObjectManager(driver);
	}

	public static void tearDown() {
		driver.quit();
	}
	
	public WebDriver getDriver() {
		return driver;
	}


}
