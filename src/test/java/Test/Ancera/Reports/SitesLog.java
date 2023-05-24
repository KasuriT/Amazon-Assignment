package Test.Ancera.Reports;

import Config.BaseTest;
import LogViewFunctions.LogCSVExport;
import MiscFunctions.ClickElement;
import MiscFunctions.DateUtil;
import MiscFunctions.DownloadFileCheck;
import PageObjects.SitesLogPage;
import Test.Ancera.Login.LoginTest;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static LogViewFunctions.FilterLock.Lock;
import static LogViewFunctions.FilterSort.Sorting;
import static LogViewFunctions.FilterWildcard.Wildcard;
import static LogViewFunctions.LogCSVExport.*;
import static LogViewFunctions.Pagination.Pagination;
import static LogViewFunctions.RowsPerPage.RowsPerPage1;
import static LogViewFunctions.VerifyColumnNames.VerifyAllColumns;
import static MiscFunctions.Constants.ReportFilePath;
import static MiscFunctions.DateUtil.date;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.getScreenshot;
import static MiscFunctions.Methods.waitElementInvisible;
import static PageObjects.BasePage.loading_cursor;
import static PageObjects.SitesLogPage.*;

public class SitesLog extends BaseTest {

    @BeforeTest
    public void extent() throws InterruptedException, IOException {
        spark = new ExtentSparkReporter("target/Reports/Sites_Log" + date + ".html");
        spark.config().setReportName("Sites Log Test Report");
    }

    @BeforeClass
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
    }


    @Test(priority = 1, enabled = true)
    public void VerifyNavigateSitesLogScreen() throws IOException, InterruptedException {
        SitesLogPage sites = new SitesLogPage(getDriver());
        sites.navigateSitesLogScreen();
    }


    @Test(priority = 2, enabled = true)
    public void LockFilterComplex() throws InterruptedException, IOException {
        Lock(complexLogTable, "Complex Log", 2);
    }


    @Test(enabled = true, priority = 3)
    public void WildcardFilterComplex() throws InterruptedException, IOException {
        Wildcard(complexLogTable, "Complex Log", 2);
    }


    @Test(priority = 4)
    public void FilterSortingComplex() throws InterruptedException, IOException {
        Sorting(complexLogTable, "Complex Log", 2);
    }

    @Test(priority = 5, enabled = true)
    public void RowsPerPageComplex() throws InterruptedException, IOException {
        RowsPerPage1(complexLogTable);
    }

    @Test(priority = 6, enabled = true)
    public void PaginationSitesComplex() throws InterruptedException, IOException {
        getDriver().navigate().refresh();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        Pagination(complexLogTable, "Sites Log");
    }


/*
	@Test (priority = 7, enabled = true)
	public void LockFilterFarm() throws InterruptedException, IOException {
		clickFarmTab();
		Lock(farmLogTable, "Sites Log",  2);
	}
*/

    @Test(priority = 8)
    public void WildcardFilterFarm() throws InterruptedException, IOException {
        SitesLogPage.openSitesLogPage();
        clickFarmTab();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        Wildcard(farmLogTable, "Sites Log", 2);
    }


    @Test(priority = 9)
    public void FilterSortingFarm() throws InterruptedException, IOException {
        clickFarmTab();
        Sorting(farmLogTable, "Sites Log", 2);
    }

    @Test(priority = 10, enabled = true)
    public void RowsPerPageFarm() throws InterruptedException, IOException {
        clickFarmTab();
        RowsPerPage1(farmLogTable);
    }

    @Test(priority = 11, enabled = true)
    public void PaginationSitesFarm() throws InterruptedException, IOException {
        getDriver().navigate().refresh();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        clickFarmTab();
        Pagination(farmLogTable, "Sites Log");
    }

