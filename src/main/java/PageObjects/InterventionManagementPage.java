package PageObjects;

import LogViewFunctions.FieldAccess;
import MiscFunctions.Constants;
import MiscFunctions.DB_Config_DB;
import MiscFunctions.NavigateToScreen;
import MiscFunctions.Queries;
import Models.ProgramManagementModel;
import com.aventstack.extentreports.gherkin.model.Scenario;
import org.apache.groovy.json.internal.IO;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Config.BaseTest.saveResult;
import static LogViewFunctions.FilterLock.Lock;
import static LogViewFunctions.FilterWildcard.Wildcard;
import static LogViewFunctions.Pagination.Pagination;
import static LogViewFunctions.FieldAccess.FieldAccessFunctionality;
import static LogViewFunctions.FieldAccess.KeyColumnsCheck;
import static MiscFunctions.Constants.*;
import static MiscFunctions.DateUtil.dateMMDDYYYY1;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import static MiscFunctions.Queries.getLastCreatedIntervention;
import static MiscFunctions.Queries.getUsersId;
import static PageObjects.BasePage.*;
import static PageObjects.FlockManagementPage.flockFarmDropdownExpand;
import static PageObjects.FlockManagementPage.flockFarmDropdownSearch;
import static PageObjects.LoginPage.logoutButton;
import static PageObjects.LoginPage.sideBar;
import static PageObjects.ProgramManagementPage.programComplexSearch;

public class InterventionManagementPage {
    public static String text;
    String labelText;
    boolean isMandatory;
    public static String interventionManagementTable = "manage-intervention-management-log";
    public static By interventionManagementTitle = By.id("Intervention Management");
    public static By fieldAccessIconIntervention = By.cssSelector("#manage-intervention-management-log #edit-field-access");
    public static By fieldOrderHeaderColumn = By.xpath("//label[contains(text(),'Field Order')]");
    public static By secondCheckboxInputFieldAccess = By.cssSelector("tr:nth-child(2) td:nth-child(4) input");
    public static By fieldAccessColumnElementsInput = By.cssSelector("td:nth-child(4) .rpt-fields input[type='checkbox']");
    public static By fieldAccessColumnElements = By.cssSelector("td:nth-child(4) .rpt-fields");

    public static By fieldNameColumnElements = By.xpath("//div[@class='table-resp apl-resp-table-v5 h-table-popup apl-scrollbar']//tr/td[3]");
    public static By keyColumnElements = By.xpath("//div[@class='table-resp apl-resp-table-v5 h-table-popup apl-scrollbar']//tr/td[3]/following-sibling::td[1]");
    public static String fieldAccessSuccessMessage = "Field Access changes have been saved successfully";
    public static By inlineEditIconIntervention = By.cssSelector("#intervention-management-log #edit-inline-access");
    public static By inlineEditIconSaveChanges = By.cssSelector("#intervention-management-log #edit-inlineSave-access");
    public static By inlineEditInterventionName = By.cssSelector("#col-0-1-entityTypeManagement input[formcontrolname='name']");
    public static By inlineEditComplex = By.xpath("//td[@id='col-0-2-entityTypeManagement']/label");
    //public static By inlineEditFieldLabel = By.cssSelector("#col-0-2-entityTypeManagement input[formcontrolname='name']");
    public static By inlineEditFieldLabel = By.cssSelector("#col-0-3-entityTypeManagement input[formcontrolname='name']");
    public static By mandatoryCheckInterventionName = By.xpath("//div[contains(text(),'Intervention name is required')]");
    public static By popupCrossIcon = By.xpath("//img[@id='close-confirmation']");
    public static By interventionCrossIcon = By.xpath("//div[@id='close-popup-modal']");
    public static By resetFiltersIconIntervention = By.cssSelector("#intervention-management-log #reset-all-filters");
    public static String removeFiltersIconIntervention = "#intervention-management-log #remove-filters.d-none";
    public static By unlockFilterIconIntervention = By.cssSelector("#intervention-management-log #remove-filters");
    public static By lockFilterIconIntervention = By.cssSelector("#intervention-management-log #save-filters");
    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Intervention Name')]")
    public WebElement interventionNameColumn;
    @FindBy(how = How.XPATH, using = "//label[@id='name_sort']")
    public WebElement nameColumn;
    @FindBy(how = How.XPATH, using = "//label[@id='displayName_sort']")
    public WebElement displayNameColumn;
    @FindBy(how = How.XPATH, using = "//label[@id='type_sort']")
    public WebElement typeColumn;
    @FindBy(how = How.XPATH, using = "//label[@id='dateCreated_sort']")
    public WebElement dateCreatedColumn;
    @FindBy(how = How.CSS, using = "#AttributeName_sort")
    public WebElement fieldLabelColumn;
    @FindBy(how = How.ID, using = "siteName_sort")
    public WebElement complexColumn;
    @FindBy(how = How.CSS, using = "#menu-administration")
    public WebElement hoverElement;
    public static By confirmationPopupPendingChanges = By.xpath("//label[contains(text(),'There are some pending changes. Do you want to sav')]");
    public static By navigateToScreen = By.xpath("//a[@id='roleMGMTManageUsersMenu']");
    public static By interventionNameColumnFilter = By.cssSelector("#entitY_NAME_show-filter");
    public static By fieldLabelColumnFilter = By.cssSelector("#AttributeName_show-filter");
    public static By complexColumnFilter = By.cssSelector("#siteName_show-filter");
    public static By viewInterventionsDropdown = By.cssSelector("#entityTypeId input");
    public static By dropdownValue = By.xpath("//span[contains(text(),'InterventionDisplay_1683667593')]");
    public static By createInterventionTypeButton = By.xpath("//a[contains(text(),'Create Intervention Type')]");
    public static By logInterventionTypeButton = By.xpath("//a[contains(text(),'Log Intervention')]");
    public static By selectComplexDropdown = By.cssSelector("#compleSiteId .toggle-list");
    public static By selectComplexDropdownField = By.xpath("//label[@for='compleSiteId']");
    public static By selectComplexSearch = By.id("compleSiteId_search");
    public static By complexSearchFieldValue = By.xpath("//b[contains(text(),'Georgia Complex')]");
    public static By complex2SearchFieldValue = By.xpath("//b[contains(text(),'Complex 2')]");
    public static By nameFieldInterventionForm = By.id("entityTypeName");
    @FindBy(how = How.ID, using = "entityTypeName")
    public WebElement nameField;
    @FindBy(how = How.ID, using = "entityTypeDisplay")
    public WebElement displayField;
    @FindBy(how = How.CSS, using = "#interventionTypeId")
    public WebElement interventionTypeField;
    @FindBy(how = How.XPATH, using = "(//input[@placeholder='Placeholder'])[3]")
    public WebElement fieldLabelField;
    String interventionName = "Intervention_";
    public static String editIcon = "/ancestor::tr//span[contains(@title, 'Edit')]";
    public static String deleteIcon = "/ancestor::tr//span[contains(@title, 'Delete')]";
    public static By displayFieldInterventionForm = By.id("entityTypeDisplay");
    String interventionDisplay = "InterventionDisplay_";
    public static By addAttributeInterventionForm = By.xpath("//button[contains(text(),'Add Attribute')]");
    public static By fieldTypeInterventionForm = By.id("fieldType-1");
    public static By fieldTypeInterventionFormValues = By.cssSelector("#fieldType-1 .ng-option");
    public static By viewInterventionsDropdownValues = By.cssSelector("#entityTypeId .ng-option");
    public static By stringFieldDropdownValue = By.xpath("//span[contains(text(),'StringField')]");
    public static By integerFieldDropdownValue = By.xpath("//span[contains(text(),'IntegerField')]");
    public static By fieldLabelInterventionForm = By.xpath("//input[@id='fieldLabel-1']");
    public static By attributeNameInterventionForm = By.xpath("//input[@id='attributeName - 1']");
    public static By nextButtonInterventionForm = By.xpath("//a[contains(text(),'Next')]");

