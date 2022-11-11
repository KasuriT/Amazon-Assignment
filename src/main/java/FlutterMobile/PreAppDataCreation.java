package FlutterMobile;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Models.ComplexConfigModel;
import Models.FlockManagementModel;
import Models.ProgramManagementModel;
import Models.ReportFilters;
import Test.Ancera.ClickElement;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.DB_Config;
import Test.Ancera.DateUtil;
import Test.Ancera.Queries;
import Test.Ancera.Administration.OrganizationManagement;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static Test.Ancera.Test_Variables.*;
import static Test.Ancera.Helper.*;
import static Test.Ancera.Test_Elements.*;
import static Test.Ancera.Test_Functions.*;
import static Test.Ancera.Constants.*;

public class PreAppDataCreation extends DB_Config {

	String name = "none";

	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		spark = new ExtentSparkReporter("target/Reports/Pre_Flutter_Mobile"+date+".html");
		spark.config().setReportName("Pre Flutter Mobile Test Report"); 
		config();
		ConfigureLogin.login();
		DB_Config.test();
	}


	@Test (enabled = false, priority = 1) 
	public void CreateOrganization() throws InterruptedException, IOException {
		OrganizationManagement.CreateOrganization(ComplexConfigModel.organizationName);
	}


	@Test (enabled= false, priority= 2) 
	public void CreateOrganizationSitesHierarchy() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-31-38: Verify Complete Organization Site Hierarchy", "This test case will verify complete site hierarchy");
			steps = test.createNode(Scenario.class, Steps);
			SoftAssert softAssert = new SoftAssert();

			driver.get(url_organization);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1500);

			for (int i=1;i<driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+orgNameCol+" label")).getText().equals(ComplexConfigModel.organizationName)) {
					driver.findElement(By.id("edit-orgn-sites-"+i)).click();
					waitElementInvisible(loading_cursor);
					break;
				}
			}

			Thread.sleep(1000);	

			driver.findElement(orgAddSite1).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			driver.findElement(orgSiteTypeInput).click();
			String regionType = driver.findElement(orgSiteTypeDropDownValue).getText();	
			softAssert.assertEquals(regionType, "Region");
			driver.findElement(orgSiteNameInput).sendKeys("Test Region");
			driver.findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage));
			Thread.sleep(1000);
			steps.createNode("4. Verify Region Site can be saved");
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "New site created.");

			steps.createNode("5. Click on + icon to create new site and verify Site Type is Sub Region");
			driver.findElement(orgAddSite2).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			driver.findElement(orgSiteTypeInput).click();
			String subregionType = driver.findElement(orgSiteTypeDropDownValue).getText();
			softAssert.assertEquals(subregionType, "Sub Region");
			driver.findElement(orgSiteNameInput).sendKeys("Test Sub Region");
			driver.findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage));
			Thread.sleep(1000);
			steps.createNode("6. Verify Sub Region Site can be saved");
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "New site created.");

			steps.createNode("7. Click on + icon to create new site and verify Site Type as Complex, Processing PLant, Testing Lab");
			driver.findElement(orgAddSite3).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			driver.findElement(orgSiteTypeInputChild).click();
			Thread.sleep(1000);

			driver.findElement(By.cssSelector("div .ng-option:nth-child(1)")).click();
			driver.findElement(orgSiteNameInput).sendKeys(ComplexConfigModel.complexName);
			driver.findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage));
			Thread.sleep(1000);
			steps.createNode("8. Verify Complex Site can be saved");
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "New site created.");

			steps.createNode("9. Click on + icon to create new site and verify Site Type as Farm");
			driver.findElement(orgAddSite4).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			driver.findElement(orgSiteTypeInputChild).click();
			String farmType = driver.findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();	
			softAssert.assertEquals(farmType, "Farm");
			driver.findElement(orgSiteNameInput).sendKeys(ComplexConfigModel.organizationFarm1Name);
			driver.findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);
			steps.createNode("10. Verify Farm Site can be saved");
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "New site created.");

			steps.createNode("11. Create House 1");
			driver.findElement(orgAddSite5).click();
			Thread.sleep(2000);
			driver.findElement(orgSiteTypeInputChild).click();
			String HouseType = driver.findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();	
			softAssert.assertEquals(HouseType, "House");
			driver.findElement(orgSiteNameInput).sendKeys(ComplexConfigModel.organizationHouse1Name);
			driver.findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage));
			Thread.sleep(2000);
			steps.createNode("12. Verify House Site can be saved");
			softAssert.assertEquals(driver.findElement(alertMessage).getText(), "New site created.");

			steps.createNode("13. Create house 2");
			driver.findElement(orgAddSite5).click();
			Thread.sleep(2000);
			driver.findElement(orgSiteNameInput).sendKeys(ComplexConfigModel.organizationHouse2Name);
			driver.findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage));
			Thread.sleep(2000);

			steps.createNode("14. Create house 3");
			driver.findElement(orgAddSite5).click();
			Thread.sleep(2000);
			driver.findElement(orgSiteNameInput).sendKeys(ComplexConfigModel.organizationHouse3Name);
			driver.findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage));
			Thread.sleep(1000);

			steps.createNode("15. Create house 4");
			driver.findElement(orgAddSite5).click();
			Thread.sleep(2000);
			driver.findElement(orgSiteNameInput).sendKeys(ComplexConfigModel.organizationHouse4Name);
			driver.findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage));
			Thread.sleep(1000);

			steps.createNode("16. Create house 5");
			driver.findElement(orgAddSite5).click();
			Thread.sleep(2000);
			driver.findElement(orgSiteNameInput).sendKeys(ComplexConfigModel.organizationHouse5Name);
			driver.findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage));
			Thread.sleep(1000);

			softAssert.assertAll();
			test.pass("Site heirarchy verified successfully");
			results.createNode("Site heirarchy verified successfully");
			test.addScreenCaptureFromPath(getScreenshot("Organization Management", OrgManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, OrgManagementReportPath, null);	
		}catch(AssertionError er){
			test.fail("Site heirarchy failed to verify successfully");
			results.createNode("Site heirarchy failed to verify successfully");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Site heirarchy failed to verify successfully");
			results.createNode("Site heirarchy failed to verify successfully");
			saveResultNew(ITestResult.FAILURE, OrgManagementReportPath, ex);
		}
	}	


	@Test (enabled= false, priority= 3) 
	public void AssignTestingSites() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-UM-14: Verify Sites column displays Active after assigning All Testing Sites to the user");

			openEditUserPopup(login_email);
			click(popupNextButton);
			click(popupNextButton);
			Thread.sleep(750);
			click(userSitesButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);
			type(userSitesSearch, ComplexConfigModel.organizationName);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);
			click(By.xpath("//*[text()='"+ComplexConfigModel.organizationName+"']"));
			Thread.sleep(1000);
			click(userSitesSaveButton);
			Thread.sleep(1000);
			getScreenshot();
			click(popupSaveButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1500);

			Assert.assertEquals(getText(alertMessage), "User details updated.");	
			test.pass("Site Assigned to user successfully");
			results.createNode("Site Assigned to user successfully");
			test.addScreenCaptureFromPath(getScreenshot("Program Management", PreFlutterMobileReportPath));
			saveResultNew(ITestResult.SUCCESS, ProgramManagementReportPath, null);
		}
		catch(AssertionError er) {
			test.fail("Site failed to assigned to user successfully");
			results.createNode("Site failed to assigned to user successfully");  
			saveResultNew(ITestResult.FAILURE, PreFlutterMobileReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Site failed to assigned to user successfully");
			results.createNode("Site failed to assigned to user successfully");  	
			saveResultNew(ITestResult.FAILURE, PreFlutterMobileReportPath, ex);
		}
	}


	
	@SuppressWarnings("unused")
	@Test (enabled= true, priority= 4) 
	public void CreateProgramVaccine() throws InterruptedException, IOException {
		try {		
			test = extent.createTest("AN-Program-02: Verify that user is able to create new Vaccine program", "This testcase will verify that user is able to create new program");

			driver.get(url_programManagement);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);
			SoftAssert softAssert = new SoftAssert();

			click(programVaccineProgramTab);
			waitElementInvisible(loading_cursor);

			for (int j=1;j<driver.findElements(By.id("col-0-vaccine")).size();j++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+j+") #col-0-vaccine label")).getText().equals(ComplexConfigModel.vaccineName)) {
					test.skip("Program already created");
					results.createNode("Program already created");
					test.addScreenCaptureFromPath(getScreenshot("Program Management", ProgramManagementReportPath));
					saveResultNew(ITestResult.SKIP, ProgramManagementReportPath, null);
					break;
				}

				else {
					driver.findElement(By.xpath("//*[text()=' Register New Program']")).click();
					waitElementInvisible(loading_cursor);
					//Program Name
					driver.findElement(programName).sendKeys(ComplexConfigModel.vaccineName);

					//Target Pathogen
					driver.findElement(programTargetPathogen).click();
					Thread.sleep(500);
					driver.findElement(programTargetPathogen).sendKeys(Keys.ENTER);
					Thread.sleep(500);

					//Program Type
					driver.findElement(programProgramType).sendKeys("Vaccine");
					Thread.sleep(500);	
					driver.findElement(programProgramType).sendKeys(Keys.ENTER);

					//Supplier
					driver.findElement(programSupplier).sendKeys(ProgramManagementModel.SupplierName);
					Thread.sleep(500);
					if (driver.findElements(By.xpath("//*[text()='Add New + ']")).size() != 0) {
						driver.findElement(By.xpath("//*[text()='Add New + ']")).click();
					}
					else {
						driver.findElement(By.cssSelector(".list-item")).click();		
					}
					Thread.sleep(500);

					//Complex
					click(programComplexList);
					type(programComplexSearch, ComplexConfigModel.complexName);
					waitElementInvisible(loading_cursor);
					Thread.sleep(1000);
					click(clickSearchItemFromHierarchy);
					Thread.sleep(1000);
					
					//Start Date
				System.out.println(1);
					click(programStartDateIcon);
					System.out.println(2);	
					waitElementInvisible(loading_cursor);
					Thread.sleep(500);
					WebElement dateWidgetTo = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#startDate .dp-popup"))).get(0);
					List<WebElement> columns1 = dateWidgetTo.findElements(By.tagName("button"));
					System.out.println(3);
					DateUtil.clickGivenDay(columns1, DateUtil.getFirstDay());
					Thread.sleep(500);

					//End Date
					driver.findElement(By.cssSelector("#endDate img")).click();
					waitElementInvisible(loading_cursor);
					Thread.sleep(500);
					WebElement dateWidgetToEnd = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#endDate .dp-popup"))).get(0);
					List<WebElement> columns2 = dateWidgetToEnd.findElements(By.tagName("button"));
					DateUtil.clickGivenDay(columns2, DateUtil.getDay("30"));
					Thread.sleep(700);

					//Program Description
					driver.findElement(programDescription).sendKeys(ProgramManagementModel.DescriptionName);

					String NoApplicationFlock = "2";
					driver.findElement(programNoApplicationFlock).sendKeys(NoApplicationFlock);
					Thread.sleep(500);

					for(int i=1; i<=Integer.parseInt(NoApplicationFlock); i++) {
						driver.findElement(By.id(programDaysApplicationFlock+"-"+i)).sendKeys(""+i);
					}

					getScreenshot();
					click(popupSaveButtonXpath);
					waitElementVisible(alertMessage);
					softAssert.assertEquals(driver.findElement(alertMessage).getText(), "New program has been created successfully"); 
					test.pass("New Program created successfully");
					results.createNode("New Program created successfully");
					getScreenshot();
					saveResultNew(ITestResult.SUCCESS, ProgramManagementReportPath, null);
					break;
				}
			}

		}catch(AssertionError er) {
			test.fail("New Program failed to create");
			results.createNode("New Program failed to create");
			saveResultNew(ITestResult.FAILURE, ProgramManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			test.fail("New Program failed to create");
			results.createNode("New Program failed to create");
			saveResultNew(ITestResult.FAILURE, ProgramManagementReportPath, ex);
		}
	}


	@SuppressWarnings("unused")
	@Test (enabled= true, priority= 5) 
	public void CreateProgramFeed() throws InterruptedException, IOException {
		try {		
			test = extent.createTest("AN-Program-05: Verify that user is able to create new Feed program", "This testcase will verify that user is able to create new program");

			driver.get(url_programManagement);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1500);

			driver.findElement(programFeedProgramTab).click();
			Thread.sleep(1500);

			for (int j=1;j<driver.findElements(By.id("col-0-feedprogram")).size();j++) {
				if (driver.findElement(By.cssSelector("tr:nth-child("+j+") #col-0-feedprogram label")).getText().equals(ComplexConfigModel.vaccineName)) {
					test.skip("Program already created");
					results.createNode("Program already created");
					test.addScreenCaptureFromPath(getScreenshot("Program Management", ProgramManagementReportPath));
					saveResultNew(ITestResult.SKIP, ProgramManagementReportPath, null);
					break;
				}

				else {

					driver.findElement(programCreateButton).click();
					waitElementInvisible(loading_cursor);
					Thread.sleep(1500);

					driver.findElement(programName).sendKeys(ComplexConfigModel.feedName);

					driver.findElement(programTargetPathogen).click();
					Thread.sleep(1000);
					driver.findElement(programTargetPathogen).sendKeys(Keys.ENTER);

					driver.findElement(programProgramType).sendKeys("Feed");
					Thread.sleep(700);	
					driver.findElement(programProgramType).sendKeys(Keys.ENTER);

					driver.findElement(programSupplier).sendKeys("China");
					Thread.sleep(700);
					if (driver.findElements(By.xpath("//*[text()='Add New + ']")).size() != 0) {
						driver.findElement(By.xpath("//*[text()='Add New + ']")).click();
					}
					else {
						driver.findElement(By.cssSelector(".list-item")).click();		
					}
					Thread.sleep(700);

					driver.findElement(programDescription).sendKeys("Feed Testing Program");

					//Complex
					click(programComplexList);
					type(programComplexSearch, ComplexConfigModel.complexName);
					waitElementInvisible(loading_cursor);
					Thread.sleep(1000);
					click(clickSearchItemFromHierarchy);


					//Start Date
					click(programStartDateIcon);
					waitElementInvisible(loading_cursor);
					Thread.sleep(500);
					WebElement dateWidgetTo = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#startDate .dp-popup"))).get(0);
					List<WebElement> columns1 = dateWidgetTo.findElements(By.tagName("button"));
					DateUtil.clickGivenDay(columns1, DateUtil.getFirstDay());
					Thread.sleep(500);
					

					driver.findElement(programFeedTypeDropdown).click();
					Thread.sleep(1000);	
					driver.findElement(programFeedTypeDropdown).sendKeys(Keys.ENTER);

					driver.findElement(programFlockDayStart).sendKeys("1");

					WebElement EndDay = driver.findElement(programFlockDayStart);
					driver.findElement(with(By.tagName("input")).toRightOf(EndDay)).sendKeys("10");

					WebElement ingredient = driver.findElement(programFeedTypeDropdown);
					driver.findElement(with(By.tagName("input")).below(ingredient)).sendKeys("Sugar");

					WebElement ingredientCategory = driver.findElement(programFlockDayStart);
					driver.findElement(with(By.tagName("input")).below(ingredientCategory)).click();
					List<WebElement> ingredientCategories = driver.findElements(By.cssSelector(".ng-option-label"));
					ingredientCategories.get(0).click();

					test.addScreenCaptureFromPath(getScreenshot("Program Management", ProgramManagementReportPath));
					driver.findElement(By.xpath(("//button[text() = ' Submit ']"))).click();
					waitElementInvisible(loading_cursor);
					Thread.sleep(2000);
					Assert.assertEquals(driver.findElement(alertMessage).getText(), "New program has been created successfully"); 
					test.pass("New Program created successfully");
					results.createNode("New Program created successfully");
					test.addScreenCaptureFromPath(getScreenshot("Program Management", ProgramManagementReportPath));
					saveResultNew(ITestResult.SUCCESS, ProgramManagementReportPath, null);
					break;
				}
			}


		}catch(AssertionError er) {
			test.fail("New Program failed to create");
			results.createNode("New Program failed to create");
			saveResultNew(ITestResult.FAILURE, ProgramManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			test.fail("New Program failed to create");
			results.createNode("New Program failed to create");
			saveResultNew(ITestResult.FAILURE, ProgramManagementReportPath, ex);
		}
	}



	@Test (description="Test Case: Create complex Configurations",enabled= true, priority = 6) 
	public void CreateComplexConfig() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-Complex: Create Complex Configuration");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Complex OPG Range Config; Screen opens");
			steps.createNode("1. Create Configuration");

			driver.get(url_complexConfig);;
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			lstCreateComplexConfig = ComplexConfigModel.CreateConfig();

			driver.findElement(complexCreateButton).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			driver.findElement(complexSelectComplexDropdown).click();
			Thread.sleep(1000);
			driver.findElement(complexSearchComplex).sendKeys(ComplexConfigModel.complexName);
			Thread.sleep(1000);
			driver.findElement(complexSelectComplexSite).click();

			driver.findElement(complexSelectProgramType).sendKeys("Vaccine");
			Thread.sleep(2000);
			driver.findElement(complexSelectProgramType).sendKeys(Keys.ENTER);

			driver.findElement(complexSelectProgramId).sendKeys(ComplexConfigModel.vaccineName);
			Thread.sleep(1000);
			driver.findElement(complexSelectProgramId).sendKeys(Keys.ENTER);

			By addProgramButton = RelativeLocator.with(By.tagName("button")).toRightOf(complexSelectProgramId);
			driver.findElement(addProgramButton).click();

			driver.findElement(complexSelectProgramType).sendKeys("Feed");
			Thread.sleep(1000);
			driver.findElement(complexSelectProgramType).sendKeys(Keys.ENTER);

			driver.findElement(complexSelectProgramId).sendKeys(ComplexConfigModel.feedName);
			Thread.sleep(1000);
			driver.findElement(complexSelectProgramId).sendKeys(Keys.ENTER);

			driver.findElement(addProgramButton).click();

			for (ComplexConfigModel objModel : lstCreateComplexConfig) { 	

				driver.findElement(complexOPGType).click();
				Thread.sleep(1000);
				List<WebElement> OPGTypeSelect = driver.findElements(complexSelectValueFromDropdown);
				OPGTypeSelect.get(0).click();

				driver.findElement(complexBirdSize).click();
				Thread.sleep(1000);
				List<WebElement> BirdSizeSelect = driver.findElements(complexSelectValueFromDropdown);
				BirdSizeSelect.get(0).click();

				driver.findElement(complexSamplingInterval).click();
				Thread.sleep(1000);
				List<WebElement> SamplingIntervalSelect = driver.findElements(complexSelectValueFromDropdown);
				SamplingIntervalSelect.get(0).click();

				driver.findElement(complexComplexThreshold).sendKeys(objModel.ComplexThreshold);
				driver.findElement(complexHouseThreshold).sendKeys(objModel.HouseThreshold);
				driver.findElement(complexLowerLimit).sendKeys(objModel.LowerLimit);
				driver.findElement(complexUpperLimit).sendKeys(objModel.UpperLimit);
				Thread.sleep(1000);
				By addConfigButton = RelativeLocator.with(By.tagName("button")).toRightOf(complexUpperLimit);
				driver.findElement(addConfigButton).click();
			}

			driver.findElement(complexSubmitButton).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			Assert.assertNotEquals(driver.findElement(alertMessage).getText(), "Complex cycling configuration details saved");
			test.pass("Complex cycling configuration details saved successfully");
			results.createNode("Complex cycling configuration details saved successfully");
			test.addScreenCaptureFromPath(getScreenshot("Salmonella Log", SalmonellaReportPath));
			saveResultNew(ITestResult.SUCCESS, PreFlutterMobileReportPath, null);
		}
		catch(AssertionError er) {
			test.fail("Complex cycling configuration details failed to save");
			results.createNode("Complex cycling configuration details failed to save");
			saveResultNew(ITestResult.FAILURE, PreFlutterMobileReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Complex cycling configuration details failed to save");
			results.createNode("Complex cycling configuration details failed to save");
			saveResultNew(ITestResult.FAILURE, PreFlutterMobileReportPath, ex);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	@SuppressWarnings({ "unchecked", "unused" })
	@Test (enabled= true, priority= 8) 
	public void Ingestion_Flock() throws InterruptedException, IOException, SQLException	{
		lstComplexConfig = ComplexConfigModel.FillDataCocci();

		for (ComplexConfigModel objModel : lstComplexConfig) {

			if(objModel.createFlock) {
				try {
					test = extent.createTest("AN-Flock: Verify user can create Flock", "This test case will verify that user can crate flock");
					driver.get(url_flockManagement);
					waitElementInvisible(loading_cursor);
					Thread.sleep(2000);
					
					driver.findElement(flockCreateButton).click();
					waitElementInvisible(loading_cursor);	
					Thread.sleep(2000);

					click(flockFarmDropdownExpand);
					type(flockFarmDropdownSearch, ComplexConfigModel.organizationFarm1Name);
					waitElementInvisible(loading_cursor);
					waitElementClickable(By.cssSelector("label b"));
					Thread.sleep(2000);
					click(By.cssSelector("label b"));
					
					type(flockIntegratorFlockID, ComplexConfigModel.flockIntegratorID);

					if (size(flockIntegratorFlockAddNew) != 0) {
						click(flockIntegratorFlockAddNew);
					}
					else {
						driver.findElement(flockIntegratorFlockID).sendKeys(Keys.ENTER);
					}
					
					click(flockBirdSizeInput);
					List<WebElement> birdSizeList = driver.findElements(flockBirdSizeDropDownOptions);
					birdSizeList.get(objModel.birdSize).click();
					
					scroll(flockPlacementDateCalendar);
					click(flockPlacementDateCalendar);

			//		List<WebElement> list = driver.findElements(By.cssSelector(".dp-current-day"));
			//		List<WebElement> list = driver.findElements(By.xpath("//*[text()='01']"));
					scroll(By.xpath("//label[text() = 'Flock Information']"));
					Thread.sleep(1000);	

					click(By.xpath("//div/div[1]/section/div[4]/div[1]/div[1]/app-date-select-box/div/div[2]/div/dp-date-picker/div[2]/div/dp-day-calendar/div/div/div[2]/button[3]"));
			//		list.get(0).click();
					Thread.sleep(1000);
								
					
					try {
						click(flockHousePlacedDropdownExpand);
					}
					catch(ElementNotInteractableException ex) {
						click(flockHousePlacedDropdownExpand);
					}

					Thread.sleep(1000);

					for(int i = 0; i<objModel.LstHouses.size(); i++) {
						scroll(By.xpath("//*[text() = '"+objModel.LstHouses.get(i)+"']"));
						driver.findElement(By.xpath("//*[text() = '"+objModel.LstHouses.get(i)+"']")).click();

					}

					scroll(flockAddNewProgram);
					ClickElement.clickByXpath(driver, "//*[text() = 'Add New Program']");
				//	click(flockAddNewProgram);
					waitElementInvisible(loading_cursor);
					Thread.sleep(1000);
					type(flockAddNewProgramTypeInput, objModel.programType);
					enterKey(flockAddNewProgramTypeInput);
					Thread.sleep(1000);
	
					type(flockAddNewProgramNameInput, objModel.program);
					enterKey(flockAddNewProgramNameInput);
					Thread.sleep(1000);

					if (objModel.programType.equals("Vaccine")) {
						type(flockCMSAdminInput, "CSMAdminTest");
						if(size(clickAddNewDropdown) != 0) {
							click(clickAddNewDropdown);
						}
						else {
							click(By.xpath("//*[text() = 'CSMAdminTest']"));
						}
					}
					
					waitElementClickable(popupSaveButton);
					Thread.sleep(500);
					click(popupSaveButton);
					waitElementInvisible(loading_cursor);
					waitElementVisible(alertMessage);
					Thread.sleep(5000);
					Assert.assertEquals(driver.findElement(alertMessage).getText(), "Data saved successfully.");
					System.out.println("Flock created successfully");

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

			}


			test = extent.createTest("AN-API_Login-01: Verify Login API", "This test case will run login api and verify that token is generated or not");

			SoftAssert softAssert = new SoftAssert();

			for (ReportFilters objFilter : objModel.lstFilters) {

				DateFormat dateFormat = new SimpleDateFormat("mm.ss");
				Date date1 = new Date();
				dateFormat.format(date1);

				RequestSpecification request = RestAssured.given();
				request.header("Content-Type", "application/json");
				JSONObject json = new JSONObject();
				json.put("piperid", piperId);
				json.put("password", piperPassword);
				json.put("DISAPIVersion", "14.13");
				request.body(json.toString());
				Response response = request.post(api_login);
				int code = response.getStatusCode();
				Assert.assertEquals(code, 200);

				String data = response.asString();
				System.out.println(data);

				JsonPath jsonPathEvaluator = response.jsonPath();
				String token = jsonPathEvaluator.get("token");		
				Thread.sleep(2000);


				RequestSpecification request_announcement = RestAssured.given();
				request_announcement.header("Content-Type", "application/json");
				request_announcement.header("Authorization", "bearer " +token);

				HttpGet postRequest = new HttpGet(api_announcement);
				postRequest.addHeader("Content-Type", "application/json");
				postRequest.addHeader("Authorization", "Bearer "+token);

				JSONObject json1 = new JSONObject();
				JSONObject json2 = new JSONObject();
				JSONObject json3 = new JSONObject();
				JSONObject json4 = new JSONObject();
				JSONArray list = new JSONArray();

				json1.put("runId", lstApiAnnouncement.get(0));
				json1.put("dateTime", lstApiAnnouncement.get(1));
				json1.put("Piperid",  lstApiAnnouncement.get(2));
				json1.put("MPNCalculationType", lstApiAnnouncement.get(3));
				json2.put("fileName", lstApiAnnouncement.get(4));
				json2.put("checksum", lstApiAnnouncement.get(5));

				list.add(json2);
				json1.put("files", list);

				request_announcement.body(json1.toString());
				Response response1 = request_announcement.post(api_announcement);

				String data1 = response1.asString();
				System.out.println(data1);
				Thread.sleep(2000);

				///////////////////////////////////////////////////////////////////File Upload API////////////////////////////////////////////////////////////////////////////////

				test = extent.createTest("AN-Coccidia-01: Ingest Coccidia run", "This test case will run and verify  ingestion");				


				RequestSpecification request_fileupload = RestAssured.given();
				request_fileupload.header("Content-Type", "application/json");
				request_fileupload.header("Authorization", "bearer " +token);

				HttpGet postRequest1 = new HttpGet(api_FileUpload);
				postRequest1.addHeader("Content-Type", "application/json");
				postRequest1.addHeader("Authorization", "Bearer "+token);

				json3.put("runId", lstCoccidiaIngest.get(0).runId);
				json3.put("checksum", lstCoccidiaIngest.get(0).checksum);
				json3.put("fileName", lstCoccidiaIngest.get(0).fileName);
				json3.put("fileType", lstCoccidiaIngest.get(0).fileType);
				json3.put("file", lstCoccidiaIngest.get(0).file);
				json3.put("fileJson", objModel.fileJson);				
				json3.put("Improc", lstCoccidiaIngest.get(0).improc);
				json3.put("RunMode", "1");
				json3.put("Pathogen", objModel.pathogen);

				request_fileupload.body(json3.toString());
				Response response2 = request_fileupload.post(api_FileUpload);
				String data3 = response2.asString();
				System.out.println(data3);
				Thread.sleep(10000);

				System.out.println("Run ID: "+objModel.SampleID);

				try{
					first:
						for (int x = 0;x<=120;x++) {

							String query2 = "Select count(status) as count from COCCIDA_OUTPUT where Sample_ID = '"+objModel.SampleID+"'";
							//	String query2 = "Select count(status) as count from COCCIDA_OUTPUT where Sample_ID = '20220714-Cocci-10535'";

							ResultSet rs2 = getStmt().executeQuery(query2);

							while (rs2.next()) {
								System.out.println("Count: "+rs2.getString("count"));

								if (rs2.getString("count").equals("12")) {

									driver.get(url_CoccidiaLog);
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-sampleId")));
									Thread.sleep(3000);

									steps.createNode("1. Click on Sample ID to expand the filter");
									ClickElement.clickById(driver, "sampleId_show-filter");			
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
									Thread.sleep(1000);
									driver.findElement(By.id("sampleId_view-all")).click();
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
									Thread.sleep(1000);
									steps.createNode("2. Search for the Sample ID's against which the data is ingested");							

									driver.findElement(By.id("sampleId_search-input")).clear();
									driver.findElement(By.id("sampleId_search-input")).sendKeys(objModel.SampleID);
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
									Thread.sleep(2000);	
									try {
										driver.findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objModel.SampleID)).click();
									}
									catch(Exception ex) {
										Thread.sleep(1000);
										driver.findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objModel.SampleID)).click();
									}
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
									Thread.sleep(800);


									steps.createNode("3. Click on Apply filter button");
									driver.findElement(By.id("sampleId_apply")).click();
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
									Thread.sleep(4000);
									test.addScreenCaptureFromPath(getScreenshot("Coccidia Log", NormalIngestionReportPath));
									String records = driver.findElement(By.id("results-found-count")).getText();

									softAssert.assertEquals(records, "12"); 
									test.addScreenCaptureFromPath(getScreenshot("Coccidia Log", NormalIngestionReportPath));
									test.pass("Run ingested successfully");
									results.createNode("Run ingested successfully");
									saveResultNew(ITestResult.SUCCESS, DataUploadReportPath, null);
									break first;	
								}
								else {
									Thread.sleep(15000);
								}					
							}	


						}
					softAssert.assertAll();	

				}

				catch(Exception ex){
					test.fail("Data failed to verify on uploading Sample Metadata Template");
					results.createNode("Data failed to verify on uploading Sample Metadata Template");
					saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, ex);	
				}

				catch(AssertionError er) {
					test.fail("Ingestion failed");
					results.createNode("Ingestion failed");
					saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, new Exception(er));
				}

				////////////////////////////////////////////////////////////End File Upload//////////////////////////////////////////////////////////////////////

				try {	
					test = extent.createTest("AN-Coccidia-03: Upload Sample MetaData File and verify the data in Report", "This test case will verify the data in report on uploading sample metedata");	
					preconditions = test.createNode(Scenario.class, PreConditions);
					steps = test.createNode(Scenario.class, Steps);
					results = test.createNode(Scenario.class, Results);

					FileInputStream fsIP= new FileInputStream(new File("./Excel/"+fileName_Mobile));
					@SuppressWarnings("resource")
					XSSFWorkbook wb = new XSSFWorkbook(fsIP);
					XSSFSheet worksheet = wb.getSheetAt(0);
					Cell cell = null;

					if (driver.findElement(By.id(ResultsCount)).getText().equals("12")) {
						for (int z=0; z<12; z++) {

							String getResultID = driver.findElement(By.cssSelector("#row-"+z+" #col-"+clResultIDCol+" label")).getText();
							//System.out.println("1: "+getResultID);
							cell=worksheet.getRow(z+1).createCell(metadata_ResultID); 
							cell.setCellValue(getResultID);  

							//							String selectQuerySite = "Select siteUniqueNumber from dbo.Site where sitename = 'TestHouse1_'"+dateYYYYMMDD;
							//							ResultSet rs1 = getStmt().executeQuery(selectQuerySite);
							//							while (rs1.next()) {
							//								System.out.println("Unique Site ID: "+rs1.getString("siteUniqueNumber"));
							//								cell=worksheet.getRow(z+1).createCell(metadata_CollectionSiteID); 
							//								cell.setCellValue(rs1.getString("siteUniqueNumber")); 
							//							}

							String getCollectionSiteID = driver.findElement(By.cssSelector("#row-"+z+" #col-"+clCollectionSiteIDCol+" label")).getText();
							cell=worksheet.getRow(z+1).createCell(metadata_CollectionSiteID); 
							cell.setCellValue(getCollectionSiteID); 

							String getSampleID = driver.findElement(By.cssSelector("#row-"+z+" #col-"+clSampleIDCol+" label")).getText();
							cell=worksheet.getRow(z+1).createCell(metadata_LabSampleID); 
							cell.setCellValue(getSampleID);  

							String selectQuery = "Select unique_Flock_id from dbo.flock_mgmt where integrator_flock_id = '"+ComplexConfigModel.flockIntegratorID+"' and Bird_Size = '"+objModel.birdSizeName+"'";
							ResultSet rs = getStmt().executeQuery(selectQuery);
							while (rs.next()) {
								String flockID = rs.getString("unique_flock_id");
								//System.out.println("Unique Flock ID: "+flockID);
								cell=worksheet.getRow(z+1).createCell(metadata_FlockID); 
								cell.setCellValue(flockID); 
							}

							String getComplex = driver.findElement(By.cssSelector("#row-"+z+" #col-"+clComplexCol+" label")).getText();
							cell=worksheet.getRow(z+1).createCell(metadata_Complex); 
							cell.setCellValue(getComplex); 

							String getFarm = driver.findElement(By.cssSelector("#row-"+z+" #col-"+clFarmCol+" label")).getText();
							cell=worksheet.getRow(z+1).createCell(metadata_Farm); 
							cell.setCellValue(getFarm); 

							String getLane = driver.findElement(By.cssSelector("#row-"+z+" #col-"+clLaneCol+" label")).getText();
							cell=worksheet.getRow(z+1).createCell(metadata_Lane); 
							cell.setCellValue(getLane);  

							String getResultDate = driver.findElement(By.cssSelector("#row-"+z+" #col-"+clDateCol+" label")).getText();
							cell=worksheet.getRow(z+1).createCell(metadata_ResultDate); 
							cell.setCellValue(getResultDate);

							String getresultTime = driver.findElement(By.cssSelector("#row-"+z+" #col-"+clTimeCol+" label")).getText();
							cell=worksheet.getRow(z+1).createCell(metadata_ResultTime); 
							cell.setCellValue(getresultTime); 

							cell=worksheet.getRow(z+1).createCell(metadata_CartridgeID); 
							cell.setCellValue(ComplexConfigModel.cartridgeID); 

							cell=worksheet.getRow(z+1).createCell(metadata_InstrumentID); 
							cell.setCellValue(InstrumentID);

							String getPiperUser = driver.findElement(By.cssSelector("#row-"+z+" #col-"+clPiperUserCol)).getText();
							cell=worksheet.getRow(z+1).createCell(metadata_PiperUser); 
							cell.setCellValue(getPiperUser);

							cell=worksheet.getRow(z+1).createCell(metadata_CollectionDate); 
							cell.setCellValue(objModel.CollectionDatee);

							fsIP.close();
						}
						//	getStmt().close();	

						FileOutputStream output_file =new FileOutputStream(new File("./Excel/"+fileName_Mobile));
						wb.write(output_file);
						output_file.close();  

						driver.get(url_dataUpload);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("OrgnTypeID"))); 
						Thread.sleep(000);
						driver.findElement(By.id("OrgnTypeID")).click();
						driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("Ancera");
						driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						driver.findElement(By.id("DataFormatId")).click();
						driver.findElement(By.cssSelector("#DataFormatId input")).sendKeys("Sample Metadata");
						driver.findElement(By.cssSelector("#DataFormatId input")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						driver.findElement(By.id("file-input")).sendKeys(fileAbsolutePath+"Excel\\"+fileName_Mobile);
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))); 
						Thread.sleep(4000);
						driver.findElement(By.cssSelector(".fa-save")).click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))); 
						Thread.sleep(6000);
						test.addScreenCaptureFromPath(getScreenshot("Coccidia Log", NormalIngestionReportPath));
						Assert.assertEquals(driver.findElement(alertMessage).getText(), fileName_Mobile+" saved successfully.");
						System.out.println("Template created successfully");
						test.pass("Template saved successfully");
						results.createNode("Template saved successfully");
						saveResultNew(ITestResult.SUCCESS, DataUploadReportPath, null);
					}
					else {
						results.createNode("12 records not displaying in table hence file upload method not executed");
						test.skip("12 records not displaying in table hence file upload method not executed");
						results.createNode("12 records not displaying in table hence file upload method not executed");
						saveResultNew(ITestResult.SKIP, DataUploadReportPath, null);
					}
				}
				catch(AssertionError er) {
					test.fail("Data failed to verify on uploading Sample Metadata Template");
					results.createNode("Data failed to verify on uploading Sample Metadata Template");
					saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, new Exception(er));
				}catch(Exception ex){
					test.fail("Data failed to verify on uploading Sample Metadata Template");
					results.createNode("Data failed to verify on uploading Sample Metadata Template");
					saveResultNew(ITestResult.FAILURE, NormalIngestionReportPath, ex);	
				}
				Thread.sleep(2000);	
			}
		}
	//	getStmt().close();	
	}


	@AfterTest
	public static void endreport() {
		extent.flush();
		DB_Config.getStmt();
		DB_Config.setStmt(getStmt());
		//	driver.close();
	}

}
