package PageObjects;

import MiscFunctions.Constants;
import MiscFunctions.NavigateToScreen;
import Models.ProgramManagementModel;
import com.aventstack.extentreports.gherkin.model.Scenario;
import org.aeonbits.owner.Config;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;
import test.v6.B;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static MiscFunctions.Constants.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.ExtentVariables.Results;
import static MiscFunctions.Methods.*;

import static LogViewFunctions.FilterLock.*;
import static LogViewFunctions.FilterWildcard.*;
import static PageObjects.BasePage.*;

import static MiscFunctions.ExtentVariables.Steps;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.steps;
import static MiscFunctions.ExtentVariables.test;
import static PageObjects.BasePage.loading_cursor;
import static Config.BaseTest.*;
import static PageObjects.LoginPage.*;
public class InterventionManagementPage {

    public WebDriver driver;
    public static String text;
    public static String interventionManagementTable = "manage-intervention-management-log";
    public static By interventionManagementTitle = By.id("Intervention Management");
    public static By inlineEditIconIntervention = By.cssSelector("#intervention-management-log #edit-inline-access");
    public static By inlineEditIconSaveChanges = By.cssSelector("#intervention-management-log #edit-inlineSave-access");
    public static By inlineEditInterventionName = By.cssSelector("#col-0-1-entityTypeManagement input[formcontrolname='name']");
    public static By inlineEditComplex = By.xpath("//td[@id='col-0-2-entityTypeManagement']/label");
    public static By inlineEditFieldLabel = By.cssSelector("#col-0-3-entityTypeManagement input[formcontrolname='name']");
    public static By mandatoryCheckInterventionName = By.xpath("//div[contains(text(),'Intervention name is required')]");
    public static By popupCrossIcon = By.xpath("//img[@id='close-confirmation']");
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
    public static By viewInterventionsDropdown = By.cssSelector("#entityTypeId");
    public static By dropdownValue = By.xpath("//span[contains(text(),'inlineEditFunctionalityCheck')]");
    public static By createInterventionTypeButton = By.xpath("//a[contains(text(),'Create Intervention Type')]");
    public static By logInterventionTypeButton = By.xpath("//a[contains(text(),'Log Intervention')]");
    public static By selectComplexDropdown = By.cssSelector("#compleSiteId .toggle-list");
    public static By selectComplexSearch = By.id("compleSiteId_search");
    public static By complexSearchFieldValue = By.xpath("//b[contains(text(),'Complex 1')]");
    public static By nameFieldInterventionForm = By.id("entityTypeName");
    @FindBy(how = How.ID, using = "entityTypeName")
    public WebElement nameField;
    @FindBy(how = How.ID, using = "entityTypeDisplay")
    public WebElement displayField;
    @FindBy(how = How.CSS, using = "#interventionTypeId")
    public WebElement interventionTypeField;
    @FindBy(how = How.XPATH, using = "//label[contains(text(), 'Field Label')]")
    public WebElement fieldLabelField;
    String interventionName = "Intervention_";
    public static String editIcon = "/ancestor::tr//span[contains(@title, 'Edit')]";
    public static String deleteIcon = "/ancestor::tr//span[contains(@title, 'Delete')]";
    public static By displayFieldInterventionForm = By.id("entityTypeDisplay");

