package com.cucumber.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.sikuli.script.Env;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.ibm.db2.jcc.am.sc;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import cucumber.api.DataTable;
import cucumber.api.Scenario;

import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CommonSteps {

	ConfigFileReader configFileReader;
	SetElements setElement;
	static Session session = null;
	static java.util.Properties config = null;
	public static WebDriver webDriver = null;

	public static int iImageCounter = 1;
	static int resetiImageCounter = 1;
	Logger log = Logger.getLogger("LOG");
	static boolean failScenarioMessageAllow = false;
	static String failScenarioMessageText = "";

	static Session ptySession = null;
	static java.util.Properties ptyconfig = null;
	Screen screenDriver = null;
	static String foldername;
	static String cwd;
	static String path;

	public CommonSteps() {
		configFileReader = new ConfigFileReader();
		setElement = new SetElements();
		screenDriver = WebDriverInitializer.getDriver();
		// setElement.getTitle();
	}

	public void getScreenShots(WebDriver webDriver, String sScreenShotName, String sFilePath, String sSubFolder) {

		if (configFileReader.getScreenShot()) {
			TakesScreenshot screen = null;
			screen = (TakesScreenshot) webDriver;
			File Source = screen.getScreenshotAs(OutputType.FILE);
			if (configFileReader.getSubFolderCreation()) {
				try {
					DateFormat FolderDateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String sTime = FolderDateFormat.format(new Date());
					File timeDir = new File(sFilePath + sTime);
					if (!timeDir.exists()) {
						timeDir.mkdir();
					}
					sFilePath = sFilePath + sTime + configFileReader.getFolderSeperator();
					File tempFileSub = new File(sFilePath + sSubFolder);
					if (!tempFileSub.exists()) {

						tempFileSub.mkdir();
					}

					sFilePath = sFilePath + sSubFolder;
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}

			}

			try {
				FileUtils.copyFile(Source,
						new File(sFilePath + sScreenShotName + configFileReader.getImageExtention()));

			} catch (IOException e1) {
				log.error(e1.getMessage(), e1);
			}
		}

	}

	// calculate dates and click

	public void modifyText(String updateID, String updateText, File path) {
		ArrayList<String> writeToFile = new ArrayList<String>();

		try (BufferedReader BR = new BufferedReader(new FileReader(path))) {
			String readLine = "";

			while ((readLine = BR.readLine()) != null) {

				// Processing the Strings with "$updateID"
				if (readLine.contains(updateID)) {
					int begin = readLine.indexOf("'");
					int end = readLine.lastIndexOf("'");

					String change = readLine.substring(begin + 1, end);
					readLine = readLine.replace(change, updateText);
				}
				writeToFile.add(readLine);
			}
		} catch (IOException ex) {
			log.error(ex.getMessage(), ex);
		}

		try (FileWriter fw = new FileWriter(path); BufferedWriter bw = new BufferedWriter(fw)) {

			for (String s : writeToFile) {
				fw.write(s + "\n");
			}
			fw.flush();
			bw.flush();
			// Clear the arraylist which contains the current file data to store
			// new file data.
			writeToFile.clear();
			log.debug("Successfully verified modify MerchantId");
		} catch (IOException e2) {
			log.error(e2.getMessage(), e2);
		}

	}

	public String[] setScreenShotPath(String function) {
		String[] screenShot = new String[1];
		switch (function) {
		case "Admin":
			screenShot[0] = "Login\\";
			break;
		case "Admin Login":
			screenShot[0] = "Login\\";
			break;

		}
		return screenShot;
	}

	// SFTP Connection

	public void CreateSFTPConnection(String uname, String host, String password) {
		JSch jsch = null;

		try {
			config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			jsch = new JSch();
			session = jsch.getSession(uname, host, 22);
			session.setPassword(password);
		} catch (Exception e) {
			System.out.println(e);

		}
	}

	// Connect to the SFTP Connection

	public void ConnectToTheSFTP() {

		try {
			session.setConfig(config);
			session.connect();
			System.out.println("Connected");
		} catch (Exception e) {
			assertTrue("failed to connect to FTP Server", false);

		}

	}

	public void UploadTheFileToSFTPlocation(String fileName, String IN_FILE_PATH) throws Exception {

		try {
			Channel channel = session.openChannel("sftp");
			channel.connect();

			ChannelSftp sftpChannel = (ChannelSftp) channel;

			sftpChannel.put("Upload files\\" + fileName, IN_FILE_PATH);

			InputStream in = sftpChannel.get(IN_FILE_PATH + fileName);
			// process inputstream as needed

			sftpChannel.exit();
			session.disconnect();

			OutputStream out = channel.getOutputStream();
			InputStream inn = channel.getInputStream();

			String output = "";
			while (sftpChannel.isClosed() != true) {

				try {
					output += streamToString1(inn);

				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}

			}

			sftpChannel.disconnect();
			session.disconnect();

			do {
				Thread.sleep(1000);
			} while (!channel.isEOF());

			session.disconnect();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw (e);

		}
	}

	public String streamToString1(InputStream input) throws Exception {
		String output = "";

		while (input.available() > 0) {

			output += ((char) (input.read()));

		}

		return output;
	}

	// ===================================putty session connect

	public void puttyConnectSession(String host, String userName, String password) throws JSchException {
		JSch jsch1 = null;
		try {
			log.debug("Crate putty connection");
			ptyconfig = new java.util.Properties();
			ptyconfig.put("StrictHostKeyChecking", "no");
			jsch1 = new JSch();
			ptySession = jsch1.getSession(userName, host, 22);
			ptySession.setPassword(password);
			log.debug("Successfully crated putty connection");

		} catch (JSchException e) {
			log.error(e.getMessage(), e);
			throw (e);
		}
	}

	public void puttyConnectChannel() throws JSchException {
		try {
			log.debug("Verify putty connection");
			ptySession.setConfig(ptyconfig);
			ptySession.connect();
			log.debug("Successfully Connected through putty");
		} catch (JSchException e) {
			log.error(e.getMessage(), e);
			throw (e);
		}
	}

	public void SFTPConnection_RUNthejarFile(String jar) {

		try {
			Channel channel = ptySession.openChannel("exec");
			try {
				((ChannelExec) channel).setCommand("cd " + configFileReader.getExecuteScriptPath() + "; " + jar + "");

				((ChannelExec) channel).setErrStream(System.err);

				channel.connect();
				channel.run();

			} catch (Exception e) {
				e.printStackTrace();
			}

			OutputStream out = channel.getOutputStream();
			InputStream in = channel.getInputStream();

			String output = "";
			while (channel.isClosed() != true) {

				try {
					output += streamToString(in);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			channel.disconnect();
			ptySession.disconnect();

			do {
				Thread.sleep(1000);
			} while (!channel.isEOF());

			ptySession.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String streamToString(InputStream input) throws Exception {
		String output = "";

		while (input.available() > 0) {

			output += ((char) (input.read()));

		}

		return output;
	}

	public void clickIcon(String arg1) throws Exception {
		log.debug("Verify click the " + arg1 + " icon");
		String xPath = "//label[contains(text(),'" + arg1 + "')]";
		try {
			setElement.clickElementByXpath(xPath);
		} catch (Exception e) {
			try {
				Thread.sleep(2000);
				log.debug("Try to click on icon again");
				setElement.clickElementByXpath("//label[contains(text(),'" + arg1 + "')]");
			} catch (Exception e1) {
				log.error(e1.getMessage(), e1);
				;
				throw (e1);
			}

		}
		log.debug("Successfully clicked the " + arg1 + " icon");
		getScreenShots(webDriver, iImageCounter++ + "_" + arg1, configFileReader.getImageLocation(),
				setScreenShotPath(arg1)[0]);
	}

	public void seePage(String arg1) {
		try {
			log.debug("Verify " + arg1 + " page is loading");
			setElement.verifyPageLoad("//h1[contains(text(),'" + arg1 + "')]");
			log.debug("Successfully loaded " + arg1 + " page");
			getScreenShots(webDriver, iImageCounter++ + "_" + arg1, configFileReader.getImageLocation(),
					setScreenShotPath(arg1)[0]);
		} catch (AssertionError e) {
			log.error(e.getMessage(), e);
			Assert.fail();
		}
	}

	public void atHomePage(String arg1) throws Exception {
		try {
			log.debug("Verify click the " + arg1 + " Home Page");
			setElement.clickElementByXpath("//label[contains(text(),'" + arg1 + "')]");
			log.debug("Successfully clicked the " + arg1 + " Home Page");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw (e);
		}
	}

	public void seeHomePage(String arg1) {
		try {
			String xPath = "//h1[contains(text(),'Main Menu')]";
			log.debug("Verify " + arg1 + " Home Page is loading");
			setElement.waitElementVisibleByXpath(xPath);
			setElement.verifyPageLoad(xPath);
			log.debug("Successfully loaded " + arg1 + " Home Page");
		} catch (AssertionError e) {
			log.error(e.getMessage(), e);
			Assert.fail();
		}
	}

	public void clickOnIconToBack(String arg1) throws Exception {
		try {
			log.debug("Verify click on " + arg1 + " icon to back");
			Thread.sleep(1000);
			setElement.clickElementByXpath("//div[contains(text(),'" + arg1 + "')]");
			log.debug("Successfully clicked on " + arg1 + " icon to back");
			getScreenShots(webDriver, iImageCounter++ + "_" + arg1, configFileReader.getImageLocation(),
					setScreenShotPath(arg1)[0]);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw (e);
		}
	}

	public void clickOnButton(String arg1) throws Exception {
		try {
			Thread.sleep(1000);
			setElement.scrolldown();
			log.debug("Verify click on " + arg1 + " Button");
			setElement.clickElementByName(arg1);
			if (setElement.isAlertPresent()) {
				setElement.clickAlert();
			}
			log.debug("Successfully clicked on " + arg1 + " Button");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw (e);
		}
	}

	public void clickTheLink(String arg1) throws Exception {
		try {
			log.debug("Verify click the link " + arg1);
			setElement.clickFirstDetailLink(arg1);
			log.debug("Successfully clicked the link " + arg1);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw (e);
		}
	}

	public void checkForSuccessMessage(String arg1, String arg2) {
		String displayMessage = "";
		String message = arg1.replaceAll("\\s", "");
		log.debug("Verify success message: " + arg1 + " in " + arg2);
		log.debug("Message 1:" + arg1);
		try {
			displayMessage = setElement.messageText();
			log.debug("Message 2:" + displayMessage);
			getScreenShots(webDriver, iImageCounter++ + "_" + arg2, configFileReader.getImageLocation(),
					setScreenShotPath(arg2)[0]);
			Assert.assertTrue("Massage comparision are different", displayMessage.contains(message));
		} catch (AssertionError e) {
			log.error(e.getMessage(), e);
			Assert.fail();
		}
		log.debug("Successfully verified: " + arg1 + " in " + arg2);
	}

	public void checkForFailedMessage(String arg1, String arg2) {
		String displayMessage = "";
		String message = arg1.replaceAll("\\s", "");
		log.debug("Verify Failed message: " + arg1 + " in " + arg2);
		log.debug("Message 1:" + arg1);
		try {
			displayMessage = setElement.messageText();
			log.debug("Message 2:" + displayMessage);
			getScreenShots(webDriver, iImageCounter++ + "_" + arg2, configFileReader.getImageLocation(),
					setScreenShotPath(arg2)[0]);
			Assert.assertTrue("Massage comparision are different", !displayMessage.contains(message));
		} catch (AssertionError e) {
			log.error(e.getMessage(), e);
			Assert.fail();
		}
		log.debug("Successfully verified: " + arg1 + " in " + arg2);
	}

	public void seeTheApplicationPage(String arg1) {
		try {
			log.debug("Verify " + arg1 + " Application Page is loading");
			setElement.verifyPageLoad("//h1[contains(text(),'" + arg1 + "')]");
			log.debug("Successfully loaded " + arg1 + " Application Page");
		} catch (AssertionError e) {
			failScenarioMessageAllow = true;
			failScenarioMessageText = arg1 + " is not displayed";
			log.error(e.getMessage(), e);
			Assert.fail();
		}
	}

	public void clickOnSquareIconOnCorner() throws Exception {
		try {
			log.debug("verify quare icon loaded");
			Thread.sleep(2000);
			setElement.clickElementById("showLeftPush");
			log.debug("click on quare icon successfully");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw (e);
		}
	}

	public void clickTheIconOnApplicationPage(String arg1) throws Exception {
		try {
			Thread.sleep(2000);
			log.debug("Verify " + arg1 + " Application Page is loading");
			setElement.clickElementByXpath("//a[contains(text(),'" + arg1 + "')]");
			log.debug("Successfully loaded " + arg1 + " Application Page");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw (e);
		}
	}

	public void pageScrollDown() {
		try {
			log.debug("Page scroll down");
			setElement.scrolldown();
			log.debug("Successfully page scroll down");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw (e);
		}
	}

	public void clickOnHomePage() throws Exception {
		try {
			log.debug("Click on Home Page");
			setElement.clickElementByXpath("//div[contains(text(),'Home')]");
			log.debug("Successfully clicked on Home Page");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw (e);
		}
	}

	public byte[] extractBytes (String ImageName) throws IOException {
		 // open image
		//ImageName ="D:\\SeleniumTesting\\E25Loyal\\cucumber\\reports\\screenshots\\3_create_appoinment_for_the_created_parent_createing_a_appoinment1560242367549";
		 File imgPath = new File(ImageName);
		 BufferedImage bufferedImage = ImageIO.read(imgPath);

		 // get DataBufferBytes from Raster
		 WritableRaster raster = bufferedImage .getRaster();
		 DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
System.out.println("data----------->"+data);
		 return ( data.getData() );
		}
	
	// For WebDriver screenshots
	public void failedScenarioscreenShot(Scenario scenario) {

//		if (scenario.getStatus().equalsIgnoreCase("failed")) {
//			try {
//				scenario.embed(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES), "image/png");
//				if (configFileReader.getFailMsgAllow()) {
//					if (failScenarioMessageAllow) {
//						scenario.write(failScenarioMessageText);
//						failScenarioMessageAllow = false;
//						failScenarioMessageText = "";
//					}
//				}
//			} catch (WebDriverException e) {
//				log.error(e.getMessage(), e);
//			}
//		}
		if (scenario.getStatus().equalsIgnoreCase("failed")) {
			try {
				
//				new File("Errors").mkdir();
//				File outputFile = new File("test1.jpg");
			    Toolkit toolkit = Toolkit.getDefaultToolkit();
			    Dimension screenSize = toolkit.getScreenSize();
			    Rectangle screenRect = new Rectangle(screenSize);
			    Robot robot = new Robot();
			    BufferedImage bufferimage = robot.createScreenCapture(screenRect);
			  // ImageIO.write(bufferimage, "png", outputFile);
			   
			   
			   
				 Thread.sleep(1000); 
				//BufferedImage bufferimage = ImageIO.read(new File("D:\\SeleniumTesting\\E25Loyal\\cucumber\\reports\\screenshots\\test111.png"));
			    Thread.sleep(1000); 
				ByteArrayOutputStream output = new ByteArrayOutputStream();
				Thread.sleep(1000); 
			      ImageIO.write(bufferimage, "png", output );
			      Thread.sleep(1000); 
			      byte [] data = output.toByteArray();
			      System.out.println("data---------->"+data);
			      Thread.sleep(2000); 
			      scenario.embed(data , "image/png");
				if (configFileReader.getFailMsgAllow()) {
					if (failScenarioMessageAllow) {
						scenario.write(failScenarioMessageText);
						failScenarioMessageAllow = false;
						failScenarioMessageText = "";
					}
				}
			} catch (Exception e) {
				System.out.println(e);
				log.error(e.getMessage(), e);
			}
		}

	}

	// For GUI screenshots
	public void failedScenarioscreenShot_GUI(Scenario scenario) throws AWTException, IOException {
		if (scenario.getStatus().equalsIgnoreCase("failed")) {
			try {

				new File("Errors").mkdir();
				File outputFile = new File("test1.jpg");
				/*
				 * Toolkit toolkit = Toolkit.getDefaultToolkit(); Dimension screenSize =
				 * toolkit.getScreenSize(); Rectangle screenRect = new Rectangle(screenSize);
				 * Robot robot = new Robot(); BufferedImage image =
				 * robot.createScreenCapture(screenRect);
				 */
				// ImageIO.write(image, "png", outputFile);
				byte[] screenshot = (((TakesScreenshot) screenDriver).getScreenshotAs(OutputType.BYTES));
				System.out.println("########### screenshot captured");

				scenario.embed(screenshot, "image/png");

				System.out.println("################ embeded to the repport " + screenshot);
				// scenario.embed(((TakesScreenshot)
				// webDriver).getScreenshotAs(OutputType.BYTES), "image/png");
				if (configFileReader.getFailMsgAllow()) {
					if (failScenarioMessageAllow) {
						scenario.write(failScenarioMessageText);
						failScenarioMessageAllow = false;
						failScenarioMessageText = "";
					}
				}
			} catch (WebDriverException e) {
				log.error(e.getMessage(), e);
			}
		}
	}

	private byte[] getScreenshotAs(OutputType<byte[]> bytes) {
		// TODO Auto-generated method stub
		return null;
	}

	public void clickTheIconTo(String arg1, String arg2) throws Exception {
		String xPath = "//label[contains(text(),'" + arg1 + "')]";
		try {
			log.debug("Verify click the " + arg1 + " icon to " + arg2);
			setElement.clickElementByXpath(xPath);
			log.debug("Successfully clicked the " + arg1 + " icon to " + arg1);
			getScreenShots(webDriver, iImageCounter++ + "_" + arg2, configFileReader.getImageLocation(),
					setScreenShotPath(arg2)[0]);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw (e);
		}
	}

	public void clickFunctionIdIconTo(String arg1, String arg2) {
		try {
			log.debug("Verify click the " + arg1 + " icon to " + arg2);
			setElement.clickElementById(arg1);
			log.debug("Successfully clicked the " + arg1 + " icon to " + arg1);
			getScreenShots(webDriver, iImageCounter++ + "_" + arg2, configFileReader.getImageLocation(),
					setScreenShotPath(arg2)[0]);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw (e);
		}
	}

	public void clickTheIconIn(String arg1, String arg2) throws Exception {
		try {
			log.debug("Verify click the " + arg1 + " icon in " + arg2);
			setElement.clickElementByXpath("//div[contains(text(),'" + arg1 + "')]");
			log.debug("Successfully clicked the " + arg1 + " icon in " + arg1);
			getScreenShots(webDriver, iImageCounter++ + "_" + arg2, configFileReader.getImageLocation(),
					setScreenShotPath(arg2)[0]);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw (e);
		}
	}

	public void seeThePageTo(String arg1, String arg2) {
		try {
			log.debug("Verify " + arg1 + " page is loading to " + arg2);
			setElement.verifyPageLoad("//h1[contains(text(),'" + arg1 + "')]");
			log.debug("Successfully loaded " + arg1 + " page to " + arg2);
			getScreenShots(webDriver, iImageCounter++ + "_" + arg2, configFileReader.getImageLocation(),
					setScreenShotPath(arg2)[0]);
		} catch (AssertionError e) {
			log.error(e.getMessage(), e);
			Assert.fail();
		}
	}

	// normal data criteria fill

	public void dataCriteriaOf(String arg1, String arg2, DataTable table) throws Exception {
		try {
			List<List<String>> data = table.raw();
			log.debug("Verify fill search criteria of " + arg2);
			for (int i = 1; i < data.size(); i++) {
				String elementType = data.get(i).get(3).toLowerCase();
				System.out.println("data.get(i).get(3)" + data.get(i).get(3));
				String option = data.get(i).get(2).toLowerCase();
				switch (elementType) {
				case "dropdown":
					String[] optionValues = option.split(",");
					String optionValueOne = optionValues[0].toLowerCase();
					String optionValueTwo = optionValues[1].toLowerCase();
					switch (optionValueOne) {
					case "name":
						if (optionValueTwo.equals("index")) {
							setElement.SelectNamedDropDownByIndex(data.get(i).get(0),
									Integer.parseInt(data.get(i).get(1)));
						}
						if (optionValueTwo.equals("value")) {
							setElement.SelectNamedDropDownByValue(data.get(i).get(0), (data.get(i).get(1)));
						}
						break;
					case "id":
						if (optionValueTwo.equals("index")) {
							setElement.SelectDropDownByIndex(data.get(i).get(0), Integer.parseInt(data.get(i).get(1)));
						}
						if (optionValueTwo.equals("value")) {
							setElement.SelectIDDropDownByValue(data.get(i).get(0), (data.get(i).get(1)));
						}
						break;
					case "xpath":
						if (optionValueTwo.equals("index")) {
							setElement.SelectXpathDropDownByIndex(data.get(i).get(0),
									Integer.parseInt(data.get(i).get(1)));
						}
						if (optionValueTwo.equals("value")) {
							setElement.SelectXpathDropDownByValue(data.get(i).get(0), (data.get(i).get(1)));
						}
						break;
					}
					break;
				case "click":
					switch (option) {
					case "id":
						setElement.clickElementById(data.get(i).get(0));
						Thread.sleep(1000);
						break;
					case "name":
						setElement.clickElementByName(data.get(i).get(0));
						Thread.sleep(1000);
						break;
					case "xpath":
						setElement.clickElementByXpath(data.get(i).get(0));
						Thread.sleep(1000);
						break;
					}
					break;
				case "textbox":
					switch (option) {
					case "id":
						setElement.setElement("TEXT", data.get(i).get(1), data.get(i).get(0));
						break;
					case "name":
						setElement.setElementByName("TEXT", data.get(i).get(1), data.get(i).get(0));
						break;
					case "xpath":
						System.out.println("in xpath section");
						setElement.setElementByXpath("TEXT", data.get(i).get(1), data.get(i).get(0));
						System.out.println("done xpath section" + data.get(i).get(0));
						break;

					}
					break;
				}
			}
			getScreenShots(webDriver, iImageCounter++ + "_fill criteria of " + arg2,
					configFileReader.getImageLocation(), setScreenShotPath(arg2)[0]);
			log.debug("Successfully filled search criteria of " + arg2);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw (e);
		}
	}

	// (GetData from excel) data criteria fill (remove the datatable Column names)

	public void ExceldataCriteriaOf(String arg1, String arg2, DataTable table) throws Exception {
		try {
			List<List<String>> data = table.raw();
			log.debug("Verify fill search criteria of " + arg2);
			for (int i = 0; i < data.size(); i++) {
				String elementType = data.get(i).get(3).toLowerCase();
				String option = data.get(i).get(2).toLowerCase();
				switch (elementType) {
				case "dropdown":
					String[] optionValues = option.split(",");
					String optionValueOne = optionValues[0].toLowerCase();
					System.out.println("optionValueOne--->" + optionValueOne);
					String optionValueTwo = optionValues[1].toLowerCase();
					System.out.println("optionValueTwo--->" + optionValueTwo);
					switch (optionValueOne) {
					case "name":
						if (optionValueTwo.equals("index")) {
							setElement.SelectNamedDropDownByIndex(data.get(i).get(0),
									Integer.parseInt(data.get(i).get(1)));
						}
						if (optionValueTwo.equals("value")) {
							setElement.SelectNamedDropDownByValue(data.get(i).get(0), (data.get(i).get(1)));
						}
						break;
					case "id":
						if (optionValueTwo.equals("index")) {
							setElement.SelectDropDownByIndex(data.get(i).get(0), Integer.parseInt(data.get(i).get(1)));
						}
						if (optionValueTwo.equals("value")) {
							setElement.SelectIDDropDownByValue(data.get(i).get(0), (data.get(i).get(1)));
						}
						break;
					case "xpath":
						if (optionValueTwo.equals("index")) {
							setElement.SelectXpathDropDownByIndex(data.get(i).get(0),
									Integer.parseInt(data.get(i).get(1)));
						}
						if (optionValueTwo.equals("value")) {
							setElement.SelectXpathDropDownByValue(data.get(i).get(0), (data.get(i).get(1)));
						}
						break;
					/*
					 * case "css": if (optionValueTwo.equals("index")) {
					 * setElement.SelectNamedDropDownByIndex(data.get(i).get(0),
					 * Integer.parseInt(data.get(i).get(1))); } if (optionValueTwo.equals("value"))
					 * { setElement.SelectNamedDropDownByValue(data.get(i).get(0),
					 * (data.get(i).get(1))); } break;
					 */
					}
					break;
				case "click":
					switch (option) {
					case "id":
						setElement.clickElementById(data.get(i).get(0));
						Thread.sleep(1000);
						break;
					case "name":
						setElement.clickElementByName(data.get(i).get(0));
						Thread.sleep(1000);
						break;
					case "xpath":
						setElement.clickElementByXpath(data.get(i).get(0));
						Thread.sleep(1000);
						break;
					}
					break;
				case "textbox":
					switch (option) {
					case "id":
						setElement.setElement("TEXT", data.get(i).get(1), data.get(i).get(0));
						break;
					case "name":
						setElement.setElementByName("TEXT", data.get(i).get(1), data.get(i).get(0));
						break;
					case "xpath":
						setElement.setElementByXpath("TEXT", data.get(i).get(1), data.get(i).get(0));
						break;

					}
					break;
				}
			}
			getScreenShots(webDriver, iImageCounter++ + "_fill criteria of " + arg2,
					configFileReader.getImageLocation(), setScreenShotPath(arg2)[0]);
			log.debug("Successfully filled search criteria of " + arg2);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw (e);
		}
	}

	// several iteration happening for Scenario outline

	public void dataCriteriaOfScenarioOut(String arg1, String arg2, DataTable table) throws Exception {
		try {
			List<List<String>> data = table.raw();
			log.debug("Verify fill search criteria of " + arg2);
			for (int i = 1; i < data.size(); i++) {
				String elementType = data.get(i).get(2).toLowerCase();
				System.out.println("elementType----> " + elementType);
				String option = data.get(i).get(1).toLowerCase();
				System.out.println("option----> " + option);
				switch (elementType) {
				case "dropdown":
					String[] optionValues = option.split(",");
					String optionValueOne = optionValues[0].toLowerCase();
					String optionValueTwo = optionValues[1].toLowerCase();
					switch (optionValueOne) {
					case "name":
						if (optionValueTwo.equals("index")) {
							setElement.SelectNamedDropDownByIndex(data.get(i).get(0),
									Integer.parseInt(data.get(i).get(1)));
						}
						if (optionValueTwo.equals("value")) {
							setElement.SelectNamedDropDownByValue(data.get(i).get(0), (data.get(i).get(1)));
						}
						break;
					case "id":
						if (optionValueTwo.equals("index")) {
							setElement.SelectDropDownByIndex(data.get(i).get(0), Integer.parseInt(data.get(i).get(1)));
						}
						if (optionValueTwo.equals("value")) {
							setElement.SelectIDDropDownByValue(data.get(i).get(0), (data.get(i).get(1)));
						}
						break;
					case "xpath":
						if (optionValueTwo.equals("index")) {
							setElement.SelectXpathDropDownByIndex(data.get(i).get(0),
									Integer.parseInt(data.get(i).get(1)));
						}
						if (optionValueTwo.equals("value")) {
							setElement.SelectXpathDropDownByValue(data.get(i).get(0), (data.get(i).get(1)));
						}
						break;
					}
					break;
				case "click":
					switch (option) {
					case "id":
						setElement.clickElementById(data.get(i).get(0));
						Thread.sleep(1000);
						break;
					case "name":
						setElement.clickElementByName(data.get(i).get(0));
						Thread.sleep(1000);
						break;
					case "xpath":
						setElement.clickElementByXpath(data.get(i).get(0));
						Thread.sleep(1000);
						break;
					}
					break;
				case "textbox":
					switch (option) {
					case "id":
						System.out.println("arg1, data.get(i).get(0)" + arg1 + "    " + data.get(i).get(0));
						setElement.setElement("TEXT", arg1, data.get(i).get(0));
						break;
					case "name":
						System.out.println("arg1, data.get(i).get(0)" + arg1 + "    " + data.get(i).get(0));
						setElement.setElementByName("TEXT", arg1, data.get(i).get(0));
						break;
					case "xpath":
						System.out.println("arg1, data.get(i).get(0)" + arg1 + "    " + data.get(i).get(0));
						setElement.setElementByXpath("TEXT", arg1, data.get(i).get(0));
						break;

					}
					break;
				}
			}
			getScreenShots(webDriver, iImageCounter++ + "_fill criteria of " + arg2,
					configFileReader.getImageLocation(), setScreenShotPath(arg2)[0]);
			log.debug("Successfully filled search criteria of " + arg2);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw (e);
		}
	}

	public void navigateToPreviousPage() throws Exception {
		try {
			log.debug("Verify navigate to previous page");
			Thread.sleep(10000);
			setElement.navigateToBack();
			log.debug("Successfully navigated to previous page");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw (e);
		}
	}

	public void clickOnButtonIn(String arg1, String arg2, DataTable table) throws Exception {
		try {
			List<List<String>> data = table.raw();
			Thread.sleep(1000);
			log.debug("Verify click " + arg1 + " button in " + arg2);
			for (int i = 1; i < data.size(); i++) {
				String option = data.get(i).get(1).toLowerCase();
				switch (option) {
				case "id":
					setElement.clickElementById(data.get(i).get(0));
					break;
				case "name":
					setElement.clickElementByName(data.get(i).get(0));
					break;
				case "xpath":
					setElement.clickElementByXpath(data.get(i).get(0));
					break;
				case "classname":
					setElement.clickElementByClassName(data.get(i).get(0));
					break;
				case "cssselector":
					setElement.clickElementByCssSelector(data.get(i).get(0));
					break;
				}
			}
			if (setElement.isAlertPresent()) {
				setElement.clickAlert();
			}
			log.debug("Successfully clicked " + arg1 + " button in " + arg2);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw (e);
		}
	}

	public void closeTheConsole() throws Exception {
		try {
			log.debug("Verify close the console");
			Thread.sleep(3000);
			webDriver.quit();
			WebDriverInitializer.initialized = false;
			log.debug("Successfully closed the console");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw (e);
		}
	}

	public void verifyTitleIn(String arg1, String arg2) {
		String title = setElement.getTitle();
		log.debug("Verify " + arg1 + " title in " + arg2);
		try {
			if (!title.equals(arg1)) {
				Assert.assertTrue("Page title is mismatched", false);
			}
			log.debug("Successfully verified " + arg1 + " title in " + arg2);
		} catch (AssertionError e) {
			log.error(e.getMessage(), e);
		}
		getScreenShots(webDriver, iImageCounter++ + "_Verify title", configFileReader.getImageLocation(),
				setScreenShotPath(arg2)[0]);
	}

	public void uploadFileIn(String arg1, DataTable table) {
		List<List<String>> data = table.raw();
		String projectPath = System.getProperty("user.dir");
		log.debug("Project path: " + projectPath);
		log.debug("Verify upload file in " + arg1);
		try {
			for (int i = 1; i < data.size(); i++) {
				String option = data.get(i).get(2).toLowerCase();
				switch (option) {
				case "id":
					setElement.setElement("TEXT", projectPath + data.get(i).get(1), data.get(i).get(0));
					break;
				case "name":
					setElement.setElementByName("TEXT", projectPath + data.get(i).get(1), data.get(i).get(0));
					break;
				case "xpath":
					setElement.setElementByXpath("TEXT", projectPath + data.get(i).get(1), data.get(i).get(0));
					break;
				}
			}
			log.debug("Successfully Verified upload file in " + arg1);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw (e);
		}
	}

	public void callVerifyMethod(DataTable table) throws Exception {
		try {
			List<List<String>> SqlLine = table.raw();
			DbManager dbManager = new DbManager();
			boolean isPassed = false;
			log.debug("Verify DB validation method");
			Thread.sleep(1000);
			for (int i = 1; i < SqlLine.size(); i++) {
				ArrayList<Integer> tmpDBValue = new ArrayList<Integer>();
				tmpDBValue = dbManager.verfyDb(SqlLine.get(i).get(0), SqlLine.get(i).get(1), SqlLine.get(i).get(2));
				isPassed = tmpDBValue.get(0) == 1 ? true : false;
				if (!isPassed) {
					failScenarioMessageText = SqlLine.get(i).get(1) + " query is expected " + SqlLine.get(i).get(2)
							+ " rows.But got " + tmpDBValue.get(1) + " rows.";
					break;
				}
			}
			log.debug("Successfully verified DB validation method");
			if (!isPassed) {
				assertTrue("Query Result Does Not Matched to Expected Count.", false);
				log.debug("Query Result Does Not Matched to Expected Count.");
			}
		} catch (AssertionError e) {
			log.error(e.getMessage(), e);
			failScenarioMessageAllow = true;
			Assert.fail();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw (e);
		}
	}

	public int iImageCounter() {
		return iImageCounter++;
	}

	public void FolderName(String Foldername) {

		foldername = Foldername;

		cwd = System.getProperty("user.dir");
		path = cwd + configFileReader.getVbsScreeshotPath() + foldername;
		System.out.println("path" + path);
	}

	public void VBSFillData(DataTable table, String SectionName) throws Exception {

		try {
			List<List<String>> data = table.raw();
			log.debug("Verify fill search criteria of ");
			for (int i = 1; i < data.size(); i++) {
				String elementType = data.get(i).get(3).toLowerCase();
				System.out.println("Action Type----> " + elementType);
				String OffsetValue = data.get(i).get(4);
				String[] optionValues = OffsetValue.split(",");
				String optionValueOne = optionValues[0].toLowerCase();
				// System.out.println("optionValueOne--->" + optionValueOne);
				String optionValueTwo = optionValues[1].toLowerCase();
				// System.out.println("optionValueTwo--->" + optionValueTwo);
				int intValueOne = Integer.parseInt(optionValueOne);
				int intValueTwo = Integer.parseInt(optionValueTwo);
				switch (elementType) {

				case "sclick":
					try {
						System.out.println("Stirng test --->" + path + "\\" + data.get(i).get(1));
						Pattern p1 = new Pattern(path + "\\" + data.get(i).get(1) + ".png");

						// double Value = Double.valueOf(data.get(i).get(5));

						// p1.similar(Value);
						
						p1.targetOffset(intValueOne, intValueTwo);
						
						screenDriver.click(p1);

						System.out.println("------------------Clicked on " + data.get(i).get(0));

						log.debug("------------------Clicked on " + data.get(i).get(0));
						Thread.sleep(1000);
					} catch (Exception e) {

						System.out.println(e);

						assertTrue("--------------Failed to click " + data.get(i).get(0), false);
					}
					break;

					
				case "dclick":
					try {
						Pattern p2 = new Pattern(path + "\\" + data.get(i).get(1) + ".png");

						// double Value = Double.valueOf(data.get(i).get(5));

						// p2.similar(Value);
						// s.wait(p.similar((float) 0.90),2 ).click();

						p2.targetOffset(intValueOne, intValueTwo);

						screenDriver.click(p2);

						System.out.println("------------------Clicked on " + data.get(i).get(0));

						log.debug("------------------Clicked on " + data.get(i).get(0));

						Thread.sleep(1000);
					} catch (Exception e) {

						System.out.println(e);

						assertTrue("--------------Failed to click " + data.get(i).get(0), false);

					}
					break;

				case "textbox":
					try {
						Pattern p8 = new Pattern(path + "\\" + data.get(i).get(1) + ".png");

						// double Value = Double.valueOf(data.get(i).get(5));

						// p8.similar(Value);

						p8.targetOffset(intValueOne, intValueTwo);

						screenDriver.click(p8);
						Thread.sleep(1000);
						screenDriver.type("a", KeyModifier.CTRL);

						screenDriver.type("c", KeyModifier.CTRL);
						// screenDriver.type(Key.BACKSPACE);
						String test = Env.getClipboard();
						System.out.println("Copyied value-->" + test);
						screenDriver.click();
						screenDriver.doubleClick();

						Thread.sleep(1000);
						screenDriver.type(Key.BACKSPACE);
						screenDriver.type(data.get(i).get(2));
						System.out.println("------------------Filling data on " + data.get(i).get(0)
								+ " field and value = " + data.get(i).get(2));
						log.debug("------------------Filling data on " + data.get(i).get(0) + " field and value = "
								+ data.get(i).get(2));

					} catch (Exception e) {

						System.out.println(e);

						assertTrue("--------------Failed to click " + data.get(i).get(0), false);

					}
					break;

				case "pagedown":

					screenDriver.wheel(1, 30);

					screenDriver.wheel(1, 30);

					// screenDriver.type(Key.PAGE_DOWN);

					break;

				case "click_similar":
					try {
						Pattern p1 = new Pattern(path + "\\" + data.get(i).get(1) + ".png");

						double Value = Double.valueOf(data.get(i).get(5));

						screenDriver.wait(p1.similar((float) Value), 2).click();

						// p1.targetOffset(intValueOne, intValueTwo);

						System.out.println("------------------Clicked on the desktop FST icon sucessfully");

						log.debug("------------------Clicked on the FST desktop icon sucessfully");
						Thread.sleep(1000);
					} catch (Exception e) {

						System.out.println(e);

						assertTrue("--------------Failed to click " + data.get(i).get(0), false);

					}
					break;
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw (e);
		}

	}

}