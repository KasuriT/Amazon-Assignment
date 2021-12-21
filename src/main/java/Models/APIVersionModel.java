package Models;

import java.util.ArrayList;

public class APIVersionModel {

	public String TestCaseName;
	public String TestCaseDescription;
	public ArrayList<ReportFilters> lstFilters;
	public String version;
	public int versionUpgradeResponse;
	public int statusCodeResponseLogin;
	public int statusCodeResponseAPIs;
	public String messageResponse;
	public String messageResponseAPI;
	public boolean responseBody;
	
	public static ArrayList<APIVersionModel> FillData() {
		ArrayList<APIVersionModel> lstAPIVersionModel = new ArrayList<APIVersionModel>();
		APIVersionModel objTmp = new APIVersionModel();
		ReportFilters objFilter = new ReportFilters();

		objTmp = new APIVersionModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-APIVersion-01: Login with higher version and verify response of other API's";
		objTmp.TestCaseDescription = "This test case will verify the response of all api's after login with higher version";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.version = "14.13";
		objTmp.versionUpgradeResponse = 0;
		objTmp.statusCodeResponseLogin = 200;
		objTmp.messageResponse = "Successful Response.";
		objTmp.messageResponseAPI = "Successful Response.";
		objTmp.responseBody = false;
		objTmp.lstFilters.add(objFilter);
		lstAPIVersionModel.add(objTmp);
		
		objTmp = new APIVersionModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-APIVersion-02: Login with same version and verify response of other API's";
		objTmp.TestCaseDescription = "This test case will verify the response of all api's after login with higher version";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.version = "14.12";
		objTmp.versionUpgradeResponse = 1;
		objTmp.statusCodeResponseLogin = 200;
		objTmp.responseBody = false;
		objTmp.messageResponse = "Successful Response.";
		objTmp.messageResponseAPI = "Upgrade the App Version.";
		objTmp.lstFilters.add(objFilter);
		lstAPIVersionModel.add(objTmp);
		
		objTmp = new APIVersionModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-APIVersion-03: Login with lower version and verify response of other API's";
		objTmp.TestCaseDescription = "This test case will verify the response of all api's after login with lower version";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.version = "14.11";
		objTmp.versionUpgradeResponse = 1;
		objTmp.statusCodeResponseLogin = 200;
		objTmp.responseBody = false;
		objTmp.messageResponse = "Successful Response.";
		objTmp.messageResponseAPI = "Upgrade the App Version.";
		objTmp.lstFilters.add(objFilter);
		lstAPIVersionModel.add(objTmp);
		
		objTmp = new APIVersionModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-APIVersion-04: Login with lower version and verify response of other API's";
		objTmp.TestCaseDescription = "This test case will verify the response of all api's after login with lower version";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.version = "14.11";
		objTmp.versionUpgradeResponse = 1;
		objTmp.statusCodeResponseLogin = 200;
		objTmp.responseBody = true;
		objTmp.messageResponse = "Please contact Administrator.";
		objTmp.lstFilters.add(objFilter);
		lstAPIVersionModel.add(objTmp);
		
		objTmp = new APIVersionModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-APIVersion-05: Login with lower version and verify response of other API's";
		objTmp.TestCaseDescription = "This test case will verify the response of all api's after login with lower version";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.version = "14.11";
		objTmp.versionUpgradeResponse = 1;
		objTmp.statusCodeResponseLogin = 200;
		objTmp.responseBody = true;
		objTmp.messageResponse = "Please contact Administrator.";
		objTmp.lstFilters.add(objFilter);
		lstAPIVersionModel.add(objTmp);

		return lstAPIVersionModel;
	}

}
