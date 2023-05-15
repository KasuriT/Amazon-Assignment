package PageObjects;

import Config.BaseTest;
import MiscFunctions.NavigateToScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;

import static Config.BaseTest.saveResult;
import static LogViewFunctions.FilterLock.Lock;
import static LogViewFunctions.FilterSort.Sorting;
import static LogViewFunctions.FilterWildcard.Wildcard;
import static LogViewFunctions.Pagination.Pagination;
import static LogViewFunctions.RowsPerPage.RowsPerPage1;

import static LogViewFunctions.VerifyColumnNames.VerifyAllColumns;
import static MiscFunctions.Constants.url_monitoringStrategy;
import static MiscFunctions.Constants.url_programManagement;
import static MiscFunctions.DateUtil.dateYYYYMMDD;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.ExtentVariables.results;
import static MiscFunctions.Methods.*;
import static PageObjects.BasePage.*;
import static java.sql.DriverManager.getDriver;

public class MonitoringStrategyPage {

    public WebDriver driver;
    public static By monitoringStrategyTitle = By.id("Monitoring Strategy");
    public static String monitoringStrategyTemplateTable = "intervention-management-log";
    public static String monitoringStrategyPlanTable = "intervention-management-plans-log";

    public By monitoringTemplateTab = By.cssSelector(".template-tab-px");
    public By monitoringPlanTab = By.cssSelector(".plan-tab-px");
    public By monitoringCreateNewTemplateButton = By.id("create-monitoring-strategy");
    public By monitoringCreateNewPlanButton = By.id("create-treatment");

    public By monitoringTemplateName = By.id("samplingPlanNameId");
    public By monitoringTemplateNameMandatoryCheck = By.cssSelector("samplingPlanNameId.has-error");
    public By monitoringTemplateType = By.id("monitoringType-0");
    public By monitoringTemplateTypeMandatoryCheck = By.cssSelector("monitoringType-0.has-error");
    public By monitoringTemplateTypeCSMSelect = By.cssSelector("//b[text() = 'CSM']");

    public By monitoringTemplateLabel = By.id("FieldLabelID-1");
    public By monitoringTemplateTargetAge = By.id("num-targetDay-1");
    public By monitoringTemplateMinAge = By.id("targetDayRangeMin-1");
    public By monitoringTemplateMaxAge = By.id("targetDayRangeMax-1");
    public By monitoringTemplateSamples = By.id("num-numSamples-1");

    public By monitoringTemplateHouseThresholdTotalOPG = By.id("houseThresholdTotalOPG-1");
    public By monitoringTemplateComplexThresholdTotalOPG = By.id("complexThresholdTotalOPG-1");
    public By monitoringTemplateLowerLimitTotalOPG = By.id("lowerLimitTotalOPG-1");
    public By monitoringTemplateUpperLimitTotalOPG = By.id("upperLimitTotalOPG-1");

    public By monitoringTemplateHouseThresholdLargeOPG = By.id("houseThresholdLargeOPG-1");
    public By monitoringTemplateComplexThresholdLargeOPG = By.id("complexThresholdLargeOPG-1");
    public By monitoringTemplateLowerLimitLargeOPG = By.id("lowerLimitLargeOPG-1");
    public By monitoringTemplateUpperLimitLargeOPG = By.id("upperLimitLargeOPG-1");

    public By monitoringTemplateAddDaysButton = By.cssSelector(".monitoring-btn");

    public By monitoringTemplateGetTableRowsCount = By.cssSelector("#"+monitoringStrategyTemplateTable+" #"+ResultsCount);

    public String monitoringTemplateNameColumn = "#col-0 label";



    public MonitoringStrategyPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateMonitoringStrategyScreen() throws IOException, InterruptedException {
        NavigateToScreen.navigate(url_monitoringStrategy, "Monitoring Strategy", monitoringStrategyTitle);
    }

    public void templateLockFilterFunctionality() throws IOException, InterruptedException {
        click(monitoringTemplateTab);
        Lock(monitoringStrategyTemplateTable, "Monitoring Strategy", 0);
    }

    public void templateWildcardFunctionality() throws InterruptedException, IOException {
        click(monitoringTemplateTab);
        Wildcard(monitoringStrategyTemplateTable, "Monitoring Strategy", 0);
    }

    public void templateSortingFunctionality() throws InterruptedException, IOException {
        click(monitoringTemplateTab);
        Sorting(monitoringStrategyTemplateTable, "Monitoring Strategy", 0);
    }

    public void templatePaginationFunctionality() throws InterruptedException, IOException {
        click(monitoringTemplateTab);
        Pagination(monitoringStrategyTemplateTable, "Monitoring Strategy");
    }

    public void templateRowsPerPageFunctionality() throws InterruptedException, IOException {
        click(monitoringTemplateTab);
        RowsPerPage1(monitoringStrategyTemplateTable);
    }

