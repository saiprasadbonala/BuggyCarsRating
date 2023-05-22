package pageobjects;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Action;
import utils.WaitUtil;

public class OverallRatingPage {

	private WebDriver driver;

	@FindBy(xpath = "//a[normalize-space()='Buggy Rating' and contains(@class,'navbar')]")
	private WebElement lnk_pageHeader;

	@FindBy(xpath = "//table/tbody/tr/td[2]/a")
	private List<WebElement> carMakeList;

	@FindBy(xpath = "//table/tbody/tr/td[3]/a")
	private List<WebElement> carModelList;

	@FindBy(xpath = "//parent::div[contains(.,'page') and @class='pull-xs-right']/a[1]")
	private WebElement lnk_previousPage;

	@FindBy(xpath = "//parent::div[contains(.,'page') and @class='pull-xs-right']/a[2]")
	private WebElement lnk_nextPage;

	@FindBy(xpath = "//parent::div[contains(.,'page') and @class='pull-xs-right']")
	private WebElement pagination;

	@FindBy(xpath = "//parent::div[contains(.,'page') and @class='pull-xs-right']/input")
	private WebElement input_CurrentPage;

	public OverallRatingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnPageHeader() {
		Action.clickElement(driver, lnk_pageHeader);
	}

	public void clickOnLinkInTable(String column, String name) {
		WaitUtil.untilPageIsLoaded(driver);

		boolean isFound = false;
		if (column.trim().equals("Make")) {
			isFound = searchInTheTableList(carMakeList, name);
		} else if (column.trim().equals("Model")) {
			isFound = searchInTheTableList(carModelList, name);
		} else {
			System.out.println("Column name is entered incorrectly");
		}

		if (!isFound) {
			System.out.println(
					"The " + column + " '" + name + "' is not available in the list. Please check the input data");
		}

		WaitUtil.untilPageIsLoaded(driver);
	}

	public int tablePageCount() {
		WaitUtil.untilPageIsLoaded(driver);
		String paginationText = pagination.getText().trim();
		String lastPageNumber = paginationText.substring(paginationText.indexOf("of") + 3, paginationText.length());
		System.out.println("Tota no. of Pages in the table: " + lastPageNumber);
		return Integer.parseInt(lastPageNumber);
	}

	public int currentPageNumber() {
		WaitUtil.untilPageIsLoaded(driver);
		Action.scrollToElement(driver, input_CurrentPage);
		String currentPageNumber = Action.getElementTextJS(driver, input_CurrentPage);
		System.out.println("Current Page Number: " + currentPageNumber);
		return Integer.parseInt(currentPageNumber);
	}

	public boolean searchInTheTableList(List<WebElement> searchList, String searchName) {
		boolean isFound = false;

		do {
			for (WebElement searchItem : searchList) {
				if (searchItem.getText().trim().equals(searchName)) {
					isFound = true;
					Action.clickElement(driver, searchItem);
					WaitUtil.waitFor(5);
					return isFound;
				}
			}

			if (!isFound) {
				if (currentPageNumber() < tablePageCount()) {
					Action.clickElement(driver, lnk_nextPage);
					WaitUtil.waitFor(5);
				} else {
					break;
				}
			}
		} while (!isFound);

		return isFound;
	}

	public void naviagateToPageInTable(int pageNumber) {
		int currentPage = currentPageNumber();
		Action.scrollToElement(driver, lnk_nextPage);
		while (currentPage != pageNumber && pageNumber <= tablePageCount()) {
			Action.clickElement(driver, lnk_nextPage);
			currentPage = currentPageNumber();
		}
		WaitUtil.untilPageIsLoaded(driver);
	}

	public boolean isNextPageEnabled() {
		return lnk_nextPage.isEnabled();
	}

	/**
	 * Verify Next page button is enabled or displayed
	 * 
	 * @paramexpectedStatus 'true' if enabled (or) 'false' if disabled is expected
	 */
	public void verifyNextPageButtonStatus(boolean expectedStatus) {
		if (expectedStatus) {
			assertTrue("Next page button is not enabled", isNextPageEnabled());
			System.out.println("Next page button is enabled as expected");
		} else {
			assertFalse("Next page button is not disabled", isNextPageEnabled());
			System.out.println("Next page button is disabled as expected");
		}
	}
}
