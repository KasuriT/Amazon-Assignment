package Models;

import static MiscFunctions.DateUtil.date0;
import static MiscFunctions.Helper.driver;
import static MiscFunctions.Helper.waitElementInvisible;
import static PageObjects.BasePage.loading_cursor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.By;

import PageObjects.OrganizationManagementPage;

public class OrganizationManagementModel {

	public boolean orgType;
	public String orgName;
	public boolean orgPhoneCode;
	public String orgPhoneNo;
	public String orgEmail;
	public String step1;
	public boolean chkMandatoryFieldsS1;
	public String step2;
	public String maxUsers;
	public boolean chkMandatoryFieldsS2;
	public String testCaseTitle;
	public String testCaseDesc;
	public String passScenario;
	public String failScenario;
	public int rows;

	public String passMessage;
	public String failMessage;
	public String fileName;
	public String fileType;
	public String alertMessage;
	public ArrayList<ReportFilters> lstFilters;
	public ArrayList<String> LstValuesSiteName;
	public ArrayList<String> LstValuesSiteType;
	public String templateName;
	public String passStep;
	public String failStep;
	public String AlertMessage;
	public String ErrorMessage;
	public boolean ErrorCase;
	public String steps;
	public boolean getParentSiteID;
	
	public String TestCaseName;
	public String TestCaseDescription;
	public String TestCaseNameSearch;
	public String TestCaseDescriptionSearch;
	public boolean startWith;
	public boolean endsWith;
	public boolean contains;
	public String input;
	
	public static String BulkSitefileName = "BulkSiteTemplate.xlsx";
	
	public static String applyFilterTitle = "Verify user can select checkbox from ";
	public static String applyFilterDesc = "This test case will verify that user can select checkbox from ";
	public static String filterIndicatorTitle = "Verify user can apply filter and table displays relevant results on applying ";
	public static String filterIndicatorDesc = "This test case will verify that user can apply filter and table displays relevant results on applying ";

	public OrganizationManagementModel (boolean _orgType, String _orgName, boolean _orgPhoneCode, String _orgPhoneNo, String _orgEmail, String _step1, boolean _chkMandatoryFieldsS1,
			String _step2, String _maxUsers, boolean _chkMandatoryFieldsS2, String _testCaseTitle, String _testCaseDesc, String _passScenario, String _failScenario)
	{
	
		this.orgType = _orgType;
		this.orgName = _orgName;
		this.orgPhoneCode = _orgPhoneCode;
		this.orgPhoneNo = _orgPhoneNo;
		this.orgEmail = _orgEmail;
		this.step1 = _step1;
		this.chkMandatoryFieldsS1 = _chkMandatoryFieldsS1;

		this.step2 = _step2;
		this.maxUsers = _maxUsers;
		this.chkMandatoryFieldsS2 = _chkMandatoryFieldsS2;

		this.testCaseTitle = _testCaseTitle;
		this.testCaseDesc = _testCaseDesc;
		this.passScenario = _passScenario;
		this.failScenario =_failScenario;
	}

	public String orgSearchName;
	public String orgSearchTestCaseTitle;
	public String orgSearchTestCaseDesc;
	public String orgSearchResult;
	public String orgSearchStep;
	public String orgSearchPassScenario;
	public String orgSearchFailScenario;

	public OrganizationManagementModel (String _orgSearchName, String _orgSearchTestCaseTitle, String _orgSearchTestCaseDesc, String _orgSearchResult, String _orgSearchStep, String _orgSearchPassScenario, String _orgSearchFaiScenario)
	{
		this.orgSearchName = _orgSearchName;
		this.orgSearchTestCaseTitle = _orgSearchTestCaseTitle;
		this.orgSearchTestCaseDesc = _orgSearchTestCaseDesc;
		this.orgSearchResult = _orgSearchResult;
		this.orgSearchStep = _orgSearchStep;
		this.orgSearchPassScenario = _orgSearchPassScenario;
		this.orgSearchFailScenario = _orgSearchFaiScenario;
	}	

