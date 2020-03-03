package com.cucumber.common;

import java.util.ArrayList;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

public class ConfigFileReader {

	private final String propertyFilePath = "config//Configuration.properties";
	PropertiesConfiguration properties = null;;
	Logger log = Logger.getLogger("LOG");

	public ConfigFileReader() {
		try {
			properties = new PropertiesConfiguration("config//Configuration.properties");
		} catch (ConfigurationException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}
	}

	public String getLoginPageUrl() {
		String loginPageUrl = (String) properties.getProperty("LOGIN_PAGE");
		if (loginPageUrl != null)
			return loginPageUrl;
		else
			throw new RuntimeException("LOGIN_PAGE not specified in the Configuration.properties file.");
	}

	public String getDBType() {
		String dbType = (String)properties.getProperty("DBType");
		if (dbType != null)
			return dbType;
		else
			throw new RuntimeException("DBType not specified in the Configuration.properties file.");
	}

	public String getDBUserName() {
		String dbUserName = (String)properties.getProperty("DBUSERNAME");
		if (dbUserName != null)
			return dbUserName;
		else
			throw new RuntimeException("DBUSERNAME not specified in the Configuration.properties file.");
	}

	public String getDBPassword() {
		String dbPassword = (String)properties.getProperty("DBPASSWORD");
		if (dbPassword != null)
			return dbPassword;
		else
			throw new RuntimeException("DBPASSWORD not specified in the Configuration.properties file.");
	}

	public String getIP() {
		String ip = (String)properties.getProperty("IP");
		if (ip != null)
			return ip;
		else
			throw new RuntimeException("IP not specified in the Configuration.properties file.");
	}
	
	public ArrayList<String> getPDFAttachments() {		
		ArrayList<String> dataPDFAttachment = new ArrayList<String>();
		dataPDFAttachment=( ArrayList<String>)properties.getProperty("PDF_ATTACHMENTS");
		ArrayList<String> removeTable = new ArrayList<String>();
		for(String x:dataPDFAttachment){
			removeTable.add(x);
		}
		
		if (!removeTable.isEmpty())
			return removeTable;
		else
			throw new RuntimeException("PDF_ATTACHMENTS not specified in the Configuration.properties file.");
		
	}
	
	public String getLoginEmailUserName() {
		String loginEmailUserName = (String)properties.getProperty("LOGINEMAILUSERNAME");
		if (loginEmailUserName != null)
			return loginEmailUserName;
		else
			throw new RuntimeException("LOGINEMAILUSERNAME not specified in the Configuration.properties file.");
	}

	public String getLoginEmailPassword() {
		String loginEmailPassword = (String)properties.getProperty("LOGINEMAILPASSWORD");
		if (loginEmailPassword != null)
			return loginEmailPassword;
		else
			throw new RuntimeException("LOGINEMAILPASSWORD not specified in the Configuration.properties file.");
	}

	public String getToEmailUserName() {
		String toEmailUserName = (String)properties.getProperty("TOEMAILUSERNAME");
		if (toEmailUserName != null)
			return toEmailUserName;
		else
			throw new RuntimeException("TOEMAILUSERNAME not specified in the Configuration.properties file.");
	}
	
	public void setID(String id){
		String idValue = (String)properties.getProperty("ID");
		if (idValue == null)
			throw new RuntimeException("ID not specified in the Configuration.properties file.");
		else{
			try {
				properties.setProperty("ID", id);
				properties.save();
			} catch (ConfigurationException e) {
				log.error(e.getMessage(), e);
			}
		}
	}
	
	public String getID() {
		String id = (String)properties.getProperty("ID");
		if (id!= null)
			return id;
		else
			throw new RuntimeException("ID not specified in the Configuration.properties file.");
	}
	
	public String getChromeDriverPath() {
		String chormeDriverPath = (String)properties.getProperty("CHROME_DRIVER_PATH");
		if (chormeDriverPath != null)
			return chormeDriverPath;
		else
			throw new RuntimeException("CHROME_DRIVER_PATH not specified in the Configuration.properties file.");
	}
	
	public String getFirefoxDriverPath() {
		String fireFoxDriverPath = (String)properties.getProperty("FIREFOX_DRIVER_PATH");
		if (fireFoxDriverPath != null)
			return fireFoxDriverPath;
		else
			throw new RuntimeException("FIREFOX_DRIVER_PATH not specified in the Configuration.properties file.");
	}
	
	
	public String getDriverType() {
		String driverType = (String)properties.getProperty("DRIVERTYPE");
		if (driverType != null)
			return driverType;
		else
			throw new RuntimeException("DRIVERTYPE not specified in the Configuration.properties file.");
	}
	
	public int getTimeOutPeriod() {
		String stringTimeOutPeriod = (String) properties.getProperty("TIME_OUT_PERIOD");
		int timeOutPeriod=Integer.parseInt(stringTimeOutPeriod);
		if (timeOutPeriod != 0)
			return timeOutPeriod;
		else
			throw new RuntimeException("TIME_OUT_PERIOD not specified in the Configuration.properties file.");
	}
	
