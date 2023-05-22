package stepdefinitions;

import java.util.List;

import contexts.TestContext;
import enums.Context;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.HomePage;
import pageobjects.CarModelPage;
import pageobjects.RegistrationPage;
import utils.Constants;

public class VoteForModelSteps {

	TestContext testContext;
	HomePage homePage;
	RegistrationPage registrationPage;
	CarModelPage carModelPage;

	public VoteForModelSteps(TestContext context) {
		testContext = context;
		homePage = testContext.getPageObjectManager().getHomePage();
		registrationPage = testContext.getPageObjectManager().getRegistrationPage();
		carModelPage = testContext.getPageObjectManager().getCarModelPage();
	}

	@Given("User navigates to Register page")
	public void user_navigates_to_register_page() {
		homePage.clickOnRegister();
	}

	@Given("User registers successfully")
	public void user_registers_successfully(DataTable dataTable) {
		List<String> registrationDetails = dataTable.row(1);
		String username = registrationDetails.get(0);
		String firstName = registrationDetails.get(1);
		String lastName = registrationDetails.get(2);
		String password = registrationDetails.get(3);
		testContext.scenarioContext.setContext(Context.NEWREGISTERED_PASSWORD, password);
		String confirmPassword = registrationDetails.get(4);
		String newRegistered_Username = registrationPage.enterRegistrationDetails(username, firstName, lastName,
				password, confirmPassword);
		testContext.scenarioContext.setContext(Context.NEWREGISTERED_USERNAME, newRegistered_Username);
		registrationPage.clickOnRegisterButton();
		registrationPage.verifyRegistrationIsSuccess(true);
	}

	@When("User logs into the application")
	public void user_logs_into_the_application() {
		homePage.clickOnPageHeader();
		String newregistered_username = (String) testContext.scenarioContext.getContext(Context.NEWREGISTERED_USERNAME);
		String newregistered_password = (String) testContext.scenarioContext.getContext(Context.NEWREGISTERED_PASSWORD);
		homePage.enterUsername(newregistered_username);
		homePage.enterPassword(newregistered_password);
		homePage.clickLoginBtn();
		homePage.verifyUserLoggedInSuccesfully(true);
	}

	@When("navigate to Popular Model page")
	public void navigate_to_popular_model_page() {
		homePage.clickOnCard(Constants.POPULARMODEL_CARD);
	}

	@Then("User should be able to vote")
	public void user_should_be_able_to_vote() {
		carModelPage.voteForModel("Vote to test Comments feature");
	}

	@Then("User comments should be updated in the table")
	public void user_comments_should_be_updated_in_the_table() {
		carModelPage.verifyUserCommentsAreUpdated("Vote to test Comments feature");
	}
}
