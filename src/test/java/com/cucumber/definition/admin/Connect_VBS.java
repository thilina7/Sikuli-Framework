package com.cucumber.definition.admin;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.sikuli.script.Finder;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.cucumber.common.ConfigFileReader;
import com.cucumber.common.SetElements;
import com.cucumber.common.WebDriverInitializer;
import com.ibm.db2.jcc.am.lo;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class Connect_VBS {

	private WebDriverInitializer webDriverInitializer;
	Screen screenDriver = null;
	Logger log = Logger.getLogger("LOG");
	ConfigFileReader ConfigFileReader;
	String cwd = System.getProperty("user.dir");
	SetElements setElements;

	public Connect_VBS(WebDriverInitializer webDriverInitializer) {

		this.webDriverInitializer = webDriverInitializer;
		screenDriver = webDriverInitializer.getDriver();
		ConfigFileReader = new ConfigFileReader();
		setElements = new SetElements();
		// setElements.getTitle();

	}
	// Screen s;

	@Given("^User search on the FireFST icon on the Desktop$")
	public void user_search_on_the_FireFST_icon_on_the_Desktop() throws Throwable {

		System.out.println("------------------Search for the FST icon");

		log.debug("------------------Search for the FST icon");
	}

	@Given("^User double click on the FireFST icon on the desktop$")
	public void user_double_click_on_the_FireFST_icon_on_the_desktop() throws Throwable {

		try {
			
			Pattern p10 = new Pattern("D:\\SeleniumTesting\\SikuliFST\\ScreenShots\\iconClick.png");

			// s.wait(p.similar((float) 0.90),2 ).click();

			p10.targetOffset(0, 0);

			screenDriver.doubleClick(p10);

			System.out.println("------------------Clicked to minimize the FST Application");

			log.debug("------------------Clicked to minimize the FST Application");

			Thread.sleep(3000);

		} catch (Exception e) {

			System.out.println("------------------Fail to click FST icon on the desktop" + e);

			log.debug("------------------Fail to click FST icon on the desktop");

			assertTrue("------------------Fail to click FST icon on the desktop", false);

		} finally {

			Thread.sleep(10000);
		}

	}

	@Then("^User should see the FireFST application open successfully$")
	public void user_should_see_the_FireFST_application_open_successfully() throws Throwable {

		try {

			boolean test = screenDriver
					.find("D:\\SeleniumTesting\\SikuliFST\\ScreenShots\\Validations\\OpenValidation.png") != null;

			System.out.println("------------------Checking if the application open sucessfully--->" + test);

			log.debug("------------------Checking if the application open sucessfully--->" + test);

			if (screenDriver
					.find("D:\\SeleniumTesting\\SikuliFST\\ScreenShots\\Validations\\OpenValidation.png") != null) {

				System.out.println("---------------Application open successfully");

				log.debug("---------------Application open successfully");

			}

			else {

				System.out.println("--------------Fail to open the application");

				log.debug("--------------Fail to open the application");

			}

			System.out.println("------------------Checking if the application open sucessfully");

			log.debug("------------------Checking if the application open sucessfully");

			Thread.sleep(3000);

		} catch (Exception e) {

			System.out.println(e);

			assertTrue("--------------Fail to open the application", false);

			System.out.println("--------------Fail to open the application");

			log.debug("--------------Fail to open the application");
		}

	}

	@Then("^User go back to the desktop to open the VBS Application$")
	public void user_go_back_to_the_desktop_to_open_the_VBS_Application() throws Throwable {
		Pattern p1 = null;
		try {
			p1 = new Pattern("D:\\SeleniumTesting\\SikuliFST\\ScreenShots\\MinimizeFST.png");

			// s.wait(p.similar((float) 0.90),2 ).click();

			p1.targetOffset(-50, 0);

			screenDriver.doubleClick(p1);

			System.out.println("------------------Clicked to minimize the FST Application");

			log.debug("------------------Clicked to minimize the FST Application");

			Thread.sleep(3000);

		} catch (Exception e) {
			screenDriver.click(p1);

			System.out.println("------------------Fail to minimize the FST Application" + e);

			log.debug("------------------Fail to minimize the FST Application");

			assertTrue("--------------Fail to minimize the FST Application", false);

		}

	}

	@Given("^User search on the VBS icon on the Desktop$")
	public void user_search_on_the_VBS_icon_on_the_Desktop() throws Throwable {

		try {

			Pattern v1 = new Pattern("D:\\SeleniumTesting\\SikuliFST\\ScreenShots\\VBS\\Start.png");

			v1.targetOffset(0, 0);

			screenDriver.wait(v1.similar((float) 0.95), 2).doubleClick();

			System.out.println("------------------Clicked on the desktop FST icon sucessfully");

			log.debug("------------------Clicked on the FST desktop icon sucessfully");

			Thread.sleep(60000);

		}

		catch (Exception e) {

			System.out.println("------------------Fail to Click on the FST desktop icon" + e);

			log.debug("------------------Fail to Click on the FST desktop icon");

			assertTrue("--------------Fail to Click on the FST desktop icon", false);

		}

	}

	@Given("^User double click on the VBS icon on the desktop$")
	public void user_double_click_on_the_VBS_icon_on_the_desktop() throws Throwable {

	}

	@Then("^User should see the VBS application open successfully$")
	public void user_should_see_the_VBS_application_open_successfully() throws Throwable {

		try {

			boolean test = screenDriver
					.find("D:\\SeleniumTesting\\SikuliFST\\ScreenShots\\Validations\\VBSicon.png") != null;

			System.out.println("------------------Checking if the application open sucessfully--->" + test);

			log.debug("------------------Checking if the application open sucessfully--->" + test);

			if (screenDriver.find("D:\\SeleniumTesting\\SikuliFST\\ScreenShots\\Validations\\VBSicon.png") != null) {

				System.out.println("---------------Application open successfully");

				log.debug("---------------Application open successfully");

			}

			else {

				System.out.println("--------------Fail to open the application");

				log.debug("--------------Fail to open the application");

			}

			System.out.println("------------------Checking if the application open sucessfully");

			log.debug("------------------Checking if the application open sucessfully");

			Thread.sleep(3000);

		} catch (Exception e) {

			System.out.println(e);

			assertTrue("--------------Fail to open the application", false);

			System.out.println("--------------Fail to open the application");

			log.debug("--------------Fail to open the application");
		}

	}

	@Then("^User click on the \"([^\"]*)\" button to go to the \"([^\"]*)\"$")
	public void user_click_on_the_button_to_go_to_the(String arg1, String arg2) throws Throwable {

		try {

			Pattern v2 = new Pattern("D:\\SeleniumTesting\\SikuliFST\\ScreenShots\\VBS\\Login.png");

			// s.wait(p.similar((float) 0.90),2 ).click();

			v2.targetOffset(0, 0);

			screenDriver.wait(v2.similar((float) 0.95), 5).doubleClick();

			System.out.println("------------------Clicked on the Login button successfully");

			log.debug("------------------Clicked on the Login button successfully");

			// s.click(p3);

			Thread.sleep(3000);

		}

		catch (Exception e) {

			System.out.println(e);

			assertTrue("--------------Fail to open the application", false);

			System.out.println("--------------Fail to open the application");

			log.debug("--------------Fail to open the application");

		}
	}

	@Then("^User should see the Main Menu items$")
	public void user_should_see_the_Main_Menu_items() throws Throwable {

	}

	@Then("^User click on the \"([^\"]*)\" button to go to view all the networks$")
	public void user_click_on_the_button_to_go_to_view_all_the_networks(String arg1) throws Throwable {

		try {
			Pattern v3 = new Pattern("D:\\SeleniumTesting\\SikuliFST\\ScreenShots\\VBS\\Networking.png");

			// s.wait(p.similar((float) 0.90),2 ).click();

			v3.targetOffset(0, 0);

			screenDriver.wait(v3.similar((float) 0.95), 2).doubleClick();

			// s.click(p3);

			System.out.println("--------------User is on the networks panel");

			log.debug("--------------User is on the networks panel");

			Thread.sleep(3000);

		}

		catch (Exception e) {

			System.out.println(e);

			assertTrue("--------------Fail to open the application", false);

			System.out.println("--------------Fail to open the application");

			log.debug("--------------Fail to open the application");

		}
	}

	@Then("^User should see all the networks in this menu$")
	public void user_should_see_all_the_networks_in_this_menu() throws Throwable {

		try {
			Pattern v4 = new Pattern("D:\\SeleniumTesting\\SikuliFST\\ScreenShots\\VBS\\HostSession.png");

			// s.wait(p.similar((float) 0.90),2 ).click();

			v4.targetOffset(0, 0);

			screenDriver.wait(v4.similar((float) 0.95), 2).click();

			// s.click(p3);
			System.out.println("--------------User is on the HostSession panel");

			log.debug("--------------User is on the HostSession panel");

			Thread.sleep(3000);

		}

		catch (Exception e) {

			System.out.println(e);

			assertTrue("--------------Fail to open the application", false);

			System.out.println("--------------Fail to open the application");

			log.debug("--------------Fail to open the application");

		}

	}

	@Then("^User should click on the \"([^\"]*)\"$")
	public void user_should_click_on_the(String arg1) throws Throwable {

	}

	@Then("^User should click \"([^\"]*)\" button to go to \"([^\"]*)\"$")
	public void user_should_click_button_to_go_to(String arg1, String arg2) throws Throwable {

		try {
			Pattern v8 = new Pattern("D:\\SeleniumTesting\\SikuliFST\\ScreenShots\\VBS\\New.png");

			// s.wait(p.similar((float) 0.90),2 ).click();

			v8.targetOffset(0, 0);

			Thread.sleep(1000);

			screenDriver.wait(v8.similar((float) 0.95), 2).doubleClick();

			// s.click(p3);
			System.out.println("--------------User clicked to go to next panel");

			log.debug("--------------User clicked to go to next panel");

			Thread.sleep(3000);

		}

		catch (Exception e) {

			System.out.println(e);

			assertTrue("--------------Fail to open the application", false);

			System.out.println("--------------Fail to open the application");

			log.debug("--------------Fail to open the application");

		}

	}

	@Then("^User should see the \"([^\"]*)\" screen$")
	public void user_should_see_the_screen(String arg1) throws Throwable {

	}

	@Then("^User select the mission which already created \"([^\"]*)\"$")
	public void user_select_the_mission_which_already_created(String arg1) throws Throwable {

		try {
			Pattern v5 = new Pattern("D:\\SeleniumTesting\\SikuliFST\\ScreenShots\\VBS\\HSDSonly_Mission.png");

			// s.wait(p.similar((float) 0.90),2 ).click();

			v5.targetOffset(0, 0);

			screenDriver.wait(v5.similar((float) 0.95), 2).click();

			// s.click(p3);
			System.out.println("--------------User clicked on the mission");

			log.debug("--------------User clicked on the mission");

			Thread.sleep(3000);

		}

		catch (Exception e) {

			System.out.println(e);

			assertTrue("--------------Fail to open the application", false);

			System.out.println("--------------Fail to open the application");

			log.debug("--------------Fail to open the application");

		}
	}

	@Then("^User should click \"([^\"]*)\" button to host the Mission$")
	public void user_should_click_button_to_host_the_Mission(String arg1) throws Throwable {

		try {
			Pattern v6 = new Pattern("D:\\SeleniumTesting\\SikuliFST\\ScreenShots\\VBS\\Start_Mission.png");

			// s.wait(p.similar((float) 0.90),2 ).click();

			v6.targetOffset(0, 0);

			screenDriver.wait(v6.similar((float) 0.95), 2).doubleClick();

			// s.click(p3);
			System.out.println("--------------User clicked on the start mission");

			log.debug("--------------User clicked on the start mission");

			Thread.sleep(3000);

		}

		catch (Exception e) {

			System.out.println(e);

			assertTrue("--------------Fail to host_the_Mission", false);

			System.out.println("--------------Fail to host_the_Mission");

			log.debug("--------------Fail to host_the_Mission");

		}
	}

	@Then("^User should see the Player Selection screen$")
	public void user_should_see_the_Player_Selection_screen() throws Throwable {

		try {

			Pattern v7 = new Pattern("D:\\SeleniumTesting\\SikuliFST\\ScreenShots\\VBS\\SimFOI.png");

			// s.wait(p.similar((float) 0.90),2 ).click();

			v7.targetOffset(0, 0);

			screenDriver.wait(v7.similar((float) 0.80), 2).doubleClick();

			// s.click(p3);
			System.out.println("--------------User is on player selection panel");

			log.debug("--------------User is on player selection panel");

			Thread.sleep(3000);
		} catch (Exception e) {

			System.out.println(e);

			assertTrue("--------------Fail to open the application", false);

			System.out.println("--------------Fail to open the application");

			log.debug("--------------Fail to open the application");

		}

	}

	@Then("^User click on the ok button select the Player$")
	public void user_click_on_the_ok_button_select_the_Player() throws Throwable {

		try {
			Pattern v10 = new Pattern("D:\\SeleniumTesting\\SikuliFST\\ScreenShots\\VBS\\ok.png");

			// s.wait(p.similar((float) 0.90),2 ).click();

			v10.targetOffset(45, 0);

			screenDriver.wait(v10.similar((float) 0.75), 2).doubleClick();

			System.out.println("--------------User clicked on the ok button");

			log.debug("--------------User clicked on the ok button");

			// s.click(p3);

			Thread.sleep(12000);

			v10.targetOffset(-50, 0);
		} catch (Exception e) {

			System.out.println(e);

			assertTrue("--------------Fail to open the application", false);

			System.out.println("--------------Fail to open the application");

			log.debug("--------------Fail to open the application");

		}
	}

	@Then("^User should see the Start Mission screen$")
	public void user_should_see_the_Start_Mission_screen() throws Throwable {

	}

	@Then("^User click on the Mission start ok button$")
	public void user_click_on_the_Mission_start_ok_button() throws Throwable {

		try {
			Pattern v11 = new Pattern("D:\\SeleniumTesting\\SikuliFST\\ScreenShots\\VBS\\Mission_ok.png");

			// s.wait(p.similar((float) 0.90),2 ).click();

			v11.targetOffset(0, 0);

			screenDriver.wait(v11.similar((float) 0.75), 2).doubleClick();

			// s.click(p3);

			Thread.sleep(3000);

			System.out.println("--------------User clicked on the mission ok button");

			log.debug("--------------User clicked on the mission ok button");

			Thread.sleep(7000);

			screenDriver.type(Key.ESC, KeyModifier.CTRL | KeyModifier.ALT);

			// ######## Pattern v23 = new
			// Pattern("D:\\SeleniumTesting\\SikuliFST\\ScreenShots\\VBS\\Validation_ok.png");

			// s.wait(p.similar((float) 0.90),2 ).click();

			// ######## v23.targetOffset(0, 0);

			// ######## screenDriver.wait(v11.similar((float) 0.75), 2).doubleClick();

			// s.click(p3);

			Thread.sleep(3000);

			System.out.println("--------------User clicked on the Validation_ok button");

			log.debug("--------------User clicked on the Validation_ok button");

		} catch (Exception e) {

			Pattern v11 = new Pattern("D:\\SeleniumTesting\\SikuliFST\\ScreenShots\\VBS\\Mission_ok1.png");

			// s.wait(p.similar((float) 0.90),2 ).click();

			v11.targetOffset(0, 0);

			screenDriver.wait(v11.similar((float) 0.75), 2).doubleClick();

			System.out.println("--------------User clicked on the mission ok yellow button");

			log.debug("--------------User clicked on the mission ok yellow button");

			// s.click(p3);

			Thread.sleep(3000);

			Pattern v23 = new Pattern("D:\\SeleniumTesting\\SikuliFST\\ScreenShots\\VBS\\Validation_ok.png");

			// s.wait(p.similar((float) 0.90),2 ).click();

			v23.targetOffset(0, 0);

			screenDriver.wait(v23.similar((float) 0.75), 2).doubleClick();

			// s.click(p3);

			Thread.sleep(3000);

			System.out.println("------------------Validaiton message not showing");

		}

	}

	@Given("^User select the FST mission which already created \"([^\"]*)\"$")
	public void user_select_the_FST_mission_which_already_created(String arg1, DataTable table) throws Throwable {

		List<List<String>> Tableraw = table.raw();

		System.out.println("Current working directory : " + cwd);

		String path = cwd + ConfigFileReader.getVbsScreeshotPath() + Tableraw.get(1).get(1) + ".png";
		Pattern v11 = new Pattern(path);

		// Pattern v11 = new
		// Pattern("D:\\SeleniumTesting\\SikuliFST\\ScreenShots\\VBS\\FSTonly_Mission.png");

		// s.wait(p.similar((float) 0.90),2 ).click();

		v11.targetOffset(0, 0);

		screenDriver.wait(v11.similar((float) 0.95), 2).doubleClick();

		System.out.println("--------------User clicked on the mission ok yellow button");

		log.debug("--------------User clicked on the mission ok yellow button");

		// s.click(p3);

		Thread.sleep(3000);

	}

	@Then("^User should see the FST Player Selection screen$")
	public void user_should_see_the_FST_Player_Selection_screen(DataTable table) throws Throwable {

		try {
			System.out.println("--------------User clicked on FST_Player_Selection1");

			setElements.VBSElementDouble_Click(table);

			System.out.println("--------------User clicked on FST_Player_Selection2");
			/*
			 * List<List<String>> Tableraw = table.raw(); String path
			 * =cwd+ConfigFileReader.getVbsScreeshotPath()+Tableraw.get(1).get(1)+".png";
			 * 
			 * Pattern v11 = new Pattern(path);
			 * 
			 * v11.targetOffset(0, 0);
			 * 
			 * screenDriver.wait(v11.similar((float) 0.95), 2).doubleClick();
			 * 
			 * System.out.println("--------------User clicked on FST_Player_Selection");
			 * 
			 * log.debug("--------------User clicked on FST_Player_Selection");
			 * 
			 * 
			 */
		}

		catch (Exception e) {

			System.out.println(e);

			assertTrue("--------------Fail clicked on FST_Player_Selection", false);

			System.out.println("--------------Fail clicked on FST_Player_Selection");

			log.debug("--------------Fail clicked on FST_Player_Selection");

		}

	}

	@Given("^User click on the FST icon from the Taskbar$")
	public void user_click_on_the_FST_icon_from_the_Taskbar(DataTable table) throws Throwable {

		setElements.VBSElementDouble_Click(table);

	}

}
