package Models;

import java.util.ArrayList;
import java.util.Arrays;
import Test.Ancera.Test_Elements;

public class SalmonellaModel {

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

	public SalmonellaModel() {

	}

	public static String buttonActiveTitle = "Verify apply filter button becomes active on selecting checkbox from ";
	public static String buttonActiveDesc = "This test case will verify that filter button becomes active on selecting checkbox from ";
	public static String applyFilterTitle = "Verify user can select checkbox from ";
	public static String applyFilterDesc = "This test case will verify that user can select checkbox from ";
	public static String filterIndicatorTitle = "Verify user can apply filter and table displays relevant results on applying ";
	public static String filterIndicatorDesc = "This test case will verify that user can apply filter and table displays relevant results on applying ";
	public static String filterTopTitle = " moves to top of filter list on applying filter";
	public static String filterTopDesc = " bubbles to top of filter list on applying filter";
	public static String CheckboxTopTitle = " moves to top of filter list on applying filter";
	public static String CheckboxTopDesc = " bubbles to top of filter list on applying filter";
	public static String clearInputTitle = "Verify input field gets cleared on clicking cross icon and filter button becomes inactive after applying ";
	public static String clearInputDesc = "This test case will verify that input field gets cleared on clicking the cross icon in ";
	public static String revertBackTitle = " rolls back to its original position on clicking reset button";
	public static String revertBackDesc = " rolls back to its original position on clicking reset button";


