package Models;

import java.util.ArrayList;
import java.util.Arrays;

import MiscFunctions.DateUtil;
import static PageObjects.FlockManagementPage.*;


public class FlockManagementModel {

	public String TestCaseName;
	public String TestCaseDescription;
	public String TestCaseNameSearch;
	public String TestCaseDescriptionSearch;
	public ArrayList<ReportFilters> lstFilters;
	public boolean viewAccess;
	public boolean unviewAccess;
	public boolean sortDescFirst;
	public boolean errorCase;
	public String steps;
	public String input;


	public static ArrayList<FlockManagementModel> lstFlockRegistrationDateSearch = new ArrayList<>();
	public static ArrayList<FlockManagementModel> lstFlockRegistrationDateEnter = new ArrayList<>();
	public static ArrayList<FlockManagementModel> lstFlockRegistrationFieldAccess = new ArrayList<>();
	public static ArrayList<FlockManagementModel> lstFlockRegistrationContexualCheck = new ArrayList<>();
	public static ArrayList<FlockManagementModel> lstFlockRegistrationEdit = new ArrayList<>();
	public static ArrayList<FlockManagementModel> lstFlockRegistrationValidation = new ArrayList<>();
	
	public FlockManagementModel() {

	}

	public static String applyFilterTitle = "Verify user can select checkbox from ";
	public static String applyFilterDesc = "This test case will verify that user can select checkbox from ";
	public static String filterIndicatorTitle = "Verify user can apply filter and table displays relevant results on applying ";
	public static String filterIndicatorDesc = "This test case will verify that user can apply filter and table displays relevant results on applying ";

//	public static String flockIntegratorID = "IntegratorID_5249";
	public static String flockIntegratorID = "IntegratorID_"+DateUtil.date0;
	public static String flockProgramName = "FlockVaccine_"+DateUtil.date0;
	public static String flockProgramAdminMethod = "AdminMethod_"+DateUtil.date0;
	public static String flockBirdSize = "Small";
	public static int totalColumnsinFlock = 56;
//
//	public static String flockIntegratorID = "IntegratorID_2528";
//	public static String flockProgramName = "FlockVaccine_2528";
//	public static String flockProgramAdminMethod = "AdminMethod_5256";

	


	public static ArrayList<FlockManagementModel> EditFlock() {
		ArrayList<FlockManagementModel> lstFlockManagementModel = new ArrayList<FlockManagementModel>();
		FlockManagementModel objTmp;
		ReportFilters objFilter;			

//		objTmp = new FlockManagementModel();
//		objFilter = new ReportFilters();
//		objFilter.FilterName = "Integrator Site ID";
//		objTmp.TestCaseName = "AN-FR-99: Verify user can edit "+objFilter.FilterName+" and it is reflected in audit log";
//		objTmp.TestCaseDescription = "This testcase will verify that user can edit "+objFilter.FilterName+" and a new row appears in audit log with changes made";
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter = new ReportFilters();
//		objFilter.FilterID = Test_Elements.flockIntegratorID;
//		objFilter.ColumnID = Test_Elements.flockIntegratorIDCol;
//		objTmp.input = "IntegratorID_TA";
//		objTmp.steps = "Edit "+objFilter.FilterName;
//		objTmp.lstFilters.add(objFilter);
//		lstFlockManagementModel.add(objTmp);

//		objTmp = new FlockManagementModel();
//		objFilter = new ReportFilters();
//		objFilter.FilterName = "Bird Type";
//		objTmp.TestCaseName = "AN-FR-100: Verify user can edit "+objFilter.FilterName+" and it is reflected in audit log";
//		objTmp.TestCaseDescription = "This testcase will verify that user can edit "+objFilter.FilterName+" and a new row appears in audit log with changes made";
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter = new ReportFilters();
//		objFilter.FilterID = Test_Elements.flockBirdType;
//		objFilter.ColumnID = Test_Elements.flockBirdTypeCol;
//		objTmp.input = "Chicken";
//		objTmp.steps = "Edit "+objFilter.FilterName;
//		objTmp.lstFilters.add(objFilter);
//		lstFlockManagementModel.add(objTmp);

		objTmp = new FlockManagementModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Bird Sex";
		objTmp.TestCaseName = "AN-FR-101: Verify user can edit "+objFilter.FilterName+" and it is reflected in audit log";
		objTmp.TestCaseDescription = "This testcase will verify that user can edit "+objFilter.FilterName+" and a new row appears in audit log with changes made";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = flockBirdSex;
		objFilter.ColumnID = flockBirdSexPlacementCol;
		objTmp.input = "Male";
		objTmp.steps = "Edit "+objFilter.FilterName;
		objTmp.lstFilters.add(objFilter);
		lstFlockManagementModel.add(objTmp);

		objTmp = new FlockManagementModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Bird Breed";
		objTmp.TestCaseName = "AN-FR-102: Verify user can edit "+objFilter.FilterName+" and it is reflected in audit log";
		objTmp.TestCaseDescription = "This testcase will verify that user can edit "+objFilter.FilterName+" and a new row appears in audit log with changes made";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
	//	objFilter.FilterID = Test_Elements.flockBirdBreed;
		objFilter.ColumnID = flockBirdBreedPlacementCol;
		objTmp.input = "Australorp";
		objTmp.steps = "Edit "+objFilter.FilterName;
		objTmp.lstFilters.add(objFilter);
		lstFlockManagementModel.add(objTmp);

		return lstFlockManagementModel;
	}


