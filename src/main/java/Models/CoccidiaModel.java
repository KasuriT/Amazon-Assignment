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
	public boolean sortLogic1;
	public boolean sortLogic2;

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
		objTmp.TestCaseNameHoverReset = "AN-CL-23: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-CL-24: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "sample-id";
		objFilter.FilterXPath = "filter-Sample-Id";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Sample-Id"));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("TD01"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1"));
		objFilter.checkboxNumber = "1";
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
		objTmp.TestCaseNameHoverReset = "AN-CL-31: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-CL-32: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "instrument-id";
		objFilter.FilterXPath = "filter-Instrument-Id";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Instrument-Id"));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("PSN0004"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1"));
		objFilter.checkboxNumber = "1";
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
		objTmp.TestCaseNameHoverReset = "AN-CL-39: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-CL-40: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "cartrtidge-id";
		objFilter.FilterXPath = "filter-Cartridge-Id";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Cartridge-Id"));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("TestAutomation"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1"));
		objFilter.checkboxNumber = "1";
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
		objTmp.TestCaseNameHoverReset = "AN-CL-47: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-CL-48: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "lane-num";
		objFilter.FilterXPath = "filter-Lane";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Lane"));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("10"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1"));
		objFilter.checkboxNumber = "1";
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
		objTmp.TestCaseNameHoverReset = "AN-CL-55: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-CL-56: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "pathogen";
		objFilter.FilterXPath = "filter-Assay";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Assay"));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("Coccidia-SYBR"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1"));
		objFilter.checkboxNumber = "1";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);               


		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Improc Version Filter";
		objTmp.TestCaseNameButtonActive = "AN-CL-57: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-CL-58: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-CL-59 "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-CL-60: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-CL-61: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-CL-62: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CL-63: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-CL-64: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "version";
		objFilter.FilterXPath = "filter-Improc-Version";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Improc-Version"));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("1"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1"));
		objFilter.checkboxNumber = "1";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp); 


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
		objTmp.TestCaseNameHoverReset = "AN-CL-71: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-CL-72: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "site-id";
		objFilter.FilterXPath = "filter-Site-Name";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Site-Name"));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("Test"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1"));
		objFilter.checkboxNumber = "1";
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
		objTmp.TestCaseNameHoverReset = "AN-CL-79: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-CL-80: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "sample-matrix";
		objFilter.FilterXPath = "filter-Sample-Matrix";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Sample-Matrix"));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("Feces"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1"));
		objFilter.checkboxNumber = "1";
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
		objTmp.TestCaseNameHoverReset = "AN-CL-87: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-CL-88: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "customer-sample-id";
		objFilter.FilterXPath =  "filter-Customer-Sample-ID";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Customer-Sample-ID"));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("Blank"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1"));
		objFilter.checkboxNumber = "1";
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
		objTmp.TestCaseNameHoverReset = "AN-CL-95: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-CL-96: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "meta-data-date-received";
		objFilter.FilterXPath = "filter-Date-Received";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Date-Received"));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("Blank"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1"));
		objFilter.checkboxNumber = "1";
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
		objTmp.TestCaseNameHoverReset = "AN-CL-103: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-CL-104: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "Kit-Lot-group-body";
		objFilter.FilterXPath =  "filter-Kit-Lot";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Kit-Lot"));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("Bl"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1"));
		objFilter.checkboxNumber = "1";
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
		objTmp.TestCaseNameHoverReset = "AN-CL-111: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-CL-112: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "user-name";
		objFilter.FilterXPath = "filter-Piper-User";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Piper-User"));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("FHasan"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1"));
		objFilter.checkboxNumber = "1";
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
		objTmp.TestCaseNameHoverReset = "AN-CL-119: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-CL-120: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "result-status";
		objFilter.FilterXPath = "filter-Result-Status";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Result-Status"));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("Blank"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1"));
		objFilter.checkboxNumber = "1";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);


		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Test Site ID Filter";
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
		objTmp.TestCaseNameHoverReset = "AN-CL-127: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-CL-128: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "test-site-id";
		objFilter.FilterXPath = "filter-test-site-id";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("test-site-id"));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("Blank"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1"));
		objFilter.checkboxNumber = "1";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		
		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Test Site Name Filter";
		objTmp.TestCaseNameButtonActive = "AN-CL-129: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-CL-130: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-CL-131: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-CL-132: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-CL-133: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-CL-134: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CL-135: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-CL-136: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "test-site-name";
		objFilter.FilterXPath = "filter-test-site-name";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("test-site-name"));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("Blank"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1"));
		objFilter.checkboxNumber = "1";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);

		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "multiple value from same filter";
		objTmp.TestCaseNameButtonActive = "AN-CL-137: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseName = "AN-CL-138: "+applyFilterTitle+objFilter.FilterName;
		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-CL-139: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-CL-140: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-CL-141: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-CL-142: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CL-143: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.TestCaseNameRevertBack = "AN-CL-144: Verify "+objFilter.FilterName+revertBackTitle;
		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "sample-id";
		objFilter.FilterXPath = "filter-Sample-Id";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Sample-Id", "Sample-Id"));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("SMP"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1", "2"));
		objFilter.checkboxNumber = "1";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		