    String interventionDisplay = "InterventionDisplay_";
    public static By addAttributeInterventionForm = By.xpath("//button[contains(text(),'Add Attribute')]");
    public static By fieldTypeInterventionForm = By.id("fieldType-1");
    public static By stringFieldDropdownValue = By.xpath("//span[contains(text(),'StringField')]");
    public static By numberFieldDropdownValue = By.cssSelector("//span[@class='ng-option-label' and text()='NumberField']");
    public static By fieldLabelInterventionForm = By.xpath("//input[@id='fieldLabel-1']");
    public static By attributeNameInterventionForm = By.xpath("//input[@id='attributeName - 1']");
    public static By nextButtonInterventionForm = By.xpath("//a[contains(text(),'Next')]");
    //  public static WebElement interventionTypeField = By.cssSelector("#interventionTypeId");
    public static By interventionNameField = By.cssSelector("#input-id");
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
        Lock(interventionManagementTable, "Intervention Management", 1);
    }

    public void wildcardFunctionality() throws InterruptedException, IOException {
        driver.get(url_interventionManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        Wildcard(interventionManagementTable, "Intervention Management", 1);
    }

    public void iconsPresenceOnInlineEditFunctionality() throws IOException, InterruptedException {
        test = extent.createTest("Verify icons reset and unlock button are hidden in inline mode on Intervention Management Screen ");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        try {
            driver.get(url_interventionManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            SoftAssert softAssert = new SoftAssert();
            click(viewInterventionsDropdown);
            click(dropdownValue);
            waitElementInvisible(loading_cursor);
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
    }

    public long setUniqueTime() throws IOException {

        // Get current timestamp
        long timestampInSeconds = System.currentTimeMillis() / 1000;

        // Combine timestamp and random number to create a unique name

        return timestampInSeconds;
    }

    public String createInterventionType() throws IOException {
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
            click(By.xpath("//label[@title='" + updatedNameField + "']" + deleteIcon));
            waitElementInvisible(loading_cursor);
            click(popupYesButton);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Intervention Type Deleted Successfully", "Intervention type has not been deleted.");
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

    public void crudLogIntervention() throws IOException, InterruptedException {
        test = extent.createTest("Verify Crud Log Intervention functionality on Intervention Management Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
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
            click(logInterventionTypeButton);
            waitElementInvisible(loading_cursor);
            click(selectComplexDropdown);
            driver.findElement(selectComplexSearch).sendKeys("Complex 1");
            enterKey(selectComplexSearch);
            click(complexSearchFieldValue);
            type(interventionNameField, "Intervention Name Field");
            Actions actions = new Actions(driver);
            actions.click(interventionTypeField).sendKeys(displayFieldValue).sendKeys(Keys.ENTER).build().perform();
            waitElementInvisible(loading_cursor);
            actions.click(fieldLabelField).sendKeys("field Label Field Value").sendKeys(Keys.ENTER).build().perform();
            //click(resetButton);
            click(submitButton);
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
            click(By.xpath("//label[@title='" + interventionName + "']" + editIcon));
            waitElementInvisible(loading_cursor);
            clear(nameFieldInterventionForm);
            type(nameFieldInterventionForm, "duplicate");
            click(nextButtonInterventionForm);
            waitElementInvisible(loading_cursor);
            click(submitButton);
            click(By.xpath("//label[@title='" + "duplicate" + "']" + deleteIcon));
            waitElementInvisible(loading_cursor);
            click(popupYesButton);
            waitElementInvisible(loading_cursor);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Intervention Type Deleted Successfully", "Intervention type has not been deleted.");
            String interventionNameDuplicate = createInterventionType();
            click(By.xpath("//label[@title='" + interventionNameDuplicate + "']" + editIcon));
            waitElementInvisible(loading_cursor);
            clear(nameFieldInterventionForm);
            type(nameFieldInterventionForm, "duplicate");
            click(nextButtonInterventionForm);
            waitElementClickable(submitButton);
            click(submitButton);
            click(By.xpath("//label[@title='" + "duplicate" + "']" + deleteIcon));
            waitElementInvisible(loading_cursor);
            click(popupYesButton);
            waitElementInvisible(loading_cursor);
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

    public void inlineEditIntervention() throws IOException, InterruptedException {
        test = extent.createTest("Verify Inline Edit Functionality on Intervention Management Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        try {
            driver.get(url_interventionManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            SoftAssert softAssert = new SoftAssert();
            click(viewInterventionsDropdown);
            click(dropdownValue);
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
            long currentTime = setUniqueTime();
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

    public void inlineEditInterventionCheckActions() throws IOException, InterruptedException {
        test = extent.createTest("Verify Inline Edit Intervention on Intervention Management Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        try {
            driver.get(url_interventionManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            SoftAssert softAssert = new SoftAssert();
            click(viewInterventionsDropdown);
            click(dropdownValue);
            waitElementInvisible(loading_cursor);
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
    }

    public void inlineEditNavigateToScreen() throws IOException, InterruptedException {
        test = extent.createTest("Verify Inline Edit Intervention on Intervention Management Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        try {
            driver.get(url_interventionManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            SoftAssert softAssert = new SoftAssert();
            click(viewInterventionsDropdown);
            click(dropdownValue);
            waitElementInvisible(loading_cursor);
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
            softAssert.assertEquals(actualUrl, url_user,"The user did not navigate to the correct url");

            // Verify on NO, the user navigates to the screen and changes are NOT saved
            driver.get(url_interventionManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            click(viewInterventionsDropdown);
            click(dropdownValue);
            waitElementInvisible(loading_cursor);
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
            softAssert.assertEquals(actualUrl, url_user,"The user did not navigate to the correct url");

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
            if (createRightsInput.isSelected() == true) {
                click(createRightsCheckbox);
                Thread.sleep(1000);
            }
            if (viewRightsInput.isSelected() == true) {
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

    public void inlineEditAccessRights() throws IOException {

        //GO to intervention management screen and verify inline edit icon is clickable
        SoftAssert softAssert = new SoftAssert();
        driver.get(url_interventionManagement);
        waitElementInvisible(loading_cursor);
        click(viewInterventionsDropdown);
        click(dropdownValue);
        waitElementInvisible(loading_cursor);
        softAssert.assertTrue(driver.findElement(inlineEditIconIntervention).isEnabled(), "Inline Edit icon is clickable");
        getScreenshot();

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
            if (updateRightsInput.isSelected() == false) {
                click(updateRightsCheckbox);
                Thread.sleep(1000);
            }
            if (viewRightsInput.isSelected() == false) {
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
    public void inlineEditAudit() throws IOException, InterruptedException {
        test = extent.createTest("Verify mandatory check as well as audit on Intervention Management Screen");
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
            click(inlineEditIconIntervention);
            waitElementInvisible(loading_cursor);
            clear(inlineEditInterventionName);
            click(inlineEditInterventionName);
            Thread.sleep(2000);
            // Mandatory check appears
            click(inlineEditIconSaveChanges);
            waitElementInvisible(loading_cursor);
            click(popupYesButton);
            waitElementInvisible(loading_cursor);
            softAssert.assertEquals(size(mandatoryCheckInterventionName), 1, "Required validation error didn't appeared");
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Please fill all required fields", "Mandatory field check missing");

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
    public void verifySorting()throws IOException, InterruptedException {
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
}
