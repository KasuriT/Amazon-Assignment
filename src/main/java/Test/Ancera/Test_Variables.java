package Test.Ancera;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Models.APIModel;
import Models.APIVersionModel;
import Models.AccessModel;
import Models.AgreementManagementModel;
import Models.AlertManagementModel;
import Models.AuditLogModel;
import Models.AutoLoginModel;
import Models.CoccidiaModel;
import Models.CoccidiaTimelineModel;
import Models.DataSecurityModel;
import Models.DataTemplateModel;
import Models.DataUploadModel;
import Models.FlockRegistrationModel;
import Models.InstallationRunModel;
import Models.LoginModel;
import Models.NormalIngestionModel;
import Models.OrgModel;
import Models.PAModel;
import Models.PiperConfigurationModel;
import Models.PiperManagementModel;
import Models.PiperSoftwareModel;
import Models.PoultryManagementModel;
import Models.ProfileModel;
import Models.RMModel;
import Models.RawImageCompareCountModel;
import Models.RawImageModel;
import Models.SalmonellaModel;
import Models.SitesLogModel;
import Models.StartAssayModel;
import Models.UserModel;

public class Test_Variables {

	public static ExtentTest test;
	public static ExtentTest node;
	public static ExtentTest preconditions; 
	public static ExtentTest steps; 
	public static ExtentTest results; 
	public static ExtentSparkReporter spark;
	public static ExtentReports extent;

	//public static String login_email = "junnaid0005@gmail.com";
	public static String login_email = "junaid.alam@analytics.com.pk";
	public static String login_password = "junaid12345";	
	//public static String login_email = "Faraz@tenx.ai";
	//public static String login_password = "tenx01tenx";
	
	public static String forgotPassword_email = "ancera.test.user100@gmail.com";
	public static String forgotPassword_password = "ancera123";
	public static String forgotPasswordSecurityEmail = "junnaid0005@gmail.com";
	public static String forgotPassword_resetPassword = "ancera123";
	
	public static String createUserEmail = "ancera.test.user101@gmail.com";
	public static String createUserPassword = "ancera123";
	public static String createUserSecurityEmail = "ancera.test.user100@gmail.com";
	
	public static String piperId = "PSN0023";
	public static String piperPassword = "piperdemo";
	
	public static String fileDownloadPath = "C:\\Users\\User\\Downloads";
	
	public static String fileAbsolutePath = "D:\\Eclipse-WorkSpace\\eclipse-workspace\\IEQACode\\";
	
	static DateFormat dateFormat = new SimpleDateFormat("MM_dd_HH_mm");
	static Date date1 = new Date();
	public static String date = dateFormat.format(date1);

	static DateFormat dateFormat0 = new SimpleDateFormat("mmss");
	static Date date10 = new Date();
	public static String date0 = dateFormat0.format(date10);
	
	static DateFormat dateFormat5 = new SimpleDateFormat("ss");
	static Date date1000 = new Date();
	public static String date1001 = dateFormat5.format(date1000);
	
	static DateFormat dateFormat50 = new SimpleDateFormat("MM-dd-yyyy");
	static Date date5000 = new Date();
	public static String dateMMDDYYYY = dateFormat50.format(date5000);
	
	static DateFormat dateFormat60 = new SimpleDateFormat("yyyyMMdd");
	static Date date6000 = new Date();
	public static String dateYYYYMMDD = dateFormat60.format(date6000);
	
	static DateFormat dateFormat501 = new SimpleDateFormat("MM/dd/yyyy");
	static Date date50001 = new Date();
	public static String dateMMDDYYYY1 = dateFormat501.format(date50001);
	
	static DateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	static Date date11 = new Date();
	public static String date100 = dateFormat1.format(date11);
	
	static DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
	static Date dateR = new Date();
	public static String dateRIY = dateFormat2.format(dateR);
	
	static DateFormat dateFormat3 = new SimpleDateFormat("HH:mm:ss");
	static Date dateRI = new Date();
	public static String dateRIT = dateFormat3.format(dateRI);
	
	public static String PreConditions = "Pre-Conditions";
	public static String Steps = "Steps";
	public static String Results = "Results";
	
	public static String userFirstName = "QA";
	
	//////////////////////////////////////////////////////Login Screen Variables////////////////////////////////////////////////////////////	

	public static ArrayList<LoginModel> lstLogin = new ArrayList<>(
			Arrays.asList(
					new LoginModel(login_email,"junaid","1. Enter valid username in Email Address field ("+login_email+")",  "2. Enter invalid password in Password field", "AN-Login-01: Verify user is not able to login with valid username and invalid password", "This test will verify that user is not able to login with valid username and invalid password", Constants.url_login, "User receives an alert message 'Sorry, we don't recognize these credentials.'", "User logged into the application", true, false),
					new LoginModel("junaid.alam@analytics.com", login_password, "1. Enter invalid username in Email Address field", "2. Enter valid password in Password field", "AN-Login-02: Verify user is not able to login with invalid username and valid password", "This test will verify that user is not able to login with invalid username and valid password", Constants.url_login, "User receives an alert message 'Sorry, we don't recognize these credentials.'", "User logged into the application", true, false),
					new LoginModel("junaid.alam@analytics.com", "junaid", "1. Enter invalid username in Email Address field", "2. Enter invalid password in Password field", "AN-Login-03: Verify user is not able to login with invalid username and invalid password", "This test will verify that user is not able to login with invalid username and invalid password", Constants.url_login, "User receives an alert message 'Sorry, we don't recognize these credentials.'", "User logged into the application", true, false),
					new LoginModel("", "", "1. Enter empty username in Email Address field in Email Address field", "2. Enter empty password in Password field", "AN-Login-04: Verify user is not able to login with empty username and empty password",  "This test will verify that user is not able to login with empty username and empty password", Constants.url_login, "User receives an alert message 'Sorry, we don't recognize these credentials.'", "User logged into the application", true, false),
					new LoginModel("", login_password, "1. Enter empty username in Email Address field", "2. Enter valid password in Password field", "AN-Login-05: Verify user is not able to login with empty username and valid password",  "This test will verify that user is not able to login with empty username and valid password",Constants.url_login, "User receives an alert message 'Sorry, we don't recognize these credentials.'", "User logged into the application", true, false),
					new LoginModel("junaid.alam@analytics.com", "", "1. Enter valid username in Email Address field ("+login_email+")", "2. Enter empty password in Password field", "AN-Login-06: Verify user is not able to login with valid username and empty password", "This test will verify that user is not able to login with valid username and empty password",  Constants.url_login, "User receives an alert message 'Sorry, we don't recognize these credentials.'", "User logged into the application", true, false),
					new LoginModel(login_email, login_password, "1. Enter valid username in Email Address field ("+login_email+")", "2. Enter valid password in Password field", "AN-Login-07: Verify user is able to login with valid username and valid password", "This test will verify that user is able to login with valid username and valid password", Constants.url_home, "User logged in successfully to home page", "User did not logged into the application", false, true)));
	
	/////////////////////////////////////////////////////End Login Screen Variables////////////////////////////////////////////////////////
	
	
	//////////////////////////////////////////////////////Forgot Password Screen Variables/////////////////////////////////////////////////	
	
	public static ArrayList<String> lstFpEmail = new ArrayList<>(
			Arrays.asList("junaid.alam@analytics.pk", 
					forgotPassword_email));
	
	public static ArrayList<String> lstFpUrl = new ArrayList<>(
			Arrays.asList(Constants.url_fp, 
					Constants.url_login));
	
	public static ArrayList<String> lstFpAlertMessages = new ArrayList<>(
			Arrays.asList("User is not registered.", 
					"Please check your e-mail for instructions."));  
	
	public static ArrayList<String> lstFpTestCase = new ArrayList<>(
			Arrays.asList("AN-FP-01: Verify user is not able to reset password with user that is not registered", 
					"AN-FP-02: Verify user is able to reset password for valid user"));
	
	public static ArrayList<String> lstFpTestCaseDescription = new ArrayList<>(
			Arrays.asList("This test case will verify that user is not able to reset password with user that is not registered", 
					"This test case will verify that user is able to reset password for valid user"));

	public static ArrayList<String> lstFpPassMessage = new ArrayList<>(
			Arrays.asList("User received alert message that 'User is not registered.' successfully", 
					"User received alert message that 'Please check your e-mail for instructions.' successfully"));
	
	public static ArrayList<String> lstFpFailMessage = new ArrayList<>(
			Arrays.asList("User did not received alert message that 'User is not registered.'", 
					"User did not received alert message that 'Please check your e-mail for instructions.'"));
	
	public static ArrayList<String> lstFpStep1 = new ArrayList<>(
			Arrays.asList("2. Enter invalid email for which account does not exist into Email Address field", 
					"2. Enter valid email for which account exists into Email Address field"));
	
	//////////////////////////////////////////////////////End Forgot Passwprd Screen Variables////////////////////////////////////////////////
	
	
	///////////////////////////////////////////////////////User Management Screen Variables///////////////////////////////////////////////////	

	public static ArrayList<UserModel> lstUserMandatoryCheck = new ArrayList<>(
			Arrays.asList(
					new UserModel("", "", false, "", true, "", false, false, false, false, false, "Leave all fields empty and click on next button", "User was not able to navigate to step 2 of create user successfully", "User was able to navigate to step 2 of create user", "AN-UM-03: Verify mandatory field check with all fields empty", "This test case will verify that user cannot create user with mandatory fields empty"),
					new UserModel("firstname", "", true, "", true, "", false, false, false, false, false, "Leave Last Name empty and click on next button", "User was not able to navigate to step 2 of create user successfully", "User was able to navigate to step 2 of create user", "AN-UM-04: Verify mandatory field check with 1 field empty", "This test case will verify that user cannot create user leaving 1 mandatory field empty"),
					new UserModel("", "", false, "alpha", true, "", false, false, false, false, false, "Enter alpha data in phone number field", "User was not able to enter alpha data in phone number field successfully", "User was able to enter alpha data in phone number field successfully", "AN-UM-05: Verify user cannot enter alpha data in phone number field", "This test case will verify that user cannot enter alpha data in phone number field"),
					new UserModel("firstname", "lastname", false, "", true, "", false, false, true, false, false, "Leave all fields empty at page", "User was not able to navigate to step 3 of create user successfully", "User was not able to navigate to step 3 of create user successfully", "AN-UM-06: Verify user cannot proceed to step 3 of create user after leaving all mandatory fields empty in screen 2", "This test case will verify that user cannot proceed to next step after leaving all mandatory fields"),
					new UserModel("firstname", "lastname", false, "", true, "email@ancera.", true, true, true, false, false, "Enter invalid email", "User was not able to navigate to step 2 of create user successfully", "User was not able to navigate to step 2 of create user successfully", "AN-UM-07: Verify user can proceed to step 3 with invalid email", "This test case will verify that user can proceed to next step with invalid email"),	
					new UserModel("firstname", "lastname", false, "", true, "email@ancera.com", true, true, false, false, true, "Enter valid data in all mandatory fields", "User was able to navigate to step 2 of create user successfully", "User was not able to navigate to step 2 of create user successfully", "AN-UM-08: Verify user can proceed to step 2 of create user after filling all mandatory fields", "This test case will verify that user can proceed to next step after filling all mandatory fields")));	

	public static ArrayList<String> lstUserAlertMessages = new ArrayList<>(
			Arrays.asList("New user created.", 
					"User details updated.",
					"User details deleted."));

	public static ArrayList<UserModel> lstUserSearch = new ArrayList<>();
	public static ArrayList<UserModel> lstUserWildcardSearch = new ArrayList<>();
	public static ArrayList<UserModel> lstUserDateSearch = new ArrayList<>();
	public static ArrayList<UserModel> lstUserDateEnter = new ArrayList<>();
	public static ArrayList<UserModel> lstUserRowCount = new ArrayList<>();
	public static ArrayList<UserModel> lstUserPagination = new ArrayList<>();
	public static ArrayList<UserModel> lstUserSorting = new ArrayList<>();
	public static ArrayList<UserModel> lstUserFieldAccess = new ArrayList<>();
	public static ArrayList<UserModel> lstUserContexualCheck = new ArrayList<>();
	public static ArrayList<UserModel> lstUserLock = new ArrayList<>();
	public static ArrayList<UserModel> lstUserEdit = new ArrayList<>();
	public static ArrayList<UserModel> lstUserValidation = new ArrayList<>();
	
	public static String userCSVFileName = "Users Log - ";
	
	///////////////////////////////////////////////////////End User Management Screen Variables///////////////////////////////////////////////////

	
	////////////////////////////////////////////////////Organization Management Screen Variables///////////////////////////////////////////////////
//	
//	public static ArrayList<OrgModel> lsttest = new ArrayList<>(
//			Arrays.asList(
//					new OrgModel(Test_Variables.lstOrgSearch)
//));
//	
	
	
	
	public static ArrayList<OrgModel> lstOrgMandatoryCheck = new ArrayList<>(
			Arrays.asList(
					new OrgModel(false, "", false, "", "", "Leave all mandatory fields empty", true,
							"Click on next button", "",  false, "AN-OM-03: Verify user cannot create organization with leaving all fields empty", 
							"This testcase will verify that user cannot create organization with leaving all fields empty", "User was not able to create organization with all fields empty successfully", "User was able to create organization with all feilds empty"),

					new OrgModel( false, "Ancera Test Org", false, "", "", "Enter only org name", true,
							"Click on next button", "",  false, "AN-OM-04: Verify user cannot create organization with only filling org name field",
							"This testcase will verify that user cannot create organization with only filling org name field", "User was not able to create organization with only filling org name field", "User was able to create organization with only filling org name field"),

					new OrgModel(false, "", false, "", "ancera"+date0+"@ancera.com", "Enter only email", true,
							"Click on next button", "",  false, "AN-OM-05: Verify user cannot create organization with only filling email field",
							"This testcase will verify that user cannot create organization with only filling email field", "User was not able to create organization with only filling email field", "User was able to create organization with only filling email field"),

					new OrgModel( false, "", true, "", "", "Enter phone code leaving phone number empty", true,
							"Click on next button", "",  false, "AN-OM-06: Verify user cannot create organization with only filling phone code field", 
							"This testcase will verify that user cannot create organization with only filling phone code field", "User was not able to create organization with only filling phone code field successfully", "User was able to create organization with only filling phone number field"),

					new OrgModel( true, "", false, "", "", "Select any value from Org Type dropdown", true,
							"Click on next button", "",  false, "AN-OM-07: Verify user cannot create organization with only filling org type field", 
							"This testcase will verify that user cannot create organization with only filling org type field", "User was not able to create organization with only filling org type field successfully", "User was able to create organization with only filling org type field"),

					new OrgModel( true, "Ancera Test Org", false, "", "", "Select only Org Type and Name", true,
							"Click on next button", "",  false, "AN-OM-08: Verify user cannot create organization with only filling Org Type and Name field", 
							"This testcase will verify that user cannot create organization with only filling Org Type and Name field", "User was not able to create organization with only filling Org Type and Name field successfully", "User was able to create organization with only filling Org Type and Name field"),

					new OrgModel( false, "", false, "", "ancera"+date0+"@ancera.com", "Select only Org Type and Email", true,
							"Click on next button", "",  false, "AN-OM-09: Verify user cannot create organization with only filling Org Type and Email field", 
							"This testcase will verify that user cannot create organization with only filling Org Type and Email field", "User was not able to create organization with only filling Org Type and Email field successfully", "User was able to create organization with only filling Org Type and Email field"),

					new OrgModel( true, "Ancera Test Org", true, "", "ancera"+date0+"@ancera.com", "Select Org Type, Country Code", true,
							"Click on next button", "",  false, "AN-OM-10: Verify user cannot create organization with only filling Org Type and Country Code field",
							"This testcase will verify that user cannot create organization with only filling Org Type and Country Code field", "User was not able to create organization with only filling Org Type and Country Code field successfully", "User was able to create organization with only filling Org Type and Country Code field"),

					new OrgModel( true, "Ancera Test Org", false, "", "ancera"+date0+"@ancera.com", "Select Org Type, Name and Email", true,
							"Click on next button", "",  false, "AN-OM-11: Verify user cannot create organization with only filling Org Type, Email and Name field", 
							"This testcase will verify that user cannot create organization with only filling Org Type, Email and Name field", "User was not able to create organization with only filling Org Type, Email and Name field successfully", "User was able to create organization with only filling Org Type, Email and Name field"),

					new OrgModel( true, "Ancera Test Org", true, "6666666666", "", "Fill all fields and leave only email field empty", true,
							"Click on next button", "",  false, "AN-OM-12: Verify user cannot create organization with leaving only email field empty",
							"This testcase will verify that user cannot create organization with leaving only email field empty", "User was not able to create organization with leaving only email field empty successfully", "User was able to create organization with leaving only email field empty"),

					new OrgModel( true, "", true, "6666666666", "ancera"+date0+"@ancera.com", "Fill all fields and leave only name field empty", true,
							"Click on next button", "",  false, "AN-OM-13: Verify user cannot create organization with leaving only name field empty", 
							"This testcase will verify that user cannot create organization with leaving only name field empty", "User was not able to create organization with leaving only name field empty successfully", "User was able to create organization with leaving only name field empty"),

					new OrgModel( true, "Test Organization"+date0, true, "6666666666", "ancera"+date0+"@ancera.com", "Fill all fields ", false,
							"Click on next button", "600000",  true, "AN-OM-15: Verify max user limit in Max Users Field",
							"This testcase will verify max user limit in Max Users Field", "User was not able to create organization after exceeding max user limit in Max Users Field", "User was able to create organization after exceeding max user limit in Max Users Field"),
					
					
					new OrgModel( true, "Test Organization"+date0, true, "6666666666", "ancera"+date0+"@ancera.com", "Fill all fields ", false,
							"Click on next button", "",  true, "AN-OM-16: Verify user navigates to step 2 of create organization popup after filling valid data in all fields",
							"This testcase will verify that user navigates to step 2 of create organization popup after filling valid data in all fields", "User was able to navigate to step 2 of create organization popup after filling valid data in all fields successfully", "User was able to navigate to step 2 of create organization popup after filling valid data in all fields")
					));

	public static ArrayList<String> lstOrganizationCreate = new ArrayList<>(
			Arrays.asList("Test Organization"+date0,
					"(666) 666-6666",
					"ancera",  //invalid email
					"ancera"+date0+"@ancera.com",  //valid email
					"100"));

	public static ArrayList<String> lstOrgAlertMessages = new ArrayList<>(
			Arrays.asList("New organization has been created successfully", 
					"Organization details updated successfully",
					"New site created.",
					"Site details updated.",
					"Site details deleted successfully.",
					"Organization details deleted successfully."));
	
	public static ArrayList<OrgModel> lstOrgBulkSite = new ArrayList<>(
			Arrays.asList(
					new OrgModel("AN-OM-02: Verify user cannot upload pdf file", "This test case will verify that user can upload pdf file", "User was able to upload pdf file successfully", "User was not able to upload pdf file", "/EULA/sample.pdf", "PNG file", "New user agreement created."),
					new OrgModel("AN-OM-03: Verify user cannot upload docx file", "This test case will verify that user cannot upload docx file", "User was not able to upload docx file successfully", "User was able to upload docx file", "/EULA/sample.docx", "DOCX file", "Please select pdf file."),
					new OrgModel("AN-OM-04: Verify user cannot upload xlsx file", "This test case will verify that user cannot upload xlsx file", "User was not able to upload xlsx file successfully", "User was able to upload xlsx file", "/EULA/sample.xlsx", "XLSX file", "Please select pdf file."),
					new OrgModel("AN-OM-05: Verify user cannot upload csv file", "This test case will verify that user cannot upload csv file", "User was not able to upload csv file successfully", "User was able to upload csv file", "/EULA/sample.csv", "CSV file", "Please select pdf file."),
					new OrgModel("AN-OM-06: Verify user cannot upload png file", "This test case will verify that user cannot upload png file", "User was not able to upload png file successfully", "User was able to upload png file", "/EULA/sample.png", "PNG file", "Please select pdf file.")
					));
	
	public static ArrayList<OrgModel> lstOrgBulkSiteUpload = new ArrayList<>();
	
	public static ArrayList<OrgModel> lstOrgSearch = new ArrayList<>();
	public static ArrayList<OrgModel> lstOrgWildcardSearch = new ArrayList<>();
	public static ArrayList<OrgModel> lstOrgDateSearch = new ArrayList<>();
	public static ArrayList<OrgModel> lstOrgDateEnter = new ArrayList<>();
	public static ArrayList<OrgModel> lstOrgRowCount = new ArrayList<>();
	public static ArrayList<OrgModel> lstOrgPagination = new ArrayList<>();
	public static ArrayList<OrgModel> lstOrgSorting = new ArrayList<>();
	public static ArrayList<OrgModel> lstOrgFieldAccess = new ArrayList<>();
	public static ArrayList<OrgModel> lstOrgContexualCheck = new ArrayList<>();
	public static ArrayList<OrgModel> lstOrgLock = new ArrayList<>();
	public static ArrayList<OrgModel> lstOrgEdit = new ArrayList<>();
	public static ArrayList<OrgModel> lstOrgValidation = new ArrayList<>();
	
	public static String orgCSVFileName = "Organizations Log - ";

	////////////////////////////////////////////////////End Organization Management Screen Variables////////////////////////////////////////////
	
	
	
	////////////////////////////////////////////////////////Access Management Screen Variables//////////////////////////////////////////////////
	
	public static ArrayList<String> lstAccessCreate = new ArrayList<>(
			Arrays.asList("Administrator"+date0, 
					"Role Description"));
	
	
	public static ArrayList<String> lstAccessAlertMessages = new ArrayList<>(
			Arrays.asList("New Reporting role created.", 
					"Role has been updated successfully."));
	
	public static ArrayList<AccessModel> lstUserManagementAccessRole = new ArrayList<>();

	//////////////////////////////////////////////////////End Access Management Screen Variables////////////////////////////////////////////////


