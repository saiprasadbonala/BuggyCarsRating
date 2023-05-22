package pageobjects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Action;
import utils.WaitUtil;

public class ProfilePage {

	private WebDriver driver;

	@FindBy(id = "username")
	private WebElement txt_username;

	@FindBy(id = "firstName")
	private WebElement txt_firstName;

	@FindBy(id = "lastName")
	private WebElement txt_lastName;

	@FindBy(id = "gender")
	private WebElement txt_gender;

	@FindBy(id = "age")
	private WebElement txt_age;

	@FindBy(id = "address")
	private WebElement txt_address;

	@FindBy(id = "phone")
	private WebElement txt_phone;

	@FindBy(id = "hobby")
	private WebElement txt_hobby;

	@FindBy(xpath = "//button[normalize-space()='Save']")
	private WebElement btn_Save;

	@FindBy(xpath = "//div[@class='result alert alert-success hidden-md-down']")
	private WebElement msg_Success;

	public ProfilePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void updateProfile() {
		WaitUtil.waitForElementToBeVisible(driver, txt_username);
		assertFalse("Username field is not disabled", txt_username.isEnabled());
		System.out.println("Username field is disabled as expected");
		Action.enterText(driver, txt_firstName, "TestFN");
		Action.enterText(driver, txt_lastName, "UserLN");
		Action.enterText(driver, txt_gender, "Male");
		Action.enterText(driver, txt_age, "29");
		Action.enterText(driver, txt_address, "India");
		Action.enterText(driver, txt_phone, "+91 987654321");
		Action.selectByVisibleText(driver, txt_hobby, "Learning");
		Action.scrollToElement(driver, btn_Save);
		Action.clickElement(driver, btn_Save);
		assertEquals("Profile is not updated", "The profile has been saved successful", msg_Success.getText().trim());
		System.out.println("Profile is updated successfully");
	}
}
