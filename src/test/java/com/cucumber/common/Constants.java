package com.cucumber.common;


public class Constants {
	// Configuration settings
	// Configuration settings
	public final static String PRODUCT_FOLDER = "\\IADMIN"; // IPAY, ICORE,
															// IADMIN, ICARD
	public final static String FOLDER_SEPERATOR = "\\";
	public final static String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
	// public final static String
	// HOST_PAGE_LINK="http://192.168.1.87:8080/iAdmin/";
	// public final static String
	// HOST_PAGE_LINK="http://192.168.1.83:8081/iAdmin_Cuc/";
	// public final static String
	// HOST_PAGE_LINK="http://192.168.1.51:8080/iAdmin/";
	// public final static String HOST_PAGE_LINK =
	// "http://192.168.11.21:16009/iAdmin/";
	// public final static String
	// HOST_PAGE_LINK="http://192.168.1.4:11080/iAdmin/";
	public final static String HOST_PAGE_LINK = "http://192.168.1.4:8080/iAdmin/";
	public static final String LOGIN_PAGE = HOST_PAGE_LINK + "index_bankadmin.html";
	// public static final String LOGIN_PAGE = HOST_PAGE_LINK +
	// "index_bankadmin.jsp";
	public final static String IMAGE_EXTENSION = ".png";
	public final static String IMAGE_LOCATION = "config\\SCREENS_SHOTS\\";
	public final static String TEST_BROWSER_FIRE_FOX = "N";
	public final static String TEST_BROWSER_CHROME = "Y";
	public final static String CHROME_DRIVER_PATH = "config\\chromedriver.exe";
	public final static String LOG_PROPERTY_FILE_PATH = "config\\log4j.properties";
	public final static String DATA_FILE_PATH = "config\\Data.xls";
	public final static String REPORT_DATA_FILE_PATH = "config\\ReportData.xls";
	public final static String REPORT_DATA_FILE_NAME ="ReportData.xls";
	public final static String WAMPSERVER_PATH="C:\\wamp\\";
	public final static String FIRE_FOX_PROFILE_LOAD = "Y";
	public final static boolean DATA_XL_FILE_ALLOW=true;
	// public final static String FEATURE_FILE_LOCATION="src/test/resources";

	public final static String FEATURE_FILE_LOCATION = "src\\test\\resources\\";
	/*
	 * public final static String FEATURE_FILE_LOCATION = "C:\\config" +
	 * PRODUCT_FOLDER + "\\featurefile";
	 */
	public final static String TARGET_OUTPUT = "config" + PRODUCT_FOLDER + "\\output\\";
	// public final static String
	// TARGET_DETAIL_HTML="E:\\workspaces-6786\\Cucumber\\testCucumber\\WebContent\\htmlDetails\\";

	public final static String SUCCESS_MESSAGE = "success";
	public final static String ERROR_MESSAGE = "error";

	// public final static String
	// MESSAGE_XPATH="/html/body/div[2]/div[3]/div/fieldset/h2";
	// public final static String
	// MESSAGE_OK_BUTTON_XPATH="/html/body/div[2]/div[3]/div/fieldset/ol/li[2]/a/button";
	public final static String MAIN_ICARD_ICON_XPATH = "/html/body/div[2]/nav/div/a[2]/div/img";
	public final static String PRODUCT_CREATION_FINAL_RESULT_IMAGE_NAME = "Creation_result";
	public final static String COMMENT_APPROVE = "Approving BY cucumber Automation";

	// Types of elements
	public final static String TYPE_TEXT = "T";
	public final static String TYPE_DROP_DOWN = "D";
	public final static String TYPE_CHECK_BOX = "C";
	public final static String TYPE_RADIO_BOX = "R";

