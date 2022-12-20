package PageObjects;

import org.openqa.selenium.By;

public class ProgramManagementPage {
	
public static By programManagementTitle = By.id("Program Management");
	
	public static By programCreateButton = By.id("create-program");
	public static By programName = By.id("programNameId");
	public static By programColumn1 = By.cssSelector("#col-0 label");
	public static By programTargetPathogen = By.cssSelector("#targetPathogenId input");
	public static By programTargetPathogenValue = By.cssSelector("#targetPathogenId .ng-value-label");
	public static By programProgramType = By.cssSelector("#programTypeId input");
	public static By programProgramTypeValue = By.cssSelector("#programTypeId .ng-value-label");
	public static By programProgramTypeDisabledCheck = By.cssSelector("#programTypeId.ng-select-disabled");
	public static By programSupplier = By.cssSelector("#supplierId input");
	public static By programDescription = By.id("descriptionId");
//	public static By programStartDate = By.id("startDate img");
	public static By programNoApplicationFlock = By.id("num-numOfApplicationId");
	public static String programDaysApplicationFlock = "num-flockDayApplicationId";
	public static By programStartDateIcon = By.cssSelector("#startDate img");
	public static By programComplexList = By.cssSelector("#compleSiteId .toggle-list");
	public static By programComplexSearch = By.id("compleSiteId_search");
	
	public static By programFeedTypeDropdown = By.cssSelector("#feedTypeId-1 input");
	public static By programFeedTypeValue = By.cssSelector("#feedTypeId-1 .ng-value-label");
	public static By programFlockDayStart  = By.id("num-flockDayStartId-1");
	public static By programFlockDayEnd  = By.id("num-flockDayEndId-1");
	
	public static By programComplexSelected = By.cssSelector("#compleSiteId.ng-valid");
	public static By programComplexMandatoryCheck = By.xpath("//*[@id='compleSiteId' and @ng-reflect-mandatory = 'true']");
	
	
	public static By programBioshuttleFlockDayStart = By.id("num-bioShuttleFlockDayStart");
	public static By programBioshuttleFlockDayStartError = By.cssSelector("#bioShuttleFlockDayStart-error-container .hide");
	public static By programBioshuttleFlockDayEnd = By.id("num-bioshuttleFlockDayEnd");
	public static By programBioshuttleFlockDayEndError = By.cssSelector("#bioShuttleFlockDayEnd-error-container .hide");
	
	public static By programAddFeedType = By.xpath("//*[text() = 'Add Feed Type']");
	public static By programFeedType2Dropdown = By.cssSelector("#feedTypeId-2 input");
	public static By programFlockDayStart2  = By.id("num-flockDayStartId-2");
	public static By programFlockDayEnd2  = By.id("num-flockDayEndId-2");
	public static By programFeedCategory = By.xpath("//*[text() = 'Feed Category']");
	
	public static By programFeedProgramTab = By.xpath("//*[text() = 'Feed Programs ']");
	public static By programTreatmentProgramTab = By.xpath("//*[text() = 'Treatment ']");
	public static By programVaccineProgramTab = By.xpath("//*[text() = 'Vaccine Programs ']");
	public static By programBioshuttleProgramTab = By.xpath("//*[text() = 'Bioshuttle ']");
	public static By programProgramUtilizationTab = By.xpath("//*[text() = 'Program Utilization ']");
	
//	public static String programEditVaccineButton = "edit-vaccine-program-";
//	public static String programEditFeedButton = "edit-feed-program-";
//	public static String programDeleteVaccineButton = "delete-vaccine-program-";
//	public static String programDeleteFeedButton = "delete-feed-program-";

	public static String programFeedTable = "feed-program-log";
	public static String programTreatmentTable = "treatment-log";
	public static String programVaccineTable = "vaccine-log";
	public static String programBioshuttleTable = "vaccine-bio-log"; 
	public static String programUtilizationTable = "program-util-log";
	
	public static String programFeed_ID = "feedprogram";
	public static String programTreatment_ID = "treatment";
	public static String programVaccine_ID = "vaccine";
	public static String programBioshuttle_ID = "vaccine-bio";
	