	/////////////////////////////////////////////////////Agreement Management Variables////////////////////////////////////////////////////////
	public static ArrayList<String> lstAgreemmentManagementFileName = new ArrayList<>(
			Arrays.asList("Agreement.pdf", 
					"file"+date0+".pdf",
					"Agreementfile"+date0+".pdf"));
	
	public static ArrayList<AgreementManagementModel> lstAgreementManagement = new ArrayList<>(
			Arrays.asList(
					new AgreementManagementModel("AN-License-02: Verify user can upload pdf file", "This test case will verify that user can upload pdf file", "User was able to upload pdf file successfully", "User was not able to upload pdf file", "/EULA/"+lstAgreemmentManagementFileName.get(0), "PDF file", "New user agreement created."),
					new AgreementManagementModel("AN-License-03: Verify user cannot upload docx file", "This test case will verify that user cannot upload docx file", "User was not able to upload docx file successfully", "User was able to upload docx file", "/EULA/sample.docx", "DOCX file", "Please select pdf file."),
					new AgreementManagementModel("AN-License-04: Verify user cannot upload xlsx file", "This test case will verify that user cannot upload xlsx file", "User was not able to upload xlsx file successfully", "User was able to upload xlsx file", "/EULA/sample.xlsx", "XLSX file", "Please select pdf file."),
					new AgreementManagementModel("AN-License-05: Verify user cannot upload csv file", "This test case will verify that user cannot upload csv file", "User was not able to upload csv file successfully", "User was able to upload csv file", "/EULA/sample.csv", "CSV file", "Please select pdf file."),
					new AgreementManagementModel("AN-License-06: Verify user cannot upload png file", "This test case will verify that user cannot upload png file", "User was not able to upload png file successfully", "User was able to upload png file", "/EULA/sample.png", "PNG file", "Please select pdf file.")
					));
	
	public static ArrayList<AgreementManagementModel> lstAgreementManagementCheckbox = new ArrayList<>(
			Arrays.asList(
					new AgreementManagementModel("/html/body/app-root/div/app-manage-user-license-component/div[1]/div[3]/div/div[2]/div[1]/div/div[1]/div", "/html/body/app-root/div/app-manage-user-license-component/div[1]/div[3]/div/div[2]/div[1]/div[1]/div[1]/div/input", "AN-License-18: Verify user can assign agreement at Organization level", "This test case will verify that user can assign agreement at Organization level", "Organization", "AN-License-19: Verify agreement is assigned to user at Organization Level", "This test case will verify that agreement is assigned to user at Organization Level", "", ""),
					new AgreementManagementModel("/html/body/app-root/div/app-manage-user-license-component/div[1]/div[3]/div/div[2]/div[1]/div[2]/div/div/ul/li/div[2]/div/div/table/tbody/tr[1]/td[1]/div", "/html/body/app-root/div/app-manage-user-license-component/div[1]/div[3]/div/div[2]/div[1]/div[2]/div/div/ul/li/div[2]/div/div/table/tbody/tr[1]/td[1]/div/input", "AN-License-20: Verify user can assign agreement at User level", "This test case will verify that user can assign agreement at User level", "User", "AN-License-21: Verify agreement is assigned to user at User Level", "This test case will verify that agreement is assigned to user at User Level", "", ""),
					new AgreementManagementModel("/html/body/app-root/div/app-manage-user-license-component/div[1]/div[3]/div/div[2]/div[1]/div[2]/div/div/ul/li/div/div[1]", "/html/body/app-root/div/app-manage-user-license-component/div[1]/div[3]/div/div[2]/div[1]/div[2]/div/div/ul/li/div[1]/div[1]/input", "AN-License-22: Verify user can assign agreement at Organization Type level", "This test case will verify that user can assign agreement at Organization Type level", "Organization Type", "AN-License-23: Verify agreement is assigned to user at Organization Type Level", "This test case will verify that agreement is assigned to user at Organization Type Level", "", "")
					));
	
	
	public static ArrayList<AgreementManagementModel> lstAgreementManagementDeactivate = new ArrayList<>(
			Arrays.asList(
					new AgreementManagementModel("No items found", "AN-License-32: Verify that assigned but deactivated agreement is not displayed in user agreement dropdown", "This testcase will verify that assigned but deactivated agreement is not displayed in user agreement dropdown", "The assigned but deactivated agreement was not displayed", "The assigned but deactivated agreement was displayed"),
					new AgreementManagementModel("", "AN-License-33: Verify that assigned and reactivated agreement is displayed in user agreement dropdown", "This testcase will verify that assigned and reactivated agreement is displayed in user agreement dropdown", "The assigned and reactivated agreement was displayed successfully", "The assigned and reactivated agreement was not displayed")
							));	
			

	
	//////////////////////////////////////////////////////End Agreement Management Variables///////////////////////////////////////////////////////
	
	
	/////////////////////////////////////////////////////Alert Management Screen////////////////////////////////////////////////////////////////
	
	public static ArrayList<AlertManagementModel> lstAlertCreate = new ArrayList<>();
	
	///////////////////////////////////////////////////End Alert Management Screen////////////////////////////////////////////////////////////////////
	
	
	//////////////////////////////////////////////////////Reports Management Screen Variables/////////////////////////////////////////////////////
	
	public static String RoleName = "Test Role - "+date0;
	//public static String RoleName = "Test Role - 2533";
		
	public static String ReportGroupName = "Test Report Group - "+date0;
	//public static String ReportGroupName = "new group";
	
	public static ArrayList<RMModel> lstRGMandatoryCheck = new ArrayList<>(
			Arrays.asList(
					new RMModel("", "", false, "Leave all fields empty", false, "AN-RM-14: Leave all fields empty and click on save button", "This test case wll verify that user cannot create report group without filling all mandatory fields", true, "The user was not able to create Report Group leaving all fields empty", "User was able to create a Report Group leaving all fields empty"),
					new RMModel("", "Lorem Ipsum", false, "Leave 2 fields empty", false, "AN-RM-15: Leave 2 field empty and click on save button", "This test case will verify that user is not able to create role with two field empty", true, "The user was not able to create Report Group leaving 2 fields empty", "User was able to create a Report Group leaving 2 fields empty"),
					new RMModel(ReportGroupName, "Group created by automation script", true, "Fill all fields", true, "AN-RM-16: User should be able to save Report Details", "This test case will verify that user is able to create new report group", false, "The user was able to create Report Group after entering valid data in all fields", "The user was not able to create Report Group")));

	//////////////////////////////////////////////////////End Reports Management Screen Variables////////////////////////////////////////////////
	
	
	/////////////////////////////////////////////////////Piper Management Screen////////////////////////////////////////////////////////////////
	
	public static ArrayList<PiperManagementModel> lstPiperManagementCreate = new ArrayList<>();
	
	///////////////////////////////////////////////////End Piper Management Screen////////////////////////////////////////////////////////////////////
	
	
	/////////////////////////////////////////////////////Piper Software Management Screen////////////////////////////////////////////////////////////////
	
	public static ArrayList<PiperSoftwareModel> lstPSManagement = new ArrayList<>(
			Arrays.asList(
					new PiperSoftwareModel("AN-PSM-02: Verify user cannot upload pdf file", "This test case will verify that user can upload pdf file", "User was not able to upload pdf file successfully", "User was able to upload pdf file", "/EULA/sample.pdf", "PDF file", "Invalid file uploaded. Please upload a file with .msi extension", ".custom-tab:nth-child(1)", "userapp"),
					new PiperSoftwareModel("AN-PSM-03: Verify user cannot upload docx file", "This test case will verify that user cannot upload docx file", "User was not able to upload docx file successfully", "User was able to upload docx file", "/EULA/sample.docx", "DOCX file", "Invalid file uploaded. Please upload a file with .msi extension", ".custom-tab:nth-child(1)", "userapp"),
					new PiperSoftwareModel("AN-PSM-04: Verify user cannot upload xlsx file", "This test case will verify that user cannot upload xlsx file", "User was not able to upload xlsx file successfully", "User was able to upload xlsx file", "/EULA/sample.xlsx", "XLSX file", "Invalid file uploaded. Please upload a file with .msi extension", ".custom-tab:nth-child(1)", "userapp"),
					new PiperSoftwareModel("AN-PSM-05: Verify user cannot upload csv file", "This test case will verify that user cannot upload csv file", "User was not able to upload csv file successfully", "User was able to upload csv file", "/EULA/sample.csv", "CSV file", "Invalid file uploaded. Please upload a file with .msi extension", ".custom-tab:nth-child(1)", "userapp"),
					new PiperSoftwareModel("AN-PSM-06: Verify user cannot upload png file", "This test case will verify that user cannot upload png file", "User was not able to upload png file successfully", "User was able to upload png file", "/EULA/sample.png", "PNG file", "Invalid file uploaded. Please upload a file with .msi extension", ".custom-tab:nth-child(1)", "userapp"),
					new PiperSoftwareModel("AN-PSM-07: Verify user cannot upload already uploaded .msi file", "This test case will verify that user cannot upload already uploaded .msi file", "User was not able to upload already uploaded .msi file", "User was able to upload already uploaded .msi file", "/EULA/PiperUserApp_Installer.08.11.10.14.msi", ".msi file", "Invalid version uploaded. Either this version already exists or is not the latest version. Please try again with a different version.", ".custom-tab:nth-child(1)", "userapp"),
					new PiperSoftwareModel("AN-PSM-08: Verify user cannot upload .msi file having less then 4 digit value seperated by decimal", "This test case will verify that user cannot upload .msi file having less then 4 digit value seperated by decimal", "User was not able to upload .msi file having less then 4 digit value seperated by decimal", "User was able to upload .msi file having less then 4 digit value seperated by decimal", "/EULA/PiperUserApp_Installer.08.11.msi", ".msi file", "Invalid version uploaded. Either this version already exists or is not the latest version. Please try again with a different version.", ".custom-tab:nth-child(1)", "userapp"),
					
					new PiperSoftwareModel("AN-PSM-09: Verify user can upload pdf file", "This test case will verify that user can upload pdf file", "User was not able to upload pdf file successfully", "User was able to upload pdf file", "/EULA/sample.pdf", "PDF file", "Invalid file uploaded. Please upload a file with .zip extension", ".custom-tab:nth-child(2)", "improSalm"),
					new PiperSoftwareModel("AN-PSM-10: Verify user cannot upload docx file", "This test case will verify that user cannot upload docx file", "User was not able to upload docx file successfully", "User was able to upload docx file", "/EULA/sample.docx", "DOCX file", "Invalid file uploaded. Please upload a file with .zip extension", ".custom-tab:nth-child(2)", "improSalm"),
					new PiperSoftwareModel("AN-PSM-11: Verify user cannot upload xlsx file", "This test case will verify that user cannot upload xlsx file", "User was not able to upload xlsx file successfully", "User was able to upload xlsx file", "/EULA/sample.xlsx", "XLSX file", "Invalid file uploaded. Please upload a file with .zip extension", ".custom-tab:nth-child(2)", "improSalm"),
					new PiperSoftwareModel("AN-PSM-12: Verify user cannot upload csv file", "This test case will verify that user cannot upload csv file", "User was not able to upload csv file successfully", "User was able to upload csv file", "/EULA/sample.csv", "CSV file", "Invalid file uploaded. Please upload a file with .zip extension", ".custom-tab:nth-child(2)", "improSalm"),
					new PiperSoftwareModel("AN-PSM-13: Verify user cannot upload png file", "This test case will verify that user cannot upload png file", "User was not able to upload png file successfully", "User was able to upload png file", "/EULA/sample.png", "PNG file", "Invalid file uploaded. Please upload a file with .zip extension", ".custom-tab:nth-child(2)", "improSalm"),
					new PiperSoftwareModel("AN-PSM-14: Verify user cannot upload already uploaded .zip file", "This test case will verify that user cannot upload already uploaded .zip file", "User was not able to upload already uploaded .zip file", "User was able to upload already uploaded .zip file", "/EULA/sal_algo_v09.79.12.10.zip", ".zip file", "Invalid version uploaded. Either this version already exists or is not the latest version. Please try again with a different version.", ".custom-tab:nth-child(2)", "improSalm"),
					new PiperSoftwareModel("AN-PSM-15: Verify user cannot upload .zip file having less then 4 digit value seperated by decimal", "This test case will verify that user cannot upload .zip file having less then 4 digit value seperated by decimal", "User was not able to upload .zip file having less then 4 digit value seperated by decimal", "User was able to upload .zip file having less then 4 digit value seperated by decimal", "/EULA/sal_algo_v09.79.zip", ".zip file", "Invalid version uploaded. Either this version already exists or is not the latest version. Please try again with a different version.", ".custom-tab:nth-child(2)", "improSalm"),

					new PiperSoftwareModel("AN-PSM-16: Verify user can upload pdf file", "This test case will verify that user can upload pdf file", "User was not able to upload pdf file successfully", "User was able to upload pdf file", "/EULA/sample.pdf", "PDF file", "Invalid file uploaded. Please upload a file with .zip extension", ".custom-tab:nth-child(3)", "improCocc"),
					new PiperSoftwareModel("AN-PSM-17: Verify user cannot upload docx file", "This test case will verify that user cannot upload docx file", "User was not able to upload docx file successfully", "User was able to upload docx file", "/EULA/sample.docx", "DOCX file", "Invalid file uploaded. Please upload a file with .zip extension", ".custom-tab:nth-child(3)", "improCocc"),
					new PiperSoftwareModel("AN-PSM-18: Verify user cannot upload xlsx file", "This test case will verify that user cannot upload xlsx file", "User was not able to upload xlsx file successfully", "User was able to upload xlsx file", "/EULA/sample.xlsx", "XLSX file", "Invalid file uploaded. Please upload a file with .zip extension", ".custom-tab:nth-child(3)", "improCocc"),
					new PiperSoftwareModel("AN-PSM-19: Verify user cannot upload csv file", "This test case will verify that user cannot upload csv file", "User was not able to upload csv file successfully", "User was able to upload csv file", "/EULA/sample.csv", "CSV file", "Invalid file uploaded. Please upload a file with .zip extension", ".custom-tab:nth-child(3)", "improCocc"),
					new PiperSoftwareModel("AN-PSM-20: Verify user cannot upload png file", "This test case will verify that user cannot upload png file", "User was not able to upload png file successfully", "User was able to upload png file", "/EULA/sample.png", "PNG file", "Invalid file uploaded. Please upload a file with .zip extension", ".custom-tab:nth-child(3)", "improCocc"),
					new PiperSoftwareModel("AN-PSM-21: Verify user cannot upload already uploaded .zip file", "This test case will verify that user cannot upload already uploaded .zip file", "User was not able to upload already uploaded .zip file", "User was able to upload already uploaded .zip file", "/EULA/algo_v09.62.12.10.zip", ".zip file", "Invalid version uploaded. Either this version already exists or is not the latest version. Please try again with a different version.", ".custom-tab:nth-child(3)", "improCocc"),
					new PiperSoftwareModel("AN-PSM-22: Verify user cannot upload .zip file having less then 4 digit value seperated by decimal", "This test case will verify that user cannot upload .zip file having less then 4 digit value seperated by decimal", "User was not able to upload .zip file having less then 4 digit value seperated by decimal", "User was able to upload .zip file having less then 4 digit value seperated by decimal", "/EULA/algo_v09.62.zip", ".zip file", "Invalid version uploaded. Either this version already exists or is not the latest version. Please try again with a different version.", ".custom-tab:nth-child(3)", "improCocc")	
					));
	
	///////////////////////////////////////////////////End Piper Software Management Screen////////////////////////////////////////////////////////////////////
	
	
	
	/////////////////////////////////////////////////////Piper Configuration Screen////////////////////////////////////////////////////////////////
	
	public static ArrayList<PiperConfigurationModel> lstPiperConfigurationCreate = new ArrayList<>();
	public static ArrayList<PiperConfigurationModel> lstPiperConfigurationCreatePA = new ArrayList<>();
	
	///////////////////////////////////////////////////End Piper Configuration Screen////////////////////////////////////////////////////////////////////
	
	
	//////////////////////////////////////////////////////Data Template Management Screen Variables////////////////////////////////////////////////
	
	public static String TemplateName = "Test Template"+date0;
	
	public static ArrayList<DataTemplateModel> lstDTMMandatoryCheck = new ArrayList<>(
			Arrays.asList(
					new DataTemplateModel(true, "", "", "1. Leave both fields empty", true, "Should not save template", false, false, "", false, "", "AN-DTM-05: Verify mandatory check with all fields empty", "This test case will verify that user cannot create template with all fields empty", "User was not able to create template with all fields empty", "User was able to create template with all fields empty", false, false)	,
					new DataTemplateModel(false, "name", "", "1. Leave only description field empty", true, "Should not save template", false,  false, "", false, "", "AN-DTM-06: Verify user cannot create template with filling only name field", "This test case will verify that user cannot create template with only name field", "User was not able to create template with name field empty", "User was able to create template with name field empty", false, false),	
					new DataTemplateModel(false, "", "desc", "1. Leave only name field empty", true, "Should not save template", false, false, "", false, "",  "AN-DTM-07: Verify that user cannot create template with filling only description field", "This test case will verify that user cannot create template with only description field", "User was not able to create template with description field empty", "User was able to create template with description field empty", false, false),	
					new DataTemplateModel(false, "Test Template", "Test Description", "1. Fill name and description field", true, "Should not save template without adding column", true,  false, "", false, "",  "AN-DTM-08: Verify user cannot create template without adding atleast 1 column", "This test case will verify that user cannot create template without adding atleast 1 column", "User was not able to create template without adding atleast 1 column", "User was able to create template without adding a column", false, false),
					new DataTemplateModel(false, "Test Template", "Test Description", "1. Leave all column fields empty", false, "Should not save template", false,  true, "", false, "",  "AN-DTM-09: Verify that user cannot add column with all column fields empty", "This test case will verify that user cannot add column leaving all fields empty", "User was not able to add column with all fields empty", "User was able to add column with all fields empty",true, false),   
					new DataTemplateModel(false, "Test Template", "Test Description", "1. Leave type and length field empty", false, "Should not save template", false,  true, "Serial", false, "",  "AN-DTM-10: Verify user cannot add column with only name field", "This test case will verify that user cannot add column with adding only name field", "User was not able to add column with only name field", "User was able to add column with only name field",true, false),
					new DataTemplateModel(false, "Test Template", "Test Description", "1. Leave name and type field empty", false, "Should not save template", false,  true, "", false, "100",  "AN-DTM-11: Verify that user cannot add column with only length field", "This test case will verify that user cannot add column with adding only length field", "User was not able to add column with only length field", "User was able to add column with only length field",true, false),
					new DataTemplateModel(false, "Test Template", "Test Description", "1. Leave only type field empty", false, "Should not save template", false,  true, "Serial", false, "100",  "AN-DTM-12: Verify that user cannot add column without adding column type", "This test case will verify that user cannot add column without adding column type", "User was not able to add column without adding column type", "User was able to add column without adding column type",true, false),
					new DataTemplateModel(false, TemplateName, "Test Description", "1. Fill all fields of template and column", false, "Should save template", false,  true, "Serial", true, "100",  "AN-DTM-13: Verify user is able to add column after filling all mandatory fields", "This test case will verify that user can add column after filling all mandatory fields of column", "User was able to add column after filling all mandatory fields", "User was not able to add column after filling all mandatory fields", true, true)
					));

	
	public static ArrayList<DataTemplateModel> lstDTMUpdate = new ArrayList<>(
			Arrays.asList(
					new DataTemplateModel(false, "Test Template", "Test Description", true, "Date", "10", "Test Case"),
					new DataTemplateModel(true, "Test Template", "Test Description", false, "", "", "Test Case")
					));
	
	public static ArrayList<String> lstDTMColumnUpdation = new ArrayList<>(
			Arrays.asList("100"));
	
	//////////////////////////////////////////////////////End Data Template Management Screen Variables////////////////////////////////////////////////
	
	
	//////////////////////////////////////////////////////Data Upload Variables//////////////////////////////////////////////////////////////////////
	
	public static ArrayList<DataUploadModel> lstDataUploadFlock = new ArrayList<>();
	public static ArrayList<DataUploadModel> lstDataUploadSitePerformance = new ArrayList<>();
	public static ArrayList<DataUploadModel> lstDataUploadSampleMetadata = new ArrayList<>();
	public static ArrayList<DataUploadModel> lstDataUploadSaveTemplate = new ArrayList<>();
	
	////////////////////////////////////////////////////End Data Upload Variables///////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////Poultry Management Variables////////////////////////////////////////////////////////////////
	
	public static ArrayList<PoultryManagementModel> lstPoultryfilter = new ArrayList<>(
			Arrays.asList(
					new PoultryManagementModel("AN-Poultry-05: Verify Feed filter functionality", "This test case will verify feed filter functionality", "3", "Feed"),
					new PoultryManagementModel("AN-Poultry-06: Verify Treatment filter functionality", "This test case will verify Treatment filter functionality", "5", "Treatment")));
	
	/////////////////////////////////////////////////End Poultry Management Variables///////////////////////////////////////////////////////////////
	
	
	/////////////////////////////////////////////////////////Announcement API Variables////////////////////////////////////////////////////////////
	
	public static String AnnouncementRunID = "MPNfile"+Test_Variables.date0;
	
	public static ArrayList<String> lstApiAnnouncement = new ArrayList<>(
			Arrays.asList(AnnouncementRunID,   //runId
					"2019-12-12",                           //date
					"DESKTOP-77VTAPV",                      //piperId
					"0",  									//MPNCalculationType
					"20200120202842_PSN0009_sammyproscess_oaeng_improc.csv",    				 //FileName
					"fab8cc1ea3f6db40c4555c3dfab09a34d2fa2b53cd66af72284af1dacb266910"));    //CheckSum              
					 
