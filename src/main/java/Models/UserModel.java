package Models;

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
}
