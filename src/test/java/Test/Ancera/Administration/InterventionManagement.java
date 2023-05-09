package Test.Ancera.Administration;

import MiscFunctions.NavigateToScreen;
import PageObjects.InterventionManagementPage;

import org.testng.annotations.*;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
//import org.openqa.selenium.chrome.ChromegetDriver();


import com.aventstack.extentreports.reporter.ExtentSparkReporter;


import Config.BaseTest;
import Test.Ancera.Login.LoginTest;

import static MiscFunctions.Methods.*;

import static MiscFunctions.DateUtil.*;
import static MiscFunctions.ExtentVariables.*;

//import static org.openqa.selenium.support.locators.RelativeLocator.with;

import org.openqa.selenium.By;

import static PageObjects.BasePage.*;
import static MiscFunctions.Constants.*;

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

    @Test(priority = 5, enabled = true, description = "TC: IE-9397")
    public void VerifyCrudInterventionFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.crudIntervention();
    }

    @Test(priority = 6, enabled = true, description = "TC: IE-9397")
    public void VerifyCrudLogInterventionFunctionality() throws IOException, InterruptedException {
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

    @Test(priority = 12, enabled = true, description = "TC: IE-9430")
    public void VerifyInlineEditAudit() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        //imp.inlineEditAudit();
    }

    @Test(priority = 13, enabled = true, description = "")
    public void verifySortingFunctionality() throws IOException, InterruptedException {
        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
        imp.verifySorting();
    }


    @AfterTest
    public static void endreport() {
        extent.flush();
    }

}