package Models;
import java.util.ArrayList;
import java.util.Arrays;

import Test.Ancera.Test_Elements;

public class ExternalCoccidiaModel {

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
	
	public ExternalCoccidiaModel() {

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
	
	
	public static ArrayList<ExternalCoccidiaModel> FillData() {
		ArrayList<ExternalCoccidiaModel> lstExternalCoccidiaModel = new ArrayList<ExternalCoccidiaModel>();
		ExternalCoccidiaModel objTmp = new ExternalCoccidiaModel();

		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "Sample ID Filter";
		objTmp.TestCaseNameButtonActive = "AN-ECL-17: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-ECL-18: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-ECL-19: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-ECL-20: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-ECL-21: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-ECL-22: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CL-23: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-ECL-24: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "sample-id";
		objFilter.FilterXPath = "filter-Lab-Sample-ID";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Lab-Sample-ID"));
		objFilter.FilterListXPathSearch = "Lab-Sample-ID-place-holder-search";
		objFilter.SearchVlaue = "TD02";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("TD02"));
		objFilter.ClearInput = "Lab-Sample-ID-clear-input";
		objFilter.wait = 20000;
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


		objTmp = new ExternalCoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Instrument ID Filter";
		objTmp.TestCaseNameButtonActive = "AN-ECL-25: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-ECL-26: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-ECL-27: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-ECL-28: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-ECL-29: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-ECL-30: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CL-31: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-ECL-32: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
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
		lstExternalCoccidiaModel.add(objTmp);
		

		objTmp = new ExternalCoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Cartridge ID Filter";
		objTmp.TestCaseNameButtonActive = "AN-ECL-33: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-ECL-34: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-ECL-35: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-ECL-36: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-ECL-37: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-ECL-38: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CL-39: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-ECL-40: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
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
		lstExternalCoccidiaModel.add(objTmp);
		
		
		objTmp = new ExternalCoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Lane Filter";
		objTmp.TestCaseNameButtonActive = "AN-ECL-41: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-ECL-42: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-ECL-43: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-ECL-44: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-ECL-45: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-ECL-46: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CL-47: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-ECL-48: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
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
		lstExternalCoccidiaModel.add(objTmp);

		
		objTmp = new ExternalCoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Assay Filter";
		objTmp.TestCaseNameButtonActive = "AN-ECL-49: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-ECL-50: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-ECL-51: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-ECL-52: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-ECL-53: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-ECL-54: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CL-55: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-ECL-56: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = true;
		objTmp.lstFilters = new ArrayList<>();
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
		lstExternalCoccidiaModel.add(objTmp);               

		
//		objTmp = new ExternalCoccidiaModel();
//		objFilter = new ReportFilters();
//		objFilter.FilterName = "Improc Version Filter";
//		objTmp.TestCaseNameButtonActive = "AN-ECL-57: "+buttonActiveTitle+objFilter.FilterName;
//		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
//		objTmp.TestCaseName = "AN-ECL-58: "+applyFilterTitle+objFilter.FilterName;
//		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
//		objTmp.TestCaseNameSearch = "AN-ECL-59 "+filterIndicatorTitle+objFilter.FilterName;
//		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
//		objTmp.TestCaseNameBubbleFilterTop = "AN-ECL-60: Verify "+objFilter.FilterName+filterTopTitle;
//		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
//		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-ECL-61: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
//		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
//		objTmp.TestCaseNameClearInput = "AN-ECL-62: "+clearInputTitle+objFilter.FilterName;
//		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
//		objTmp.TestCaseNameHoverReset = "AN-CL-63: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
//		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
//		objTmp.TestCaseNameRevertBack = "AN-ECL-64: Verify "+objFilter.FilterName+revertBackTitle;
//		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
//		objTmp.ReloadPage = false;
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter.FilterID = "version";
//		objFilter.FilterXPath = "filter-Improc-Version";
//		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Improc-Version"));
//		objFilter.FilterListXPathSearch = "Improc-Version-place-holder-search";
//		objFilter.SearchVlaue = "Bl";
//		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Improc-Version_cust-cb-lst-txt_Blank"));
//		objFilter.getRowValue = Test_Elements.slImprocIDRow;
//		objFilter.rowValueExpected = objFilter.SearchVlaue;
//		objFilter.ClearInput = "Improc-Version-clear-input";
//		objFilter.wait = 5000;
//		objTmp.lstFilters.add(objFilter);
//		lstExternalCoccidiaModel.add(objTmp); 
		
		
		objTmp = new ExternalCoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection Site Name Filter";
		objTmp.TestCaseNameButtonActive = "AN-ECL-65: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-ECL-66: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-ECL-67: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-ECL-68: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-ECL-69: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-ECL-70: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CL-71: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-ECL-72: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "site-id";
		objFilter.FilterXPath = "filter-Collection-Site-Name";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Collection-Site-Name"));
		objFilter.FilterListXPathSearch = "Collection-Site-Name-place-holder-search";
		objFilter.SearchVlaue = "Test";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Test"));
		objFilter.getRowValue = Test_Elements.slSiteNameRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "Collection-Site-Name-clear-input";
		objFilter.wait = 4500;
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp); 
		
		
		objTmp = new ExternalCoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Sample Matrix Filter";
		objTmp.TestCaseNameButtonActive = "AN-ECL-73: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-ECL-74: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-ECL-75: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-ECL-76: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-ECL-77: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-ECL-78: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CL-79: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-ECL-80: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
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
		lstExternalCoccidiaModel.add(objTmp); 