//		objTmp = new CoccidiaModel();
//		objFilter = new ReportFilters();
//		objFilter.FilterName = "Two Filters";
//		objTmp.TestCaseNameButtonActive = "AN-CL-129: "+buttonActiveTitle+objFilter.FilterName;
//		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
//		objTmp.TestCaseName = "AN-CL-130: "+applyFilterTitle+objFilter.FilterName;
//		objTmp.TestCaseDescription = applyFilterDesc+objFilter.FilterName;
//		objTmp.TestCaseNameSearch = "AN-CL-131: "+filterIndicatorTitle+objFilter.FilterName;
//		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
//		objTmp.TestCaseNameBubbleFilterTop = "AN-CL-132: Verify "+objFilter.FilterName+filterTopTitle;
//		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
//		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-CL-133: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
//		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
//		objTmp.TestCaseNameClearInput = "AN-CL-134: "+clearInputTitle+objFilter.FilterName;
//		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
//		objTmp.TestCaseNameHoverReset = "AN-CL-135: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
//		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
//		objTmp.TestCaseNameRevertBack = "AN-CL-136: Verify "+objFilter.FilterName+revertBackTitle;
//		objTmp.TestCaseDescriptionRevertBack = "This testcase will verify that "+objFilter.FilterName+revertBackDesc;
//		objTmp.ReloadPage = false;
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter = new ReportFilters();
//		objFilter.FilterID = "instrument-id";
//		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Sample-Id", "Instrument-Id"));
//		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("SMP10", "PSN0004"));
//		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("SMP10", "PSN0004")); 
//		objFilter.FilterID = "sample-id";
//		objFilter.FilterXPath = "filter-Sample-Id";
//		objTmp.lstFilters.add(objFilter);
//		lstCoccidiaModel.add(objTmp);

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
		objFilter.FilterListXPathSearch = Test_Elements.clToday;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "0";
		objFilter.fromDate = "0";
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
		objFilter.FilterListXPathSearch = Test_Elements.clLast24Hours;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "-1";
		objFilter.fromDate = "-1";
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
		objFilter.FilterListXPathSearch = Test_Elements.clLast7Days;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "0";
		objFilter.fromDate = "-7";
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
		objFilter.FilterListXPathSearch = Test_Elements.clLast30Days;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "0";
		objFilter.fromDate = "-30";
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
		objFilter.FilterListXPathSearch = Test_Elements.clLastMonth;
		objFilter.toMonth = "-1";
		objFilter.fromMonth = "-1";
		objFilter.toDate = "30";
		objFilter.fromDate = "1";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);

