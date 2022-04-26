package Models;

import java.util.ArrayList;
import java.util.Arrays;
import Test.Ancera.Test_Elements;

public class FlockRegistrationModel {

	public String TestCaseName;
	public String TestCaseDescription;
	public String TestCaseNameSearch;
	public String TestCaseDescriptionSearch;
	public ArrayList<ReportFilters> lstFilters;
	public boolean paginationExist;
	public boolean paginationLastPage;
	public boolean paginationNextPage;
	public boolean paginationFirstPage;
	public boolean paginationPreviousPage;
	public boolean sortLogic1;
	public boolean sortLogic2;
	public boolean startWith;
	public boolean endsWith;
	public boolean contains;
	public boolean viewAccess;
	public boolean unviewAccess;
	public boolean sortDescFirst;
	public boolean errorCase;
	public String steps;
	public String input;

	public FlockRegistrationModel() {

	}

	public static String applyFilterTitle = "Verify user can select checkbox from ";
	public static String applyFilterDesc = "This test case will verify that user can select checkbox from ";
	public static String filterIndicatorTitle = "Verify user can apply filter and table displays relevant results on applying ";
	public static String filterIndicatorDesc = "This test case will verify that user can apply filter and table displays relevant results on applying ";


	public static ArrayList<FlockRegistrationModel> FillData() {
		ArrayList<FlockRegistrationModel> lstFlockRegistrationModel = new ArrayList<FlockRegistrationModel>();
		FlockRegistrationModel objTmp; 
		ReportFilters  objFilter;

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Integrator ID Filter";
		objTmp.TestCaseName = "AN-FR-02: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-FR-03: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList(Test_Elements.flockIntegratorID));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("3"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp); 

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site ID Filter";
		objTmp.TestCaseName = "AN-FR-04: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-FR-05: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList(Test_Elements.flockSiteID));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("3"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp); 

//		objTmp = new FlockRegistrationModel();
//		objFilter = new ReportFilters();
//		objFilter.FilterName = "Hatch Date Filter";
//		objTmp.TestCaseName = "AN-FR-06: "+applyFilterTitle+objFilter.FilterName;
//		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
//		objTmp.TestCaseNameSearch = "AN-FR-07: "+filterIndicatorTitle+objFilter.FilterName;
//		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter = new ReportFilters();
//		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList(Test_Elements.flockHatchDate));
//		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("3"));
//		objTmp.lstFilters.add(objFilter);
//		lstFlockRegistrationModel.add(objTmp); 

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Placement Date Filter";
		objTmp.TestCaseName = "AN-FR-08: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-FR-09: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList(Test_Elements.flockPlacementDate));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("3"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp); 

//		objTmp = new FlockRegistrationModel();
//		objFilter = new ReportFilters();
//		objFilter.FilterName = "Bird Type Filter";
//		objTmp.TestCaseName = "AN-FR-10: "+applyFilterTitle+objFilter.FilterName;
//		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
//		objTmp.TestCaseNameSearch = "AN-FR-11: "+filterIndicatorTitle+objFilter.FilterName;
//		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter = new ReportFilters();
//		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList(Test_Elements.flockBirdType));
//		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("3"));
//		objTmp.lstFilters.add(objFilter);
//		lstFlockRegistrationModel.add(objTmp); 

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Bird Sex Filter";
		objTmp.TestCaseName = "AN-FR-12: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-FR-13: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList(Test_Elements.flockBirdSex));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("3"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp); 

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Bird Breed Filter";
		objTmp.TestCaseName = "AN-FR-14: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-FR-15: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList(Test_Elements.flockBirdBreed));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("3"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp); 

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Processing Date Filter";
		objTmp.TestCaseName = "AN-FR-16: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-FR-17: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList(Test_Elements.flockProcessingDate));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("3"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp); 

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Processing Site ID Filter";
		objTmp.TestCaseName = "AN-FR-18: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-FR-18: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList(Test_Elements.flockProcessingSiteID));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("3"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp); 

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Two Filters";
		objTmp.TestCaseName = "AN-FR-19: Verify user can apply 2 filters at same time";
		objTmp.TestCaseDescription = "This testcase will verify that user can apply 2 filters at same time";
		objTmp.TestCaseNameSearch = "AN-FR-20: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList(Test_Elements.flockIntegratorID, Test_Elements.flockSiteID));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("3"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		return lstFlockRegistrationModel;
	}


