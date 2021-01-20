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
import Models.AccessModel;
import Models.AgreementManagementModel;
import Models.CoccidiaModel;
import Models.CoccidiaTimelineModel;
import Models.DataTemplateModel;
import Models.ExternalCoccidiaModel;
import Models.ExternalSalmonellaModel;
import Models.LoginModel;
import Models.OrgModel;
import Models.ProfileModel;
import Models.RMModel;
import Models.SalmonellaModel;
import Models.UserModel;


public class Test_Variables {

	public static ExtentTest test;
	public static ExtentTest node;
	public static ExtentTest preconditions; 
	public static ExtentTest steps; 
	public static ExtentTest results; 
	public static ExtentSparkReporter spark;
	public static ExtentReports extent;

	
	public static String login_email = "junaid.alam@analytics.com.pk";
	public static String login_password = "junaid123";
	
	//public static String login_email = "faraz@tenx.ai";
	//public static String login_password = "tenx01tenx";
	
	
	
	public static String forgotPassword_email = "ancera.test.user100@gmail.com";
	public static String forgotPassword_password = "ancera123";
	public static String forgotPasswordSecurityEmail = "junnaid0005@gmail.com";
	public static String forgotPassword_resetPassword = "ancera123";
	
	public static String createUserEmail = "ancera.test.user101@gmail.com";
	public static String createUserPassword = "ancera123";
	public static String createUserSecurityEmail = "ancera.test.user100@gmail.com";
	
	public static String piperId = "PSN0002";
	public static String piperPassword = "piperdemo";
	
	public static String fileDownloadPath = "C:\\Users\\Muhammad Jawad Rauf\\Downloads";
	
	
	
	static DateFormat dateFormat = new SimpleDateFormat("MM_dd_HH_mm");
	static Date date1 = new Date();
	public static String date = dateFormat.format(date1);

	static DateFormat dateFormat0 = new SimpleDateFormat("mmss");
	static Date date10 = new Date();
	public static String date0 = dateFormat0.format(date10);
	
	static DateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	static Date date11 = new Date();
	static String date100 = dateFormat1.format(date11);
		
