package Models;

import java.util.ArrayList;
import java.util.Arrays;

import MiscFunctions.Constants;

public class ProfileModel {

	public Boolean isOpenPage;
	public String url;
	public String testCaseNavigate;
	public String testCaseDescription;
	public String stepPage;
	public String firstName;
	public String lastName;
	public Boolean cellCode;
	public String cellNo;
	public Boolean phoneCode;
	public String phoneNo;
	public String testCaseMandatoryCheck;
	public Boolean CheckS1;
	public String pageTitle;

	public ProfileModel( String _url, String _testCase, String _testCaseDescription, String _stepPage, String _pageTitle)

	{
		this.url = _url;
		this.testCaseNavigate = _testCase;
		this.testCaseDescription = _testCaseDescription;
		this.stepPage = _stepPage;
		this.pageTitle = _pageTitle;
	}



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
					new ProfileModel(Constants.url_complexConfig, "AN-PS-09: Navigate to Profile Setting from Complex Cycling Config Screen", "This test case will verify user can navigate to Profile Setting page from Complex Cycling Config Management Screen", "1. Hover to sidebar and click on Complex Cycling Config", "Complex OPG Range Config"),
					new ProfileModel(Constants.url_flockManagement, "AN-PS-10: Navigate to Profile Setting from Flock Registration Screen", "This test case will verify user can navigate to Profile Setting page from Flock Registration Screen", "1. Hover to sidebar and click on Flock Registration", "Flock Management"),
					new ProfileModel(Constants.url_programManagement, "AN-PS-11: Navigate to Profile Setting from Program Management Screen", "This test case will verify user can navigate to Profile Setting page from Program Registration Screen", "1. Hover to sidebar and click on Program Registration", "Program Management"),
					new ProfileModel(Constants.url_piperManagement, "AN-PS-12: Navigate to Profile Setting from Piper Management Screen", "This test case will verify user can navigate to Profile Setting page from Piper Management Screen", "1. Hover to sidebar and click on Piper Management", "PIPER Management"),
					new ProfileModel(Constants.url_piperSoftware, "AN-PS-13: Navigate to Profile Setting from Piper Software Management Screen", "This test case will verify user can navigate to Profile Setting page from Piper Management Screen", "1. Hover to sidebar and click on Piper Software Management", "PIPER Software Management"),
					new ProfileModel(Constants.url_piperConfiguration, "AN-PS-14: Navigate to Profile Setting from Piper Configuration Management Screen", "This test case will verify user can navigate to Profile Setting page from Piper Management Screen", "1. Hover to sidebar and click on Piper Config Management", "PIPER Configuration Management"),
					new ProfileModel(Constants.url_dataTemplate, "AN-PS-15: Navigate to Profile Setting from Data Template Screen", "This test case will verify user can navigate to Profile Setting page from Data Template Screen", "1. Hover to sidebar and click on Data Template", "Data Template Management"),
					new ProfileModel(Constants.url_dataUpload, "AN-PS-16: Navigate to Profile Setting from Data Upload Screen", "This test case will verify user can navigate to Profile Setting page from Data Upload Screen", "1. Hover to sidebar and click on Data Upload", "Data Upload"),
					new ProfileModel(Constants.url_poultryManagement, "AN-PS-17: Navigate to Profile Setting from Poultry Management Screen", "This test case will verify user can navigate to Profile Setting page from Poultry Management Screen", "1. Hover to sidebar and click on Poultry Management", "Logging and Management"),
					new ProfileModel(Constants.url_reports, "AN-PS-18: Navigate to Profile Setting from Reports Screen", "This test case will verify user can navigate to Profile Setting page from Reports Screen", "1. Hover to sidebar and click on Reports", "Reports")
					));



}
