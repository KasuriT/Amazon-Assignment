package Models;

public class APIModel {


	public String runId;
	public String checksum;
	public String fileName;
	public String fileType;
	public String file;
	public String fileJson;
	public String improc;
	public boolean search;
	public String testCaseTitle;
	public String testCaseDesc;
	public String step;
	public String passScenario;
	public String failScenario;
	public String testCaseTitleIngestion;
	public String testCaseDescIngestion;

	public APIModel (String _runId, String _checksum,  String _fileName, String _fileType, String _file, String _fileJson, String _improc, 
			 boolean _search, String _testCaseTitle, String _testCaseDesc, String _step, String _passScenario, String _failScenario, 
			 String _testCaseTitleIngestion, String _testCaseDescIngestion)

	{
		this.runId = _runId;
		this.checksum = _checksum;
		this.fileName = _fileName;
		this.fileType = _fileType;
		this.file = _file;
		this.fileJson = _fileJson;
		this.improc = _improc;
		this.search = _search;
		this.testCaseTitle = _testCaseTitle;
		this.testCaseDesc = _testCaseDesc;
		this.step = _step;
		this.passScenario = _passScenario;
		this.failScenario = _failScenario;
		this.testCaseTitleIngestion = _testCaseTitleIngestion;
		this.testCaseDescIngestion = _testCaseDescIngestion;
	}


}