//		objTmp = new CoccidiaModel();
//		objTmp.TestCaseName = "AN-CL-10: Verify user can filter This Month from date Filter";
//		objTmp.TestCaseDescription = "This testcase will verify the functionality of This Month after selecting 'This Month'";
//		objTmp.Filter1 = false;
//		objTmp.Filter2 = false;
//		objTmp.Filter3 = true;
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter = new ReportFilters();
//		objFilter.FilterName = "Date Filter";
//		objFilter.FilterListXPathSearch = Test_Elements.clThisMonth;
//		objFilter.toMonth = "0";
//		objFilter.fromMonth = "0";
//		objFilter.toDate = "0";
//		objFilter.fromDate = "1";
//		objTmp.lstFilters.add(objFilter);
//		lstCoccidiaModel.add(objTmp);

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
		objFilter.fromDate = "12/01/2021";
		objFilter.toDate = "20/01/2021";
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
		objFilter.FilterListXPathSearch = "rows-100";
		objFilter.count = "100";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);

		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-145: Verify 250 rows are displayed when 250 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 250 rows are displayed when 250 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-CL-146: Verify 250 rows are displayed when 250 Rows per Page is selected and user moves to next page";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that 250 rows are displayed when 250 Rows per Page is selected and user moves to next page";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "250 Rows per Page";
		objFilter.FilterListXPathSearch = "rows-250";
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
		objFilter.FilterListXPathSearch = "rows-500";
		objFilter.count = "500";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);

		return lstCoccidiaModel;
	}
	
	
	public static ArrayList<CoccidiaModel> sorting() {
		ArrayList<CoccidiaModel> lstCoccidiaModel = new ArrayList<CoccidiaModel>();
		CoccidiaModel objTmp = new CoccidiaModel();

		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "Lane";
		objTmp.TestCaseName = "AN-CL-218: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-laneNum";
		objFilter.count = "1";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);

		objTmp = new CoccidiaModel();
	    objFilter = new ReportFilters();
		objFilter.FilterName = "Lab Sample ID";
		objTmp.TestCaseName = "AN-CL-219: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-sampleId";
		objFilter.count = "2";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);

		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "QC Code";
		objTmp.TestCaseName = "AN-CL-220: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-countOutcome";
		objFilter.count = "3";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);

		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Result Status";
		objTmp.TestCaseName = "AN-CL-221: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-result_status";
		objFilter.count = "4";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Total OPG";
		objTmp.TestCaseName = "AN-CL-222: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-totalOPG";
		objFilter.count = "5";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Small OPG";
		objTmp.TestCaseName = "AN-CL-223: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-totalSmallOPG";
		objFilter.count = "6";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		
		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Medium OPG";
		objTmp.TestCaseName = "AN-CL-224: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-totalMediumOPG";
		objFilter.count = "7";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Large OPG";
		objTmp.TestCaseName = "AN-CL-225: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-totalLargeOPG";
		objFilter.count = "8";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Result";
		objTmp.TestCaseName = "AN-CL-222: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-outcome";
		objFilter.count = "9";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);

		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Assay";
		objTmp.TestCaseName = "AN-CL-223: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-pathogen";
		objFilter.count = "10";                   
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);

		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Result ID";
		objTmp.TestCaseName = "AN-CL-224: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-runId";
		objFilter.count = "11";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);

		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Result Date";
		objTmp.TestCaseName = "AN-CL-225: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = false;
		objFilter.ColumnID = "sort-scanDateTime";
		objFilter.count = "12";
		objTmp.lstFilters.add(objFilter);       
		lstCoccidiaModel.add(objTmp);

		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site Name";
		objTmp.TestCaseName = "AN-CL-226: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-site_id";           
		objFilter.count = "13";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);

		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Sample Matrix";
		objTmp.TestCaseName = "AN-CL-229: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-sample_matrix";           
		objFilter.count = "14";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);

		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Customer Sample ID";
		objTmp.TestCaseName = "AN-CL-231: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-customer_sample_id";           
		objFilter.count = "15";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);

		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Received";
		objTmp.TestCaseName = "AN-CL-232: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-metadata_date_recieved";           
		objFilter.count = "16";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);

		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Cartridge ID";
		objTmp.TestCaseName = "AN-CL-233: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-cartridgeId";           
		objFilter.count = "17";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);

		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Instrument ID";
		objTmp.TestCaseName = "AN-CL-234: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-instrumentId";           
		objFilter.count = "18";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);

		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Total Count";
		objTmp.TestCaseName = "AN-CL-236: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-oocystTotalCount";           
		objFilter.count = "19";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);

		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Small Count";
		objTmp.TestCaseName = "AN-CL-237: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-oocystSmallCount";           
		objFilter.count = "20";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);

		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Medium Count";
		objTmp.TestCaseName = "AN-CL-238: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-oocystMediumCount";           
		objFilter.count = "21";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);

		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Large Count";
		objTmp.TestCaseName = "AN-CL-239: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-oocystLargeCount";           
		objFilter.count = "22";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);

		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Mean Intensity";
		objTmp.TestCaseName = "AN-CL-240: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-oocystMeanIntensity";           
		objFilter.count = "23";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);

		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Piper User";
		objTmp.TestCaseName = "AN-CL-241: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-user_name";           
		objFilter.count = "24";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);

		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Kit Lot";
		objTmp.TestCaseName = "AN-CL-242: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-kit_lot";           
		objFilter.count = "25";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);

		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Improc Version";
		objTmp.TestCaseName = "AN-CL-243: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-version";           
		objFilter.count = "26";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);

		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Test Site ID";
		objTmp.TestCaseName = "AN-CL-244: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-testSiteId";
		objFilter.count = "27";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		objTmp = new CoccidiaModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Test Site Name";
		objTmp.TestCaseName = "AN-CL-245: Verify values are sorted on clicking on "+objFilter.FilterName+" column header";
		objTmp.TestCaseDescription = "This testcase will verify that values are sorted on clicking "+objFilter.FilterName+" column header";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.sortLogic1 = true;
		objFilter.ColumnID = "sort-testSiteName";
		objFilter.count = "28";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		return lstCoccidiaModel;	
	}	
	
	
	
	
}


