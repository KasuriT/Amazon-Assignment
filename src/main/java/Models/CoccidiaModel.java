package Models;
import java.util.ArrayList;
import java.util.Arrays;

import Test.Ancera.Test_Elements;

public class CoccidiaModel {

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
	public String TestCaseNameRevertBack;
	public String TestCaseDescriptionRevertBack;
	public ArrayList<ReportFilters> lstFilters;
	public boolean ReloadPage;
	public boolean ResetFilter;
	public boolean Filter1;
	public boolean Filter2;
	public boolean Filter3;
	public boolean paginationExist;
	public boolean paginationLastPage;
	public boolean paginationNextPage;
	public boolean paginationFirstPage;
	public boolean paginationPreviousPage;

	public CoccidiaModel() {

	}

	
	public static String buttonActiveTitle = "Verify apply filter button becomes active on selecting checkbox from ";
	public static String buttonActiveDesc = "This test case will verify that filter button becomes active on selecting checkbox from ";
	public static String applyFilterTitle = "Verify user can apply ";
	public static String applyFilterDesc = "This test case will verify that filtered records are displayed in table on applying ";
	public static String filterIndicatorTitle = "Verify blue filter indicator appears next to applied filter and apply filter button becomes inactive on applying ";
	public static String filterIndicatorDesc = "This test case will verify that blue filter indicator appears next to applied filter and apply filter button becomes inactive on applying ";
	public static String filterTopTitle = " moves to top of filter list on applying filter";
	public static String filterTopDesc = " bubbles to top of filter list on applying filter";
	public static String CheckboxTopTitle = " moves to top of filter list on applying filter";
	public static String CheckboxTopDesc = " bubbles to top of filter list on applying filter";
	public static String clearInputTitle = "Verify input field gets cleared on clicking cross icon and filter button becomes inactive after applying ";
	public static String clearInputDesc = "This test case will verify that input field gets cleared on clicking the cross icon in ";
	public static String revertBackTitle = " rolls back to its original position on clicking reset button";
	public static String revertBackDesc = " rolls back to its original position on clicking reset button";
	
	
	public static ArrayList<CoccidiaModel> FillData() {
		ArrayList<CoccidiaModel> lstCoccidiaModel = new ArrayList<CoccidiaModel>();
		CoccidiaModel objTmp = new CoccidiaModel();
		
		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "Sample ID Filter";
		objTmp.TestCaseNameButtonActive = "AN-CL-17: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-CL-18: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-CL-19: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-CL-20: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-CL-21: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-CL-22: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-CL-24: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "sample-id";
		objFilter.FilterXPath = "filter-Sample-ID";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Sample-ID"));
		objFilter.FilterListXPathSearch = "Sample-ID-place-holder-search";
		objFilter.SearchVlaue = "TD02";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("TD02"));
		objFilter.getRowValue = Test_Elements.slSampleIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "Sample-ID-clear-input";
		objFilter.wait = 4000;
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);

		
		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Instrument ID Filter";
		objTmp.TestCaseNameButtonActive = "AN-CL-25: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-CL-26: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-CL-27: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-CL-28: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-CL-29: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-CL-30: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-CL-32: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "isntrument-id";
		objFilter.FilterXPath = "filter-Instrument-ID";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Instrument-ID"));
		objFilter.FilterListXPathSearch = "Instrument-ID-place-holder-search";
		objFilter.SearchVlaue = "PSN0004";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("PSN0004"));
		objFilter.getRowValue = Test_Elements.slInstrumentIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "Instrument-ID-clear-input";
		objFilter.wait = 4000;
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		

		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Cartridge ID Filter";
		objTmp.TestCaseNameButtonActive = "AN-CL-33: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-CL-34: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-CL-35: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-CL-36: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-CL-37: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-CL-38: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-CL-40: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "cartrtidge-id";
		objFilter.FilterXPath = "filter-Cartridge-ID";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Cartridge-ID"));
		objFilter.FilterListXPathSearch = "Cartridge-ID-place-holder-search";
		objFilter.SearchVlaue = "TestAutomation";  
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("TestAutomation"));  //uat
		objFilter.getRowValue = Test_Elements.slCatridgeIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "Cartridge-ID-clear-input";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		
		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Lane Filter";
		objTmp.TestCaseNameButtonActive = "AN-CL-41: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-CL-42: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-CL-43: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-CL-44: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-CL-45: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-CL-46: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-CL-48: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "lane-num";
		objFilter.FilterXPath = "filter-Lane";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Lane"));
		objFilter.FilterListXPathSearch = "Lane-place-holder-search";
		objFilter.SearchVlaue = "10";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("10"));
		objFilter.getRowValue = Test_Elements.slLaneIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "Lane-clear-input";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
	
		
		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Assay Filter";
		objTmp.TestCaseNameButtonActive = "AN-CL-49: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-CL-50: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-CL-51: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-CL-52: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-CL-53: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-CL-54: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-CL-56: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "pathogen";
		objFilter.FilterXPath = "filter-Assay";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Assay"));
		objFilter.FilterListXPathSearch = "Assay-place-holder-search";
		objFilter.SearchVlaue = "Coccidia-SYBR";   
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Coccidia-SYBR"));  //uat
		objFilter.getRowValue = Test_Elements.slAssayRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "Assay-clear-input";
		objFilter.wait = 4000;
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);               

		
//		objTmp = new CoccidiaModel();
//		objFilter = new ReportFilters();
//		objFilter.FilterName = "Improc Version Filter";
//		objTmp.TestCaseNameButtonActive = "AN-CL-57: "+buttonActiveTitle+objFilter.FilterName;
//		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
//		objTmp.TestCaseName = "AN-CL-58: "+applyFilterTitle+objFilter.FilterName;
//		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
//		objTmp.TestCaseNameSearch = "AN-CL-59 "+filterIndicatorTitle+objFilter.FilterName;
//		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
//		objTmp.TestCaseNameBubbleFilterTop = "AN-CL-60: Verify "+objFilter.FilterName+filterTopTitle;
//		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
//		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-CL-61: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
//		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
//		objTmp.TestCaseNameClearInput = "AN-CL-62: "+clearInputTitle+objFilter.FilterName;
//		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
//		objTmp.TestCaseNameRevertBack = "AN-CL-64: Verify "+objFilter.FilterName+revertBackTitle;
//		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
//		objTmp.ReloadPage = false;
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter = new ReportFilters();
//		objFilter.FilterID = "version";
//		objFilter.FilterXPath = "filter-Improc-Version";
//		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Improc-Version"));
//		objFilter.FilterListXPathSearch = "Improc-Version-place-holder-search";
//		objFilter.SearchVlaue = "";
//		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("select-all-text-Improc Version"));
//		objFilter.getRowValue = Test_Elements.slImprocIDRow;
//		objFilter.rowValueExpected = objFilter.SearchVlaue;
//		objFilter.ClearInput = "Improc-Version-clear-input";
//		objFilter.wait = 5000;
//		objTmp.lstFilters.add(objFilter);
//		lstCoccidiaModel.add(objTmp); 
		
		
		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site Name Filter";
		objTmp.TestCaseNameButtonActive = "AN-CL-65: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-CL-66: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-CL-67: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-CL-68: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-CL-69: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-CL-70: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-CL-72: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "site-id";
		objFilter.FilterXPath = "filter-Site-Name";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Site-Name"));
		objFilter.FilterListXPathSearch = "Site-Name-place-holder-search";
		objFilter.SearchVlaue = "Test";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Test"));
		objFilter.getRowValue = Test_Elements.slSiteNameRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "Site-Name-clear-input";
		objFilter.wait = 4500;
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp); 
		
		
		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Sample Matrix Filter";
		objTmp.TestCaseNameButtonActive = "AN-CL-73: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-CL-74: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-CL-75: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-CL-76: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-CL-77: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-CL-78: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-CL-80: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "sample-matrix";
		objFilter.FilterXPath = "filter-Sample-Matrix";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Sample-Matrix"));
		objFilter.FilterListXPathSearch = "Sample-Matrix-place-holder-search";
		objFilter.SearchVlaue = "Feces";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Feces"));
		objFilter.getRowValue = Test_Elements.slSampleMatrixRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "Sample-Matrix-clear-input";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp); 
		
		
		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Customer Sample ID Filter";
		objTmp.TestCaseNameButtonActive = "AN-CL-81: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-CL-82: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-CL-83: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-CL-84: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-CL-85: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-CL-86: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-CL-88: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "customer-sample-id";
		objFilter.FilterXPath =  "filter-Customer-Sample-ID";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Customer-Sample-ID"));
		objFilter.FilterListXPathSearch = "Customer-Sample-ID-place-holder-search";
		objFilter.SearchVlaue = "CS1";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("CS1"));
		objFilter.getRowValue = Test_Elements.slCSampleIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "Customer-Sample-ID-clear-input";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);

		
		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Received Filter";
		objTmp.TestCaseNameButtonActive = "AN-CL-89: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-CL-90: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-CL-91: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-CL-92: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-CL-93: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-CL-94: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-CL-96: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "meta-data-recieved";
		objFilter.FilterXPath = "filter-Date-Received";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Date-Received"));
		objFilter.FilterListXPathSearch = "Date-Received-place-holder-search";
		objFilter.SearchVlaue = "06-11-2020";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("06-11-2020"));
		objFilter.getRowValue = Test_Elements.slDateReceivedRow;
		objFilter.rowValueExpected = "";
		objFilter.ClearInput = "Date-Received-clear-input";
		objFilter.wait = 6000;
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		
		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Kit Lot Filter";
		objTmp.TestCaseNameButtonActive = "AN-CL-97: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-CL-98: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-CL-99: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-CL-100: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-CL-101: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-CL-102: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-CL-104: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "kit-lot";
		objFilter.FilterXPath =  "filter-Kit-Lot";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Kit-Lot"));
		objFilter.FilterListXPathSearch = "Kit-Lot-place-holder-search";
		objFilter.SearchVlaue = "A";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("A"));
		objFilter.getRowValue = Test_Elements.slKitLotRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "Kit-Lot-clear-input";
		objFilter.wait = 4000;
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		
		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Piper User Filter";
		objTmp.TestCaseNameButtonActive = "AN-CL-105: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-CL-106: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-CL-107: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-CL-108: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-CL-109: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-CL-110: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-CL-112: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "user-name";
		objFilter.FilterXPath = "filter-Piper-User";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Piper-User"));
		objFilter.FilterListXPathSearch = "Piper-User-place-holder-search";
		objFilter.SearchVlaue = "FHasan";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("FHasan"));
		objFilter.getRowValue = Test_Elements.slPiperUserRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "Piper-User-clear-input";
		objFilter.wait = 4500;
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
	
		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Result Status Filter";
		objTmp.TestCaseNameButtonActive = "AN-CL-113: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-CL-114: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-CL-115: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-CL-116: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-CL-117: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-CL-118: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-CL-120: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "result-status";
		objFilter.FilterXPath = "filter-Result-Status";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Result-Status"));
		objFilter.FilterListXPathSearch = "Result-Status-place-holder-search";
		objFilter.SearchVlaue = "Completed";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Completed"));
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "Result-Status-clear-input";
		objFilter.wait = 7000;
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
			
		
		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "multiple value from same filter";
		objTmp.TestCaseNameButtonActive = "AN-CL-121: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-CL-122: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-CL-123: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-CL-124: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-CL-125: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-CL-126: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-CL-127: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "sample-id";
		objFilter.FilterXPath = "filter-Sample-ID";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Sample-ID"));
		objFilter.FilterListXPathSearch = "Sample-ID-place-holder-search";
		objFilter.SearchVlaue = "TD";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("TD05", "TD06"));
		objFilter.getRowValue = Test_Elements.slSampleIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "Sample-ID-clear-input";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
	
		
		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Two Filters";
		objTmp.TestCaseNameButtonActive = "AN-CL-192: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-CL-193: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-CL-194: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-CL-195: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-CL-196: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-CL-197: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-CL-199: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "isntrument-id";
		objFilter.FilterXPath = "filter-Instrument-ID";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Instrument-ID"));
		objFilter.FilterListXPathSearch = "Instrument-ID-place-holder-search";
		objFilter.SearchVlaue = "PSN0004";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("PSN0004"));
		objFilter.ClearInput = "Instrument-ID-clear-input";
		objFilter.FilterID = "sample-id";
		objFilter.FilterXPath = "filter-Sample-ID";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Sample-ID"));
		objFilter.FilterListXPathSearch = "Sample-ID-place-holder-search";
		objFilter.SearchVlaue = "TD";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("TD05"));
		objFilter.ClearInput = "Sample-ID-clear-input";
		objFilter.wait = 4000;
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
	
		return lstCoccidiaModel;
	}

	
	
	
	public static ArrayList<CoccidiaModel> FillDate() {
		ArrayList<CoccidiaModel> lstCoccidiaModel = new ArrayList<CoccidiaModel>();
		CoccidiaModel objTmp = new CoccidiaModel();
		
		objTmp.TestCaseName = "AN-CL-05: Verify user can filter Today date from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of date filter after selecting 'Today'";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.Filter3 = false;
		objTmp.lstFilters = new ArrayList<>();
		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterXPath = Test_Elements.clDateOpen;
		objFilter.FilterListXPathSearch = Test_Elements.clToday;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "0";
		objFilter.fromDate = "0";
		objFilter.FilterListXPathPrefix = Test_Elements.clDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.clDateTo;
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
	
		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-06: Verify user can filter Last 24 Hours from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of Last 24 Hours after selecting 'Last 24 Hours'";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.Filter3 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterXPath = Test_Elements.clDateOpen;
		objFilter.FilterListXPathSearch = Test_Elements.clLast24Hours;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "-1";
		objFilter.fromDate = "-1";
		objFilter.FilterListXPathPrefix = Test_Elements.clDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.clDateTo;
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		
		
		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-07: Verify user can filter Last 7 Days from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of Last 7 Days after selecting 'Last 7 Days'";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.Filter3 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterXPath = Test_Elements.clDateOpen;
		objFilter.FilterListXPathSearch = Test_Elements.clLast7Days;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "0";
		objFilter.fromDate = "-7";
		objFilter.FilterListXPathPrefix = Test_Elements.clDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.clDateTo;
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		
		
		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-08: Verify user can filter Last 30 Days from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of Last 30 Days after selecting 'Last 30 Days'";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.Filter3 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterXPath = Test_Elements.clDateOpen;
		objFilter.FilterListXPathSearch = Test_Elements.clLast30Days;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "0";
		objFilter.fromDate = "-30";
		objFilter.FilterListXPathPrefix = Test_Elements.clDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.clDateTo;
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		
		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-09: Verify user can filter Last Month from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of Last Month after selecting 'Last Month'";
		objTmp.Filter1 = false;
		objTmp.Filter2 = true;
		objTmp.Filter3 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterXPath = Test_Elements.clDateOpen;
		objFilter.FilterListXPathSearch = Test_Elements.clLastMonth;
		objFilter.toMonth = "-1";
		objFilter.fromMonth = "-1";
		objFilter.toDate = "30";
		objFilter.fromDate = "1";
		objFilter.FilterListXPathPrefix = Test_Elements.clDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.clDateTo;
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		
		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-10: Verify user can filter This Month from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of This Month after selecting 'This Month'";
		objTmp.Filter1 = false;
		objTmp.Filter2 = false;
		objTmp.Filter3 = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterXPath = Test_Elements.clDateOpen;
		objFilter.FilterListXPathSearch = Test_Elements.clThisMonth;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "0";
		objFilter.fromDate = "1";
		objFilter.FilterListXPathPrefix = Test_Elements.clDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.clDateTo;
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		
		return lstCoccidiaModel;
	}
	
	
	
	
	public static ArrayList<CoccidiaModel> EnterDate() {
		ArrayList<CoccidiaModel> lstCoccidiaModel = new ArrayList<CoccidiaModel>();
		CoccidiaModel objTmp = new CoccidiaModel();
		
		objTmp.TestCaseName = "AN-CL-11: Verify user cannot apply filter with invalid date";
		objTmp.TestCaseDescription = "This testcase will verify that user cannot add date with invalid from and to date";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.lstFilters = new ArrayList<>();
		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "Enter invalid date";
		objFilter.FilterListXPathPrefix = Test_Elements.clDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.clDateTo;
		objFilter.fromDate = "12/01/2020";
		objFilter.toDate = "11/11/202020";
		objFilter.alertMessage = "Please select a valid date";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		
		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-12: Verify user cannot apply filter with any other date format except mm/dd/yyyy";
		objTmp.TestCaseDescription = "This testcase will verify that user cannot apply filter with any other date format except mm/dd/yyyy";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Enter any other date format except mm/dd/yyyy";
		objFilter.FilterListXPathPrefix = Test_Elements.clDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.clDateTo;
		objFilter.fromDate = "12/01/2020";
		objFilter.toDate = "20/12/2020";
		objFilter.alertMessage = "Please select a valid date";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);

		
		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-13: Verify user cannot apply filter with from date greater than to date";
		objTmp.TestCaseDescription = "This testcase will verify that user cannot apply filter with from date greater than to date";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Enter from date greater than to date";
		objFilter.FilterListXPathPrefix = Test_Elements.clDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.clDateTo;
		objFilter.fromDate = "12/01/2020";
		objFilter.toDate = "10/01/2020";
		objFilter.alertMessage = "Please select a valid date";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		
		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-14: Verify user can search for records with valid date";
		objTmp.TestCaseDescription = "This testcase will verify that user can search for records with valid date";
		objTmp.Filter1 = false;
		objTmp.Filter2 = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Enter valid date";
		objFilter.FilterListXPathPrefix = Test_Elements.clDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.clDateTo;
		objFilter.fromDate = "12/01/2020";
		objFilter.toDate = "01/07/2021";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		return lstCoccidiaModel;
		
}
	
	
	public static ArrayList<CoccidiaModel> pagination() {
		ArrayList<CoccidiaModel> lstCoccidiaModel = new ArrayList<CoccidiaModel>();
		CoccidiaModel objTmp = new CoccidiaModel();
		
		objTmp.TestCaseName = "AN-CL-138: Verify pagination exist on Coccidia Log report";
		objTmp.TestCaseDescription = "This testcase will verify that pagination exist on Coccidia Log report";
		objTmp.paginationExist = true;
		objTmp.paginationLastPage = false;
		objTmp.paginationPreviousPage = false;
		objTmp.paginationFirstPage = false;
		objTmp.paginationNextPage = false;
		objTmp.lstFilters = new ArrayList<>();
		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "Pagination exists";
		objFilter.paginationPage = "next-page";
		objFilter.paginationCount = Test_Elements.clTotalPages;
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-139: Verify user navigates to last page on clicking '>>' button in pagnation";
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
		objFilter.paginationCount = Test_Elements.clTotalPages;
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		
		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-140: Verify user navigates to previous page on clicking '<' button in pagnation";
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
		objFilter.paginationCount = Test_Elements.clTotalPages;
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
	
	
		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-141: Verify user navigates to first page on clicking '<<' button in pagnation";
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
		objFilter.paginationCount = Test_Elements.clTotalPages;
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		
		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-142: Verify user navigates to next page on clicking '>' button in pagnation";
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
		objFilter.paginationCount = "activePageNumber";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		return lstCoccidiaModel;	
}
	
	
	
	public static ArrayList<CoccidiaModel> searchRows() {
		ArrayList<CoccidiaModel> lstCoccidiaModel = new ArrayList<CoccidiaModel>();
		CoccidiaModel objTmp = new CoccidiaModel();
		
		objTmp.TestCaseName = "AN-CL-143: Verify 100 rows are displayed when 100 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 100 rows are displayed when 100 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-CL-144: Verify 100 rows are displayed when 100 Rows per Page is selected and user moves to next page";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that 100 rows are displayed when 100 Rows per Page is selected and user moves to next page";
		objTmp.lstFilters = new ArrayList<>();
		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "100 Rows per Page";
	//	objFilter.FilterXPath = Test_Elements.clRowsDropdown;
		objFilter.FilterListXPathSearch = "100";
		objFilter.count = "100";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		
		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-145: Verify 250 rows are displayed when 250 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 250 rows are displayed when 250 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-CL-146: Verify 500 rows are displayed when 250 Rows per Page is selected and user moves to next page";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that 250 rows are displayed when 250 Rows per Page is selected and user moves to next page";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "250 Rows per Page";
	//	objFilter.FilterXPath = Test_Elements.clRowsDropdown;
		objFilter.FilterListXPathSearch = "250";
		objFilter.count = "250";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
			
		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-147: Verify 500 rows are displayed when 500 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 100 rows are displayed when 500 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-CL-148: Verify 500 rows are displayed when 500 Rows per Page is selected and user moves to next page";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that 500 rows are displayed when 500 Rows per Page is selected and user moves to next page";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "500 Rows per Page";
	//	objFilter.FilterXPath = Test_Elements.clRowsDropdown;
		objFilter.FilterListXPathSearch = "500";
		objFilter.count = "500";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
	
	
		return lstCoccidiaModel;
		
}
	
}