	public static ArrayList<FlockRegistrationModel> Wildcard() {
		ArrayList<FlockRegistrationModel> lstFlockRegistrationModel = new ArrayList<FlockRegistrationModel>();
		FlockRegistrationModel objTmp; 
		ReportFilters objFilter;

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Integrator Site ID Filter";
		objTmp.TestCaseName = "AN-FR-20: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.flockIntegratorID;
		objFilter.FilterXPath = Test_Elements.flockIntegratorID;
		objTmp.startWith = true;
		objTmp.contains = false;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.flockIntegratorIDCol;
		objTmp.input = "T";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("T", "t"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Integrator Site ID Filter";
		objTmp.TestCaseName = "AN-FR-21: Verify wild card Contains search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Contains search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.flockIntegratorID;
		objFilter.FilterXPath = Test_Elements.flockIntegratorID;
		objTmp.startWith = false;
		objTmp.contains = true;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.flockIntegratorIDCol;
		objTmp.input = "a";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Integrator Site ID Filter";
		objTmp.TestCaseName = "AN-FR-22: Verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.flockIntegratorID;
		objFilter.FilterXPath = Test_Elements.flockIntegratorID;
		objTmp.startWith = false;
		objTmp.contains = false;
		objTmp.endsWith = true;
		objFilter.ColumnID = Test_Elements.flockIntegratorIDCol;
		objTmp.input = "a";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site ID Filter";
		objTmp.TestCaseName = "AN-FR-23: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.flockSiteID;
		objFilter.FilterXPath = Test_Elements.flockSiteID;
		objTmp.startWith = true;
		objTmp.contains = false;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.flockSiteIDCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site ID Filter";
		objTmp.TestCaseName = "AN-FR-24: Verify wild card Contains search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Contains search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.flockSiteID;
		objFilter.FilterXPath = Test_Elements.flockSiteID;
		objTmp.startWith = false;
		objTmp.contains = true;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.flockSiteIDCol;
		objTmp.input = "V";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("V", "v"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site ID Filter";
		objTmp.TestCaseName = "AN-FR-25: Verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.flockSiteID;
		objFilter.FilterXPath = Test_Elements.flockSiteID;
		objTmp.startWith = false;
		objTmp.contains = false;
		objTmp.endsWith = true;
		objFilter.ColumnID = Test_Elements.flockSiteIDCol;
		objTmp.input = "a";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Bird Sex Filter";
		objTmp.TestCaseName = "AN-FR-29: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.flockBirdSex;
		objFilter.FilterXPath = Test_Elements.flockBirdSex;
		objTmp.startWith = true;
		objTmp.contains = false;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.flockBirdSexCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Bird Sex Filter";
		objTmp.TestCaseName = "AN-FR-30: Verify wild card Contains search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Contains search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.flockBirdSex;
		objFilter.FilterXPath = Test_Elements.flockBirdSex;
		objTmp.startWith = false;
		objTmp.contains = true;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.flockBirdSexCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Bird Sex Filter";
		objTmp.TestCaseName = "AN-FR-31: Verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.flockBirdSex;
		objFilter.FilterXPath = Test_Elements.flockBirdSex;
		objTmp.startWith = false;
		objTmp.contains = false;
		objTmp.endsWith = true;
		objFilter.ColumnID = Test_Elements.flockBirdSexCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Bird Breed Filter";
		objTmp.TestCaseName = "AN-FR-32: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.flockBirdBreed;
		objFilter.FilterXPath = Test_Elements.flockBirdBreed;
		objTmp.startWith = true;
		objTmp.contains = false;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.flockBirdBreedCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Bird Breed Filter";
		objTmp.TestCaseName = "AN-FR-33: Verify wild card Contains search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Contains search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.flockBirdBreed;
		objFilter.FilterXPath = Test_Elements.flockBirdBreed;
		objTmp.startWith = false;
		objTmp.contains = true;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.flockBirdBreedCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Bird Breed Filter";
		objTmp.TestCaseName = "AN-FR-34: Verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.flockBirdBreed;
		objFilter.FilterXPath = Test_Elements.flockBirdBreed;
		objTmp.startWith = false;
		objTmp.contains = false;
		objTmp.endsWith = true;
		objFilter.ColumnID = Test_Elements.flockBirdBreedCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);


		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Processing Site ID Filter";
		objTmp.TestCaseName = "AN-FR-35: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.flockProcessingSiteID;
		objFilter.FilterXPath = Test_Elements.flockProcessingSiteID;
		objTmp.startWith = true;
		objTmp.contains = false;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.flockProcessingSiteIDCol;
		objTmp.input = "b";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("B", "b"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp); 

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Processing Site ID Filter";
		objTmp.TestCaseName = "AN-FR-36: Verify wild card Contains search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Contains search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.flockProcessingSiteID;
		objFilter.FilterXPath = Test_Elements.flockProcessingSiteID;
		objTmp.startWith = false;
		objTmp.contains = true;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.flockProcessingSiteIDCol;
		objTmp.input = "a";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp); 

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Processing Site ID Filter";
		objTmp.TestCaseName = "AN-FR-37: Verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.flockProcessingSiteID;
		objFilter.FilterXPath = Test_Elements.flockProcessingSiteID;
		objTmp.startWith = false;
		objTmp.contains = false;
		objTmp.endsWith = true;
		objFilter.ColumnID = Test_Elements.flockProcessingSiteIDCol;
		objTmp.input = "c";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("c", "c"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp); 

		return lstFlockRegistrationModel;
	}	

