/* This java class provides WebElements and function methods for Survey Management screen
 *
 * @author Eman Arshad*/
package PageObjects;

import Config.BaseTest;
import MiscFunctions.Methods;
import com.aventstack.extentreports.gherkin.model.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static Config.BaseTest.saveResult;
import static LogViewFunctions.Pagination.Pagination;
import static MiscFunctions.Constants.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import static PageObjects.BasePage.loading_cursor;

public class SurveyManagementPage {




        public WebDriver driver;
        public static String text;



        public static By SurveyManagementPageTitle = By.id("Form Management");

        @FindBy(how = How.ID, using = "edit-field-access")
        private WebElement fieldAccessButton;
        @FindBy(how = How.ID, using = "module_show-filter")
        private WebElement moduleColumn;
        @FindBy(how = How.ID, using = "formName_show-filter")
        private WebElement formNameColumnFilter;
        @FindBy(how = How.ID, using = "formTypeName_show-filter")
        private WebElement formTypeColumnFilter;
        @FindBy(how = How.ID, using = "status_show-filter")
        private WebElement statusColumnFilter;
        //  this will locate element on 1st column filter checkbox at index 3
        @FindBy(how = How.CSS, using = "th:nth-child(3) li:nth-child(1) label")
        private WebElement facilitySafeCultureItem;
        @FindBy(how = How.ID, using = "insDte_show-filter")
        private WebElement dateCreatedColumn;
        @FindBy(how = How.CSS, using = "th:nth-child(5) li:nth-child(3) label")
        private WebElement dateItem;

        @FindBy(how = How.ID, using = "Access Management")
        private WebElement accessManagementPageHeader;
        @FindBy(how = How.ID, using = "edit-role-rights-0")
        private WebElement editRightsIcon;
        @FindBy(how = How.ID, using = "isCreateForm Management")
        private WebElement createFormManagementRight;
        @FindBy(how = How.ID, using = "formName_sort")
        private WebElement formNameColumnSort;
        @FindBy(how = How.ID, using = "formTypeName_sort")
        private WebElement formTypeNameColumnSort;
        @FindBy(how = How.ID, using = "module_sort")
        private WebElement moduleColumnSort;
        @FindBy(how = How.ID, using = "status_sort")
        private WebElement statusColumnSort;
        @FindBy(how = How.ID, using = "insDte_sort")
        private WebElement dateCreatedColumnSort;
        @FindBy(how = How.ID, using = "next-page")
        private WebElement nextPageIcon;
        @FindBy(how = How.ID, using = "last-page")
        private WebElement lastPageIcon;
        @FindBy(how = How.ID, using = "previous-page")
        private WebElement previousPageIcon;
        @FindBy(how = How.ID, using = "first-page")
        private WebElement firstPageIcon;
        @FindBy(how = How.ID, using = "manage-user")
        public static String formManagementTable;


        @FindBy(how = How.XPATH, using = "//tbody/tr[5]/td[4]/label[1]/div[1]/label[1]")
        private WebElement dateCreatedRadioButton;

        @FindBy(how = How.ID, using = "btn-save")
        private WebElement fieldAccessSaveButton;
        @FindBy(how = How.ID, using = "btn-reset")
        private WebElement fieldAccessResetButton;
        @FindBy(how = How.ID, using = "results-found-count")
        private WebElement ResultsFoundCountText;
        @FindBy(how = How.ID, using = "remove-filters")
        private WebElement unlockFiltersButton;
        @FindBy(how = How.ID, using = "message")
        private WebElement formChangesSavedLabel;
        @FindBy(how = How.ID, using = "create-form")
        private WebElement newFormButton;
        @FindBy(how = How.ID, using = "delete-survey-management-1-surveyManagement")
        private WebElement deleteIcon;
        @FindBy(how = How.ID, using = "view-survey-gram-1-surveyManagement")
        private WebElement viewActionIcon;
        @FindBy(how = How.ID, using = "btn-yes")
        private WebElement deleteYesButton;
        @FindBy(how = How.ID, using = "btn-no")
        private WebElement deleteNoButton;
        @FindBy(how = How.ID, using = "close-confirmation")
        private WebElement closeDeletePopup;
        @FindBy(how = How.ID, using = "edit-survey-gram-1-surveyManagement")
        private WebElement editIcon;
        @FindBy(how = How.ID, using = "close-popup-modal")
        private WebElement crossEditform;

        @FindBy(how = How.ID, using = "view-able-2")
        private WebElement checkbox;
        @FindBy(how = How.ID, using = "btn-reset")
        private WebElement editCancelButton;
        @FindBy(how = How.ID, using = "btn-next")
        private WebElement editNextButton;
        @FindBy(how = How.ID, using = "close-popup-modal")
        private WebElement crossButtonNewForm;

        @FindBy(how = How.ID, using = "surveyName")
        private WebElement formNameField;
        @FindBy(how = How.CLASS_NAME, using = "survey-textBox-Area ng-pristine ng-valid ng-touched")
        private WebElement formDescriptionField;
        @FindBy(how = How.ID, using = "survey-btn-1")
        private WebElement selectSurveyButton;
        @FindBy(how = How.ID, using = "survey-btn-0")
        private WebElement selectGeneralButton;
        @FindBy(how = How.ID, using = "survayModule")
        private WebElement selectSurveyModule;
        @FindBy(how = How.ID, using = "btn-next")
        private WebElement nextButtonForm;
        @FindBy(how = How.ID, using = "btn-save")
        private WebElement submitButtonForm;
        @FindBy(how = How.ID, using = "btn-back")
        private WebElement backButtonNewForm;
        @FindBy(how = How.ID, using = "btn-reset")
        private WebElement cancelButtonNewForm;
        @FindBy(how = How.XPATH, using = "//span[contains(text(),'Exclude Result')]")
        private WebElement excludeResultText;
        @FindBy(how = How.XPATH, using = "//*[text()=' Add Category ']")
        private WebElement addCategoryButton;
        @FindBy(how = How.ID, using = "categoryName")
        private WebElement categoryNameField;
        @FindBy(how = How.CLASS_NAME, using = "fa fa-trash  survey-trash-icon")
        private WebElement categoryDeleteIcon;
        @FindBy(how = How.ID, using = "categoryWeightFactor")
        private WebElement categoryWeightField;
        @FindBy(how = How.ID, using = "open-profile")
        private WebElement profileSettingsButton;
        @FindBy(how = How.ID, using = "close-profile")
        private WebElement profileSettingsBackButton;
        @FindBy(how = How.ID, using = "view-survey-gram-1-surveyManagement")
        private WebElement surveyViewButton;
        @FindBy(how = How.ID, using = "edit-survey-gram-1-surveyManagement")
        private WebElement surveyEditButton;
        @FindBy(how = How.CLASS_NAME, using = "popup-head ng-tns-c17-0 ng-star-inserted")
        private WebElement FormHeader;
        @FindBy(how = How.ID, using = "generalTextId")
        private WebElement surveyTextField;
        @FindBy(how = How.ID, using = "editModeActiveBtn")
        private WebElement activeToggleButton;
        @FindBy(how = How.ID, using = "questionInputFieldId")
        private WebElement questionInputField;
        @FindBy(how = How.ID, using = "cdk-drop-list-29")
        private WebElement dropField;
        @FindBy(how = How.ID, using = "delete0")
        private WebElement questionInputFieldDeleteIcon;
        @FindBy(how = How.XPATH, using = "//div[@label=' What kind of form do you want to create?' AND @class='form-group col-md-12 col-lg-12 col-sm-12 survey-label ']")
        private WebElement labelToGetText;
        @FindBy(how = How.ID, using = "menu-administration")
        private WebElement menuIcon;
        @FindBy(how = How.ID, using = "roleMGMTManageSurveyManagement")
        private WebElement surveyManagementScreen;

