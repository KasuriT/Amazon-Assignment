package Models;

import java.util.ArrayList;
import java.util.Arrays;
import Test.Ancera.Test_Elements;

public class SalmonellaModel {

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
	public ArrayList<ReportFilters> lstFilters;
	public boolean ApplyFilter;
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

	public static ArrayList<SalmonellaModel> FillData() {
		ArrayList<SalmonellaModel> lstSalmonellaModel = new ArrayList<SalmonellaModel>();
		SalmonellaModel objTmp = new SalmonellaModel();
		
		objTmp.TestCaseName = "AN-SL-21: Verify user can filter 'Medium' from Load Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter 'Medium' from Load Filter";
		objTmp.TestCaseNameSearch = "AN-SL-22: Verify the results are displayed in the table after applying 'Medium' from Load Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying 'Medium' from Load Filter";
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-23: Verify filter bubbles to top of filter list on applying filter";
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that filter bubbles to top of filter list on applying filter";
		objTmp.TestCaseNameBubbleFilterCheckbox = "AN-SL-24: Verify filter bubbles to top of filter list on applying filter";
		objTmp.TestCaseDescriptionBubbleFilterCheckbox = "This testcase will verify that filter bubbles to top of filter list on applying filter";
		objTmp.TestCaseNameClearInput = "AN-SL-25: Verify user can clear input field and reset Load Filter on selecting 'Medium'";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Load Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "Load Filter";
		objFilter.FilterXPath = "Load";
		objFilter.FilterListXPathSearch = "place-holder-search-Load";
		objFilter.SearchVlaue = "Medium";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Medium"));
		objFilter.getRowValue = Test_Elements.slLoadRow;
		objFilter.rowValueExpected = Test_Elements.slLoadYellow;
		objFilter.ClearInput = "clear-input-Load";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
	/*
		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-26: Verify user can filter 'High' from Load Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter 'High' from Load Filter";
		objTmp.TestCaseNameSearch = "AN-SL-27: Verify the results are displayed in the table after applying 'High' from Load Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying 'High' from Load Filter";
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-28: Verify filter bubbles to top of filter list on applying filter";
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that filter bubbles to top of filter list on applying filter";
		objTmp.TestCaseNameClearInput = "AN-SL-30: Verify user can clear input field and reset Load Filter on selecting 'High'";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Load Filter by clicking on cross icon and reset the filter";
    	objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Load Filter";
		objFilter.FilterXPath = "Load";
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
		objTmp.TestCaseName = "AN-SL-31: Verify user can filter 'Low' from Load Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter 'Low' from Load Filter";
		objTmp.TestCaseNameSearch = "AN-SL-28: Verify the results are displayed in the table after applying Load Filter with 'Low'";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Load Filter with 'Low'";
		objTmp.TestCaseNameBubbleFilterTop = "AN-SL-23: Verify filter bubbles to top of filter list on applying filter";
		objTmp.TestCaseDescriptionBubbleFilterTop = "This testcase will verify that filter bubbles to top of filter list on applying filter";
		objTmp.TestCaseNameClearInput = "AN-SL-29: Verify user can clear input field and reset Load Filter on selecting 'Low'";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Load Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Load Filter";
		objFilter.FilterXPath = "Load";
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
		objTmp.TestCaseName = "AN-SL-30: Verify user can filter any value from Lab Sample ID Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Lab Sample ID Filter";
		objTmp.TestCaseNameSearch = "AN-SL-31: Verify the results are displayed in the table after applying Lab Sample ID Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Lab Sample ID Filter";
		objTmp.TestCaseNameClearInput = "AN-SL-32: Verify user can clear input field and reset Lab Sample ID Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Lab Sample ID Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Lab Sample ID Filter";
		objFilter.FilterXPath = "Lab Sample ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Lab Sample ID";
		objFilter.SearchVlaue = "0604sample1";
		objFilter.FilterListXPathPrefix = Test_Elements.slSampleIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.slSampleIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.slSampleIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("0604sample1"));
		objFilter.getRowValue = Test_Elements.slSampleIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "clear-input-Lab Sample ID";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		

		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-33: Verify user can filter any value from Instrument ID Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Instrument ID Filter";
		objTmp.TestCaseNameSearch = "AN-SL-34: Verify the results are displayed in the table after applying Instrument ID Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Instrument ID Filter";
		objTmp.TestCaseNameClearInput = "AN-SL-35: Verify user can clear input field and reset Instrument ID Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Instrument ID Filter by clicking on cross icon and reset the filter";
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
		objFilter.getRowValue = Test_Elements.slInstrumentIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "clear-input-Instrument ID";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		

		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-36: Verify user can filter any value from Catridge ID Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Catridge ID Filter";
		objTmp.TestCaseNameSearch = "AN-SL-37: Verify the results are displayed in the table after applying Catridge ID Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Catridge ID Filter";
		objTmp.TestCaseNameClearInput = "AN-SL-38: Verify user can clear input field and reset Catridge ID Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Catridge ID Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Cartridge ID Filter";
		objFilter.FilterXPath = "Cartridge ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Cartridge ID";
		objFilter.SearchVlaue = "20201023_swtest1";  
		objFilter.FilterListXPathPrefix = Test_Elements.slCatridgeIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.slCatridgeIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.slCatridgeIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("20201023_swtest1"));  //uat
		objFilter.getRowValue = Test_Elements.slCatridgeIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "clear-input-Cartridge ID";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		

		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-39: Verify user can filter any value from Lane Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Lane Filter";
		objTmp.TestCaseNameSearch = "AN-SL-40: Verify the results are displayed in the table after applying Lane Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Lane Filter";
		objTmp.TestCaseNameClearInput = "AN-SL-41: Verify user can clear input field and reset Lane Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Lane Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Lane Filter";
		objFilter.FilterXPath = "Lane";
		objFilter.FilterListXPathSearch = "place-holder-search-Lane";
		objFilter.SearchVlaue = "10";
		objFilter.FilterListXPathPrefix = Test_Elements.slLanebeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.slLaneafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.slLaneafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("10"));
		objFilter.getRowValue = Test_Elements.slLaneIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "clear-input-Lane";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
	
		
		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-42: Verify user can filter any value from Assay Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Assay Filter";
		objTmp.TestCaseNameSearch = "AN-SL-43: Verify the results are displayed in the table after applying Assay Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Assay Filter";
		objTmp.TestCaseNameClearInput = "AN-SL-44: Verify user can clear input field and reset Assay Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Assay Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = true;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Assay Filter";
		objFilter.FilterXPath = "Assay";
		objFilter.FilterListXPathSearch = "place-holder-search-Assay";
		objFilter.SearchVlaue = "listeria-probes";   
		objFilter.FilterListXPathPrefix = Test_Elements.slAssaybeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.slAssayafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.slAssayafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("listeria-probes"));  //uat
		objFilter.getRowValue = Test_Elements.slAssayRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "clear-input-Assay";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		
		
		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-45: Verify user can filter any value from QC Code Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from QC Code Filter";
		objTmp.TestCaseNameSearch = "AN-SL-46: Verify the results are displayed in the table after applying QC Code Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying QC Code Filter";
		objTmp.TestCaseNameClearInput = "AN-SL-47: Verify user can clear input field and reset QC Code Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of QC Code Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "QC Code Filter";
		objFilter.FilterXPath = "QC Code";
		objFilter.FilterListXPathSearch = "place-holder-search-QC Code";
		objFilter.SearchVlaue = "0.000";
		objFilter.FilterListXPathPrefix = Test_Elements.slQCCodebeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.slQCCodeafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.slQCCodeafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("0.000"));
		objFilter.getRowValue = Test_Elements.slQCCodeRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-QC Code";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);                   

		
		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-48: Verify user can filter any value from Improc Version Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Improc Version Filter";
		objTmp.TestCaseNameSearch = "AN-SL-49: Verify the results are displayed in the table after applying Improc Version Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Improc Version Filter";
		objTmp.TestCaseNameClearInput = "AN-SL-50: Verify user can clear input field and reset Improc Version Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Improc Version Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Improc Version Filter";
		objFilter.FilterXPath = "Improc Version";
		objFilter.FilterListXPathSearch = "place-holder-search-Improc Version";
		objFilter.SearchVlaue = "0.0";
		objFilter.FilterListXPathPrefix = Test_Elements.slImprocbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.slImprocafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.slImprocafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("0.0"));
		objFilter.getRowValue = Test_Elements.slImprocIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Improc Version";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp); 
		
	
		
		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-51: Verify user can filter any value from Collection Site Name Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Collection Site Name Filter";
		objTmp.TestCaseNameSearch = "AN-SL-52: Verify the results are displayed in the table after applying Collection Site Name Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Collection Site Name Filter";
		objTmp.TestCaseNameClearInput = "AN-SL-53: Verify user can clear input field and reset Collection Site Name Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Collection Site Name Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection Site Name Filter";
		objFilter.FilterXPath = "Collection Site Name";
		objFilter.FilterListXPathSearch = "place-holder-search-Collection Site Name";
		objFilter.SearchVlaue = "Peco Gordo";
		objFilter.FilterListXPathPrefix = Test_Elements.slSiteNamebeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.slSiteNameafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.slSiteNameafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Peco Gordo"));
		objFilter.getRowValue = Test_Elements.slSiteNameRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Collection Site Name";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp); 
		

		
		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-54: Verify user can filter any value from Collection Site ID Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Collection Site ID Filter";
		objTmp.TestCaseNameSearch = "AN-SL-55: Verify the results are displayed in the table after applying Collection Site ID Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Collection Site ID Filter";
		objTmp.TestCaseNameClearInput = "AN-SL-56: Verify user can clear input field and reset Collection Site ID Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Collection Site ID Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection Site ID Filter";
		objFilter.FilterXPath = "Collection Site ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Collection Site ID";
		objFilter.SearchVlaue = "1001001";
		objFilter.FilterListXPathPrefix = Test_Elements.slSiteIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.slSiteIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.slSiteIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1001001"));
		objFilter.getRowValue = Test_Elements.slSiteIDRow;
		objFilter.rowValueExpected = "";
		objFilter.ClearInput = "clear-input-Collection Site ID";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp); 
		
		
		
		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-57: Verify user can filter any value from Collection Site Type Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Collection Site Type Filter";
		objTmp.TestCaseNameSearch = "AN-SL-58: Verify the results are displayed in the table after applying Collection Site Type Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Collection Site Type Filter";
		objTmp.TestCaseNameClearInput = "AN-SL-59: Verify user can clear input field and reset Collection Site Type Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Collection Site Type Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection Site Type Filter";
		objFilter.FilterXPath = "Collection Site Type";  
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
		objTmp.TestCaseName = "AN-SL-60: Verify user can filter any value from Sample Matrix Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Sample Matrix Filter";
		objTmp.TestCaseNameSearch = "AN-SL-61: Verify the results are displayed in the table after applying Sample Matrix Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Sample Matrix Filter";
		objTmp.TestCaseNameClearInput = "AN-SL-62: Verify user can clear input field and reset Sample Matrix Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Sample Matrix Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Sample Matrix Filter";
		objFilter.FilterXPath = "Sample Matrix";
		objFilter.FilterListXPathSearch = "place-holder-search-Sample Matrix";
		objFilter.SearchVlaue = "Feces";
		objFilter.FilterListXPathPrefix = Test_Elements.slSampleMatrixbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.slSampleMatrixafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.slSampleMatrixafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Feces"));
		objFilter.getRowValue = Test_Elements.slSampleMatrixRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Sample Matrix";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp); 
		
		
		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-63: Verify user can filter any value from Customer Sample ID Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Customer Sample ID Filter";
		objTmp.TestCaseNameSearch = "AN-SL-64: Verify the results are displayed in the table after applying Customer Sample ID Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Customer Sample ID Filter";
		objTmp.TestCaseNameClearInput = "AN-SL-65: Verify user can clear input field and reset Customer Sample ID Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Customer Sample ID Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = true;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Customer Sample ID Filter";
		objFilter.FilterXPath =  "Customer Sample Id";
		objFilter.FilterListXPathSearch = "place-holder-search-Customer Sample Id";
		objFilter.SearchVlaue = "BAL-1234";
		objFilter.FilterListXPathPrefix = Test_Elements.slCSampleIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.slCSampleIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.slCSampleIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("BAL-1234"));
		objFilter.getRowValue = Test_Elements.slCSampleIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Customer Sample Id";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
	

		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-66: Verify user can filter any value from Date Received Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Date Received Filter";
		objTmp.TestCaseNameSearch = "AN-SL-67: Verify the results are displayed in the table after applying Date Received Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Date Received Filter";
		objTmp.TestCaseNameClearInput = "AN-SL-68: Verify user can clear input field and reset Date Received Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Date Received Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = true;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Received Filter";
		objFilter.FilterXPath = "Date Received";
		objFilter.FilterListXPathSearch = "place-holder-search-Date Received";
		objFilter.SearchVlaue = "06-29-2020";
		objFilter.FilterListXPathPrefix = Test_Elements.slDateReceivedbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.slDateReceivedafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.slDateReceivedafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("06-29-2020"));
		objFilter.getRowValue = Test_Elements.slDateReceivedRow;
		objFilter.rowValueExpected = "";
		objFilter.ClearInput = "clear-input-Date Received";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		
		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-69: Verify user can filter any value from Kit Lot Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Kit Lot Filter";
		objTmp.TestCaseNameSearch = "AN-SL-70: Verify the results are displayed in the table after applying Kit Lot Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Kit Lot Filter";
		objTmp.TestCaseNameClearInput = "AN-SL-71: Verify user can clear input field and reset Kit Lot Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Kit Lot Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Kit Lot Filter";
		objFilter.FilterXPath =  "Kit Lot";
		objFilter.FilterListXPathSearch = "place-holder-search-Kit Lot";
		objFilter.SearchVlaue = "SAL456773";
		objFilter.FilterListXPathPrefix = Test_Elements.slKitLotbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.slKitLotafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.slKitLotafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("SAL456773"));
		objFilter.getRowValue = Test_Elements.slKitLotRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Kit Lot";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
		
		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-72: Verify user can filter any value from Piper User Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Piper User Filter";
		objTmp.TestCaseNameSearch = "AN-SL-73: Verify the results are displayed in the table after applying Piper User Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Piper User Filter";
		objTmp.TestCaseNameClearInput = "AN-SL-74: Verify user can clear input field and reset  Piper User Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Piper User Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Piper User Filter";
		objFilter.FilterXPath = "Piper User";
		objFilter.FilterListXPathSearch = "place-holder-search-Piper User";
		objFilter.SearchVlaue = "FHasan";
		objFilter.FilterListXPathPrefix = Test_Elements.slPiperUserbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.slPiperUserafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.slPiperUserafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("FHasan"));
		objFilter.getRowValue = Test_Elements.slPiperUserRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Piper User";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		

		objTmp = new SalmonellaModel();
		objTmp.TestCaseName = "AN-SL-75: Verify user can filter any value from Result Status Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Result Status Filter";
		objTmp.TestCaseNameSearch = "AN-SL-76: Verify the results are displayed in the table after applying Result Status Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Result Status Filter";
		objTmp.TestCaseNameClearInput = "AN-SL-77: Verify user can clear input field and reset  Result Status Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Result Status Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Piper User Filter";
		objFilter.FilterXPath = "Result Status";
		objFilter.FilterListXPathSearch = "place-holder-search-Result Status";
		objFilter.SearchVlaue = "Pending";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Pending"));
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Result Status";
		objFilter.wait = 3000;
		objTmp.lstFilters.add(objFilter);
		lstSalmonellaModel.add(objTmp);
		
			
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


