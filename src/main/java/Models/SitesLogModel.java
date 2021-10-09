package Models;

import java.util.ArrayList;
import java.util.Arrays;
import Test.Ancera.Test_Elements;

public class SitesLogModel {

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
	public String FilterHideID;
	public String FilterUnHideID;
	public String input;
	public String fileJson;
	public String sampleID;

	public SitesLogModel() {

	}

	public static String applyFilterTitle = "Verify user can select checkbox from ";
	public static String applyFilterDesc = "This test case will verify that user can select checkbox from ";
	public static String filterIndicatorTitle = "Verify user can apply filter and table displays relevant results on applying ";
	public static String filterIndicatorDesc = "This test case will verify that user can apply filter and table displays relevant results on applying ";

	
	public static ArrayList<SitesLogModel> FillData() {
		ArrayList<SitesLogModel> lstSitesLogModel = new ArrayList<SitesLogModel>();
		SitesLogModel objTmp; 
		ReportFilters  objFilter;
		

		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site ID Filter";
		objTmp.TestCaseName = "AN-Sites-02: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-Sites-03: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("#"+Test_Elements.sitesSortFilter+""+Test_Elements.sitesSiteID));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1", "2"));
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp); 
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site Name Filter";
		objTmp.TestCaseName = "AN-Sites-04: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-Sites-05: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("#"+Test_Elements.sitesSortFilter+""+Test_Elements.sitesSiteName));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1", "2"));
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp); 

		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site Type Filter";
		objTmp.TestCaseName = "AN-Sites-06: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-Sites-07: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("#"+Test_Elements.sitesSortFilter+""+Test_Elements.sitesSiteType));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1", "2"));
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp); 
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "State Filter";
		objTmp.TestCaseName = "AN-Sites-08: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-Sites-09: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("#"+Test_Elements.sitesSortFilter+""+Test_Elements.sitesState));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1", "2"));
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp); 
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Zip Code Filter";
		objTmp.TestCaseName = "AN-Sites-10: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-Sites-11: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("#"+Test_Elements.sitesSortFilter+""+Test_Elements.sitesZipCode));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1", "2"));
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp); 
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Created Filter";
		objTmp.TestCaseName = "AN-Sites-12: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-Sites-13: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("#"+Test_Elements.sitesSortFilter+""+Test_Elements.sitesDateCreated));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1", "2"));
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp); 
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Created By Filter";
		objTmp.TestCaseName = "AN-Sites-14: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-Sites-15: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("#"+Test_Elements.sitesSortFilter+""+Test_Elements.sitesCreatedBy));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1", "2"));
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp); 
			
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Two Filters";
		objTmp.TestCaseName = "AN-Sites-16: Verify user can apply 2 filters at same time";
		objTmp.TestCaseDescription = "This testcase will verify that user can apply 2 filters at same time";
		objTmp.TestCaseNameSearch = "AN-Sites-17: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("#"+Test_Elements.sitesSortFilter+""+Test_Elements.sitesSiteID, "#"+Test_Elements.sitesSortFilter+""+Test_Elements.sitesSiteName));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1"));
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);

		return lstSitesLogModel;
	}

	
	public static ArrayList<SitesLogModel> Wildcard() {
		ArrayList<SitesLogModel> lstSitesLogModel = new ArrayList<SitesLogModel>();
		SitesLogModel objTmp = new SitesLogModel();

		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "Site ID Filter";
		objTmp.TestCaseName = "AN-Sites-20: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "#"+Test_Elements.sitesSortFilter+""+Test_Elements.sitesSiteID;
		objFilter.FilterXPath = Test_Elements.sitesSiteID;
		objTmp.startWith = true;
		objTmp.contains = false;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.sitesSiteIDCol;
		objTmp.input = "T";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("T", "t"));
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
			
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site ID Filter";
		objTmp.TestCaseName = "AN-Sites-21: Verify wild card Contains search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Contains search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "#"+Test_Elements.sitesSortFilter+""+Test_Elements.sitesSiteID;
		objFilter.FilterXPath = Test_Elements.sitesSiteID;
		objTmp.startWith = false;
		objTmp.contains = true;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.sitesSiteIDCol;
		objTmp.input = "a";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
	
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site ID Filter";
		objTmp.TestCaseName = "AN-Sites-22: Verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "#"+Test_Elements.sitesSortFilter+""+Test_Elements.sitesSiteID;
		objFilter.FilterXPath = Test_Elements.sitesSiteID;
		objTmp.startWith = false;
		objTmp.contains = false;
		objTmp.endsWith = true;
		objFilter.ColumnID = Test_Elements.sitesSiteIDCol;
		objTmp.input = "a";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);

		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site Name Filter";
		objTmp.TestCaseName = "AN-Sites-23: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "#"+Test_Elements.sitesSortFilter+""+Test_Elements.sitesSiteName;
		objFilter.FilterXPath = Test_Elements.sitesSiteName;
		objTmp.startWith = true;
		objTmp.contains = false;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.sitesSiteNameCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
			
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site Name Filter";
		objTmp.TestCaseName = "AN-Sites-24: Verify wild card Contains search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Contains search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "#"+Test_Elements.sitesSortFilter+""+Test_Elements.sitesSiteName;
		objFilter.FilterXPath = Test_Elements.sitesSiteName;
		objTmp.startWith = false;
		objTmp.contains = true;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.sitesSiteNameCol;
		objTmp.input = "V";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("V", "v"));
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site Name Filter";
		objTmp.TestCaseName = "AN-Sites-25: Verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "#"+Test_Elements.sitesSortFilter+""+Test_Elements.sitesSiteName;
		objFilter.FilterXPath = Test_Elements.sitesSiteName;
		objTmp.startWith = false;
		objTmp.contains = false;
		objTmp.endsWith = true;
		objFilter.ColumnID = Test_Elements.sitesSiteNameCol;
		objTmp.input = "a";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site Type Filter";
		objTmp.TestCaseName = "AN-Sites-26: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "#"+Test_Elements.sitesSortFilter+""+Test_Elements.sitesSiteType;
		objFilter.FilterXPath = Test_Elements.sitesSiteType;
		objTmp.startWith = true;
		objTmp.contains = false;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.sitesSiteTypeCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);  
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site Type Filter";
		objTmp.TestCaseName = "AN-Sites-27: Verify wild card Contains search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Contains search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "#"+Test_Elements.sitesSortFilter+""+Test_Elements.sitesSiteType;
		objFilter.FilterXPath = Test_Elements.sitesSiteType;
		objTmp.startWith = false;
		objTmp.contains = true;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.sitesSiteTypeCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp); 
	
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site Type Filter";
		objTmp.TestCaseName = "AN-Sites-28: Verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "#"+Test_Elements.sitesSortFilter+""+Test_Elements.sitesSiteType;
		objFilter.FilterXPath = Test_Elements.sitesSiteType;
		objTmp.startWith = false;
		objTmp.contains = false;
		objTmp.endsWith = true;
		objFilter.ColumnID = Test_Elements.sitesSiteTypeCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp); 
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "State Filter";
		objTmp.TestCaseName = "AN-Sites-29: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "#"+Test_Elements.sitesSortFilter+""+Test_Elements.sitesState;
		objFilter.FilterXPath = Test_Elements.sitesState;
		objTmp.startWith = true;
		objTmp.contains = false;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.sitesStateCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);

		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "State Filter";
		objTmp.TestCaseName = "AN-Sites-30: Verify wild card Contains search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Contains search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "#"+Test_Elements.sitesSortFilter+""+Test_Elements.sitesState;
		objFilter.FilterXPath = Test_Elements.sitesState;
		objTmp.startWith = false;
		objTmp.contains = true;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.sitesStateCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "State Filter";
		objTmp.TestCaseName = "AN-Sites-31: Verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "#"+Test_Elements.sitesSortFilter+""+Test_Elements.sitesState;
		objFilter.FilterXPath = Test_Elements.sitesState;
		objTmp.startWith = false;
		objTmp.contains = false;
		objTmp.endsWith = true;
		objFilter.ColumnID = Test_Elements.sitesStateCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Zip Code Filter";
		objTmp.TestCaseName = "AN-Sites-32: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "#"+Test_Elements.sitesSortFilter+""+Test_Elements.sitesZipCode;
		objFilter.FilterXPath = Test_Elements.sitesZipCode;
		objTmp.startWith = true;
		objTmp.contains = false;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.sitesZipCodeCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);

		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Zip Code Filter";
		objTmp.TestCaseName = "AN-Sites-33: Verify wild card Contains search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Contains search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "#"+Test_Elements.sitesSortFilter+""+Test_Elements.sitesZipCode;
		objFilter.FilterXPath = Test_Elements.sitesZipCode;
		objTmp.startWith = false;
		objTmp.contains = true;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.sitesZipCodeCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Zip Code Filter";
		objTmp.TestCaseName = "AN-Sites-34: Verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "#"+Test_Elements.sitesSortFilter+""+Test_Elements.sitesZipCode;
		objFilter.FilterXPath = Test_Elements.sitesZipCode;
		objTmp.startWith = false;
		objTmp.contains = false;
		objTmp.endsWith = true;
		objFilter.ColumnID = Test_Elements.sitesZipCodeCol;
		objTmp.input = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Created By Filter";
		objTmp.TestCaseName = "AN-Sites-35: Verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Starts With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "#"+Test_Elements.sitesSortFilter+""+Test_Elements.sitesCreatedBy;
		objFilter.FilterXPath = Test_Elements.sitesCreatedBy;
		objTmp.startWith = true;
		objTmp.contains = false;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.sitesCreatedByCol;
		objTmp.input = "b";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("B", "b"));
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp); 
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Created By Filter";
		objTmp.TestCaseName = "AN-Sites-36: Verify wild card Contains search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Contains search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "#"+Test_Elements.sitesSortFilter+""+Test_Elements.sitesCreatedBy;
		objFilter.FilterXPath = Test_Elements.sitesCreatedBy;
		objTmp.startWith = false;
		objTmp.contains = true;
		objTmp.endsWith = false;
		objFilter.ColumnID = Test_Elements.sitesCreatedByCol;
		objTmp.input = "a";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A", "a"));
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp); 
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Created By Filter";
		objTmp.TestCaseName = "AN-Sites-37: Verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify wild card Ends With search on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "#"+Test_Elements.sitesSortFilter+""+Test_Elements.sitesCreatedBy;
		objFilter.FilterXPath = Test_Elements.sitesCreatedBy;
		objTmp.startWith = false;
		objTmp.contains = false;
		objTmp.endsWith = true;
		objFilter.ColumnID = Test_Elements.sitesCreatedByCol;
		objTmp.input = "c";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("c", "c"));
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp); 

		return lstSitesLogModel;
	}	

	public static ArrayList<SitesLogModel> Lock() {
		ArrayList<SitesLogModel> lstSitesLogModel = new ArrayList<SitesLogModel>();
		SitesLogModel objTmp;
		ReportFilters objFilter;
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site ID Filter";
		objTmp.TestCaseName = "AN-Sites-41: Verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.sitesSiteID+""+Test_Elements.sitesShowFilter;
		objFilter.FilterSort = Test_Elements.sitesSortFilter+""+Test_Elements.sitesSiteID;
		objFilter.FilterApply = Test_Elements.sitesSiteID+""+Test_Elements.sitesApplyFilter;
		objFilter.FilterClear = Test_Elements.sitesSiteID+""+Test_Elements.sitesClearFilter;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site Name Filter";
		objTmp.TestCaseName = "AN-Sites-42: Verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.sitesSiteName+""+Test_Elements.sitesShowFilter;
		objFilter.FilterSort = Test_Elements.sitesSortFilter+""+Test_Elements.sitesSiteName;
		objFilter.FilterApply = Test_Elements.sitesSiteName+""+Test_Elements.sitesApplyFilter;
		objFilter.FilterClear = Test_Elements.sitesSiteName+""+Test_Elements.sitesClearFilter;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site Type Filter";
		objTmp.TestCaseName = "AN-Sites-43: Verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.sitesSiteType+""+Test_Elements.sitesShowFilter;
		objFilter.FilterSort = Test_Elements.sitesSortFilter+""+Test_Elements.sitesSiteType;
		objFilter.FilterApply = Test_Elements.sitesSiteType+""+Test_Elements.sitesApplyFilter;
		objFilter.FilterClear = Test_Elements.sitesSiteType+""+Test_Elements.sitesClearFilter;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "State Filter";
		objTmp.TestCaseName = "AN-Sites-44: Verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.sitesState+""+Test_Elements.sitesShowFilter;
		objFilter.FilterSort = Test_Elements.sitesSortFilter+""+Test_Elements.sitesState;
		objFilter.FilterApply = Test_Elements.sitesState+""+Test_Elements.sitesApplyFilter;
		objFilter.FilterClear = Test_Elements.sitesState+""+Test_Elements.sitesClearFilter;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "ZipCode Filter";
		objTmp.TestCaseName = "AN-Sites-45: Verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.sitesZipCode+""+Test_Elements.sitesShowFilter;
		objFilter.FilterSort = Test_Elements.sitesSortFilter+""+Test_Elements.sitesZipCode;
		objFilter.FilterApply = Test_Elements.sitesZipCode+""+Test_Elements.sitesApplyFilter;
		objFilter.FilterClear = Test_Elements.sitesZipCode+""+Test_Elements.sitesClearFilter;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
	
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Created Filter";
		objTmp.TestCaseName = "AN-Sites-46: Verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.sitesDateCreated+""+Test_Elements.sitesShowFilter;
		objFilter.FilterSort = Test_Elements.sitesSortFilter+""+Test_Elements.sitesDateCreated;
		objFilter.FilterApply = Test_Elements.sitesDateCreated+""+Test_Elements.sitesApplyFilter;
		objFilter.FilterClear = Test_Elements.sitesDateCreated+""+Test_Elements.sitesClearFilter;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Created By Filter";
		objTmp.TestCaseName = "AN-Sites-47: Verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will verify Lock Filter functionality on "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = Test_Elements.sitesCreatedBy+""+Test_Elements.sitesShowFilter;
		objFilter.FilterSort = Test_Elements.sitesSortFilter+""+Test_Elements.sitesCreatedBy;
		objFilter.FilterApply = Test_Elements.sitesCreatedBy+""+Test_Elements.sitesApplyFilter;
		objFilter.FilterClear = Test_Elements.sitesCreatedBy+""+Test_Elements.sitesClearFilter;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		return lstSitesLogModel;
	}

	
	public static ArrayList<SitesLogModel> pagination() {
		ArrayList<SitesLogModel> lstSitesLogModel = new ArrayList<SitesLogModel>();
		SitesLogModel objTmp = new SitesLogModel();

		objTmp.TestCaseName = "AN-Sites-50: Verify pagination exist on Salmonella Log report";
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
		lstSitesLogModel.add(objTmp);

		objTmp = new SitesLogModel();
		objTmp.TestCaseName = "AN-Sites-51: Verify user navigates to last page on clicking '>>' button in pagnation";
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
		lstSitesLogModel.add(objTmp);

		objTmp = new SitesLogModel();
		objTmp.TestCaseName = "AN-Sites-52: Verify user navigates to previous page on clicking '<' button in pagnation";
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
		lstSitesLogModel.add(objTmp);

		objTmp = new SitesLogModel();
		objTmp.TestCaseName = "AN-Sites-53: Verify user navigates to first page on clicking '<<' button in pagnation";
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
		lstSitesLogModel.add(objTmp);

		objTmp = new SitesLogModel();
		objTmp.TestCaseName = "AN-Sites-54: Verify user navigates to next page on clicking '>' button in pagnation";
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
		lstSitesLogModel.add(objTmp);

		return lstSitesLogModel;
	}


	public static ArrayList<SitesLogModel> searchRows() {
		ArrayList<SitesLogModel> lstSitesLogModel = new ArrayList<SitesLogModel>();
		SitesLogModel objTmp = new SitesLogModel();

		objTmp.TestCaseName = "AN-Sites-55: Verify 100 rows are displayed when 100 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 100 rows are displayed when 100 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-Sites-56: Verify 100 rows are displayed when 100 Rows per Page is selected and user moves to next page";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that 100 rows are displayed when 100 Rows per Page is selected and user moves to next page";
		objTmp.lstFilters = new ArrayList<>();
		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "100 Rows per Page";
		objFilter.FilterListXPathSearch = "rows-100";
		objFilter.count = "100";
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);


		objTmp = new SitesLogModel();
		objTmp.TestCaseName = "AN-Sites-57: Verify 250 rows are displayed when 250 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 250 rows are displayed when 250 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-Sites-58: Verify 250 rows are displayed when 250 Rows per Page is selected and user moves to next page";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that 250 rows are displayed when 250 Rows per Page is selected and user moves to next page";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "250 Rows per Page";
		objFilter.FilterListXPathSearch = "rows-250";
		objFilter.count = "250";
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);


		objTmp = new SitesLogModel();
		objTmp.TestCaseName = "AN-Sites-59: Verify 500 rows are displayed when 500 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 100 rows are displayed when 500 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-Sites-60: Verify 500 rows are displayed when 500 Rows per Page is selected and user moves to next page";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that 500 rows are displayed when 500 Rows per Page is selected and user moves to next page";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "500 Rows per Page";
		objFilter.FilterListXPathSearch = "rows-500";  
		objFilter.count = "500"; 
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);

		return lstSitesLogModel;	
	}


	public static ArrayList<SitesLogModel> sorting() {
		ArrayList<SitesLogModel> lstSitesLogModel = new ArrayList<SitesLogModel>();
		SitesLogModel objTmp;
		ReportFilters objFilter;
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site ID";
		objTmp.TestCaseName = "AN-Sites-61: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesSiteID;           
		objFilter.count = Test_Elements.sitesSiteIDCol;
		objTmp.sortDescFirst = true;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site Name";
		objTmp.TestCaseName = "AN-Sites-62: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesSiteName;            
		objFilter.count = Test_Elements.sitesSiteNameCol;
		objTmp.sortDescFirst = true;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);

		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site Type";
		objTmp.TestCaseName = "AN-Sites-63: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesSiteType;            
		objFilter.count = Test_Elements.sitesSiteTypeCol;
		objTmp.sortDescFirst = true;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
