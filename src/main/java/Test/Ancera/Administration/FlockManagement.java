package Test.Ancera.Administration;

import java.util.Arrays;
import java.util.List;

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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Models.FlockManagementModel;
import Models.ProgramManagementModel;
import Test.Ancera.ClickElement;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
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
	 

	@Test (priority = 25, enabled = true) 
	public void CreateProgram() throws InterruptedException, IOException, SQLException {
		test = extent.createTest("AN-FR-98: Verify user can create Program");
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

		if (Constants.url.contains("qa") || Constants.url.contains("dev")) {
			ResultSet getComplexNameResults = getStmt().executeQuery(Queries.getComplexName);
			while (getComplexNameResults.next()) {
				String complexName = getComplexNameResults.getString("siteName");
				System.out.println("Complex Name: "+complexName);
				click(programComplexList);
				type(programComplexSearch, complexName);
			}
		}
		if (Constants.url.contains("uat")) {
			driver.findElement(programComplexSearch).sendKeys(ProgramManagementModel.ComplexNameUAT);
		}
		
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		click(clickSearchItemFromHierarchy);

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
		//	getStmt().close();
	}

	
	
	@Test
	public void edit() throws InterruptedException, IOException {
		driver.get(url_flockManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		openFlockAudit();
	}
	

	@Test (enabled= true, priority = 26) 
	public void CreateFlock() throws InterruptedException, IOException, SQLException {
		try {
			test = extent.createTest("AN-FR-98: Verify user can create Flock");

			SoftAssert softAssert = new SoftAssert();

			driver.get(url_flockManagement);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			String PreResultsCount = getText(By.id(ResultsCount));

			click(flockCreateButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			if (Constants.url.contains("qa") || Constants.url.contains("dev")) {
				ResultSet getFarmNameResults = getStmt().executeQuery(Queries.getFarmName);
				while (getFarmNameResults.next()) {
					String farmName = getFarmNameResults.getString("siteName");
					System.out.println("Farm Name: "+farmName);
					click(flockFarmDropdownExpand);
					type(flockFarmDropdownSearch, farmName);

				}
			}
			
			if (Constants.url.contains("uat")) {
				driver.findElement(programComplexSearch).sendKeys(ProgramManagementModel.FarmNameUAT);
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
				driver.findElement(flockIntegratorFlockID).sendKeys(Keys.ENTER);
			}


			click(flockBirdSizeInput);
			List<WebElement> birdSizeDropdownValues = driver.findElements(flockBirdSizeDropDownOptions);
			softAssert.assertEquals(birdSizeDropdownValues.get(0).getText(), "Small");
			softAssert.assertEquals(birdSizeDropdownValues.get(1).getText(), "Medium");
			softAssert.assertEquals(birdSizeDropdownValues.get(2).getText(), "Large");
			softAssert.assertEquals(birdSizeDropdownValues.get(3).getText(), "Pullets");
			softAssert.assertEquals(birdSizeDropdownValues.get(4).getText(), "Broilers");


			type(flockBirdSizeInput, FlockManagementModel.flockBirdSize);
			driver.findElement(flockBirdSizeInput).sendKeys(Keys.ENTER);

			click(flockBirdSexInput);
			List<WebElement> birdSexDropdownValues = driver.findElements(flockBirdSexDropDownOptions);
			softAssert.assertEquals(birdSexDropdownValues.get(0).getText(), "Male");
			softAssert.assertEquals(birdSexDropdownValues.get(1).getText(), "Female");
			softAssert.assertEquals(birdSexDropdownValues.get(2).getText(), "Mixed");
			enterKey(flockBirdSexInput);

			click(flockMarketingProgramInput);
			List<WebElement> flockMarketingProgramdropdownValues = driver.findElements(flockMarketingProgramDropDownOptions);
			softAssert.assertEquals(flockMarketingProgramdropdownValues.get(0).getText(), "Conventional");
			softAssert.assertEquals(flockMarketingProgramdropdownValues.get(1).getText(), "No Human Antibiotics");
			softAssert.assertEquals(flockMarketingProgramdropdownValues.get(2).getText(), "No Antibiotics Ever");
			softAssert.assertEquals(flockMarketingProgramdropdownValues.get(3).getText(), "Organic");
			softAssert.assertEquals(flockMarketingProgramdropdownValues.get(4).getText(), "Pastured");
			softAssert.assertEquals(flockMarketingProgramdropdownValues.get(5).getText(), "Other");
			enterKey(flockMarketingProgramInput);

			scroll(flockPlacementDateCalendar);
			click(flockPlacementDateCalendar);

			List<WebElement> list = driver.findElements(By.cssSelector(".dp-current-day"));
			scroll(By.xpath("//label[text() = 'Flock Information']"));
			Thread.sleep(1000);	

			list.get(2).click();
			Thread.sleep(1000);

			click(flockHousePlacedDropdownExpand);
			Thread.sleep(1000);
			
			if (Constants.url.contains("qa") || Constants.url.contains("dev")) {
			ResultSet getHouseNameResults = getStmt().executeQuery(Queries.getFarmHousesCount);
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

			waitElementClickable(popupSaveButton);
			Thread.sleep(500);
			click(popupSaveButton);
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
			Thread.sleep(5000);
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Data saved successfully.");

			String PostResultsCount = getText(By.id(ResultsCount));
			softAssert.assertEquals(Integer.parseInt(PostResultsCount), Integer.parseInt(PreResultsCount)+1);

			openFlockAudit();
			softAssert.assertEquals(size(auditGetRowCount), 3, "Audit Log not displaying a row for flock creation");
			softAssert.assertEquals(getText(auditActionRow1), "Created", "Audit Log not displaying a row for with Action created");

			softAssert.assertAll();

			test.pass("Flock was created successfully");
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
		//	getStmt().close();
	}


	@Test (description = "IE-3595", enabled= true, priority = 27) 
	public void DuplicateFlockWithSameSiteAndPlacementDate() throws InterruptedException, IOException, SQLException {
		try {
			test = extent.createTest("AN-FR-99: Verify that Flock cannot be duplicated with same Site ID and Placement Date.");
			SoftAssert softAssert = new SoftAssert();

			driver.get(url_flockManagement);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			click(flockCreateButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			
			if (Constants.url.contains("qa") || Constants.url.contains("dev")) {
				ResultSet getFarmNameResults = getStmt().executeQuery(Queries.getFarmName);
				while (getFarmNameResults.next()) {
					String farmName = getFarmNameResults.getString("siteName");
					click(flockFarmDropdownExpand);
					type(flockFarmDropdownSearch, farmName);

				}		
			}

			if (Constants.url.contains("uat")) {
				driver.findElement(programComplexSearch).sendKeys(ProgramManagementModel.FarmNameUAT);
			}
			
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			click(By.cssSelector("label b"));
			
			type(flockBirdSizeInput, FlockManagementModel.flockBirdSize);
			driver.findElement(flockBirdSizeInput).sendKeys(Keys.ENTER);


			scroll(flockPlacementDateCalendar);
			click(flockPlacementDateCalendar);
			//	scroll(By.xpath("//*[@id=\"manage-program\"]/div[2]/app-placement-log/div[2]/app-popup-component/div/div/div/div[3]/app-add-editflock-management/form/div/div/div[2]/div/div[1]/section/div[4]/div[1]/div[1]/app-date-select-box/div/div[2]/div/dp-date-picker/div[2]/div/dp-day-calendar/div/div/div[2]/button[3]"));
			Thread.sleep(1000);
			//	DateUtil.clickDay("01");
			click(By.xpath("//*[@id=\"manage-program\"]/div[2]/app-placement-log/div[2]/app-popup-component/div/div/div/div[3]/app-add-editflock-management/form/div/div/div[2]/div/div[1]/section/div[4]/div[1]/div[1]/app-date-select-box/div/div[2]/div/dp-date-picker/div[2]/div/dp-day-calendar/div/div/div[2]/button[3]"));


			click(flockHousePlacedDropdownExpand);
			Thread.sleep(1000);
			click(By.xpath("//*[text() = 'Select All']"));
			click(flockHousePlacedDropdownExpand);

			//			scroll(flockAddNewProgram);
			//			click(flockPlacementDateCalendar);
			//			Thread.sleep(700);
			//			DateUtil.clickDay("01");

			waitElementVisible(alertMessage);
			Thread.sleep(1000);
			softAssert.assertTrue(driver.findElement(alertMessage).getText().contains("already exists at"));
			softAssert.assertAll();

			test.pass("Flock was created successfully");
			test.addScreenCaptureFromPath(getScreenshot("Flock Registration", FlockManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, FlockManagementReportPath, null);	
		}
		catch(AssertionError er) {
			test.fail("Flock failed to create");
			saveResultNew(ITestResult.FAILURE, FlockManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Flock failed to create");
			saveResultNew(ITestResult.FAILURE, FlockManagementReportPath, ex);
		}
		//	getStmt().close();
	}


	@Test (enabled= true, priority =28) 
	public void EditFlock() throws InterruptedException, IOException {
		try {
			test = extent.createTest("Verify user can edit flock");
			driver.get(url_flockManagement);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			SoftAssert softAssert = new SoftAssert();

			openFlockAudit();
			int getRowsPre = size(flockAuditRowCount);
			Thread.sleep(1000);
			click(popupCloseButton);

			openEditFlock();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			//	softAssert.assertEquals(size(flockAddNewFlockButton), 0, "Add new Flock button is visible on Edit Flock popup");

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

			softAssert.assertEquals(getRowsPost, getRowsPre);
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


	@Test(enabled= true, priority =29)
	public void GetPlacementHouses() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-FR-99: Verify House Placement popup from log and audit view");
			SoftAssert softAssert = new SoftAssert();
			driver.get(url_flockManagement);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);

			String b = getText(By.cssSelector("tr:nth-child(1) #col-8 label[title]:nth-child(2)"));

			click(By.cssSelector("tr:nth-child(1) #col-8"));
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
			test.addScreenCaptureFromPath(getScreenshot("Flock Registration", FlockManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, FlockManagementReportPath, null);	
		}
		catch(AssertionError er) {
			test.fail("House Placement log not displaying all houses");
			saveResultNew(ITestResult.FAILURE, FlockManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("House Placement log not displaying all houses");
			saveResultNew(ITestResult.FAILURE, FlockManagementReportPath, ex);
		}
	}



	@Test (enabled= true, priority =30) 
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
			
			if (Constants.url.contains("qa") || Constants.url.contains("dev")) {
				ResultSet getComplexNameResults = getStmt().executeQuery(Queries.getComplexName);
				while (getComplexNameResults.next()) {
					String complexName = getComplexNameResults.getString("siteName");
					System.out.println("Complex Name: "+complexName);
					type(By.id("complexName_search-input"), complexName);
					waitElementInvisible(loading_cursor);
					Thread.sleep(2000);
					click(By.id("complexName_cust-cb-lst-txt_"+complexName));		
				}
			}
			if (Constants.url.contains("uat")) {
				driver.findElement(programComplexSearch).sendKeys(ProgramManagementModel.ComplexNameUAT);
				waitElementInvisible(loading_cursor);
				Thread.sleep(2000);
				click(By.id("complexName_cust-cb-lst-txt_"+ProgramManagementModel.ComplexNameUAT));	
			}
			
		
			click(By.id("complexName_apply"));
			
			waitElementInvisible(loading_cursor);
			//		softAssert.assertEquals(size(By.cssSelector("#"+flockPlacementTable+" #"+flockInlineButtonTooltip)), 1, "Tooltip is not applied on in line edit button");
			click(By.id(flockInlineButton));
			waitElementInvisible(loading_cursor);

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
			type(flockInlineProgramName, FlockManagementModel.flockProgramName);
			enterKey(flockInlineProgramName);
			type(flockDoseInput, "1.52");

			waitElementVisible(flockAdministrationMethod);
			Thread.sleep(1000);	
			type(flockAdministrationMethod, FlockManagementModel.flockProgramAdminMethod);

			if (size(clickAddNewDropdown) != 0) {
				click(clickAddNewDropdown);
			}
			else {
				click(By.xpath("//*[text()='"+FlockManagementModel.flockProgramAdminMethod+"']"));		
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


	@Test (description = "IE-4153", enabled = true, priority =31) 
	public void VerifyUserSitesinFarmDropDown() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-FR-105: Verify that all sites assigned to user are displayed in Flock Farm dropdown");

			openEditUserPopup(login_email);
			click(popupNextButton);
			Thread.sleep(500);
			click(popupNextButton);
			Thread.sleep(500);

			int collectionSitesSize = 0;
			for (int i=1;i<=driver.findElements(By.cssSelector(".site-tree-card")).size();i++) {
				if (!driver.findElement(By.xpath("//*[@id=\"select-sites\"]//div["+i+"]/div/p[2]")).getText().equals("Collection Sites: 0")) {
					collectionSitesSize = collectionSitesSize+1;

					if (i == size(By.cssSelector(".site-tree-card"))) {
						driver.get(url_flockManagement);
						waitElementInvisible(loading_cursor);
						Thread.sleep(1000);

						click(flockCreateButton);
						waitElementInvisible(loading_cursor);
						waitElementClickable(flockFarmDropdownExpand);
						click(flockFarmDropdownExpand);
						int sitesCountFarmDropdown = size(flockFarmDropdownGetAllSites);

						Assert.assertEquals(sitesCountFarmDropdown, collectionSitesSize);
						test.pass("Only those sites appeared  which are assigned to user successfully");
						test.addScreenCaptureFromPath(getScreenshot("Flock Management", FlockManagementReportPath));
						saveResultNew(ITestResult.SUCCESS, FlockManagementReportPath, null);	
					}
				}
			}
		}
		catch(AssertionError er) {
			test.fail("Sites not appeared  which are assigned to user");
			saveResultNew(ITestResult.FAILURE, FlockManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Sites not appeared  which are assigned to user");
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


	@Test(priority= 42)
	public void ExportCSVFeed() throws InterruptedException, IOException {
		driver.get(url_flockManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		CSVExport1("Flock Management", FlockManagementReportPath, flockPlacementCSVFileName, flockPlacementTable, 2, 0);
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
		//	DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
		//	Date date1 = new Date();
		//	String date= dateFormat.format(date1);
			Thread.sleep(1500);

			SalmonellaLog fr= new SalmonellaLog();
			File newfile = fr.getTheNewestFile(fileDownloadPath, "csv");
			String filename= newfile.getName();
			//System.out.println("Latest CSV file is = "+filename);
			Assert.assertTrue(filename.startsWith(flockPlacementAuditFileName));
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


	@Test (description="Test Case: Test Flock Template Download",enabled= true, priority = 45) 
	public void TemplateExport() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-FR-107: Verify user can download Flock Template file", "This test case will verify that user download Flock Template file");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

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

//.overlay-loader
