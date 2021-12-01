package Models;

import java.util.ArrayList;
import java.util.Arrays;

import Test.Ancera.Administration.OrganizationManagement;

public class OrgModel {

	public boolean isOpenPopUp;
	public boolean orgType;
	public String orgName;
	public boolean orgPhoneCode;
	public String orgPhoneNo;
	public String orgEmail;
	public String step1;
	public boolean chkMandatoryFieldsS1;
	public String step2;
	public String maxUsers;
	public boolean roles;
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
	public String steps;
	public boolean getParentSiteID;
	
	public static String BulkSitefileName = "BulkSiteTemplate.xlsx";
	public static String BulkSitefileName1 = "BulkSiteTemplate1.xlsx";
	public static String BulkSitefileNameHierarchy = "BulkSiteTemplate1.xlsx";

	public OrgModel (boolean _isOpenPopup, boolean _orgType, String _orgName, boolean _orgPhoneCode, String _orgPhoneNo, String _orgEmail, String _step1, boolean _chkMandatoryFieldsS1,
			String _step2, String _maxUsers, boolean _roles, boolean _chkMandatoryFieldsS2, String _testCaseTitle, String _testCaseDesc, String _passScenario, String _failScenario)
	{
		this.isOpenPopUp = _isOpenPopup;
		this.orgType = _orgType;
		this.orgName = _orgName;
		this.orgPhoneCode = _orgPhoneCode;
		this.orgPhoneNo = _orgPhoneNo;
		this.orgEmail = _orgEmail;
		this.step1 = _step1;
		this.chkMandatoryFieldsS1 = _chkMandatoryFieldsS1;

		this.step2 = _step2;
		this.maxUsers = _maxUsers;
		this.roles = _roles;
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

	public OrgModel() {
	}

	public static ArrayList<OrgModel> BulkSiteFillData() {
		ArrayList<OrgModel> lstBulkSiteUploadModel = new ArrayList<OrgModel>();
		OrgModel objTmp = new OrgModel();
		ReportFilters objFilter = new ReportFilters();
		
		objTmp.testCaseTitle = "AN-OM-36: Verify that user cannot upload Bulk Sites with empty rows";
		objTmp.testCaseDesc = "This test case will verify that user cannot upload Bulk Sites with empty rows ";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 1, 2, 0, 1, 2,0,1,2));
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1,1,1,2,2,2,3,3,3));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("","","","","","","","",""));
		objTmp.getParentSiteID = false;
		objTmp.fileName =BulkSitefileName;
		objTmp.AlertMessage = "Uploaded file contains no rows.";
		objTmp.passStep = "User was not able to upload Bulk Sites with empty rows successfully";
		objTmp.failStep = "User was able to upload Bulk Sites with empty rows";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
		
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-37: Verify that user cannot upload Bulk Sites leaving Parent Site empty";
		objTmp.testCaseDesc = "This test case will verify that user cannot upload Bulk Sites leaving Parent Site empty";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0,1,2));
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1,1,1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("", "Region", "TestSite1"));
		objTmp.getParentSiteID = false;
		objTmp.fileName =BulkSitefileName;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.passStep = "User was not able to upload Bulk Sites leaving Parent Site empty successfully";
		objTmp.failStep = "User was able to upload Bulk Sites leaving Parent Site empty";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
		
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-38: Verify that user cannot upload Bulk Sites leaving Site Type empty";
		objTmp.testCaseDesc = "This test case will verify that user cannot upload Bulk Sites leaving Site Type empty";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1,2));
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1,1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("", "TestSite1"));
		objTmp.getParentSiteID = true;
		objTmp.fileName =BulkSitefileName;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.passStep = "User was not able to upload Bulk Sites leaving Site Type empty successfully";
		objTmp.failStep = "User was able to upload Bulk Sites leaving Site Type empty";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);

		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-39: Verify that user cannot upload Bulk Sites with invalid Site Type";
		objTmp.testCaseDesc = "This test case will verify that user cannot upload Bulk Sites with invalid Site Type empty";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1,2));
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1,1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Home", "TestSite1"));
		objTmp.getParentSiteID = true;
		objTmp.fileName =BulkSitefileName;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.passStep = "User was not able to upload Bulk Sites leaving Site Type empty successfully";
		objTmp.failStep = "User was able to upload Bulk Sites leaving Site Type empty";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
		
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-40: Verify that user cannot upload Bulk Sites leaving Site Name empty";
		objTmp.testCaseDesc = "This test case will verify that user cannot upload Bulk Sites leaving Site Name empty";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1,2));
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1,1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Region", ""));
		objTmp.getParentSiteID = true;
		objTmp.fileName = BulkSitefileName;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.passStep = "User was not able to upload Bulk Sites leaving Site Name empty successfully";
		objTmp.failStep = "User was able to upload Bulk Sites leaving Site Name empty";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
		

		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-36: Verify that user cannot upload Bulk Sites with duplicate Site Name";
		objTmp.testCaseDesc = "This test case will verify that user cannot upload Bulk Sites with duplicate Site Name";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1, 2, 1, 2));
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1, 1, 2, 2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Region", "Sub Region", "TestRegion", "TestRegion"));
		objTmp.getParentSiteID = true;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.passStep = "User was not able to upload Bulk Sites with duplicate Site Name successfully";
		objTmp.failStep = "User was able to upload Bulk Sites with duplicate Site Name";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
		
	
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-36: Verify that user cannot upload Bulk Sites with country other than USA";
		objTmp.testCaseDesc = "This test case will verify that user cannot upload Bulk Sites with country other than USA";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1, 1, 1, 2, 2,2));
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1, 2, 4,0, 1,2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Region", "TestRegion", "Pakistan", "", "", ""));
		objTmp.getParentSiteID = true;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.passStep = "User was not able to upload Bulk Sites with country other than USA successfully";
		objTmp.failStep = "User was able to upload Bulk Sites with country other than USA";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
				
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-36: Verify that user cannot upload Bulk Sites with value in not in decimal number for latitude and longitude";
		objTmp.testCaseDesc = "This test case will verify that user cannot upload Bulk Sites with country other than USA";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1));
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1, 2, 4, 8, 9));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Region", "TestRegion", "", "long", "lat"));
		objTmp.getParentSiteID = true;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.passStep = "User was not able to upload Bulk Sites with value in not in decimal number for latitude and longitude successfully";
		objTmp.failStep = "User was able to upload Bulk Sites with value in not in decimal number for latitude and longitude";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
				
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-36: Verify that user cannot upload Bulk Sites hierarchy in incorrect order";
		objTmp.testCaseDesc = "This test case will verify that user cannot upload Bulk Sites hierarchy in incorrect order";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1, 2, 1, 2, 1, 1));
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1, 1, 2, 2, 8, 9));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Region", "Complex", "TestRegion", "TestSubRegion", "", ""));
		objTmp.getParentSiteID = true;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.passStep = "User was not able to upload Bulk Sites hierarchy in incorrect order successfully";
		objTmp.failStep = "User was able to upload Bulk Sites hierarchy in incorrect order";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
			
		objTmp = new OrgModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-36: Verify that user cannot upload Bulk Sites hierarchy in incorrect order";
		objTmp.testCaseDesc = "This test case will verify that user cannot upload Bulk Sites hierarchy in incorrect order";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1, 2, 3, 1, 2, 3));
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1, 1, 1, 2, 2, 2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Region", "Sub Region", "Complex", "TestRegion", "TestSubRegion", "TestComplex"));
		objTmp.getParentSiteID = true;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.passStep = "User was not able to upload Bulk Sites hierarchy in incorrect order successfully";
		objTmp.failStep = "User was able to upload Bulk Sites hierarchy in incorrect order";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
		
		return lstBulkSiteUploadModel;
	}

}