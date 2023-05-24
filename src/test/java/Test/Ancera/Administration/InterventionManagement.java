package Test.Ancera.Administration;

import Config.BaseTest;
import PageObjects.InterventionManagementPage;
import Test.Ancera.Login.LoginTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static MiscFunctions.DateUtil.date;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.spark;

public class InterventionManagement extends BaseTest {

    @BeforeTest
    public void extent() throws InterruptedException, IOException {
        spark = new ExtentSparkReporter("target/Reports/Administration_Intervention_Management" + date + ".html");
        spark.config().setReportName("Intervention Management Test Report");
    }

    @BeforeClass
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
    }

    @Test(priority = 1, enabled = true)
    public void VerifyNavigateInterventionMgtScreen() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.navigateInterventionMgtScreen();
    }
    @Test(priority = 2, enabled = true)
    public void VerifyLockFilterFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.lockFilterFunctionality();
    }

    @Test(priority = 3, enabled = true)
    public void VerifyWildcardFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.wildcardFunctionality();
    }

    @Test(priority = 4, enabled = true, description = "TC: IE-9468, TC: IE-9402")
    public void VerifyIconsPresenceOnInlineEditFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.iconsPresenceOnInlineEditFunctionality();
    }

    @Test(priority = 5, enabled = true, description = "TC: IE-9397, 9170")
    public void VerifyCrudInterventionFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.crudIntervention();
    }

    @Test(priority = 6, enabled = true, description = "TC: IE-9397, 9281, 9282")
    public void VerifyCrudLogInterventionFunctionality() throws IOException, InterruptedException, SQLException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.crudLogIntervention();
    }

    @Test(priority = 7, enabled = true, description = "TC: IE-9395")
    public void VerifySameNameInterventionFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.sameNameIntervention();
    }

    @Test(priority = 8, enabled = true, description = "TC: IE-9416")
    public void VerifyInlineEditInterventionFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.inlineEditIntervention();
    }

    @Test(priority = 9, enabled = true, description = "TC: IE-9467")
    public void VerifyInlineEditInterventionCheckActions() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.inlineEditInterventionCheckActions();
    }

    @Test(priority = 10, enabled = true, description = "TC: IE-9430")
    public void VerifyInlineEditNavigateToScreen() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.inlineEditNavigateToScreen();
    }

    @Test(priority = 11, enabled = true, description = "TC: IE-9411, 9412")
    public void VerifyInlineEditAccessRights() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.AccessRights();
        LoginTest.login();
        imp.inlineEditAccessRights();
        imp.adminAccessRights();
        LoginTest.login();
    }

    @Test(priority = 12, enabled = true, description = "TC: IE-9446")
    public void VerifyInlineEditAudit() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.inlineEditAudit();
    }

    @Test(priority = 13, enabled = false, description = "TC:9252, 9407")
    public void VerifySortingFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.verifySorting();
    }

    @Test(priority = 14, enabled = true, description = "TC:9468")
    public void VerifyFieldAccessFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.verifyFieldAccessFunctionality();
    }

    @Test(priority = 15, enabled = true, description = "TC:9272, TC:9273")
    public void VerifyMandatoryChecksFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.verifyMandatoryChecksFunctionality();
    }

    @Test(priority = 16, enabled = true, description = "TC:9167, 9168, 9170, 9171")
    public void VerifyToggleButtonFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.verifyToggleButtonFunctionality();
    }

    @Test(priority = 17, enabled = true, description = "TC:9166")
    public void VerifyMultipleAttributesIntervention() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.verifyMultipleAttributesIntervention();
    }

    @Test(priority = 18, enabled = true, description = "TC:9172")
    public void VerifyCreateInterventionAllAttributes() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.createInterventionWithAllAttributes();
    }

    @Test(priority = 19, enabled = true, description = "TC:9173, 9174, 9179")
    public void VerifyRequiredValidationsInterventionType() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.requiredValidationsInterventionType();
    }

    @Test(priority = 20, enabled = true, description = "TC:9175, 9176")
    public void VerifyDragAndDropInterventionType() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.dragAndDropInterventionType();
    }

    @Test(priority = 21, enabled = true, description = "TC:9250")
    public void VerifyTooltipFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.verifyToolTipFunctionality();
    }

    @Test(priority = 22, enabled = true, description = "TC:9253")
    public void VerifyPaginationFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.verifyPaginationFunctionality();
    }

    @Test(priority = 23, enabled = true, description = "TC:9276")
    public void VerifyRequiredChecksLogIntervention() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.verifyRequiredChecksLogIntervention();
    }

    @Test(priority = 24, enabled = true, description = "TC:9277")
    public void VerifyResetButtonFunctionalityLogIntervention() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.resetButtonFunctionalityLogIntervention(); // will be updated when the bug gets fixed
    }

    @Test(priority = 25, enabled = true, description = "TC:9278, 9279")
    public void VerifyViewInterventionDropdownFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.viewInterventionDropdownFunctionality();
    }

    @Test(priority = 26, enabled = true, description = "TC:9278, 9279")
    public void VerifyContextualFilterFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.viewContextualFilterFunctionality();
    }

    @Test(priority = 27, enabled = true, description = "TC:9429")
    public void VerifyContextualFailterFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.viewContextualFilterFunctionality();
    }


    @AfterTest
    public static void endreport() {
        extent.flush();
    }

}