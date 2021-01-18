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
		objTmp.TestCaseNameButtonActive = "AN-SL-24: Verify apply filter button becomes active on selecting 'Medium' from Load filter";
		objTmp.TestCaseDescriptionButtonActive = "This test case will verify that filter button becomes active on selecting 'Medium' from Load filter";
		objTmp.TestCaseName = "AN-SL-25: Verify user can filter 'Medium' from Load Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter 'Medium' from Load Filter";
		objTmp.TestCaseNameSearch = "AN-SL-26: Verify that blue filter indicator appears next to applied filter and apply filter button becomes inactive on applying 'Medium' from Load Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that blue filter indicator appears next to applied filter and apply filter button becomes inactive on applying 'Medium' from Load Filter";
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-27: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-28: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-29: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-SL-30: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "w2-cell-count";
		objFilter.FilterXPath = "Load";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Load"));
		objFilter.FilterListXPathSearch = "place-holder-search-Load";
		objFilter.SearchVlaue = "Medium";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Medium"));
		objFilter.getRowValue = Test_Elements.slLoadRow;
		objFilter.rowValueExpected = Test_Elements.slLoadYellow;
		objFilter.ClearInput = "clear-input-Load";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
	
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Load Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-38: Verify apply filter button becomes active on selecting 'High' from Load filter";
		objTmp.TestCaseDescriptionButtonActive = "This test case will verify that filter button becomes active on selecting 'High' from Load filter";
		objTmp.TestCaseName = "AN-SL-32: Verify user can filter 'High' from Load Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter 'High' from Load Filter";
		objTmp.TestCaseNameSearch = "AN-SL-33: Verify that blue filter indicator appears next to applied filter and apply filter button becomes inactive on applying 'High' from Load Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that blue filter indicator appears next to applied filter and apply filter button becomes inactive on applying 'High' from Load Filter";
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-34: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-35: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-36: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-SL-37: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "w2-cell-count";
		objFilter.FilterXPath = "Load";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Load"));
		objFilter.FilterListXPathSearch = "place-holder-search-Load";
		objFilter.SearchVlaue = "High";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("High"));
		objFilter.getRowValue = Test_Elements.slLoadRow;
		objFilter.rowValueExpected = Test_Elements.slLoadRed;
		objFilter.ClearInput = "clear-input-Load";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Load Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-38: Verify apply filter button becomes active on selecting 'Low' from Load filter";
		objTmp.TestCaseDescriptionButtonActive = "This test case will verify that filter button becomes active on selecting 'Low' from Load filter";
		objTmp.TestCaseName = "AN-SL-39: Verify user can filter 'Low' from Load Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter 'Low' from Load Filter";
		objTmp.TestCaseNameSearch = "AN-SL-40: Verify that blue filter indicator appears next to applied filter and apply filter button becomes inactive on applying 'Low' from Load Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that blue filter indicator appears next to applied filter and apply filter button becomes inactive on applying 'Low' from Load Filter";
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-41: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-42: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-43: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-SL-44: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "w2-cell-count";
		objFilter = new ReportFilters();
		objFilter.FilterName = "Load Filter";
		objFilter.FilterXPath = "Load";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Load"));
		objFilter.FilterListXPathSearch = "place-holder-search-Load";
		objFilter.SearchVlaue = "Low";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Low"));
		objFilter.getRowValue = Test_Elements.slLoadRow;
		objFilter.rowValueExpected = Test_Elements.slLoadGreen;  
		objFilter.ClearInput = "clear-input-Load";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);


		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Lab Sample ID Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-45: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-46: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-47: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-48: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-49: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-50: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-SL-51: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "sample-id";
		objFilter.FilterXPath = "Lab Sample ID";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Lab Sample ID"));
		objFilter.FilterListXPathSearch = "place-holder-search-Lab Sample ID";
		objFilter.SearchVlaue = "adv";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("adv"));
		objFilter.getRowValue = Test_Elements.slSampleIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "clear-input-Lab Sample ID";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Instrument ID Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-52: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-53: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-54: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-55: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-56: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-57: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-SL-58: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "isntrument-id";
		objFilter.FilterXPath = "Instrument ID";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Instrument ID"));
		objFilter.FilterListXPathSearch = "place-holder-search-Instrument ID";
		objFilter.SearchVlaue = "PSN0004";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("PSN0004"));
		objFilter.getRowValue = Test_Elements.slInstrumentIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "clear-input-Instrument ID";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Cartridge ID Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-59: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-60: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-61: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-62: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-63: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-64: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-SL-65: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "cartrtidge-id";
		objFilter.FilterXPath = "Cartridge ID";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Cartridge ID"));
		objFilter.FilterListXPathSearch = "place-holder-search-Cartridge ID";
		objFilter.SearchVlaue = "20201023_swtest1";  
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("20201023_swtest1"));  //uat
		objFilter.getRowValue = Test_Elements.slCatridgeIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "clear-input-Cartridge ID";
		objFilter.wait = 3000;
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
		objTmp.TestCaseNameRevertBack = "AN-SL-72: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "lane-num";
		objFilter.FilterXPath = "Lane";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Lane"));
		objFilter.FilterListXPathSearch = "place-holder-search-Lane";
		objFilter.SearchVlaue = "10";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("10"));
		objFilter.getRowValue = Test_Elements.slLaneIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "clear-input-Lane";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
	
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Assay Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-73: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-74: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-75: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-76: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-77: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-78: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-SL-79: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "pathogen";
		objFilter.FilterXPath = "Assay";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Assay"));
		objFilter.FilterListXPathSearch = "place-holder-search-Assay";
		objFilter.SearchVlaue = "listeria-probes";   
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("listeria-probes"));  //uat
		objFilter.getRowValue = Test_Elements.slAssayRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "clear-input-Assay";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
			
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "QC Code Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-80: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-81: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-82: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-83: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-84: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-85: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-SL-86: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "count-outcome";
		objFilter.FilterXPath = "QC Code";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("QC Code"));
		objFilter.FilterListXPathSearch = "place-holder-search-QC Code";
		objFilter.SearchVlaue = "0.000";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("0.000"));
		objFilter.getRowValue = Test_Elements.slQCCodeRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-QC Code";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);                   

		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Improc Version Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-87: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-88: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-89: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-90: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-91: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-92: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-SL-93: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "version";
		objFilter.FilterXPath = "Improc Version";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Improc Version"));
		objFilter.FilterListXPathSearch = "place-holder-search-Improc Version";
		objFilter.SearchVlaue = "0.0";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("0.0"));
		objFilter.getRowValue = Test_Elements.slImprocIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Improc Version";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp); 
		
	
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection Site Name Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-94: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-53: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-54: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-55: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-56: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-57: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-SL-58: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "site-id";
		objFilter.FilterXPath = "Collection Site Name";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Collection Site Name"));
		objFilter.FilterListXPathSearch = "place-holder-search-Collection Site Name";
		objFilter.SearchVlaue = "Peco Gordo";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Peco Gordo"));
		objFilter.getRowValue = Test_Elements.slSiteNameRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Collection Site Name";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp); 
		

		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection Site ID Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-52: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-53: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-54: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-55: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-56: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-57: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-SL-58: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "collection-site-id";
		objFilter.FilterXPath = "Collection Site ID";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Collection Site ID"));
		objFilter.FilterListXPathSearch = "place-holder-search-Collection Site ID";
		objFilter.SearchVlaue = "1001001";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1001001"));
		objFilter.getRowValue = Test_Elements.slSiteIDRow;
		objFilter.rowValueExpected = "";
		objFilter.ClearInput = "clear-input-Collection Site ID";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp); 
		
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection Site Type Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-52: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-53: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-54: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-55: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-56: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-57: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-SL-58: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "collection-site-type";
		objFilter.FilterXPath = "Collection Site Type"; 
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Collection Site Type"));
		objFilter.FilterListXPathSearch = "place-holder-search-Collection Site Type";
		objFilter.SearchVlaue = "Farm";
		objFilter.FilterListXPathPrefix = Test_Elements.slSiteTypebeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.slSiteTypeafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.slSiteTypeafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Farm"));
		objFilter.getRowValue = Test_Elements.slSiteTypeRow;
		objFilter.rowValueExpected = "";
		objFilter.ClearInput = "clear-input-Collection Site Type";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp); 
		
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Sample Matrix Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-52: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-53: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-54: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-55: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-56: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-57: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-SL-58: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "sample-matrix";
		objFilter.FilterXPath = "Sample Matrix";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Sample Matrix"));
		objFilter.FilterListXPathSearch = "place-holder-search-Sample Matrix";
		objFilter.SearchVlaue = "Feces";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Feces"));
		objFilter.getRowValue = Test_Elements.slSampleMatrixRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Sample Matrix";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp); 
		
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Customer Sample ID Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-52: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-53: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-54: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-55: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-56: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-57: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-SL-58: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "customer-sample-id";
		objFilter.FilterXPath =  "Customer Sample Id";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Customer Sample Id"));
		objFilter.FilterListXPathSearch = "place-holder-search-Customer Sample Id";
		objFilter.SearchVlaue = "BAL-1234";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("BAL-1234"));
		objFilter.getRowValue = Test_Elements.slCSampleIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Customer Sample Id";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Received Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-52: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-53: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-54: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-55: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-56: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-57: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-SL-58: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "meta-data-recieved";
		objFilter.FilterXPath = "Date Received";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Date Received"));
		objFilter.FilterListXPathSearch = "place-holder-search-Date Received";
		objFilter.SearchVlaue = "06-29-2020";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("06-29-2020"));
		objFilter.getRowValue = Test_Elements.slDateReceivedRow;
		objFilter.rowValueExpected = "";
		objFilter.ClearInput = "clear-input-Date Received";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Kit Lot Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-52: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-53: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-54: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-55: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-56: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-57: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-SL-58: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "kit-lot";
		objFilter.FilterXPath =  "Kit Lot";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Kit Lot"));
		objFilter.FilterListXPathSearch = "place-holder-search-Kit Lot";
		objFilter.SearchVlaue = "SAL456773";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("SAL456773"));
		objFilter.getRowValue = Test_Elements.slKitLotRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Kit Lot";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		
		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Piper User Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-52: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-53: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-54: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-55: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-56: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-57: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-SL-58: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "user-name";
		objFilter.FilterXPath = "Piper User";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Piper User"));
		objFilter.FilterListXPathSearch = "place-holder-search-Piper User";
		objFilter.SearchVlaue = "FHasan";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("FHasan"));
		objFilter.getRowValue = Test_Elements.slPiperUserRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Piper User";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		

		objTmp = new SalmonellaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Result Status Filter";
		objTmp.TestCaseNameButtonActive = "AN-SL-52: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-SL-53: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-SL-54: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-55: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-56: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-SL-57: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameRevertBack = "AN-SL-58: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "result-status";
		objFilter.FilterXPath = "Result Status";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Result Status"));
		objFilter.FilterListXPathSearch = "place-holder-search-Result Status";
		objFilter.SearchVlaue = "Pending";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Pending"));
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Result Status";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		/*	
			
		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-78: Verify user can filter multiple value from same filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter multiple value from same filter";
		objTmp.TestCaseNameSearch = "AN-SL-79: Verify the results are displayed in the table after applying multiple value from same filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying multiple value from same filter";
		objTmp.TestCaseNameClearInput = "AN-SL-80: Verify user can clear input field and reset filter after applying multiple value from same filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field and reset filter after applying multiple value from same filter by clicking on cross icon"; 
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "multiple value from same filter";
		objFilter.FilterXPath = "Lab Sample ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Lab Sample ID";
		objFilter.SearchVlaue = "0604";
		objFilter.FilterListXPathPrefix = Test_Elements.slSampleIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.slSampleIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.slSampleIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("0604sample1", "0604sample2"));
		objFilter.getRowValue = Test_Elements.slSampleIDRow;
		objFilter.rowValueExpected = "0604sample2";
		objFilter.ClearInput = "clear-input-Lab Sample ID";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
	
		
		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-81: Verify user can filter values from two filters at sametime";
		objTmp.TestCaseDescription = "This testcase will verify user filter values from two filters at sametime";
		objTmp.TestCaseNameSearch = "AN-SL-82: Verify the results are displayed in the table after applying filter values from two filters at sametime";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying filter values from two filters at sametime";
		objTmp.TestCaseNameClearInput = "AN-SL-83: Verify user can clear input fields and reset filters after applying filter values from two filters at sametime";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field after applying filter values from two filters at sametime";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Instrument ID Filter";
		objFilter.FilterXPath = "Instrument ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Instrument ID";
		objFilter.SearchVlaue = "PSN0004";
		objFilter.FilterListXPathPrefix = Test_Elements.slInstrumentIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.slInstrumentIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.slInstrumentIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("PSN0004"));
		objFilter.ClearInput = "clear-input-Instrument ID";
		objFilter.FilterXPath = "Lab Sample ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Lab Sample ID";
		objFilter.SearchVlaue = "0604sample2";
		objFilter.FilterListXPathPrefix = Test_Elements.slSampleIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.slSampleIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.slSampleIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("0604sample2"));
		objFilter.getRowValue = Test_Elements.slSampleIDRow;
		objFilter.rowValueExpected = "0604sample2";
		objFilter.ClearInput = "clear-input-Lab Sample ID";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);

