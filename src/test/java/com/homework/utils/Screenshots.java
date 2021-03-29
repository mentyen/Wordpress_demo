package com.homework.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.MediaEntityBuilder;

public class Screenshots {
	public static String SCREENSHOT_FOLDER_PATH;
	private static final String SCREENSHOTS_FOLDER ="/AutomationReports/screenshots/";

	
	static {
		createDirectory();
	}
	
	
	protected static String captureScreenshot(WebDriver driver,String name) {
		String random=RandomStringUtils.randomNumeric(5);
		String destination="."+SCREENSHOTS_FOLDER+name+random+".png";
		TakesScreenshot ts=(TakesScreenshot)driver;
		File srcFile=ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile,new File(destination));
		}catch(Exception e) {
			System.out.println("Not able to capture screenshot");
		}
		return destination;
	}
	
	public static void addStepWithScreenshotInReport(WebDriver driver,String message) {
		if(driver!=null) {
			String path="."+Screenshots.captureScreenshot(driver,"screenshot");
			try {
				//ExtentTestManager.pass(message,MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			//ExtentTestManager.pass(message);
		}
	}
	
	public static void addFailStepWithScreenshotInReport(WebDriver driver,String message) {
		if(driver!=null) {
			String path="."+Screenshots.captureScreenshot(driver,"screenshot");
			try {
				//ExtentTestManager.fail(message,MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			//ExtentTestManager.fail(message);
		}
	}

	private static void createDirectory() {
		String drive=getDriveWithFreeSpace();
		if(drive.contains("C:")) {
			SCREENSHOT_FOLDER_PATH=System.getProperty("user.dir")+SCREENSHOTS_FOLDER;							
		}else {
			SCREENSHOT_FOLDER_PATH=System.getProperty("user.dir")+SCREENSHOTS_FOLDER;
		}
		
		if(!new File(SCREENSHOT_FOLDER_PATH).exists()) {
			File file=new File(SCREENSHOT_FOLDER_PATH);
			if(!file.exists()) {
				if(!file.mkdir()) {
					System.out.println("Fail to create screenshot dir:-->"+SCREENSHOT_FOLDER_PATH);
				}
			}
		}
		
	}

	private static String getDriveWithFreeSpace() {
		String drivePath=null;
		File[]avalDrives=File.listRoots();
		if(avalDrives.length>=1) {
			for(File file:avalDrives) {
				if(file.getFreeSpace()>100000000) {
					drivePath=file.toString();
				}
			}
		}
		return drivePath;
	}

}
