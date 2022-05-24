package Models;

import java.util.ArrayList;
import java.util.Arrays;

import Test.Ancera.Test_Elements;

public class UserModel {

	public boolean isOpenPopUp;
	public String userFirstName;
	public String userLastName;
	public boolean userPhoneCode;
	public String userPhoneNo;
	public boolean chkMandatoryFieldsS1;
	public String userEmail;
	public boolean userOrganizationType;
	public boolean userOrganization;
	public boolean chkMandatoryFieldsS2;
	public boolean userAsssignedRole;
	public boolean chkMandatoryFieldsS3;
	public String step1;
	public String passScenario;
	public String failScenario;
	public String testCaseTitle;
	public String testCaseDesc;
	public ArrayList<ReportFilters> lstFilters;
	public String AlertMessage;
	public String userType;
	public String sortType;
	public boolean search;
	public String TestCaseName;
	public String TestCaseDescription;
	public String TestCaseNameSearch;
	public String TestCaseDescriptionSearch;
	public boolean startWith;
	public boolean endsWith;
	public boolean contains;
	public boolean viewAccess;
	public boolean unviewAccess;
	public boolean errorCase;
	public String steps;
	public String input;
	
	public static String applyFilterTitle = "Verify user can select checkbox from ";
	public static String applyFilterDesc = "This test case will verify that user can select checkbox from ";
	public static String filterIndicatorTitle = "Verify user can apply filter and table displays relevant results on applying ";
	public static String filterIndicatorDesc = "This test case will verify that user can apply filter and table displays relevant results on applying ";

	public UserModel (String _userFirstName, String _userLastName, boolean _userPhoneCode, String _userPhoneNo, 
			boolean _chkMandatoryFieldsS1, String _userEmail, boolean _userOrganizationType, boolean _userOrganization, boolean _chkMandatoryFieldsS2, 
			boolean _userAssignedRole, boolean _chkMandatoryFieldsS3, String _step1, String _passScenario, String _failScenario, String _testCaseTitle, String _testCaseDesc )

	{
		this.userFirstName = _userFirstName;
		this.userLastName = _userLastName;
		this.userPhoneCode = _userPhoneCode;
		this.userPhoneNo = _userPhoneNo;
		this.chkMandatoryFieldsS1 = _chkMandatoryFieldsS1;
		this.userEmail = _userEmail;
		this.userOrganizationType = _userOrganizationType;
		this.userOrganization = _userOrganization;
		this.chkMandatoryFieldsS2 = _chkMandatoryFieldsS2;
		this.userAsssignedRole = _userAssignedRole;
		this.chkMandatoryFieldsS3 = _chkMandatoryFieldsS3;
		this.step1 = _step1;
		this.passScenario = _passScenario;
		this.failScenario =_failScenario;
		this.testCaseTitle = _testCaseTitle;
		this.testCaseDesc = _testCaseDesc;
	}


	public String userSearchName;
	public String userSearchTestCaseTitle;
	public String userSearchTestCaseDesc;
	public String userSearchResult;
	public String userSearchStep;
	public String userSearchPassScenario;
	public String userSearchFailScenario;

	public UserModel (String _userSearchName, String _userSearchTestCaseTitle, String _userSearchTestCaseDesc, String _userSearchResult, String _userSearchStep,
			String _userSearchPassScenario, String _userSearchFaiScenario)
	{

		this.userSearchName = _userSearchName;
		this.userSearchTestCaseTitle = _userSearchTestCaseTitle;
		this.userSearchTestCaseDesc = _userSearchTestCaseDesc;
		this.userSearchResult = _userSearchResult;
		this.userSearchStep = _userSearchStep;
		this.userSearchPassScenario = _userSearchPassScenario;
		this.userSearchFailScenario = _userSearchFaiScenario;

	}
	
	public UserModel() {
	}
	
	
	public static ArrayList<UserModel> FillData() {
		ArrayList<UserModel> lstUserModel = new ArrayList<UserModel>();
		UserModel objTmp; 
		ReportFilters  objFilter;

		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "First Name Filter";
		objTmp.TestCaseName = "AN-UM-27: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-UM-28: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList(Test_Elements.userFirstName));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("3"));
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp); 
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Last Name Filter";
		objTmp.TestCaseName = "AN-UM-29: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-UM-30: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList(Test_Elements.userLastName));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("3"));
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp); 