	public static ArrayList<String> lstSampleID = new ArrayList<>(
			Arrays.asList("Automation1"+date0,  
					"Automation2"+date0,
					"Automation3"+date0,
					"Automation4"+date0,
					"Automation5"+date0,
					"Automation6"+date0,
					"Automation7"+date0,
					"Automation8"+date0,
					"Automation9"+date0)); 
	
	//////////////////////////////////////////////////////End Announcement API Variables/////////////////////////////////////////////////////

	
	///////////////////////////////////////////////////////////Salmonella Ingest//////////////////////////////////////////////////////////////////			
	public static String CartridgeID = "TestCartridge"+date0;  //unique cartridge id used while ingestion
	public static String InstrumentID = "PSN0009"; //instrument id used while ingestion
	public static String UserID = "100"; //user id used for qa ingestion
	//public static String UserID = "98"; //user id used for uat ingestion
	public static String RunID = "Test"+lstSampleID.get(0); //unique run id used while ingestion
	public static String PiperUser = "Jalam"; //piper user name
	public static String ImprocVersion = "9.7.1.5";  //version used while ingestion
	public static String RunType = "Normal"; 
	public static String slImprocVersion = "4.0.8.2";
	public static String slRunID = "Test"+lstSampleID.get(1);
	public static String slCSiteID = "1001001";
	
	///////////Sample MetaData Upload Data//////////////////
	public static String FlockID = "Flock Test";
	public static String RequestedAssay = "RA Test";
	public static String KitLot = "KitLot Test";
	public static String CustomerSampleID = "CSampleID Test";
	public static String SampleMatrix = "AT_SampleMatrix";
	public static String fileName = "MetaData RunMode1.xlsx";
	public static String SiteID = "1001001";
//	public static String SiteID = "1267007";
	public static String invalidSiteID = "1267002";  //siteid not assigned to user
	public static String CollectionDate = "12/12/2021";
	////////End Sample MetaData Upload Data/////////////////

