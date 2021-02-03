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

	public SalmonellaModel() {

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


	public static ArrayList<SalmonellaModel> FillData() {
		ArrayList<SalmonellaModel> lstSalmonellaModel = new ArrayList<SalmonellaModel>();
		SalmonellaModel objTmp = new SalmonellaModel();

		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "Load Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-18: Verify apply filter button becomes active on selecting 'Medium' from Load filter";
		objTmp.TestCaseDescriptionButtonActive = "This test case will verify that filter button becomes active on selecting 'Medium' from Load filter";
		objTmp.TestCaseName = "AN-SL-19: Verify user can filter 'Medium' from Load Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter 'Medium' from Load Filter";
		objTmp.TestCaseNameSearch = "AN-SL-20: Verify that blue filter indicator appears next to applied filter and apply filter button becomes inactive on applying 'Medium' from Load Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that blue filter indicator appears next to applied filter and apply filter button becomes inactive on applying 'Medium' from Load Filter";
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-21: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-22: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-23: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-SL-24: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-SL-25: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "w2-cell-count";
		objFilter.FilterXPath = "filter-Load";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Load"));
		objFilter.FilterListXPathSearch = "Load-place-holder-search";
		objFilter.SearchVlaue = "Medium";
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("Med"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Medium"));
		objFilter.getRowValue = Test_Elements.slLoadRow;
		objFilter.rowValueExpected = Test_Elements.slLoadYellow;
		objFilter.ClearInput = "Load-clear-input";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);


		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Load Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-26: Verify apply filter button becomes active on selecting 'High' from Load filter";
		objTmp.TestCaseDescriptionButtonActive = "This test case will verify that filter button becomes active on selecting 'High' from Load filter";
		objTmp.TestCaseName = "AN-SL-27: Verify user can filter 'High' from Load Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter 'High' from Load Filter";
		objTmp.TestCaseNameSearch = "AN-SL-28: Verify that blue filter indicator appears next to applied filter and apply filter button becomes inactive on applying 'High' from Load Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that blue filter indicator appears next to applied filter and apply filter button becomes inactive on applying 'High' from Load Filter";
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-29: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-30: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-31: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-SL-32: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-SL-33: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "w2-cell-count";
		objFilter.FilterXPath = "filter-Load";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Load"));
		objFilter.FilterListXPathSearch = "Load-place-holder-search";
		objFilter.SearchVlaue = "High";
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("High"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("High"));
		objFilter.getRowValue = Test_Elements.slLoadRow;
		objFilter.rowValueExpected = Test_Elements.slLoadRed;
		objFilter.ClearInput = "Load-clear-input";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);


		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Load Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-34: Verify apply filter button becomes active on selecting 'Low' from Load filter";
		objTmp.TestCaseDescriptionButtonActive = "This test case will verify that filter button becomes active on selecting 'Low' from Load filter";
		objTmp.TestCaseName = "AN-SL-35: Verify user can filter 'Low' from Load Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter 'Low' from Load Filter";
		objTmp.TestCaseNameSearch = "AN-SL-36: Verify that blue filter indicator appears next to applied filter and apply filter button becomes inactive on applying 'Low' from Load Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that blue filter indicator appears next to applied filter and apply filter button becomes inactive on applying 'Low' from Load Filter";
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-37: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-38: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-39: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-SL-40: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-SL-41: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "w2-cell-count";
		objFilter.FilterXPath = "filter-Load";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Load"));
		objFilter.FilterListXPathSearch = "Load-place-holder-search";
		objFilter.SearchVlaue = "Low";
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("Low"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Low")); 
		objFilter.getRowValue = Test_Elements.slLoadRow;
		objFilter.rowValueExpected = Test_Elements.slLoadGreen; 
		objFilter.ClearInput = "Load-clear-input";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);


		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Lab Sample ID Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-42: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-43: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-44: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-45: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-46: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-47: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-SL-48: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-SL-49: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "sample-id";
		objFilter.FilterXPath = "filter-Lab-Sample-ID";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Lab-Sample-ID"));
	//	objFilter.FilterListXPathSearch = "Lab-Sample-ID-place-holder-search";
	//	objFilter.SearchVlaue = "0604sample1";
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("0604sample1"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("0604sample1"));
	//	objFilter.ClearInput = "Lab-Sample-ID-clear-input";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);


		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Instrument ID Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-50: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-51: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-52: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-53: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-54: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-55: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-SL-56: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-SL-57: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "isntrument-id";
		objFilter.FilterXPath = "filter-Instrument-ID";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Instrument-ID"));
	//	objFilter.FilterListXPathSearch = "Instrument-ID-place-holder-search";
	//	objFilter.SearchVlaue = "PSN0004";
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("PSN0004"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("PSN0004"));
	//	objFilter.ClearInput = "Instrument-ID-clear-input";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);


		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Cartridge ID Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-58: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-59: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-60: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-61: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-62: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-63: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-SL-64: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-SL-65: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "cartrtidge-id";
		objFilter.FilterXPath = "filter-Cartridge-ID";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Cartridge-ID"));
//		objFilter.FilterListXPathSearch = "Cartridge-ID-place-holder-search";
//		objFilter.SearchVlaue = "20201023_swtest1";  
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("20201023_swtest1"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("20201023_swtest1")); 
//		objFilter.ClearInput = "Cartridge-ID-clear-input";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);


		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Lane Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-66: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-67: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-68: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-69: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-70: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-71: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-SL-72: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-SL-73: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "lane-num";
		objFilter.FilterXPath = "filter-Lane";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Lane"));
	//	objFilter.FilterListXPathSearch = "Lane-place-holder-search";
	//	objFilter.SearchVlaue = "10";
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("10"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("10")); 
	//	objFilter.ClearInput = "Lane-clear-input";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);


		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Assay Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-74: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-75: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-76: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-77: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-78: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-79: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-SL-80: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-SL-81: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "pathogen";
		objFilter.FilterXPath = "filter-Assay";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Assay"));
		objFilter.FilterListXPathSearch = "Assay-place-holder-search";
		objFilter.SearchVlaue = "listeria-probes";   
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("listeria-probes"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("listeria-probes"));  //uat
		objFilter.ClearInput = "Assay-clear-input";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);


		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "QC Code Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-82: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-83: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-84: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-85: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-86: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-87: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-SL-88: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-SL-89: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "count-outcome";
		objFilter.FilterXPath = "filter-QC-Code";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("QC-Code"));
		objFilter.FilterListXPathSearch = "QC-Code-place-holder-search";
		objFilter.SearchVlaue = "P";
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("p"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("PASS"));
		objFilter.ClearInput = "QC-Code-clear-input";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);                   


		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Improc Version Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-90: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-91: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-92: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-93: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-94: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-95: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-SL-96: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-SL-97: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "version";
		objFilter.FilterXPath = "filter-Improc-Version";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Improc-Version"));
		objFilter.FilterListXPathSearch = "Improc-Version-place-holder-search";
		objFilter.SearchVlaue = "pro";
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("pro"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("process"));
		objFilter.ClearInput = "Improc-Version-clear-input";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp); 



		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection Site Name Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-98: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-99: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-100: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-101: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-102: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-103: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-SL-104: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-SL-105: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "site-id";
		objFilter.FilterXPath = "filter-Collection-Site-Name";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Collection-Site-Name"));
		objFilter.FilterListXPathSearch = "Collection-Site-Name-place-holder-search";
		objFilter.SearchVlaue = "Test";
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("Test"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Test"));
		objFilter.ClearInput = "Collection-Site-Name-clear-input";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp); 



		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection Site ID Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-106: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-107: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-108: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-109: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-110: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-111: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-SL-112: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-SL-113: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "collection-site-id";
		objFilter.FilterXPath = "filter-Collection-Site-ID";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Collection-Site-ID"));
		objFilter.FilterListXPathSearch = "Collection-Site-ID-place-holder-search";
		objFilter.SearchVlaue = "1001002";
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("1001002"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1001002"));
		objFilter.ClearInput = "Collection-Site-ID-clear-input";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp); 


		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection Site Type Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-114: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-115: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-116: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-117: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-118: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-119: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-SL-120: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-SL-121: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "collection-site-type";
		objFilter.FilterXPath = "filter-Collection-Site-Type"; 
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Collection-Site-Type"));
		objFilter.FilterListXPathSearch = "Collection-Site-Type-place-holder-search";
		objFilter.SearchVlaue = "Farm";
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("Farm"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Farm"));
		objFilter.ClearInput = "Collection-Site-Type-clear-input";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp); 


		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Sample Matrix Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-122: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-123: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-124: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-125: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-126: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-127: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-SL-128: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-SL-129: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "sample-matrix";
		objFilter.FilterXPath = "filter-Sample-Matrix";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Sample-Matrix"));
		objFilter.FilterListXPathSearch = "Sample-Matrix-place-holder-search";
		objFilter.SearchVlaue = "Feces";
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("Feces"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Feces"));
		objFilter.ClearInput = "Sample-Matrix-clear-input";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp); 


		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Customer Sample ID Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-130: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-131: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-132: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-133: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-134: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-135: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-SL-136: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-SL-137: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "customer-sample-id";
		objFilter.FilterXPath =  "filter-Customer-Sample-Id";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Customer-Sample-Id"));
		objFilter.FilterListXPathSearch = "Customer-Sample-Id-place-holder-search";
		objFilter.SearchVlaue = "BAL-1234";
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("BAL-1234"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("BAL-1234"));
		objFilter.ClearInput = "Customer-Sample-Id-clear-input";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);


		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Received Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-138: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-139: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-140: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-141: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-142: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-143: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-SL-144: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-SL-145: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "meta-data-recieved";
		objFilter.FilterXPath = "filter-Date-Received";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Date-Received"));
		objFilter.FilterListXPathSearch = "Date-Received-place-holder-search";
		objFilter.SearchVlaue = "06-29-2020";
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("06-29-2020"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("06-29-2020"));
		objFilter.ClearInput = "Date-Received-clear-input";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);


		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Kit Lot Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-146: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-147: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-148: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-149: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-150: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-151: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-SL-152: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-SL-153: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "kit-lot";
		objFilter.FilterXPath =  "filter-Kit-Lot";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Kit-Lot"));
		objFilter.FilterListXPathSearch = "Kit-Lot-place-holder-search";
		objFilter.SearchVlaue = "SAL456773";
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("SAL456773"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("SAL456773"));
		objFilter.ClearInput = "Kit-Lot-clear-input";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);


		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Piper User Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-154: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-155: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-156: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-157: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-158: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-159: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-SL-160: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-SL-161: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "user-name";
		objFilter.FilterXPath = "filter-Piper-User";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Piper-User"));
		objFilter.FilterListXPathSearch = "Piper-User-place-holder-search";
		objFilter.SearchVlaue = "FHasan";
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("FHasan"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("FHasan"));
		objFilter.ClearInput = "Piper-User-clear-input";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);


		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Result Status Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-162: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-163: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-164: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-165: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-166: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-167: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-SL-168: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-SL-169: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "result-status";
		objFilter.FilterXPath = "filter-Result-Status";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Result-Status"));
		objFilter.FilterListXPathSearch = "Result-Status-place-holder-search";
		objFilter.SearchVlaue = "Blank";
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("Blank"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Blank"));
		objFilter.ClearInput = "Result-Status-clear-input";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);


		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "multiple value from same filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-170: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-171: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-172: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-173: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-174: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-175: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-SL-176: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-SL-177: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "sample-id";
		objFilter.FilterXPath = "filter-Lab-Sample-ID";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Lab-Sample-ID", "Lab-Sample-ID"));
		objFilter.FilterListXPathSearch = "Lab-Sample-ID-place-holder-search";
		objFilter.SearchVlaue = "0604sample";
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("0604"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("0604sample1", "0604sample2")); 
		objFilter.ClearInput = "Lab-Sample-ID-clear-input";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);



				objTmp = new SalmonellaModel();
				objFilter = new ReportFilters();
				objFilter.FilterName = "Two Filters";
				objTmp.TestCaseNameButtonActive = "AN-SL-178: "+buttonActiveTitle+objFilter.FilterName;
				objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
				objTmp.TestCaseName = "AN-SL-179: "+applyFilterTitle+objFilter.FilterName;
				objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
				objTmp.TestCaseNameSearch = "AN-SL-180: "+filterIndicatorTitle+objFilter.FilterName;
				objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
				objTmp.TestCaseNameBubbleFilterTop = "AN-SL-181: Verify "+objFilter.FilterName+filterTopTitle;
				objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
				objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-182: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
				objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
				objTmp.TestCaseNameClearInput = "AN-SL-183: "+clearInputTitle+objFilter.FilterName;
				objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
				objTmp.TestCaseNameHoverReset = "AN-SL-184: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
				objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
				objTmp.TestCaseNameRevertBack = "AN-SL-185: Verify "+objFilter.FilterName+revertBackTitle;
				objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
				objTmp.ReloadPage = false;
				objTmp.lstFilters = new ArrayList<>();
				objFilter = new ReportFilters();
				objFilter.FilterID = "isntrument-id";
		//		objFilter.FilterXPath = "filter-Instrument-ID";
				objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Lab-Sample-ID", "Instrument-ID"));
				objFilter.FilterListXPathSearch = "Instrument-ID-place-holder-search";
			//	objFilter.SearchVlaue = "finalsitetest1";
			//	objFilter.SearchVlaue = "PSN0013";
				objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("finalsitetest1", "PSN0013"));
				objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("final", "PSN0013"));
	//			objFilter.getRowValue = Test_Elements.slInstrumentIDRow;
	//			objFilter.rowValueExpected = objFilter.SearchVlaue; 
				objFilter.ClearInput = "Instrument-ID-clear-input";
				objFilter.FilterID = "sample-id";
				objFilter.FilterXPath = "filter-Lab-Sample-ID";
		//		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Lab-Sample-ID"));
				objFilter.FilterListXPathSearch = "Lab-Sample-ID-place-holder-search";
				
		//		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("0604sample1"));
		//		objFilter.getRowValue = Test_Elements.slSampleIDRow;
		//		objFilter.rowValueExpected = objFilter.SearchVlaue; 
				objFilter.ClearInput = "Lab-Sample-ID-clear-input";
		//		objFilter.wait = 3000;
				objTmp.lstFilters.add(objFilter);
				lstSalmonellaModel.add(objTmp);

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
		objFilter.FilterXPath = Test_Elements.slDateOpen;
		objFilter.FilterListXPathSearch = Test_Elements.slToday;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "0";
		objFilter.fromDate = "0";
		objFilter.FilterListXPathPrefix = Test_Elements.slDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.slDateTo;
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
		objFilter.FilterXPath = Test_Elements.slDateOpen;
		objFilter.FilterListXPathSearch = Test_Elements.slLast24Hours;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "-1";
		objFilter.fromDate = "-1";
		objFilter.FilterListXPathPrefix = Test_Elements.slDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.slDateTo;
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
		objFilter.FilterXPath = Test_Elements.slDateOpen;
		objFilter.FilterListXPathSearch = Test_Elements.slLast7Days;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "0";
		objFilter.fromDate = "-7";
		objFilter.FilterListXPathPrefix = Test_Elements.slDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.slDateTo;
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
		objFilter.FilterXPath = Test_Elements.slDateOpen;
		objFilter.FilterListXPathSearch = Test_Elements.slLast30Days;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "0";
		objFilter.fromDate = "-30";
		objFilter.FilterListXPathPrefix = Test_Elements.slDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.slDateTo;
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
		objFilter.FilterXPath = Test_Elements.slDateOpen;
		objFilter.FilterListXPathSearch = Test_Elements.slLastMonth;
		objFilter.toMonth = "-1";
		objFilter.fromMonth = "-1";
		objFilter.toDate = "30";
		objFilter.fromDate = "1";
		objFilter.FilterListXPathPrefix = Test_Elements.slDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.slDateTo;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);


