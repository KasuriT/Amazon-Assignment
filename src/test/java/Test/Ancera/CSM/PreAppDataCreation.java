package Test.Ancera.CSM;

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

import org.aeonbits.owner.ConfigFactory;
import org.apache.http.client.methods.HttpGet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Config.BaseTest;
import Config.ReadPropertyFile;
import MiscFunctions.FrameworkConstants;
import MiscFunctions.Methods;
import MiscFunctions.ClickElement;
import MiscFunctions.DB_Config;
import MiscFunctions.DateUtil;
import Models.ComplexConfigModel;
import Models.ProgramManagementModel;
import Models.ReportFilters;
import PageObjects.CoccidiaLogPage;
import PageObjects.UserManagementPage;
import Test.Ancera.Administration.OrganizationManagement;
import Test.Ancera.Login.LoginTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static PageObjects.OrganizationManagementPage.*;
import static PageObjects.FlockManagementPage.*;
import static PageObjects.ProgramManagementPage.*;
import static PageObjects.CoccidiaLogPage.*;
import static PageObjects.DataTemplateManagementPage.*;
import static PageObjects.ComplexOPGPage.*;
import static PageObjects.BasePage.*;
import static MiscFunctions.Constants.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import static Models.IngestionsModel.*;

public class PreAppDataCreation extends BaseTest {

