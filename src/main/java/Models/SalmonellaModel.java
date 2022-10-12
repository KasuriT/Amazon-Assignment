package Models;

import java.util.ArrayList;
import java.util.Arrays;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;

public class SalmonellaModel {


	public String TestCaseName;
	public String TestCaseDescription;

	public ArrayList<ReportFilters> lstFilters;
	public boolean ReloadPage;
	public boolean Filter1;
	public boolean Filter2;
	public boolean Filter3;
	public boolean sortLogic1;
	public boolean sortLogic2;
	public boolean resetFilter;
	public boolean startWith;
	public boolean endsWith;
	public boolean contains;
	public boolean runIngestion;
	public boolean firstCase;
	public boolean secondCase;
	public boolean viewAccess;
	public boolean unviewAccess;
	public String FilterHideID;
	public String FilterUnHideID;
	public String input;
	public String fileJson;
	public String sampleID;

	public SalmonellaModel() {

	}


	public static ArrayList<SalmonellaModel> FillDate() {
		ArrayList<SalmonellaModel> lstSalmonellaModel = new ArrayList<SalmonellaModel>();
		SalmonellaModel objTmp = new SalmonellaModel();

		objTmp.TestCaseName = "AN-SL-04: Verify user can filter Today date from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of date filter after selecting 'Today'";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.Filter3 = false;
		objTmp.lstFilters = new ArrayList<>();
		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterListXPathSearch = Test_Elements.slToday;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "0";
		objFilter.fromDate = "0";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-05: Verify user can filter Last 24 Hours from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of Last 24 Hours after selecting 'Last 24 Hours'";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.Filter3 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterListXPathSearch = Test_Elements.slLast24Hours;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "-1";
		objFilter.fromDate = "-1";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-06: Verify user can filter Last 7 Days from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of Last 7 Days after selecting 'Last 7 Days'";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.Filter3 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterListXPathSearch = Test_Elements.slLast7Days;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "-1";
		objFilter.fromDate = "-7";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-07: Verify user can filter Last 30 Days from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of Last 30 Days after selecting 'Last 30 Days'";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.Filter3 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterListXPathSearch = Test_Elements.slLast30Days;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "-1";
		objFilter.fromDate = "-30";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-08: Verify user can filter Last Month from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of Last Month after selecting 'Last Month'";
		objTmp.Filter1 = false;
		objTmp.Filter2 = true;
		objTmp.Filter3 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterListXPathSearch = Test_Elements.slLastMonth;
		objFilter.toMonth = "-1";
		objFilter.fromMonth = "-1";
		objFilter.toDate = "30";
		objFilter.fromDate = "1";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-09: Verify user can filter This Month from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of This Month after selecting 'This Month'";
		objTmp.Filter1 = false;
		objTmp.Filter2 = false;
		objTmp.Filter3 = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterListXPathSearch = Test_Elements.slThisMonth;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "0";
		objFilter.fromDate = "1";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		return lstSalmonellaModel;
	}


