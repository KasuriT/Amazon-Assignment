package PageObjects;

import static MiscFunctions.Constants.url_reports;
import static PageObjects.BasePage.loading_cursor;
import org.openqa.selenium.By;
import org.testng.Assert;

import Config.BaseTest;
import MiscFunctions.Methods;

public class CoccidiaLogPage {

	private static By coccidiaLogBox = By.cssSelector("img[alt='Coccidia Log']"); 
	private static By coccidiaLogTitle = By.id("Coccidia Log");
	
	public static String coccidiaLogTable = "coccidia-data-log";
	public static String clLaneCol = "0";
	public static String clSampleIDCol = "1";
	public static String clQCCodeCol = "2";
	public static String clResultStatusCol = "3";
	public static String clTotalOPGCol = "4";
	public static String clSmallOPGCol = "5";
	public static String clMediumOPGCol = "6";
	public static String clLargeOPGCol = "7";
	public static String clResultCol = "8";
	public static String clAssayCol = "9";
	public static String clResultIDCol = "10";
	public static String clDateCol = "11";
	public static String clTimeCol = "12";
	public static String clSiteNameCol = "13";
	public static String clSampleMatrixCol = "14";
	public static String clCSampleIDCol = "15";
	public static String clReceivedDateCol = "16";
	public static String clCatridgeIDCol = "17";
	public static String clInstrumentIDCol = "18";
	public static String clTotalCountCol = "19";
	public static String clSmallCountCol = "20";
	public static String clMediumCountCol = "21";
	public static String clLargeCountCol = "22";
	public static String clMeanIntensityCol = "23";
	public static String clPiperUserCol = "24";
	public static String clKitLotCol = "25";
	public static String clImprocIDCol = "26";
	public static String clTestSiteIDCol = "27";
	public static String clTestSiteNameCol = "28";
	public static String clCollectionSiteTypeCol = "29";
	public static String clRequestedAssayCol = "30";
	public static String clFlockIDCol = "31";
	public static String clRunTypeCol = "32";
	public static String clCollectionDateCol = "33";
	public static String clCollectionSiteIDCol = "34";
	public static String clFarmCol = "35";
	public static String clComplexCol = "36";
	public static String clVaccinceProgramCol = "37";
	public static String clFeedProgramCol = "38";
	public static String clFlockDayCol = "39";
	public static String clFieldAccessCol = "40";
	
	public static String clAuditLaneCol = "0";
	public static String clAuditSampleIDCol = "1";
	public static String clAuditQCCodeCol = "2";
	public static String clAuditResultStatusCol = "3";
	public static String clAuditAssayCol = "9";
	public static String clAuditDateCol = "10";
	public static String clAuditTimeCol = "11";
	public static String clAuditSiteNameCol = "12";
	public static String clAuditSampleMatrixCol = "13";
	public static String clAuditCSampleIDCol = "14";
	public static String clAuditReceivedDateCol = "15";
	public static String clAuditCartridgeIDCol = "16";
	public static String clAuditInstrumentIDCol = "17";
	public static String clAuditTotalCountCol = "18";
	public static String clAuditSmallCountCol = "19";
	public static String clAuditMediumCountCol = "20";
	public static String clAuditLargeCountCol = "21";
	public static String clAuditMeanIntensityCol = "22";
	public static String clAuditPiperUserCol = "23";
	public static String clAuditKitLotCol = "24";
	public static String clAuditImprocIDCol = "25";
	public static String clAuditTestSiteIDCol = "26";
	public static String clAuditTestSiteNameCol = "27";
	public static String clAuditCSiteTypeCol = "28";
	public static String clAuditRequestedAssayCol = "29";
	public static String clAuditFlockIDCol = "30";
	public static String clAuditRunTypeCol = "31";
	public static String clAuditCollectionDateCol = "32";
	public static String clAuditCollectionSiteIDCol = "33";
	public static String clAuditFarmCol = "34";
	public static String clAuditComplexCol = "35";
	public static String clAuditVaccinceProgramCol = "36";
	public static String clAuditFeedProgramCol = "37";
	public static String clAuditFlockDayCol = "38";
	
	public static String clShowFilter = "_show-filter";
	public static String clSortFilter = "sort-";
	public static String clApplyFilter = "_apply";
	public static String clClearFilter = "_clear-filter";
	public static String clSearchInput = "_search-input";
	
	public static String clLane = "laneNum";
	public static String clSampleID = "sampleId";
	public static String clQCCode = "countOutcome";
	public static String clResultStatus = "result_status";
	public static String clTotalOPG = "totalOPG";
	public static String clSmallOPG = "totalSmallOPG";
	public static String clMediumOPG = "totalMediumOPG";
	public static String clLargeOPG = "totalLargeOPG";
	public static String clResult = "outcome";
	public static String clAssay = "pathogen";
	public static String clResultID = "runId";
	public static String clResultTime = "time";
	public static String clResultDate = "scanDateTime";
	public static String clCollectionSiteName = "site_id";
	public static String clSampleMatrix = "sample_matrix";
	public static String clCustomerSampleID = "customer_sample_id";
	public static String clDateReceived = "metadata_date_recieved";
	public static String clCartridgeID = "cartridgeId";
	public static String clInstrumentID = "instrumentId";
	public static String clTotalCount = "oocystTotalCount";
	public static String clSmallCount = "oocystSmallCount";
	public static String clMediumCount = "oocystMediumCount";
	public static String clLargeCount = "oocystLargeCount";
	public static String clMeanIntensity = "oocystMeanIntensity";
	public static String clPiperUser = "user_name";
	public static String clkitLot = "kit_lot";
	public static String clImprocVersion = "version";
	public static String clTestSiteID = "testSiteId";
	public static String clTestSiteName = "testSiteName";
	public static String clCollectionSiteType = "collection_site_type";
	public static String clRequestedAssay = "requested_assay";
	public static String clFlockID = "flock_id";
	public static String clRunType = "runType";
	public static String clCollectionDate = "collectionDate";
	public static String clCollectionSiteID = "collection_site_id";
	public static String clFarm = "farm";
	public static String clComplex = "complex";
	public static String clVaccineProgram = "vaccineProgram";
	public static String clFeedProgram = "feedProgram";
	public static String clFlockDay = "flockDay";
	
	public static String clToday = "#list-title_range-0";
	public static String clLast24Hours = "#list-title_range-1";
	public static String clLast7Days = "#list-title_range-2";
	public static String clLast30Days = "#list-title_range-3";
	public static String clThisMonth = "#list-title_range-4";
	public static String clLastMonth = "#list-title_range-5";	
	
	public static String clTotalPages = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[15]/p";

	public static String clTable = "coccidia-data-log";
	
	public static String clPNGFileName = "Coccidia Run Timeline - ";
	public static String clCSVFileName = "Coccidia Log - ";
	public static String clCSVAuditFileName = "Coccidia Log Audit - ";
	public static String clSampleMetaData = "Sample Metadata Upload Template";
	
	public static void openCoccidiaLogPage() {
		BaseTest driver = new BaseTest();
		driver.getDriver().get(url_reports);
		Methods.waitElementInvisible(loading_cursor);
		Methods.click(coccidiaLogBox);
		Methods.waitElementInvisible(loading_cursor);
		Assert.assertEquals(Methods.getText(coccidiaLogTitle), "Coccidia Log"); 	
	}
	
}