	public static ArrayList<APIModel> lstSalmonellaIngest = new ArrayList<>(
			Arrays.asList(
					new APIModel(AnnouncementRunID, "fab8cc1ea3f6db40c4555c3dfab09a34d2fa2b53cd66af72284af1dacb266910", 
							"20200120202842_PSN0009_sammyproscess_oaeng_improc.csv", "csv",                                                                                                        //03132020_PSN16_21612_lis1
							"TEFORV9OTyxEQVRFLFRJTUUsVzFfUENfQ09VTlQsVzFfQ0VMTF9DT1VOVCxXMV9QQ19NRUFOX0lOVEVOU0lUWSxXMV9DRUxMX01FQU5fSU5URU5TSVRZLFcxX1BDX1JBTkdFX0lOVEVOU0lUWSxXMV9DRUxMX1JBTkdFX0lOVEVOU0lUWSxXMV9QQ19DVl9JTlRFTlNJVFksVzFfQ0VMTF9DVl9JTlRFTlNJVFksVzFfUENfTUVBTl9TSVpFLFcxX0NFTExfTUVBTl9TSVpFLFcxX1BDX1JBTkdFX1NJWkUsVzFfQ0VMTF9SQU5HRV9TSVpFLFcxX1BDX0NWX1NJWkUsVzFfQ0VMTF9DVl9TSVpFLFcyX1BDX0NPVU5ULFcyX0NFTExfQ09VTlQsVzJfUENfTUVBTl9JTlRFTlNJVFksVzJfQ0VMTF9NRUFOX0lOVEVOU0lUWSxXMl9QQ19SQU5HRV9JTlRFTlNJVFksVzJfQ0VMTF9SQU5HRV9JTlRFTlNJVFksVzJfUENfQ1ZfSU5URU5TSVRZLFcyX0NFTExfQ1ZfSU5URU5TSVRZLFcyX1BDX01FQU5fU0laRSxXMl9DRUxMX01FQU5fU0laRSxXMl9QQ19SQU5HRV9TSVpFLFcyX0NFTExfUkFOR0VfU0laRSxXMl9QQ19DVl9TSVpFLFcyX0NFTExfQ1ZfU0laRSxTVEFUVVMsTEFORV9UT1RBTF9BUkVBX1VNMixMQU5FX05PSVNFX0FSRUFfVU0yLExBTkVfTk9JU0VfUkFUSU9fUEVSQ0VOVCxMQU5FX05PSVNFX09CSkVDVF9DT1VOVCxXMV9OT0lTRV9BUkVBX1VNMixXMl9OT0lTRV9BUkVBX1VNMixXMV9OT0lTRV9SQVRJT19QRVJDRU5ULFcyX05PSVNFX1JBVElPX1BFUkNFTlQsVzFfTk9JU0VfT0JKRUNUX0NPVU5ULFcyX05PSVNFX09CSkVDVF9DT1VOVCxMQU5FX1NNQUxMX05PSVNFX09CSkVDVF9DT1VOVCxMQU5FX01FRElVTV9OT0lTRV9PQkpFQ1RfQ09VTlQsTEFORV9MQVJHRV9OT0lTRV9PQkpFQ1RfQ09VTlQsTEFORV9FWFRSQV9MQVJHRV9OT0lTRV9PQkpFQ1RfQ09VTlQsRVJST1JfQ09ERQ0KMSwyMDIwLTAxLTIwLDIwOjM0OjI0LjU1NjAxNCw3LDU5LDExNy4yNzgzNjczNDY5Mzg3OCwyMi40MTk5Mjg4MjU2MjI3NzYsMjMzLDEyOCwwLjYwMDUzMjk4NTc4OTI5MTMsMS4wNTczODE3MTE4OTc4NzY4LDE1My4xNDI4NTcxNDI4NTcxNCw2Ljg5Mjg1NzE0Mjg1NzE0MywxNzguMDcsMTIuNzk0OTk5OTk5OTk5OTk4LDAuNDUzNDc2MjM3NjMwMzEzMTQsMC42NjI2NjU3Mzk3MDE0MTg4LDIxLDI1LDgyLjU1NDY1NTg3MDQ0NTM0LDEwLjMxOTg2NTMxOTg2NTMyLDI1MSw1MCwwLjk0ODIzNTAwNzExMDYwMSwwLjgwMDQxODA5OTgwNDYzNDcsNjguNjkwNDc2MTkwNDc2MTksOC42NDcwNTg4MjM1Mjk0MTEsMTI0LjIsMTMuMjM5OTk5OTk5OTk5OTk4LDAuODM0MTA4MTAzNDA1MTA3NCwwLjQ1ODIxMjYyOTA4OTc2NjMsNTQwNjI2MTEzLjAsMTIxOTE0My41LDAuMjMsNDUxLDUwMzMuNSwxODk4Ny4wLDAuMDA2MjU5MDU0MDg1MTQxMDQyLDAuMDIzNjA5OTQ1MzQ5MDc1NzgyLDIzLDU1LDQ1MCwxLDAsMCwNCjIsMjAyMC0wMS0yMCwyMDozNTozMC45NTg4NDMsMCwwLG5hbixuYW4sMCwwLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sMCwxLG5hbixuYW4sMCwwLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sNTM5NzI2NjA3LjAsNDU4NDcuMCwwLjAxLDE5LDAuMCwwLjAsMC4wLDAuMCwwLDAsMTksMCwwLDAsDQozLDIwMjAtMDEtMjAsMjA6Mzc6MjEuMjQ4ODg5LDAsMCxuYW4sbmFuLDAsMCxuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLDAsMzMsbmFuLDExNS45OTIzNjY0MTIyMTM3NCwwLDEzMCxuYW4sMC4yOTg1MjI3MzE4MDI4NTE1NSxuYW4sOC43LG5hbiwxMS4zMTk5OTk5OTk5OTk5OTksbmFuLDAuNTAzMTI4MDE0NDQyMjgyNiw1Mzg2NzMzOTguMCwyMDc3NzQuMCwwLjA0LDI2LDAuMCwwLjAsMC4wLDAuMCwwLDAsMjUsMSwwLDAsDQo=", 
							"[{'LANE':'1','PATHOGEN':' Salmonella Presence/Absence    ','SAMPLEID':'Test"+lstSampleID.get(0)+"','SCANDATETIME':'"+date100+"','OUTCOME':'','STATUSCODE':'3','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'','CARTRIDGEID':'"+CartridgeID+"','EXPERIMENTID':'','INSTRUMENTID':'"+InstrumentID+"','USERID':'"+UserID+"','RUN_ID':'"+slRunID+"','RUN_TYPE':'SCRIPT_1001a Salmonella Probes','MPN_CALCULATION_TYPE':'0','DILUTION_FACTOR':'0.01','LANE_NO':'1','DATE':'2020-06-03','TIME':'10:45:43.914898','W1_PC_COUNT':'0','W1_CELL_COUNT':'0','W1_PC_MEAN_INTENSITY':'','W1_CELL_MEAN_INTENSITY':'','W1_PC_RANGE_INTENSITY':'0','W1_CELL_RANGE_INTENSITY':'0','W1_PC_CV_INTENSITY':'','W1_CELL_CV_INTENSITY':'','W1_PC_MEAN_SIZE':'','W1_CELL_MEAN_SIZE':'','W1_PC_RANGE_SIZE':'','W1_CELL_RANGE_SIZE':'','W1_PC_CV_SIZE':'','W1_CELL_CV_SIZE':'','W2_PC_COUNT':'0','W2_CELL_COUNT':'2000','W2_PC_MEAN_INTENSITY':'','W2_CELL_MEAN_INTENSITY':'','W2_PC_RANGE_INTENSITY':'0','W2_CELL_RANGE_INTENSITY':'0','W2_PC_CV_INTENSITY':'','W2_CELL_CV_INTENSITY':'','W2_PC_MEAN_SIZE':'','W2_CELL_MEAN_SIZE':'','W2_PC_RANGE_SIZE':'','W2_CELL_RANGE_SIZE':'','W2_PC_CV_SIZE':'','W2_CELL_CV_SIZE':'','STATUS':'','LANE_TOTAL_AREA_UM2':'326035983.8748','LANE_NOISE_AREA_UM2':'82232170.4712','LANE_NOISE_RATIO_PERCENT':'95.73','LANE_NOISE_OBJECT_COUNT':'71','W1_NOISE_AREA_UM2':'8323637.364','W2_NOISE_AREA_UM2':'8315160.7508','W1_NOISE_RATIO_PERCENT':'100.0','W2_NOISE_RATIO_PERCENT':'99.8993635494474','W1_NOISE_OBJECT_COUNT':'0','W2_NOISE_OBJECT_COUNT':'0','LANE_SMALL_NOISE_OBJECT_COUNT':'71','LANE_MEDIUM_NOISE_OBJECT_COUNT':'0','LANE_LARGE_NOISE_OBJECT_COUNT':'0','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'0','IMPROC':'ImprocSalm01','VERSION':'"+slImprocVersion+"','ERROR_CODE':'B01','IE_COLLECTION_SITE_ID':'1001043','IE_SAMPLE_MATRIX_ID':''},{'LANE':'2','PATHOGEN':' Salmonella Presence/Absence    ','SAMPLEID':'Test"+lstSampleID.get(0)+"','SCANDATETIME':'"+date100+"','OUTCOME':'pass','STATUSCODE':'3','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'','CARTRIDGEID':'"+CartridgeID+"','EXPERIMENTID':'','INSTRUMENTID':'"+InstrumentID+"','USERID':'"+UserID+"','RUN_ID':'"+slRunID+"','RUN_TYPE':'SCRIPT_1001a Salmonella Probes','MPN_CALCULATION_TYPE':'0','DILUTION_FACTOR':'0.1','LANE_NO':'2','DATE':'2020-06-03','TIME':'10:45:43.914898','W1_PC_COUNT':'0','W1_CELL_COUNT':'0','W1_PC_MEAN_INTENSITY':'','W1_CELL_MEAN_INTENSITY':'','W1_PC_RANGE_INTENSITY':'0','W1_CELL_RANGE_INTENSITY':'0','W1_PC_CV_INTENSITY':'','W1_CELL_CV_INTENSITY':'','W1_PC_MEAN_SIZE':'','W1_CELL_MEAN_SIZE':'','W1_PC_RANGE_SIZE':'','W1_CELL_RANGE_SIZE':'','W1_PC_CV_SIZE':'','W1_CELL_CV_SIZE':'','W2_PC_COUNT':'0','W2_CELL_COUNT':'2000','W2_PC_MEAN_INTENSITY':'','W2_CELL_MEAN_INTENSITY':'','W2_PC_RANGE_INTENSITY':'0','W2_CELL_RANGE_INTENSITY':'0','W2_PC_CV_INTENSITY':'','W2_CELL_CV_INTENSITY':'','W2_PC_MEAN_SIZE':'','W2_CELL_MEAN_SIZE':'','W2_PC_RANGE_SIZE':'','W2_CELL_RANGE_SIZE':'','W2_PC_CV_SIZE':'','W2_CELL_CV_SIZE':'','STATUS':'','LANE_TOTAL_AREA_UM2':'326035983.8748','LANE_NOISE_AREA_UM2':'82232170.4712','LANE_NOISE_RATIO_PERCENT':'95.73','LANE_NOISE_OBJECT_COUNT':'71','W1_NOISE_AREA_UM2':'8323637.364','W2_NOISE_AREA_UM2':'8315160.7508','W1_NOISE_RATIO_PERCENT':'100.0','W2_NOISE_RATIO_PERCENT':'99.8993635494474','W1_NOISE_OBJECT_COUNT':'0','W2_NOISE_OBJECT_COUNT':'0','LANE_SMALL_NOISE_OBJECT_COUNT':'71','LANE_MEDIUM_NOISE_OBJECT_COUNT':'0','LANE_LARGE_NOISE_OBJECT_COUNT':'0','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'0','IMPROC':'ImprocSalm01','VERSION':'"+slImprocVersion+"','ERROR_CODE':'B01','IE_COLLECTION_SITE_ID':'1001043','IE_SAMPLE_MATRIX_ID':''},{'LANE':'3','PATHOGEN':' Salmonella Presence/Absence    ','SAMPLEID':'Test"+lstSampleID.get(0)+"','SCANDATETIME':'"+date100+"','OUTCOME':'pass','STATUSCODE':'3','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'B01','CARTRIDGEID':'"+CartridgeID+"','EXPERIMENTID':'','INSTRUMENTID':'"+InstrumentID+"','USERID':'"+UserID+"','RUN_ID':'"+slRunID+"','RUN_TYPE':'SCRIPT_1001a Salmonella Probes','MPN_CALCULATION_TYPE':'0','DILUTION_FACTOR':'0.01','LANE_NO':'3','DATE':'2020-06-03','TIME':'10:45:43.914898','W1_PC_COUNT':'0','W1_CELL_COUNT':'0','W1_PC_MEAN_INTENSITY':'','W1_CELL_MEAN_INTENSITY':'','W1_PC_RANGE_INTENSITY':'0','W1_CELL_RANGE_INTENSITY':'0','W1_PC_CV_INTENSITY':'','W1_CELL_CV_INTENSITY':'','W1_PC_MEAN_SIZE':'','W1_CELL_MEAN_SIZE':'','W1_PC_RANGE_SIZE':'','W1_CELL_RANGE_SIZE':'','W1_PC_CV_SIZE':'','W1_CELL_CV_SIZE':'','W2_PC_COUNT':'0','W2_CELL_COUNT':'2000','W2_PC_MEAN_INTENSITY':'','W2_CELL_MEAN_INTENSITY':'','W2_PC_RANGE_INTENSITY':'0','W2_CELL_RANGE_INTENSITY':'0','W2_PC_CV_INTENSITY':'','W2_CELL_CV_INTENSITY':'','W2_PC_MEAN_SIZE':'','W2_CELL_MEAN_SIZE':'','W2_PC_RANGE_SIZE':'','W2_CELL_RANGE_SIZE':'','W2_PC_CV_SIZE':'','W2_CELL_CV_SIZE':'','STATUS':'','LANE_TOTAL_AREA_UM2':'326035983.8748','LANE_NOISE_AREA_UM2':'82232170.4712','LANE_NOISE_RATIO_PERCENT':'95.73','LANE_NOISE_OBJECT_COUNT':'71','W1_NOISE_AREA_UM2':'8323637.364','W2_NOISE_AREA_UM2':'8315160.7508','W1_NOISE_RATIO_PERCENT':'100.0','W2_NOISE_RATIO_PERCENT':'99.8993635494474','W1_NOISE_OBJECT_COUNT':'0','W2_NOISE_OBJECT_COUNT':'0','LANE_SMALL_NOISE_OBJECT_COUNT':'71','LANE_MEDIUM_NOISE_OBJECT_COUNT':'0','LANE_LARGE_NOISE_OBJECT_COUNT':'0','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'0','IMPROC':'ImprocSalm01','VERSION':'"+slImprocVersion+"','ERROR_CODE':'B01','IE_COLLECTION_SITE_ID':'1001043','IE_SAMPLE_MATRIX_ID':''},{'LANE':'4','PATHOGEN':' Salmonella Presence/Absence    ','SAMPLEID':'Test"+lstSampleID.get(1)+"','SCANDATETIME':'"+date100+"','OUTCOME':'pass','STATUSCODE':'3','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'B01','CARTRIDGEID':'"+CartridgeID+"','EXPERIMENTID':'','INSTRUMENTID':'"+InstrumentID+"','USERID':'"+UserID+"','RUN_ID':'"+slRunID+"','RUN_TYPE':'SCRIPT_1001a Salmonella Probes','MPN_CALCULATION_TYPE':'0','DILUTION_FACTOR':'0.01','LANE_NO':'4','DATE':'2020-06-03','TIME':'10:45:43.914898','W1_PC_COUNT':'0','W1_CELL_COUNT':'0','W1_PC_MEAN_INTENSITY':'','W1_CELL_MEAN_INTENSITY':'','W1_PC_RANGE_INTENSITY':'0','W1_CELL_RANGE_INTENSITY':'0','W1_PC_CV_INTENSITY':'','W1_CELL_CV_INTENSITY':'','W1_PC_MEAN_SIZE':'','W1_CELL_MEAN_SIZE':'','W1_PC_RANGE_SIZE':'','W1_CELL_RANGE_SIZE':'','W1_PC_CV_SIZE':'','W1_CELL_CV_SIZE':'','W2_PC_COUNT':'0','W2_CELL_COUNT':'2000','W2_PC_MEAN_INTENSITY':'','W2_CELL_MEAN_INTENSITY':'','W2_PC_RANGE_INTENSITY':'0','W2_CELL_RANGE_INTENSITY':'0','W2_PC_CV_INTENSITY':'','W2_CELL_CV_INTENSITY':'','W2_PC_MEAN_SIZE':'','W2_CELL_MEAN_SIZE':'','W2_PC_RANGE_SIZE':'','W2_CELL_RANGE_SIZE':'','W2_PC_CV_SIZE':'','W2_CELL_CV_SIZE':'','STATUS':'','LANE_TOTAL_AREA_UM2':'326035983.8748','LANE_NOISE_AREA_UM2':'82232170.4712','LANE_NOISE_RATIO_PERCENT':'95.73','LANE_NOISE_OBJECT_COUNT':'71','W1_NOISE_AREA_UM2':'8323637.364','W2_NOISE_AREA_UM2':'8315160.7508','W1_NOISE_RATIO_PERCENT':'100.0','W2_NOISE_RATIO_PERCENT':'99.8993635494474','W1_NOISE_OBJECT_COUNT':'0','W2_NOISE_OBJECT_COUNT':'0','LANE_SMALL_NOISE_OBJECT_COUNT':'71','LANE_MEDIUM_NOISE_OBJECT_COUNT':'0','LANE_LARGE_NOISE_OBJECT_COUNT':'0','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'0','IMPROC':'ImprocSalm01','VERSION':'"+slImprocVersion+"','ERROR_CODE':'B01','IE_COLLECTION_SITE_ID':'1001043','IE_SAMPLE_MATRIX_ID':''},{'LANE':'5','PATHOGEN':' Salmonella Presence/Absence    ','SAMPLEID':'Test"+lstSampleID.get(1)+"','SCANDATETIME':'"+date100+"','OUTCOME':'pass','STATUSCODE':'3','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'B01','CARTRIDGEID':'"+CartridgeID+"','EXPERIMENTID':'','INSTRUMENTID':'"+InstrumentID+"','USERID':'"+UserID+"','RUN_ID':'"+slRunID+"','RUN_TYPE':'SCRIPT_1001a Salmonella Probes','MPN_CALCULATION_TYPE':'0','DILUTION_FACTOR':'0.01','LANE_NO':'5','DATE':'2020-06-03','TIME':'10:45:43.914898','W1_PC_COUNT':'0','W1_CELL_COUNT':'0','W1_PC_MEAN_INTENSITY':'','W1_CELL_MEAN_INTENSITY':'','W1_PC_RANGE_INTENSITY':'0','W1_CELL_RANGE_INTENSITY':'0','W1_PC_CV_INTENSITY':'','W1_CELL_CV_INTENSITY':'','W1_PC_MEAN_SIZE':'','W1_CELL_MEAN_SIZE':'','W1_PC_RANGE_SIZE':'','W1_CELL_RANGE_SIZE':'','W1_PC_CV_SIZE':'','W1_CELL_CV_SIZE':'','W2_PC_COUNT':'0','W2_CELL_COUNT':'2000','W2_PC_MEAN_INTENSITY':'','W2_CELL_MEAN_INTENSITY':'','W2_PC_RANGE_INTENSITY':'0','W2_CELL_RANGE_INTENSITY':'0','W2_PC_CV_INTENSITY':'','W2_CELL_CV_INTENSITY':'','W2_PC_MEAN_SIZE':'','W2_CELL_MEAN_SIZE':'','W2_PC_RANGE_SIZE':'','W2_CELL_RANGE_SIZE':'','W2_PC_CV_SIZE':'','W2_CELL_CV_SIZE':'','STATUS':'','LANE_TOTAL_AREA_UM2':'326035983.8748','LANE_NOISE_AREA_UM2':'82232170.4712','LANE_NOISE_RATIO_PERCENT':'95.73','LANE_NOISE_OBJECT_COUNT':'71','W1_NOISE_AREA_UM2':'8323637.364','W2_NOISE_AREA_UM2':'8315160.7508','W1_NOISE_RATIO_PERCENT':'100.0','W2_NOISE_RATIO_PERCENT':'99.8993635494474','W1_NOISE_OBJECT_COUNT':'0','W2_NOISE_OBJECT_COUNT':'0','LANE_SMALL_NOISE_OBJECT_COUNT':'71','LANE_MEDIUM_NOISE_OBJECT_COUNT':'0','LANE_LARGE_NOISE_OBJECT_COUNT':'0','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'0','IMPROC':'ImprocSalm01','VERSION':'"+slImprocVersion+"','ERROR_CODE':'B01','IE_COLLECTION_SITE_ID':'1001043','IE_SAMPLE_MATRIX_ID':''},{'LANE':'6','PATHOGEN':' Salmonella Presence/Absence    ','SAMPLEID':'Test"+lstSampleID.get(1)+"','SCANDATETIME':'"+date100+"','OUTCOME':'pass','STATUSCODE':'3','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'B01','CARTRIDGEID':'"+CartridgeID+"','EXPERIMENTID':'','INSTRUMENTID':'"+InstrumentID+"','USERID':'"+UserID+"','RUN_ID':'"+slRunID+"','RUN_TYPE':'SCRIPT_1001a Salmonella Probes','MPN_CALCULATION_TYPE':'0','DILUTION_FACTOR':'0.01','LANE_NO':'6','DATE':'2020-06-03','TIME':'10:45:43.914898','W1_PC_COUNT':'0','W1_CELL_COUNT':'0','W1_PC_MEAN_INTENSITY':'','W1_CELL_MEAN_INTENSITY':'','W1_PC_RANGE_INTENSITY':'0','W1_CELL_RANGE_INTENSITY':'0','W1_PC_CV_INTENSITY':'','W1_CELL_CV_INTENSITY':'','W1_PC_MEAN_SIZE':'','W1_CELL_MEAN_SIZE':'','W1_PC_RANGE_SIZE':'','W1_CELL_RANGE_SIZE':'','W1_PC_CV_SIZE':'','W1_CELL_CV_SIZE':'','W2_PC_COUNT':'0','W2_CELL_COUNT':'900','W2_PC_MEAN_INTENSITY':'','W2_CELL_MEAN_INTENSITY':'','W2_PC_RANGE_INTENSITY':'0','W2_CELL_RANGE_INTENSITY':'0','W2_PC_CV_INTENSITY':'','W2_CELL_CV_INTENSITY':'','W2_PC_MEAN_SIZE':'','W2_CELL_MEAN_SIZE':'','W2_PC_RANGE_SIZE':'','W2_CELL_RANGE_SIZE':'','W2_PC_CV_SIZE':'','W2_CELL_CV_SIZE':'','STATUS':'','LANE_TOTAL_AREA_UM2':'326035983.8748','LANE_NOISE_AREA_UM2':'82232170.4712','LANE_NOISE_RATIO_PERCENT':'95.73','LANE_NOISE_OBJECT_COUNT':'71','W1_NOISE_AREA_UM2':'8323637.364','W2_NOISE_AREA_UM2':'8315160.7508','W1_NOISE_RATIO_PERCENT':'100.0','W2_NOISE_RATIO_PERCENT':'99.8993635494474','W1_NOISE_OBJECT_COUNT':'0','W2_NOISE_OBJECT_COUNT':'0','LANE_SMALL_NOISE_OBJECT_COUNT':'71','LANE_MEDIUM_NOISE_OBJECT_COUNT':'0','LANE_LARGE_NOISE_OBJECT_COUNT':'0','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'0','IMPROC':'ImprocSalm01','VERSION':'"+slImprocVersion+"','ERROR_CODE':'B01','IE_COLLECTION_SITE_ID':'1001043','IE_SAMPLE_MATRIX_ID':''},{'LANE':'7','PATHOGEN':' Salmonella Presence/Absence    ','SAMPLEID':'Test"+lstSampleID.get(2)+"','SCANDATETIME':'"+date100+"','OUTCOME':'pass','STATUSCODE':'3','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'B01','CARTRIDGEID':'"+CartridgeID+"','EXPERIMENTID':'','INSTRUMENTID':'"+InstrumentID+"','USERID':'"+UserID+"','RUN_ID':'"+slRunID+"','RUN_TYPE':'SCRIPT_1001a Salmonella Probes','MPN_CALCULATION_TYPE':'0','DILUTION_FACTOR':'0.01','LANE_NO':'7','DATE':'2020-06-03','TIME':'10:45:43.914898','W1_PC_COUNT':'0','W1_CELL_COUNT':'0','W1_PC_MEAN_INTENSITY':'','W1_CELL_MEAN_INTENSITY':'','W1_PC_RANGE_INTENSITY':'0','W1_CELL_RANGE_INTENSITY':'0','W1_PC_CV_INTENSITY':'','W1_CELL_CV_INTENSITY':'','W1_PC_MEAN_SIZE':'','W1_CELL_MEAN_SIZE':'','W1_PC_RANGE_SIZE':'','W1_CELL_RANGE_SIZE':'','W1_PC_CV_SIZE':'','W1_CELL_CV_SIZE':'','W2_PC_COUNT':'0','W2_CELL_COUNT':'2000','W2_PC_MEAN_INTENSITY':'','W2_CELL_MEAN_INTENSITY':'','W2_PC_RANGE_INTENSITY':'0','W2_CELL_RANGE_INTENSITY':'0','W2_PC_CV_INTENSITY':'','W2_CELL_CV_INTENSITY':'','W2_PC_MEAN_SIZE':'','W2_CELL_MEAN_SIZE':'','W2_PC_RANGE_SIZE':'','W2_CELL_RANGE_SIZE':'','W2_PC_CV_SIZE':'','W2_CELL_CV_SIZE':'','STATUS':'','LANE_TOTAL_AREA_UM2':'326035983.8748','LANE_NOISE_AREA_UM2':'82232170.4712','LANE_NOISE_RATIO_PERCENT':'95.73','LANE_NOISE_OBJECT_COUNT':'71','W1_NOISE_AREA_UM2':'8323637.364','W2_NOISE_AREA_UM2':'8315160.7508','W1_NOISE_RATIO_PERCENT':'100.0','W2_NOISE_RATIO_PERCENT':'99.8993635494474','W1_NOISE_OBJECT_COUNT':'0','W2_NOISE_OBJECT_COUNT':'0','LANE_SMALL_NOISE_OBJECT_COUNT':'71','LANE_MEDIUM_NOISE_OBJECT_COUNT':'0','LANE_LARGE_NOISE_OBJECT_COUNT':'0','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'0','IMPROC':'ImprocSalm01','VERSION':'"+slImprocVersion+"','ERROR_CODE':'B01','IE_COLLECTION_SITE_ID':'1001043','IE_SAMPLE_MATRIX_ID':''},{'LANE':'8','PATHOGEN':' Salmonella Presence/Absence    ','SAMPLEID':'Test"+lstSampleID.get(2)+"','SCANDATETIME':'"+date100+"','OUTCOME':'pass','STATUSCODE':'3','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'B01','CARTRIDGEID':'"+CartridgeID+"','EXPERIMENTID':'','INSTRUMENTID':'"+InstrumentID+"','USERID':'"+UserID+"','RUN_ID':'"+slRunID+"','RUN_TYPE':'SCRIPT_1001a Salmonella Probes','MPN_CALCULATION_TYPE':'0','DILUTION_FACTOR':'0.01','LANE_NO':'8','DATE':'2020-06-03','TIME':'10:45:43.914898','W1_PC_COUNT':'0','W1_CELL_COUNT':'0','W1_PC_MEAN_INTENSITY':'','W1_CELL_MEAN_INTENSITY':'','W1_PC_RANGE_INTENSITY':'0','W1_CELL_RANGE_INTENSITY':'0','W1_PC_CV_INTENSITY':'','W1_CELL_CV_INTENSITY':'','W1_PC_MEAN_SIZE':'','W1_CELL_MEAN_SIZE':'','W1_PC_RANGE_SIZE':'','W1_CELL_RANGE_SIZE':'','W1_PC_CV_SIZE':'','W1_CELL_CV_SIZE':'','W2_PC_COUNT':'0','W2_CELL_COUNT':'100','W2_PC_MEAN_INTENSITY':'','W2_CELL_MEAN_INTENSITY':'','W2_PC_RANGE_INTENSITY':'0','W2_CELL_RANGE_INTENSITY':'0','W2_PC_CV_INTENSITY':'','W2_CELL_CV_INTENSITY':'','W2_PC_MEAN_SIZE':'','W2_CELL_MEAN_SIZE':'','W2_PC_RANGE_SIZE':'','W2_CELL_RANGE_SIZE':'','W2_PC_CV_SIZE':'','W2_CELL_CV_SIZE':'','STATUS':'','LANE_TOTAL_AREA_UM2':'326035983.8748','LANE_NOISE_AREA_UM2':'82232170.4712','LANE_NOISE_RATIO_PERCENT':'95.73','LANE_NOISE_OBJECT_COUNT':'71','W1_NOISE_AREA_UM2':'8323637.364','W2_NOISE_AREA_UM2':'8315160.7508','W1_NOISE_RATIO_PERCENT':'100.0','W2_NOISE_RATIO_PERCENT':'99.8993635494474','W1_NOISE_OBJECT_COUNT':'0','W2_NOISE_OBJECT_COUNT':'0','LANE_SMALL_NOISE_OBJECT_COUNT':'71','LANE_MEDIUM_NOISE_OBJECT_COUNT':'0','LANE_LARGE_NOISE_OBJECT_COUNT':'0','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'0','IMPROC':'ImprocSalm01','VERSION':'"+slImprocVersion+"','ERROR_CODE':'B01','IE_COLLECTION_SITE_ID':'1001043','IE_SAMPLE_MATRIX_ID':''},{'LANE':'9','PATHOGEN':' Salmonella Presence/Absence    ','SAMPLEID':'Test"+lstSampleID.get(2)+"','SCANDATETIME':'"+date100+"','OUTCOME':'pass','STATUSCODE':'3','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'B01','CARTRIDGEID':'"+CartridgeID+"','EXPERIMENTID':'','INSTRUMENTID':'"+InstrumentID+"','USERID':'"+UserID+"','RUN_ID':'"+slRunID+"','RUN_TYPE':'SCRIPT_1001a Salmonella Probes','MPN_CALCULATION_TYPE':'0','DILUTION_FACTOR':'0.01','LANE_NO':'9','DATE':'2020-06-03','TIME':'10:45:43.914898','W1_PC_COUNT':'0','W1_CELL_COUNT':'0','W1_PC_MEAN_INTENSITY':'','W1_CELL_MEAN_INTENSITY':'','W1_PC_RANGE_INTENSITY':'0','W1_CELL_RANGE_INTENSITY':'0','W1_PC_CV_INTENSITY':'','W1_CELL_CV_INTENSITY':'','W1_PC_MEAN_SIZE':'','W1_CELL_MEAN_SIZE':'','W1_PC_RANGE_SIZE':'','W1_CELL_RANGE_SIZE':'','W1_PC_CV_SIZE':'','W1_CELL_CV_SIZE':'','W2_PC_COUNT':'0','W2_CELL_COUNT':'100','W2_PC_MEAN_INTENSITY':'','W2_CELL_MEAN_INTENSITY':'','W2_PC_RANGE_INTENSITY':'0','W2_CELL_RANGE_INTENSITY':'0','W2_PC_CV_INTENSITY':'','W2_CELL_CV_INTENSITY':'','W2_PC_MEAN_SIZE':'','W2_CELL_MEAN_SIZE':'','W2_PC_RANGE_SIZE':'','W2_CELL_RANGE_SIZE':'','W2_PC_CV_SIZE':'','W2_CELL_CV_SIZE':'','STATUS':'','LANE_TOTAL_AREA_UM2':'326035983.8748','LANE_NOISE_AREA_UM2':'82232170.4712','LANE_NOISE_RATIO_PERCENT':'95.73','LANE_NOISE_OBJECT_COUNT':'71','W1_NOISE_AREA_UM2':'8323637.364','W2_NOISE_AREA_UM2':'8315160.7508','W1_NOISE_RATIO_PERCENT':'100.0','W2_NOISE_RATIO_PERCENT':'99.8993635494474','W1_NOISE_OBJECT_COUNT':'0','W2_NOISE_OBJECT_COUNT':'0','LANE_SMALL_NOISE_OBJECT_COUNT':'71','LANE_MEDIUM_NOISE_OBJECT_COUNT':'0','LANE_LARGE_NOISE_OBJECT_COUNT':'0','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'0','IMPROC':'ImprocSalm01','VERSION':'"+slImprocVersion+"','ERROR_CODE':'B01','IE_COLLECTION_SITE_ID':'1001043','IE_SAMPLE_MATRIX_ID':''},{'LANE':'10','PATHOGEN':' Salmonella Presence/Absence    ','SAMPLEID':'Test"+lstSampleID.get(3)+"','SCANDATETIME':'"+date100+"','OUTCOME':'pass','STATUSCODE':'3','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'B01','CARTRIDGEID':'"+CartridgeID+"','EXPERIMENTID':'','INSTRUMENTID':'"+InstrumentID+"','USERID':'"+UserID+"','RUN_ID':'"+slRunID+"','RUN_TYPE':'SCRIPT_1001a Salmonella Probes','MPN_CALCULATION_TYPE':'0','DILUTION_FACTOR':'0.01','LANE_NO':'10','DATE':'2020-06-03','TIME':'10:45:43.914898','W1_PC_COUNT':'0','W1_CELL_COUNT':'0','W1_PC_MEAN_INTENSITY':'','W1_CELL_MEAN_INTENSITY':'','W1_PC_RANGE_INTENSITY':'0','W1_CELL_RANGE_INTENSITY':'0','W1_PC_CV_INTENSITY':'','W1_CELL_CV_INTENSITY':'','W1_PC_MEAN_SIZE':'','W1_CELL_MEAN_SIZE':'','W1_PC_RANGE_SIZE':'','W1_CELL_RANGE_SIZE':'','W1_PC_CV_SIZE':'','W1_CELL_CV_SIZE':'','W2_PC_COUNT':'0','W2_CELL_COUNT':'100','W2_PC_MEAN_INTENSITY':'','W2_CELL_MEAN_INTENSITY':'','W2_PC_RANGE_INTENSITY':'0','W2_CELL_RANGE_INTENSITY':'0','W2_PC_CV_INTENSITY':'','W2_CELL_CV_INTENSITY':'','W2_PC_MEAN_SIZE':'','W2_CELL_MEAN_SIZE':'','W2_PC_RANGE_SIZE':'','W2_CELL_RANGE_SIZE':'','W2_PC_CV_SIZE':'','W2_CELL_CV_SIZE':'','STATUS':'','LANE_TOTAL_AREA_UM2':'326035983.8748','LANE_NOISE_AREA_UM2':'82232170.4712','LANE_NOISE_RATIO_PERCENT':'95.73','LANE_NOISE_OBJECT_COUNT':'71','W1_NOISE_AREA_UM2':'8323637.364','W2_NOISE_AREA_UM2':'8315160.7508','W1_NOISE_RATIO_PERCENT':'100.0','W2_NOISE_RATIO_PERCENT':'99.8993635494474','W1_NOISE_OBJECT_COUNT':'0','W2_NOISE_OBJECT_COUNT':'0','LANE_SMALL_NOISE_OBJECT_COUNT':'71','LANE_MEDIUM_NOISE_OBJECT_COUNT':'0','LANE_LARGE_NOISE_OBJECT_COUNT':'0','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'0','IMPROC':'ImprocSalm01','VERSION':'"+slImprocVersion+"','ERROR_CODE':'B01','IE_COLLECTION_SITE_ID':'1001043','IE_SAMPLE_MATRIX_ID':''},{'LANE':'11','PATHOGEN':' Salmonella Presence/Absence    ','SAMPLEID':'Test"+lstSampleID.get(3)+"','SCANDATETIME':'"+date100+"','OUTCOME':'pass','STATUSCODE':'3','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'B01','CARTRIDGEID':'"+CartridgeID+"','EXPERIMENTID':'','INSTRUMENTID':'"+InstrumentID+"','USERID':'"+UserID+"','RUN_ID':'"+slRunID+"','RUN_TYPE':'SCRIPT_1001a Salmonella Probes','MPN_CALCULATION_TYPE':'0','DILUTION_FACTOR':'0.01','LANE_NO':'11','DATE':'2020-06-03','TIME':'10:45:43.914898','W1_PC_COUNT':'0','W1_CELL_COUNT':'0','W1_PC_MEAN_INTENSITY':'','W1_CELL_MEAN_INTENSITY':'','W1_PC_RANGE_INTENSITY':'0','W1_CELL_RANGE_INTENSITY':'0','W1_PC_CV_INTENSITY':'','W1_CELL_CV_INTENSITY':'','W1_PC_MEAN_SIZE':'','W1_CELL_MEAN_SIZE':'','W1_PC_RANGE_SIZE':'','W1_CELL_RANGE_SIZE':'','W1_PC_CV_SIZE':'','W1_CELL_CV_SIZE':'','W2_PC_COUNT':'0','W2_CELL_COUNT':'100','W2_PC_MEAN_INTENSITY':'','W2_CELL_MEAN_INTENSITY':'','W2_PC_RANGE_INTENSITY':'0','W2_CELL_RANGE_INTENSITY':'0','W2_PC_CV_INTENSITY':'','W2_CELL_CV_INTENSITY':'','W2_PC_MEAN_SIZE':'','W2_CELL_MEAN_SIZE':'','W2_PC_RANGE_SIZE':'','W2_CELL_RANGE_SIZE':'','W2_PC_CV_SIZE':'','W2_CELL_CV_SIZE':'','STATUS':'','LANE_TOTAL_AREA_UM2':'326035983.8748','LANE_NOISE_AREA_UM2':'82232170.4712','LANE_NOISE_RATIO_PERCENT':'95.73','LANE_NOISE_OBJECT_COUNT':'71','W1_NOISE_AREA_UM2':'8323637.364','W2_NOISE_AREA_UM2':'8315160.7508','W1_NOISE_RATIO_PERCENT':'100.0','W2_NOISE_RATIO_PERCENT':'99.8993635494474','W1_NOISE_OBJECT_COUNT':'0','W2_NOISE_OBJECT_COUNT':'0','LANE_SMALL_NOISE_OBJECT_COUNT':'71','LANE_MEDIUM_NOISE_OBJECT_COUNT':'0','LANE_LARGE_NOISE_OBJECT_COUNT':'0','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'0','IMPROC':'ImprocSalm01','VERSION':'"+slImprocVersion+"','ERROR_CODE':'B01','IE_COLLECTION_SITE_ID':'1001043','IE_SAMPLE_MATRIX_ID':''},{'LANE':'12','PATHOGEN':' Salmonella Presence/Absence    ','SAMPLEID':'Test"+lstSampleID.get(3)+"','SCANDATETIME':'"+date100+"','OUTCOME':'pass','STATUSCODE':'3','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'B01','CARTRIDGEID':'"+CartridgeID+"','EXPERIMENTID':'','INSTRUMENTID':'"+InstrumentID+"','USERID':'"+UserID+"','RUN_ID':'"+slRunID+"','RUN_TYPE':'SCRIPT_1001a Salmonella Probes','MPN_CALCULATION_TYPE':'0','DILUTION_FACTOR':'0.01','LANE_NO':'12','DATE':'2020-06-03','TIME':'10:45:43.914898','W1_PC_COUNT':'0','W1_CELL_COUNT':'0','W1_PC_MEAN_INTENSITY':'','W1_CELL_MEAN_INTENSITY':'','W1_PC_RANGE_INTENSITY':'0','W1_CELL_RANGE_INTENSITY':'0','W1_PC_CV_INTENSITY':'','W1_CELL_CV_INTENSITY':'','W1_PC_MEAN_SIZE':'','W1_CELL_MEAN_SIZE':'','W1_PC_RANGE_SIZE':'','W1_CELL_RANGE_SIZE':'','W1_PC_CV_SIZE':'','W1_CELL_CV_SIZE':'','W2_PC_COUNT':'0','W2_CELL_COUNT':'100','W2_PC_MEAN_INTENSITY':'','W2_CELL_MEAN_INTENSITY':'','W2_PC_RANGE_INTENSITY':'0','W2_CELL_RANGE_INTENSITY':'0','W2_PC_CV_INTENSITY':'','W2_CELL_CV_INTENSITY':'','W2_PC_MEAN_SIZE':'','W2_CELL_MEAN_SIZE':'','W2_PC_RANGE_SIZE':'','W2_CELL_RANGE_SIZE':'','W2_PC_CV_SIZE':'','W2_CELL_CV_SIZE':'','STATUS':'','LANE_TOTAL_AREA_UM2':'326035983.8748','LANE_NOISE_AREA_UM2':'82232170.4712','LANE_NOISE_RATIO_PERCENT':'95.73','LANE_NOISE_OBJECT_COUNT':'71','W1_NOISE_AREA_UM2':'8323637.364','W2_NOISE_AREA_UM2':'8315160.7508','W1_NOISE_RATIO_PERCENT':'100.0','W2_NOISE_RATIO_PERCENT':'99.8993635494474','W1_NOISE_OBJECT_COUNT':'0','W2_NOISE_OBJECT_COUNT':'0','LANE_SMALL_NOISE_OBJECT_COUNT':'71','LANE_MEDIUM_NOISE_OBJECT_COUNT':'0','LANE_LARGE_NOISE_OBJECT_COUNT':'0','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'0','IMPROC':'ImprocSalm01','VERSION':'"+slImprocVersion+"','ERROR_CODE':'B01','IE_COLLECTION_SITE_ID':'1001043','IE_SAMPLE_MATRIX_ID':''}]",		
							"ImprocSalm01", 1, "Salmonella", true, "AN-API_FileUpload-01: Run File Upload API with unique Run ID", "This test case will run ingestion with unique run id", "Run API with unique RUN ID and unique Sample ID", "File Upload API ran successfully; File was ingested", "File Upload API failed to run", "AN-SL-01: Verify that data is ingested after running API with Unique Run ID", "This test case will verify that data is ingested after running file upload API")));     

