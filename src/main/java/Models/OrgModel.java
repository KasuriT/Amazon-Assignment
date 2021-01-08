package Models;

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

	public OrgModel (String _orgSearchName, String _orgSearchTestCaseTitle, String _orgSearchTestCaseDesc, String _orgSearchResult, String _orgSearchStep,
			String _orgSearchPassScenario, String _orgSearchFaiScenario)
	{

		this.orgSearchName = _orgSearchName;
		this.orgSearchTestCaseTitle = _orgSearchTestCaseTitle;
		this.orgSearchTestCaseDesc = _orgSearchTestCaseDesc;
		this.orgSearchResult = _orgSearchResult;
		this.orgSearchStep = _orgSearchStep;
		this.orgSearchPassScenario = _orgSearchPassScenario;
		this.orgSearchFailScenario = _orgSearchFaiScenario;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}