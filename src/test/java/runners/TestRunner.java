package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", 
glue = { "stepdefinitions" }, monochrome = true, 
plugin = {"pretty", "html:target/HTMLReports/report.html",
		"json:target/JSONReports/report.json",
		"junit:target/JUnitReports/report.xml" },
tags = "@functional", stepNotifications = true)

public class TestRunner {

}