	String name = "none";

	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		spark = new ExtentSparkReporter("target/Reports/Pre_Flutter_Mobile"+DateUtil.date+".html");
		spark.config().setReportName("Pre Flutter Mobile Test Report"); 
		DB_Config.test();
	}

	@Test
	public void Login() throws InterruptedException, IOException {
		LoginTest.login();
	}

	@Test (enabled = true, priority = 1) 
	public void CreateOrganization() throws InterruptedException, IOException {
		OrganizationManagement org = new OrganizationManagement();
		org.CreateOrganizationFunction(ComplexConfigModel.organizationName);
	}


	@Test (enabled= true, priority= 2) 
	public void CreateOrganizationSitesHierarchy() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-OM-31-38: Verify Complete Organization Site Hierarchy", "This test case will verify complete site hierarchy");
			steps = test.createNode(Scenario.class, Steps);
			SoftAssert softAssert = new SoftAssert();

			getDriver().get(url_organization);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1500);

			for (int i=1;i<getDriver().findElements(By.cssSelector("tr")).size(); i++) {
				if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") #col-"+orgNameCol+" label")).getText().equals(ComplexConfigModel.organizationName)) {
					getDriver().findElement(By.id("edit-orgn-sites-"+i)).click();
					waitElementInvisible(loading_cursor);
					break;
				}
			}

			Thread.sleep(1000);	

			getDriver().findElement(orgAddSite1).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			getDriver().findElement(orgSiteTypeInput).click();
			String regionType = getDriver().findElement(orgSiteTypeDropDownValue).getText();	
			softAssert.assertEquals(regionType, "Region");
			getDriver().findElement(orgSiteNameInput).sendKeys("Test Region");
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
		waitElementVisible(alertMessage);
			Thread.sleep(1000);
			steps.createNode("4. Verify Region Site can be saved");
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.");

			steps.createNode("5. Click on + icon to create new site and verify Site Type is Sub Region");
			getDriver().findElement(orgAddSite2).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			getDriver().findElement(orgSiteTypeInput).click();
			String subregionType = getDriver().findElement(orgSiteTypeDropDownValue).getText();
			softAssert.assertEquals(subregionType, "Sub Region");
			getDriver().findElement(orgSiteNameInput).sendKeys("Test Sub Region");
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			waitElementVisible(alertMessage);
		waitElementVisible(alertMessage);
			Thread.sleep(1000);
			steps.createNode("6. Verify Sub Region Site can be saved");
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.");

			steps.createNode("7. Click on + icon to create new site and verify Site Type as Complex, Processing PLant, Testing Lab");
			getDriver().findElement(orgAddSite3).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			getDriver().findElement(orgSiteTypeInputChild).click();
			Thread.sleep(1000);

			getDriver().findElement(By.cssSelector("div .ng-option:nth-child(1)")).click();
			getDriver().findElement(orgSiteNameInput).sendKeys(ComplexConfigModel.complexName);
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
		waitElementVisible(alertMessage);
			Thread.sleep(1000);
			steps.createNode("8. Verify Complex Site can be saved");
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.");

			steps.createNode("9. Click on + icon to create new site and verify Site Type as Farm");
			getDriver().findElement(orgAddSite4).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			getDriver().findElement(orgSiteTypeInputChild).click();
			String farmType = getDriver().findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();	
			softAssert.assertEquals(farmType, "Farm");
			getDriver().findElement(orgSiteNameInput).sendKeys(ComplexConfigModel.organizationFarm1Name);
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);
			steps.createNode("10. Verify Farm Site can be saved");
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.");

			steps.createNode("11. Create House 1");
			getDriver().findElement(orgAddSite5).click();
			Thread.sleep(2000);
			getDriver().findElement(orgSiteTypeInputChild).click();
			String HouseType = getDriver().findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();	
			softAssert.assertEquals(HouseType, "House");
			getDriver().findElement(orgSiteNameInput).sendKeys(ComplexConfigModel.organizationHouse1Name);
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
		waitElementVisible(alertMessage);
			Thread.sleep(2000);
			steps.createNode("12. Verify House Site can be saved");
			softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New site created.");

			steps.createNode("13. Create house 2");
			getDriver().findElement(orgAddSite5).click();
			Thread.sleep(2000);
			getDriver().findElement(orgSiteNameInput).sendKeys(ComplexConfigModel.organizationHouse2Name);
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
		waitElementVisible(alertMessage);
			Thread.sleep(2000);

			steps.createNode("14. Create house 3");
			getDriver().findElement(orgAddSite5).click();
			Thread.sleep(2000);
			getDriver().findElement(orgSiteNameInput).sendKeys(ComplexConfigModel.organizationHouse3Name);
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
		waitElementVisible(alertMessage);
			Thread.sleep(1000);

			steps.createNode("15. Create house 4");
			getDriver().findElement(orgAddSite5).click();
			Thread.sleep(2000);
			getDriver().findElement(orgSiteNameInput).sendKeys(ComplexConfigModel.organizationHouse4Name);
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
		waitElementVisible(alertMessage);
			Thread.sleep(1000);

			steps.createNode("16. Create house 5");
			getDriver().findElement(orgAddSite5).click();
			Thread.sleep(2000);
			getDriver().findElement(orgSiteNameInput).sendKeys(ComplexConfigModel.organizationHouse5Name);
			getDriver().findElement(popupSaveButton).click();
			waitElementInvisible(loading_cursor);
		waitElementVisible(alertMessage);
			Thread.sleep(1000);

			softAssert.assertAll();
			test.pass("Site heirarchy verified successfully");
			results.createNode("Site heirarchy verified successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);	
		}catch(AssertionError er){
			test.fail("Site heirarchy failed to verify successfully");
			results.createNode("Site heirarchy failed to verify successfully");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("Site heirarchy failed to verify successfully");
			results.createNode("Site heirarchy failed to verify successfully");
			saveResult(ITestResult.FAILURE, ex);
		}
	}	


	@Test (enabled= true, priority= 3) 
	public void AssignTestingSites() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-UM-14: Verify Sites column displays Active after assigning All Testing Sites to the user");
			ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);
			UserManagementPage.openEditUserPopup(config.ie_username());
			click(popupNextButton);
			click(popupNextButton);
			Thread.sleep(750);
			click(UserManagementPage.userSitesButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);
			type(UserManagementPage.userSitesSearch, ComplexConfigModel.organizationName);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);
			click(By.xpath("//*[text()='"+ComplexConfigModel.organizationName+"']"));
			Thread.sleep(1000);
			click(UserManagementPage.userSitesSaveButton);
			Thread.sleep(1000);
			getScreenshot();
			click(popupSaveButton);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1500);

			Assert.assertEquals(getText(alertMessage), "User details updated.");	
			test.pass("Site Assigned to user successfully");
			results.createNode("Site Assigned to user successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}
		catch(AssertionError er) {
			test.fail("Site failed to assigned to user successfully");
			results.createNode("Site failed to assigned to user successfully");  
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Site failed to assigned to user successfully");
			results.createNode("Site failed to assigned to user successfully");  	
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	
	@SuppressWarnings("unused")
	@Test (enabled= true, priority= 4) 
	public void CreateProgramVaccine() throws InterruptedException, IOException {
		try {		
			test = extent.createTest("AN-Program-02: Verify that user is able to create new Vaccine program", "This testcase will verify that user is able to create new program");

			getDriver().get(url_programManagement);
			waitElementInvisible(loading_cursor);
			Thread.sleep(2000);
			SoftAssert softAssert = new SoftAssert();

			click(programVaccineProgramTab);
			waitElementInvisible(loading_cursor);

			for (int j=1;j<getDriver().findElements(By.id("col-0-vaccine")).size();j++) {
				if (getDriver().findElement(By.cssSelector("tr:nth-child("+j+") #col-0-vaccine label")).getText().equals(ComplexConfigModel.vaccineName)) {
					test.skip("Program already created");
					results.createNode("Program already created");
					getScreenshot();
					saveResult(ITestResult.SKIP, null);
					break;
				}

				else {
					getDriver().findElement(By.xpath("//*[text()=' Register New Program']")).click();
					waitElementInvisible(loading_cursor);
					//Program Name
					getDriver().findElement(programName).sendKeys(ComplexConfigModel.vaccineName);

					//Target Pathogen
					getDriver().findElement(programTargetPathogen).click();
					Thread.sleep(500);
					getDriver().findElement(programTargetPathogen).sendKeys(Keys.ENTER);
					Thread.sleep(500);

					//Program Type
					getDriver().findElement(programProgramType).sendKeys("Vaccine");
					Thread.sleep(500);	
					getDriver().findElement(programProgramType).sendKeys(Keys.ENTER);

					//Supplier
					getDriver().findElement(programSupplier).sendKeys(ProgramManagementModel.SupplierName);
					Thread.sleep(500);
					if (getDriver().findElements(By.xpath("//*[text()='Add New + ']")).size() != 0) {
						getDriver().findElement(By.xpath("//*[text()='Add New + ']")).click();
					}
					else {
						getDriver().findElement(By.cssSelector(".list-item")).click();		
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
					Methods method = new Methods();
					WebElement dateWidgetTo = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#startDate .dp-popup"))).get(0);
					List<WebElement> columns1 = dateWidgetTo.findElements(By.tagName("button"));
					System.out.println(3);
					DateUtil.clickGivenDay(columns1, DateUtil.getFirstDay());
					Thread.sleep(500);

					//End Date
					getDriver().findElement(By.cssSelector("#endDate img")).click();
					waitElementInvisible(loading_cursor);
					Thread.sleep(500);
					WebElement dateWidgetToEnd = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#endDate .dp-popup"))).get(0);
					List<WebElement> columns2 = dateWidgetToEnd.findElements(By.tagName("button"));
					DateUtil.clickGivenDay(columns2, DateUtil.getDay("30"));
					Thread.sleep(700);

					//Program Description
					getDriver().findElement(programDescription).sendKeys(ProgramManagementModel.DescriptionName);

					String NoApplicationFlock = "2";
					getDriver().findElement(programNoApplicationFlock).sendKeys(NoApplicationFlock);
					Thread.sleep(500);

					for(int i=1; i<=Integer.parseInt(NoApplicationFlock); i++) {
						getDriver().findElement(By.id(programDaysApplicationFlock+"-"+i)).sendKeys(""+i);
					}

					getScreenshot();
					click(popupSaveButtonXpath);
					waitElementVisible(alertMessage);
					softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New program has been created successfully"); 
					test.pass("New Program created successfully");
					results.createNode("New Program created successfully");
					getScreenshot();
					saveResult(ITestResult.SUCCESS, null);
					break;
				}
			}

		}catch(AssertionError er) {
			test.fail("New Program failed to create");
			results.createNode("New Program failed to create");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}catch(Exception ex) {
			test.fail("New Program failed to create");
			results.createNode("New Program failed to create");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@SuppressWarnings("unused")
	@Test (enabled= true, priority= 5) 
	public void CreateProgramFeed() throws InterruptedException, IOException {
		try {		
			test = extent.createTest("AN-Program-05: Verify that user is able to create new Feed program", "This testcase will verify that user is able to create new program");

			getDriver().get(url_programManagement);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1500);

			getDriver().findElement(programFeedProgramTab).click();
			Thread.sleep(1500);

			for (int j=1;j<getDriver().findElements(By.id("col-0-feedprogram")).size();j++) {
				if (getDriver().findElement(By.cssSelector("tr:nth-child("+j+") #col-0-feedprogram label")).getText().equals(ComplexConfigModel.vaccineName)) {
					test.skip("Program already created");
					results.createNode("Program already created");
					getScreenshot();
					saveResult(ITestResult.SKIP, null);
					break;
				}

				else {

					getDriver().findElement(programCreateButton).click();
					waitElementInvisible(loading_cursor);
					Thread.sleep(1500);

					getDriver().findElement(programName).sendKeys(ComplexConfigModel.feedName);

					getDriver().findElement(programTargetPathogen).click();
					Thread.sleep(1000);
					getDriver().findElement(programTargetPathogen).sendKeys(Keys.ENTER);

					getDriver().findElement(programProgramType).sendKeys("Feed");
					Thread.sleep(700);	
					getDriver().findElement(programProgramType).sendKeys(Keys.ENTER);

					getDriver().findElement(programSupplier).sendKeys("China");
					Thread.sleep(700);
					if (getDriver().findElements(By.xpath("//*[text()='Add New + ']")).size() != 0) {
						getDriver().findElement(By.xpath("//*[text()='Add New + ']")).click();
					}
					else {
						getDriver().findElement(By.cssSelector(".list-item")).click();		
					}
					Thread.sleep(700);

					getDriver().findElement(programDescription).sendKeys("Feed Testing Program");

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
					Methods method = new Methods();
					WebElement dateWidgetTo = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#startDate .dp-popup"))).get(0);
					List<WebElement> columns1 = dateWidgetTo.findElements(By.tagName("button"));
					DateUtil.clickGivenDay(columns1, DateUtil.getFirstDay());
					Thread.sleep(500);
					

					getDriver().findElement(programFeedTypeDropdown).click();
					Thread.sleep(1000);	
					getDriver().findElement(programFeedTypeDropdown).sendKeys(Keys.ENTER);

					getDriver().findElement(programFlockDayStart).sendKeys("1");

					WebElement EndDay = getDriver().findElement(programFlockDayStart);
					getDriver().findElement(with(By.tagName("input")).toRightOf(EndDay)).sendKeys("10");

					WebElement ingredient = getDriver().findElement(programFeedTypeDropdown);
					getDriver().findElement(with(By.tagName("input")).below(ingredient)).sendKeys("Sugar");

					WebElement ingredientCategory = getDriver().findElement(programFlockDayStart);
					getDriver().findElement(with(By.tagName("input")).below(ingredientCategory)).click();
					List<WebElement> ingredientCategories = getDriver().findElements(By.cssSelector(".ng-option-label"));
					ingredientCategories.get(0).click();

					getScreenshot();
					getDriver().findElement(By.xpath(("//button[text() = ' Submit ']"))).click();
					waitElementInvisible(loading_cursor);
					Thread.sleep(2000);
					Assert.assertEquals(getDriver().findElement(alertMessage).getText(), "New program has been created successfully"); 
					test.pass("New Program created successfully");
					results.createNode("New Program created successfully");
					getScreenshot();
					saveResult(ITestResult.SUCCESS, null);
					break;
				}
			}


		}catch(AssertionError er) {
			test.fail("New Program failed to create");
			results.createNode("New Program failed to create");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}catch(Exception ex) {
			test.fail("New Program failed to create");
			results.createNode("New Program failed to create");
			saveResult(ITestResult.FAILURE, ex);
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

			getDriver().get(url_complexConfig);;
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			ComplexConfigModel.lstCreateComplexConfig = ComplexConfigModel.CreateConfig();

			getDriver().findElement(complexCreateButton).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			getDriver().findElement(complexSelectComplexDropdown).click();
			Thread.sleep(1000);
			getDriver().findElement(complexSearchComplex).sendKeys(ComplexConfigModel.complexName);
			Thread.sleep(1000);
			getDriver().findElement(complexSelectComplexSite).click();

			getDriver().findElement(complexSelectProgramType).sendKeys("Vaccine");
			Thread.sleep(2000);
			getDriver().findElement(complexSelectProgramType).sendKeys(Keys.ENTER);

			getDriver().findElement(complexSelectProgramId).sendKeys(ComplexConfigModel.vaccineName);
			Thread.sleep(1000);
			getDriver().findElement(complexSelectProgramId).sendKeys(Keys.ENTER);

			By addProgramButton = RelativeLocator.with(By.tagName("button")).toRightOf(complexSelectProgramId);
			getDriver().findElement(addProgramButton).click();

			getDriver().findElement(complexSelectProgramType).sendKeys("Feed");
			Thread.sleep(1000);
			getDriver().findElement(complexSelectProgramType).sendKeys(Keys.ENTER);

			getDriver().findElement(complexSelectProgramId).sendKeys(ComplexConfigModel.feedName);
			Thread.sleep(1000);
			getDriver().findElement(complexSelectProgramId).sendKeys(Keys.ENTER);

			getDriver().findElement(addProgramButton).click();

			for (ComplexConfigModel objModel : ComplexConfigModel.lstCreateComplexConfig) { 	

				getDriver().findElement(complexOPGType).click();
				Thread.sleep(1000);
				List<WebElement> OPGTypeSelect = getDriver().findElements(complexSelectValueFromDropdown);
				OPGTypeSelect.get(0).click();

				getDriver().findElement(complexBirdSize).click();
				Thread.sleep(1000);
				List<WebElement> BirdSizeSelect = getDriver().findElements(complexSelectValueFromDropdown);
				BirdSizeSelect.get(0).click();

				getDriver().findElement(complexSamplingInterval).click();
				Thread.sleep(1000);
				List<WebElement> SamplingIntervalSelect = getDriver().findElements(complexSelectValueFromDropdown);
				SamplingIntervalSelect.get(0).click();

				getDriver().findElement(complexComplexThreshold).sendKeys(objModel.ComplexThreshold);
				getDriver().findElement(complexHouseThreshold).sendKeys(objModel.HouseThreshold);
				getDriver().findElement(complexLowerLimit).sendKeys(objModel.LowerLimit);
				getDriver().findElement(complexUpperLimit).sendKeys(objModel.UpperLimit);
				Thread.sleep(1000);
				By addConfigButton = RelativeLocator.with(By.tagName("button")).toRightOf(complexUpperLimit);
				getDriver().findElement(addConfigButton).click();
			}

			getDriver().findElement(complexSubmitButton).click();
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);
			Assert.assertNotEquals(getDriver().findElement(alertMessage).getText(), "Complex cycling configuration details saved");
			test.pass("Complex cycling configuration details saved successfully");
			results.createNode("Complex cycling configuration details saved successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
		}
		catch(AssertionError er) {
			test.fail("Complex cycling configuration details failed to save");
			results.createNode("Complex cycling configuration details failed to save");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Complex cycling configuration details failed to save");
			results.createNode("Complex cycling configuration details failed to save");
			saveResult(ITestResult.FAILURE, ex);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	@SuppressWarnings({ "unchecked", "unused" })
	@Test (enabled= true, priority= 8) 
	public void Ingestion_Flock() throws InterruptedException, IOException, SQLException	{
		ComplexConfigModel.lstComplexConfig = ComplexConfigModel.FillDataCocci();

		for (ComplexConfigModel objModel : ComplexConfigModel.lstComplexConfig) {

			if(objModel.createFlock) {
				try {
					test = extent.createTest("AN-Flock: Verify user can create Flock", "This test case will verify that user can crate flock");
					getDriver().get(url_flockManagement);
					waitElementInvisible(loading_cursor);
					Thread.sleep(2000);
					
					getDriver().findElement(flockCreateButton).click();
					waitElementInvisible(loading_cursor);	
					Thread.sleep(2000);

					click(flockFarmDropdownExpand);
					type(flockFarmDropdownSearch, ComplexConfigModel.organizationFarm1Name);
					waitElementInvisible(loading_cursor);
					waitElementClickable(By.cssSelector("label b"));
					Thread.sleep(2000);
					scroll(By.cssSelector("label b"));
					click(By.cssSelector("label b"));
					
					type(flockIntegratorFlockID, ComplexConfigModel.flockIntegratorID);

					if (size(flockIntegratorFlockAddNew) != 0) {
						click(flockIntegratorFlockAddNew);
					}
					else {
						getDriver().findElement(flockIntegratorFlockID).sendKeys(Keys.ENTER);
					}
					
					click(flockBirdSizeInput);
					List<WebElement> birdSizeList = getDriver().findElements(flockBirdSizeDropDownOptions);
					birdSizeList.get(objModel.birdSize).click();
					
					scroll(flockPlacementDateCalendar);
					click(flockPlacementDateCalendar);

			//		List<WebElement> list = getDriver().findElements(By.cssSelector(".dp-current-day"));
			//		List<WebElement> list = getDriver().findElements(By.xpath("//*[text()='01']"));
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
						getDriver().findElement(By.xpath("//*[text() = '"+objModel.LstHouses.get(i)+"']")).click();

					}

					scroll(flockAddNewProgram);
					ClickElement.clickByXpath(getDriver(), "//*[text() = 'Add New Program']");
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
					Assert.assertEquals(getDriver().findElement(alertMessage).getText(), "Data saved successfully.");
					System.out.println("Flock created successfully");

					test.pass("Flock was created successfully");
					results.createNode("Flock was created successfully");
					getScreenshot();
					saveResult(ITestResult.SUCCESS, null);	
				}
				catch(AssertionError er) {
					test.fail("Flock failed to create");
					results.createNode("Flock failed to create");
					saveResult(ITestResult.FAILURE, new Exception(er));
				}
				catch(Exception ex) {
					test.fail("Flock failed to create");
					results.createNode("Flock failed to create");
					saveResult(ITestResult.FAILURE, ex);
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

							ResultSet rs2 = DB_Config.getStmt().executeQuery(query2);

							while (rs2.next()) {
								System.out.println("Count: "+rs2.getString("count"));

								if (rs2.getString("count").equals("12")) {

									CoccidiaLogPage.openCoccidiaLogPage();
									waitElementInvisible(loading_cursor);
									waitElementInvisible(loading_cursor);
									waitElementVisible(By.id("sort-sampleId"));
									Thread.sleep(3000);

									steps.createNode("1. Click on Sample ID to expand the filter");
									ClickElement.clickById(getDriver(), "sampleId_show-filter");			
									waitElementInvisible(loading_cursor);
									Thread.sleep(1000);
									getDriver().findElement(By.id("sampleId_view-all")).click();
									waitElementInvisible(loading_cursor);
									Thread.sleep(1000);
									steps.createNode("2. Search for the Sample ID's against which the data is ingested");							

									getDriver().findElement(By.id("sampleId_search-input")).clear();
									getDriver().findElement(By.id("sampleId_search-input")).sendKeys(objModel.SampleID);
									waitElementInvisible(loading_cursor);
									Thread.sleep(2000);	
									try {
										getDriver().findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objModel.SampleID)).click();
									}
									catch(Exception ex) {
										Thread.sleep(1000);
										getDriver().findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objModel.SampleID)).click();
									}
									waitElementInvisible(loading_cursor);
									Thread.sleep(800);


									steps.createNode("3. Click on Apply filter button");
									getDriver().findElement(By.id("sampleId_apply")).click();
									waitElementInvisible(loading_cursor);
									Thread.sleep(4000);
									getScreenshot();
									String records = getDriver().findElement(By.id("results-found-count")).getText();

									softAssert.assertEquals(records, "12"); 
									getScreenshot();									test.pass("Run ingested successfully");
									results.createNode("Run ingested successfully");
									saveResult(ITestResult.SUCCESS, null);
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
					saveResult(ITestResult.FAILURE, ex);	
				}

				catch(AssertionError er) {
					test.fail("Ingestion failed");
					results.createNode("Ingestion failed");
					saveResult(ITestResult.FAILURE, new Exception(er));
				}

				////////////////////////////////////////////////////////////End File Upload//////////////////////////////////////////////////////////////////////

				try {	
					test = extent.createTest("AN-Coccidia-03: Upload Sample MetaData File and verify the data in Report", "This test case will verify the data in report on uploading sample metedata");	
					preconditions = test.createNode(Scenario.class, PreConditions);
					steps = test.createNode(Scenario.class, Steps);
					results = test.createNode(Scenario.class, Results);

				//	FileInputStream fsIP= new FileInputStream(new File("./Excel/"+fileName_Mobile));
					FileInputStream fsIP= new FileInputStream(new File(FrameworkConstants.CSMDataTemplateUpload));
					@SuppressWarnings("resource")
					XSSFWorkbook wb = new XSSFWorkbook(fsIP);
					XSSFSheet worksheet = wb.getSheetAt(0);
					Cell cell = null;

					if (getDriver().findElement(By.id(ResultsCount)).getText().equals("12")) {
						for (int z=0; z<12; z++) {

							String getResultID = getDriver().findElement(By.cssSelector("#row-"+z+" #col-"+clResultIDCol+" label")).getText();
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

							String getCollectionSiteID = getDriver().findElement(By.cssSelector("#row-"+z+" #col-"+clCollectionSiteIDCol+" label")).getText();
							cell=worksheet.getRow(z+1).createCell(metadata_CollectionSiteID); 
							cell.setCellValue(getCollectionSiteID); 

							String getSampleID = getDriver().findElement(By.cssSelector("#row-"+z+" #col-"+clSampleIDCol+" label")).getText();
							cell=worksheet.getRow(z+1).createCell(metadata_LabSampleID); 
							cell.setCellValue(getSampleID);  

							String selectQuery = "Select unique_Flock_id from dbo.flock_mgmt where integrator_flock_id = '"+ComplexConfigModel.flockIntegratorID+"' and Bird_Size = '"+objModel.birdSizeName+"'";
							ResultSet rs = DB_Config.getStmt().executeQuery(selectQuery);
							while (rs.next()) {
								String flockID = rs.getString("unique_flock_id");
								//System.out.println("Unique Flock ID: "+flockID);
								cell=worksheet.getRow(z+1).createCell(metadata_FlockID); 
								cell.setCellValue(flockID); 
							}

							String getComplex = getDriver().findElement(By.cssSelector("#row-"+z+" #col-"+clComplexCol+" label")).getText();
							cell=worksheet.getRow(z+1).createCell(metadata_Complex); 
							cell.setCellValue(getComplex); 

							String getFarm = getDriver().findElement(By.cssSelector("#row-"+z+" #col-"+clFarmCol+" label")).getText();
							cell=worksheet.getRow(z+1).createCell(metadata_Farm); 
							cell.setCellValue(getFarm); 

							String getLane = getDriver().findElement(By.cssSelector("#row-"+z+" #col-"+clLaneCol+" label")).getText();
							cell=worksheet.getRow(z+1).createCell(metadata_Lane); 
							cell.setCellValue(getLane);  

							String getResultDate = getDriver().findElement(By.cssSelector("#row-"+z+" #col-"+clDateCol+" label")).getText();
							cell=worksheet.getRow(z+1).createCell(metadata_ResultDate); 
							cell.setCellValue(getResultDate);

							String getresultTime = getDriver().findElement(By.cssSelector("#row-"+z+" #col-"+clTimeCol+" label")).getText();
							cell=worksheet.getRow(z+1).createCell(metadata_ResultTime); 
							cell.setCellValue(getresultTime); 

							cell=worksheet.getRow(z+1).createCell(metadata_CartridgeID); 
							cell.setCellValue(ComplexConfigModel.cartridgeID); 

							cell=worksheet.getRow(z+1).createCell(metadata_InstrumentID); 
							cell.setCellValue(InstrumentID);

							String getPiperUser = getDriver().findElement(By.cssSelector("#row-"+z+" #col-"+clPiperUserCol)).getText();
							cell=worksheet.getRow(z+1).createCell(metadata_PiperUser); 
							cell.setCellValue(getPiperUser);

							cell=worksheet.getRow(z+1).createCell(metadata_CollectionDate); 
							cell.setCellValue(objModel.CollectionDatee);

							fsIP.close();
						}
						//	getStmt().close();	

						FileOutputStream output_file =new FileOutputStream(new File(FrameworkConstants.CSMDataTemplateUpload));
						wb.write(output_file);
						output_file.close();  

						getDriver().get(url_dataUpload);
						waitElementVisible(By.id("OrgnTypeID"));
						Thread.sleep(000);
						getDriver().findElement(By.id("OrgnTypeID")).click();
						getDriver().findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("Ancera");
						getDriver().findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						getDriver().findElement(By.id("DataFormatId")).click();
						getDriver().findElement(By.cssSelector("#DataFormatId input")).sendKeys("Sample Metadata");
						getDriver().findElement(By.cssSelector("#DataFormatId input")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						getDriver().findElement(By.id("file-input")).sendKeys(FrameworkConstants.CSMDataTemplateUpload);
						waitElementInvisible(loading_cursor);
						waitElementVisible(alertMessage);
						Thread.sleep(4000);
						getDriver().findElement(By.cssSelector(".fa-save")).click();
						waitElementInvisible(loading_cursor);
						waitElementVisible(alertMessage);
						waitElementVisible(alertMessage);
						Thread.sleep(6000);
						getScreenshot();
						Assert.assertTrue(getDriver().findElement(alertMessage).getText().contains("SampleMetadata_Mobile.xlsx saved successfully."));
						System.out.println("Template created successfully");
						test.pass("Template saved successfully");
						results.createNode("Template saved successfully");
						saveResult(ITestResult.SUCCESS, null);
					}
					else {
						results.createNode("12 records not displaying in table hence file upload method not executed");
						test.skip("12 records not displaying in table hence file upload method not executed");
						results.createNode("12 records not displaying in table hence file upload method not executed");
						saveResult(ITestResult.SKIP, null);
					}
				}
				catch(AssertionError er) {
					test.fail("Data failed to verify on uploading Sample Metadata Template");
					results.createNode("Data failed to verify on uploading Sample Metadata Template");
					saveResult(ITestResult.FAILURE, new Exception(er));
				}catch(Exception ex){
					test.fail("Data failed to verify on uploading Sample Metadata Template");
					results.createNode("Data failed to verify on uploading Sample Metadata Template");
					saveResult(ITestResult.FAILURE, ex);	
				}
				Thread.sleep(2000);	
			}
		}
	//	getStmt().close();	
	}

}