    //  public static WebElement interventionTypeField = By.cssSelector("#interventionTypeId");
    public static By interventionNameField = By.cssSelector("#input-id");
    public static By interventionNameMandatoryCheck = By.cssSelector("label[for='input-id']");
    public static By resetButton = By.xpath("//a[contains(text(),'Reset')]");
    public static By submitButton = By.xpath("//a[contains(text(),'Submit')]");
    public static By interventionNameFieldRequiredError = By.cssSelector("#entityTypeName-error-container .hide");
    String fieldLabel = "FieldLabel_";
    // Locate all the table rows (excluding the header)
    public By rowsRecord = By.xpath("//table/tbody/tr");
    String targetRowText = "Admin";
    public By editRights = By.xpath(".//span[@title='Edit Rights']");
    public static By saveButtonEditRights = By.cssSelector(".fa-save");
    @FindBy(how = How.CSS, using = "tr:nth-child(29) td:nth-child(2) input")
    public WebElement createRightsInput;
    public static By createRightsCheckbox = By.cssSelector("tr:nth-child(29) td:nth-child(2) .custom-checkbox");
    @FindBy(how = How.CSS, using = "tr:nth-child(29) td:nth-child(3) input")
    public WebElement updateRightsInput;
    public static By updateRightsCheckbox = By.cssSelector("tr:nth-child(29) td:nth-child(3) .custom-checkbox");
    @FindBy(how = How.CSS, using = "tr:nth-child(29) td:nth-child(4) input")
    public WebElement viewRightsInput;
    public static By viewRightsCheckbox = By.cssSelector("tr:nth-child(29) td:nth-child(4) .custom-checkbox");
    public static By firstRow = By.xpath("//table/tbody/tr[1]");
    public static By closeButton = By.xpath("//div[@id='close-popup-modal']");
    public static By firstColumn = By.id("col-0-entityTypeManagement");
    public static By auditIconFirstRow = By.id("audit-trial-0");
    int auditRowCount = 0;
    int auditUpdatedRowCount = 0;
    public static By inlineEditByAuditFieldLabel = By.cssSelector("#col-1-3-entityTypeManagement input[formcontrolname='name']");
    public static By mandatoryToggleButton = By.xpath("//label[@class='switch ml-3']");
    public static String fieldTypeInterventionFormGeneric = "fieldType-";
    public static String fieldLabelInterventionFormGeneric = "//input[@id='fieldLabel-";
    public static String attributeNameInterventionFormGeneric = "//input[@id='attributeName - ";
    public static By deleteAttributeIcon = By.id("deleteAttributeIconId-1");
    public static By cancelButtonInterventionType = By.id("btn-cancel");
    public static By previousStepperInterventionType = By.id("progressbar-step-1");
    public static By nextStepperInterventionType = By.id("progressbar-step-2");
    public static By dropdownSingleSelectField = By.xpath("//span[contains(text(),'DropDownSingleSelectField')]");
    public static By radioButtonSingleSelectField = By.xpath("//span[contains(text(),'RadioButtonSingleSelectField')]");
    public static By dropdownMultiSelectField = By.xpath("//span[contains(text(),'DropDownMultiSelectField')]");
    public static By radioButtonMultiSelectField = By.xpath("//span[contains(text(),'RadioButtonMultiSelectField')]");
    public static By deleteOptionIcon = By.id("deleteAttributeOpIconId-1");
    public static By addSelectionButton = By.id("addAttributeOpbtnId-1");
    public static By addSelectionValue = By.id("num-optionValue-1-4");
    public static By addSelectionDescription = By.id("optionText-1-4");
    public static String optionValueText = "num-optionValue-";
    public static String descriptionValueText = "optionText-";
    @FindBy(how = How.XPATH, using = "(//i[contains(@class, 'drag-handle')])[1]")
    public WebElement dragElement;
    @FindBy(how = How.XPATH, using = "(//i[contains(@class, 'drag-handle')])[2]")
    public WebElement optionHandle;
    public static By optionValue = By.cssSelector("#num-optionValue-3-1");
    public static By optionValue2 = By.cssSelector("#num-optionValue-3-2");
    public static By optionDescription = By.cssSelector("#optionText-3-1");
    public static By optionDescription2 = By.cssSelector("#optionText-3-2");
    @FindBy(how = How.XPATH, using = "(//i[contains(@class, 'drag-handle')])[3]")
    public WebElement dropOption;
    public static By nameRowText = By.cssSelector("tr:nth-child(1) td:nth-child(1) label");
    public static By displayNameRowText = By.cssSelector("tr:nth-child(1) td:nth-child(2) label");
    public static By typeRowText = By.cssSelector("tr:nth-child(1) td:nth-child(3) label");
    public static By dateCreatedRowText = By.cssSelector("tr:nth-child(1) td:nth-child(4) label");
    public static By editIconRowText = By.cssSelector("#edit-intervention-gram-1-interventionManagement");
    public static String editIconTooltipText = "Edit Intervention type";
    public static By deleteIconRowText = By.cssSelector("#delete-intervention-management-1-interventionManagement");
    public static String deleteIconTooltipText = "Delete Intervention type";
    public static By interventionLogSecondRow = By.xpath("//table//tr[2]");
    boolean isDeleteIconPresent = true;
    public static String getInterventionLog = "//*[@id='table-entityType-management-log']//tbody//tr[2]";
    public static String getUpdatedInterventionLog = "//*[@id='table-entityType-management-log']//tbody//tr[1]";
    public static By logInterventionNameCheckbox = By.xpath("//ul[@id='ul-entityType-management_entitY_NAME']/li[last()]//small");
    public static By logInterventionNameCheckboxApplyButton = By.cssSelector("#entityType-management_entitY_NAME_apply");
    public static By logComplexCheckbox = By.xpath("//ul[@id='ul-entityType-management_siteName']/li[last()]//small");
    public static By logComplexCheckboxApplyButton = By.cssSelector("#entityType-management_siteName_apply");
    public static By logAttributeCheckbox = By.xpath("//ul[@id='ul-entityType-management_AttributeName']/li[last()]//small");
    public static By logAttributeCheckboxApplyButton = By.cssSelector("#entityType-management_AttributeName_apply");
    public static String expectedTooltipTextResetFilters = "Reset Filters";
    public static String expectedTooltipTextFieldAccess = "Field Access";
    public static String expectedTooltipTextUnlocked = "Unlock Filters";
    public static String expectedTooltipTextLocked = "Lock Applied Filters";

    private WebDriver driver;

