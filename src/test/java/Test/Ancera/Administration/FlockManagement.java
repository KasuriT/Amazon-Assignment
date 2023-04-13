package Test.Ancera.Administration;

import java.util.Arrays;
import java.util.List;

import MiscFunctions.*;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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
import Config.ReadPropertyFile;
import MiscFunctions.DB_Config_DW;
import Models.FlockManagementModel;
import Models.ProgramManagementModel;
import PageObjects.FlockManagementPage;
import PageObjects.UserManagementPage;
import Test.Ancera.Login.LoginTest;

import static LogViewFunctions.FilterLock.*;
import static LogViewFunctions.FilterWildcard.*;
import static LogViewFunctions.FilterSort.*;
import static LogViewFunctions.Pagination.*;
import static LogViewFunctions.RowsPerPage.*;
import static MiscFunctions.Constants.*;
import static MiscFunctions.DateUtil.*;
import static MiscFunctions.DownloadFileCheck.getTheNewestFile;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import static LogViewFunctions.LogCSVExport.*;
import static PageObjects.FlockManagementPage.*;
import static PageObjects.ProgramManagementPage.*;
import static PageObjects.BasePage.*;

import java.io.*;
//import static org.openqa.selenium.support.locators.RelativeLocator.with;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FlockManagement extends BaseTest {


	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		spark = new ExtentSparkReporter("target/Reports/Administration_Flock_Management"+date+".html");
		spark.config().setReportName("Flock Management Test Report"); 
		DB_Config_DW.test();
	}

	@BeforeClass
	public void Login() throws InterruptedException, IOException {
		LoginTest.login();
	}

	/*
		@Test (priority = 1) 
	public void NavigateFlock() throws InterruptedException, IOException {
			NavigateToScreen.navigate(url_flockManagement, "Flock Management", FlockManagementTitle);
	}


	@Test (priority = 2) 
	public void LockPlacement() throws InterruptedException, IOException {
		Lock(flockPlacementTable, "Flock Management", 1);
	}


	@Test (priority = 3) 
	public void LockMortality() throws InterruptedException, IOException {
		getDriver().findElement(flockMortalityTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Lock(flockMortalityTable, "Flock Management", 1);
	}


	@Test (priority = 4) 
	public void LockSettlement() throws InterruptedException, IOException {
		getDriver().findElement(flockSettlementTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Lock(flockSettlementTable, "Flock Management", 1);
	}


	@Test (priority = 5) 
	public void LockCondemnation() throws InterruptedException, IOException {
		getDriver().findElement(flockCondemnationTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Lock(flockCondemnationTable, "Flock Management", 1);
	}


	@Test (priority = 6) 
	public void WildcardPlacement() throws InterruptedException, IOException {
		getDriver().get(url_flockManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Wildcard(flockPlacementTable, "Flock Management", 1);
	}


	@Test (priority = 7) 
	public void WildcardMortality() throws InterruptedException, IOException {
		getDriver().get(url_flockManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		getDriver().findElement(flockMortalityTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Wildcard(flockMortalityTable, "Flock Management", 1);
	}


	@Test (priority = 8) 
	public void WildcardSettlement() throws InterruptedException, IOException {
		getDriver().findElement(flockSettlementTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Wildcard(flockSettlementTable, "Flock Management", 1);
	}


	@Test (priority = 9) 
	public void WildcardCondemnation() throws InterruptedException, IOException {
		getDriver().findElement(flockCondemnationTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Wildcard(flockCondemnationTable, "Flock Management", 1);
	}


	@Test(priority= 10)
	public void SortingPlacement() throws InterruptedException, IOException {
		getDriver().get(url_flockManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(2000);
		Sorting(flockPlacementTable, "Flock Management", 1);
	}


	@Test(priority= 11)
	public void SortingMortality() throws InterruptedException, IOException {
		getDriver().findElement(flockMortalityTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Sorting(flockMortalityTable, "Flock Management", 1);
	}


	@Test(priority= 12)
	public void SortingSettlement() throws InterruptedException, IOException {
		getDriver().findElement(flockSettlementTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Sorting(flockSettlementTable, "Flock Management", 1);
	}


	@Test(priority= 13)
	public void SortingCondemnation() throws InterruptedException, IOException {
		getDriver().findElement(flockCondemnationTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Sorting(flockCondemnationTable, "Flock Management", 1);
	}


	@Test(priority= 14, enabled = true)
	public void RowsPerPagePlacement() throws InterruptedException, IOException {
		getDriver().get(url_flockManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		RowsPerPage1(flockPlacementTable);
	}


	@Test(priority= 15, enabled = true)
	public void RowsPerPageMortality() throws InterruptedException, IOException {
		getDriver().findElement(flockMortalityTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		RowsPerPage1(flockMortalityTable);
	}


	@Test(priority= 16, enabled = true)
	public void RowsPerPageSettlement() throws InterruptedException, IOException {
		getDriver().findElement(flockSettlementTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		RowsPerPage1(flockSettlementTable);
	}


	@Test(priority= 17, enabled = true)
	public void RowsPerPageCondemnation() throws InterruptedException, IOException {
		getDriver().findElement(flockCondemnationTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		RowsPerPage1(flockCondemnationTable);
	}


	@Test (priority = 18) 
	public void PaginationPlacement() throws InterruptedException, IOException {
		getDriver().get(url_flockManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		Pagination(flockPlacementTable, "Program Management", ReportFilePath);
	}


	@Test (priority = 19) 
	public void PaginationMortality() throws InterruptedException, IOException {
		getDriver().findElement(flockMortalityTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Pagination(flockMortalityTable, "Program Management", ReportFilePath);
	}


	@Test (priority = 20) 
	public void PaginationSettlement() throws InterruptedException, IOException {
		getDriver().findElement(flockSettlementTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Pagination(flockSettlementTable, "Program Management", ReportFilePath);
	}


	@Test (priority = 21) 
	public void PaginationCondemnation() throws InterruptedException, IOException {
		getDriver().findElement(flockCondemnationTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Pagination(flockCondemnationTable, "Program Management", ReportFilePath);
	}
	 

	@Test (priority = 25, enabled = true) 
	public void CreateProgram() throws InterruptedException, IOException, SQLException {
		test = extent.createTest("AN-FR-98: Verify user can create Program");
		getDriver().get(url_programManagement);
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

		if (Constants.config.url().contains("qa") || Constants.config.url().contains("dev")) {
			ResultSet getComplexNameResults = DB_Config_DW.getStmt().executeQuery(Queries.getComplexName);
			while (getComplexNameResults.next()) {
				String complexName = getComplexNameResults.getString("siteName");
				System.out.println("Complex Name: "+complexName);
				click(programComplexList);
				type(programComplexSearch, complexName);
			}
		}
		if (Constants.config.url().contains("uat")) {
			getDriver().findElement(programComplexSearch).sendKeys(ProgramManagementModel.ComplexNameUAT);
		}
		
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		click(clickSearchItemFromHierarchy);

		click(programStartDateIcon);
		waitElementInvisible(loading_cursor);
		Thread.sleep(500);
		Methods method = new Methods();
		WebElement dateWidgetTo = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#startDate .dp-popup"))).get(0);	
		List<WebElement> columns1 = dateWidgetTo.findElements(By.tagName("button"));
		DateUtil.clickGivenDay(columns1, DateUtil.getFirstDay());
		Thread.sleep(500);
		type(programNoApplicationFlock, "0");
		click(popupSaveButtonXpath);
		waitElementInvisible(loading_cursor);
		Thread.sleep(700);
		Assert.assertEquals(getDriver().findElement(alertMessage).getText(), "New program has been created successfully");
		//	getStmt().close();
	}

	*/

	@Test (enabled= true, priority = 26) 
	public void CreateFlock() throws InterruptedException, IOException, SQLException {
		try {
			test = extent.createTest("AN-FR-98: Verify user can create Flock");

			SoftAssert softAssert = new SoftAssert();

			getDriver().get(url_flockManagement);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			String PreResultsCount = getText(By.id(ResultsCount));

			click(flockCreateButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			if (Constants.config.url().contains("qa") || Constants.config.url().contains("dev")) {
				ResultSet getFarmNameResults = DB_Config_DW.getStmt().executeQuery(Queries.getFarmName);
				while (getFarmNameResults.next()) {
					String farmName = getFarmNameResults.getString("siteName");
					System.out.println("Farm Name: "+farmName);
					click(flockFarmDropdownExpand);
					type(flockFarmDropdownSearch, farmName);

				}
			}
			
			if (Constants.config.url().contains("uat")) {
				getDriver().findElement(programComplexSearch).sendKeys(ProgramManagementModel.FarmNameUAT);
			}
			
			waitElementInvisible(loading_cursor);
			waitElementClickable(By.cssSelector("label b"));
			Thread.sleep(2000);
			click(By.cssSelector("label b"));
			
			type(flockIntegratorFlockID, FlockManagementModel.flockIntegratorID);

			if (size(flockIntegratorFlockAddNew) != 0) {
				click(flockIntegratorFlockAddNew);
			}
			else {
				getDriver().findElement(flockIntegratorFlockID).sendKeys(Keys.ENTER);
			}


			click(flockBirdSizeInput);
			List<WebElement> birdSizeDropdownValues = getDriver().findElements(flockBirdSizeDropDownOptions);
			softAssert.assertEquals(birdSizeDropdownValues.get(0).getText(), "Small");
			softAssert.assertEquals(birdSizeDropdownValues.get(1).getText(), "Medium");
			softAssert.assertEquals(birdSizeDropdownValues.get(2).getText(), "Large");
			softAssert.assertEquals(birdSizeDropdownValues.get(3).getText(), "Pullets");
			softAssert.assertEquals(birdSizeDropdownValues.get(4).getText(), "Broilers");
			softAssert.assertEquals(birdSizeDropdownValues.get(5).getText(), "Big Broilers");


			type(flockBirdSizeInput, FlockManagementModel.flockBirdSize);
			getDriver().findElement(flockBirdSizeInput).sendKeys(Keys.ENTER);

			click(flockBirdSexInput);
			List<WebElement> birdSexDropdownValues = getDriver().findElements(flockBirdSexDropDownOptions);
			softAssert.assertEquals(birdSexDropdownValues.get(0).getText(), "Male");
			softAssert.assertEquals(birdSexDropdownValues.get(1).getText(), "Female");
			softAssert.assertEquals(birdSexDropdownValues.get(2).getText(), "Mixed");
			enterKey(flockBirdSexInput);

			click(flockMarketingProgramInput);
			List<WebElement> flockMarketingProgramdropdownValues = getDriver().findElements(flockMarketingProgramDropDownOptions);
			softAssert.assertEquals(flockMarketingProgramdropdownValues.get(0).getText(), "Conventional");
			softAssert.assertEquals(flockMarketingProgramdropdownValues.get(1).getText(), "No Human Antibiotics");
			softAssert.assertEquals(flockMarketingProgramdropdownValues.get(2).getText(), "No Antibiotics Ever");
			softAssert.assertEquals(flockMarketingProgramdropdownValues.get(3).getText(), "Organic");
			softAssert.assertEquals(flockMarketingProgramdropdownValues.get(4).getText(), "Pastured");
			softAssert.assertEquals(flockMarketingProgramdropdownValues.get(5).getText(), "Other");
			enterKey(flockMarketingProgramInput);

			scroll(flockPlacementDateCalendar);
			click(flockPlacementDateCalendar);

			List<WebElement> list = getDriver().findElements(By.cssSelector(".dp-current-day"));
			scroll(By.xpath("//label[text() = 'Flock Information']"));
			Thread.sleep(1000);	

			list.get(2).click();
			Thread.sleep(1000);

			click(flockHousePlacedDropdownExpand);
			Thread.sleep(1000);
			
			if (Constants.config.url().contains("qa") || Constants.config.url().contains("dev")) {
			ResultSet getHouseNameResults = DB_Config_DW.getStmt().executeQuery(Queries.getFarmHousesCount);
			while (getHouseNameResults.next()) {
				int housesCount = getHouseNameResults.getInt("Count");
				System.out.println("House Count: "+housesCount);
				softAssert.assertEquals(size(getDropdownValueCount), housesCount, "Houses count of farm not right");
				click(By.xpath("//*[text() = 'Select All']"));
				click(flockHousePlacedDropdownExpand);
				softAssert.assertEquals(size(By.xpath("//*[contains(text(), 'Birds placed')]")), housesCount, "Birds Placed fields not populated");
			}
			}
			else {
				test.skip("cannot be executed on UAT");
			}

			waitElementClickable(popupSubmitButton);
			Thread.sleep(500);
			click(popupSubmitButton);
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(5000);
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Data saved successfully.");

			String PostResultsCount = getText(By.id(ResultsCount));
			softAssert.assertEquals(Integer.parseInt(PostResultsCount), Integer.parseInt(PreResultsCount)+1);

			click(flockPlacementTab);
			Thread.sleep(500);
			FlockManagementPage.openFlockAudit();
			softAssert.assertEquals(size(auditGetRowCount), 3, "Audit Log not displaying a row for flock creation");
			softAssert.assertEquals(getText(auditActionRow1), "Created", "Audit Log not displaying a row for with Action created");

			softAssert.assertAll();

			
			getScreenshot();
			test.pass("Flock was created successfully");
			saveResult(ITestResult.SUCCESS, null);	
		}
		catch(AssertionError er) {
			test.fail("Flock failed to create");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Flock failed to create");
			saveResult(ITestResult.FAILURE, ex);
		}
	}




	@Test (description = "IE-3595", enabled= true, priority = 27) 
	public void DuplicateFlockWithSameSiteAndPlacementDate() throws InterruptedException, IOException, SQLException {
		try {
			test = extent.createTest("AN-FR-99: Verify that Flock cannot be duplicated with same Site ID and Placement Date.");
			SoftAssert softAssert = new SoftAssert();

			getDriver().get(url_flockManagement);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			click(flockCreateButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			
			if (Constants.config.url().contains("qa") || Constants.config.url().contains("dev")) {
				ResultSet getFarmNameResults = DB_Config_DW.getStmt().executeQuery(Queries.getFarmName);
				while (getFarmNameResults.next()) {
					String farmName = getFarmNameResults.getString("siteName");
					click(flockFarmDropdownExpand);
					type(flockFarmDropdownSearch, farmName);

				}		
			}

			if (Constants.config.url().contains("uat")) {
				getDriver().findElement(programComplexSearch).sendKeys(ProgramManagementModel.FarmNameUAT);
			}
			
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			click(By.cssSelector("label b"));
			
			type(flockBirdSizeInput, FlockManagementModel.flockBirdSize);
			getDriver().findElement(flockBirdSizeInput).sendKeys(Keys.ENTER);

			scroll(flockPlacementDateCalendar);
			click(flockPlacementDateCalendar);

            List<WebElement> list = getDriver().findElements(By.cssSelector(".dp-current-day"));
            scroll(By.xpath("//label[text() = 'Flock Information']"));
            Thread.sleep(1000);    

            list.get(2).click();
            Thread.sleep(1000);
			
			click(flockHousePlacedDropdownExpand);
			Thread.sleep(1000);
			click(By.xpath("//*[text() = 'Select All']"));
			click(flockHousePlacedDropdownExpand);

			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			softAssert.assertTrue(getDriver().findElement(alertMessage).getText().contains("already exists at"));
			softAssert.assertAll();

			test.pass("Flock was created successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);	
		}
		catch(AssertionError er) {
			test.fail("Flock failed to create");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Flock failed to create");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (enabled= true, priority =28) 
	public void EditFlock() throws InterruptedException, IOException {
		try {
			test = extent.createTest("Verify user can edit flock");
			getDriver().get(url_flockManagement);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			SoftAssert softAssert = new SoftAssert();

			FlockManagementPage.openFlockAudit();
			int getRowsPre = size(flockAuditRowCount);
			Thread.sleep(1000);
			click(popupCloseButton);

			FlockManagementPage.openEditFlock();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			type(flockBirdSexInput, "Male");
			getDriver().findElement(flockBirdSexInput).sendKeys(Keys.ENTER);
			click(By.xpath("(//*[@id = 'btn-submit'])[1]"));
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Data saved successfully.");


			click(flockEditMortalityTab);
			waitElementInvisible(loading_cursor);	
			for (int i=1; i<=9; i++) {
				By mortalityField = By.cssSelector("#num-week_"+i+"_Mortality");
				scroll(mortalityField);
				type(mortalityField, Integer.toString(i));
			}
			click(By.xpath("(//*[@id = 'btn-submit'])[2]"));
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Data saved successfully.");


			click(flockEditSettlementTab);
			waitElementInvisible(loading_cursor);	
			type(flockWeeklyFarmRank, "1");
			type(flockHistoricalFarmCostVariance, "1");
			type(flockWeeklyFarmCostVariance, "1");
			type(flockPlacementDensityInput, "1.5");
			softAssert.assertEquals(getAttribute(flockPlacementDensityInput), "1.5", "Not able to write decimal value in Flock density field");
			click(By.xpath("(//*[@id = 'btn-submit'])[3]"));
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Data saved successfully.");


			click(flockEditCondemnationTab);
			waitElementInvisible(loading_cursor);
			type(flockNumBirdsDOAPlant, "1");
			click(By.xpath("(//*[@id = 'btn-submit'])[4]"));
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Data saved successfully.");
			click(popupCloseButton);


			FlockManagementPage.openFlockAudit();
			int getRowsPost = size(flockAuditRowCount);
			click(popupCloseButton);

			softAssert.assertEquals(getRowsPost, getRowsPre);
			softAssert.assertAll();

			test.pass("Flock was edited successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);	
		}
		catch(AssertionError er) {
			test.fail("Flock failed to edit");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Flock failed to edit");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test(enabled= true, priority =29)
	public void GetPlacementHouses() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-FR-99: Verify House Placement popup from log and audit view");
			SoftAssert softAssert = new SoftAssert();
			getDriver().get(url_flockManagement);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);

			String b = getText(By.cssSelector("tr:nth-child(1) #col-"+flockPlacementDateCol+" label[title]:nth-child(2)"));

			click(By.cssSelector("tr:nth-child(1) #col-"+flockPlacementDateCol));
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);

			int totalHouses = Integer.parseInt(b.substring(1, b.length()-5)) +2;
			softAssert.assertEquals(size(popupTotalRows), totalHouses);

			click(popupCloseButton);

			click(flockAuditTrail);
			waitElementInvisible(loading_cursor);
			
			int auditRowsCount = size(popupTotalRows);

			scroll(By.cssSelector(".popup-card tr:nth-child(1) #col-"+placementAuditHousePlacementCol));
			Thread.sleep(1000);
			click(By.cssSelector(".popup-card tr:nth-child(1) #col-"+placementAuditHousePlacementCol));
			waitElementInvisible(loading_cursor);
			softAssert.assertEquals(size(popupTotalRows)-auditRowsCount, totalHouses);
			click(popupCloseButton);
			test.pass("House Placement popup tested successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);	
		}
		catch(AssertionError er) {
			test.fail("House Placement log not displaying all houses");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("House Placement log not displaying all houses");
			saveResult(ITestResult.FAILURE, ex);
		}
	}



	@Test (enabled= true, priority =30)
	public void InlineEditFlock() throws InterruptedException, IOException, SQLException {
		try {			
			test = extent.createTest("Verify inline edit flock");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			getDriver().get(url_flockManagement);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			SoftAssert softAssert = new SoftAssert();

			click(By.id(flockIntegratorID+flockShowFilter));
			waitElementInvisible(loading_cursor);
			type(By.id(flockIntegratorID+flockSearchFilter), FlockManagementModel.flockIntegratorID);
			click (By.cssSelector("#ul-integratorFlockId li:nth-child(1) label"));
			click(By.id(flockIntegratorID+flockApplyFilter));
			waitElementInvisible(loading_cursor);
		//	softAssert.assertEquals(size(By.cssSelector("#"+flockPlacementTable+" #"+flockInlineButtonTooltip)), 1, "Tooltip is not applied on in line edit button");
			click(By.id(flockInlineButton));
			waitElementInvisible(loading_cursor);

			//verify fields should not be editable
			softAssert.assertEquals(size(By.cssSelector("#"+flockPlacementTable+" #col-"+flockIDPlacementCol+" input")), 0, "Flock ID is editable field");
			softAssert.assertEquals(size(By.cssSelector("#"+flockPlacementTable+" #col-"+flockNumofBirdsPlacedPlacementCol+" input")), 0, "Number of birds placed is editable field");
			softAssert.assertEquals(size(By.cssSelector("#"+flockPlacementTable+" #col-"+flockComplexPlacementCol+" input")), 0, "Complex is editable field");
			softAssert.assertEquals(size(By.cssSelector("#"+flockPlacementTable+" #col-"+flockIntegratorPlacementCol+" input")), 0, "Integrator is editable field");
			softAssert.assertEquals(size(By.cssSelector("#"+flockPlacementTable+" #col-"+flockFarmSiteIDPlacementCol+" input")), 0, "Farm Site ID is editable field");

			click(flockInlineNewProgramIcon);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			if(size(flockProgramDeleteButton) != 0) {
				click(flockProgramDeleteButton);
			}
			click(flockInlineAddNewProgramPopup);
//			type(flockInlineProgramName, FlockManagementModel.flockProgramName);
			click(flockInlineProgramName);
			Thread.sleep(500);
			enterKey(flockInlineProgramName);
			type(flockDoseInput, "1.52");


			if (size(flockAdministrationMethod) != 0) {
				type(flockAdministrationMethod, FlockManagementModel.flockProgramAdminMethod);

				if (size(clickAddNewDropdown) != 0) {
					click(clickAddNewDropdown);
				} else {
					click(By.xpath("//*[text()='" + FlockManagementModel.flockProgramAdminMethod + "']"));
				}
			}
			Thread.sleep(1000);
			click(flockProgramSaveButton);
			waitElementInvisible(loading_cursor);


			clear(flockBirdBreed);
			type(flockBirdBreed, "Female");
			enterKey(flockBirdBreed);
			click(By.cssSelector("#"+flockPlacementTable+" #"+flockInlineButtonSave));
			waitElementInvisible(loading_cursor);
			click(popupYesButton);
			waitElementVisible(alertMessage);
			softAssert.assertEquals(getText(alertMessage), "Data saved successfully.");
			getScreenshot();

			click(flockMortalityTab);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);
			//		softAssert.assertEquals(size(By.cssSelector("#"+flockMortalityTable+" #"+flockInlineButtonTooltip)), 1, "Tooltip is not applied on in line edit button");

			try {
				waitElementClickable(By.cssSelector("#"+flockMortalityTable+" #"+flockInlineButton));
				click(By.cssSelector("#"+flockMortalityTable+" #"+flockInlineButton));
			}
			catch(ElementNotInteractableException ex) {
				System.out.println(456);
				click(By.cssSelector("#"+flockMortalityTable+" #"+flockInlineButton));
			}

			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			softAssert.assertEquals(size(By.cssSelector("#"+flockMortalityTable+" #col-"+flockFlockIDCol+" input")), 0, "Flock ID is editable field");
			softAssert.assertEquals(size(By.cssSelector("#"+flockMortalityTable+" #col-"+flockIntegratorFlockIDCol+" input")), 0, "Integrator Flock ID is editable field");
			softAssert.assertEquals(size(By.cssSelector("#"+flockMortalityTable+" #col-"+flockIntegratorCol+" input")), 0, "Integrator placed is editable field");
			softAssert.assertEquals(size(By.cssSelector("#"+flockMortalityTable+" #col-"+flockComplexCol+" input")), 0, "Complex is editable field");
			softAssert.assertEquals(size(By.cssSelector("#"+flockMortalityTable+" #col-"+flockFarmCol+" input")), 0, "Farm is editable field");
			softAssert.assertEquals(size(By.cssSelector("#"+flockMortalityTable+" #col-"+flockFarmSiteIDCol+" input")), 0, "Farm Site ID is editable field");
			softAssert.assertEquals(size(By.cssSelector("#"+flockMortalityTable+" #col-"+flockPlacementDateCol+" input")), 0, "Placement Date is editable field");			

			clear(flockInlineMortality1Input);
			type(flockInlineMortality1Input, "2.5");
			click(By.cssSelector("#"+flockMortalityTable+" #"+flockInlineButtonSave));
			waitElementVisible(popupYesButton);
			click(popupYesButton);
			waitElementVisible(alertMessage);
			softAssert.assertEquals(getText(alertMessage), "Data saved successfully.");
			getScreenshot();

			click(flockSettlementTab);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2500);
			//	softAssert.assertEquals(size(By.cssSelector("#"+flockSettlementTable+" #"+flockInlineButtonTooltip)), 1, "Tooltip is not applied on in line edit button");
			try {
				waitElementClickable(By.cssSelector("#"+flockSettlementTable+" #"+flockInlineButton));
				click(By.cssSelector("#"+flockSettlementTable+" #"+flockInlineButton));
			}
			catch(ElementNotInteractableException ex) {
				click(By.cssSelector("#"+flockSettlementTable+" #"+flockInlineButton));
			}
			System.out.println(4);
			waitElementInvisible(loading_cursor);

			softAssert.assertEquals(size(By.cssSelector("#"+flockSettlementTable+" #col-"+flockFlockIDCol+" input")), 0, "Flock ID is editable field");
			softAssert.assertEquals(size(By.cssSelector("#"+flockSettlementTable+" #col-"+flockIntegratorFlockIDCol+" input")), 0, "Integrator Flock ID is editable field");
			softAssert.assertEquals(size(By.cssSelector("#"+flockSettlementTable+" #col-"+flockIntegratorCol+" input")), 0, "Integrator placed is editable field");
			softAssert.assertEquals(size(By.cssSelector("#"+flockSettlementTable+" #col-"+flockComplexCol+" input")), 0, "Complex is editable field");
			softAssert.assertEquals(size(By.cssSelector("#"+flockSettlementTable+" #col-"+flockFarmCol+" input")), 0, "Farm is editable field");
			softAssert.assertEquals(size(By.cssSelector("#"+flockSettlementTable+" #col-"+flockFarmSiteIDCol+" input")), 0, "Farm Site ID is editable field");
			softAssert.assertEquals(size(By.cssSelector("#"+flockSettlementTable+" #col-"+flockPlacementDateCol+" input")), 0, "Placement Date is editable field");					

			type(flockInlineSettlementWeeklyFarmRank, "1");
			type(flockInlineSettlementHistoricalFarmCostVariance, "2");
			type(flockInlineSettlementWeeklyFarmCostVariance, "3");
			type(flockInlineSettlementhatchDate, dateMMDDYYYY1);
			type(flockInlineSettlementDaysOut, "4");
			type(flockInlineSettlementAgeofLitter, "5");
			type(flockInlineSettlementAverageSoldAge, "6");
			type(flockInlineSettlementNumBirdsSold, "7");
			clear(flockInlineSettlementPlacementDensity);
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
			click(By.cssSelector("#"+flockSettlementTable+" #"+flockInlineButtonSave));
			click(popupYesButton);
			waitElementVisible(alertMessage);
			softAssert.assertEquals(getText(alertMessage), "Data saved successfully.");
			getScreenshot();

			click(flockCondemnationTab);

			waitElementInvisible(loading_cursor);
			Thread.sleep(2500);
			//	softAssert.assertEquals(size(By.cssSelector("#"+flockCondemnationTable+" #"+flockInlineButtonTooltip)), 1, "Tooltip is not applied on in line edit button");
			try {
				waitElementClickable(By.cssSelector("#"+flockCondemnationTable+" #"+flockInlineButton));
				click(By.cssSelector("#"+flockCondemnationTable+" #"+flockInlineButton));
			}
			catch(ElementNotInteractableException ex) {
				click(By.cssSelector("#"+flockCondemnationTable+" #"+flockInlineButton));
			}
			waitElementInvisible(loading_cursor);

			softAssert.assertEquals(size(By.cssSelector("#"+flockCondemnationTable+" #col-"+flockFlockIDCol+" input")), 0, "Flock ID is editable field in Condemnation Tab");
			softAssert.assertEquals(size(By.cssSelector("#"+flockCondemnationTable+" #col-"+flockIntegratorFlockIDCol+" input")), 0, "Integrator Flock ID is editable field in Condemnation Tab");
			softAssert.assertEquals(size(By.cssSelector("#"+flockCondemnationTable+" #col-"+flockIntegratorCol+" input")), 0, "Integrator placed is editable field in Condemnation Tab");
			softAssert.assertEquals(size(By.cssSelector("#"+flockCondemnationTable+" #col-"+flockComplexCol+" input")), 0, "Complex is editable field in Condemnation Tab");
			softAssert.assertEquals(size(By.cssSelector("#"+flockCondemnationTable+" #col-"+flockFarmCol+" input")), 0, "Farm is editable field in Condemnation Tab");
			softAssert.assertEquals(size(By.cssSelector("#"+flockCondemnationTable+" #col-"+flockFarmSiteIDCol+" input")), 0, "Farm Site ID is editable field in Condemnation Tab");
			softAssert.assertEquals(size(By.cssSelector("#"+flockCondemnationTable+" #col-"+flockPlacementDateCol+" input")), 0, "Placement Date is editable field in Condemnation Tab");			

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
			click(By.cssSelector("#"+flockCondemnationTable+" #"+flockInlineButtonSave));
			click(popupYesButton);
			waitElementVisible(alertMessage);
			softAssert.assertEquals(getText(alertMessage), "Data saved successfully.");
			softAssert.assertAll();

			test.pass("Flock inline tested successfully");
			results.createNode("Flock inline tested successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);	
		}
		catch(AssertionError er) {
			test.fail("Flock inline failed");
			results.createNode("Flock inline failed");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Flock inline failed");
			results.createNode("Flock inline failed");
			saveResult(ITestResult.FAILURE, ex);
		}
		DB_Config_DW.getStmt().close();
	}


	@Test (description = "IE-4153", enabled = true, priority =31) 
	public void VerifyUserSitesinFarmDropDown() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-FR-105: Verify that all sites assigned to user are displayed in Flock Farm dropdown");
			ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);
			UserManagementPage.openEditUserPopup(config.ie_username());
			click(popupNextButton);
			Thread.sleep(500);
			click(popupNextButton);
			Thread.sleep(500);

			int collectionSitesSize = 0;
			for (int i=1;i<=getDriver().findElements(By.cssSelector(".site-tree-card")).size();i++) {
				if (!getDriver().findElement(By.xpath("//*[@id=\"select-sites\"]//div["+i+"]/div/p[2]")).getText().equals("Collection Sites: 0")) {
					collectionSitesSize = collectionSitesSize+1;

					if (i == size(By.cssSelector(".site-tree-card"))) {
						getDriver().get(url_flockManagement);
						waitElementInvisible(loading_cursor);
						Thread.sleep(1000);

						click(flockCreateButton);
						waitElementInvisible(loading_cursor);
						waitElementVisible(flockFarmDropdownExpand);
						click(flockFarmDropdownExpand);
						int sitesCountFarmDropdown = size(flockFarmDropdownGetAllSites);

						Assert.assertEquals(sitesCountFarmDropdown, collectionSitesSize);
						test.pass("Only those sites appeared  which are assigned to user successfully");
						getScreenshot();
						saveResult(ITestResult.SUCCESS, null);	
					}
				}
			}
		}
		catch(AssertionError er) {
			test.fail("Sites not appeared  which are assigned to user");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Sites not appeared  which are assigned to user");
			saveResult(ITestResult.FAILURE, ex);
		}
	}



	@Test(priority= 42)
	public void ExportCSV() throws InterruptedException, IOException {
		getDriver().get(url_flockManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		CSVExport1("Flock Management", flockPlacementCSVFileName, flockPlacementTable, 2, 0);
	}


	@Test (description="Test Case: Test Flock Audit Download",enabled= true, priority = 44) 
	public void CSVAuditExport() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-FR-106: Verify user can download Flock Audit file", "This test case will verify that user can download Flock Audit file");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Hover mouse towards table");
			steps.createNode("2. Export file button becomes visible");
			Thread.sleep(1000);

			//String getRowCount = getDriver().findElement(By.id("results-found-count")).getText();			

			steps.createNode("3. Click on the button");
			steps.createNode("4. Dropdown cloud pop ups");
			getDriver().findElement(By.cssSelector("#csv-action img")).click();
			getScreenshot();
			Thread.sleep(1500);
			steps.createNode("5. Click on Export with Audit as CSV");
			ClickElement.clickById(getDriver(), "export-audit-csv");
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
		//	DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
		//	Date date1 = new Date();
		//	String date= dateFormat.format(date1);
			Thread.sleep(1500);

		//	SalmonellaLog fr= new SalmonellaLog();
			File newfile = getTheNewestFile(fileDownloadPath, "csv");
			String filename= newfile.getName();
			//System.out.println("Latest CSV file is = "+filename);
			Assert.assertTrue(filename.startsWith(flockPlacementAuditFileName));
			test.pass("CSV file downloaded successfully");
			results.createNode("CSV file downloads successfully");
			saveResult(ITestResult.SUCCESS, null);

			File file = new File(fileDownloadPath+"\\"+filename); 
			if(file.delete())
				System.out.println("CSV Audit file deleted");
		}
		catch(AssertionError er) {
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (description="Test Case: Test Flock Template Download",enabled= true, priority = 45) 
	public void TemplateExport() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-FR-107: Verify user can download Flock Template file", "This test case will verify that user download Flock Template file");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Hover mouse towards table");
			steps.createNode("2. Export file button becomes visible");
			steps.createNode("3. Click on the button");


			getDriver().findElement(By.cssSelector("#csv-action img")).click();
			steps.createNode("4. Dropdown cloud pop ups");
			Thread.sleep(1000);
			ClickElement.clickById(getDriver(), "export-data-template");
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			getScreenshot();
			steps.createNode("5. Click on Export Data Template");
			//
			File newfile = getTheNewestFile(fileDownloadPath, "xlsx");
			String filename= newfile.getName();
			Assert.assertTrue(filename.startsWith(flockEOFTemplateFileName), "File did not downloaded with name as FLOCK METADATA");

			FileInputStream fis = new FileInputStream(fileDownloadPath+"\\"+flockEOFTemplateFileName+".xlsx");
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("Data");
			XSSFRow row = sheet.getRow(0);
			int colNum = row.getLastCellNum();
			System.out.println("Total Number of Columns in the excel is : "+colNum);

			Assert.assertEquals(colNum, FlockManagementModel.totalColumnsinFlock);

			test.pass("Sample MetaData template downloaded successfully");
			results.createNode("Sample MetaData template downloaded successfully");
			saveResult(ITestResult.SUCCESS, null);

			File file = new File(fileDownloadPath+"\\"+filename); 
			if(file.delete())
				System.out.println("Template file deleted");
		}
		catch(AssertionError er) {
			test.fail("Sample MetaData downoad failed");
			results.createNode("Sample MetaData failed to download");  
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Sample MetaData downoad failed");
			results.createNode("Sample MetaData failed to download");  	
			saveResult(ITestResult.FAILURE, ex);
		}
	}

	@AfterTest
	public static void endreport() {
		extent.flush();
	}
}

