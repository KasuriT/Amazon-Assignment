package Models;

public class PiperSoftwareModel {

	public String testCaseTitle;
	public String testCaseDescription;
	public String passMessage;
	public String failMessage;
	public String fileName;
	public String fileType;
	public String alertMessage;
	public String improcButton;
	public String uploadButton;

	
	
	
	public PiperSoftwareModel (String _testCaseTitle, String _testCaseDescription, String _passMessage, String _failMessage, String _fileName, 
			String _fileType, String _alertMessage, String _improcButton, String _uploadButton) {
		
		this.testCaseTitle = _testCaseTitle;
		this.testCaseDescription = _testCaseDescription;
		this.passMessage = _passMessage;
		this.failMessage = _failMessage;
		this.fileName = _fileName;
		this.fileType = _fileType;
		this.alertMessage = _alertMessage;
		this.improcButton = _improcButton;
		this.uploadButton = _uploadButton;
	}
	
}
