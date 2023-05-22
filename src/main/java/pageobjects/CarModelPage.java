package pageobjects;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Action;
import utils.WaitUtil;

public class CarModelPage {

	private WebDriver driver;

	@FindBy(xpath = "//a[normalize-space()='Buggy Rating' and contains(@class,'navbar')]")
	private WebElement lnk_pageHeader;

	@FindBy(id = "comment")
	private WebElement comment;

	@FindBy(xpath = "//button[normalize-space()='Vote!']")
	private WebElement btn_Vote;

	@FindBy(xpath = "//h4[descendant::text()='Votes: ']/strong")
	private WebElement voteCount;

	@FindBy(xpath = "//p[@class='card-text']")
	private WebElement msg_Vote_ThankYou;

	@FindBy(xpath = "//table/tbody/tr[1]/td[3]")
	private WebElement tb_firstComment;

	@FindBy(xpath = "//div[@class='row']/h3")
	private WebElement lbl_CarModel;

	public CarModelPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnPageHeader() {
		Action.clickElement(driver, lnk_pageHeader);
	}

	public void voteForModel(String comments) {
		WaitUtil.untilPageIsLoaded(driver);
		Action.enterText(driver, comment, comments);
		Action.clickElement(driver, btn_Vote);
		WaitUtil.waitForElementToBeVisible(driver, msg_Vote_ThankYou);
		assertEquals("Expected message is not displayed", "Thank you for your vote!",
				msg_Vote_ThankYou.getText().trim());
	}

	public void verifyUserCommentsAreUpdated(String expectedComments) {
		Action.scrollToElement(driver, tb_firstComment);
		assertEquals("Comments are not updated in the table", expectedComments, tb_firstComment.getText().trim());
		System.out.println("Comments are updated in the atble successfully");
	}

	public void verifyCarModelPageIsDispayed(String expectedCarModel) {
		WaitUtil.untilPageIsLoaded(driver);
		Action.scrollToElement(driver, lbl_CarModel);
		assertEquals("Car Model page '" + expectedCarModel + "' is not dispayed", expectedCarModel,
				lbl_CarModel.getText().trim());
		System.out.println("Car Model page '" + expectedCarModel + "' is dispayed");
	}
}
