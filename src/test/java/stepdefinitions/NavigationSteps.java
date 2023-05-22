package stepdefinitions;

import java.util.List;

import contexts.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.HomePage;
import pageobjects.OverallRatingPage;
import pageobjects.CarMakePage;
import pageobjects.CarModelPage;
import utils.Constants;

public class NavigationSteps {
	TestContext testContext;
	HomePage homePage;
	CarMakePage carMakePage;
	CarModelPage carModelPage;
	OverallRatingPage overallRatingPage;

	public NavigationSteps(TestContext context) {
		testContext = context;
		homePage = testContext.getPageObjectManager().getHomePage();
		carMakePage = testContext.getPageObjectManager().getCarMakePage();
		carModelPage = testContext.getPageObjectManager().getCarModelPage();
		overallRatingPage = testContext.getPageObjectManager().getOverallRatingPage();
	}

	@Given("User logged into the application")
	public void user_logged_into_the_application(DataTable dataTable) {
		List<String> registrationDetails = dataTable.row(1);
		String username = registrationDetails.get(0);
		String password = registrationDetails.get(1);
		homePage.enterUsername(username);
		homePage.enterPassword(password);
		homePage.clickLoginBtn();
		homePage.verifyUserLoggedInSuccesfully(true);
	}

	@Given("User clicks on Popular Make card")
	public void user_clicks_on_popular_make_card() {
		homePage.clickOnCard(Constants.POPULARMAKE_CARD);
	}

	@When("User clicks on Popular Make page header")
	public void user_clicks_on_popular_make_page_header() {
		carMakePage.clickOnPageHeader();
	}

	@When("User clicks on Popular Model page header")
	public void user_clicks_on_popular_model_page_header() {
		carModelPage.clickOnPageHeader();
	}

	@When("User clicks on Overall Rating page header")
	public void user_clicks_on_overall_rating_page_header() {
		overallRatingPage.clickOnPageHeader();
	}

	@Then("User is navigated back to Home page")
	public void user_is_navigated_back_to_home_page() {
		homePage.verifyHomePageIsDisplayed();
	}

	@Given("User clicks on Popular Model card")
	public void user_clicks_on_popular_model_card() {
		homePage.clickOnCard(Constants.POPULARMODEL_CARD);
	}

	@Given("User clicks on Overall Rating card")
	public void user_clicks_on_overall_rating_card() {
		homePage.clickOnCard(Constants.OVERALLRATING_CARD);
	}
}