	public String orgSearch;
	public String url;
	public OrganizationManagementModel (String _orgSearch, String _url)
	{
		this.orgSearch = _orgSearch;
		this.url = _url;
	}
	
	
	public OrganizationManagementModel() {
	}


	public static ArrayList<OrganizationManagementModel> BulkSiteFillData() {
		ArrayList<OrganizationManagementModel> lstBulkSiteUploadModel = new ArrayList<OrganizationManagementModel>();
		OrganizationManagementModel objTmp = new OrganizationManagementModel();
		ReportFilters objFilter = new ReportFilters();
		
		objTmp = new OrganizationManagementModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-46: Verify that user cannot upload Bulk Sites leaving Parent Site empty";
		objTmp.testCaseDesc = "This test case will verify that user cannot upload Bulk Sites leaving Parent Site empty";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0,1,2));
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1,1,1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("", "Region", "TestSite1"));
		objTmp.getParentSiteID = false;
		objTmp.ErrorCase = true;
	//	objTmp.fileName =BulkSitefileName;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.ErrorMessage = "No value provided for PARENT_SITE_ID.";
		objTmp.passStep = "User was not able to upload Bulk Sites leaving Parent Site empty successfully";
		objTmp.failStep = "User was able to upload Bulk Sites leaving Parent Site empty";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
	
		objTmp = new OrganizationManagementModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-47: Verify that user cannot upload Bulk Sites leaving Site Type empty";
		objTmp.testCaseDesc = "This test case will verify that user cannot upload Bulk Sites leaving Site Type empty";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1,2));
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1,1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("", "TestSite1"));
		objTmp.getParentSiteID = true;
		objTmp.ErrorCase = true;
		objTmp.fileName =BulkSitefileName;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.ErrorMessage = "No value provided for SITE_TYPE.";
		objTmp.passStep = "User was not able to upload Bulk Sites leaving Site Type empty successfully";
		objTmp.failStep = "User was able to upload Bulk Sites leaving Site Type empty";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
	
		objTmp = new OrganizationManagementModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-48: Verify that user cannot upload Bulk Sites with invalid Site Type";
		objTmp.testCaseDesc = "This test case will verify that user cannot upload Bulk Sites with invalid Site Type empty";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1,2));
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1,1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Home", "TestSite1"));
		objTmp.getParentSiteID = true;
		objTmp.ErrorCase = true;
		objTmp.fileName =BulkSitefileName;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.ErrorMessage = "Site Type does not exists.";
		objTmp.passStep = "User was not able to upload Bulk Sites leaving Site Type empty successfully";
		objTmp.failStep = "User was able to upload Bulk Sites leaving Site Type empty";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
		
		objTmp = new OrganizationManagementModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-49: Verify that user cannot upload Bulk Sites when Site Type violates hierarchy rules";
		objTmp.testCaseDesc = "This test case will verify that user cannot upload Bulk Sites when Site Type violates hierarchy rules";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1,2));
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1,1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Farm", "TestSite1"));
		objTmp.getParentSiteID = true;
		objTmp.ErrorCase = true;
		objTmp.fileName =BulkSitefileName;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.ErrorMessage = "Site Type violates hierarchical rules.";
		objTmp.passStep = "User was not able to upload Bulk Sites leaving Site Type empty successfully";
		objTmp.failStep = "User was able to upload Bulk Sites leaving Site Type empty";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
			
		objTmp = new OrganizationManagementModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-50: Verify that user cannot upload Bulk Sites leaving Site Name empty";
		objTmp.testCaseDesc = "This test case will verify that user cannot upload Bulk Sites leaving Site Name empty";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1,2));
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1,1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Region", ""));
		objTmp.getParentSiteID = true;
		objTmp.ErrorCase = true;
		objTmp.fileName = BulkSitefileName;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.ErrorMessage = "No value provided for SITE_NAME.";
		objTmp.passStep = "User was not able to upload Bulk Sites leaving Site Name empty successfully";
		objTmp.failStep = "User was able to upload Bulk Sites leaving Site Name empty";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
			
		objTmp = new OrganizationManagementModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-51: Verify that user cannot upload Bulk Sites with duplicate Site Name";
		objTmp.testCaseDesc = "This test case will verify that user cannot upload Bulk Sites with duplicate Site Name";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1, 2, 1, 2, 1, 2, 1, 2));
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1, 1, 2, 2, 8, 8, 9, 9));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Region", "Region", "TestRegion", "TestRegion", "","","",""));
		objTmp.getParentSiteID = true;
		objTmp.ErrorCase = true;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.ErrorMessage = "Repetition found in Key Field.";
		objTmp.passStep = "User was not able to upload Bulk Sites with duplicate Site Name successfully";
		objTmp.failStep = "User was able to upload Bulk Sites with duplicate Site Name";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);	
		
		objTmp = new OrganizationManagementModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-52: Verify that user cannot upload Bulk Sites with country other than USA";
		objTmp.testCaseDesc = "This test case will verify that user cannot upload Bulk Sites with country other than USA";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1, 1, 1, 2, 2,2));
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1, 2, 4,0, 1,2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Region", "TestRegion", "Pakistan", "", "", ""));
		objTmp.getParentSiteID = true;
		objTmp.ErrorCase = true;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.ErrorMessage = "Country does not exists.";
		objTmp.passStep = "User was not able to upload Bulk Sites with country other than USA successfully";
		objTmp.failStep = "User was able to upload Bulk Sites with country other than USA";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
	
		objTmp = new OrganizationManagementModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-53: Verify that user cannot upload Bulk Sites with invalid state";
		objTmp.testCaseDesc = "This test case will verify that user cannot upload Bulk Sites with invalid state";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1, 1, 1, 1));
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1, 2, 4, 5));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Region", "TestRegion", "United States", "Punjab"));
		objTmp.getParentSiteID = true;
		objTmp.ErrorCase = true;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.ErrorMessage = "State does not exists.";
		objTmp.passStep = "User was not able to upload Bulk Sites with country other than USA successfully";
		objTmp.failStep = "User was able to upload Bulk Sites with country other than USA";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
		
		objTmp = new OrganizationManagementModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-54: Verify that user cannot upload Bulk Sites with invalid city";
		objTmp.testCaseDesc = "This test case will verify that user cannot upload Bulk Sites with invalid city";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1));
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1, 2, 4, 5, 6));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Region", "TestRegion", "United States", "New York", "Lahore"));
		objTmp.getParentSiteID = true;
		objTmp.ErrorCase = true;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.ErrorMessage = "City does not exists.";
		objTmp.passStep = "User was not able to upload Bulk Sites with country other than USA successfully";
		objTmp.failStep = "User was able to upload Bulk Sites with country other than USA";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
		
		objTmp = new OrganizationManagementModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-55: Verify that user cannot upload Bulk Sites with value in not in decimal number for latitude";
		objTmp.testCaseDesc = "This test case will verify that user cannot upload Bulk Sites with value in not in decimal number for latitude";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 1));
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1, 2, 4, 5, 6, 8));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Region", "TestRegion", "United States", "New York", "", "Latitude"));
		objTmp.getParentSiteID = true;
		objTmp.ErrorCase = true;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.ErrorMessage = "Value in LATITUDE is not valid.";
		objTmp.passStep = "User was not able to upload Bulk Sites with value in not in decimal number for latitude and longitude successfully";
		objTmp.failStep = "User was able to upload Bulk Sites with value in not in decimal number for latitude and longitude";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
		
		objTmp = new OrganizationManagementModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-56: Verify that user cannot upload Bulk Sites with value in not in decimal number for Longitude";
		objTmp.testCaseDesc = "This test case will verify that user cannot upload Bulk Sites with value in not in decimal number for Longitude";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 1, 1));
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1, 2, 4, 5, 6, 8, 9));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Region", "TestRegion", "United States", "New York", "", "", "Longitude"));
		objTmp.getParentSiteID = true;
		objTmp.ErrorCase = true;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.ErrorMessage = "Value in LONGITUDE is not valid.";
		objTmp.passStep = "User was not able to upload Bulk Sites with value in not in decimal number for latitude and longitude successfully";
		objTmp.failStep = "User was able to upload Bulk Sites with value in not in decimal number for latitude and longitude";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
	
		objTmp = new OrganizationManagementModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-57: Verify that user can upload Bulk Sites hierarchy in correct order";
		objTmp.testCaseDesc = "This test case will verify that user can upload Bulk Sites hierarchy in correct order";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2));
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1,1,2,2,3,3,4,4,5,5,6,6,8,8,9,9));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Region", "Region", "TestRegion1", "TestRegion2", "New York", "New York", "United States", "United States", "New York", "New York", "New York", "New York", "40.71", "40.71", "-74.00", "-74.00"));
		objTmp.getParentSiteID = true;
		objTmp.ErrorCase = false;
		objTmp.AlertMessage = BulkSitefileName+" loaded successfully.";
		objTmp.passStep = "User was not able to upload Bulk Sites hierarchy in incorrect order successfully";
		objTmp.failStep = "User was able to upload Bulk Sites hierarchy in incorrect order";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
		
		return lstBulkSiteUploadModel;
	}
	
	
	
	public static ArrayList<String> lstOrganizationCreate = new ArrayList<>(
			Arrays.asList("Test Organization"+date0,
					"(666) 666-6666",
					"ancera",  //invalid email
					"ancera"+date0+"@ancera.com",  //valid email
					"100",
					"Test_Allied_Org_"+date0));


	public static ArrayList<String> lstOrgAlertMessages = new ArrayList<>(
			Arrays.asList("New organization has been created successfully", 
					"Organization details updated successfully",
					"New site created.",
					"Site details updated.",
					"Site details deleted successfully.",
					"Organization details deleted successfully.",
					"Product has been added successfully.", 
					"Product details deleted",
					"Product details updated"));
	
	public static ArrayList<OrganizationManagementModel> lstOrgBulkSite = new ArrayList<>(
			Arrays.asList(
					new OrganizationManagementModel("AN-OM-02: Verify user cannot upload pdf file", "This test case will verify that user can upload pdf file", "User was able to upload pdf file successfully", "User was not able to upload pdf file", "/EULA/sample.pdf", "PNG file", "New user agreement created."),
					new OrganizationManagementModel("AN-OM-03: Verify user cannot upload docx file", "This test case will verify that user cannot upload docx file", "User was not able to upload docx file successfully", "User was able to upload docx file", "/EULA/sample.docx", "DOCX file", "Please select pdf file."),
					new OrganizationManagementModel("AN-OM-04: Verify user cannot upload xlsx file", "This test case will verify that user cannot upload xlsx file", "User was not able to upload xlsx file successfully", "User was able to upload xlsx file", "/EULA/sample.xlsx", "XLSX file", "Please select pdf file."),
					new OrganizationManagementModel("AN-OM-05: Verify user cannot upload csv file", "This test case will verify that user cannot upload csv file", "User was not able to upload csv file successfully", "User was able to upload csv file", "/EULA/sample.csv", "CSV file", "Please select pdf file."),
					new OrganizationManagementModel("AN-OM-06: Verify user cannot upload png file", "This test case will verify that user cannot upload png file", "User was not able to upload png file successfully", "User was able to upload png file", "/EULA/sample.png", "PNG file", "Please select pdf file.")
					));
	
	public static ArrayList<OrganizationManagementModel> lstOrgBulkSiteUpload = new ArrayList<>();
	
	public static void openEditOrgPopup(String orgName) throws InterruptedException, IOException {
		for (int i=1;i<driver.findElements(By.cssSelector("tr")).size(); i++) {
			if (driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+OrganizationManagementPage.orgNameCol+" label")).getText().equals(orgName)) {
				driver.findElement(By.id("edit-orgn-"+i)).click();
				waitElementInvisible(loading_cursor);
				break;
			}
		}
	}
}