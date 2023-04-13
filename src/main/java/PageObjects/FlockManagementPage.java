package PageObjects;

import static PageObjects.BasePage.loading_cursor;
import java.io.IOException;
import org.openqa.selenium.By;
import static MiscFunctions.Methods.*;
import Models.FlockManagementModel;

public class FlockManagementPage {

public static By FlockManagementTitle = By.id("Flock Management");
	
	public static String flockPlacementTable = "flock-placement-log";
	public static String flockMortalityTable = "flock-mortality-log";
	public static String flockSettlementTable = "flock-settlement-log";
	public static String flockCondemnationTable = "flock-condemnation-log";
	
	public static By flockPlacementTab = By.xpath("//*[text() = 'Placement ']");
	public static By flockMortalityTab = By.xpath("//*[text() = 'Mortality ']");
	public static By flockSettlementTab = By.xpath("//*[text() = 'Settlement ']");
	public static By flockCondemnationTab = By.xpath("//*[text() = 'Condemnation ']");
		
	public static By flockCreateButton = By.id("create-flock");
	public static By flockAddNewFlockButton = By.id("add-flock");
	
	public static By flockFarmMandatoryCheck = By.xpath("//*[@ng-reflect-id = 'farmSiteId-0' and @ng-reflect-mandatory = 'true']");	
	
	public static By flockFarmDropdownExpand = By.cssSelector("[formcontrolname='farmId'] .down ");
	public static By flockFarmDropdownSearch = By.cssSelector("[formcontrolname='farmId'] input");
	public static By flockFarmDropdownGetAllSites = By.cssSelector("[formcontrolname='farmId'] tr");
	public static By flockIntegratorFlockID = By.cssSelector("#integratorFlockId-0");
	public static By flockIntegratorFlockAddNew = By.xpath("//*[text() = 'Add New + ']");
	public static By flockBirdSizeInput = By.cssSelector("#birdSizeId-0 input");
	public static By flockBirdSizeDropDownOptions = By.cssSelector("#birdSizeId-0 .ng-option");
	public static By flockBirdSexInput = By.cssSelector("#birdSexId-0 input");
	public static By flockBirdSexDropDownOptions = By.cssSelector("#birdSexId-0 .ng-option");
	public static By flockMarketingProgramInput = By.cssSelector("#marketingProgramId-0 input");
	public static By flockMarketingProgramDropDownOptions = By.cssSelector("#marketingProgramId-0 .ng-option");
	
	public static By flockDoseInput = By.id("numBirdsPlacedId");
	public static By flockProgramDeleteButton = By.cssSelector("[title='Delete program details']");

	public static By flockPlacementDensityInput = By.id("num-placementDensity");
	public static By flockWeeklyFarmRank = By.id("num-weeklyFarmRank");
	public static By flockHistoricalFarmCostVariance = By.id("num-historicalFarmCostVariance");
	public static By flockWeeklyFarmCostVariance = By.id("num-weeklyFarmCostVariance");
	public static By flockNumBirdsDOAPlant = By.id("num-numBirdsDOAPlant");
		
	public static By flockPlacementDateCalendar = By.xpath("(//*[@class = 'apl-datepicker__calender-icon'])[1]");
	public static By flockEstHarvestDateCalendar = By.xpath("(//*[@class = 'apl-datepicker__calender-icon'])[2]");
	public static By flockHousePlacedDropdownExpand = By.cssSelector("#housePlaced-0 .ng-arrow-wrapper");
	public static By flockHousePlacedList = By.cssSelector("#housePlaced-0 .ng-option");
	public static By flockHousePlacedInput = By.cssSelector("#housePlaced-0 .ng-input input");
	public static By flockAddNewProgram = By.xpath("//*[text() = 'Add New Program']");
	public static By flockAddNewProgramTypeInput = By.cssSelector("#programTypeId-0-0 input");
	public static By flockAddNewProgramNameInput = By.cssSelector("#programId-0-0 input");
	public static By flockCMSAdminInput = By.cssSelector("input#administrationMethodId");
	
