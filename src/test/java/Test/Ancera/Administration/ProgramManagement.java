package Test.Ancera.Administration;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;

import org.apache.groovy.json.internal.IO;
import org.apache.poi.ss.usermodel.CellStyle;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Config.BaseTest;
import MiscFunctions.Constants;
import MiscFunctions.DB_Config_DW;
import MiscFunctions.Methods;
import MiscFunctions.Queries;
import Models.ProgramManagementModel;
import Test.Ancera.Login.LoginTest;

import static PageObjects.FlockManagementPage.*;
import static PageObjects.ProgramManagementPage.*;
import static PageObjects.BasePage.*;
import static LogViewFunctions.FilterLock.*;
import static LogViewFunctions.FilterWildcard.*;
import static LogViewFunctions.FilterSort.*;
import static LogViewFunctions.Pagination.*;
import static LogViewFunctions.RowsPerPage.*;
import static MiscFunctions.Constants.*;
import static MiscFunctions.DateUtil.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import static LogViewFunctions.LogCSVExport.*;
import static Models.ProgramManagementModel.*;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

import org.openqa.selenium.support.ui.Select;


public class ProgramManagement extends BaseTest {


    @BeforeTest
    public void extent() throws InterruptedException, IOException {
        spark = new ExtentSparkReporter("target/Reports/Administration_Program_Management" + date + ".html");
        spark.config().setReportName("Program Management Test Report");
        DB_Config_DW.test();
    }

    @BeforeClass
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
    }

