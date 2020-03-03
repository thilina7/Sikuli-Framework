package com.cucumber.definition.admin;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.cucumber.common.CommonSteps;
import com.cucumber.common.ConfigFileReader;
import com.cucumber.common.SetElements;
import com.cucumber.common.WebDriverInitializer;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class Fires_and_Effects_Using_AMC {

	private WebDriverInitializer webDriverInitializer;
	Screen screenDriver = null;
	Logger log = Logger.getLogger("LOG");
	ConfigFileReader ConfigFileReader;
	String cwd = System.getProperty("user.dir");
	SetElements setElements;
	CommonSteps CommonSteps;

	public Fires_and_Effects_Using_AMC(WebDriverInitializer webDriverInitializer) {

		this.webDriverInitializer = webDriverInitializer;
		screenDriver = webDriverInitializer.getDriver();
		ConfigFileReader = new ConfigFileReader();
		setElements = new SetElements();
		CommonSteps = new CommonSteps();
		// setElements.getTitle();

	}

	@Given("^user should be in the Instructor control panel to Automate the \"([^\"]*)\" Testcase$")
	public void user_should_be_in_the_Instructor_control_panel_to_Automate_the_Testcase(String TestFolderName,
			DataTable table) throws Throwable {

		CommonSteps.FolderName(TestFolderName);

		try {
			CommonSteps.VBSFillData(table,"");
		}

		catch (Exception e) {

			assertTrue("--------------Failed to click ", false);
		}
	}

	@Given("^User should click on the clear button to \"([^\"]*)\" clear all the fields$")
	public void user_should_click_on_the_clear_button_to_clear_all_the_fields(String arg1, DataTable table)
			throws Throwable {

		try {
			CommonSteps.VBSFillData(table,arg1);
		}

		catch (Exception e) {

			assertTrue("--------------Failed to click ", false);
		}

	}

	@Given("^User should be fill the data on the \"([^\"]*)\" on the panel$")
	public void user_should_be_fill_the_data_on_the_on_the_panel(String arg1, DataTable table) throws Throwable {
		try {
			CommonSteps.VBSFillData(table,arg1);
		}

		catch (Exception e) {

			assertTrue("--------------Issues on " + arg1 + " Sections", false);
		}
	}

	@Given("^User should Select \"([^\"]*)\" from the target location grid types$")
	public void user_should_Select_from_the_target_location_grid_types(String arg1, DataTable table) throws Throwable {
		try {
			CommonSteps.VBSFillData(table,arg1);
		}

		catch (Exception e) {

			assertTrue("--------------Issues on " + arg1 + " Sections", false);
		}
	}

	@Given("^User should enter the \"([^\"]*)\" on the target location section$")
	public void user_should_enter_the_on_the_target_location_section(String arg1, DataTable table) throws Throwable {
		try {
			CommonSteps.VBSFillData(table,arg1);
		}

		catch (Exception e) {

			assertTrue("--------------Issues on " + arg1 + " Sections", false);
		}
	}

	@Given("^User should fill the Target description \"([^\"]*)\"$")
	public void user_should_fill_the_Target_description(String arg1, DataTable table) throws Throwable {
		try {
			CommonSteps.VBSFillData(table,arg1);
		}

		catch (Exception e) {

			assertTrue("--------------Issues on " + arg1 + " Sections", false);
		}
	}

	@Given("^User should Ammo/Fuse combination section \"([^\"]*)\"$")
	public void user_should_Ammo_Fuse_combination_section(String arg1, DataTable table) throws Throwable {
		try {
			CommonSteps.VBSFillData(table,arg1);
		}

		catch (Exception e) {

			assertTrue("--------------Issues on " + arg1 + " Sections", false);
		}
	}

	@Given("^User select the \"([^\"]*)\" Distribution types$")
	public void user_select_the_Distribution_types(String arg1, DataTable table) throws Throwable {
		try {
			CommonSteps.VBSFillData(table,arg1);
		}

		catch (Exception e) {

			assertTrue("--------------Issues on " + arg1 + " Sections", false);
		}
	}

	@Given("^User select a \"([^\"]*)\" Method of Fire and control$")
	public void user_select_a_Method_of_Fire_and_control(String arg1, DataTable table) throws Throwable {
		try {
			CommonSteps.VBSFillData(table,arg1);
		}

		catch (Exception e) {

			assertTrue("--------------Issues on " + arg1 + " Sections", false);
		}
	}

	@Given("^User click on validate Execute button \"([^\"]*)\"$")
	public void user_click_on_validate_Execute_button(String arg1, DataTable table) throws Throwable {
		try {
			CommonSteps.VBSFillData(table,arg1);
		}

		catch (Exception e) {

			assertTrue("--------------Issues on " + arg1 + " Sections", false);
		}
	}

	@Given("^User Click on Fire button \"([^\"]*)\" to fire the mission$")
	public void user_Click_on_Fire_button_to_fire_the_mission(String arg1, DataTable table) throws Throwable {
		try {
			CommonSteps.VBSFillData(table,arg1);
		}

		catch (Exception e) {

			assertTrue("--------------Issues on " + arg1 + " Sections", false);
		}
	}

	@Given("^User open Gun panel$")
	public void user_open_Gun_panel(DataTable arg1) throws Throwable {

	}

	@Then("^Analyse the CFF Projectile panel$")
	public void analyse_the_CFF_Projectile_panel(DataTable arg1) throws Throwable {

	}

	// #####################################33

	@Given("^I want to write a step with precondition$")
	public void i_want_to_write_a_step_with_precondition() throws Throwable {

		System.out.println("testtttttttttttttttttttttttttttttttttttt1");

	}

}