	/////////////////Placement//////////////////
	public static String flockIDPlacementCol = "0";
	public static String flockIntegratorIDPlacementCol = "1";
	public static String flockBirdSizePlacementCol = "2";
	public static String flockBirdSexPlacementCol = "3";
	public static String flockMarketingProgramPlacementCol = "4";
	public static String flockBirdBreedPlacementCol = "5";
	public static String flockNumofBirdsPlacedPlacementCol = "7";
	public static String flockProgramDetailsPlacementCol = "8";
	public static String flockProgramHousePlacementCol = "9";
	public static String flockComplexPlacementCol = "10";
	public static String flockFarmPlacementCol = "11";
	public static String flockIntegratorPlacementCol = "12";
	public static String flockFarmSiteIDPlacementCol = "13";
	///////////////End Placement//////////////////
	
	//////////Common Columns in all Tabs///////////
	public static String flockFlockIDCol = "0";
	public static String flockIntegratorFlockIDCol = "1";
	public static String flockIntegratorCol = "2";
	public static String flockComplexCol = "3";
	public static String flockFarmCol = "4";
	public static String flockFarmSiteIDCol = "5";
	public static String flockPlacementDateCol = "6";
	///////End Common Columns in all Tabs///////////

	/////////////////Mortality//////////////////
	public static String flockMortalityCol = "7";
	////////////////End Mortality////////////////
	
	/////////////////Settlement//////////////////
	public static String flockWeeklyFarmRankCol = "7";
	public static String flockHistoricalFarmCostVarianceCol = "8";
	public static String flockWeeklyFarmCostVarianceCol = "9";
	public static String flockHatchDateCol = "10";
	public static String flockDaysOutCol = "11";
	public static String flockAgeOfLiterCol = "12";
	public static String flockAverageSoldAgeCol = "13";
	public static String flockNumBirdsSoldCol = "14";
	public static String flockPlacementDensityCol = "15";
	public static String flockProcessingDateCol = "16";
	public static String flockProcessingSiteIDCol = "17";
	public static String flockUSDAPlantIDCol = "18";
	public static String flockPlantLocationCol = "19";
	public static String flockNumOfBirdsProcessedCol = "20";
	public static String flockAvgBirdWeightLBCol = "21";
	public static String flockAvgBirdWeightKGCol = "22";
	public static String flockTotalWeightProcessedLBCol = "23";
	public static String flockTotalWeightProcessedKGCol = "24";
	public static String flockTotalFeedWeightLBCol = "25";
	public static String flockTotalFeedWeightKGCol = "26";
	public static String flockFCRCol = "27";
	public static String flockAdjustedFCRCol = "28";
	public static String flockFeedCostPerLivePoundCol = "29";
	public static String flockMedicationCostPerLivePoundCol = "30";
	public static String flockGrowerCostPerLivePoundCol = "31";
	public static String flockLivabilityPercentageCol = "32";
	public static String flockOverallMortalityPercentageCol = "33";
	/////////////////End Settlement//////////////////
	
	/////////////////Condemnation//////////////////
	public static String flockNumBirdsDOAPlantCol = "7";
	public static String flockTotalWeightCondemnedLBCol = "8";
	public static String flockTotalWeightCondemnedKGCol = "9";
	public static String flockNumBirdsCondemnedWholeCol = "10";
	public static String flockPartsWeightCondemnedLBCol = "11";
	public static String flockPartsWeightCondemnedKGCol = "12";
	public static String flockKCalLBCol = "13";
	public static String flockGradeAPawsPercentageCol = "14";
	public static String flockIPPercentageCol = "15";
	public static String flockLeukosisPercentageCol = "16";
	public static String flockSeptoxPercentageCol = "17";
	public static String flockTumorPercentageCol = "18";
	///////////////End Condemnation////////////////

	public static String flockInlineButton = "edit-inline-access";
	public static String flockInlineButtonTooltip = ("span[title  =' In-line Edit']");
	
	public static String flockInlineButtonSave = "edit-inlineSave-access";
	public static By flockBirdBreed = By.cssSelector("#birdBreedId-0-5 input");
	
	
	public static By flockInlineNewProgramIcon = By.cssSelector("tr:nth-child(1) #col-"+flockProgramDetailsPlacementCol+" label span");
	public static By flockInlineAddNewProgramPopup = By.id("add-program");
	public static By flockInlineProgramName = By.cssSelector("#programId input");
	public static By flockAdministrationMethod = By.cssSelector("#administrationMethodId input");
	public static By flockProgramSaveButton = By.id("btn-save-program");
	
