package com.cucumber.common;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import cucumber.api.DataTable;
import jxl.Sheet;
import jxl.Workbook;

public class SetElements {

	// public static boolean exit = false;

	Logger log = Logger.getLogger("LOG");
	static ConfigFileReader configFileReader;
	static WebDriver driver;
	static String cwd = System.getProperty("user.dir");
	static Screen screenDriver;

	public SetElements() {

		configFileReader = new ConfigFileReader();
		screenDriver = WebDriverInitializer.getDriver();

	}

	public void setElement(String sType, String sText, String sId) {

		if (sType.equalsIgnoreCase("TEXT")) {
			waitElementVisibleById(sId, configFileReader.getTimeOutPeriod());
			driver.findElement(By.id(sId)).clear();
			driver.findElement(By.id(sId)).sendKeys(sText);
		} else if (sType.equalsIgnoreCase("DROPDOWN")) {
			waitElementVisibleById(sId, configFileReader.getTimeOutPeriod());
			Select DropDown = new Select(driver.findElement(By.id(sId))); // 2
			DropDown.selectByValue(sText);
		} else if (sType.equalsIgnoreCase("RADIOBOX")) {
			List<WebElement> rdBtn = driver.findElements(By.id(sId));
			int iSize = rdBtn.size();

			for (int i = 0; i < iSize; i++) {

				String sValue = rdBtn.get(i).getAttribute("value");

				if (sValue.equalsIgnoreCase(sText)) {
					rdBtn.get(i).click();
					break;
				}
			}
		} else if (sType.equalsIgnoreCase("CHECKBOX"))

		{
			waitElementVisibleById(sId, configFileReader.getTimeOutPeriod());
			if (!driver.findElement(By.id(sId)).isSelected()) {
				driver.findElement(By.id(sId)).click();
			}

		}
	}

	public void setElementByName(String sType, String sText, String sName) {

		if (sType.equalsIgnoreCase("TEXT")) {
			waitElementVisibleByName(sName, configFileReader.getTimeOutPeriod());
			driver.findElement(By.name(sName)).clear();
			driver.findElement(By.name(sName)).sendKeys(sText);
		} else if (sType.equalsIgnoreCase("DROPDOWN")) {
			waitElementVisibleByName(sName, configFileReader.getTimeOutPeriod());
			Select DropDown = new Select(driver.findElement(By.name(sName))); // 2
			DropDown.selectByValue(sText);
		} else if (sType.equalsIgnoreCase("RADIOBOX")) {
			List<WebElement> rdBtn = driver.findElements(By.name(sName));
			int iSize = rdBtn.size();

			// Start the loop from first Check Box to last Check Boxe
			for (int i = 0; i < iSize; i++) {
				// Store the Check Box name to the string variable,
				// using 'Value' attribute
				String sValue = rdBtn.get(i).getAttribute("value");
				// Select the Check Box it the value of the Check Box is
				// same what you are looking for
				if (sValue.equalsIgnoreCase(sText)) {
					rdBtn.get(i).click();
					break;
				}
			}
		} else if (sType.equalsIgnoreCase("CHECKBOX")) {
			waitElementVisibleByName(sName, configFileReader.getTimeOutPeriod());
			if (!driver.findElement(By.name(sName)).isSelected()) {
				driver.findElement(By.name(sName)).click();
			}
		}

	}

	public void setElementByXpath(String sType, String sText, String sXpath) {

		if (sType.equalsIgnoreCase("TEXT")) {
			waitElementVisibleByXpath(sXpath);
			driver.findElement(By.xpath(sXpath)).clear();
			driver.findElement(By.xpath(sXpath)).sendKeys(sText);
		} else if (sType.equalsIgnoreCase("DROPDOWN")) {
			waitElementVisibleByXpath(sXpath);
			Select DropDown = new Select(driver.findElement(By.xpath(sXpath))); // 2
			DropDown.selectByValue(sText);
		} else if (sType.equalsIgnoreCase("RADIOBOX")) {
			List<WebElement> rdBtn = driver.findElements(By.xpath(sXpath));
			int iSize = rdBtn.size();
			// Start the loop from first Check Box to last Check Boxe
			for (int i = 0; i < iSize; i++) {
				// Store the Check Box name to the string variable,
				// using 'Value' attribute
				String sValue = rdBtn.get(i).getAttribute("value");
				// Select the Check Box it the value of the Check Box is
				// same what you are looking for
				if (sValue.equalsIgnoreCase(sText)) {
					rdBtn.get(i).click();
					break;
				}
			}
		} else if (sType.equalsIgnoreCase("CHECKBOX"))

		{

			waitElementVisibleByXpath(sXpath);
			if (!driver.findElement(By.xpath(sXpath)).isSelected()) {
				driver.findElement(By.xpath(sXpath)).click();
			}
		}
	}