	public String getImageLocation() {
		String imageLocation = (String) properties.getProperty("IMAGE_LOCATION");
		if (imageLocation != null)
			return imageLocation;
		else
			throw new RuntimeException("IMAGE_LOCATION not specified in the Configuration.properties file.");
	}

	public String getImageExtention() {
		String imageExtention = (String) properties.getProperty("IMAGE_EXTENSION");
		if (imageExtention != null)
			return imageExtention;
		else
			throw new RuntimeException("IMAGE_EXTENSION not specified in the Configuration.properties file.");
	}
	
	public boolean getScreenShot() {
		String stringscreenShot = (String) properties.getProperty("GET_SCREEN_SHOTS");
		boolean screenShot=false;
		if (stringscreenShot != null){
			if(stringscreenShot.equalsIgnoreCase("true"))
				screenShot=true;
			
			return screenShot;
		}
		else
			throw new RuntimeException("GET_SCREEN_SHOTS not specified in the Configuration.properties file.");
	}
	
	public boolean getSubFolderCreation() {
		String stringSubFolderCreation = (String) properties.getProperty("ADD_SUB_FOLDER_CREATION");
		boolean subFolderCreation=false;
		if (stringSubFolderCreation != null){
			if(stringSubFolderCreation.equalsIgnoreCase("true"))
				subFolderCreation=true;
			
			return subFolderCreation;
		}
		else
			throw new RuntimeException("ADD_SUB_FOLDER_CREATION not specified in the Configuration.properties file.");
	}
	
	public String getFolderSeperator() {
		String folderSeperator = (String) properties.getProperty("FOLDER_SEPERATOR");
		if (folderSeperator != null)
			return folderSeperator;
		else
			throw new RuntimeException("FOLDER_SEPERATOR not specified in the Configuration.properties file.");
	}
	
	public boolean getFailMsgAllow() {
		String StringFailMsgAllow = (String) properties.getProperty("FAILSCENARIOMESSAGEALLOW");
		boolean failMsgAllow=false;
		if (StringFailMsgAllow != null){
			if(StringFailMsgAllow.equalsIgnoreCase("true"))
				failMsgAllow=true;
			
			return failMsgAllow;
		}
		else
			throw new RuntimeException("FAILSCENARIOMESSAGEALLOW not specified in the Configuration.properties file.");
	}
	
	public String getTestUserName() {
		String testUserName = (String)properties.getProperty("TESTUSERNAME");
		if (testUserName != null)
			return testUserName;
		else
			throw new RuntimeException("TESTUSERNAME not specified in the Configuration.properties file.");
	}

	public String getTestUserPassword() {
		String testUserPassword = (String)properties.getProperty("TESTUSERPASSWORD");
		if (testUserPassword != null)
			return testUserPassword;
		else
			throw new RuntimeException("TESTUSERPASSWORD not specified in the Configuration.properties file.");
	}
	
	public String getFtpIp() {
		String FtpIp = (String)properties.getProperty("FTPIP");
		if (FtpIp != null)
			return FtpIp;
		else
			throw new RuntimeException("TESTUSERPASSWORD not specified in the Configuration.properties file.");
	}
	
	public String getFtpUserName() {
		String ftpUserName = (String)properties.getProperty("FTPUSERNAME");
		if (ftpUserName != null)
			return ftpUserName;
		else
			throw new RuntimeException("FTPUSERNAME not specified in the Configuration.properties file.");
	}
	
	public String getFtpPassword() {
		String ftpPassword = (String)properties.getProperty("FTPPASSWORD");
		if (ftpPassword != null)
			return ftpPassword;
		else
			throw new RuntimeException("FTPPASSWORD not specified in the Configuration.properties file.");
	}
			
	public String getUploadFileName() {
		String uploadFileName = (String)properties.getProperty("UPLOADFILENAME");
		if (uploadFileName != null)
			return uploadFileName;
		else
			throw new RuntimeException("UPLOADFILENAME not specified in the Configuration.properties file.");
	}
	
	public String getToUploadFilePath() {
		String toUploadFilePath = (String)properties.getProperty("TOUPLOADFILEPATH");
		if (toUploadFilePath != null)
			return toUploadFilePath;
		else
			throw new RuntimeException("TOUPLOADFILEPATH not specified in the Configuration.properties file.");
	}
	
	public String getExecuteScriptPath() {
		String executeScriptPath = (String)properties.getProperty("EXECUTESCRIPTPATH");
		if (executeScriptPath != null)
			return executeScriptPath;
		else
			throw new RuntimeException("EXECUTESCRIPTPATH not specified in the Configuration.properties file.");
	}
	
	public String getVbsScreeshotPath() {
		String toUploadFilePath = (String)properties.getProperty("VBS_SCREENSHOT_PATH");
		if (toUploadFilePath != null)
			return toUploadFilePath;
		else
			throw new RuntimeException("VBS_SCREENSHOT_PATH not specified in the Configuration.properties file.");
	}
	
}
