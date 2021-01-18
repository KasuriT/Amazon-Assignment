package Models;
import java.util.ArrayList;
import java.util.Arrays;

import Test.Ancera.Test_Elements;

public class ExternalCoccidiaModel {

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
	public ExternalCoccidiaModel() {

	}

	public static ArrayList<ExternalCoccidiaModel> FillData() {
		ArrayList<ExternalCoccidiaModel> lstExternalCoccidiaModel = new ArrayList<ExternalCoccidiaModel>();
		ExternalCoccidiaModel objTmp = new ExternalCoccidiaModel();

		objTmp.TestCaseName = "AN-ECL-19: Verify user can filter any value from Sample ID Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Sample ID Filter";
		objTmp.TestCaseNameSearch = "AN-ECL-20: Verify the results are displayed in the table after applying Sample ID Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Sample ID Filter";
		objTmp.TestCaseNameClearInput = "AN-ECL-21: Verify clear input functionality of Sample ID Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Sample ID Filter by clicking on cross icon";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "Lab Sample ID Filter";
		objFilter.FilterXPath = "Lab Sample ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Lab Sample ID";
		objFilter.SearchVlaue = "2019_SFC_2";
		objFilter.FilterListXPathPrefix = Test_Elements.eclSampleIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eclSampleIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eclSampleIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2019_SFC_2"));
		objFilter.getRowValue = Test_Elements.eclSampleIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "clear-input-Lab Sample ID";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);

/*
		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-22: Verify user can filter any value from Instrument ID Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Instrument ID Filter";
		objTmp.TestCaseNameSearch = "AN-ECL-23: Verify the results are displayed in the table after applying Instrument ID Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Instrument ID Filter";
		objTmp.TestCaseNameClearInput = "AN-ECL-24: Verify clear input functionality of Instrument ID Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Instrument ID Filter by clicking on cross icon";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Instrument ID Filter";
		objFilter.FilterXPath = "Instrument ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Instrument ID";
		objFilter.SearchVlaue = "PSN0002";
		objFilter.FilterListXPathPrefix = Test_Elements.eclInstrumentIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eclInstrumentIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eclInstrumentIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("PSN0002"));
		objFilter.getRowValue = Test_Elements.eclInstrumentIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "clear-input-Instrument ID";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-25: Verify user can filter any value from Catridge ID Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Catridge ID Filter";
		objTmp.TestCaseNameSearch = "AN-ECL-26: Verify the results are displayed in the table after applying Catridge ID Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Catridge ID Filter";
		objTmp.TestCaseNameClearInput = "AN-ECL-27: Verify clear input functionality of Catridge ID Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Catridge ID Filter by clicking on cross icon";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Cartridge ID Filter";
		objFilter.FilterXPath = "Cartridge ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Cartridge ID";
		objFilter.SearchVlaue = "03-31-2020_PSN13_21613_cocc";  
		objFilter.FilterListXPathPrefix = Test_Elements.eclCatridgeIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eclCatridgeIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eclCatridgeIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("03-31-2020_PSN13_21613_cocc"));
		objFilter.getRowValue = Test_Elements.eclCatridgeIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "clear-input-Cartridge ID";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-28: Verify user can filter any value from Lane Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Lane Filter";
		objTmp.TestCaseNameSearch = "AN-ECL-29: Verify the results are displayed in the table after applying Lane Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Lane Filter";
		objTmp.TestCaseNameClearInput = "AN-ECL-30: Verify clear input functionality of Lane Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Lane Filter by clicking on cross icon";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Lane Filter";
		objFilter.FilterXPath = "Lane";
		objFilter.FilterListXPathSearch = "place-holder-search-Lane";
		objFilter.SearchVlaue = "10";
		objFilter.FilterListXPathPrefix = Test_Elements.eclLanebeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eclLaneafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eclLaneafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("10"));
		objFilter.getRowValue = Test_Elements.eclLaneIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "clear-input-Lane";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-31: Verify user can filter any value from Assay Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Assay Filter";
		objTmp.TestCaseNameSearch = "AN-ECL-32: Verify the results are displayed in the table after applying Assay Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Assay Filter";
		objTmp.TestCaseNameClearInput = "AN-ECL-33: Verify clear input functionality of Assay Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Assay Filter by clicking on cross icon";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Assay Filter";
		objFilter.FilterXPath = "Assay";
		objFilter.FilterListXPathSearch = "place-holder-search-Assay";
		objFilter.SearchVlaue = "Coccidia-SYBR";
		objFilter.FilterListXPathPrefix = Test_Elements.eclAssaybeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eclAssayafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eclAssayafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Coccidia-SYBR"));
		objFilter.getRowValue = Test_Elements.eclAssayRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue; 
		objFilter.ClearInput = "clear-input-Assay";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);



		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-34: Verify user can filter any value from Improc Version Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Improc Version Filter";
		objTmp.TestCaseNameSearch = "AN-ECL-35: Verify the results are displayed in the table after applying Improc Version Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Improc Version Filter";
		objTmp.TestCaseNameClearInput = "AN-ECL-36: Verify clear input functionality of Improc Version Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Improc Version Filter by clicking on cross icon";
		objTmp.ApplyFilter = true;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Improc Version Filter";
		objFilter.FilterXPath = "Improc Version";
		objFilter.FilterListXPathSearch = "place-holder-search-Improc Version";
		objFilter.SearchVlaue = "2.0";
		objFilter.FilterListXPathPrefix = Test_Elements.eclImprocbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eclImprocafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eclImprocafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2.0"));
		objFilter.getRowValue = Test_Elements.eclImprocIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Improc Version";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp); 



		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-37: Verify user can filter any value from Site Name Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Site Name Filter";
		objTmp.TestCaseNameSearch = "AN-ECL-38: Verify the results are displayed in the table after applying Site Name Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Site Name Filter";
		objTmp.TestCaseNameClearInput = "AN-ECL-39: Verify clear input functionality of Site Name Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Site Name Filter by clicking on cross icon";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection Site Name Filter";
		objFilter.FilterXPath = "Collection Site Name";
		objFilter.FilterListXPathSearch = "place-holder-search-Collection Site Name";
		objFilter.SearchVlaue = "Peco Gordo";
		objFilter.FilterListXPathPrefix = Test_Elements.eclSiteNamebeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eclSiteNameafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eclSiteNameafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Peco Gordo"));
		objFilter.getRowValue = Test_Elements.eclSiteNameRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Collection Site Name";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp); 



		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-40: Verify user can filter any value from Sample Matrix Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Sample Matrix Filter";
		objTmp.TestCaseNameSearch = "AN-ECL-41: Verify the results are displayed in the table after applying Sample Matrix Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Sample Matrix Filter";
		objTmp.TestCaseNameClearInput = "AN-ECL-42: Verify clear input functionality of Sample Matrix Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Sample Matrix Filter by clicking on cross icon";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Sample Matrix Filter";
		objFilter.FilterXPath = "Sample Matrix";
		objFilter.FilterListXPathSearch = "place-holder-search-Sample Matrix";;
		objFilter.SearchVlaue = "Feces";
		objFilter.FilterListXPathPrefix = Test_Elements.eclSampleMatrixbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eclSampleMatrixafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eclSampleMatrixafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Feces"));
		objFilter.getRowValue = Test_Elements.eclSampleMatrixRow;
		objFilter.rowValueExpected = "";
		objFilter.ClearInput = "clear-input-Sample Matrix";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp); 


		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-43: Verify user can filter any value from Customer Sample ID Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Customer Sample ID Filter";
		objTmp.TestCaseNameSearch = "AN-ECL-44: Verify the results are displayed in the table after applying Customer Sample ID Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Customer Sample ID Filter";
		objTmp.TestCaseNameClearInput = "AN-ECL-45: Verify clear input functionality of Customer Sample ID Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Customer Sample ID Filter by clicking on cross icon";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Customer Sample ID Filter";
		objFilter.FilterXPath = "Customer Sample ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Customer Sample ID";
		objFilter.SearchVlaue = "CS1";
		objFilter.FilterListXPathPrefix = Test_Elements.eclCSampleIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eclCSampleIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eclCSampleIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("CS1"));
		objFilter.getRowValue = Test_Elements.eclCSampleIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Customer Sample ID";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-46: Verify user can filter any value from Date Received Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Date Received Filter";
		objTmp.TestCaseNameSearch = "AN-ECL-47: Verify the results are displayed in the table after applying Date Received Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Date Received Filter";
		objTmp.TestCaseNameClearInput = "AN-ECL-48: Verify clear input functionality of Date Received Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Date Received Filter by clicking on cross icon";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Received Filter";
		objFilter.FilterXPath = "Date Received";
		objFilter.FilterListXPathSearch = "place-holder-search-Date Received";
		objFilter.SearchVlaue = "06-11-2020";
		objFilter.FilterListXPathPrefix = Test_Elements.eclDateReceivedbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eclDateReceivedafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eclDateReceivedafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("06-11-2020"));
		objFilter.getRowValue = Test_Elements.eclDateReceivedRow;
		objFilter.rowValueExpected = "";
		objFilter.ClearInput = "clear-input-Date Received";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-49: Verify user can filter any value from Kit Lot Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Kit Lot Filter";
		objTmp.TestCaseNameSearch = "AN-ECL-50: Verify the results are displayed in the table after applying Kit Lot Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Kit Lot Filter";
		objTmp.TestCaseNameClearInput = "AN-ECL-51: Verify clear input functionality of Kit Lot Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Kit Lot Filter by clicking on cross icon";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Kit Lot Filter";
		objFilter.FilterXPath = "Kit Lot";
		objFilter.FilterListXPathSearch = "place-holder-search-Kit Lot";
		objFilter.SearchVlaue = "June";
		objFilter.FilterListXPathPrefix = Test_Elements.eclKitLotbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eclKitLotafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eclKitLotafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("June"));
		objFilter.getRowValue = Test_Elements.eclKitLotRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Kit Lot";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-52: Verify user can filter any value from Piper User Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Piper User Filter";
		objTmp.TestCaseNameSearch = "AN-ECL-53: Verify the results are displayed in the table after applying Piper User Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Piper User Filter";
		objTmp.TestCaseNameClearInput = "AN-ECL-54: Verify clear input functionality of Piper User Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Piper User Filter by clicking on cross icon";
		objTmp.ApplyFilter = true;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Piper User Filter";
		objFilter.FilterXPath = "Piper User";
		objFilter.FilterListXPathSearch = "place-holder-search-Piper User";
		objFilter.SearchVlaue = "QUSER";
		objFilter.FilterListXPathPrefix = Test_Elements.eclPiperUserbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eclPiperUserafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eclPiperUserafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("QUSER"));
		objFilter.getRowValue = Test_Elements.eclPiperUserRow;
		objFilter.rowValueExpected = "";
		objFilter.ClearInput = "clear-input-Piper User";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-55: Verify user can filter any value from Collection Site ID Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Collection Site ID Filter";
		objTmp.TestCaseNameSearch = "AN-ECL-56: Verify the results are displayed in the table after applying Collection Site ID Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Collection Site ID Filter";
		objTmp.TestCaseNameClearInput = "AN-ECL-57: Verify clear input functionality of Collection Site ID Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Collection Site ID Filter by clicking on cross icon";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection Site ID Filter";
		objFilter.FilterXPath = "Collection Site ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Collection Site ID";
		objFilter.SearchVlaue = "1001001";  
		objFilter.FilterListXPathPrefix = Test_Elements.eclCollectionSitebeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eclCollectionSiteafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eclCollectionSiteafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("1001001"));
		objFilter.getRowValue = Test_Elements.eclCSiteIDRow;
		objFilter.rowValueExpected = "";
		objFilter.ClearInput = "clear-input-Collection Site ID";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-58: Verify user can filter any value from Requested Assay Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Requested Assay Filter";
		objTmp.TestCaseNameSearch = "AN-ECL-59: Verify the results are displayed in the table after applying Requested Assay Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Requested Assay Filter";
		objTmp.TestCaseNameClearInput = "AN-ECL-60: Verify clear input functionality of Requested Assay Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Requested Assay Filter by clicking on cross icon";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Requested Assay Filter";
		objFilter.FilterXPath = "Requested Assay";
		objFilter.FilterListXPathSearch = "place-holder-search-Requested Assay";
		objFilter.SearchVlaue = "   RA8   ";
		objFilter.FilterListXPathPrefix = Test_Elements.eclRequestedAssaybeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eclRequestedAssayafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eclRequestedAssayafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("   RA8   "));
		objFilter.getRowValue = Test_Elements.eclRequestedAssayRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Requested Assay";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-61: Verify user can filter any value from Flock ID Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Flock ID Filter";
		objTmp.TestCaseNameSearch = "AN-ECL-62: Verify the results are displayed in the table after applying Flock ID Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Flock ID Filter";
		objTmp.TestCaseNameClearInput = "AN-ECL-63: Verify clear input functionality of Flock ID Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Flock ID Filter by clicking on cross icon";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Flock ID Filter";
		objFilter.FilterXPath = "Flock ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Flock ID";
		objFilter.SearchVlaue = "62";
		objFilter.FilterListXPathPrefix = Test_Elements.eclFlockIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eclFlockIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eclFlockIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("62"));
		objFilter.getRowValue = Test_Elements.eclFlockIDRow;
		objFilter.rowValueExpected = objFilter.SearchVlaue;
		objFilter.ClearInput = "clear-input-Flock ID";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);
*/

		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-64: Verify user can filter any value from Collection Site Type Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Collection Site Type Filter";
		objTmp.TestCaseNameSearch = "AN-ECL-65: Verify the results are displayed in the table after applying Collection Site Type Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Collection Site Type Filter";
		objTmp.TestCaseNameClearInput = "AN-ECL-66: Verify clear input functionality of Collection Site Type Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Collection Site Type Filter by clicking on cross icon";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Collection Site Type Filter";
		objFilter.FilterXPath = "Collection Site Type";
		objFilter.FilterListXPathSearch = "place-holder-search-Collection Site Type";
		objFilter.SearchVlaue = "Complex";
		objFilter.FilterListXPathPrefix = Test_Elements.eclSiteTypebeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eclSiteTypeafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eclSiteTypeafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Complex"));
		objFilter.getRowValue = Test_Elements.eclCSiteTypeRow;
		objFilter.rowValueExpected = "";
		objFilter.ClearInput = "clear-input-Collection Site Type";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


/*
		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-67: Verify user can filter any value from Result Status Filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter any value from Result Status Filter";
		objTmp.TestCaseNameSearch = "AN-ECL-68: Verify the results are displayed in the table after applying Result Status Filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying Result Status Filter";
		objTmp.TestCaseNameClearInput = "AN-ECL-69: Verify clear input functionality of Result Status Filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field of Result Status Filter by clicking on cross icon";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Result Status Filter";
		objFilter.FilterXPath = "Result Status";
		objFilter.FilterListXPathSearch = "place-holder-search-Result Status";
		objFilter.SearchVlaue = "Pending";
		objFilter.FilterListXPathPrefix = Test_Elements.eclResultStatusbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eclResultStatusafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eclResultStatusafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Pending"));
		//objFilter.getRowValue = Test_Elements.eclResultStatusRow; 
		objFilter.rowValueExpected = "";        
		objFilter.ClearInput = "clear-input-Result Status";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-70: Verify user can filter multiple value from same filter";
		objTmp.TestCaseDescription = "This testcase will verify user can filter multiple value from same filter";
		objTmp.TestCaseNameSearch = "AN-ECL-71: Verify the results are displayed in the table after applying multiple value from same filter";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying multiple value from same filter";
		objTmp.TestCaseNameClearInput = "AN-ECL-72: Verify clear input functionality after applying multiple value from same filter";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field after applying multiple value from same filter by clicking on cross icon"; 
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = true;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "multiple value from same filter";
		objFilter.FilterXPath = "Lab Sample ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Lab Sample ID";
		objFilter.SearchVlaue = "TD";
		objFilter.FilterListXPathPrefix = Test_Elements.eclSampleIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eclSampleIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eclSampleIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("TD05", "TD06"));
		objFilter.getRowValue = Test_Elements.eclSampleIDRow;
		objFilter.rowValueExpected = "TD06";
		objFilter.ClearInput = "clear-input-Lab Sample ID";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);



		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-73: Verify user can filter values from two filters at sametime";
		objTmp.TestCaseDescription = "This testcase will verify user filter values from two filters at sametime";
		objTmp.TestCaseNameSearch = "AN-ECL-74: Verify the results are displayed in the table after applying filter values from two filters at sametime";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify the results are displayed in the table after applying filter values from two filters at sametime";
		objTmp.TestCaseNameClearInput = "AN-ECL-75: Verify clear input functionality after applying filter values from two filters at sametimer";
		objTmp.TestCaseDescClearInput = "This test case will verify user can clear search field after applying filter values from two filters at sametime";
		objTmp.ApplyFilter = false;
		objTmp.ResetFilter = false;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Instrument ID Filter";
		objFilter.FilterXPath = "Instrument ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Instrument ID";
		objFilter.SearchVlaue = "PSN0004";
		objFilter.FilterListXPathPrefix = Test_Elements.eclInstrumentIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eclInstrumentIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eclInstrumentIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("PSN0004"));
		objFilter.ClearInput = "clear-input-Instrument ID";
		objFilter.FilterXPath = "Lab Sample ID";
		objFilter.FilterListXPathSearch = "place-holder-search-Lab Sample ID";
		objFilter.SearchVlaue = "Coccivac";
		objFilter.FilterListXPathPrefix = Test_Elements.eclSampleIDbeforeXpath;
		objFilter.FilterListXPathSuffix = Test_Elements.eclSampleIDafterXpath;
		objFilter.FilterListXPathChkSuffix = Test_Elements.eclSampleIDafterXpath1;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("Coccivac"));
		objFilter.getRowValue = Test_Elements.eclSampleIDRow;
		objFilter.rowValueExpected = "Coccivac";
		objFilter.ClearInput = "clear-input-Lab Sample ID";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);
*/
		return lstExternalCoccidiaModel;
	}


	public static ArrayList<ExternalCoccidiaModel> FillDate() {
		ArrayList<ExternalCoccidiaModel> lstExternalCoccidiaModel = new ArrayList<ExternalCoccidiaModel>();
		ExternalCoccidiaModel objTmp = new ExternalCoccidiaModel();

		objTmp.TestCaseName = "AN-ECL-07: Verify user can filter Today date from date Filter";
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
		objTmp.TestCaseName = "AN-ECL-08: Verify user can filter Last 24 Hours from date Filter";
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
		objTmp.TestCaseName = "AN-ECL-09: Verify user can filter Last 7 Days from date Filter";
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
		objTmp.TestCaseName = "AN-ECL-10: Verify user can filter Last 30 Days from date Filter";
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
		objTmp.TestCaseName = "AN-ECL-11: Verify user can filter Last Month from date Filter";
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
		objTmp.TestCaseName = "AN-ECL-12: Verify user can filter This Month from date Filter";
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

		objTmp.TestCaseName = "AN-ECL-13: Verify user cannot apply filter with invalid date";
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
		objTmp.TestCaseName = "AN-ECL-14: Verify user cannot apply filter with any other date format except mm/dd/yyyy";
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
		objTmp.TestCaseName = "AN-ECL-15: Verify user cannot apply filter with from date greater than to date";
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
		objTmp.TestCaseName = "AN-ECL-16: Verify user can search for records with valid date";
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

		objTmp.TestCaseName = "AN-ECL-77: Verify pagination exist on Salmonella Log report";
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
		objTmp.TestCaseName = "AN-ECL-78: Verify user navigates to last page on clicking '>>' button in pagnation";
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
		objTmp.TestCaseName = "AN-ECL-79: Verify user navigates to previous page on clicking '<' button in pagnation";
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
		objTmp.TestCaseName = "AN-ECL-80: Verify user navigates to first page on clicking '<<' button in pagnation";
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
		objTmp.TestCaseName = "AN-ECL-81: Verify user navigates to next page on clicking '>' button in pagnation";
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

		objTmp.TestCaseName = "AN-ECL-82: Verify 100 rows are displayed when 100 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 100 rows are displayed when 100 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-ECL-83: Verify 100 rows are displayed when 100 Rows per Page is selected and user moves to next page";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that 100 rows are displayed when 100 Rows per Page is selected and user moves to next page";
		objTmp.lstFilters = new ArrayList<>();
		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "100 Rows per Page";
		objFilter.FilterListXPathSearch = "100";
		objFilter.count = "100";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-84: Verify 250 rows are displayed when 250 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 250 rows are displayed when 250 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-ECL-85: Verify 500 rows are displayed when 250 Rows per Page is selected and user moves to next page";
		objTmp.TestCaseDescriptionSearch = "This testcase will verify that 250 rows are displayed when 250 Rows per Page is selected and user moves to next page";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterName = "250 Rows per Page";
		objFilter.FilterListXPathSearch = "250";
		objFilter.count = "250";
		objTmp.lstFilters.add(objFilter);
		lstExternalCoccidiaModel.add(objTmp);


		objTmp = new ExternalCoccidiaModel();
		objTmp.TestCaseName = "AN-ECL-86: Verify 500 rows are displayed when 500 Rows per Page is selected";
		objTmp.TestCaseDescription = "This testcase will verify that 100 rows are displayed when 500 Rows per Page is selected";
		objTmp.TestCaseNameSearch = "AN-ECL-87: Verify 500 rows are displayed when 500 Rows per Page is selected and user moves to next page";
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


