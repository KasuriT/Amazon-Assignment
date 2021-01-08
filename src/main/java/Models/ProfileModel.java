package Models;

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
	
	public ProfileModel( String _url, String _testCase, String _testCaseDescription, String _stepPage)
	
	{
		this.url = _url;
		this.testCaseNavigate = _testCase;
		this.testCaseDescription = _testCaseDescription;
		this.stepPage = _stepPage;
}
	
	
	
	public ProfileModel(Boolean _isOpenPage, String _firstName, String _lastName, Boolean _cellCode, String _cellNo, Boolean _phoneCode, String _phoneNo, String _testCaseMandatoryCheck, Boolean _CheckS1)
	
	{
		this.isOpenPage = _isOpenPage;
		this.firstName = _firstName;
		this.lastName = _lastName;
		this.cellCode = _cellCode;
		this.cellNo = _cellNo;
		this.phoneCode = _phoneCode;
		this.phoneNo = _phoneNo;
		this.testCaseMandatoryCheck = _testCaseMandatoryCheck;
		this.CheckS1 = _CheckS1;
}
	
	
	
}