	public static String slPNGFileName = "Salmonella Run Timeline - ";
	public static String slCSVFileName = "Salmonella Log - ";
	public static String slCSVAuditFileName = "Salmonella Audit Log - ";
	public static String slSampleMetaData = "Sample Metadata Upload Template";

	public static ArrayList<NormalIngestionModel> lstNormalIngestion = new ArrayList<>(); 
	public static ArrayList<SalmonellaModel> lstSalmonellaSearch = new ArrayList<>();
	public static ArrayList<SalmonellaModel> lstSalmonellaWildcardSearch = new ArrayList<>();
	public static ArrayList<SalmonellaModel> lstSalmonellaDateSearch = new ArrayList<>();
	public static ArrayList<SalmonellaModel> lstSalmonellaDateEnter = new ArrayList<>();
	public static ArrayList<SalmonellaModel> lstSalmonellaRowCount = new ArrayList<>();
	public static ArrayList<SalmonellaModel> lstSalmonellaPagination = new ArrayList<>();
	public static ArrayList<SalmonellaModel> lstSalmonellaSorting = new ArrayList<>();
	public static ArrayList<SalmonellaModel> lstSalmonellaFieldAccess = new ArrayList<>();
	public static ArrayList<SalmonellaModel> lstSalmonellaContexualCheck = new ArrayList<>();
	public static ArrayList<SalmonellaModel> lstSalmonellaLock = new ArrayList<>();
	

	////////////////////////////////////////////////////End Salmonella Ingest Variables/////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////Coccidia Ingest Variables//////////////////////////////////////////////////////////////////////

