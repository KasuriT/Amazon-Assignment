package MiscFunctions;

import java.time.Duration;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import Config.ReadPropertyFile;

public class Constants {

	public static final String browser = "chrome";
	public static long implicitwait=10;

	public static WebDriverWait wait = new WebDriverWait(Helper.driver, Duration.ofSeconds(150, 1));
	
	public static ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);
	
	public static String url_login = config.url()+"/auth/sign-in";
	public static String url_fp = config.url()+"/auth/forgot-password";
	public static String url_home = config.url()+"/home";
	public static String url_user = config.url()+"/home/admin/users";
	public static String url_organization = config.url()+"/home/admin/organizations";
	public static String url_access = config.url()+"/home/admin/roles";
	public static String url_reportsManagement = config.url()+"/home/admin/reportroles";
	public static String url_alert = config.url()+"/home/admin/alerts";
	public static String url_agreementManagement = config.url()+"/home/admin/user-agreement";
	public static String url_companyProducts = config.url()+"/home/admin/product";
	public static String url_barcodeManagement = config.url()+"/home/admin/barcode-generation";
	public static String url_complexConfig = config.url()+"/home/admin/complex-cycling-config";
	public static String url_flockManagement = config.url()+"/home/admin/flock-management"; 
	public static String url_programManagement = config.url()+"/home/admin/program";
	public static String url_piperManagement = config.url()+"/home/admin/piper";
	public static String url_piperSoftware = config.url()+"/home/admin/piper-software-management";
	public static String url_piperConfiguration = config.url()+"/home/admin/piper-configuration";
	public static String url_dataTemplate = config.url()+"/home/metadata/dataformat";
	public static String url_dataUpload = config.url()+"/home/client/dataupload";
	public static String url_poultryManagement = config.url()+"/home/metadata/poultry";
	public static String url_reports = config.url()+"/home/reports/explore";

	public static String url_GmailSignin = "https://accounts.google.com/ServiceLogin?sacu=1&scc=1&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&osid=1&service=mail&ss=1&ltmpl=default&rm=false#identifier";
	
	public static String ReportFilePath = "./target/Reports";
	public static String ReportScreenshotPath = "/ScreenShots/ScreenShot";
	
	public static String api_login = config.api_url()+"/login";
	public static String api_announcement = config.api_url()+"/runfilelist";
	public static String api_FileUpload = config.api_url()+"/fileupload";
	public static String api_StartAssay = config.api_url()+"/startAssay?apiVersion=18.0";
	public static String api_RawImage = config.api_url()+"/rawimgupload?apiVersion=18.0";
	public static String api_FileRequest = config.api_url()+"/filerequest?apiVersion=17.0";
	public static String api_MPNSettingRequest = config.api_url()+"/mpnsettingsrequest?apiVersion=17.0";
	public static String api_UserListRequest = config.api_url()+"/userlistrequest?apiversion=18.0";
	public static String api_HeartBeat = config.api_url()+"/heartbeat?apiversion=17.0";
	public static String api_SystemLogs = config.api_url()+"/systemlogs";
	
	
	
	
	
//	public static String templateFileNameDS = "MetaData DataSecurity.xlsx";
	
	public static String RUNMODE_COL = "Runmode";
	public static String DATA_SHEET = "TestData";
	
	public static String SUITE_SHEET = "Suite";
	public static String SUITENAME_COL ="SuiteName";
	
	public static String TESTCASE_SHEET = "TestCases";
	public static String TESTCASE_COL = "TestCases";
	
	public static String RUNMODE_YES = "Y";
	public static String RUNMODE_NO = "N";
	
	public static String SUITE_XL_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\Suite.xlsx";
	public static String SUITE1_XL_PATH=System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\master.xlsx";
}