	public static ArrayList<FlockRegistrationModel> Lock() {
		ArrayList<FlockRegistrationModel> lstFlockRegistrationModel = new ArrayList<FlockRegistrationModel>();
		FlockRegistrationModel objTmp;
		ReportFilters objFilter;

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Integrator Flock ID Filter";
		objTmp.TestCaseName = "AN-FR-41: Verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.flockIntegratorID+""+Test_Elements.flockShowFilter;
		objFilter.FilterSort = Test_Elements.flockSortFilter+""+Test_Elements.flockIntegratorID;
		objFilter.FilterApply = Test_Elements.flockIntegratorID+""+Test_Elements.flockApplyFilter;
		objFilter.FilterClear = Test_Elements.flockIntegratorID+""+Test_Elements.flockClearFilter;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site ID Filter";
		objTmp.TestCaseName = "AN-FR-42: Verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.sitesSiteID+""+Test_Elements.sitesShowFilter;
		objFilter.FilterSort = Test_Elements.sitesSortFilter+""+Test_Elements.sitesSiteID;
		objFilter.FilterApply = Test_Elements.sitesSiteID+""+Test_Elements.sitesApplyFilter;
		objFilter.FilterClear = Test_Elements.sitesSiteID+""+Test_Elements.sitesClearFilter;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

//		objTmp = new FlockRegistrationModel();
//		objFilter = new ReportFilters();
//		objFilter.FilterName = "Hatch Date Filter";
//		objTmp.TestCaseName = "AN-FR-43: Verify Lock Filter functionality on "+objFilter.FilterName;
//		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter.FilterID = Test_Elements.flockHatchDate+""+Test_Elements.sitesShowFilter;
//		objFilter.FilterSort = Test_Elements.sitesSortFilter+""+Test_Elements.flockHatchDate;
//		objFilter.FilterApply = Test_Elements.flockHatchDate+""+Test_Elements.sitesApplyFilter;
//		objFilter.FilterClear = Test_Elements.flockHatchDate+""+Test_Elements.sitesClearFilter;
//		objTmp.lstFilters.add(objFilter);
//		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Placement Date Filter";
		objTmp.TestCaseName = "AN-FR-44: Verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.flockPlacementDate+""+Test_Elements.sitesShowFilter;
		objFilter.FilterSort = Test_Elements.sitesSortFilter+""+Test_Elements.flockPlacementDate;
		objFilter.FilterApply = Test_Elements.flockPlacementDate+""+Test_Elements.sitesApplyFilter;
		objFilter.FilterClear = Test_Elements.flockPlacementDate+""+Test_Elements.sitesClearFilter;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

//		objTmp = new FlockRegistrationModel();
//		objFilter = new ReportFilters();
//		objFilter.FilterName = "Bird Type Filter";
//		objTmp.TestCaseName = "AN-FR-45: Verify Lock Filter functionality on "+objFilter.FilterName;
//		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter.FilterID = Test_Elements.flockBirdType+""+Test_Elements.sitesShowFilter;
//		objFilter.FilterSort = Test_Elements.sitesSortFilter+""+Test_Elements.flockBirdType;
//		objFilter.FilterApply = Test_Elements.flockBirdType+""+Test_Elements.sitesApplyFilter;
//		objFilter.FilterClear = Test_Elements.flockBirdType+""+Test_Elements.sitesClearFilter;
//		objTmp.lstFilters.add(objFilter);
//		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Bird Sex Filter";
		objTmp.TestCaseName = "AN-FR-46: Verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.flockBirdSex+""+Test_Elements.sitesShowFilter;
		objFilter.FilterSort = Test_Elements.sitesSortFilter+""+Test_Elements.flockBirdSex;
		objFilter.FilterApply = Test_Elements.flockBirdSex+""+Test_Elements.sitesApplyFilter;
		objFilter.FilterClear = Test_Elements.flockBirdSex+""+Test_Elements.sitesClearFilter;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Bird Breed Filter";
		objTmp.TestCaseName = "AN-FR-47: Verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.flockBirdBreed+""+Test_Elements.sitesShowFilter;
		objFilter.FilterSort = Test_Elements.sitesSortFilter+""+Test_Elements.flockBirdBreed;
		objFilter.FilterApply = Test_Elements.flockBirdBreed+""+Test_Elements.sitesApplyFilter;
		objFilter.FilterClear = Test_Elements.flockBirdBreed+""+Test_Elements.sitesClearFilter;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Processing Date Filter";
		objTmp.TestCaseName = "AN-FR-48: Verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.flockProcessingDate+""+Test_Elements.sitesShowFilter;
		objFilter.FilterSort = Test_Elements.sitesSortFilter+""+Test_Elements.flockProcessingDate;
		objFilter.FilterApply = Test_Elements.flockProcessingDate+""+Test_Elements.sitesApplyFilter;
		objFilter.FilterClear = Test_Elements.flockProcessingDate+""+Test_Elements.sitesClearFilter;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Processing Site ID Filter";
		objTmp.TestCaseName = "AN-FR-49: Verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.flockProcessingSiteID+""+Test_Elements.sitesShowFilter;
		objFilter.FilterSort = Test_Elements.sitesSortFilter+""+Test_Elements.flockProcessingSiteID;
		objFilter.FilterApply = Test_Elements.flockProcessingSiteID+""+Test_Elements.sitesApplyFilter;
		objFilter.FilterClear = Test_Elements.flockProcessingSiteID+""+Test_Elements.sitesClearFilter;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		return lstFlockRegistrationModel;
	}