	public static String profileTitle = "Profile Settings (Testing Environment)";
	
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
					new UserModel(true, "", "", false, "", true, "", false, false, false, false, false, "Leave all fields empty and click on next button", "User was not able to navigate to step 2 of create user successfully", "User was able to navigate to step 2 of create user", "AN-UM-03: Verify mandatory field check with all fields empty", "This test case will verify that user cannot create user with mandatory fields empty"),
					new UserModel(false, "firstname", "lastname", true, "", true, "", false, false, false, false, false, "Leave only 1 field empty and click on next button", "User was not able to navigate to step 2 of create user successfully", "User was able to navigate to step 2 of create user", "AN-UM-04: Verify mandatory field check with 1 field empty", "This test case will verify that user cannot create user leaving 1 mandatory field empty"),
					new UserModel(false, "", "", false, "alpha", true, "", false, false, false, false, false, "Enter alpha data in phone number field", "User was not able to enter alpha data in phone number field successfully", "User was able to enter alpha data in phone number field successfully", "AN-UM-05: Verify user cannot enter alpha data in phone number field", "This test case will verify that user cannot enter alpha data in phone number field"),
					new UserModel(false, "firstname", "lastname", true, "6666666666", true, "email@ancera.com", false, false, false, false, false, "Enter valid data in all mandatory fields", "User was able to navigate to step 2 of create user successfully", "User was not able to navigate to step 2 of create user successfully", "AN-UM-06: Verify user can proceed to step 2 of create user after filling all mandatory fields", "This test case will verify that user can proceed to next step after filling all mandatory fields")));	
	
	public static ArrayList<String> lstUserCreate = new ArrayList<>(
			Arrays.asList("Ancera Test", 
					"User",
					"(666) 666-6666"));
	
	public static ArrayList<String> lstUserEmails = new ArrayList<>(
			Arrays.asList("anceraTest",
					Test_Variables.createUserEmail));

	public static ArrayList<String> lstUserAlertMessages = new ArrayList<>(
			Arrays.asList("New user created.", 
					"User details updated.",
					"User details deleted."));
	
	public static ArrayList<String> lstUserUpdate = new ArrayList<>(
			Arrays.asList("User updated"));

	public static ArrayList<UserModel> lstUserSearch = new ArrayList<>(
		Arrays.asList(
			new UserModel(lstUserCreate.get(0), "AN-UM-15: Verify user can search for created user", "This test case will verify that user can search for created user", "1", "Search for created user", "User searched successfully for valid input", "User was not able to search for created user"),
			new UserModel("invalid input", "AN-UM-16: Verify search using invalid input returns 0 results", "This test case will verify that search using invalid input returns 0 results", "0", "Search using invalid data", "Invalid input returned no result", "Invalid input returned results")));

	
	///////////////////////////////////////////////////////End User Management Screen Variables///////////////////////////////////////////////////

	
	
	////////////////////////////////////////////////////Organization Management Screen Variables///////////////////////////////////////////////////
	
	public static ArrayList<OrgModel> lstOrgMandatoryCheck = new ArrayList<>(
			Arrays.asList(
					new OrgModel(true, false, "", false, "", "", "Leave all mandatory fields empty", true,
							"Click on next button", "", false, false, "AN-OM-03: Verify user cannot create organization with leaving all fields empty", 
							"This testcase will verify that user cannot create organization with leaving all fields empty", "User was not able to create organization with all fields empty successfully", "User was able to create organization with all feilds empty"),


					new OrgModel(false, false, "Ancera Test Org", false, "", "", "Enter only org name", true,
							"Click on next button", "", false, false, "AN-OM-04: Verify user cannot create organization with only filling org name field",
							"This testcase will verify that user cannot create organization with leaving all fields empty", "User was not able to create organization with all fields empty successfully", "User was able to create organization with all feilds empty"),


					new OrgModel(false, false, "", false, "", "ancera"+date0+"@ancera.com", "Enter only email", true,
							"Click on next button", "", false, false, "AN-OM-05: Verify user cannot create organization with only filling email field",
							"This testcase will verify that user cannot create organization with  leaving all fields empty", "User was not able to create organization with all fields empty successfully", "User was able to create organization with all feilds empty"),


					new OrgModel(false, false, "", false, "6666666666", "", "Enter only phone number", true,
							"Click on next button", "", false, false, "AN-OM-06: Verify user cannot create organization with only filling phone number field", 
							"This testcase will verify that user cannot create organization with  leaving all fields empty", "User was not able to create organization with all fields empty successfully", "User was able to create organization with all feilds empty"),


					new OrgModel(false, true, "", false, "", "", "Select any value from Org Type dropdown", true,
							"Click on next button", "", false, false, "AN-OM-07: Verify user cannot create organization with only filling org type field", 
							"This testcase will verify that user cannot create organization with  leaving all fields empty", "User was not able to create organization with all fields empty successfully", "User was able to create organization with all feilds empty"),


					new OrgModel(false, true, "Ancera Test Org", false, "", "", "Select only Org Type and Name", true,
							"Click on next button", "", false, false, "AN-OM-08: Verify user cannot create organization with only filling Org Type and Name field", 
							"This testcase will verify that user cannot create organization with  leaving all fields empty", "User was not able to create organization with all fields empty successfully", "User was able to create organization with all feilds empty"),


					new OrgModel(false, false, "", false, "", "ancera"+date0+"@ancera.com", "Select only Org Type and Email", true,
							"Click on next button", "", false, false, "AN-OM-09: Verify user cannot create organization with only filling Org Type and Email field", 
							"This testcase will verify that user cannot create organization with  leaving all fields empty", "User was not able to create organization with all fields empty successfully", "User was able to create organization with all feilds empty"),


					new OrgModel(false, true, "Ancera Test Org", true, "", "ancera"+date0+"@ancera.com", "Select Org Type, Country Code", true,
							"Click on next button", "", false, false, "AN-OM-10: Verify user cannot create organization with only filling Org Type and Country Code field",
							"This testcase will verify that user cannot create organization with  leaving all fields empty", "User was not able to create organization with all fields empty successfully", "User was able to create organization with all feilds empty"),


					new OrgModel(false, true, "Ancera Test Org", false, "", "ancera"+date0+"@ancera.com", "Select Org Type, Name and Email", true,
							"Click on next button", "", false, false, "AN-OM-11: Verify user cannot create organization with only filling Org Type, Email and Name field", 
							"This testcase will verify that user cannot create organization with  leaving all fields empty", "User was not able to create organization with all fields empty successfully", "User was able to create organization with all feilds empty"),


					new OrgModel(false, true, "Ancera Test Org", true, "6666666666", "", "Fill all fields and leave only email field empty", true,
							"Click on next button", "", false, false, "AN-OM-12: Verify user cannot create organization with leaving only email field empty",
							"This testcase will verify that user cannot create organization with  leaving all fields empty", "User was not able to create organization with all fields empty successfully", "User was able to create organization with all feilds empty"),

					new OrgModel(false, true, "", true, "6666666666", "ancera"+date0+"@ancera.com", "Fill all fields and leave only name field empty", true,
							"Click on next button", "", false, false, "AN-OM-13: Verify user cannot create organization with leaving only name field empty", 
							"This testcase will verify that user cannot create organization with  leaving all fields empty", "User was not able to create organization with all fields empty successfully", "User was able to create organization with all feilds empty"),

					new OrgModel(false, true, "Test Organization"+date0, true, "6666666666", "ancera"+date0+"@ancera.com", "Fill all fields ", false,
							"Click on next button", "", false, true, "AN-OM-14: Verify user navigates to step 2 of create organization popup after filling valid data in all fields",
							"This testcase will verify that user cannot create organization with  leaving all fields empty", "User was not able to create organization with all fields empty successfully", "User was able to create organization with all feilds empty"),

					new OrgModel(false, false, "Ancera Test Org", false, "6666666666", "ancera"+date0+"@ancera.com", "Fill all fields and leave role field empty", false,
							"Click on save button", "5", false, true, "AN-OM-15: Verify user cannot create organization with leaving only role field empty",
							"This testcase will verify that user cannot create organization with  leaving all fields empty", "User was not able to create organization with all fields empty successfully", "User was able to create organization with all feilds empty")
					));


	public static ArrayList<String> lstOrganizationCreate = new ArrayList<>(
			Arrays.asList("Test Organization"+date0,
					"(666) 666-6666",
					"ancera",  //invalid email
					"ancera"+date0+"@ancera.com",  //valid email
					"100"));


	public static ArrayList<OrgModel> lstOrgSearch = new ArrayList<>(
			Arrays.asList(
					new OrgModel("Test Organization"+date0, "AN-OM-21: Verify user can search for created organization", "This test case will verify that user can search for created organization", "1", "Search for created organization", "Organiation searched successfully for valid input", "User was not able to search for created organization"),
					new OrgModel("invalid input", "AN-OM-22: Verify search using invalid input returns 0 results", "This test case will verify that search using invalid input returns 0 results", "0", "Search using invalid data", "Invalid input returned no result", "Invalid input returned results")));


	public static ArrayList<String> lstOrgAlertMessages = new ArrayList<>(
			Arrays.asList("New organization created.", 
					"Organization details updated.",
					"New site created.",
					"Site details updated.",
					"Site details deleted.",
					"Organization details deleted."));

	
	////////////////////////////////////////////////////End Organization Management Screen Variables////////////////////////////////////////////
	
	
	
	////////////////////////////////////////////////////////Access Management Screen Variables//////////////////////////////////////////////////
	
	public static ArrayList<AccessModel> lstAccessMandatoryCheck = new ArrayList<>(
			Arrays.asList(
					new AccessModel(true, "", "", "Leave both fields empty", "AN-AM-04: Leave both fields empty", true),
					new AccessModel(true, "Test", "", "Leave only desc field empty", "AN-AM-05: Leave only Description field empty", true),
					new AccessModel(true, "", "desc", "Leave name field empty", "AN-AM-06: Leave 1 field empty", true)
				//	new AccessModel(true, "Administrator"+date0, "Role Test Description", "Fill both fields; should create role", "AN-RM-06: Enter data in both fields; should create role", false)
					));
	
	
	public static ArrayList<String> lstAccessCreate = new ArrayList<>(
			Arrays.asList("Administrator"+date0, 
					"Role Description"));
	
	
	public static ArrayList<String> lstAccessAlertMessages = new ArrayList<>(
			Arrays.asList("New role created.", 
					"Role details updated."));
	

	//////////////////////////////////////////////////////End Access Management Screen Variables////////////////////////////////////////////////


	/////////////////////////////////////////////////////Agreement Management Variables////////////////////////////////////////////////////////
	
	public static ArrayList<AgreementManagementModel> lstAgreementManagement = new ArrayList<>(
			Arrays.asList(
					new AgreementManagementModel("AN-License-02: Verify user can upload pdf file", "This test case will verify that user can upload pdf file", "User was able to upload pdf file successfully", "User was not able to upload pdf file", "/EULA/sample.pdf", "PNG file", "New user agreement created."),
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
					new AgreementManagementModel("samplefile"+date0+".pdf", "AN-License-33: Verify that assigned and reactivated agreement is displayed in user agreement dropdown", "This testcase will verify that assigned and reactivated agreement is displayed in user agreement dropdown", "The assigned and reactivated agreement was displayed successfully", "The assigned and reactivated agreement was not displayed")
							));
			
			
			
	public static ArrayList<String> lstAgreemmentManagementFileName = new ArrayList<>(
			Arrays.asList("sample.pdf", 
					"file"+date0+".pdf",
					"samplefile"+date0+".pdf"));
	
	////////////////////////////////////////////////End Agreement Management Variables////////////////////////////////////////////////////////
	
	
	
	
	
	////////////////////////////////////////////////////////Reports Management Screen Variables//////////////////////////////////////////////////
	
	public static String RoleName = "Test Role"+date0;
	public static String ReportName = "Test Report Group"+date0;
	public static int ReportGroupConfigLength = 35;
	public static int ReportGroupLength = 35;


	public static ArrayList<RMModel> lstRMMandatoryCheck = new ArrayList<>(
			Arrays.asList(
					new RMModel(true, "", "", "1. Leave all fields empty", false, "AN-RM-04: Verify Mandatory field check with all fields empty", "This test case will verify that user is not able to create role with all fields empty", true, "The user was not able to create Role leaving all fields empty", "User was able to create a Role leaving all fields empty"),
					new RMModel(true, "", "desc", "1. Leave one field empty", false, "AN-RM-05: Verify Mandatory field check with one field empty","This test case will verify that user is not able to create role with one field empty", true, "The user was not able to create Role leaving all fields empty", "User was able to create a Role leaving all fields empty"),
					new RMModel(true, RoleName, "Role Test Description", "1. Enter valid data in both fields", true, "AN-RM-06: Verify user is able to create a new Role", "This test case will verify that user is able to create new role", false, "The user was able to create a role successfully", "The user was not able to create a Role")));


	public static ArrayList<String> lstRMtestCase = new ArrayList<>(
			Arrays.asList("", 
					""));
	
	public static ArrayList<String> lstRMtestCaseDesc = new ArrayList<>(
			Arrays.asList(RoleName, 
					"Role description updated"));
	
	public static ArrayList<String> lstRMUpdation = new ArrayList<>(
			Arrays.asList(RoleName, 
					"Role description updated"));


	public static ArrayList<RMModel> lstRGMandatoryCheck = new ArrayList<>(
			Arrays.asList(
					new RMModel("", "", false, "Leave all fields empty", false, "AN-RM-16: Leave all fields empty and click on save button", "This test case wll verify that user cannot create report group without filling all mandatory fields", true, "The user was not able to create Report Group leaving all fields empty", "User was able to create a Report Group leaving all fields empty"),
					new RMModel("", "Lorem Ipsum", false, "Leave 2 fields empty", false, "AN-RM-17: Leave 2 field empty and click on save button", "This test case will verify that user is not able to create role with two field empty", true, "The user was not able to create Report Group leaving 2 fields empty", "User was able to create a Report Group leaving 2 fields empty"),
					new RMModel("", "desc", true, "Leave 1 field empty", false, "AN-RM-18: Leave 1 field empty and click on save button", "This test case will verify that user is not able to create role with one field empty", true, "The user was not able to create Report Group leaving 1 field empty", "User was able to create a Report Group leaving 1 field empty"),
					new RMModel(ReportName, "Group created by automation script", false, "Fill all fields", true, "AN-RM-19: User should be able to save Report Details", "This test case will verify that user is able to create new report group", false, "The user was able to create Report Group after entering valid data in all fields", "The user was not able to create Report Group")));

	//////////////////////////////////////////////////////End Reports Management Screen Variables////////////////////////////////////////////////
	
	
	
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
	
	////////////////////////////////////////////////////End Data Upload Variables///////////////////////////////////////////////////////////////////
	
	
	
	/////////////////////////////////////////////////////////Announcement API Variables/////////////////////////////////////////////////////

	
	public static String AnnouncementRunID = "MPNfile"+Test_Variables.date0;
	
	public static ArrayList<String> lstApiAnnouncement = new ArrayList<>(
			Arrays.asList(AnnouncementRunID,   //runId
					"2019-12-12",                           //date
					"DESKTOP-77VTAPV",                      //piperId
					"0",  									//MPNCalculationType
					"20200120202842_PSN0009_sammyprocess_oaeng_improc.csv",    				 //FileName
					"fab8cc1ea3f6db40c4555c3dfab09a34d2fa2b53cd66af72284af1dacb266910"));    //CheckSum              

	
	public static ArrayList<String> lstRunID = new ArrayList<>(
			Arrays.asList("Automation"+date0,  
					"Automation"+date0,
					"Automation1015")); 
		
	public static ArrayList<String> lstSampleID = new ArrayList<>(
			Arrays.asList("Automation"+date0,  
					"Automation116",
					"Automation118")); 
	
	/////////////////////////////////////////  ////////////End Announcement API Variables/////////////////////////////////////////////////////
	

	
	///////////////////////////////////////////////////////////Salmonella Ingest//////////////////////////////////////////////////////////////////
	
	public static ArrayList<APIModel> lstSalmonellaIngest = new ArrayList<>(
			Arrays.asList(
					new APIModel(AnnouncementRunID, "fab8cc1ea3f6db40c4555c3dfab09a34d2fa2b53cd66af72284af1dacb266910", 
							"20200120202842_PSN0009_sammyprocess_oaeng_improc.csv", "csv",                                                                                                        //03132020_PSN16_21612_lis1
							"TEFORV9OTyxEQVRFLFRJTUUsVzFfUENfQ09VTlQsVzFfQ0VMTF9DT1VOVCxXMV9QQ19NRUFOX0lOVEVOU0lUWSxXMV9DRUxMX01FQU5fSU5URU5TSVRZLFcxX1BDX1JBTkdFX0lOVEVOU0lUWSxXMV9DRUxMX1JBTkdFX0lOVEVOU0lUWSxXMV9QQ19DVl9JTlRFTlNJVFksVzFfQ0VMTF9DVl9JTlRFTlNJVFksVzFfUENfTUVBTl9TSVpFLFcxX0NFTExfTUVBTl9TSVpFLFcxX1BDX1JBTkdFX1NJWkUsVzFfQ0VMTF9SQU5HRV9TSVpFLFcxX1BDX0NWX1NJWkUsVzFfQ0VMTF9DVl9TSVpFLFcyX1BDX0NPVU5ULFcyX0NFTExfQ09VTlQsVzJfUENfTUVBTl9JTlRFTlNJVFksVzJfQ0VMTF9NRUFOX0lOVEVOU0lUWSxXMl9QQ19SQU5HRV9JTlRFTlNJVFksVzJfQ0VMTF9SQU5HRV9JTlRFTlNJVFksVzJfUENfQ1ZfSU5URU5TSVRZLFcyX0NFTExfQ1ZfSU5URU5TSVRZLFcyX1BDX01FQU5fU0laRSxXMl9DRUxMX01FQU5fU0laRSxXMl9QQ19SQU5HRV9TSVpFLFcyX0NFTExfUkFOR0VfU0laRSxXMl9QQ19DVl9TSVpFLFcyX0NFTExfQ1ZfU0laRSxTVEFUVVMsTEFORV9UT1RBTF9BUkVBX1VNMixMQU5FX05PSVNFX0FSRUFfVU0yLExBTkVfTk9JU0VfUkFUSU9fUEVSQ0VOVCxMQU5FX05PSVNFX09CSkVDVF9DT1VOVCxXMV9OT0lTRV9BUkVBX1VNMixXMl9OT0lTRV9BUkVBX1VNMixXMV9OT0lTRV9SQVRJT19QRVJDRU5ULFcyX05PSVNFX1JBVElPX1BFUkNFTlQsVzFfTk9JU0VfT0JKRUNUX0NPVU5ULFcyX05PSVNFX09CSkVDVF9DT1VOVCxMQU5FX1NNQUxMX05PSVNFX09CSkVDVF9DT1VOVCxMQU5FX01FRElVTV9OT0lTRV9PQkpFQ1RfQ09VTlQsTEFORV9MQVJHRV9OT0lTRV9PQkpFQ1RfQ09VTlQsTEFORV9FWFRSQV9MQVJHRV9OT0lTRV9PQkpFQ1RfQ09VTlQsRVJST1JfQ09ERQ0KMSwyMDIwLTAxLTIwLDIwOjM0OjI0LjU1NjAxNCw3LDU5LDExNy4yNzgzNjczNDY5Mzg3OCwyMi40MTk5Mjg4MjU2MjI3NzYsMjMzLDEyOCwwLjYwMDUzMjk4NTc4OTI5MTMsMS4wNTczODE3MTE4OTc4NzY4LDE1My4xNDI4NTcxNDI4NTcxNCw2Ljg5Mjg1NzE0Mjg1NzE0MywxNzguMDcsMTIuNzk0OTk5OTk5OTk5OTk4LDAuNDUzNDc2MjM3NjMwMzEzMTQsMC42NjI2NjU3Mzk3MDE0MTg4LDIxLDI1LDgyLjU1NDY1NTg3MDQ0NTM0LDEwLjMxOTg2NTMxOTg2NTMyLDI1MSw1MCwwLjk0ODIzNTAwNzExMDYwMSwwLjgwMDQxODA5OTgwNDYzNDcsNjguNjkwNDc2MTkwNDc2MTksOC42NDcwNTg4MjM1Mjk0MTEsMTI0LjIsMTMuMjM5OTk5OTk5OTk5OTk4LDAuODM0MTA4MTAzNDA1MTA3NCwwLjQ1ODIxMjYyOTA4OTc2NjMsNTQwNjI2MTEzLjAsMTIxOTE0My41LDAuMjMsNDUxLDUwMzMuNSwxODk4Ny4wLDAuMDA2MjU5MDU0MDg1MTQxMDQyLDAuMDIzNjA5OTQ1MzQ5MDc1NzgyLDIzLDU1LDQ1MCwxLDAsMCwNCjIsMjAyMC0wMS0yMCwyMDozNTozMC45NTg4NDMsMCwwLG5hbixuYW4sMCwwLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sMCwxLG5hbixuYW4sMCwwLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sNTM5NzI2NjA3LjAsNDU4NDcuMCwwLjAxLDE5LDAuMCwwLjAsMC4wLDAuMCwwLDAsMTksMCwwLDAsDQozLDIwMjAtMDEtMjAsMjA6Mzc6MjEuMjQ4ODg5LDAsMCxuYW4sbmFuLDAsMCxuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLDAsMzMsbmFuLDExNS45OTIzNjY0MTIyMTM3NCwwLDEzMCxuYW4sMC4yOTg1MjI3MzE4MDI4NTE1NSxuYW4sOC43LG5hbiwxMS4zMTk5OTk5OTk5OTk5OTksbmFuLDAuNTAzMTI4MDE0NDQyMjgyNiw1Mzg2NzMzOTguMCwyMDc3NzQuMCwwLjA0LDI2LDAuMCwwLjAsMC4wLDAuMCwwLDAsMjUsMSwwLDAsDQo=", 
							"[{'LANE':'1','PATHOGEN':'Listeria-Probes','SAMPLEID': 'Test"+lstSampleID.get(0)+"','SCANDATETIME':'"+date100+"','OUTCOME':'Positive','STATUSCODE':'3','CALIBRATED_PIPER_COUNTS':'5550','COUNT_OUTCOME':'PASS','CARTRIDGEID':'TestAutomation','EXPERIMENTID':'','INSTRUMENTID':'PSN0018','USERID':'98','RUN_ID':'Test"+lstRunID.get(0)+"','RUN_TYPE':'SCRIPT_1005a Listeria Probes','LANE_NO':'1','DATE':'2020-03-13','TIME':'','W1_PC_COUNT':'0','W1_CELL_COUNT':'5000','W1_PC_MEAN_INTENSITY':'','W1_CELL_MEAN_INTENSITY':'','W1_PC_RANGE_INTENSITY':'0','W1_CELL_RANGE_INTENSITY':'216','W1_PC_CV_INTENSITY':'','W1_CELL_CV_INTENSITY':'0.9665891343102634','W1_PC_MEAN_SIZE':'','W1_CELL_MEAN_SIZE':'6.491416309012876','W1_PC_RANGE_SIZE':'','W1_CELL_RANGE_SIZE':'11.0','W1_PC_CV_SIZE':'','W1_CELL_CV_SIZE':'1.039571210329305','W2_PC_COUNT':'1','W2_CELL_COUNT':'45','W2_PC_MEAN_INTENSITY':'77.69852941176471','W2_CELL_MEAN_INTENSITY':'9.731929549219636','W2_PC_RANGE_INTENSITY':'191','W2_CELL_RANGE_INTENSITY':'218','W2_PC_CV_INTENSITY':'0.5874496495238567','W2_CELL_CV_INTENSITY':'0.5872565165253875','W2_PC_MEAN_SIZE':'116.5','W2_CELL_MEAN_SIZE':'13.179852084267145','W2_PC_RANGE_SIZE':'0.0','W2_CELL_RANGE_SIZE':'37.0','W2_PC_CV_SIZE':'0.0','W2_CELL_CV_SIZE':'1.1630941924867089','STATUS':'','LANE_TOTAL_AREA_UM2':'536636840.0','LANE_NOISE_AREA_UM2':'5123.5','LANE_NOISE_RATIO_PERCENT':'0.0','LANE_NOISE_OBJECT_COUNT':'7','W1_NOISE_AREA_UM2':'1468.0','W2_NOISE_AREA_UM2':'8921.5','W1_NOISE_RATIO_PERCENT':'0.001825427912384434','W2_NOISE_RATIO_PERCENT':'0.011093702398050225','W1_NOISE_OBJECT_COUNT':'5','W2_NOISE_OBJECT_COUNT':'44','LANE_SMALL_NOISE_OBJECT_COUNT':'7','LANE_MEDIUM_NOISE_OBJECT_COUNT':'0','LANE_LARGE_NOISE_OBJECT_COUNT':'0','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'0','IMPROC':'ImprocSalm01','VERSION':'2.4','ERROR_CODE':'', 'MPN_CALCULATION_TYPE':'1', 'DILUTION_FACTOR':'0.1'}]", 
							"ImprocSalm01", true, "AN-API_FileUpload-01: Run File Upload API with unique Run ID", "This test case will run ingestion with unique run id", "Run API with unique RUN ID and unique Sample ID", "File Upload API ran successfully; File was ingested", "File Upload API failed to run", "AN-SL-01: Verify that data is ingested after running API with Unique Run ID", "This test case will verify that data is ingested after running file upload API")
					
//					new SalmonellaIngestModel(AnnouncementRunID, "fab8cc1ea3f6db40c4555c3dfab09a34d2fa2b53cd66af72284af1dacb266910", 
//							"20200120202842_PSN0009_sammyprocess_oaeng_improc.csv", "csv", 
//							"TEFORV9OTyxEQVRFLFRJTUUsVzFfUENfQ09VTlQsVzFfQ0VMTF9DT1VOVCxXMV9QQ19NRUFOX0lOVEVOU0lUWSxXMV9DRUxMX01FQU5fSU5URU5TSVRZLFcxX1BDX1JBTkdFX0lOVEVOU0lUWSxXMV9DRUxMX1JBTkdFX0lOVEVOU0lUWSxXMV9QQ19DVl9JTlRFTlNJVFksVzFfQ0VMTF9DVl9JTlRFTlNJVFksVzFfUENfTUVBTl9TSVpFLFcxX0NFTExfTUVBTl9TSVpFLFcxX1BDX1JBTkdFX1NJWkUsVzFfQ0VMTF9SQU5HRV9TSVpFLFcxX1BDX0NWX1NJWkUsVzFfQ0VMTF9DVl9TSVpFLFcyX1BDX0NPVU5ULFcyX0NFTExfQ09VTlQsVzJfUENfTUVBTl9JTlRFTlNJVFksVzJfQ0VMTF9NRUFOX0lOVEVOU0lUWSxXMl9QQ19SQU5HRV9JTlRFTlNJVFksVzJfQ0VMTF9SQU5HRV9JTlRFTlNJVFksVzJfUENfQ1ZfSU5URU5TSVRZLFcyX0NFTExfQ1ZfSU5URU5TSVRZLFcyX1BDX01FQU5fU0laRSxXMl9DRUxMX01FQU5fU0laRSxXMl9QQ19SQU5HRV9TSVpFLFcyX0NFTExfUkFOR0VfU0laRSxXMl9QQ19DVl9TSVpFLFcyX0NFTExfQ1ZfU0laRSxTVEFUVVMsTEFORV9UT1RBTF9BUkVBX1VNMixMQU5FX05PSVNFX0FSRUFfVU0yLExBTkVfTk9JU0VfUkFUSU9fUEVSQ0VOVCxMQU5FX05PSVNFX09CSkVDVF9DT1VOVCxXMV9OT0lTRV9BUkVBX1VNMixXMl9OT0lTRV9BUkVBX1VNMixXMV9OT0lTRV9SQVRJT19QRVJDRU5ULFcyX05PSVNFX1JBVElPX1BFUkNFTlQsVzFfTk9JU0VfT0JKRUNUX0NPVU5ULFcyX05PSVNFX09CSkVDVF9DT1VOVCxMQU5FX1NNQUxMX05PSVNFX09CSkVDVF9DT1VOVCxMQU5FX01FRElVTV9OT0lTRV9PQkpFQ1RfQ09VTlQsTEFORV9MQVJHRV9OT0lTRV9PQkpFQ1RfQ09VTlQsTEFORV9FWFRSQV9MQVJHRV9OT0lTRV9PQkpFQ1RfQ09VTlQsRVJST1JfQ09ERQ0KMSwyMDIwLTAxLTIwLDIwOjM0OjI0LjU1NjAxNCw3LDU5LDExNy4yNzgzNjczNDY5Mzg3OCwyMi40MTk5Mjg4MjU2MjI3NzYsMjMzLDEyOCwwLjYwMDUzMjk4NTc4OTI5MTMsMS4wNTczODE3MTE4OTc4NzY4LDE1My4xNDI4NTcxNDI4NTcxNCw2Ljg5Mjg1NzE0Mjg1NzE0MywxNzguMDcsMTIuNzk0OTk5OTk5OTk5OTk4LDAuNDUzNDc2MjM3NjMwMzEzMTQsMC42NjI2NjU3Mzk3MDE0MTg4LDIxLDI1LDgyLjU1NDY1NTg3MDQ0NTM0LDEwLjMxOTg2NTMxOTg2NTMyLDI1MSw1MCwwLjk0ODIzNTAwNzExMDYwMSwwLjgwMDQxODA5OTgwNDYzNDcsNjguNjkwNDc2MTkwNDc2MTksOC42NDcwNTg4MjM1Mjk0MTEsMTI0LjIsMTMuMjM5OTk5OTk5OTk5OTk4LDAuODM0MTA4MTAzNDA1MTA3NCwwLjQ1ODIxMjYyOTA4OTc2NjMsNTQwNjI2MTEzLjAsMTIxOTE0My41LDAuMjMsNDUxLDUwMzMuNSwxODk4Ny4wLDAuMDA2MjU5MDU0MDg1MTQxMDQyLDAuMDIzNjA5OTQ1MzQ5MDc1NzgyLDIzLDU1LDQ1MCwxLDAsMCwNCjIsMjAyMC0wMS0yMCwyMDozNTozMC45NTg4NDMsMCwwLG5hbixuYW4sMCwwLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sMCwxLG5hbixuYW4sMCwwLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sNTM5NzI2NjA3LjAsNDU4NDcuMCwwLjAxLDE5LDAuMCwwLjAsMC4wLDAuMCwwLDAsMTksMCwwLDAsDQozLDIwMjAtMDEtMjAsMjA6Mzc6MjEuMjQ4ODg5LDAsMCxuYW4sbmFuLDAsMCxuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLDAsMzMsbmFuLDExNS45OTIzNjY0MTIyMTM3NCwwLDEzMCxuYW4sMC4yOTg1MjI3MzE4MDI4NTE1NSxuYW4sOC43LG5hbiwxMS4zMTk5OTk5OTk5OTk5OTksbmFuLDAuNTAzMTI4MDE0NDQyMjgyNiw1Mzg2NzMzOTguMCwyMDc3NzQuMCwwLjA0LDI2LDAuMCwwLjAsMC4wLDAuMCwwLDAsMjUsMSwwLDAsDQo=", 
//							"[{'LANE':'1','PATHOGEN':'Listeria-Probes','SAMPLEID': 'Test"+lstSalmonellaSampleID.get(1)+"','SCANDATETIME':'2020/10/26 08:30:17','OUTCOME':'Positive','STATUSCODE':'3','CALIBRATED_PIPER_COUNTS':'5550','COUNT_OUTCOME':'PASS','CARTRIDGEID':'TestAutomation','EXPERIMENTID':'','INSTRUMENTID':'PSN0018','USERID':'98','RUN_ID':'Test"+lstSalmonellaRunID.get(1)+"','RUN_TYPE':'SCRIPT_1005a Listeria Probes','LANE_NO':'1','DATE':'2020-03-13','TIME':'','W1_PC_COUNT':'0','W1_CELL_COUNT':'5000','W1_PC_MEAN_INTENSITY':'','W1_CELL_MEAN_INTENSITY':'','W1_PC_RANGE_INTENSITY':'0','W1_CELL_RANGE_INTENSITY':'216','W1_PC_CV_INTENSITY':'','W1_CELL_CV_INTENSITY':'0.9665891343102634','W1_PC_MEAN_SIZE':'','W1_CELL_MEAN_SIZE':'6.491416309012876','W1_PC_RANGE_SIZE':'','W1_CELL_RANGE_SIZE':'11.0','W1_PC_CV_SIZE':'','W1_CELL_CV_SIZE':'1.039571210329305','W2_PC_COUNT':'1','W2_CELL_COUNT':'45','W2_PC_MEAN_INTENSITY':'77.69852941176471','W2_CELL_MEAN_INTENSITY':'9.731929549219636','W2_PC_RANGE_INTENSITY':'191','W2_CELL_RANGE_INTENSITY':'218','W2_PC_CV_INTENSITY':'0.5874496495238567','W2_CELL_CV_INTENSITY':'0.5872565165253875','W2_PC_MEAN_SIZE':'116.5','W2_CELL_MEAN_SIZE':'13.179852084267145','W2_PC_RANGE_SIZE':'0.0','W2_CELL_RANGE_SIZE':'37.0','W2_PC_CV_SIZE':'0.0','W2_CELL_CV_SIZE':'1.1630941924867089','STATUS':'','LANE_TOTAL_AREA_UM2':'536636840.0','LANE_NOISE_AREA_UM2':'5123.5','LANE_NOISE_RATIO_PERCENT':'0.0','LANE_NOISE_OBJECT_COUNT':'7','W1_NOISE_AREA_UM2':'1468.0','W2_NOISE_AREA_UM2':'8921.5','W1_NOISE_RATIO_PERCENT':'0.001825427912384434','W2_NOISE_RATIO_PERCENT':'0.011093702398050225','W1_NOISE_OBJECT_COUNT':'5','W2_NOISE_OBJECT_COUNT':'44','LANE_SMALL_NOISE_OBJECT_COUNT':'7','LANE_MEDIUM_NOISE_OBJECT_COUNT':'0','LANE_LARGE_NOISE_OBJECT_COUNT':'0','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'0','IMPROC':'ImprocSalm01','VERSION':'2.4','ERROR_CODE':'', 'MPN_CALCULATION_TYPE':'1', 'DILUTION_FACTOR':'0.1'}]", 
//							"ImprocSalm01", true, "AN-SL-04: Run File Upload Ingest API with duplicate Run ID and unique Sample ID", "This test case will run ingestion with same run id and unique sample id", "Run API with duplicate Run ID and unique Sample ID", "File Upload API ran successfully; File was ingested", "File Upload API failed to run", "AN-SL-05: Verify that data is not ingested after running API with same Run ID", "This test case will verify that data is not ingested after running file upload API with duplicate RUN ID"),
					
//					new SalmonellaIngestModel(AnnouncementRunID, "fab8cc1ea3f6db40c4555c3dfab09a34d2fa2b53cd66af72284af1dacb266910", 
//							"20200120202842_PSN0009_sammyprocess_oaeng_improc.csv", "csv", 
//							"TEFORV9OTyxEQVRFLFRJTUUsVzFfUENfQ09VTlQsVzFfQ0VMTF9DT1VOVCxXMV9QQ19NRUFOX0lOVEVOU0lUWSxXMV9DRUxMX01FQU5fSU5URU5TSVRZLFcxX1BDX1JBTkdFX0lOVEVOU0lUWSxXMV9DRUxMX1JBTkdFX0lOVEVOU0lUWSxXMV9QQ19DVl9JTlRFTlNJVFksVzFfQ0VMTF9DVl9JTlRFTlNJVFksVzFfUENfTUVBTl9TSVpFLFcxX0NFTExfTUVBTl9TSVpFLFcxX1BDX1JBTkdFX1NJWkUsVzFfQ0VMTF9SQU5HRV9TSVpFLFcxX1BDX0NWX1NJWkUsVzFfQ0VMTF9DVl9TSVpFLFcyX1BDX0NPVU5ULFcyX0NFTExfQ09VTlQsVzJfUENfTUVBTl9JTlRFTlNJVFksVzJfQ0VMTF9NRUFOX0lOVEVOU0lUWSxXMl9QQ19SQU5HRV9JTlRFTlNJVFksVzJfQ0VMTF9SQU5HRV9JTlRFTlNJVFksVzJfUENfQ1ZfSU5URU5TSVRZLFcyX0NFTExfQ1ZfSU5URU5TSVRZLFcyX1BDX01FQU5fU0laRSxXMl9DRUxMX01FQU5fU0laRSxXMl9QQ19SQU5HRV9TSVpFLFcyX0NFTExfUkFOR0VfU0laRSxXMl9QQ19DVl9TSVpFLFcyX0NFTExfQ1ZfU0laRSxTVEFUVVMsTEFORV9UT1RBTF9BUkVBX1VNMixMQU5FX05PSVNFX0FSRUFfVU0yLExBTkVfTk9JU0VfUkFUSU9fUEVSQ0VOVCxMQU5FX05PSVNFX09CSkVDVF9DT1VOVCxXMV9OT0lTRV9BUkVBX1VNMixXMl9OT0lTRV9BUkVBX1VNMixXMV9OT0lTRV9SQVRJT19QRVJDRU5ULFcyX05PSVNFX1JBVElPX1BFUkNFTlQsVzFfTk9JU0VfT0JKRUNUX0NPVU5ULFcyX05PSVNFX09CSkVDVF9DT1VOVCxMQU5FX1NNQUxMX05PSVNFX09CSkVDVF9DT1VOVCxMQU5FX01FRElVTV9OT0lTRV9PQkpFQ1RfQ09VTlQsTEFORV9MQVJHRV9OT0lTRV9PQkpFQ1RfQ09VTlQsTEFORV9FWFRSQV9MQVJHRV9OT0lTRV9PQkpFQ1RfQ09VTlQsRVJST1JfQ09ERQ0KMSwyMDIwLTAxLTIwLDIwOjM0OjI0LjU1NjAxNCw3LDU5LDExNy4yNzgzNjczNDY5Mzg3OCwyMi40MTk5Mjg4MjU2MjI3NzYsMjMzLDEyOCwwLjYwMDUzMjk4NTc4OTI5MTMsMS4wNTczODE3MTE4OTc4NzY4LDE1My4xNDI4NTcxNDI4NTcxNCw2Ljg5Mjg1NzE0Mjg1NzE0MywxNzguMDcsMTIuNzk0OTk5OTk5OTk5OTk4LDAuNDUzNDc2MjM3NjMwMzEzMTQsMC42NjI2NjU3Mzk3MDE0MTg4LDIxLDI1LDgyLjU1NDY1NTg3MDQ0NTM0LDEwLjMxOTg2NTMxOTg2NTMyLDI1MSw1MCwwLjk0ODIzNTAwNzExMDYwMSwwLjgwMDQxODA5OTgwNDYzNDcsNjguNjkwNDc2MTkwNDc2MTksOC42NDcwNTg4MjM1Mjk0MTEsMTI0LjIsMTMuMjM5OTk5OTk5OTk5OTk4LDAuODM0MTA4MTAzNDA1MTA3NCwwLjQ1ODIxMjYyOTA4OTc2NjMsNTQwNjI2MTEzLjAsMTIxOTE0My41LDAuMjMsNDUxLDUwMzMuNSwxODk4Ny4wLDAuMDA2MjU5MDU0MDg1MTQxMDQyLDAuMDIzNjA5OTQ1MzQ5MDc1NzgyLDIzLDU1LDQ1MCwxLDAsMCwNCjIsMjAyMC0wMS0yMCwyMDozNTozMC45NTg4NDMsMCwwLG5hbixuYW4sMCwwLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sMCwxLG5hbixuYW4sMCwwLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sNTM5NzI2NjA3LjAsNDU4NDcuMCwwLjAxLDE5LDAuMCwwLjAsMC4wLDAuMCwwLDAsMTksMCwwLDAsDQozLDIwMjAtMDEtMjAsMjA6Mzc6MjEuMjQ4ODg5LDAsMCxuYW4sbmFuLDAsMCxuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLDAsMzMsbmFuLDExNS45OTIzNjY0MTIyMTM3NCwwLDEzMCxuYW4sMC4yOTg1MjI3MzE4MDI4NTE1NSxuYW4sOC43LG5hbiwxMS4zMTk5OTk5OTk5OTk5OTksbmFuLDAuNTAzMTI4MDE0NDQyMjgyNiw1Mzg2NzMzOTguMCwyMDc3NzQuMCwwLjA0LDI2LDAuMCwwLjAsMC4wLDAuMCwwLDAsMjUsMSwwLDAsDQo=", 
//							"[{'LANE':'1','PATHOGEN':'Listeria-Probes','SAMPLEID': 'Test"+lstSalmonellaSampleID.get(2)+"','SCANDATETIME':'"+date100+"','OUTCOME':'Positive','STATUSCODE':'3','CALIBRATED_PIPER_COUNTS':'5550','COUNT_OUTCOME':'PASS','CARTRIDGEID':'TestAutomation','EXPERIMENTID':'','INSTRUMENTID':'PSN0018','USERID':'98','RUN_ID':'Test"+lstSalmonellaRunID.get(2)+"','RUN_TYPE':'SCRIPT_1005a Listeria Probes','LANE_NO':'1','DATE':'2020-03-13','TIME':'','W1_PC_COUNT':'0','W1_CELL_COUNT':'5000','W1_PC_MEAN_INTENSITY':'','W1_CELL_MEAN_INTENSITY':'','W1_PC_RANGE_INTENSITY':'0','W1_CELL_RANGE_INTENSITY':'216','W1_PC_CV_INTENSITY':'','W1_CELL_CV_INTENSITY':'0.9665891343102634','W1_PC_MEAN_SIZE':'','W1_CELL_MEAN_SIZE':'6.491416309012876','W1_PC_RANGE_SIZE':'','W1_CELL_RANGE_SIZE':'11.0','W1_PC_CV_SIZE':'','W1_CELL_CV_SIZE':'1.039571210329305','W2_PC_COUNT':'1','W2_CELL_COUNT':'45','W2_PC_MEAN_INTENSITY':'77.69852941176471','W2_CELL_MEAN_INTENSITY':'9.731929549219636','W2_PC_RANGE_INTENSITY':'191','W2_CELL_RANGE_INTENSITY':'218','W2_PC_CV_INTENSITY':'0.5874496495238567','W2_CELL_CV_INTENSITY':'0.5872565165253875','W2_PC_MEAN_SIZE':'116.5','W2_CELL_MEAN_SIZE':'13.179852084267145','W2_PC_RANGE_SIZE':'0.0','W2_CELL_RANGE_SIZE':'37.0','W2_PC_CV_SIZE':'0.0','W2_CELL_CV_SIZE':'1.1630941924867089','STATUS':'','LANE_TOTAL_AREA_UM2':'536636840.0','LANE_NOISE_AREA_UM2':'5123.5','LANE_NOISE_RATIO_PERCENT':'0.0','LANE_NOISE_OBJECT_COUNT':'7','W1_NOISE_AREA_UM2':'1468.0','W2_NOISE_AREA_UM2':'8921.5','W1_NOISE_RATIO_PERCENT':'0.001825427912384434','W2_NOISE_RATIO_PERCENT':'0.011093702398050225','W1_NOISE_OBJECT_COUNT':'5','W2_NOISE_OBJECT_COUNT':'44','LANE_SMALL_NOISE_OBJECT_COUNT':'7','LANE_MEDIUM_NOISE_OBJECT_COUNT':'0','LANE_LARGE_NOISE_OBJECT_COUNT':'0','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'0','IMPROC':'ImprocSalm01','VERSION':'2.4','ERROR_CODE':'', 'MPN_CALCULATION_TYPE':'1', 'DILUTION_FACTOR':'0.1'}]", 
//							"ImprocSalm01", true, "AN-API_FileUpload-01: Run File Upload API with current date/time", "This test case will run ingestion with unique run id and current date/time", "Run API with uniqure Run ID and current date/time", "File Upload API ran successfully; File was ingested on top of the table", "File Upload API failed to run", "AN-SL-07: Verify that data is ingested on top of the table", "This test case will verify that data is ingested on top of the table after running api with current date/time")
					));               
	
	public static ArrayList<SalmonellaModel> lstSalmonellaSearch = new ArrayList<>();
	
	public static String slPNGFileName = "Salmonella Run Timeline - ";
	public static String slCSVFileName = "Salmonella Log - ";
	public static String slSampleMetaData = "Sample Metadata Upload Template";
	
	
	public static ArrayList<SalmonellaModel> lstSalmonellaDateSearch = new ArrayList<>();
	
	public static ArrayList<SalmonellaModel> lstSalmonellaDateEnter = new ArrayList<>();
	
	public static ArrayList<SalmonellaModel> lstSalmonellaRowCount = new ArrayList<>();
	
	public static ArrayList<SalmonellaModel> lstSalmonellaPagination = new ArrayList<>();

	////////////////////////////////////////////////////End Salmonella Ingest Variables/////////////////////////////////////////////////////////////////
	
	
	
	//////////////////////////////////////////////////External Salmonella Ingest Variables//////////////////////////////////////////////////////////////
	
	public static ArrayList<APIModel> lstExternalSalmonellaIngest = new ArrayList<>(
			Arrays.asList(
					new APIModel(AnnouncementRunID, "fab8cc1ea3f6db40c4555c3dfab09a34d2fa2b53cd66af72284af1dacb266910", 
							"20200120202842_PSN0009_sammyprocess_oaeng_improc.csv", "csv",                                                                                                        //03132020_PSN16_21612_lis1
							"TEFORV9OTyxEQVRFLFRJTUUsVzFfUENfQ09VTlQsVzFfQ0VMTF9DT1VOVCxXMV9QQ19NRUFOX0lOVEVOU0lUWSxXMV9DRUxMX01FQU5fSU5URU5TSVRZLFcxX1BDX1JBTkdFX0lOVEVOU0lUWSxXMV9DRUxMX1JBTkdFX0lOVEVOU0lUWSxXMV9QQ19DVl9JTlRFTlNJVFksVzFfQ0VMTF9DVl9JTlRFTlNJVFksVzFfUENfTUVBTl9TSVpFLFcxX0NFTExfTUVBTl9TSVpFLFcxX1BDX1JBTkdFX1NJWkUsVzFfQ0VMTF9SQU5HRV9TSVpFLFcxX1BDX0NWX1NJWkUsVzFfQ0VMTF9DVl9TSVpFLFcyX1BDX0NPVU5ULFcyX0NFTExfQ09VTlQsVzJfUENfTUVBTl9JTlRFTlNJVFksVzJfQ0VMTF9NRUFOX0lOVEVOU0lUWSxXMl9QQ19SQU5HRV9JTlRFTlNJVFksVzJfQ0VMTF9SQU5HRV9JTlRFTlNJVFksVzJfUENfQ1ZfSU5URU5TSVRZLFcyX0NFTExfQ1ZfSU5URU5TSVRZLFcyX1BDX01FQU5fU0laRSxXMl9DRUxMX01FQU5fU0laRSxXMl9QQ19SQU5HRV9TSVpFLFcyX0NFTExfUkFOR0VfU0laRSxXMl9QQ19DVl9TSVpFLFcyX0NFTExfQ1ZfU0laRSxTVEFUVVMsTEFORV9UT1RBTF9BUkVBX1VNMixMQU5FX05PSVNFX0FSRUFfVU0yLExBTkVfTk9JU0VfUkFUSU9fUEVSQ0VOVCxMQU5FX05PSVNFX09CSkVDVF9DT1VOVCxXMV9OT0lTRV9BUkVBX1VNMixXMl9OT0lTRV9BUkVBX1VNMixXMV9OT0lTRV9SQVRJT19QRVJDRU5ULFcyX05PSVNFX1JBVElPX1BFUkNFTlQsVzFfTk9JU0VfT0JKRUNUX0NPVU5ULFcyX05PSVNFX09CSkVDVF9DT1VOVCxMQU5FX1NNQUxMX05PSVNFX09CSkVDVF9DT1VOVCxMQU5FX01FRElVTV9OT0lTRV9PQkpFQ1RfQ09VTlQsTEFORV9MQVJHRV9OT0lTRV9PQkpFQ1RfQ09VTlQsTEFORV9FWFRSQV9MQVJHRV9OT0lTRV9PQkpFQ1RfQ09VTlQsRVJST1JfQ09ERQ0KMSwyMDIwLTAxLTIwLDIwOjM0OjI0LjU1NjAxNCw3LDU5LDExNy4yNzgzNjczNDY5Mzg3OCwyMi40MTk5Mjg4MjU2MjI3NzYsMjMzLDEyOCwwLjYwMDUzMjk4NTc4OTI5MTMsMS4wNTczODE3MTE4OTc4NzY4LDE1My4xNDI4NTcxNDI4NTcxNCw2Ljg5Mjg1NzE0Mjg1NzE0MywxNzguMDcsMTIuNzk0OTk5OTk5OTk5OTk4LDAuNDUzNDc2MjM3NjMwMzEzMTQsMC42NjI2NjU3Mzk3MDE0MTg4LDIxLDI1LDgyLjU1NDY1NTg3MDQ0NTM0LDEwLjMxOTg2NTMxOTg2NTMyLDI1MSw1MCwwLjk0ODIzNTAwNzExMDYwMSwwLjgwMDQxODA5OTgwNDYzNDcsNjguNjkwNDc2MTkwNDc2MTksOC42NDcwNTg4MjM1Mjk0MTEsMTI0LjIsMTMuMjM5OTk5OTk5OTk5OTk4LDAuODM0MTA4MTAzNDA1MTA3NCwwLjQ1ODIxMjYyOTA4OTc2NjMsNTQwNjI2MTEzLjAsMTIxOTE0My41LDAuMjMsNDUxLDUwMzMuNSwxODk4Ny4wLDAuMDA2MjU5MDU0MDg1MTQxMDQyLDAuMDIzNjA5OTQ1MzQ5MDc1NzgyLDIzLDU1LDQ1MCwxLDAsMCwNCjIsMjAyMC0wMS0yMCwyMDozNTozMC45NTg4NDMsMCwwLG5hbixuYW4sMCwwLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sMCwxLG5hbixuYW4sMCwwLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sNTM5NzI2NjA3LjAsNDU4NDcuMCwwLjAxLDE5LDAuMCwwLjAsMC4wLDAuMCwwLDAsMTksMCwwLDAsDQozLDIwMjAtMDEtMjAsMjA6Mzc6MjEuMjQ4ODg5LDAsMCxuYW4sbmFuLDAsMCxuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLDAsMzMsbmFuLDExNS45OTIzNjY0MTIyMTM3NCwwLDEzMCxuYW4sMC4yOTg1MjI3MzE4MDI4NTE1NSxuYW4sOC43LG5hbiwxMS4zMTk5OTk5OTk5OTk5OTksbmFuLDAuNTAzMTI4MDE0NDQyMjgyNiw1Mzg2NzMzOTguMCwyMDc3NzQuMCwwLjA0LDI2LDAuMCwwLjAsMC4wLDAuMCwwLDAsMjUsMSwwLDAsDQo=", 
							"[{'LANE':'1','PATHOGEN':'Listeria-Probes','SAMPLEID': 'Test"+lstSampleID.get(0)+"','SCANDATETIME':'"+date100+"','OUTCOME':'Positive','STATUSCODE':'3','CALIBRATED_PIPER_COUNTS':'5550','COUNT_OUTCOME':'PASS','CARTRIDGEID':'TestAutomation','EXPERIMENTID':'','INSTRUMENTID':'PSN0018','USERID':'98','RUN_ID':'Test"+lstRunID.get(0)+"','RUN_TYPE':'SCRIPT_1005a Listeria Probes','LANE_NO':'1','DATE':'2020-03-13','TIME':'','W1_PC_COUNT':'0','W1_CELL_COUNT':'5000','W1_PC_MEAN_INTENSITY':'','W1_CELL_MEAN_INTENSITY':'','W1_PC_RANGE_INTENSITY':'0','W1_CELL_RANGE_INTENSITY':'216','W1_PC_CV_INTENSITY':'','W1_CELL_CV_INTENSITY':'0.9665891343102634','W1_PC_MEAN_SIZE':'','W1_CELL_MEAN_SIZE':'6.491416309012876','W1_PC_RANGE_SIZE':'','W1_CELL_RANGE_SIZE':'11.0','W1_PC_CV_SIZE':'','W1_CELL_CV_SIZE':'1.039571210329305','W2_PC_COUNT':'1','W2_CELL_COUNT':'45','W2_PC_MEAN_INTENSITY':'77.69852941176471','W2_CELL_MEAN_INTENSITY':'9.731929549219636','W2_PC_RANGE_INTENSITY':'191','W2_CELL_RANGE_INTENSITY':'218','W2_PC_CV_INTENSITY':'0.5874496495238567','W2_CELL_CV_INTENSITY':'0.5872565165253875','W2_PC_MEAN_SIZE':'116.5','W2_CELL_MEAN_SIZE':'13.179852084267145','W2_PC_RANGE_SIZE':'0.0','W2_CELL_RANGE_SIZE':'37.0','W2_PC_CV_SIZE':'0.0','W2_CELL_CV_SIZE':'1.1630941924867089','STATUS':'','LANE_TOTAL_AREA_UM2':'536636840.0','LANE_NOISE_AREA_UM2':'5123.5','LANE_NOISE_RATIO_PERCENT':'0.0','LANE_NOISE_OBJECT_COUNT':'7','W1_NOISE_AREA_UM2':'1468.0','W2_NOISE_AREA_UM2':'8921.5','W1_NOISE_RATIO_PERCENT':'0.001825427912384434','W2_NOISE_RATIO_PERCENT':'0.011093702398050225','W1_NOISE_OBJECT_COUNT':'5','W2_NOISE_OBJECT_COUNT':'44','LANE_SMALL_NOISE_OBJECT_COUNT':'7','LANE_MEDIUM_NOISE_OBJECT_COUNT':'0','LANE_LARGE_NOISE_OBJECT_COUNT':'0','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'0','IMPROC':'ImprocSalm01','VERSION':'2.4','ERROR_CODE':'', 'MPN_CALCULATION_TYPE':'1', 'DILUTION_FACTOR':'0.1'}]", 
							"ImprocSalm01", true, "AN-API_FileUpload-01: Run File Upload API with unique Run ID", "This test case will run ingestion with unique run id", "Run API with unique RUN ID and unique Sample ID", "File Upload API ran successfully; File was ingested", "File Upload API failed to run", "AN-ESL-01: Verify that data is ingested after running API with Unique Run ID", "This test case will verify that data is ingested after running file upload API")
					));
	
	public static ArrayList<ExternalSalmonellaModel> lstExternalSalmonellaSearch = new ArrayList<>();
	
	public static String eslPNGFileName = "Salmonella Run Timeline - ";
	public static String eslCSVFileName = "Salmonella Log - ";
	public static String eslSampleMetaData = "Sample Metadata Upload Template";
	
	public static ArrayList<ExternalSalmonellaModel> lstExternalSalmonellaDateSearch = new ArrayList<>();
	
	public static ArrayList<ExternalSalmonellaModel> lstExternalSalmonellaDateEnter = new ArrayList<>();
	
	public static ArrayList<ExternalSalmonellaModel> lstExternalSalmonellaRowCount = new ArrayList<>();
	
	public static ArrayList<ExternalSalmonellaModel> lstExternalSalmonellaPagination = new ArrayList<>();
	
	////////////////////////////////////////////////End External Salmonella Ingest Variables////////////////////////////////////////////////////////////
	
	
	
	////////////////////////////////////////////////////Coccidia Ingest Variables//////////////////////////////////////////////////////////////////////
	
		
	public static ArrayList<APIModel> lstCoccidiaIngest = new ArrayList<>(
			Arrays.asList(
					new APIModel(AnnouncementRunID, "fab8cc1ea3f6db40c4555c3dfab09a34d2fa2b53cd66af72284af1dacb266910", 
							"20200120202842_PSN0009_sammyprocess_oaeng_improc.csv", "csv",                                                                                                        //03132020_PSN16_21612_lis1
							"TEFORV9OTyxEQVRFLFRJTUUsVzFfUENfQ09VTlQsVzFfQ0VMTF9DT1VOVCxXMV9QQ19NRUFOX0lOVEVOU0lUWSxXMV9DRUxMX01FQU5fSU5URU5TSVRZLFcxX1BDX1JBTkdFX0lOVEVOU0lUWSxXMV9DRUxMX1JBTkdFX0lOVEVOU0lUWSxXMV9QQ19DVl9JTlRFTlNJVFksVzFfQ0VMTF9DVl9JTlRFTlNJVFksVzFfUENfTUVBTl9TSVpFLFcxX0NFTExfTUVBTl9TSVpFLFcxX1BDX1JBTkdFX1NJWkUsVzFfQ0VMTF9SQU5HRV9TSVpFLFcxX1BDX0NWX1NJWkUsVzFfQ0VMTF9DVl9TSVpFLFcyX1BDX0NPVU5ULFcyX0NFTExfQ09VTlQsVzJfUENfTUVBTl9JTlRFTlNJVFksVzJfQ0VMTF9NRUFOX0lOVEVOU0lUWSxXMl9QQ19SQU5HRV9JTlRFTlNJVFksVzJfQ0VMTF9SQU5HRV9JTlRFTlNJVFksVzJfUENfQ1ZfSU5URU5TSVRZLFcyX0NFTExfQ1ZfSU5URU5TSVRZLFcyX1BDX01FQU5fU0laRSxXMl9DRUxMX01FQU5fU0laRSxXMl9QQ19SQU5HRV9TSVpFLFcyX0NFTExfUkFOR0VfU0laRSxXMl9QQ19DVl9TSVpFLFcyX0NFTExfQ1ZfU0laRSxTVEFUVVMsTEFORV9UT1RBTF9BUkVBX1VNMixMQU5FX05PSVNFX0FSRUFfVU0yLExBTkVfTk9JU0VfUkFUSU9fUEVSQ0VOVCxMQU5FX05PSVNFX09CSkVDVF9DT1VOVCxXMV9OT0lTRV9BUkVBX1VNMixXMl9OT0lTRV9BUkVBX1VNMixXMV9OT0lTRV9SQVRJT19QRVJDRU5ULFcyX05PSVNFX1JBVElPX1BFUkNFTlQsVzFfTk9JU0VfT0JKRUNUX0NPVU5ULFcyX05PSVNFX09CSkVDVF9DT1VOVCxMQU5FX1NNQUxMX05PSVNFX09CSkVDVF9DT1VOVCxMQU5FX01FRElVTV9OT0lTRV9PQkpFQ1RfQ09VTlQsTEFORV9MQVJHRV9OT0lTRV9PQkpFQ1RfQ09VTlQsTEFORV9FWFRSQV9MQVJHRV9OT0lTRV9PQkpFQ1RfQ09VTlQsRVJST1JfQ09ERQ0KMSwyMDIwLTAxLTIwLDIwOjM0OjI0LjU1NjAxNCw3LDU5LDExNy4yNzgzNjczNDY5Mzg3OCwyMi40MTk5Mjg4MjU2MjI3NzYsMjMzLDEyOCwwLjYwMDUzMjk4NTc4OTI5MTMsMS4wNTczODE3MTE4OTc4NzY4LDE1My4xNDI4NTcxNDI4NTcxNCw2Ljg5Mjg1NzE0Mjg1NzE0MywxNzguMDcsMTIuNzk0OTk5OTk5OTk5OTk4LDAuNDUzNDc2MjM3NjMwMzEzMTQsMC42NjI2NjU3Mzk3MDE0MTg4LDIxLDI1LDgyLjU1NDY1NTg3MDQ0NTM0LDEwLjMxOTg2NTMxOTg2NTMyLDI1MSw1MCwwLjk0ODIzNTAwNzExMDYwMSwwLjgwMDQxODA5OTgwNDYzNDcsNjguNjkwNDc2MTkwNDc2MTksOC42NDcwNTg4MjM1Mjk0MTEsMTI0LjIsMTMuMjM5OTk5OTk5OTk5OTk4LDAuODM0MTA4MTAzNDA1MTA3NCwwLjQ1ODIxMjYyOTA4OTc2NjMsNTQwNjI2MTEzLjAsMTIxOTE0My41LDAuMjMsNDUxLDUwMzMuNSwxODk4Ny4wLDAuMDA2MjU5MDU0MDg1MTQxMDQyLDAuMDIzNjA5OTQ1MzQ5MDc1NzgyLDIzLDU1LDQ1MCwxLDAsMCwNCjIsMjAyMC0wMS0yMCwyMDozNTozMC45NTg4NDMsMCwwLG5hbixuYW4sMCwwLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sMCwxLG5hbixuYW4sMCwwLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sNTM5NzI2NjA3LjAsNDU4NDcuMCwwLjAxLDE5LDAuMCwwLjAsMC4wLDAuMCwwLDAsMTksMCwwLDAsDQozLDIwMjAtMDEtMjAsMjA6Mzc6MjEuMjQ4ODg5LDAsMCxuYW4sbmFuLDAsMCxuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLDAsMzMsbmFuLDExNS45OTIzNjY0MTIyMTM3NCwwLDEzMCxuYW4sMC4yOTg1MjI3MzE4MDI4NTE1NSxuYW4sOC43LG5hbiwxMS4zMTk5OTk5OTk5OTk5OTksbmFuLDAuNTAzMTI4MDE0NDQyMjgyNiw1Mzg2NzMzOTguMCwyMDc3NzQuMCwwLjA0LDI2LDAuMCwwLjAsMC4wLDAuMCwwLDAsMjUsMSwwLDAsDQo=", 
							"[{'LANE':'1','PATHOGEN':'Coccidia-SYBR','SAMPLEID':'Test"+lstSampleID.get(0)+"','SCANDATETIME':'"+date100+"','FIGUREOFMERIT':'0','OUTCOME':'QCFail','STATUSCODE':'1024','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'E066','CARTRIDGEID':'TestAutomation','EXPERIMENTID':'','INSTRUMENTID':'PSN0004','USERID':'98','RUN_ID':'Test"+lstRunID.get(0)+"','RUN_TYPE':'SCRIPT_swtest','LANE_NO':'1','DATE':'','TIME':'','LANE_TOTAL_OOCYST_COUNT':'14','LANE_TOTAL_OOCYST_MEAN_INTENSITY':'','LANE_TOTAL_OOCYST_RANGE_INTENSITY':'','LANE_TOTAL_OOCYST_CV_INTENSITY':'','LANE_TOTAL_OOCYST_MEAN_AREA':'','LANE_TOTAL_OOCYST_RANGE_AREA':'','LANE_TOTAL_OOCYST_CV_AREA':'','LANE_TOTAL_OOCYST_MEAN_MAJORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MAJORAXIS':'','LANE_TOTAL_OOCYST_CV_MAJORAXIS':'','LANE_TOTAL_OOCYST_MEAN_MINORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MINORAXIS':'','LANE_TOTAL_OOCYST_CV_MINORAXIS':'','LANE_TOTAL_OOCYST_COUNT_SPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNSPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_SMALL_OOCYST_COUNT':'','LANE_SMALL_OOCYST_MEAN_INTENSITY':'','LANE_SMALL_OOCYST_RANGE_INTENSITY':'','LANE_SMALL_OOCYST_CV_INTENSITY':'','LANE_SMALL_OOCYST_MEAN_AREA':'','LANE_SMALL_OOCYST_RANGE_AREA':'','LANE_SMALL_OOCYST_CV_AREA':'','LANE_SMALL_OOCYST_MEAN_MAJORAXIS':'','LANE_SMALL_OOCYST_RANGE_MAJORAXIS':'','LANE_SMALL_OOCYST_CV_MAJORAXIS':'','LANE_SMALL_OOCYST_MEAN_MINORAXIS':'','LANE_SMALL_OOCYST_RANGE_MINORAXIS':'','LANE_SMALL_OOCYST_CV_MINORAXIS':'','LANE_SMALL_OOCYST_COUNT_SPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNSPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_MEDIUM_OOCYST_COUNT':'','LANE_MEDIUM_OOCYST_MEAN_INTENSITY':'','LANE_MEDIUM_OOCYST_RANGE_INTENSITY':'','LANE_MEDIUM_OOCYST_CV_INTENSITY':'','LANE_MEDIUM_OOCYST_MEAN_AREA':'','LANE_MEDIUM_OOCYST_RANGE_AREA':'','LANE_MEDIUM_OOCYST_CV_AREA':'','LANE_MEDIUM_OOCYST_MEAN_MAJORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MAJORAXIS':'','LANE_MEDIUM_OOCYST_CV_MAJORAXIS':'','LANE_MEDIUM_OOCYST_MEAN_MINORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MINORAXIS':'','LANE_MEDIUM_OOCYST_CV_MINORAXIS':'','LANE_MEDIUM_OOCYST_COUNT_SPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNSPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_LARGE_OOCYST_COUNT':'','LANE_LARGE_OOCYST_MEAN_INTENSITY':'','LANE_LARGE_OOCYST_RANGE_INTENSITY':'','LANE_LARGE_OOCYST_CV_INTENSITY':'','LANE_LARGE_OOCYST_MEAN_AREA':'','LANE_LARGE_OOCYST_RANGE_AREA':'','LANE_LARGE_OOCYST_CV_AREA':'','LANE_LARGE_OOCYST_MEAN_MAJORAXIS':'','LANE_LARGE_OOCYST_RANGE_MAJORAXIS':'','LANE_LARGE_OOCYST_CV_MAJORAXIS':'','LANE_LARGE_OOCYST_MEAN_MINORAXIS':'','LANE_LARGE_OOCYST_RANGE_MINORAXIS':'','LANE_LARGE_OOCYST_CV_MINORAXIS':'','LANE_LARGE_OOCYST_COUNT_SPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNSPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNCERTAINSPORULATION':'','STATUS':'','LANE_TOTAL_AREA_UM2':'','LANE_NOISE_AREA_UM2':'','LANE_NOISE_RATIO_PERCENT':'','LANE_NOISE_OBJECT_COUNT':'','LANE_SMALL_NOISE_OBJECT_COUNT':'','LANE_MEDIUM_NOISE_OBJECT_COUNT':'','LANE_LARGE_NOISE_OBJECT_COUNT':'','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'','IMPROC':'','VERSION':'','ERROR_CODE':'Exception'}]",
							"ImprocCocc01", true, "AN-API_FileUpload-01: Run File Upload API with unique Run ID", "This test case will run ingestion with unique run id", "Run API with unique RUN ID and unique Sample ID", "File Upload API ran successfully; File was ingested", "File Upload API failed to run", "AN-CL-01: Verify that data is ingested after running API with Unique Run ID", "This test case will verify that data is ingested after running file upload API")
					
//					new SalmonellaIngestModel(AnnouncementRunID, "fab8cc1ea3f6db40c4555c3dfab09a34d2fa2b53cd66af72284af1dacb266910", 
//							"20200120202842_PSN0009_sammyprocess_oaeng_improc.csv", "csv", 
//							"TEFORV9OTyxEQVRFLFRJTUUsVzFfUENfQ09VTlQsVzFfQ0VMTF9DT1VOVCxXMV9QQ19NRUFOX0lOVEVOU0lUWSxXMV9DRUxMX01FQU5fSU5URU5TSVRZLFcxX1BDX1JBTkdFX0lOVEVOU0lUWSxXMV9DRUxMX1JBTkdFX0lOVEVOU0lUWSxXMV9QQ19DVl9JTlRFTlNJVFksVzFfQ0VMTF9DVl9JTlRFTlNJVFksVzFfUENfTUVBTl9TSVpFLFcxX0NFTExfTUVBTl9TSVpFLFcxX1BDX1JBTkdFX1NJWkUsVzFfQ0VMTF9SQU5HRV9TSVpFLFcxX1BDX0NWX1NJWkUsVzFfQ0VMTF9DVl9TSVpFLFcyX1BDX0NPVU5ULFcyX0NFTExfQ09VTlQsVzJfUENfTUVBTl9JTlRFTlNJVFksVzJfQ0VMTF9NRUFOX0lOVEVOU0lUWSxXMl9QQ19SQU5HRV9JTlRFTlNJVFksVzJfQ0VMTF9SQU5HRV9JTlRFTlNJVFksVzJfUENfQ1ZfSU5URU5TSVRZLFcyX0NFTExfQ1ZfSU5URU5TSVRZLFcyX1BDX01FQU5fU0laRSxXMl9DRUxMX01FQU5fU0laRSxXMl9QQ19SQU5HRV9TSVpFLFcyX0NFTExfUkFOR0VfU0laRSxXMl9QQ19DVl9TSVpFLFcyX0NFTExfQ1ZfU0laRSxTVEFUVVMsTEFORV9UT1RBTF9BUkVBX1VNMixMQU5FX05PSVNFX0FSRUFfVU0yLExBTkVfTk9JU0VfUkFUSU9fUEVSQ0VOVCxMQU5FX05PSVNFX09CSkVDVF9DT1VOVCxXMV9OT0lTRV9BUkVBX1VNMixXMl9OT0lTRV9BUkVBX1VNMixXMV9OT0lTRV9SQVRJT19QRVJDRU5ULFcyX05PSVNFX1JBVElPX1BFUkNFTlQsVzFfTk9JU0VfT0JKRUNUX0NPVU5ULFcyX05PSVNFX09CSkVDVF9DT1VOVCxMQU5FX1NNQUxMX05PSVNFX09CSkVDVF9DT1VOVCxMQU5FX01FRElVTV9OT0lTRV9PQkpFQ1RfQ09VTlQsTEFORV9MQVJHRV9OT0lTRV9PQkpFQ1RfQ09VTlQsTEFORV9FWFRSQV9MQVJHRV9OT0lTRV9PQkpFQ1RfQ09VTlQsRVJST1JfQ09ERQ0KMSwyMDIwLTAxLTIwLDIwOjM0OjI0LjU1NjAxNCw3LDU5LDExNy4yNzgzNjczNDY5Mzg3OCwyMi40MTk5Mjg4MjU2MjI3NzYsMjMzLDEyOCwwLjYwMDUzMjk4NTc4OTI5MTMsMS4wNTczODE3MTE4OTc4NzY4LDE1My4xNDI4NTcxNDI4NTcxNCw2Ljg5Mjg1NzE0Mjg1NzE0MywxNzguMDcsMTIuNzk0OTk5OTk5OTk5OTk4LDAuNDUzNDc2MjM3NjMwMzEzMTQsMC42NjI2NjU3Mzk3MDE0MTg4LDIxLDI1LDgyLjU1NDY1NTg3MDQ0NTM0LDEwLjMxOTg2NTMxOTg2NTMyLDI1MSw1MCwwLjk0ODIzNTAwNzExMDYwMSwwLjgwMDQxODA5OTgwNDYzNDcsNjguNjkwNDc2MTkwNDc2MTksOC42NDcwNTg4MjM1Mjk0MTEsMTI0LjIsMTMuMjM5OTk5OTk5OTk5OTk4LDAuODM0MTA4MTAzNDA1MTA3NCwwLjQ1ODIxMjYyOTA4OTc2NjMsNTQwNjI2MTEzLjAsMTIxOTE0My41LDAuMjMsNDUxLDUwMzMuNSwxODk4Ny4wLDAuMDA2MjU5MDU0MDg1MTQxMDQyLDAuMDIzNjA5OTQ1MzQ5MDc1NzgyLDIzLDU1LDQ1MCwxLDAsMCwNCjIsMjAyMC0wMS0yMCwyMDozNTozMC45NTg4NDMsMCwwLG5hbixuYW4sMCwwLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sMCwxLG5hbixuYW4sMCwwLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sNTM5NzI2NjA3LjAsNDU4NDcuMCwwLjAxLDE5LDAuMCwwLjAsMC4wLDAuMCwwLDAsMTksMCwwLDAsDQozLDIwMjAtMDEtMjAsMjA6Mzc6MjEuMjQ4ODg5LDAsMCxuYW4sbmFuLDAsMCxuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLDAsMzMsbmFuLDExNS45OTIzNjY0MTIyMTM3NCwwLDEzMCxuYW4sMC4yOTg1MjI3MzE4MDI4NTE1NSxuYW4sOC43LG5hbiwxMS4zMTk5OTk5OTk5OTk5OTksbmFuLDAuNTAzMTI4MDE0NDQyMjgyNiw1Mzg2NzMzOTguMCwyMDc3NzQuMCwwLjA0LDI2LDAuMCwwLjAsMC4wLDAuMCwwLDAsMjUsMSwwLDAsDQo=", 
//							"[{'LANE':'1','PATHOGEN':'Listeria-Probes','SAMPLEID': 'Test"+lstSalmonellaSampleID.get(1)+"','SCANDATETIME':'2020/10/26 08:30:17','OUTCOME':'Positive','STATUSCODE':'3','CALIBRATED_PIPER_COUNTS':'5550','COUNT_OUTCOME':'PASS','CARTRIDGEID':'TestAutomation','EXPERIMENTID':'','INSTRUMENTID':'PSN0018','USERID':'98','RUN_ID':'Test"+lstSalmonellaRunID.get(1)+"','RUN_TYPE':'SCRIPT_1005a Listeria Probes','LANE_NO':'1','DATE':'2020-03-13','TIME':'','W1_PC_COUNT':'0','W1_CELL_COUNT':'5000','W1_PC_MEAN_INTENSITY':'','W1_CELL_MEAN_INTENSITY':'','W1_PC_RANGE_INTENSITY':'0','W1_CELL_RANGE_INTENSITY':'216','W1_PC_CV_INTENSITY':'','W1_CELL_CV_INTENSITY':'0.9665891343102634','W1_PC_MEAN_SIZE':'','W1_CELL_MEAN_SIZE':'6.491416309012876','W1_PC_RANGE_SIZE':'','W1_CELL_RANGE_SIZE':'11.0','W1_PC_CV_SIZE':'','W1_CELL_CV_SIZE':'1.039571210329305','W2_PC_COUNT':'1','W2_CELL_COUNT':'45','W2_PC_MEAN_INTENSITY':'77.69852941176471','W2_CELL_MEAN_INTENSITY':'9.731929549219636','W2_PC_RANGE_INTENSITY':'191','W2_CELL_RANGE_INTENSITY':'218','W2_PC_CV_INTENSITY':'0.5874496495238567','W2_CELL_CV_INTENSITY':'0.5872565165253875','W2_PC_MEAN_SIZE':'116.5','W2_CELL_MEAN_SIZE':'13.179852084267145','W2_PC_RANGE_SIZE':'0.0','W2_CELL_RANGE_SIZE':'37.0','W2_PC_CV_SIZE':'0.0','W2_CELL_CV_SIZE':'1.1630941924867089','STATUS':'','LANE_TOTAL_AREA_UM2':'536636840.0','LANE_NOISE_AREA_UM2':'5123.5','LANE_NOISE_RATIO_PERCENT':'0.0','LANE_NOISE_OBJECT_COUNT':'7','W1_NOISE_AREA_UM2':'1468.0','W2_NOISE_AREA_UM2':'8921.5','W1_NOISE_RATIO_PERCENT':'0.001825427912384434','W2_NOISE_RATIO_PERCENT':'0.011093702398050225','W1_NOISE_OBJECT_COUNT':'5','W2_NOISE_OBJECT_COUNT':'44','LANE_SMALL_NOISE_OBJECT_COUNT':'7','LANE_MEDIUM_NOISE_OBJECT_COUNT':'0','LANE_LARGE_NOISE_OBJECT_COUNT':'0','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'0','IMPROC':'ImprocSalm01','VERSION':'2.4','ERROR_CODE':'', 'MPN_CALCULATION_TYPE':'1', 'DILUTION_FACTOR':'0.1'}]", 
//							"ImprocSalm01", true, "AN-SL-04: Run File Upload Ingest API with duplicate Run ID and unique Sample ID", "This test case will run ingestion with same run id and unique sample id", "Run API with duplicate Run ID and unique Sample ID", "File Upload API ran successfully; File was ingested", "File Upload API failed to run", "AN-SL-05: Verify that data is not ingested after running API with same Run ID", "This test case will verify that data is not ingested after running file upload API with duplicate RUN ID"),
					
//					new SalmonellaIngestModel(AnnouncementRunID, "fab8cc1ea3f6db40c4555c3dfab09a34d2fa2b53cd66af72284af1dacb266910", 
//							"20200120202842_PSN0009_sammyprocess_oaeng_improc.csv", "csv", 
//							"TEFORV9OTyxEQVRFLFRJTUUsVzFfUENfQ09VTlQsVzFfQ0VMTF9DT1VOVCxXMV9QQ19NRUFOX0lOVEVOU0lUWSxXMV9DRUxMX01FQU5fSU5URU5TSVRZLFcxX1BDX1JBTkdFX0lOVEVOU0lUWSxXMV9DRUxMX1JBTkdFX0lOVEVOU0lUWSxXMV9QQ19DVl9JTlRFTlNJVFksVzFfQ0VMTF9DVl9JTlRFTlNJVFksVzFfUENfTUVBTl9TSVpFLFcxX0NFTExfTUVBTl9TSVpFLFcxX1BDX1JBTkdFX1NJWkUsVzFfQ0VMTF9SQU5HRV9TSVpFLFcxX1BDX0NWX1NJWkUsVzFfQ0VMTF9DVl9TSVpFLFcyX1BDX0NPVU5ULFcyX0NFTExfQ09VTlQsVzJfUENfTUVBTl9JTlRFTlNJVFksVzJfQ0VMTF9NRUFOX0lOVEVOU0lUWSxXMl9QQ19SQU5HRV9JTlRFTlNJVFksVzJfQ0VMTF9SQU5HRV9JTlRFTlNJVFksVzJfUENfQ1ZfSU5URU5TSVRZLFcyX0NFTExfQ1ZfSU5URU5TSVRZLFcyX1BDX01FQU5fU0laRSxXMl9DRUxMX01FQU5fU0laRSxXMl9QQ19SQU5HRV9TSVpFLFcyX0NFTExfUkFOR0VfU0laRSxXMl9QQ19DVl9TSVpFLFcyX0NFTExfQ1ZfU0laRSxTVEFUVVMsTEFORV9UT1RBTF9BUkVBX1VNMixMQU5FX05PSVNFX0FSRUFfVU0yLExBTkVfTk9JU0VfUkFUSU9fUEVSQ0VOVCxMQU5FX05PSVNFX09CSkVDVF9DT1VOVCxXMV9OT0lTRV9BUkVBX1VNMixXMl9OT0lTRV9BUkVBX1VNMixXMV9OT0lTRV9SQVRJT19QRVJDRU5ULFcyX05PSVNFX1JBVElPX1BFUkNFTlQsVzFfTk9JU0VfT0JKRUNUX0NPVU5ULFcyX05PSVNFX09CSkVDVF9DT1VOVCxMQU5FX1NNQUxMX05PSVNFX09CSkVDVF9DT1VOVCxMQU5FX01FRElVTV9OT0lTRV9PQkpFQ1RfQ09VTlQsTEFORV9MQVJHRV9OT0lTRV9PQkpFQ1RfQ09VTlQsTEFORV9FWFRSQV9MQVJHRV9OT0lTRV9PQkpFQ1RfQ09VTlQsRVJST1JfQ09ERQ0KMSwyMDIwLTAxLTIwLDIwOjM0OjI0LjU1NjAxNCw3LDU5LDExNy4yNzgzNjczNDY5Mzg3OCwyMi40MTk5Mjg4MjU2MjI3NzYsMjMzLDEyOCwwLjYwMDUzMjk4NTc4OTI5MTMsMS4wNTczODE3MTE4OTc4NzY4LDE1My4xNDI4NTcxNDI4NTcxNCw2Ljg5Mjg1NzE0Mjg1NzE0MywxNzguMDcsMTIuNzk0OTk5OTk5OTk5OTk4LDAuNDUzNDc2MjM3NjMwMzEzMTQsMC42NjI2NjU3Mzk3MDE0MTg4LDIxLDI1LDgyLjU1NDY1NTg3MDQ0NTM0LDEwLjMxOTg2NTMxOTg2NTMyLDI1MSw1MCwwLjk0ODIzNTAwNzExMDYwMSwwLjgwMDQxODA5OTgwNDYzNDcsNjguNjkwNDc2MTkwNDc2MTksOC42NDcwNTg4MjM1Mjk0MTEsMTI0LjIsMTMuMjM5OTk5OTk5OTk5OTk4LDAuODM0MTA4MTAzNDA1MTA3NCwwLjQ1ODIxMjYyOTA4OTc2NjMsNTQwNjI2MTEzLjAsMTIxOTE0My41LDAuMjMsNDUxLDUwMzMuNSwxODk4Ny4wLDAuMDA2MjU5MDU0MDg1MTQxMDQyLDAuMDIzNjA5OTQ1MzQ5MDc1NzgyLDIzLDU1LDQ1MCwxLDAsMCwNCjIsMjAyMC0wMS0yMCwyMDozNTozMC45NTg4NDMsMCwwLG5hbixuYW4sMCwwLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sMCwxLG5hbixuYW4sMCwwLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sNTM5NzI2NjA3LjAsNDU4NDcuMCwwLjAxLDE5LDAuMCwwLjAsMC4wLDAuMCwwLDAsMTksMCwwLDAsDQozLDIwMjAtMDEtMjAsMjA6Mzc6MjEuMjQ4ODg5LDAsMCxuYW4sbmFuLDAsMCxuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLDAsMzMsbmFuLDExNS45OTIzNjY0MTIyMTM3NCwwLDEzMCxuYW4sMC4yOTg1MjI3MzE4MDI4NTE1NSxuYW4sOC43LG5hbiwxMS4zMTk5OTk5OTk5OTk5OTksbmFuLDAuNTAzMTI4MDE0NDQyMjgyNiw1Mzg2NzMzOTguMCwyMDc3NzQuMCwwLjA0LDI2LDAuMCwwLjAsMC4wLDAuMCwwLDAsMjUsMSwwLDAsDQo=", 
//							"[{'LANE':'1','PATHOGEN':'Listeria-Probes','SAMPLEID': 'Test"+lstSalmonellaSampleID.get(2)+"','SCANDATETIME':'"+date100+"','OUTCOME':'Positive','STATUSCODE':'3','CALIBRATED_PIPER_COUNTS':'5550','COUNT_OUTCOME':'PASS','CARTRIDGEID':'TestAutomation','EXPERIMENTID':'','INSTRUMENTID':'PSN0018','USERID':'98','RUN_ID':'Test"+lstSalmonellaRunID.get(2)+"','RUN_TYPE':'SCRIPT_1005a Listeria Probes','LANE_NO':'1','DATE':'2020-03-13','TIME':'','W1_PC_COUNT':'0','W1_CELL_COUNT':'5000','W1_PC_MEAN_INTENSITY':'','W1_CELL_MEAN_INTENSITY':'','W1_PC_RANGE_INTENSITY':'0','W1_CELL_RANGE_INTENSITY':'216','W1_PC_CV_INTENSITY':'','W1_CELL_CV_INTENSITY':'0.9665891343102634','W1_PC_MEAN_SIZE':'','W1_CELL_MEAN_SIZE':'6.491416309012876','W1_PC_RANGE_SIZE':'','W1_CELL_RANGE_SIZE':'11.0','W1_PC_CV_SIZE':'','W1_CELL_CV_SIZE':'1.039571210329305','W2_PC_COUNT':'1','W2_CELL_COUNT':'45','W2_PC_MEAN_INTENSITY':'77.69852941176471','W2_CELL_MEAN_INTENSITY':'9.731929549219636','W2_PC_RANGE_INTENSITY':'191','W2_CELL_RANGE_INTENSITY':'218','W2_PC_CV_INTENSITY':'0.5874496495238567','W2_CELL_CV_INTENSITY':'0.5872565165253875','W2_PC_MEAN_SIZE':'116.5','W2_CELL_MEAN_SIZE':'13.179852084267145','W2_PC_RANGE_SIZE':'0.0','W2_CELL_RANGE_SIZE':'37.0','W2_PC_CV_SIZE':'0.0','W2_CELL_CV_SIZE':'1.1630941924867089','STATUS':'','LANE_TOTAL_AREA_UM2':'536636840.0','LANE_NOISE_AREA_UM2':'5123.5','LANE_NOISE_RATIO_PERCENT':'0.0','LANE_NOISE_OBJECT_COUNT':'7','W1_NOISE_AREA_UM2':'1468.0','W2_NOISE_AREA_UM2':'8921.5','W1_NOISE_RATIO_PERCENT':'0.001825427912384434','W2_NOISE_RATIO_PERCENT':'0.011093702398050225','W1_NOISE_OBJECT_COUNT':'5','W2_NOISE_OBJECT_COUNT':'44','LANE_SMALL_NOISE_OBJECT_COUNT':'7','LANE_MEDIUM_NOISE_OBJECT_COUNT':'0','LANE_LARGE_NOISE_OBJECT_COUNT':'0','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'0','IMPROC':'ImprocSalm01','VERSION':'2.4','ERROR_CODE':'', 'MPN_CALCULATION_TYPE':'1', 'DILUTION_FACTOR':'0.1'}]", 
//							"ImprocSalm01", true, "AN-SL-06: Run File Upload API with current date/time", "This test case will run ingestion with unique run id and current date/time", "Run API with uniqure Run ID and current date/time", "File Upload API ran successfully; File was ingested on top of the table", "File Upload API failed to run", "AN-SL-07: Verify that data is ingested on top of the table", "This test case will verify that data is ingested on top of the table after running api with current date/time")

					));
	               
	
	public static ArrayList<CoccidiaModel> lstCoccidiaSearch = new ArrayList<>();
		
	public static String clPNGFileName = "Coccidia Run Timeline - ";
	public static String clCSVFileName = "Coccidia Log - ";
	public static String clSampleMetaData = "Sample Metadata Upload Template";
	
	public static ArrayList<CoccidiaModel> lstCoccidiaDateSearch = new ArrayList<>();
	
	public static ArrayList<CoccidiaModel> lstCoccidiaDateEnter = new ArrayList<>();
	
	public static ArrayList<CoccidiaModel> lstCoccidiaRowCount = new ArrayList<>();
	
	public static ArrayList<CoccidiaModel> lstCoccidiaPagination = new ArrayList<>();
	
	////////////////////////////////////////////////////////////////End Coccidia Variables/////////////////////////////////////////////////////////////////////////////////////
	
	
	
	///////////////////////////////////////////////////External Coccidia Ingest Variables///////////////////////////////////////////////////////////////////

	
	public static ArrayList<String> lstExternalCoccidiaRunID = new ArrayList<>(
			Arrays.asList("Automation"+date0,  
					"Automation"+date0,
					"Automation1015")); 
	
	public static ArrayList<String> lstExternalCoccidiaSampleID = new ArrayList<>(
			Arrays.asList("Automation"+date0,  
					"Automation116",
					"Automation118")); 
		
	public static ArrayList<APIModel> lstExternalCoccidiaIngest = new ArrayList<>(
			Arrays.asList(
					new APIModel(AnnouncementRunID, "fab8cc1ea3f6db40c4555c3dfab09a34d2fa2b53cd66af72284af1dacb266910", 
							"20200120202842_PSN0009_sammyprocess_oaeng_improc.csv", "csv",                                                                                                        //03132020_PSN16_21612_lis1
							"TEFORV9OTyxEQVRFLFRJTUUsVzFfUENfQ09VTlQsVzFfQ0VMTF9DT1VOVCxXMV9QQ19NRUFOX0lOVEVOU0lUWSxXMV9DRUxMX01FQU5fSU5URU5TSVRZLFcxX1BDX1JBTkdFX0lOVEVOU0lUWSxXMV9DRUxMX1JBTkdFX0lOVEVOU0lUWSxXMV9QQ19DVl9JTlRFTlNJVFksVzFfQ0VMTF9DVl9JTlRFTlNJVFksVzFfUENfTUVBTl9TSVpFLFcxX0NFTExfTUVBTl9TSVpFLFcxX1BDX1JBTkdFX1NJWkUsVzFfQ0VMTF9SQU5HRV9TSVpFLFcxX1BDX0NWX1NJWkUsVzFfQ0VMTF9DVl9TSVpFLFcyX1BDX0NPVU5ULFcyX0NFTExfQ09VTlQsVzJfUENfTUVBTl9JTlRFTlNJVFksVzJfQ0VMTF9NRUFOX0lOVEVOU0lUWSxXMl9QQ19SQU5HRV9JTlRFTlNJVFksVzJfQ0VMTF9SQU5HRV9JTlRFTlNJVFksVzJfUENfQ1ZfSU5URU5TSVRZLFcyX0NFTExfQ1ZfSU5URU5TSVRZLFcyX1BDX01FQU5fU0laRSxXMl9DRUxMX01FQU5fU0laRSxXMl9QQ19SQU5HRV9TSVpFLFcyX0NFTExfUkFOR0VfU0laRSxXMl9QQ19DVl9TSVpFLFcyX0NFTExfQ1ZfU0laRSxTVEFUVVMsTEFORV9UT1RBTF9BUkVBX1VNMixMQU5FX05PSVNFX0FSRUFfVU0yLExBTkVfTk9JU0VfUkFUSU9fUEVSQ0VOVCxMQU5FX05PSVNFX09CSkVDVF9DT1VOVCxXMV9OT0lTRV9BUkVBX1VNMixXMl9OT0lTRV9BUkVBX1VNMixXMV9OT0lTRV9SQVRJT19QRVJDRU5ULFcyX05PSVNFX1JBVElPX1BFUkNFTlQsVzFfTk9JU0VfT0JKRUNUX0NPVU5ULFcyX05PSVNFX09CSkVDVF9DT1VOVCxMQU5FX1NNQUxMX05PSVNFX09CSkVDVF9DT1VOVCxMQU5FX01FRElVTV9OT0lTRV9PQkpFQ1RfQ09VTlQsTEFORV9MQVJHRV9OT0lTRV9PQkpFQ1RfQ09VTlQsTEFORV9FWFRSQV9MQVJHRV9OT0lTRV9PQkpFQ1RfQ09VTlQsRVJST1JfQ09ERQ0KMSwyMDIwLTAxLTIwLDIwOjM0OjI0LjU1NjAxNCw3LDU5LDExNy4yNzgzNjczNDY5Mzg3OCwyMi40MTk5Mjg4MjU2MjI3NzYsMjMzLDEyOCwwLjYwMDUzMjk4NTc4OTI5MTMsMS4wNTczODE3MTE4OTc4NzY4LDE1My4xNDI4NTcxNDI4NTcxNCw2Ljg5Mjg1NzE0Mjg1NzE0MywxNzguMDcsMTIuNzk0OTk5OTk5OTk5OTk4LDAuNDUzNDc2MjM3NjMwMzEzMTQsMC42NjI2NjU3Mzk3MDE0MTg4LDIxLDI1LDgyLjU1NDY1NTg3MDQ0NTM0LDEwLjMxOTg2NTMxOTg2NTMyLDI1MSw1MCwwLjk0ODIzNTAwNzExMDYwMSwwLjgwMDQxODA5OTgwNDYzNDcsNjguNjkwNDc2MTkwNDc2MTksOC42NDcwNTg4MjM1Mjk0MTEsMTI0LjIsMTMuMjM5OTk5OTk5OTk5OTk4LDAuODM0MTA4MTAzNDA1MTA3NCwwLjQ1ODIxMjYyOTA4OTc2NjMsNTQwNjI2MTEzLjAsMTIxOTE0My41LDAuMjMsNDUxLDUwMzMuNSwxODk4Ny4wLDAuMDA2MjU5MDU0MDg1MTQxMDQyLDAuMDIzNjA5OTQ1MzQ5MDc1NzgyLDIzLDU1LDQ1MCwxLDAsMCwNCjIsMjAyMC0wMS0yMCwyMDozNTozMC45NTg4NDMsMCwwLG5hbixuYW4sMCwwLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sMCwxLG5hbixuYW4sMCwwLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sNTM5NzI2NjA3LjAsNDU4NDcuMCwwLjAxLDE5LDAuMCwwLjAsMC4wLDAuMCwwLDAsMTksMCwwLDAsDQozLDIwMjAtMDEtMjAsMjA6Mzc6MjEuMjQ4ODg5LDAsMCxuYW4sbmFuLDAsMCxuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLDAsMzMsbmFuLDExNS45OTIzNjY0MTIyMTM3NCwwLDEzMCxuYW4sMC4yOTg1MjI3MzE4MDI4NTE1NSxuYW4sOC43LG5hbiwxMS4zMTk5OTk5OTk5OTk5OTksbmFuLDAuNTAzMTI4MDE0NDQyMjgyNiw1Mzg2NzMzOTguMCwyMDc3NzQuMCwwLjA0LDI2LDAuMCwwLjAsMC4wLDAuMCwwLDAsMjUsMSwwLDAsDQo=", 
							"[{'LANE':'1','PATHOGEN':'Coccidia-SYBR','SAMPLEID':'Test"+lstExternalCoccidiaSampleID.get(0)+"','SCANDATETIME':'"+date100+"','FIGUREOFMERIT':'0','OUTCOME':'QCFail','STATUSCODE':'1024','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'E066','CARTRIDGEID':'TestAutomation','EXPERIMENTID':'','INSTRUMENTID':'PSN0004','USERID':'98','RUN_ID':'Test"+lstExternalCoccidiaRunID.get(0)+"','RUN_TYPE':'SCRIPT_swtest','LANE_NO':'1','DATE':'','TIME':'','LANE_TOTAL_OOCYST_COUNT':'14','LANE_TOTAL_OOCYST_MEAN_INTENSITY':'','LANE_TOTAL_OOCYST_RANGE_INTENSITY':'','LANE_TOTAL_OOCYST_CV_INTENSITY':'','LANE_TOTAL_OOCYST_MEAN_AREA':'','LANE_TOTAL_OOCYST_RANGE_AREA':'','LANE_TOTAL_OOCYST_CV_AREA':'','LANE_TOTAL_OOCYST_MEAN_MAJORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MAJORAXIS':'','LANE_TOTAL_OOCYST_CV_MAJORAXIS':'','LANE_TOTAL_OOCYST_MEAN_MINORAXIS':'','LANE_TOTAL_OOCYST_RANGE_MINORAXIS':'','LANE_TOTAL_OOCYST_CV_MINORAXIS':'','LANE_TOTAL_OOCYST_COUNT_SPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNSPORULATED':'','LANE_TOTAL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_SMALL_OOCYST_COUNT':'','LANE_SMALL_OOCYST_MEAN_INTENSITY':'','LANE_SMALL_OOCYST_RANGE_INTENSITY':'','LANE_SMALL_OOCYST_CV_INTENSITY':'','LANE_SMALL_OOCYST_MEAN_AREA':'','LANE_SMALL_OOCYST_RANGE_AREA':'','LANE_SMALL_OOCYST_CV_AREA':'','LANE_SMALL_OOCYST_MEAN_MAJORAXIS':'','LANE_SMALL_OOCYST_RANGE_MAJORAXIS':'','LANE_SMALL_OOCYST_CV_MAJORAXIS':'','LANE_SMALL_OOCYST_MEAN_MINORAXIS':'','LANE_SMALL_OOCYST_RANGE_MINORAXIS':'','LANE_SMALL_OOCYST_CV_MINORAXIS':'','LANE_SMALL_OOCYST_COUNT_SPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNSPORULATED':'','LANE_SMALL_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_MEDIUM_OOCYST_COUNT':'','LANE_MEDIUM_OOCYST_MEAN_INTENSITY':'','LANE_MEDIUM_OOCYST_RANGE_INTENSITY':'','LANE_MEDIUM_OOCYST_CV_INTENSITY':'','LANE_MEDIUM_OOCYST_MEAN_AREA':'','LANE_MEDIUM_OOCYST_RANGE_AREA':'','LANE_MEDIUM_OOCYST_CV_AREA':'','LANE_MEDIUM_OOCYST_MEAN_MAJORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MAJORAXIS':'','LANE_MEDIUM_OOCYST_CV_MAJORAXIS':'','LANE_MEDIUM_OOCYST_MEAN_MINORAXIS':'','LANE_MEDIUM_OOCYST_RANGE_MINORAXIS':'','LANE_MEDIUM_OOCYST_CV_MINORAXIS':'','LANE_MEDIUM_OOCYST_COUNT_SPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNSPORULATED':'','LANE_MEDIUM_OOCYST_COUNT_UNCERTAINSPORULATION':'','LANE_LARGE_OOCYST_COUNT':'','LANE_LARGE_OOCYST_MEAN_INTENSITY':'','LANE_LARGE_OOCYST_RANGE_INTENSITY':'','LANE_LARGE_OOCYST_CV_INTENSITY':'','LANE_LARGE_OOCYST_MEAN_AREA':'','LANE_LARGE_OOCYST_RANGE_AREA':'','LANE_LARGE_OOCYST_CV_AREA':'','LANE_LARGE_OOCYST_MEAN_MAJORAXIS':'','LANE_LARGE_OOCYST_RANGE_MAJORAXIS':'','LANE_LARGE_OOCYST_CV_MAJORAXIS':'','LANE_LARGE_OOCYST_MEAN_MINORAXIS':'','LANE_LARGE_OOCYST_RANGE_MINORAXIS':'','LANE_LARGE_OOCYST_CV_MINORAXIS':'','LANE_LARGE_OOCYST_COUNT_SPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNSPORULATED':'','LANE_LARGE_OOCYST_COUNT_UNCERTAINSPORULATION':'','STATUS':'','LANE_TOTAL_AREA_UM2':'','LANE_NOISE_AREA_UM2':'','LANE_NOISE_RATIO_PERCENT':'','LANE_NOISE_OBJECT_COUNT':'','LANE_SMALL_NOISE_OBJECT_COUNT':'','LANE_MEDIUM_NOISE_OBJECT_COUNT':'','LANE_LARGE_NOISE_OBJECT_COUNT':'','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'','IMPROC':'','VERSION':'','ERROR_CODE':'Exception'}]",
							"ImprocCocc01", true, "AN-API_FileUpload-01: Run File Upload API with unique Run ID", "This test case will run ingestion with unique run id", "Run API with unique RUN ID and unique Sample ID", "File Upload API ran successfully; File was ingested", "File Upload API failed to run", "AN-ECL-01: Verify that data is ingested after running API with Unique Run ID", "This test case will verify that data is ingested after running file upload API")
					
//					new APIModel(AnnouncementRunID, "fab8cc1ea3f6db40c4555c3dfab09a34d2fa2b53cd66af72284af1dacb266910", 
//							"20200120202842_PSN0009_sammyprocess_oaeng_improc.csv", "csv", 
//							"TEFORV9OTyxEQVRFLFRJTUUsVzFfUENfQ09VTlQsVzFfQ0VMTF9DT1VOVCxXMV9QQ19NRUFOX0lOVEVOU0lUWSxXMV9DRUxMX01FQU5fSU5URU5TSVRZLFcxX1BDX1JBTkdFX0lOVEVOU0lUWSxXMV9DRUxMX1JBTkdFX0lOVEVOU0lUWSxXMV9QQ19DVl9JTlRFTlNJVFksVzFfQ0VMTF9DVl9JTlRFTlNJVFksVzFfUENfTUVBTl9TSVpFLFcxX0NFTExfTUVBTl9TSVpFLFcxX1BDX1JBTkdFX1NJWkUsVzFfQ0VMTF9SQU5HRV9TSVpFLFcxX1BDX0NWX1NJWkUsVzFfQ0VMTF9DVl9TSVpFLFcyX1BDX0NPVU5ULFcyX0NFTExfQ09VTlQsVzJfUENfTUVBTl9JTlRFTlNJVFksVzJfQ0VMTF9NRUFOX0lOVEVOU0lUWSxXMl9QQ19SQU5HRV9JTlRFTlNJVFksVzJfQ0VMTF9SQU5HRV9JTlRFTlNJVFksVzJfUENfQ1ZfSU5URU5TSVRZLFcyX0NFTExfQ1ZfSU5URU5TSVRZLFcyX1BDX01FQU5fU0laRSxXMl9DRUxMX01FQU5fU0laRSxXMl9QQ19SQU5HRV9TSVpFLFcyX0NFTExfUkFOR0VfU0laRSxXMl9QQ19DVl9TSVpFLFcyX0NFTExfQ1ZfU0laRSxTVEFUVVMsTEFORV9UT1RBTF9BUkVBX1VNMixMQU5FX05PSVNFX0FSRUFfVU0yLExBTkVfTk9JU0VfUkFUSU9fUEVSQ0VOVCxMQU5FX05PSVNFX09CSkVDVF9DT1VOVCxXMV9OT0lTRV9BUkVBX1VNMixXMl9OT0lTRV9BUkVBX1VNMixXMV9OT0lTRV9SQVRJT19QRVJDRU5ULFcyX05PSVNFX1JBVElPX1BFUkNFTlQsVzFfTk9JU0VfT0JKRUNUX0NPVU5ULFcyX05PSVNFX09CSkVDVF9DT1VOVCxMQU5FX1NNQUxMX05PSVNFX09CSkVDVF9DT1VOVCxMQU5FX01FRElVTV9OT0lTRV9PQkpFQ1RfQ09VTlQsTEFORV9MQVJHRV9OT0lTRV9PQkpFQ1RfQ09VTlQsTEFORV9FWFRSQV9MQVJHRV9OT0lTRV9PQkpFQ1RfQ09VTlQsRVJST1JfQ09ERQ0KMSwyMDIwLTAxLTIwLDIwOjM0OjI0LjU1NjAxNCw3LDU5LDExNy4yNzgzNjczNDY5Mzg3OCwyMi40MTk5Mjg4MjU2MjI3NzYsMjMzLDEyOCwwLjYwMDUzMjk4NTc4OTI5MTMsMS4wNTczODE3MTE4OTc4NzY4LDE1My4xNDI4NTcxNDI4NTcxNCw2Ljg5Mjg1NzE0Mjg1NzE0MywxNzguMDcsMTIuNzk0OTk5OTk5OTk5OTk4LDAuNDUzNDc2MjM3NjMwMzEzMTQsMC42NjI2NjU3Mzk3MDE0MTg4LDIxLDI1LDgyLjU1NDY1NTg3MDQ0NTM0LDEwLjMxOTg2NTMxOTg2NTMyLDI1MSw1MCwwLjk0ODIzNTAwNzExMDYwMSwwLjgwMDQxODA5OTgwNDYzNDcsNjguNjkwNDc2MTkwNDc2MTksOC42NDcwNTg4MjM1Mjk0MTEsMTI0LjIsMTMuMjM5OTk5OTk5OTk5OTk4LDAuODM0MTA4MTAzNDA1MTA3NCwwLjQ1ODIxMjYyOTA4OTc2NjMsNTQwNjI2MTEzLjAsMTIxOTE0My41LDAuMjMsNDUxLDUwMzMuNSwxODk4Ny4wLDAuMDA2MjU5MDU0MDg1MTQxMDQyLDAuMDIzNjA5OTQ1MzQ5MDc1NzgyLDIzLDU1LDQ1MCwxLDAsMCwNCjIsMjAyMC0wMS0yMCwyMDozNTozMC45NTg4NDMsMCwwLG5hbixuYW4sMCwwLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sMCwxLG5hbixuYW4sMCwwLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sNTM5NzI2NjA3LjAsNDU4NDcuMCwwLjAxLDE5LDAuMCwwLjAsMC4wLDAuMCwwLDAsMTksMCwwLDAsDQozLDIwMjAtMDEtMjAsMjA6Mzc6MjEuMjQ4ODg5LDAsMCxuYW4sbmFuLDAsMCxuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLDAsMzMsbmFuLDExNS45OTIzNjY0MTIyMTM3NCwwLDEzMCxuYW4sMC4yOTg1MjI3MzE4MDI4NTE1NSxuYW4sOC43LG5hbiwxMS4zMTk5OTk5OTk5OTk5OTksbmFuLDAuNTAzMTI4MDE0NDQyMjgyNiw1Mzg2NzMzOTguMCwyMDc3NzQuMCwwLjA0LDI2LDAuMCwwLjAsMC4wLDAuMCwwLDAsMjUsMSwwLDAsDQo=", 
//							"[{'LANE':'1','PATHOGEN':'Listeria-Probes','SAMPLEID': 'Test"+lstExternalCoccidiaSampleID.get(1)+"','SCANDATETIME':'2020/10/26 08:30:17','OUTCOME':'Positive','STATUSCODE':'3','CALIBRATED_PIPER_COUNTS':'5550','COUNT_OUTCOME':'PASS','CARTRIDGEID':'TestAutomation','EXPERIMENTID':'','INSTRUMENTID':'PSN0018','USERID':'98','RUN_ID':'Test"+lstExternalCoccidiaRunID.get(1)+"','RUN_TYPE':'SCRIPT_1005a Listeria Probes','LANE_NO':'1','DATE':'2020-03-13','TIME':'','W1_PC_COUNT':'0','W1_CELL_COUNT':'5000','W1_PC_MEAN_INTENSITY':'','W1_CELL_MEAN_INTENSITY':'','W1_PC_RANGE_INTENSITY':'0','W1_CELL_RANGE_INTENSITY':'216','W1_PC_CV_INTENSITY':'','W1_CELL_CV_INTENSITY':'0.9665891343102634','W1_PC_MEAN_SIZE':'','W1_CELL_MEAN_SIZE':'6.491416309012876','W1_PC_RANGE_SIZE':'','W1_CELL_RANGE_SIZE':'11.0','W1_PC_CV_SIZE':'','W1_CELL_CV_SIZE':'1.039571210329305','W2_PC_COUNT':'1','W2_CELL_COUNT':'45','W2_PC_MEAN_INTENSITY':'77.69852941176471','W2_CELL_MEAN_INTENSITY':'9.731929549219636','W2_PC_RANGE_INTENSITY':'191','W2_CELL_RANGE_INTENSITY':'218','W2_PC_CV_INTENSITY':'0.5874496495238567','W2_CELL_CV_INTENSITY':'0.5872565165253875','W2_PC_MEAN_SIZE':'116.5','W2_CELL_MEAN_SIZE':'13.179852084267145','W2_PC_RANGE_SIZE':'0.0','W2_CELL_RANGE_SIZE':'37.0','W2_PC_CV_SIZE':'0.0','W2_CELL_CV_SIZE':'1.1630941924867089','STATUS':'','LANE_TOTAL_AREA_UM2':'536636840.0','LANE_NOISE_AREA_UM2':'5123.5','LANE_NOISE_RATIO_PERCENT':'0.0','LANE_NOISE_OBJECT_COUNT':'7','W1_NOISE_AREA_UM2':'1468.0','W2_NOISE_AREA_UM2':'8921.5','W1_NOISE_RATIO_PERCENT':'0.001825427912384434','W2_NOISE_RATIO_PERCENT':'0.011093702398050225','W1_NOISE_OBJECT_COUNT':'5','W2_NOISE_OBJECT_COUNT':'44','LANE_SMALL_NOISE_OBJECT_COUNT':'7','LANE_MEDIUM_NOISE_OBJECT_COUNT':'0','LANE_LARGE_NOISE_OBJECT_COUNT':'0','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'0','IMPROC':'ImprocSalm01','VERSION':'2.4','ERROR_CODE':'', 'MPN_CALCULATION_TYPE':'1', 'DILUTION_FACTOR':'0.1'}]", 
//							"ImprocSalm01", true, "AN-SL-04: Run File Upload Ingest API with duplicate Run ID and unique Sample ID", "This test case will run ingestion with same run id and unique sample id", "Run API with duplicate Run ID and unique Sample ID", "File Upload API ran successfully; File was ingested", "File Upload API failed to run", "AN-SL-05: Verify that data is not ingested after running API with same Run ID", "This test case will verify that data is not ingested after running file upload API with duplicate RUN ID"),
					
//					new APIModel(AnnouncementRunID, "fab8cc1ea3f6db40c4555c3dfab09a34d2fa2b53cd66af72284af1dacb266910", 
//							"20200120202842_PSN0009_sammyprocess_oaeng_improc.csv", "csv", 
//							"TEFORV9OTyxEQVRFLFRJTUUsVzFfUENfQ09VTlQsVzFfQ0VMTF9DT1VOVCxXMV9QQ19NRUFOX0lOVEVOU0lUWSxXMV9DRUxMX01FQU5fSU5URU5TSVRZLFcxX1BDX1JBTkdFX0lOVEVOU0lUWSxXMV9DRUxMX1JBTkdFX0lOVEVOU0lUWSxXMV9QQ19DVl9JTlRFTlNJVFksVzFfQ0VMTF9DVl9JTlRFTlNJVFksVzFfUENfTUVBTl9TSVpFLFcxX0NFTExfTUVBTl9TSVpFLFcxX1BDX1JBTkdFX1NJWkUsVzFfQ0VMTF9SQU5HRV9TSVpFLFcxX1BDX0NWX1NJWkUsVzFfQ0VMTF9DVl9TSVpFLFcyX1BDX0NPVU5ULFcyX0NFTExfQ09VTlQsVzJfUENfTUVBTl9JTlRFTlNJVFksVzJfQ0VMTF9NRUFOX0lOVEVOU0lUWSxXMl9QQ19SQU5HRV9JTlRFTlNJVFksVzJfQ0VMTF9SQU5HRV9JTlRFTlNJVFksVzJfUENfQ1ZfSU5URU5TSVRZLFcyX0NFTExfQ1ZfSU5URU5TSVRZLFcyX1BDX01FQU5fU0laRSxXMl9DRUxMX01FQU5fU0laRSxXMl9QQ19SQU5HRV9TSVpFLFcyX0NFTExfUkFOR0VfU0laRSxXMl9QQ19DVl9TSVpFLFcyX0NFTExfQ1ZfU0laRSxTVEFUVVMsTEFORV9UT1RBTF9BUkVBX1VNMixMQU5FX05PSVNFX0FSRUFfVU0yLExBTkVfTk9JU0VfUkFUSU9fUEVSQ0VOVCxMQU5FX05PSVNFX09CSkVDVF9DT1VOVCxXMV9OT0lTRV9BUkVBX1VNMixXMl9OT0lTRV9BUkVBX1VNMixXMV9OT0lTRV9SQVRJT19QRVJDRU5ULFcyX05PSVNFX1JBVElPX1BFUkNFTlQsVzFfTk9JU0VfT0JKRUNUX0NPVU5ULFcyX05PSVNFX09CSkVDVF9DT1VOVCxMQU5FX1NNQUxMX05PSVNFX09CSkVDVF9DT1VOVCxMQU5FX01FRElVTV9OT0lTRV9PQkpFQ1RfQ09VTlQsTEFORV9MQVJHRV9OT0lTRV9PQkpFQ1RfQ09VTlQsTEFORV9FWFRSQV9MQVJHRV9OT0lTRV9PQkpFQ1RfQ09VTlQsRVJST1JfQ09ERQ0KMSwyMDIwLTAxLTIwLDIwOjM0OjI0LjU1NjAxNCw3LDU5LDExNy4yNzgzNjczNDY5Mzg3OCwyMi40MTk5Mjg4MjU2MjI3NzYsMjMzLDEyOCwwLjYwMDUzMjk4NTc4OTI5MTMsMS4wNTczODE3MTE4OTc4NzY4LDE1My4xNDI4NTcxNDI4NTcxNCw2Ljg5Mjg1NzE0Mjg1NzE0MywxNzguMDcsMTIuNzk0OTk5OTk5OTk5OTk4LDAuNDUzNDc2MjM3NjMwMzEzMTQsMC42NjI2NjU3Mzk3MDE0MTg4LDIxLDI1LDgyLjU1NDY1NTg3MDQ0NTM0LDEwLjMxOTg2NTMxOTg2NTMyLDI1MSw1MCwwLjk0ODIzNTAwNzExMDYwMSwwLjgwMDQxODA5OTgwNDYzNDcsNjguNjkwNDc2MTkwNDc2MTksOC42NDcwNTg4MjM1Mjk0MTEsMTI0LjIsMTMuMjM5OTk5OTk5OTk5OTk4LDAuODM0MTA4MTAzNDA1MTA3NCwwLjQ1ODIxMjYyOTA4OTc2NjMsNTQwNjI2MTEzLjAsMTIxOTE0My41LDAuMjMsNDUxLDUwMzMuNSwxODk4Ny4wLDAuMDA2MjU5MDU0MDg1MTQxMDQyLDAuMDIzNjA5OTQ1MzQ5MDc1NzgyLDIzLDU1LDQ1MCwxLDAsMCwNCjIsMjAyMC0wMS0yMCwyMDozNTozMC45NTg4NDMsMCwwLG5hbixuYW4sMCwwLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sMCwxLG5hbixuYW4sMCwwLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sNTM5NzI2NjA3LjAsNDU4NDcuMCwwLjAxLDE5LDAuMCwwLjAsMC4wLDAuMCwwLDAsMTksMCwwLDAsDQozLDIwMjAtMDEtMjAsMjA6Mzc6MjEuMjQ4ODg5LDAsMCxuYW4sbmFuLDAsMCxuYW4sbmFuLG5hbixuYW4sbmFuLG5hbixuYW4sbmFuLDAsMzMsbmFuLDExNS45OTIzNjY0MTIyMTM3NCwwLDEzMCxuYW4sMC4yOTg1MjI3MzE4MDI4NTE1NSxuYW4sOC43LG5hbiwxMS4zMTk5OTk5OTk5OTk5OTksbmFuLDAuNTAzMTI4MDE0NDQyMjgyNiw1Mzg2NzMzOTguMCwyMDc3NzQuMCwwLjA0LDI2LDAuMCwwLjAsMC4wLDAuMCwwLDAsMjUsMSwwLDAsDQo=", 
//							"[{'LANE':'1','PATHOGEN':'Listeria-Probes','SAMPLEID': 'Test"+lstExternalCoccidiaSampleID.get(2)+"','SCANDATETIME':'"+date100+"','OUTCOME':'Positive','STATUSCODE':'3','CALIBRATED_PIPER_COUNTS':'5550','COUNT_OUTCOME':'PASS','CARTRIDGEID':'TestAutomation','EXPERIMENTID':'','INSTRUMENTID':'PSN0018','USERID':'98','RUN_ID':'Test"+lstExternalCoccidiaRunID.get(2)+"','RUN_TYPE':'SCRIPT_1005a Listeria Probes','LANE_NO':'1','DATE':'2020-03-13','TIME':'','W1_PC_COUNT':'0','W1_CELL_COUNT':'5000','W1_PC_MEAN_INTENSITY':'','W1_CELL_MEAN_INTENSITY':'','W1_PC_RANGE_INTENSITY':'0','W1_CELL_RANGE_INTENSITY':'216','W1_PC_CV_INTENSITY':'','W1_CELL_CV_INTENSITY':'0.9665891343102634','W1_PC_MEAN_SIZE':'','W1_CELL_MEAN_SIZE':'6.491416309012876','W1_PC_RANGE_SIZE':'','W1_CELL_RANGE_SIZE':'11.0','W1_PC_CV_SIZE':'','W1_CELL_CV_SIZE':'1.039571210329305','W2_PC_COUNT':'1','W2_CELL_COUNT':'45','W2_PC_MEAN_INTENSITY':'77.69852941176471','W2_CELL_MEAN_INTENSITY':'9.731929549219636','W2_PC_RANGE_INTENSITY':'191','W2_CELL_RANGE_INTENSITY':'218','W2_PC_CV_INTENSITY':'0.5874496495238567','W2_CELL_CV_INTENSITY':'0.5872565165253875','W2_PC_MEAN_SIZE':'116.5','W2_CELL_MEAN_SIZE':'13.179852084267145','W2_PC_RANGE_SIZE':'0.0','W2_CELL_RANGE_SIZE':'37.0','W2_PC_CV_SIZE':'0.0','W2_CELL_CV_SIZE':'1.1630941924867089','STATUS':'','LANE_TOTAL_AREA_UM2':'536636840.0','LANE_NOISE_AREA_UM2':'5123.5','LANE_NOISE_RATIO_PERCENT':'0.0','LANE_NOISE_OBJECT_COUNT':'7','W1_NOISE_AREA_UM2':'1468.0','W2_NOISE_AREA_UM2':'8921.5','W1_NOISE_RATIO_PERCENT':'0.001825427912384434','W2_NOISE_RATIO_PERCENT':'0.011093702398050225','W1_NOISE_OBJECT_COUNT':'5','W2_NOISE_OBJECT_COUNT':'44','LANE_SMALL_NOISE_OBJECT_COUNT':'7','LANE_MEDIUM_NOISE_OBJECT_COUNT':'0','LANE_LARGE_NOISE_OBJECT_COUNT':'0','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'0','IMPROC':'ImprocSalm01','VERSION':'2.4','ERROR_CODE':'', 'MPN_CALCULATION_TYPE':'1', 'DILUTION_FACTOR':'0.1'}]", 
//							"ImprocSalm01", true, "AN-SL-06: Run File Upload API with current date/time", "This test case will run ingestion with unique run id and current date/time", "Run API with uniqure Run ID and current date/time", "File Upload API ran successfully; File was ingested on top of the table", "File Upload API failed to run", "AN-SL-07: Verify that data is ingested on top of the table", "This test case will verify that data is ingested on top of the table after running api with current date/time")
					));
	               
	
	public static ArrayList<ExternalCoccidiaModel> lstExternalCoccidiaSearch = new ArrayList<>();
	
	public static String eclPNGFileName = "Coccidia Run Timeline - ";
	public static String eclCSVFileName = "Coccidia Log - ";
	public static String eclSampleMetaData = "Sample Metadata Upload Template";
	
	public static ArrayList<ExternalCoccidiaModel> lstExternalCoccidiaDateSearch = new ArrayList<>();
	
	public static ArrayList<ExternalCoccidiaModel> lstExternalCoccidiaDateEnter = new ArrayList<>();
	
	public static ArrayList<ExternalCoccidiaModel> lstExternalCoccidiaRowCount = new ArrayList<>();
	
	public static ArrayList<ExternalCoccidiaModel> lstExternalCoccidiaPagination = new ArrayList<>();
	
	//////////////////////////////////////////////////////////////End External Coccidia Variables////////////////////////////////////////////////////////////////////////////////////
	
	
	
	//////////////////////////////////////////////////////////////Coccidia Timeline Variables////////////////////////////////////////////////////////////////////////////////////

	
	public static ArrayList<CoccidiaTimelineModel> lstCoccidiaTimelineSearch = new ArrayList<>();
	
	public static String ctlTimelineFileName = "Coccidia Run Timeline - ";
	public static String ctlOCountFileName = "Oocyst Counts (Most Recent 10 Days) - ";
	public static String ctlLast10FileName = "Last 10 Coccidia Tests - ";
	public static String ctlOverTimeFileName = "Oocyst Counts Over Time - ";
	
	public static ArrayList<CoccidiaTimelineModel> lstCoccidiaTimelineDateSearch = new ArrayList<>();
	
	public static ArrayList<CoccidiaTimelineModel> lstCoccidiaTimelineDateEnter = new ArrayList<>();
	
	
	//////////////////////////////////////////////////////////////End Coccidia Timeline Variables////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	/////////////////////////////////////////////////////////////Profile Variables//////////////////////////////////////////////////////////////////////
	
	public static ArrayList<ProfileModel> lstProfileNavigate = new ArrayList<>(
			Arrays.asList(
					new ProfileModel(Constants.url_home,"AN-PS-01: Navigate to Profile Setting from Home Screen", "This test case will verify user can navigate to Profile Setting page from Home Screen", "1. Hover to sidebar and click on Home"),
					new ProfileModel(Constants.url_user,"AN-PS-02: Navigate to Profile Setting from User Management Screen", "This test case will verify user can navigate to Profile Setting page from User Management Screen", "1. Hover to sidebar and click on User Management"),
					new ProfileModel(Constants.url_organization,"AN-PS-03: Navigate to Profile Setting from Organization Management Screen", "This test case will verify user can navigate to Profile Setting page from Access Management Screen", "1. Hover to sidebar and click on Organization Management"),
					new ProfileModel(Constants.url_access,"AN-PS-04: Navigate to Profile Setting from Access Management Screen", "This test case will verify user can navigate to Profile Setting page from Access Management Screen", "1. Hover to sidebar and click on Access Management Screen"),
					new ProfileModel(Constants.url_reportsManagement,"AN-PS-05: Navigate to Profile Setting from Reports Management Screen", "This test case will verify user can navigate to Profile Setting page from Reports Management Screen", "1. Hover to sidebar and click on Reports Management"),
					new ProfileModel(Constants.url_piperManagement, "AN-PS-06: Navigate to Profile Setting from Piper Management Screen", "This test case will verify user can navigate to Profile Setting page from Piper Management Screen", "1. Hover to sidebar and click on Piper Management"),
					new ProfileModel(Constants.url_alert, "AN-PS-07: Navigate to Profile Setting from Alert Management Screen", "This test case will verify user can navigate to Profile Setting page from Alert Management Screen", "1. Hover to sidebar and click on Alert Management"),
					new ProfileModel(Constants.url_agreementManagement, "AN-PS-08: Navigate to Profile Setting from Agreement Management Screen", "This test case will verify user can navigate to Profile Setting page from Agreement Management Screen", "1. Hover to sidebar and click on Agreement Management"),
					new ProfileModel(Constants.url_dataTemplate, "AN-PS-09: Navigate to Profile Setting from Data Template Screen", "This test case will verify user can navigate to Profile Setting page from Data Template Screen", "1. Hover to sidebar and click on Data Template"),
					new ProfileModel(Constants.url_dataUpload, "AN-PS-10: Navigate to Profile Setting from Data Upload Screen", "This test case will verify user can navigate to Profile Setting page from Data Upload Screen", "1. Hover to sidebar and click on Data Upload"),
					new ProfileModel(Constants.url_reports, "AN-PS-11: Navigate to Profile Setting from Reports Screen", "This test case will verify user can navigate to Profile Setting page from Reports Screen", "1. Hover to sidebar and click on Reports"),
					new ProfileModel(Constants.url_SalmonellaLog, "AN-PS-12: Navigate to Profile Setting from Salmoenella Screen", "This test case will verify user can navigate to Profile Setting page from Salmonella Screen", "1. Hover to sidebar; click on Reports and select Salmonella log Report"),
					new ProfileModel(Constants.url_ExternalSalmonellaLog, "AN-PS-13: Navigate to Profile Setting from External Salmonella Screen", "This test case will verify user can navigate to Profile Setting page from External Salmonella  Screen", "1. Hover to sidebar; click on Reports and select External Salmonella log Report"),
					new ProfileModel(Constants.url_MPNSalmonellaLog, "AN-PS-14: Navigate to Profile Setting from MPN Salmonella Log Screen", "This test case will verify user can navigate to Profile Setting page from MPN Salmonella Log Screen", "1. Hover to sidebar; click on Reports and select MPN Salmonella Log"),
					new ProfileModel(Constants.url_ExternalMPNSalmonellaLog, "AN-PS-15: Navigate to Profile Setting from External MPN Salmonella Log Screen", "This test case will verify user can navigate to Profile Setting page from External MPN Salmonella Log Screen", "1. Hover to sidebar; click on Reports and select External MPN Salmonella Log"),
					new ProfileModel(Constants.url_CoccidiaLog, "AN-PS-16: Navigate to Profile Setting from Coccidia Log Screen", "This test case will verify user can navigate to Profile Setting page from Coccidia Log Screen", "1. Hover to sidebar; click on Reports and select Coccidia Log"),
					new ProfileModel(Constants.url_CoccidiaTimeline, "AN-PS-17: Navigate to Profile Setting from Coccidia Timeline Screen", "This test case will verify user can navigate to Profile Setting page from Coccidia Timeline Screen", "1. Hover to sidebar; click on Reports and select Coccidia Timeline"),
					new ProfileModel(Constants.url_ExternalCoccidiaLog, "AN-PS-18: Navigate to Profile Setting from External Coccidia Log Screen", "This test case will verify user can navigate to Profile Setting page from External Coccidia Log Screen", "1. Hover to sidebar; click on Reports and select External Coccidia Log"),
					new ProfileModel(Constants.url_LocalDashboard, "AN-PS-19: Navigate to Profile Setting from Local Dashboard Screen", "This test case will verify user can navigate to Profile Setting page from Local Dashboard Screen", "1. Hover to sidebar; click on Reports and select Local Dashboard")
					));
	
	
	public static ArrayList<ProfileModel> lstProfileMandatoryCheck = new ArrayList<>(
			Arrays.asList(
				//	new ProfileModel(true, "", "", false, "6666666666", false, "", "testcase1", true),
			//		new ProfileModel(true, "Junaid", "", false, "6666666666", false, "", "testcase2", true),
					new ProfileModel(true, "Junaid", "Alam", true, "6666666666", false, "", "testcase3", true)
			//		new ProfileModel(false, "junaid", "", false, "", false, "", "testcase4", true),
			//		new ProfileModel(false, "junaid", "alam", true, "6666666666", true, "6666666666", "testcase4", true)
					));
	
	
	
}
