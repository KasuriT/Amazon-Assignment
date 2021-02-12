package Models;

public class StartAssayModel {

	public String DateTime;
	public String InstrumentID;
	public String UserID;
	public String CartridgeID;
	public String RunID;
	public String PathogenName;
	public String testCaseTitle;
	public String testCaseDesc;
	public String step;
	public String testCaseTitleVerification;
	public String testCaseDescVerification;
	public String url;
	
	public StartAssayModel (String _DateTime, String _InstrumentID, String _UserID, String _CartridgeID, String _RunID, String _PathogenName, String _testCaseTitle, 
							String _testCaseDesc, String _step, String _testCaseTitleVerification, String _testCaseDescVerification, String _url) {
		
		this.DateTime = _DateTime;
		this.InstrumentID = _InstrumentID;
		this.UserID = _UserID;
		this.CartridgeID = _CartridgeID;
		this.RunID = _RunID;
		this.PathogenName = _PathogenName;
		this.testCaseTitle = _testCaseTitle;
		this.testCaseDesc = _testCaseDesc;
		this.step = _step;
		this.testCaseTitleVerification = _testCaseTitleVerification;
		this.testCaseDescVerification = _testCaseDescVerification;
		this.url = _url;
	}
	
}
