package Models;

import java.util.ArrayList;
import java.util.Arrays;

import Test.Ancera.Test_Elements;

public class ExternalSalmonellaModel {

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
	public boolean paginationExist;
	public boolean paginationLastPage;
	public boolean paginationNextPage;
	public boolean paginationFirstPage;
	public boolean paginationPreviousPage;
	public ExternalSalmonellaModel() {

	}
	
	
	public static ArrayList<ExternalSalmonellaModel> FillData() {
		ArrayList<ExternalSalmonellaModel> lstExternalSalmonellaModel = new ArrayList<ExternalSalmonellaModel>();
		ExternalSalmonellaModel objTmp = new ExternalSalmonellaModel();
		
		objTmp.TestCaseName = "AN-ESL-17: Verify user can filter 'Medium' from Load Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter 'Medium' from Load Filter";
		objTmp.TestCaseNameSearch = "AN-ESL-18: Verify the results are displayed in the table after applying 'Medium' from Load Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying 'Medium' from Load Filter";
		objTmp.TestCaseNameClearInput = "AN-ESL-19: Verify user can clear input field and reset Load Filter on selecting 'Medium'";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Load Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "Load Filter";
		objFilter.FilterXPath = "Load";
		objFilter.FilterListXPathSearch = "place-holder-search-Load";
		objFilter.SearchVlaue = "Medium";
		objFilter.FilterListXPathPrefix = Test_Elements.eslLoadbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eslLoadafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eslLoadafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Medium"));
		objFilter.getRowValue = Test_Elements.eslLoadRow;
		objFilter.rowValueExpected = Test_Elements.eslLoadYellow;
		objFilter.ClearInput = "clear-input-Load";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);

	
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-20: Verify user can filter 'High' from Load Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter 'High' from Load Filter";
		objTmp.TestCaseNameSearch = "AN-ESL-21: Verify the results are displayed in the table after applying 'High' from Load Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying 'High' from Load Filter";
		objTmp.TestCaseNameClearInput = "AN-ESL-22: Verify user can clear input field and reset Load Filter on selecting 'High'";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Load Filter by clicking on cross icon and reset the filter";         
    	objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Load Filter";
		objFilter.FilterXPath = "Load";
		objFilter.FilterListXPathSearch = "place-holder-search-Load";
		objFilter.SearchVlaue = "High";
		objFilter.FilterListXPathPrefix = Test_Elements.eslLoadbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eslLoadafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eslLoadafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("High"));
		objFilter.getRowValue = Test_Elements.eslLoadRow;
		objFilter.rowValueExpected = Test_Elements.eslLoadRed;
		objFilter.ClearInput = "clear-input-Load";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
		
		
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-23: Verify user can filter 'Low' from Load Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter 'Low' from Load Filter";
		objTmp.TestCaseNameSearch = "AN-ESL-24: Verify the results are displayed in the table after applying Load Filter with 'Low'";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Load Filter with 'Low'";
		objTmp.TestCaseNameClearInput = "AN-ESL-25: Verify user can clear input field and reset Load Filter on selecting 'Low'";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Load Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Load Filter";
		objFilter.FilterXPath = "Load";
		objFilter.FilterListXPathSearch = "place-holder-search-Load";
		objFilter.SearchVlaue = "Low";
		objFilter.FilterListXPathPrefix = Test_Elements.eslLoadbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eslLoadafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eslLoadafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Low"));
		objFilter.getRowValue = Test_Elements.eslLoadRow;
		objFilter.rowValueExpected = Test_Elements.eslLoadGreen;  
		objFilter.ClearInput = "clear-input-Load";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);

		
		
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-26: Verify user can filter any value from Lab Sample ID Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Lab Sample ID Filter";
		objTmp.TestCaseNameSearch = "AN-ESL-27: Verify the results are displayed in the table after applying Lab Sample ID Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Lab Sample ID Filter";
		objTmp.TestCaseNameClearInput = "AN-ESL-28: Verify user can clear input field and reset Lab Sample ID Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Lab Sample ID Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Sample ID Filter";
		objFilter.FilterXPath = "Lab Sample ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Lab Sample ID";
		objFilter.SearchVlaue = "0604sample1";
		objFilter.FilterListXPathPrefix = Test_Elements.eslSampleIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eslSampleIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eslSampleIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("0604sample1"));
		objFilter.getRowValue = Test_Elements.eslSampleIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "clear-input-Lab Sample ID";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
	

		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-29: Verify user can filter any value from Instrument ID Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Instrument ID Filter";
		objTmp.TestCaseNameSearch = "AN-ESL-30: Verify the results are displayed in the table after applying Instrument ID Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Instrument ID Filter";
		objTmp.TestCaseNameClearInput = "AN-ESL-31: Verify user can clear input field and reset Instrument ID Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Instrument ID Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Instrument ID Filter";
		objFilter.FilterXPath = "Instrument ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Instrument ID";
		objFilter.SearchVlaue = "PSN0002";
		objFilter.FilterListXPathPrefix = Test_Elements.eslInstrumentIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eslInstrumentIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eslInstrumentIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("PSN0002"));
		objFilter.getRowValue = Test_Elements.eslInstrumentIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "clear-input-Instrument ID";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
		

		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-32: Verify user can filter any value from Catridge ID Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Catridge ID Filter";
		objTmp.TestCaseNameSearch = "AN-ESL-33: Verify the results are displayed in the table after applying Catridge ID Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Catridge ID Filter";
		objTmp.TestCaseNameClearInput = "AN-ESL-34: Verify user can clear input field and reset Catridge ID Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Catridge ID Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = true;
		objTmp.ResetFilter = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Cartridge ID Filter";
		objFilter.FilterXPath = "Cartridge ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Cartridge ID";
		objFilter.SearchVlaue = "20201023_swtest1";   
		objFilter.FilterListXPathPrefix = Test_Elements.eslCatridgeIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eslCatridgeIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eslCatridgeIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("20201023_swtest1"));  //uat
		objFilter.getRowValue = Test_Elements.eslCatridgeIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "clear-input-Cartridge ID";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
		
		
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-35: Verify user can filter any value from Lane Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Lane Filter";
		objTmp.TestCaseNameSearch = "AN-ESL-36: Verify the results are displayed in the table after applying Lane Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Lane Filter";
		objTmp.TestCaseNameClearInput = "AN-SL-37: Verify user can clear input field and reset Lane Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Lane Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Lane Filter";
		objFilter.FilterXPath = "Lane";
		objFilter.FilterListXPathSearch = "place-holder-search-Lane";
		objFilter.SearchVlaue = "12";
		objFilter.FilterListXPathPrefix = Test_Elements.eslLanebeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eslLaneafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eslLaneafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("12"));
		objFilter.getRowValue = Test_Elements.eslLaneIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "clear-input-Lane";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
		

		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-38: Verify user can filter any value from Assay Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Assay Filter";
		objTmp.TestCaseNameSearch = "AN-ESL-39: Verify the results are displayed in the table after applying Assay Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Assay Filter";
		objTmp.TestCaseNameClearInput = "AN-ESL-40: Verify user can clear input field and reset Assay Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Assay Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Assay Filter";
		objFilter.FilterXPath = "Assay";
		objFilter.FilterListXPathSearch = "place-holder-search-Assay";
		objFilter.SearchVlaue = "listeria-probes";   
		objFilter.FilterListXPathPrefix = Test_Elements.eslAssaybeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eslAssayafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eslAssayafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("listeria-probes")); 
		objFilter.getRowValue = Test_Elements.eslAssayRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "clear-input-Assay";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
			
		
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-41: Verify user can filter any value from QC Code Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from QC Code Filter";
		objTmp.TestCaseNameSearch = "AN-ESL-42: Verify the results are displayed in the table after applying QC Code Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying QC Code Filter";
		objTmp.TestCaseNameClearInput = "AN-ESL-43: Verify user can clear input field and reset QC Code Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of QC Code Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = true;
		objTmp.ResetFilter = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "QC Code Filter";
		objFilter.FilterXPath = "QC Code";
		objFilter.FilterListXPathSearch = "place-holder-search-QC Code";
		objFilter.SearchVlaue = "0.000";
		objFilter.FilterListXPathPrefix = Test_Elements.eslQCCodebeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eslQCCodeafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eslQCCodeafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("0.000"));
		objFilter.getRowValue = Test_Elements.eslQCCodeRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-QC Code";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);                   

		
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-44: Verify user can filter any value from Improc Version Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Improc Version Filter";
		objTmp.TestCaseNameSearch = "AN-ESL-45: Verify the results are displayed in the table after applying Improc Version Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Improc Version Filter";
		objTmp.TestCaseNameClearInput = "AN-ESL-46: Verify user can clear input field and reset Improc Version Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Improc Version Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Improc Version Filter";
		objFilter.FilterXPath = "Improc Version";
		objFilter.FilterListXPathSearch = "place-holder-search-Improc Version";
		objFilter.SearchVlaue = "0.0";
		objFilter.FilterListXPathPrefix = Test_Elements.eslImprocbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eslImprocafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eslImprocafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("0.0"));
		objFilter.getRowValue = Test_Elements.eslImprocIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Improc Version";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp); 
		
		
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-47: Verify user can filter any value from Collection Site Name Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Collection Site Name Filter";
		objTmp.TestCaseNameSearch = "AN-ESL-48: Verify the results are displayed in the table after applying Collection Site Name Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Collection Site Name Filter";
		objTmp.TestCaseNameClearInput = "AN-ESL-49: Verify user can clear input field and reset Collection Site Name Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Collection Site Name Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection Site Name Filter";
		objFilter.FilterXPath = "Collection Site Name";
		objFilter.FilterListXPathSearch = "place-holder-search-Collection Site Name";
		objFilter.SearchVlaue = "Peco Gordo";
		objFilter.FilterListXPathPrefix = Test_Elements.eslSiteNamebeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eslSiteNameafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eslSiteNameafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Peco Gordo"));
		objFilter.getRowValue = Test_Elements.eslSiteNameRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Collection Site Name";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp); 	

		
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-50: Verify user can filter any value from Collection Site ID Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Collection Site ID Filter";
		objTmp.TestCaseNameSearch = "AN-ESL-51: Verify the results are displayed in the table after applying Collection Site ID Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Collection Site ID Filter";
		objTmp.TestCaseNameClearInput = "AN-ESL-52: Verify user can clear input field and reset Collection Site ID Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Collection Site ID Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection Site ID Filter";
		objFilter.FilterXPath = "Collection Site ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Collection Site ID";
		objFilter.SearchVlaue = "1001002";
		objFilter.FilterListXPathPrefix = Test_Elements.eslSiteIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eslSiteIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eslSiteIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1001002"));
		objFilter.getRowValue = Test_Elements.eslSiteIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Collection Site ID";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp); 
			
		
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-53: Verify user can filter any value from Collection Site Type Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Collection Site Type Filter";
		objTmp.TestCaseNameSearch = "AN-ESL-54: Verify the results are displayed in the table after applying Collection Site Type Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Collection Site Type Filter";
		objTmp.TestCaseNameClearInput = "AN-ESL-55: Verify user can clear input field and reset Collection Site Type Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Collection Site Type Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = true;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection Site Type Filter";
		objFilter.FilterXPath = "Collection Site Type";
		objFilter.FilterListXPathSearch = "place-holder-search-Collection Site Type";
		objFilter.SearchVlaue = "Farm";
		objFilter.FilterListXPathPrefix = Test_Elements.eslSiteTypebeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eslSiteTypeafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eslSiteTypeafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Farm"));
		objFilter.getRowValue = Test_Elements.eslSiteTypeRow;
		objFilter.rowValueExpected = "";
		objFilter.ClearInput = "clear-input-Collection Site Type";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp); 
		
		
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-56: Verify user can filter any value from Sample Matrix Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Sample Matrix Filter";
		objTmp.TestCaseNameSearch = "AN-ESL-57: Verify the results are displayed in the table after applying Sample Matrix Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Sample Matrix Filter";
		objTmp.TestCaseNameClearInput = "AN-ESL-58: Verify user can clear input field and reset Sample Matrix Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Sample Matrix Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = true;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Sample Matrix Filter";
		objFilter.FilterXPath = "Sample Matrix";
		objFilter.FilterListXPathSearch = "place-holder-search-Sample Matrix";
		objFilter.SearchVlaue = "Rinse";
		objFilter.FilterListXPathPrefix = Test_Elements.eslSampleMatrixbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eslSampleMatrixafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eslSampleMatrixafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Rinse"));
		objFilter.getRowValue = Test_Elements.eslSampleMatrixRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Sample Matrix";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp); 
		
		
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-59: Verify user can filter any value from Customer Sample ID Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Customer Sample ID Filter";
		objTmp.TestCaseNameSearch = "AN-ESL-60: Verify the results are displayed in the table after applying Customer Sample ID Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Customer Sample ID Filter";
		objTmp.TestCaseNameClearInput = "AN-ESL-61: Verify user can clear input field and reset Customer Sample ID Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Customer Sample ID Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Customer Sample ID Filter";
		objFilter.FilterXPath = "Customer Sample Id";
		objFilter.FilterListXPathSearch = "place-holder-search-Customer Sample Id";
		objFilter.SearchVlaue = "BAL-1234";
		objFilter.FilterListXPathPrefix = Test_Elements.eslCSampleIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eslCSampleIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eslCSampleIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("BAL-1234"));
		objFilter.getRowValue = Test_Elements.eslCSampleIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Customer Sample Id";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
		
		
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-62: Verify user can filter any value from Date Received Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Date Received Filter";
		objTmp.TestCaseNameSearch = "AN-ESL-63: Verify the results are displayed in the table after applying Date Received Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Date Received Filter";
		objTmp.TestCaseNameClearInput = "AN-ESL-64: Verify user can clear input field and reset Date Received Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Date Received Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Received Filter";
		objFilter.FilterXPath = "Date Received";
		objFilter.FilterListXPathSearch = "place-holder-search-Date Received";
		objFilter.SearchVlaue = "06-29-2020";
		objFilter.FilterListXPathPrefix = Test_Elements.eslDateReceivedbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eslDateReceivedafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eslDateReceivedafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("06-29-2020"));
		objFilter.getRowValue = Test_Elements.eslDateReceivedRow;
		objFilter.rowValueExpected = "";
		objFilter.ClearInput = "clear-input-Date Received";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
		
		
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-65: Verify user can filter any value from Kit Lot Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Kit Lot Filter";
		objTmp.TestCaseNameSearch = "AN-ESL-66: Verify the results are displayed in the table after applying Kit Lot Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Kit Lot Filter";
		objTmp.TestCaseNameClearInput = "AN-ESL-67: Verify user can clear input field and reset Kit Lot Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Kit Lot Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = true;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Kit Lot Filter";
		objFilter.FilterXPath = "Kit Lot";
		objFilter.FilterListXPathSearch = "place-holder-search-Kit Lot";
		objFilter.SearchVlaue = "SL9087";
		objFilter.FilterListXPathPrefix = Test_Elements.eslKitLotbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eslKitLotafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eslKitLotafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("SL9087"));
		objFilter.getRowValue = Test_Elements.eslKitLotRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Kit Lot";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
		

		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-68: Verify user can filter any value from Piper User Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Piper User Filter";
		objTmp.TestCaseNameSearch = "AN-ESL-69: Verify the results are displayed in the table after applying Piper User Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Piper User Filter";
		objTmp.TestCaseNameClearInput = "AN-ESL-70: Verify user can clear input field and reset  Piper User Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Piper User Filter by clicking on cross icon and reset the filter";     
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Piper User Filter";
		objFilter.FilterXPath = "Piper User";
		objFilter.FilterListXPathSearch = "place-holder-search-Piper User";
		objFilter.SearchVlaue = "FHasan";
		objFilter.FilterListXPathPrefix = Test_Elements.eslPiperUserbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eslPiperUserafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eslPiperUserafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("FHasan"));
		objFilter.getRowValue = Test_Elements.eslPiperUserRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Piper User";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);

		
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-71: Verify user can filter any value from Requested Assay Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Requested Assay Filter";
		objTmp.TestCaseNameSearch = "AN-ESL-72: Verify the results are displayed in the table after applying Requested Assay Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Requested Assay Filter";
		objTmp.TestCaseNameClearInput = "AN-ESL-73: Verify user can clear input field and reset Requested Assay Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Requested Assay Filter by clicking on cross icon and reset the filter"; 
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Requested Assay Filter";
		objFilter.FilterXPath = "Requested Assay";
		objFilter.FilterListXPathSearch = "place-holder-search-Requested Assay";
		objFilter.SearchVlaue = "Salmonella Detection";
		objFilter.FilterListXPathPrefix = Test_Elements.eslRequestedAssaybeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eslRequestedAssayafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eslRequestedAssayafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Salmonella Detection"));
		objFilter.getRowValue = Test_Elements.eslRequestedAssayRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Requested Assay";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);

		
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-74: Verify user can filter any value from Flock ID Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Flock ID Filter";
		objTmp.TestCaseNameSearch = "AN-ESL-75: Verify the results are displayed in the table after applying Flock ID Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Flock ID Filter";
		objTmp.TestCaseNameClearInput = "AN-ESL-76: Verify user can clear input field and reset Flock ID Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Flock ID Filter by clicking on cross icon and reset the filter"; 
		objTmp.ApplyFilter = true;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Flock ID Filter";
		objFilter.FilterXPath = "Flock ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Flock ID";
		objFilter.SearchVlaue = "76h";
		objFilter.FilterListXPathPrefix = Test_Elements.eslFlockIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eslFlockIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eslFlockIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("76h"));
		objFilter.getRowValue = Test_Elements.eslFlockIDRow;
		objFilter.rowValueExpected = "";
		objFilter.ClearInput = "clear-input-Flock ID";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
		
	
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-77: Verify user can filter any value from Result Status Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Result Status Filter";
		objTmp.TestCaseNameSearch = "AN-ESL-78: Verify the results are displayed in the table after applying Result Status Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Result Status Filter";
		objTmp.TestCaseNameClearInput = "AN-ESL-79: Verify user can clear input field and reset  Result Status Filter";
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
		lstExternalSalmonellaModel.add(objTmp);
		
		
		
		
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-80: Verify user can filter multiple value from same filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter multiple value from same filter";
		objTmp.TestCaseNameSearch = "AN-ESL-81: Verify the results are displayed in the table after applying multiple value from same filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying multiple value from same filter";
		objTmp.TestCaseNameClearInput = "AN-ESL-82: Verify clear input functionality after applying multiple value from same filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field after applying multiple value from same filter by clicking on cross icon";
		objTmp.ApplyFilter = true;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "multiple value from same filter (Sample ID)";
		objFilter.FilterXPath = "Lab Sample ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Lab Sample ID";
		objFilter.SearchVlaue = "0604";
		objFilter.FilterListXPathPrefix = Test_Elements.eslSampleIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eslSampleIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eslSampleIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("0604sample1", "0604sample2"));
		objFilter.getRowValue = Test_Elements.eslSampleIDRow;
		objFilter.rowValueExpected = "0604sample2";
		objFilter.ClearInput = "clear-input-Lab Sample ID";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
		
		
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-83: Search for values from more than 1 filter";
		objTmp.TestCaseDescription = "This testcase will test multiple filters at same time";
		objTmp.TestCaseNameSearch = "AN-ESL-84: Verify the results are displayed in the table after applying two filters at same time";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying 2 filters at same time";
		objTmp.TestCaseNameClearInput = "AN-ESL-85: Verify user can clear input fields and reset filters after applying filter values from two filters at sametime";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field after applying filter values from two filters at sametime";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Instrument ID Filter";
		objFilter.FilterXPath = "Instrument ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Instrument ID";
		objFilter.SearchVlaue = "PSN0002";
		objFilter.FilterListXPathPrefix = Test_Elements.eslInstrumentIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eslInstrumentIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eslInstrumentIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("PSN0002"));
		objFilter.ClearInput = "clear-input-Instrument ID";
		objFilter.FilterXPath = "Lab Sample ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Lab Sample ID";
		objFilter.SearchVlaue = "0604sample2";
		objFilter.FilterListXPathPrefix = Test_Elements.eslSampleIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eslSampleIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eslSampleIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("0604sample2"));
		objFilter.getRowValue = Test_Elements.eslSampleIDRow;
		objFilter.rowValueExpected = "0604sample2";
		objFilter.ClearInput = "clear-input-Lab Sample ID";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
		
		return lstExternalSalmonellaModel;
	}
	
	
	
	public static ArrayList<ExternalSalmonellaModel> FillDate() {
		ArrayList<ExternalSalmonellaModel> lstExternalSalmonellaModel = new ArrayList<ExternalSalmonellaModel>();
		ExternalSalmonellaModel objTmp = new ExternalSalmonellaModel();
		
		objTmp.TestCaseName = "AN-ESL-06: Verify user can filter Today date from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of date filter after selecting 'Today'";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.Filter3 = false;
		objTmp.lstFilters = new ArrayList<>();
		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterXPath = Test_Elements.eslDateOpen;
		objFilter.FilterListXPathSearch = Test_Elements.eslToday;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "0";
		objFilter.fromDate = "0";
		objFilter.FilterListXPathPrefix = Test_Elements.eslDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.eslDateTo;
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
		
	
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-07: Verify user can filter Last 24 Hours from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of Last 24 Hours after selecting 'Last 24 Hours'";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.Filter3 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterXPath = Test_Elements.eslDateOpen;
		objFilter.FilterListXPathSearch = Test_Elements.eslLast24Hours;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "-1";
		objFilter.fromDate = "-1";
		objFilter.FilterListXPathPrefix = Test_Elements.eslDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.eslDateTo;
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
		
				
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-08: Verify user can filter Last 7 Days from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of Last 7 Days after selecting 'Last 7 Days'";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.Filter3 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterXPath = Test_Elements.eslDateOpen;
		objFilter.FilterListXPathSearch = Test_Elements.eslLast7Days;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "0";
		objFilter.fromDate = "-7";
		objFilter.FilterListXPathPrefix = Test_Elements.eslDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.eslDateTo;
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
		
				
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-09: Verify user can filter Last 30 Days from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of Last 30 Days after selecting 'Last 30 Days'";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.Filter3 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterXPath = Test_Elements.eslDateOpen;
		objFilter.FilterListXPathSearch = Test_Elements.eslLast30Days;
		objFilter.toMonth = "0";
		objFilter.fromMonth = "0";
		objFilter.toDate = "0";
		objFilter.fromDate = "-30";
		objFilter.FilterListXPathPrefix = Test_Elements.eslDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.eslDateTo;
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
		
		
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-10: Verify user can filter Last Month from date Filter";
		objTmp.TestCaseDescription = "This testcase will verify the functionality of Last Month after selecting 'Last Month'";
		objTmp.Filter1 = false;
		objTmp.Filter2 = true;
		objTmp.Filter3 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Filter";
		objFilter.FilterXPath = Test_Elements.eslDateOpen;
		objFilter.FilterListXPathSearch = Test_Elements.eslLastMonth;
		objFilter.toMonth = "-1";
		objFilter.fromMonth = "-1";
		objFilter.toDate = "30";
		objFilter.fromDate = "1";
		objFilter.FilterListXPathPrefix = Test_Elements.eslDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.eslDateTo;
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
		
		
//		objTmp = new ExternalSalmonellaModel();
//		objTmp.TestCaseName = "AN-ESL-11: Verify user can filter This Month from date Filter";
//		objTmp.TestCaseDescription = "This testcase will verify the functionality of This Month after selecting 'This Month'";
//		objTmp.Filter1 = false;
//		objTmp.Filter2 = false;
//		objTmp.Filter3 = true;
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter = new ReportFilters();
//		objFilter.FilterName = "Date Filter";
//		objFilter.FilterXPath = Test_Elements.eslDateOpen;
//		objFilter.FilterListXPathSearch = Test_Elements.eslThisMonth;
//		objFilter.toMonth = "0";
//		objFilter.fromMonth = "0";
//		objFilter.toDate = "0";
//		objFilter.fromDate = "1";
//		objFilter.FilterListXPathPrefix = Test_Elements.eslDateFrom;
//		objFilter.FilterListXPathSuffix = Test_Elements.eslDateTo;
//		objTmp.lstFilters.add(objFilter);
//		lstExternalSalmonellaModel.add(objTmp);
			
		return lstExternalSalmonellaModel;
	}
	
	
	
	
	public static ArrayList<ExternalSalmonellaModel> EnterDate() {
		ArrayList<ExternalSalmonellaModel> lstExternalSalmonellaModel = new ArrayList<ExternalSalmonellaModel>();
		ExternalSalmonellaModel objTmp = new ExternalSalmonellaModel();
		
		objTmp.TestCaseName = "AN-ESL-12: Verify user cannot apply filter with invalid date";
		objTmp.TestCaseDescription = "This testcase will verify that user cannot add date with invalid from and to date";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.lstFilters = new ArrayList<>();
		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "Enter invalid date";
		objFilter.FilterListXPathPrefix = Test_Elements.eslDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.eslDateTo;
		objFilter.fromDate = "12/01/2020";
		objFilter.toDate = "11/11/202020";
		objFilter.alertMessage = "Please select a valid date";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
		
		
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-13: Verify user cannot apply filter with any other date format except mm/dd/yyyy";
		objTmp.TestCaseDescription = "This testcase will verify that user cannot apply filter with any other date format except mm/dd/yyyy";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Enter any other date format except mm/dd/yyyy";
		objFilter.FilterListXPathPrefix = Test_Elements.eslDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.eslDateTo;
		objFilter.fromDate = "12/01/2020";
		objFilter.toDate = "20/12/2020";
		objFilter.alertMessage = "Please select a valid date";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);

		
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-14: Verify user cannot apply filter with from date greater than to date";
		objTmp.TestCaseDescription = "This testcase will verify that user cannot apply filter with from date greater than to date";
		objTmp.Filter1 = true;
		objTmp.Filter2 = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Enter from date greater than to date";
		objFilter.FilterListXPathPrefix = Test_Elements.eslDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.eslDateTo;
		objFilter.fromDate = "12/01/2020";
		objFilter.toDate = "10/01/2020";
		objFilter.alertMessage = "Please select a valid date";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
		
		
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-15: Verify user can search for records with valid date";
		objTmp.TestCaseDescription = "This testcase will verify that user can search for records with valid date";
		objTmp.Filter1 = false;
		objTmp.Filter2 = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Enter valid date";
		objFilter.FilterListXPathPrefix = Test_Elements.eslDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.eslDateTo;
		objFilter.fromDate = "12/01/2020";
		objFilter.toDate = "12/07/2020";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
		
		return lstExternalSalmonellaModel;
		
	}
	
	
	public static ArrayList<ExternalSalmonellaModel> pagination() {
		ArrayList<ExternalSalmonellaModel> lstExternalSalmonellaModel = new ArrayList<ExternalSalmonellaModel>();
		ExternalSalmonellaModel objTmp = new ExternalSalmonellaModel();
		
		objTmp.TestCaseName = "AN-ESL-87: Verify pagination exist on Salmonella Log report";
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
		objFilter.paginationCount = Test_Elements.eslTotalPages;
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
		
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-88: Verify user navigates to last page on clicking '>>' button in pagnation";
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
		objFilter.paginationCount = Test_Elements.eslTotalPages;
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
		
		
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-89: Verify user navigates to previous page on clicking '<' button in pagnation";
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
		objFilter.paginationCount = Test_Elements.eslTotalPages;
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
	
	
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-90: Verify user navigates to first page on clicking '<<' button in pagnation";
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
		objFilter.paginationCount = Test_Elements.eslTotalPages;
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
		
		
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-91: Verify user navigates to next page on clicking '>' button in pagnation";
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
		objFilter.paginationCount = Test_Elements.eslTotalPages;
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
		
		return lstExternalSalmonellaModel;
		
}
	

	
	public static ArrayList<ExternalSalmonellaModel> searchRows() {
		ArrayList<ExternalSalmonellaModel> lstExternalSalmonellaModel = new ArrayList<ExternalSalmonellaModel>();
		ExternalSalmonellaModel objTmp = new ExternalSalmonellaModel();
		
		objTmp.TestCaseName = "AN-ESL-92: Verify 100 rows are displayed when 100 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 100 rows are displayed when 100 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-ESL-93: Verify 100 rows are displayed when 100 Rows per Page is selected and user moves to next page";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that 100 rows are displayed when 100 Rows per Page is selected and user moves to next page";
		objTmp.lstFilters = new ArrayList<>();
		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "100 Rows per Page";
		objFilter.FilterXPath = Test_Elements.eslRowsDropdown;
		objFilter.FilterListXPathSearch = "100";
		objFilter.count = "100";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
		
		
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-94: Verify 250 rows are displayed when 250 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 250 rows are displayed when 250 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-ESL-95: Verify 250 rows are displayed when 250 Rows per Page is selected and user moves to next page";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that 250 rows are displayed when 250 Rows per Page is selected and user moves to next page";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "250 Rows per Page";
		objFilter.FilterXPath = Test_Elements.eslRowsDropdown;
		objFilter.FilterListXPathSearch = "250";
		objFilter.count = "250";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
		
			
		objTmp = new ExternalSalmonellaModel();
		objTmp.TestCaseName = "AN-ESL-95: Verify 500 rows are displayed when 500 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 100 rows are displayed when 500 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-ESL-96: Verify 500 rows are displayed when 500 Rows per Page is selected and user moves to next page";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that 500 rows are displayed when 500 Rows per Page is selected and user moves to next page";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "500 Rows per Page";
		objFilter.FilterXPath = Test_Elements.eslRowsDropdown;
		objFilter.FilterListXPathSearch = "500";
		objFilter.count = "500";
		objTmp.lstFilters.add(objFilter);
		lstExternalSalmonellaModel.add(objTmp);
	
		return lstExternalSalmonellaModel;	
}
	
	
}
