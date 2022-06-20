package Test.Ancera.Administration;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

import Models.ComplexConfigModel;
import Models.NormalIngestionModel;
import Models.ReportFilters;
import Test.Ancera.ClickElement;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.DB_Config;
import Test.Ancera.DateUtil;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PreFlutterMobile extends DB_Config{

	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Pre_Flutter_Mobile"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Pre Flutter Mobile Test Report"); 

		Helper.config();
		ConfigureLogin.login();
		DB_Config.test();
	}


	@Test (priority = 1) 
	public void CreateOrg() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-OM-20: Verify user can create New Organizationn", "This test case will verify that user can create new organization");

			Helper.driver.get(Constants.url_organization);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);

			Helper.driver.findElement(Test_Elements.orgCreateButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.orgTypeDropDownExpand).click();
			Thread.sleep(750);
			Helper.driver.findElement(Test_Elements.orgTypeInput).sendKeys(Keys.ENTER);

			Helper.driver.findElement(Test_Elements.orgNameInput).sendKeys(ComplexConfigModel.organizationName);

			Helper.driver.findElement(Test_Elements.popupNextButton).click();
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.orgMaxUsersInput).sendKeys("10");

			Helper.driver.findElement(Test_Elements.orgSystemRolesExpand).click();
			Thread.sleep(1000);
			List<WebElement> systemroleSelect = Helper.driver.findElements(By.cssSelector(".ng-dropdown-panel-items"));
			systemroleSelect.get(0).click();			
			Helper.driver.findElement(Test_Elements.orgSystemRolesExpand).click();

			Helper.driver.findElement(Test_Elements.orgReportRolesExpand).click();
			Thread.sleep(1000);
			List<WebElement> reportroleSelect = Helper.driver.findElements(By.cssSelector(".ng-dropdown-panel-items"));
			reportroleSelect.get(0).click();

			Helper.driver.findElement(Test_Elements.orgReportRolesExpand).click();
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();;
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), Test_Variables.lstOrgAlertMessages.get(0)); 
			Test_Variables.test.pass("New Organization created successfully");
			Test_Variables.results.createNode("New Organization created successfully; displayed alert message 'New organization created.'");	
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("New Organization creation failed");
			Test_Variables.results.createNode("New Organization creation failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("New Organization creation failed");
			Test_Variables.results.createNode("New Organization creation failed");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
		}	
	}


	@Test (description="Test Case: Organization Site Check",enabled= true, priority= 10) 
	public void OrganizationSitesHierarchyCheck() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-OM-31-38: Verify Complete Organization Site Hierarchy", "This test case will verify complete site hierarchy");

			SoftAssert softAssert = new SoftAssert();

			Helper.driver.get(Constants.url_organization);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));

			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.orgNameCol+" label")).getText().equals(ComplexConfigModel.organizationName)) {
					Helper.driver.findElement(By.id("edit-orgn-sites-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					break;
				}
			}

			Thread.sleep(1000);	
			//	Helper.driver.findElement(Test_Elements.orgParentSiteClick).click(); 
			//	Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			//	Thread.sleep(2000);



			Helper.driver.findElement(Test_Elements.orgAddSite1).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.orgSiteTypeInput).click();
			String regionType = Helper.driver.findElement(Test_Elements.orgSiteTypeDropDownValue).getText();	
			softAssert.assertEquals(regionType, "Region");
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys("Test Region");
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("4. Verify Region Site can be saved");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New site created.");

			Test_Variables.steps.createNode("5. Click on + icon to create new site and verify Site Type is Sub Region");
			Helper.driver.findElement(Test_Elements.orgAddSite2).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.orgSiteTypeInput).click();
			String subregionType = Helper.driver.findElement(Test_Elements.orgSiteTypeDropDownValue).getText();
			softAssert.assertEquals(subregionType, "Sub Region");
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys("Test Sub Region");
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("6. Verify Sub Region Site can be saved");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New site created.");

			Test_Variables.steps.createNode("7. Click on + icon to create new site and verify Site Type as Complex, Processing PLant, Testing Lab");
			Helper.driver.findElement(Test_Elements.orgAddSite3).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.orgSiteTypeInputChild).click();
			Thread.sleep(1000);

			Helper.driver.findElement(By.cssSelector("div .ng-option:nth-child(1)")).click();
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys("Test Complex Site");
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("8. Verify Complex Site can be saved");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New site created.");

			Test_Variables.steps.createNode("9. Click on + icon to create new site and verify Site Type as Farm");
			Helper.driver.findElement(Test_Elements.orgAddSite4).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.orgSiteTypeInputChild).click();
			String farmType = Helper.driver.findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();	
			softAssert.assertEquals(farmType, "Farm");
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys("Test Farm");
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("10. Verify Farm Site can be saved");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New site created.");

			Test_Variables.steps.createNode("11. Click on + icon to create new site and verify Site Type as House");
			Helper.driver.findElement(Test_Elements.orgAddSite5).click();
			Thread.sleep(2000);
			Helper.driver.findElement(Test_Elements.orgSiteTypeInputChild).click();
			String HouseType = Helper.driver.findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();	
			softAssert.assertEquals(HouseType, "House");
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys("Test House");
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("12. Verify House Site can be saved");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New site created.");
			softAssert.assertAll();
			Test_Variables.test.pass("Site heirarchy verified successfully");
			Test_Variables.results.createNode("Site heirarchy verified successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Organization Management", Constants.OrgManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.OrgManagementReportPath, null);	
		}catch(AssertionError er){
			Test_Variables.test.fail("Site heirarchy failed to verify successfully");
			Test_Variables.results.createNode("Site heirarchy failed to verify successfully");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("Site heirarchy failed to verify successfully");
			Test_Variables.results.createNode("Site heirarchy failed to verify successfully");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.OrgManagementReportPath, ex);
		}
	}	



	@Test (enabled= true, priority= 10) 
	public void VerifyTestingSitesAccess() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-14: Verify Sites column displays Active after assigning All Testing Sites to the user", "This test case will verify Sites column displays Active after assigning sites to the user");

			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.usercreateButton));
			Thread.sleep(3000);

			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.login_email)) {


					WebElement filter_scroll = Helper.driver.findElement(By.id("edit-user-"+i));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll);
					Thread.sleep(2000);

					Helper.driver.findElement(By.id("edit-user-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(4000);

					Helper.driver.findElement(By.id("btn-next")).click();
					Helper.driver.findElement(By.id("btn-next")).click();
					Thread.sleep(750);

					Helper.driver.findElement(By.cssSelector(".btn-sites")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(2000);

					Helper.driver.findElement(Test_Elements.userSitesSearch).sendKeys(ComplexConfigModel.organizationName);
					Thread.sleep(1000);
					Helper.driver.findElement(By.xpath("//*[text()=' "+ComplexConfigModel.organizationName+" ']")).click();

					Helper.driver.findElement(By.id("btn-ok-sites")).click();
					Thread.sleep(1000);
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
					Helper.driver.findElement(By.id("btn-save")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(1500);
					Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "User details updated.");	
					Test_Variables.test.pass("Site Assigned to user successfully");
					Test_Variables.results.createNode("Site Assigned to user successfully");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.PreFlutterMobileReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.ProgramManagementReportPath, null);
					break;

				}
			}	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Site failed to assigned to user successfully");
			Test_Variables.results.createNode("Site failed to assigned to user successfully");  
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PreFlutterMobileReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Site failed to assigned to user successfully");
			Test_Variables.results.createNode("Site failed to assigned to user successfully");  	
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PreFlutterMobileReportPath, ex);
		}
	}






	@Test (enabled= true, priority= 2) 
	public void CreateProgramVaccine() throws InterruptedException, IOException {
		try {		
			Test_Variables.test = Test_Variables.extent.createTest("AN-Program-02: Verify that user is able to create new Vaccine program", "This testcase will verify that user is able to create new program");

			Helper.driver.get(Constants.url_programManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1500);



			//			List<WebElement> allProducts = Helper.driver.findElements(Test_Elements.programColumn1);
			//			for (WebElement product: allProducts) {
			//				System.out.println(product.getText());
			//			    if(!product.getText().equals(ComplexConfigModel.vaccineName)) 
			//			        return; // or break or whatever
			//			}



			for (int j=1;j<Helper.driver.findElements(Test_Elements.programColumn1).size();j++) {
				if (Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-0 label")).getText().equals(ComplexConfigModel.vaccineName)) {
					break;
				}

				else {
					Helper.driver.findElement(Test_Elements.programCreateButton).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(1500);

					Helper.driver.findElement(Test_Elements.programName).sendKeys(ComplexConfigModel.vaccineName);

					Helper.driver.findElement(Test_Elements.programTargetPathogen).click();
					Thread.sleep(1000);
					Helper.driver.findElement(Test_Elements.programTargetPathogen).sendKeys(Keys.ENTER);

					Helper.driver.findElement(Test_Elements.programProgramType).sendKeys("Vaccine");
					Thread.sleep(1000);	
					Helper.driver.findElement(Test_Elements.programProgramType).sendKeys(Keys.ENTER);

					Helper.driver.findElement(Test_Elements.programSupplier).sendKeys("China");
					Thread.sleep(1000);
					if (Helper.driver.findElements(By.xpath("//*[text()='Add New + ']")).size() != 0) {
						Helper.driver.findElement(By.xpath("//*[text()='Add New + ']")).click();
					}

					else {
						Helper.driver.findElement(By.cssSelector(".list-item")).click();		
					}

					Helper.driver.findElement(Test_Elements.programDescription).sendKeys("Vaccine Testing Program");

					Helper.driver.findElement(By.cssSelector("#startDate img")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(2000);
					WebElement dateWidgetTo = Test_Elements.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#startDate .dp-popup"))).get(0);
					List<WebElement> columns1 = dateWidgetTo.findElements(By.tagName("button"));
					DateUtil.clickGivenDay(columns1, DateUtil.getFirstDay());
					Thread.sleep(2000);

					String NoApplicationFlock = "2";
					Helper.driver.findElement(Test_Elements.programNoApplicationFlock).sendKeys(NoApplicationFlock);
					Thread.sleep(1000);

					for(int i=1; i<=Integer.parseInt(NoApplicationFlock); i++) {
						Helper.driver.findElement(By.id(Test_Elements.programDaysApplicationFlock+"-"+i)).sendKeys(""+i);
					}
					Thread.sleep(1500);

					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.ProgramManagementReportPath));
					Helper.driver.findElement(By.xpath(("//*[text()=' Submit ']"))).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(1000);
					Assert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New program has been created successfully"); 
					Test_Variables.test.pass("New Program created successfully");
					Test_Variables.results.createNode("New Program created successfully");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.ProgramManagementReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.ProgramManagementReportPath, null);
					break;
				}
			}

		}catch(AssertionError er) {
			Test_Variables.test.fail("New Program failed to create");
			Test_Variables.results.createNode("New Program failed to create");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			Test_Variables.test.fail("New Program failed to create");
			Test_Variables.results.createNode("New Program failed to create");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority= 5) 
	public void CreateProgramFeed() throws InterruptedException, IOException {
		try {		
			Test_Variables.test = Test_Variables.extent.createTest("AN-Program-05: Verify that user is able to create new Feed program", "This testcase will verify that user is able to create new program");

			Helper.driver.get(Constants.url_programManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1500);

			Helper.driver.findElement(Test_Elements.programFeedProgramTab).click();

			String rowCount = Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText();

			for (int j=1;j<Integer.parseInt(rowCount);j++) {
				if (Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-0 label")).getText().equals(ComplexConfigModel.feedName)) {
					break;
				}

				else {

					Helper.driver.findElement(Test_Elements.programCreateButton).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(1500);

					Helper.driver.findElement(Test_Elements.programName).sendKeys(ComplexConfigModel.feedName);

					Helper.driver.findElement(Test_Elements.programTargetPathogen).click();
					Thread.sleep(1000);
					Helper.driver.findElement(Test_Elements.programTargetPathogen).sendKeys(Keys.ENTER);

					Helper.driver.findElement(Test_Elements.programProgramType).sendKeys("Feed");
					Thread.sleep(1000);	
					Helper.driver.findElement(Test_Elements.programProgramType).sendKeys(Keys.ENTER);

					Helper.driver.findElement(Test_Elements.programSupplier).sendKeys("China");
					Thread.sleep(1000);
					if (Helper.driver.findElements(By.xpath("//*[text()='Add New + ']")).size() != 0) {
						Helper.driver.findElement(By.xpath("//*[text()='Add New + ']")).click();
					}
					else {
						Helper.driver.findElement(By.cssSelector(".list-item")).click();		
					}
					Thread.sleep(1000);

					Helper.driver.findElement(Test_Elements.programDescription).sendKeys("Feed Testing Program");

					Helper.driver.findElement(Test_Elements.programFeedTypeDropdown).click();
					Thread.sleep(1000);	
					Helper.driver.findElement(Test_Elements.programFeedTypeDropdown).sendKeys(Keys.ENTER);

					Helper.driver.findElement(Test_Elements.programFlockDayStart).sendKeys("1");

					WebElement EndDay = Helper.driver.findElement(Test_Elements.programFlockDayStart);
					Helper.driver.findElement(with(By.tagName("input")).toRightOf(EndDay)).sendKeys("10");

					WebElement ingredient = Helper.driver.findElement(Test_Elements.programFeedTypeDropdown);
					Helper.driver.findElement(with(By.tagName("input")).below(ingredient)).sendKeys("Sugar");

					WebElement ingredientCategory = Helper.driver.findElement(Test_Elements.programFlockDayStart);
					Helper.driver.findElement(with(By.tagName("input")).below(ingredientCategory)).click();
					List<WebElement> ingredientCategories = Helper.driver.findElements(By.cssSelector(".ng-option-label"));
					ingredientCategories.get(0).click();

					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.ProgramManagementReportPath));
					Helper.driver.findElement(By.xpath(("//*[text()=' Submit ']"))).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(5000);
					Assert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New program has been created successfully"); 
					Test_Variables.test.pass("New Program created successfully");
					Test_Variables.results.createNode("New Program created successfully");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.ProgramManagementReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.ProgramManagementReportPath, null);
					break;
				}
			}
		}catch(AssertionError er) {
			Test_Variables.test.fail("New Program failed to create");
			Test_Variables.results.createNode("New Program failed to create");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			Test_Variables.test.fail("New Program failed to create");
			Test_Variables.results.createNode("New Program failed to create");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, ex);
		}
	}



	@Test (description="Test Case: Create complex Configurations",enabled= true, priority = 4) 
	public void CreateComplexConfig() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Complex: Create Complex Configuration");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Administration and select Complex OPG Range Config; Screen opens");
			Test_Variables.steps.createNode("1. Create Configuration");

			Helper.driver.get(Constants.url_complexConfig);;
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(3000);
			Test_Variables.lstCreateComplexConfig = ComplexConfigModel.CreateConfig();

			Helper.driver.findElement(Test_Elements.complexCreateButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.complexSelectComplexDropdown).click();
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.complexSearchComplex).sendKeys("TestComplex");
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.complexSelectComplexSite).click();

			Helper.driver.findElement(Test_Elements.complexSelectVaccine).sendKeys("TestCMP");
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.complexSelectVaccine).sendKeys(Keys.ENTER);

			Helper.driver.findElement(Test_Elements.complexSelectFeed).sendKeys("TestCMF");
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.complexSelectFeed).sendKeys(Keys.ENTER);

			for (ComplexConfigModel objModel : Test_Variables.lstCreateComplexConfig) { 	

				Helper.driver.findElement(Test_Elements.complexOPGType).click();
				Thread.sleep(1000);
				List<WebElement> OPGTypeSelect = Helper.driver.findElements(Test_Elements.complexSelectValueFromDropdown);
				OPGTypeSelect.get(0).click();

				Helper.driver.findElement(Test_Elements.complexBirdSize).click();
				Thread.sleep(1000);
				List<WebElement> BirdSizeSelect = Helper.driver.findElements(Test_Elements.complexSelectValueFromDropdown);
				BirdSizeSelect.get(0).click();

				Helper.driver.findElement(Test_Elements.complexSamplingInterval).click();
				Thread.sleep(1000);
				List<WebElement> SamplingIntervalSelect = Helper.driver.findElements(Test_Elements.complexSelectValueFromDropdown);
				SamplingIntervalSelect.get(0).click();

				Helper.driver.findElement(Test_Elements.complexComplexThreshold).sendKeys(objModel.ComplexThreshold);
				Helper.driver.findElement(Test_Elements.complexHouseThreshold).sendKeys(objModel.HouseThreshold);
				Helper.driver.findElement(Test_Elements.complexLowerLimit).sendKeys(objModel.LowerLimit);
				Helper.driver.findElement(Test_Elements.complexUpperLimit).sendKeys(objModel.UpperLimit);
				Thread.sleep(1000);
				Helper.driver.findElement(Test_Elements.complexAddButton).click();
			}

			Helper.driver.findElement(Test_Elements.complexSubmitButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Assert.assertNotEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "Complex cycling configuration details saved");
			Test_Variables.test.pass("Complex cycling configuration details saved successfully");
			Test_Variables.results.createNode("Complex cycling configuration details saved successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PreFlutterMobileReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Complex cycling configuration details failed to save");
			Test_Variables.results.createNode("Complex cycling configuration details failed to save");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PreFlutterMobileReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Complex cycling configuration details failed to save");
			Test_Variables.results.createNode("Complex cycling configuration details failed to save");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PreFlutterMobileReportPath, ex);
		}
	}



	@Test (enabled= true, priority =11) 
	public void CreateFlock() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-FR-98: Verify user can create Flock", "This test case will verify that user can crate flock");
			Helper.driver.get(Constants.url_flockRegistration);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(2000);

			Helper.driver.findElement(Test_Elements.flockCreateButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	

			Helper.driver.findElement(Test_Elements.flockIntegratorFlockID).sendKeys("IntegratorID_"+Test_Variables.date0);
			if (Helper.driver.findElements(By.cssSelector("#integratorFlockId .list-item")).size() != 0) {
				Helper.driver.findElement(By.cssSelector("#integratorFlockId .list-item")).click();
			}

			Helper.driver.findElement(Test_Elements.flockBirdSizeExpandDropDown).click();
			List<WebElement> birdSizeList = Helper.driver.findElements(By.cssSelector(".ng-option"));
			birdSizeList.get(5).click();

			Helper.driver.findElement(Test_Elements.flockFarmExpandDropdown).click();

			Helper.driver.findElement(Test_Elements.flockFarmSeach).sendKeys(ComplexConfigModel.organizationFarm1Name);
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath("//b[text() = '"+ComplexConfigModel.organizationFarm1Name+"']")).click();

			Helper.driver.findElement(Test_Elements.flockHousePlacementDots).click();

			Helper.driver.findElement(By.cssSelector("#placementDate img")).click();
			Thread.sleep(1000);		

			WebElement dateWidgetTo = Test_Elements.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#placementDate .dp-popup"))).get(0);
			List<WebElement> columns1 = dateWidgetTo.findElements(By.tagName("button"));
			DateUtil.clickGivenDay(columns1, DateUtil.getDay("02"));

			Helper.driver.findElement(Test_Elements.flockSelectHouses).click();
			Helper.driver.findElement(By.xpath("//*[text() = '"+ComplexConfigModel.organizationHouse1Name+"']")).click();
			Helper.driver.findElement(Test_Elements.flockHouseSaveButton).click();

			Helper.driver.findElement(Test_Elements.flockProgramDetailsDots).click();

			Helper.driver.findElement(Test_Elements.flockProgramExpandDropDown).sendKeys(ComplexConfigModel.vaccineName);	
			Helper.driver.findElement(Test_Elements.flockProgramExpandDropDown).sendKeys(Keys.ENTER);
			Thread.sleep(1000);

			Helper.driver.findElement(Test_Elements.flockAdministrationMethod).sendKeys("CmsAdminMethod");
			if (Helper.driver.findElements(By.xpath("//*[text()='Add New + ']")).size() != 0) {
				Helper.driver.findElement(By.xpath("//*[text()='Add New + ']")).click();
			}
			else {
				Helper.driver.findElement(By.cssSelector(".list-item")).click();		
			}

			Helper.driver.findElement(Test_Elements.flockProgramSaveButton).click();

			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Assert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "Data saved successfully.");

			Test_Variables.test.pass("Flock was created successfully");
			Test_Variables.results.createNode("Flock was created successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Flock Registration", Constants.FlockRegistrationReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.FlockRegistrationReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Flock failed to create");
			Test_Variables.results.createNode("Flock failed to create");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Flock failed to create");
			Test_Variables.results.createNode("Flock failed to create");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, ex);
		}
	}


	@SuppressWarnings({ "unchecked", "unused" })
	@Test (description="Test Case: Run Ingestion for Coccidia", enabled= true, priority= 2) 
	public void NormalIngestionCoccidia() throws InterruptedException, IOException	{
		Test_Variables.lstNormalIngestion = NormalIngestionModel.FillDataCocci();
		for (NormalIngestionModel objModel : Test_Variables.lstNormalIngestion) { 
			Test_Variables.test = Test_Variables.extent.createTest("AN-API_Login-01: Verify Login API", "This test case will run login api and verify that token is generated or not");

			SoftAssert softAssert = new SoftAssert();

			for (ReportFilters objFilter : objModel.lstFilters) {

				DateFormat dateFormat = new SimpleDateFormat("mm.ss");
				Date date1 = new Date();
				dateFormat.format(date1);

				RequestSpecification request = RestAssured.given();
				request.header("Content-Type", "application/json");
				JSONObject json = new JSONObject();
				json.put("piperid", Test_Variables.piperId);
				json.put("password", Test_Variables.piperPassword);
				json.put("DISAPIVersion", "14.13");
				request.body(json.toString());
				Response response = request.post(Constants.api_login);
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

				HttpGet postRequest = new HttpGet(Constants.api_announcement);
				postRequest.addHeader("Content-Type", "application/json");
				postRequest.addHeader("Authorization", "Bearer "+token);

				JSONObject json1 = new JSONObject();
				JSONObject json2 = new JSONObject();
				JSONObject json3 = new JSONObject();
				JSONObject json4 = new JSONObject();
				JSONArray list = new JSONArray();

				json1.put("runId", Test_Variables.lstApiAnnouncement.get(0));
				json1.put("dateTime", Test_Variables.lstApiAnnouncement.get(1));
				json1.put("Piperid",  Test_Variables.lstApiAnnouncement.get(2));
				json1.put("MPNCalculationType", Test_Variables.lstApiAnnouncement.get(3));
				json2.put("fileName", Test_Variables.lstApiAnnouncement.get(4));
				json2.put("checksum", Test_Variables.lstApiAnnouncement.get(5));

				list.add(json2);
				json1.put("files", list);

				request_announcement.body(json1.toString());
				Response response1 = request_announcement.post(Constants.api_announcement);

				String data1 = response1.asString();
				System.out.println(data1);
				Thread.sleep(2000);

				///////////////////////////////////////////////////////////////////File Upload API////////////////////////////////////////////////////////////////////////////////

				Test_Variables.test = Test_Variables.extent.createTest("AN-Coccidia-01: Ingest Coccidia run", "This test case will run and verify  ingestion");	

				RequestSpecification request_fileupload = RestAssured.given();
				request_fileupload.header("Content-Type", "application/json");
				request_fileupload.header("Authorization", "bearer " +token);

				HttpGet postRequest1 = new HttpGet(Constants.api_FileUpload);
				postRequest1.addHeader("Content-Type", "application/json");
				postRequest1.addHeader("Authorization", "Bearer "+token);

				json3.put("runId", Test_Variables.lstCoccidiaIngest.get(0).runId);
				json3.put("checksum", Test_Variables.lstCoccidiaIngest.get(0).checksum);
				json3.put("fileName", Test_Variables.lstCoccidiaIngest.get(0).fileName);
				json3.put("fileType", Test_Variables.lstCoccidiaIngest.get(0).fileType);
				json3.put("file", Test_Variables.lstCoccidiaIngest.get(0).file);
				json3.put("fileJson", objModel.fileJson);				
				json3.put("Improc", Test_Variables.lstCoccidiaIngest.get(0).improc);
				json3.put("RunMode", "1");
				json3.put("Pathogen", objModel.pathogen);

				request_fileupload.body(json3.toString());
				Response response2 = request_fileupload.post(Constants.api_FileUpload);
				String data3 = response2.asString();
				System.out.println(data3);

				try{
					for (int x = 0;x<=100;x++) {

						String query1 = "Select count(status) as count from COCCIDA_OUTPUT where Sample_ID like '%"+Test_Variables.date0+"' and Sample_ID like '"+Test_Variables.dateYYYYMMDD+"%'";

						ResultSet rs1 = getStmt().executeQuery(query1);

						while (rs1.next()) {
							System.out.println("Count: "+rs1.getString("count"));

							if (rs1.getString("count").equals("12")) {

								int i = 1;
								while (i<=12) {
									System.out.println("'"+Test_Variables.dateYYYYMMDD+"_Cocci_"+i+"_"+Test_Variables.date0+"'");
									String query2 = "Select lane_Total_oocyst_count from COCCIDA_OUTPUT where Sample_ID = '"+Test_Variables.dateYYYYMMDD+"_Cocci_"+i+"_"+Test_Variables.date0+"'";

									ResultSet rs = getStmt().executeQuery(query2);
									while (rs.next()) {
										System.out.println("Total Count: "+rs.getString("lane_Total_oocyst_count"));
										String a = rs.getString("lane_Total_oocyst_count");
										String removeDecimal =	a.replaceAll("\\.0*$", "")	;							
										System.out.println(removeDecimal);

										String path = System.getProperty("user.dir")+"\\Excel\\SampleMetadata_Mobile.xlsx";
										FileInputStream fs = new FileInputStream(path);
										XSSFWorkbook workbook = new XSSFWorkbook(fs);
										XSSFSheet sheet = workbook.getSheetAt(0);
										Row row = sheet.getRow(i);
										Cell cell = row.getCell(1);

										DataFormatter formatter = new DataFormatter();
										String val = formatter.formatCellValue(sheet.getRow(i).getCell(1));
										System.out.println("Value: "+val);

										FileInputStream fs1 = new FileInputStream(path);
										Workbook wb = new XSSFWorkbook(fs1);
										Sheet sheet1 = wb.getSheetAt(0);
										int lastRow = sheet1.getLastRowNum();
										Row row1 = sheet1.getRow(i);
										Cell cell1 = row1.createCell(2);
										cell1.setCellValue("");
										cell1.setCellValue(removeDecimal);

										if (val != null && removeDecimal != null) {
											int difference = Integer.parseInt(val) - Integer.parseInt(removeDecimal);

											Row row2 = sheet1.getRow(i);
											Cell cell2 = row2.createCell(3);
											cell2.setCellValue("");
											cell2.setCellValue(difference);
											softAssert.assertEquals(val, difference);

										}
										else {
											System.out.println("Column was Null; cannot find difference");
										}

										FileOutputStream fos = new FileOutputStream(path);
										wb.write(fos);
										fos.close();

										i++;
									}
								}			
							}
							else {
								Thread.sleep(120000);
							}
						}
					}
					softAssert.assertAll();
					getStmt().close();		
				}

				catch (Exception e)
				{
					e.printStackTrace();
				}







				try{
					Test_Variables.test = Test_Variables.extent.createTest("AN-Coccidia-02: Verify the ingestion and relevant records from report", "This test case will verify the ingestion and relevant records from report");	

					Thread.sleep(150000);
					Helper.driver.get(Constants.url_CoccidiaLog);
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-sampleId")));
					Thread.sleep(1000);

					Test_Variables.steps.createNode("1. Click on Sample ID to expand the filter");
					ClickElement.clickById(Helper.driver, "sampleId_show-filter");			
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1000);
					Helper.driver.findElement(By.id("sampleId_view-all")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1000);
					Test_Variables.steps.createNode("2. Search for the Sample ID's against which the data is ingested");


					Helper.driver.findElement(By.id("sampleId_search-input")).clear();
					Helper.driver.findElement(By.id("sampleId_search-input")).sendKeys(objFilter.LstSampleID.get(0));
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(2000);	
					try {
						Helper.driver.findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objFilter.LstSampleID.get(0))).click();
					}
					catch(Exception ex) {
						Thread.sleep(1000);
						Helper.driver.findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objFilter.LstSampleID.get(0))).click();
					}
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(800);


					Test_Variables.steps.createNode("3. Click on Apply filter button");
					Helper.driver.findElement(By.id("sampleId_apply")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(4000);
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.NormalIngestionReportPath));
					String records = Helper.driver.findElement(By.id("results-found-count")).getText();

					softAssert.assertEquals(records, "12"); 

					softAssert.assertAll();


					Test_Variables.test.pass("Ingested data verified successfully in log");
					Test_Variables.results.createNode("Ingested data verified successfully in log");
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.NormalIngestionReportPath, null);
				}catch(AssertionError er) {
					Test_Variables.test.fail("Data ingestion verification failed");
					Test_Variables.results.createNode("Data ingestion verification failed");
					Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, new Exception(er));
				}catch(Exception ex){
					Test_Variables.test.fail("Data ingestion verification failed");
					Test_Variables.results.createNode("Data ingestion verification failed");
					Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, ex);
				}

				////////////////////////////////////////////////////////////End File Upload//////////////////////////////////////////////////////////////////////

				try {	
					Test_Variables.test = Test_Variables.extent.createTest("AN-Coccidia-03: Upload Sample MetaData File and verify the data in Report", "This test case will verify the data in report on uploading sample metedata");	
					Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
					Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
					Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

					Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
					Test_Variables.preconditions.createNode("2. Run Start Assaay and file Upload API");
					Test_Variables.steps.createNode("1. Upload Sample MetaData Template against the ingested run");
					Test_Variables.steps.createNode("2. Verify the data in Report");

					FileInputStream fsIP= new FileInputStream(new File("./Excel/"+Test_Variables.fileName));
					@SuppressWarnings("resource")
					XSSFWorkbook wb = new XSSFWorkbook(fsIP);
					XSSFSheet worksheet = wb.getSheetAt(0);
					Cell cell = null;

					if (Helper.driver.findElement(By.id("results-found-count")).getText().equals("12")) {

						for (int z=0; z<12; z++) {

							String getSampleID = Helper.driver.findElement(By.cssSelector("#row-"+z+" #col-"+Test_Elements.clSampleIDCol)).getText();
							cell=worksheet.getRow(z+1).createCell(4); 
							cell.setCellValue(getSampleID+"Updt");  

							String getResultID = Helper.driver.findElement(By.cssSelector("#row-"+z+" #col-"+Test_Elements.clResultIDCol)).getText();
							cell=worksheet.getRow(z+1).createCell(0); 
							cell.setCellValue(getResultID);  

							cell=worksheet.getRow(z+1).createCell(19); 
							cell.setCellValue(Test_Variables.CartridgeID); 

							cell=worksheet.getRow(z+1).createCell(3); 
							cell.setCellValue(Test_Variables.SampleMatrix); 

							cell=worksheet.getRow(z+1).createCell(9); 
							cell.setCellValue(Test_Variables.FlockID); 

							cell=worksheet.getRow(z+1).createCell(8); 
							cell.setCellValue(Test_Variables.RequestedAssay); 

							cell=worksheet.getRow(z+1).createCell(2); 
							cell.setCellValue(Test_Variables.KitLot); 

							cell=worksheet.getRow(z+1).createCell(6); 
							cell.setCellValue(Test_Variables.CustomerSampleID); 

							cell=worksheet.getRow(z+1).createCell(1); 
							cell.setCellValue(Test_Variables.SiteID); 

							String getLane = Helper.driver.findElement(By.cssSelector("#row-"+z+" #col-0")).getText();
							cell=worksheet.getRow(z+1).createCell(16); 
							cell.setCellValue(getLane);  

							fsIP.close();
						}

						FileOutputStream output_file =new FileOutputStream(new File("./Excel/"+Test_Variables.fileName));
						wb.write(output_file);
						output_file.close();  

						Helper.driver.get(Constants.url_dataUpload);
						Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("OrgnTypeID"))); 
						Thread.sleep(1000);
						Helper.driver.findElement(By.id("OrgnTypeID")).click();
						Helper.driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("Ancera");
						Helper.driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						Helper.driver.findElement(By.id("DataFormatId")).click();
						Helper.driver.findElement(By.cssSelector("#DataFormatId input")).sendKeys("Sample Metadata");
						Helper.driver.findElement(By.cssSelector("#DataFormatId input")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						Helper.driver.findElement(By.id("file-input")).sendKeys(Test_Variables.fileAbsolutePath+"Excel\\"+Test_Variables.fileName);
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))); 
						Thread.sleep(4000);
						Helper.driver.findElement(By.cssSelector(".fa-save")).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))); 
						Thread.sleep(2000);

						Helper.driver.get(Constants.url_CoccidiaLog);
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-sampleId")));
						Thread.sleep(1000);

						Helper.driver.findElement(By.id("sampleId_show-filter")).click();	
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(800);
						Helper.driver.findElement(By.id("sampleId_view-all")).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);
						Helper.driver.findElement(By.id("sampleId_search-input")).clear();
						Helper.driver.findElement(By.id("sampleId_search-input")).sendKeys(objFilter.LstSampleID.get(0)+"Updt");
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(2000);				

						try {
							Helper.driver.findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objFilter.LstSampleID.get(0)+"Updt")).click();
						}
						catch (Exception ex) {
							ClickElement.clickByCss(Helper.driver, "#sampleId_cust-cb-lst-txt_"+objFilter.LstSampleID.get(0)+"Updt");
						}

						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(2000);	
						Helper.driver.findElement(By.id("sampleId_apply")).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(3000);
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.NormalIngestionReportPath));		

						for (int k=0; k<12;k++) {
							int lane = k+1;
							String laneGetText = Helper.driver.findElement(By.cssSelector("tr:nth-child("+lane+") td:nth-child(3) label")).getText();
							int laneNumber = Integer.parseInt(laneGetText);

							String getSampleId = Helper.driver.findElement(By.cssSelector("#row-"+k+" #col-"+Test_Elements.clSampleIDCol+" label")).getText();
							softAssert.assertEquals(getSampleId, objFilter.LstSampleID.get(0)+"Updt");

							String getRequestedAssay = Helper.driver.findElement(By.cssSelector("#row-"+k+" #col-"+Test_Elements.clRequestedAssayCol+" label")).getText();
							softAssert.assertEquals(getRequestedAssay, Test_Variables.RequestedAssay);

							String getFlockID = Helper.driver.findElement(By.cssSelector("#row-"+k+" #col-"+Test_Elements.clFlockIDCol+" label")).getText();
							softAssert.assertEquals(getFlockID, Test_Variables.FlockID);

							String getCSampleID = Helper.driver.findElement(By.cssSelector("#row-"+k+" #col-"+Test_Elements.clCSampleIDCol+" label")).getText();
							softAssert.assertEquals(getCSampleID, Test_Variables.CustomerSampleID);

							String getSampleMatrix = Helper.driver.findElement(By.cssSelector("#row-"+k+" #col-"+Test_Elements.clSampleMatrixCol+" label")).getText();
							softAssert.assertEquals(getSampleMatrix, Test_Variables.SampleMatrix);

							String getKitLot = Helper.driver.findElement(By.cssSelector("#row-"+k+" #col-"+Test_Elements.clKitLotCol+" label")).getText();
							softAssert.assertEquals(getKitLot, Test_Variables.KitLot);

							String getSampleID = Helper.driver.findElement(By.cssSelector("#row-"+k+" #col-"+Test_Elements.clSampleIDCol+" label")).getText();

							Helper.driver.findElement(By.id("audit-trial-"+k)).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
							Thread.sleep(1000);		

							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.NormalIngestionReportPath));

							String getAuditSampleID = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.clAuditSampleIDCol+".text-dark")).getText();
							softAssert.assertEquals(getAuditSampleID, getSampleID);

							String getAuditAction = Helper.driver.findElement(By.id("audit-action-0")).getText();
							softAssert.assertEquals(getAuditAction, "Modified");

							String getAuditResultStatus = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.clAuditResultStatusCol+".text-dark")).getText();
							softAssert.assertEquals(getAuditResultStatus, "Completed");

							String getAuditCartridgeId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.clAuditCartridgeIDCol+".text-dark")).getText();
							softAssert.assertEquals(getAuditCartridgeId, objModel.cartridgeID);

							String getAuditInstrumentId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.clAuditInstrumentIDCol+".text-dark")).getText();
							softAssert.assertEquals(getAuditInstrumentId, Test_Variables.InstrumentID);

							String getAuditPiperUser = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-23.text-dark")).getText();
							Assert.assertEquals(getAuditPiperUser.isBlank(), false, "Piper User is empty");

							String getAuditImprocVersion = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.clAuditImprocIDCol+".text-dark")).getText();
							softAssert.assertEquals(getAuditImprocVersion, Test_Variables.ImprocVersion);

							String getAuditTestSiteId = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.clAuditTestSiteIDCol+".text-dark")).getText();
							softAssert.assertTrue(getAuditTestSiteId.isEmpty() == false);

							String getAuditTestSiteName = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) #col-"+Test_Elements.clAuditTestSiteNameCol+".text-dark")).getText();
							softAssert.assertTrue(getAuditTestSiteName.isEmpty() == false);

							Helper.driver.findElement(By.cssSelector(Test_Elements.closeAudit)).click(); 
							softAssert.assertAll();
						}
						Test_Variables.test.pass("Data Verified successfully on uploading Sample Metadata Template");
						Test_Variables.results.createNode("Data Verified successfully on uploading Sample Metadata Template");
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.NormalIngestionReportPath, null);
					}
					else {
						Test_Variables.results.createNode("12 records not displaying in table hence file upload method not executed");
						System.out.println("12 records not displaying in table hence file upload method not executed");
					}
				}
				catch(AssertionError er) {
					Test_Variables.test.fail("Data failed to verify on uploading Sample Metadata Template");
					Test_Variables.results.createNode("Data failed to verify on uploading Sample Metadata Template");
					Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, new Exception(er));
				}catch(Exception ex){
					Test_Variables.test.fail("Data failed to verify on uploading Sample Metadata Template");
					Test_Variables.results.createNode("Data failed to verify on uploading Sample Metadata Template");
					Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, ex);	
				}
				Thread.sleep(2000);	
			}
		}
	}


	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		DB_Config.getStmt();
		DB_Config.setStmt(getStmt());
		//	Helper.driver.close();
	}

}
