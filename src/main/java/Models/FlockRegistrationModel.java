package Models;

import java.util.ArrayList;
import java.util.Arrays;
import Test.Ancera.Test_Elements;
import Test.Ancera.MetaData.DataUpload;

public class FlockRegistrationModel {

	public String TestCaseNameButtonActive;
	public String TestCaseDescriptionButtonActive;
	public String TestCaseName;
	public String TestCaseDescription;
	public String TestCaseNameSearch;
	public String TestCaseDescriptionSearch;
	public String TestCaseNameBubbleFilterTop;
	public String TestCaseDescriptionBubbleFilterTop;
	public String TestCaseNameBubbleFilterCheckbox;
	public String TestCaseDescriptionBubbleFilterCheckbox;
	public String TestCaseNameClearInput;
	public String TestCaseDescClearInput;
	public String TestCaseNameHoverReset;
	public String TestCaseDescriptionHoverReset;
	public String TestCaseNameRevertBack;
	public String TestCaseDescriptionRevertBack;
	public String TestCaseNameSort;
	public String TestCaseDescriptionSort;
	public ArrayList<ReportFilters> lstFilters;
	public boolean ReloadPage;
	public boolean Filter1;
	public boolean Filter2;
	public boolean Filter3;
	public boolean paginationExist;
	public boolean paginationLastPage;
	public boolean paginationNextPage;
	public boolean paginationFirstPage;
	public boolean paginationPreviousPage;
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
	public boolean sortDescFirst;
	public boolean errorCase;
	public String steps;
	public String FilterHideID;
	public String FilterUnHideID;
	public String input;
	public String fileJson;
	public String sampleID;

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
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2"));
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
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp); 

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Hatch Date Filter";
		objTmp.TestCaseName = "AN-FR-06: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-FR-07: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList(Test_Elements.flockHatchDate));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp); 

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
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp); 

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Bird Type Filter";
		objTmp.TestCaseName = "AN-FR-10: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-FR-11: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList(Test_Elements.flockBirdType));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp); 

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
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2"));
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
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2"));
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
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2"));
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
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2"));
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
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList(Test_Elements.flockIntegratorID,Test_Elements.flockSiteID));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2"));
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
		objFilter.FilterName = "Site ID Filter";
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
		objFilter.FilterName = "Site ID Filter";
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
		objFilter.FilterName = "Bird Type Filter";
		objTmp.TestCaseName = "AN-FR-26: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.flockBirdType;
		objFilter.FilterXPath = Test_Elements.flockBirdType;
		objTmp.startWith = true;
		objTmp.contains = false;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.flockBirdTypeCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);  

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Bird Type Filter";
		objTmp.TestCaseName = "AN-FR-27: Verify wild card Contains search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Contains search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.flockBirdType;
		objFilter.FilterXPath = Test_Elements.flockBirdType;
		objTmp.startWith = false;
		objTmp.contains = true;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.flockBirdTypeCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp); 

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Bird Type Filter";
		objTmp.TestCaseName = "AN-FR-28: Verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.flockBirdType;
		objFilter.FilterXPath = Test_Elements.flockBirdType;
		objTmp.startWith = false;
		objTmp.contains = false;
		objTmp.endsWith = true;
		objFilter.ColumnID = Test_Elements.flockBirdTypeCol;
		objTmp.input = "A";
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

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Hatch Date Filter";
		objTmp.TestCaseName = "AN-FR-43: Verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.flockHatchDate+""+Test_Elements.sitesShowFilter;
		objFilter.FilterSort = Test_Elements.sitesSortFilter+""+Test_Elements.flockHatchDate;
		objFilter.FilterApply = Test_Elements.flockHatchDate+""+Test_Elements.sitesApplyFilter;
		objFilter.FilterClear = Test_Elements.flockHatchDate+""+Test_Elements.sitesClearFilter;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

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

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Bird Type Filter";
		objTmp.TestCaseName = "AN-FR-45: Verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.flockBirdType+""+Test_Elements.sitesShowFilter;
		objFilter.FilterSort = Test_Elements.sitesSortFilter+""+Test_Elements.flockBirdType;
		objFilter.FilterApply = Test_Elements.flockBirdType+""+Test_Elements.sitesApplyFilter;
		objFilter.FilterClear = Test_Elements.flockBirdType+""+Test_Elements.sitesClearFilter;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

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
		objTmp.TestCaseName = "AN-FR-47: Verify Lock Filter functionality on "+objFilter.FilterName;
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
		objTmp.TestCaseName = "AN-FR-47: Verify Lock Filter functionality on "+objFilter.FilterName;
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
		objFilter.FilterName = "Hatch Date";
		objTmp.TestCaseName = "AN-FR-63: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockHatchDate;            
		objFilter.count = Test_Elements.flockHatchDateCol;
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
		objFilter.FilterName = "Bird Type";
		objTmp.TestCaseName = "AN-FR-67: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockBirdType;           
		objFilter.count = Test_Elements.flockBirdTypeCol;
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
		objFilter.FilterName = "Num Birds DOA Plant ID";
		objTmp.TestCaseName = "AN-FR-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockNumBirdsDOA;           
		objFilter.count = Test_Elements.flockNumBirdsDOACol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);


		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Num Birds Processed LB";
		objTmp.TestCaseName = "AN-FR-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockNumBirdsProcessed;           
		objFilter.count = Test_Elements.flockNumBirdsProcessedCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Total Weight Processed KG";
		objTmp.TestCaseName = "AN-FR-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
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
		objTmp.TestCaseName = "AN-FR-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockTotalFeedWeightLB;           
		objFilter.count = Test_Elements.flockTotalFeedWeightLBCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Total Weight Condemned LB";
		objTmp.TestCaseName = "AN-FR-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
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
		objTmp.TestCaseName = "AN-FR-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
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
		objTmp.TestCaseName = "AN-FR-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockNumBirdsCondemnedWhole;           
		objFilter.count = Test_Elements.flockNumBirdsCondemnedWholeCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Bird Weight Condemned LB";
		objTmp.TestCaseName = "AN-FR-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockBirdsWeightCondemnedLB;           
		objFilter.count = Test_Elements.flockBirdsWeightCondemnedLBCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Bird Weight Condemned KG";
		objTmp.TestCaseName = "AN-FR-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockBirdsWeightCondemnedKG;           
		objFilter.count = Test_Elements.flockBirdsWeightCondemnedKGCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Parts Weight Condemned LB";
		objTmp.TestCaseName = "AN-FR-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
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
		objTmp.TestCaseName = "AN-FR-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockPartsWeightCondemnedKG;           
		objFilter.count = Test_Elements.flockPartsWeightCondemnedKGCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Total Amount Paid";
		objTmp.TestCaseName = "AN-FR-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockTotalAmountPaid;           
		objFilter.count = Test_Elements.flockTotalAmountPaidCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Total Amount Paid Weight LB";
		objTmp.TestCaseName = "AN-FR-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockTotalAmountPaidWeightLB;           
		objFilter.count = Test_Elements.flockTotalAmountPaidWeightLBCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Total Cost Per Weight LB";
		objTmp.TestCaseName = "AN-FR-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockTotalCostPerWeight;           
		objFilter.count = Test_Elements.flockTotalCostPerWeightCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Livability Percentage";
		objTmp.TestCaseName = "AN-FR-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
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
		objTmp.TestCaseName = "AN-FR-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
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
		objTmp.TestCaseName = "AN-FR-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
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
		objTmp.TestCaseName = "AN-FR-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
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
		objTmp.TestCaseName = "AN-FR-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
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
		objTmp.TestCaseName = "AN-FR-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
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
		objTmp.TestCaseName = "AN-FR-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
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
		objTmp.TestCaseName = "AN-FR-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
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
		objTmp.TestCaseName = "AN-FR-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
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
		objTmp.TestCaseName = "AN-FR-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockAGradePaws;           
		objFilter.count = Test_Elements.flockAGradePawsCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Last 7 Days Mortality Perc";
		objTmp.TestCaseName = "AN-FR-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockLast7Days;           
		objFilter.count = Test_Elements.flockLast7DaysCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);


		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Out Time Days";
		objTmp.TestCaseName = "AN-FR-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.flockOutTimeDays;           
		objFilter.count = Test_Elements.flockOutTimeDaysCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		return lstFlockRegistrationModel;	
	}	


	public static ArrayList<FlockRegistrationModel> EditFlock() {
		ArrayList<FlockRegistrationModel> lstFlockRegistrationModel = new ArrayList<FlockRegistrationModel>();
		FlockRegistrationModel objTmp;
		ReportFilters objFilter;			

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Integrator Site ID";
		objTmp.TestCaseName = "AN-FR-73: Verify user can edit "+objFilter.FilterName+" and it is reflected in audit log";
		objTmp.TestCaseDescription = "This testcase will verify that user can edit "+objFilter.FilterName+" and a new row appears in audit log with changes made";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.flockIntegratorID;
		objFilter.ColumnID = Test_Elements.flockIntegratorIDCol;
		objTmp.input = "IntegratorID_TA";
		objTmp.steps = "Edit "+objFilter.FilterName;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Bird Type";
		objTmp.TestCaseName = "AN-FR-74: Verify user can edit "+objFilter.FilterName+" and it is reflected in audit log";
		objTmp.TestCaseDescription = "This testcase will verify that user can edit "+objFilter.FilterName+" and a new row appears in audit log with changes made";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.flockBirdType;
		objFilter.ColumnID = Test_Elements.flockBirdTypeCol;
		objTmp.input = "Chicken";
		objTmp.steps = "Edit "+objFilter.FilterName;
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);

		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Bird Sex";
		objTmp.TestCaseName = "AN-FR-75: Verify user can edit "+objFilter.FilterName+" and it is reflected in audit log";
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
		objTmp.TestCaseName = "AN-FR-76: Verify user can edit "+objFilter.FilterName+" and it is reflected in audit log";
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
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(17, 20));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("200", "2000"));
		objFilter.ColumnID = Test_Elements.flockTotalWeightCondemnedKGCol;
		objTmp.errorCase = true;
		objTmp.steps = "Enter total weight condemned kg less than birds weight and parts weights";
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);
		
		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-FR-48: Verify that error appears when total weight condemend is less than birds weight and parts weights";
		objTmp.TestCaseDescription = "This test case will verify that error appears when total weight condemend is less than birds weight and parts weights";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(17, 20));
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
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(16, 19));
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
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(16, 19));
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
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(4, 11));
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
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(4, 11));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("20", "10"));
		objFilter.ColumnID = Test_Elements.flockNumBirdsProcessedCol;
		objTmp.errorCase = false;
		objTmp.steps = "Enter Number of Birds Processed less than Number of Birds Placed";
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);
		
		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-FR-52: Verify that system should not accept Number of Birds Processed greater than Number of Birds Placed";
		objTmp.TestCaseDescription = "This test case will verify that system should not accept Number of Birds Processed greater than Number of Birds Placed";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(10, 4));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("50", "10"));
		objFilter.ColumnID = Test_Elements.flockNumBirdsProcessedCol;
		objTmp.errorCase = true;
		objTmp.steps = "Enter Number of Birds Processed greater than Number of Birds Placed";
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);
		
		objTmp = new FlockRegistrationModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-FR-53: Verify that system accept Number of Birds Processed less than Number of Birds Placed";
		objTmp.TestCaseDescription = "This test case will verify that system should accept Number of Birds Processed less than Number of Birds Placed";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(10, 4));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("10", "50"));
		objFilter.ColumnID = Test_Elements.flockNumBirdsProcessedCol;
		objTmp.errorCase = false;
		objTmp.steps = "Enter Number of Birds Processed less than Number of Birds Placed";
		objTmp.lstFilters.add(objFilter);
		lstFlockRegistrationModel.add(objTmp);
		
