package Test.Ancera.Administration;

import Config.BaseTest;
import MiscFunctions.DB_Config_DW;
import MiscFunctions.NavigateToScreen;
import PageObjects.InterventionManagementPage;
import PageObjects.MonitoringStrategyPage;
import PageObjects.SitesLogPage;
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
        msp.templateLockFilterFunctionality();
    }

    @Test(priority = 11, enabled = true)
    public void VerifyPlanWildcardFunctionality() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.templateWildcardFunctionality();
    }

    @Test(priority = 12, enabled = true)
    public void VerifyPlanSortingFunctionality() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.templateSortingFunctionality();
    }

    @Test(priority = 13, enabled = true)
    public void VerifyPlanPaginaitonFunctionality() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.templatePaginationFunctionality();
    }

    @Test(priority = 14, enabled = true)
    public void VerifyPlanRowsPerPageFunctionality() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.templateRowsPerPageFunctionality();
    }

    @Test(priority = 15, enabled = true)
    public void VerifyColumnsPlanTab() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.planVerifyColumnsFunctionality(new String[]{"Name", "Monitoring Type", "# Collection Intervals", "Performance Criteria", "Action"});
    }


    @Test(priority = 16, enabled = true)
    public void VerifyCreateTemplate() throws IOException, InterruptedException {
        MonitoringStrategyPage msp = new MonitoringStrategyPage(getDriver());
        msp.createNewTemplate();
    }


    @AfterTest
    public static void endreport() {
        extent.flush();
    }

}
