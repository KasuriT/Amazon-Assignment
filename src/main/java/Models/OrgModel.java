package Models;

import java.util.ArrayList;
import java.util.Arrays;

import Test.Ancera.Test_Elements;

public class OrgModel {

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
	public static String BulkSitefileNameEmpty = "BulkSiteTemplate1.xlsx";
	public static String BulkSitefileNameHierarchy = "BulkSiteTemplate1.xlsx";
	
	public static String applyFilterTitle = "Verify user can select checkbox from ";
	public static String applyFilterDesc = "This test case will verify that user can select checkbox from ";
	public static String filterIndicatorTitle = "Verify user can apply filter and table displays relevant results on applying ";
	public static String filterIndicatorDesc = "This test case will verify that user can apply filter and table displays relevant results on applying ";

	public OrgModel (boolean _orgType, String _orgName, boolean _orgPhoneCode, String _orgPhoneNo, String _orgEmail, String _step1, boolean _chkMandatoryFieldsS1,
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

	public OrgModel (String _orgSearchName, String _orgSearchTestCaseTitle, String _orgSearchTestCaseDesc, String _orgSearchResult, String _orgSearchStep, String _orgSearchPassScenario, String _orgSearchFaiScenario)
	{
		this.orgSearchName = _orgSearchName;
		this.orgSearchTestCaseTitle = _orgSearchTestCaseTitle;
		this.orgSearchTestCaseDesc = _orgSearchTestCaseDesc;
		this.orgSearchResult = _orgSearchResult;
		this.orgSearchStep = _orgSearchStep;
		this.orgSearchPassScenario = _orgSearchPassScenario;
		this.orgSearchFailScenario = _orgSearchFaiScenario;
	}	

	//////////
//	public ArrayList<OrgModel> orgSearch;
//	public OrgModel (ArrayList<OrgModel> orgSearch)
//	{
//		this.orgSearch = orgSearch;
//	}
//	
	
	
	
	/////////////
	
	public OrgModel() {
	}


	public static ArrayList<OrgModel> BulkSiteFillData() {
		ArrayList<OrgModel> lstBulkSiteUploadModel = new ArrayList<OrgModel>();
		OrgModel objTmp = new OrgModel();
		ReportFilters objFilter = new ReportFilters();
		
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-46: Verify that user cannot upload Bulk Sites leaving Parent Site empty";
		objTmp.testCaseDesc = "This test case will verify that user cannot upload Bulk Sites leaving Parent Site empty";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0,1,2));
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1,1,1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("", "Region", "TestSite1"));
		objTmp.getParentSiteID = false;
		objTmp.ErrorCase = true;
		objTmp.fileName =BulkSitefileName;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.ErrorMessage = "No value provided for PARENT_SITE_ID.";
		objTmp.passStep = "User was not able to upload Bulk Sites leaving Parent Site empty successfully";
		objTmp.failStep = "User was able to upload Bulk Sites leaving Parent Site empty";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
	
		objTmp = new OrgModel();
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
	
		objTmp = new OrgModel();
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
		objTmp.ErrorMessage = "Site Type does not exist.";
		objTmp.passStep = "User was not able to upload Bulk Sites leaving Site Type empty successfully";
		objTmp.failStep = "User was able to upload Bulk Sites leaving Site Type empty";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
		
		objTmp = new OrgModel();
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
			
		objTmp = new OrgModel();
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
			
		objTmp = new OrgModel();
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
		
		objTmp = new OrgModel();
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
		objTmp.ErrorMessage = "Country does not exist.";
		objTmp.passStep = "User was not able to upload Bulk Sites with country other than USA successfully";
		objTmp.failStep = "User was able to upload Bulk Sites with country other than USA";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
	
		objTmp = new OrgModel();
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
		objTmp.ErrorMessage = "State does not exist.";
		objTmp.passStep = "User was not able to upload Bulk Sites with country other than USA successfully";
		objTmp.failStep = "User was able to upload Bulk Sites with country other than USA";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
		
		objTmp = new OrgModel();
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
		objTmp.ErrorMessage = "City does not exist.";
		objTmp.passStep = "User was not able to upload Bulk Sites with country other than USA successfully";
		objTmp.failStep = "User was able to upload Bulk Sites with country other than USA";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
		
		objTmp = new OrgModel();
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
		objTmp.ErrorMessage = "Value in LATITUDE is not a valid decimal number.";
		objTmp.passStep = "User was not able to upload Bulk Sites with value in not in decimal number for latitude and longitude successfully";
		objTmp.failStep = "User was able to upload Bulk Sites with value in not in decimal number for latitude and longitude";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
		
		objTmp = new OrgModel();
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
		objTmp.ErrorMessage = "Value in LONGITUDE is not a valid decimal number.";
		objTmp.passStep = "User was not able to upload Bulk Sites with value in not in decimal number for latitude and longitude successfully";
		objTmp.failStep = "User was able to upload Bulk Sites with value in not in decimal number for latitude and longitude";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
	
		objTmp = new OrgModel();
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
	
	
	public static ArrayList<OrgModel> FillData() {
		ArrayList<OrgModel> lstOrgModel = new ArrayList<OrgModel>();
		OrgModel objTmp; 
		ReportFilters  objFilter;

		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Organization Name Filter";
		objTmp.TestCaseName = "AN-OM-65: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-OM-66: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList(Test_Elements.orgName));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("3"));
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp); 
		
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Phone Number Filter";
		objTmp.TestCaseName = "AN-OM-67: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-OM-68: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList(Test_Elements.orgPhoneNumber));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("3"));
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp); 
		
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "City Filter";
		objTmp.TestCaseName = "AN-OM-69: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-OM-70: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList(Test_Elements.orgCity));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("3"));
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp); 
		
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "State Filter";
		objTmp.TestCaseName = "AN-OM-71: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-OM-72: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList(Test_Elements.orgState));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("3"));
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp); 
		
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Country Filter";
		objTmp.TestCaseName = "AN-OM-73: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-OM-74: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList(Test_Elements.orgCountry));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("3"));
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp); 
		
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Organization Type Filter";
		objTmp.TestCaseName = "AN-OM-75: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-OM-76: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList(Test_Elements.orgOrganzationType));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("3"));
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp); 
		