*/

		return lstSalmonellaModel;
	}
	
	
	
	public static ArrayList<SalmonellaModel> FillDate() {
		ArrayList<SalmonellaModel> lstSalmonellaModel = new ArrayList<SalmonellaModel>();
		SalmonellaModel objTmp = new SalmonellaModel();
		
		objTmp.TestCaseName = "AN-SL-10: Verify user can filter Today date from date Filter";
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
		objTmp.TestCaseName = "AN-SL-11: Verify user can filter Last 24 Hours from date Filter";
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
		objTmp.TestCaseName = "AN-SL-12: Verify user can filter Last 7 Days from date Filter";
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
		objTmp.TestCaseName = "AN-SL-13: Verify user can filter Last 30 Days from date Filter";
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
		objTmp.TestCaseName = "AN-SL-14: Verify user can filter Last Month from date Filter";
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
//		objTmp.TestCaseName = "AN-SL-15: Verify user can filter This Month from date Filter";
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
		
		objTmp.TestCaseName = "AN-SL-16: Verify user cannot apply filter with invalid date";
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
		objTmp.TestCaseName = "AN-SL-17: Verify user cannot apply filter with any other date format except mm/dd/yyyy";
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
		objTmp.TestCaseName = "AN-SL-18: Verify user cannot apply filter with from date greater than to date";
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
		objTmp.TestCaseName = "AN-SL-19: Verify user can search for records with valid date";
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
		
		objTmp.TestCaseName = "AN-SL-85: Verify pagination exist on Salmonella Log report";
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
		objTmp.TestCaseName = "AN-SL-86: Verify user navigates to last page on clicking '>>' button in pagnation";
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
		objTmp.TestCaseName = "AN-SL-87: Verify user navigates to previous page on clicking '<' button in pagnation";
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
		objTmp.TestCaseName = "AN-SL-88: Verify user navigates to first page on clicking '<<' button in pagnation";
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
		objTmp.TestCaseName = "AN-SL-89: Verify user navigates to next page on clicking '>' button in pagnation";
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
		
		objTmp.TestCaseName = "AN-SL-90: Verify 100 rows are displayed when 100 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 100 rows are displayed when 100 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-SL-91: Verify 100 rows are displayed when 100 Rows per Page is selected and user moves to next page";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that 100 rows are displayed when 100 Rows per Page is selected and user moves to next page";
		objTmp.lstFilters = new ArrayList<>();
		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "100 Rows per Page";
		objFilter.FilterListXPathSearch = "100";
		objFilter.count = "100";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		
		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-92: Verify 250 rows are displayed when 250 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 250 rows are displayed when 250 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-SL-93: Verify 250 rows are displayed when 250 Rows per Page is selected and user moves to next page";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that 250 rows are displayed when 250 Rows per Page is selected and user moves to next page";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "250 Rows per Page";
		objFilter.FilterListXPathSearch = "250";
		objFilter.count = "250";
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
			
		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-94: Verify 500 rows are displayed when 500 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 100 rows are displayed when 500 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-SL-95: Verify 500 rows are displayed when 500 Rows per Page is selected and user moves to next page";
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
	

	
	}

