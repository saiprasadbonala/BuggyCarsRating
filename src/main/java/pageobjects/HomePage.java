package pageobjects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Action;
import utils.WaitUtil;

public class HomePage {

	private WebDriver driver;

	@FindBy(name = "login")
	private WebElement txt_username;

	@FindBy(name = "password")
	private WebElement txt_password;

	@FindBy(xpath = "//button[normalize-space()='Login']")
	private WebElement btn_login;

	@FindBy(xpath = "//a[normalize-space()='Register']")
	private WebElement lnkTxt_register;

	@FindBy(xpath = "//li/a[normalize-space()='Profile']")
	private WebElement lnkTxt_Profile;

	@FindBy(xpath = "//li/a[normalize-space()='Logout']")
	private WebElement lnkTxt_Logout;

	@FindBy(xpath = "//a[normalize-space()='Buggy Rating' and contains(@class,'navbar')]")
	private WebElement lnk_pageHeader;

	@FindBy(xpath = "//*[contains(@class,'label-warning')]")
	private WebElement msg_InvalidLogin;

	@FindBy(xpath = "//div[@class='card' and descendant::*[normalize-space()='Popular Make']]/a")
	private WebElement popularMake;

	@FindBy(xpath = "//div[@class='card' and descendant::*[normalize-space()='Popular Model']]/a")
	private WebElement popularModel;

	@FindBy(xpath = "//div[@class='card' and descendant::*[normalize-space()='Overall Rating']]/a")
	private WebElement overallRating;

	@FindBy(xpath = "//div[@class='card']/a")
	private List<WebElement> cards;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnRegister() {
		Action.clickElement(driver, lnkTxt_register);
		System.out.println("Clicked on Register link");
	}

	public void enterUsername(String username) {
		Action.enterText(driver, txt_username, username);
	}

	public void enterPassword(String password) {
		Action.enterText(driver, txt_password, password);
	}

	public void clickLoginBtn() {
		Action.clickElement(driver, btn_login);
		System.out.println("Clicked on Login button");
	}

	public void clickOnPageHeader() {
		Action.clickElement(driver, lnk_pageHeader);
	}

	public void verifyUserLoggedInSuccesfully(boolean expectedStatus) {

		if (expectedStatus) {
			boolean isDisplayed = false;
			try {
				if (lnkTxt_Profile.isDisplayed()) {
					isDisplayed = true;
					System.out.println("Login page is displayed");
				}
			} catch (Exception e) {
				assertFalse("Login page is not displayed", isDisplayed);
			}
		} else {
			String actualMessage = msg_InvalidLogin.getText().trim();
			assertEquals("Error message is not displayed as expected", "Invalid username/password", actualMessage);
		}
	}

	public void logout() {
		Action.clickElement(driver, lnkTxt_Logout);
		assertTrue("User logout failed", btn_login.isDisplayed());
		System.out.println("User logged out successfully");
	}

	public void clickOnCard(String cardName) {
		if (cardName.equals("Popular Make")) {
			Action.clickElement(driver, popularMake);
		} else if (cardName.equals("Popular Model")) {
			Action.clickElement(driver, popularModel);
		} else if (cardName.equals("Overall Rating")) {
			Action.clickElement(driver, overallRating);
		} else {
			System.out.println("Card name entered is not avilable");
		}

		WaitUtil.untilPageIsLoaded(driver);
	}

	public void verifyHomePageIsDisplayed() {
		WaitUtil.untilPageIsLoaded(driver);
		assertTrue("Home Page is not displayed", cards.size() > 0);
		System.out.println("Home Page is displayed");
	}

	public void verifyProfileLinkIsDisplayed() {
		assertTrue("Profile option is not displayed on Home page", lnkTxt_Profile.isDisplayed());
		System.out.println("Profile option is not displayed on Home page");
	}

	public void clickOnProfile() {
		Action.clickElement(driver, lnkTxt_Profile);
	}
}
