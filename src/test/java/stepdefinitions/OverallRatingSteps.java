package stepdefinitions;

import contexts.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.CarMakePage;
import pageobjects.CarModelPage;
import pageobjects.HomePage;
import pageobjects.OverallRatingPage;
import utils.Constants;

public class OverallRatingSteps {

	TestContext testContext;
	HomePage homePage;
	OverallRatingPage overallRatingPage;
	CarMakePage carMakePage;
	CarModelPage carModelPage;

	public OverallRatingSteps(TestContext context) {
		testContext = context;
		homePage = testContext.getPageObjectManager().getHomePage();
		overallRatingPage = testContext.getPageObjectManager().getOverallRatingPage();
		carMakePage = testContext.getPageObjectManager().getCarMakePage();
		carModelPage = testContext.getPageObjectManager().getCarModelPage();
	}

	@Given("User navigates to Overall Rating page")
	public void user_navigates_to_overall_rating_page() {
		homePage.clickOnCard(Constants.OVERALLRATING_CARD);
	}

	@When("User goes to last page of the cars list from pagination")
	public void user_goes_to_last_page_of_the_cars_list_from_pagination() {
		overallRatingPage.naviagateToPageInTable(overallRatingPage.tablePageCount());
	}

	@Then("Page Next button should be disabled")
	public void page_next_button_should_be_disabled() {
		overallRatingPage.verifyNextPageButtonStatus(false);
	}

	@When("User selects the {string} name {string}")
	public void user_selects_the_name(String column, String name) {
		overallRatingPage.clickOnLinkInTable(column, name);
	}

	@Then("User should be navigated to Make {string} page")
	public void user_should_be_navigated_to_make_page(String carMakeName) {
		carMakePage.verifyCarMakePageIsDispayed(carMakeName);
	}

	@Then("User should be navigated to Model {string} page")
	public void user_should_be_navigated_to_model_page(String carModelName) {
		carModelPage.verifyCarModelPageIsDispayed(carModelName);
	}
}
