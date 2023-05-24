package Test.Ancera.Administration;

import Config.BaseTest;
import MiscFunctions.DB_Config_DB;
import MiscFunctions.DB_Config_DW;
import PageObjects.MonitoringStrategyPage;
import Test.Ancera.Login.LoginTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static MiscFunctions.DateUtil.date;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.spark;

public class MonitoringStrategy extends BaseTest {

    @BeforeTest
    public void extent() throws InterruptedException, IOException {
        spark = new ExtentSparkReporter("target/Reports/Administration_Monitoring_Strategy" + date + ".html");
        spark.config().setReportName("Monitoring Strategy Test Report");
        DB_Config_DW.test();
        DB_Config_DB.test();
    }

    @BeforeClass
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
    }

    @Test(priority = 1, enabled = true)
    public void VerifyNavigateMonitoringStrategyPage() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.navigateMonitoringStrategyScreen();
    }


    @Test(priority = 2, enabled = true)
    public void VerifyTemplateLockFilterFunctionality() throws InterruptedException, IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.templateLockFilterFunctionality();
    }

    @Test(priority = 3, enabled = true)
    public void VerifyTemplateWildcardFunctionality() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.templateWildcardFunctionality();
    }

    @Test(priority = 4, enabled = true)
    public void VerifyTemplateSortingFunctionality() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.templateSortingFunctionality();
    }

    @Test(priority = 5, enabled = true)
    public void VerifyTemplatePaginaitonFunctionality() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.templatePaginationFunctionality();
    }

    @Test(priority = 6, enabled = true)
    public void VerifyTemplateRowsPerPageFunctionality() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.templateRowsPerPageFunctionality();
    }

    @Test(priority = 7, enabled = true)
    public void VerifyColumnsTemplateTab() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.templateVerifyColumnsFunctionality(new String[]{"Name", "Monitoring Type", "# Collection Intervals", "Performance Criteria", "Action"});
    }

    @Test(priority = 10, enabled = true)
    public void VerifyPlanLockFilterFunctionality() throws InterruptedException, IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.planLockFilterFunctionality();
    }

    @Test(priority = 11, enabled = true)
    public void VerifyPlanWildcardFunctionality() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.planWildcardFunctionality();
    }

    @Test(priority = 12, enabled = true)
    public void VerifyPlanSortingFunctionality() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.planSortingFunctionality();
    }

    @Test(priority = 13, enabled = true)
    public void VerifyPlanPaginaitonFunctionality() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.planPaginationFunctionality();
    }

    @Test(priority = 14, enabled = true)
    public void VerifyPlanRowsPerPageFunctionality() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.planRowsPerPageFunctionality();
    }

    @Test(priority = 15, enabled = true)
    public void VerifyColumnsPlanTab() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.planVerifyColumnsFunctionality(new String[]{"Template Name", "Plan Name", "Monitoring Type", "# Collection Intervals", "Performance Criteria", "Complex", "Start Date", "End Date", "Action"});
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test(priority = 18, enabled = true)
    public void VerifyCreateTemplate() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.createNewTemplate();
    }


    @Test(priority = 19, enabled = true)
    public void VerifyCreateTemplateFromDB() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.verifyCreatedTemplateFromDB();
    }

    @Test(priority = 20, enabled = true)
    public void ViewCreatedTemplate() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.viewCreatedTemplate();
    }

    @Test(priority = 21, enabled = true)
    public void EditCreatedTemplate() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.editCreatedTemplate();
    }

    @Test(priority = 22, enabled = true, description = "IE-9620, IE-9624")
    public void VerifyTemplateConfigurationDisplays() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.verifySelectingTemplateDisplaysConfiguration();
    }


    @Test(priority = 23, enabled = true)
     public void VerifyCreatePlanFromTemplate() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
       msp.createPlanFromTemplate();
    }

    @Test(priority = 24, enabled = true)
     public void VerifyCreatePlanFromDB() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
       msp.verifyCreatedPlanFromDB();
    }


    @Test(priority = 25, enabled = true, description = "IE-9813")
     public void VerifyPlanPresentInDropdownAfterChange() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
       msp.planPresentInDropdownAfterChange();
    }

    @Test(priority = 26, enabled = true)
    public void VerifyViewPlanFromListIcon() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.verifyClickListIconFunctionality();
    }


    @Test(priority = 27, enabled = true)
    public void CopyCreatedTemplate() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.copyCreatedTemplate();
    }

    @Test(priority = 28, enabled = true)
    public void ViewCreatedPlan() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.viewCreatedPlan();
    }

    @Test(priority = 29, enabled = true)
    public void EditCreatedPlan() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.editCreatedPlan();
    }

    @Test(priority = 30, enabled = false)
    public void DeleteCreatedPlan() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.deleteCreatedPlan();
    }

    @Test(priority = 33, enabled = true)
    public void DeleteCreatedTemplate() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.deleteCreatedTemplate();
    }

    @Test(priority = 34, enabled = true, description = "IE-9616")
    public void VerifyAllTemplatesInPlanTemplateDropdown() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.verifyTemplatesInPlanDropdown();
    }


    @Test(priority = 35, enabled = true, description = "IE-")
    public void VerifyAllValidationsOnAddDaysFields() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.verifyValidationsOnDaysFields();
    }

    @Test(priority = 36, enabled = true, description = "IE-9629")
    public void VerifyAllCompexAssignedToUserDisplays() throws IOException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.complexAssignedToUserDisplays();
    }



    @AfterTest
    public static void endreport() {
        extent.flush();
    }

}