	public static ArrayList<APIModel> lstCoccidiaIngest = new ArrayList<>(
			Arrays.asList(
					new APIModel(AnnouncementRunID, "fab8cc1ea3f6db40c4555c3dfab09a34d2fa2b53cd66af72284af1dacb266910", 
							"20200120202842_PSN0009_sammyproscess_oaeng_improc.csv", "csv",                                                                                                        //03132020_PSN16_21612_lis1
							"TEFORV9OTyxEQVRFLFRJTUUsVzFfUENfQ09VTlQsVzFfQ0VMTF9DT1VOVCxXMV9QQ19NRUFOX0lOVEVOU0lUWSxXMV9DRUxMX01FQU5fSU5URU5TSVRZLFcxX1BDX1JBTkdFX0lOVEVOU0lUWSxXMV9DRUxMX1JBTkdFX0lOVEVOU0lUWSxXMV9QQ19DVl9JTlRFTlNJVFksVzFfQ0VMTF9DVl9JTlRFTlNJVFksVzFfUENfTUVBTl9TSVpFLFcxX0NFTExfTUVBTl9TSVpFLFcxX1BDX1JBTkdFX1NJWkUsVzFfQ0VMTF9SQU5HRV9TSVpFLFcxX1BDX0NWX1NJWkUsVzFfQ0VMTF9DVl9TSVpFLFcyX1BDX0NPVU5ULFcyX0NFTExfQ09VTlQsVzJfUENfTUVBTl9JTlRFTlNJVFksVzJfQ0VMTF9NRUFOX0lOVEVOU0lUWSxXMl9QQ19SQU5HRV9JTlRFTlNJVFksVzJfQ0VMTF9SQU5HRV9JTlRFTlNJVFksVzJfUENfQ1ZfSU5URU5TSVRZLFcyX0NFTExfQ1ZfSU5URU5TSVRZLFcyX1BDX01FQU5fU0laRSxXMl9DRUxMX01FQU5fU0laRSxXMl9QQ19SQU5HRV9TSVpFLFcyX0NFTExfUkFOR0VfU0laRSxXMl9QQ19DVl9TSVpFLFcyX0NFTExfQ1ZfU0laRSxTVEFUVVMsTEFORV9UT1RBTF9BUkVBX1VNMixMQU5FX05PSVNFX0FSRUFfVU0yLExBTkVfTk9JU0VfUkFUSU9fUEVSQ0VOVCxMQU5FX05PSVNFX09CSkVDVF9DT1VOVCxXMV9OT0lTRV9BUkVBX1VNMixXMl9OT0lTRV9BUkVBX1VNMixXMV9OT0lTRV9SQVRJT19QRVJDRU5ULFcyX05PSVNFX1JBVElPX1BFUkNFTlQsVzFfTk9JU0VfT0JKRUNUX0NPVU5ULFcyX05PSVNFX09CSkVDVF9DT1VOVCxMQU5FX1NNQUxMX05PSVNFX09CSkVDVF9DT1VOVCxMQU5FX01FRElVTV9OT0lTRV9PQkpFQ1RfQ09VTlQsTEFORV9MQVJHRV9OT0lTRV9PQkpFQ1RfQ09VTlQsTEFORV9FWFRSQV9MQVJHRV9OT0lTRV9PQkpFQ1RfQ09VTlQsRVJST1JfQ09ERQ0KMSwyMDIwLTAxLTIwLDIwOjM0OjI0LjU1NjAxNCw3LDU5LDExNy4yNzgzNjczNDY5Mzg3OCwyMi40MTk5Mjg4MjU2MjI3NzYsMjMzLDEyOCwwLjYwMDUzMjk4NTc4OTI5MTMsMS4wNTczODE3MTE4OTc4NzY4LDE1My4xNDI4NTcxNDI4NTcxNCw2Ljg5Mjg1NzE0Mjg1NzE0MywxNzguMDcsMTIuNzk0OTk5OTk5OTk5OTk4LDAuNDUzNDc2MjM3NjMwMzEzMTQsMC42NjI2NjU3Mzk3MDE0MTg4LDIxLDI1LDgyLjU1NDY1NTg3MDQ0NTM0LDEwLjMxOTg2NTMxOTg2NTMyLDI1MSw1MCwwLjk0ODIzNTAwNzExMDYwMSwwLjgwMDQxODA5OTgwNDYzNDcsNjguNjkwNDc2MTkwNDc2MTksOC42NDcwNTg4MjM1Mjk0MTEsMTI0LjIsMTMuMjM5OTk5OTk5OTk5OTk4LDAuODM0MTA4MTAzNDA1MTA3NCwwLjQ1ODIxMjYyOTA4OTc2NjMsNTQwNjI2MTEzLjAsMTIxOTE0My41LDAuMjMsNDUxLDUwMzMuNSwxODk4Ny4wLDAuMDA2MjU5MDU0MDg1MTQxMDQyLDAuMDIzNjA5OTQ1MzQ5MDc1NzgyLDIzLDU1LDQ1MCwxLDAsMCwNCjIsMjAyMC0wMS0yMCwyMDozNTozMC45NTg4NDMsMCwwLG5hbixuYW4sMCwwLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sMCwxLG5hbixuYW4sMCwwLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sNTM5NzI2NjA3LjAsNDU4NDcuMCwwLjAxLDE5LDAuMCwwLjAsMC4wLDAuMCwwLDAsMTksMCwwLDAsDQozLDIwMjAtMDEtMjAsMjA6Mzc6MjEuMjQ4ODg5LDAsMCxuYW4sbmFuLDAsMCxuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLDAsMzMsbmFuLDExNS45OTIzNjY0MTIyMTM3NCwwLDEzMCxuYW4sMC4yOTg1MjI3MzE4MDI4NTE1NSxuYW4sOC43LG5hbiwxMS4zMTk5OTk5OTk5OTk5OTksbmFuLDAuNTAzMTI4MDE0NDQyMjgyNiw1Mzg2NzMzOTguMCwyMDc3NzQuMCwwLjA0LDI2LDAuMCwwLjAsMC4wLDAuMCwwLDAsMjUsMSwwLDAsDQo=", 
					        "[{'LANE':'1','PATHOGEN':'Coccidia','SAMPLEID':'Test"+lstSampleID.get(0)+"','SCANDATETIME':'"+date100+"','FIGUREOFMERIT':'0','OUTCOME':'','STATUSCODE':'','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'','CARTRIDGEID':'"+CartridgeID+"','EXPERIMENTID':'','INSTRUMENTID':'"+InstrumentID+"','USERID':'"+UserID+"','RUN_ID':'"+RunID+"','RUN_TYPE':'SCRIPT_swtest','LANE_NO':'1','DATE':'','TIME':'','LANE_TOTAL_OOCYST_COUNT':'13','LANE_TOTAL_OOCYST_MEAN_INTENSITY':'','LANE_TOTAL_OOCYST_RANGE_INTENSITY':'','LANE_TOTAL_OOCYST_CV_INTENSITY':'','LANE_TOTAL_OOCYST_MEAN_AREA':'','LANE_TOTAL_OOCYST_RANGE_AREA':'','LANE_TOTAL_OOCYST_CV_AREA':'','LANE_TOTAL_OOCYST_MEAN_MAJORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MAJORAXIS':'','LANE_TOTAL_OOCYST_CV_MAJORAXIS':'','LANE_TOTAL_OOCYST_MEAN_MINORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MINORAXIS':'','LANE_TOTAL_OOCYST_CV_MINORAXIS':'','LANE_TOTAL_OOCYST_COUNT_SPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNSPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_SMALL_OOCYST_COUNT':'130','LANE_SMALL_OOCYST_MEAN_INTENSITY':'','LANE_SMALL_OOCYST_RANGE_INTENSITY':'','LANE_SMALL_OOCYST_CV_INTENSITY':'','LANE_SMALL_OOCYST_MEAN_AREA':'','LANE_SMALL_OOCYST_RANGE_AREA':'','LANE_SMALL_OOCYST_CV_AREA':'','LANE_SMALL_OOCYST_MEAN_MAJORAXIS':'','LANE_SMALL_OOCYST_RANGE_MAJORAXIS':'','LANE_SMALL_OOCYST_CV_MAJORAXIS':'','LANE_SMALL_OOCYST_MEAN_MINORAXIS':'','LANE_SMALL_OOCYST_RANGE_MINORAXIS':'','LANE_SMALL_OOCYST_CV_MINORAXIS':'','LANE_SMALL_OOCYST_COUNT_SPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNSPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_MEDIUM_OOCYST_COUNT':'130','LANE_MEDIUM_OOCYST_MEAN_INTENSITY':'','LANE_MEDIUM_OOCYST_RANGE_INTENSITY':'','LANE_MEDIUM_OOCYST_CV_INTENSITY':'','LANE_MEDIUM_OOCYST_MEAN_AREA':'','LANE_MEDIUM_OOCYST_RANGE_AREA':'','LANE_MEDIUM_OOCYST_CV_AREA':'','LANE_MEDIUM_OOCYST_MEAN_MAJORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MAJORAXIS':'','LANE_MEDIUM_OOCYST_CV_MAJORAXIS':'','LANE_MEDIUM_OOCYST_MEAN_MINORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MINORAXIS':'','LANE_MEDIUM_OOCYST_CV_MINORAXIS':'','LANE_MEDIUM_OOCYST_COUNT_SPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNSPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_LARGE_OOCYST_COUNT':'120','LANE_LARGE_OOCYST_MEAN_INTENSITY':'','LANE_LARGE_OOCYST_RANGE_INTENSITY':'','LANE_LARGE_OOCYST_CV_INTENSITY':'','LANE_LARGE_OOCYST_MEAN_AREA':'','LANE_LARGE_OOCYST_RANGE_AREA':'','LANE_LARGE_OOCYST_CV_AREA':'','LANE_LARGE_OOCYST_MEAN_MAJORAXIS':'','LANE_LARGE_OOCYST_RANGE_MAJORAXIS':'','LANE_LARGE_OOCYST_CV_MAJORAXIS':'','LANE_LARGE_OOCYST_MEAN_MINORAXIS':'','LANE_LARGE_OOCYST_RANGE_MINORAXIS':'','LANE_LARGE_OOCYST_CV_MINORAXIS':'','LANE_LARGE_OOCYST_COUNT_SPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNSPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNCERTAINSPORULATION':'','STATUS':'','LANE_TOTAL_AREA_UM2':'','LANE_NOISE_AREA_UM2':'','LANE_NOISE_RATIO_PERCENT':'','LANE_NOISE_OBJECT_COUNT':'','LANE_SMALL_NOISE_OBJECT_COUNT':'','LANE_MEDIUM_NOISE_OBJECT_COUNT':'','LANE_LARGE_NOISE_OBJECT_COUNT':'','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'','IMPROC':'ImprocCocc01','VERSION':'"+ImprocVersion+"','ERROR_CODE':'Exception','IE_COLLECTION_SITE_ID':''},{'LANE':'2','PATHOGEN':'Coccidia','SAMPLEID':'Test"+lstSampleID.get(0)+"','SCANDATETIME':'"+date100+"','FIGUREOFMERIT':'0','OUTCOME':'','STATUSCODE':'','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'','CARTRIDGEID':'"+CartridgeID+"','EXPERIMENTID':'','INSTRUMENTID':'"+InstrumentID+"','USERID':'"+UserID+"','RUN_ID':'"+RunID+"','RUN_TYPE':'SCRIPT_swtest','LANE_NO':'2','DATE':'','TIME':'','LANE_TOTAL_OOCYST_COUNT':'103','LANE_TOTAL_OOCYST_MEAN_INTENSITY':'','LANE_TOTAL_OOCYST_RANGE_INTENSITY':'','LANE_TOTAL_OOCYST_CV_INTENSITY':'','LANE_TOTAL_OOCYST_MEAN_AREA':'','LANE_TOTAL_OOCYST_RANGE_AREA':'','LANE_TOTAL_OOCYST_CV_AREA':'','LANE_TOTAL_OOCYST_MEAN_MAJORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MAJORAXIS':'','LANE_TOTAL_OOCYST_CV_MAJORAXIS':'','LANE_TOTAL_OOCYST_MEAN_MINORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MINORAXIS':'','LANE_TOTAL_OOCYST_CV_MINORAXIS':'','LANE_TOTAL_OOCYST_COUNT_SPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNSPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_SMALL_OOCYST_COUNT':'13','LANE_SMALL_OOCYST_MEAN_INTENSITY':'','LANE_SMALL_OOCYST_RANGE_INTENSITY':'','LANE_SMALL_OOCYST_CV_INTENSITY':'','LANE_SMALL_OOCYST_MEAN_AREA':'','LANE_SMALL_OOCYST_RANGE_AREA':'','LANE_SMALL_OOCYST_CV_AREA':'','LANE_SMALL_OOCYST_MEAN_MAJORAXIS':'','LANE_SMALL_OOCYST_RANGE_MAJORAXIS':'','LANE_SMALL_OOCYST_CV_MAJORAXIS':'','LANE_SMALL_OOCYST_MEAN_MINORAXIS':'','LANE_SMALL_OOCYST_RANGE_MINORAXIS':'','LANE_SMALL_OOCYST_CV_MINORAXIS':'','LANE_SMALL_OOCYST_COUNT_SPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNSPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_MEDIUM_OOCYST_COUNT':'13','LANE_MEDIUM_OOCYST_MEAN_INTENSITY':'','LANE_MEDIUM_OOCYST_RANGE_INTENSITY':'','LANE_MEDIUM_OOCYST_CV_INTENSITY':'','LANE_MEDIUM_OOCYST_MEAN_AREA':'','LANE_MEDIUM_OOCYST_RANGE_AREA':'','LANE_MEDIUM_OOCYST_CV_AREA':'','LANE_MEDIUM_OOCYST_MEAN_MAJORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MAJORAXIS':'','LANE_MEDIUM_OOCYST_CV_MAJORAXIS':'','LANE_MEDIUM_OOCYST_MEAN_MINORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MINORAXIS':'','LANE_MEDIUM_OOCYST_CV_MINORAXIS':'','LANE_MEDIUM_OOCYST_COUNT_SPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNSPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_LARGE_OOCYST_COUNT':'120','LANE_LARGE_OOCYST_MEAN_INTENSITY':'','LANE_LARGE_OOCYST_RANGE_INTENSITY':'','LANE_LARGE_OOCYST_CV_INTENSITY':'','LANE_LARGE_OOCYST_MEAN_AREA':'','LANE_LARGE_OOCYST_RANGE_AREA':'','LANE_LARGE_OOCYST_CV_AREA':'','LANE_LARGE_OOCYST_MEAN_MAJORAXIS':'','LANE_LARGE_OOCYST_RANGE_MAJORAXIS':'','LANE_LARGE_OOCYST_CV_MAJORAXIS':'','LANE_LARGE_OOCYST_MEAN_MINORAXIS':'','LANE_LARGE_OOCYST_RANGE_MINORAXIS':'','LANE_LARGE_OOCYST_CV_MINORAXIS':'','LANE_LARGE_OOCYST_COUNT_SPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNSPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNCERTAINSPORULATION':'','STATUS':'','LANE_TOTAL_AREA_UM2':'','LANE_NOISE_AREA_UM2':'','LANE_NOISE_RATIO_PERCENT':'','LANE_NOISE_OBJECT_COUNT':'','LANE_SMALL_NOISE_OBJECT_COUNT':'','LANE_MEDIUM_NOISE_OBJECT_COUNT':'','LANE_LARGE_NOISE_OBJECT_COUNT':'','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'','IMPROC':'ImprocCocc01','VERSION':'"+ImprocVersion+"','ERROR_CODE':'Exception','IE_COLLECTION_SITE_ID':''},{'LANE':'3','PATHOGEN':'Coccidia','SAMPLEID':'Test"+lstSampleID.get(0)+"','SCANDATETIME':'"+date100+"','FIGUREOFMERIT':'0','OUTCOME':'','STATUSCODE':'','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'','CARTRIDGEID':'"+CartridgeID+"','EXPERIMENTID':'','INSTRUMENTID':'"+InstrumentID+"','USERID':'"+UserID+"','RUN_ID':'"+RunID+"','RUN_TYPE':'SCRIPT_swtest','LANE_NO':'3','DATE':'','TIME':'','LANE_TOTAL_OOCYST_COUNT':'130','LANE_TOTAL_OOCYST_MEAN_INTENSITY':'','LANE_TOTAL_OOCYST_RANGE_INTENSITY':'','LANE_TOTAL_OOCYST_CV_INTENSITY':'','LANE_TOTAL_OOCYST_MEAN_AREA':'','LANE_TOTAL_OOCYST_RANGE_AREA':'','LANE_TOTAL_OOCYST_CV_AREA':'','LANE_TOTAL_OOCYST_MEAN_MAJORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MAJORAXIS':'','LANE_TOTAL_OOCYST_CV_MAJORAXIS':'','LANE_TOTAL_OOCYST_MEAN_MINORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MINORAXIS':'','LANE_TOTAL_OOCYST_CV_MINORAXIS':'','LANE_TOTAL_OOCYST_COUNT_SPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNSPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_SMALL_OOCYST_COUNT':'13','LANE_SMALL_OOCYST_MEAN_INTENSITY':'','LANE_SMALL_OOCYST_RANGE_INTENSITY':'','LANE_SMALL_OOCYST_CV_INTENSITY':'','LANE_SMALL_OOCYST_MEAN_AREA':'','LANE_SMALL_OOCYST_RANGE_AREA':'','LANE_SMALL_OOCYST_CV_AREA':'','LANE_SMALL_OOCYST_MEAN_MAJORAXIS':'','LANE_SMALL_OOCYST_RANGE_MAJORAXIS':'','LANE_SMALL_OOCYST_CV_MAJORAXIS':'','LANE_SMALL_OOCYST_MEAN_MINORAXIS':'','LANE_SMALL_OOCYST_RANGE_MINORAXIS':'','LANE_SMALL_OOCYST_CV_MINORAXIS':'','LANE_SMALL_OOCYST_COUNT_SPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNSPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_MEDIUM_OOCYST_COUNT':'13','LANE_MEDIUM_OOCYST_MEAN_INTENSITY':'','LANE_MEDIUM_OOCYST_RANGE_INTENSITY':'','LANE_MEDIUM_OOCYST_CV_INTENSITY':'','LANE_MEDIUM_OOCYST_MEAN_AREA':'','LANE_MEDIUM_OOCYST_RANGE_AREA':'','LANE_MEDIUM_OOCYST_CV_AREA':'','LANE_MEDIUM_OOCYST_MEAN_MAJORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MAJORAXIS':'','LANE_MEDIUM_OOCYST_CV_MAJORAXIS':'','LANE_MEDIUM_OOCYST_MEAN_MINORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MINORAXIS':'','LANE_MEDIUM_OOCYST_CV_MINORAXIS':'','LANE_MEDIUM_OOCYST_COUNT_SPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNSPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_LARGE_OOCYST_COUNT':'120','LANE_LARGE_OOCYST_MEAN_INTENSITY':'','LANE_LARGE_OOCYST_RANGE_INTENSITY':'','LANE_LARGE_OOCYST_CV_INTENSITY':'','LANE_LARGE_OOCYST_MEAN_AREA':'','LANE_LARGE_OOCYST_RANGE_AREA':'','LANE_LARGE_OOCYST_CV_AREA':'','LANE_LARGE_OOCYST_MEAN_MAJORAXIS':'','LANE_LARGE_OOCYST_RANGE_MAJORAXIS':'','LANE_LARGE_OOCYST_CV_MAJORAXIS':'','LANE_LARGE_OOCYST_MEAN_MINORAXIS':'','LANE_LARGE_OOCYST_RANGE_MINORAXIS':'','LANE_LARGE_OOCYST_CV_MINORAXIS':'','LANE_LARGE_OOCYST_COUNT_SPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNSPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNCERTAINSPORULATION':'','STATUS':'','LANE_TOTAL_AREA_UM2':'','LANE_NOISE_AREA_UM2':'','LANE_NOISE_RATIO_PERCENT':'','LANE_NOISE_OBJECT_COUNT':'','LANE_SMALL_NOISE_OBJECT_COUNT':'','LANE_MEDIUM_NOISE_OBJECT_COUNT':'','LANE_LARGE_NOISE_OBJECT_COUNT':'','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'','IMPROC':'ImprocCocc01','VERSION':'"+ImprocVersion+"','ERROR_CODE':'Exception','IE_COLLECTION_SITE_ID':''},{'LANE':'4','PATHOGEN':'Coccidia','SAMPLEID':'Test"+lstSampleID.get(1)+"','SCANDATETIME':'"+date100+"','FIGUREOFMERIT':'0','OUTCOME':'','STATUSCODE':'','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'','CARTRIDGEID':'"+CartridgeID+"','EXPERIMENTID':'','INSTRUMENTID':'"+InstrumentID+"','USERID':'"+UserID+"','RUN_ID':'"+RunID+"','RUN_TYPE':'SCRIPT_swtest','LANE_NO':'4','DATE':'','TIME':'','LANE_TOTAL_OOCYST_COUNT':'103','LANE_TOTAL_OOCYST_MEAN_INTENSITY':'','LANE_TOTAL_OOCYST_RANGE_INTENSITY':'','LANE_TOTAL_OOCYST_CV_INTENSITY':'','LANE_TOTAL_OOCYST_MEAN_AREA':'','LANE_TOTAL_OOCYST_RANGE_AREA':'','LANE_TOTAL_OOCYST_CV_AREA':'','LANE_TOTAL_OOCYST_MEAN_MAJORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MAJORAXIS':'','LANE_TOTAL_OOCYST_CV_MAJORAXIS':'','LANE_TOTAL_OOCYST_MEAN_MINORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MINORAXIS':'','LANE_TOTAL_OOCYST_CV_MINORAXIS':'','LANE_TOTAL_OOCYST_COUNT_SPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNSPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_SMALL_OOCYST_COUNT':'13','LANE_SMALL_OOCYST_MEAN_INTENSITY':'','LANE_SMALL_OOCYST_RANGE_INTENSITY':'','LANE_SMALL_OOCYST_CV_INTENSITY':'','LANE_SMALL_OOCYST_MEAN_AREA':'','LANE_SMALL_OOCYST_RANGE_AREA':'','LANE_SMALL_OOCYST_CV_AREA':'','LANE_SMALL_OOCYST_MEAN_MAJORAXIS':'','LANE_SMALL_OOCYST_RANGE_MAJORAXIS':'','LANE_SMALL_OOCYST_CV_MAJORAXIS':'','LANE_SMALL_OOCYST_MEAN_MINORAXIS':'','LANE_SMALL_OOCYST_RANGE_MINORAXIS':'','LANE_SMALL_OOCYST_CV_MINORAXIS':'','LANE_SMALL_OOCYST_COUNT_SPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNSPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_MEDIUM_OOCYST_COUNT':'13','LANE_MEDIUM_OOCYST_MEAN_INTENSITY':'','LANE_MEDIUM_OOCYST_RANGE_INTENSITY':'','LANE_MEDIUM_OOCYST_CV_INTENSITY':'','LANE_MEDIUM_OOCYST_MEAN_AREA':'','LANE_MEDIUM_OOCYST_RANGE_AREA':'','LANE_MEDIUM_OOCYST_CV_AREA':'','LANE_MEDIUM_OOCYST_MEAN_MAJORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MAJORAXIS':'','LANE_MEDIUM_OOCYST_CV_MAJORAXIS':'','LANE_MEDIUM_OOCYST_MEAN_MINORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MINORAXIS':'','LANE_MEDIUM_OOCYST_CV_MINORAXIS':'','LANE_MEDIUM_OOCYST_COUNT_SPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNSPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_LARGE_OOCYST_COUNT':'120','LANE_LARGE_OOCYST_MEAN_INTENSITY':'','LANE_LARGE_OOCYST_RANGE_INTENSITY':'','LANE_LARGE_OOCYST_CV_INTENSITY':'','LANE_LARGE_OOCYST_MEAN_AREA':'','LANE_LARGE_OOCYST_RANGE_AREA':'','LANE_LARGE_OOCYST_CV_AREA':'','LANE_LARGE_OOCYST_MEAN_MAJORAXIS':'','LANE_LARGE_OOCYST_RANGE_MAJORAXIS':'','LANE_LARGE_OOCYST_CV_MAJORAXIS':'','LANE_LARGE_OOCYST_MEAN_MINORAXIS':'','LANE_LARGE_OOCYST_RANGE_MINORAXIS':'','LANE_LARGE_OOCYST_CV_MINORAXIS':'','LANE_LARGE_OOCYST_COUNT_SPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNSPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNCERTAINSPORULATION':'','STATUS':'','LANE_TOTAL_AREA_UM2':'','LANE_NOISE_AREA_UM2':'','LANE_NOISE_RATIO_PERCENT':'','LANE_NOISE_OBJECT_COUNT':'','LANE_SMALL_NOISE_OBJECT_COUNT':'','LANE_MEDIUM_NOISE_OBJECT_COUNT':'','LANE_LARGE_NOISE_OBJECT_COUNT':'','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'','IMPROC':'ImprocCocc01','VERSION':'"+ImprocVersion+"','ERROR_CODE':'Exception','IE_COLLECTION_SITE_ID':''},{'LANE':'5','PATHOGEN':'Coccidia','SAMPLEID':'Test"+lstSampleID.get(1)+"','SCANDATETIME':'"+date100+"','FIGUREOFMERIT':'0','OUTCOME':'','STATUSCODE':'','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'','CARTRIDGEID':'"+CartridgeID+"','EXPERIMENTID':'','INSTRUMENTID':'"+InstrumentID+"','USERID':'"+UserID+"','RUN_ID':'"+RunID+"','RUN_TYPE':'SCRIPT_swtest','LANE_NO':'5','DATE':'','TIME':'','LANE_TOTAL_OOCYST_COUNT':'143','LANE_TOTAL_OOCYST_MEAN_INTENSITY':'','LANE_TOTAL_OOCYST_RANGE_INTENSITY':'','LANE_TOTAL_OOCYST_CV_INTENSITY':'','LANE_TOTAL_OOCYST_MEAN_AREA':'','LANE_TOTAL_OOCYST_RANGE_AREA':'','LANE_TOTAL_OOCYST_CV_AREA':'','LANE_TOTAL_OOCYST_MEAN_MAJORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MAJORAXIS':'','LANE_TOTAL_OOCYST_CV_MAJORAXIS':'','LANE_TOTAL_OOCYST_MEAN_MINORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MINORAXIS':'','LANE_TOTAL_OOCYST_CV_MINORAXIS':'','LANE_TOTAL_OOCYST_COUNT_SPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNSPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_SMALL_OOCYST_COUNT':'13','LANE_SMALL_OOCYST_MEAN_INTENSITY':'','LANE_SMALL_OOCYST_RANGE_INTENSITY':'','LANE_SMALL_OOCYST_CV_INTENSITY':'','LANE_SMALL_OOCYST_MEAN_AREA':'','LANE_SMALL_OOCYST_RANGE_AREA':'','LANE_SMALL_OOCYST_CV_AREA':'','LANE_SMALL_OOCYST_MEAN_MAJORAXIS':'','LANE_SMALL_OOCYST_RANGE_MAJORAXIS':'','LANE_SMALL_OOCYST_CV_MAJORAXIS':'','LANE_SMALL_OOCYST_MEAN_MINORAXIS':'','LANE_SMALL_OOCYST_RANGE_MINORAXIS':'','LANE_SMALL_OOCYST_CV_MINORAXIS':'','LANE_SMALL_OOCYST_COUNT_SPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNSPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_MEDIUM_OOCYST_COUNT':'13','LANE_MEDIUM_OOCYST_MEAN_INTENSITY':'','LANE_MEDIUM_OOCYST_RANGE_INTENSITY':'','LANE_MEDIUM_OOCYST_CV_INTENSITY':'','LANE_MEDIUM_OOCYST_MEAN_AREA':'','LANE_MEDIUM_OOCYST_RANGE_AREA':'','LANE_MEDIUM_OOCYST_CV_AREA':'','LANE_MEDIUM_OOCYST_MEAN_MAJORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MAJORAXIS':'','LANE_MEDIUM_OOCYST_CV_MAJORAXIS':'','LANE_MEDIUM_OOCYST_MEAN_MINORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MINORAXIS':'','LANE_MEDIUM_OOCYST_CV_MINORAXIS':'','LANE_MEDIUM_OOCYST_COUNT_SPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNSPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_LARGE_OOCYST_COUNT':'120','LANE_LARGE_OOCYST_MEAN_INTENSITY':'','LANE_LARGE_OOCYST_RANGE_INTENSITY':'','LANE_LARGE_OOCYST_CV_INTENSITY':'','LANE_LARGE_OOCYST_MEAN_AREA':'','LANE_LARGE_OOCYST_RANGE_AREA':'','LANE_LARGE_OOCYST_CV_AREA':'','LANE_LARGE_OOCYST_MEAN_MAJORAXIS':'','LANE_LARGE_OOCYST_RANGE_MAJORAXIS':'','LANE_LARGE_OOCYST_CV_MAJORAXIS':'','LANE_LARGE_OOCYST_MEAN_MINORAXIS':'','LANE_LARGE_OOCYST_RANGE_MINORAXIS':'','LANE_LARGE_OOCYST_CV_MINORAXIS':'','LANE_LARGE_OOCYST_COUNT_SPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNSPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNCERTAINSPORULATION':'','STATUS':'','LANE_TOTAL_AREA_UM2':'','LANE_NOISE_AREA_UM2':'','LANE_NOISE_RATIO_PERCENT':'','LANE_NOISE_OBJECT_COUNT':'','LANE_SMALL_NOISE_OBJECT_COUNT':'','LANE_MEDIUM_NOISE_OBJECT_COUNT':'','LANE_LARGE_NOISE_OBJECT_COUNT':'','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'','IMPROC':'ImprocCocc01','VERSION':'"+ImprocVersion+"','ERROR_CODE':'Exception','IE_COLLECTION_SITE_ID':''},{'LANE':'6','PATHOGEN':'Coccidia','SAMPLEID':'Test"+lstSampleID.get(1)+"','SCANDATETIME':'"+date100+"','FIGUREOFMERIT':'0','OUTCOME':'','STATUSCODE':'','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'','CARTRIDGEID':'"+CartridgeID+"','EXPERIMENTID':'','INSTRUMENTID':'"+InstrumentID+"','USERID':'"+UserID+"','RUN_ID':'"+RunID+"','RUN_TYPE':'SCRIPT_swtest','LANE_NO':'6','DATE':'','TIME':'','LANE_TOTAL_OOCYST_COUNT':'163','LANE_TOTAL_OOCYST_MEAN_INTENSITY':'','LANE_TOTAL_OOCYST_RANGE_INTENSITY':'','LANE_TOTAL_OOCYST_CV_INTENSITY':'','LANE_TOTAL_OOCYST_MEAN_AREA':'','LANE_TOTAL_OOCYST_RANGE_AREA':'','LANE_TOTAL_OOCYST_CV_AREA':'','LANE_TOTAL_OOCYST_MEAN_MAJORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MAJORAXIS':'','LANE_TOTAL_OOCYST_CV_MAJORAXIS':'','LANE_TOTAL_OOCYST_MEAN_MINORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MINORAXIS':'','LANE_TOTAL_OOCYST_CV_MINORAXIS':'','LANE_TOTAL_OOCYST_COUNT_SPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNSPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_SMALL_OOCYST_COUNT':'13','LANE_SMALL_OOCYST_MEAN_INTENSITY':'','LANE_SMALL_OOCYST_RANGE_INTENSITY':'','LANE_SMALL_OOCYST_CV_INTENSITY':'','LANE_SMALL_OOCYST_MEAN_AREA':'','LANE_SMALL_OOCYST_RANGE_AREA':'','LANE_SMALL_OOCYST_CV_AREA':'','LANE_SMALL_OOCYST_MEAN_MAJORAXIS':'','LANE_SMALL_OOCYST_RANGE_MAJORAXIS':'','LANE_SMALL_OOCYST_CV_MAJORAXIS':'','LANE_SMALL_OOCYST_MEAN_MINORAXIS':'','LANE_SMALL_OOCYST_RANGE_MINORAXIS':'','LANE_SMALL_OOCYST_CV_MINORAXIS':'','LANE_SMALL_OOCYST_COUNT_SPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNSPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_MEDIUM_OOCYST_COUNT':'13','LANE_MEDIUM_OOCYST_MEAN_INTENSITY':'','LANE_MEDIUM_OOCYST_RANGE_INTENSITY':'','LANE_MEDIUM_OOCYST_CV_INTENSITY':'','LANE_MEDIUM_OOCYST_MEAN_AREA':'','LANE_MEDIUM_OOCYST_RANGE_AREA':'','LANE_MEDIUM_OOCYST_CV_AREA':'','LANE_MEDIUM_OOCYST_MEAN_MAJORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MAJORAXIS':'','LANE_MEDIUM_OOCYST_CV_MAJORAXIS':'','LANE_MEDIUM_OOCYST_MEAN_MINORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MINORAXIS':'','LANE_MEDIUM_OOCYST_CV_MINORAXIS':'','LANE_MEDIUM_OOCYST_COUNT_SPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNSPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_LARGE_OOCYST_COUNT':'120','LANE_LARGE_OOCYST_MEAN_INTENSITY':'','LANE_LARGE_OOCYST_RANGE_INTENSITY':'','LANE_LARGE_OOCYST_CV_INTENSITY':'','LANE_LARGE_OOCYST_MEAN_AREA':'','LANE_LARGE_OOCYST_RANGE_AREA':'','LANE_LARGE_OOCYST_CV_AREA':'','LANE_LARGE_OOCYST_MEAN_MAJORAXIS':'','LANE_LARGE_OOCYST_RANGE_MAJORAXIS':'','LANE_LARGE_OOCYST_CV_MAJORAXIS':'','LANE_LARGE_OOCYST_MEAN_MINORAXIS':'','LANE_LARGE_OOCYST_RANGE_MINORAXIS':'','LANE_LARGE_OOCYST_CV_MINORAXIS':'','LANE_LARGE_OOCYST_COUNT_SPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNSPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNCERTAINSPORULATION':'','STATUS':'','LANE_TOTAL_AREA_UM2':'','LANE_NOISE_AREA_UM2':'','LANE_NOISE_RATIO_PERCENT':'','LANE_NOISE_OBJECT_COUNT':'','LANE_SMALL_NOISE_OBJECT_COUNT':'','LANE_MEDIUM_NOISE_OBJECT_COUNT':'','LANE_LARGE_NOISE_OBJECT_COUNT':'','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'','IMPROC':'ImprocCocc01','VERSION':'"+ImprocVersion+"','ERROR_CODE':'Exception','IE_COLLECTION_SITE_ID':''},{'LANE':'7','PATHOGEN':'Coccidia','SAMPLEID':'Test"+lstSampleID.get(2)+"','SCANDATETIME':'"+date100+"','FIGUREOFMERIT':'0','OUTCOME':'','STATUSCODE':'','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'','CARTRIDGEID':'"+CartridgeID+"','EXPERIMENTID':'','INSTRUMENTID':'"+InstrumentID+"','USERID':'"+UserID+"','RUN_ID':'"+RunID+"','RUN_TYPE':'SCRIPT_swtest','LANE_NO':'7','DATE':'','TIME':'','LANE_TOTAL_OOCYST_COUNT':'173','LANE_TOTAL_OOCYST_MEAN_INTENSITY':'','LANE_TOTAL_OOCYST_RANGE_INTENSITY':'','LANE_TOTAL_OOCYST_CV_INTENSITY':'','LANE_TOTAL_OOCYST_MEAN_AREA':'','LANE_TOTAL_OOCYST_RANGE_AREA':'','LANE_TOTAL_OOCYST_CV_AREA':'','LANE_TOTAL_OOCYST_MEAN_MAJORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MAJORAXIS':'','LANE_TOTAL_OOCYST_CV_MAJORAXIS':'','LANE_TOTAL_OOCYST_MEAN_MINORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MINORAXIS':'','LANE_TOTAL_OOCYST_CV_MINORAXIS':'','LANE_TOTAL_OOCYST_COUNT_SPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNSPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_SMALL_OOCYST_COUNT':'13','LANE_SMALL_OOCYST_MEAN_INTENSITY':'','LANE_SMALL_OOCYST_RANGE_INTENSITY':'','LANE_SMALL_OOCYST_CV_INTENSITY':'','LANE_SMALL_OOCYST_MEAN_AREA':'','LANE_SMALL_OOCYST_RANGE_AREA':'','LANE_SMALL_OOCYST_CV_AREA':'','LANE_SMALL_OOCYST_MEAN_MAJORAXIS':'','LANE_SMALL_OOCYST_RANGE_MAJORAXIS':'','LANE_SMALL_OOCYST_CV_MAJORAXIS':'','LANE_SMALL_OOCYST_MEAN_MINORAXIS':'','LANE_SMALL_OOCYST_RANGE_MINORAXIS':'','LANE_SMALL_OOCYST_CV_MINORAXIS':'','LANE_SMALL_OOCYST_COUNT_SPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNSPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_MEDIUM_OOCYST_COUNT':'13','LANE_MEDIUM_OOCYST_MEAN_INTENSITY':'','LANE_MEDIUM_OOCYST_RANGE_INTENSITY':'','LANE_MEDIUM_OOCYST_CV_INTENSITY':'','LANE_MEDIUM_OOCYST_MEAN_AREA':'','LANE_MEDIUM_OOCYST_RANGE_AREA':'','LANE_MEDIUM_OOCYST_CV_AREA':'','LANE_MEDIUM_OOCYST_MEAN_MAJORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MAJORAXIS':'','LANE_MEDIUM_OOCYST_CV_MAJORAXIS':'','LANE_MEDIUM_OOCYST_MEAN_MINORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MINORAXIS':'','LANE_MEDIUM_OOCYST_CV_MINORAXIS':'','LANE_MEDIUM_OOCYST_COUNT_SPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNSPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_LARGE_OOCYST_COUNT':'120','LANE_LARGE_OOCYST_MEAN_INTENSITY':'','LANE_LARGE_OOCYST_RANGE_INTENSITY':'','LANE_LARGE_OOCYST_CV_INTENSITY':'','LANE_LARGE_OOCYST_MEAN_AREA':'','LANE_LARGE_OOCYST_RANGE_AREA':'','LANE_LARGE_OOCYST_CV_AREA':'','LANE_LARGE_OOCYST_MEAN_MAJORAXIS':'','LANE_LARGE_OOCYST_RANGE_MAJORAXIS':'','LANE_LARGE_OOCYST_CV_MAJORAXIS':'','LANE_LARGE_OOCYST_MEAN_MINORAXIS':'','LANE_LARGE_OOCYST_RANGE_MINORAXIS':'','LANE_LARGE_OOCYST_CV_MINORAXIS':'','LANE_LARGE_OOCYST_COUNT_SPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNSPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNCERTAINSPORULATION':'','STATUS':'','LANE_TOTAL_AREA_UM2':'','LANE_NOISE_AREA_UM2':'','LANE_NOISE_RATIO_PERCENT':'','LANE_NOISE_OBJECT_COUNT':'','LANE_SMALL_NOISE_OBJECT_COUNT':'','LANE_MEDIUM_NOISE_OBJECT_COUNT':'','LANE_LARGE_NOISE_OBJECT_COUNT':'','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'','IMPROC':'ImprocCocc01','VERSION':'"+ImprocVersion+"','ERROR_CODE':'Exception','IE_COLLECTION_SITE_ID':''},{'LANE':'8','PATHOGEN':'Coccidia','SAMPLEID':'Test"+lstSampleID.get(2)+"','SCANDATETIME':'"+date100+"','FIGUREOFMERIT':'0','OUTCOME':'','STATUSCODE':'','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'','CARTRIDGEID':'"+CartridgeID+"','EXPERIMENTID':'','INSTRUMENTID':'"+InstrumentID+"','USERID':'"+UserID+"','RUN_ID':'"+RunID+"','RUN_TYPE':'SCRIPT_swtest','LANE_NO':'8','DATE':'','TIME':'','LANE_TOTAL_OOCYST_COUNT':'183','LANE_TOTAL_OOCYST_MEAN_INTENSITY':'','LANE_TOTAL_OOCYST_RANGE_INTENSITY':'','LANE_TOTAL_OOCYST_CV_INTENSITY':'','LANE_TOTAL_OOCYST_MEAN_AREA':'','LANE_TOTAL_OOCYST_RANGE_AREA':'','LANE_TOTAL_OOCYST_CV_AREA':'','LANE_TOTAL_OOCYST_MEAN_MAJORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MAJORAXIS':'','LANE_TOTAL_OOCYST_CV_MAJORAXIS':'','LANE_TOTAL_OOCYST_MEAN_MINORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MINORAXIS':'','LANE_TOTAL_OOCYST_CV_MINORAXIS':'','LANE_TOTAL_OOCYST_COUNT_SPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNSPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_SMALL_OOCYST_COUNT':'13','LANE_SMALL_OOCYST_MEAN_INTENSITY':'','LANE_SMALL_OOCYST_RANGE_INTENSITY':'','LANE_SMALL_OOCYST_CV_INTENSITY':'','LANE_SMALL_OOCYST_MEAN_AREA':'','LANE_SMALL_OOCYST_RANGE_AREA':'','LANE_SMALL_OOCYST_CV_AREA':'','LANE_SMALL_OOCYST_MEAN_MAJORAXIS':'','LANE_SMALL_OOCYST_RANGE_MAJORAXIS':'','LANE_SMALL_OOCYST_CV_MAJORAXIS':'','LANE_SMALL_OOCYST_MEAN_MINORAXIS':'','LANE_SMALL_OOCYST_RANGE_MINORAXIS':'','LANE_SMALL_OOCYST_CV_MINORAXIS':'','LANE_SMALL_OOCYST_COUNT_SPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNSPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_MEDIUM_OOCYST_COUNT':'13','LANE_MEDIUM_OOCYST_MEAN_INTENSITY':'','LANE_MEDIUM_OOCYST_RANGE_INTENSITY':'','LANE_MEDIUM_OOCYST_CV_INTENSITY':'','LANE_MEDIUM_OOCYST_MEAN_AREA':'','LANE_MEDIUM_OOCYST_RANGE_AREA':'','LANE_MEDIUM_OOCYST_CV_AREA':'','LANE_MEDIUM_OOCYST_MEAN_MAJORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MAJORAXIS':'','LANE_MEDIUM_OOCYST_CV_MAJORAXIS':'','LANE_MEDIUM_OOCYST_MEAN_MINORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MINORAXIS':'','LANE_MEDIUM_OOCYST_CV_MINORAXIS':'','LANE_MEDIUM_OOCYST_COUNT_SPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNSPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_LARGE_OOCYST_COUNT':'120','LANE_LARGE_OOCYST_MEAN_INTENSITY':'','LANE_LARGE_OOCYST_RANGE_INTENSITY':'','LANE_LARGE_OOCYST_CV_INTENSITY':'','LANE_LARGE_OOCYST_MEAN_AREA':'','LANE_LARGE_OOCYST_RANGE_AREA':'','LANE_LARGE_OOCYST_CV_AREA':'','LANE_LARGE_OOCYST_MEAN_MAJORAXIS':'','LANE_LARGE_OOCYST_RANGE_MAJORAXIS':'','LANE_LARGE_OOCYST_CV_MAJORAXIS':'','LANE_LARGE_OOCYST_MEAN_MINORAXIS':'','LANE_LARGE_OOCYST_RANGE_MINORAXIS':'','LANE_LARGE_OOCYST_CV_MINORAXIS':'','LANE_LARGE_OOCYST_COUNT_SPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNSPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNCERTAINSPORULATION':'','STATUS':'','LANE_TOTAL_AREA_UM2':'','LANE_NOISE_AREA_UM2':'','LANE_NOISE_RATIO_PERCENT':'','LANE_NOISE_OBJECT_COUNT':'','LANE_SMALL_NOISE_OBJECT_COUNT':'','LANE_MEDIUM_NOISE_OBJECT_COUNT':'','LANE_LARGE_NOISE_OBJECT_COUNT':'','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'','IMPROC':'ImprocCocc01','VERSION':'"+ImprocVersion+"','ERROR_CODE':'Exception','IE_COLLECTION_SITE_ID':''},{'LANE':'9','PATHOGEN':'Coccidia','SAMPLEID':'Test"+lstSampleID.get(2)+"','SCANDATETIME':'"+date100+"','FIGUREOFMERIT':'0','OUTCOME':'','STATUSCODE':'','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'','CARTRIDGEID':'"+CartridgeID+"','EXPERIMENTID':'','INSTRUMENTID':'"+InstrumentID+"','USERID':'"+UserID+"','RUN_ID':'"+RunID+"','RUN_TYPE':'SCRIPT_swtest','LANE_NO':'9','DATE':'','TIME':'','LANE_TOTAL_OOCYST_COUNT':'193','LANE_TOTAL_OOCYST_MEAN_INTENSITY':'','LANE_TOTAL_OOCYST_RANGE_INTENSITY':'','LANE_TOTAL_OOCYST_CV_INTENSITY':'','LANE_TOTAL_OOCYST_MEAN_AREA':'','LANE_TOTAL_OOCYST_RANGE_AREA':'','LANE_TOTAL_OOCYST_CV_AREA':'','LANE_TOTAL_OOCYST_MEAN_MAJORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MAJORAXIS':'','LANE_TOTAL_OOCYST_CV_MAJORAXIS':'','LANE_TOTAL_OOCYST_MEAN_MINORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MINORAXIS':'','LANE_TOTAL_OOCYST_CV_MINORAXIS':'','LANE_TOTAL_OOCYST_COUNT_SPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNSPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_SMALL_OOCYST_COUNT':'13','LANE_SMALL_OOCYST_MEAN_INTENSITY':'','LANE_SMALL_OOCYST_RANGE_INTENSITY':'','LANE_SMALL_OOCYST_CV_INTENSITY':'','LANE_SMALL_OOCYST_MEAN_AREA':'','LANE_SMALL_OOCYST_RANGE_AREA':'','LANE_SMALL_OOCYST_CV_AREA':'','LANE_SMALL_OOCYST_MEAN_MAJORAXIS':'','LANE_SMALL_OOCYST_RANGE_MAJORAXIS':'','LANE_SMALL_OOCYST_CV_MAJORAXIS':'','LANE_SMALL_OOCYST_MEAN_MINORAXIS':'','LANE_SMALL_OOCYST_RANGE_MINORAXIS':'','LANE_SMALL_OOCYST_CV_MINORAXIS':'','LANE_SMALL_OOCYST_COUNT_SPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNSPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_MEDIUM_OOCYST_COUNT':'13','LANE_MEDIUM_OOCYST_MEAN_INTENSITY':'','LANE_MEDIUM_OOCYST_RANGE_INTENSITY':'','LANE_MEDIUM_OOCYST_CV_INTENSITY':'','LANE_MEDIUM_OOCYST_MEAN_AREA':'','LANE_MEDIUM_OOCYST_RANGE_AREA':'','LANE_MEDIUM_OOCYST_CV_AREA':'','LANE_MEDIUM_OOCYST_MEAN_MAJORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MAJORAXIS':'','LANE_MEDIUM_OOCYST_CV_MAJORAXIS':'','LANE_MEDIUM_OOCYST_MEAN_MINORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MINORAXIS':'','LANE_MEDIUM_OOCYST_CV_MINORAXIS':'','LANE_MEDIUM_OOCYST_COUNT_SPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNSPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_LARGE_OOCYST_COUNT':'120','LANE_LARGE_OOCYST_MEAN_INTENSITY':'','LANE_LARGE_OOCYST_RANGE_INTENSITY':'','LANE_LARGE_OOCYST_CV_INTENSITY':'','LANE_LARGE_OOCYST_MEAN_AREA':'','LANE_LARGE_OOCYST_RANGE_AREA':'','LANE_LARGE_OOCYST_CV_AREA':'','LANE_LARGE_OOCYST_MEAN_MAJORAXIS':'','LANE_LARGE_OOCYST_RANGE_MAJORAXIS':'','LANE_LARGE_OOCYST_CV_MAJORAXIS':'','LANE_LARGE_OOCYST_MEAN_MINORAXIS':'','LANE_LARGE_OOCYST_RANGE_MINORAXIS':'','LANE_LARGE_OOCYST_CV_MINORAXIS':'','LANE_LARGE_OOCYST_COUNT_SPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNSPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNCERTAINSPORULATION':'','STATUS':'','LANE_TOTAL_AREA_UM2':'','LANE_NOISE_AREA_UM2':'','LANE_NOISE_RATIO_PERCENT':'','LANE_NOISE_OBJECT_COUNT':'','LANE_SMALL_NOISE_OBJECT_COUNT':'','LANE_MEDIUM_NOISE_OBJECT_COUNT':'','LANE_LARGE_NOISE_OBJECT_COUNT':'','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'','IMPROC':'ImprocCocc01','VERSION':'"+ImprocVersion+"','ERROR_CODE':'Exception','IE_COLLECTION_SITE_ID':''},{'LANE':'10','PATHOGEN':'Coccidia','SAMPLEID':'Test"+lstSampleID.get(3)+"','SCANDATETIME':'"+date100+"','FIGUREOFMERIT':'0','OUTCOME':'','STATUSCODE':'','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'','CARTRIDGEID':'"+CartridgeID+"','EXPERIMENTID':'','INSTRUMENTID':'"+InstrumentID+"','USERID':'"+UserID+"','RUN_ID':'"+RunID+"','RUN_TYPE':'SCRIPT_swtest','LANE_NO':'10','DATE':'','TIME':'','LANE_TOTAL_OOCYST_COUNT':'193','LANE_TOTAL_OOCYST_MEAN_INTENSITY':'','LANE_TOTAL_OOCYST_RANGE_INTENSITY':'','LANE_TOTAL_OOCYST_CV_INTENSITY':'','LANE_TOTAL_OOCYST_MEAN_AREA':'','LANE_TOTAL_OOCYST_RANGE_AREA':'','LANE_TOTAL_OOCYST_CV_AREA':'','LANE_TOTAL_OOCYST_MEAN_MAJORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MAJORAXIS':'','LANE_TOTAL_OOCYST_CV_MAJORAXIS':'','LANE_TOTAL_OOCYST_MEAN_MINORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MINORAXIS':'','LANE_TOTAL_OOCYST_CV_MINORAXIS':'','LANE_TOTAL_OOCYST_COUNT_SPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNSPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_SMALL_OOCYST_COUNT':'13','LANE_SMALL_OOCYST_MEAN_INTENSITY':'','LANE_SMALL_OOCYST_RANGE_INTENSITY':'','LANE_SMALL_OOCYST_CV_INTENSITY':'','LANE_SMALL_OOCYST_MEAN_AREA':'','LANE_SMALL_OOCYST_RANGE_AREA':'','LANE_SMALL_OOCYST_CV_AREA':'','LANE_SMALL_OOCYST_MEAN_MAJORAXIS':'','LANE_SMALL_OOCYST_RANGE_MAJORAXIS':'','LANE_SMALL_OOCYST_CV_MAJORAXIS':'','LANE_SMALL_OOCYST_MEAN_MINORAXIS':'','LANE_SMALL_OOCYST_RANGE_MINORAXIS':'','LANE_SMALL_OOCYST_CV_MINORAXIS':'','LANE_SMALL_OOCYST_COUNT_SPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNSPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_MEDIUM_OOCYST_COUNT':'13','LANE_MEDIUM_OOCYST_MEAN_INTENSITY':'','LANE_MEDIUM_OOCYST_RANGE_INTENSITY':'','LANE_MEDIUM_OOCYST_CV_INTENSITY':'','LANE_MEDIUM_OOCYST_MEAN_AREA':'','LANE_MEDIUM_OOCYST_RANGE_AREA':'','LANE_MEDIUM_OOCYST_CV_AREA':'','LANE_MEDIUM_OOCYST_MEAN_MAJORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MAJORAXIS':'','LANE_MEDIUM_OOCYST_CV_MAJORAXIS':'','LANE_MEDIUM_OOCYST_MEAN_MINORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MINORAXIS':'','LANE_MEDIUM_OOCYST_CV_MINORAXIS':'','LANE_MEDIUM_OOCYST_COUNT_SPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNSPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_LARGE_OOCYST_COUNT':'120','LANE_LARGE_OOCYST_MEAN_INTENSITY':'','LANE_LARGE_OOCYST_RANGE_INTENSITY':'','LANE_LARGE_OOCYST_CV_INTENSITY':'','LANE_LARGE_OOCYST_MEAN_AREA':'','LANE_LARGE_OOCYST_RANGE_AREA':'','LANE_LARGE_OOCYST_CV_AREA':'','LANE_LARGE_OOCYST_MEAN_MAJORAXIS':'','LANE_LARGE_OOCYST_RANGE_MAJORAXIS':'','LANE_LARGE_OOCYST_CV_MAJORAXIS':'','LANE_LARGE_OOCYST_MEAN_MINORAXIS':'','LANE_LARGE_OOCYST_RANGE_MINORAXIS':'','LANE_LARGE_OOCYST_CV_MINORAXIS':'','LANE_LARGE_OOCYST_COUNT_SPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNSPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNCERTAINSPORULATION':'','STATUS':'','LANE_TOTAL_AREA_UM2':'','LANE_NOISE_AREA_UM2':'','LANE_NOISE_RATIO_PERCENT':'','LANE_NOISE_OBJECT_COUNT':'','LANE_SMALL_NOISE_OBJECT_COUNT':'','LANE_MEDIUM_NOISE_OBJECT_COUNT':'','LANE_LARGE_NOISE_OBJECT_COUNT':'','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'','IMPROC':'ImprocCocc01','VERSION':'"+ImprocVersion+"','ERROR_CODE':'Exception','IE_COLLECTION_SITE_ID':''},{'LANE':'11','PATHOGEN':'Coccidia','SAMPLEID':'Test"+lstSampleID.get(3)+"','SCANDATETIME':'"+date100+"','FIGUREOFMERIT':'0','OUTCOME':'','STATUSCODE':'','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'','CARTRIDGEID':'"+CartridgeID+"','EXPERIMENTID':'','INSTRUMENTID':'"+InstrumentID+"','USERID':'"+UserID+"','RUN_ID':'"+RunID+"','RUN_TYPE':'SCRIPT_swtest','LANE_NO':'11','DATE':'','TIME':'','LANE_TOTAL_OOCYST_COUNT':'113','LANE_TOTAL_OOCYST_MEAN_INTENSITY':'','LANE_TOTAL_OOCYST_RANGE_INTENSITY':'','LANE_TOTAL_OOCYST_CV_INTENSITY':'','LANE_TOTAL_OOCYST_MEAN_AREA':'','LANE_TOTAL_OOCYST_RANGE_AREA':'','LANE_TOTAL_OOCYST_CV_AREA':'','LANE_TOTAL_OOCYST_MEAN_MAJORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MAJORAXIS':'','LANE_TOTAL_OOCYST_CV_MAJORAXIS':'','LANE_TOTAL_OOCYST_MEAN_MINORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MINORAXIS':'','LANE_TOTAL_OOCYST_CV_MINORAXIS':'','LANE_TOTAL_OOCYST_COUNT_SPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNSPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_SMALL_OOCYST_COUNT':'13','LANE_SMALL_OOCYST_MEAN_INTENSITY':'','LANE_SMALL_OOCYST_RANGE_INTENSITY':'','LANE_SMALL_OOCYST_CV_INTENSITY':'','LANE_SMALL_OOCYST_MEAN_AREA':'','LANE_SMALL_OOCYST_RANGE_AREA':'','LANE_SMALL_OOCYST_CV_AREA':'','LANE_SMALL_OOCYST_MEAN_MAJORAXIS':'','LANE_SMALL_OOCYST_RANGE_MAJORAXIS':'','LANE_SMALL_OOCYST_CV_MAJORAXIS':'','LANE_SMALL_OOCYST_MEAN_MINORAXIS':'','LANE_SMALL_OOCYST_RANGE_MINORAXIS':'','LANE_SMALL_OOCYST_CV_MINORAXIS':'','LANE_SMALL_OOCYST_COUNT_SPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNSPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_MEDIUM_OOCYST_COUNT':'13','LANE_MEDIUM_OOCYST_MEAN_INTENSITY':'','LANE_MEDIUM_OOCYST_RANGE_INTENSITY':'','LANE_MEDIUM_OOCYST_CV_INTENSITY':'','LANE_MEDIUM_OOCYST_MEAN_AREA':'','LANE_MEDIUM_OOCYST_RANGE_AREA':'','LANE_MEDIUM_OOCYST_CV_AREA':'','LANE_MEDIUM_OOCYST_MEAN_MAJORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MAJORAXIS':'','LANE_MEDIUM_OOCYST_CV_MAJORAXIS':'','LANE_MEDIUM_OOCYST_MEAN_MINORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MINORAXIS':'','LANE_MEDIUM_OOCYST_CV_MINORAXIS':'','LANE_MEDIUM_OOCYST_COUNT_SPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNSPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_LARGE_OOCYST_COUNT':'120','LANE_LARGE_OOCYST_MEAN_INTENSITY':'','LANE_LARGE_OOCYST_RANGE_INTENSITY':'','LANE_LARGE_OOCYST_CV_INTENSITY':'','LANE_LARGE_OOCYST_MEAN_AREA':'','LANE_LARGE_OOCYST_RANGE_AREA':'','LANE_LARGE_OOCYST_CV_AREA':'','LANE_LARGE_OOCYST_MEAN_MAJORAXIS':'','LANE_LARGE_OOCYST_RANGE_MAJORAXIS':'','LANE_LARGE_OOCYST_CV_MAJORAXIS':'','LANE_LARGE_OOCYST_MEAN_MINORAXIS':'','LANE_LARGE_OOCYST_RANGE_MINORAXIS':'','LANE_LARGE_OOCYST_CV_MINORAXIS':'','LANE_LARGE_OOCYST_COUNT_SPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNSPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNCERTAINSPORULATION':'','STATUS':'','LANE_TOTAL_AREA_UM2':'','LANE_NOISE_AREA_UM2':'','LANE_NOISE_RATIO_PERCENT':'','LANE_NOISE_OBJECT_COUNT':'','LANE_SMALL_NOISE_OBJECT_COUNT':'','LANE_MEDIUM_NOISE_OBJECT_COUNT':'','LANE_LARGE_NOISE_OBJECT_COUNT':'','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'','IMPROC':'ImprocCocc01','VERSION':'"+ImprocVersion+"','ERROR_CODE':'Exception','IE_COLLECTION_SITE_ID':''},{'LANE':'12','PATHOGEN':'Coccidia','SAMPLEID':'Test"+lstSampleID.get(3)+"','SCANDATETIME':'"+date100+"','FIGUREOFMERIT':'0','OUTCOME':'','STATUSCODE':'','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'','CARTRIDGEID':'"+CartridgeID+"','EXPERIMENTID':'','INSTRUMENTID':'"+InstrumentID+"','USERID':'"+UserID+"','RUN_ID':'"+RunID+"','RUN_TYPE':'SCRIPT_swtest','LANE_NO':'12','DATE':'','TIME':'','LANE_TOTAL_OOCYST_COUNT':'113','LANE_TOTAL_OOCYST_MEAN_INTENSITY':'','LANE_TOTAL_OOCYST_RANGE_INTENSITY':'','LANE_TOTAL_OOCYST_CV_INTENSITY':'','LANE_TOTAL_OOCYST_MEAN_AREA':'','LANE_TOTAL_OOCYST_RANGE_AREA':'','LANE_TOTAL_OOCYST_CV_AREA':'','LANE_TOTAL_OOCYST_MEAN_MAJORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MAJORAXIS':'','LANE_TOTAL_OOCYST_CV_MAJORAXIS':'','LANE_TOTAL_OOCYST_MEAN_MINORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MINORAXIS':'','LANE_TOTAL_OOCYST_CV_MINORAXIS':'','LANE_TOTAL_OOCYST_COUNT_SPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNSPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_SMALL_OOCYST_COUNT':'13','LANE_SMALL_OOCYST_MEAN_INTENSITY':'','LANE_SMALL_OOCYST_RANGE_INTENSITY':'','LANE_SMALL_OOCYST_CV_INTENSITY':'','LANE_SMALL_OOCYST_MEAN_AREA':'','LANE_SMALL_OOCYST_RANGE_AREA':'','LANE_SMALL_OOCYST_CV_AREA':'','LANE_SMALL_OOCYST_MEAN_MAJORAXIS':'','LANE_SMALL_OOCYST_RANGE_MAJORAXIS':'','LANE_SMALL_OOCYST_CV_MAJORAXIS':'','LANE_SMALL_OOCYST_MEAN_MINORAXIS':'','LANE_SMALL_OOCYST_RANGE_MINORAXIS':'','LANE_SMALL_OOCYST_CV_MINORAXIS':'','LANE_SMALL_OOCYST_COUNT_SPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNSPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_MEDIUM_OOCYST_COUNT':'13','LANE_MEDIUM_OOCYST_MEAN_INTENSITY':'','LANE_MEDIUM_OOCYST_RANGE_INTENSITY':'','LANE_MEDIUM_OOCYST_CV_INTENSITY':'','LANE_MEDIUM_OOCYST_MEAN_AREA':'','LANE_MEDIUM_OOCYST_RANGE_AREA':'','LANE_MEDIUM_OOCYST_CV_AREA':'','LANE_MEDIUM_OOCYST_MEAN_MAJORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MAJORAXIS':'','LANE_MEDIUM_OOCYST_CV_MAJORAXIS':'','LANE_MEDIUM_OOCYST_MEAN_MINORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MINORAXIS':'','LANE_MEDIUM_OOCYST_CV_MINORAXIS':'','LANE_MEDIUM_OOCYST_COUNT_SPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNSPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_LARGE_OOCYST_COUNT':'120','LANE_LARGE_OOCYST_MEAN_INTENSITY':'','LANE_LARGE_OOCYST_RANGE_INTENSITY':'','LANE_LARGE_OOCYST_CV_INTENSITY':'','LANE_LARGE_OOCYST_MEAN_AREA':'','LANE_LARGE_OOCYST_RANGE_AREA':'','LANE_LARGE_OOCYST_CV_AREA':'','LANE_LARGE_OOCYST_MEAN_MAJORAXIS':'','LANE_LARGE_OOCYST_RANGE_MAJORAXIS':'','LANE_LARGE_OOCYST_CV_MAJORAXIS':'','LANE_LARGE_OOCYST_MEAN_MINORAXIS':'','LANE_LARGE_OOCYST_RANGE_MINORAXIS':'','LANE_LARGE_OOCYST_CV_MINORAXIS':'','LANE_LARGE_OOCYST_COUNT_SPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNSPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNCERTAINSPORULATION':'','STATUS':'','LANE_TOTAL_AREA_UM2':'','LANE_NOISE_AREA_UM2':'','LANE_NOISE_RATIO_PERCENT':'','LANE_NOISE_OBJECT_COUNT':'','LANE_SMALL_NOISE_OBJECT_COUNT':'','LANE_MEDIUM_NOISE_OBJECT_COUNT':'','LANE_LARGE_NOISE_OBJECT_COUNT':'','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'','IMPROC':'ImprocCocc01','VERSION':'"+ImprocVersion+"','ERROR_CODE':'Exception','IE_COLLECTION_SITE_ID':''}]",
					        "ImprocCocc01", 1, "Coccidia",  true, "AN-API_FileUpload-01: Run File Upload API with unique Run ID", "This test case will run ingestion with unique run id", "Run API with unique RUN ID and unique Sample ID", "File Upload API ran successfully; File was ingested", "File Upload API failed to run", "AN-CL-01: Verify that data is ingested after running API with Unique Run ID", "This test case will verify that data is ingested after running file upload API") ));
	
