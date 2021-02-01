package Models;

import java.util.ArrayList;
import java.util.Arrays;

import Test.Ancera.Test_Elements;

public class CoccidiaTimelineModel {

	public String TestCaseName;
	public String TestCaseDescription;
	public String TestCaseNameSearch;
	public String TestCaseDescriptionSearch;
	public String TestCaseNameClearInput;
	public String TestCaseDescClearInput;
	public ArrayList<ReportFilters> lstFilters;
	public boolean ApplyFilter;
	public boolean ResetFilter;
	public boolean Filter1;
	public boolean Filter2;
	public boolean Filter3;

	public CoccidiaTimelineModel() {

	}

	public static ArrayList<CoccidiaTimelineModel> FillData() {
		ArrayList<CoccidiaTimelineModel> lstCoccidiaTimelineModel = new ArrayList<CoccidiaTimelineModel>();
		CoccidiaTimelineModel objTmp = new CoccidiaTimelineModel();
		
		objTmp.TestCaseName = "AN-CT-14: Verify user can filter any value from Sample ID Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Sample ID Filter";
		objTmp.TestCaseNameSearch = "AN-CT-15: Verify the results are displayed in the table after applying Sample ID Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Sample ID Filter";
		objTmp.TestCaseNameClearInput = "AN-CT-18: Verify clear input functionality of Sample ID Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Sample ID Filter by clicking on cross icon";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "Sample ID Filter";
		objFilter.FilterXPath = "filter-Sample-ID";
		objFilter.FilterListXPathSearch = "Sample-ID-place-holder-search";
		objFilter.SearchVlaue = "Coccivac";
		objFilter.FilterListXPathPrefix = Test_Elements.ctlSampleIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.ctlSampleIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.ctlSampleIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Coccivac"));
		objFilter.ClearInput = "Sample-ID-clear-input";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaTimelineModel.add(objTmp);
		
/*
		objTmp = new CoccidiaTimelineModel();
		objTmp.TestCaseName = "AN-CT-19: Verify user can filter any value from Instrument ID Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Instrument ID Filter";
		objTmp.TestCaseNameSearch = "AN-CT-20: Verify the results are displayed in the table after applying Instrument ID Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Instrument ID Filter";
		objTmp.TestCaseNameClearInput = "AN-CT-23: Verify clear input functionality of Instrument ID Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Instrument ID Filter by clicking on cross icon";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Instrument ID Filter";
		objFilter.FilterXPath = "Instrument ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Instrument ID";
		objFilter.SearchVlaue = "PSN0002";
		objFilter.FilterListXPathPrefix = Test_Elements.ctlInstrumentIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.ctlInstrumentIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.ctlInstrumentIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("PSN0002"));
		objFilter.ClearInput = "clear-input-Instrument ID";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaTimelineModel.add(objTmp);
		

		objTmp = new CoccidiaTimelineModel();
		objTmp.TestCaseName = "AN-CT-24: Verify user can filter any value from Catridge ID Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Catridge ID Filter";
		objTmp.TestCaseNameSearch = "AN-CT-25: Verify the results are displayed in the table after applying Catridge ID Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Catridge ID Filter";
		objTmp.TestCaseNameClearInput = "AN-CT-28: Verify clear input functionality of Catridge ID Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Catridge ID Filter by clicking on cross icon";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Catridge ID Filter";
		objFilter.FilterXPath = "Catridge ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Cartridge ID";
		objFilter.SearchVlaue = "03-31-2020_PSN13_21613_cocc";  
		objFilter.FilterListXPathPrefix = Test_Elements.ctlCatridgeIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.ctlCatridgeIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.ctlCatridgeIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("03-31-2020_PSN13_21613_cocc"));
		objFilter.ClearInput = "clear-input-Cartridge ID";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaTimelineModel.add(objTmp);
		

		objTmp = new CoccidiaTimelineModel();
		objTmp.TestCaseName = "AN-CT-29: Verify user can filter any value from Lane Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Lane Filter";
		objTmp.TestCaseNameSearch = "AN-CT-30: Verify the results are displayed in the table after applying Lane Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Lane Filter";
		objTmp.TestCaseNameClearInput = "AN-CT-33: Verify clear input functionality of Lane Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Lane Filter by clicking on cross icon";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Lane Filter";
		objFilter.FilterXPath = Test_Elements.ctlLaneID;
		objFilter.FilterListXPathSearch = "place-holder-search-Lane";
		objFilter.SearchVlaue = "10";
		objFilter.FilterListXPathPrefix = Test_Elements.ctlLanebeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.ctlLaneafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.ctlLaneafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("10"));
		objFilter.ClearInput = "clear-input-Lane";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaTimelineModel.add(objTmp);
	

		objTmp = new CoccidiaTimelineModel();
		objTmp.TestCaseName = "AN-CT-34: Verify user can filter any value from Pathogen Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Pathogen Filter";
		objTmp.TestCaseNameSearch = "AN-CT-35: Verify the results are displayed in the table after applying Pathogen Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Pathogen Filter";
		objTmp.TestCaseNameClearInput = "AN-CT-38: Verify clear input functionality of Pathogen Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Pathogen Filter by clicking on cross icon";
		objTmp.ApplyFilter = true;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Pathogen Filter";
		objFilter.FilterXPath = "Pathogen";
		objFilter.FilterListXPathSearch = "place-holder-search-Pathogen";
		objFilter.SearchVlaue = "Coccidia";
		objFilter.FilterListXPathPrefix = Test_Elements.ctlPathogenbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.ctlPathogenafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.ctlPathogenafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Coccidia"));
		objFilter.ClearInput = "place-holder-search-Pathogen";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaTimelineModel.add(objTmp);
		               

		
		objTmp = new CoccidiaTimelineModel();
		objTmp.TestCaseName = "AN-CT-39: Verify user can filter any value from Improc Version Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Improc Version Filter";
		objTmp.TestCaseNameSearch = "AN-CT-40: Verify the results are displayed in the table after applying Improc Version Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Improc Version Filter";
		objTmp.TestCaseNameClearInput = "AN-CT-43: Verify clear input functionality of Improc Version Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Improc Version Filter by clicking on cross icon";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Improc Version Filter";
		objFilter.FilterXPath = "Improc Version";
		objFilter.FilterListXPathSearch = "place-holder-search-Improc Version";
		objFilter.SearchVlaue = "2.0";
		objFilter.FilterListXPathPrefix = Test_Elements.ctlImprocbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.ctlImprocafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.ctlImprocafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2.0"));
		objFilter.ClearInput = "clear-input-Improc Version";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaTimelineModel.add(objTmp); 		
		
		
		objTmp = new CoccidiaTimelineModel();
		objTmp.TestCaseName = "AN-CT-44: Verify user can filter multiple value from same filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter multiple value from same filter";
		objTmp.TestCaseNameSearch = "AN-CT-45: Verify the results are displayed in the table after applying multiple value from same filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying multiple value from same filter";
		objTmp.TestCaseNameClearInput = "AN-CT-48: Verify clear input functionality after applying multiple value from same filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field after applying multiple value from same filter by clicking on cross icon"; 
		objTmp.ApplyFilter = true;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "multiple value from same filter";
		objFilter.FilterXPath = "Sample ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Sample ID";
		objFilter.SearchVlaue = "TD";
		objFilter.FilterListXPathPrefix = Test_Elements.ctlSampleIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.ctlSampleIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.ctlSampleIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("TD05", "TD06"));
		objFilter.ClearInput = "clear-input-Sample ID";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaTimelineModel.add(objTmp);
		
	*/
		
		objTmp = new CoccidiaTimelineModel();
		objTmp.TestCaseName = "AN-CT-49: Verify user can filter values from two filters at sametime";
		objTmp.TestCaseDescription = "This testcase will verify user filter values from two filters at sametime";
		objTmp.TestCaseNameSearch = "AN-CT-50: Verify the results are displayed in the table after applying filter values from two filters at sametime";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying filter values from two filters at sametime";
		objTmp.TestCaseNameClearInput = "AN-CT-53: Verify clear input functionality after applying filter values from two filters at sametimer";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field after applying filter values from two filters at sametime";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Instrument ID Filter";
		objFilter.FilterXPath = "filter-Instrument-ID";
		objFilter.FilterListXPathSearch = "Instrument-ID-place-holder-search";
		objFilter.SearchVlaue = "PSN0004";
		objFilter.FilterListXPathPrefix = Test_Elements.ctlInstrumentIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.ctlInstrumentIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.ctlInstrumentIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("PSN0004"));
		objFilter.ClearInput = "Instrument-ID-clear-input";
		objFilter.FilterXPath = "filter-Sample-ID";
		objFilter.FilterListXPathSearch = "Sample-ID-place-holder-search";
		objFilter.SearchVlaue = "Coccivac";
		objFilter.FilterListXPathPrefix = Test_Elements.ctlSampleIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.ctlSampleIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.ctlSampleIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Coccivac"));
		objFilter.ClearInput = "Sample-ID-clear-input";
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


		objTmp = new CoccidiaTimelineModel();
		objTmp.TestCaseName = "AN-CT-07: Verify user can filter This Month from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of This Month after selecting 'This Month'";
		objTmp.Filter1 = false;
		objTmp.Filter2 = false;
		objTmp.Filter3 = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterListXPathSearch = Test_Elements.ctlThisMonth;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "0";
		objFilter.fromDate = "1";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaTimelineModel.add(objTmp);

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