        @FindBy(how = How.ID, using = "progressbar-step-2")
        private WebElement surveyFormTab2;
        @FindBy(how = How.ID, using = "progressbar-step-3")
        private WebElement surveyFormTab3;


        private static String getRecordsFoundText;
        private static By fieldAccessForm = By.id("close-popup-modal");
        private static By newForm = By.id("create-form");
        private static By confirmationMessage = By.className("confirmation-message");
        private static By editFormHeader = By.className("popup-header");
        private static By editFormBackButton = By.id("btn-back");
        private static By verifyBackButtonNewForm = By.id("btn-back");
        private static By verifyCancelButtonNewForm = By.id("btn-reset");
        private static By categoryName = By.id("categoryName");
        private static By surveyManagementTab = By.id("surveyManagementTab");
        private static By firstNameFieldProfileSection = By.id("firstNameId");
        private static By FormHeaderText = By.id("popup-head ng-tns-c17-0 ng-star-inserted");

        //Create object of Survey Management Class
        //Constructor
        public SurveyManagementPage(WebDriver driver) {
                this.driver = driver;
                //Initialise Elements
                PageFactory.initElements(driver, this);
        }
        public void verifyTooltip() {
                String tooltipText = fieldAccessButton.getAttribute("title");
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertTrue(tooltipText.equals("Field Access"), "First soft assert failed");
        }


        public void verifyFormDataCount() throws InterruptedException {
                //Apply Filters on Module and Date Created Columns
                moduleColumn.click();
                Thread.sleep(3000);
                facilitySafeCultureItem.click();
                Thread.sleep(3000);
                dateCreatedColumn.click();
                Thread.sleep(3000);
                dateItem.click();
                fieldAccessButton.click();
                Thread.sleep(3000);
                // moduleRadioButton.click();
                Thread.sleep(3000);
                dateCreatedRadioButton.click();
                fieldAccessSaveButton.click();
                Thread.sleep(3000);
                getRecordsFoundText = ResultsFoundCountText.getText();
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertTrue(getRecordsFoundText.equals("9"), "9 records found");
        }

        public void verifyFormUpdatedDate() throws InterruptedException {
                //Reset the field access form
                fieldAccessButton.click();
                Thread.sleep(3000);
                fieldAccessResetButton.click();
                fieldAccessSaveButton.click();
                dateCreatedColumn.click();
        }

        public void verifyFormDataCountRefresh() throws InterruptedException {
                unlockFiltersButton.click();
                driver.navigate().refresh();
                getRecordsFoundText = ResultsFoundCountText.getText();
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertTrue(getRecordsFoundText.equals("9"), "9 records found");
        }

        public static void NavigateToScreen(String url,  String name, By id) throws InterruptedException, IOException {
                try{
                        test = extent.createTest("AN-01: Navigate to "+name+" Screen");
                        steps = test.createNode(Scenario.class, Steps);

                        steps.createNode("1. Hover to sidebar to expand the menu");
                        steps.createNode("2. Select "+name+" from side bar menu");
                        BaseTest drive = new BaseTest();
                        drive.getDriver().get(url);
                        waitElementInvisible(loading_cursor);
                        Methods.waitElementVisible(id);
                        Thread.sleep(3000);
                        Assert.assertEquals(drive.getDriver().findElement(id).getText(), ""+name);
                        test.pass("User navigated to "+name+" screen successfully");
                        getScreenshot();
                        saveResult(ITestResult.SUCCESS, null);
                }catch(AssertionError er){
                        test.fail("User failed to navigate to "+name+" screen");
                        saveResult(ITestResult.FAILURE, new Exception(er));
                }
                catch(Exception ex){
                        test.fail("User failed to navigate to "+name+" screen");
                        saveResult(ITestResult.FAILURE, ex);
                }
        }
        public void resetDefaultButtonFunctionality() throws InterruptedException {
                fieldAccessButton.click();
                Thread.sleep(3000);
                fieldAccessResetButton.click();
                Thread.sleep(1000);
                fieldAccessSaveButton.click();
                waitElementVisible(newForm);
                fieldAccessButton.click();
                waitElementVisible(fieldAccessForm);
                Thread.sleep(5000);
                WebElement checkbox = driver.findElement(By.xpath("//input[@id='view-able-2']"));
                //((JavascriptExecutor)driver).executeScript("var elem=arguments[0]; setTimeout(function() {elem.click();}, 100)", checkbox);
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", checkbox);
                //waitElementVisible(cb));
                //checkbox.click();
                Thread.sleep(3000);
                fieldAccessSaveButton.click();
                waitElementVisible(newForm);
                Thread.sleep(1000);
                fieldAccessButton.click();
                waitElementVisible(fieldAccessForm);
                Thread.sleep(1000);
                fieldAccessResetButton.click();
                fieldAccessSaveButton.click();
                waitElementVisible(newForm);
                //  text = formChangesSavedLabel.getText();
                // SoftAssert softAssert = new SoftAssert();
                // softAssert.assertEquals(text,"Field Access changes have been saved successfully.");
        }

        public void selectionOfColumnsFunctionality() throws InterruptedException {
                //text = formNameColumnHeading.getText();
                fieldAccessButton.click();
                waitElementVisible(fieldAccessForm);
                fieldAccessResetButton.click();
                Thread.sleep(3000);
                fieldAccessSaveButton.click();
                waitElementVisible(newForm);
                fieldAccessButton.click();
                waitElementVisible(fieldAccessForm);
                //not displaying first column on logview
                WebElement checkbox = driver.findElement(By.id("view-able-0"));
                //((JavascriptExecutor)driver).executeScript("var elem=arguments[0]; setTimeout(function() {elem.click();}, 100)", checkbox);
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", checkbox);
                Thread.sleep(1000);
                checkbox.click();
                fieldAccessSaveButton.click();
                waitElementVisible(newForm);
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertNotEquals(text, "Form Name");

        }