	public static String clPNGFileName = "Coccidia Run Timeline - ";
	public static String clCSVFileName = "Coccidia Log - ";
	public static String clCSVAuditFileName = "Coccidia Log Audit - ";
	public static String clSampleMetaData = "Sample Metadata Upload Template";
	
	public static ArrayList<CoccidiaModel> lstCoccidiaSearch = new ArrayList<>();
	public static ArrayList<CoccidiaModel> lstCoccidiaWildcardSearch = new ArrayList<>();
	public static ArrayList<CoccidiaModel> lstCoccidiaDateSearch = new ArrayList<>();
	public static ArrayList<CoccidiaModel> lstCoccidiaDateEnter = new ArrayList<>();
	public static ArrayList<CoccidiaModel> lstCoccidiaRowCount = new ArrayList<>();
	public static ArrayList<CoccidiaModel> lstCoccidiaPagination = new ArrayList<>();
	public static ArrayList<CoccidiaModel> lstCoccidiaSorting = new ArrayList<>();
	public static ArrayList<CoccidiaModel> lstCoccidiaFieldAccess = new ArrayList<>();
	public static ArrayList<CoccidiaModel> lstCoccidiaContexualCheck = new ArrayList<>();
	public static ArrayList<CoccidiaModel> lstCoccidiaLock = new ArrayList<>();
	