	public static By flockInlineMortality1Input = By.cssSelector("tr:nth-child(1) #col-"+flockMortalityCol+" input");
	
	public static By flockInlineSettlementWeeklyFarmRank = By.cssSelector("tr:nth-child(1) #col-"+flockWeeklyFarmRankCol+" input");
	public static By flockInlineSettlementHistoricalFarmCostVariance = By.cssSelector("tr:nth-child(1) #col-"+flockHistoricalFarmCostVarianceCol+" input");
	public static By flockInlineSettlementWeeklyFarmCostVariance = By.cssSelector("tr:nth-child(1) #col-"+flockWeeklyFarmCostVarianceCol+" input");
	public static By flockInlineSettlementhatchDate = By.cssSelector("tr:nth-child(1) #col-"+flockHatchDateCol+" input");
	public static By flockInlineSettlementDaysOut = By.cssSelector("tr:nth-child(1) #col-"+flockDaysOutCol+" input");
	public static By flockInlineSettlementAgeofLitter = By.cssSelector("tr:nth-child(1) #col-"+flockAgeOfLiterCol+" input");
	public static By flockInlineSettlementAverageSoldAge = By.cssSelector("tr:nth-child(1) #col-"+flockAverageSoldAgeCol+" input");
	public static By flockInlineSettlementNumBirdsSold = By.cssSelector("tr:nth-child(1) #col-"+flockNumBirdsSoldCol+" input");
	public static By flockInlineSettlementPlacementDensity = By.cssSelector("tr:nth-child(1) #col-"+flockPlacementDensityCol+" input");
	public static By flockInlineSettlementProcessingDate = By.cssSelector("tr:nth-child(1) #col-"+flockProcessingDateCol+" input");
	public static By flockInlineSettlementProcessingSiteID = By.cssSelector("tr:nth-child(1) #col-"+flockProcessingSiteIDCol+" input");
	public static By flockInlineSettlementUSDAPlantID = By.cssSelector("tr:nth-child(1) #col-"+flockUSDAPlantIDCol+" input");
	public static By flockInlineSettlementPlantLocation = By.cssSelector("tr:nth-child(1) #col-"+flockPlantLocationCol+" input");
	public static By flockInlineSettlementNumOfBridsProcessed = By.cssSelector("tr:nth-child(1) #col-"+flockNumOfBirdsProcessedCol+" input");
	public static By flockInlineSettlementAvgBirdWeightLB = By.cssSelector("tr:nth-child(1) #col-"+flockAvgBirdWeightLBCol+" input");
	public static By flockInlineSettlementAvgBirdWeightKG = By.cssSelector("tr:nth-child(1) #col-"+flockAvgBirdWeightKGCol+" input");
	public static By flockInlineSettlementTotalWeightProcessedLB = By.cssSelector("tr:nth-child(1) #col-"+flockTotalWeightProcessedLBCol+" input");
	public static By flockInlineSettlementTotalWeightProcessedKG = By.cssSelector("tr:nth-child(1) #col-"+flockTotalWeightProcessedKGCol+" input");
	public static By flockInlineSettlementTotalFeedWeightLB = By.cssSelector("tr:nth-child(1) #col-"+flockTotalFeedWeightLBCol+" input");
	public static By flockInlineSettlementTotalFeedWeightKG = By.cssSelector("tr:nth-child(1) #col-"+flockTotalFeedWeightKGCol+" input");
	public static By flockInlineSettlementFCR = By.cssSelector("tr:nth-child(1) #col-"+flockFCRCol+" input");
	public static By flockInlineSettlementAdjustedFCR = By.cssSelector("tr:nth-child(1) #col-"+flockAdjustedFCRCol+" input");
	public static By flockInlineSettlementFeedCostPerLivePound = By.cssSelector("tr:nth-child(1) #col-"+flockFeedCostPerLivePoundCol+" input");
	public static By flockInlineSettlementMedicationCostPerLivePound = By.cssSelector("tr:nth-child(1) #col-"+flockMedicationCostPerLivePoundCol+" input");
	public static By flockInlineSettlementGrowerCostPerLivePound = By.cssSelector("tr:nth-child(1) #col-"+flockGrowerCostPerLivePoundCol+" input");
	public static By flockInlineSettlementLivabilityPercentage = By.cssSelector("tr:nth-child(1) #col-"+flockLivabilityPercentageCol+" input");
	public static By flockInlineSettlementOverallMortalityPercentage = By.cssSelector("tr:nth-child(1) #col-"+flockOverallMortalityPercentageCol+" input");
	