	public final static boolean GET_SCREEN_SHOTS = true;
	public final static boolean ADD_SUB_FOLDER_CREATION = true; // This will put
																// all images in
																// relevant sub
																// folders
																// according to
																// the module
	public final static String SUB_LOGIN = "Login" + FOLDER_SEPERATOR;
	public final static String SUB_CUSTOMER = "Customers" + FOLDER_SEPERATOR;
	public final static String SUB_PRODUCT_SVC = "Products_SVC" + FOLDER_SEPERATOR;
	public final static String SUB_PRODUCT_DEBIT = "Products_DEBIT" + FOLDER_SEPERATOR;
	public final static String SUB_PRODUCT_CREDIT = "Products_CREDIT" + FOLDER_SEPERATOR;
	public final static String SUB_DEBIT_CREATION_NON = "Debit_Non" + FOLDER_SEPERATOR;
	public final static String SUB_CAll_INQUERY = "Call_Inquery" + FOLDER_SEPERATOR;
	public final static String SUB_EMBOSS_FILE_MANAGEMENT = "EmbossFile" + FOLDER_SEPERATOR;
	public final static String SUB_EMBOSS_CARD_INQUERY = "CardInquery" + FOLDER_SEPERATOR;
	public final static String SUB_SUSPEND = "Suspend" + FOLDER_SEPERATOR;
	public final static String SUB_REVOKE = "Revoke" + FOLDER_SEPERATOR;
	public final static String SUB_CLOSE = "Close" + FOLDER_SEPERATOR;
	public final static String SUB_SVC_MANAGMENT = "Svc_Manage" + FOLDER_SEPERATOR;
	public final static String SUB_TOP_UP = "Topup" + FOLDER_SEPERATOR;
	public final static String SUB_TOP_UP_REJECTED = "Topup_Reject" + FOLDER_SEPERATOR;
	public final static String SUB_CARDINVENTORY = "CardInventory" + FOLDER_SEPERATOR;
	public final static String SUB_CardUtilityManagement = "CardUtilityManagement" + FOLDER_SEPERATOR;
	public final static String SUB_CardRenewals = "CardRenewals" + FOLDER_SEPERATOR;
	public final static String SUB_ADD_BUSINESS_TYPE = "AddBusinessType" + FOLDER_SEPERATOR;
	public final static String SUB_Merchant_Category_Mgmt = "MerchantCategoryMgmt" + FOLDER_SEPERATOR;
	public final static String SUB_Blacklisted_CardManagement = "BlacklistedCardManagement" + FOLDER_SEPERATOR;
	public final static String SUB_ADD_ON_US_BIN = "ONUSBIN" + FOLDER_SEPERATOR;
	public final static String SUB_ADMIN = "Admin" + FOLDER_SEPERATOR;
	public final static String SUB_ADMIN_USER = "AdminuSER" + FOLDER_SEPERATOR;
	public final static String SUB_BILETERAL_MGT = "BilateralLimit" + FOLDER_SEPERATOR;
	public final static String SUB_MULTILETERAL_MGT = "MultilateralLimit" + FOLDER_SEPERATOR;
	public final static String ATM_LOCATION_MGT = "ATMLocationManagement" + FOLDER_SEPERATOR;

	public final static String SUB_ACCOUNT = "DebitAccount" + FOLDER_SEPERATOR;
	public final static String SUB_DEBIT_CHANGE_LIMIT = "DebitChangeLimit" + FOLDER_SEPERATOR;
	public final static String SUB_SVC_CREATION_PERSON = "SVC_PERSON" + FOLDER_SEPERATOR;
	public final static String SUB_TRXN_INQUIRY = "Trxn_Inquiry" + FOLDER_SEPERATOR;

	public final static long TIME_OUT_PERIOD = 30;

	// DB Details
	public final static String SWTUSERNAME = "usl_d_swt";
    public final static String SWTPASSWORD = "usl_d_swt";
    public final static String BKNUSERNAME = "usl_d_bkn";
    public final static String BKNPASSWORD = "usl_d_bkn";
    public final static String IPGUSERNAME = "usl_d_ipy";
    public final static String IPGPASSWORD = "usl_d_ipy";
	public final static String IP = "jdbc:postgresql://192.168.21.101:5002/usld";

	
	public final static String DBType ="oracle";
	public final static String iSuite4_IP="jdbc:oracle:thin:@192.168.1.131:1521:devdb";
	public final static String DBNAME="devdb";
	public final static String CRDUSERNAME="IBL_D_CRD";
	public final static String CRDPASSWORD="IBL_D_CRD";
	
	
	
	public final static String PRODUCT_FOLDER_REPORTS = "\\iReports";
	public final static String TARGET_OUTPUT_REPORTS = "config" + PRODUCT_FOLDER + "\\output\\";
	
}