	public String startUpValidation() {

		String sErrorMessage = "";

		File file_Feature_folder = new File("feature file/");
		File file_Image_folder = new File(configFileReader.getImageLocation());

		if (!file_Feature_folder.isDirectory()) {
			sErrorMessage = "!!!!!!!CANNOT FIND THE FEATURE FILE FOLDER!!!!!!!\n";
		} else if (!file_Image_folder.isDirectory()) {
			sErrorMessage += "!!!!!!!!CANNOT FIND THE IMAGE LOCATION FILE FOLDER!!!!!!!!\n";
		}

		return sErrorMessage;
	}

	/*
	 * Checks whether the Given String is Empty or Not.
	 * 
	 * @param s
	 * 
	 * @return
	 */

	public boolean isEmpty(String str) {

		if ((str == null) || (str.trim().equals("")) || isblank(str) || (str.length() == 0) || (str.equals("null"))) {
			return true;
		}
		return false;
	}

	/*
	 * This method checks for a blank string and returns true if everything is spac
	 * This method is only called from the isEmpty method and is a private method.
	 */

	private boolean isblank(String s) {

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if ((c != ' ') && (c != '\t') && (c != '\n')) {
				return false;
			}
		}
		return true;
	}

	public boolean visibleByXpath(String xPath, long TimeOutSeconds) {
		if (driver.findElement(By.xpath(xPath)).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public void waitElementVisibleById(String sId, long TimeOutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, TimeOutSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(sId)));
	}

	public void waitElementVisibleByName(String sName, long TimeOutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, TimeOutSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(sName)));
	}

	public void waitElementVisibleByLinkText(String sLinkText, long TimeOutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, TimeOutSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(sLinkText)));
	}

	public void waitElementVisibleByCssSelector(String sCssSelector, long TimeOutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, TimeOutSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(sCssSelector)));
	}

	public void waitElementVisibleByXpath(String xPath) {
		WebDriverWait wait = new WebDriverWait(driver, configFileReader.getTimeOutPeriod());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
	}

	public void waitElementVisibleByTagName(String sTagName, long TimeOutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, TimeOutSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(sTagName)));
	}

	public void waitElementPresenceByXpath(String xPath) {
		WebDriverWait wait = new WebDriverWait(driver, configFileReader.getTimeOutPeriod());
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
	}

	public void waitElementClickableByXpath(String xPath) {
		WebDriverWait wait = new WebDriverWait(driver, configFileReader.getTimeOutPeriod());
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
	}

	public void waitElementClickableByClassName(String className) {
		WebDriverWait wait = new WebDriverWait(driver, configFileReader.getTimeOutPeriod());
		wait.until(ExpectedConditions.elementToBeClickable(By.className(className)));
	}

	// CssSelector

	public void waitElementClickableByCssSelector(String CssSelector) {
		WebDriverWait wait = new WebDriverWait(driver, configFileReader.getTimeOutPeriod());
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(CssSelector)));
	}

	public void waitElementClickableByName(String sName, long TimeOutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, TimeOutSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(By.name(sName)));
	}

	public void waitElementClickableById(String sId, long TimeOutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, TimeOutSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(sId)));
	}

	public void clickElementByName(String sName) {
		waitElementClickableByName(sName, configFileReader.getTimeOutPeriod());
		driver.findElement(By.name(sName)).click();
	}

	public void clickElementById(String sId) {
		waitElementClickableById(sId, configFileReader.getTimeOutPeriod());
		driver.findElement(By.id(sId)).click();
	}

	public void clickElementByLinkText(String sLinkText) {
		waitElementVisibleByLinkText(sLinkText, configFileReader.getTimeOutPeriod());
		driver.findElement(By.linkText(sLinkText)).click();
	}

	public void clickElementByXpath(String xPath) throws Exception {
		waitElementClickableByXpath(xPath);
		driver.findElement(By.xpath(xPath)).click();
	}

	public void clickElementByClassName(String ClassName) throws Exception {
		waitElementClickableByClassName(ClassName);
		driver.findElement(By.className(ClassName)).click();
	}

	public void clickElementByCssSelector(String CssSelector) throws Exception {
		waitElementClickableByCssSelector(CssSelector);
		driver.findElement(By.cssSelector(CssSelector)).click();
	}

	public void clickElementTageName(String sTagName) {
		waitElementVisibleByTagName(sTagName, configFileReader.getTimeOutPeriod());
		driver.findElement(By.tagName(sTagName)).click();
	}

	public void switchElementByName(String name) {
		waitElementVisibleByName(name, configFileReader.getTimeOutPeriod());
		driver.switchTo().frame(driver.findElement(By.name(name)));
	}

	public String getElementTextById(String id) {
		waitElementVisibleById(id, configFileReader.getTimeOutPeriod());
		return driver.findElement(By.id(id)).getText();
	}

	public String getElementTextByName(String name) {
		waitElementVisibleByName(name, configFileReader.getTimeOutPeriod());
		return driver.findElement(By.name(name)).getText();
	}

	public String getElementTextByXpath(String xPath) {
		waitElementVisibleByXpath(xPath);
		return driver.findElement(By.xpath(xPath)).getText();

	}

	public String getElementTextByTagName(String sTagName) {
		waitElementVisibleByTagName(sTagName, configFileReader.getTimeOutPeriod());
		return driver.findElement(By.tagName(sTagName)).getText();

	}

	public String getAttributeValueById(String id) {
		waitElementVisibleById(id, configFileReader.getTimeOutPeriod());
		return driver.findElement(By.id(id)).getAttribute("value");

	}

	public void delayWebDriver(long Seconds) {
		driver.manage().timeouts().implicitlyWait(Seconds, TimeUnit.SECONDS);
	}

	public boolean isCheckBoxSelected(String sId) {
		boolean bCheck = false;

		waitElementVisibleById(sId, configFileReader.getTimeOutPeriod());
		WebElement checkBox1 = driver.findElement(By.id(sId));
		if (checkBox1.isSelected()) {
			bCheck = true;
		}

		return bCheck;
	}

	public void SelectDropDownByIndex(String sId, int iIndex) {
		waitElementVisibleById(sId, configFileReader.getTimeOutPeriod());
		Select DropDown = new Select(driver.findElement(By.id(sId))); // 2
		DropDown.selectByIndex(iIndex);
	}

	/**
	 * Click first check box element out of check box list
	 */
	public void clickFirstCheckBoxElement() {
		List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
		if (!checkboxes.get(0).isSelected()) {
			checkboxes.get(0).click();
		}
	}

	/**
	 * Click first Redio element out of check box list
	 */
	public void clickFirstRedioElement() {
		List<WebElement> radio = driver.findElements(By.cssSelector("input[type='radio']"));
		radio.get(0).click();
	}

	/**
	 * Click first Detail link element out of detail link list
	 */
	public void clickFirstDetailLink() {
		List<WebElement> links = driver.findElements(By.tagName("a"));

		for (WebElement link : links) {
			if (link.getText().equals("Details")) {
				link.click();
				break;
			}
		}
	}

	/**
	 * Click Detail link by parameter value out of detail link list :
	 * <a href="javascript://" onclick="submitformCard('2207')">Details</a>
	 */
	public void clickDetailLinkByParam(String param) {
		driver.findElement(By.cssSelector("a[onclick*='submitformCard'][onclick*='" + param + "']")).click();
	}

	/**
	 * check for blank page
	 */
	public void checkBlankPage() {

		try {
			WebDriverWait wait = new WebDriverWait(driver, configFileReader.getTimeOutPeriod());
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("div")));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			if (jse instanceof WebDriver) {
				jse.executeScript("window.history.go(-1)");
			} else {
				throw new IllegalStateException("This driver does not support JavaScript!");
			}
			assertTrue("Blank page", false);
		}
	}

	/**
	 * Click first tree element link matching the given link-text
	 * 
	 */
	public void clickFirstTreeItem(String sLinkText) {
		waitElementVisibleByLinkText(sLinkText, configFileReader.getTimeOutPeriod() * 2);
		List<WebElement> listItems = driver.findElements(By.cssSelector("li[role='treeitem']"));

		for (WebElement link : listItems) {
			WebElement anchorlink = link.findElement(By.tagName("a"));
			if (anchorlink != null && sLinkText.equals(anchorlink.getText())) {
				anchorlink.click();
				break;
			}
		}
	}

	/**
	 * Click first link matching the given link-text
	 * 
	 * @throws Exception
	 * 
	 */
	public void clickFirstDetailLink(String sLinkType) throws Exception {
		boolean i = true;
		boolean isClick = false;
		while (i) {
			log.debug("clickFirstDetailLink starting");
			List<WebElement> links = driver.findElements(By.tagName("a"));
			log.debug("links size" + links.size());
			for (WebElement link : links) {
				log.debug("link.getText():" + link.getText());
				if (link.getText().equals(sLinkType)) {
					link.click();
					log.debug("link.click()");
					i = false;
					isClick = true;
					break;
				}
			}
			if (!isClick) {
				try {
					clickElementByXpath("//input[@value='Next']");
					i = true;
					log.debug("Click on next button");
				} catch (Exception e1) {
					i = false;
					log.error("failed click on " + sLinkType);
					log.error("Click on next button in Exception");
					log.error(e1.getMessage(), e1);
					throw (e1);
				}
			}
		}
	}

	/**
	 * Verify first link matching the given link-text
	 * 
	 */
	public boolean verifyDetailLink(String sLinkType) {
		waitElementVisibleByLinkText(sLinkType, configFileReader.getTimeOutPeriod() * 2);
		List<WebElement> links = driver.findElements(By.tagName("a"));
		boolean i = true;

		for (WebElement link : links) {
			if (link.getText().equals(sLinkType)) {
				i = true;
				break;
			} else
				i = false;
		}
		return i;
	}

	/**
	 * Click all links matching the given link-text
	 * 
	 * @throws InterruptedException
	 * 
	 */
	public void clickAllDetailLinks(String sLinkType) throws InterruptedException {

		boolean isException = true;

		while (isException) {
			Thread.sleep(1000);
			List<WebElement> links = driver.findElements(By.tagName("a"));

			log.debug("Size of links List" + links.size());

			for (WebElement link : links) {
				isException = false;

				log.debug("link GetText() " + link.getText());

				if (link.getText().equals(sLinkType)) {
					isException = true;
					link.click();
					break;
				}
			}
		}
	}

	/**
	 * Click item from an drop down from index by selecting select element by name
	 * 
	 */
	public void SelectNamedDropDownByIndex(String sName, int iIndex) {
		waitElementVisibleByName(sName, configFileReader.getTimeOutPeriod());
		Select DropDown = new Select(driver.findElement(By.name(sName)));
		DropDown.selectByIndex(iIndex);

	}

	/**
	 * Click item from an drop down from index by selecting select element by name
	 * 
	 */
	public void SelectXpathDropDownByIndex(String sXpath, int iIndex) {
		waitElementVisibleByXpath(sXpath);
		Select DropDown = new Select(driver.findElement(By.xpath(sXpath)));
		DropDown.selectByIndex(iIndex);

	}

	/**
	 * Click item from an drop down from by selecting select element by name
	 * 
	 */
	public void clickSelectBoxItems(String sSelectBoxName, String sItemName) {
		waitElementVisibleByName(sSelectBoxName, configFileReader.getTimeOutPeriod());
		Select DropDown = new Select(driver.findElement(By.name(sSelectBoxName)));
		DropDown.selectByVisibleText(sItemName);

	}

	/**
	 * Click item from an drop down from value by selecting name to select an
	 * element
	 * 
	 */
	public void SelectNamedDropDownByValue(String sName, String sValue) {
		waitElementVisibleByName(sName, configFileReader.getTimeOutPeriod());
		Select DropDown = new Select(driver.findElement(By.name(sName)));
		DropDown.selectByValue(sValue);

	}

	/**
	 * Click item from an drop down by selecting xpath to select an element
	 * 
	 */
	public void SelectXpathDropDownByValue(String sXpath, String sValue) {
		waitElementVisibleByXpath(sXpath);
		Select DropDown = new Select(driver.findElement(By.xpath(sXpath)));
		DropDown.selectByValue(sValue);

	}

	/**
	 * Click item from an drop down by selecting id to select an element
	 * 
	 */
	public void SelectIDDropDownByValue(String sId, String sValue) {
		waitElementVisibleById(sId, configFileReader.getTimeOutPeriod());
		Select DropDown = new Select(driver.findElement(By.id(sId)));
		DropDown.selectByValue(sValue);
	}

	/**
	 * Click item from an drop down by selecting select element by text
	 * 
	 */
	public void SelectNamedDropDownByText(String sName, String sText) {
		waitElementVisibleByName(sName, configFileReader.getTimeOutPeriod());
		Select DropDown = new Select(driver.findElement(By.name(sName)));
		DropDown.selectByVisibleText(sText);

	}

	/**
	 * get the Search values from the searched section
	 * 
	 */
	public String GetValuesFromSearch(String sClass) {
		waitElementClickableByClassName(sClass);
		String SeachedValue = driver.findElement(By.className(sClass)).getText();

		return SeachedValue;
	}

	public boolean selectByPartOfVisibleText(String sName, String value) {
		String fullValue = "";
		Boolean status = false;
		waitElementVisibleByTagName("option", configFileReader.getTimeOutPeriod());
		List<WebElement> optionElements = driver.findElements(By.tagName("option"));
		waitElementVisibleByName(sName, configFileReader.getTimeOutPeriod());
		for (WebElement optionElement : optionElements) {
			if (optionElement.getText().contains(value)) {
				fullValue = optionElement.getText();
				break;
			}
		}
		Select DropDown = new Select(driver.findElement(By.name(sName)));
		DropDown.selectByVisibleText(fullValue);
		status = true;
		return status;

	}

	/**
	 * Extract a displayed output from web page(table) by matching the preceding
	 * text.
	 * 
	 */
	public String extractValueFromMessageString(String sMatchString, String sLinkText) {
		delayWebDriver(5);
		String sResult = "";
		String sTemResult = "";

		List<WebElement> listItems = driver.findElements(By.cssSelector("ol[class='msg_list']"));

		for (WebElement link : listItems) {
			WebElement anchorlink = link.findElement(By.tagName("li"));
			sTemResult = anchorlink.getText();
			if (sTemResult != null && sTemResult.contains(sMatchString.trim())) {
				sResult = sTemResult.split(sMatchString)[1].trim();
				break;
			}
		}
		return sResult;
	}

	/**
	 * Check if a specific text is present in a table cell .
	 * 
	 */
	public boolean extractMessageFromTable(String sMatchString) {
		delayWebDriver(10);
		boolean bFound = false;
		String sTemResult = "";
		try {
			Thread.sleep(2000);
			List<WebElement> listItems = driver.findElements(By.cssSelector("div[class='main_content']"));
			log.debug("listItems size " + listItems.size());
			for (WebElement link : listItems) {
				Thread.sleep(1000);
				WebElement anchorlink = null;
				try {
					anchorlink = link.findElement(By.tagName("td"));
					log.debug("anchorlink " + anchorlink);
					sTemResult = anchorlink.getText();
					log.debug("sTemResult " + sTemResult);
					if (sTemResult != null && sTemResult.contains(sMatchString.trim())) {
						bFound = true;
						break;
					}
				} catch (StaleElementReferenceException e1) {
					Thread.sleep(3000);
					try {
						anchorlink = link.findElement(By.tagName("td"));
						log.debug("anchorlink in StaleElementReferenceException " + anchorlink);
						sTemResult = anchorlink.getText();
						log.debug("sTemResult in StaleElementReferenceException " + sTemResult);
						if (sTemResult != null && sTemResult.contains(sMatchString.trim())) {
							bFound = true;
							break;
						}
					} catch (Exception e2) {
						log.debug("Not found " + sMatchString);
						log.error(e2.getMessage(), e2);
					}

				}

			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return bFound;
	}

	public boolean extractMessageFromTableBr(String sMatchString) {
		delayWebDriver(10);
		boolean bFound = false;
		String sTemResult = "";
		try {
			Thread.sleep(2000);
			List<WebElement> listItems = driver.findElements(By.cssSelector("div[class='main_content']"));
			log.debug("listItems size " + listItems.size());
			for (WebElement link : listItems) {
				Thread.sleep(1000);
				WebElement anchorlink = null;
				try {
					anchorlink = link.findElement(By.tagName("b"));
					log.debug("anchorlink " + anchorlink);
					sTemResult = anchorlink.getText();
					log.debug("sTemResult " + sTemResult);
					if (sTemResult != null && sTemResult.contains(sMatchString.trim())) {
						bFound = true;
						break;
					}
				} catch (StaleElementReferenceException e1) {
					Thread.sleep(3000);
					try {
						anchorlink = link.findElement(By.tagName("b"));
						log.debug("anchorlink in StaleElementReferenceException " + anchorlink);
						sTemResult = anchorlink.getText();
						log.debug("sTemResult in StaleElementReferenceException " + sTemResult);
						if (sTemResult != null && sTemResult.contains(sMatchString.trim())) {
							bFound = true;
							break;
						}
					} catch (Exception e2) {
						log.debug("Not found " + sMatchString);
						log.error(e2.getMessage(), e2);
						;
					}

				}

			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return bFound;
	}

	/**
	 * Click on selected column cell if a specific text is present in a table cell .
	 * 
	 * @throws Exception
	 *
	 */
	public void clickTextTable(String sMatchString, int clickColumn, int selectColumn) throws Exception {

		List<WebElement> tr_collection = driver.findElements(By.xpath("//table/tbody/tr"));

		boolean isFound = false;

		while (!isFound) {
			try {
				int row_num = 1;
				for (WebElement trElement : tr_collection) {
					List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
					if (td_collection.get(selectColumn - 1).getText().contains(sMatchString)) {
						isFound = true;
						clickElementByXpath("//table/tbody/tr[" + row_num + "]/td[" + clickColumn + "]/input");
						break;
					}
					row_num++;
				}
				if (!isFound) {
					try {
						clickElementByXpath("//input[@value='Next']");
					} catch (Exception e1) {
						log.error(e1.getMessage(), e1);
						isFound = true;
						throw (e1);
					}
				}
			} catch (Exception e) {
				try {
					clickElementByXpath("//input[@value='Next']");
				} catch (Exception e1) {
					log.error(e1.getMessage(), e1);
					isFound = false;
					throw (e);
				}
			}
		}
	}

	/**
	 * If specific text is present in cell,click on cell
	 * 
	 * @throws Exception
	 *
	 */
	public void clickSpecifiedTextTable(String sMatchString, String finalElement, String tableTrXpath)
			throws Exception {

		List<WebElement> tr_collection = driver.findElements(By.xpath(tableTrXpath));

		int row_num = 1;
		boolean isFound = false;
		int i = 0;
		WebElement trElement = null;
		while (!isFound) {
			trElement = tr_collection.get(i);
			List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
			int col_num = 1;
			for (WebElement tdElement : td_collection) {
				if (tdElement.getText().contains(sMatchString)) {
					isFound = true;
					clickElementByXpath(tableTrXpath + "[" + row_num + "]/td[" + col_num + "]/a");
					break;
				}
				col_num++;
			}
			i++;
			row_num++;
		}
	}

	/**
	 * Extract a displayed output from web page(list) by matching the preceding
	 * text.
	 * 
	 */
	public boolean extractMessageFromListElement(String sMatchString) {
		// delayWebDriver(5);
		String sTemResult = "";
		String sCssSelector = "ol[class='msg_list']";
		boolean bFound = false;
		waitElementVisibleByCssSelector(sCssSelector, configFileReader.getTimeOutPeriod());
		List<WebElement> listItems = driver.findElements(By.cssSelector(sCssSelector));

		for (WebElement link : listItems) {
			WebElement anchorlink = link.findElement(By.tagName("li"));
			sTemResult = anchorlink.getText();
			log.debug("Message1 " + sMatchString);
			log.debug("Message2 " + anchorlink);
			if (sTemResult != null && sTemResult.contains(sMatchString.trim())) {
				bFound = true;
				break;
			}
		}
		return bFound;

	}

	// Get Message Text
	public String messageText() {
		// delayWebDriver(10);
		String sTemResult = "";
		String sCssSelector = "ol[class='msg_list']";
		log.debug("Starting to get message text");
		try {
			// Thread.sleep(1000);
			waitElementVisibleByCssSelector(sCssSelector, 3 * configFileReader.getTimeOutPeriod());
			List<WebElement> listItems = driver.findElements(By.cssSelector(sCssSelector));
			log.debug("List Items are :" + listItems);
			for (WebElement link : listItems) {
				WebElement anchorlink = link.findElement(By.tagName("li"));
				sTemResult = anchorlink.getText();
				log.debug("Message text in page:" + sTemResult);
			}
		} catch (UnhandledAlertException f) {
			try {
				log.debug("Starting hamdle pop up message");
				clickAlert();
				log.debug("Successfully handled pop up message");
				List<WebElement> listItems = driver.findElements(By.cssSelector("ol[class='msg_list']"));

				for (WebElement link : listItems) {
					WebElement anchorlink = link.findElement(By.tagName("li"));
					sTemResult = anchorlink.getText();
					log.debug("Message text in page:" + sTemResult);
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		} catch (Exception e1) {
			log.debug("Getting error");
			log.error(e1.getMessage(), e1);
		}
		return sTemResult.replaceAll("\\s", "");

	}

	/*
	 * Get ID from Success Message
	 * 
	 */

	public String getID() {
		delayWebDriver(5);
		String sTemResult = "";
		String id = "";

		List<WebElement> listItems = driver.findElements(By.cssSelector("ol[class='msg_list']"));
		for (WebElement link : listItems) {
			WebElement anchorlink = link.findElement(By.tagName("li"));
			sTemResult = anchorlink.getText();
			if (sTemResult != null) {
				id = sTemResult.replaceAll("[^0-9]", "");
			}
		}
		return id;

	}

	/*
	 * Verify page is loaded from page heading
	 * 
	 */
	public void verifyPageLoad(String xpath) {
		boolean accManaPagePresent = false;
		int i = 0;
		while (i < 2) {
			try {
				waitElementPresenceByXpath(xpath);
				waitElementVisibleByXpath(xpath);
				accManaPagePresent = driver.findElement(By.xpath(xpath)).isDisplayed();
				i = i + 2;
			} catch (Exception e) {
				log.debug("Got exception " + i + " time");
				i++;
				log.error(e.getMessage(), e);
			}
		}
		Assert.assertTrue(accManaPagePresent);

		/*
		 * try { Assert.assertTrue(accManaPagePresent); } catch (AssertionError e1) {
		 * log.error(e1.getMessage(), e1); Assert.fail(); }
		 */
	}

	public void scrolldown() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,10000)", "");
	}

	// Check alert present

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Check alert present

	public String getTitle() {
		String title = "";
		title = driver.getTitle();

		return title;
	}

	// To click on the 'OK' button of the alert

	public void clickAlert() {
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		log.debug("Alert data: " + alertText);
		alert.accept();
	}

	// Navigate to back

	public void navigateToBack() {
		driver.navigate().back();
	}

	public void selectDateInCalendar(String changeDateCount) throws Exception {

		DateFormat year = new SimpleDateFormat("yyyy");
		DateFormat month = new SimpleDateFormat("MM");
		DateFormat day = new SimpleDateFormat("dd");
		Date date = new Date();
		String selectDate = null;
		int yearNumber = Integer.parseInt(year.format(date));
		int previousMonth = Integer.parseInt(month.format(date)) - 1;
		switch (changeDateCount) {
		case "0":
		case "-1":
			selectDate = Integer.toString(Integer.parseInt(day.format(date)) - Integer.parseInt(changeDateCount));
			if (Integer.parseInt(selectDate) > 0) {
				selectDateInCalender(selectDate, "//table/tbody/tr/td/table/tbody/tr");
			} else {
				switch (previousMonth) {
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					selectMonthDateInCalender("31", "//table/tbody/tr/td/table/tbody/tr");
					break;
				case 4:
				case 6:
				case 9:
				case 11:
					selectMonthDateInCalender("30", "//table/tbody/tr/td/table/tbody/tr");
					break;
				case 2:
					if (((yearNumber % 4 == 0) && !(yearNumber % 100 == 0)) || (yearNumber % 400 == 0)) {
						selectMonthDateInCalender("29", "//table/tbody/tr/td/table/tbody/tr");
					} else {
						selectMonthDateInCalender("28", "//table/tbody/tr/td/table/tbody/tr");
					}
					break;
				default:
					log.error("Invalid month.");
					break;

				}
			}
			break;
		case "+1":
			selectDate = Integer.toString(Integer.parseInt(day.format(date)) + Integer.parseInt(changeDateCount));
			int currentMonth = Integer.parseInt(month.format(date));
			if (Integer.parseInt(selectDate) <= 28) {
				selectDateInCalender(selectDate, "//table/tbody/tr/td/table/tbody/tr");
			}
			if (Integer.parseInt(selectDate) == 31 && (currentMonth == 1 || currentMonth == 3 || currentMonth == 5
					|| currentMonth == 7 || currentMonth == 8 || currentMonth == 10 || currentMonth == 12)) {
				selectDateInCalender(selectDate, "//table/tbody/tr/td/table/tbody/tr");
			}
			if (Integer.parseInt(selectDate) == 30
					&& (currentMonth == 4 || currentMonth == 6 || currentMonth == 9 || currentMonth == 11)) {
				selectDateInCalender(selectDate, "//table/tbody/tr/td/table/tbody/tr");
			}
			if (Integer.parseInt(selectDate) == 29 && currentMonth == 2
					&& (((yearNumber % 4 == 0) && !(yearNumber % 100 == 0)) || (yearNumber % 400 == 0))) {
				selectDateInCalender(selectDate, "//table/tbody/tr/td/table/tbody/tr");
			} else {
				selectMonthDateInCalender("1", "//table/tbody/tr/td/table/tbody/tr");
			}
			break;
		}

	}

	// set time,calculate dates and click

	public void setTimeDateInCalendar(int previousDateCount, String time) throws Exception {
		DateFormat year = new SimpleDateFormat("yyyy");
		DateFormat month = new SimpleDateFormat("MM");
		DateFormat day = new SimpleDateFormat("dd");
		Date date = new Date();
		int yearNumber = Integer.parseInt(year.format(date));
		int previousMonth = Integer.parseInt(month.format(date)) - 1;
		String selectDate = Integer.toString(Integer.parseInt(day.format(date)) - previousDateCount);
		if (Integer.parseInt(selectDate) > 0) {
			setTimeDateInCalender(selectDate, "//table/tbody/tr/td/table/tbody/tr", "00:00:01");
		} else {
			switch (previousMonth) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				setTimeMonthDateInCalender("31", "//table/tbody/tr/td/table/tbody/tr", "00:00:01");
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				setTimeMonthDateInCalender("30", "//table/tbody/tr/td/table/tbody/tr", "00:00:01");
				break;
			case 2:
				if (((yearNumber % 4 == 0) && !(yearNumber % 100 == 0)) || (yearNumber % 400 == 0)) {
					setTimeMonthDateInCalender("29", "//table/tbody/tr/td/table/tbody/tr", "00:00:01");
				} else {
					setTimeMonthDateInCalender("28", "//table/tbody/tr/td/table/tbody/tr", "00:00:01");
				}
				break;
			default:
				log.error("Invalid month.");
				break;

			}
		}

	}

	// Select date from new window calendar
	public void selectDateInCalender(String date, String xPath) throws Exception {

		// Store the current window handle
		String winHandleBefore = driver.getWindowHandle();

		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		// Perform the actions on new window
		clickDateInCalendar(date, xPath);

		// Switch back to original browser (first window)
		driver.switchTo().window(winHandleBefore);
		driver.switchTo().frame("topFrame");

	}

	// Select month and date from new window calendar
	public void selectMonthDateInCalender(String date, String xPath) throws Exception {
		// Store the current window handle
		String winHandleBefore = driver.getWindowHandle();

		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		// Perform the actions on new window

		clickElementByXpath("//table/tbody/tr/td/table/tbody/tr/td/a[2]");

		clickDateInCalendar(date, xPath);

		// Switch back to original browser (first window)
		driver.switchTo().window(winHandleBefore);
		driver.switchTo().frame("topFrame");
	}

	/**
	 * Click date in Calendar
	 * 
	 * @throws Exception
	 *
	 */
	public void clickDateInCalendar(String sMatchString, String xPath) throws Exception {

		List<WebElement> tr_collection = driver.findElements(By.xpath(xPath));
		int row_num = 0;
		boolean isFound = false;
		int i = 0;
		WebElement trElement = null;
		while (!isFound) {
			trElement = tr_collection.get(i);
			List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
			int col_num = 1;
			for (WebElement tdElement : td_collection) {
				if (tdElement.getText().equals(sMatchString)) {
					isFound = true;
					clickElementByXpath("//table/tbody/tr[" + row_num + "]/td[" + col_num + "]/a");
					break;
				}
				col_num++;
			}
			i++;
			row_num++;
		}
	}

	// set time and date from new window calendar
	public void setTimeDateInCalender(String date, String xPath, String time) throws Exception {

		// Store the current window handle
		String winHandleBefore = driver.getWindowHandle();

		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		// Perform the actions on new window
		Thread.sleep(1000);
		setElementByXpath("TEXT", time, "//table/tbody/tr/td/table/tbody/tr[8]/td/font/input");
		clickDateInCalendar(date, xPath);

		// Switch back to original browser (first window)
		driver.switchTo().window(winHandleBefore);
		driver.switchTo().frame("topFrame");

	}

	// Set time,month and date from new window calendar
	public void setTimeMonthDateInCalender(String date, String xPath, String time) throws Exception {
		// Store the current window handle
		String winHandleBefore = driver.getWindowHandle();

		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		// Perform the actions on new window
		Thread.sleep(1000);
		setElementByXpath("TEXT", time, "//table/tbody/tr/td/table/tbody/tr[8]/td/font/input");
		clickElementByXpath("//table/tbody/tr/td/table/tbody/tr/td/a[2]");
		clickDateInCalendar(date, xPath);

		// Switch back to original browser (first window)
		driver.switchTo().window(winHandleBefore);
		driver.switchTo().frame("topFrame");
	}

	// Validation message method

	public void ValidationMessage(DataTable Table) throws Exception {

		List<List<String>> Tableraw = Table.raw();

		try {
			System.out.println("------------I should see the popup message '" + Tableraw.get(1).get(2) + "'");
			log.debug("-------------I should see the popup message " + Tableraw.get(1).get(2));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(3000);
			if (driver.findElement(By.xpath(Tableraw.get(1).get(0))).getText()
					.equalsIgnoreCase(Tableraw.get(1).get(2))) {
				System.out.println("--------------------popup message '" + Tableraw.get(1).get(2)
						+ "' showing correctly and its pass");
				log.debug("--------------------popup message '" + Tableraw.get(1).get(2)
						+ "' showing correctly and its pass");

			} else {

				log.debug("--------------------Fail '" + Tableraw.get(1).get(2) + "'");
				assertTrue("--------------------Fail '" + Tableraw.get(1).get(2) + "'", false);

			}
		} catch (Exception e) {
			System.out.println("--------------------Fail '" + Tableraw.get(1).get(2) + "'");
			log.error(e.getMessage(), e);
			throw (e);
		}
	}

	// Get Excel values from the Excel

	public String GetExcelValue(String searchValue1, String sheet) {
		// String sheet = "RegisterClient";
		FileInputStream fs = null;
		Workbook wb = null;
		try {

			String FilePath = Constants.REPORT_DATA_FILE_PATH;
			fs = new FileInputStream(FilePath);
			wb = Workbook.getWorkbook(fs);
			Sheet sh = wb.getSheet(sheet);
			int cols = sh.getColumns();
			int rows = sh.getRows();

			// TO get the access to the sheet
			for (int column = 0; column < cols; column++) {

				for (int row = 0; row < rows; row++) {

					String cellValue = sh.getCell(column, row).getContents();

					if (cellValue.equals(searchValue1)) {

						// searchValue1 = sh.getCell((column+1), row).getContents();
						searchValue1 = sh.getCell((column + 1), row).getContents();
						System.out.println("searchValue1--------->" + searchValue1);
						return searchValue1;

					}

				}
			}

		} catch (Exception e) {

			System.out.println(e);
		}
		return searchValue1;
	}

	// GetExcelValues from the excel sheet and arrange those data to the DataTable

	public DataTable GetExcelValueDataTable(String sheet, DataTable table) {

		List<List<String>> Query2 = table.raw();

		System.out.println("**Query2** " + Query2);
		List<List<String>> rawData = new ArrayList<List<String>>();

		for (int i = 0; i < Query2.size(); i++) {
			System.out.println("column values--->" + Query2.get(i).get(1));
			log.debug("column values--->" + Query2.get(i).get(1));
			String SearchValue = GetExcelValue(Query2.get(i).get(1), sheet);
			System.out.println("Query coloumn after Edit:  " + SearchValue);

			List<String> middle = Arrays.asList(Query2.get(i).get(0), SearchValue, Query2.get(i).get(2),
					Query2.get(i).get(3));

			rawData.add(middle);

		}
		DataTable modifications = DataTable.create(rawData);
		System.out.println("rawData-------------->" + modifications);
		log.debug("rawData-------------->" + modifications);

		return modifications;

	}

	// ################################################# Sikuli Methods ####################################################

	public void VBSElementDouble_Click(DataTable table) {

		try {
			System.out.println("--------------User clicked on FST_Player_Selection");
			List<List<String>> Tableraw = table.raw();

			System.out.println("--------------User clicked on FST_Player_Selection" + Tableraw.get(1).get(1));

			String path = cwd + configFileReader.getVbsScreeshotPath() + Tableraw.get(1).get(1) + ".png";

			System.out.println("--------------User clicked on FST_Player_Selection2" + Tableraw.get(1).get(1));

			Pattern v11 = new Pattern(path);

			System.out.println("--------------User clicked on FST_Player_Selection3" + Tableraw.get(1).get(1));

			v11.targetOffset(0, 0);

			System.out.println("--------------User clicked on FST_Player_Selection4" + Tableraw.get(1).get(1));

			screenDriver.doubleClick(v11);

			System.out.println("--------------User clicked on FST_Player_Selection5" + Tableraw.get(1).get(1));

			System.out.println("--------------User clicked on FST_Player_Selection");

			log.debug("--------------User clicked on FST_Player_Selection");

		}

		catch (Exception e) {

			System.out.println(e);

			assertTrue("--------------Fail clicked on FST_Player_Selection", false);

			System.out.println("--------------Fail clicked on FST_Player_Selection");

			log.debug("--------------Fail clicked on FST_Player_Selection");

		}

	}
}
