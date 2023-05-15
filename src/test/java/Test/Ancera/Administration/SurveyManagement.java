package Test.Ancera.Administration;
import PageObjects.SurveyManagementPage;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.*;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Config.BaseTest;
import MiscFunctions.Constants;
import MiscFunctions.DB_Config_DW;
import Test.Ancera.Login.LoginTest;

import static MiscFunctions.DateUtil.*;
import static MiscFunctions.ExtentVariables.*;
import static PageObjects.SurveyManagementPage.*;

//import static org.openqa.selenium.support.locators.RelativeLocator.with;


public class SurveyManagement extends BaseTest {


    @BeforeTest
    public void extent() throws InterruptedException, IOException {
        spark = new ExtentSparkReporter("target/Reports/Survey_Management" + date + ".html");
        spark.config().setReportName("Survey Management Test Report");
        DB_Config_DW.test();
    }

    @BeforeClass
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
    }

    @Test(priority = 1, enabled = true)
    public void NavigateSurveyMgtScreen() throws IOException, InterruptedException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.NavigateToScreen(Constants.url_surveyManagement, "Form Management", SurveyManagementPageTitle);
    }

    @Test(description = "Test Case: Verify toolTip is replaced with Field Access icon", enabled = true, priority = 3)
    public void testTooltip() {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.verifyTooltip();
    }

    @Test(description = "Test Case: Verify correct form data count is displayed if the applied filter columns are hidden from logview", enabled = true, priority = 4)
    public void testFormDataCount() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.verifyFormDataCount();
    }

    @Test(description = "Test Case: Verify that the date must be updated when the user adds a new form with date column hidden", enabled = true, priority = 5)
    public void testFormUpdatedDate() throws InterruptedException, IOException {
        SurveyManagementPage surveyManagement = new SurveyManagementPage(getDriver());
        surveyManagement.verifyFormUpdatedDate();
    }

    @Test(description = "Test Case: Verify correct form data count is displayed if the applied filter columns are hidden from log-view after unlock and refresh functionality", enabled = true, priority = 6)
    public void testFormDataCountRefresh() throws InterruptedException, IOException {
        SurveyManagementPage surveyManagement = new SurveyManagementPage(getDriver());
        surveyManagement.verifyFormDataCount();
        surveyManagement.verifyFormDataCountRefresh();
    }

    @Test(description = "Test Case: IE-8199 Verify reset default button functionality", enabled = true, priority = 7)
    public void testResetDefaultButtonFunctionality() throws InterruptedException, IOException {
        SurveyManagementPage surveyManagementPage = new SurveyManagementPage(getDriver());
        surveyManagementPage.resetDefaultButtonFunctionality();
    }

    @Test(description = "Test Case: IE-8198 Verify selection/ deselection of columns", enabled = true, priority = 8)
    public void testSelectionOfColumns() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.selectionOfColumnsFunctionality();
    }

    @Test(description = "Test Case: IE-8197 Verify deselection of columns", enabled = true, priority = 9)
    public void testDeselectionOfColumns() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.deselectionOfColumnsFunctionality();
    }

    @Test(description = "Test Case: IE-8194 - Verify deselection of keycolumns", enabled = true, priority = 10)
    public void testDeselectionOfKeyColumns() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.deselectionOfKeyColumnsFunctionality();
    }

    @Test(description = "Test Case IE-7467: Verify Delete icon on Survey Management", enabled = true, priority = 11)
    public void testDeleteIcon() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.deleteIconFunctionality();
    }

    @Test(description = "Test Case IE-7466: Verify survey can only be deleted when no questions were answered", enabled = true, priority = 12)
    public void testDeleteSurveyForNoQuestionsAnswered() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.deleteSurveyForNoQuestionsAnswered();
    }

    @Test(description = "Test Case IE-7465: Verify log view will have a delete icon", enabled = true, priority = 13)
    public void testPresenceOfDeleteIcon() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.presenceOfDeleteIcon();
    }

    @Test(description = "Test Case IE-7452: Verify cross button of Editing popup", enabled = true, priority = 14)
    public void testCrossButtonOfEditingPopup() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.crossButtonOfEditingPopup();
    }

    @Test(description = "Test Case IE-7451: Verify cancel button of Editing popup", enabled = true, priority = 15)
    public void testCancelButtonOfEditingPopup() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.cancelButtonOfEditingPopup();
    }

    @Test(description = "Test Case IE-7450: Verify next button of Editing popup", enabled = true, priority = 16)
    public void testNextButtonOfEditingPopup() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.nextButtonOfEditingPopup();
    }

    @Test(description = "Test Case IE-7449: Verify presence of Editing popup", enabled = true, priority = 17)
    public void testPresenceOfEditingPopup() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.presenceOfEditingPopup();
    }

    @Test(description = "Test Case IE-7424: Verify cross button of New Form", enabled = true, priority = 18)
    public void testCrossIconOfNewForm() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.crossIconOfNewForm();
    }

    @Test(description = "Test Case IE-7423: Verify next button of New Form when enabled", enabled = true, priority = 19)
    public void testEnabledNextButtonNewForm() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.enabledNextButtonNewForm();
    }

    @Test(description = "Test Case IE-7422: Verify back button of New Form when enabled", enabled = true, priority = 20)
    public void testBackButtonNewForm() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.backButtonNewForm();
    }

    @Test(description = "Test Case IE-7409: Verify next button of New Form when disabled", enabled = true, priority = 21)
    public void testDisabledNextButtonNewForm() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.disabledNextButtonNewForm();
    }

    @Test(description = "Test Case IE-8202: Verify date should be shown against form created when date column is hidden.", enabled = true, priority = 22)
    public void testDateColumnHiddenNewForm() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.dateColumnHiddenNewForm();
    }

    @Test(description = "Test Case IE-7411: Verify exclude result toggle button is added", enabled = true, priority = 23)
    public void testExcludeResultToggleButtonPresence() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.excludeResultToggleButtonPresence();
    }

    @Test(description = "Test Case IE-7410: Verify clicking on next button will redirect to next screen", enabled = true, priority = 24)
    public void testNextButtonFunctionalityNewForm() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.nextButtonFunctionalityNewForm();
    }

    @Test(description = "Test Case IE-7408: Verify Cancel Button functionality on New Form", enabled = true, priority = 25)
    public void testCancelButtonOfNewForm() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.cancelButtonOfNewForm();
    }

    @Test(description = "Test Case IE-7407: Verify Add Category can be deleted on New Form", enabled = true, priority = 26)
    public void testDeleteAddCategoryNewForm() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.deleteAddCategoryNewForm();
    }

    @Test(description = "Test Case IE-7406: Verify that, add category will have an input field", enabled = true, priority = 27)
    public void testAddCategoryInputFieldsCheck() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.addCategoryInputFieldsCheck();
    }

    @Test(description = "Test Case IE-7373: Verify that, form title name is an input field", enabled = true, priority = 28)
    public void testFormTitleNameInputField() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.formTitleNameInputField();
    }

    @Test(description = "Test Case IE-7374: Verify that, form description name is an input field", enabled = true, priority = 29)
    public void testFormDescriptionInputField() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.formDescriptionInputField();
    }

    @Test(description = "Test Case IE-7375: Verify that, Module dropdown is present in new form", enabled = true, priority = 30)
    public void testModuleDropdown() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.moduleDropdown();
    }

    @Test(description = "Test Case IE-7399: Verify that, categories will be added on clicking new category", enabled = true, priority = 31)
    public void testAddCategories() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.addCategoriesNewForm();
    }

    @Test(description = "Test Case IE-7403: Verify that, category should have a weight factor of 100%", enabled = true, priority = 32)
    public void testCategoryWeightFactor() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.categoryWeightFactor();
    }

    @Test(description = "Test Case IE-7372: Verify that, General and Survey tabs will be shown", enabled = true, priority = 33)
    public void testTabsNewForm() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.tabsNewForm();
    }

    @Test(description = "Test Case IE-7339: Verify that, Profile icon is displayed and is enabled", enabled = true, priority = 34)
    public void testProfileSettingsIcon() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.profileSettingsIcon();
    }

    @Test(description = "Test Case IE-7327: Verify that, Survey view functionality", enabled = true, priority = 35)
    public void testSurveyViewButtonFunctionality() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.surveyViewButtonFunctionality();
    }

    @Test(description = "Test Case IE-7326: Verify that, Edit button survey functionality", enabled = true, priority = 36)
    public void testActiveSurveyEditButtonFunctionality() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.activeSurveyEditButtonFunctionality();
    }

    @Test(description = "Test Case IE-7126: Verify that, Edit button survey functionality", enabled = true, priority = 37)
    public void testInactiveSurveyEditButtonFunctionality() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.inactiveSurveyEditButtonFunctionality();
    }

    @Test(description = "Test Case IE-7012: Verify that, Delete button survey functionality", enabled = true, priority = 38)
    public void testDeleteIconSurveyFunctionality() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.deleteIconSurveyFunctionality();
    }

    @Test(description = "Test Case IE-7458: Verify that the success message is shown on saving the form", enabled = true, priority = 39)
    public void testSuccessMessageSurveyFunctionality() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.successMessageSurveyFunctionality();
    }

    @Test(description = "Test Case IE-7430: Verify that the user has the ability to set form active/inactive", enabled = true, priority = 40)
    public void testActiveToggleButtonFunctionality() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.activeToggleButtonFunctionality();
    }

    @Test(description = "Test Case IE-7427: Verify that question input has a delete icon", enabled = true, priority = 41)
    public void testQuestionInputDeleteIconFunctionality() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.questionInputDeleteIconFunctionality();
    }

    @Test(description = "Test Case IE-7336: Verify reset default button functionality for Column Reordering", enabled = true, priority = 42)
    public void testFieldAccessColumnReordering() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.fieldAccessColumnReordering();
    }

    @Test(description = "Test Case IE-7402: Verify new form button functionality", enabled = true, priority = 43)
    public void testNewFormFunctionality() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.newFormFunctionality();
    }

    @Test(description = "Test Case IE-7371: Verify question will be asked to create what kind of form?", enabled = true, priority = 44)
    public void testVerifyTextQuestionForm() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.verifyTextQuestionForm();
    }

    @Test(description = "Test Case IE-7006: Verify that log view has all icons under Action Header", enabled = true, priority = 45)
    public void testActionHeaderIcons() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.actionHeaderIcons();
    }

    @Test(description = "Test Case IE-7370: Verify form will have two tabs", enabled = true, priority = 46)
    public void testVerifySurveyTabs() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.verifySurveyTabs();
    }

    @Test(description = "Test Case IE-7367: Verify that survey management is under IE administrator", enabled = true, priority = 47)
    public void testSurveyManagementPageScreenUnderIE() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        // SurveyManagementPage.SurveyManagementPageScreenUnderIE();
    }

    @Test(description = "Test Case IE-7332: Verify lock/ unlock filter feature icon functionality", enabled = true, priority = 48)
    public void testLockAndUnlockFunctionality() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.lockAndUnlockFunctionality();
    }

 /*  @Test(description = "Test Case IE-8202: Verify lock/ unlock filter feature icon functionality", enabled = true, priority = 49)
   public void testLockAndUnlockFunctionality() throws InterruptedException, IOException {
       SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
       SurveyManagementPage.lockAndUnlockFunctionality();
   }*/

    @Test(description = "Test Case IE-7370: Verify the survey form will have tabs", enabled = true, priority = 50)
    public void testSurveyFormTabs() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.surveyFormTabs();
    }

    @Test(description = "Test Case IE-7457: Verify all fields on all screens on Edit form should be editable", enabled = true, priority = 51)
    public void testEditFormFields() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.editFormFields();
    }

    @Test(description = "Test Case IE-7337: Verify n-n ", enabled = true, priority = 52)
    public void testNToN() throws InterruptedException, IOException {
        SurveyManagementPage surveyManagementPage = new SurveyManagementPage(getDriver());
        surveyManagementPage.editFormFields();
    }

    @Test(description = "Test Case IE-7341: Verify that administration rights are working on survey management ", enabled = true, priority = 53)
    public void VerifyAdministrationRights() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        //    SurveyManagementPage.administrationRights();
    }

    @Test(description = "Test Case IE-7328: Verify ascending/descending sorting on survey management screen", enabled = true, priority = 54)
    public void VerifySortingOnColumns() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.sortingOnColumns();
    }

    @Test(description = "Test Case IE-7328: Verify pagination on survey management screen", enabled = true, priority = 55)
    public void VerifyPagination() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.verifyPagination();
    }

    @Test(description = "Test Case IE-7415: Verify Question types on survey management screen", enabled = true, priority = 56)
    public void VerifyQuestionTypes() throws InterruptedException, IOException {
        SurveyManagementPage SurveyManagementPage = new SurveyManagementPage(getDriver());
        SurveyManagementPage.verifyQuestionTypes();
    }


    @AfterTest
    public static void endreport() {
        extent.flush();
    }
}


