package stepdefinitions;

import contexts.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.HomePage;
import pageobjects.ProfilePage;
import pageobjects.RegistrationPage;

public class ProfileUpdateSteps {

	TestContext testContext;
	HomePage homePage;
	RegistrationPage registrationPage;
	ProfilePage profilePage;

	public ProfileUpdateSteps(TestContext context) {
		testContext = context;
		homePage = testContext.getPageObjectManager().getHomePage();
		registrationPage = testContext.getPageObjectManager().getRegistrationPage();
		profilePage = testContext.getPageObjectManager().getProfilePage();
	}

	@Given("Profile option is displayed on the Home page")
	public void profile_option_is_displayed_on_the_home_page() {
		homePage.verifyProfileLinkIsDisplayed();
	}

	@When("User clicks on Profile button")
	public void user_clicks_on_profile_button() {
		homePage.clickOnProfile();
	}

	@Then("User should be able to update profile")
	public void user_should_be_able_to_update_profile() {
		profilePage.updateProfile();
	}
}