//		objTmp = new SitesLogModel();
//		objFilter = new ReportFilters();
//		objFilter.FilterName = "Street Address";
//		objTmp.TestCaseName = "AN-Sites-64: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
//		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesStreetAddress;           
//		objFilter.count = Test_Elements.sitesStreetAddress;
//		objTmp.lstFilters.add(objFilter);
//		lstSitesLogModel.add(objTmp);

		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "State";
		objTmp.TestCaseName = "AN-Sites-65: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesState;           
		objFilter.count = Test_Elements.sitesStateCol;
		objTmp.sortDescFirst = true;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
	
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Zip Code";
		objTmp.TestCaseName = "AN-Sites-66: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesZipCode;           
		objFilter.count = Test_Elements.sitesZipCodeCol;
		objTmp.sortDescFirst = true;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Country";
		objTmp.TestCaseName = "AN-Sites-67: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesCountry;           
		objFilter.count = Test_Elements.sitesCountryCol;
		objTmp.sortDescFirst = true;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Latitude";
		objTmp.TestCaseName = "AN-Sites-68: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesLatitude;           
		objFilter.count = Test_Elements.sitesLatitudeCol;
		objTmp.sortDescFirst = true;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Longitude";
		objTmp.TestCaseName = "AN-Sites-69: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesLongitude;           
		objFilter.count = Test_Elements.sitesLongitudeCol;
		objTmp.sortDescFirst = true;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
	
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Created";
		objTmp.TestCaseName = "AN-Sites-70: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesDateCreated;           
		objFilter.count = Test_Elements.sitesDateCreatedCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Created By";
		objTmp.TestCaseName = "AN-Sites-71: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.ColumnID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesCreatedBy;           
		objFilter.count = Test_Elements.sitesCreatedByCol;
		objTmp.sortDescFirst = false;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
	
		return lstSitesLogModel;	
	}	
	

	public static ArrayList<SitesLogModel> FieldAccess() {
		ArrayList<SitesLogModel> lstSitesLogModel = new ArrayList<SitesLogModel>();
		SitesLogModel objTmp;
		ReportFilters objFilter;			

		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site Type Filter";
		objTmp.TestCaseName = "AN-Sites-73: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.sitesSiteTypeCol;
		objTmp.FilterHideID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesSiteType;
		objTmp.FilterUnHideID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesSiteName;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = false;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
			
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Street Address Filter";
		objTmp.TestCaseName = "AN-Sites-74: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.sitesStreetAddressCol;
		objTmp.FilterHideID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesStreetAddress;
		objTmp.FilterUnHideID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesSiteType;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "State Filter";
		objTmp.TestCaseName = "AN-Sites-75: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.sitesStateCol;
		objTmp.FilterHideID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesState;
		objTmp.FilterUnHideID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesStreetAddress;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Zip Code Filter";
		objTmp.TestCaseName = "AN-Sites-76: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.sitesZipCodeCol;
		objTmp.FilterHideID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesZipCode;
		objTmp.FilterUnHideID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesState;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Country Filter";
		objTmp.TestCaseName = "AN-Sites-77: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.sitesCountryCol;
		objTmp.FilterHideID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesCountry;
		objTmp.FilterUnHideID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesZipCode;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Latitude Filter";
		objTmp.TestCaseName = "AN-Sites-78: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.sitesLatitudeCol;
		objTmp.FilterHideID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesLatitude;
		objTmp.FilterUnHideID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesCountry;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Longitude Filter";
		objTmp.TestCaseName = "AN-Sites-79: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.sitesLongitudeCol;
		objTmp.FilterHideID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesLongitude;
		objTmp.FilterUnHideID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesLatitude;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Created Filter";
		objTmp.TestCaseName = "AN-Sites-80: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.sitesDateCreatedCol;
		objTmp.FilterHideID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesDateCreated;
		objTmp.FilterUnHideID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesLongitude;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Created By Filter";
		objTmp.TestCaseName = "AN-Sites-81: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.sitesCreatedByCol;
		objTmp.FilterHideID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesCreatedBy;
		objTmp.FilterUnHideID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesDateCreated;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Select back all columns";
		objTmp.TestCaseName = "AN-Sites-82: Select back all unselected columns";
		objTmp.TestCaseDescription = "This testcase will select back all unselected columns";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = Test_Elements.sitesFieldAccessCol;
		objTmp.FilterHideID = "";
		objTmp.FilterUnHideID = Test_Elements.sitesSortFilter+""+Test_Elements.sitesDateCreated;
		objTmp.unviewAccess = false;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		return lstSitesLogModel;
	}
}