        public void deselectionOfColumnsFunctionality() throws InterruptedException {
                // text = formNameColumnHeading.getText();
                fieldAccessButton.click();
                  waitElementVisible(fieldAccessForm);
                fieldAccessResetButton.click();
                fieldAccessSaveButton.click();
            waitElementVisible(newForm);
                fieldAccessButton.click();
            waitElementVisible(fieldAccessForm);
                //not displaying first column on logview
                WebElement checkbox = driver.findElement(By.id("view-able-0"));
                ((JavascriptExecutor) driver).executeScript("var elem=arguments[0]; setTimeout(function() {elem.click();}, 100)", checkbox);
                Thread.sleep(1000);
                checkbox.click();
                WebElement checkbox2 = driver.findElement(By.id("view-able-1"));
                ((JavascriptExecutor) driver).executeScript("var elem=arguments[0]; setTimeout(function() {elem.click();}, 100)", checkbox);
                Thread.sleep(1000);
                checkbox2.click();
                fieldAccessSaveButton.click();
            waitElementVisible(newForm);
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertNotEquals(text, "Form Name");

        }

        public void deselectionOfKeyColumnsFunctionality() throws InterruptedException {
                String formNameHeading = formNameColumnSort.getText();
                String formTypeNameHeading = formTypeNameColumnSort.getText();
                String statusHeading = statusColumnSort.getText();
                fieldAccessButton.click();
            waitElementVisible(fieldAccessForm);
                fieldAccessResetButton.click();
                fieldAccessSaveButton.click();
            waitElementVisible(newForm);
                fieldAccessButton.click();
            waitElementVisible(fieldAccessForm);
                //not displaying first column on logview
                WebElement checkbox = driver.findElement(By.id("view-able-0"));
                ((JavascriptExecutor) driver).executeScript("var elem=arguments[0]; setTimeout(function() {elem.click();}, 100)", checkbox);
                Thread.sleep(1000);
                checkbox.click();
                WebElement checkbox2 = driver.findElement(By.id("view-able-1"));
                ((JavascriptExecutor) driver).executeScript("var elem=arguments[0]; setTimeout(function() {elem.click();}, 100)", checkbox2);
                Thread.sleep(1000);
                checkbox2.click();
                WebElement checkbox4 = driver.findElement(By.id("view-able-1"));
                ((JavascriptExecutor) driver).executeScript("var elem=arguments[0]; setTimeout(function() {elem.click();}, 100)", checkbox4);
                Thread.sleep(1000);
                checkbox4.click();
                fieldAccessSaveButton.click();
            waitElementVisible(newForm);
                //Verify Form Name, Form Type and Status columns are shown on logview
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertEquals(formNameHeading, "Form Name");
                SoftAssert softAssert1 = new SoftAssert();
                softAssert1.assertNotEquals(text, "Form Type");
                SoftAssert softAssert2 = new SoftAssert();
                softAssert1.assertNotEquals(text, "Status");
        }

        public void deleteIconFunctionality() throws InterruptedException {
                deleteIcon.click();
            waitElementVisible(confirmationMessage);
                closeDeletePopup.click();
            waitElementVisible(newForm);
        }

        public void deleteSurveyForNoQuestionsAnswered() throws InterruptedException {
                deleteIcon.click();
            waitElementVisible(confirmationMessage);
                deleteYesButton.click();
                Thread.sleep(1000);
            waitElementVisible(newForm);
        }

        public void presenceOfDeleteIcon() throws InterruptedException {
                String title = deleteIcon.getAttribute("title");
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertEquals(title, "Delete Form");
        }

        public void crossButtonOfEditingPopup() throws InterruptedException {
                editIcon.click();
            waitElementVisible(editFormHeader);
                crossEditform.click();
            waitElementVisible(newForm);
        }

        public void cancelButtonOfEditingPopup() throws InterruptedException {
                editIcon.click();
            waitElementVisible(editFormHeader);
                editCancelButton.click();
            waitElementVisible(newForm);
        }

        public void nextButtonOfEditingPopup() throws InterruptedException {
                editIcon.click();
            waitElementVisible(editFormHeader);
                editNextButton.click();
            waitElementVisible(editFormBackButton);
        }

        public void presenceOfEditingPopup() throws InterruptedException {
                editIcon.click();
                waitElementVisible(editFormHeader);
                crossEditform.click();
                waitElementVisible(newForm);
        }

        public void crossIconOfNewForm() throws InterruptedException {
                newFormButton.click();
                waitElementVisible(editFormHeader);
                crossButtonNewForm.click();
                waitElementVisible(newForm);
        }

        public void setFormNameField(String formName) {
                formNameField.clear();
                formNameField.sendKeys(formName);
        }

        public void setSelectSurveyModule(String SurveyModule) {
                selectSurveyModule.clear();
                selectSurveyModule.sendKeys(SurveyModule);
        }

        public void setFormDescriptionField(String formDescription) {
                formDescriptionField.clear();
                formDescriptionField.sendKeys(formDescription);
        }

        public void enabledNextButtonNewForm() throws InterruptedException {
                newFormButton.click();
                waitElementVisible(editFormHeader);
                setFormNameField("test form name field");
                selectSurveyButton.click();
                setSelectSurveyModule("Facility Safety Culture");
                //Verify Next Button gets enabled or not
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertEquals(true, nextButtonForm.isEnabled());
                crossButtonNewForm.click();
                waitElementVisible(newForm);
                softAssert.assertAll();
        }

        public void backButtonNewForm() throws InterruptedException {
                newFormButton.click();
                waitElementVisible(editFormHeader);
                setFormNameField("test form name field");
                selectSurveyButton.click();
                setSelectSurveyModule("Facility Safety Culture");
                nextButtonForm.click();
                waitElementVisible(verifyBackButtonNewForm);
                backButtonNewForm.click();
                waitElementVisible(verifyCancelButtonNewForm);
                cancelButtonNewForm.click();
                waitElementVisible(newForm);
        }

        public void disabledNextButtonNewForm() throws InterruptedException {
                newFormButton.click();
                waitElementVisible(editFormHeader);
                //Verify Next Button is disabled
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertEquals(false, nextButtonForm.isEnabled());
                setFormNameField("test form name field");
                //Again verify Next Button is disabled
                softAssert.assertEquals(false, nextButtonForm.isEnabled());
                selectSurveyButton.click();
                //Again verify Next Button is disabled
                softAssert.assertEquals(false, nextButtonForm.isEnabled());
                crossButtonNewForm.click();
                waitElementVisible(newForm);
                softAssert.assertAll();
        }

        public void dateColumnHiddenNewForm() throws InterruptedException {

        }

        public void excludeResultToggleButtonPresence() throws InterruptedException {
                newFormButton.click();
                waitElementVisible(fieldAccessForm);
                //Verifying the presence of the Exclude Result Toggle button
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertEquals(true, excludeResultText.isDisplayed());
                softAssert.assertAll();
        }

