package managers;

import org.openqa.selenium.WebDriver;

import pageobjects.CarMakePage;
import pageobjects.CarModelPage;
import pageobjects.HomePage;
import pageobjects.OverallRatingPage;
import pageobjects.ProfilePage;
import pageobjects.RegistrationPage;

public class PageObjectManager {
	private WebDriver driver;
	private RegistrationPage registrationPage;
	private HomePage homePage;
	private CarMakePage carMakePage;
	private CarModelPage carModelPage;
	private OverallRatingPage overallRatingPage;
	private ProfilePage profilePage;

	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}

	public HomePage getHomePage() {
		return (homePage == null) ? homePage = new HomePage(driver) : homePage;
	}

	public RegistrationPage getRegistrationPage() {
		return (registrationPage == null) ? registrationPage = new RegistrationPage(driver) : registrationPage;
	}

	public CarMakePage getCarMakePage() {
		return (carMakePage == null) ? carMakePage = new CarMakePage(driver) : carMakePage;
	}

	public CarModelPage getCarModelPage() {
		return (carModelPage == null) ? carModelPage = new CarModelPage(driver) : carModelPage;
	}

	public ProfilePage getProfilePage() {
		return (profilePage == null) ? profilePage = new ProfilePage(driver) : profilePage;
	}

	public OverallRatingPage getOverallRatingPage() {
		return (overallRatingPage == null) ? overallRatingPage = new OverallRatingPage(driver) : overallRatingPage;
	}
}