	public static By flockInlineNumBirdsDOAPlantCondemnation = By.cssSelector("tr:nth-child(1) #col-"+flockNumBirdsDOAPlantCol+" input");
	public static By flockInlineTotalWeightCondemnedLBCondemnation = By.cssSelector("tr:nth-child(1) #col-"+flockTotalWeightCondemnedLBCol+" input");
	public static By flockInlineTotalWeightCondemnedKGCondemnation = By.cssSelector("tr:nth-child(1) #col-"+flockTotalWeightCondemnedKGCol+" input");
	public static By flockInlineNumBirdsCondemnedWholeCondemnation = By.cssSelector("tr:nth-child(1) #col-"+flockNumBirdsCondemnedWholeCol+" input");
	public static By flockInlinePartsWeightCondemnedLBCondemnation = By.cssSelector("tr:nth-child(1) #col-"+flockPartsWeightCondemnedLBCol+" input");
	public static By flockInlinePartsWeightCondemnedKGCondemnation = By.cssSelector("tr:nth-child(1) #col-"+flockPartsWeightCondemnedKGCol+" input");
	public static By flockInlineKCalLBCondemnation = By.cssSelector("tr:nth-child(1) #col-"+flockKCalLBCol+" input");
	public static By flockInlineGradeAPawsPercentageCondemnation = By.cssSelector("tr:nth-child(1) #col-"+flockGradeAPawsPercentageCol+" input");
	public static By flockInlineIPPercentageCondemnation = By.cssSelector("tr:nth-child(1) #col-"+flockIPPercentageCol+" input");
	public static By flockInlineLeukosisPercentageCondemnation = By.cssSelector("tr:nth-child(1) #col-"+flockLeukosisPercentageCol+" input");
	public static By flockInlineSeptoxPercentageCondemnation = By.cssSelector("tr:nth-child(1) #col-"+flockSeptoxPercentageCol+" input");
	public static By flockInlineTumorPercentageCondemnation = By.cssSelector("tr:nth-child(1) #col-"+flockTumorPercentageCol+" input");
	
	public static By flockAuditTrail = By.id("audit-trial-0");
	public static By flockAuditRowCount = By.cssSelector(".audit-v2 tr");
	public static By flockEditMortalityTab = By.cssSelector("#flockManage li:nth-child(2)");
	public static By flockEditSettlementTab = By.cssSelector("#flockManage li:nth-child(3)");
	public static By flockEditCondemnationTab = By.cssSelector("#flockManage li:nth-child(4)");

	public static String placementAuditFlockIDCol = "0";
	public static String placementChangedDateCol = "audit-changed-date-0";
	public static String placementAuditActionCol = "audit-action-0";
	public static String placementAuditChangedDateCol = "audit-changed-by-0";
	public static String placementAuditIntegratorFlockIDCol = "0";
	public static String placementAuditBirdSizeCol = "1";
	public static String placementAuditBirdSexCol = "2";
	public static String placementAuditMarketingProgramCol = "3";
	public static String placementAuditBirdBreedCol = "4";
	public static String placementAuditNoOFBirdsPlacedCol = "5";
	public static String placementAuditProgramDetailsCol = "6";
	public static String placementAuditHousePlacementCol = "7";
	public static String placementAuditComplexCol = "8";
	public static String placementAuditFarmCol = "9";
	public static String placementAuditPlacementDateCol = "10";
	public static String placementAuditIntegratorCol = "11";
	public static String placementAuditFarmSiteIDCol = "12";
	public static String placementAuditEstimatedHarvestDateCol = "13";
	
	
	public static String mortalityCol = "";
	
	public static By flockProgramSaveDisabledCheck = By.cssSelector("#btn-save.disabled-v2");

	public static String flockShowFilter = "_show-filter";
	public static String flockSearchFilter = "_search-input";
	public static String flockSortFilter = "sort-";
	public static String flockApplyFilter = "_apply";
	public static String flockClearFilter = "_clear-filter";