//	@Test (priority = 12, enabled = true)
//	public void LockFilterHouse() throws InterruptedException, IOException {
//		clickHouseTab();
//		Lock(houseLogTable, "Sites Log",  2);
//	}


    @Test(priority = 13)
    public void WildcardFilterHouse() throws InterruptedException, IOException {
        SitesLogPage.openSitesLogPage();
        clickHouseTab();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        Wildcard(houseLogTable, "Sites Log", 2);
    }


    @Test(priority = 14)
    public void FilterSortingHouse() throws InterruptedException, IOException {
        clickHouseTab();
        Sorting(houseLogTable, "Sites Log", 2);
    }

    @Test(priority = 15, enabled = true)
    public void RowsPerPageHouse() throws InterruptedException, IOException {
        clickHouseTab();
        RowsPerPage1(houseLogTable);
    }

    @Test(priority = 16, enabled = true)
    public void PaginationSitesHouse() throws InterruptedException, IOException {
        getDriver().navigate().refresh();
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        clickHouseTab();
        Pagination(houseLogTable, "Sites Log");
    }

    @Test (enabled= true, priority = 17)
    public void VerifyColumnsComplexTab() throws IOException, InterruptedException {
        SitesLogPage.openSitesLogPage();
        VerifyAllColumns(complexLogTable, new String[]{"", "", "Site ID", "Site Name", "Site Barcode","Created Date", "Created By", "Organization", "Region", "Sub Region", "Number Of Farms Under Complex", "Current Program", "Planned Date To Change Program", "Number Of Houses", "Complex Manager", "Complex Manager Email", "Complex Manager Phone Number", "Street Address", "City", "State", "Zip Code", "Country", "Latitude", "Longitude", ""});
    }

    @Test (enabled= true, priority = 18)
    public void VerifyColumnsFarmTab() throws IOException, InterruptedException {
        SitesLogPage.openSitesLogPage();
        VerifyAllColumns(farmLogTable, new String[]{"", "", "Farm Number", "Site ID", "Site Name", "Site Barcode", "Created Date", "Created By", "Organization", "Region", "Sub Region", "Complex", "Farm Type", "Six Flock Rank", "Number Of Houses", "Total Head Capacity", "Street Address", "City", "State", "Zip Code", "Country", "Latitude", "Longitude", "Contract Grower Name", "Contract Grower Email", "Contract Grower Phone Number", ""});
    }

    @Test (enabled= true, priority = 19)
    public void VerifyColumnsHouseTab() throws IOException, InterruptedException {
        clickHouseTab();
        VerifyAllColumns(houseLogTable, new String[]{"", "", "Site ID", "Site Name", "Site Barcode", "Created Date", "Created By", "Organization", "Region", "Sub Region", "Complex", "Farm", "House Classification", "Year Built", "Age Of Building", "Dimensions", "Square Footage", "Capacity", "Number Of Feed Bins", "Feeder Type", "Drinker Type", "Ventilation Type", "Litter Type", "Number Of Runs On Litter", "Floor Type", "Street Address", "City", "State", "Zip Code", "Country", "Latitude", "Longitude", ""});
    }

    @Test (enabled= true, priority = 20)
    public void ExportCSVComplexTab() throws IOException, InterruptedException {
        SitesLogPage.openSitesLogPage();
       CSVExport1("Complex Log", sitesComplexTabCSVFileName, complexLogTable, 3,3);
    }

    @Test (enabled= true, priority = 21)
    public void ExportCSVFarmTab() throws IOException, InterruptedException {
        clickFarmTab();
        CSVExport1("Farm Log", sitesComplexTabCSVFileName, farmLogTable, 4,3);
    }

    @Test (enabled= true, priority = 22)
    public void ExportCSVHouseTab() throws IOException, InterruptedException {
        clickHouseTab();
        CSVExport1("House Log", sitesHouseTabCSVFileName, houseLogTable, 3,3);
    }

    @Test (enabled= true, priority = 23)
    public void ExportCSVAuditComplexTab() throws IOException, InterruptedException {
        clickComplexTab();
        CSVAuditExport("Complex Log", sitesComplexTabCSVAuditFileName, complexLogTable, true);
    }

    @Test (enabled= true, priority = 24)
    public void ExportCSVAuditFarmTab() throws IOException, InterruptedException {
        clickFarmTab();
        CSVAuditExport("Farm Log", sitesFarmTabCSVAuditFileName, farmLogTable, true);
    }

    @Test (enabled= true, priority = 25)
    public void ExportCSVAuditHouseTab() throws IOException, InterruptedException {
        clickHouseTab();
        CSVAuditExport("House Log", sitesHouseTabCSVAuditFileName, houseLogTable, true);
    }




    @AfterTest
    public static void endreport() {
        extent.flush();
    }
}