	public static ArrayList<FlockManagementModel> FlockValidation() {
		ArrayList<FlockManagementModel> lstFlockManagementModel = new ArrayList<FlockManagementModel>();
		FlockManagementModel objTmp;
		ReportFilters objFilter;		

		objTmp = new FlockManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-FR-47: Verify that error appears when total weight condemend is less than birds weight and parts weights";
		objTmp.TestCaseDescription = "This test case will verify that error appears when total weight condemend is less than birds weight and parts weights";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(18, 23, 1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("200", "2000", DataUploadModel.FarmSiteID));
	//	objFilter.ColumnID = Test_Elements.flockTotalWeightCondemnedKGCol;
		objTmp.errorCase = true;
		objTmp.steps = "Enter total weight condemned kg less than birds weight and parts weights";
		objTmp.lstFilters.add(objFilter);
		lstFlockManagementModel.add(objTmp);
		
		objTmp = new FlockManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-FR-48: Verify that no error appears when total weight condemend is less than birds weight and parts weights";
		objTmp.TestCaseDescription = "This test case will verify that no error appears when total weight condemend is less than birds weight and parts weights";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(18, 23));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("2500", "300"));
	//	objFilter.ColumnID = Test_Elements.flockTotalWeightCondemnedKGCol;
		objTmp.errorCase = false;
		objTmp.steps = "Enter total weight condemned kg less than birds weight and parts weights";
		objTmp.lstFilters.add(objFilter);
		lstFlockManagementModel.add(objTmp);
		
		
		objTmp = new FlockManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-FR-49: Verify that error appears when total weight condemend lb is less than birds weight and parts weights";
		objTmp.TestCaseDescription = "This test case will verify that error appears when total weight condemend lb is less than birds weight and parts weights";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(17, 22));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("200", "2000"));
	//	objFilter.ColumnID = Test_Elements.flockTotalWeightCondemnedLBCol;
		objTmp.errorCase = true;
		objTmp.steps = "Enter total weight condemned kg less than birds weight and parts weights";
		objTmp.lstFilters.add(objFilter);
		lstFlockManagementModel.add(objTmp);
		
		objTmp = new FlockManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-FR-50: Verify that error appears when total weight condemend lb is less than birds weight and parts weights";
		objTmp.TestCaseDescription = "This test case will verify that error appears when total weight condemend lbis less than birds weight and parts weights";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(17, 22));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("2500", "300"));
	//	objFilter.ColumnID = Test_Elements.flockTotalWeightCondemnedLBCol;
		objTmp.errorCase = false;
		objTmp.steps = "Enter total weight condemned lb less than birds weight and parts weights";
		objTmp.lstFilters.add(objFilter);
		lstFlockManagementModel.add(objTmp);
		
		objTmp = new FlockManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-FR-50: Verify that system should not accept Number of Birds Processed greater than Number of Birds Placed";
		objTmp.TestCaseDescription = "This test case will verify that system should not accept Number of Birds Processed greater than Number of Birds Placed";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(5, 12));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("10", "20"));
	//	objFilter.ColumnID = Test_Elements.flockNumBirdsProcessedCol;
		objTmp.errorCase = true;
		objTmp.steps = "Enter Number of Birds Processed greater than Number of Birds Placed";
		objTmp.lstFilters.add(objFilter);
		lstFlockManagementModel.add(objTmp);
		
		objTmp = new FlockManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-FR-51: Verify that system accept Number of Birds Processed less than Number of Birds Placed";
		objTmp.TestCaseDescription = "This test case will verify that system should accept Number of Birds Processed less than Number of Birds Placed";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(5, 12));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("20", "10"));
//		objFilter.ColumnID = Test_Elements.flockNumBirdsProcessedCol;
		objTmp.errorCase = false;
		objTmp.steps = "Enter Number of Birds Processed less than Number of Birds Placed";
		objTmp.lstFilters.add(objFilter);
		lstFlockManagementModel.add(objTmp);
		
//		objTmp = new FlockManagementModel();
//		objFilter = new ReportFilters();
//		objTmp.TestCaseName = "AN-FR-52: Verify that system should not accept Number of Birds Processed greater than Number of Birds Placed";
//		objTmp.TestCaseDescription = "This test case will verify that system should not accept Number of Birds Processed greater than Number of Birds Placed";
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(13, 5));
//		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("10", "50"));
//		objFilter.ColumnID = Test_Elements.flockNumBirdsProcessedCol;
//		objTmp.errorCase = true;
//		objTmp.steps = "Enter Number of Birds Processed greater than Number of Birds Placed";
//		objTmp.lstFilters.add(objFilter);
//		lstFlockManagementModel.add(objTmp);
//		
//		objTmp = new FlockManagementModel();
//		objFilter = new ReportFilters();
//		objTmp.TestCaseName = "AN-FR-53: Verify that system accept Number of Birds Processed less than Number of Birds Placed";
//		objTmp.TestCaseDescription = "This test case will verify that system should accept Number of Birds Processed less than Number of Birds Placed";
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(13, 5));
//		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("50", "20"));
//		objFilter.ColumnID = Test_Elements.flockNumBirdsProcessedCol;
//		objTmp.errorCase = false;
//		objTmp.steps = "Enter Number of Birds Processed less than Number of Birds Placed";
//		objTmp.lstFilters.add(objFilter);
//		lstFlockManagementModel.add(objTmp);
		
		return lstFlockManagementModel;
	}
	
	

}