        public void nextButtonFunctionalityNewForm() throws InterruptedException {
                newFormButton.click();
                waitElementVisible(editFormHeader);
                setFormNameField("test form name field");
                selectSurveyButton.click();
                setSelectSurveyModule("Facility Safety Culture");
                nextButtonForm.click();
                waitElementVisible(verifyBackButtonNewForm);
                crossButtonNewForm.click();
                waitElementVisible(newForm);

        }

        public void cancelButtonOfNewForm() throws InterruptedException {
                newFormButton.click();
                waitElementVisible(editFormHeader);
                cancelButtonNewForm.click();
                waitElementVisible(newForm);
        }

        public void deleteAddCategoryNewForm() throws InterruptedException {
                newFormButton.click();
                waitElementVisible(editFormHeader);
                addCategoryButton.click();
                waitElementVisible(categoryName);
                categoryDeleteIcon.click();
                waitElementVisible(editFormHeader);
                cancelButtonNewForm.click();
                waitElementVisible(newForm);
        }

        public void addCategoryInputFieldsCheck() throws InterruptedException {
                newFormButton.click();
                waitElementVisible(editFormHeader);
                addCategoryButton.click();
                waitElementVisible(categoryName);
                //Verify Add Category Section has an input field
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertEquals(true, categoryNameField.isDisplayed());
                softAssert.assertEquals(true, categoryWeightField.isDisplayed());
                softAssert.assertAll();
                crossButtonNewForm.click();
                waitElementVisible(newForm);
        }

        public void formTitleNameInputField() throws InterruptedException {
                newFormButton.click();
                waitElementVisible(editFormHeader);
                //Verify Form Title Name is an input field
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertEquals(true, formNameField.isDisplayed());
                setFormNameField("Test Form Title Name is an input field");
                softAssert.assertEquals(true, formNameField.isEnabled());
                softAssert.assertAll();
                cancelButtonNewForm.click();
                waitElementVisible(newForm);
        }

        public void formDescriptionInputField() throws InterruptedException {
                newFormButton.click();
                waitElementVisible(editFormHeader);
                //Verify Form Title Name is an input field
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertEquals(true, formDescriptionField.isDisplayed());
                setFormDescriptionField("Test Form Description is an input field");
                softAssert.assertEquals(true, formDescriptionField.isEnabled());
                softAssert.assertAll();
                cancelButtonNewForm.click();
                waitElementVisible(newForm);
        }

        public void moduleDropdown() throws InterruptedException {
                newFormButton.click();
                waitElementVisible(editFormHeader);
                //Verify Module dropdown is present in create form
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertEquals(true, selectSurveyModule.isDisplayed());
                Select option = new Select(selectSurveyModule);
                option.selectByVisibleText("Facility Safety Culture");
                softAssert.assertAll();
                cancelButtonNewForm.click();
                waitElementVisible(newForm);
        }

        public void setCategoryNameField(String categoryName) {
                categoryNameField.clear();
                categoryNameField.sendKeys(categoryName);
        }

        public void setCategoryWeightField(String categoryWeight) {
                categoryWeightField.clear();
                categoryWeightField.sendKeys(categoryWeight);
        }

        public void addCategoriesNewForm() throws InterruptedException {
                newFormButton.click();
                waitElementVisible(editFormHeader);
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertEquals(true, addCategoryButton.isEnabled());
                addCategoryButton.click();
                //Waiting for Category fields to appear
                waitElementVisible(categoryName);
                setCategoryNameField("test 1");
                setCategoryWeightField("50");
                addCategoryButton.click();
                waitElementVisible(categoryName);
                setCategoryNameField("test 2");
                setCategoryWeightField("50");
                softAssert.assertAll();
                crossButtonNewForm.click();
                waitElementVisible(newForm);
        }

        public void categoryWeightFactor() throws InterruptedException {
                newFormButton.click();
                waitElementVisible(editFormHeader);
                addCategoryButton.click();
                //Waiting for Category fields to appear
                waitElementVisible(categoryName);
                //verify weight field cannot be more than 100
                if (categoryWeightField.isDisplayed())
                        setCategoryWeightField("101");
                else
                        setCategoryWeightField("100");
                String enteredText = categoryWeightField.getAttribute("value");
                System.out.println("Entered text is: " + enteredText);
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertEquals(enteredText, "100");
                softAssert.assertAll();
                crossButtonNewForm.click();
                waitElementVisible(newForm);
        }

        public void tabsNewForm() throws InterruptedException {
                newFormButton.click();
                waitElementVisible(surveyManagementTab);
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertEquals(true, selectSurveyButton.isEnabled());
                softAssert.assertEquals(true, selectGeneralButton.isEnabled());
                softAssert.assertAll();
                crossButtonNewForm.click();
                waitElementVisible(newForm);
        }

        public void profileSettingsIcon() throws InterruptedException {
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertEquals(true, profileSettingsButton.isDisplayed());
                if (profileSettingsButton.isEnabled()) {
                        profileSettingsButton.click();
                        waitElementVisible(firstNameFieldProfileSection);
                        String actualTitle = driver.getTitle();
                        String expectedTitle = "Profile Settings (Testing Environment)";
                        softAssert.assertEquals(expectedTitle, actualTitle);
                        if (profileSettingsBackButton.isEnabled()) {
                                profileSettingsBackButton.click();
                                waitElementVisible(newForm);
                        } else {
                                driver.navigate().to(url_SurveyManagement);
                        }
                } else {
                        softAssert.assertTrue(true, "Profile Settings button is disabled");
                }
                softAssert.assertAll();
        }

        public void surveyViewButtonFunctionality() throws InterruptedException {
                SoftAssert softAssert = new SoftAssert();
                if (surveyViewButton.isEnabled()) {
                        softAssert.assertTrue(true, "Survey view icon is clickable");
                        surveyViewButton.click();
                        waitElementVisible(FormHeaderText);
                        String getTextForViewFormHeader = FormHeader.getText();
                        softAssert.assertTrue(getTextForViewFormHeader.contains(" View Form "));
                        crossButtonNewForm.click();
                        waitElementVisible(newForm);
                } else {
                        softAssert.assertTrue(true, "Survey view icon is not clickable");
                }
                softAssert.assertAll();
        }

        public void activeSurveyEditButtonFunctionality() throws InterruptedException {
                SoftAssert softAssert = new SoftAssert();
                if (surveyEditButton.isEnabled()) {
                        softAssert.assertTrue(true, "Survey edit icon is clickable");
                        surveyEditButton.click();
                        waitElementVisible(FormHeaderText);
                        String getTextForViewFormHeader = FormHeader.getText();
                        softAssert.assertTrue(getTextForViewFormHeader.contains(" Edit Form "));
                        nextButtonForm.click();
                        //click on the Next button as soon as the "text" field is visible
                        waitElementVisible((By) surveyTextField);
                        nextButtonForm.click();
                        waitElementVisible((By) submitButtonForm);
                        submitButtonForm.click();
                        waitElementVisible(newForm);
                } else {
                        softAssert.assertTrue(true, "Survey edit icon is not clickable");
                }
                softAssert.assertAll();
        }