	public static ArrayList<SalmonellaModel> FillData() {
		ArrayList<SalmonellaModel> lstSalmonellaModel = new ArrayList<SalmonellaModel>();
		SalmonellaModel objTmp = new SalmonellaModel();
	
		ReportFilters  objFilter = new ReportFilters();
		objFilter.FilterName = "Lane Filter";
		objTmp.TestCaseName = "AN-SL-17: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-18: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "#sort-laneNum";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Lane"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2", "3")); 
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);	
	
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Lab Sample ID Filter";
		objTmp.TestCaseName = "AN-SL-19: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-20: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "#sort-sampleId";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Sample-Id"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("14", "15"));
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
			
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "QC Code Filter";
		objTmp.TestCaseName = "AN-SL-21: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-22: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "#sort-countOutcome";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Count-Outcome"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2", "3"));
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp); 
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Result Status Filter";
		objTmp.TestCaseName = "AN-SL-23: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-24: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "#sort-result_status";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Result-Status"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1", "2"));
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Assay Filter";
		objTmp.TestCaseName = "AN-SL-25: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-26: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "#sort-pathogen";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Assay"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2", "3")); 
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);		
			
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection Site Name Filter";
		objTmp.TestCaseName = "AN-SL-27: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-28: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "#sort-site_id";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Site-Name"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2", "3"));
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp); 

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection Site ID Filter";
		objTmp.TestCaseName = "AN-SL-29: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-30: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "#sort-collection_site_id";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Collection-Site-ID"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2", "3"));
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp); 

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection Site Type Filter";
		objTmp.TestCaseName = "AN-SL-31: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-32: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "#sort-collection_site_type";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Collection-Site-Type"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2", "3"));
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp); 
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Sample Matrix Filter";
		objTmp.TestCaseName = "AN-SL-33: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-34: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "#sort-sample_matrix";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Sample-Matrix"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2", "3"));
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp); 

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Customer Sample ID Filter";
		objTmp.TestCaseName = "AN-SL-35: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-36: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "#sort-customer_sample_id";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Customer-Sample-ID"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2", "3"));
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Received Filter";
		objTmp.TestCaseName = "AN-SL-37: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-38: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "#sort-metadata_date_recieved";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Date-Received"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1", "2"));
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Cartridge ID Filter";
		objTmp.TestCaseName = "AN-SL-39: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-40: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "#sort-cartridgeId";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Cartridge-Id"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2", "3")); 
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
			
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Instrument ID Filter";
		objTmp.TestCaseName = "AN-SL-41: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-42: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "#sort-instrumentId";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Instrument-Id"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2", "3"));
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Load Filter";
		objTmp.TestCaseName = "AN-SL-43: Verify user can filter 'Medium' from Load Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter 'Medium' from Load Filter";
		objTmp.TestCaseNameSearch = "AN-SL-44: Verify that blue filter indicator appears next to applied filter and apply filter button becomes inactive on applying 'Medium' from Load Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that blue filter indicator appears next to applied filter and apply filter button becomes inactive on applying 'Medium' from Load Filter";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "#sort-w2CellCount";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Load"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2"));
		objFilter.getRowValue = Test_Elements.slLoadRow;
		objFilter.rowValueExpected = Test_Elements.slLoadYellow;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
	
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Load Filter";
		objTmp.TestCaseName = "AN-SL-45: Verify user can filter 'High' from Load Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter 'High' from Load Filter";
		objTmp.TestCaseNameSearch = "AN-SL-46: Verify that blue filter indicator appears next to applied filter and apply filter button becomes inactive on applying 'High' from Load Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that blue filter indicator appears next to applied filter and apply filter button becomes inactive on applying 'High' from Load Filter";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "#sort-w2CellCount";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Load"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1"));
		objFilter.getRowValue = Test_Elements.slLoadRow;
		objFilter.rowValueExpected = Test_Elements.slLoadRed;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Load Filter";
		objTmp.TestCaseName = "AN-SL-47: Verify user can filter 'Low' from Load Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter 'Low' from Load Filter";
		objTmp.TestCaseNameSearch = "AN-SL-48: Verify that blue filter indicator appears next to applied filter and apply filter button becomes inactive on applying 'Low' from Load Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that blue filter indicator appears next to applied filter and apply filter button becomes inactive on applying 'Low' from Load Filter";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "#sort-w2CellCount";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Load"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("3")); 
		objFilter.getRowValue = Test_Elements.slLoadRow;
		objFilter.rowValueExpected = Test_Elements.slLoadGreen; 
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Piper User Filter";
		objTmp.TestCaseName = "AN-SL-49: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-50: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "#sort-user_name";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Piper-User"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2", "3"));
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Kit Lot Filter";
		objTmp.TestCaseName = "AN-SL-51: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-52: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "#sort-kit_lot";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Kit-Lot"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2", "3"));
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Improc Version Filter";
		objTmp.TestCaseName = "AN-SL-53: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-54: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "#sort-version";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Improc-Version"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1", "2"));
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp); 
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Test Site ID Filter";
		objTmp.TestCaseName = "AN-SL-55: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-56: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "#sort-testSiteId";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("test-site-id"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1", "2"));
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Requested Assay Filter";
		objTmp.TestCaseName = "AN-ESL-57: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-ESL-58: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "#sort-requested_assay";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Requested-Assay"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1", "2"));
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Flock ID Filter";
		objTmp.TestCaseName = "AN-ESL-59: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-ESL-60: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "#sort-flock_id";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Flock-ID"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1", "2"));
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Test Site Name Filter";
		objTmp.TestCaseName = "AN-SL-61: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-62: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "#sort-testSiteName";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("test-site-name"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1", "2"));
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
//		objTmp = new SalmonellaModel();
//		objFilter = new ReportFilters();
//		objFilter.FilterName = "Two Filters";
//		objTmp.TestCaseName = "AN-SL-63: "+applyFilterTitle+objFilter.FilterName;
//		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
//		objTmp.TestCaseNameSearch = "AN-SL-64: "+filterIndicatorTitle+objFilter.FilterName;
//		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter = new ReportFilters();
//		objFilter.FilterID = "isntrument-id";
//		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Sample-Id", "Instrument-Id"));
//		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("SMP01", "PSN0018"));
//		objFilter.FilterID = "sample-id";
//		objFilter.FilterListXPathSearch = "Sample-Id-place-holder-search";
//		objTmp.lstFilters.add(objFilter);
//		lstSalmonellaModel.add(objTmp);

		return lstSalmonellaModel;
	}



	public static ArrayList<SalmonellaModel> FillDate() {
		ArrayList<SalmonellaModel> lstSalmonellaModel = new ArrayList<SalmonellaModel>();
		SalmonellaModel objTmp = new SalmonellaModel();

		objTmp.TestCaseName = "AN-SL-06: Verify user can filter Today date from date Filter";
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
		objTmp.TestCaseName = "AN-SL-07: Verify user can filter Last 24 Hours from date Filter";
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
		objTmp.TestCaseName = "AN-SL-08: Verify user can filter Last 7 Days from date Filter";
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
		objFilter.toDate = "0";
		objFilter.fromDate = "-7";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-09: Verify user can filter Last 30 Days from date Filter";
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
		objFilter.toDate = "0";
		objFilter.fromDate = "-30";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-10: Verify user can filter Last Month from date Filter";
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
		objTmp.TestCaseName = "AN-SL-11: Verify user can filter This Month from date Filter";
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


	public static ArrayList<SalmonellaModel> pagination() {
		ArrayList<SalmonellaModel> lstSalmonellaModel = new ArrayList<SalmonellaModel>();
		SalmonellaModel objTmp = new SalmonellaModel();

		objTmp.TestCaseName = "AN-SL-188: Verify pagination exist on Salmonella Log report";
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
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-189: Verify user navigates to last page on clicking '>>' button in pagnation";
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
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-190: Verify user navigates to previous page on clicking '<' button in pagnation";
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
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-191: Verify user navigates to first page on clicking '<<' button in pagnation";
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
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-192: Verify user navigates to next page on clicking '>' button in pagnation";
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
		lstSalmonellaModel.add(objTmp);

		return lstSalmonellaModel;
	}


	public static ArrayList<SalmonellaModel> searchRows() {
		ArrayList<SalmonellaModel> lstSalmonellaModel = new ArrayList<SalmonellaModel>();
		SalmonellaModel objTmp = new SalmonellaModel();

		objTmp.TestCaseName = "AN-SL-193: Verify 100 rows are displayed when 100 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 100 rows are displayed when 100 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-SL-194: Verify 100 rows are displayed when 100 Rows per Page is selected and user moves to next page";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that 100 rows are displayed when 100 Rows per Page is selected and user moves to next page";
		objTmp.lstFilters = new ArrayList<>();
		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "100 Rows per Page";
		objFilter.FilterListXPathSearch = "rows-100";
		objFilter.count = "100";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);


		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-195: Verify 250 rows are displayed when 250 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 250 rows are displayed when 250 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-SL-196: Verify 250 rows are displayed when 250 Rows per Page is selected and user moves to next page";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that 250 rows are displayed when 250 Rows per Page is selected and user moves to next page";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "250 Rows per Page";
		objFilter.FilterListXPathSearch = "rows-250";
		objFilter.count = "250";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);


		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-197: Verify 500 rows are displayed when 500 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 100 rows are displayed when 500 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-SL-198: Verify 500 rows are displayed when 500 Rows per Page is selected and user moves to next page";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that 500 rows are displayed when 500 Rows per Page is selected and user moves to next page";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "500 Rows per Page";
		objFilter.FilterListXPathSearch = "rows-500";  
		objFilter.count = "500"; 
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		return lstSalmonellaModel;	
	}


	public static ArrayList<SalmonellaModel> sorting() {
		ArrayList<SalmonellaModel> lstSalmonellaModel = new ArrayList<SalmonellaModel>();
		SalmonellaModel objTmp = new SalmonellaModel();

		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "Lane";
		objTmp.TestCaseName = "AN-SL-218: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-laneNum";
		objFilter.count = "2";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
	    objFilter = new ReportFilters();
		objFilter.FilterName = "Lab Sample ID";
		objTmp.TestCaseName = "AN-SL-219: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-sampleId";
		objFilter.count = "2";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "QC Code";
		objTmp.TestCaseName = "AN-SL-220: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-countOutcome";
		objFilter.count = "3";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Result Status";
		objTmp.TestCaseName = "AN-SL-221: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-result_status";
		objFilter.count = "4";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Result";
		objTmp.TestCaseName = "AN-SL-222: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-outcome";
		objFilter.count = "5";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Assay";
		objTmp.TestCaseName = "AN-SL-223: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-pathogen";
		objFilter.count = "6";                   
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Result ID";
		objTmp.TestCaseName = "AN-SL-224: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-runId";
		objFilter.count = "7";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Result Date";
		objTmp.TestCaseName = "AN-SL-225: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = false;
		objFilter.ColumnID = "sort-scanDateTime";
		objFilter.count = "8";
		objTmp.lstFilters.add(objFilter);       
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection Site Name";
		objTmp.TestCaseName = "AN-SL-226: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-site_id";           
		objFilter.count = "10";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection Site ID";
		objTmp.TestCaseName = "AN-SL-227: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-collection_site_id";           
		objFilter.count = "11";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection Site Type";
		objTmp.TestCaseName = "AN-SL-228: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-collection_site_type";           
		objFilter.count = "12";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Sample Matrix";
		objTmp.TestCaseName = "AN-SL-229: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-sample_matrix";           
		objFilter.count = "13";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Dilution Factor";
		objTmp.TestCaseName = "AN-SL-230: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-dilution_factor";           
		objFilter.count = "14";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Customer Sample ID";
		objTmp.TestCaseName = "AN-SL-231: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-customer_sample_id";           
		objFilter.count = "15";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Received";
		objTmp.TestCaseName = "AN-SL-232: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-metadata_date_recieved";           
		objFilter.count = "16";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Cartridge ID";
		objTmp.TestCaseName = "AN-SL-233: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-cartridgeId";           
		objFilter.count = "17";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Instrument ID";
		objTmp.TestCaseName = "AN-SL-234: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-instrumentId";           
		objFilter.count = "18";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "W1 Cell Count";
		objTmp.TestCaseName = "AN-SL-235: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-w1CellCount";           
		objFilter.count = "19";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "W1 PC Count";
		objTmp.TestCaseName = "AN-SL-236: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-w1PCCount";           
		objFilter.count = "20";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "W1 Mean Intensity";
		objTmp.TestCaseName = "AN-SL-237: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-w1CellMeanIntensity";           
		objFilter.count = "21";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "W2 Cell Count";
		objTmp.TestCaseName = "AN-SL-238: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-w2CellCount";           
		objFilter.count = "22";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "W2 PC Count";
		objTmp.TestCaseName = "AN-SL-239: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-w2PCCount";           
		objFilter.count = "23";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "W2 Mean Intensity";
		objTmp.TestCaseName = "AN-SL-240: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-w2CellMeanIntensity";           
		objFilter.count = "24";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Piper User";
		objTmp.TestCaseName = "AN-SL-241: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-user_name";           
		objFilter.count = "25";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Kit Lot";
		objTmp.TestCaseName = "AN-SL-242: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-kit_lot";           
		objFilter.count = "26";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Improc Version";
		objTmp.TestCaseName = "AN-SL-243: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-version";           
		objFilter.count = "27";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Test Site ID";
		objTmp.TestCaseName = "AN-SL-244: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-testSiteId";
		objFilter.count = "28";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Test Site Name";
		objTmp.TestCaseName = "AN-SL-245: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-testSiteName";
		objFilter.count = "29";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		return lstSalmonellaModel;	
	}	
	

	
}


