package Models;

import java.util.ArrayList;

public class TestModel {

	public ArrayList<OrgModel> orgSearchName;
	public String orgSearchTestCaseTitle;
	public String orgSearchTestCaseDesc;
	public String orgSearchResult;
	public String orgSearchStep;
	public String orgSearchPassScenario;
	public String orgSearchFailScenario;

	public TestModel (ArrayList<OrgModel> orgSearchName, String _orgSearchTestCaseTitle)
	{
		this.orgSearchName = orgSearchName;
		this.orgSearchTestCaseTitle = _orgSearchTestCaseTitle;

	}
	
}
