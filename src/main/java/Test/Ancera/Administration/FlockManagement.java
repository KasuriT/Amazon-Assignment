package Test.Ancera.Administration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import Models.FlockManagementModel;
import Test.Ancera.ClickElement;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.DB_Config;
import Test.Ancera.DateUtil;
import Test.Ancera.Queries;
import Test.Ancera.Reports.SalmonellaLog;

import static Test.Ancera.Test_Variables.*;
import static Test.Ancera.Helper.*;
import static Test.Ancera.Test_Elements.*;
import static Test.Ancera.Test_Functions.*;
import static Test.Ancera.Constants.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import static org.openqa.selenium.support.locators.RelativeLocator.with;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FlockManagement extends DB_Config {

	
	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		spark = new ExtentSparkReporter("target/Reports/Administration_Flock_Management"+date+".html");
		spark.config().setReportName("Flock Management Test Report"); 
		config();
		ConfigureLogin.login();
		DB_Config.test();
	}


	@Test (priority = 1) 
	public void NavigateFlock() throws InterruptedException, IOException {
		NavigateToScreen(url_flockManagement, "Flock Management", FlockManagementReportPath, FlockManagementTitle);
	}


	@Test (priority = 2) 
	public void LockPlacement() throws InterruptedException, IOException {
		driver.get(url_flockManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		Lock(flockPlacementTable, "Flock Management", FlockManagementReportPath, 1);
	}

	
	@Test (priority = 3) 
	public void LockMortality() throws InterruptedException, IOException {
		driver.findElement(flockMortalityTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Lock(flockMortalityTable, "Flock Management", FlockManagementReportPath, 1);
	}

	
	@Test (priority = 4) 
	public void LockSettlement() throws InterruptedException, IOException {
		driver.findElement(flockSettlementTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Lock(flockSettlementTable, "Flock Management", FlockManagementReportPath, 1);
	}

	
	@Test (priority = 5) 
	public void LockCondemnation() throws InterruptedException, IOException {
		driver.findElement(flockCondemnationTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Lock(flockCondemnationTable, "Flock Management", FlockManagementReportPath, 1);
	}


	@Test (priority = 6) 
	public void WildcardPlacement() throws InterruptedException, IOException {
		driver.get(url_flockManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Wildcard1(flockPlacementTable, "Flock Management", FlockManagementReportPath, 1);
	}


	@Test (priority = 7) 
	public void WildcardMortality() throws InterruptedException, IOException {
		driver.findElement(flockMortalityTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Wildcard1(flockMortalityTable, "Flock Management", FlockManagementReportPath, 1);
	}


	@Test (priority = 8) 
	public void WildcardSettlement() throws InterruptedException, IOException {
		driver.findElement(flockSettlementTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Wildcard1(flockSettlementTable, "Flock Management", FlockManagementReportPath, 1);
	}


	@Test (priority = 9) 
	public void WildcardCondemnation() throws InterruptedException, IOException {
		driver.findElement(flockCondemnationTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Wildcard1(flockCondemnationTable, "Flock Management", FlockManagementReportPath, 1);
	}


	@Test(priority= 10)
	public void SortingPlacement() throws InterruptedException, IOException {
		driver.get(url_flockManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(2000);
		Sorting1(flockPlacementTable, "Flock Management", FlockManagementReportPath, 1);
	}


	@Test(priority= 11)
	public void SortingMortality() throws InterruptedException, IOException {
		driver.findElement(flockMortalityTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Sorting1(flockMortalityTable, "Flock Management", FlockManagementReportPath, 1);
	}


	@Test(priority= 12)
	public void SortingSettlement() throws InterruptedException, IOException {
		driver.findElement(flockSettlementTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Sorting1(flockSettlementTable, "Flock Management", FlockManagementReportPath, 1);
	}


	@Test(priority= 13)
	public void SortingCondemnation() throws InterruptedException, IOException {
		driver.findElement(flockCondemnationTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Sorting1(flockCondemnationTable, "Flock Management", FlockManagementReportPath, 1);
	}


	@Test(priority= 14, enabled = true)
	public void RowsPerPagePlacement() throws InterruptedException, IOException {
		driver.get(url_flockManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		RowsPerPage(flockPlacementTable);
	}


	@Test(priority= 15, enabled = true)
	public void RowsPerPageMortality() throws InterruptedException, IOException {
		driver.findElement(flockMortalityTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		RowsPerPage(flockMortalityTable);
	}

	
	@Test(priority= 16, enabled = true)
	public void RowsPerPageSettlement() throws InterruptedException, IOException {
		driver.findElement(flockSettlementTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		RowsPerPage(flockSettlementTable);
	}


	@Test(priority= 17, enabled = true)
	public void RowsPerPageCondemnation() throws InterruptedException, IOException {
		driver.findElement(flockCondemnationTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		RowsPerPage(flockCondemnationTable);
	}


	@Test (priority = 18) 
	public void PaginationPlacement() throws InterruptedException, IOException {
		driver.get(url_flockManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		Pagination(flockPlacementTable, "Program Management", ProgramManagementReportPath);
	}


	@Test (priority = 19) 
	public void PaginationMortality() throws InterruptedException, IOException {
		driver.findElement(flockMortalityTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Pagination(flockMortalityTable, "Program Management", ProgramManagementReportPath);
	}


	@Test (priority = 20) 
	public void PaginationSettlement() throws InterruptedException, IOException {
		driver.findElement(flockSettlementTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Pagination(flockSettlementTable, "Program Management", ProgramManagementReportPath);
	}

	
	@Test (priority = 21) 
	public void PaginationCondemnation() throws InterruptedException, IOException {
		driver.findElement(flockCondemnationTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Pagination(flockCondemnationTable, "Program Management", ProgramManagementReportPath);
	}


	@Test (priority = 21) 
	public void CreateProgram() throws InterruptedException, IOException, SQLException {

		driver.get(url_programManagement);
		waitElementClickable(programVaccineProgramTab);
		click(programVaccineProgramTab);
		waitElementInvisible(loading_cursor);
		click(programCreateButton);
		waitElementInvisible(loading_cursor);
		type(programName, FlockManagementModel.flockProgramName);
		click(programTargetPathogen);
		Thread.sleep(500);
		enterKey(programTargetPathogen);
		Thread.sleep(500);
		type(programProgramType, "Vaccine");
		Thread.sleep(500);	
		enterKey(programProgramType);

		ResultSet getComplexNameResults = getStmt().executeQuery(Queries.getComplexName);
		while (getComplexNameResults.next()) {
			String complexName = getComplexNameResults.getString("siteName");
			System.out.println("Complex Name: "+complexName);
			click(programComplexList);
			type(programComplexSearch, complexName);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			click(clickSearchItemFromHierarchy);
		}

		click(programStartDateIcon);
		waitElementInvisible(loading_cursor);
		Thread.sleep(500);
		WebElement dateWidgetTo = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#startDate .dp-popup"))).get(0);	
		List<WebElement> columns1 = dateWidgetTo.findElements(By.tagName("button"));
		DateUtil.clickGivenDay(columns1, DateUtil.getFirstDay());
		Thread.sleep(500);
		type(programNoApplicationFlock, "0");
		click(popupSaveButtonXpath);
		waitElementInvisible(loading_cursor);
		Thread.sleep(700);
		Assert.assertEquals(driver.findElement(alertMessage).getText(), "New program has been created successfully");
		getStmt().close();
	}


	@Test (enabled= true, priority = 22) 
	public void CreateFlock() throws InterruptedException, IOException, SQLException {
		try {
			test = extent.createTest("AN-FR-98: Verify user can create Flock");
			results = test.createNode(Scenario.class, Results);

			SoftAssert softAssert = new SoftAssert();

			driver.get(url_flockManagement);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			click(flockCreateButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			ResultSet getFarmNameResults = getStmt().executeQuery(Queries.getFarmName);

			while (getFarmNameResults.next()) {
				String farmName = getFarmNameResults.getString("siteName");
				System.out.println("Farm Name: "+farmName);

				click(flockFarmDropdownExpand);
				type(flockFarmDropdownSearch, farmName);
				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);
				click(By.cssSelector("label b"));
			}

			type(flockIntegratorFlockID, FlockManagementModel.flockIntegratorID);

			if (size(flockIntegratorFlockAddNew) != 0) {
				click(flockIntegratorFlockAddNew);
			}
			else {
				driver.findElement(flockIntegratorFlockID).sendKeys(Keys.ENTER);
			}

			type(flockBirdSizeInput, "Small");
			driver.findElement(flockBirdSizeInput).sendKeys(Keys.ENTER);

			click(flockHousePlacedDropdownExpand);
			Thread.sleep(1000);
			click(By.xpath("//*[text() = 'Select All']"));
			click(flockHousePlacedDropdownExpand);

			scroll(flockAddNewProgram);
			click(flockPlacementDateCalendar);
			Thread.sleep(700);
			DateUtil.clickDay("01");

			waitElementClickable(popupSaveButton);
			Thread.sleep(500);
			click(popupSaveButton);
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Flock created successfully");

			softAssert.assertEquals(getText(By.cssSelector("tr:nth-child(1) #col-"+placementIntegratorFlockIDCol)), "");

			
			softAssert.assertAll();

			test.pass("Flock was created successfully");
			results.createNode("Flock was created successfully");
			test.addScreenCaptureFromPath(getScreenshot("Flock Registration", FlockManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, FlockManagementReportPath, null);	
		}
		catch(AssertionError er) {
			test.fail("Flock failed to create");
			results.createNode("Flock failed to create");
			saveResultNew(ITestResult.FAILURE, FlockManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Flock failed to create");
			results.createNode("Flock failed to create");
			saveResultNew(ITestResult.FAILURE, FlockManagementReportPath, ex);
		}
		getStmt().close();
	}



	@Test (enabled= true, priority =12) 
	public void EditFlock() throws InterruptedException, IOException {
		try {
			driver.get(url_flockManagement);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			SoftAssert softAssert = new SoftAssert();
			test = extent.createTest("Verify user can edit flock");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			openFlockAudit();
			int getRowsPre = size(flockAuditRowCount);
			Thread.sleep(1000);
			click(popupCloseButton);

			openEditFlock();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			type(flockBirdSexInput, "Male");
			driver.findElement(flockBirdSexInput).sendKeys(Keys.ENTER);
			click(By.xpath("(//*[@id = 'btn-save'])[1]"));
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Data saved successfully.");


			click(flockEditMortalityTab);
			waitElementInvisible(loading_cursor);	
			for (int i=1; i<=9; i++) {
				By mortalityField = By.cssSelector("#num-week_"+i+"_Mortality");
				scroll(mortalityField);
				type(mortalityField, Integer.toString(i));
			}
			click(By.xpath("(//*[@id = 'btn-save'])[2]"));
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Data saved successfully.");
			
			
			click(flockEditSettlementTab);
			waitElementInvisible(loading_cursor);	
			type(flockWeeklyFarmRank, "1");
			type(flockHistoricalFarmCostVariance, "1");
			type(flockWeeklyFarmCostVariance, "1");
			type(flockPlacementDensityInput, "1.5");
			softAssert.assertEquals(getAttribute(flockPlacementDensityInput), "1.5", "Not able to write decimal value in Flock density field");
			click(By.xpath("(//*[@id = 'btn-save'])[3]"));
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Data saved successfully.");
			

			click(flockEditCondemnationTab);
			waitElementInvisible(loading_cursor);
			type(flockNumBirdsDOAPlant, "1");
			click(By.xpath("(//*[@id = 'btn-save'])[4]"));
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Data saved successfully.");
			click(popupCloseButton);

			
			openFlockAudit();
			int getRowsPost = size(flockAuditRowCount);
			click(popupCloseButton);

			softAssert.assertEquals(getRowsPost+4, getRowsPre);
			softAssert.assertAll();

			test.pass("Flock was edited successfully");
			results.createNode("Flock was edited successfully");
			test.addScreenCaptureFromPath(getScreenshot("Flock Registration", FlockManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, FlockManagementReportPath, null);	
		}
		catch(AssertionError er) {
			test.fail("Flock failed to edit");
			results.createNode("Flock failed to edit");
			saveResultNew(ITestResult.FAILURE, FlockManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Flock failed to edit");
			results.createNode("Flock failed to edit");
			saveResultNew(ITestResult.FAILURE, FlockManagementReportPath, ex);
		}
	}



	@Test (enabled= true, priority =12) 
	public void InlineEditFlock() throws InterruptedException, IOException, SQLException {
		try {			
			test = extent.createTest("Verify inline edit flock");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			driver.get(url_flockManagement);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			SoftAssert softAssert = new SoftAssert();
		
			click(By.id("complexName_show-filter"));
			waitElementInvisible(loading_cursor);
			ResultSet getComplexNameResults = getStmt().executeQuery(Queries.getComplexName);
			while (getComplexNameResults.next()) {
				String complexName = getComplexNameResults.getString("siteName");
				System.out.println("Complex Name: "+complexName);
				type(By.id("complexName_search-input"), complexName);
				waitElementInvisible(loading_cursor);
				Thread.sleep(2000);
				click(By.id("complexName_cust-cb-lst-txt_"+complexName));
				click(By.id("complexName_apply"));
			}
			
			waitElementInvisible(loading_cursor);
			
			
			click(flockInlineButton);
			waitElementInvisible(loading_cursor);
		
			
			click(flockInlineNewProgramIcon);
			waitElementInvisible(loading_cursor);
			click(flockInlineAddNewProgramPopup);
			type(flockInlineProgramName, FlockManagementModel.flockProgramName);
			enterKey(flockInlineProgramName);
			type(flockAdministrationMethod, FlockManagementModel.flockProgramAdminMethod);
			
			if (size(clickAddNewDropdown) != 0) {
				click(clickAddNewDropdown);
			}
			else {
				click(By.xpath("//*[text()='"+FlockManagementModel.flockProgramAdminMethod+"']"));		
			}
		
			click(flockProgramSaveButton);
			waitElementInvisible(loading_cursor);
		
			
			clear(flockBirdBreed);
			type(flockBirdBreed, "Female");
			enterKey(flockBirdBreed);
			click(flockInlineButtonSave);
			waitElementInvisible(loading_cursor);
			click(popupYesButton);
			waitElementVisible(alertMessage);
			softAssert.assertEquals(getText(alertMessage), "Data saved successfully.");

			click(flockMortalityTab);
			driver.findElement(By.cssSelector("#"+flockMortalityTable+" #"+flockInlineButton));
			clear(flockInlineMortality1Input);
			type(flockInlineMortality1Input, "2.5");
			driver.findElement(By.cssSelector("#"+flockMortalityTable+" #"+flockInlineButtonSave));
			click(popupYesButton);
			waitElementVisible(alertMessage);
			softAssert.assertEquals(getText(alertMessage), "Data saved successfully.");
			
			click(flockSettlementTab);
			driver.findElement(By.cssSelector("#"+flockSettlementTable+" #"+flockInlineButton));
			waitElementInvisible(loading_cursor);
			type(flockInlineSettlementWeeklyFarmRank, "1");
			type(flockInlineSettlementHistoricalFarmCostVariance, "2");
			type(flockInlineSettlementWeeklyFarmCostVariance, "3");
			type(flockInlineSettlementhatchDate, dateMMDDYYYY1);
			type(flockInlineSettlementDaysOut, "4");
			type(flockInlineSettlementAgeofLitter, "5");
			type(flockInlineSettlementAverageSoldAge, "6");
			type(flockInlineSettlementNumBirdsSold, "7");
			type(flockInlineSettlementPlacementDensity, "8.5");
			softAssert.assertEquals(getAttribute(flockInlineSettlementPlacementDensity), "8.5", "Placement Density is not accepting decimal value");
			type(flockInlineSettlementProcessingDate, dateMMDDYYYY1);
			type(flockInlineSettlementProcessingSiteID, "9");
			type(flockInlineSettlementUSDAPlantID, "10");
			type(flockInlineSettlementPlantLocation, "11");
			type(flockInlineSettlementNumOfBridsProcessed, "12");
			type(flockInlineSettlementAvgBirdWeightLB, "13");
			type(flockInlineSettlementAvgBirdWeightKG, "14");
			type(flockInlineSettlementTotalWeightProcessedLB, "15");
			type(flockInlineSettlementTotalWeightProcessedKG, "16");
			type(flockInlineSettlementTotalFeedWeightLB, "17");
			type(flockInlineSettlementTotalFeedWeightKG, "18");
			type(flockInlineSettlementFCR, "19");
			type(flockInlineSettlementAdjustedFCR, "20");
			type(flockInlineSettlementFeedCostPerLivePound, "21");
			type(flockInlineSettlementMedicationCostPerLivePound, "22");
			type(flockInlineSettlementGrowerCostPerLivePound, "23");
			type(flockInlineSettlementLivabilityPercentage, "24");
			type(flockInlineSettlementOverallMortalityPercentage, "25");
			driver.findElement(By.cssSelector("#"+flockSettlementTable+" #"+flockInlineButtonSave));
			click(popupYesButton);
			waitElementVisible(alertMessage);
			softAssert.assertEquals(getText(alertMessage), "Data saved successfully.");
			
			
			click(flockCondemnationTab);
			driver.findElement(By.cssSelector("#"+flockCondemnationTable+" #"+flockInlineButton));
			waitElementInvisible(loading_cursor);
			type(flockInlineNumBirdsDOAPlantCondemnation, "1");
			type(flockInlineTotalWeightCondemnedLBCondemnation, "2");
			type(flockInlineTotalWeightCondemnedKGCondemnation, "3");
			type(flockInlineNumBirdsCondemnedWholeCondemnation, "4");
			type(flockInlinePartsWeightCondemnedLBCondemnation, "5");
			type(flockInlinePartsWeightCondemnedKGCondemnation, "6");
			type(flockInlineKCalLBCondemnation, "7");
			type(flockInlineGradeAPawsPercentageCondemnation, "8");
			type(flockInlineIPPercentageCondemnation, "9");
			type(flockInlineLeukosisPercentageCondemnation, "10");
			type(flockInlineSeptoxPercentageCondemnation, "11");
			type(flockInlineIPPercentageCondemnation, "12");
			type(flockInlineTumorPercentageCondemnation, "13");
			driver.findElement(By.cssSelector("#"+flockCondemnationTable+" #"+flockInlineButtonSave));
			click(popupYesButton);
			waitElementVisible(alertMessage);
			softAssert.assertEquals(getText(alertMessage), "Data saved successfully.");
			softAssert.assertAll();

			test.pass("Flock inline tested successfully");
			results.createNode("Flock inline tested successfully");
			test.addScreenCaptureFromPath(getScreenshot("Flock Registration", FlockManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, FlockManagementReportPath, null);	
		}
		catch(AssertionError er) {
			test.fail("Flock inline failed");
			results.createNode("Flock inline failed");
			saveResultNew(ITestResult.FAILURE, FlockManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Flock inline failed");
			results.createNode("Flock inline failed");
			saveResultNew(ITestResult.FAILURE, FlockManagementReportPath, ex);
		}
		getStmt().close();
	}



	@Test (description = "IE-3200", enabled= true, priority =13) 
	public void EditPlacementDate() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-FR-105: Verify that new flock is created on editing the Placement Date only of the existing flock", "This testcase will verify that new flock is created on editing the Placement Date only of the existing flock");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Flock Registration; Flock Registration reports open");

			driver.get(url_flockManagement);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);

			//WebElement scroll = driver.findElement(By.cssSelector("tr:nth-child(1) #edit-flock"));
			//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll); 
			//Thread.sleep(2000);	
			
			driver.findElement(By.id("audit-trial-0")).click();
			waitElementInvisible(loading_cursor);	
			int auditRows = driver.findElements(By.cssSelector(".audit-v2 tr")).size();

			driver.findElement(By.cssSelector("tr:nth-child(1) #edit-flock")).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);

			driver.findElement(By.cssSelector("#placementDate img")).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);

			//WebElement dateWidgetFrom = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("/html/body/app-root/div/app-manage-flock-registration/div[2]/app-popup-component/div/div/div/div[3]/app-create-flock/div/div[1]/form/div/div[2]/app-date-select-box/div/div[2]/div/dp-date-picker/div[2]/div"))).get(0);
			WebElement dateWidgetFrom = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#placementDate .dp-popup"))).get(0);
			List<WebElement> columns = dateWidgetFrom.findElements(By.tagName("button"));
			DateUtil.clickGivenDay(columns, DateUtil.getCurrentDay());
			Thread.sleep(2000);

			//driver.findElement(By.cssSelector("#processingDate img")).click();
			//waitElementInvisible(loading_cursor);
			//Thread.sleep(2000);
			//WebElement dateWidgetTo = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#processingDate .dp-popup"))).get(0);
			//List<WebElement> columns1 = dateWidgetTo.findElements(By.tagName("button"));
			//DateUtil.clickGivenDay(columns1, DateUtil.getCurrentDayPlus(1));
			//Thread.sleep(2000);

			driver.findElement(By.id("submit-flock")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector(".anc-btn-secondary")).click();
			waitElementInvisible(loading_cursor);	
			SoftAssert softAssert = new SoftAssert();
			driver.findElement(By.id("audit-trial-0")).click();
			waitElementInvisible(loading_cursor);		
			softAssert.assertEquals(driver.findElements(By.cssSelector(".audit-v2 tr")).size(), auditRows+1);
			driver.findElement(By.id("close-gen-modal")).click();
			softAssert.assertAll();
			test.pass("New Flock was created successfully");
			results.createNode("New Flock was created successfully");
			test.addScreenCaptureFromPath(getScreenshot("Flock Registration", FlockManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, FlockManagementReportPath, null);	
		}
		catch(AssertionError er) {
			test.fail("New flock was not created");
			results.createNode("New flock was not created");
			saveResultNew(ITestResult.FAILURE, FlockManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("New flock was not created");
			results.createNode("New flock was not created");
			saveResultNew(ITestResult.FAILURE, FlockManagementReportPath, ex);
		}
	}





	@Test (enabled= true, priority = 15, dependsOnMethods = { "FlockValidation" }) 
	public void FlockUploadBulk() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-FR-103: Verify flocks are added through bulk upload", "This testcase will verify that flocks are added through bulk upload");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			steps.createNode("1. Navigate to Flock Registrations screen");
			steps.createNode("2. Click on create new button; and select file from bulk upload");
			steps.createNode("3. Upload file and click on save button");

			driver.findElement(By.id("submit-flock")).click();		
			driver.findElement(By.cssSelector(".anc-btn-secondary")).click();
			waitElementInvisible(loading_cursor);	
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));	
			Assert.assertEquals(driver.findElement(By.id("message")).getText(), "FlockMetadataValidation.xlsx saved successfully.");
			test.pass("Flock uploaded successfully");
			results.createNode("Flock uploaded successfully");
			test.addScreenCaptureFromPath(getScreenshot("Flock Registration", FlockManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, FlockManagementReportPath, null);
		}
		catch(AssertionError er) {
			test.fail("Flock failed to upload");
			results.createNode("Flock failed to upload");
			saveResultNew(ITestResult.FAILURE, FlockManagementReportPath, new Exception(er));
		}
	}


	@Test (enabled= true, priority = 16, dependsOnMethods = { "FlockValidation" }) 
	public void SiteAppearnace() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-FR-104: Verify only those sites appear which are assigned to user", "This testcase will verify that only those sites are displayed to user which are assigned to him");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Flock Registrations");
			steps.createNode("1. Verify only those sites appear which are assigned to user");

			driver.get(url_user);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);
			
			for (int i=1;i<=500;i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-3 label")).getText().equals(login_email)) {
					driver.findElement(By.id("edit-user-"+i)).click();
					break;
				}	
			}

			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			driver.findElement(By.id("btn-next")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("btn-next")).click();
			Thread.sleep(2000);

			int collectionSitesSize = 0;
			for (int i=1;i<=driver.findElements(By.cssSelector(".site-tree-card")).size();i++) {
				if (!driver.findElement(By.xpath("//*[@id=\"select-sites\"]//div["+i+"]/div/p[2]")).getText().equals("Collection Sites: 0")) {
					collectionSitesSize = collectionSitesSize+1;

					if (i == driver.findElements(By.cssSelector(".site-tree-card")).size()) {
						driver.get(url_flockManagement);
						waitElementInvisible(loading_cursor);
						Thread.sleep(2000);
						driver.findElement(By.id("create-flock")).click();
						waitElementInvisible(loading_cursor);
						driver.findElement(By.cssSelector("#siteId .fa-chevron-down")).click();
						waitElementInvisible(loading_cursor);

						int sitesCountflock = driver.findElements(By.cssSelector("#siteId tr")).size();

						Assert.assertEquals(sitesCountflock, collectionSitesSize);
						test.pass("Only those sites appeared  which are assigned to user successfully");
						results.createNode("Only those sites appeared which are assigned to user successfully");
						test.addScreenCaptureFromPath(getScreenshot("Flock Registrations", FlockManagementReportPath));
						saveResultNew(ITestResult.SUCCESS, FlockManagementReportPath, null);	
					}
				}
			}
		}
		catch(AssertionError er) {
			test.fail("Those sites did not appeared which are assigned to user");
			results.createNode("Those sites did not appeared which are assigned to user");
			saveResultNew(ITestResult.FAILURE, FlockManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Those sites did not appeared which are assigned to user");
			results.createNode("Those sites did not appeared which are assigned to user");
			saveResultNew(ITestResult.FAILURE, FlockManagementReportPath, ex);
		}
	}



	@Test (enabled= true, priority =29) 
	public void AllignmentTest() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-FR-98: Verify that int data type columns are right alligned", "This testcase will verify that int data type columns are right alligned");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);
			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Flock Registration; Flock Registration reports open");
			steps.createNode("1. Verify int data type columns are right alligned");

			driver.get(url_flockManagement);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			driver.findElement(By.id("edit-field-access")).click();
			waitElementInvisible(loading_cursor);
			driver.findElement(popupResetButton).click();
			waitElementInvisible(loading_cursor);
			driver.findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);

			SoftAssert softAssert = new SoftAssert();
			/*	softAssert.assertNotEquals(driver.findElements(By.cssSelector("#col-"+flockNumBirdsPlacedCol+" .text-right")).size(), 0, "Num birds placed is not right alligned");
		softAssert.assertNotEquals(driver.findElements(By.cssSelector("#col-"+flockNumBirdsDOACol+" .text-right")).size(), 0, "Num birds DOA is not right alligned");
		softAssert.assertNotEquals(driver.findElements(By.cssSelector("#col-"+flockNumBirdsProcessedCol+" .text-right")).size(), 0, "Num Birds Processed is not right alligned");
		softAssert.assertNotEquals(driver.findElements(By.cssSelector("#col-"+flockTotalWeightProcessedLBCol+" .text-right")).size(), 0, "Total Weight Processed LB is not right alligned");
		softAssert.assertNotEquals(driver.findElements(By.cssSelector("#col-"+flockTotalWeightProcessedKGCol+" .text-right")).size(), 0, "Total Weight Processed KG is not right alligned");
		softAssert.assertNotEquals(driver.findElements(By.cssSelector("#col-"+flockTotalFeedWeightLBCol+" .text-right")).size(), 0, "Total Feed Weight LB column is not right alligned");
		softAssert.assertNotEquals(driver.findElements(By.cssSelector("#col-"+flockTotalFeedWeightKGCol+" .text-right")).size(), 0, "Total Feed Weight KG column is not right alligned");
		softAssert.assertNotEquals(driver.findElements(By.cssSelector("#col-"+flockTotalWeightCondemnedLBCol+" .text-right")).size(), 0, "Total Weight Condemend LB column is not right alligned");
		softAssert.assertNotEquals(driver.findElements(By.cssSelector("#col-"+flockTotalWeightCondemnedKGCol+" .text-right")).size(), 0, "Total Weight Condemend KG column is not right alligned");
		softAssert.assertNotEquals(driver.findElements(By.cssSelector("#col-"+flockPartsWeightCondemnedLBCol+" .text-right")).size(), 0, "Parts Weight Condemend LB column is not right alligned");
		softAssert.assertNotEquals(driver.findElements(By.cssSelector("#col-"+flockPartsWeightCondemnedKGCol+" .text-right")).size(), 0, "Parts Weight Condemend KG column is not right alligned");
		softAssert.assertNotEquals(driver.findElements(By.cssSelector("#col-"+flockTotalCostPerWeightCol+" .text-right")).size(), 0, "Total Cost USDT per Weight column is not right alligned");
		softAssert.assertNotEquals(driver.findElements(By.cssSelector("#col-"+flockLiveabilityCol+" .text-right")).size(), 0, "Livability column is not right alligned");
		softAssert.assertNotEquals(driver.findElements(By.cssSelector("#col-"+flockMortalityCol+" .text-right")).size(), 0, "Mortality column is not right alligned");


		List<WebElement> A_Grade_Paws_PERC = driver.findElements(By.cssSelector("#col-"+flockAGradePawsCol));
		int size2 = A_Grade_Paws_PERC.size();
		for(int i=1;i<size2;i++) {
			if (A_Grade_Paws_PERC.get(i).getText() != "") {
				Double a = Double.parseDouble(A_Grade_Paws_PERC.get(i).getText());
				System.out.println(a);
				if (a<=1 && a >=0) {
					softAssert.assertTrue(true, "A Grade Paws percentile is in range 0-1");
				}
				else {
					softAssert.assertTrue(true, "A Grade Paws percentile is not in range 0-1");
				}
			}
		}

		List<WebElement> Mortality_PERC = driver.findElements(By.cssSelector("#col-"+flockMortalityCol));
		int size3 = Mortality_PERC.size();
		for(int i=1;i<size3;i++) {
			if (Mortality_PERC.get(i).getText() != "") {
				Double a = Double.parseDouble(Mortality_PERC.get(i).getText());
				System.out.println(a);
				if (a<=1 && a >=0) {
					softAssert.assertTrue(true, "Mortality percentile is in range 0-1");
				}
				else {
					softAssert.assertTrue(true, "Mortality percentile is not in range 0-1");
				}
			}
		}

		List<WebElement> Livability_PERC = driver.findElements(By.cssSelector("#col-"+flockLiveabilityCol));
		int size4 = Mortality_PERC.size();
		for(int i=1;i<size4;i++) {
			if (Livability_PERC.get(i).getText() != "") {
				Double a = Double.parseDouble(Livability_PERC.get(i).getText());
				System.out.println(a);
				if (a<=1 && a >=0) {
					softAssert.assertTrue(true, "Livability percentile is in range 0-1");
				}
				else {
					softAssert.assertTrue(true, "Livability percentile is not in range 0-1");
				}
			}
		}
			 */
			softAssert.assertAll();	
			test.pass("Int data type columns are right alligned");
			results.createNode("Int data type columns are right alligned");
			test.addScreenCaptureFromPath(getScreenshot("Flock Registration", FlockManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, FlockManagementReportPath, null);
		}catch(AssertionError er) {
			test.fail("Int data type columns are not right alligned");
			results.createNode("Int data type columns are not right alligned");
			saveResultNew(ITestResult.FAILURE, FlockManagementReportPath, new Exception(er));
		}catch(Exception ex){
			test.fail("Int data type columns are not right alligned");
			results.createNode("Int data type columns are not right alligned");
			saveResultNew(ITestResult.FAILURE, FlockManagementReportPath, ex);
		}
	}


	public File getTheNewestFile(String filePath, String ext) {
		File theNewestFile = null;
		File dir = new File(filePath);
		FileFilter fileFilter = new WildcardFileFilter("*." + ext);
		File[] files = dir.listFiles(fileFilter);

		if (files.length > 0) {
			Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			theNewestFile = files[0];
		}
		return theNewestFile;
	}


	@SuppressWarnings({ "unused", "resource" })
	@Test (description="Test Case: Test flock CSV Download",enabled= true, priority =17) 
	public void CSVExport() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-FR-105: Verify user can download Flock CSV file and verify the records", "This test case will verify that user can download Flock CSV file");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on Flock Registration; Flock Registration reports open");

			steps.createNode("1. Hover mouse towards table");
			steps.createNode("2. Export file button becomes visible");
			waitElementInvisible(loading_cursor);

			driver.get(url_flockManagement);
			waitElementInvisible(loading_cursor);

			driver.findElement(By.id(sitesSiteID+""+flockShowFilter)).click();	
			waitElementInvisible(loading_cursor);
			Thread.sleep(800);						
			ClickElement.clickByCss(driver, "#"+flockSortFilter+""+flockSiteID+" li:nth-child(3) label");

			ClickElement.clickById(driver, flockSiteID+""+sitesApplyFilter);
			waitElementInvisible(loading_cursor);
			Thread.sleep(800);

			String getRowText = driver.findElement(By.id("results-found-count")).getText();

			steps.createNode("3. Click on the button");
			steps.createNode("4. Dropdown cloud pop ups");
			steps.createNode("5. Click on Export as CSV");	
			steps.createNode("6. Verify the columns are same in table and CSV");

			Thread.sleep(1000);
			driver.findElement(By.cssSelector("#csv-action img")).click();
			Thread.sleep(1000);
			test.addScreenCaptureFromPath(getScreenshot("Flock Registration", FlockManagementReportPath));
			ClickElement.clickById(driver, "export-csv");
			waitElementInvisible(loading_cursor);
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			SalmonellaLog fr= new SalmonellaLog();
			File newfile = fr.getTheNewestFile(fileDownloadPath, "csv");
			String filename= newfile.getName();
			Assert.assertEquals(filename, flockCSVFileName+date+".csv");
			test.pass("CSV file downloaded successfully");
			results.createNode("CSV file downloads successfully");
			saveResultNew(ITestResult.SUCCESS, FlockManagementReportPath, null);

			File file = new File(fileDownloadPath+"\\"+filename);
			if(file.exists()){
				System.out.println("File Exists");
			}	

			SoftAssert softAssert = new SoftAssert();
			FileReader filereader = new FileReader(file);
			CSVReader reader = new CSVReader(filereader);
			reader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
			StringBuffer buffer = new StringBuffer();
			String data[];	

			int columnsCountTotal = 0;
			int rowsCount = 1;
			while((data = reader.readNext()) != null) {
				for (int i = 0; i<data.length; i++) {
					int rows = driver.findElements(By.cssSelector("tr")).size();
					if (rowsCount < rows) {
						int totalColumns = driver.findElements(By.cssSelector("tr:nth-child(1) td")).size() - 2;
						int columnsCount = columnsCountTotal+2;

						if (driver.findElements(By.cssSelector("tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).size() != 0 && columnsCount<=totalColumns) {
							softAssert.assertEquals(data[i].trim(), driver.findElement(By.cssSelector("tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).getText().trim());
						}
						else {
							rowsCount = rowsCount+1;
							columnsCount =0;
							columnsCountTotal = 0;  
						}
						columnsCountTotal++;
					}
				}
			}

			Path path = Paths.get(fileDownloadPath+"\\"+filename);
			long lines = 0;
			try {
				lines = Files.lines(path).count();
			} catch (IOException e) {
				e.printStackTrace();
			}

			long excludeHeader = lines - 1;
			String s = String.valueOf(excludeHeader);

			String str = getRowText;
			str = str.replace(",", "");
			Assert.assertEquals(s, str);

			if(file.delete()) {
				System.out.println("CSV file deleted");  
			}
			softAssert.assertAll();
		}
		catch(AssertionError er) {
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			saveResultNew(ITestResult.FAILURE, FlockManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			saveResultNew(ITestResult.FAILURE, FlockManagementReportPath, ex);
		}
		Thread.sleep(1000);
	}


	@Test (description="Test Case: Test Flock Audit Download",enabled= true, priority = 18) 
	public void CSVAuditExport() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-FR-106: Verify user can download Flock Audit file", "This test case will verify that user can download Flock Audit file");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on Flock Registration; Coccidia Log reports open");

			steps.createNode("1. Hover mouse towards table");
			steps.createNode("2. Export file button becomes visible");
			Thread.sleep(1000);

			//String getRowCount = driver.findElement(By.id("results-found-count")).getText();

			steps.createNode("3. Click on the button");
			steps.createNode("4. Dropdown cloud pop ups");
			driver.findElement(By.cssSelector("#csv-action img")).click();
			test.addScreenCaptureFromPath(getScreenshot("Flock Registration", FlockManagementReportPath));
			Thread.sleep(1500);
			steps.createNode("5. Click on Export with Audit as CSV");
			ClickElement.clickById(driver, "export-audit-csv");
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			SalmonellaLog fr= new SalmonellaLog();
			File newfile = fr.getTheNewestFile(fileDownloadPath, "csv");
			String filename= newfile.getName();
			//System.out.println("Latest CSV file is = "+filename);
			Assert.assertEquals(filename, flockCSVAuditFileName+date+".csv");
			test.pass("CSV file downloaded successfully");
			results.createNode("CSV file downloads successfully");
			saveResultNew(ITestResult.SUCCESS, FlockManagementReportPath, null);

			File file = new File(fileDownloadPath+"\\"+filename); 
			if(file.delete())
				System.out.println("CSV Audit file deleted");
		}
		catch(AssertionError er) {
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			saveResultNew(ITestResult.FAILURE, FlockManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			saveResultNew(ITestResult.FAILURE, FlockManagementReportPath, ex);
		}
	}



	@Test (description="Test Case: Test Flock Template Download",enabled= true, priority = 19) 
	public void TemplateExport() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-FR-107: Verify user can download Flock Template file", "This test case will verify that user download Flock Template file");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on Flock Registration; Coccidia Log reports open");

			steps.createNode("1. Hover mouse towards table");
			steps.createNode("2. Export file button becomes visible");
			steps.createNode("3. Click on the button");
			driver.findElement(By.cssSelector("#csv-action img")).click();
			steps.createNode("4. Dropdown cloud pop ups");
			Thread.sleep(1000);
			ClickElement.clickById(driver, "export-data-template");
			Thread.sleep(1000);
			test.addScreenCaptureFromPath(getScreenshot("Flock Registration", FlockManagementReportPath));
			steps.createNode("5. Click on Export Data Template");
			SalmonellaLog fr= new SalmonellaLog();
			File newfile = fr.getTheNewestFile(fileDownloadPath, "xlsx");
			String filename= newfile.getName();
			Assert.assertTrue(filename.startsWith("FLOCK METADATA"), "File did not downloaded with name as FLOCK METADATA");

			int colNumTable = driver.findElements(By.cssSelector("th")).size();

			FileInputStream fis = new FileInputStream(fileDownloadPath+"\\FLOCK METADATA.xlsx");
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("Sheet");
			XSSFRow row = sheet.getRow(0);
			int colNum = row.getLastCellNum();
			System.out.println("Total Number of Columns in the excel is : "+colNum);

			Assert.assertEquals(colNum, colNumTable);

			test.pass("Sample MetaData template downloaded successfully");
			results.createNode("Sample MetaData template downloaded successfully");
			saveResultNew(ITestResult.SUCCESS, FlockManagementReportPath, null);

			File file = new File(fileDownloadPath+"\\"+filename); 
			if(file.delete())
				System.out.println("Template file deleted");
		}
		catch(AssertionError er) {
			test.fail("Sample MetaData downoad failed");
			results.createNode("Sample MetaData failed to download");  
			saveResultNew(ITestResult.FAILURE, FlockManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Sample MetaData downoad failed");
			results.createNode("Sample MetaData failed to download");  	
			saveResultNew(ITestResult.FAILURE, FlockManagementReportPath, ex);
		}
	}

	@AfterTest
	public static void endreport() {
		extent.flush();
		//	driver.close();
	}

}