/*
    @Test(enabled = true, priority = 2)
    public void CreatePrograms() throws InterruptedException, IOException, SQLException {
        lstProgramManagementSearch = ProgramManagementModel.FillData();

        getDriver().get(Constants.url_programManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);

        for (ProgramManagementModel objModel : lstProgramManagementSearch) {
            try {
                if (!objModel.Program.equals("Treatment")) {
                    test = extent.createTest(objModel.TestCaseNameCreate);
                    steps = test.createNode(Scenario.class, Steps);
                    results = test.createNode(Scenario.class, Results);

                    steps.createNode("1. Click on Create New Program button");
                    steps.createNode("2. Add valid data in all fields");
                    steps.createNode("3. Click on save button");

                    SoftAssert softAssert = new SoftAssert();

                    getDriver().findElement(By.xpath("//*[text()= 'Vaccine Programs ']")).click();
                    waitElementInvisible(loading_cursor);
                    getDriver().findElement(By.xpath("//*[text()=' Register New Program']")).click();
                    waitElementInvisible(loading_cursor);

                    //Program Name
                    softAssert.assertEquals(getDriver().findElements(By.cssSelector("#btn-save.disabled-v2")).size(), 2, "Mandatory check failed");
                    getDriver().findElement(programName).sendKeys(objModel.ProgramName);
                    softAssert.assertEquals(getDriver().findElements(By.cssSelector("#btn-save.disabled-v2")).size(), 2, "Mandatory check failed");

                    //Target Pathogen
                    getDriver().findElement(programTargetPathogen).click();
                    Thread.sleep(500);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(".ng-option-label")).getText(), "Coccidia");
                    getDriver().findElement(programTargetPathogen).sendKeys(Keys.ENTER);
                    Thread.sleep(500);
                    softAssert.assertEquals(getDriver().findElements(By.cssSelector("#btn-save.disabled-v2")).size(), 2, "Mandatory check failed");

                    //Program Type
                    getDriver().findElement(programProgramType).sendKeys(objModel.ProgramType);
                    Thread.sleep(500);
                    softAssert.assertEquals(getDriver().findElements(By.cssSelector("#btn-save.disabled-v2")).size(), 2, "Mandatory check failed");
                    getDriver().findElement(programProgramType).sendKeys(Keys.ENTER);

                    //Vaccine Number of Applications on Flock
                    if (objModel.ProgramType.startsWith("Vaccine")) {
                        getDriver().findElement(programNoApplicationFlock).sendKeys("64");
                        softAssert.assertEquals(getDriver().findElements(By.cssSelector("#numOfApplicationId-error-container svg")).size(), 1, "Mandatory check failed on No of Application Flock");
                        Thread.sleep(500);
                        clear(programNoApplicationFlock);

                        String NoApplicationFlock = "2";
                        getDriver().findElement(programNoApplicationFlock).sendKeys(NoApplicationFlock);
                        Thread.sleep(500);

                        for (int i = 1; i <= Integer.parseInt(NoApplicationFlock); i++) {
                            getDriver().findElement(By.id(programDaysApplicationFlock + "-" + i)).sendKeys("" + i);
                        }
                    }
                    //Supplier
                    if (!objModel.ProgramType.equals("Feed")) {    //creating feed program without supplier
                        getDriver().findElement(programSupplier).sendKeys(ProgramManagementModel.SupplierName);
                        Thread.sleep(500);
                        if (getDriver().findElements(By.xpath("//*[text()='Add New + ']")).size() != 0) {
                            getDriver().findElement(By.xpath("//*[text()='Add New + ']")).click();
                        } else {
                            getDriver().findElement(By.cssSelector(".list-item")).click();
                        }
                        Thread.sleep(500);
                    }

                    //Complex
                    //	softAssert.assertEquals(size(programComplexMandatoryCheck), 1, "Complex should be mandatory field");
                    click(programComplexList);
                    if (Constants.config.url().contains("qa") || Constants.config.url().contains("dev")) {
                        //  getDriver().findElement(programComplexSearch).sendKeys(ProgramManagementModel.ComplexNameQA);
                        ResultSet getComplexNameResults = DB_Config_DW.getStmt().executeQuery(Queries.getComplexName);
                        while (getComplexNameResults.next()) {
                            String complexName = getComplexNameResults.getString("siteName");
                            System.out.println("Complex Name: " + complexName);
                            type(programComplexSearch, complexName);
                            enterKey(programComplexSearch);
                        }
                    }
                    if (Constants.config.url().contains("uat")) {
                        getDriver().findElement(programComplexSearch).sendKeys(ProgramManagementModel.ComplexNameUAT);

                    }

                    Thread.sleep(1500);
                    waitElementInvisible(loading_cursor);
                    Thread.sleep(1000);
                    click(clickSearchItemFromHierarchy);

                    //Start Date
                    click(programStartDateIcon);
                    waitElementInvisible(loading_cursor);
                    Thread.sleep(500);
                    Methods method = new Methods();
                    WebElement dateWidgetTo = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#startDate .dp-popup"))).get(0);
                    List<WebElement> columns1 = dateWidgetTo.findElements(By.tagName("button"));
                    clickGivenDay(columns1, getFirstDay());
                    Thread.sleep(500);

                    //End Date
                    getDriver().findElement(By.cssSelector("#endDate img")).click();
                    waitElementInvisible(loading_cursor);
                    Thread.sleep(500);
                    WebElement dateWidgetToEnd = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#endDate .dp-popup"))).get(0);
                    List<WebElement> columns2 = dateWidgetToEnd.findElements(By.tagName("button"));
                    clickGivenDay(columns2, getDay("30"));
                    Thread.sleep(700);

                    //Program Description
                    getDriver().findElement(programDescription).sendKeys(ProgramManagementModel.DescriptionName);


                    //Feed Details
                    if (objModel.ProgramType.equals("Feed")) {
                        getDriver().findElement(programFeedTypeDropdown).click();
                        Thread.sleep(500);
                        getDriver().findElement(programFeedTypeDropdown).sendKeys(Keys.ENTER);

                        getDriver().findElement(programFlockDayStart).sendKeys("1");

                        WebElement EndDay = getDriver().findElement(programFlockDayStart);
                        getDriver().findElement(with(By.tagName("input")).toRightOf(EndDay)).sendKeys("10");

                        WebElement ingredientCategory = getDriver().findElement(programFlockDayStart);
                        getDriver().findElement(with(By.tagName("input")).below(ingredientCategory)).click();
                        List<WebElement> ingredientCategories = getDriver().findElements(By.cssSelector(".ng-option-label"));
                        softAssert.assertEquals(ingredientCategories.get(0).getText(), "Antibiotic");
                        softAssert.assertEquals(ingredientCategories.get(1).getText(), "Coccidia Stat");
                        softAssert.assertEquals(ingredientCategories.get(2).getText(), "Natural");
                        ingredientCategories.get(0).click();
                        //getDriver().findElement(By.xpath(("//*[text()='Add Ingredient']"))).click();
                        //   WebElement ingredient = getDriver().findElement(ingredientFeedTypeDropdown);
                        //    getDriver().findElement(with(By.tagName("input")).below(ingredient)).click();
                        if (Constants.config.url().contains("qa") || (Constants.config.url().contains("dev"))) {
                            getDriver().findElement(programIngredient).sendKeys(ProgramManagementModel.ingredientNameQA);
                            Thread.sleep(500);

                            getDriver().findElement(By.xpath("//b[contains(text(),'Ingredient')]")).click();

                        }
                        if (Constants.config.url().contains("uat")) {
                            getDriver().findElement(programIngredient).sendKeys(ProgramManagementModel.ingredientName);
                            Thread.sleep(500);
                            getDriver().findElement(By.xpath("//b[contains(text(),'Narasin')]")).click();

                        }
                    }

                    //Bioshuttle Details
                    if (objModel.ProgramType.equals("Bioshuttle")) {
                        getDriver().findElement(By.id("bioShuttleVaccineName")).sendKeys("BioShuttle Vaccine");    //bioshuttle name field
                        getDriver().findElement(By.id("fcBioshuttleFeedName")).sendKeys("Bioshuttle feed");
                        getDriver().findElement(By.id("num-numOfApplicationId")).sendKeys("1");
                        getDriver().findElement(By.id("num-flockDayApplicationId-1")).sendKeys("1");
                        getDriver().findElement(By.id("num-flockDayApplicationId-1")).sendKeys("2");
                        getDriver().findElement(programFeedTypeDropdown).click();
                        getDriver().findElement(programFeedTypeDropdown).sendKeys(Keys.ENTER);


                        type(programBioshuttleFlockDayStart, "64");
                        softAssert.assertEquals(size(programBioshuttleFlockDayStartError), 0, "Error did not appeared");
                        clear(programBioshuttleFlockDayStart);
                        type(programBioshuttleFlockDayStart, "1");
                        softAssert.assertEquals(size(programBioshuttleFlockDayStartError), 1, "Error did not hiden");

                        type(programBioshuttleFlockDayEnd, "64");
                        softAssert.assertEquals(size(programBioshuttleFlockDayEndError), 0, "Error did not appeared");
                        clear(programBioshuttleFlockDayEnd);
                        type(programBioshuttleFlockDayEnd, "10");
                    }

                    //getScreenshot();
                    getDriver().findElement(By.xpath(("//button[text() = ' Submit ']"))).click();

                    waitElementInvisible(loading_cursor);
                    Thread.sleep(1500);
                    softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New program has been created successfully");

                    //Feed Verification
                    if (objModel.ProgramType.equals("Feed")) {
                        waitElementVisible(By.cssSelector("tr:nth-child(1) " + programFeedProgramNameCol));
                        softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programFeedProgramNameCol)).getText(), ProgramManagementModel.FeedProgramName);
                        softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programFeedSupplierNameCol)).getText(), "");
                        softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programFeedDescriptionCol)).getText(), ProgramManagementModel.DescriptionName);
                        //  softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programFeedFeedTypesCol)).getText(), "Feed Type 1");
                        //  softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programFeedStartDateCol)).getText(), dateMM + "/01/" + dateYYYY);
                        //  softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programFeedEndDateCol)).getText(), dateMM + "/30/" + dateYYYY);
                    }

                    //Vaccine Verification
                    if (objModel.ProgramType.equals("Vaccine")) {
                        waitElementVisible(By.cssSelector("tr:nth-child(1) " + programVaccineProgramNameCol));
                        //  softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programVaccineProgramNameCol)).getText(), ProgramManagementModel.VaccineProgramName);
                        //  softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programVaccineSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
                        softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programVaccineNumberOfApplicationFlockCol)).getText(), "2");
                        softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programVaccineDescriptionCol)).getText(), ProgramManagementModel.DescriptionName);
                        softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programVaccineStartDateCol)).getText(), dateMM + "/01/" + dateYYYY);
                        //    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programVaccineEndDateCol)).getText(), dateMM + "/30/" + dateYYYY);
                        softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programVaccineFlockDayApplicationCol)).getText(), "1,2");
                    }

                    //Vaccine Bioshuttle Verification
                    if (objModel.ProgramType.equals("Bioshuttle")) {
                        waitElementVisible(By.cssSelector("tr:nth-child(1) " + programBioshuttleProgramNameCol));
                        softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programBioshuttleProgramNameCol)).getText(), ProgramManagementModel.BioshuttleProgramName);
                        //	softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
                        //	softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleNumberOfApplicationFlockCol)).getText(), "2");
                        softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programBioshuttleDescriptionCol)).getText(), ProgramManagementModel.DescriptionName);
                        softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programBioshuttleStartDateCol)).getText(), dateMM + "/01/" + dateYYYY);
                        //	softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programBioshuttleEndDateCol)).getText(), dateMM + "/30/" + dateYYYY);
                        softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programBioshuttleFlockDayApplicationCol)).getText(), "12");
                        //	softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleFlockDayStartCol)).getText(), "1");
                        //	softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleFlockDayEndCol)).getText(), "10");
                    }

                    softAssert.assertAll();
                    test.pass("New Program created successfully");
                    results.createNode("New Program created successfully");
                    getScreenshot();
                    saveResult(ITestResult.SUCCESS, null);
                }
            } catch (AssertionError er) {
                test.fail("New Program failed to create");
                results.createNode("New Program failed to create");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("New Program failed to create");
                results.createNode("New Program failed to create");
                saveResult(ITestResult.FAILURE, ex);
            }

        }
    }


    @Test(priority = 3, enabled = true, description = "Create Treatment functionality")
    public void VerifyCreateTreatmentFunctionality() throws InterruptedException, IOException {
        try {
            test = extent.createTest("Verify Create Treatment functionality on Program Management Screen ");
            steps = test.createNode(Scenario.class, Steps);
            results = test.createNode(Scenario.class, Results);

            getDriver().get(url_programManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            getScreenshot();
            SoftAssert softAssert = new SoftAssert();
            getDriver().findElement(By.xpath("//*[text()= 'Vaccine Programs ']")).click();
            waitElementInvisible(loading_cursor);
            getDriver().findElement(By.xpath("//*[text()=' Record Treatment']")).click();
            waitElementInvisible(loading_cursor);
            getDriver().findElement(By.cssSelector("#create-treatment")).isEnabled();
            //    click(recordTreatmentButton);
            waitElementVisible(createTreatmentHeading);
            click(complexField);
            // waitElementClickable(Canton);
            click(Canton);
            waitElementInvisible(loading_cursor);
            click(farmField);
            click(farmFieldValue);
            waitElementInvisible(loading_cursor);
            //Start Date
            click(startDateField);
            waitElementInvisible(loading_cursor);
            Thread.sleep(500);
            Methods method = new Methods();
            WebElement dateWidgetTo = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#startDate .dp-popup"))).get(0);
            List<WebElement> columns1 = dateWidgetTo.findElements(By.tagName("button"));
            clickGivenDay(columns1, getFirstDay());
            Thread.sleep(500);

            //End Date
            click(endDateField);
            waitElementInvisible(loading_cursor);
            Thread.sleep(500);
            WebElement dateWidgetToEnd = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#endDate .dp-popup"))).get(0);
            List<WebElement> columns2 = dateWidgetToEnd.findElements(By.tagName("button"));
            clickGivenDay(columns2, getDay("28"));
            Thread.sleep(700);

            //Flock placemment date field value select
            click(flockPlacementDateField);
            Thread.sleep(700);
            click(flockPlacementDateFieldValue);
            waitElementInvisible(loading_cursor);

            //select houses applied field
            click(housesAppliedField);
            Thread.sleep(700);
            click(housesAppliedValue);
            waitElementInvisible(loading_cursor);
            click(treatmentNameField);
            Thread.sleep(700);
            getDriver().findElement(treatmentNameField).sendKeys(ProgramManagementModel.TreatmentProgramName);
            //  type(treatmentNameField, "Treatment name");
            click(treatmentDescriptionField);
            type(treatmentDescriptionField, "Treatment Program");
            getDriver().findElement(programSupplier).sendKeys(ProgramManagementModel.SupplierName);
            click(targetField);
            Thread.sleep(700);
            click(targetFieldValue);

            waitElementInvisible(loading_cursor);
            getDriver().findElement(formTreatmentField).sendKeys(formTreatmentName);

            //Verify business validations for flock age start field and flock age end field
            WebElement flockAgeStart = getDriver().findElement(By.id("num-FlockDayStartId"));
            softAssert.assertEquals(flockAgeStart.getAttribute("value"), "0");
            WebElement flockAgeEnd = getDriver().findElement(By.id("num-flockDayEndId"));
            softAssert.assertEquals(flockAgeEnd.getAttribute("value"), "27");

            click(saveButton);

            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            softAssert.assertEquals(getText(alertMessage), "New treatment has been created successfully");

            waitElementVisible(By.cssSelector("tr:nth-child(1) " + programTreatmentProgramNameCol));
            softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programTreatmentProgramNameCol)).getText(), ProgramManagementModel.TreatmentProgramName);
            softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programTreatmentSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
            softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programTreatmentDescriptionCol)).getText(), ProgramManagementModel.DescriptionName);
            softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programTreatmentStartDateCol)).getText(), dateMM + "/01/" + dateYYYY);
            softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programTreatmentEndDateCol)).getText(), dateMM + "/30/" + dateYYYY);

            softAssert.assertAll();
            test.pass("Create Treatment functionality passed successfully on Program Management screen");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Field Access functionality failed for feed programs on Program Management screen");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Field Access functionality failed.");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(enabled = true, priority = 4)
    public void UpdatePrograms() throws InterruptedException, IOException {
        lstProgramManagementSearch = ProgramManagementModel.FillData();

        getDriver().get(Constants.url_programManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);

        for (ProgramManagementModel objModel : lstProgramManagementSearch) {
            try {
                test = extent.createTest(objModel.TestCaseNameUpdate);
                steps = test.createNode(Scenario.class, Steps);
                results = test.createNode(Scenario.class, Results);

                steps.createNode("1. Click on Create New Program button");
                steps.createNode("2. Add valid data in all fields");
                steps.createNode("3. Click on save button");

                SoftAssert softAssert = new SoftAssert();

                getDriver().findElement(By.xpath("//*[text()= '" + objModel.Program + " ']")).click();
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);

                List<WebElement> programsName = getDriver().findElements(By.cssSelector(objModel.ProgramName_CSS));

                for (int i = 1; i <= programsName.size(); i++) {
                    if (getDriver().findElement(By.cssSelector("tr:nth-child(" + i + ") " + objModel.ProgramName_CSS)).getText().equals(objModel.ProgramName)) {
                        scroll(By.xpath("//*[@id='" + objModel.ProgramTable + "'] //*[text()='Action']"));
                        waitElementClickable(By.id(objModel.EditButtonPre + "" + i + "-" + objModel.ButtonPost));
                        Thread.sleep(1000);
                        getDriver().findElement(By.id(objModel.EditButtonPre + "" + i + "-" + objModel.ButtonPost)).click();
                        break;
                    }
//					else {
//                        Assert.assertTrue(false);
//                    }
                }

                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);
                //Program Description
                if (!objModel.Program.equals("Treatment")) {
                    type(programDescription, " Updated");
                } else {
                    type(programTreatmentDescription, " Updated");
                }
                Thread.sleep(500);
                getScreenshot();
                click(popupSaveButton);

                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);

                softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Program details updated");

                //Feed Verification
                if (objModel.ProgramType.equals("Feed")) {
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programFeedProgramNameCol)).getText(), ProgramManagementModel.FeedProgramName);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programFeedSupplierNameCol)).getText(), "");
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programFeedDescriptionCol)).getText(), ProgramManagementModel.DescriptionName + " Updated");
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programFeedFeedTypesCol)).getText(), "Feed Type 1");
                    //  softAssert.assertEquals(getDriver().findElement(By.cssSelector(programFeedStartDateCol)).getText(), dateMM + "/01/" + dateYYYY);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programFeedEndDateCol)).getText(), dateMM + "/30/" + dateYYYY);
                }

                //Treatment Verification
                if (objModel.ProgramType.equals("Treatment")) {
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programTreatmentProgramNameCol)).getText(), ProgramManagementModel.TreatmentProgramName);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programTreatmentSupplierNameCol)).getText(), "");
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programTreatmentDescriptionCol)).getText(), ProgramManagementModel.DescriptionName + " Updated");
                }

                //Vaccine Verification
                if (objModel.ProgramType.equals("Vaccine")) {
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programVaccineProgramNameCol)).getText(), ProgramManagementModel.VaccineProgramName);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programVaccineSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programVaccineNumberOfApplicationFlockCol)).getText(), "2");
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programVaccineDescriptionCol)).getText(), ProgramManagementModel.DescriptionName + " Updated");
                    //    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programVaccineStartDateCol)).getText(), dateMM + "/01/" + dateYYYY);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programVaccineEndDateCol)).getText(), dateMM + "/30/" + dateYYYY);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programVaccineFlockDayApplicationCol)).getText(), "1,2");
                }

                //Vaccine Bioshuttle Verification
                if (objModel.ProgramType.equals("Bioshuttle")) {
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programBioshuttleProgramNameCol)).getText(), ProgramManagementModel.BioshuttleProgramName);
                    //	softAssert.assertEquals(getDriver().findElement(By.cssSelector(programBioshuttleSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
                    //	softAssert.assertEquals(getDriver().findElement(By.cssSelector(programBioshuttleNumberOfApplicationFlockCol)).getText(), "2");
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programBioshuttleDescriptionCol)).getText(), ProgramManagementModel.DescriptionName + " Updated");
                    //softAssert.assertEquals(getDriver().findElement(By.cssSelector(programBioshuttleStartDateCol)).getText(),  "01/" + dateMM +"/" + dateYYYY);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programBioshuttleEndDateCol)).getText(), dateMM + "/30/" + dateYYYY);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programBioshuttleFlockDayApplicationCol)).getText(), "12");
                    //	softAssert.assertEquals(getDriver().findElement(By.cssSelector(programBioshuttleFlockDayStartCol)).getText(), "1");
                    //	softAssert.assertEquals(getDriver().findElement(By.cssSelector(programBioshuttleFlockDayEndCol)).getText(), "10");
                }
                softAssert.assertAll();
                test.pass("New Program updated successfully");
                results.createNode("New Program updated successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("New Program failed to update");
                results.createNode("New Program failed to update");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("New Program failed to update");
                results.createNode("New Program failed to update");
                saveResult(ITestResult.FAILURE, ex);
            }
        }
    }


    @Test(enabled = true, priority = 5)
    public void DuplicatePrograms() throws InterruptedException, IOException {
        lstProgramManagementSearch = ProgramManagementModel.FillData();

        getDriver().get(Constants.url_programManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);

        for (ProgramManagementModel objModel : lstProgramManagementSearch) {
            try {
                test = extent.createTest(objModel.TestCaseNameDuplicate);
                preconditions = test.createNode(Scenario.class, PreConditions);
                steps = test.createNode(Scenario.class, Steps);
                results = test.createNode(Scenario.class, Results);

                preconditions.createNode("1. Go to url " + Constants.url_login);
                preconditions.createNode("2. Login with valid credentials; user navigates to home page");
                preconditions.createNode("3. Hover to sidebar to expand the menu");
                preconditions.createNode("4. Click on Administration and select Program Management; Program Management screen opens");
                steps.createNode("1. Click on Create New Program button");
                steps.createNode("2. Add valid data in all fields");
                steps.createNode("3. Click on save button");

                SoftAssert softAssert = new SoftAssert();

                getDriver().findElement(By.xpath("//*[text()= '" + objModel.Program + " ']")).click();
                waitElementInvisible(loading_cursor);

                Thread.sleep(1000);

                List<WebElement> programsName = getDriver().findElements(By.cssSelector(objModel.ProgramName_CSS));

                for (int i = 1; i <= programsName.size(); i++) {
                    System.out.println(getDriver().findElement(By.cssSelector("tr:nth-child(" + i + ") " + objModel.ProgramName_CSS)).getText());

                    if (getDriver().findElement(By.cssSelector("tr:nth-child(" + i + ") " + objModel.ProgramName_CSS)).getText().equals(objModel.ProgramName)) {
                        System.out.println("gfgdfg");
                        scroll(By.xpath("//*[@id='" + objModel.ProgramTable + "'] //*[text()='Action']"));
                        waitElementClickable(By.id(objModel.EditButtonPre + "" + i + "-" + objModel.ButtonPost));
                        Thread.sleep(1000);
                        //	getDriver().findElement(By.id(objModel.CopyButtonPre+""+i+"-"+objModel.ButtonPost)).click();
                        getDriver().findElement(By.xpath("//*[@id = '" + objModel.CopyButtonPre + "" + i + "-" + objModel.ButtonPost + "']")).click();

                        break;
                    }
//					else {
//						Assert.assertTrue(false, "Program not found");
//					}
                }

                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);

                type(programName, objModel.ProgramName + "_Copy");
                softAssert.assertEquals(getDriver().findElement(programTargetPathogenValue).getText(), "Coccidia", "Target Pathogen is empty ");
                softAssert.assertEquals(getDriver().findElement(programProgramTypeValue).getText(), objModel.ProgramType, " Program Type is empty ");
                softAssert.assertEquals(size(programProgramTypeDisabledCheck), 1, "Program Type is not disabled");


                //Supplier
                if (!objModel.ProgramType.equals("Feed")) {
                    softAssert.assertEquals(getDriver().findElement(programSupplier).getAttribute("value"), ProgramManagementModel.SupplierName, "Supplier name is empty ");
                }

                //	softAssert.assertEquals(size(programComplexMandatoryCheck), 1, "Complex should be mandatory field");
                softAssert.assertEquals(size(programComplexSelected), 1);
                softAssert.assertNotEquals(getDriver().findElement(By.id("startDate")).getAttribute("value"), "", "Start Date is empty");
                softAssert.assertNotEquals(getDriver().findElement(By.id("endDate")).getAttribute("value"), "", "End Date is empty");
                softAssert.assertNotEquals(getDriver().findElement(programDescription).getAttribute("value"), "", "Description is empty");
                clear(programDescription);
                type(programDescription, ProgramManagementModel.DescriptionName + "_Copy");

                //Vaccine Number of Applications on Flock
                if (objModel.ProgramType.startsWith("Bioshuttle")) {
                    String NoApplicationFlock = "1";
                    softAssert.assertEquals(getDriver().findElement(programNoApplicationFlock).getAttribute("value"), NoApplicationFlock, "No of Application Flock empty");

                    type(By.id("bioShuttleVaccineName"), "SinoVac");
                    scroll(By.id("fcBioshuttleFeedName"));
                    type(By.id("fcBioshuttleFeedName"), "SinoFeed");


                    for (int i = 1; i <= Integer.parseInt(NoApplicationFlock); i++) {
                        //	softAssert.assertEquals(Integer.parseInt(getDriver().findElement(By.id(programDaysApplicationFlock + "-" + i)).getAttribute("value")), i, "No of Application Flock Days empty");
                    }

                }

                //Feed Details
                if (objModel.ProgramType.equals("Feed")) {
                    softAssert.assertEquals(getDriver().findElement(programFeedTypeValue).getText(), "Feed Type 1");
                    //					softAssert.assertNotEquals(getDriver().findElement(programFlockDayStart).getAttribute("value"), "");

                    WebElement EndDay = getDriver().findElement(programFlockDayStart);
                    softAssert.assertNotEquals(getDriver().findElement(with(By.tagName("input")).toRightOf(EndDay)).getAttribute("value"), "");

                    //					WebElement ingredient = getDriver().findElement(programFeedTypeDropdown);
                    //					softAssert.assertEquals(getDriver().findElement(with(By.tagName("input")).below(ingredient)).getAttribute("value"), "Sugar");
                }


                //Bioshuttle Details
                if (objModel.ProgramType.equals("Bioshuttle")) {
                    //		softAssert.assertEquals(getDriver().findElement(By.xpath("//div[2]/div[1]/app-anc-input-box/div/input[1]")).getAttribute("value"), "BioShuttle Vaccine");
                }

                //getScreenshot();
                getDriver().findElement(By.xpath(("//button[text() = ' Submit ']"))).click();

                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);

                softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New program has been created successfully");

                //Feed Verification
                if (objModel.ProgramType.equals("Feed")) {
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programFeedProgramNameCol)).getText(), ProgramManagementModel.FeedProgramName + "_Copy");
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programFeedSupplierNameCol)).getText(), "");
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programFeedDescriptionCol)).getText(), ProgramManagementModel.DescriptionName + "_Copy");
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programFeedFeedTypesCol)).getText(), "Feed Type 1");
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programFeedStartDateCol)).getText(), dateMM + "/01/" + dateYYYY);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programFeedEndDateCol)).getText(), dateMM + "/30/" + dateYYYY);
                }

                //Vaccine Verification
                if (objModel.ProgramType.equals("Vaccine")) {
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programVaccineProgramNameCol)).getText(), ProgramManagementModel.VaccineProgramName + "_Copy");
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programVaccineSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programVaccineNumberOfApplicationFlockCol)).getText(), "2");
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programVaccineDescriptionCol)).getText(), ProgramManagementModel.DescriptionName + "_Copy");
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programVaccineStartDateCol)).getText(), dateMM + "/01/" + dateYYYY);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programVaccineEndDateCol)).getText(), dateMM + "/30/" + dateYYYY);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programVaccineFlockDayApplicationCol)).getText(), "1,2");
                }

                //Vaccine Bioshuttle Verification
                if (objModel.ProgramType.equals("Bioshuttle")) {
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programBioshuttleProgramNameCol)).getText(), ProgramManagementModel.BioshuttleProgramName + "_Copy");
                    //	softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
                    //	softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleNumberOfApplicationFlockCol)).getText(), "2");
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programBioshuttleDescriptionCol)).getText(), ProgramManagementModel.DescriptionName + "_Copy");
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programBioshuttleStartDateCol)).getText(), dateMM + "/01/" + dateYYYY);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programBioshuttleEndDateCol)).getText(), dateMM + "/30/" + dateYYYY);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programBioshuttleFlockDayApplicationCol)).getText(), "12");
                    //	softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleFlockDayStartCol)).getText(), "1");
                    //	softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleFlockDayEndCol)).getText(), "10");
                }

                softAssert.assertAll();
                test.pass("New Program copied successfully");
                results.createNode("New Program copied successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("New Program failed to copy");
                results.createNode("New Program failed to copy");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("New Program failed to copy");
                results.createNode("New Program failed to copy");
                saveResult(ITestResult.FAILURE, ex);
            }
        }
    }


    @Test(enabled = true, priority = 6)
    public void DeleteDuplicatePrograms() throws InterruptedException, IOException {
        lstProgramManagementSearch = ProgramManagementModel.FillData();

        getDriver().get(Constants.url_programManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);

        for (ProgramManagementModel objModel : lstProgramManagementSearch) {
            try {
                test = extent.createTest(objModel.TestCaseNameDelete);
                steps = test.createNode(Scenario.class, Steps);
                results = test.createNode(Scenario.class, Results);

                steps.createNode("1. Delete the created program");

                SoftAssert softAssert = new SoftAssert();
                getDriver().findElement(By.xpath("//*[text()= '" + objModel.Program + " ']")).click();
                waitElementInvisible(loading_cursor);

                List<WebElement> programsName = getDriver().findElements(By.cssSelector(objModel.ProgramName_CSS));

                for (int i = 1; i <= programsName.size(); i++) {
                    if (getDriver().findElement(By.cssSelector("tr:nth-child(" + i + ") " + objModel.ProgramName_CSS)).getText().equals(objModel.ProgramName + "_Copy")) {
                        System.out.println("1");
                        scroll(By.xpath("//*[@id='" + objModel.ProgramTable + "'] //*[text()='Action']"));
                        waitElementClickable(By.id(objModel.DeleteButtonPre + "" + i + "-" + objModel.ButtonPost));
                        Thread.sleep(1000);
                        getDriver().findElement(By.id(objModel.DeleteButtonPre + "" + i + "-" + objModel.ButtonPost)).click();
                        break;
                    }
                }

                waitElementVisible(popupYesButton);
                click(popupYesButton);
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);

                softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Program details deleted");
                softAssert.assertAll();
                test.pass("New Program deleted successfully");
                results.createNode("New Program deleted successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("New Program failed to delete");
                results.createNode("New Program failed to delete");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("New Program failed to delete");
                results.createNode("New Program failed to delete");
                saveResult(ITestResult.FAILURE, ex);
            }
        }
    }

    @Test(enabled = true, priority = 7)
    public void DeletePrograms() throws InterruptedException, IOException {
        lstProgramManagementSearch = ProgramManagementModel.FillData();

        getDriver().get(Constants.url_programManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);

        for (ProgramManagementModel objModel : lstProgramManagementSearch) {
            try {
                test = extent.createTest(objModel.TestCaseNameDelete);
                steps = test.createNode(Scenario.class, Steps);
                results = test.createNode(Scenario.class, Results);

                steps.createNode("1. Delete the created program");

                SoftAssert softAssert = new SoftAssert();
                getDriver().findElement(By.xpath("//*[text()= '" + objModel.Program + " ']")).click();
                waitElementInvisible(loading_cursor);

                List<WebElement> programsName = getDriver().findElements(By.cssSelector(objModel.ProgramName_CSS));

                for (int i = 1; i <= programsName.size(); i++) {
                    if (getDriver().findElement(By.cssSelector("tr:nth-child(" + i + ") " + objModel.ProgramName_CSS)).getText().equals(objModel.ProgramName)) {
                        System.out.println("1");
                        scroll(By.xpath("//*[@id='" + objModel.ProgramTable + "'] //*[text()='Action']"));
                        waitElementClickable(By.id(objModel.DeleteButtonPre + "" + i + "-" + objModel.ButtonPost));
                        Thread.sleep(1000);
                        getDriver().findElement(By.id(objModel.DeleteButtonPre + "" + i + "-" + objModel.ButtonPost)).click();
                        break;
                    }
                }

                waitElementVisible(popupYesButton);
                click(popupYesButton);
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);

                softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Program details deleted");
                softAssert.assertAll();
                test.pass("New Program deleted successfully");
                results.createNode("New Program deleted successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("New Program failed to delete");
                results.createNode("New Program failed to delete");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("New Program failed to delete");
                results.createNode("New Program failed to delete");
                saveResult(ITestResult.FAILURE, ex);
            }
        }
    }


    @Test(enabled = true, priority = 8)
    public void VerifyColumns() throws InterruptedException, IOException {
        try {
            test = extent.createTest("AN-Program-09: Verify that all columns appear", "This testcase will verify that all columns appear in table");
            preconditions = test.createNode(Scenario.class, PreConditions);
            steps = test.createNode(Scenario.class, Steps);
            results = test.createNode(Scenario.class, Results);

            preconditions.createNode("1. Go to url " + Constants.url_login);
            preconditions.createNode("2. Login with valid credentials; user navigates to home page");
            preconditions.createNode("3. Hover to sidebar to expand the menu");
            preconditions.createNode("4. Click on Administration and select Program Management; Program Management screen opens");
            preconditions.createNode("5. Create Feed Program");
            steps.createNode("1. Verify all columns appear and columns have filter icon with them");

            SoftAssert softAssert = new SoftAssert();

            getDriver().get(Constants.url_programManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            String[] feedColumnNamesExpected = {"Program Name", "Supplier Name", "Description", "Feed Types", "Start Date", "End Date", "Complex", "Target Pathogen", "Action"};
            String[] treatmentColumnNamesExpected = {"", "Treatment Name", "Supplier Name", "Description", "Target", "Form", "Route", "Dosage", "Packages Consumed", "Package Size", "Start Date", "End Date", "Complex", "Flock Age Start", "Flock Age End", "Farm", "Houses", "Placement Date", "Action"};
            String[] vaccineColumnNamesExpected = {"Program Name", "Supplier Name", "Applications On Flock", "Description", "Start Date", "End Date", "Flock Day Applications", "Complex", "Target Pathogen", "Action"};
            String[] bioshuttleColumnNamesExpected = {"", "Program Name", "Program Type", "Description", "Target Pathogen", "Complex", "Start Date", "End Date", "Vaccine Name", "Vaccine Supplier", "No. of Applications on Flock", "Flock Day(s) Application", "Feed Program Name", "Feed Program Supplier", "Feed Types", "Feed Type Categories", "Action"};
            String[] utilizationColumnNamesExpected = {"Flock ID", "Integrator Flock ID", "Program Type", "Program Name", "Complex", "Farm", "House Placement", "Start Date", "End Date"};


            List<WebElement> feedColumnNamesActual = getDriver().findElements(By.cssSelector("#" + programFeedTable + " th"));
            int sizeFeedTable = feedColumnNamesActual.size();
            for (int i = 0; i < sizeFeedTable; i++) {
                softAssert.assertEquals(feedColumnNamesActual.get(i).getText(), feedColumnNamesExpected[i]);
            }
            softAssert.assertEquals(size(By.xpath("//*[@id = '" + programFeedTable + "'] //*[contains(@id, '_show-filter')]")), 6, "All filter now displaying filter not displaying for feed tab");

            System.out.println("1");

            click(programTreatmentProgramTab);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            List<WebElement> treatmentColumnNamesActual = getDriver().findElements(By.cssSelector("#" + programTreatmentTable + " th"));
            int sizeTreatmentTable = treatmentColumnNamesActual.size() - 1;


            for (int i = 0; i < sizeTreatmentTable; i++) {
                System.out.println(treatmentColumnNamesActual.get(i).getText() + "--->" + treatmentColumnNamesExpected[i]);
                softAssert.assertEquals(treatmentColumnNamesActual.get(i).getText(), treatmentColumnNamesExpected[i]);
            }
            softAssert.assertEquals(size(By.xpath("//*[@id = '" + programTreatmentTable + "'] //*[contains(@id, '_show-filter')]")), 15, "All filter now displaying filter not displaying for treatment tab");

            click(programVaccineProgramTab);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            List<WebElement> vaccineColumnNamesActual = getDriver().findElements(By.cssSelector("#" + programVaccineTable + " th"));
            int sizeVaccineTable = vaccineColumnNamesActual.size();
            for (int i = 0; i < sizeVaccineTable; i++) {
                softAssert.assertEquals(vaccineColumnNamesActual.get(i).getText(), vaccineColumnNamesExpected[i]);
            }

            softAssert.assertEquals(size(By.xpath("//*[@id = '" + programVaccineTable + "'] //*[contains(@id, '_show-filter')]")), 7, "All filter now displaying filter not displaying in vaccine tab");


            click(programBioshuttleProgramTab);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            List<WebElement> bioshuttleColumnNamesActual = getDriver().findElements(By.cssSelector("#" + programBioshuttleTable + " th"));
            int sizeBioshuttleTable = vaccineColumnNamesActual.size() - 1;
            for (int i = 0; i < sizeBioshuttleTable; i++) {
                System.out.println(bioshuttleColumnNamesActual.get(i).getText() + "--->" + bioshuttleColumnNamesExpected[i]);
                softAssert.assertEquals(bioshuttleColumnNamesActual.get(i).getText(), bioshuttleColumnNamesExpected[i]);
            }

            softAssert.assertEquals(size(By.xpath("//*[@id = '" + programBioshuttleTable + "'] //*[contains(@id, '_show-filter')]")), 12, "All filter now displaying filter not displaying in bioshuttle tab");


            click(programProgramUtilizationTab);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            List<WebElement> utilizationColumnNamesActual = getDriver().findElements(By.cssSelector("#" + programUtilizationTable + " th"));
            int sizeUtilizationTable = vaccineColumnNamesActual.size() - 1;


            for (int i = 0; i < sizeUtilizationTable; i++) {
                System.out.println(utilizationColumnNamesActual.get(i).getText() + "-->" + utilizationColumnNamesExpected[i]);
                softAssert.assertEquals(utilizationColumnNamesActual.get(i).getText(), utilizationColumnNamesExpected[i]);
            }

            softAssert.assertEquals(size(By.xpath("//*[@id = '" + programUtilizationTable + "'] //*[contains(@id, '_show-filter')]")), 8, "All filter now displaying filter not displaying in bioshuttle tab");

            softAssert.assertAll();
            test.pass("All columns displayed successfully");
            results.createNode("All columns displayed successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("All columns did not displayed");
            results.createNode("All columns did not displayed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("All columns did not displayed");
            results.createNode("All columns did not displayed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    @Test(enabled = true, priority = 9)
    public void ComplexFilterTest() throws InterruptedException, IOException {
        lstProgramManagementSearch = ProgramManagementModel.FillData();

        getDriver().get(Constants.url_programManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        SoftAssert softAssert = new SoftAssert();

        for (ProgramManagementModel objModel : lstProgramManagementSearch) {
            try {
                test = extent.createTest("AN-Program: Verify Site Name Filter Functionality");
                results = test.createNode(Scenario.class, Results);

                getDriver().findElement(By.xpath("//*[text()= '" + objModel.Program + " ']")).click();
                waitElementInvisible(loading_cursor);


                String recordsBefore = getDriver().findElement(By.cssSelector("#" + objModel.ProgramTable + " #" + ResultsCount)).getText();

                scroll(By.cssSelector("#" + objModel.ProgramTable + " #complex" + "" + ShowFilter));
                Thread.sleep(500);
                click(By.cssSelector("#" + objModel.ProgramTable + " #complex" + "" + ShowFilter));
                waitElementInvisible(loading_cursor);
                Thread.sleep(700);

                if (getDriver().findElements(By.cssSelector("#sort-complex-" + objModel.ButtonPost + " tr")).size() >= 2) {

                    scroll(By.cssSelector("#sort-complex-" + objModel.ButtonPost + " tr:nth-child(2) label:nth-child(1)"));
                    click(By.cssSelector("#sort-complex-" + objModel.ButtonPost + " tr:nth-child(2) label:nth-child(1)"));

                    waitElementInvisible(loading_cursor);
                    scroll(By.cssSelector("#" + objModel.ProgramTable + " #list-title_apply"));
                    click(By.cssSelector("#" + objModel.ProgramTable + " #list-title_apply"));

                    waitElementInvisible(loading_cursor);
                    Thread.sleep(1000);
                    softAssert.assertNotEquals(getDriver().findElement(By.cssSelector("#" + objModel.ProgramTable + " #" + ResultsCount)).getText(), recordsBefore);


                    test.pass("Checkbox selected successfully");
                    getScreenshot();
                    saveResult(ITestResult.SUCCESS, null);
                }

            } catch (AssertionError er) {
                test.fail("Filer lock functionality failed");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("Filer lock functionality failed");
                saveResult(ITestResult.FAILURE, ex);
            }
        }
    }

 */


    @Test(priority = 10, enabled = true)
    public void LockFeed() throws InterruptedException, IOException {
        getDriver().get(url_programManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        getDriver().findElement(programFeedProgramTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Lock(programFeedTable, "Program Management", 0);
    }


    @Test(priority = 11, enabled = true)
    public void LockTreatment() throws InterruptedException, IOException {
        getDriver().findElement(programTreatmentProgramTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Lock(programTreatmentTable, "Program Management", 1);
    }


    @Test(priority = 12, enabled = true)
    public void LockVaccine() throws InterruptedException, IOException {
        getDriver().findElement(programVaccineProgramTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Lock(programVaccineTable, "Program Management", 0);
    }


    @Test(priority = 13, enabled = true)
    public void LockBioshuttle() throws InterruptedException, IOException {
        getDriver().findElement(programBioshuttleProgramTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Lock(programBioshuttleTable, "Program Management", 1);
    }


    @Test(priority = 14, enabled = true)
    public void LockUtilization() throws InterruptedException, IOException {
        getDriver().findElement(programProgramUtilizationTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Lock(programUtilizationTable, "Program Management", 0);
    }


    @Test(priority = 15, enabled = true)
    public void WildcardFeed() throws InterruptedException, IOException {
        getDriver().get(url_programManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        getDriver().findElement(programFeedProgramTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Wildcard(programFeedTable, "Program Management", 0);
    }


    @Test(priority = 16, enabled = true)
    public void WildcardTreatment() throws InterruptedException, IOException {
        getDriver().findElement(programTreatmentProgramTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Wildcard(programTreatmentTable, "Program Management", 1);
    }


    @Test(priority = 17, enabled = true)
    public void WildcardVaccine() throws InterruptedException, IOException {
        getDriver().findElement(programVaccineProgramTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Wildcard(programVaccineTable, "Program Management", 0);
    }


    @Test(priority = 18, enabled = true)
    public void WildcardBioshuttle() throws InterruptedException, IOException {
        getDriver().findElement(programBioshuttleProgramTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Wildcard(programBioshuttleTable, "Program Management", 1);
    }


    @Test(priority = 19, enabled = true)
    public void WildcardUtilization() throws InterruptedException, IOException {
        getDriver().get(url_programManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        getDriver().findElement(programProgramUtilizationTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Wildcard(programUtilizationTable, "Program Management", 0);
    }


    @Test(priority = 20, enabled = true)
    public void sortingFeed() throws InterruptedException, IOException {
        getDriver().get(url_programManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        getDriver().findElement(programFeedProgramTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Sorting(programFeedTable, "Program Management", 0);
    }


    @Test(priority = 21, enabled = true)
    public void sortingTreatment() throws InterruptedException, IOException {
        getDriver().findElement(programTreatmentProgramTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Sorting(programTreatmentTable, "Program Management", 1);
    }


    @Test(priority = 22, enabled = true)
    public void sortingVaccine() throws InterruptedException, IOException {
        getDriver().findElement(programVaccineProgramTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Sorting(programVaccineTable, "Program Management", 0);
    }


    @Test(priority = 23, enabled = true)
    public void sortingBioshuttle() throws InterruptedException, IOException {
        getDriver().findElement(programBioshuttleProgramTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Sorting(programBioshuttleTable, "Program Management", 1);
    }


    @Test(priority = 24, enabled = true)
    public void sortingUtilization() throws InterruptedException, IOException {
        getDriver().findElement(programProgramUtilizationTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Sorting(programUtilizationTable, "Program Management", 0);
    }


    @Test(priority = 25, enabled = true)
    public void RowsPerPageFeed() throws InterruptedException, IOException {
        getDriver().get(url_programManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        getDriver().findElement(programFeedProgramTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        RowsPerPage1(programFeedTable);
    }

    @Test(priority = 26, enabled = true)
    public void RowsPerPageTreatment() throws InterruptedException, IOException {
        getDriver().findElement(programTreatmentProgramTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        RowsPerPage1(programTreatmentTable);
    }


    @Test(priority = 27, enabled = true)
    public void RowsPerPageVaccine() throws InterruptedException, IOException {
        getDriver().findElement(programVaccineProgramTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        RowsPerPage1(programVaccineTable);
    }


    @Test(priority = 28, enabled = true)
    public void RowsPerPageBioshuttle() throws InterruptedException, IOException {
        getDriver().findElement(programBioshuttleProgramTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        RowsPerPage1(programBioshuttleTable);
    }


    @Test(priority = 29, enabled = true)
    public void RowsPerPageUtilization() throws InterruptedException, IOException {
        getDriver().findElement(programProgramUtilizationTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        RowsPerPage1(programUtilizationTable);
    }


    @Test(priority = 30, enabled = true)
    public void PaginationFeed() throws InterruptedException, IOException {
        getDriver().get(url_programManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        getDriver().findElement(programFeedProgramTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Pagination(programFeedTable, "Program Management", ReportFilePath);
    }

    @Test(priority = 31, enabled = true)
    public void PaginationTreatment() throws InterruptedException, IOException {
        getDriver().findElement(programTreatmentProgramTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Pagination(programTreatmentTable, "Program Management", ReportFilePath);
    }

    @Test(priority = 32, enabled = true)
    public void PaginationVaccine() throws InterruptedException, IOException {
        getDriver().findElement(programVaccineProgramTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Pagination(programVaccineTable, "Program Management", ReportFilePath);
    }

    @Test(priority = 33, enabled = true)
    public void PaginationBioshuttle() throws InterruptedException, IOException {
        getDriver().findElement(programBioshuttleProgramTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Pagination(programBioshuttleTable, "Program Management", ReportFilePath);
    }

    @Test(priority = 34, enabled = true)
    public void PaginationUtilization() throws InterruptedException, IOException {
        getDriver().findElement(programProgramUtilizationTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        Pagination(programUtilizationTable, "Program Management", ReportFilePath);
    }


    @Test(priority = 35, enabled = true)
    public void ExportCSVFeed() throws InterruptedException, IOException {
        getDriver().get(url_programManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        getDriver().findElement(programFeedProgramTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        CSVExport1("Program Management", programFeedCSVFileName, programFeedTable, 1, 0);
    }


    @Test(priority = 36, enabled = true)
    public void ExportCSVTreatment() throws InterruptedException, IOException {
        getDriver().findElement(programTreatmentProgramTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        CSVExport1("Program Management", programTreatmentCSVFileName, programTreatmentTable, 2, 2);
    }


    @Test(priority = 37, enabled = true)
    public void ExportCSVVaccine() throws InterruptedException, IOException {
        getDriver().findElement(programVaccineProgramTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        CSVExport1("Program Management", programVaccineCSVFileName, programVaccineTable, 1, 0);
    }

    @Test(priority = 38, enabled = false)
    public void ExportCSVBioshuttle() throws InterruptedException, IOException {
        getDriver().get(url_programManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        getDriver().findElement(programBioshuttleProgramTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        CSVExport1("Program Management", programBioshuttleCSVFileName, programBioshuttleTable, 2, 2);
    }

    @Test(priority = 39, enabled = true)
    public void ExportCSVUtilization() throws InterruptedException, IOException {
        getDriver().get(url_programManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        getDriver().findElement(programProgramUtilizationTab).click();
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        CSVExport1("Program Management", programUtilizationCSVFileName, programUtilizationTable, 1, 0);
    }


    @Test(priority = 40, enabled = false, description = "Inline Edit Functionality for Feed Programs Tab")
    public void InlineEditFeedProgramsFunctionality() throws InterruptedException, IOException {
        try {
            test = extent.createTest("Verify inline edit functionality for Feed Programs tab on the Program Management Screen");
            steps = test.createNode(Scenario.class, Steps);
            results = test.createNode(Scenario.class, Results);

            getDriver().get(url_programManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            getScreenshot();
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertTrue(getDriver().findElement(By.id("edit-inline-access")).isEnabled(), "Inline Edit button is working");
            click(inlineEditButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2500);
            //	softAssert.assertEquals(size(By.cssSelector("#"+programFeedProgramTable+" #col-"+feedProgramsTargetPathogenColumn+" input")), 0, "Target Pathogen is editable field");
            clear(feedProgramsProgramNameColumn);
            type(feedProgramsProgramNameColumn, "Program Name field testing");
            clear(feedProgramDescriptionColumn);
            type(feedProgramDescriptionColumn, "Test inline edit by automation");
            type(feedProgramSupplierNameColumn, "Elanco");
            enterKey(feedProgramSupplierNameColumn);
            type(feedProgramsComplexColumn, "AS COMPLEX");
            enterKey(feedProgramsComplexColumn);
            type(feedProgramsStartDateColumn, "01012023");
            type(feedProgramsEndDateColumn, "01022023");
            //Click on inline button to save changes
            click(programInlineButtonSave);
            waitElementVisible(popupYesButton);
            click(popupYesButton);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getText(alertMessage), "Data saved successfully.");
            getDriver().navigate().refresh();
            waitElementInvisible(loading_cursor);

            softAssert.assertAll();

            test.pass("Program inline functionality tested successfully");
            results.createNode("Program inline functionality tested successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Flock inline failed");
            results.createNode("Flock inline failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Flock inline failed");
            results.createNode("Flock inline failed");
            saveResult(ITestResult.FAILURE, ex);
        }


    }

    @Test(priority = 41, enabled = false, description = "Inline Edit Functionality for Vaccine Programs Tab")
    public void VaccineProgramsInlineEditFunctionality() throws InterruptedException, IOException {
        try {
            test = extent.createTest("Verify inline edit functionality for Vaccine Programs Tab on Program Management Screen ");
            steps = test.createNode(Scenario.class, Steps);
            results = test.createNode(Scenario.class, Results);

            getDriver().get(url_programManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            getScreenshot();
            SoftAssert softAssert = new SoftAssert();
            getDriver().findElement(By.xpath("//*[text()= 'Vaccine Programs ']")).click();
            waitElementInvisible(loading_cursor);
            softAssert.assertTrue(getDriver().findElement(By.id("edit-inline-access")).isEnabled(), "Inline Edit button is working");
            click(inlineEditButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2500);
            //softAssert.assertEquals(size(By.cssSelector("#"+vaccineProgramsTable+" #col-"+vaccineProgramsTargetPathogenCol+" input")), 0, "Target Pathogen is editable field");
            if (getDriver().findElement(By.cssSelector("tr:nth-child(1) #col-" + vaccineProgramsTargetPathogenCol + " input")).isEnabled()) {
                softAssert.assertTrue(true, "Target Pathogen is an editable field");
            } else {
                softAssert.assertTrue(true, "Target Pathogen is not an editable field in inline edit mode");
            }
            clear(vaccineProgramsProgramNameField);
            type(vaccineProgramsProgramNameField, "Vaccine Programs Program Name field testing");
            type(vaccineProgramsSupplierNameField, "Elanco");
            enterKey(vaccineProgramsSupplierNameField);
            clear(vaccineProgramsDescriptionField);
            type(vaccineProgramsDescriptionField, "Vaccine Programs tab Test Description field inline edit by automation");
            type(vaccineProgramsStartDateField, "12012023");
            type(vaccineProgramsEndDateField, "13022023");
            click(vaccineProgramsApplicationsOnFlockField);
            //		waitElementVisible(vaccineTabApplicationsOnFlockPopup);
            clear(vaccineTabApplicationsOnFlockField);
            type(vaccineTabApplicationsOnFlockField, "2");
            clear(vaccineTabFlockDayApplicationField1);
            type(vaccineTabFlockDayApplicationField1, "1");
            clear(vaccineTabFlockDayApplicationField2);
            type(vaccineTabFlockDayApplicationField2, "2");
            click(popupSaveButton);
            //Click on inline button to save changes
            click(programInlineButtonSave);
            waitElementVisible(popupYesButton);
            click(popupYesButton);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getText(alertMessage), "Data saved successfully.");
            getDriver().navigate().refresh();
            waitElementInvisible(loading_cursor);

            softAssert.assertAll();

            test.pass("Program inline functionality tested successfully for vaccine programs tab");
            results.createNode("Program inline functionality tested successfully for vaccine programs tab");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Flock inline failed for vaccine programs tab");
            results.createNode("Flock inline failed for vaccine programs tab");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Flock inline failed for vaccine programs tab");
            results.createNode("Flock inline failed for vaccine programs tab");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(priority = 42, enabled = false, description = "Inline Edit Functionality for Vaccine Programs Tab")
    public void BioshuttleTabInlineEditFunctionality() throws InterruptedException, IOException {
        try {
            test = extent.createTest("Verify inline edit functionality for Bioshuttle Tab on Program Management Screen ");
            steps = test.createNode(Scenario.class, Steps);
            results = test.createNode(Scenario.class, Results);

            getDriver().get(url_programManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            getScreenshot();
            SoftAssert softAssert = new SoftAssert();
            getDriver().findElement(By.xpath("//*[text()= 'Bioshuttle ']")).click();
            waitElementInvisible(loading_cursor);
            //	softAssert.assertTrue(getDriver().findElement(By.id("edit-inline-access")).isEnabled(), "Inline Edit button is working");
            click(inlineEditButton);
            waitElementInvisible(loading_cursor);

            clear(bioshuttleProgramNameField);
            type(bioshuttleProgramNameField, "Bioshuttle Program Name field inline tested");
            clear(bioshuttleDescriptionField);
            type(bioshuttleDescriptionField, "Vaccine Programs tab Test Description field inline edit by automation");

            click(popupSaveButton);
            //Click on inline button to save changes
            click(programInlineButtonSave);
            waitElementVisible(popupYesButton);
            click(popupYesButton);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getText(alertMessage), "Data saved successfully.");
            getDriver().navigate().refresh();
            waitElementInvisible(loading_cursor);

            softAssert.assertAll();

            test.pass("Program inline functionality tested successfully for bioshuttle tab");
            results.createNode("Program inline functionality tested successfully for bioshuttle tab");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Flock inline failed for bioshuttle programs tab");
            results.createNode("Flock inline failed for bioshuttle programs tab");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Flock inline failed for bioshuttle tab");
            results.createNode("Flock inline failed for bioshuttle tab");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(priority = 43, enabled = false, description = "Field Access Functionality to check for Program Management screen")
    public void verifyFieldAccessButtonFunctionality() throws InterruptedException, IOException {
        try {
            test = extent.createTest("Verify field access functionality on Program Management Screen ");
            steps = test.createNode(Scenario.class, Steps);
            results = test.createNode(Scenario.class, Results);

            getDriver().get(url_programManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            getScreenshot();
            SoftAssert softAssert = new SoftAssert();
            //**Verify tooltip for Field Access Column**
            WebElement fieldAccess = getDriver().findElement(By.id("edit-field-access"));
            String tooltipText = fieldAccess.getAttribute("title");
            softAssert.assertTrue(tooltipText.equals("Field Access"), "First soft assert failed");
            //***Verify test case that Field Access column is Clickable or not***
            if (getDriver().findElement(By.id("edit-field-access")).isEnabled()) {
                softAssert.assertTrue(true, "Field Access Icon is clickable");
            } else {
                softAssert.assertTrue(true, "Field access Icon is not clickable");
            }
            click(fieldAccessIcon);
            if (getDriver().findElement(By.xpath("//label[contains(text(),'Field Order')]")).isDisplayed()) {
                softAssert.assertTrue(true, "Field Access column is displayed");
            } else {
                softAssert.assertTrue(true, "Field Access column is not displayed");
            }

            //***Verify reset default functionality for field access column TC: IE-3755, IE-3754***

            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            click(resetDefaultButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);


            click(saveButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), " Field Access changes have been saved successfully ");

           /* if (getDriver().findElement(By.xpath("//label[contains(text(),'Field Order')]")).isDisplayed()) {
                softAssert.assertTrue(true, "Field Access column is displayed");
            } else {
                softAssert.assertTrue(true, "Field Access column is not displayed");
            }*/

            test.pass("Field Access functionality tested successfully on Program Management screen");
            results.createNode("Field Access functionality tested successfully on Program Management screen");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Field Access functionality failed on Program Management screen");
            results.createNode("Field Access functionality failed on Program Management screen");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Field Access functionality failed.");
            results.createNode("Field Access functionality failed.");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(enabled = false, priority = 44, dependsOnMethods = {"verifyFieldAccessButtonFunctionality"})
    public void FieldAccessFeedTab() throws InterruptedException, IOException {

        steps.createNode("Click on field access icon to verify column hide functionality for feed programs tab");
        try {
            SoftAssert softAssert = new SoftAssert();
            getDriver().findElement(By.cssSelector("#feed-program-log #edit-field-access")).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(1500);

            if (getDriver().findElement(By.cssSelector("tr:nth-child(2) td:nth-child(4) input")).isSelected() == true) {
                getDriver().findElement(By.cssSelector("tr:nth-child(2) td:nth-child(4) label .rpt-fields")).click();
            }

            Thread.sleep(1000);
            getDriver().findElement(By.id("btn-save")).click();
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Field Access changes have been saved successfully");
            waitElementInvisible(loading_cursor);
            steps.createNode("Verify deselected column is hidden in the logview");
            softAssert.assertEquals(getDriver().findElements(By.id("supplierName_sort")).size(), 0);

            getDriver().findElement(By.cssSelector("#feed-program-log #edit-field-access")).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(1500);

            if (getDriver().findElement(By.cssSelector("tr:nth-child(2) td:nth-child(4) input")).isSelected() == false) {
                getDriver().findElement(By.cssSelector("tr:nth-child(2) td:nth-child(4) label .rpt-fields")).click();
            }

            Thread.sleep(1000);
            getDriver().findElement(By.id("btn-save")).click();
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Field Access changes have been saved successfully");
            waitElementInvisible(loading_cursor);

            steps.createNode("Verify checked column is displayed in the logview");
            softAssert.assertEquals(getDriver().findElements(By.id("supplierName_sort")).size(), 1);

            steps.createNode("Verify that the user cannot uncheck the following columns from the field access column");
            getDriver().findElement(By.cssSelector("#feed-program-log #edit-field-access")).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(1500);

            if (getDriver().findElement(By.cssSelector("tr:nth-child(1) td:nth-child(4) label .rpt-fields")).isSelected()) {
                softAssert.assertFalse(true, "Program name column checkbox is enabled");
            } else {
                softAssert.assertTrue(true, "Program name column checkbox is disabled");
                System.out.println("passed");
            }

            getDriver().findElement(By.id("close-popup-modal")).click();

            softAssert.assertAll();
            test.pass("Field access functionality verified successfully for feed programs tab");
            results.createNode("Field access functionality verified successfully for feed programs tab");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Field access functionality failed for feed programs tab");
            results.createNode("Field access functionality failed for feed programs tab");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Field access functionality failed for feed programs tab");
            results.createNode("Field access functionality failed for feed programs tab");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    @Test(enabled = false, priority = 45, dependsOnMethods = {"verifyFieldAccessButtonFunctionality"})
    public void FieldAccessTreatmentTab() throws InterruptedException, IOException {

        steps.createNode("Click on field access icon to verify column hide functionality for treatment programs tab");
        try {
            getDriver().findElement(By.xpath("//li[contains(text(),'Treatment')]")).click();
            waitElementInvisible(loading_cursor);
            SoftAssert softAssert = new SoftAssert();
            getDriver().findElement(By.cssSelector("#treatment-log #edit-field-access")).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(1500);

            if (getDriver().findElement(By.cssSelector("tr:nth-child(2) td:nth-child(4) input")).isSelected() == true) {
                getDriver().findElement(By.cssSelector("tr:nth-child(2) td:nth-child(4) label .rpt-fields")).click();
            }

            Thread.sleep(1000);
            getDriver().findElement(By.id("btn-save")).click();
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Field Access changes have been saved successfully");
            waitElementInvisible(loading_cursor);
            steps.createNode("Verify deselected column is hidden in the logview");
            softAssert.assertEquals(getDriver().findElements(By.cssSelector("#treatment-log #supplierName_sort")).size(), 0);

            getDriver().findElement(By.cssSelector("#treatment-log #edit-field-access")).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(1500);

            if (getDriver().findElement(By.cssSelector("tr:nth-child(2) td:nth-child(4) input")).isSelected() == false) {
                getDriver().findElement(By.cssSelector("tr:nth-child(2) td:nth-child(4) label .rpt-fields")).click();
            }

            Thread.sleep(1000);
            getDriver().findElement(By.id("btn-save")).click();
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Field Access changes have been saved successfully");
            waitElementInvisible(loading_cursor);

            steps.createNode("Verify checked column is displayed in the logview");
            softAssert.assertEquals(getDriver().findElements(By.cssSelector("#treatment-log #supplierName_sort")).size(), 1);

            steps.createNode("Verify that the user cannot uncheck the following columns from the field access column");
            getDriver().findElement(By.cssSelector("#treatment-log #edit-field-access")).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(1500);

            if (getDriver().findElement(By.cssSelector("tr:nth-child(1) td:nth-child(4) label .rpt-fields")).isSelected()) {
                softAssert.assertFalse(true, "treatment name column checkbox is enabled");
            } else {
                softAssert.assertTrue(true, "treatment column checkbox is disabled");
                System.out.println("passed");
            }

            getDriver().findElement(By.id("close-popup-modal")).click();

            softAssert.assertAll();
            test.pass("Field access functionality verified successfully for treatment programs tab");
            results.createNode("Field access functionality verified successfully for treatment programs tab");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Field access functionality failed for treatment programs tab");
            results.createNode("Field access functionality failed for treatment programs tab");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Field access functionality failed for treatment programs tab");
            results.createNode("Field access functionality failed for treatment programs tab");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(enabled = false, priority = 46, dependsOnMethods = {"verifyFieldAccessButtonFunctionality"})
    public void FieldAccessVaccineTab() throws InterruptedException, IOException {

        steps.createNode("Click on field access icon to verify column hide functionality for treatment programs tab");
        try {
            getDriver().findElement(By.xpath("//li[contains(text(),'Vaccine Programs')]")).click();
            waitElementInvisible(loading_cursor);
            SoftAssert softAssert = new SoftAssert();
            getDriver().findElement(By.cssSelector("#vaccine-log #edit-field-access")).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(1500);

            if (getDriver().findElement(By.cssSelector("tr:nth-child(2) td:nth-child(4) input")).isSelected() == true) {
                getDriver().findElement(By.cssSelector("tr:nth-child(2) td:nth-child(4) label .rpt-fields")).click();
            }

            Thread.sleep(1000);
            getDriver().findElement(By.id("btn-save")).click();
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Field Access changes have been saved successfully");
            waitElementInvisible(loading_cursor);
            steps.createNode("Verify deselected column is hidden in the logview");
            softAssert.assertEquals(getDriver().findElements(By.cssSelector("#vaccine-log #supplierName_sort")).size(), 0);

            getDriver().findElement(By.cssSelector("#vaccine-log #edit-field-access")).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(1500);

            if (getDriver().findElement(By.cssSelector("tr:nth-child(2) td:nth-child(4) input")).isSelected() == false) {
                getDriver().findElement(By.cssSelector("tr:nth-child(2) td:nth-child(4) label .rpt-fields")).click();
            }

            Thread.sleep(1000);
            getDriver().findElement(By.id("btn-save")).click();
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Field Access changes have been saved successfully");
            waitElementInvisible(loading_cursor);

            steps.createNode("Verify checked column is displayed in the logview");
            softAssert.assertEquals(getDriver().findElements(By.cssSelector("#vaccine-log #supplierName_sort")).size(), 1);

            steps.createNode("Verify that the user cannot uncheck the following columns from the field access column");
            getDriver().findElement(By.cssSelector("#vaccine-log #edit-field-access")).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(1500);

            if (getDriver().findElement(By.cssSelector("tr:nth-child(1) td:nth-child(4) label .rpt-fields")).isSelected()) {
                softAssert.assertFalse(true, "vaccine program name column checkbox is enabled");
            } else {
                softAssert.assertTrue(true, "vaccine program name column checkbox is disabled");
                System.out.println("passed");
            }

            getDriver().findElement(By.id("close-popup-modal")).click();

            softAssert.assertAll();
            test.pass("Field access functionality verified successfully for vaccine programs tab");
            results.createNode("Field access functionality verified successfully for vaccine programs tab");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Field access functionality failed for vaccine programs tab");
            results.createNode("Field access functionality failed for vaccine programs tab");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Field access functionality failed for vaccine programs tab");
            results.createNode("Field access functionality failed for vaccine programs tab");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(enabled = false, priority = 47, dependsOnMethods = {"verifyFieldAccessButtonFunctionality"})
    public void FieldAccessBioshuttleTab() throws InterruptedException, IOException {

        steps.createNode("Click on field access icon to verify column hide functionality for Bioshuttle programs tab");
        try {
            getDriver().findElement(By.xpath("//li[contains(text(),'Bioshuttle')]")).click();
            waitElementInvisible(loading_cursor);
            SoftAssert softAssert = new SoftAssert();
            getDriver().findElement(By.cssSelector("#vaccine-bio-log #edit-field-access")).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(1500);

            if (getDriver().findElement(By.cssSelector("tr:nth-child(2) td:nth-child(4) input")).isSelected() == true) {
                getDriver().findElement(By.cssSelector("tr:nth-child(2) td:nth-child(4) label .rpt-fields")).click();
            }

            Thread.sleep(1000);
            getDriver().findElement(By.id("btn-save")).click();
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Field Access changes have been saved successfully");
            waitElementInvisible(loading_cursor);
            steps.createNode("Verify deselected column is hidden in the logview");
            softAssert.assertEquals(getDriver().findElements(By.cssSelector("#vaccine-bio-log #programType_sort")).size(), 0);

            getDriver().findElement(By.cssSelector("#vaccine-bio-log #edit-field-access")).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(1500);

            if (getDriver().findElement(By.cssSelector("tr:nth-child(2) td:nth-child(4) input")).isSelected() == false) {
                getDriver().findElement(By.cssSelector("tr:nth-child(2) td:nth-child(4) label .rpt-fields")).click();
            }

            Thread.sleep(1000);
            getDriver().findElement(By.id("btn-save")).click();
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Field Access changes have been saved successfully");
            waitElementInvisible(loading_cursor);

            steps.createNode("Verify checked column is displayed in the logview");
            softAssert.assertEquals(getDriver().findElements(By.cssSelector("#vaccine-bio-log #programType_sort")).size(), 1);

            steps.createNode("Verify that the user cannot uncheck the following columns from the field access column");
            getDriver().findElement(By.cssSelector("#vaccine-bio-log #edit-field-access")).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(1500);

            if (getDriver().findElement(By.cssSelector("tr:nth-child(1) td:nth-child(4) label .rpt-fields")).isSelected()) {
                softAssert.assertFalse(true, "bioshuttle program name column checkbox is enabled");
            } else {
                softAssert.assertTrue(true, "bioshuttle program name column checkbox is disabled");
                System.out.println("passed");
            }

            getDriver().findElement(By.id("close-popup-modal")).click();

            softAssert.assertAll();
            test.pass("Field access functionality verified successfully for bioshuttle programs tab");
            results.createNode("Field access functionality verified successfully for bioshuttle programs tab");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Field access functionality failed for bioshuttle programs tab");
            results.createNode("Field access functionality failed for bioshuttle programs tab");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Field access functionality failed for bioshuttle programs tab");
            results.createNode("Field access functionality failed for bioshuttle programs tab");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(enabled = false, priority = 47, dependsOnMethods = {"verifyFieldAccessButtonFunctionality"})
    public void FieldAccessProgramUtilizationTab() throws InterruptedException, IOException {

        steps.createNode("Click on field access icon to verify column hide functionality for Program Utilization tab");
        try {
            getDriver().findElement(By.xpath("//li[contains(text(),'Program Utilization')]")).click();
            waitElementInvisible(loading_cursor);
            SoftAssert softAssert = new SoftAssert();
            getDriver().findElement(By.cssSelector("#program-util-log #edit-field-access")).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(1500);

            if (getDriver().findElement(By.cssSelector("tr:nth-child(2) td:nth-child(4) input")).isSelected() == true) {
                getDriver().findElement(By.cssSelector("tr:nth-child(2) td:nth-child(4) label .rpt-fields")).click();
            }

            Thread.sleep(1000);
            getDriver().findElement(By.id("btn-save")).click();
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Field Access changes have been saved successfully");
            waitElementInvisible(loading_cursor);
            steps.createNode("Verify deselected column is hidden in the logview");
            softAssert.assertEquals(getDriver().findElements(By.cssSelector("#program-util-log #sort-integratorFlockId-program")).size(), 0);

            getDriver().findElement(By.cssSelector("#program-util-log #edit-field-access")).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(1500);

            if (getDriver().findElement(By.cssSelector("tr:nth-child(2) td:nth-child(4) input")).isSelected() == false) {
                getDriver().findElement(By.cssSelector("tr:nth-child(2) td:nth-child(4) label .rpt-fields")).click();
            }

            Thread.sleep(1000);
            getDriver().findElement(By.id("btn-save")).click();
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Field Access changes have been saved successfully");
            waitElementInvisible(loading_cursor);

            steps.createNode("Verify checked column is displayed in the logview");
            softAssert.assertEquals(getDriver().findElements(By.cssSelector("#program-util-log #sort-integratorFlockId-program")).size(), 1);

            steps.createNode("Verify that the user cannot uncheck the following columns from the field access column");
            getDriver().findElement(By.cssSelector("#program-util-log #edit-field-access")).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(1500);

            if (getDriver().findElement(By.cssSelector("tr:nth-child(1) td:nth-child(4) label .rpt-fields")).isSelected()) {
                softAssert.assertFalse(true, "Flock ID column checkbox is enabled");
            } else {
                softAssert.assertTrue(true, "Flock ID column checkbox is disabled");
                System.out.println("passed");
            }

            getDriver().findElement(By.id("close-popup-modal")).click();

            softAssert.assertAll();
            test.pass("Field access functionality verified successfully for Program Utilization tab");
            results.createNode("Field access functionality verified successfully for Program Utilization tab");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Field access functionality failed for Program Utilization tab");
            results.createNode("Field access functionality failed for Program Utilization tab");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Field access functionality failed for Program Utilization tab");
            results.createNode("Field access functionality failed for Program Utilization tab");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    @Test(priority = 50, enabled = false, description = "Edit Treatment functionality", dependsOnMethods = {"VerifyCreateTreatmentFunctionality"})
    public void VerifyEditTreatmentFunctionality() throws InterruptedException, IOException {
        try {
            test = extent.createTest("Verify Edit Treatment functionality on Program Management Screen ");
            steps = test.createNode(Scenario.class, Steps);
            results = test.createNode(Scenario.class, Results);
            getDriver().get(url_programManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            getScreenshot();
            SoftAssert softAssert = new SoftAssert();
            getDriver().findElement(By.xpath("//li[contains(text(),'Treatment')]")).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(500);
            if (getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programTreatmentProgramNameCol)).getText().equals(ProgramManagementModel.TreatmentProgramName)) {
                scroll(By.xpath("//*[@id='" + programTreatmentTable + "'] //*[text()='Action']"));
                waitElementClickable(By.id(programTreatmentEdit + "" + "1" + "-" + programTreatment_ID));
                Thread.sleep(1000);
                getDriver().findElement(By.id(programTreatmentEdit + "" + "1" + "-" + programTreatment_ID)).click();
            } else {
                Assert.assertTrue(false);
            }
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            //Program Description
            getDriver().findElement(treatmentDescriptionField).sendKeys(" Updated");
            Thread.sleep(500);
            getScreenshot();
            click(popupSaveButton);

            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Treatment details updated");
            softAssert.assertEquals(getDriver().findElement(By.cssSelector("#col-2-treatment label")).getText(), "Treatment Program");
            softAssert.assertAll();
            test.pass("Edit Treatment functionality passed successfully on Program Management screen");
            results.createNode("Edit Treatment functionality tested successfully on Program Management screen");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Edit Treatment functionality failed on Program Management screen");
            results.createNode("Edit Treatment functionality failed on Program Management screen");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Edit Treatment functionality failed.");
            results.createNode("Edit Treatment functionality failed.");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(priority = 51, enabled = false, description = "Delete Treatment functionality", dependsOnMethods = {"VerifyCreateTreatmentFunctionality"})
    public void VerifyDeleteTreatmentFunctionality() throws InterruptedException, IOException {
        try {
            test = extent.createTest("Verify Delete Treatment functionality on Program Management Screen ");
            steps = test.createNode(Scenario.class, Steps);
            results = test.createNode(Scenario.class, Results);
            steps.createNode("Delete the created treatment");

            getDriver().get(url_programManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            getScreenshot();

            SoftAssert softAssert = new SoftAssert();
            getDriver().findElement(By.xpath("//li[contains(text(),'Treatment')]")).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(500);
            if (getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programTreatmentProgramNameCol)).getText().equals(ProgramManagementModel.TreatmentProgramName)) {
                scroll(By.xpath("//*[@id='" + programTreatmentTable + "'] //*[text()='Action']"));
                waitElementClickable(By.id(programTreatmentDelete + "" + "1" + "-" + programTreatment_ID));
                Thread.sleep(1000);
                getDriver().findElement(By.id(programTreatmentDelete + "" + "1" + "-" + programTreatment_ID)).click();
            } else {
                softAssert.assertTrue(false);
            }

            waitElementVisible(popupYesButton);
            click(popupYesButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Data details deleted.");
            softAssert.assertAll();

            test.pass("Delete Treatment functionality passed successfully on Program Management screen");
            results.createNode("Delete Treatment functionality tested successfully on Program Management screen");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Delete Treatment functionality failed on Program Management screen");
            results.createNode("Delete Treatment functionality failed on Program Management screen");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Delete Treatment functionality failed.");
            results.createNode("Delete Treatment functionality failed.");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(priority = 52, enabled = false, description = "Inline edit Treatment functionality")
    public void InlineEditTreatmentFunctionality() throws InterruptedException, IOException {
        try {
            test = extent.createTest("Verify Inline Edit Treatment functionality on Program Management Screen ");
            steps = test.createNode(Scenario.class, Steps);
            results = test.createNode(Scenario.class, Results);

            getDriver().get(url_programManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            getScreenshot();
            SoftAssert softAssert = new SoftAssert();

            click(treatmentTab);
            waitElementInvisible(loading_cursor);
            Thread.sleep(500);

            waitElementClickable(treatmentTabInlineEditIcon);

            click(treatmentTabInlineEditIcon);

            waitElementInvisible(loading_cursor);
            Thread.sleep(500);
            clear(treatmentDescriptionColumn);
            type(treatmentDescriptionColumn, "Test inline edit by automation");

            //Click on inline button to save changes
            click(By.cssSelector("#" + treatmentTable + " #" + treatmentInlineButtonSave));
            waitElementVisible(popupYesButton);
            click(popupYesButton);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getText(alertMessage), "Data saved successfully.");
            getDriver().navigate().refresh();
            waitElementInvisible(loading_cursor);

            softAssert.assertAll();

            test.pass("Inline Edit Treatment functionality passed successfully on Program Management screen");
            results.createNode("Inline Edit Treatment functionality tested successfully for feed programs on Program Management screen");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Inline Edit Treatment functionality failed for feed programs on Program Management screen");
            results.createNode("Inline Edit Treatment functionality failed for feed programs on Program Management screen");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Inline Edit Treatment functionality failed.");
            results.createNode("Inline Edit Treatment functionality failed.");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(enabled = true, priority = 53, description = "TC: IE- 3687, Verify added Ingredient can be deleted on clicking delete icon.")
    public void VerifyAddIngredientFunctionality() throws InterruptedException, IOException {

        getDriver().get(Constants.url_programManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        try {
            steps = test.createNode(Scenario.class, Steps);
            results = test.createNode(Scenario.class, Results);
            steps.createNode("1. Click on Register New Program button");
            steps.createNode("2. Click on Add ingredient icon, and add new ingredient");
            steps.createNode("3. Delete the ingredient and verify it's deleted");

            SoftAssert softAssert = new SoftAssert();

            getDriver().findElement(By.xpath("//*[text()= 'Vaccine Programs ']")).click();
            waitElementInvisible(loading_cursor);
            getDriver().findElement(By.xpath("//*[text()=' Register New Program']")).click();
            waitElementInvisible(loading_cursor);

            getDriver().findElement(programProgramType).sendKeys("Feed");
            Thread.sleep(500);
            getDriver().findElement(programProgramType).sendKeys(Keys.ENTER);

            WebElement ingredientCategory = getDriver().findElement(programFlockDayStart);
            getDriver().findElement(with(By.tagName("input")).below(ingredientCategory)).click();
            List<WebElement> ingredientCategories = getDriver().findElements(By.cssSelector(".ng-option-label"));
            ingredientCategories.get(0).click();
            if (Constants.config.url().contains("qa") || (Constants.config.url().contains("dev"))) {
                getDriver().findElement(programIngredient).sendKeys(ProgramManagementModel.ingredientNameQA);
                Thread.sleep(500);

                getDriver().findElement(By.xpath("//b[contains(text(),'Ingredient')]")).click();

            }
            if (Constants.config.url().contains("uat")) {
                getDriver().findElement(programIngredient).sendKeys(ProgramManagementModel.ingredientName);
                Thread.sleep(500);

                getDriver().findElement(By.xpath("//b[contains(text(),'Narasin')]")).click();

            }
            Thread.sleep(1000);
            click(addedIngredient);
            WebElement second = getDriver().findElement(with(By.tagName("input")).below(ingredientCategory));
            getDriver().findElement(with(By.tagName("input")).below(second)).click();
            List<WebElement> ingredientCategories2 = getDriver().findElements(By.cssSelector(".ng-option-label"));
            ingredientCategories2.get(0).click();

            if (Constants.config.url().contains("qa") || (Constants.config.url().contains("dev"))) {
                getDriver().findElement(programIngredient2).sendKeys(ProgramManagementModel.ingredientNameQA);
                Thread.sleep(500);

                WebElement checkbox = getDriver().findElement(By.xpath("//b[contains(text(),'Ingredient')]"));
                JavascriptExecutor executor2 = (JavascriptExecutor) getDriver();
                executor2.executeScript("arguments[0].click();", checkbox);

            }
            if (Constants.config.url().contains("uat")) {
                getDriver().findElement(programIngredient2).sendKeys(ProgramManagementModel.ingredientName);
                Thread.sleep(500);
                WebElement checkbox = getDriver().findElement(By.xpath("//b[contains(text(),'Narasin')]"));
                JavascriptExecutor executor2 = (JavascriptExecutor) getDriver();
                executor2.executeScript("arguments[0].click();", checkbox);
            }
            Thread.sleep(1000);
            click(deleteIngredientIcon);
            Thread.sleep(2000);
            softAssert.assertEquals(size(programIngredient2), 0, "Verify added ingredient is deleted");

            softAssert.assertAll();
            test.pass("Added ingredient functionality passed successfully");
            results.createNode("Added ingredient functionality passed successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("failed to verify");
            results.createNode("failed to verify");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("failed to verify added ingredient functionality");
            results.createNode("failed to verify added ingredient functionality");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(enabled = false, priority = 54, description = "TC: IE- 3692, Verify that Reset button is clickable and working on create program form.")
    public void ProgramManagementResetButtonFunctionality() throws InterruptedException, IOException {

        getDriver().get(url_programManagement);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);

        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        steps.createNode("1. Click on Register New Program button");
        steps.createNode("2. Enter all mandatory fields");
        steps.createNode("3. Click on reset and verify it");

        getDriver().findElement(By.xpath("//*[text()= 'Vaccine Programs ']")).click();
        waitElementInvisible(loading_cursor);
        getDriver().findElement(By.xpath("//*[text()=' Register New Program']")).click();
        waitElementInvisible(loading_cursor);
        try {
            SoftAssert softAssert = new SoftAssert();

            getDriver().findElement(programTargetPathogen).click();
            Thread.sleep(500);
            softAssert.assertEquals(getDriver().findElement(By.cssSelector(".ng-option-label")).getText(), "Coccidia");
            getDriver().findElement(programTargetPathogen).sendKeys(Keys.ENTER);
            Thread.sleep(500);

            getDriver().findElement(programProgramType).sendKeys("Vaccine");
            Thread.sleep(500);
            getDriver().findElement(programProgramType).sendKeys(Keys.ENTER);

            String NoApplicationFlock = "2";
            getDriver().findElement(programNoApplicationFlock).sendKeys(NoApplicationFlock);
            Thread.sleep(500);

            for (int i = 1; i <= Integer.parseInt(NoApplicationFlock); i++) {
                getDriver().findElement(By.id(programDaysApplicationFlock + "-" + i)).sendKeys("" + i);
            }

            click(programComplexList);
            if (config.url().contains("qa") || config.url().contains("dev")) {
                getDriver().findElement(programComplexSearch).sendKeys(ComplexNameQA);
            }
            if (config.url().contains("uat")) {
                getDriver().findElement(programComplexSearch).sendKeys(ComplexNameUAT);

            }

            Thread.sleep(1500);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            click(clickSearchItemFromHierarchy);

            click(programStartDateIcon);
            waitElementInvisible(loading_cursor);
            Thread.sleep(500);
            Methods method = new Methods();
            WebElement dateWidgetTo = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#startDate .dp-popup"))).get(0);
            List<WebElement> columns1 = dateWidgetTo.findElements(By.tagName("button"));
            clickGivenDay(columns1, getFirstDay());
            Thread.sleep(500);

            getDriver().findElement(By.cssSelector("#endDate img")).click();
            waitElementInvisible(loading_cursor);
            Thread.sleep(500);
            WebElement dateWidgetToEnd = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#endDate .dp-popup"))).get(0);
            List<WebElement> columns2 = dateWidgetToEnd.findElements(By.tagName("button"));
            clickGivenDay(columns2, getDay("30"));
            Thread.sleep(700);

            getDriver().findElement(programDescription).sendKeys(DescriptionName);
            getScreenshot();
            //click on reset button
            getDriver().findElement(By.id(("btn-reset"))).click();
            waitElementInvisible(loading_cursor);

            //Verify save button is disabled
            softAssert.assertEquals(getDriver().findElements(By.cssSelector("#btn-save.disabled-v2")).size(), 2, "Mandatory check failed");

            steps.createNode("Verify all fields on reset button click are cleared");
            WebElement programTypeDropdown = getDriver().findElement(By.cssSelector("#programTypeId input"));
            softAssert.assertEquals(programTypeDropdown.getAttribute("value"), "", "Program type field is reset");
            WebElement programDescription = getDriver().findElement(By.id("descriptionId"));
            softAssert.assertEquals(programDescription.getAttribute("value"), "", "Program description field is reset");
            WebElement programStartDateIcon = getDriver().findElement(By.cssSelector("#startDate img"));
            softAssert.assertEquals(programStartDateIcon.getAttribute("value"), null, "Start Date field is reset");
            WebElement programEndDateIcon = getDriver().findElement(By.cssSelector("#endDate img"));
            softAssert.assertEquals(programEndDateIcon.getAttribute("value"), null, "End date field is reset");
            WebElement programComplexList = getDriver().findElement(By.cssSelector("#compleSiteId .toggle-list"));
            softAssert.assertEquals(programComplexList.getAttribute("value"), null, "Complex field is reset");
            WebElement programTargetPathogen = getDriver().findElement(By.cssSelector("#targetPathogenId input"));
            softAssert.assertEquals(programTargetPathogen.getAttribute("value"), "", "target pathogen field is reset");

            softAssert.assertAll();
            test.pass("reset button functionality passed successfully");
            results.createNode("reset button functionality passed successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);

        } catch (AssertionError er) {
            test.fail("failed to verify");
            results.createNode("failed to verify");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("failed to verify added reset button functionality");
            results.createNode("failed to verify reset button functionality");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    @AfterTest
    public static void endreport() {
        extent.flush();
    }

}