        public void inactiveSurveyEditButtonFunctionality() throws InterruptedException {
                SoftAssert softAssert = new SoftAssert();
                if (surveyEditButton.isEnabled()) {
                        softAssert.assertTrue(true, "Survey edit icon is clickable");
                        surveyEditButton.click();
                        waitElementVisible(FormHeaderText);
                        String getTextForViewFormHeader = FormHeader.getText();
                        softAssert.assertTrue(getTextForViewFormHeader.contains(" Edit Form "));
                        nextButtonForm.click();
                        //click on the Next button as soon as the "text" field is visible
                        waitElementVisible((By) surveyTextField);
                        nextButtonForm.click();
                        waitElementVisible((By) submitButtonForm);
                        submitButtonForm.click();
                        waitElementVisible(newForm);
                } else {
                        softAssert.assertTrue(true, "Survey edit icon is not clickable");
                }
                softAssert.assertAll();
        }

        public void deleteIconSurveyFunctionality() throws InterruptedException {
                SoftAssert softAssert = new SoftAssert();
                if (deleteIcon.isEnabled()) {
                        deleteIcon.click();
                        waitElementVisible(confirmationMessage);
                        deleteYesButton.click();
                        waitElementVisible(newForm);
                } else {
                        softAssert.assertTrue(true, "Survey delete icon is not clickable");
                }
                softAssert.assertAll();
        }

        public void successMessageSurveyFunctionality() throws InterruptedException {

                SoftAssert softAssert = new SoftAssert();
                try {
                        if (surveyEditButton.isEnabled()) {
                                surveyEditButton.click();
                                waitElementVisible(FormHeaderText);
                                String getTextForViewFormHeader = FormHeader.getText();
                                softAssert.assertTrue(getTextForViewFormHeader.contains(" Edit Form "));
                                nextButtonForm.click();
                                //click on the Next button as soon as the "text" field is visible
                                waitElementVisible((By) surveyTextField);
                                nextButtonForm.click();
                                //click on the Submit button as soon as the "submit" button is visible
                                waitElementVisible((By) submitButtonForm);
                                submitButtonForm.click();
                                waitElementVisible(newForm);
                                if (formChangesSavedLabel.isDisplayed()) {
                                        String getLabelMessage = formChangesSavedLabel.getText();
                                        softAssert.assertEquals(getLabelMessage, " Form details have been saved successfully ");
                                } else {
                                        softAssert.assertTrue(true, "Success saved message does not appear");
                                }
                        } else {
                                softAssert.assertTrue(true, "Survey Edit icon button is disabled");
                        }
                } catch (Exception e) {
                        // Printing logs for my report
                        Reporter.log("Unable to see the success label message");
                        throw (e);
                }
        }

        public void activeToggleButtonFunctionality() throws InterruptedException {
                SoftAssert softAssert = new SoftAssert();
                try {
                        if (surveyEditButton.isEnabled()) {
                                surveyEditButton.click();
                                waitElementVisible(FormHeaderText);
                                String getTextForViewFormHeader = FormHeader.getText();
                                softAssert.assertTrue(getTextForViewFormHeader.contains(" Edit Form "));
                                //Setting form to Active State
                                boolean activeChecked = true;
                                if (activeChecked != activeToggleButton.isSelected())
                                        activeToggleButton.click();
                                else
                                        softAssert.assertTrue(true, "Active toggle button is already checked");
                                //Setting form to Inactive State
                                boolean inactiveChecked = true;
                                if (inactiveChecked == activeToggleButton.isSelected())
                                        activeToggleButton.click();
                                else
                                        softAssert.assertTrue(true, "Active toggle button is already unchecked");
                                nextButtonForm.click();
                                //click on the Next button as soon as the "text" field is visible
                                waitElementVisible((By) surveyTextField);
                                nextButtonForm.click();
                                //click on the Submit button as soon as the "submit" button is visible
                                waitElementVisible((By) submitButtonForm);
                                submitButtonForm.click();
                                waitElementVisible(newForm);
                        } else {
                                softAssert.assertTrue(true, "Survey Edit icon button is disabled");
                        }
                } catch (Exception e) {
                        // Printing logs for my report
                        Reporter.log("Unable to check the active toggle button functionality");
                        throw (e);
                }
        }

        public void questionInputDeleteIconFunctionality() throws InterruptedException {
                try {
                        Actions act = new Actions(driver);
                        //  act.dragAndDrop(questionInputField, dropField).perform();
                        act.clickAndHold(questionInputField).pause(Duration.ofSeconds(2)).moveToElement(dropField).pause(Duration.ofSeconds(2)).release().build().perform();
                        waitElementVisible((By) questionInputFieldDeleteIcon);
                        questionInputFieldDeleteIcon.click();
                        waitElementInvisible(String.valueOf((By) questionInputFieldDeleteIcon));
                        crossEditform.click();
                        waitElementVisible(newForm);
                } catch (Exception e) {
                        // Printing logs for my report
                        Reporter.log("Unable to perform the delete icon functionality for question type input field");
                        throw (e);
                }
        }

        public void fieldAccessColumnReordering() throws InterruptedException {
                SoftAssert softAssert = new SoftAssert();
                try {
                        driver.get(url_SurveyManagement);
                        waitElementInvisible(loading_cursor);
                        Thread.sleep(3000);
                        if (fieldAccessButton.isDisplayed()) {
                                fieldAccessButton.click();
                                waitElementVisible(fieldAccessForm);
                                //no unique attribute

                        } else {
                                softAssert.assertTrue(true, "Field access button is not visible on screen");
                        }
                } catch (Exception e) {
                        // Printing logs for my report
                        Reporter.log("Unable to perform the field access icon functionality for question type input field");
                        throw (e);
                }
        }

        public void newFormFunctionality() throws InterruptedException {
                SoftAssert softAssert = new SoftAssert();
                try {
                        driver.get(url_SurveyManagement);
                        waitElementInvisible(loading_cursor);
                        Thread.sleep(3000);
                        if (newFormButton.isEnabled()) {
                                newFormButton.click();
                                waitElementVisible(editFormHeader);
                                softAssert.assertTrue(true, "New form gets opened to create a survey/form");
                                //Click on cross icon to close the popup/form
                                crossButtonNewForm.click();
                                waitElementVisible(newForm);
                        } else {
                                softAssert.assertTrue(true, "New Form button is not clickable on screen");
                        }
                } catch (Exception e) {
                        // Printing logs for my report
                        Reporter.log("Unable to open the survey popup/form");
                        throw (e);
                }
        }