//		objTmp = new OrgModel();
//		objFilter = new ReportFilters();
//		objFilter.FilterName = "Two Filters";
//		objTmp.TestCaseName = "AN-OM-77: Verify user can apply 2 filters at same time";
//		objTmp.TestCaseDescription = "This testcase will verify that user can apply 2 filters at same time";
//		objTmp.TestCaseNameSearch = "AN-UM-78: "+filterIndicatorTitle+objFilter.FilterName;
//		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter = new ReportFilters();
//		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList(Test_Elements.orgOrganzationType,Test_Elements.orgName));
//		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1", "3"));
//		objTmp.lstFilters.add(objFilter);
//		lstOrgModel.add(objTmp);
		
		return lstOrgModel;
	}
	
	public static ArrayList<OrgModel> Wildcard() {
		ArrayList<OrgModel> lstOrgModel = new ArrayList<OrgModel>();
		OrgModel objTmp; 
		ReportFilters  objFilter;

		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Organization Name Filter";
		objTmp.TestCaseName = "AN-OM-79: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.orgName;
		objTmp.startWith = true;
		objTmp.contains = false;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.orgNameCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp);
		
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Organization Name Filter";
		objTmp.TestCaseName = "AN-OM-80: Verify wild card Contains search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Contains search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.orgName;
		objTmp.startWith = false;
		objTmp.contains = true;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.orgNameCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp);
		
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Organization Name Filter";
		objTmp.TestCaseName = "AN-OM-81: Verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.orgName;
		objTmp.startWith = false;
		objTmp.contains = false;
		objTmp.endsWith = true;
		objFilter.ColumnID = Test_Elements.orgNameCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp);
				
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Phone Number Filter";
		objTmp.TestCaseName = "AN-OM-82: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.orgPhoneNumber;
		objTmp.startWith = true;
		objTmp.contains = false;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.orgPhoneNumberCol;
		objTmp.input = "+1";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("+1", "+1"));
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp);
		
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Phone Number Filter";
		objTmp.TestCaseName = "AN-OM-83: Verify wild card Contains search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Contains search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.orgPhoneNumber;
		objTmp.startWith = false;
		objTmp.contains = true;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.orgPhoneNumberCol;
		objTmp.input = "+1";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("+1", "+1"));
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp);
			
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Phone Number Filter";
		objTmp.TestCaseName = "AN-OM-84: Verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.orgPhoneNumber;
		objTmp.startWith = false;
		objTmp.contains = false;
		objTmp.endsWith = true;
		objFilter.ColumnID = Test_Elements.orgPhoneNumberCol;
		objTmp.input = "+1";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("+1", "+1"));
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp);
				
		
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "City Filter";
		objTmp.TestCaseName = "AN-OM-85: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.orgCity;
		objTmp.startWith = true;
		objTmp.contains = false;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.orgCityCol;
		objTmp.input = "Bo";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Bo", "bo"));
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp);
		
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "City Filter";
		objTmp.TestCaseName = "AN-OM-86: Verify wild card Contains search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Contains search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.orgCity;
		objTmp.startWith = false;
		objTmp.contains = true;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.orgCityCol;
		objTmp.input = "Bo";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Bo", "bo"));
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp);
			
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "City Filter";
		objTmp.TestCaseName = "AN-OM-87: Verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.orgCity;
		objTmp.startWith = false;
		objTmp.contains = false;
		objTmp.endsWith = true;
		objFilter.ColumnID = Test_Elements.orgCityCol;
		objTmp.input = "Bo";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Bo", "bo"));
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp);
		
		
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "State Filter";
		objTmp.TestCaseName = "AN-OM-88: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.orgState;
		objTmp.startWith = true;
		objTmp.contains = false;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.orgStateCol;
		objTmp.input = "B";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("B", "b"));
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp);
		
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "State Filter";
		objTmp.TestCaseName = "AN-OM-89: Verify wild card Contains search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Contains search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.orgState;
		objTmp.startWith = false;
		objTmp.contains = true;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.orgStateCol;
		objTmp.input = "B";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("B", "b"));
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp);
			
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "State Filter";
		objTmp.TestCaseName = "AN-OM-90: Verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.orgState;
		objTmp.startWith = false;
		objTmp.contains = false;
		objTmp.endsWith = true;
		objFilter.ColumnID = Test_Elements.orgStateCol;
		objTmp.input = "B";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("B", "b"));
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp);
		
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Country Filter";
		objTmp.TestCaseName = "AN-OM-91: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.orgCountry;
		objTmp.startWith = true;
		objTmp.contains = false;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.orgCountryCol;
		objTmp.input = "Un";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Un", "un"));
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp);
		
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Country Filter";
		objTmp.TestCaseName = "AN-OM-92: Verify wild card Contains search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Contains search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.orgCountry;
		objTmp.startWith = false;
		objTmp.contains = true;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.orgCountryCol;
		objTmp.input = "Un";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Un", "un"));
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp);
			
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Country Filter";
		objTmp.TestCaseName = "AN-OM-93: Verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.orgCountry;
		objTmp.startWith = false;
		objTmp.contains = false;
		objTmp.endsWith = true;
		objFilter.ColumnID = Test_Elements.orgCountryCol;
		objTmp.input = "Un";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Un", "un"));
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp);
		
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Organization Type Filter";
		objTmp.TestCaseName = "AN-OM-94: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.orgOrganzationType;
		objTmp.startWith = true;
		objTmp.contains = false;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.orgOrganzationTypeCol;
		objTmp.input = "cli";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("cli", "Cli"));
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp);
		
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Organization Type Filter";
		objTmp.TestCaseName = "AN-OM-95: Verify wild card Contains search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Contains search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.orgOrganzationType;
		objTmp.startWith = false;
		objTmp.contains = true;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.orgOrganzationTypeCol;
		objTmp.input = "cli";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("cli", "Cli"));
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp);
			
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Organization Type Filter";
		objTmp.TestCaseName = "AN-OM-96: Verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.orgOrganzationType;
		objTmp.startWith = false;
		objTmp.contains = false;
		objTmp.endsWith = true;
		objFilter.ColumnID = Test_Elements.orgOrganzationTypeCol;
		objTmp.input = "cli";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("cli", "Cli"));
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp);
		return lstOrgModel;
	}

	public static ArrayList<OrgModel> Lock() {
		ArrayList<OrgModel> lstOrgModel = new ArrayList<OrgModel>();
		OrgModel objTmp = new OrgModel();
		ReportFilters objFilter ;
		
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Organization Name Filter";
		objTmp.TestCaseName = "AN-OM-97: Verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.orgName;
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp);
		
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Phone Number Filter";
		objTmp.TestCaseName = "AN-OM-98: Verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.orgPhoneNumber;
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp);
		
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "City Filter";
		objTmp.TestCaseName = "AN-OM-99: Verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.orgCity;
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp);
		
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "State Filter";
		objTmp.TestCaseName = "AN-OM-100: Verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.orgState;
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp);
		
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Country Filter";
		objTmp.TestCaseName = "AN-OM-101: Verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.orgCountry;
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp);
		
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Organization Type Filter";
		objTmp.TestCaseName = "AN-OM-102: Verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.orgOrganzationType;
		objTmp.lstFilters.add(objFilter);
		lstOrgModel.add(objTmp);
		
		return lstOrgModel;
	}
}