	public static ArrayList<FlockRegistrationModel> pagination() {
		ArrayList<FlockRegistrationModel> lstFlockRegistrationModel = new ArrayList<FlockRegistrationModel>();
		FlockRegistrationModel objTmp = new FlockRegistrationModel();

		objTmp.TestCaseName = "AN-FR-50: Verify pagination exist on Salmonella Log report";
		objTmp.TestCaseDescription = "This testcase will verify that pagination exist on Salmonella Log report";
		objTmp.paginationExist = true;
		objTmp.paginationLastPage = false;
		objTmp.paginationPreviousPage = false;
		objTmp.paginationFirstPage = false;
		objTmp.paginationNextPage = false;
		objTmp.lstFilters = new ArrayList<>();
		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "Pagination exists";
		objFilter.paginationPage = "next-page";
		objFilter.paginationCount = Test_Elements.slTotalPages;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objTmp.TestCaseName = "AN-FR-51: Verify user navigates to last page on clicking '>>' button in pagnation";
		objTmp.TestCaseDescription = "This testcase will verify that user navgates to last page on clicking '>>' button in pagnation";
		objTmp.paginationExist = false;
		objTmp.paginationLastPage = true;
		objTmp.paginationPreviousPage = false;
		objTmp.paginationFirstPage = false;
		objTmp.paginationNextPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "'>>'";
		objFilter.paginationPage = "last-page";
		objFilter.paginationCount = Test_Elements.slTotalPages;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objTmp.TestCaseName = "AN-FR-52: Verify user navigates to previous page on clicking '<' button in pagnation";
		objTmp.TestCaseDescription = "This testcase will verify that user navgates to previous page on clicking '<' button in pagnation";
		objTmp.paginationExist = false;
		objTmp.paginationLastPage = false;
		objTmp.paginationPreviousPage = true;
		objTmp.paginationFirstPage = false;
		objTmp.paginationNextPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "'<'";
		objFilter.paginationPage = "previous-page";
		objFilter.paginationCount = Test_Elements.slTotalPages;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objTmp.TestCaseName = "AN-FR-53: Verify user navigates to first page on clicking '<<' button in pagnation";
		objTmp.TestCaseDescription = "This testcase will verify that user navgates to first page on clicking '<<' button in pagnation";
		objTmp.paginationExist = false;
		objTmp.paginationLastPage = false;
		objTmp.paginationPreviousPage = false;
		objTmp.paginationFirstPage = true;
		objTmp.paginationNextPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "'<<'";
		objFilter.paginationPage = "first-page";
		objFilter.paginationCount = Test_Elements.slTotalPages;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objTmp.TestCaseName = "AN-FR-54: Verify user navigates to next page on clicking '>' button in pagnation";
		objTmp.TestCaseDescription = "This testcase will verify that user navgates to next page on clicking '>' button in pagnation";
		objTmp.paginationExist = false;
		objTmp.paginationLastPage = false;
		objTmp.paginationPreviousPage = false;
		objTmp.paginationFirstPage = false;
		objTmp.paginationNextPage = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "'>'";
		objFilter.paginationPage = "next-page";
		objFilter.paginationCount = Test_Elements.slTotalPages;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		return lstFlockRegistrationModel;
	}


