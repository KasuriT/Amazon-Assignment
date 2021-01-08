package Models;

public class DataTemplateModel {


	public boolean isOpenPopUp;
	public String dtmName;
	public String dtmDesc;
	public String step1;
	public boolean chkMandatoryFieldsS1;
	public String step2;
	public boolean chkAlert;
	
	public boolean chkClm;
	public String clmName;
	public boolean clmType;
	public String clmLength;	
	public boolean chkS2;
	
	public boolean verifyClm;
	public String testcaseTitle;
	public String testcaseDesc;
	public String passScenario;
	public String failScenario;
	

	public DataTemplateModel (boolean _isOpenPopup, String _dtmName, String _dtmDesc, String _step1, boolean _chkMandatoryFieldsS1,
			String _step2, boolean _chkAlert, boolean _chkClm, String _clmName, boolean _clmType, String _clmLength, String _testcaseTitle, String _testcaseDesc, String _passScenario, String _failScenario, boolean _chkS2, boolean _verifyClm )
	
	{
		this.isOpenPopUp = _isOpenPopup;
		this.dtmName = _dtmName;
		this.dtmDesc = _dtmDesc;
		this.step1 = _step1;
		this.chkMandatoryFieldsS1 = _chkMandatoryFieldsS1;
		this.step2 = _step2;
		this.chkAlert = _chkAlert;
		
		this.chkClm = _chkClm;
		this.clmName = _clmName;
		this.clmType = _clmType;
		this.clmLength = _clmLength;
		
		this.testcaseTitle = _testcaseTitle;
		this.testcaseDesc = _testcaseDesc;
		this.passScenario = _passScenario;
		this.failScenario =_failScenario;
		this.chkS2 = _chkS2;
		this.verifyClm = _verifyClm;
}
	
	
	public boolean updateTemplate;
	public String updateName;
	public String updateDesc;
	public boolean updateColumn;
	public String updateClmName;
	public String updateFieldLength;
	public String updateTestCase;
	
	
	
	public DataTemplateModel (boolean _updateTemplate, String _updateName, String _updateDesc, boolean _updateColumn, String _updateClmName, String _updateFieldsLength, String _updateTestCase)
	{
	
		this.updateTemplate = _updateTemplate;
		this.updateName = _updateName;
		this.updateDesc = _updateDesc;
		this.updateColumn = _updateColumn;
		this.updateClmName = _updateClmName;
		this.updateFieldLength = _updateFieldsLength;
		this.updateTestCase = _updateTestCase;
		
		
		
	}
	
	
	
	
	
	
}