package com.cucumber.definition.common;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.cucumber.common.CommonSteps;
import com.cucumber.common.ConfigFileReader;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CommonDefinition {

	ConfigFileReader configFileReader;
	CommonSteps commStp;
	Logger log = Logger.getLogger("LOG");

	public CommonDefinition() {
		configFileReader = new ConfigFileReader();
		commStp = new CommonSteps();
	}

	//ok
	@Given("^I Click the \"([^\"]*)\" icon$")
	public void i_Click_the_icon(String arg1) throws Throwable {
		commStp.clickIcon(arg1);
	}

	//ok
	@Then("^I should see the \"([^\"]*)\" page$")
	public void i_should_see_the_page(String arg1) throws Throwable {
		commStp.seePage(arg1);
	}

	//ok
	@Given("^I am at \"([^\"]*)\" Home Page$")
	public void i_am_at_Home_Page(String arg1) throws Throwable {
		commStp.atHomePage(arg1);
	}

	//ok
	@Then("^I should see \"([^\"]*)\" Home Page$")
	public void i_should_see_Home_Page(String arg1) throws Throwable {
		commStp.seeHomePage(arg1);
	}

	//ok
	@Then("^I click on \"([^\"]*)\" icon to back$")
	public void i_click_on_icon_to_back(String arg1) throws Throwable {
		commStp.clickOnIconToBack(arg1);
	}

	//ok
	@And("^I Click on Button \"([^\"]*)\"$")
	public void i_Click_on_Button(String arg1) throws Throwable {
		commStp.clickOnButton(arg1);
	}

	//ok
	@And("^I click the link \"([^\"]*)\"$")
	public void i_click_the_link(String arg1) throws Throwable {
		commStp.clickTheLink(arg1);
	}

	//ok
	@Then("^I Check for Success Message \"([^\"]*)\" in \"([^\"]*)\"$")
	public void i_Check_for_Success_Message_in(String arg1, String arg2) throws Throwable {
		commStp.checkForSuccessMessage(arg1, arg2);
	}

	//ok
	@Then("^I Check for Failed Message \"([^\"]*)\" in \"([^\"]*)\"$")
	public void i_Check_for_Failed_Message_in(String arg1, String arg2) throws Throwable {
		commStp.checkForFailedMessage(arg1, arg2);
	}

	//ok
	@Then("^I should see the \"([^\"]*)\" Application Page$")
	public void i_should_see_the_Application_Page(String arg1) throws Throwable {
		commStp.seeTheApplicationPage(arg1);
	}

	//ok
	@Then("^I click on square icon  on corner$")
	public void i_click_on_square_icon_on_corner() throws Throwable {
		commStp.clickOnSquareIconOnCorner();
	}

	//ok
	@And("^I Click the \"([^\"]*)\" icon on Application Page$")
	public void i_Click_the_icon_on_Application_Page(String arg1) throws Throwable {
		commStp.clickTheIconOnApplicationPage(arg1);
	}

	@Given("^page scroll down$")
	public void page_scroll_down() throws Throwable {
		commStp.pageScrollDown();
	}

	//ok
	@Given("^I click on Home Page$")
	public void i_click_on_Home_Page() throws Throwable {
		commStp.clickOnHomePage();
	}

	@After
	public void screenShot(Scenario scenario) throws AWTException, IOException {
		
//		if (configFileReader.getDriverType()=="CHROME") {
		commStp.failedScenarioscreenShot(scenario);
//		}
//		else if(configFileReader.getDriverType()=="FIREFOX") {
//			
//			commStp.failedScenarioscreenShot(scenario);	
//		}
		
//		else if(configFileReader.getDriverType()=="GUI") {
//			
//			System.out.println("##############in Commondefinition1");
//			
//			commStp.failedScenarioscreenShot_GUI(scenario);
//			
//			System.out.println("##############in Commondefinition");
//		}
	}

	//ok
	@Given("^I Click the \"([^\"]*)\" icon to \"([^\"]*)\"$")
	public void i_Click_the_icon_to(String arg1, String arg2) throws Throwable {
		commStp.clickTheIconTo(arg1, arg2);
	}

	//ok
	@Given("^I Click \"([^\"]*)\" function id icon to \"([^\"]*)\"$")
	public void i_Click_function_id_icon_to(String arg1, String arg2) throws Throwable {
		commStp.clickFunctionIdIconTo(arg1, arg2);
	}

	//ok
	@Given("^I Click the \"([^\"]*)\" icon in \"([^\"]*)\"$")
	public void i_Click_the_icon_in(String arg1, String arg2) throws Throwable {
		commStp.clickTheIconIn(arg1, arg2);
	}

	//ok
	@Then("^I should see the \"([^\"]*)\" page to \"([^\"]*)\"$")
	public void i_should_see_the_page_to(String arg1, String arg2) throws Throwable {
		commStp.seeThePageTo(arg1, arg2);
	}

	//ok
	@Then("^I \"([^\"]*)\" data criteria of \"([^\"]*)\"$")
	public void i_data_criteria_of(String arg1, String arg2, DataTable table) throws Throwable {
		commStp.dataCriteriaOf(arg1, arg2, table);
	}

	@Then("^navigate to previous page$")
	public void navigate_to_previous_page() throws Throwable {
		commStp.navigateToPreviousPage();
	}

	//ok
	@Then("^I click on \"([^\"]*)\" button in \"([^\"]*)\"$")
	public void i_click_on_button_in(String arg1, String arg2, DataTable table) throws Throwable {
		commStp.clickOnButtonIn(arg1, arg2, table);
	}

	//ok
	@And("^I close the console$")
	public void i_close_the_console() throws Throwable {
		commStp.closeTheConsole();
	}

	//ok
	@Given("^I verify \"([^\"]*)\" title in \"([^\"]*)\"$")
	public void i_verify_title_in(String arg1, String arg2) throws Throwable {
		commStp.verifyTitleIn(arg1, arg2);
	}

	//ok
	@Given("^I upload file in \"([^\"]*)\"$")
	public void i_upload_file_in(String arg1, DataTable table) throws Throwable {
		commStp.uploadFileIn(arg1, table);
	}

	//ok
	@Then("^I call verify method$")
	public void i_call_verify_method(DataTable table) throws Throwable {
		commStp.callVerifyMethod(table);

	}

	//ok
	@Given("^I am login to the FTP server$")
	public void i_am_login_to_the_FTP_server() throws Throwable {

		commStp.CreateSFTPConnection(configFileReader.getFtpUserName(), configFileReader.getFtpIp(),
				configFileReader.getFtpPassword());
	}
	
	//ok
	@Then("^I should be connected to the ftp server successfully$")
	public void i_should_be_connected_to_the_ftp_server_successfully() throws Throwable {
		
		commStp.ConnectToTheSFTP();
	
	}
	
	//ok
	@Then("^I Upload the File to the Server by giving the path$")
	public void i_Upload_the_File_to_the_Server_by_giving_the_path() throws Throwable {
		
		commStp.UploadTheFileToSFTPlocation(configFileReader.getUploadFileName(), configFileReader.getToUploadFilePath());
	}
	
	//ok
	@Given("^I am login to the putty server$")
	public void i_am_login_to_the_putty_server() throws Throwable {
		
		commStp.puttyConnectSession(configFileReader.getFtpUserName(), configFileReader.getFtpIp(),
				configFileReader.getFtpPassword());
		
	}
	
	//ok
	@Then("^I should be connected to the putty server successfully$")
	public void i_should_be_connected_to_the_putty_server_successfully() throws Throwable {
	    
		commStp.puttyConnectChannel();
		
	}
	
	//ok
	@Then("^I Run the \"([^\"]*)\" jar file$")
	public void i_Run_the_jar_file(String jar) throws Throwable {
	    
		commStp.SFTPConnection_RUNthejarFile(jar);
	}


	@Before(value = "@FeatureFile")
	public void Feature_iCore_Home_Page() {
		log.debug("---------------------------------------------------------------------------");
		log.debug("Feature: FeatureFile");
	}

}
