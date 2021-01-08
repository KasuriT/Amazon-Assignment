package Models;

public class RMModel {

	public boolean isOpenPopUp;
	public String rmName;
	public String rmDesc;
	public String step1;
	public boolean save;
	public String testcaseTitle;
	public String testcaseDesc;
	public boolean step;
	public String passScenario;
	public String failScenario;
	
	
	public String rgName;
	public String rgDesc;
	public boolean rgReport;
	public String rgstep1;
	public boolean rgsave;
	public String rgtestcaseTitle;
	public String rgtestcaseDesc;
	public boolean rgstep;
	public String rgpassScenario;
	public String rgfailScenario;
	
	
	public RMModel (boolean _isOpenPopup, String _rmName, String _rmDesc, String _step1, boolean _save, String _testcaseTitle, String _testcaseDesc, boolean _step, String _passScenario, String _failScenario )
	
	{
		this.isOpenPopUp = _isOpenPopup;
		this.rmName = _rmName;
		this.rmDesc = _rmDesc;
		this.step1 = _step1;
		this.save = _save;
		this.testcaseTitle = _testcaseTitle;
		this.testcaseDesc = _testcaseDesc;
		this.step = _step;
		this.passScenario = _passScenario;
		this.failScenario =_failScenario;
		

	}
	
	
	public RMModel (String _rgName, String _rgDesc, boolean _rgReports, String _rgstep1, boolean _rgsave, String _rgtestcaseTitle, String _rgtestcaseDesc, boolean _rgstep, String _rgpassScenario, String _rgfailScenario )
	
	{
		this.rgName = _rgName;
		this.rgDesc = _rgDesc;
		this.rgReport = _rgReports;
		this.rgstep1 = _rgstep1;
		this.rgsave = _rgsave;
		this.rgtestcaseTitle = _rgtestcaseTitle;
		this.rgtestcaseDesc = _rgtestcaseDesc;
		this.rgstep = _rgstep;
		this.rgpassScenario = _rgpassScenario;
		this.rgfailScenario =_rgfailScenario;

	}
	
	
	
	
	
	
	
	
	
	
	

	
	
}
