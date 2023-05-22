package stepdefinitions;

import java.util.List;

import contexts.TestContext;
import enums.Context;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.HomePage;
import pageobjects.RegistrationPage;

public class UserRegistrationAndLoginSteps {

	TestContext testContext;
	HomePage homePage;
	RegistrationPage registrationPage;

	public UserRegistrationAndLoginSteps(TestContext context) {
		testContext = context;
		homePage = testContext.getPageObjectManager().getHomePage();
		registrationPage = testContext.getPageObjectManager().getRegistrationPage();
	}

	@Given("User is on register page")
	public void user_is_on_register_page() {
		homePage.clickOnRegister();
	}

	@When("User enters all the required details")
	public void user_enters_all_the_required_details(DataTable dataTable) {
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
	}

	@When("Click on Register button")
	public void click_on_register_button() {
		registrationPage.clickOnRegisterButton();
	}

	@Then("User should be registered successfully")
	public void user_should_be_registered_successfully() {
		registrationPage.verifyRegistrationIsSuccess(true);
	}

	@Then("User should login with registered credentials successfully")
	public void user_should_login_with_registered_credentials_successfully() {
		homePage.clickOnPageHeader();
		String newregistered_username = (String) testContext.scenarioContext.getContext(Context.NEWREGISTERED_USERNAME);
		String newregistered_password = (String) testContext.scenarioContext.getContext(Context.NEWREGISTERED_PASSWORD);
		homePage.enterUsername(newregistered_username);
		homePage.enterPassword(newregistered_password);
		homePage.clickLoginBtn();
		homePage.verifyUserLoggedInSuccesfully(true);
	}

	@Then("User already exits error is shown")
	public void user_already_exits_error_is_shown() {
		registrationPage.verifyRegistrationIsSuccess(false);
	}

	@Given("User enters username {string} and password {string}")
	public void user_enters_username_and_password(String username, String password) {
		homePage.enterUsername(username);
		homePage.enterPassword(password);
	}

	@When("User clicks on Login button")
	public void user_clicks_on_login_button() {
		homePage.clickLoginBtn();
	}

	@Then("User should be logged in successfully")
	public void user_should_be_logged_in_successfully() {
		homePage.verifyUserLoggedInSuccesfully(true);
	}

	@Then("User login should fail with error message")
	public void user_login_should_fail_with_error_message() {
		homePage.verifyUserLoggedInSuccesfully(false);
	}

	@Then("User logs out successfully")
	public void user_logs_out_successfully() {
		homePage.logout();
	}
}
