package PageObjects;

import Config.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ProgramManagementPage extends BaseTest {

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

	public static By programIngredient = By.xpath("//input[@id='0-0-ingredientId']");
	public static By programIngredient2 = By.xpath("//input[@id='0-1-ingredientId']");

	public static By deleteIngredientIcon = By.xpath("//img[@src='../../../../../assets/images/components-icons/icon-trash-white.png']");

	public static By programDescription = By.id("descriptionId");
	public static By programTreatmentDescription = By.id("treatmentDescriptionId");
	//	public static By programStartDate = By.id("startDate img");
	public static By programNoApplicationFlock = By.id("num-numOfApplicationId");
	public static String programDaysApplicationFlock = "num-flockDayApplicationId";
	public static By programStartDateIcon = By.cssSelector("#startDate img");
	public static By programComplexList = By.cssSelector("#compleSiteId .toggle-list");
	public static By programComplexSearch = By.id("compleSiteId_search");

	public static By programFeedTypeDropdown = By.cssSelector("#feedTypeId-1 input");

	public static By ingredientFeedTypeDropdown = By.xpath("//img[@src='assets/images/list.svg']");


	public static By programFeedTypeValue = By.cssSelector("#feedTypeId-1 .ng-value-label");
	public static By programFlockDayStart = By.id("num-flockDayStartId-1");
	public static By programFlockDayEnd = By.id("num-flockDayEndId-1");

	public static By programComplexSelected = By.cssSelector("#compleSiteId.ng-valid");
	public static By programComplexMandatoryCheck = By.xpath("//*[@id='compleSiteId' and @ng-reflect-mandatory = 'true']");


	public static By programBioshuttleFlockDayStart = By.id("num-flockDayStartId-1");
	public static By programBioshuttleFlockDayStartError = By.cssSelector("#flockDayStartId-1-error-container .hide");
	public static By programBioshuttleFlockDayEnd = By.id("num-flockDayEndId-1");
	public static By programBioshuttleFlockDayEndError = By.cssSelector("#flockDayEndId-1-error-container .hide");

	public static By programAddFeedType = By.xpath("//*[text() = 'Add Feed Type']");
	public static By programFeedType2Dropdown = By.cssSelector("#feedTypeId-2 input");
	public static By programFlockDayStart2 = By.id("num-flockDayStartId-2");
	public static By programFlockDayEnd2 = By.id("num-flockDayEndId-2");
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
	public static String programVaccine_ProgramName_FilterIcon_CSS = "#" + programVaccineTable + " #programName_show-filter";

	public static String programFeedProgramNameCol = "#col-0-feedprogram label";
	public static String programFeedSupplierNameCol = "#col-1-feedprogram label";
	public static String programFeedDescriptionCol = "#col-2-feedprogram label";
	public static String programFeedFeedTypesCol = "#col-3-feedprogram label";
	public static String programFeedStartDateCol = "#col-4-feedprogram label";
	public static String programFeedEndDateCol = "#col-5-feedprogram label";
	public static String programFeedComplexCol = "#col-6-feedprogram label";
	public static String programFeedTargetPathogenCol = "#col-7-feedprogram label";

	public static String programTreatmentProgramNameCol = "#col-0-treatment label";
	public static String programTreatmentSupplierNameCol = "#col-1-treatment label";
	public static String programTreatmentDescriptionCol = "#col-2-treatment label";
	public static String programTreatmentStartDateCol = "#col-9-treatment label";
	public static String programTreatmentEndDateCol = "#col-10-treatment label";
	public static String programTreatmentComplexCol = "#col-11-treatment label";

	public static String programVaccineProgramNameCol = "#col-0-vaccine label";
	public static String programVaccineSupplierNameCol = "#col-1-vaccine label";
	public static String programVaccineNumberOfApplicationFlockCol = "#col-2-vaccine label";
	public static String programVaccineDescriptionCol = "#col-3-vaccine label";
	public static String programVaccineStartDateCol = "#col-4-vaccine label";
	public static String programVaccineEndDateCol = "#col-5-vaccine label";
	public static String programVaccineFlockDayApplicationCol = "#col-6-vaccine label";
	public static String programVaccineComplexCol = "#col-7-vaccine label";
	public static String programVaccineTargetPathogenCol = "#col-8-vaccine label";

	public static String programBioshuttleProgramNameCol = "#col-0-vaccine-bio label";
	public static String programBioshuttleProgramTypeCol = "#col-1-vaccine-bio label";
	public static String programBioshuttleDescriptionCol = "#col-2-vaccine-bio label";
	public static String programBioshuttleTargetPathogenCol = "#col-3-vaccine-bio label";
	public static String programBioshuttleComplexCol = "#col-4-vaccine-bio label";
	public static String programBioshuttleStartDateCol = "#col-5-vaccine-bio label";
	public static String programBioshuttleEndDateCol = "#col-6-vaccine-bio label";
	public static String programBioshuttleVaccineNameCol = "#col-7-vaccine-bio label";
	public static String programBioshuttleVaccineSupplierCol = "#col-8-vaccine-bio label";
	public static String programBioshuttleNumberOfApplicationOnFlockCol = "#col-9-vaccine-bio label";
	public static String programBioshuttleFlockDayApplicationCol = "#col-10-vaccine-bio label";
	public static String programBioshuttleFeedProgramNameCol = "#col-11-vaccine-bio label";
	public static String programBioshuttleFeedProgramSupplierCol = "#col-12-vaccine-bio label";
	public static String programBioshuttleFeedTypesCol = "#col-13-vaccine-bio label";
	public static String programBioshuttleFeedTypeCategoriesCol = "#col-14-vaccine-bio label";
	public WebElement feedNameField;
	@FindBy(how = How.ID, using = "fcBioshuttleFeedName")


	public static String programFeedCSVFileName = "Feed Program Log - ";
	public static String programTreatmentCSVFileName = "Treatment Log - ";
	public static String programVaccineCSVFileName = "Vaccine Log - ";
	public static String programBioshuttleCSVFileName = "Bioshuttle Log - ";
	public static String programUtilizationCSVFileName = "Program Utilization Log - ";


	//***INLINE EDIT FUNCTIONALITY LOCATORS**

	public static By inlineEditButton = By.id("edit-inline-access");

	public static String programFeedProgramTable = "manage-feed-program-log";

	public static String feedProgramsTargetPathogenColumn = "3";
	public static String feedProgramsDescriptionColumnIndex = "2";
	public static String feedProgramsComplexColumnIndex = "7";

	public static String feedProgramsSupplierNameColumnIndex = "1";
	public static String feedProgramsProgramNameColumnIndex = "0";
	public static String feedProgramsStartDateColumnIndex = "5";

	public static String feedProgramsEndDateColumnIndex = "6";


	public static By feedProgramDescriptionColumn = By.cssSelector("tr:nth-child(1) #col-" + feedProgramsDescriptionColumnIndex + " input");
	public static By feedProgramSupplierNameColumn = By.cssSelector("tr:nth-child(1) #col-" + feedProgramsSupplierNameColumnIndex + " input");
	public static By feedProgramsComplexColumn = By.cssSelector("tr:nth-child(1) #col-" + feedProgramsComplexColumnIndex + " input");
	public static By feedProgramsProgramNameColumn = By.cssSelector("tr:nth-child(1) #col-" + feedProgramsProgramNameColumnIndex + " input");

	public static By feedProgramsStartDateColumn = By.cssSelector("tr:nth-child(1) #col-" + feedProgramsStartDateColumnIndex + " input");
	public static By feedProgramsEndDateColumn = By.cssSelector("tr:nth-child(1) #col-" + feedProgramsEndDateColumnIndex + " input");
	public static String vaccineProgramsTable = "vaccine-log";
	public static String vaccineProgramsTargetPathogenCol = "4";
	public static By vaccineProgramsTargetPathogenField = By.cssSelector("tr:nth-child(1) #col-" + vaccineProgramsTargetPathogenCol + " input");

	public static String vaccineProgramsProgramNameCol = "0";
	public static By vaccineProgramsProgramNameField = By.cssSelector("tr:nth-child(1) #col-" + vaccineProgramsProgramNameCol + " input");
	public static String vaccineProgramsSupplierNameCol = "1";
	public static By vaccineProgramsSupplierNameField = By.cssSelector("tr:nth-child(1) #col-" + vaccineProgramsSupplierNameCol + " input");
	public static String vaccineProgramsApplicationsOnFlockCol = "2";
	public static By vaccineProgramsApplicationsOnFlockField = By.cssSelector("tr:nth-child(1) #col-2");
	public static String vaccineProgramsDescriptionCol = "3";
	public static By vaccineProgramsDescriptionField = By.cssSelector("tr:nth-child(1) #col-" + vaccineProgramsDescriptionCol + " input");
	public static String vaccineProgramsFlockDayApplicationsCol = "7";
	public static By vaccineProgramsFlockDayApplicationsField = By.cssSelector("tr:nth-child(1) #col-" + vaccineProgramsFlockDayApplicationsCol + " input");
	public static String vaccineProgramsStartDateCol = "5";
	public static By vaccineProgramsStartDateField = By.cssSelector("tr:nth-child(1) #col-" + vaccineProgramsStartDateCol + " input");
	public static String vaccineProgramsEndDateCol = "6";
	public static By vaccineProgramsEndDateField = By.cssSelector("tr:nth-child(1) #col-" + vaccineProgramsEndDateCol + " input");

	public static By vaccineTabApplicationsOnFlockPopup = By.xpath("//div[contains(text(),'Edit Program Application')]");
	public static By vaccineTabApplicationsOnFlockField = By.id("num-numOfApplicationId");
	public static By vaccineTabFlockDayApplicationField1 = By.id("num-flockDayApplicationId-1");
	public static By vaccineTabFlockDayApplicationField2 = By.id("num-flockDayApplicationId-2");

	//Bioshuttle tab locators
	public static String bioshuttleTabTable = "manage-vaccine-bio-log";

	public static String bioshuttleProgramNameCol = "0";
	public static By bioshuttleProgramNameField = By.cssSelector("tr:nth-child(1) #col-" + bioshuttleProgramNameCol + " input");

	public static String bioshuttleProgramTypeCol = "1";
	public static By bioshuttleProgramTypeField = By.cssSelector("tr:nth-child(1) #col-" + bioshuttleProgramTypeCol + " input");
	public static String bioshuttleDescriptionCol = "2";
	public static By bioshuttleDescriptionField = By.cssSelector("tr:nth-child(1) #col-" + bioshuttleDescriptionCol + " input");
	public static String bioshuttleTargetPathogenCol = "3";
	public static By bioshuttleTargetPathogenField = By.cssSelector("tr:nth-child(1) #col-" + bioshuttleTargetPathogenCol + " input");
	public static String bioshuttleComplexCol = "4";
	public static By bioshuttleComplexField = By.cssSelector("tr:nth-child(1) #col-" + bioshuttleComplexCol + " input");
	public static String bioshuttleStartDateCol = "5";
	public static By bioshuttleStartDateField = By.cssSelector("tr:nth-child(1) #col-" + bioshuttleStartDateCol + " input");
	public static String bioshuttleEndDateCol = "6";
	public static By bioshuttleEndDateField = By.cssSelector("tr:nth-child(1) #col-" + bioshuttleEndDateCol + " input");

	//FIELD ACCESS FUNCTIONALITY LOCATORS

	public static By fieldAccessIcon = By.id("edit-field-access");

	WebElement fieldAccess = getDriver().findElement(By.id("edit-field-access"));

	public static By resetDefaultButton = By.id("btn-reset");
	public static By saveButton = By.id("btn-save");

	public static By fieldAccessTitle = By.xpath("//label[contains(text(),'Field Order')]");
	public static By dropFirstColumn = By.id("drag-control-element-0");

	public static By successMessageFieldAccess = By.id("message");

	//***Record Treatment Locators*****
	public static By recordTreatmentButton = By.xpath("//*[text()=' Record Treatment']");
	public static By createTreatmentHeading = By.xpath("//div[contains(text(),'Create Treatment')]");
	//public static By complexField = By.xpath("//*[text(),'Select Complex')]");

	public static By complexField = By.id("fcSiteId");
	public static By Canton = By.xpath("//span[contains(text(),'Canton')]");

	public static By farmField = By.id("farmSiteId");

	public static By farmFieldValue = By.xpath("//span[contains(text(),'P&T')]");

	public static By startDateField = By.cssSelector("#startDate img");

	public static By endDateField = By.cssSelector("#endDate img");

	public static By flockPlacementDateField = By.id("placementDateId");

	public static By flockPlacementDateFieldValue = By.xpath("//span[contains(text(),'03/01/2023 - Small')]");

	public static By housesAppliedField = By.id("housePlaced");

	public static By housesAppliedValue = By.xpath("//label[contains(text(),'House 1')]");

	public static By treatmentNameField = By.id("treatmentNameId");
	public static By treatmentDescriptionField = By.id("treatmentDescriptionId");


	public static By targetField = By.cssSelector("#targetsId");
	public static By targetFieldValue = By.xpath("//label[contains(text(),'Coccidiosis')]");
	public static By formTreatmentField = By.xpath("//input[@id='formId']");

	//Inline Edit Treatment functionality locators

	public static By programInlineButtonSave = By.id("edit-inline-access");

	public static By treatmentTab = By.xpath("//*[text()='Treatment ']");
	public static String treatmentTable = "treatment-log";
	public static String treatmentInlineButtonSave = "edit-inlineSave-access";

	public static By treatmentTabInlineEditIcon = By.cssSelector("#treatment-log #edit-inline-access");

	public static By treatmentDescriptionColumn = By.xpath("//tbody/tr[@id='row-0-treatment']/td[@id='col-2-treatment']/div[1]/label[1]/input[1]");
	public static By addedIngredient = By.xpath("//label[contains(text(),'Add Ingredient')]");
	public static By ingredientCategoryDropdown = By.id("feedCategoryId");


}