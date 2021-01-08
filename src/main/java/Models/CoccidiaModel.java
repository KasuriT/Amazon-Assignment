package Models;
import java.util.ArrayList;
import java.util.Arrays;

import Test.Ancera.Test_Elements;

public class CoccidiaModel {

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

	public CoccidiaModel() {

	}

	public static ArrayList<CoccidiaModel> FillData() {
		ArrayList<CoccidiaModel> lstCoccidiaModel = new ArrayList<CoccidiaModel>();
		CoccidiaModel objTmp = new CoccidiaModel();
		
		objTmp.TestCaseName = "AN-CL-18: Verify user can filter any value from Sample ID Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Sample ID Filter";
		objTmp.TestCaseNameSearch = "AN-CL-19: Verify the results are displayed in the table after applying Sample ID Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Sample ID Filter";
		objTmp.TestCaseNameClearInput = "AN-CL-20: Verify user can clear input field and reset Lab Sample ID Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Lab Sample ID Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "Sample ID Filter";
		objFilter.FilterXPath = "Sample ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Sample ID";
		objFilter.SearchVlaue = "2019_SFC_2";
		objFilter.FilterListXPathPrefix = Test_Elements.clSampleIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.clSampleIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.clSampleIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2019_SFC_2"));
		objFilter.getRowValue = Test_Elements.clSampleIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "clear-input-Sample ID";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		

		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-21: Verify user can filter any value from Instrument ID Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Instrument ID Filter";
		objTmp.TestCaseNameSearch = "AN-CL-22: Verify the results are displayed in the table after applying Instrument ID Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Instrument ID Filter";
		objTmp.TestCaseNameClearInput = "AN-CL-23: Verify user can clear input field and reset Instrument ID Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Instrument ID Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Instrument ID Filter";
		objFilter.FilterXPath = "Instrument ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Instrument ID";
		objFilter.SearchVlaue = "PSN0002";
		objFilter.FilterListXPathPrefix = Test_Elements.clInstrumentIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.clInstrumentIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.clInstrumentIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("PSN0002"));
		objFilter.getRowValue = Test_Elements.clInstrumentIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "clear-input-Instrument ID";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		

		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-24: Verify user can filter any value from Catridge ID Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Catridge ID Filter";
		objTmp.TestCaseNameSearch = "AN-CL-25: Verify the results are displayed in the table after applying Catridge ID Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Catridge ID Filter";
		objTmp.TestCaseNameClearInput = "AN-CL-26: Verify user can clear input field and reset Catridge ID Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Catridge ID Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Cartridge ID Filter";
		objFilter.FilterXPath = "Cartridge ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Cartridge ID";
		objFilter.SearchVlaue = "03-31-2020_PSN13_21613_cocc";  
		objFilter.FilterListXPathPrefix = Test_Elements.clCatridgeIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.clCatridgeIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.clCatridgeIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("03-31-2020_PSN13_21613_cocc"));
		objFilter.getRowValue = Test_Elements.clCatridgeIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "clear-input-Cartridge ID";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		

		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-27: Verify user can filter any value from Lane Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Lane Filter";
		objTmp.TestCaseNameSearch = "AN-CL-28: Verify the results are displayed in the table after applying Lane Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Lane Filter";
		objTmp.TestCaseNameClearInput = "AN-CL-29: Verify user can clear input field and reset Lane Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Lane Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Lane Filter";
		objFilter.FilterXPath = "Lane";
		objFilter.FilterListXPathSearch = "place-holder-search-Lane";
		objFilter.SearchVlaue = "10";
		objFilter.FilterListXPathPrefix = Test_Elements.clLanebeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.clLaneafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.clLaneafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("10"));
		objFilter.getRowValue = Test_Elements.clLaneIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "clear-input-Lane";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
	

		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-30: Verify user can filter any value from Assay Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Assay Filter";
		objTmp.TestCaseNameSearch = "AN-CL-31: Verify the results are displayed in the table after applying Assay Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Assay Filter";
		objTmp.TestCaseNameClearInput = "AN-CL-32: Verify user can clear input field and reset Assay Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Assay Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Assay Filter";
		objFilter.FilterXPath = "Assay";
		objFilter.FilterListXPathSearch = "place-holder-search-Assay";
		objFilter.SearchVlaue = "Coccidia";
		objFilter.FilterListXPathPrefix = Test_Elements.clAssaybeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.clAssayafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.clAssayafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Coccidia-SYBR"));
		objFilter.getRowValue = Test_Elements.clAssayRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = Test_Elements.clAssayClearInput;
		objFilter.ClearInput = "clear-input-Assay";
		lstCoccidiaModel.add(objTmp);
		               

		
		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-33: Verify user can filter any value from Improc Version Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Improc Version Filter";
		objTmp.TestCaseNameSearch = "AN-CL-34: Verify the results are displayed in the table after applying Improc Version Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Improc Version Filter";
		objTmp.TestCaseNameClearInput = "AN-CL-35: Verify user can clear input field and reset Improc Version Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Improc Version Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Improc Version Filter";
		objFilter.FilterXPath = "Improc Version";
		objFilter.FilterListXPathSearch = "place-holder-search-Improc Version";
		objFilter.SearchVlaue = "2.0";
		objFilter.FilterListXPathPrefix = Test_Elements.clImprocbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.clImprocafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.clImprocafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2.0"));
		objFilter.getRowValue = Test_Elements.clImprocIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Improc Version";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp); 
		
	
		
		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-36: Verify user can filter any value from Site Name Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Site Name Filter";
		objTmp.TestCaseNameSearch = "AN-CL-37: Verify the results are displayed in the table after applying Site Name Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Site Name Filter";
		objTmp.TestCaseNameClearInput = "AN-CL-38: Verify user can clear input field and reset Site Name Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Site Name Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = true;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site Name Filter";
		objFilter.FilterXPath = "Site Name";
		objFilter.FilterListXPathSearch = "place-holder-search-Site Name";
		objFilter.SearchVlaue = "Peco Gordo";
		objFilter.FilterListXPathPrefix = Test_Elements.clSiteNamebeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.clSiteNameafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.clSiteNameafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Peco Gordo"));
		objFilter.getRowValue = Test_Elements.clSiteNameRow;
		objFilter.rowValueExpected = "";
		objFilter.ClearInput = "clear-input-Site Name";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp); 
		
		
		
		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-39: Verify user can filter any value from Sample Matrix Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Sample Matrix Filter";
		objTmp.TestCaseNameSearch = "AN-CL-40: Verify the results are displayed in the table after applying Sample Matrix Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Sample Matrix Filter";
		objTmp.TestCaseNameClearInput = "AN-CL-41: Verify user can clear input field and reset Sample Matrix Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Sample Matrix Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Sample Matrix Filter";
		objFilter.FilterXPath = "Sample Matrix";
		objFilter.FilterListXPathSearch = "place-holder-search-Sample Matrix";
		objFilter.SearchVlaue = "Feces";
		objFilter.FilterListXPathPrefix = Test_Elements.clSampleMatrixbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.clSampleMatrixafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.clSampleMatrixafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Feces"));
		objFilter.getRowValue = Test_Elements.clSampleMatrixRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Sample Matrix";;
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp); 
		
		
		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-42: Verify user can filter any value from Customer Sample ID Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Customer Sample ID Filter";
		objTmp.TestCaseNameSearch = "AN-CL-43: Verify the results are displayed in the table after applying Customer Sample ID Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Customer Sample ID Filter";
		objTmp.TestCaseNameClearInput = "AN-CL-44: Verify user can clear input field and reset Customer Sample ID Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Customer Sample ID Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Customer Sample ID Filter";
		objFilter.FilterXPath = "Customer Sample ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Customer Sample ID";;
		objFilter.SearchVlaue = "CS1";
		objFilter.FilterListXPathPrefix = Test_Elements.clCSampleIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.clCSampleIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.clCSampleIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("CS1"));
		objFilter.getRowValue = Test_Elements.clCSampleIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Customer Sample ID";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		
		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-45: Verify user can filter any value from Date Received Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Date Received Filter";
		objTmp.TestCaseNameSearch = "AN-CL-46: Verify the results are displayed in the table after applying Date Received Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Date Received Filter";
		objTmp.TestCaseNameClearInput = "AN-CL-47: Verify user can clear input field and reset Date Received Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Date Received Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Received Filter";
		objFilter.FilterXPath = "Date Received";
		objFilter.FilterListXPathSearch = "place-holder-search-Date Received";
		objFilter.SearchVlaue = "06-11-2020";
		objFilter.FilterListXPathPrefix = Test_Elements.clDateReceivedbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.clDateReceivedafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.clDateReceivedafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("06-11-2020"));
		objFilter.getRowValue = Test_Elements.clDateReceivedRow;
		objFilter.rowValueExpected = "";
		objFilter.ClearInput = "clear-input-Date Received";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		
		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-48: Verify user can filter any value from Kit Lot Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Kit Lot Filter";
		objTmp.TestCaseNameSearch = "AN-CL-49: Verify the results are displayed in the table after applying Kit Lot Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Kit Lot Filter";
		objTmp.TestCaseNameClearInput = "AN-CL-50: Verify user can clear input field and reset Kit Lot Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Kit Lot Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Kit Lot Filter";
		objFilter.FilterXPath = "Kit Lot";
		objFilter.FilterListXPathSearch = "place-holder-search-Kit Lot";
		objFilter.SearchVlaue = "June";
		objFilter.FilterListXPathPrefix = Test_Elements.clKitLotbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.clKitLotafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.clKitLotafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("June"));
		objFilter.getRowValue = Test_Elements.clKitLotRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Kit Lot";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		
		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-51: Verify user can filter any value from Piper User Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Piper User Filter";
		objTmp.TestCaseNameSearch = "AN-CL-52: Verify the results are displayed in the table after applying Piper User Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Piper User Filter";
		objTmp.TestCaseNameClearInput = "AN-CL-53: Verify user can clear input field and reset  Piper User Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Piper User Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Piper User Filter";
		objFilter.FilterXPath = "Piper User";
		objFilter.FilterListXPathSearch = "place-holder-search-Piper User";
		objFilter.SearchVlaue = "QUSER";
		objFilter.FilterListXPathPrefix = Test_Elements.clPiperUserbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.clPiperUserafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.clPiperUserafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("QUSER"));
		objFilter.getRowValue = Test_Elements.clPiperUserRow;
		objFilter.rowValueExpected = "";
		objFilter.ClearInput = "clear-input-Piper User";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		
		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-54: Verify user can filter any value from Result Status Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Result Status Filter";
		objTmp.TestCaseNameSearch = "AN-CL-55: Verify the results are displayed in the table after applying Result Status Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Result Status Filter";
		objTmp.TestCaseNameClearInput = "AN-CL-56: Verify user can clear input field and reset  Result Status Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Result Status Filter by clicking on cross icon and reset the filter";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Result Status Filter";
		objFilter.FilterXPath = "Result Status";
		objFilter.FilterListXPathSearch = "place-holder-search-Result Status";
		objFilter.SearchVlaue = "Pending";
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Pending"));
		objFilter.ClearInput = "clear-input-Result Status";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
	
		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-57: Verify user can filter multiple value from same filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter multiple value from same filter";
		objTmp.TestCaseNameSearch = "AN-CL-58: Verify the results are displayed in the table after applying multiple value from same filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying multiple value from same filter";
		objTmp.TestCaseNameClearInput = "AN-CL-59: Verify user can clear input field and reset filter after applying multiple value from same filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field and reset filter after applying multiple value from same filter by clicking on cross icon"; 
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "multiple value from same filter";
		objFilter.FilterXPath = "Sample ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Sample ID";
		objFilter.SearchVlaue = "TD";
		objFilter.FilterListXPathPrefix = Test_Elements.clSampleIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.clSampleIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.clSampleIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("TD05", "TD06"));
		objFilter.getRowValue = Test_Elements.clSampleIDRow;
		objFilter.rowValueExpected = "TD06";
		objFilter.ClearInput = "clear-input-Sample ID";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		
		objTmp = new CoccidiaModel();
		objTmp.TestCaseName = "AN-CL-60: Verify user can filter values from two filters at sametime";
		objTmp.TestCaseDescription = "This testcase will verify user filter values from two filters at sametime";
		objTmp.TestCaseNameSearch = "AN-CL-61: Verify the results are displayed in the table after applying filter values from two filters at sametime";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying filter values from two filters at sametime";
		objTmp.TestCaseNameClearInput = "AN-CL-62: Verify user can clear input fields and reset filters after applying filter values from two filters at sametime";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field after applying filter values from two filters at sametime";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Instrument ID Filter";
		objFilter.FilterXPath = "Instrument ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Instrument ID";
		objFilter.SearchVlaue = "PSN0004";
		objFilter.FilterListXPathPrefix = Test_Elements.clInstrumentIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.clInstrumentIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.clInstrumentIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("PSN0004"));
		objFilter.ClearInput = "clear-input-Instrument ID";
		objFilter.FilterXPath = "Sample ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Sample ID";
		objFilter.SearchVlaue = "Coccivac";
		objFilter.FilterListXPathPrefix = Test_Elements.clSampleIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.clSampleIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.clSampleIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Coccivac"));
		objFilter.getRowValue = Test_Elements.clSampleIDRow;
		objFilter.rowValueExpected = "Coccivac";
		objFilter.ClearInput = "clear-input-Sample ID";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
	
		return lstCoccidiaModel;
	}

	
	
	
	public static ArrayList<CoccidiaModel> FillDate() {
		ArrayList<CoccidiaModel> lstCoccidiaModel = new ArrayList<CoccidiaModel>();
		CoccidiaModel objTmp = new CoccidiaModel();
		
		objTmp.TestCaseName = "AN-CL-07: Verify user can filter Today date from date Filter";
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
		objTmp.TestCaseName = "AN-CL-08: Verify user can filter Last 24 Hours from date Filter";
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
		objTmp.TestCaseName = "AN-CL-09: Verify user can filter Last 7 Days from date Filter";
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
		objTmp.TestCaseName = "AN-CL-10: Verify user can filter Last 30 Days from date Filter";
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
		objTmp.TestCaseName = "AN-CL-11: Verify user can filter Last Month from date Filter";
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
		objTmp.TestCaseName = "AN-CL-12: Verify user can filter This Month from date Filter";
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
		
		objTmp.TestCaseName = "AN-CL-13: Verify user cannot apply filter with invalid date";
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
		objTmp.TestCaseName = "AN-CL-14: Verify user cannot apply filter with any other date format except mm/dd/yyyy";
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
		objTmp.TestCaseName = "AN-CL-15: Verify user cannot apply filter with from date greater than to date";
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
		objTmp.TestCaseName = "AN-CL-16: Verify user can search for records with valid date";
		objTmp.TestCaseDescription = "This testcase will verify that user can search for records with valid date";
		objTmp.Filter1 = false;
		objTmp.Filter2 = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Enter valid date";
		objFilter.FilterListXPathPrefix = Test_Elements.clDateFrom;
		objFilter.FilterListXPathSuffix = Test_Elements.clDateTo;
		objFilter.fromDate = "12/01/2020";
		objFilter.toDate = "12/07/2020";
		objTmp.lstFilters.add(objFilter);
		lstCoccidiaModel.add(objTmp);
		
		return lstCoccidiaModel;
		
}
	
	
	public static ArrayList<CoccidiaModel> pagination() {
		ArrayList<CoccidiaModel> lstCoccidiaModel = new ArrayList<CoccidiaModel>();
		CoccidiaModel objTmp = new CoccidiaModel();
		
		objTmp.TestCaseName = "AN-CL-64: Verify pagination exist on Coccidia Log report";
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
		objTmp.TestCaseName = "AN-CL-65: Verify user navigates to last page on clicking '>>' button in pagnation";
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
		objTmp.TestCaseName = "AN-CL-66: Verify user navigates to previous page on clicking '<' button in pagnation";
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
		objTmp.TestCaseName = "AN-CL-67: Verify user navigates to first page on clicking '<<' button in pagnation";
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
		objTmp.TestCaseName = "AN-CL-68: Verify user navigates to next page on clicking '>' button in pagnation";
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
		
		objTmp.TestCaseName = "AN-CL-69: Verify 100 rows are displayed when 100 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 100 rows are displayed when 100 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-CL-70: Verify 100 rows are displayed when 100 Rows per Page is selected and user moves to next page";
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
		objTmp.TestCaseName = "AN-CL-71: Verify 250 rows are displayed when 250 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 250 rows are displayed when 250 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-CL-72: Verify 500 rows are displayed when 250 Rows per Page is selected and user moves to next page";
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
		objTmp.TestCaseName = "AN-CL-73: Verify 500 rows are displayed when 500 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 100 rows are displayed when 500 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-CL-74: Verify 500 rows are displayed when 500 Rows per Page is selected and user moves to next page";
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