    public void templateVerifyColumnsFunctionality(String[] columnNamesExpected) throws InterruptedException, IOException {
        click(monitoringTemplateTab);
        VerifyAllColumns(monitoringStrategyTemplateTable, columnNamesExpected);
//      VerifyAllColumns(complexLogTable, new String[]{"Name", "Monitoring Type", "# Collection Intervals", "Performance Criteria", "Action"});
    }

    public void planLockFilterFunctionality() throws IOException, InterruptedException {
        click(monitoringPlanTab);
        Lock(monitoringStrategyPlanTable, "Monitoring Strategy", 0);
    }

    public void planWildcardFunctionality() throws InterruptedException, IOException {
        click(monitoringPlanTab);
        Wildcard(monitoringStrategyPlanTable, "Monitoring Strategy", 0);
    }

    public void planSortingFunctionality() throws InterruptedException, IOException {
        click(monitoringPlanTab);
        Sorting(monitoringStrategyPlanTable, "Monitoring Strategy", 0);
    }

    public void planPaginationFunctionality() throws InterruptedException, IOException {
        click(monitoringPlanTab);
        Pagination(monitoringStrategyPlanTable, "Monitoring Strategy");
    }

    public void planRowsPerPageFunctionality() throws InterruptedException, IOException {
        click(monitoringPlanTab);
        RowsPerPage1(monitoringStrategyPlanTable);
    }

    public void planVerifyColumnsFunctionality(String[] columnNamesExpected) throws InterruptedException, IOException {
        click(monitoringPlanTab);
        VerifyAllColumns(monitoringStrategyPlanTable, columnNamesExpected);
    }


    public void addDays(String label, String targetAge, String minAge, String maxAge, String houseThreshold, String complexThreshold, String lowerLimit, String upperLimit) {
        type(monitoringTemplateLabel, label);
        type(monitoringTemplateTargetAge, targetAge);
        type(monitoringTemplateMinAge, minAge);
        type(monitoringTemplateMaxAge, maxAge);
        type(monitoringTemplateSamples, "10");

        type(monitoringTemplateHouseThresholdTotalOPG, houseThreshold);
        type(monitoringTemplateComplexThresholdTotalOPG, complexThreshold);
        type(monitoringTemplateLowerLimitTotalOPG, lowerLimit);
        type(monitoringTemplateUpperLimitTotalOPG, upperLimit);

        type(monitoringTemplateHouseThresholdLargeOPG, houseThreshold);
        type(monitoringTemplateComplexThresholdLargeOPG, complexThreshold);
        type(monitoringTemplateLowerLimitLargeOPG, lowerLimit);
        type(monitoringTemplateUpperLimitLargeOPG, upperLimit);

        click(monitoringTemplateAddDaysButton);
    }


    public void createNewTemplate() throws IOException {
        try {
            test = extent.createTest("AN-Template-20: Verify user can create a template");
            SoftAssert softAssert = new SoftAssert();

            BaseTest driver = new BaseTest();
            driver.getDriver().get(url_monitoringStrategy);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            click(monitoringTemplateTab);
            click(monitoringCreateNewTemplateButton);

            //header title check
            softAssert.assertEquals(getText(popupHeaderTitle), "Create Monitoring Strategy Template");

            //mandatory fields check
            click(monitoringTemplateName);
            click(monitoringTemplateType);
            click(monitoringTemplateName);
            softAssert.assertEquals(size(monitoringTemplateNameMandatoryCheck), 1, "Mandatory field error not displayed for Name field");
            softAssert.assertEquals(size(monitoringTemplateTypeMandatoryCheck), 1, "Mandatory field error not displayed for Type field");
            softAssert.assertEquals(size(popupSubmitButtonDisabled), 1, "Submit button is disabled when mandatory fields are empty");

            //enter valid data
            type(monitoringTemplateName, "TestComplexSite_" + dateYYYYMMDD);
            type(monitoringTemplateType, "CSM");
            click(monitoringTemplateTypeCSMSelect);

            //verify user cannot save template without adding at least 1 day
            click(popupSubmitButton);
            softAssert.assertEquals(alertMessage, "Add at least one day");

            addDays("Day 7", "7", "4", "10", "50%", "50%", "10000", "20000");
            addDays("Day 14", "14", "7", "20", "50%", "50%", "20000", "30000");
            addDays("Day 21", "21", "10", "30", "60%", "40%", "30000", "40000");
            addDays("Day 28", "28", "14", "40", "67%", "33%", "40000", "50000");
            addDays("Day 35", "35", "18", "50", "67%", "33%", "50000", "60000");
            addDays("Day 49", "49", "24", "60", "67%", "33%", "50000", "60000");

            click(popupSubmitButton);
            softAssert.assertEquals(alertMessage, "Monitoring Strategy has been saved successfully");


            String tableRowsCount = getText(monitoringTemplateGetTableRowsCount);
            for(int i=0;i<=Integer.parseInt(tableRowsCount); i++) {
                    if(getText(By.cssSelector("#row-"+i+" "+monitoringTemplateNameColumn)).equals("")) {

                    }
            }


            softAssert.assertAll();
            test.pass("New Template created successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);

        } catch (AssertionError er) {
            test.fail("New Template failed to create");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("New Template failed to create");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


}
