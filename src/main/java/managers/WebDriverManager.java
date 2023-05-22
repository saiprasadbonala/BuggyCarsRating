package managers;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import dataproviders.PropertiesFileReader;
import enums.BroswerType;
import utils.Constants;

public class WebDriverManager {
	private WebDriver driver;
	private PropertiesFileReader propFileReader = PropertiesFileReader.getInstance();
	private static BroswerType browserType;
	private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
	private static final String FIREFOX_DRIVER_PROPERTY = "webdriver.gecko.driver";

	public WebDriverManager() {
		browserType = propFileReader.getBrowser();
	}

	public WebDriver getDriver() {
		if (driver == null)
			driver = createDriver();
		return driver;
	}

	private WebDriver createDriver() {
		switch (browserType) {
		case FIREFOX:
			System.setProperty(FIREFOX_DRIVER_PROPERTY, Constants.PROJECTFOLDER_PATH + propFileReader.getRelativeDriverPath());
			FirefoxOptions ffOptions = new FirefoxOptions();
			ffOptions.addPreference("fission.bfcacheInParent", false);
			ffOptions.addPreference("fission.webContentIsolationStrategy", 0);
			driver = new FirefoxDriver(ffOptions);
			break;
		case CHROME:
//			System.setProperty(CHROME_DRIVER_PROPERTY, Constants.PROJECTFOLDER_PATH + propFileReader.getRelativeDriverPath());
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
			break;
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(propFileReader.getImplicitlyWait()));
		return driver;
	}

	public void navigateTo(String url) {
		driver.navigate().to(url);
	}

	public void closeDriver() {
		driver.close();
	}

	public void quitDriver() {
		driver.quit();
	}
}
