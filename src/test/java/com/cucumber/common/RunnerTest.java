package com.cucumber.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.PropertyConfigurator;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;

import com.github.mkolisnyk.cucumber.runner.AfterSuite;
import com.github.mkolisnyk.cucumber.runner.BeforeSuite;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;

import cucumber.api.CucumberOptions;

@RunWith(ExtraExtendedCucumber.class)

@ExtendedCucumberOptions(jsonReport = "reports/cucumber-json/cucumber.json", 
                         jsonUsageReport = "reports/cucumber-json/usage.json", 
                         usageReport = true, coverageReport = true, 
                         detailedReport = true, 
                         overviewReport = true, 
                         toPDF = true, 
                         outputFolder = "reports/", 
                         screenShotLocation = "screenshots/",
                         screenShotSize = "700px", retryCount = 0)

@CucumberOptions(features = { "feature file/" }, glue = { "com.cucumber.definition.admin", "com.cucumber.common",
		"com.cucumber.definition.common" }, tags = {"~@dontRun"}, dryRun = false, plugin = { "html:reports/cucumber-html",
				"json:reports/cucumber-json/cucumber.json", "usage:reports/cucumber-json/usage.json" })

public class RunnerTest {

	public static void main(String ar[]) {

		JUnitCore.main(RunnerTest.class.getName());
	}

	@BeforeSuite
	public static void start() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println("-----SERVICE START UP TIME :-----------" + dateFormat.format(new Date()));
		PropertyConfigurator.configure("config/log4.properties");
	}
	
	 @AfterSuite
	    public static void complete() throws Throwable {
	    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    	System.out.println("-----SERVICE END TIME :-----------"+dateFormat.format(new Date()));
	    }	    

}