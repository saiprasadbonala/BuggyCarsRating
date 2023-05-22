package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Action {

	public static void clickElement(WebDriver driver, WebElement el) {
		WaitUtil.untilPageIsLoaded(driver);
		el.click();
		WaitUtil.untilPageIsLoaded(driver);
	}

	public static void enterText(WebDriver driver, WebElement el, String inputText) {
		WaitUtil.untilPageIsLoaded(driver);
		el.clear();
		el.sendKeys(inputText);
	}

	public static void scrollToElement(WebDriver driver, WebElement el) {
		WaitUtil.untilPageIsLoaded(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
		WaitUtil.untilPageIsLoaded(driver);
		WaitUtil.waitFor(5);
	}

	public static String getElementTextJS(WebDriver driver, WebElement el) {
		WaitUtil.untilPageIsLoaded(driver);
		return ((JavascriptExecutor) driver).executeScript("return arguments[0].value", el).toString();
	}

	public static void selectByVisibleText(WebDriver driver, WebElement el, String text) {
		Select select = new Select(el);
		select.selectByVisibleText(text);
		WaitUtil.untilPageIsLoaded(driver);
	}
}