	public static String flockIntegratorID = "integratorFlockId";
	public static String flockSiteID = "siteId";
	public static String flockHatchDate = "hatchDate";
	public static String flockPlacementDate = "placementDate";
	public static String flockNumBirdsPlaced = "numOfBirdsPlaced";
	public static String flockBirdType = "birdType";
	public static String flockBirdSex = "birdSex";
	//public static String flockBirdBreed = "birdBreed";
	public static String flockProcessingDate = "processingDate";
	public static String flockProcessingSiteID = "processingSiteId";
	public static String flockNumBirdsDOA = "numBirdsDoaPlant";
	public static String flockNumBirdsProcessed = "numBirdsProcessed";
	public static String flockTotalWeightProcessedLB = "totalWeightProcessedLB";
	public static String flockTotalWeightProcessedKG = "totalWeightProcessedKG";
	public static String flockTotalFeedWeightLB = "totalFeedWeightLB";
	public static String flockTotalFeedWeightKG = "totalFeedWeightKG";
	public static String flockTotalWeightCondemendLB  = "totalWeightCondemnedLB";
	public static String flockTotalWeightCondemendKG  = "totalWeightCondemnedKG";
	public static String flockNumBirdsCondemnedWhole = "numBirdsCondemnedWhole";
	public static String flockBirdsWeightCondemnedLB = "birdWeightCondemnedLB";
	public static String flockBirdsWeightCondemnedKG = "birdWeightCondemnedKG";
	public static String flockPartsWeightCondemnedLB = "partsWeightCondemnedLB";
	public static String flockPartsWeightCondemnedKG = "partsWeightCondemnedKG";
	public static String flockTotalGrowerPay = "totalAmountPaidUSD";
	public static String flockAmountPaidPerPound = "totalAmountPaidUSDWeightLB";
	public static String flockTotalCostUSDPerWeight = "totalCostUSDPerWeightLB";
	public static String flockLiveability = "livabilityPerc";
	public static String flockMortality = "mortalityPerc";
	public static String flockAvgDailyWeightGainLB = "avgDailyWeightGainLB";
	public static String flockAvgAgeBirdsProcessed = "avgAgeOfBirdProcessedDays";
	public static String flockAvgBirdWeightLB = "avgBirdWeightLB";
	public static String flockAvgBirdWeightKG = "avgBirdWeightKG";
	public static String flockFCR = "fcr";
	public static String flockNumBirdsSold = "numBirdsSold";
	public static String flockAdjFCR = "adjFcr";
	public static String flockAGradePaws = "aGradePawsPerc";
	public static String flockOutTimeDays = "outTimeDays";
	public static String flockBirdSize = "birdSize";
	public static String flockMarketingProgram = "marketingProgram";
	public static String flockUSDAPlantID = "usdaPlantId";
	public static String flockDowngradePawsPerc = "downgradePawsPerc";
	public static String flockTotalMortality = "totalMortality";
	public static String flockTimeFrame = "timeFrame";
	public static String flockUniqueFlockID = "uniqueFlockId";

	public static String flockPlacementCSVFileName = "Placement Log - ";
	public static String flockEOFTemplateFileName = "End of Flock Template_";
	public static String flockPlacementAuditFileName = "Placement Audit Log - ";
	
	public static String flockCSVFileName = "Flock Registration Log - ";
	
	
	
    public static void openFlockAudit() throws InterruptedException, IOException {
        click(flockMortalityTab);
        
        for(int i=1; i<size(By.cssSelector("tr")); i++) {     
            if (getText(By.cssSelector("tr:nth-child("+i+") #col-"+flockIntegratorFlockIDCol+" label")).equals(FlockManagementModel.flockIntegratorID)) {
                int j = i-1;
                click(By.id("audit-trial-"+j));
                waitElementInvisible(loading_cursor);    
                Thread.sleep(1500);    
                break;
            }}}
	
    
    public static void openEditFlock() throws InterruptedException, IOException {
        click(flockMortalityTab);
        for(int i=1; i<size(By.cssSelector("tr")); i++) {
            if (getText(By.cssSelector("tr:nth-child("+i+") #col-"+flockIntegratorCol+" label")).equals(FlockManagementModel.flockIntegratorID)) {
                click(By.id("edit-feed-program-"+i));
                waitElementInvisible(loading_cursor);    
                Thread.sleep(1500);    
            }
        }}
	
	
}