        public void verifyTextQuestionForm() throws InterruptedException {
                SoftAssert softAssert = new SoftAssert();
                try {
                        driver.get(url_SurveyManagement);
                        waitElementInvisible(loading_cursor);
                        Thread.sleep(3000);
                        if (newFormButton.isEnabled()) {
                                newFormButton.click();
                                waitElementVisible(editFormHeader);
                                softAssert.assertTrue(true, "New form gets opened to create a survey/form");
                                //Get text of question
                                String getText = labelToGetText.getText();
                                softAssert.assertEquals(getText, " What kind of form do you want to create?");
                                WebElement labelToGetText = driver.findElement(By.className("form-group col-md-12 col-lg-12 col-sm-12 survey-label "));
                                ((JavascriptExecutor) driver).executeScript("arguments[0].getText();", labelToGetText);
                                //Compare actual text with expected text
                                softAssert.assertEquals(labelToGetText, " What kind of form do you want to create?");
                                //Click on cross icon to close the popup/form
                                crossButtonNewForm.click();
                                waitElementVisible(newForm);
                        } else {
                                softAssert.assertTrue(true, "Question does not appear");
                        }
                } catch (Exception e) {
                        // Printing logs for my report
                        Reporter.log("Question does not exist on form");
                        throw (e);
                }
        }

        public void verifySurveyTabs() throws InterruptedException {
                SoftAssert softAssert = new SoftAssert();
                try {
                        driver.get(url_SurveyManagement);
                        waitElementInvisible(loading_cursor);
                        Thread.sleep(3000);
                        if (newFormButton.isEnabled()) {
                                newFormButton.click();
                                waitElementVisible(editFormHeader);
                                softAssert.assertTrue(true, "New form gets opened to create a survey/form");
                                //Verify Gebe

                                //Click on cross icon to close the popup/form
                                crossButtonNewForm.click();
                                waitElementVisible(newForm);
                        } else {
                                softAssert.assertTrue(true, "The form doesn't have two tabs");
                        }
                        softAssert.assertAll();
                } catch (Exception e) {
                        // Printing logs for my report
                        Reporter.log("The form does not have two tabs");
                        throw (e);
                }
        }

        public void actionHeaderIcons() throws InterruptedException {
                SoftAssert softAssert = new SoftAssert();
                try {
                        if (editIcon.isDisplayed())
                                softAssert.assertTrue(true, "Edit Action icon is displayed");
                        else
                                softAssert.assertTrue(true, "Edit action icon is not displayed");
                        if (deleteIcon.isDisplayed())
                                softAssert.assertTrue(true, "Delete Action icon is displayed");
                        else
                                softAssert.assertTrue(true, "Delete action icon is not displayed");
                        if (viewActionIcon.isDisplayed())
                                softAssert.assertTrue(true, "View Action icon is displayed");
                        else
                                softAssert.assertTrue(true, "View action icon is not displayed");
                } catch (Exception e) {
                        // Printing logs for my report
                        Reporter.log("The log view does not have action icons");
                        throw (e);
                }
        }

        public void surveyManagementScreenUnderIE() throws InterruptedException {
                SoftAssert softAssert = new SoftAssert();
                try {
                        driver.get(url_SurveyManagement);
                        waitElementInvisible(loading_cursor);
                        Thread.sleep(3000);
                        //Locating the Main Menu
                        //Instantiating Actions class
                        Actions actions = new Actions(driver);
                        //Hovering on main menu
                        actions.moveToElement(menuIcon);
                        // Locating the element from Menu
                        //To mouseover on survey management screen
                        actions.moveToElement(surveyManagementScreen);
                        //build()- used to compile all the actions into a single step
                        actions.click().build().perform();
                        //Getting the current url and verifying it
                        String get_current_url = driver.getCurrentUrl();
                        Assert.assertEquals(get_current_url, "https://ie-qa.ancera.com/home/survey/survey");

                } catch (Exception e) {
                        // Printing logs for my report
                        Reporter.log("The survey management screen is not present under menu and is not enabled");
                        throw (e);
                }
                softAssert.assertAll();
        }

        public void lockAndUnlockFunctionality() throws InterruptedException {

        }

        public void surveyFormTabs() throws InterruptedException {
                driver.get(url_SurveyManagement);
                waitElementInvisible(loading_cursor);
                Thread.sleep(2000);
                SoftAssert softAssert = new SoftAssert();
                try {
                        if (editIcon.isDisplayed()) {
                                editIcon.click();
                                waitElementVisible(editFormHeader);
                                surveyFormTab2.click();
                                waitElementVisible((By) surveyTextField);
                                surveyFormTab3.click();
                                waitElementVisible((By) submitButtonForm);
                                softAssert.assertEquals(true, submitButtonForm.isDisplayed());
                        } else {
                                softAssert.assertTrue(true, "newFormButton is not present on Survey Management screen.");
                        }
                        softAssert.assertAll();
                } catch (Exception e) {
                        // Printing logs for my report
                        Reporter.log("The survey form does not have tabs, hence test case failed");
                        throw (e);
                }

        }

        public void editFormFields() throws InterruptedException {
                driver.get(url_SurveyManagement);
                waitElementInvisible(loading_cursor);
                Thread.sleep(2000);
                SoftAssert softAssert = new SoftAssert();
                try {

                } catch (Exception e) {
                        // Printing logs for my report
                        Reporter.log("The fields are not editable on Edit Form");
                        throw (e);
                }
        }
    public void administrationRightsSurvey() throws InterruptedException{
        driver.get(url_access);
        SoftAssert softAssert = new SoftAssert();
        waitElementVisible((By) accessManagementPageHeader);
        String getHeaderText = accessManagementPageHeader.getText();
        softAssert.assertEquals(getHeaderText, "Access Management");
        softAssert.assertTrue(true,"On Access management screen");
        if(editRightsIcon.isEnabled()){
            editRightsIcon.click();
            waitElementVisible((By) createFormManagementRight);
            createFormManagementRight.click();
        }
        else{
            softAssert.assertTrue(true, "Edit Rights icon for Admin is not working.");
        }
        try{

        }
        catch (Exception e){
            // Printing logs for my report
            Reporter.log("The fields are not editable on Edit Form");
            throw (e);
        }
    }