//		objTmp = new UserModel();
//		objFilter = new ReportFilters();
//		objFilter.FilterName = "Mobile Number Filter";
//		objTmp.TestCaseName = "AN-UM-23: "+applyFilterTitle+objFilter.FilterName;
//		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
//		objTmp.TestCaseNameSearch = "AN-UM-24: "+filterIndicatorTitle+objFilter.FilterName;
//		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter = new ReportFilters();
//		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList(Test_Elements.userMobileNo));
//		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("4"));
//		objTmp.lstFilters.add(objFilter);
//		lstUserModel.add(objTmp); 
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Email Filter";
		objTmp.TestCaseName = "AN-UM-31: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-UM-32: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList(Test_Elements.userEmail));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("3"));
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp); 
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Organization Type Filter";
		objTmp.TestCaseName = "AN-UM-27: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-UM-28: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList(Test_Elements.userOrgType));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("3"));
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp); 
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Organization Filter";
		objTmp.TestCaseName = "AN-UM-33: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-UM-34: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList(Test_Elements.userOrg));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("3"));
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp); 
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Two Filters";
		objTmp.TestCaseName = "AN-UM-35: Verify user can apply 2 filters at same time";
		objTmp.TestCaseDescription = "This testcase will verify that user can apply 2 filters at same time";
		objTmp.TestCaseNameSearch = "AN-UM-36: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList(Test_Elements.userOrgType,Test_Elements.userFirstName));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2", "3"));
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		return lstUserModel;
		}
	
	
	public static ArrayList<UserModel> Wildcard() {
		ArrayList<UserModel> lstUserModel = new ArrayList<UserModel>();
		UserModel objTmp; 
		ReportFilters  objFilter;

		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "First Name Filter";
		objTmp.TestCaseName = "AN-UM-37: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.userFirstName;
		objTmp.startWith = true;
		objTmp.contains = false;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.userFirstNameCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "First Name Filter";
		objTmp.TestCaseName = "AN-UM-38: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.userFirstName;
		objTmp.startWith = false;
		objTmp.contains = true;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.userFirstNameCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "First Name Filter";
		objTmp.TestCaseName = "AN-UM-39: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.userFirstName;
		objTmp.startWith = false;
		objTmp.contains = false;
		objTmp.endsWith = true;
		objFilter.ColumnID = Test_Elements.userFirstNameCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Last Name Filter";
		objTmp.TestCaseName = "AN-UM-40: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.userLastName;
		objTmp.startWith = true;
		objTmp.contains = false;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.userLastNameCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Last Name Filter";
		objTmp.TestCaseName = "AN-UM-41: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.userLastName;
		objTmp.startWith = false;
		objTmp.contains = true;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.userLastNameCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Last Name Filter";
		objTmp.TestCaseName = "AN-UM-42: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.userLastName;
		objTmp.startWith = false;
		objTmp.contains = false;
		objTmp.endsWith = true;
		objFilter.ColumnID = Test_Elements.userLastNameCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Mobile Number Filter";
		objTmp.TestCaseName = "AN-UM-43: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.userMobileNo;
		objTmp.startWith = true;
		objTmp.contains = false;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.userMobileNoCol;
		objTmp.input = "+93";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("+93", "+93"));
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Mobile Number Filter";
		objTmp.TestCaseName = "AN-UM-44: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.userMobileNo;
		objTmp.startWith = false;
		objTmp.contains = true;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.userMobileNoCol;
		objTmp.input = "1";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1", "1"));
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Mobile Number Filter";
		objTmp.TestCaseName = "AN-UM-45: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.userMobileNo;
		objTmp.startWith = false;
		objTmp.contains = false;
		objTmp.endsWith = true;
		objFilter.ColumnID = Test_Elements.userMobileNoCol;
		objTmp.input = "2";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2", "2"));
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Email Filter";
		objTmp.TestCaseName = "AN-UM-46: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.userEmail;
		objTmp.startWith = true;
		objTmp.contains = false;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.userEmailCol;
		objTmp.input = "jun";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("jun", "jun"));
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Email Filter";
		objTmp.TestCaseName = "AN-UM-47: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.userEmail;
		objTmp.startWith = false;
		objTmp.contains = true;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.userEmailCol;
		objTmp.input = "ancera";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("ancera", "ancera"));
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Email Filter";
		objTmp.TestCaseName = "AN-UM-48: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.userEmail;
		objTmp.startWith = false;
		objTmp.contains = true;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.userEmailCol;
		objTmp.input = ".pk";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList(".pk", ".pk"));
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Organization Type Filter";
		objTmp.TestCaseName = "AN-UM-49: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.userOrgType;
		objTmp.startWith = true;
		objTmp.contains = false;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.userOrgTypeCol;
		objTmp.input = "cl";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Cl", "cl"));
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Organization Type Filter";
		objTmp.TestCaseName = "AN-UM-50: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.userOrgType;
		objTmp.startWith = false;
		objTmp.contains = true;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.userOrgTypeCol;
		objTmp.input = "ie";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("ie", "ie"));
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Organization Type Filter";
		objTmp.TestCaseName = "AN-UM-51: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.userOrgType;
		objTmp.startWith = false;
		objTmp.contains = false;
		objTmp.endsWith = true;
		objFilter.ColumnID = Test_Elements.userOrgTypeCol;
		objTmp.input = "a";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		return lstUserModel;
	}
	
	
	
	public static ArrayList<UserModel> sorting() {
		ArrayList<UserModel> lstUserModel = new ArrayList<UserModel>();
		UserModel objTmp = new UserModel();
		ReportFilters objFilter ;
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "First Name";
		objTmp.testCaseTitle = "AN-UM-52: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.testCaseDesc = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.SortFilter+""+Test_Elements.userFirstName;           
		objFilter.count = Test_Elements.userFirstNameCol;
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Last Name";
		objTmp.testCaseTitle = "AN-UM-53: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.testCaseDesc = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.SortFilter+""+Test_Elements.userLastName;           
		objFilter.count = Test_Elements.userLastNameCol;
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Mobile Number";
		objTmp.testCaseTitle = "AN-UM-54: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.testCaseDesc = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.SortFilter+""+Test_Elements.userMobileNo;           
		objFilter.count = Test_Elements.userMobileNoCol;
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Email";
		objTmp.testCaseTitle = "AN-UM-57: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.testCaseDesc = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.SortFilter+""+Test_Elements.userEmail;           
		objFilter.count = Test_Elements.userEmailCol;
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Organazition Type";
		objTmp.testCaseTitle = "AN-UM-55: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.testCaseDesc = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.SortFilter+""+Test_Elements.userOrgType;           
		objFilter.count = Test_Elements.userOrgTypeCol;
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Organazition";
		objTmp.testCaseTitle = "AN-UM-56: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.testCaseDesc = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.SortFilter+""+Test_Elements.userOrg;           
		objFilter.count = Test_Elements.userOrgCol;
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Role";
		objTmp.testCaseTitle = "AN-UM-57: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.testCaseDesc = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.SortFilter+""+Test_Elements.userRole;           
		objFilter.count = Test_Elements.userRoleCol;
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Reporting";
		objTmp.testCaseTitle = "AN-UM-58: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.testCaseDesc = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.SortFilter+""+Test_Elements.userReporting;           
		objFilter.count = Test_Elements.userReportingCol;
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site Access";
		objTmp.testCaseTitle = "AN-UM-59: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.testCaseDesc = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.SortFilter+""+Test_Elements.userSiteAccess;           
		objFilter.count = Test_Elements.userSiteAccessCol;
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		return lstUserModel;
	}
	
	
	public static ArrayList<UserModel> Lock() {
		ArrayList<UserModel> lstUserModel = new ArrayList<UserModel>();
		UserModel objTmp = new UserModel();
		ReportFilters objFilter ;
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "First Name Filter";
		objTmp.TestCaseName = "AN-UM-60: Verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.userFirstName;
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Last Name Filter";
		objTmp.TestCaseName = "AN-UM-61: Verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.userLastName;
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
//		objTmp = new UserModel();
//		objFilter = new ReportFilters();
//		objFilter.FilterName = "Mobile No Filter";
//		objTmp.TestCaseName = "AN-UM-50: Verify Lock Filter functionality on "+objFilter.FilterName;
//		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter.FilterID = Test_Elements.userMobileNo;
//		objTmp.lstFilters.add(objFilter);
//		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Email Filter";
		objTmp.TestCaseName = "AN-UM-62: Verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.userEmail;
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Org Type Filter";
		objTmp.TestCaseName = "AN-UM-63: Verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.userOrgType;
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Org Filter";
		objTmp.TestCaseName = "AN-UM-64: Verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.userOrg;
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		return lstUserModel;
		
	}
}