/*
		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-48: Verify that user can upload Flock Metadata with Hatch Date less than Placement Date";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Flock Metadata with Hatch Date less than Placement Date";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(2, 3));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("2021-06-01", "2021-06-05"));
		objTmp.steps = "Enter Hatch Date less than Placement Date and upload the Flock Metadata";
		objTmp.AlertMessage = DataUpload.flockFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Flock Metadata with Hatch Date less than Placement Date successfully";
		objTmp.failStep = "User was not able to upload Flock Metadata with Hatch Date less than Placement Date";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-49: Verify that user cannot upload Flock Metadata with Placement Date greater than Processing Date";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Placement Date greater than Processing Date";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(8, 3));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("2021-06-01", "2021-06-10"));
		objTmp.steps = "Enter Placement Date greater than Processing Date and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with Placement Date greater than Processing Date successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Placement Date greater than Processing Date";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-50: Verify that user can upload Flock Metadata with Placement Date less than Processing Date";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Flock Metadata with Placement Date less than Processing Date";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(8, 3, 2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("2021-06-10", "2021-06-01", "2021-05-25"));
		objTmp.steps = "Enter Placement Date less than Processing Date and upload the Flock Metadata";
		objTmp.AlertMessage = DataUpload.flockFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Flock Metadata with Placement Date less than Processing Date successfully";
		objTmp.failStep = "User was not able to upload Flock Metadata with Placement Date less than Processing Date";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-51: Verify that user cannot upload Flock Metadata with Organization Site ID in Processing Site ID field";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Organization Site ID in Processing Site ID field";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(9));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(OrganizationSiteID));
		objTmp.steps = "Enter Organization Site ID in Processing Site ID field and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with Organization Site ID in Processing Site ID field successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Organization Site ID in Processing Site ID field";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-52: Verify that user cannot upload Flock Metadata with Region Site ID in Processing Site ID field";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Region Site ID in Processing Site ID field";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(9));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(RegionSiteID));
		objTmp.steps = "Enter Region Site ID in Processing Site ID field and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with Region Site ID in Processing Site ID field successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Region Site ID in Processing Site ID field";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-53: Verify that user cannot upload Flock Metadata with Sub-Region Site ID in Processing Site ID field";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Sub-Region Site ID in Processing Site ID field";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(9));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(SubRegionSiteID));
		objTmp.steps = "Enter Sub-Region Site ID in Processing Site ID field and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with Sub-Region Site ID in Processing Site ID field successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Sub-Region Site ID in Processing Site ID field";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-54: Verify that user cannot upload Flock Metadata with Complex Site ID in Processing Site ID field";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Complex Site ID in Processing Site ID field";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(9));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(ComplexSiteID));
		objTmp.steps = "Enter Complex Site ID in Processing Site ID field and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with Complex Site ID in Processing Site ID field successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Complex Site ID in Processing Site ID field";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-55: Verify that user cannot upload Flock Metadata with House Site ID in Processing Site ID field";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with House Site ID in Processing Site ID field";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(9));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(HouseSiteID));
		objTmp.steps = "Enter House Site ID in Processing Site ID field and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with House Site ID in Processing Site ID field successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with House Site ID in Processing Site ID field";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-56: Verify that user cannot upload Flock Metadata with Farm Site ID in Processing Site ID field";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Farm Site ID in Processing Site ID field";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(9));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(FarmSiteID));
		objTmp.steps = "Enter Farm Site ID in Processing Site ID field and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with Farm Site ID in Processing Site ID field successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Farm Site ID in Processing Site ID field";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-57: Verify that user can upload Flock Metadata with Processing Plant Site ID in Processing Site ID field";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Flock Metadata with Processing Plant Site ID in Processing Site ID field";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(9));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(ProcessingPlantSiteID));
		objTmp.steps = "Enter Processing Plant Site ID in Processing Site ID field and upload the Flock Metadata";
		objTmp.AlertMessage = DataUpload.flockFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Flock Metadata with Processing Plant Site ID in Processing Site ID field successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Processing Plant Site ID in Processing Site ID field";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-58: Verify that user cannot upload Flock Metadata with Number of Birds DOA at Plant greater than Number of Birds Placed";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Number of Birds DOA at Plant greater than Number of Birds Placed";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(10, 4));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("100", "80"));
		objTmp.steps = "Enter Number of Birds DOA at Plant greater than Number of Birds Placed and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with Number of Birds DOA at Plant greater than Number of Birds Placed successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Number of Birds DOA at Plant greater than Number of Birds Placed";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-59: Verify that user can upload Flock Metadata with Number of Birds DOA at Plant less than Number of Birds Placed";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Number of Birds DOA at Plant less than Number of Birds Placed";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(10, 4));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("80", "100"));
		objTmp.steps = "Enter Number of Birds DOA at Plant less than Number of Birds Placed and upload the Flock Metadata";
		objTmp.AlertMessage = DataUpload.flockFileName+" loaded successfully.";
		objTmp.passStep = "User was not able to upload Flock Metadata with Number of Birds DOA at Plant less than Number of Birds Placed successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Number of Birds DOA at Plant less than Number of Birds Placed";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-60: Verify that user cannot upload Flock Metadata with Number of Birds Processed greater than Number of Birds Placed";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Number of Birds Processed greater than Number of Birds Placed";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(11, 4));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("100", "80"));
		objTmp.steps = "Enter Number of Birds DOA at Plant greater than Number of Birds Processed and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with Number of Birds Processed greater than Number of Birds Placed successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Number of Birds Processed greater than Number of Birds Placed";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-61: Verify that user can upload Flock Metadata with Number of Birds Processed less than Number of Birds Placed";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Flock Metadata with Number of Birds Processed less than Number of Birds Placed";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(11, 4));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("80", "100"));
		objTmp.steps = "Enter Number of Birds Processed less than Number of Birds Placed and upload the Flock Metadata";
		objTmp.AlertMessage = DataUpload.flockFileName+" loaded successfully.";
		objTmp.passStep = "User was not able to upload Flock Metadata with Number of Birds Processed less than Number of Birds Placed successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Number of Birds Processed less than Number of Birds Placed";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-62: Verify that user cannot upload Flock Metadata with Total Weight_Condemned_LB <= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Total Weight_LB Condemned Must be <= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(16, 19, 21));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("50", "100", "100"));
		objTmp.steps = "Enter Total Weight_Condemned_LB <= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with Total Weight_LB Condemned Must be <= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Total Weight_LB Condemned Must be <= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-63: Verify that user can upload Flock Metadata with Total Weight_LB Condemned Must be >= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Flock Metadata with Total Weight_LB Condemned Must be >= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(16, 19, 21));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("150", "80", "40"));
		objTmp.steps = "Enter Total Weight_LB Condemned Must be >= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB and upload the Flock Metadata";
		objTmp.AlertMessage = DataUpload.flockFileName+" loaded successfully.";
		objTmp.passStep = "User was not able to upload Flock Metadata with Total Weight_LB Condemned Must be >= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Total Weight_LB Condemned Must be >= PARTS_CONDEMNED_LB + BIRD_CONDEMNED_LB";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-64: Verify that user cannot upload Flock Metadata with Total Weight_KG Condemned Must be <= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Flock Metadata with Total Weight_KG Condemned Must be <= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(17, 20, 22));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("150", "100", "100"));
		objTmp.steps = "Enter Total Weight_LB Condemned Must be <= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG and upload the Flock Metadata";
		objTmp.AlertMessage = "Errors found in "+DataUpload.flockFileName;
		objTmp.passStep = "User was not able to upload Flock Metadata with Total Weight_KG Condemned Must be <= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Total Weight_KG Condemned Must be <= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-65: Verify that user can upload Flock Metadata with Total Weight_KG Condemned Must be >= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Flock Metadata with Total Weight_KG Condemned Must be >= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(17, 20, 22));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("150", "80", "40"));
		objTmp.steps = "Enter Total Weight_LB Condemned Must be >= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG and upload the Flock Metadata";
		objTmp.AlertMessage = DataUpload.flockFileName+" loaded successfully.";
		objTmp.passStep = "User was not able to upload Flock Metadata with Total Weight_KG Condemned Must be >= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG successfully";
		objTmp.failStep = "User was able to upload Flock Metadata with Total Weight_KG Condemned Must be >= PARTS_CONDEMNED_KG + BIRD_CONDEMNED_KG";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);
*/
		return lstFlockRegistrationModel;
	}




}


