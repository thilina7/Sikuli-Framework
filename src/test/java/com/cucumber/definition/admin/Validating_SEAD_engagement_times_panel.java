package com.cucumber.definition.admin;

import static org.junit.Assert.assertTrue;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.temporal.ValueRange;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.sikuli.script.Screen;

import com.cucumber.common.CommonSteps;
import com.cucumber.common.ConfigFileReader;
import com.cucumber.common.SetElements;
import com.cucumber.common.WebDriverInitializer;
import com.gargoylesoftware.htmlunit.javascript.host.dom.Range;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;

public class Validating_SEAD_engagement_times_panel {

	private WebDriverInitializer webDriverInitializer;
	Screen screenDriver = null;
	Logger log = Logger.getLogger("LOG");
	ConfigFileReader ConfigFileReader;
	String cwd = System.getProperty("user.dir");
	SetElements setElements;
	CommonSteps CommonSteps;

	public Validating_SEAD_engagement_times_panel(WebDriverInitializer webDriverInitializer) {

		this.webDriverInitializer = webDriverInitializer;
		screenDriver = webDriverInitializer.getDriver();
		ConfigFileReader = new ConfigFileReader();
		setElements = new SetElements();
		CommonSteps = new CommonSteps();
		// setElements.getTitle();

	}

	@Given("^User should click on the \"([^\"]*)\" to end the mission$")
	public void user_should_click_on_the_to_end_the_mission(String arg1, DataTable table) throws Throwable {

		try {
			Thread.sleep(2000);
			CommonSteps.VBSFillData(table,arg1);
		}

		catch (Exception e) {

			assertTrue("--------------Failed to click ", false);
		}

	}
	
	@Given("^User should click on the \"([^\"]*)\" valid and execute buttons$")
	public void user_should_click_on_the_valid_and_execute_buttons(String arg1, DataTable table) throws Throwable {
		
		
		try {
			CommonSteps.VBSFillData(table,arg1);
		}

		catch (Exception e) {

			assertTrue("--------------Failed to click ", false);
		}
		
		
		new File("Errors").mkdir();
		File outputFile = new File("test1.jpg");
	    Toolkit toolkit = Toolkit.getDefaultToolkit();
	    Dimension screenSize = toolkit.getScreenSize();
	    Rectangle screenRect = new Rectangle(screenSize);
	    Robot robot = new Robot();
	    BufferedImage image = robot.createScreenCapture(screenRect);
	   ImageIO.write(image, "png", outputFile);
	}
	
	
	
	
	
	
	
	
	
	
}