	////////////////////////////////////////////////////////////////End Coccidia Variables/////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////////////////////Sites Log Variables///////////////////////////////////////////////////////////////////////////////////			

	public static ArrayList<SitesLogModel> lstSitesLogSearch = new ArrayList<>();
	public static ArrayList<SitesLogModel> lstSiteLogWildcardSearch = new ArrayList<>();
	public static ArrayList<SitesLogModel> lstSitesLogDateSearch = new ArrayList<>();
	public static ArrayList<SitesLogModel> lstSitesLogDateEnter = new ArrayList<>();
	public static ArrayList<SitesLogModel> lstSitesLogRowCount = new ArrayList<>();
	public static ArrayList<SitesLogModel> lstSitesLogPagination = new ArrayList<>();
	public static ArrayList<SitesLogModel> lstSitesLogSorting = new ArrayList<>();
	public static ArrayList<SitesLogModel> lstSitesLogFieldAccess = new ArrayList<>();
	public static ArrayList<SitesLogModel> lstSitesLogContexualCheck = new ArrayList<>();
	public static ArrayList<SitesLogModel> lstSitesLogLock = new ArrayList<>();
	
	public static String sitesCSVFileName = "Sites Log - ";
	public static String sitesCSVAuditFileName = "Sites Audit Log - ";
	
	//////////////////////////////////////////////////////////////////End Sites Log Variables////////////////////////////////////////////////////////////////////////////////

	
	////////////////////////////////////////////////////////////////////Flock Registration Variables///////////////////////////////////////////////////////////////////////////////////			

	public static ArrayList<FlockRegistrationModel> lstFlockRegistrationSearch = new ArrayList<>();
	public static ArrayList<FlockRegistrationModel> lstFlockRegistrationWildcardSearch = new ArrayList<>();
	public static ArrayList<FlockRegistrationModel> lstFlockRegistrationDateSearch = new ArrayList<>();
	public static ArrayList<FlockRegistrationModel> lstFlockRegistrationDateEnter = new ArrayList<>();
	public static ArrayList<FlockRegistrationModel> lstFlockRegistrationRowCount = new ArrayList<>();
	public static ArrayList<FlockRegistrationModel> lstFlockRegistrationPagination = new ArrayList<>();
	public static ArrayList<FlockRegistrationModel> lstFlockRegistrationSorting = new ArrayList<>();
	public static ArrayList<FlockRegistrationModel> lstFlockRegistrationFieldAccess = new ArrayList<>();
	public static ArrayList<FlockRegistrationModel> lstFlockRegistrationContexualCheck = new ArrayList<>();
	public static ArrayList<FlockRegistrationModel> lstFlockRegistrationLock = new ArrayList<>();
	public static ArrayList<FlockRegistrationModel> lstFlockRegistrationEdit = new ArrayList<>();
	public static ArrayList<FlockRegistrationModel> lstFlockRegistrationValidation = new ArrayList<>();
	
	public static String flockCSVFileName = "Flock Registration Log - ";
	public static String flockCSVAuditFileName = "Flock Registration Audit Log - ";
	public static String flockSampleMetaData = "FLOCK METADATA";
	
	//////////////////////////////////////////////////////////////////End Flock Registration Variables////////////////////////////////////////////////////////////////////////////////
	
	
	/////////////////////////////////////////////////////////////////API Version////////////////////////////////////////////////////////////////
	
	public static ArrayList<APIVersionModel> lstTestAPIVersion = new ArrayList<>();
	
	/////////////////////////////////////////////////////////////////API Version////////////////////////////////////////////////////////////////
	
	
	
	//////////////////////////////////////////////////////////////Coccidia Timeline Variables////////////////////////////////////////////////////////////////////////////////////

	public static String ctlTimelineFileName = "Coccidia Run Timeline - ";
	public static String ctlOCountFileName = "Oocyst Counts (Most Recent 10 Days) - ";
	public static String ctlLast10FileName = "Last 10 Coccidia Tests - ";
	public static String ctlOverTimeFileName = "Oocyst Counts Over Time - ";
	
	public static ArrayList<CoccidiaTimelineModel> lstCoccidiaTimelineSearch = new ArrayList<>();
	public static ArrayList<CoccidiaTimelineModel> lstCoccidiaTimelineDateSearch = new ArrayList<>();
	public static ArrayList<CoccidiaTimelineModel> lstCoccidiaTimelineDateEnter = new ArrayList<>();
	
	//////////////////////////////////////////////////////////////End Coccidia Timeline Variables////////////////////////////////////////////////////////////////////////////////////
	
	
	//////////////////////////////////////////////////////////////////////Start Assay/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static ArrayList<StartAssayModel> lstStartAssaySalmonella = new ArrayList<>(
			Arrays.asList(
					new StartAssayModel(date100, InstrumentID, UserID, CartridgeID, slRunID, "salmonella", "AN-StartAssay-01: Verify SAlmonella Assay Api", "This testcase will verify that user can run Start Assay API for Salmonella", "1. Run Start Assay API with PathogenName as Salmonella and unique RunID", "", "", Constants.url_SalmonellaLog)));
    
	public static ArrayList<StartAssayModel> lstStartAssayCoccidia = new ArrayList<>(
			Arrays.asList(
					new StartAssayModel(date100, InstrumentID, UserID, CartridgeID, RunID, "coccidia", "AN-StartAssay-01: Verify Coccidia Assay Api", "This testcase will verify that user can run Start Assay API for Coccidia", "1. Run Start Assay API with PathogenName as Coccidia and unique RunID", "", "", Constants.url_CoccidiaLog)));
    
	
	////////////////////////////////////////////////////////////////End Start Assay///////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	//////////////////////////////////////////////////////////////Installation Run///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static ArrayList<InstallationRunModel> lstInstallationRunCreate = new ArrayList<>();
	public static ArrayList<InstallationRunModel> lstInstallationRunCreateCoccidia = new ArrayList<>();
	
//	public static String installationImprocVersionSalm = "9.9.9.9";    //qa
//	public static String installationImprocVersionCocci = "5.2.1.0";   //qa
//	public static String installationImprocVersionListeria = "9.9.9.9";   //qa
	
	public static String installationImprocVersionSalm = "22.22.22.22";    //qa
	public static String installationImprocVersionCocci = "11.12.13.14";   //qa
	public static String installationImprocVersionListeria = "22.22.22.22";   //qa
	
//	public static String installationImprocVersionSalm = "4.0.20.2";   //uat
//	public static String installationImprocVersionCocci = "2.10.3.3";  //uat
//	public static String installationImprocVersionListeria = "4.0.20.2";  //uat
	
//	public static String installationImprocVersionSalm = "4.0.8.2";    //dev
//	public static String installationImprocVersionCocci = "2.10.4.3";   //dev
//	public static String installationImprocVersionListeria = "4.0.8.2";   //dev
	
	////////////////////////////////////////////////////////////End Installation Run////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//////////////////////////////////////////////////////////////Raw Image///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static ArrayList<RawImageModel> lstRawImageCoccidia = new ArrayList<>();
	public static ArrayList<RawImageModel> lstRawImageSalmonella = new ArrayList<>();
	public static ArrayList<RawImageCompareCountModel> lstRawImageComparison = new ArrayList<>();

	////////////////////////////////////////////////////////////End Raw Image////////////////////////////////////////////////////////////////////////////////////////////////////
	
	 
	
	/////////////////////////////////////////////////////////////P/A Configuration//////////////////////////////////////////////////////////////////////////////////////////////////// 
	
	public static ArrayList<PAModel> lstPASalmonella = new ArrayList<>();
	public static String PA_SampleMatrixSalm = "AT_SampleMatrix";    //Sample Matrix used while ingestion for Salmonella case 1 (Make sure configuration is already made)
	public static String PA_SampleMatrixList = "AT_SampleMatrixListeria"; //Sample Matrix used while ingestion for Listeria case 1 (Make sure configuration is already made)
	public static String PA_SampleMatrixID = "201";   //Sample Matrix ID used while ingestion
	public static String PA_SampleMatrixIDListeria = "41";  //Sample Matrix ID used while ingestion
	public static String PA_ImprocVersion = "4.0.8.2"; //Version set for Sample Matrix in Piper Configuration Setting
//	public static String PA_ImprocVersionNew = "4.0.8.2"; //Version used for creating new Sample Matrix in Piper Configuration Setting for case 3  //dev
	public static String PA_ImprocVersionNew = "4.0.26.0"; //Version used for creating new Sample Matrix in Piper Configuration Setting for case 3  //qa
	public static String PA_Threshold = "1000";  //Sample Matrix threshold set while ingestion in Piper Configuartion Setting
	public static String PA_fileName = "PA_Config_Sample_Metadata_Upload.xlsx"; //Name of file in directory used to upload Sample MAtrix

	///////////////////////////////////////////////////////////End P/A Configuration//////////////////////////////////////////////////////////////////////////////////////////////////// 
	
	
	////////////////////////////////////////////////////////Data Security Ingestion/////////////////////////////////////////////////////////////////////////
	
	public static ArrayList<DataSecurityModel> lstDataSecurity = new ArrayList<>(); 
	
	public static String templateFileNameDS = "MetaData DataSecurity.xlsx";
	
////////////////////////////////////////////////////////Data Security Ingestion/////////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////Profile Variables//////////////////////////////////////////////////////////////////////////
	
	public static ArrayList<ProfileModel> lstProfileNavigate = new ArrayList<>(
			Arrays.asList(
					new ProfileModel(Constants.url_home,"AN-PS-01: Navigate to Profile Setting from Home Screen", "This test case will verify user can navigate to Profile Setting page from Home Screen", "1. Hover to sidebar and click on Home", "Home"),
				new ProfileModel(Constants.url_user,"AN-PS-02: Navigate to Profile Setting from User Management Screen", "This test case will verify user can navigate to Profile Setting page from User Management Screen", "1. Hover to sidebar and click on User Management", "User Management"),
					new ProfileModel(Constants.url_organization,"AN-PS-03: Navigate to Profile Setting from Organization Management Screen", "This test case will verify user can navigate to Profile Setting page from Access Management Screen", "1. Hover to sidebar and click on Organization Management", "Organization Management"),
					new ProfileModel(Constants.url_access,"AN-PS-04: Navigate to Profile Setting from Access Management Screen", "This test case will verify user can navigate to Profile Setting page from Access Management Screen", "1. Hover to sidebar and click on Access Management Screen", "Access Management"),
					new ProfileModel(Constants.url_reportsManagement,"AN-PS-05: Navigate to Profile Setting from Reports Management Screen", "This test case will verify user can navigate to Profile Setting page from Reports Management Screen", "1. Hover to sidebar and click on Reports Management", "Reports Management"),
					new ProfileModel(Constants.url_barcodeManagement, "AN-PS-06: Navigate to Profile Setting from barcode Management Screen", "This test case will verify user can navigate to Profile Setting page from Barcode Management Screen", "1. Hover to sidebar and click on Barcode Management", "Barcode Management"),
					new ProfileModel(Constants.url_agreementManagement, "AN-PS-07: Navigate to Profile Setting from Agreement Management Screen", "This test case will verify user can navigate to Profile Setting page from Agreement Management Screen", "1. Hover to sidebar and click on Agreement Management", "Agreement Management"),
					new ProfileModel(Constants.url_alert, "AN-PS-08: Navigate to Profile Setting from Alert Management Screen", "This test case will verify user can navigate to Profile Setting page from Alert Management Screen", "1. Hover to sidebar and click on Alert Management", "Alert Management"),
					new ProfileModel(Constants.url_cyclingConfig, "AN-PS-08: Navigate to Profile Setting from Complex Cycling Config Screen", "This test case will verify user can navigate to Profile Setting page from Complex Cycling Config Management Screen", "1. Hover to sidebar and click on Complex Cycling Config", "Complex OPG Range Config"),
					new ProfileModel(Constants.url_flockRegistration, "AN-PS-08: Navigate to Profile Setting from Complex Cycling Config Screen", "This test case will verify user can navigate to Profile Setting page from Flock Registration Screen", "1. Hover to sidebar and click on Flock Registration", "Flock Registrations"),
					new ProfileModel(Constants.url_piperManagement, "AN-PS-09: Navigate to Profile Setting from Piper Management Screen", "This test case will verify user can navigate to Profile Setting page from Piper Management Screen", "1. Hover to sidebar and click on Piper Management", "PIPER Management"),
					new ProfileModel(Constants.url_piperSoftware, "AN-PS-10: Navigate to Profile Setting from Piper Software Management Screen", "This test case will verify user can navigate to Profile Setting page from Piper Management Screen", "1. Hover to sidebar and click on Piper Software Management", "PIPER Software Management"),
					new ProfileModel(Constants.url_piperConfiguration, "AN-PS-11: Navigate to Profile Setting from Piper Configuration Management Screen", "This test case will verify user can navigate to Profile Setting page from Piper Management Screen", "1. Hover to sidebar and click on Piper Config Management", "PIPER Configuration Management"),
					new ProfileModel(Constants.url_dataTemplate, "AN-PS-12: Navigate to Profile Setting from Data Template Screen", "This test case will verify user can navigate to Profile Setting page from Data Template Screen", "1. Hover to sidebar and click on Data Template", "Data Template Management"),
					new ProfileModel(Constants.url_dataUpload, "AN-PS-13: Navigate to Profile Setting from Data Upload Screen", "This test case will verify user can navigate to Profile Setting page from Data Upload Screen", "1. Hover to sidebar and click on Data Upload", "Data Upload"),
					new ProfileModel(Constants.url_poultryManagement, "AN-PS-14: Navigate to Profile Setting from Poultry Management Screen", "This test case will verify user can navigate to Profile Setting page from Poultry Management Screen", "1. Hover to sidebar and click on Poultry Management", "Logging and Management"),
					new ProfileModel(Constants.url_reports, "AN-PS-15: Navigate to Profile Setting from Reports Screen", "This test case will verify user can navigate to Profile Setting page from Reports Screen", "1. Hover to sidebar and click on Reports", "Reports"),
					new ProfileModel(Constants.url_SalmonellaLog, "AN-PS-16: Navigate to Profile Setting from Salmoenella Screen", "This test case will verify user can navigate to Profile Setting page from Salmonella Screen", "1. Hover to sidebar; click on Reports and select Salmonella log Report", "Salmonella Log"),
					new ProfileModel(Constants.url_CoccidiaLog, "AN-PS-17: Navigate to Profile Setting from Coccidia Log Screen", "This test case will verify user can navigate to Profile Setting page from Coccidia Log Screen", "1. Hover to sidebar; click on Reports and select Coccidia Log", "Coccidia Log"),
					new ProfileModel(Constants.url_CoccidiaTimeline, "AN-PS-18: Navigate to Profile Setting from Coccidia Timeline Screen", "This test case will verify user can navigate to Profile Setting page from Coccidia Timeline Screen", "1. Hover to sidebar; click on Reports and select Coccidia Timeline", "Coccidia Timeline"),
					new ProfileModel(Constants.url_LocalDashboard, "AN-PS-19: Navigate to Profile Setting from Local Dashboard Screen", "This test case will verify user can navigate to Profile Setting page from Local Dashboard Screen", "1. Hover to sidebar; click on Reports and select Local Dashboard", "Local Dashboard"),
					new ProfileModel(Constants.url_SitesLog, "AN-PS-20: Navigate to Profile Setting from Sites Log Screen", "This test case will verify user can navigate to Profile Setting page from Sites Log Screen", "1. Hover to sidebar; click on Reports and select Sites Log", "Sites Log")
					));
	
	
	public static ArrayList<ProfileModel> lstProfileMandatoryCheck = new ArrayList<>(
			Arrays.asList(
				//	new ProfileModel(true, "", "", false, "6666666666", false, "", "testcase1", true),
			//		new ProfileModel(true, "Junaid", "", false, "6666666666", false, "", "testcase2", true),
					new ProfileModel(true, "Junaid", "Alam", true, "6666666666", false, "", "testcase3", true)
			//		new ProfileModel(false, "junaid", "", false, "", false, "", "testcase4", true),
			//		new ProfileModel(false, "junaid", "alam", true, "6666666666", true, "6666666666", "testcase4", true)
					));
	
//////////////////////////////////////////////////////////End Profile Variables//////////////////////////////////////////////////////////////////////////////

	
///////////////////////////////////////////////////////////////Audit Log/////////////////////////////////////////////////////////////////////////////////////
		
	public static ArrayList<Integer> lstAuditRowEdit= new ArrayList<>(
			Arrays.asList(	  				
					19,
					5
				//	6,
				//	7,
				//	16,
				//	17,
				//	4
					)); 
	
	public static ArrayList<String> lstAuditRowData= new ArrayList<>(
			Arrays.asList(
					
					""+date1001,  //Kit Lot
					"Complex"+date1001 //Sample Matrix
				//	"CS"+date1001,  //Customer Sample ID
				//	"Test"+date1001, //Requested Assay
				//	""+dateMMDDYYYY, //Date Received
				//	"TestAutomationU"+date0, //Sample ID
				//	"1001001"
					));//Site ID

	public static ArrayList<String> lstAuditSampleID= new ArrayList<>(
			Arrays.asList(
					
					""+lstSampleID.get(0),  //Kit Lot
					""+lstSampleID.get(0) //Sample Matrix
				//	""+lstSampleID.get(0),  //Customer Sample ID
				//	""+lstSampleID.get(0), //Requested Assay
				//	""+lstSampleID.get(0), //Date Received
				//	""+lstSampleID.get(0),
				//	"AutomationU"+date0, //Sample ID
				//	""+lstSampleID.get(0)
					)); //Site ID
	
	
	public static ArrayList<String> lstAuditStatus= new ArrayList<>(
			Arrays.asList(
					
					"Created", 
					"Modified"
				//	"Modified",
				//	"Modified",
				//	"Modified",
				//	"Modified"
					)); 
	
	
	public static ArrayList<AuditLogModel> lstAuditLog = new ArrayList<>(
			Arrays.asList(
					new AuditLogModel("AN-SL-339: Verify that new row is added with 'Created' status", "This test case will verify that on uploading sample metadata template after updating site id new row is added with 'Modified status'", "Kit Lot"),
					new AuditLogModel("AN-SL-340: Upload Sample Metadata template and verify that new row is added with 'Modified' status on updating Site ID", "This test case will verify that on uploading sample metadata template after updating site id new row is added with 'Modified status'", "Kit Lot")
				//	new AuditLogModel("AN-SL-341: Upload Sample Metadata template and verify that new row is added with 'Modified' status on updating KitLot", "This test case will verify that on uploading sample metadata template after updating kit lot new row is added with 'Modified status'", "Kit Lot"),
				//	new AuditLogModel("AN-SL-342: Upload Sample Metadata template and verify that new row is added with 'Modified' status on updating Sample Matrix", "This test case will verify that on uploading sample metadata template after updating sample matrix new row is added with 'Modified status'", "Sample Matrix"),
				//	new AuditLogModel("AN-SL-343: Upload Sample Metadata template and verify that new row is added with 'Modified' status on updating Customer Sample ID", "This test case will verify that on uploading sample metadata template after updating customer sample id new row is added with 'Modified status'", "Customer Sample ID"),
				//	new AuditLogModel("AN-SL-344: Upload Sample Metadata template and verify that new row is added with 'Modified' status on updating Requested Assay", "This test case will verify that on uploading sample metadata template after updating Requested Assay new row is added with 'Modified status'", "Requested Assay"),
				//	new AuditLogModel("AN-SL-345: Upload Sample Metadata template and verify that new row is added with 'Modified' status on updating Date Received", "This test case will verify that on uploading sample metadata template after updating Date Received new row is added with 'Modified status'", "Date Received"),
				//	new AuditLogModel("AN-SL-346: Upload Sample Metadata template and verify that new row is added with 'Modified' status on updating Sample ID", "This test case will verify that on uploading sample metadata template after updating Sample ID new row is added with 'Modified status'", "Sample ID")
					));
	
//////////////////////////////////////////////////////////////End Audit Log//////////////////////////////////////////////////////////////////////////////////	
	

/////////////////////////////////////////////////////////AutoLogin///////////////////////////////////////////////////////////////////////////////////////////

public static ArrayList<AutoLoginModel> lstAutoLoginCheck = new ArrayList<>();

////////////////////////////////////////////////////////End AutoLogin/////////////////////////////////////////////////////////////////////////////////////////
}