        public void administrationRights() throws InterruptedException, IOException {
                try {
                        test = extent.createTest("Verify create, update and view role of Form Management screen", "This test case will verify that create, update and view role of Form Management screen");
                        preconditions = test.createNode(Scenario.class, PreConditions);
                        steps = test.createNode(Scenario.class, Steps);
                        results = test.createNode(Scenario.class, Results);
                        preconditions.createNode("1. Go to url " + url_login);
                        preconditions.createNode("2. Login with valid credentials; user navigates to home page");
                        preconditions.createNode("3. Hover to sidebar to expand the menu");
                        preconditions.createNode("4. Click on Administration and select Access Management");
                        steps.createNode("1. Click on edit rights icon next to assigned role");
                        SoftAssert softAssert = new SoftAssert();
                        ///////////////////////////////
                        //getUserAccess();
                        String getSystemRole = driver.findElement(By.cssSelector("#rolesId .ng-value-label")).getText();
                        driver.get(url_access);
                        waitElementInvisible(String.valueOf(By.id("notification-loading")));
                        Thread.sleep(750);
                        driver.findElement(By.id("userRoleName_sort")).click();
                        waitElementInvisible(String.valueOf(By.id("notification-loading")));
                        Thread.sleep(1000);
                        for (int i = 1; i <= 200; i++) {
                                if (driver.findElement(By.cssSelector("tr:nth-child(" + i + ") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
                                        int j = i - 1;
                                        WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-" + j + " img"));
                                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
                                        driver.findElement(By.id("edit-role-rights-" + j)).click();
                                        break;
                                }
                        }
                        steps.createNode("2. Unselect Create and Update radio button next to Form Management");
                        waitElementInvisible(String.valueOf(By.id("notification-loading")));
                        Thread.sleep(2000);
                        if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateForm Management']")).isSelected() == true) {
                                driver.findElement(By.cssSelector("tr:nth-child(28) td:nth-child(2) .custom-checkbox")).click();
                        }

                        if (driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateForm Management']")).isSelected() == true) {
                                driver.findElement(By.cssSelector("tr:nth-child(28) td:nth-child(3) .custom-checkbox")).click();
                        }
                        driver.findElement(By.cssSelector(".fa-save")).click();
                        waitElementInvisible(String.valueOf(By.id("notification-loading")));
                        waitElementVisible(By.id("message"));
                        softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
                        //login();
                        steps.createNode("3. Go to Form Management screen and verify that user is not able to create or update");
                        driver.get(url_SurveyManagement);
                        waitElementInvisible(String.valueOf(By.id("notification-loading")));
                        Thread.sleep(1000);
                        //   softAssert.assertEquals(driver.findElements(By.id("create-report-role")).size(), 0, "Create button is visible");
                        // driver.findElement(By.id("edit-report-role-1")).click();
                        // waitElementInvisible(By.id("notification-loading")));
                        //  Thread.sleep(1000);
                        // softAssert.assertEquals(driver.findElements(By.cssSelector("#nameId.ng-touched")).size(), 0, "User is able to edit report role");

                        //  driver.findElement(By.id("close-popup-modal")).click();
                        //////////////////////////////////////////
                        steps.createNode("4. Go to Access Management screen and unselect View radio button next to Form Management");
                        driver.get(url_access);
                        waitElementInvisible(String.valueOf(By.id("notification-loading")));
                        Thread.sleep(750);
                        driver.findElement(By.id("userRoleName_sort")).click();
                        waitElementInvisible(String.valueOf(By.id("notification-loading")));
                        Thread.sleep(1000);
                        for (int i = 1; i <= 200; i++) {
                                if (driver.findElement(By.cssSelector("tr:nth-child(" + i + ") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
                                        int j = i - 1;
                                        WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-" + j + " img"));
                                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
                                        driver.findElement(By.id("edit-role-rights-" + j)).click();
                                        break;
                                }
                        }

                        waitElementInvisible(String.valueOf(By.id("notification-loading")));
                        Thread.sleep(2000);
                        if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateForm Management']")).isSelected() == true) {
                                driver.findElement(By.cssSelector("tr:nth-child(28) td:nth-child(2) .custom-checkbox")).click();
                        }

                        if (driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateForm Management']")).isSelected() == true) {
                                driver.findElement(By.cssSelector("tr:nth-child(28) td:nth-child(3) .custom-checkbox")).click();
                        }

                        if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewForm Management']")).isSelected() == true) {
                                driver.findElement(By.cssSelector("tr:nth-child(28) td:nth-child(4) .custom-checkbox")).click();
                        }

                        driver.findElement(By.cssSelector(".fa-save")).click();
                        waitElementInvisible(String.valueOf(By.id("notification-loading")));
                        waitElementVisible(By.id("message"));
                        softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");

                        //LoginTest.login();

                        Actions builder = new Actions(driver);
                        waitElementVisible(By.id("menu-administration"));
                        WebElement pngHover = driver.findElement(By.id("menu-administration"));
                        builder.moveToElement(pngHover).build().perform();

                        steps.createNode("5. Verify that Form Management is not visible in side menu bar");
                        driver.findElement(By.id("menu-administration")).click();
                        softAssert.assertEquals(driver.findElements(By.id("roleMGMTManageSurveyManagement")).size(), 0, "User can view form mgt");

                        driver.get(url_access);
                        waitElementInvisible(String.valueOf(By.id("notification-loading")));
                        Thread.sleep(750);
                        driver.findElement(By.id("userRoleName_sort")).click();
                        waitElementInvisible(String.valueOf(By.id("notification-loading")));
                        Thread.sleep(1000);
                        for (int i = 1; i <= 200; i++) {
                                if (driver.findElement(By.cssSelector("tr:nth-child(" + i + ") td:nth-child(1)  label")).getText().equals(getSystemRole)) {
                                        int j = i - 1;
                                        WebElement scroll = driver.findElement(By.cssSelector("#edit-role-rights-" + j + " img"));
                                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
                                        driver.findElement(By.id("edit-role-rights-" + j)).click();
                                        break;
                                }
                        }

                        waitElementInvisible(String.valueOf(By.id("notification-loading")));
                        Thread.sleep(2000);
                        if (driver.findElement(By.xpath("//input[normalize-space(@id)='isCreateForm Management']")).isSelected() == false) {
                                driver.findElement(By.cssSelector("tr:nth-child(28) td:nth-child(2) .custom-checkbox")).click();
                        }

                        if (driver.findElement(By.xpath("//input[normalize-space(@id)='isUpdateForm Management']")).isSelected() == false) {
                                driver.findElement(By.cssSelector("tr:nth-child(28) td:nth-child(3) .custom-checkbox")).click();
                        }

                        if (driver.findElement(By.xpath("//input[normalize-space(@id)='isViewForm Management']")).isSelected() == false) {
                                driver.findElement(By.cssSelector("tr:nth-child(28) td:nth-child(4) .custom-checkbox")).click();
                        }
                        driver.findElement(By.cssSelector(".fa-save")).click();
                        waitElementInvisible(String.valueOf(By.id("notification-loading")));
                        waitElementVisible(By.id("message"));
                        softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "Rights details has been updated successfully.");
                        softAssert.assertAll();
                        test.pass("Access Rights passed for Form Management Screen successfully");
                        results.createNode("Access Rights passed for Form Management Screen successfully");
                        getScreenshot();
                        saveResult(ITestResult.SUCCESS, null);
                }		 catch (AssertionError er) {
                        test.fail("Access Rights failed for Form Management Screen");
                        results.createNode("Access Rights failed for Form Management Screen");
                        saveResult(ITestResult.FAILURE, new Exception(er));
                } catch (Exception ex) {
                        test.fail("Access Rights failed for Form Management Screen");
                        results.createNode("Access Rights failed for Form Management Screen");
                        saveResult(ITestResult.FAILURE, ex);
                }
        }

