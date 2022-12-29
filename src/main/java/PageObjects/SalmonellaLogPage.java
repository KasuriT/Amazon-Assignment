package PageObjects;

import org.openqa.selenium.By;
import org.testng.Assert;

import Config.BaseTest;

import static MiscFunctions.Constants.*;
import static MiscFunctions.Methods.*;
import static PageObjects.BasePage.loading_cursor;

public class SalmonellaLogPage {

	private static By salmonellaLogBox = By.cssSelector("img[alt='Salmonella Log']"); 
	private static By salmonellaLogTitle = By.id("Salmonella Log");

	public static String salmonellaLogTable = "salmonella-data-log";
	
	public static String slLaneCol = "0";
	public static String slSampleIDCol = "1";
	public static String slQCCodeCol = "2";
	public static String slResultStatusCol = "3";
	public static String slResultCol = "4";
	public static String slAssayCol = "5";
	public static String slResultIDCol = "6";
	public static String slDateCol = "7";
	public static String slTimeCol = "8";
	public static String slSiteNameCol = "9";
	public static String slSiteIDCol = "10";
	public static String slSiteTypeCol = "11";
	public static String slSampleMatrixCol = "12";
	public static String slDilutionFactorCol = "13";
	public static String slCSampleIDCol = "14";
	public static String slReceivedDateCol = "15";
	public static String slCartridgeIDCol = "16";
	public static String slInstrumentIDCol = "17";
	public static String slW1CellCountCol = "18";
	public static String slW1PCCountCol = "19";
	public static String slW1MeanIntensityCol = "20";
	public static String slW2CellCountCol = "21";
	public static String slW2CPCCountCol = "22";
	public static String slW2MeanIntensityCol = "23";
	public static String slPiperUserCol = "24";
	public static String slKitLotCol = "25";
	public static String slImprocIDCol = "26";
	public static String slTestSiteIDCol = "27";
	public static String slTestSiteNameCol = "28";
	public static String slRequestedAssayCol = "29";
	public static String slFlockIDCol = "30";
	public static String slRunTypeCol = "31";
	public static String slCollectionDateCol = "32";
	public static String slFarmCol = "33";
	public static String slComplexCol = "34";
	public static String slFieldAccessCol = "35";
	public static String footerCount = ".filter-popup__footer--count";
	
	public static String slAuditLaneColCss = "5";
	public static String slAuditLaneCol = "0";
	public static String slAuditSampleIDCol = "1";
	public static String slAuditQCCodeCol = "2";
	public static String slAuditResultStatusCol = "3";
	public static String slAuditResultCol = "4";
	public static String slAuditAssayCol = "5";
	public static String slAuditDateCol = "6";
	public static String slAuditTimeCol = "7";
	public static String slAuditSiteNameCol = "8";
	public static String slAuditSiteIDCol = "9";
	public static String slAuditSiteTypeCol = "10";
	public static String slAuditSampleMatrixCol = "11";
	public static String slAuditDilutionFactorCol = "12";
	public static String slAuditCSampleIDCol = "13";
	public static String slAuditReceivedDateCol = "14";
	public static String slAuditCartridgeIDCol = "15";
	public static String slAuditInstrumentIDCol = "16";
	public static String slAuditW1CellCountCol = "17";
	public static String slAuditW1PCCountCol = "18";
	public static String slAuditW1MeanIntensityCol = "19";
	public static String slAuditW2CellCountCol = "20";
	public static String slAuditW2CPCCountCol = "21";
	public static String slAuditW2MeanIntensityCol = "22";
	public static String slAuditPiperUserCol = "23";
	public static String slAuditKitLotCol = "24";
	public static String slAuditImprocIDCol = "25";
	public static String slAuditTestSiteIDCol = "26";
	public static String slAuditTestSiteNameCol = "27";
	public static String slAuditRequestedAssayCol = "28";
	public static String slAuditFlockIDCol = "29";
	public static String slAuditRunTypeCol = "30";
	public static String slAuditCollectionDateCol = "31";
	public static String slAuditFarmCol = "32";
	public static String slAuditComplexCol = "33";
	
	public static String slToday = "#list-title_range-0";
	public static String slLast24Hours = "#list-title_range-1";
	public static String slLast7Days = "#list-title_range-2";
	public static String slLast30Days = "#list-title_range-3";
	public static String slThisMonth = "#list-title_range-4";
	public static String slLastMonth = "#list-title_range-5";	
					
	public static String slShowFilter = "_show-filter";
	public static String slSortFilter = "sort-";
	public static String slApplyFilter = "_apply";
	public static String slClearFilter = "_clear-filter";
	public static String slSearchInput = "_search-input";
	
	public static String slLane = "laneNum";
	public static String slSampleID = "sampleId";
	public static String slQCCode = "countOutcome";
	public static String slResultStatus = "result_status";
	public static String slResult = "outcome";
	public static String slAssay = "pathogen";
	public static String slResultID = "runId";
	public static String slResultTime = "time";
	public static String slResultDate = "scanDateTime";
	public static String slCollectionSiteID = "collection_site_id";
	public static String slCollectionSiteName = "site_id";
	public static String slCollectionSiteType = "collection_site_type"; 
	public static String slSampleMatrix = "sample_matrix";
	public static String slDilutionFactor = "dilution_factor";
	public static String slCustomerSampleID = "customer_sample_id";
	public static String slDateReceived = "metadata_date_recieved";
	public static String slCartridgeID = "cartridgeId";
	public static String slInstrumentID = "instrumentId";
	public static String slW1CellCount = "w1CellCount";
	public static String slW1PCCount = "w1PCCount";
	public static String slW1MeanIntensity = "w1CellMeanIntensity";
	public static String slW2CellCount = "w2CellCount";
	public static String slW2PCCount = "w2PCCount";
	public static String slW2MeanIntensity = "w2CellMeanIntensity";
	public static String slPiperUser = "user_name";
	public static String slkitLot = "kit_lot";
	public static String slImprocVersion = "version";
	public static String slTestSiteID = "testSiteId";
	public static String slTestSiteName = "testSiteName";
	public static String slRequestedAssay = "requested_assay";
	public static String slFlockID = "flock_id";
	public static String slRunType = "runType";
	public static String slCollectionDate = "collectionDate";
	public static String slFarm = "farm";
	public static String slComplex = "complex";
	
	public static String slLoadRow = "/html/body/app-root/div/app-salmonella-log/div[1]/div/div[2]/div[2]/div[5]/div/table/tbody/tr[1]/td[1]";
	public static String slLoadRed = ".red";  
	public static String slLoadYellow = ".yellow"; 
	public static String slLoadGreen = ".green";

	public static String slTotalPages = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[15]/p";

	public static String closeAudit = "#close-gen-modal";
	
	public static String slTable = "salmonella-data-log";
	
	public static String slPNGFileName = "Salmonella Run Timeline - ";
	public static String slCSVFileName = "Salmonella Log - ";
	public static String slCSVAuditFileName = "Salmonella Audit Log - ";
	public static String slSampleMetaData = "Sample Metadata Upload Template";

	
	public static void openSalmonellaLogPage() {
		BaseTest driver = new BaseTest();
		driver.getDriver().get(url_reports);
		waitElementInvisible(loading_cursor);
		click(salmonellaLogBox);
		waitElementInvisible(loading_cursor);
		Assert.assertEquals(getText(salmonellaLogTitle), "Salmonella Log"); 	
	}
	
	
}