//		objTmp = new SalmonellaModel();
//		objTmp.TestCaseName = "AN-SL-11: Verify user can filter This Month from date Filter";
//		objTmp.TestCaseDescription = "This testcase will verify the functionality of This Month after selecting 'This Month'";
//		objTmp.Filter1 = false;
//		objTmp.Filter2 = false;
//		objTmp.Filter3 = true;
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter = new ReportFilters();
//		objFilter.FilterName = "Date Filter";
//		objFilter.FilterXPath = Test_Elements.slDateOpen;
//		objFilter.FilterListXPathSearch = Test_Elements.slThisMonth;
//		objFilter.toMonth = "0";
//		objFilter.fromMonth = "0";
//		objFilter.toDate = "0";
//		objFilter.fromDate = "1";
//		objFilter.FilterListXPathPrefix = Test_Elements.slDateFrom;
//		objFilter.FilterListXPathSuffix = Test_Elements.slDateTo;
//		objTmp.lstFilters.add(objFilter);
//		lstSalmonellaModel.add(objTmp);


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
		objFilter.FilterListXPathPrefix = Test_Elements.slDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.slDateTo;
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
		objFilter.FilterListXPathPrefix = Test_Elements.slDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.slDateTo;
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
		objFilter.FilterListXPathPrefix = Test_Elements.slDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.slDateTo;
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
		objFilter.FilterListXPathPrefix = Test_Elements.slDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.slDateTo;
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
		objFilter.FilterListXPathSearch = "100";
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
		objFilter.FilterListXPathSearch = "250";
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
		objFilter.FilterListXPathSearch = "500";
		objFilter.count = "500";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		return lstSalmonellaModel;	
	}
	
	
	public static ArrayList<SalmonellaModel> sorting() {
		ArrayList<SalmonellaModel> lstSalmonellaModel = new ArrayList<SalmonellaModel>();
		SalmonellaModel objTmp = new SalmonellaModel();
	
		
//		objTmp.TestCaseName = "AN-SL-193: Verify 100 rows are displayed when 100 Rows per Page is selected";
//		objTmp.TestCaseDescription = "This testcase will verify that 100 rows are displayed when 100 Rows per Page is selected";
//		objTmp.TestCaseNameSearch = "AN-SL-194: Verify 100 rows are displayed when 100 Rows per Page is selected and user moves to next page";
//		objTmp.TestCaseDescriptionSearch = "This testcase will verify that 100 rows are displayed when 100 Rows per Page is selected and user moves to next page";
//		objTmp.lstFilters = new ArrayList<>();
//		ReportFilters objFilter = new ReportFilters();
//		objFilter.FilterName = "1";
//		objFilter.ColumnID = "sort-laneNum";
//		objFilter.count = "1";
//		objTmp.lstFilters.add(objFilter);
//		lstSalmonellaModel.add(objTmp);
//		
//		objTmp = new SalmonellaModel();
		
		ReportFilters objFilter = new ReportFilters();
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
		
//		objTmp = new SalmonellaModel();
//		objFilter = new ReportFilters();
//		objFilter.FilterName = "Result Time";
//		objTmp.TestCaseName = "AN-SL-226: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
//		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
//		objTmp.lstFilters = new ArrayList<>();
//		objTmp.sortLogic1 = true;
//		objFilter.ColumnID = "sort-time";           
//		objFilter.count = "9";                 
//		objTmp.lstFilters.add(objFilter);
//		lstSalmonellaModel.add(objTmp);
		
		
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
		
//		objTmp = new SalmonellaModel();
//		objFilter = new ReportFilters();
//		objFilter.FilterName = "W2 Cell Count";
//		objTmp.TestCaseName = "AN-SL-238: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
//		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
//		objTmp.lstFilters = new ArrayList<>();
//		objTmp.sortLogic1 = true;
//		objFilter.ColumnID = "sort-w2CellCount";           
//		objFilter.count = "22";
//		objTmp.lstFilters.add(objFilter);
//		lstSalmonellaModel.add(objTmp);
		
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
		
		return lstSalmonellaModel;	
	}
}