		objTmp = new ExternalCoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Customer Sample ID Filter";
		objTmp.TestCaseNameButtonActive = "AN-ECL-81: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-ECL-82: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-ECL-83: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-ECL-84: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-ECL-85: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-ECL-86: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CL-87: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-ECL-88: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
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
		lstExternalCoccidiaModel.add(objTmp);

		
		objTmp = new ExternalCoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Received Filter";
		objTmp.TestCaseNameButtonActive = "AN-ECL-89: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-ECL-90: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-ECL-91: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-ECL-92: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-ECL-93: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-ECL-94: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CL-95: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-ECL-96: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = true;
		objTmp.lstFilters = new ArrayList<>();
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
		lstExternalCoccidiaModel.add(objTmp);
		
		
		objTmp = new ExternalCoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Kit Lot Filter";
		objTmp.TestCaseNameButtonActive = "AN-ECL-97: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-ECL-98: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-ECL-99: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-ECL-100: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-ECL-101: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-ECL-102: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CL-103: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-ECL-104: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "kit-lot";
		objFilter.FilterXPath =  "filter-Kit-Lot";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Kit-Lot"));
		objFilter.FilterListXPathSearch = "Kit-Lot-place-holder-search";
		objFilter.SearchVlaue = "Bl";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Blank"));
		objFilter.getRowValue = Test_Elements.slKitLotRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "Kit-Lot-clear-input";
		objFilter.wait = 4000;
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);
		
		
		objTmp = new ExternalCoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Piper User Filter";
		objTmp.TestCaseNameButtonActive = "AN-ECL-105: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-ECL-106: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-ECL-107: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-ECL-108: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-ECL-109: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-ECL-110: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CL-111: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-ECL-112: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "user-name";
		objFilter.FilterXPath = "filter-Piper-User";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Piper-User"));
		objFilter.FilterListXPathSearch = "Piper-User-place-holder-search";
		objFilter.SearchVlaue = "FHasan";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("FHasan"));
		objFilter.getRowValue = Test_Elements.slPiperUserRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "Piper-User-clear-input";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


		objTmp = new ExternalCoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection Site ID Filter";
		objTmp.TestCaseNameButtonActive = "AN-ECL-113: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-ECL-114: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-ECL-115: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-ECL-116: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-ECL-117: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-ECL-118: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CL-119: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-ECL-120: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "collection-site-id";
		objFilter.FilterXPath = "filter-Collection-Site-ID";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Collection-Site-ID"));
		objFilter.FilterListXPathSearch = "Collection-Site-ID-place-holder-search";
		objFilter.SearchVlaue = "Blank";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Blank"));
		objFilter.ClearInput = "Collection-Site-ID-clear-input";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


		objTmp = new ExternalCoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Requested Assay Filter";
		objTmp.TestCaseNameButtonActive = "AN-ECL-121: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-ECL-122: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-ECL-123: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-ECL-124: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-ECL-125: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-ECL-126: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CL-127: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-ECL-128: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "requested-assay";
		objFilter.FilterXPath = "filter-Requested-Assay";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Requested-Assay"));
		objFilter.FilterListXPathSearch = "Requested-Assay-place-holder-search";
		objFilter.SearchVlaue = "Blank";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Blank"));
		objFilter.ClearInput = "Requested-Assay-clear-input";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);
	
		
		objTmp = new ExternalCoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Flock ID Filter";
		objTmp.TestCaseNameButtonActive = "AN-ECL-129: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-ECL-130: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-ECL-131: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-ECL-132: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-ECL-133: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-ECL-134: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CL-135: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-ECL-136: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "flock-id";
		objFilter.FilterXPath = "filter-Flock-ID";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Flock-ID"));
		objFilter.FilterListXPathSearch = "Flock-ID-place-holder-search";
		objFilter.SearchVlaue = "Blank";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Blank"));
		objFilter.ClearInput = "Flock-ID-clear-input";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);
		
		
		objTmp = new ExternalCoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection-Site-Type Filter";
		objTmp.TestCaseNameButtonActive = "AN-ECL-137: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-ECL-138: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-ECL-139: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-ECL-140: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-ECL-141: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-ECL-142: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CL-143: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-ECL-144: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "collection-site-type";
		objFilter.FilterXPath = "filter-Collection-Site-Type";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Collection-Site-Type"));
		objFilter.FilterListXPathSearch = "Collection-Site-Type-place-holder-search";
		objFilter.SearchVlaue = "Complex";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Complex"));
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "Collection-Site-Type-clear-input";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);
	
		
		objTmp = new ExternalCoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Result Status Filter";
		objTmp.TestCaseNameButtonActive = "AN-ECL-145: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-ECL-146: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-ECL-147: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-ECL-148: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-ECL-149: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-ECL-150: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CL-151: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-ECL-152: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "result-status";
		objFilter.FilterXPath = "filter-Result-Status";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Result-Status"));
		objFilter.FilterListXPathSearch = "Result-Status-place-holder-search";
		objFilter.SearchVlaue = "Completed";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Completed"));
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "Result-Status-clear-input";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);
			
		
		objTmp = new ExternalCoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "multiple value from same filter";
		objTmp.TestCaseNameButtonActive = "AN-ECL-153: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-ECL-154: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-ECL-155: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-ECL-156: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-ECL-157: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-ECL-158: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CL-159: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-ECL-160: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "sample-id";
		objFilter.FilterXPath = "filter-Lab-Sample-ID";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Lab-Sample-ID", "Lab-Sample-ID"));
		objFilter.FilterListXPathSearch = "Lab-Sample-ID-place-holder-search";
		objFilter.SearchVlaue = "TD";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("TD05", "TD06"));
		objFilter.getRowValue = Test_Elements.slSampleIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "Lab-Sample-ID-clear-input";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);





