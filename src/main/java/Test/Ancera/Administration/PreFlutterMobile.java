package Test.Ancera.Administration;

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

	String name = "none";
	
	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Pre_Flutter_Mobile"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Pre Flutter Mobile Test Report"); 

		Helper.config();
		ConfigureLogin.login();
		DB_Config.test();
	}


	@Test (enabled = true, priority = 1) 
	public void CreateOrganization() throws InterruptedException, IOException {
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


	@Test (enabled= true, priority= 2) 
	public void CreateOrganizationSitesHierarchy() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-OM-31-38: Verify Complete Organization Site Hierarchy", "This test case will verify complete site hierarchy");

			SoftAssert softAssert = new SoftAssert();

			Helper.driver.get(Constants.url_organization);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1500);
			
			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.orgNameCol+" label")).getText().equals(ComplexConfigModel.organizationName)) {
					Helper.driver.findElement(By.id("edit-orgn-sites-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					break;
				}
			}

			Thread.sleep(1000);	
			
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
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys("TestComplexSite_"+Test_Variables.dateYYYYMMDD);
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
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys(ComplexConfigModel.organizationFarm1Name);
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(2000);
			Test_Variables.steps.createNode("10. Verify Farm Site can be saved");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New site created.");

			Test_Variables.steps.createNode("11. Create House 1");
			Helper.driver.findElement(Test_Elements.orgAddSite5).click();
			Thread.sleep(2000);
			Helper.driver.findElement(Test_Elements.orgSiteTypeInputChild).click();
			String HouseType = Helper.driver.findElement(By.cssSelector("div .ng-option:nth-child(1)")).getText();	
			softAssert.assertEquals(HouseType, "House");
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys(ComplexConfigModel.organizationHouse1Name);
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(2000);
			Test_Variables.steps.createNode("12. Verify House Site can be saved");
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New site created.");
			
			Test_Variables.steps.createNode("13. Create house 2");
			Helper.driver.findElement(Test_Elements.orgAddSite5).click();
			Thread.sleep(2000);
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys(ComplexConfigModel.organizationHouse2Name);
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(2000);
			
			Test_Variables.steps.createNode("14. Create house 3");
			Helper.driver.findElement(Test_Elements.orgAddSite5).click();
			Thread.sleep(2000);
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys(ComplexConfigModel.organizationHouse3Name);
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(1000);
			
			Test_Variables.steps.createNode("15. Create house 4");
			Helper.driver.findElement(Test_Elements.orgAddSite5).click();
			Thread.sleep(2000);
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys(ComplexConfigModel.organizationHouse4Name);
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(1000);
			
			Test_Variables.steps.createNode("16. Create house 5");
			Helper.driver.findElement(Test_Elements.orgAddSite5).click();
			Thread.sleep(2000);
			Helper.driver.findElement(Test_Elements.orgSiteNameInput).sendKeys(ComplexConfigModel.organizationHouse5Name);
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(1000);
			
			
			
			
//			Helper.driver.findElement(By.cssSelector("li ul li ul li ul li ul li ul li ")).click();
//			Thread.sleep(3000);
//			name  = Helper.driver.findElement(By.cssSelector("#num-SiteIDId")).getAttribute("value");
//			System.out.println(name);
			
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
	
	

	@Test (enabled= true, priority= 3) 
	public void VerifyTestingSitesAccess() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-UM-14: Verify Sites column displays Active after assigning All Testing Sites to the user", "This test case will verify Sites column displays Active after assigning sites to the user");

			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.usercreateButton));
			Thread.sleep(3000);

			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.login_email)) {
					WebElement filter_scroll = Helper.driver.findElement(By.id("edit-user-"+i));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll);
					Thread.sleep(2000);

					Helper.driver.findElement(By.id("edit-user-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(4000);

					Helper.driver.findElement(Test_Elements.popupNextButton).click();
					Helper.driver.findElement(Test_Elements.popupNextButton).click();
					Thread.sleep(750);

					Helper.driver.findElement(Test_Elements.userSitesButton).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(2000);

					Helper.driver.findElement(Test_Elements.userSitesSearch).sendKeys(ComplexConfigModel.organizationName);
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(2000);
					Helper.driver.findElement(By.xpath("//*[text()='"+ComplexConfigModel.organizationName+"']")).click();
					Thread.sleep(1000);
					Helper.driver.findElement(Test_Elements.userSitesSaveButton).click();
					Thread.sleep(1000);
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("User Management", Constants.UserManagementReportPath));
					Helper.driver.findElement(Test_Elements.popupSaveButton).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(1500);
					break;
				}
			}
			Assert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "User details updated.");	
			Test_Variables.test.pass("Site Assigned to user successfully");
			Test_Variables.results.createNode("Site Assigned to user successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.PreFlutterMobileReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ProgramManagementReportPath, null);
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


	@SuppressWarnings("unused")
	@Test (enabled= true, priority= 4) 
	public void CreateProgramVaccine() throws InterruptedException, IOException {
		try {		
			Test_Variables.test = Test_Variables.extent.createTest("AN-Program-02: Verify that user is able to create new Vaccine program", "This testcase will verify that user is able to create new program");

			Helper.driver.get(Constants.url_programManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			
			Thread.sleep(2000);

			Helper.driver.findElement(Test_Elements.programVaccineProgramTab).click();
			Thread.sleep(1500);
			
			for (int j=1;j<Helper.driver.findElements(Test_Elements.programColumn1).size();j++) {
				if (Helper.driver.findElement(By.cssSelector("#row-"+j+" #col-0 label")).getText().equals(ComplexConfigModel.vaccineName)) {
					break;
				}

				else {
					Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(Test_Elements.programCreateButton));
					Helper.driver.findElement(Test_Elements.programCreateButton).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(3000);
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

					break;
				}
			}
			Assert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New program has been created successfully"); 
			Test_Variables.test.pass("New Program created successfully");
			Test_Variables.results.createNode("New Program created successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.ProgramManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ProgramManagementReportPath, null);
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


	@SuppressWarnings("unused")
	@Test (enabled= true, priority= 5) 
	public void CreateProgramFeed() throws InterruptedException, IOException {
		try {		
			Test_Variables.test = Test_Variables.extent.createTest("AN-Program-05: Verify that user is able to create new Feed program", "This testcase will verify that user is able to create new program");

			Helper.driver.get(Constants.url_programManagement);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1500);

			Helper.driver.findElement(Test_Elements.programFeedProgramTab).click();
			Thread.sleep(1500);
			String rowCount = Helper.driver.findElement(By.cssSelector("#"+Test_Elements.programFeedTable+" #"+Test_Elements.ResultsCount)).getText();

			for (int j=1;j<Integer.parseInt(rowCount);j++) {
				if (Helper.driver.findElement(By.cssSelector("#"+Test_Elements.programFeedTable+" #row-"+j+" #col-0 label")).getText().equals(ComplexConfigModel.feedName)) {
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


					Helper.driver.findElement(By.cssSelector("#startDate img")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(2000);

					WebElement dateWidgetTo = Test_Elements.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#startDate .dp-popup"))).get(0);

					List<WebElement> columns1 = dateWidgetTo.findElements(By.tagName("button"));
					DateUtil.clickGivenDay(columns1, DateUtil.getFirstDay());
					Thread.sleep(2000);

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
					break;
				}
			}
			Assert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New program has been created successfully"); 
			Test_Variables.test.pass("New Program created successfully");
			Test_Variables.results.createNode("New Program created successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Program Management", Constants.ProgramManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.ProgramManagementReportPath, null);

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



	@Test (description="Test Case: Create complex Configurations",enabled= true, priority = 6) 
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
			Helper.driver.findElement(Test_Elements.complexSearchComplex).sendKeys("TestComplexSite_"+Test_Variables.dateYYYYMMDD);
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.complexSelectComplexSite).click();

			Helper.driver.findElement(Test_Elements.complexSelectProgramType).sendKeys("Vaccine");
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.complexSelectProgramType).sendKeys(Keys.ENTER);

			Helper.driver.findElement(Test_Elements.complexSelectProgramId).sendKeys(ComplexConfigModel.vaccineName);
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.complexSelectProgramId).sendKeys(Keys.ENTER);
			
			By addProgramButton = RelativeLocator.with(By.tagName("button")).toRightOf(Test_Elements.complexSelectProgramId);
			Helper.driver.findElement(addProgramButton).click();
			
			Helper.driver.findElement(Test_Elements.complexSelectProgramType).sendKeys("Feed");
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.complexSelectProgramType).sendKeys(Keys.ENTER);

			Helper.driver.findElement(Test_Elements.complexSelectProgramId).sendKeys(ComplexConfigModel.feedName);
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.complexSelectProgramId).sendKeys(Keys.ENTER);
			
			Helper.driver.findElement(addProgramButton).click();

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
				By addConfigButton = RelativeLocator.with(By.tagName("button")).toRightOf(Test_Elements.complexUpperLimit);
				Helper.driver.findElement(addConfigButton).click();
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

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	@SuppressWarnings({ "unchecked", "unused" })
	@Test (enabled= true, priority= 8) 
	public void Ingestion_Flock() throws InterruptedException, IOException, SQLException	{
		Test_Variables.lstComplexConfig = ComplexConfigModel.FillDataCocci();
		
		for (ComplexConfigModel objModel : Test_Variables.lstComplexConfig) { 
			
			if(objModel.createFlock) {
				try {
					Test_Variables.test = Test_Variables.extent.createTest("AN-Flock: Verify user can create Flock", "This test case will verify that user can crate flock");
					Helper.driver.get(Constants.url_flockRegistration);
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(2000);

					Helper.driver.findElement(Test_Elements.flockCreateButton).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	

					Helper.driver.findElement(By.id("add-flock")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
					Thread.sleep(1000);
					
					Helper.driver.findElement(Test_Elements.flockIntegratorFlockID).sendKeys(ComplexConfigModel.flockIntegratorID);
					if (Helper.driver.findElements(By.cssSelector("#integratorFlockId .list-item")).size() != 0) {
						Helper.driver.findElement(By.cssSelector("#integratorFlockId .list-item")).click();
					}
					else {
						Helper.driver.findElement(By.xpath("//*[text()='"+ComplexConfigModel.flockIntegratorID+"']")).click();		
					}

					Helper.driver.findElement(Test_Elements.flockBirdSizeExpandDropDown).click();
					List<WebElement> birdSizeList = Helper.driver.findElements(By.cssSelector(".ng-option"));
					birdSizeList.get(objModel.birdSize).click();

					Helper.driver.findElement(Test_Elements.flockFarmExpandDropdown).click();

					Helper.driver.findElement(Test_Elements.flockFarmSeach).sendKeys(ComplexConfigModel.organizationFarm1Name);
					Thread.sleep(2500);
					Helper.driver.findElement(By.xpath("//b[text() = '"+ComplexConfigModel.organizationFarm1Name+"']")).click();
					Thread.sleep(1500);
					WebElement scroll = Helper.driver.findElement(Test_Elements.flockProgramDetailsDots);
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Thread.sleep(1000);
					Helper.driver.findElement(Test_Elements.flockHousePlacementDots).click();
					
					Helper.driver.findElement(By.cssSelector("#placementDate img")).click();
					Thread.sleep(1000);		
					WebElement dateWidgetTo = Test_Elements.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#placementDate .dp-popup"))).get(0);
					List<WebElement> columns1 = dateWidgetTo.findElements(By.tagName("button"));
					DateUtil.clickGivenDay(columns1, DateUtil.getDay("01"));
					Thread.sleep(2000);

					Helper.driver.findElement(By.xpath("//div[1]/div[3]/div[1]/ng-select[1]/div[1]/div[1]/div[2]/input[1]")).click();
					//Helper.driver.findElement(Test_Elements.flockSelectHouses).click();
					Thread.sleep(1000);
					
					
					for(int i = 0; i<objModel.LstHouses.size(); i++) {
						Helper.driver.findElement(By.xpath("//*[text() = '"+objModel.LstHouses.get(i)+"']")).click();;
					}
					
					
					Helper.driver.findElement(Test_Elements.flockHouseSaveButton).click();
					Thread.sleep(1000);
					Helper.driver.findElement(Test_Elements.flockProgramDetailsDots).click();
					Thread.sleep(1000);
					Helper.driver.findElement(Test_Elements.flockProgramExpandDropDown).sendKeys(objModel.program);	
					Helper.driver.findElement(Test_Elements.flockProgramExpandDropDown).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
				
					if (objModel.program.equals(ComplexConfigModel.vaccineName)) {
						Helper.driver.findElement(Test_Elements.flockAdministrationMethod).sendKeys("CmsAdminMethod");
						if (Helper.driver.findElements(By.xpath("//*[text()='Add New + ']")).size() != 0) {
							Helper.driver.findElement(By.xpath("//*[text()='Add New + ']")).click();
						}
						else {
							Helper.driver.findElement(By.xpath("//*[text()='CmsAdminMethod']")).click();		
						}
					}
		
					Helper.driver.findElement(Test_Elements.flockProgramSaveButton).click();
					Thread.sleep(1000);
					Helper.driver.findElement(Test_Elements.popupSaveButton).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(1000);
					Assert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "Data saved successfully.");
					System.out.println("Flock created successfully");

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

								Helper.driver.get(Constants.url_CoccidiaLog);
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort-sampleId")));
								Thread.sleep(3000);

								Test_Variables.steps.createNode("1. Click on Sample ID to expand the filter");
								ClickElement.clickById(Helper.driver, "sampleId_show-filter");			
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(1000);
								Helper.driver.findElement(By.id("sampleId_view-all")).click();
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(1000);
								Test_Variables.steps.createNode("2. Search for the Sample ID's against which the data is ingested");							

								Helper.driver.findElement(By.id("sampleId_search-input")).clear();
								Helper.driver.findElement(By.id("sampleId_search-input")).sendKeys(objModel.SampleID);
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								Thread.sleep(2000);	
								try {
									Helper.driver.findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objModel.SampleID)).click();
								}
								catch(Exception ex) {
									Thread.sleep(1000);
									Helper.driver.findElement(By.cssSelector("#sampleId_cust-cb-lst-txt_"+objModel.SampleID)).click();
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
								Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.NormalIngestionReportPath));
								Test_Variables.test.pass("Run ingested successfully");
								Test_Variables.results.createNode("Run ingested successfully");
								Helper.saveResultNew(ITestResult.SUCCESS, Constants.DataUploadReportPath, null);
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
						Test_Variables.test.fail("Data failed to verify on uploading Sample Metadata Template");
						Test_Variables.results.createNode("Data failed to verify on uploading Sample Metadata Template");
						Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, ex);	
					}
				
				catch(AssertionError er) {
					Test_Variables.test.fail("Ingestion failed");
					Test_Variables.results.createNode("Ingestion failed");
					Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, new Exception(er));
				}

				////////////////////////////////////////////////////////////End File Upload//////////////////////////////////////////////////////////////////////

				try {	
					Test_Variables.test = Test_Variables.extent.createTest("AN-Coccidia-03: Upload Sample MetaData File and verify the data in Report", "This test case will verify the data in report on uploading sample metedata");	
					Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
					Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
					Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

					FileInputStream fsIP= new FileInputStream(new File("./Excel/"+Test_Variables.fileName_Mobile));
					@SuppressWarnings("resource")
					XSSFWorkbook wb = new XSSFWorkbook(fsIP);
					XSSFSheet worksheet = wb.getSheetAt(0);
					Cell cell = null;
					
					if (Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText().equals("12")) {
						for (int z=0; z<12; z++) {

							String getResultID = Helper.driver.findElement(By.cssSelector("#row-"+z+" #col-"+Test_Elements.clResultIDCol+" label")).getText();
							//System.out.println("1: "+getResultID);
							cell=worksheet.getRow(z+1).createCell(Test_Elements.metadata_ResultID); 
							cell.setCellValue(getResultID);  

//							String selectQuerySite = "Select siteUniqueNumber from dbo.Site where sitename = 'TestHouse1_'"+Test_Variables.dateYYYYMMDD;
//							ResultSet rs1 = getStmt().executeQuery(selectQuerySite);
//							while (rs1.next()) {
//								System.out.println("Unique Site ID: "+rs1.getString("siteUniqueNumber"));
//								cell=worksheet.getRow(z+1).createCell(Test_Elements.metadata_CollectionSiteID); 
//								cell.setCellValue(rs1.getString("siteUniqueNumber")); 
//							}
							
							String getCollectionSiteID = Helper.driver.findElement(By.cssSelector("#row-"+z+" #col-"+Test_Elements.clCollectionSiteIDCol+" label")).getText();
							cell=worksheet.getRow(z+1).createCell(Test_Elements.metadata_CollectionSiteID); 
							cell.setCellValue(getCollectionSiteID); 

							String getSampleID = Helper.driver.findElement(By.cssSelector("#row-"+z+" #col-"+Test_Elements.clSampleIDCol+" label")).getText();
							cell=worksheet.getRow(z+1).createCell(Test_Elements.metadata_LabSampleID); 
							cell.setCellValue(getSampleID);  
							
							String selectQuery = "Select unique_Flock_id from dbo.flock_mgmt where integrator_flock_id = '"+ComplexConfigModel.flockIntegratorID+"' and Bird_Size = '"+objModel.birdSizeName+"'";
							ResultSet rs = getStmt().executeQuery(selectQuery);
							while (rs.next()) {
								String flockID = rs.getString("unique_flock_id");
								//System.out.println("Unique Flock ID: "+flockID);
								cell=worksheet.getRow(z+1).createCell(Test_Elements.metadata_FlockID); 
								cell.setCellValue(flockID); 
							}
	
							String getComplex = Helper.driver.findElement(By.cssSelector("#row-"+z+" #col-"+Test_Elements.clComplexCol+" label")).getText();
							cell=worksheet.getRow(z+1).createCell(Test_Elements.metadata_Complex); 
							cell.setCellValue(getComplex); 

							String getFarm = Helper.driver.findElement(By.cssSelector("#row-"+z+" #col-"+Test_Elements.clFarmCol+" label")).getText();
							cell=worksheet.getRow(z+1).createCell(Test_Elements.metadata_Farm); 
							cell.setCellValue(getFarm); 

							String getLane = Helper.driver.findElement(By.cssSelector("#row-"+z+" #col-"+Test_Elements.clLaneCol+" label")).getText();
							cell=worksheet.getRow(z+1).createCell(Test_Elements.metadata_Lane); 
							cell.setCellValue(getLane);  

							String getResultDate = Helper.driver.findElement(By.cssSelector("#row-"+z+" #col-"+Test_Elements.clDateCol+" label")).getText();
							cell=worksheet.getRow(z+1).createCell(Test_Elements.metadata_ResultDate); 
							cell.setCellValue(getResultDate);

							String getresultTime = Helper.driver.findElement(By.cssSelector("#row-"+z+" #col-"+Test_Elements.clTimeCol+" label")).getText();
							cell=worksheet.getRow(z+1).createCell(Test_Elements.metadata_ResultTime); 
							cell.setCellValue(getresultTime); 

							cell=worksheet.getRow(z+1).createCell(Test_Elements.metadata_CartridgeID); 
							cell.setCellValue(ComplexConfigModel.cartridgeID); 

							cell=worksheet.getRow(z+1).createCell(Test_Elements.metadata_InstrumentID); 
							cell.setCellValue(Test_Variables.InstrumentID);

							String getPiperUser = Helper.driver.findElement(By.cssSelector("#row-"+z+" #col-"+Test_Elements.clPiperUserCol)).getText();
							cell=worksheet.getRow(z+1).createCell(Test_Elements.metadata_PiperUser); 
							cell.setCellValue(getPiperUser);

							cell=worksheet.getRow(z+1).createCell(Test_Elements.metadata_CollectionDate); 
							cell.setCellValue(objModel.CollectionDatee);

							fsIP.close();
						}
					//	getStmt().close();	

						FileOutputStream output_file =new FileOutputStream(new File("./Excel/"+Test_Variables.fileName_Mobile));
						wb.write(output_file);
						output_file.close();  

						Helper.driver.get(Constants.url_dataUpload);
						Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("OrgnTypeID"))); 
						Thread.sleep(000);
						Helper.driver.findElement(By.id("OrgnTypeID")).click();
						Helper.driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("Ancera");
						Helper.driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						Helper.driver.findElement(By.id("DataFormatId")).click();
						Helper.driver.findElement(By.cssSelector("#DataFormatId input")).sendKeys("Sample Metadata");
						Helper.driver.findElement(By.cssSelector("#DataFormatId input")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						Helper.driver.findElement(By.id("file-input")).sendKeys(Test_Variables.fileAbsolutePath+"Excel\\"+Test_Variables.fileName_Mobile);
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))); 
						Thread.sleep(4000);
						Helper.driver.findElement(By.cssSelector(".fa-save")).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))); 
						Thread.sleep(6000);
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.NormalIngestionReportPath));
						Assert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), Test_Variables.fileName_Mobile+" saved successfully.");
						System.out.println("Template created successfully");
						Test_Variables.test.pass("Template saved successfully");
						Test_Variables.results.createNode("Template saved successfully");
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.DataUploadReportPath, null);
					}
					else {
						Test_Variables.results.createNode("12 records not displaying in table hence file upload method not executed");
						Test_Variables.test.skip("12 records not displaying in table hence file upload method not executed");
						Test_Variables.results.createNode("12 records not displaying in table hence file upload method not executed");
						Helper.saveResultNew(ITestResult.SKIP, Constants.DataUploadReportPath, null);
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
		getStmt().close();	
	}

	

	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		DB_Config.getStmt();
		DB_Config.setStmt(getStmt());
		//	Helper.driver.close();
	}

}
