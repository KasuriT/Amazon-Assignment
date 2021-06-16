package Models;

import java.util.ArrayList;

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

	public UserModel (boolean _isOpenPopup, String _userFirstName, String _userLastName, boolean _userPhoneCode, String _userPhoneNo, 
			boolean _chkMandatoryFieldsS1, String _userEmail, boolean _userOrganizationType, boolean _userOrganization, boolean _chkMandatoryFieldsS2, 
			boolean _userAssignedRole, boolean _chkMandatoryFieldsS3, String _step1, String _passScenario, String _failScenario, String _testCaseTitle, String _testCaseDesc )

	{
		this.isOpenPopUp = _isOpenPopup;
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
		UserModel objTmp = new UserModel();
		
		ReportFilters objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-53: Verify sorting functionality on First Name for All Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on First Name for All Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-all";
		objTmp.sortType = "sort-first-name";
		objTmp.search = false;
		objTmp.step1 = "Select All Users from tab above and click on First-Name column";
		objTmp.passScenario = "Users sorted for firstname for All Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for firstname for All Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-54: Verify sorting functionality on Last Name for All Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Last Name for All Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-all";
		objTmp.sortType = "sort-last-name";
		objTmp.search = false;
		objTmp.step1 = "Select All Users from tab above and click on Last-Name column";
		objTmp.passScenario = "Users sorted for lastname for All Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for lastname for All Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-55: Verify sorting functionality on Cell# for All Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Cell# for All Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-all";
		objTmp.sortType = "sort-cell";
		objTmp.search = false;
		objTmp.step1 = "Select All Users from tab above and click on Cell # column";
		objTmp.passScenario = "Users sorted for Cell # for All Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for Cell # for All Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-56: Verify sorting functionality on Email for All Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Email for All Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-all";
		objTmp.sortType = "sort-email";
		objTmp.search = false;
		objTmp.step1 = "Select All Users from tab above and click on Email column";
		objTmp.passScenario = "Users sorted for Email for All Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for Email for All Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-57: Verify sorting functionality on Organization Type for All Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Organization Type for All Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-all";
		objTmp.sortType = "sort-orgn-type";
		objTmp.search = false;
		objTmp.step1 = "Select All Users from tab above and click on Organization Type column";
		objTmp.passScenario = "Users sorted for Organization Type for All Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for firstname for All Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-58: Verify sorting functionality on Organization for All User type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Organization for All User type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-all";
		objTmp.sortType = "sort-orgn";
		objTmp.search = false;
		objTmp.step1 = "Select All Users from tab above and click on Organization column";
		objTmp.passScenario = "Users sorted for Organization for All Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for Organization for All Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-59: Verify sorting functionality on First Name for Ancera Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on First Name for Ancera Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-1";
		objTmp.sortType = "sort-first-name";
		objTmp.search = false;
		objTmp.step1 = "Select Ancera Users from tab above and click on First-Name column";
		objTmp.passScenario = "Users sorted for firstname for Ancera Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for firstname for Ancera Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-60: Verify sorting functionality on Last Name for Ancera Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Last Name for Ancera Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-1";
		objTmp.sortType = "sort-last-name";
		objTmp.search = false;
		objTmp.step1 = "Select Ancera Users from tab above and click on Last-Name column";
		objTmp.passScenario = "Users sorted for lastname for Ancera Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for lastname for Ancera Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-61: Verify sorting functionality on Cell # for Ancera Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Cell # for Ancera Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-1";
		objTmp.sortType = "sort-cell";
		objTmp.search = false;
		objTmp.step1 = "Select Ancera Users from tab above and click on Cell # column";
		objTmp.passScenario = "Users sorted for Cell # for Ancera Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for Cell # for Ancera Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-62: Verify sorting functionality on Email for Ancera Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Email for Ancera Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-1";
		objTmp.sortType = "sort-email";
		objTmp.search = false;
		objTmp.step1 = "Select Ancera Users from tab above and click on Email column";
		objTmp.passScenario = "Users sorted for Email for Ancera Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for Email for Ancera Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-63: Verify sorting functionality on Organization Type for Ancera Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Organization Type for Ancera Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-1";
		objTmp.sortType = "sort-orgn-type";
		objTmp.search = false;
		objTmp.step1 = "Select Ancera Users from tab above and click on Organization Type column";
		objTmp.passScenario = "Users sorted for Organization Type for Ancera Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for firstname for Ancera Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-64: Verify sorting functionality on Organization for All User type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Organization for All User type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-1";
		objTmp.sortType = "sort-orgn";
		objTmp.search = false;
		objTmp.step1 = "Select Ancera Users from tab above and click on Organization column";
		objTmp.passScenario = "Users sorted for Organization for Ancera Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for Organization for Ancera Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-65: Verify sorting functionality on First Name for Client Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on First Name for Client Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-2";
		objTmp.sortType = "sort-first-name";
		objTmp.search = false;
		objTmp.step1 = "Select Client Users from tab above and click on First-Name column";
		objTmp.passScenario = "Users sorted for firstname for Client Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for firstname for Client Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-66: Verify sorting functionality on Last Name for Client Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Last Name for Client Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-2";
		objTmp.sortType = "sort-last-name";
		objTmp.search = false;
		objTmp.step1 = "Select Client Users from tab above and click on Last-Name column";
		objTmp.passScenario = "Users sorted for lastname for Client Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for lastname for Client Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-67: Verify sorting functionality on Cell # for Client Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Cell # for Client Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-2";
		objTmp.sortType = "sort-cell";
		objTmp.search = false;
		objTmp.step1 = "Select Client Users from tab above and click on Cell # column";
		objTmp.passScenario = "Users sorted for Cell # for Client Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for Cell # for Client Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-68: Verify sorting functionality on Email for Client Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Email for Client Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-2";
		objTmp.sortType = "sort-email";
		objTmp.search = false;
		objTmp.step1 = "Select Client Users from tab above and click on Email column";
		objTmp.passScenario = "Users sorted for Email for Client Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for Email for Client Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-69: Verify sorting functionality on Organization Type for Client Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Organization Type for Client Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-2";
		objTmp.sortType = "sort-orgn-type";
		objTmp.search = false;
		objTmp.step1 = "Select Client Users from tab above and click on Organization Type column";
		objTmp.passScenario = "Users sorted for Organization Type for Client Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for firstname for Client Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-70: Verify sorting functionality on Organization for All User type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Organization for All User type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-2";
		objTmp.sortType = "sort-orgn";
		objTmp.search = false;
		objTmp.step1 = "Select Client Users from tab above and click on Organization column";
		objTmp.passScenario = "Users sorted for Organization for Client Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for Organization for Client Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-71: Verify sorting functionality on First Name for Partner Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on First Name for Partner Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-3";
		objTmp.sortType = "sort-first-name";
		objTmp.search = false;
		objTmp.step1 = "Select Partner Users from tab above and click on First-Name column";
		objTmp.passScenario = "Users sorted for firstname for Partner Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for firstname for Partner Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-72: Verify sorting functionality on Last Name for Partner Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Last Name for Partner Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-3";
		objTmp.sortType = "sort-last-name";
		objTmp.search = false;
		objTmp.step1 = "Select Partner Users from tab above and click on Last-Name column";
		objTmp.passScenario = "Users sorted for lastname for Partner Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for lastname for Partner Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-73: Verify sorting functionality on Cell # for Partner Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Cell # for Partner Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-3";
		objTmp.sortType = "sort-cell";
		objTmp.search = false;
		objTmp.step1 = "Select Partner Users from tab above and click on Cell # column";
		objTmp.passScenario = "Users sorted for Cell # for Partner Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for Cell # for Partner Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-74: Verify sorting functionality on Email for Partner Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Email for Partner Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-3";
		objTmp.sortType = "sort-email";
		objTmp.search = false;
		objTmp.step1 = "Select Partner Users from tab above and click on Email column";
		objTmp.passScenario = "Users sorted for Email for Partner Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for Email for Partner Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-75: Verify sorting functionality on Organization Type for Partner Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Organization Type for Partner Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-3";
		objTmp.sortType = "sort-orgn-type";
		objTmp.search = false;
		objTmp.step1 = "Select Partner Users from tab above and click on Organization Type column";
		objTmp.passScenario = "Users sorted for Organization Type for Partner Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for firstname for Partner Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-76: Verify sorting functionality on Organization for All User type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Organization for All User type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-3";
		objTmp.sortType = "sort-orgn";
		objTmp.search = false;
		objTmp.step1 = "Select Partner Users from tab above and click on Organization column";
		objTmp.passScenario = "Users sorted for Organization for Partner Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for Organization for Partner Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		
		objTmp = new UserModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-77: Verify sorting functionality on First Name for Consumer Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on First Name for Consumer Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-4";
		objTmp.sortType = "sort-first-name";
		objTmp.search = false;
		objTmp.step1 = "Select Consumer Users from tab above and click on First-Name column";
		objTmp.passScenario = "Users sorted for firstname for Consumer Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for firstname for Consumer Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-78: Verify sorting functionality on Last Name for Consumer Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Last Name for Consumer Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-4";
		objTmp.sortType = "sort-last-name";
		objTmp.search = false;
		objTmp.step1 = "Select Consumer Users from tab above and click on Last-Name column";
		objTmp.passScenario = "Users sorted for lastname for Consumer Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for lastname for Consumer Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-79: Verify sorting functionality on Cell # for Consumer Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Cell # for Consumer Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-4";
		objTmp.sortType = "sort-cell";
		objTmp.search = false;
		objTmp.step1 = "Select Consumer Users from tab above and click on Cell # column";
		objTmp.passScenario = "Users sorted for Cell # for Consumer Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for Cell # for Consumer Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-80: Verify sorting functionality on Email for Consumer Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Email for Consumer Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-4";
		objTmp.sortType = "sort-email";
		objTmp.search = false;
		objTmp.step1 = "Select Consumer Users from tab above and click on Email column";
		objTmp.passScenario = "Users sorted for Email for Consumer Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for Email for Consumer Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-81: Verify sorting functionality on Organization Type for Consumer Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Organization Type for Consumer Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-4";
		objTmp.sortType = "sort-orgn-type";
		objTmp.search = false;
		objTmp.step1 = "Select Consumer Users from tab above and click on Organization Type column";
		objTmp.passScenario = "Users sorted for Organization Type for Consumer Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for firstname for Consumer Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-82: Verify sorting functionality on Organization for All User type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Organization for All User type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-4";
		objTmp.sortType = "sort-orgn";
		objTmp.search = false;
		objTmp.step1 = "Select Consumer Users from tab above and click on Organization column";
		objTmp.passScenario = "Users sorted for Organization for Consumer Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for Organization for Consumer Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-88: Verify sorting functionality on First Name for All Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on First Name for All Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-all";
		objTmp.sortType = "sort-first-name";
		objTmp.search = true;
		objTmp.step1 = "Select All Users from tab above and click on First-Name column";
		objTmp.passScenario = "Users sorted for firstname for All Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for firstname for All Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-89: Verify sorting functionality on Last Name for All Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Last Name for All Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-all";
		objTmp.sortType = "sort-last-name";
		objTmp.search = false;
		objTmp.step1 = "Select All Users from tab above and click on Last-Name column";
		objTmp.passScenario = "Users sorted for lastname for All Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for lastname for All Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-90: Verify sorting functionality on Cell# for All Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Cell# for All Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-all";
		objTmp.sortType = "sort-cell";
		objTmp.search = false;
		objTmp.step1 = "Select All Users from tab above and click on Cell # column";
		objTmp.passScenario = "Users sorted for Cell # for All Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for Cell # for All Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-91: Verify sorting functionality on Email for All Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Email for All Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-all";
		objTmp.sortType = "sort-email";
		objTmp.search = false;
		objTmp.step1 = "Select All Users from tab above and click on Email column";
		objTmp.passScenario = "Users sorted for Email for All Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for Email for All Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-92: Verify sorting functionality on Organization Type for All Users type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Organization Type for All Users type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-all";
		objTmp.sortType = "sort-orgn-type";
		objTmp.search = false;
		objTmp.step1 = "Select All Users from tab above and click on Organization Type column";
		objTmp.passScenario = "Users sorted for Organization Type for All Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for firstname for All Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		objTmp = new UserModel();	
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-UM-93: Verify sorting functionality on Organization for All User type";
		objTmp.testCaseDesc = "This test case will verify sorting functionality on Organization for All User type";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.userType = "orgnType-all";
		objTmp.sortType = "sort-orgn";
		objTmp.search = false;
		objTmp.step1 = "Select All Users from tab above and click on Organization column";
		objTmp.passScenario = "Users sorted for Organization for All Users type successfully" ;
		objTmp.failScenario = "Users failed to sort for Organization for All Users type";
		objTmp.lstFilters.add(objFilter);
		lstUserModel.add(objTmp);
		
		return lstUserModel;

	}
}