	public static ArrayList<SalmonellaModel> EnterDate() {
		ArrayList<SalmonellaModel> lstSalmonellaModel = new ArrayList<SalmonellaModel>();
		SalmonellaModel objTmp = new SalmonellaModel();

		objTmp.TestCaseName = "AN-SL-13: Verify user cannot apply filter with invalid date";
		objTmp.TestCaseDescription = "This testcase will verify that user cannot add date with invalid from and to date";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.lstFilters = new ArrayList<>();
		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "Enter invalid date";
		objFilter.fromDate = "12/01/2020";
		objFilter.toDate = "11/11/202020";
		objFilter.alertMessage = "Please select a valid date";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-14: Verify user cannot apply filter with any other date format except mm/dd/yyyy";
		objTmp.TestCaseDescription = "This testcase will verify that user cannot apply filter with any other date format except mm/dd/yyyy";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Enter any other date format except mm/dd/yyyy";
		objFilter.fromDate = "12/01/2020";
		objFilter.toDate = "20/12/2020";
		objFilter.alertMessage = "Please select a valid date";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-15: Verify user cannot apply filter with from date greater than to date";
		objTmp.TestCaseDescription = "This testcase will verify that user cannot apply filter with from date greater than to date";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Enter from date greater than to date";
		objFilter.fromDate = "12/01/2020";
		objFilter.toDate = "10/01/2020";
		objFilter.alertMessage = "Please select a valid date";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-16: Verify user can search for records with valid date";
		objTmp.TestCaseDescription = "This testcase will verify that user can search for records with valid date";
		objTmp.Filter1 = false;
		objTmp.Filter2 = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Enter valid date";
		objFilter.fromDate = "12/01/2020";
		objFilter.toDate = "01/07/2021";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		return lstSalmonellaModel;

	}

	
	public static ArrayList<SalmonellaModel> ContexualCheck() {
		ArrayList<SalmonellaModel> lstSalmonellaModel = new ArrayList<SalmonellaModel>();
		SalmonellaModel objTmp;
		ReportFilters objFilter;
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Lab Sample ID Filter";
		objTmp.TestCaseName = "AN-SL-109: Apply "+objFilter.FilterName+" and verify all other filters behaves contexually";
		objTmp.TestCaseDescription = "This testcase will verify that after applying "+objFilter.FilterName+" all other filters behave contexually";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objTmp.runIngestion = true;
		objTmp.firstCase = true;
		objTmp.secondCase = false;
		objTmp.sampleID = Test_Variables.dateYYYYMMDD+"-Salm-"+Test_Variables.date0;
	    objTmp.fileJson = "[{'LANE':'1','PATHOGEN':'Salmonella','SAMPLEID':'"+objTmp.sampleID+"','SCANDATETIME':'"+Test_Variables.date100+"','OUTCOME':'','STATUSCODE':'','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'','CARTRIDGEID':'CartIDContexual"+Test_Variables.date0+"','EXPERIMENTID':'','INSTRUMENTID':'"+Test_Variables.InstrumentID+"','USERID':'"+Test_Variables.UserID+"','RUN_ID':'"+objTmp.sampleID+"','RUN_TYPE':'SCRIPT_1001a Salmonella Probes','MPN_CALCULATION_TYPE':'0','DILUTION_FACTOR':'0.01','LANE_NO':'1','DATE':'2020-06-03','TIME':'10:45:43.914898','W1_PC_COUNT':'0','W1_CELL_COUNT':'0','W1_PC_MEAN_INTENSITY':'','W1_CELL_MEAN_INTENSITY':'','W1_PC_RANGE_INTENSITY':'0','W1_CELL_RANGE_INTENSITY':'0','W1_PC_CV_INTENSITY':'','W1_CELL_CV_INTENSITY':'','W1_PC_MEAN_SIZE':'','W1_CELL_MEAN_SIZE':'','W1_PC_RANGE_SIZE':'','W1_CELL_RANGE_SIZE':'','W1_PC_CV_SIZE':'','W1_CELL_CV_SIZE':'','W2_PC_COUNT':'0','W2_CELL_COUNT':'200','W2_PC_MEAN_INTENSITY':'','W2_CELL_MEAN_INTENSITY':'','W2_PC_RANGE_INTENSITY':'0','W2_CELL_RANGE_INTENSITY':'0','W2_PC_CV_INTENSITY':'','W2_CELL_CV_INTENSITY':'','W2_PC_MEAN_SIZE':'','W2_CELL_MEAN_SIZE':'','W2_PC_RANGE_SIZE':'','W2_CELL_RANGE_SIZE':'','W2_PC_CV_SIZE':'','W2_CELL_CV_SIZE':'','STATUS':'','LANE_TOTAL_AREA_UM2':'326035983.8748','LANE_NOISE_AREA_UM2':'82232170.4712','LANE_NOISE_RATIO_PERCENT':'95.73','LANE_NOISE_OBJECT_COUNT':'71','W1_NOISE_AREA_UM2':'8323637.364','W2_NOISE_AREA_UM2':'8315160.7508','W1_NOISE_RATIO_PERCENT':'100.0','W2_NOISE_RATIO_PERCENT':'99.8993635494474','W1_NOISE_OBJECT_COUNT':'0','W2_NOISE_OBJECT_COUNT':'0','LANE_SMALL_NOISE_OBJECT_COUNT':'71','LANE_MEDIUM_NOISE_OBJECT_COUNT':'0','LANE_LARGE_NOISE_OBJECT_COUNT':'0','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'0','IMPROC':'ImprocSalm01','VERSION':'4.0.8.2','ERROR_CODE':'B01','IE_COLLECTION_SITE_ID':'1001043','IE_SAMPLE_MATRIX_ID':''}]";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("sampleId"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList(objTmp.sampleID));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("laneNum", "countOutcome", "pathogen", "collection_site_id", "collection_site_type", "sample_matrix", "customer_sample_id", "metadata_date_recieved", "cartridgeId", "instrumentId", "w2CellCount", "user_name", "kit_lot", "version", "testSiteId", "requested_assay", "flock_id", "testSiteName", "runType"));
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Cartridge ID Filter";
		objTmp.TestCaseName = "AN-SL-110: Apply "+objFilter.FilterName+" and verify all other filters behaves contexually";
		objTmp.TestCaseDescription = "This testcase will verify that after applying "+objFilter.FilterName+" all other filters behave contexually";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objTmp.runIngestion = false;
		objTmp.firstCase = true;
		objTmp.secondCase = false;
		objTmp.sampleID = Test_Variables.dateYYYYMMDD+"-Salm-"+Test_Variables.date0;
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("cartridgeId"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("CartIDContexual"+Test_Variables.date0));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("laneNum", "sampleId", "countOutcome", "pathogen", "collection_site_id", "collection_site_type", "sample_matrix", "customer_sample_id", "metadata_date_recieved", "instrumentId", "w2CellCount", "user_name", "kit_lot", "version", "testSiteId", "requested_assay", "flock_id", "testSiteName", "runType"));
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
	
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Lane Filter";
		objTmp.TestCaseName = "AN-SL-111: Apply "+objFilter.FilterName+" and verify all other filters behaves contexually";
		objTmp.TestCaseDescription = "This testcase will verify that after applying "+objFilter.FilterName+" all other filters behave contexually";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objTmp.firstCase = false;
		objTmp.secondCase = true;
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("laneNum"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1"));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("sampleId"));
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Instrument Filter";
		objTmp.TestCaseName = "AN-SL-112: Apply "+objFilter.FilterName+" and verify all other filters behaves contexually";
		objTmp.TestCaseDescription = "This testcase will verify that after applying "+objFilter.FilterName+" all other filters behave contexually";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objTmp.firstCase = false;
		objTmp.secondCase = true;
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("instrumentId"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("PSN0001"));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList( "sampleId", "collection_site_id", "sample_matrix", "cartridgeId", "kit_lot", "version"));
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
			
		return lstSalmonellaModel;
	}	
	
	
	public static ArrayList<SalmonellaModel> FieldAccess() {
		ArrayList<SalmonellaModel> lstSalmonellaModel = new ArrayList<SalmonellaModel>();
		SalmonellaModel objTmp;
		ReportFilters objFilter;
	
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Lane Filter";
		objTmp.TestCaseName = "AN-SL-175: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slLaneCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slLane;
		objTmp.FilterUnHideID = "";
		objTmp.unviewAccess = true;   //will hide the current column
		objTmp.viewAccess = false;    //will unhide the previously hidden column
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
			
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Lab Sample ID Filter";
		objTmp.TestCaseName = "AN-SL-176: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slSampleIDCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slSampleID;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slLane;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
	
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "QC Code Filter";
		objTmp.TestCaseName = "AN-SL-177: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slQCCodeCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slQCCode;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slSampleID;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Result Status Filter";
		objTmp.TestCaseName = "AN-SL-178: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slResultStatusCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slResultStatus;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slQCCode;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Result Filter";
		objTmp.TestCaseName = "AN-SL-179: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slResultCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slResult;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slResultStatus;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Assay Filter";
		objTmp.TestCaseName = "AN-SL-180: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slAssayCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slAssay;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slResult;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Result ID Filter";
		objTmp.TestCaseName = "AN-SL-181: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slResultIDCol;
		objTmp.FilterHideID = "";
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slAssay;
		objTmp.unviewAccess = false;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Result Date Filter";
		objTmp.TestCaseName = "AN-SL-182: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slDateCol;
		objTmp.FilterHideID = "";
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slResultDate;
		objTmp.unviewAccess = false;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Result Time Filter";
		objTmp.TestCaseName = "AN-SL-183: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slTimeCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slResultTime;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slAssay;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection Site Name Filter";
		objTmp.TestCaseName = "AN-SL-184: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slSiteNameCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slCollectionSiteName;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slResultTime;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection Site ID Filter";
		objTmp.TestCaseName = "AN-SL-185: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slSiteIDCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slCollectionSiteID;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slCollectionSiteName;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection Site Type Filter";
		objTmp.TestCaseName = "AN-SL-186: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slSiteTypeCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slCollectionSiteType;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slCollectionSiteID;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Sample Matrix Filter";
		objTmp.TestCaseName = "AN-SL-187: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slSampleMatrixCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slSampleMatrix;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slCollectionSiteType;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Dilution Factor Filter";
		objTmp.TestCaseName = "AN-SL-188: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slDilutionFactorCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slDilutionFactor;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slSampleMatrix;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Customer Sample ID Filter";
		objTmp.TestCaseName = "AN-SL-189: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slCSampleIDCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slCustomerSampleID;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slDilutionFactor;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Received Filter";
		objTmp.TestCaseName = "AN-SL-190: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slReceivedDateCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slDateReceived;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slCustomerSampleID;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Cartridge ID Filter";
		objTmp.TestCaseName = "AN-SL-191: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slCartridgeIDCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slCartridgeID;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slDateReceived;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Instrument ID Filter";
		objTmp.TestCaseName = "AN-SL-192: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slInstrumentIDCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slInstrumentID;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slCartridgeID;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "W1 Cell Count Filter";
		objTmp.TestCaseName = "AN-SL-193: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slW1CellCountCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slW1CellCount;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slInstrumentID;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "W1 PC Count Filter";
		objTmp.TestCaseName = "AN-SL-194: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slW1PCCountCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slW1PCCount;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slW1CellCount;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "W1 Mean Intensity Filter";
		objTmp.TestCaseName = "AN-SL-195: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slW1MeanIntensityCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slW1MeanIntensity;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slW1PCCount;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "W2 Cell Count Filter";
		objTmp.TestCaseName = "AN-SL-196: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slW2CellCountCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slW2CellCount;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slW1MeanIntensity;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "W2 PC Count Filter";
		objTmp.TestCaseName = "AN-SL-197: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slW2CPCCountCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slW2PCCount;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slW2CellCount;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "W2 Mean Intensity Filter";
		objTmp.TestCaseName = "AN-SL-198: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slW2MeanIntensityCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slW2MeanIntensity;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slW2PCCount;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Piper User Filter";
		objTmp.TestCaseName = "AN-SL-199: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slPiperUserCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slPiperUser;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slW2MeanIntensity;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Kit Lot Filter";
		objTmp.TestCaseName = "AN-SL-200: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slKitLotCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slkitLot;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slPiperUser;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Improc Version Filter";
		objTmp.TestCaseName = "AN-SL-201: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slImprocIDCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slImprocVersion;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slkitLot;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Test Site ID Filter";
		objTmp.TestCaseName = "AN-SL-202: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slTestSiteIDCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slTestSiteID;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slImprocVersion;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Test Site Name Filter";
		objTmp.TestCaseName = "AN-SL-203: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slTestSiteNameCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slTestSiteName;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slTestSiteID;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Requested Assay Filter";
		objTmp.TestCaseName = "AN-SL-204: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slRequestedAssayCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slRequestedAssay;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slTestSiteName;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Flock ID Filter";
		objTmp.TestCaseName = "AN-SL-205: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slFlockIDCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slFlockID;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slRequestedAssay;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
	
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Run Type Filter";
		objTmp.TestCaseName = "AN-SL-206: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slRunTypeCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slRunType;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slFlockID;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
			
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection Date Filter";
		objTmp.TestCaseName = "AN-SL-207: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.slCollectionDateCol;
		objTmp.FilterHideID = Test_Elements.slSortFilter+""+Test_Elements.slCollectionDate;
		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slRunType;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
//		objTmp = new SalmonellaModel();
//		objFilter = new ReportFilters();
//		objFilter.FilterName = "Select back all columns";
//		objTmp.TestCaseName = "AN-SL-208: Select back all unselected columns";
//		objTmp.TestCaseDescription = "This testcase will select back all unselected columns";
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter = new ReportFilters();
//		objFilter.FilterID = Test_Elements.slFieldAccessCol;
//		objTmp.FilterHideID = "";
//		objTmp.FilterUnHideID = Test_Elements.slSortFilter+""+Test_Elements.slCollectionDate;
//		objTmp.unviewAccess = false;
//		objTmp.viewAccess = true;
//		objTmp.lstFilters.add(objFilter);
//		lstSalmonellaModel.add(objTmp);
		
		return lstSalmonellaModel;
	}
	
}