	public static ArrayList<FlockRegistrationModel> searchRows() {
		ArrayList<FlockRegistrationModel> lstFlockRegistrationModel = new ArrayList<FlockRegistrationModel>();
		FlockRegistrationModel objTmp = new FlockRegistrationModel();

		objTmp.TestCaseName = "AN-FR-55: Verify 100 rows are displayed when 100 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 100 rows are displayed when 100 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-FR-56: Verify 100 rows are displayed when 100 Rows per Page is selected and user moves to next page";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that 100 rows are displayed when 100 Rows per Page is selected and user moves to next page";
		objTmp.lstFilters = new ArrayList<>();
		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "100 Rows per Page";
		objFilter.FilterListXPathSearch = "rows-100";
		objFilter.count = "100";
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);


		objTmp = new FlockRegistrationModel();
		objTmp.TestCaseName = "AN-FR-57: Verify 250 rows are displayed when 250 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 250 rows are displayed when 250 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-FR-58: Verify 250 rows are displayed when 250 Rows per Page is selected and user moves to next page";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that 250 rows are displayed when 250 Rows per Page is selected and user moves to next page";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "250 Rows per Page";
		objFilter.FilterListXPathSearch = "rows-250";
		objFilter.count = "250";
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);


		objTmp = new FlockRegistrationModel();
		objTmp.TestCaseName = "AN-FR-59: Verify 500 rows are displayed when 500 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 100 rows are displayed when 500 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-FR-60: Verify 500 rows are displayed when 500 Rows per Page is selected and user moves to next page";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that 500 rows are displayed when 500 Rows per Page is selected and user moves to next page";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "500 Rows per Page";
		objFilter.FilterListXPathSearch = "rows-500";  
		objFilter.count = "500"; 
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		return lstFlockRegistrationModel;	
	}


	public static ArrayList<FlockRegistrationModel> sorting() {
		ArrayList<FlockRegistrationModel> lstFlockRegistrationModel = new ArrayList<FlockRegistrationModel>();
		FlockRegistrationModel objTmp;
		ReportFilters objFilter;

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Integrator Flock ID";
		objTmp.TestCaseName = "AN-FR-61: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockIntegratorID;           
		objFilter.count = Test_Elements.flockIntegratorIDCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site ID";
		objTmp.TestCaseName = "AN-FR-62: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockSiteID;            
		objFilter.count = Test_Elements.flockSiteIDCol;
		objTmp.sortDescFirst = true;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Placement Date";
		objTmp.TestCaseName = "AN-FR-65: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockPlacementDate;           
		objFilter.count = Test_Elements.flockPlacementDateCol;
		objTmp.sortDescFirst = true;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Num. Birds Placed";
		objTmp.TestCaseName = "AN-FR-66: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockNumBirdsPlaced;           
		objFilter.count = Test_Elements.flockNumBirdsPlacedCol;
		objTmp.sortDescFirst = true;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Bird Sex";
		objTmp.TestCaseName = "AN-FR-68: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockBirdSex;           
		objFilter.count = Test_Elements.flockBirdSexCol;
		objTmp.sortDescFirst = true;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Bird Breed";
		objTmp.TestCaseName = "AN-FR-69: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockBirdBreed;           
		objFilter.count = Test_Elements.flockBirdBreedCol;
		objTmp.sortDescFirst = true;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Processing Date";
		objTmp.TestCaseName = "AN-FR-70: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockProcessingDate;           
		objFilter.count = Test_Elements.flockProcessingDateCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Processing Site ID";
		objTmp.TestCaseName = "AN-FR-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesCreatedBy;           
		objFilter.count = Test_Elements.sitesCreatedByCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Num Birds DOA Plant";
		objTmp.TestCaseName = "AN-FR-72: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockNumBirdsDOA;           
		objFilter.count = Test_Elements.flockNumBirdsDOACol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);


		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Num Birds Processed";
		objTmp.TestCaseName = "AN-FR-73: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockNumBirdsProcessed;           
		objFilter.count = Test_Elements.flockNumBirdsProcessedCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);
		
		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Total Weight Processed LB";
		objTmp.TestCaseName = "AN-FR-74: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockTotalWeightProcessedLB;           
		objFilter.count = Test_Elements.flockTotalWeightProcessedLBCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Total Weight Processed KG";
		objTmp.TestCaseName = "AN-FR-74: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockTotalWeightProcessedKG;           
		objFilter.count = Test_Elements.flockTotalWeightProcessedKGCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Total Feed Weight LB";
		objTmp.TestCaseName = "AN-FR-75: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockTotalFeedWeightLB;           
		objFilter.count = Test_Elements.flockTotalFeedWeightLBCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);
		
		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Total Feed Weight KG";
		objTmp.TestCaseName = "AN-FR-75: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockTotalFeedWeightKG;           
		objFilter.count = Test_Elements.flockTotalFeedWeightKGCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Total Weight Condemned LB";
		objTmp.TestCaseName = "AN-FR-76: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockTotalWeightCondemendLB;           
		objFilter.count = Test_Elements.flockTotalWeightCondemendLB;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Total Weight Condemned KG";
		objTmp.TestCaseName = "AN-FR-77: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockTotalWeightCondemendKG;           
		objFilter.count = Test_Elements.flockTotalWeightCondemnedKGCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Num Birds Condemned Whole";
		objTmp.TestCaseName = "AN-FR-78: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockNumBirdsCondemnedWhole;           
		objFilter.count = Test_Elements.flockNumBirdsCondemnedWholeCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

