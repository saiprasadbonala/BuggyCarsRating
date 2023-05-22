package stepdefinitions;

import contexts.TestContext;
import dataproviders.PropertiesFileReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.WaitUtil;

public class Hooks {

	TestContext testContext;

	public Hooks(TestContext context) {
		testContext = context;
	}

	@Before
	public void openApplication() {
		testContext.getWebDriverManager().navigateTo(PropertiesFileReader.getInstance().getApplicationUrl());
		WaitUtil.untilPageIsLoaded(testContext.getWebDriverManager().getDriver());
	}

	@After
	public void tearDown() {
		WaitUtil.untilPageIsLoaded(testContext.getWebDriverManager().getDriver());
		testContext.getWebDriverManager().quitDriver();
	}
}