	public static String programFeedEdit = "edit-feed-program-";
	public static String programTreatmentEdit = "edit-treatment-program-";
	public static String programVaccineEdit = "edit-vaccine-program-";
	public static String programBioshuttleEdit = "edit-vaccine-bio-program-";
	
	public static String programFeedCopy = "copy-feed-program-";
	public static String programTreatmentCopy = "copy-treatment-program-";
	public static String programVaccineCopy = "0-";
	public static String programBioshuttleCopy = "copy-vaccine-bio-program-";
	
	public static String programFeedDelete = "delete-feed-program-";
	public static String programTreatmentDelete = "delete-treatment-program-";
	public static String programVaccineDelete = "delete-vaccine-program-";
	public static String programBioshuttleDelete = "delete-vaccine-bio-program-";
	
	public static String programVaccineProgramTab_XPATH = "//*[text() = 'Vaccine Programs ']";
	public static String programFeedProgramTab_XPATH = "//*[text() = 'Feed Programs ']";
	public static String programVaccine_ProgramName_FilterIcon_CSS = "#"+programVaccineTable+" #programName_show-filter";
	
	public static String programFeedProgramNameCol = "#col-0-feedprogram label";
	public static String programFeedSupplierNameCol = "#col-1-feedprogram label";
	public static String programFeedDescriptionCol = "#col-2-feedprogram label";
	public static String programFeedFeedTypesCol = "#col-3-feedprogram label";
	public static String programFeedStartDateCol = "#col-4-feedprogram label";
	public static String programFeedEndDateCol = "#col-5-feedprogram label";
	public static String programFeedComplexCol = "#col-6-feedprogram label";
	
	public static String programTreatmentProgramNameCol = "#col-0-treatment label";
	public static String programTreatmentSupplierNameCol = "#col-1-treatment label";
	public static String programTreatmentDescriptionCol = "#col-2-treatment label";
	public static String programTreatmentStartDateCol = "#col-3-treatment label";
	public static String programTreatmentEndDateCol = "#col-4-treatment label";
	public static String programTreatmentNameCol = "#col-5-treatment label";
	public static String programTreatmentFlockDayStartCol = "#col-6-treatment label";
	public static String programTreatmentFlockDayEndCol = "#col-7-treatment label";
	public static String programTreatmentRouteCol = "#col-8-treatment label";
	public static String programTreatmentTreatmentDescriptionCol = "#col-9-treatment label";
	public static String programTreatmentComplexCol = "#col-10-treatment label";
	
	public static String programVaccineProgramNameCol = "#col-0-vaccine label";
	public static String programVaccineSupplierNameCol = "#col-1-vaccine label";
	public static String programVaccineNumberOfApplicationFlockCol = "#col-2-vaccine label";
	public static String programVaccineDescriptionCol = "#col-3-vaccine label";
	public static String programVaccineStartDateCol = "#col-4-vaccine label";
	public static String programVaccineEndDateCol = "#col-5-vaccine label";
	public static String programVaccineFlockDayApplicationCol = "#col-6-vaccine label";

	public static String programBioshuttleProgramNameCol = "#col-0-vaccine-bio label";
	public static String programBioshuttleSupplierNameCol = "#col-1-vaccine-bio label";
	public static String programBioshuttleNumberOfApplicationFlockCol = "#col-2-vaccine-bio label";
	public static String programBioshuttleDescriptionCol = "#col-3-vaccine-bio label";
	public static String programBioshuttleStartDateCol = "#col-4-vaccine-bio label";
	public static String programBioshuttleEndDateCol = "#col-5-vaccine-bio label";
	public static String programBioshuttleFlockDayApplicationCol = "#col-6-vaccine-bio label";
	public static String programBioshuttleNameCol = "#col-7-vaccine-bio label";
	public static String programBioshuttleFlockDayStartCol = "#col-8-vaccine-bio label";
	public static String programBioshuttleFlockDayEndCol = "#col-9-vaccine-bio label";
	
	public static String programFeedCSVFileName = "Feed Program Log - ";
	public static String programTreatmentCSVFileName = "Treatment Program Log - ";
	public static String programVaccineCSVFileName = "Vaccine Log - ";
	public static String programBioshuttleCSVFileName = "Bioshuttle Log - ";
	public static String programUtilizationCSVFileName = "Program Utilization Log - ";
}
