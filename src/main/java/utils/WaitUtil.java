package utils;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dataproviders.PropertiesFileReader;

public class WaitUtil {

	private static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition, Duration timeout) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeout);
		webDriverWait.withTimeout(timeout);

		try {
			webDriverWait.until(waitCondition);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void untilPageIsLoaded(WebDriver driver) {
		untilPageLoadComplete(driver, PropertiesFileReader.getInstance().getImplicitlyWait());
		untilJqueryIsDone(driver, PropertiesFileReader.getInstance().getImplicitlyWait());
	}

	public static void untilPageLoadComplete(WebDriver driver, Long timeoutInSeconds) {
		until(driver, (d) -> {
			Boolean isPageLoaded = (Boolean) ((JavascriptExecutor) driver).executeScript("return document.readyState")
					.equals("complete");
			if (!isPageLoaded)
				System.out.println("Document is loading");
			return isPageLoaded;
		}, Duration.ofSeconds(timeoutInSeconds));
	}

	public static void untilJqueryIsDone(WebDriver driver, Long timeoutInSeconds) {
		until(driver, (d) -> {
			Boolean isJqueryCallDone = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active==0");
			if (!isJqueryCallDone)
				System.out.println("JQuery call is in Progress");
			return isJqueryCallDone;
		}, Duration.ofSeconds(timeoutInSeconds));
	}

	public static void waitForElementToBeVisible(WebDriver driver, WebElement el) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(el));
	}

	public static void waitFor(int timeInSeconds) {
		try {
			Thread.sleep(timeInSeconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
