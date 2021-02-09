package Models;

import java.util.ArrayList;
import java.util.Arrays;

import Test.Ancera.Test_Elements;

public class CoccidiaTimelineModel {

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
	public boolean ApplyFilter;
	public boolean ResetFilter;
	public boolean ReloadPage;
	public boolean Filter1;
	public boolean Filter2;
	public boolean Filter3;

	public CoccidiaTimelineModel() {

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

	public static ArrayList<CoccidiaTimelineModel> FillData() {
		ArrayList<CoccidiaTimelineModel> lstCoccidiaTimelineModel = new ArrayList<CoccidiaTimelineModel>();
		CoccidiaTimelineModel objTmp = new CoccidiaTimelineModel();
		
		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "Sample ID Filter";
		objTmp.TestCaseNameButtonActive = "AN-CT-13: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-CT-14: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-CT-15: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-CT-16: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-CT-17: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CT-18: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "sample-id";
		objFilter.FilterXPath = "filter-Sample-ID";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Sample-ID"));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("TD01"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("TD01"));
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaTimelineModel.add(objTmp);
		
		
		
		objTmp = new CoccidiaTimelineModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Instrument ID Filter";
		objTmp.TestCaseNameButtonActive = "AN-CT-19: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-CT-20: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-CT-21: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-CT-22: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-CT-23: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CT-24: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "isntrument-id";
		objFilter.FilterXPath = "filter-Instrument-ID";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Instrument-ID"));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("PSN0004"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("PSN0004"));
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaTimelineModel.add(objTmp);


		objTmp = new CoccidiaTimelineModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Cartridge ID Filter";
		objTmp.TestCaseNameButtonActive = "AN-CT-25: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-CT-26: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-CT-27: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-CT-28: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-CT-29: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CT-30: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.ReloadPage = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "cartrtidge-id";
		objFilter.FilterXPath = "filter-Cartridge-ID";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Cartridge-ID"));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("TestAutomation"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("TestAutomation")); 
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaTimelineModel.add(objTmp);


		objTmp = new CoccidiaTimelineModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Lane Filter";
		objTmp.TestCaseNameButtonActive = "AN-CT-31: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-CT-32: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-CT-33: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-CT-34: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-CT-35: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CT-36: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "lane-num";
		objFilter.FilterXPath = "filter-Lane-No";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Lane-No"));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("10"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("10")); 
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaTimelineModel.add(objTmp);

		
		objTmp = new CoccidiaTimelineModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Pathogen Filter";
		objTmp.TestCaseNameButtonActive = "AN-CT-37: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-CT-38: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-CT-39: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-CT-40: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-CT-41: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CT-42: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "pathogen";
		objFilter.FilterXPath = "filter-Pathogen";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Pathogen"));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("Coccidia"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Coccidia"));
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaTimelineModel.add(objTmp);               

		
		objTmp = new CoccidiaTimelineModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Improc Version Filter";
		objTmp.TestCaseNameButtonActive = "AN-CT-43: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-CT-44"+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-CT-45: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-CT-46: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-CT-47: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CT-48: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "version";
		objFilter.FilterXPath = "filter-Improc-Version";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Improc-Version"));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("1"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1"));
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaTimelineModel.add(objTmp); 
		

		objTmp = new CoccidiaTimelineModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "multiple value from same filter";
		objTmp.TestCaseNameButtonActive = "AN-CT-49: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-CT-50: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-CT-51: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-CT-52: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-CT-53: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CT-54: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterID = "sample-id";
		objFilter.FilterXPath = "filter-Sample-ID";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Sample-ID", "Sample-ID"));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("SMP"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("SMP01", "SMP02"));
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaTimelineModel.add(objTmp);


		objTmp = new CoccidiaTimelineModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Two Filters";
		objTmp.TestCaseNameButtonActive = "AN-CT-55: "+buttonActiveTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionButtonActive = buttonActiveDesc+objFilter.FilterName;
		objTmp.TestCaseNameSearch = "AN-CT-56: "+filterIndicatorTitle+objFilter.FilterName;
		objTmp.TestCaseDescriptionSearch = filterIndicatorDesc+objFilter.FilterName;
		objTmp.TestCaseNameBubbleFilterTop = "AN-CT-57: Verify "+objFilter.FilterName+filterTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that "+objFilter.FilterName+filterTopDesc;
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-CT-58: Verify selected checkbox from "+objFilter.FilterName+CheckboxTopTitle;
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that "+objFilter.FilterName+CheckboxTopDesc;
		objTmp.TestCaseNameClearInput = "AN-CT-59: "+clearInputTitle+objFilter.FilterName;
		objTmp.TestCaseDescClearInput = clearInputDesc+objFilter.FilterName;
		objTmp.TestCaseNameHoverReset = "AN-CT-60: Verify clicking on the blue indicator next to "+objFilter.FilterName+" resets it";
		objTmp.TestCaseDescriptionHoverReset = "This testcase will verify that hovering and clicking on the blue indicator next to applied filter resets it";
		objTmp.ReloadPage = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = "isntrument-id";
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("Sample-ID", "Instrument-ID"));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("SMP01", "PSN0013"));
		objFilter.LstFilterSearch = new ArrayList<>(Arrays.asList("SMP01", "PSN0013")); 
		objFilter.FilterID = "sample-id";
		objFilter.FilterXPath = "filter-Sample-ID";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaTimelineModel.add(objTmp);
	
		return lstCoccidiaTimelineModel;
	}
	
	
	
	public static ArrayList<CoccidiaTimelineModel> FillDate() {
		ArrayList<CoccidiaTimelineModel> lstCoccidiaTimelineModel = new ArrayList<CoccidiaTimelineModel>();
		CoccidiaTimelineModel objTmp = new CoccidiaTimelineModel();

		objTmp.TestCaseName = "AN-CT-02: Verify user can filter Today date from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of date filter after selecting 'Today'";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.Filter3 = false;
		objTmp.lstFilters = new ArrayList<>();
		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
	//	objFilter.FilterXPath = Test_Elements.eclDateOpen;
		objFilter.FilterListXPathSearch = Test_Elements.ctlToday;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "0";
		objFilter.fromDate = "0";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaTimelineModel.add(objTmp);


		objTmp = new CoccidiaTimelineModel();
		objTmp.TestCaseName = "AN-CT-03: Verify user can filter Last 24 Hours from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of Last 24 Hours after selecting 'Last 24 Hours'";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.Filter3 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterListXPathSearch = Test_Elements.ctlLast24Hours;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "-1";
		objFilter.fromDate = "-1";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaTimelineModel.add(objTmp);



		objTmp = new CoccidiaTimelineModel();
		objTmp.TestCaseName = "AN-CT-04: Verify user can filter Last 7 Days from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of Last 7 Days after selecting 'Last 7 Days'";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.Filter3 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterListXPathSearch = Test_Elements.ctlLast7Days;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "0";
		objFilter.fromDate = "-7";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaTimelineModel.add(objTmp);



		objTmp = new CoccidiaTimelineModel();
		objTmp.TestCaseName = "AN-CT-05: Verify user can filter Last 30 Days from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of Last 30 Days after selecting 'Last 30 Days'";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.Filter3 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterListXPathSearch = Test_Elements.ctlLast30Days;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "0";
		objFilter.fromDate = "-30";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaTimelineModel.add(objTmp);


		objTmp = new CoccidiaTimelineModel();
		objTmp.TestCaseName = "AN-CT-06: Verify user can filter Last Month from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of Last Month after selecting 'Last Month'";
		objTmp.Filter1 = false;
		objTmp.Filter2 = true;
		objTmp.Filter3 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterListXPathSearch = Test_Elements.ctlLastMonth;
		objFilter.toMonth = "-1";
		objFilter.fromMonth = "-1";
		objFilter.toDate = "30";
		objFilter.fromDate = "1";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaTimelineModel.add(objTmp);


//		objTmp = new CoccidiaTimelineModel();
//		objTmp.TestCaseName = "AN-CT-07: Verify user can filter This Month from date Filter";
//		objTmp.TestCaseDescription = "This testcase will verify the functionality of This Month after selecting 'This Month'";
//		objTmp.Filter1 = false;
//		objTmp.Filter2 = false;
//		objTmp.Filter3 = true;
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter = new ReportFilters();
//		objFilter.FilterName = "Date Filter";
//		objFilter.FilterListXPathSearch = Test_Elements.ctlThisMonth;
//		objFilter.toMonth = "0";
//		objFilter.fromMonth = "0";
//		objFilter.toDate = "0";
//		objFilter.fromDate = "1";
//		objTmp.lstFilters.add(objFilter);
//		lstCoccidiaTimelineModel.add(objTmp);

		return lstCoccidiaTimelineModel;
	}


	public static ArrayList<CoccidiaTimelineModel> EnterDate() {
		ArrayList<CoccidiaTimelineModel> lstCoccidiaTimelineModel = new ArrayList<CoccidiaTimelineModel>();
		CoccidiaTimelineModel objTmp = new CoccidiaTimelineModel();

		objTmp.TestCaseName = "AN-CT-08: Verify user cannot apply filter with invalid date";
		objTmp.TestCaseDescription = "This testcase will verify that user cannot add date with invalid from and to date";
		objTmp.Filter1 = true;
		objTmp.lstFilters = new ArrayList<>();
		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "Enter invalid date";
		objFilter.fromDate = "12/01/2020";
		objFilter.toDate = "11/11/202020";
		objFilter.alertMessage = "Please select a valid date";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaTimelineModel.add(objTmp);

		objTmp = new CoccidiaTimelineModel();
		objTmp.TestCaseName = "AN-CT-09: Verify user cannot apply filter with any other date format except mm/dd/yyyy";
		objTmp.TestCaseDescription = "This testcase will verify that user cannot apply filter with any other date format except mm/dd/yyyy";
		objTmp.Filter1 = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Enter any other date format except mm/dd/yyyy";
		objFilter.fromDate = "12/01/2020";
		objFilter.toDate = "20/12/2020";
		objFilter.alertMessage = "Please select a valid date";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaTimelineModel.add(objTmp);

		objTmp = new CoccidiaTimelineModel();
		objTmp.TestCaseName = "AN-CT-10: Verify user cannot apply filter with from date greater than to date";
		objTmp.TestCaseDescription = "This testcase will verify that user cannot apply filter with from date greater than to date";
		objTmp.Filter1 = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Enter from date greater than to date";
		objFilter.fromDate = "12/01/2020";
		objFilter.toDate = "10/01/2020";
		objFilter.alertMessage = "Please select a valid date";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaTimelineModel.add(objTmp);

		return lstCoccidiaTimelineModel;
	}
}