        public void sortingOnColumns() throws InterruptedException, IOException {
                try {
                        test = extent.createTest("Verify sorting on columns on Survey Management Screen", "This test case will verify the ascending/ descending sort on Form Management screen");
                        preconditions = test.createNode(Scenario.class, PreConditions);
                        steps = test.createNode(Scenario.class, Steps);
                        results = test.createNode(Scenario.class, Results);
                        preconditions.createNode("1. Go to Form management screen " + url_SurveyManagement);
                        preconditions.createNode("2. Click on the first column");
                        preconditions.createNode("3. Capture all webelements into list");
                        preconditions.createNode("4. Sort on the original list and compare it with the sorted list");
                        steps.createNode("1. Verify Sorting on first column 'Form Name'");
                        SoftAssert softAssert = new SoftAssert();
                        formNameColumnSort.click();
                        //capture all web Elements into list
                        List<WebElement> elementsList = driver.findElements(By.xpath("//tr/td[1]"));
                        //capture text of all elements into new(original) list
                        List<String> originalList = elementsList.stream().map(s -> s.getText()).collect(Collectors.toList());
                        //sorting on the original list and name it to sorted List
                        List<String> sortedList = originalList.stream().sorted().collect(Collectors.toList());
                        //Compare original list vs sorted list
                        softAssert.assertTrue(originalList.equals(sortedList));
                        steps.createNode("2. Verify sorting on second column 'Form Type'");
                        formTypeNameColumnSort.click();
                        //capture all web Elements into list
                        List<WebElement> elementsListFormType = driver.findElements(By.xpath("//tr/td[2]"));
                        //capture text of all elements into new(original) list
                        List<String> originalListFormType = elementsListFormType.stream().map(s -> s.getText()).collect(Collectors.toList());
                        //sorting on the original list and name it to sorted List
                        List<String> sortedListFormType = originalListFormType.stream().sorted().collect(Collectors.toList());
                        //Compare original list vs sorted list
                        softAssert.assertTrue(originalListFormType.equals(sortedListFormType));
                        steps.createNode("3. Verify sorting on third column 'Module");
                        moduleColumn.click();
                        List<WebElement> elementsListModuleColumn = driver.findElements(By.xpath("//tr/td[3]"));
                        List<String> originalListModuleColumn = elementsListModuleColumn.stream().map(s -> s.getText()).collect(Collectors.toList());
                        List<String> sortedListModuleColumn = originalListModuleColumn.stream().sorted().collect(Collectors.toList());
                        softAssert.assertTrue(originalListModuleColumn.equals(sortedListModuleColumn));
                        steps.createNode("4. Verify sorting on fourth column 'Status'");
                        dateCreatedColumn.click();
                        List<WebElement> elementsListDateCreatedColumn = driver.findElements(By.xpath("//tr/td[5]"));
                        List<String> originalListDateCreatedColumn = elementsListDateCreatedColumn.stream().map(s -> s.getText()).collect(Collectors.toList());
                        List<String> sortedListDateCreatedColumn = originalListDateCreatedColumn.stream().sorted().collect(Collectors.toList());
                        softAssert.assertTrue(originalListDateCreatedColumn.equals(sortedListDateCreatedColumn));
                        steps.createNode("5. Verify sorting on fifth column 'Date Created'");
                        statusColumnSort.click();
                        List<WebElement> elementsListStatusColumn = driver.findElements(By.xpath("//tr/td[4]"));
                        List<String> originalListStatusColumn = elementsListStatusColumn.stream().map(s -> s.getText()).collect(Collectors.toList());
                        List<String> sortedListStatusColumn = originalListStatusColumn.stream().sorted().collect(Collectors.toList());
                        softAssert.assertTrue(originalListStatusColumn.equals(sortedListStatusColumn));
                        softAssert.assertAll();
                        test.pass("Sorting Functionality Passed for Form Management Screen successfully");
                        results.createNode("Sorting Functionality Passed for Form Management Screen successfully");
                        //  test.addScreenCaptureFromPath(getScreenshot("Form Management", FormManagementReportPath));
                        saveResult(ITestResult.SUCCESS, null);
                } catch (AssertionError er) {
                        test.fail("Sorting Functionality failed for Form Management Screen");
                        results.createNode("Sorting Functionality failed for Form Management Screen");
                        saveResult(ITestResult.FAILURE, new Exception(er));
                } catch (Exception ex) {
                        test.fail("Sorting Functionality failed for Form Management Screen");
                        results.createNode("Sorting Functionality failed for Form Management Screen");
                        saveResult(ITestResult.FAILURE, ex);
                }
        }

        public void verifyPagination() throws InterruptedException, IOException {
                Pagination(formManagementTable, "Form Management", ReportFilePath);
        }

        public void verifyQuestionTypes() throws InterruptedException, IOException {
                try {
                        test = extent.createTest("Verify Question Types on Survey Management Screen", "This test case will verify the question types on Form Management screen");
                        preconditions = test.createNode(Scenario.class, PreConditions);
                        steps = test.createNode(Scenario.class, Steps);
                        results = test.createNode(Scenario.class, Results);
                        preconditions.createNode("1. Go to Form management screen " + url_SurveyManagement);
                        preconditions.createNode("2. Click on the new form button");
                        preconditions.createNode("3. Select form type, enter form name, and select module");
                        preconditions.createNode("4. Click on Next button");
                        steps.createNode("1. Verify 'Input type' Question");
                        SoftAssert softAssert = new SoftAssert();
                        if(newFormButton.isEnabled()){
                                newFormButton.click();
                                waitElementVisible(editFormHeader);
                                softAssert.assertTrue(true, "New form gets opened to create a survey/form");
                                selectSurveyButton.click();
                                setFormNameField("test form name field");
                                selectSurveyButton.click();
                                setSelectSurveyModule("Facility Safety Culture");
                                nextButtonForm.click();
                                waitElementVisible((By) surveyTextField);
                                Actions act = new Actions(driver);
                                //act.dragAndDrop(questionInputField, dropField).perform();
                                act.clickAndHold(questionInputField).pause(Duration.ofSeconds(2)).moveToElement(dropField).pause(Duration.ofSeconds(2)).release().build().perform();


                        }
                        else {
                                softAssert.assertFalse(true, "The new form button is not working");
                        }
                        steps.createNode("2. Verify 'Radio type' Question");

                } catch (AssertionError er) {
                        test.fail("Verify Question types fields failed for Form Management Screen");
                        results.createNode("Verify Question types fields failed for Form Management Screen");
                        saveResult(ITestResult.FAILURE, new Exception(er));
                } catch (Exception ex) {
                        test.fail("Verify Question types fields failed for Form Management Screen");
                        results.createNode("Verify Question types fields failed for Form Management Screen");
                        saveResult(ITestResult.FAILURE, ex);
                }


        }
    }



