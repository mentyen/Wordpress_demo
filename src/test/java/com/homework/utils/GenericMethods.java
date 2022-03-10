package com.homework.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

public class GenericMethods {
	public WebDriver driver;
	private FluentWait<WebDriver> fluentWait;
	private Duration fluentWaitDuration = Duration.ofSeconds(ConfigsReader.getAsInt("FLUENT_WAIT"));
	private Duration puulingIntervals = Duration.ofMillis(ConfigsReader.getAsInt("POLLING_INTERVAL"));
	private final JavascriptExecutor jsExecutor;
	Logger logger ;

	public GenericMethods(WebDriver driver) {
		logger=Logger.getLogger("logger");
		this.driver = driver;
		fluentWait = new FluentWait<>(driver).withTimeout(fluentWaitDuration).pollingEvery(puulingIntervals)
				.ignoring(NoSuchElementException.class).ignoring(ElementNotVisibleException.class)
				.ignoring(ElementNotSelectableException.class).ignoring(StaleElementReferenceException.class)
				.ignoring(ElementClickInterceptedException.class).ignoring(ElementNotInteractableException.class);
		jsExecutor = (JavascriptExecutor) driver;
		//PropertyConfigurator.configure("src/test/resources/log4j.properties");
	}

	protected void log(String msg, boolean isScreenshotRequired) {
		logger.info(msg);
		if (!isScreenshotRequired) {
			// ExtentTestManager.pass(String.valueOf(msg));
		} else {
			// Screenshots.addStepWithScreenshotInReport(driver, String.valueOf(msg));
		}
	}

	protected void logFail(String msg, boolean isScreenshotRequired) {
		logger.info(msg);
		if (!isScreenshotRequired) {
			// ExtentTestManager.fail(String.valueOf(msg));
		} else {
			// Screenshots.addFailStepWithScreenshotInReport(driver, String.valueOf(msg));
		}
	}

	protected String getRandom(int num) {
		return RandomStringUtils.randomAlphabetic(num);
	}

	protected List<WebElement> getElements(final By by) {
		return fluentWait.until(new ExpectedCondition<List<WebElement>>() {
			public List<WebElement> apply(WebDriver driver) {
				return driver.findElements(by);
			}
		});
	}

	protected WebElement getElement(final By by) {
		return fluentWait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(by);
			}
		});
	}

	protected boolean isDisplayed(final By by) {
		try {
			WebElement element = getElement(by);
			highlightElement(element);
			return element.isDisplayed();
		} catch (Exception e) {
			logFail("Element is not visible, as result exceptions occure >>> " + e, false);
		}
		return false;
	}

	private void highlightElement(WebElement element) {
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
				"color: yellow; border: 2px solid cyan;");
		WaitUtils.sleep(500);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");

	}

	protected String getText(final By by) {
		try {
			WebElement element = getElement(by);
			return element.getText();
		} catch (Exception e) {
			Assert.fail("Fail to get text from element, as result exceptions occure >>> " + e);
		}
		return null;
	}

	protected void setInputValue(final By by, final String value) {
		try {
			WebElement element = getElement(by);
			element.clear();
			element.sendKeys(value);
			log("User set value:-->" + value, false);

		} catch (Exception e) {
			Assert.fail("Fail to send key, as result exception occure >>> " + e);
		}
	}

	protected void setInputValueRobot(String value) {
		StringSelection stringSelection = new StringSelection(value);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, stringSelection);

		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			WaitUtils.sleep(150);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			WaitUtils.sleep(250);
		} catch (AWTException e) {
			e.printStackTrace();
		}

	}

	protected void setUserName(final By by, final String value) {
		try {
			WebElement element = getElement(by);
			element.clear();
			element.sendKeys(value);
			log("User set user name:--> xxxxxx", false);

		} catch (Exception e) {
			Assert.fail("Fail to send key, as result exception occure >>>" + e);
		}
	}

	protected void setPassword(final By by, final String value) {
		try {
			WebElement element = getElement(by);
			element.clear();
			element.sendKeys(value);
			log("User set password:--> xxxxxx", false);

		} catch (Exception e) {
			Assert.fail("Fail to send key, as result exception occure >>>" + e);
		}
	}

	protected void clickElement(final By by, final String value) {
		try {
			getElement(by).click();
			log("User tap:-->" + value, false);
		} catch (Exception e) {
			Assert.fail("Fail to perform click, as result exception occure >>>" + e);
		}
	}

	protected void clickElementJS(final By by, final String value) {
		try {
			jsExecutor.executeScript("arguments[0].click();", getElement(by));
			log("User tap:-->" + value, false);
		} catch (Exception e) {
			Assert.fail("Fail to perform click, as result exception occure >>>" + e);
		}
	}

	protected String switchWindow(String window) {
		String parent = null;
		String child = null;
		parent = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		if (window.contains("child")) {
			while (it.hasNext()) {
				child = it.next().toString();
				if (!parent.equalsIgnoreCase(child)) {
					driver.switchTo().window(child);
				} else {
					logFail("Fail to switch to child windows", false);
				}
			}
		} else if (window.contains("parent")) {
			driver.switchTo().window(parent);
		} else {
			logFail("Windows does not found, select from  >>> child/parent", false);
		}
		return child;
	}

	protected void closeWindow(String window) {
		String parent = null;
		String curr = null;

		final Set<String> handles = driver.getWindowHandles();
		final Iterator<String> it = handles.iterator();
		while (it.hasNext()) {
			curr = it.next().toString();
			if (curr.trim().equalsIgnoreCase(window)) {
				try {
					driver.switchTo().window(window);
					driver.close();
					if (parent != null) {
						driver.switchTo().window(parent);
						break;
					}
				} catch (Exception ex) {

				}

			} else
				parent = curr;
		}

	}

	protected boolean isEmpty(String str) {
		if (str != null && !str.isEmpty())
			return true;
		else
			return false;
	}

	protected void hitEnter() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			WaitUtils.sleep(250);
		} catch (Exception e) {

		}
	}

	protected void hitTab() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			WaitUtils.sleep(50);
		} catch (Exception e) {

		}
	}

	protected void scrollintoview(final By by) {
		jsExecutor.executeScript("arguments[0].scrollIntoView();", getElement(by));
	}

	protected void scrollTop() {
		jsExecutor.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
	}

	protected void scrollBottom() {
		long lastHeight = (long) (jsExecutor.executeScript("return document.body.scrollHeight"));
		while (true) {
			jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			WaitUtils.sleep(100);
			long newHeight = (long) (jsExecutor.executeScript("return document.body.scrollHeight"));
			if (newHeight == lastHeight) {
				break;
			}
			lastHeight = newHeight;
		}
	}

	protected void isAnyBrokenLinks(String homePage) {
		String url = null;
		HttpURLConnection huc = null;
		int respCode;

		List<WebElement> links = getElements(By.tagName("a"));
		Iterator<WebElement> it = links.iterator();
		while (it.hasNext()) {
			url = it.next().getAttribute("href");
			// System.out.println("URL >>> "+url);
			if (url == null || url.isEmpty()) {
				logger.info(url + " >>> is either not configured for anchor tag or it is empty");
				continue;
			}
			if (!url.startsWith(homePage)) {
				logger.info(url + " >>> belongs to another domain, skipping it.");
				continue;
			}

			try {
				huc = (HttpURLConnection) (new URL(url).openConnection());
				huc.setRequestMethod("HEAD");
				huc.connect();
				respCode = huc.getResponseCode();
				if (respCode >= 400) {
					logger.info(url + " >>> is a broken link");
				} else {
					logger.info(url + " >>> is a valid link");
				}

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