    //Create object of Intervention Management Class
    //Constructor
    public InterventionManagementPage(WebDriver driver) {
        this.driver = driver;
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void navigateInterventionMgtScreen() throws IOException, InterruptedException {
        NavigateToScreen.navigate(url_interventionManagement, "Intervention Management", interventionManagementTitle);
    }

    public void lockFilterFunctionality() throws InterruptedException, IOException {
        driver.get(url_interventionManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        Lock(interventionManagementTable, "Intervention Management", 0);
    }

    public void wildcardFunctionality() throws InterruptedException, IOException {
        driver.get(url_interventionManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        Wildcard(interventionManagementTable, "Intervention Management", 1);
    }

    public void iconsPresenceOnInlineEditFunctionality() throws IOException, InterruptedException, SQLException {
        test = extent.createTest("Verify icons reset and unlock button are hidden in inline mode on Intervention Management Screen ");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        DB_Config_DB.test();
        try {
            driver.get(url_interventionManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            SoftAssert softAssert = new SoftAssert();

            getLastCreatedIntervention();


            click(inlineEditIconIntervention);
            waitElementInvisible(loading_cursor);
            softAssert.assertEquals(size(By.cssSelector(ResetFilters)), 0, "Reset filters icon is not hidden");
            softAssert.assertEquals(size(By.cssSelector(UnlockFilter)), 0, "Unlock filters icon is not hidden");
            softAssert.assertAll();
            test.pass("Test case passed successfully");
            results.createNode("Test case passed successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Confirmation popup display changes are not verified");
            results.createNode("Confirmation popup display changes are nor verified");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Confirmation popup display changes failed");
            results.createNode("Confirmation popup display changes failed");
            saveResult(ITestResult.FAILURE, ex);
        }
        DB_Config_DB.getStmt().close();
    }

    public long setUniqueTime() throws IOException {

        // Get current timestamp
        long timestampInSeconds = System.currentTimeMillis() / 1000;

        // Combine timestamp and random number to create a unique name

        return timestampInSeconds;
    }

    public String createInterventionType() throws IOException, InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        click(createInterventionTypeButton);
        waitElementVisible(nameFieldInterventionForm);
        long currentTime = setUniqueTime();
        String setInterventionName = interventionName + currentTime;
        type(nameFieldInterventionForm, setInterventionName);
        clear(nameFieldInterventionForm);
        softAssert.assertEquals(size(interventionNameFieldRequiredError), 0, "Error appeared for mandatory check");
        type(nameFieldInterventionForm, setInterventionName);
        System.out.println(setInterventionName);
        softAssert.assertEquals(size(interventionNameFieldRequiredError), 1, "Error appeared for mandatory check");
        type(displayFieldInterventionForm, interventionDisplay + currentTime);
        click(addAttributeInterventionForm);
        click(fieldTypeInterventionForm);
        click(stringFieldDropdownValue);
        click(fieldLabelInterventionForm);
        type(fieldLabelInterventionForm, "Field Label");
        type(attributeNameInterventionForm, "AttributeName");
        click(nextButtonInterventionForm);
        waitElementClickable(popupSubmitButton);
        click(popupCloseButton);
        click(popupNoButton);
        click(popupSubmitButton);
        waitElementInvisible(loading_cursor);
        waitElementVisible(alertMessage);
        softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Intervention type has been created successfully", "Intervention type has not been created");
        return setInterventionName;
    }

    private boolean isAlertPresent() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void crudIntervention() throws IOException, InterruptedException {
        test = extent.createTest("Verify crud intervention functionality on Intervention Management Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        try {
            driver.get(url_interventionManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            SoftAssert softAssert = new SoftAssert();
            String interventionName = createInterventionType();
            softAssert.assertTrue(driver.findElement(By.xpath("//label[@title='" + interventionName + "']")).isDisplayed(), "Newly created intervention does not appear on logview");
            click(By.xpath("//label[@title='" + interventionName + "']" + editIcon));
            waitElementInvisible(loading_cursor);
            // Verify tc that on no edit, when I click on x icon, confirmation popup should not appear
            click(popupCloseButton);
            // Verify that the confirmation popup does not appear
            boolean isAlertPresent = isAlertPresent();
            softAssert.assertFalse(isAlertPresent, "Confirmation popup appeared");
            // Verify edit functionality for the intervention type
            click(By.xpath("//label[@title='" + interventionName + "']" + editIcon));
            waitElementInvisible(loading_cursor);
            type(nameFieldInterventionForm, "Updated");
            String updatedNameField = nameField.getAttribute("value");
            type(displayFieldInterventionForm, " Updated");
            click(fieldLabelInterventionForm);
            type(fieldLabelInterventionForm, " Updated");
            type(attributeNameInterventionForm, "AttributeUpdated");
            click(nextButtonInterventionForm);
            waitElementClickable(popupSubmitButton);
            click(popupSubmitButton);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Intervention type has been updated successfully", "Intervention type has not been updated");

            // Verify that the date is saved in table
            softAssert.assertEquals(driver.findElement(dateCreatedRowText).getText(), dateMMDDYYYY1, "Current date is not displayed in table");

            // Verify that the intervention form should not be deleted after clicking on the NO button displayed on the confirmation popup.
            click(By.xpath("//label[@title='" + updatedNameField + "']" + deleteIcon));
            waitElementInvisible(loading_cursor);
            click(popupNoButton);
            waitElementInvisible(loading_cursor);

            // Verify that the intervention form should not be deleted after clicking on the X icon displayed on the confirmation popup.
            click(By.xpath("//label[@title='" + updatedNameField + "']" + deleteIcon));
            waitElementInvisible(loading_cursor);
            click(popupCrossIcon);
            waitElementInvisible(loading_cursor);

            // Verify that the intervention form should be deleted after clicking on the YES button displayed on the confirmation popup
            click(By.xpath("//label[@title='" + updatedNameField + "']" + deleteIcon));
            waitElementInvisible(loading_cursor);
            click(popupYesButton);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Intervention Type Deleted Successfully", "Intervention type has not been deleted.");
            waitElementInvisible(loading_cursor);
            softAssert.assertAll();
            test.pass("Test case passed successfully");
            results.createNode("Test case passed successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Crud intervention type created successfully");
            results.createNode("Crud intervention type created successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Crud intervention type did not created successfully");
            results.createNode("Crud intervention type did not created successfully");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public boolean verifyLogInterventionDataSanity(String setInterventionNameField, String interventionLogRow) {
        try {
            SoftAssert softAssert = new SoftAssert();
            String query = "select Top 1 E.Entity_Name, s.sitename, V.String_Data_Value \n" +
                    "from ENTITY E\n" +
                    "left join [VALUE] V on E.ENTITY_ID = V.ENTITY_ID\n" +
                    "left join VALUE_OPTION VO on VO.VALUE_ID = V.VALUE_ID\n" +
                    "left join TYPE_ATTRIBUTE_OPTION TAO on TAO.TYPE_ATTRIBUTE_OPTION_ID = VO.TYPE_ATTRIBUTE_OPTION_ID\n" +
                    "join site s on s.siteid = e.site_id\n" +
                    "where E.ENTITY_NAME = '" + setInterventionNameField + "';";
            String getRowData = interventionLogRow;
            // Get all columns data from front end
            List<WebElement> cells = driver.findElement(By.xpath(getRowData)).findElements(By.tagName("td"));
            List<String> frontendRow = new ArrayList<>();

            if (!cells.isEmpty()) {
                for (WebElement cell : cells) {
                    frontendRow.add(cell.getText());
                }
            } else {
                System.out.println("No columns found  " + firstRow);
            }

            ResultSet resultSet = DB_Config_DB.getStmt().executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            List<String> backendRow = new ArrayList<>();

            if (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    backendRow.add(resultSet.getString(i));
                }
            }
            // Compare the front end and back end data
            for (int i = 1; i <= columnCount; i++) {
                String frontendValue = frontendRow.get(i).toLowerCase();
                String backendValue = backendRow.get(i - 1).toLowerCase();
                System.out.println("Frontend value for column " + i + ": " + frontendValue);
                System.out.println("Backend value for column " + i + ": " + backendValue);
                softAssert.assertEquals(frontendValue, backendValue, "Value in frontend and backend do not match for column " + i);
            }

            softAssert.assertAll();
            test.pass("Data sanity verified successfully");
            results.createNode("Data sanity verified successfully");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            test.fail("Data sanity failed");
            results.createNode("Data Sanity failed");
            return false;
        }

    }


    public void crudLogIntervention() throws IOException, InterruptedException, SQLException {
        test = extent.createTest("Verify Crud Log Intervention functionality on Intervention Management Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        DB_Config_DB.test();

        try {
            driver.get(url_interventionManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            SoftAssert softAssert = new SoftAssert();
            String interventionName = createInterventionType();
            click(By.xpath("//label[@title='" + interventionName + "']" + editIcon));
            waitElementInvisible(loading_cursor);
            String displayFieldValue = displayField.getAttribute("value");
            System.out.println(displayFieldValue);
            click(popupCloseButton);

            // Verify log intervention button is available on the intervention management screen and its clickable
            softAssert.assertTrue(driver.findElement(logInterventionTypeButton).isEnabled(), "Log intervention button is not clickable");
            // Verify that multiple responses can be added against log intervention type
            Actions actions = new Actions(driver);

            String setInterventionNameField = "";
            long currentTime = setUniqueTime();

                if (Constants.config.url().contains("qa") || Constants.config.url().contains("dev"))
                {
                    ResultSet getComplexNameResults = DB_Config_DB.getStmt().executeQuery(Queries.getTwoComplexNameAssignedToUser(getUsersId()));
                    int i=0;
                    //    ResultSet getComplexNameResults = DB_Config_DW.getStmt().executeQuery(Queries.getComplexName);
                    while (getComplexNameResults.next())
                    {
                        click(logInterventionTypeButton);
                        waitElementInvisible(loading_cursor);
                        click(selectComplexDropdown);
                        Thread.sleep(2000);

                        String complexName = getComplexNameResults.getString("siteName");
                        System.out.println("Complex Name: " + complexName);
                        type(programComplexSearch, complexName);
                        Thread.sleep(1000);
                        click(By.cssSelector("#compleSiteId b"));

                        actions.click(interventionTypeField).sendKeys(displayFieldValue).sendKeys(Keys.ENTER).build().perform();
                        waitElementInvisible(loading_cursor);
                        setInterventionNameField = "Log" + currentTime;
                        type(interventionNameField, setInterventionNameField);

                        waitElementVisible(By.xpath("(//input[@placeholder='Placeholder'])[3]"));
                        if (i == 1)
                            actions.click(fieldLabelField).sendKeys("fieldLabel").sendKeys(Keys.ENTER).build().perform();
                        else
                            actions.click(fieldLabelField).sendKeys("fieldLabel2").sendKeys(Keys.ENTER).build().perform();
                        waitElementInvisible(loading_cursor);
                        click(submitButton);
                        waitElementInvisible(loading_cursor);
                        Thread.sleep(2000);
                        i++;
                    }
                }

            System.out.println(setInterventionNameField);
            boolean sanityPassed = verifyLogInterventionDataSanity(setInterventionNameField, getInterventionLog);
            softAssert.assertTrue(sanityPassed, "Data sanity verification failed");

            // Verify a new response is added in the log view
            softAssert.assertTrue(driver.findElement(interventionLogSecondRow).isEnabled(), "Another row was not created in the logview.");

            // Edit the log response

            click(By.xpath("//label[@title='" + setInterventionNameField + "']" + editIcon));
            waitElementInvisible(loading_cursor);

            // Verify complex and intervention field is disabled

            softAssert.assertEquals(size(By.cssSelector("#compleSiteId.ng-untouched")), 1, "Complex dropdown is not disabled");

            softAssert.assertEquals(size(By.cssSelector("#interventionTypeId.ng-untouched")), 1, "Intervention type dropdown is not disabled");

            // actions.click(driver.findElement(interventionNameField)).sendKeys(" Edit").sendKeys(Keys.ENTER).build().perform();
            clear(interventionNameField);
            Thread.sleep(1000);
            setInterventionNameField = "Edit" + currentTime;
            type(interventionNameField, setInterventionNameField);
            Thread.sleep(1000);
            String logName = driver.findElement(interventionNameField).getAttribute("value");

            System.out.println(" LogNAME is" + logName);
            click(submitButton);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Intervention Entity has been updated successfully", "Edit message for intervention log didn't appeared");
            waitElementInvisible(loading_cursor);
            waitElementVisible(interventionManagementTitle);
            Thread.sleep(2000);

            click(By.id(ResetFilters));
            waitElementInvisible(loading_cursor);

            sanityPassed = verifyLogInterventionDataSanity(logName, getUpdatedInterventionLog);
            softAssert.assertTrue(sanityPassed, "Data sanity for update functionality failed");
            // Verify the system does not allow the user to delete the response
            try
            {
                driver.findElement(By.xpath("//label[@title='Intervention']" + deleteIcon));
            } catch (NoSuchElementException e)
            {
                isDeleteIconPresent = false;
            }

            softAssert.assertFalse(isDeleteIconPresent, "Delete icon is present against log response");

            softAssert.assertAll();
            test.pass("Test case passed successfully");
            results.createNode("Test case passed successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Edit intervention type created successfully");
            results.createNode("Edit intervention type created successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Edit intervention type did not created successfully");
            results.createNode("Edit intervention type did not created successfully");
            saveResult(ITestResult.FAILURE, ex);
        }
        DB_Config_DB.getStmt().close();
    }

    public void sameNameIntervention() throws IOException, InterruptedException {
        test = extent.createTest("Verify Same Name Intervention created on Intervention Management Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        try {
            driver.get(url_interventionManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            SoftAssert softAssert = new SoftAssert();
            String interventionName = createInterventionType();
            click(By.xpath("//label[@title='" + interventionName + "']" + deleteIcon));
            Thread.sleep(1500);
            waitElementInvisible(loading_cursor);
            click(popupYesButton);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Intervention Type Deleted Successfully", "Intervention type has not been deleted.");
            String interventionNameDuplicate = createInterventionType();
            click(By.xpath("//label[@title='" + interventionNameDuplicate + "']" + editIcon));
            waitElementInvisible(loading_cursor);
            clear(nameFieldInterventionForm);
            Thread.sleep(1000);
            type(nameFieldInterventionForm, interventionName);
            Thread.sleep(1000);
            click(nextButtonInterventionForm);
            waitElementClickable(submitButton);
            click(submitButton);
            waitElementInvisible(loading_cursor);
            click(By.xpath("//label[@title='" + interventionName + "']" + deleteIcon));
            waitElementInvisible(loading_cursor);
            click(popupYesButton);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Intervention Type Deleted Successfully", "Intervention type has not been deleted.");
            softAssert.assertAll();
            test.pass("Test case passed successfully");
            results.createNode("Test case passed successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Same name intervention type created successfully");
            results.createNode("Same name intervention type created successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Same name intervention type did not created successfully");
            results.createNode("Same name intervention type did not created successfully");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void inlineEditIntervention() throws IOException, InterruptedException, SQLException {
        test = extent.createTest("Verify Inline Edit Functionality on Intervention Management Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        Actions actions = new Actions(driver);
        DB_Config_DB.test();
        try {

            driver.get(url_interventionManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            SoftAssert softAssert = new SoftAssert();
            String interventionName = createInterventionType();
            click(By.xpath("//label[@title='" + interventionName + "']" + editIcon));
            waitElementInvisible(loading_cursor);
            String displayFieldValue = displayField.getAttribute("value");
            System.out.println(displayFieldValue);
            click(popupCloseButton);

            // Verify log intervention button is available on the intervention management screen and its clickable
            softAssert.assertTrue(driver.findElement(logInterventionTypeButton).isEnabled(), "Log intervention button is not clickable");
            // Verify that multiple responses can be added against log intervention type

            String setInterventionNameField = "";
            long currentTime = setUniqueTime();

            if (Constants.config.url().contains("qa") || Constants.config.url().contains("dev"))
            {
                ResultSet getComplexNameResults = DB_Config_DB.getStmt().executeQuery(Queries.getTwoComplexNameAssignedToUser(getUsersId()));
                int i=0;
                //    ResultSet getComplexNameResults = DB_Config_DW.getStmt().executeQuery(Queries.getComplexName);
                while (getComplexNameResults.next())
                {
                    click(logInterventionTypeButton);
                    waitElementInvisible(loading_cursor);
                    click(selectComplexDropdown);
                    Thread.sleep(2000);

                    String complexName = getComplexNameResults.getString("siteName");
                    System.out.println("Complex Name: " + complexName);
                    type(programComplexSearch, complexName);
                    Thread.sleep(1000);
                    click(By.cssSelector("#compleSiteId b"));

                    actions.click(interventionTypeField).sendKeys(displayFieldValue).sendKeys(Keys.ENTER).build().perform();
                    waitElementInvisible(loading_cursor);
                    setInterventionNameField = "Log" + currentTime;
                    type(interventionNameField, setInterventionNameField);

                    waitElementVisible(By.xpath("(//input[@placeholder='Placeholder'])[3]"));
                    if (i == 1)
                        actions.click(fieldLabelField).sendKeys("fieldLabel").sendKeys(Keys.ENTER).build().perform();
                    else
                        actions.click(fieldLabelField).sendKeys("fieldLabel2").sendKeys(Keys.ENTER).build().perform();
                    waitElementInvisible(loading_cursor);
                    click(submitButton);
                    waitElementInvisible(loading_cursor);
                    Thread.sleep(2000);
                    i++;
                }
            }

            //click(viewInterventionsDropdown);
            //click(dropdownValue);
            waitElementInvisible(loading_cursor);
            click(inlineEditIconIntervention);
            waitElementInvisible(loading_cursor);
            // Verify that the confirmation popup does not appear
            click(inlineEditIconSaveChanges);
            waitElementInvisible(loading_cursor);
            boolean isAlertPresent = isAlertPresent();
            softAssert.assertFalse(isAlertPresent, "Confirmation popup appeared");
            // Edit all fields through Inline Edit functionality
            click(inlineEditIconIntervention);
            waitElementInvisible(loading_cursor);
            clear(inlineEditInterventionName);
            //long currentTime = setUniqueTime();
            type(inlineEditInterventionName, interventionName + currentTime);
            clear(inlineEditFieldLabel);
            type(inlineEditFieldLabel, fieldLabel + currentTime);

            // Verify that the complex field is not an editable field
            String actualCursorStyle = driver.findElement(inlineEditComplex).getCssValue("cursor");
            String expectedCursorStyle = "not-allowed";
            softAssert.assertEquals(expectedCursorStyle, actualCursorStyle, "The field is editable");
            // Verify that the confirmation popup does appear and the user clicks on no
            click(inlineEditIconSaveChanges);
            waitElementInvisible(loading_cursor);
            softAssert.assertFalse(isAlertPresent, "Confirmation popup appeared");
            click(popupNoButton);
            waitElementInvisible(loading_cursor);
            softAssert.assertEquals(size(inlineEditFieldLabel), 0, "the user will navigate to the simple log view");
            // Verify that the confirmation popup does appear and the user clicks on cross icon
            click(inlineEditIconIntervention);
            waitElementInvisible(loading_cursor);
            clear(inlineEditInterventionName);
            type(inlineEditInterventionName, interventionName + currentTime);
            clear(inlineEditFieldLabel);
            type(inlineEditFieldLabel, fieldLabel + currentTime);
            click(inlineEditIconSaveChanges);
            waitElementInvisible(loading_cursor);
            softAssert.assertFalse(isAlertPresent, "Confirmation popup appeared");
            click(popupCrossIcon);
            waitElementInvisible(loading_cursor);
            softAssert.assertEquals(size(inlineEditFieldLabel), 1, "the user will navigate to the inline edit mode");
            // Verify that the confirmation popup does appear and the user clicks on yes
            click(inlineEditIconSaveChanges);
            waitElementInvisible(loading_cursor);
            softAssert.assertFalse(isAlertPresent, "Confirmation popup appeared");
            click(popupYesButton);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Intervention Entity has been updated successfully", "Intervention log has not been edited by inline edit functionality");
            waitElementVisible(interventionManagementTitle);
            softAssert.assertAll();
            test.pass("Test case passed successfully");
            results.createNode("Test case passed successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Inline Edit intervention functionality passed successfully");
            results.createNode("Inline Edit intervention functionality passed successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Inline Edit intervention functionality failed");
            results.createNode("Inline Edit intervention functionality failed");
            saveResult(ITestResult.FAILURE, ex);
        }
        DB_Config_DB.getStmt().close();
    }

    public void testSorting(WebElement columnElement) {
        SoftAssert softAssert = new SoftAssert();
        // get the current data in the table
        List<WebElement> rows = driver.findElements(rowsRecord);
        List<String> beforeSort = new ArrayList<>();
        for (WebElement row : rows) {
            beforeSort.add(row.getText());
        }
        // click on the column header to trigger the sorting action
        columnElement.click();

        // wait for the table to reload with the sorted data
        waitElementVisible(firstRow);
        rows = driver.findElements(rowsRecord);

        // get the data in the table after sorting
        List<String> afterSort = new ArrayList<>();
        for (WebElement row : rows) {
            afterSort.add(row.getText());
        }

        // compare the data before and after sorting
        if (beforeSort.equals(afterSort)) {
            System.out.println("Sorting action was not triggered");
            softAssert.assertEquals(beforeSort, afterSort, "Sorting action was not triggered");
        } else {
            System.out.println("Sorting action was triggered");
        }
        softAssert.assertAll();
    }

    public void inlineEditInterventionCheckActions() throws IOException, InterruptedException, SQLException {
        test = extent.createTest("Verify Inline Edit Intervention on Intervention Management Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        DB_Config_DB.test();
        try {
            driver.get(url_interventionManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            SoftAssert softAssert = new SoftAssert();

            getLastCreatedIntervention();

            click(inlineEditIconIntervention);
            waitElementInvisible(loading_cursor);
            // Verify that the sorting, filtering, and pagination feature does not work in inline edit mode
            testSorting(interventionNameColumn);
            testSorting(complexColumn);
            testSorting(fieldLabelColumn);
            //Check filtering icon is hidden
            softAssert.assertEquals(size(interventionNameColumnFilter), 0, "filters icon is not hidden");
            softAssert.assertEquals(size(complexColumnFilter), 0, "filter icon is not hidden");
            softAssert.assertEquals(size(fieldLabelColumnFilter), 0, "filter icon is not hidden");
            //Check pagination is hidden
            softAssert.assertEquals(size(By.id(firstPagePagination)), 0, "first page is not hidden");
            softAssert.assertEquals(size(By.id(nextPagePagination)), 0, "next page is not hidden");
            softAssert.assertEquals(size(By.id(lastPagePagination)), 0, "last page is not hidden");
            softAssert.assertEquals(size(By.id(previousPagePagination)), 0, "previous page is not hidden");
            softAssert.assertAll();
            test.pass("Test case passed successfully");
            results.createNode("Test case passed successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Inline Edit intervention functionality for sorting, filtering and pagination features passed successfully");
            results.createNode("Inline Edit intervention functionality for sorting, filtering and pagination features passed successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Inline Edit intervention functionality for sorting, filtering and pagination features failed");
            results.createNode("Inline Edit intervention functionality for sorting, filtering and pagination features failed");
            saveResult(ITestResult.FAILURE, ex);
        }
        DB_Config_DB.getStmt().close();
    }

    public void inlineEditNavigateToScreen() throws IOException, InterruptedException, SQLException {
        test = extent.createTest("Verify Inline Edit Intervention on Intervention Management Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        DB_Config_DB.test();
        try {
            driver.get(url_interventionManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            SoftAssert softAssert = new SoftAssert();

            getLastCreatedIntervention();

            click(inlineEditIconIntervention);
            waitElementInvisible(loading_cursor);
            // Verify that the confirmation dialogue box display whenever the user navigates to any screen from IE in inline edit mode
            type(inlineEditInterventionName, " Editing");
            Actions builder = new Actions(driver);
            builder.moveToElement(hoverElement).build().perform();
            hoverElement.click();
            click(navigateToScreen);
            waitElementVisible(confirmationPopupPendingChanges);
            click(popupCrossIcon);

            // Verify on yes, the user navigates to the screen and changes are saved
            builder.moveToElement(hoverElement).build().perform();
            click(navigateToScreen);
            waitElementVisible(confirmationPopupPendingChanges);
            click(popupYesButton);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Intervention Entity has been updated successfully");
            // Verify if the current URL matches the expected URL
            String actualUrl = driver.getCurrentUrl();
            softAssert.assertEquals(actualUrl, url_user, "The user did not navigate to the correct url");

            // Verify on NO, the user navigates to the screen and changes are NOT saved
            driver.get(url_interventionManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            getLastCreatedIntervention();

            click(inlineEditIconIntervention);
            waitElementInvisible(loading_cursor);
            type(inlineEditInterventionName, " Editing");
            builder.moveToElement(hoverElement).build().perform();
            hoverElement.click();
            click(navigateToScreen);
            waitElementVisible(confirmationPopupPendingChanges);
            click(popupNoButton);
            softAssert.assertEquals(size(alertMessage), 0, "Success message appeared");
            actualUrl = driver.getCurrentUrl();
            softAssert.assertEquals(actualUrl, url_user, "The user did not navigate to the correct url");

            softAssert.assertAll();
            test.pass("Test case passed successfully");
            results.createNode("Test case passed successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Inline Edit intervention functionality for sorting, filtering and pagination features passed successfully");
            results.createNode("Inline Edit intervention functionality for sorting, filtering and pagination features passed successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Inline Edit intervention functionality for sorting, filtering and pagination features failed");
            results.createNode("Inline Edit intervention functionality for sorting, filtering and pagination features failed");
            saveResult(ITestResult.FAILURE, ex);
        }
        DB_Config_DB.getStmt().close();
    }

    public void AccessRights() throws IOException, InterruptedException {
        test = extent.createTest("Verify Inline Edit Rights on Intervention Management Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        try {
            SoftAssert softAssert = new SoftAssert();
            driver.get(url_access);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            List<WebElement> rows = driver.findElements(rowsRecord);
            // Loop through each row and click on the "Edit Rights" icon of the Admin row
            for (WebElement row : rows) {
                String rowText = row.getText();
                if (rowText.contains(targetRowText)) {
                    WebElement editRightsIcon = row.findElement(editRights);
                    editRightsIcon.click();
                    Thread.sleep(1500);
                    break;
                }
            }
            if (createRightsInput.isSelected()) {
                click(createRightsCheckbox);
                Thread.sleep(1000);
            }
            if (viewRightsInput.isSelected()) {
                click(viewRightsCheckbox);
                Thread.sleep(1000);
            }

            click(saveButtonEditRights);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Rights details has been updated successfully.");

            //logout
            hover(sideBar);
            click(logoutButton);

            //Go to access management screen, and revert changes
            softAssert.assertAll();
            test.pass("Test case passed successfully");
            results.createNode("Edit rights for intervention management screen updated successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Edit rights for intervention management screen updated successfully");
            results.createNode("Edit rights for intervention management screen updated successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Edit rights for intervention management screen updated failed");
            results.createNode("Edit rights for intervention management screen updated failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void inlineEditAccessRights() throws IOException, SQLException {

        DB_Config_DB.test();
        //GO to intervention management screen and verify inline edit icon is clickable
        SoftAssert softAssert = new SoftAssert();
        driver.get(url_interventionManagement);
        waitElementInvisible(loading_cursor);

        getLastCreatedIntervention();

        softAssert.assertTrue(driver.findElement(inlineEditIconIntervention).isEnabled(), "Inline Edit icon is clickable");
        getScreenshot();
        DB_Config_DB.getStmt().close();

    }

    public void adminAccessRights() throws IOException, InterruptedException {
        test = extent.createTest("Verify all access rights are given on Intervention Management Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        try {
            //Go to access management screen, and revert admin changes
            SoftAssert softAssert = new SoftAssert();
            driver.get(url_access);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            List<WebElement> rows = driver.findElements(rowsRecord);
            // Loop through each row and click on the "Edit Rights" icon of the Admin row
            for (WebElement row : rows) {
                String rowText = row.getText();
                if (rowText.contains(targetRowText)) {
                    WebElement editRightsIcon = row.findElement(editRights);
                    editRightsIcon.click();
                    Thread.sleep(1500);
                    break;
                }
            }
            if (createRightsInput.isSelected() == false) {
                click(createRightsCheckbox);
                Thread.sleep(1000);
            }
            if (!updateRightsInput.isSelected()) {
                click(updateRightsCheckbox);
                Thread.sleep(1000);
            }
            if (!viewRightsInput.isSelected()) {
                click(viewRightsCheckbox);
                Thread.sleep(1000);
            }

            click(saveButtonEditRights);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Rights details has been updated successfully.");

            //logout
            hover(sideBar);
            click(logoutButton);

            softAssert.assertAll();
            getScreenshot();
            test.pass("Test case passed successfully");
            results.createNode("Admin rights for intervention management screen passed successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Admin rights for intervention management screen updated successfully");
            results.createNode("Admin rights for intervention management screen given successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Admin rights for intervention management screen given failed");
            results.createNode("Admin rights for intervention management screen given failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void inlineEditAudit() throws IOException, InterruptedException, SQLException {
        test = extent.createTest("Verify audit on Intervention Management Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        DB_Config_DB.test();
        try {
            SoftAssert softAssert = new SoftAssert();
            driver.get(url_interventionManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            getLastCreatedIntervention();


            // Click on the audit icon to get the current count of rows
            click(auditIconFirstRow);
            waitElementInvisible(loading_cursor);
            waitElementVisible(closeButton);

            // Get the number of rows
            auditRowCount = size(auditGetRowCount);
            System.out.println("Number of rows: " + auditRowCount);
            driver.navigate().refresh();
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            getLastCreatedIntervention();

            // Change one or two records in the rows
            click(inlineEditIconIntervention);
            waitElementInvisible(loading_cursor);

            clear(inlineEditFieldLabel);
            type(inlineEditFieldLabel, fieldLabel + "verifyAudit");

            clear(inlineEditByAuditFieldLabel);
            type(inlineEditByAuditFieldLabel, fieldLabel + "verifyAudit");

            // Save the inline edit changes
            click(inlineEditIconSaveChanges);
            waitElementInvisible(loading_cursor);
            click(popupYesButton);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Intervention Entity has been updated successfully", "Intervention log has not been edited by inline edit functionality");
            waitElementVisible(interventionManagementTitle);

            // Now again click on the audit icon to get the current count of rows
            click(auditIconFirstRow);
            waitElementInvisible(loading_cursor);
            waitElementVisible(closeButton);

            // Get the updated number of rows
            auditUpdatedRowCount = size(auditGetRowCount);
            System.out.println("Updated number of rows: " + auditUpdatedRowCount);

            softAssert.assertEquals(auditRowCount, auditUpdatedRowCount - 1, "A new row is not added in audit");

            softAssert.assertAll();
            getScreenshot();
            test.pass("Test case passed successfully");
            results.createNode("Audit for intervention management screen passed successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Audit for intervention management screen updated successfully");
            results.createNode("Audit for intervention management screen given successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Audit for intervention management screen given failed");
            results.createNode("Audit for intervention management screen given failed");
            saveResult(ITestResult.FAILURE, ex);
        }
        DB_Config_DB.getStmt().close();
    }

    public void testSortingFunctionality(WebElement columnElement) {
        SoftAssert softAssert = new SoftAssert();
        // click on the column header to trigger the sorting action
        columnElement.click();

        // wait for the first row in the table to become visible
        waitElementVisible(firstRow);
        // get the data in the table before sorting
        List<WebElement> rows = driver.findElements(rowsRecord);
        List<String> beforeSort = new ArrayList<>();
        for (WebElement row : rows) {
            beforeSort.add(row.getText());
        }

        // sort the data in ascending order
        List<String> expected = new ArrayList<>(beforeSort);
        Collections.sort(expected);

        // compare the data before and after sorting using assert
        List<String> actual = new ArrayList<>(beforeSort);
        Collections.sort(actual);
        softAssert.assertEquals(expected, actual, "Sorting action was not successful");
        softAssert.assertAll();
    }

    public void verifySorting() throws IOException, InterruptedException {
        test = extent.createTest("Verify sorting functionality on Intervention Management Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        try {
            driver.get(url_interventionManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            // Check sorting on intervention type logview
            testSortingFunctionality(nameColumn);
            testSortingFunctionality(displayNameColumn);
            testSortingFunctionality(typeColumn);
            testSortingFunctionality(dateCreatedColumn);

            // Check sorting on log intervention
            click(viewInterventionsDropdown);
            click(dropdownValue);
            waitElementInvisible(loading_cursor);
            testSortingFunctionality(interventionNameColumn);
            testSortingFunctionality(complexColumn);
            testSortingFunctionality(fieldLabelColumn);

            getScreenshot();
            test.pass("Test case passed successfully");
            results.createNode("sorting functionality on intervention management screen passed successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("sorting functionality on intervention management screen updated successfully");
            results.createNode("sorting functionality on intervention management screen given successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("sorting functionality on intervention management screen given failed");
            results.createNode("sorting functionality on intervention management screen given failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void verifyFieldAccessFunctionality() throws InterruptedException, IOException {
        driver.get(url_interventionManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        FieldAccessFunctionality(fieldAccessIconIntervention);
        KeyColumnsCheck(fieldAccessIconIntervention, new String[] {"Name", "Type"});
    }

    public void verifyMandatoryChecksFunctionality() throws IOException, InterruptedException {
        test = extent.createTest("Verify mandatory checks on Intervention Management Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        try {
            SoftAssert softAssert = new SoftAssert();
            driver.get(url_interventionManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            click(viewInterventionsDropdown);
            click(dropdownValue);
            waitElementInvisible(loading_cursor);
            WebElement tdElements = driver.findElement(firstColumn);
            // get the label element inside the first column
            WebElement labelElement = tdElements.findElement(By.tagName("label"));
            // get the text of the label element
            String text = labelElement.getText();
            System.out.println(text);
            click(By.xpath("//label[@title='" + text + "']" + editIcon));
            waitElementInvisible(loading_cursor);
            type(interventionNameField, "Mandatory Check");
            Thread.sleep(2000);
            driver.findElement(interventionNameField).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
            Thread.sleep(2000);
            // Mandatory check appears
            softAssert.assertEquals(size(mandatoryCheckInterventionName), 1, "Required validation error didn't appeared");
            click(closeButton);
            waitElementInvisible(loading_cursor);

            click(logInterventionTypeButton);
            waitElementInvisible(loading_cursor);
            labelText = driver.findElement(selectComplexDropdownField).getText();
            isMandatory = labelText.contains("*");

            //Assert that the Complex dropdown field is mandatory
            softAssert.assertTrue(isMandatory, "Complex dropdown field is not marked as mandatory.");

            labelText = interventionTypeField.getText();
            isMandatory = labelText.contains("*");
            //Assert that the Intervention type dropdown field is mandatory
            softAssert.assertTrue(isMandatory, "Intervention type dropdown field is not marked as mandatory.");

            labelText = driver.findElement(interventionNameMandatoryCheck).getText();
            isMandatory = labelText.contains("*");
            //Assert that the Intervention Name field is mandatory
            softAssert.assertTrue(isMandatory, "Intervention Name field is not marked as mandatory.");

            softAssert.assertAll();
            getScreenshot();
            test.pass("Test case passed successfully");
            results.createNode("Mandatory Checks Functionality on intervention management screen passed successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Mandatory Checks Functionality on intervention management screen passed successfully");
            results.createNode("Mandatory Checks Functionality on intervention management screen passed successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Mandatory Checks Functionality on intervention management screen failed");
            results.createNode("Mandatory Checks Functionality on intervention management screen failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void verifyToggleButtonFunctionality() throws IOException, InterruptedException {
        test = extent.createTest("Verify toggle button is present against every field type on Intervention Management Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        try {
            SoftAssert softAssert = new SoftAssert();
            driver.get(url_interventionManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            // Verify cancel button functionality on Intervention Management screen
            click(createInterventionTypeButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            click(cancelButtonInterventionType);
            waitElementInvisible(loading_cursor);
            waitElementVisible(interventionManagementTitle);

            click(createInterventionTypeButton);
            waitElementInvisible(loading_cursor);
            waitElementVisible(nameFieldInterventionForm);
            long currentTime = setUniqueTime();
            String setInterventionName = interventionName + currentTime;
            type(nameFieldInterventionForm, setInterventionName);
            type(displayFieldInterventionForm, interventionDisplay + currentTime);
            click(addAttributeInterventionForm);

            // Check toggle button is off
            softAssert.assertFalse(driver.findElement(mandatoryToggleButton).isSelected(), "Toggle button is on");

            // Verify on each field type, toggle button is displayed
            click(fieldTypeInterventionForm);
            Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10));
            List<WebElement> FieldTypeDropdownValues = driver.findElements(fieldTypeInterventionFormValues);
            for (int i = 0; i < FieldTypeDropdownValues.size(); i++) {
                click(fieldTypeInterventionForm); // Click the dropdown again to refresh the options
                FieldTypeDropdownValues = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(fieldTypeInterventionFormValues)); // Wait for all options to be visible
                FieldTypeDropdownValues.get(i).click();
                Thread.sleep(1000);
                softAssert.assertTrue(driver.findElement(mandatoryToggleButton).isDisplayed(), "Toggle button is not present against ");
                softAssert.assertTrue(driver.findElement(deleteAttributeIcon).isDisplayed(), "Delete icon is not present against ");
                click(deleteAttributeIcon);
                Thread.sleep(1000);
                click(addAttributeInterventionForm);
                Thread.sleep(1000);
            }

            // Verifying previous stepper and next stepper functionality
            click(fieldTypeInterventionForm);
            click(stringFieldDropdownValue);
            click(fieldLabelInterventionForm);
            type(fieldLabelInterventionForm, "Field Label");
            type(attributeNameInterventionForm, "AttributeName");

            click(nextStepperInterventionType);

            click(previousStepperInterventionType);
            waitElementVisible(addAttributeInterventionForm);
            Thread.sleep(1500);

            click(cancelButtonInterventionType);
            Thread.sleep(1000);
            click(popupYesButton);
            waitElementInvisible(loading_cursor);
            waitElementVisible(interventionManagementTitle);

            click(createInterventionTypeButton);
            waitElementInvisible(loading_cursor);
            waitElementVisible(nameFieldInterventionForm);
            type(nameFieldInterventionForm, setInterventionName);
            type(displayFieldInterventionForm, interventionDisplay + currentTime);
            click(addAttributeInterventionForm);
            Thread.sleep(1500);

            click(fieldTypeInterventionForm);
            click(stringFieldDropdownValue);
            click(fieldLabelInterventionForm);
            type(fieldLabelInterventionForm, "Field Label");
            type(attributeNameInterventionForm, "AttributeName");

            click(nextStepperInterventionType);
            click(submitButton);
            waitElementInvisible(loading_cursor);

            softAssert.assertAll();
            getScreenshot();
            test.pass("Test case passed successfully");
            results.createNode("Toggle Button Functionality on intervention management screen passed successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Toggle Button Functionality on intervention management screen passed successfully");
            results.createNode("Toggle Button Functionality on intervention management screen passed successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Toggle Button Functionality on intervention management screen failed");
            results.createNode("Toggle Button Functionality on intervention management screen failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void verifyMultipleAttributesIntervention() throws IOException, InterruptedException {
        test = extent.createTest("Verify multiple attributes intervention type test case on Intervention Management Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        try {
            SoftAssert softAssert = new SoftAssert();
            driver.get(url_interventionManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            click(createInterventionTypeButton);
            waitElementVisible(nameFieldInterventionForm);
            long currentTime = setUniqueTime();
            String setInterventionName = interventionName + currentTime;
            type(nameFieldInterventionForm, setInterventionName);
            type(displayFieldInterventionForm, interventionDisplay + currentTime);
            int j = 0;
            for (int i = 1; i <= 3; i++) {
                click(addAttributeInterventionForm);
                click(By.id(fieldTypeInterventionFormGeneric + i));
                List<WebElement> elements = driver.findElements(stringFieldDropdownValue);
                elements.get(j).click(); // Select the second element (index 1)
                ++j;
                click(By.xpath(fieldLabelInterventionFormGeneric + i + "']"));
                type(By.xpath(fieldLabelInterventionFormGeneric + i + "']"), "Field Label" + i);
                type(By.xpath(attributeNameInterventionFormGeneric + i + "']"), "AttributeName" + i);
            }
            click(nextButtonInterventionForm);
            waitElementClickable(popupSubmitButton);
            click(popupSubmitButton);
            waitElementInvisible(loading_cursor);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Intervention type has been created successfully", "Intervention type with multiple attributes has not been created");
            softAssert.assertAll();
            getScreenshot();
            test.pass("Test case passed successfully");
            results.createNode("Intervention type with multiple attributes created successfully on intervention management screen");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Intervention type with multiple attributes test case passed successfully");
            results.createNode("Intervention type with multiple attributes test case passed successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Intervention type with multiple attributes test case failed");
            results.createNode("Intervention type with multiple attributes test case failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void createInterventionWithAllAttributes() throws IOException, InterruptedException {
        test = extent.createTest("Verify Create Intervention with all field types test case on Intervention Management Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        try {
            SoftAssert softAssert = new SoftAssert();
            driver.get(url_interventionManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            click(createInterventionTypeButton);
            waitElementVisible(nameFieldInterventionForm);
            long currentTime = setUniqueTime();
            String setInterventionName = interventionName + currentTime;
            type(nameFieldInterventionForm, setInterventionName);
            type(displayFieldInterventionForm, interventionDisplay + currentTime);

            Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10));
            List<WebElement> FieldTypeDropdownValues;
            int j =0;
            for (int i = 1; i <= 5; i++) {
                click(addAttributeInterventionForm);
                click(By.id(fieldTypeInterventionFormGeneric + i));
                By fieldTypeValues = By.cssSelector("#" + fieldTypeInterventionFormGeneric + i + " .ng-option");
                FieldTypeDropdownValues = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(fieldTypeValues)); // Wait for all options to be visible
                FieldTypeDropdownValues.get(j).click();
                click(By.xpath(fieldLabelInterventionFormGeneric + i + "']"));
                type(By.xpath(fieldLabelInterventionFormGeneric + i + "']"), "Field Label" + i);
                type(By.xpath(attributeNameInterventionFormGeneric + i + "']"), "AttributeName" + i);
                j++;
            }

            j = 5;
            for(int i=6; i <=9 ;i++) {
                click(addAttributeInterventionForm);
                click(By.id(fieldTypeInterventionFormGeneric + i));
                By fieldTypeValues = By.cssSelector("#" + fieldTypeInterventionFormGeneric + i + " .ng-option");
                FieldTypeDropdownValues = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(fieldTypeValues)); // Wait for all options to be visible
                FieldTypeDropdownValues.get(j).click();
                click(By.xpath(fieldLabelInterventionFormGeneric + i + "']"));
                type(By.xpath(fieldLabelInterventionFormGeneric + i + "']"), "Field Label" + i);
                type(By.xpath(attributeNameInterventionFormGeneric + i + "']"), "AttributeName" + i);
                j++;
                for(int k =1; k<=3; k++) {
                    type(By.id(optionValueText + i + "-" + k), "optionValue" + k);
                    type(By.id(descriptionValueText + i + "-" + k), "descriptionValue" + k);
                }
            }

            j =9;
            for (int i = 10; i <= 11; i++) {
                click(addAttributeInterventionForm);
                click(By.id(fieldTypeInterventionFormGeneric + i));
                By fieldTypeValues = By.cssSelector("#" + fieldTypeInterventionFormGeneric + i + " .ng-option");
                FieldTypeDropdownValues = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(fieldTypeValues)); // Wait for all options to be visible
                FieldTypeDropdownValues.get(j).click();
                click(By.xpath(fieldLabelInterventionFormGeneric + i + "']"));
                type(By.xpath(fieldLabelInterventionFormGeneric + i + "']"), "Field Label" + i);
                type(By.xpath(attributeNameInterventionFormGeneric + i + "']"), "AttributeName" + i);
                j++;
            }

            click(nextButtonInterventionForm);
            waitElementClickable(popupSubmitButton);
            click(popupSubmitButton);
            waitElementInvisible(loading_cursor);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Intervention type has been created successfully", "Intervention type with  all field types has not been created");
            softAssert.assertAll();
            getScreenshot();
            test.pass("Test case passed successfully");
            results.createNode("Intervention with all field types created successfully on intervention management screen");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Intervention with all field types test case passed successfully");
            results.createNode("Intervention with all field types test case passed successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Intervention with all field types test case failed");
            results.createNode("Intervention with all field types test case failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void checkTwoOptionsRequiredMessage(By multidropdownValue) throws InterruptedException, IOException {
        SoftAssert softAssert = new SoftAssert();
        click(createInterventionTypeButton);
        waitElementInvisible(loading_cursor);
        waitElementVisible(nameFieldInterventionForm);
        type(nameFieldInterventionForm, "interventionName");
        type(displayFieldInterventionForm, "interventionDisplay");
        click(addAttributeInterventionForm);
        click(fieldTypeInterventionForm);
        click(multidropdownValue);
        Thread.sleep(1000);
        softAssert.assertTrue(driver.findElement(addSelectionButton).isDisplayed(), "Add selection button is not present against " + multidropdownValue);
        click(addSelectionButton);
        softAssert.assertTrue(driver.findElement(addSelectionValue).isEnabled(), "Add selection value is not enabled against " + multidropdownValue);
        softAssert.assertTrue(driver.findElement(addSelectionDescription).isEnabled(), "Add selection description is not enabled against " + multidropdownValue);
        waitElementVisible(deleteOptionIcon);

        //Click on delete icon multiple times so only one delete icon is left to check the required error message
        for (int i = 0; i < 3; i++) {
            click(deleteOptionIcon);
            Thread.sleep(1000);
        }
        click(nextButtonInterventionForm);
        waitElementVisible(alertMessage);
        softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Please add at least two attribute options", "Message didn't appeared, test case failed");
        Thread.sleep(1500);
        getScreenshot();
        click(cancelButtonInterventionType);
        waitElementVisible(popupYesButton);
        Thread.sleep(1000);
        click(popupYesButton);
        waitElementInvisible(loading_cursor);
        softAssert.assertAll();
    }

    public void requiredValidationsInterventionType() throws IOException, InterruptedException {
        test = extent.createTest("Verify Required Validations on Create New Intervention type on Intervention Management Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        try {
            driver.get(url_interventionManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            SoftAssert softAssert = new SoftAssert();
            click(createInterventionTypeButton);
            waitElementInvisible(loading_cursor);
            waitElementVisible(nameFieldInterventionForm);
            type(nameFieldInterventionForm, "interventionName");
            type(displayFieldInterventionForm, "interventionDisplay");
            click(addAttributeInterventionForm);
            click(fieldTypeInterventionForm);
            click(integerFieldDropdownValue);
            type(fieldLabelInterventionForm, "123");
            type(attributeNameInterventionForm, "12345");

            softAssert.assertTrue(true, "Only numbers allowed");

            click(nextButtonInterventionForm);
            waitElementVisible(interventionCrossIcon);

            // Verify that on Review and submit screen the fields are disabled
            softAssert.assertTrue(!driver.findElement(interventionNameField).isEnabled(), "Input field is not disabled");

            click(interventionCrossIcon);
            waitElementVisible(popupYesButton);
            click(popupYesButton);
            Thread.sleep(1500);

            checkTwoOptionsRequiredMessage(dropdownMultiSelectField);
            checkTwoOptionsRequiredMessage(radioButtonMultiSelectField);
            checkTwoOptionsRequiredMessage(radioButtonSingleSelectField);
            checkTwoOptionsRequiredMessage(dropdownSingleSelectField);
            test.pass("Test case passed successfully");
            results.createNode("Verified at least two options are required for all of the dropdown field types and add selection functionality on intervention management screen");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Verify at least two options are required for all of the dropdown field types and add selection functionality test case passed successfully");
            results.createNode("Verify at least two options are required for all of the dropdown field types and add selection functionality test case passed successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Verify at least two options are required for all of the dropdown field types and add selection functionality test case failed");
            results.createNode("Verify at least two options are required for all of the dropdown field types and add selection functionality test case failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void dragAndDropInterventionType() throws IOException, InterruptedException {
        test = extent.createTest("Verify Drag and Drop Functionality on Create New Intervention type on Intervention Management Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        try {
            SoftAssert softAssert = new SoftAssert();
            driver.get(url_interventionManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            click(createInterventionTypeButton);
            waitElementVisible(nameFieldInterventionForm);
            long currentTime = setUniqueTime();
            String setInterventionName = interventionName + currentTime;
            type(nameFieldInterventionForm, setInterventionName);
            type(displayFieldInterventionForm, interventionDisplay + currentTime);

            int j = 0;
            for (int i = 1; i <= 2; i++) {
                click(addAttributeInterventionForm);
                click(By.id(fieldTypeInterventionFormGeneric + i));
                List<WebElement> elements = driver.findElements(stringFieldDropdownValue);
                elements.get(j).click();
                ++j;
                click(By.xpath(fieldLabelInterventionFormGeneric + i + "']"));
                type(By.xpath(fieldLabelInterventionFormGeneric + i + "']"), "FieldLabel" + i);
                type(By.xpath(attributeNameInterventionFormGeneric + i + "']"), "AttributeName" + i);
            }

            Actions act = new Actions(driver);
            // Drag and drop
            //    act.clickAndHold(dragFieldType).moveToElement(dropFieldType).release().build().perform();
            //    act.dragAndDropBy(dragElement, 0, 300).build().perform();
            //   act.dragAndDrop(dragElement, dropElement).build().perform();

            act.clickAndHold(dragElement).pause(Duration.ofSeconds(2)).moveToElement(optionHandle).pause(Duration.ofSeconds(2)).release().build().perform();

            // Verify dropdown options drag and drop functionality

            click(addAttributeInterventionForm);

            click(By.id(fieldTypeInterventionFormGeneric + "3"));
            click(dropdownSingleSelectField);
            click(By.xpath(fieldLabelInterventionFormGeneric + "3']"));
            type(By.xpath(fieldLabelInterventionFormGeneric + "3']"), "FieldLabel" + "3");
            type(By.xpath(attributeNameInterventionFormGeneric + "3']"), "AttributeName" + "3");

            // delete one option
            click(deleteOptionIcon);
            Thread.sleep(1000);

            type(optionValue, "1");
            type(optionValue2, "2");

            type(optionDescription, "OptionDes");
            type(optionDescription2, "OptionDes2");

            act.clickAndHold(optionHandle).pause(Duration.ofSeconds(2)).moveToElement(dropOption).pause(Duration.ofSeconds(2)).release().build().perform();

            Thread.sleep(1500);

            click(nextButtonInterventionForm);
            waitElementClickable(popupSubmitButton);
            click(popupSubmitButton);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Intervention type has been created successfully", "Intervention type has not been created by drag and drop attributes");

            // Open the edit form
            click(By.xpath("//label[@title='" + setInterventionName + "']" + editIcon));
            waitElementInvisible(loading_cursor);
            Thread.sleep(1500);
            // Verify that the field type has been dragged
            String inputValue = driver.findElement(fieldLabelInterventionForm).getAttribute("value");
            softAssert.assertEquals(inputValue, "FieldLabel1");

            softAssert.assertAll();
            getScreenshot();
            test.pass("Test case passed successfully");
            results.createNode("Verified Drag and Drop Functionality on intervention management screen");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Verify Drag and Drop Functionality test case passed successfully");
            results.createNode("Verify Drag and Drop functionality test case passed successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Verify Drag and Drop Functionality test case failed");
            results.createNode("Verify Drag and Drop Functionality test case failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void verifyToolTipFunctionality() throws IOException, InterruptedException {
        test = extent.createTest("Verify Drag and Drop Functionality on Create New Intervention type on Intervention Management Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        try {
            SoftAssert softAssert = new SoftAssert();
            driver.get(url_interventionManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            Actions action = new Actions(driver);
            String tooltipText;

            action.moveToElement(driver.findElement(resetFiltersIconIntervention)).perform();
            tooltipText = driver.findElement(resetFiltersIconIntervention).getAttribute("title");
            System.out.println("tooltip text: " + tooltipText);
            softAssert.assertEquals(tooltipText, expectedTooltipTextResetFilters);
            action.moveToElement(driver.findElement(fieldAccessIconIntervention)).perform();
            tooltipText = driver.findElement(fieldAccessIconIntervention).getAttribute("title");
            System.out.println("tooltip text: " + tooltipText);
            softAssert.assertEquals(tooltipText, expectedTooltipTextFieldAccess);

            if (driver.findElements(By.cssSelector(removeFiltersIconIntervention)).size() == 0) {
                // Get the tooltip text of the lock icon
                tooltipText = driver.findElement(unlockFilterIconIntervention).getAttribute("title");
                System.out.println("Tooltip text of lock icon: " + tooltipText);
                softAssert.assertEquals(tooltipText, expectedTooltipTextUnlocked);
                // Click on the lock icon to unlock it
                click(unlockFilterIconIntervention);
                Thread.sleep(2000);
                // Get the tooltip text of the unlock icon
                tooltipText = driver.findElement(lockFilterIconIntervention).getAttribute("title");
                System.out.println("Tooltip text of unlock icon: " + tooltipText);
                // Assert that the tooltip text of the unlock icon matches the expected text
                softAssert.assertEquals(tooltipText, expectedTooltipTextLocked);
            }
            if (driver.findElements(By.cssSelector(removeFiltersIconIntervention)).size() == 1) {
                // Get the tooltip text of the unlock icon
                tooltipText = driver.findElement(unlockFilterIconIntervention).getAttribute("title");
                softAssert.assertEquals(tooltipText, expectedTooltipTextUnlocked);
                // Click on the unlock icon to lock it
                click(lockFilterIconIntervention);
                Thread.sleep(2000);
                // Get the tooltip text of the lock icon
                tooltipText = driver.findElement(lockFilterIconIntervention).getAttribute("title");
                System.out.println("Tooltip text of lock icon: " + tooltipText);
                // Assert that the tooltip text of the lock icon matches the expected text
                softAssert.assertEquals(tooltipText, expectedTooltipTextLocked);
            }

            // Get the tooltip text of the logview
            tooltipText = driver.findElement(nameRowText).getAttribute("title");
            System.out.println("Tooltip text of name column text: " + tooltipText);
            softAssert.assertTrue(!tooltipText.isEmpty(), "tool tip is present");

            tooltipText = driver.findElement(displayNameRowText).getAttribute("title");
            System.out.println("Tooltip text of display column text: " + tooltipText);
            softAssert.assertTrue(!tooltipText.isEmpty(), "tool tip is present");

            tooltipText = driver.findElement(typeRowText).getAttribute("title");
            System.out.println("Tooltip text of type column text: " + tooltipText);
            softAssert.assertTrue(!tooltipText.isEmpty(), "tool tip is present");

            tooltipText = driver.findElement(dateCreatedRowText).getAttribute("title");
            System.out.println("Tooltip text of date created column text: " + tooltipText);
            softAssert.assertTrue(!tooltipText.isEmpty(), "tool tip is present");

            // Get the tooltip text of the edit icon
            tooltipText = driver.findElement(editIconRowText).getAttribute("title");
            System.out.println("Tooltip text of edit icon: " + tooltipText);
            // Assert that the tooltip text of the lock icon matches the expected text
            softAssert.assertEquals(tooltipText, editIconTooltipText);

            // Get the tooltip text of the delete icon
            tooltipText = driver.findElement(deleteIconRowText).getAttribute("title");
            System.out.println("Tooltip text of delete icon: " + tooltipText);
            // Assert that the tooltip text of the lock icon matches the expected text
            softAssert.assertEquals(tooltipText, deleteIconTooltipText);

            softAssert.assertAll();
            getScreenshot();
            test.pass("Test case passed successfully");
            results.createNode("Verified Tooltip Functionality on intervention management screen");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Verify Tooltip Functionality test case passed successfully");
            results.createNode("Verify Tooltip functionality test case passed successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Verify Tooltip Functionality test case failed");
            results.createNode("Verify Tooltip Functionality test case failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void verifyPaginationFunctionality() throws InterruptedException, IOException {
        driver.get(url_interventionManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        Pagination(interventionManagementTable, "Intervention Management");
    }

    public void verifyRequiredChecksLogIntervention() throws IOException, InterruptedException {
        test = extent.createTest("Verify Required Checks on Intervention Log form on Intervention Management Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        try {
            SoftAssert softAssert = new SoftAssert();
            driver.get(url_interventionManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            click(logInterventionTypeButton);
            waitElementInvisible(loading_cursor);

            click(selectComplexDropdown);
            driver.findElement(selectComplexSearch).sendKeys("Complex 1");
            enterKey(selectComplexSearch);
            click(complexSearchFieldValue);
            Actions actions = new Actions(driver);
            actions.click(interventionTypeField).sendKeys("inlineEditFunctionalityCheck").sendKeys(Keys.ENTER).build().perform();
            waitElementInvisible(loading_cursor);

            softAssert.assertTrue(driver.findElement(resetButton).isEnabled(), "Reset Button is not enabled");
            softAssert.assertTrue(driver.findElement(submitButton).isEnabled(), "Submit Button is not enabled");

            click(submitButton);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Please fill all the mandatory fields", "Mandatory message didn't appeared");
            Thread.sleep(2000);

            softAssert.assertAll();
            getScreenshot();
            test.pass("Test case passed successfully");
            results.createNode("Verified Required Mandatory checks on intervention log on intervention management screen");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Verify Required Mandatory checks on intervention log test case passed successfully");
            results.createNode("Verify Required Mandatory checks on intervention log test case passed successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Verify Required Mandatory checks on intervention log test case failed");
            results.createNode("Verify Required Mandatory checks on intervention log test case failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void resetButtonFunctionalityLogIntervention() throws IOException, InterruptedException {
        test = extent.createTest("Verify Reset Button functionality on Intervention Log form on Intervention Management Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        try {
            SoftAssert softAssert = new SoftAssert();
            driver.get(url_interventionManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            click(logInterventionTypeButton);
            waitElementInvisible(loading_cursor);

            click(selectComplexDropdown);
            driver.findElement(selectComplexSearch).sendKeys("Complex 1");
            enterKey(selectComplexSearch);
            click(complexSearchFieldValue);
            type(interventionNameField, "Intervention Name Field");
            Actions actions = new Actions(driver);
            actions.click(interventionTypeField).sendKeys("inlineEditFunctionalityCheck").sendKeys(Keys.ENTER).build().perform();
            waitElementInvisible(loading_cursor);
            actions.click(fieldLabelField).sendKeys("fieldLabelField").sendKeys(Keys.ENTER).build().perform();
            Thread.sleep(1000);

            click(resetButton);
            Thread.sleep(1000);

            String inputField = fieldLabelField.getAttribute("value");

            softAssert.assertEquals(inputField, "");

            softAssert.assertAll();
            getScreenshot();
            test.pass("Test case passed successfully");
            results.createNode("Verified Reset Button functionality on intervention log on intervention management screen");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Verify Reset Button functionality on intervention log test case passed successfully");
            results.createNode("Verify Reset Button functionality on intervention log test case passed successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Verify Reset Button functionality on intervention log test case failed");
            results.createNode("Verify Reset Button functionality on intervention log test case failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void viewInterventionDropdownFunctionality() throws IOException, InterruptedException {
        test = extent.createTest("Verify View Intervention dropdown functionality on Intervention Log form on Intervention Management Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        try {
            SoftAssert softAssert = new SoftAssert();
            driver.get(url_interventionManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            click(viewInterventionsDropdown);
            Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10));
            List<WebElement> values;
            String firstSelectedValue = null;
            // as first value in dropdown is already selected, we will loop through a minimum of 10 elements one by one to verify
            for (int i = 1; i <= 10; i++) {
                click(viewInterventionsDropdown); // Click the dropdown again to refresh the options
                values = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(viewInterventionsDropdownValues)); // Wait for all options to be visible
                values.get(i).click();
                waitElementInvisible(loading_cursor);
                WebElement selectedOption = driver.findElement(viewInterventionsDropdown);
                String selectedValue = selectedOption.getText();
                System.out.println(selectedValue);
                // Verify that only one value gets selected
                if (firstSelectedValue == null) {
                    firstSelectedValue = selectedValue;
                } else {
                    softAssert.assertTrue(!selectedValue.equals(firstSelectedValue), "same value got selected");
                }
            }

            click(viewInterventionsDropdown);
            click(dropdownValue);
            waitElementInvisible(loading_cursor);
            softAssert.assertTrue(fieldLabelColumn.isDisplayed(), "On selected intervention type attributes are not displayed");
            softAssert.assertAll();
            getScreenshot();
            test.pass("Test case passed successfully");
            results.createNode("Verified View Intervention dropdown functionality on intervention log on intervention management screen");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Verify View Intervention dropdown functionality on intervention log test case passed successfully");
            results.createNode("Verify View Intervention dropdown functionality on intervention log test case passed successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Verify View Intervention dropdown functionality on intervention mgt screen test case failed");
            results.createNode("Verify View Intervention dropdown functionality on intervention mgt screen test case failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void verifyContextualFilterFunctionality(By columnFilter, By columnCheckbox, By applyButton) throws InterruptedException {
        // Verify contextual filter functionality
        SoftAssert softAssert = new SoftAssert();
        String recordsBeforeFilter = driver.findElement(By.id(ResultsCount)).getText();
        int beforeFilter = Integer.parseInt(recordsBeforeFilter);

        click(columnFilter);
        waitElementInvisible(loading_cursor);
        click(columnCheckbox);
        Thread.sleep(1500);

        click(applyButton);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);


        String recordsAfterFilter = driver.findElement(By.id(ResultsCount)).getText();
        int afterFilter = Integer.parseInt(recordsAfterFilter);

        softAssert.assertEquals(afterFilter + 1, beforeFilter);

        click(resetFiltersIconIntervention);
        waitElementInvisible(loading_cursor);
        softAssert.assertAll();
    }

    public void viewContextualFilterFunctionality() throws InterruptedException, IOException {
        test = extent.createTest("Verify View Intervention dropdown functionality on Intervention Log form on Intervention Management Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        try {
            SoftAssert softAssert = new SoftAssert();
            driver.get(url_interventionManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            click(viewInterventionsDropdown);
            Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10));
            List<WebElement> values;
            click(viewInterventionsDropdown);
            values = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(viewInterventionsDropdownValues)); // Wait for all options to be visible
            values.get(1).click();
            waitElementInvisible(loading_cursor);

            verifyContextualFilterFunctionality(interventionNameColumnFilter, logInterventionNameCheckbox, logInterventionNameCheckboxApplyButton);
            verifyContextualFilterFunctionality(complexColumnFilter, logComplexCheckbox, logComplexCheckboxApplyButton);
            verifyContextualFilterFunctionality(fieldLabelColumnFilter, logAttributeCheckbox, logAttributeCheckboxApplyButton);

            getScreenshot();
            test.pass("Test case passed successfully");
            results.createNode("Verified View Intervention dropdown functionality on intervention log on intervention management screen");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Verify View Intervention dropdown functionality on intervention log test case passed successfully");
            results.createNode("Verify View Intervention dropdown functionality on intervention log test case passed successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Verify View Intervention dropdown functionality on intervention mgt screen test case failed");
            results.createNode("Verify View Intervention dropdown functionality on intervention mgt screen test case failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void getLastCreatedIntervention() throws SQLException {
        if (Constants.config.url().contains("qa") || Constants.config.url().contains("dev")) {
            ResultSet getInterventionResult = DB_Config_DB.getStmt().executeQuery(Queries.getLastCreatedIntervention());

            while (getInterventionResult.next()) {
                String lastCreatedInterventionDisplay = getInterventionResult.getString("ENTITY_TYPE_DISPLAY");
                System.out.println("Intervention Display Name: " + lastCreatedInterventionDisplay);
                click(viewInterventionsDropdown);
                type(viewInterventionsDropdown, lastCreatedInterventionDisplay);
                enterKey(viewInterventionsDropdown);
                waitElementInvisible(loading_cursor);
            }
        }
    }

}

