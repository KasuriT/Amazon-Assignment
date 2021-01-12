package Models;

public class AgreementManagementModel {

	public String testCaseTitle;
	public String testCaseDescription;
	public String passMessage;
	public String failMessage;
	public String fileName;
	public String fileType;
	public String alertMessage;
	public String selectCheckbox;
	public String checkboxInput;
	public String stepName;
	public String testCaseTitleUser;
	public String testCaseDescriptionUser;
	public String userStatus;
	
	
	
	public AgreementManagementModel (String _testCaseTitle, String _testCaseDescription, String _passMessage, String _failMessage, String _fileName, 
			String _fileType, String _alertMessage) {
		
		this.testCaseTitle = _testCaseTitle;
		this.testCaseDescription = _testCaseDescription;
		this.passMessage = _passMessage;
		this.failMessage = _failMessage;
		this.fileName = _fileName;
		this.fileType = _fileType;
		this.alertMessage = _alertMessage;
	}


	public AgreementManagementModel (String _selectCheckbox, String _chekboxInput, String _testCaseTitle, String _testCaseDescription, String _stepName, String _testCaseTitleUser, String _testCaseDescriptionUser, String _passMessage, String _failMessage) {
		this.selectCheckbox = _selectCheckbox;
		this.checkboxInput = _chekboxInput;
		this.testCaseTitle = _testCaseTitle;
		this.testCaseDescription = _testCaseDescription;
		this.stepName = _stepName;
		this.testCaseTitleUser = _testCaseTitleUser;
		this.testCaseDescriptionUser = _testCaseDescriptionUser;
		this.passMessage = _passMessage;
		this.failMessage = _failMessage;
	}
	
	
	public AgreementManagementModel (String _userStatus, String _testCaseTitle, String _testCaseDescription, String _passMessage, String _failMessage) {

		this.userStatus = _userStatus;
		this.testCaseTitle = _testCaseTitle;
		this.testCaseDescription = _testCaseDescription;
		this.passMessage = _passMessage;
		this.failMessage = _failMessage;
		
	}
	
	
}