//		objTmp = new FlockRegistrationModel();
//		objFilter = new ReportFilters();
//		objFilter.FilterName = "Bird Weight Condemned LB";
//		objTmp.TestCaseName = "AN-FR-79: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
//		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockBirdsWeightCondemnedLB;           
//		objFilter.count = Test_Elements.flockBirdsWeightCondemnedLBCol;
//		objTmp.sortDescFirst = false;
//		objTmp.lstFilters.add(objFilter);
//		lstFlockRegistrationModel.add(objTmp);
//
//		objTmp = new FlockRegistrationModel();
//		objFilter = new ReportFilters();
//		objFilter.FilterName = "Bird Weight Condemned KG";
//		objTmp.TestCaseName = "AN-FR-80: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
//		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockBirdsWeightCondemnedKG;           
//		objFilter.count = Test_Elements.flockBirdsWeightCondemnedKGCol;
//		objTmp.sortDescFirst = false;
//		objTmp.lstFilters.add(objFilter);
//		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Parts Weight Condemned LB";
		objTmp.TestCaseName = "AN-FR-81: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockPartsWeightCondemnedLB;           
		objFilter.count = Test_Elements.flockPartsWeightCondemnedLBCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Parts Weight Condemned KG";
		objTmp.TestCaseName = "AN-FR-82: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockPartsWeightCondemnedKG;           
		objFilter.count = Test_Elements.flockPartsWeightCondemnedKGCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objFilter = new ReportFilters();
		objFilter.FilterName = "Total Grower Pay";
		objTmp.TestCaseName = "AN-FR-83: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockTotalGrowerPay;           
		objFilter.count = Test_Elements.flockTotalGrowerPayCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Amount Paid Per Pound";
		objTmp.TestCaseName = "AN-FR-83: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockAmountPaidPerPound;           
		objFilter.count = Test_Elements.flockAmountPaidPePoundCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Total Cost Per Weight LB";
		objTmp.TestCaseName = "AN-FR-85: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockTotalCostUSDPerWeight;           
		objFilter.count = Test_Elements.flockTotalCostPerWeightCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Livability Percentage";
		objTmp.TestCaseName = "AN-FR-86: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockLiveability;           
		objFilter.count = Test_Elements.flockLiveabilityCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Mortality Percentage";
		objTmp.TestCaseName = "AN-FR-87: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockMortality;           
		objFilter.count = Test_Elements.flockMortalityCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);


		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Avg Daily Weight Gain LB";
		objTmp.TestCaseName = "AN-FR-88: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockAvgDailyWeightGainLB;           
		objFilter.count = Test_Elements.flockAvgDailyWeightGainLBCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Avg Age of Bird Processed";
		objTmp.TestCaseName = "AN-FR-89: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockAvgAgeBirdsProcessed;           
		objFilter.count = Test_Elements.flockAvgAgeBirdsProcessedCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);


		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Avg Bird Weight LB";
		objTmp.TestCaseName = "AN-FR-90: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockAvgBirdWeightLB;           
		objFilter.count = Test_Elements.flockAvgBirdWeightLBCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);


		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Avg Bird Weight KG";
		objTmp.TestCaseName = "AN-FR-91: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockAvgBirdWeightKG;           
		objFilter.count = Test_Elements.flockAvgBirdWeightKGCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);


		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "FCR";
		objTmp.TestCaseName = "AN-FR-92: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockFCR;           
		objFilter.count = Test_Elements.flockFCRCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Num Birds Sold";
		objTmp.TestCaseName = "AN-FR-93: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockNumBirdsSold;           
		objFilter.count = Test_Elements.flockNumBirdsSoldCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);


		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "ADJ. FCR";
		objTmp.TestCaseName = "AN-FR-94: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockAdjFCR;           
		objFilter.count = Test_Elements.flockAdjFCRCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "A Grade Paws Perc";
		objTmp.TestCaseName = "AN-FR-95: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockAGradePaws;           
		objFilter.count = Test_Elements.flockAGradePawsCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Out Time Days";
		objTmp.TestCaseName = "AN-FR-97: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockOutTimeDays;           
		objFilter.count = Test_Elements.flockOutTimeDaysCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);
		
		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Out Time Days";
		objTmp.TestCaseName = "AN-FR-97: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockOutTimeDays;           
		objFilter.count = Test_Elements.flockOutTimeDaysCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Bird Size";
		objTmp.TestCaseName = "AN-FR-97: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockBirdSize;           
		objFilter.count = Test_Elements.flockBirdSizeCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);
		
		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Marketing Program";
		objTmp.TestCaseName = "AN-FR-97: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockMarketingProgram;           
		objFilter.count = Test_Elements.flockMarketingProgramCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);
		
		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "USDA Plant ID";
		objTmp.TestCaseName = "AN-FR-97: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockUSDAPlantID;           
		objFilter.count = Test_Elements.flockUSDAPlantIDCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);
			
		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "USDA Plant ID";
		objTmp.TestCaseName = "AN-FR-97: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockDowngradePawsPerc;           
		objFilter.count = Test_Elements.flockDowngradePawsPercCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);
		
		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Total Mortality";
		objTmp.TestCaseName = "AN-FR-97: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockTotalMortality;           
		objFilter.count = Test_Elements.flockTotalMortalityCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);
		
		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Time Frame";
		objTmp.TestCaseName = "AN-FR-97: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockTimeFrame;           
		objFilter.count = Test_Elements.flockTimeFrameCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);
		
		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Unique Flock ID";
		objTmp.TestCaseName = "AN-FR-98: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockUniqueFlockID;           
		objFilter.count = Test_Elements.flockUniqueFlockIDCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);
		
		return lstFlockRegistrationModel;	
	}	


	public static ArrayList<FlockRegistrationModel> EditFlock() {
		ArrayList<FlockRegistrationModel> lstFlockRegistrationModel = new ArrayList<FlockRegistrationModel>();
		FlockRegistrationModel objTmp;
		ReportFilters objFilter;			

//		objTmp = new FlockRegistrationModel();
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
//		lstFlockRegistrationModel.add(objTmp);

//		objTmp = new FlockRegistrationModel();
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
//		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Bird Sex";
		objTmp.TestCaseName = "AN-FR-101: Verify user can edit "+objFilter.FilterName+" and it is reflected in audit log";
		objTmp.TestCaseDescription = "This testcase will verify that user can edit "+objFilter.FilterName+" and a new row appears in audit log with changes made";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.flockBirdSex;
		objFilter.ColumnID = Test_Elements.flockBirdSexCol;
		objTmp.input = "Male";
		objTmp.steps = "Edit "+objFilter.FilterName;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Bird Breed";
		objTmp.TestCaseName = "AN-FR-102: Verify user can edit "+objFilter.FilterName+" and it is reflected in audit log";
		objTmp.TestCaseDescription = "This testcase will verify that user can edit "+objFilter.FilterName+" and a new row appears in audit log with changes made";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.flockBirdBreed;
		objFilter.ColumnID = Test_Elements.flockBirdBreedCol;
		objTmp.input = "Australorp";
		objTmp.steps = "Edit "+objFilter.FilterName;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		return lstFlockRegistrationModel;
	}


	public static ArrayList<FlockRegistrationModel> FlockValidation() {
		ArrayList<FlockRegistrationModel> lstFlockRegistrationModel = new ArrayList<FlockRegistrationModel>();
		FlockRegistrationModel objTmp;
		ReportFilters objFilter;		

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-FR-47: Verify that error appears when total weight condemend is less than birds weight and parts weights";
		objTmp.TestCaseDescription = "This test case will verify that error appears when total weight condemend is less than birds weight and parts weights";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(18, 23, 1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("200", "2000", DataUploadModel.FarmSiteID));
		objFilter.ColumnID = Test_Elements.flockTotalWeightCondemnedKGCol;
		objTmp.errorCase = true;
		objTmp.steps = "Enter total weight condemned kg less than birds weight and parts weights";
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);
		
		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-FR-48: Verify that no error appears when total weight condemend is less than birds weight and parts weights";
		objTmp.TestCaseDescription = "This test case will verify that no error appears when total weight condemend is less than birds weight and parts weights";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(18, 23));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("2500", "300"));
		objFilter.ColumnID = Test_Elements.flockTotalWeightCondemnedKGCol;
		objTmp.errorCase = false;
		objTmp.steps = "Enter total weight condemned kg less than birds weight and parts weights";
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);
		
		
		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-FR-49: Verify that error appears when total weight condemend lb is less than birds weight and parts weights";
		objTmp.TestCaseDescription = "This test case will verify that error appears when total weight condemend lb is less than birds weight and parts weights";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(17, 22));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("200", "2000"));
		objFilter.ColumnID = Test_Elements.flockTotalWeightCondemnedLBCol;
		objTmp.errorCase = true;
		objTmp.steps = "Enter total weight condemned kg less than birds weight and parts weights";
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);
		
		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-FR-50: Verify that error appears when total weight condemend lb is less than birds weight and parts weights";
		objTmp.TestCaseDescription = "This test case will verify that error appears when total weight condemend lbis less than birds weight and parts weights";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(17, 22));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("2500", "300"));
		objFilter.ColumnID = Test_Elements.flockTotalWeightCondemnedLBCol;
		objTmp.errorCase = false;
		objTmp.steps = "Enter total weight condemned lb less than birds weight and parts weights";
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);
		
		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-FR-50: Verify that system should not accept Number of Birds Processed greater than Number of Birds Placed";
		objTmp.TestCaseDescription = "This test case will verify that system should not accept Number of Birds Processed greater than Number of Birds Placed";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(5, 12));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("10", "20"));
		objFilter.ColumnID = Test_Elements.flockNumBirdsProcessedCol;
		objTmp.errorCase = true;
		objTmp.steps = "Enter Number of Birds Processed greater than Number of Birds Placed";
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);
		
		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-FR-51: Verify that system accept Number of Birds Processed less than Number of Birds Placed";
		objTmp.TestCaseDescription = "This test case will verify that system should accept Number of Birds Processed less than Number of Birds Placed";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(5, 12));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("20", "10"));
		objFilter.ColumnID = Test_Elements.flockNumBirdsProcessedCol;
		objTmp.errorCase = false;
		objTmp.steps = "Enter Number of Birds Processed less than Number of Birds Placed";
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);
		
//		objTmp = new FlockRegistrationModel();
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
//		lstFlockRegistrationModel.add(objTmp);
//		
//		objTmp = new FlockRegistrationModel();
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
//		lstFlockRegistrationModel.add(objTmp);
		
		return lstFlockRegistrationModel;
	}
}