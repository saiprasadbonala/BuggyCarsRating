package pageobjects;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Action;

public class RegistrationPage {

	private WebDriver driver;

	@FindBy(id = "username")
	private WebElement txt_username;

	@FindBy(id = "firstName")
	private WebElement txt_firstName;

	@FindBy(id = "lastName")
	private WebElement txt_lastName;

	@FindBy(id = "password")
	private WebElement txt_password;

	@FindBy(id = "confirmPassword")
	private WebElement txt_confirmPassword;

	@FindBy(xpath = "//button[@type='submit' and normalize-space()='Register']")
	private WebElement btn_register;

	@FindBy(xpath = "//div[contains(@class,'result alert')]")
	private WebElement alert_registrationStatusMsg;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String enterRegistrationDetails(String username, String firstName, String lastName, String password,
			String confirmPassword) {
		if (username.equalsIgnoreCase("Random")) {
			Random random = new Random();
			String alphaNum = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";
			char[] randomAlphaNum = alphaNum.toCharArray();
			char[] randomizedUsername = new char[10];
			for (int i = 0; i < 10; ++i) {
				randomizedUsername[i] = randomAlphaNum[random.nextInt(randomAlphaNum.length)];
			}
			username = new String(randomizedUsername);
		}

		Action.enterText(driver, txt_username, username);

		Action.enterText(driver, txt_firstName, firstName);

		Action.enterText(driver, txt_lastName, lastName);

		Action.enterText(driver, txt_password, password);

		Action.enterText(driver, txt_confirmPassword, confirmPassword);

		return username;
	}

	public void clickOnRegisterButton() {
		Action.clickElement(driver, btn_register);
		System.out.println("Clicked on Register button");
	}

	public void verifyRegistrationIsSuccess(boolean expectedStatus) {
		String actualMessage = alert_registrationStatusMsg.getText().trim();

		if (expectedStatus) {
			assertEquals("Registration of the user is not successful", "Registration is successful", actualMessage);
		} else {
			assertEquals("Error message is not displayed as expected", "UsernameExistsException: User already exists",
					actualMessage);
		}
	}
}
