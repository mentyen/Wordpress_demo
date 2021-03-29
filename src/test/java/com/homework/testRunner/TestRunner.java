package com.homework.testRunner;

import java.io.IOException;

import org.testng.annotations.AfterClass;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//@RunWith(Cucumber.class)
@CucumberOptions(features = {"src//test//resources//features"}
, glue = {"com.homework.stepdefinition"}
,plugin = {"pretty", "html:target/cucumber-default-reports", "json:target/cucumber.json",}
, tags = { "@WORDPRESS_SMOKE_MEPAGE" }
, monochrome = true
, strict = true
, dryRun = false)

public class TestRunner extends AbstractTestNGCucumberTests{
	@AfterClass
	public void tearDown() {
		Runtime rt=Runtime.getRuntime();
		try {
			rt.exec("taskkill /F /IM chrome.exe /T");
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
}