//		objTmp = new ExternalCoccidiaModel();	
//		objFilter.FilterName = "Two Filters";
//		objTmp.TestCaseNameButtonActive = "AN-ECL-161: "+buttonActiveTitle+objFilter.FilterName;
//		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
//		objTmp.TestCaseName = "AN-ECL-162: "+applyFilterTitle+objFilter.FilterName;
//		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
//		objTmp.TestCaseNameSearch = "AN-ECL-163: "+filterIndicatorTitle+objFilter.FilterName;
//		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
//		objTmp.TestCaseNameBubbleFilterTop = "AN-ECL-164: Verify "+objFilter.FilterName+filterTopTitle;
//		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
//		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-ECL-165: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
//		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
//		objTmp.TestCaseNameClearInput = "AN-ECL-166: "+clearInputTitle+objFilter.FilterName;
//		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
//		objTmp.TestCaseNameHoverReset = "AN-CL-167: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
//		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
//		objTmp.TestCaseNameRevertBack = "AN-ECL-168: Verify "+objFilter.FilterName+revertBackTitle;
//		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
//		objTmp.ReloadPage = false;
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter = new ReportFilters();
//		objFilter.FilterName = "Two Filters";
//		objFilter.FilterID = "isntrument-id";
//		objFilter.FilterXPath = "filter-Instrument-ID";
//		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Instrument-ID", "Lab-Sample-ID"));
//		objFilter.FilterListXPathSearch = "Instrument-ID-place-holder-search";
//		objFilter.SearchVlaue = "";
//		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("DESKTOP-77VTAPV", "1"));
//		objFilter.ClearInput = "Instrument-ID-clear-input";
//		objFilter.FilterID = "sample-id";
//		objFilter.FilterXPath = "filter-Lab-Sample-ID";
////		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Lab-Sample-ID"));
//		objFilter.FilterListXPathSearch = "Lab-Sample-ID-place-holder-search";
//		objFilter.SearchVlaue = "1";
//	//	objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("TD05"));
//		objFilter.ClearInput = "Lab-Sample-ID-clear-input";
//		objFilter.wait = 4000;
//		objTmp.lstFilters.add(objFilter);
//		lstExternalCoccidiaModel.add(objTmp);

		return lstExternalCoccidiaModel;
	}


	public static ArrayList<ExternalCoccidiaModel> FillDate() {
		ArrayList<ExternalCoccidiaModel> lstExternalCoccidiaModel = new ArrayList<ExternalCoccidiaModel>();
		ExternalCoccidiaModel objTmp = new ExternalCoccidiaModel();

		objTmp.TestCaseName = "AN-ECL-05: Verify user can filter Today date from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of date filter after selecting 'Today'";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.Filter3 = false;
		objTmp.lstFilters = new ArrayList<>();
		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterXPath = Test_Elements.eclDateOpen;
		objFilter.FilterListXPathSearch = Test_Elements.eclToday;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "0";
		objFilter.fromDate = "0";
		objFilter.FilterListXPathPrefix = Test_Elements.eclDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.eclDateTo;
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-06: Verify user can filter Last 24 Hours from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of Last 24 Hours after selecting 'Last 24 Hours'";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.Filter3 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterXPath = Test_Elements.eclDateOpen;
		objFilter.FilterListXPathSearch = Test_Elements.eclLast24Hours;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "-1";
		objFilter.fromDate = "-1";
		objFilter.FilterListXPathPrefix = Test_Elements.eclDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.eclDateTo;
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);



		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-07: Verify user can filter Last 7 Days from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of Last 7 Days after selecting 'Last 7 Days'";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.Filter3 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterXPath = Test_Elements.eclDateOpen;
		objFilter.FilterListXPathSearch = Test_Elements.eclLast7Days;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "0";
		objFilter.fromDate = "-7";
		objFilter.FilterListXPathPrefix = Test_Elements.eclDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.eclDateTo;
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);



		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-08: Verify user can filter Last 30 Days from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of Last 30 Days after selecting 'Last 30 Days'";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.Filter3 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterXPath = Test_Elements.eclDateOpen;
		objFilter.FilterListXPathSearch = Test_Elements.eclLast30Days;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "0";
		objFilter.fromDate = "-30";
		objFilter.FilterListXPathPrefix = Test_Elements.eclDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.eclDateTo;
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-09: Verify user can filter Last Month from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of Last Month after selecting 'Last Month'";
		objTmp.Filter1 = false;
		objTmp.Filter2 = true;
		objTmp.Filter3 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterXPath = Test_Elements.eclDateOpen;
		objFilter.FilterListXPathSearch = Test_Elements.eclLastMonth;
		objFilter.toMonth = "-1";
		objFilter.fromMonth = "-1";
		objFilter.toDate = "30";
		objFilter.fromDate = "1";
		objFilter.FilterListXPathPrefix = Test_Elements.eclDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.eclDateTo;
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-10: Verify user can filter This Month from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of This Month after selecting 'This Month'";
		objTmp.Filter1 = false;
		objTmp.Filter2 = false;
		objTmp.Filter3 = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterXPath = Test_Elements.eclDateOpen;
		objFilter.FilterListXPathSearch = Test_Elements.eclThisMonth;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "0";
		objFilter.fromDate = "1";
		objFilter.FilterListXPathPrefix = Test_Elements.eclDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.eclDateTo;
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


		return lstExternalCoccidiaModel;
	}




	public static ArrayList<ExternalCoccidiaModel> EnterDate() {
		ArrayList<ExternalCoccidiaModel> lstExternalCoccidiaModel = new ArrayList<ExternalCoccidiaModel>();
		ExternalCoccidiaModel objTmp = new ExternalCoccidiaModel();

		objTmp.TestCaseName = "AN-ECL-11: Verify user cannot apply filter with invalid date";
		objTmp.TestCaseDescription = "This testcase will verify that user cannot add date with invalid from and to date";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.lstFilters = new ArrayList<>();
		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "Enter invalid date";
		objFilter.FilterListXPathPrefix = Test_Elements.eclDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.eclDateTo;
		objFilter.fromDate = "12/01/2020";
		objFilter.toDate = "11/11/202020";
		objFilter.alertMessage = "Please select a valid date";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-12: Verify user cannot apply filter with any other date format except mm/dd/yyyy";
		objTmp.TestCaseDescription = "This testcase will verify that user cannot apply filter with any other date format except mm/dd/yyyy";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Enter any other date format except mm/dd/yyyy";
		objFilter.FilterListXPathPrefix = Test_Elements.eclDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.eclDateTo;
		objFilter.fromDate = "12/01/2020";
		objFilter.toDate = "20/12/2020";
		objFilter.alertMessage = "Please select a valid date";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-13: Verify user cannot apply filter with from date greater than to date";
		objTmp.TestCaseDescription = "This testcase will verify that user cannot apply filter with from date greater than to date";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Enter from date greater than to date";
		objFilter.FilterListXPathPrefix = Test_Elements.eclDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.eclDateTo;
		objFilter.fromDate = "12/01/2020";
		objFilter.toDate = "10/01/2020";
		objFilter.alertMessage = "Please select a valid date";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-14: Verify user can search for records with valid date";
		objTmp.TestCaseDescription = "This testcase will verify that user can search for records with valid date";
		objTmp.Filter1 = false;
		objTmp.Filter2 = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Enter valid date";
		objFilter.FilterListXPathPrefix = Test_Elements.eclDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.eclDateTo;
		objFilter.fromDate = "12/01/2020";
		objFilter.toDate = "01/07/2021";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);

		return lstExternalCoccidiaModel;

	}


	public static ArrayList<ExternalCoccidiaModel> pagination() {
		ArrayList<ExternalCoccidiaModel> lstExternalCoccidiaModel = new ArrayList<ExternalCoccidiaModel>();
		ExternalCoccidiaModel objTmp = new ExternalCoccidiaModel();

		objTmp.TestCaseName = "AN-ECL-170: Verify pagination exist on Salmonella Log report";
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
		objFilter.paginationCount = Test_Elements.eclTotalPages;
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);

		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-171: Verify user navigates to last page on clicking '>>' button in pagnation";
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
		objFilter.paginationCount = Test_Elements.eclTotalPages;
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-172: Verify user navigates to previous page on clicking '<' button in pagnation";
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
		objFilter.paginationCount = Test_Elements.eclTotalPages;
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-173: Verify user navigates to first page on clicking '<<' button in pagnation";
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
		objFilter.paginationCount = Test_Elements.eclTotalPages;
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-174: Verify user navigates to next page on clicking '>' button in pagnation";
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
		objFilter.paginationCount = Test_Elements.eclTotalPages;
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);

		return lstExternalCoccidiaModel;	
	}



	public static ArrayList<ExternalCoccidiaModel> searchRows() {
		ArrayList<ExternalCoccidiaModel> lstExternalCoccidiaModel = new ArrayList<ExternalCoccidiaModel>();
		ExternalCoccidiaModel objTmp = new ExternalCoccidiaModel();

		objTmp.TestCaseName = "AN-ECL-175: Verify 100 rows are displayed when 100 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 100 rows are displayed when 100 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-ECL-176: Verify 100 rows are displayed when 100 Rows per Page is selected and user moves to next page";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that 100 rows are displayed when 100 Rows per Page is selected and user moves to next page";
		objTmp.lstFilters = new ArrayList<>();
		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "100 Rows per Page";
		objFilter.FilterListXPathSearch = "100";
		objFilter.count = "100";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-177: Verify 250 rows are displayed when 250 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 250 rows are displayed when 250 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-ECL-178: Verify 500 rows are displayed when 250 Rows per Page is selected and user moves to next page";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that 250 rows are displayed when 250 Rows per Page is selected and user moves to next page";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "250 Rows per Page";
		objFilter.FilterListXPathSearch = "250";
		objFilter.count = "250";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-179: Verify 500 rows are displayed when 500 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 100 rows are displayed when 500 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-ECL-180: Verify 500 rows are displayed when 500 Rows per Page is selected and user moves to next page";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that 500 rows are displayed when 500 Rows per Page is selected and user moves to next page";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "500 Rows per Page";
		objFilter.FilterListXPathSearch = "500";
		objFilter.count = "500";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);

		return lstExternalCoccidiaModel;
	}
}


