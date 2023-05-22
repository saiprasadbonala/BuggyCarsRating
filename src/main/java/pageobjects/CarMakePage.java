package pageobjects;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Action;
import utils.WaitUtil;

public class CarMakePage {

	private WebDriver driver;

	@FindBy(xpath = "//a[normalize-space()='Buggy Rating' and contains(@class,'navbar')]")
	private WebElement lnk_pageHeader;

	@FindBy(xpath = "//h3[@class='card-header']")
	private WebElement lbl_CarMake;

	public CarMakePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnPageHeader() {
		Action.clickElement(driver, lnk_pageHeader);
	}

	public void verifyCarMakePageIsDispayed(String expectedCarMake) {
		WaitUtil.untilPageIsLoaded(driver);
		Action.scrollToElement(driver, lbl_CarMake);
		assertEquals("Car Make page '" + expectedCarMake + "' is not displayed", expectedCarMake,
				lbl_CarMake.getText().trim());
		System.out.println("Car Make page '" + expectedCarMake + "' is displayed");
	}
}
