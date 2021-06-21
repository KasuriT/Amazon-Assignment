package Models;

import java.util.ArrayList;

public class InstallationRunModel {

	public String TestCaseName;
	public String TestCaseDescription;
	public String steps;
	public String passStep;
	public String failStep;
	public String dataLogOutcome;
	public String Outcome;
	public String file;
	public static String StatusCode;
	public boolean GreaterLesserCheck;
	public ArrayList<ReportFilters> lstFilters;
	
	public InstallationRunModel() {

	}
	
	public static ArrayList<InstallationRunModel> FillData() {
		ArrayList<InstallationRunModel> lstInstallationRunModel = new ArrayList<InstallationRunModel>();	
		InstallationRunModel objTmp = new InstallationRunModel();
		
		ReportFilters objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-IR-02: Verify QC Code is Fail against all lanes when Outcome is 'QCFAil' and StatusCode is '34'";
		objTmp.TestCaseDescription = "This test case will verify QC Code is Fail against all lanes when Outcome is 'QCFAil' and StatusCode is '34'";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.MinMean = "10";
		objFilter.MaxMean = "1500";
		objFilter.MinStd = "100";
		objFilter.MaxStd = "1000";
		objTmp.dataLogOutcome = "FAIL";
		objTmp.steps = "Run ingestion with Outcome as QCFail and StatusCode 34";
		objTmp.passStep = "QC Code was Fail against all lanes when Outcome was 'QCFAil' and StatusCode was '34' successfully";
		objTmp.failStep = "QC Code did not Fail against all lanes when Outcome was 'QCFAil' and StatusCode was '34'";
		objTmp.GreaterLesserCheck = false;
		objTmp.lstFilters.add(objFilter);
		lstInstallationRunModel.add(objTmp);
	
		objTmp = new InstallationRunModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-IR-03: Verify QC Code is Fail against all lanes when Mean is out of range";
		objTmp.TestCaseDescription = "This test case will verify QC Code is Fail against all lanes when Mean is out of range";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.MinMean = "10";
		objFilter.MaxMean = "1000";
		objFilter.MinStd = "100";
		objFilter.MaxStd = "1000";
		objTmp.dataLogOutcome = "FAIL";
		objTmp.steps = "Run ingestion with Mean out of range";
		objTmp.passStep = "QC Code was Fail against all lanes when Mean was out of range successfully";
		objTmp.failStep = "QC Code did not Fail against all lanes when Mean was out of range";
		objTmp.GreaterLesserCheck = false;
		objTmp.lstFilters.add(objFilter);
		lstInstallationRunModel.add(objTmp);
				
		objTmp = new InstallationRunModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-IR-04: Verify QC Code is Fail against all lanes when Standard Deviation is out of range";
		objTmp.TestCaseDescription = "This test case will verify QC Code is Fail against all lanes when Standard Deviation is out of range";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.MinMean = "10";
		objFilter.MaxMean = "1500";
		objFilter.MinStd = "100";
		objFilter.MaxStd = "500";
		objTmp.dataLogOutcome = "FAIL";
		objTmp.steps = "Run ingestion with Standard Deviation out of range";
		objTmp.passStep = "QC Code was Fail against all lanes when Standard Deviation was out of range successfully";
		objTmp.failStep = "QC Code did not Fail against all lanes when Standard Deviation was out of range";
		objTmp.GreaterLesserCheck = false;
		objTmp.lstFilters.add(objFilter);
		lstInstallationRunModel.add(objTmp);

		objTmp = new InstallationRunModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-IR-05: Verify QC Code is Pass when Mean and Standard Deviation are in range";
		objTmp.TestCaseDescription = "This test case will verify QC Code is Pass when Mean and Standard Deviation are in range";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.MinMean = "1";
		objFilter.MaxMean = "1500";
		objFilter.MinStd = "1";
		objFilter.MaxStd = "1000";
		objTmp.dataLogOutcome = "PASS";
		objTmp.steps = "Run ingestion with Standard Deviation out of range";
		objTmp.passStep = "QC Code was Pass when Mean and Standard Deviation were in range successfully";
		objTmp.failStep = "QC Code was not Pass when Mean and Standard Deviation were in range";
		objTmp.GreaterLesserCheck = false;
		objTmp.lstFilters.add(objFilter);
		lstInstallationRunModel.add(objTmp);
	
		return lstInstallationRunModel;
	}
	
}
