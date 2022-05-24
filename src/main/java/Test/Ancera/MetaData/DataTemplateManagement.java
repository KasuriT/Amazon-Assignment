package Test.Ancera.MetaData;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Models.DataTemplateModel;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;


public class DataTemplateManagement{


	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/MetaData_Template_Management"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Data TemplateManagement Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}


	@Test (description="Test Case: Navigate to Data Template Management Template",enabled=true, priority= 1) 
	public void Navigate() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-DTM-01: Verify user can navigate to Data Template Management Template Screen", "This test case will verify user can navigate to Data Template Management Template Screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.steps.createNode("1. Hover to sidebar to expand the menu");
			Test_Variables.steps.createNode("2. Click on MetaData and select Data Template Management");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
			Helper.driver.get(Constants.url_dataTemplate);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElement(By.cssSelector("#screen-header p")).getText(), "Data Template Management"); 
			Test_Variables.test.pass("User navigated successfully");
			Test_Variables.results.createNode("User navigated to Data Template Management Screen successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.DataTemplateManagementReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("User failed to navigate");
			Test_Variables.results.createNode("User failed to navigate");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("User failed to navigate");
			Test_Variables.results.createNode("User failed to navigate");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, ex);
		}
	}


	@Test (description="Exceptional Flow: Reset field check", enabled= true, priority= 2) 
	public void ResetFieldCheck() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-DTM-02: Verify user can reset Create Template fields", "This test case will verify that user is able to reset create template field");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Data Template Management; Data Template Management screen opens");
			Test_Variables.steps.createNode("1. Click on Create New button");
			Test_Variables.steps.createNode("2. Enter template name and description");
			Test_Variables.steps.createNode("3. Click on reset button");

			Helper.driver.findElement(Test_Elements.dtmCreateButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.dtmName).sendKeys("Test");
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.dtmDesc).sendKeys("Description");
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
			Helper.driver.findElement(Test_Elements.popupResetButton).click();
			Thread.sleep(1500);

			String nameActual = Helper.driver.findElement(Test_Elements.dtmName).getText();
			String descActual = Helper.driver.findElement(Test_Elements.dtmDesc).getText();	

			Assert.assertTrue(nameActual.isEmpty(), "Name field was not reset");
			Assert.assertTrue(descActual.isEmpty(), "Description field was not reset");
			Test_Variables.test.pass("Template fields reset successfully");
			Test_Variables.results.createNode("Template fields reset successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.DataTemplateManagementReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("Template fields failed to reset");
			Test_Variables.results.createNode("Template fields failed to reset");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Template fields failed to reset");
			Test_Variables.results.createNode("Template fields failed to reset");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, ex);
		}
	}	


	@Test (description="Exceptional Flow: Column Reset field check", enabled= true, priority= 3) 
	public void ClmResetFieldCheck() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-DTM-03: Verify user can reset Template Column fields", "This test case will verify that user is able to reset template column fields");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Data Template Management; Data Template Management screen opens");
			Test_Variables.steps.createNode("1. Click on Create New button");
			Test_Variables.steps.createNode("2. Enter data in template column fields");
			Test_Variables.steps.createNode("3. Click on reset button");

			Helper.driver.findElement(Test_Elements.dtmClmName).sendKeys("Test");
			Thread.sleep(750);
			Helper.driver.findElement(Test_Elements.dtmClmDefaultValue).sendKeys("100");
			Thread.sleep(750);
			Helper.driver.findElement(Test_Elements.dtmClmLength).sendKeys("100");
			Thread.sleep(750);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
			Helper.driver.findElement(By.id("btn-reset-field")).click();
			Thread.sleep(1000);

			String nameActual = Helper.driver.findElement(By.id("ColNameID")).getText();
			String defaultValueActual = Helper.driver.findElement(By.id("defaultValueId")).getText();	
			String lengthActual = Helper.driver.findElement(By.id("colLengthId")).getText();

			SoftAssert softAssert = new SoftAssert();
			softAssert.assertTrue(nameActual.isEmpty(), "Name field was not reset");
			softAssert.assertTrue(defaultValueActual.isEmpty(), "Description field was not reset");
			softAssert.assertTrue(lengthActual.isEmpty(), "Description field was not reset");
			softAssert.assertAll();
			Test_Variables.test.pass("Template fields reset successfully");
			Test_Variables.results.createNode("Template fields reset successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.DataTemplateManagementReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("Template column fields failed to reset");
			Test_Variables.results.createNode("Template fields failed to reset");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Template column fields failed to reset");
			Test_Variables.results.createNode("Template fields failed to reset");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, ex);
		}
	}	



	@Test (description="Exceptional Flow: Mandatory field check", enabled= true, priority= 4) 
	public void MandatoryFieldCheck() throws InterruptedException, IOException {

		Helper.driver.get(Constants.url_dataTemplate);	
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(1500);
		Helper.driver.findElement(Test_Elements.dtmCreateButton).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(1000);

		for (DataTemplateModel objModel : Test_Variables.lstDTMMandatoryCheck) {

			Test_Variables.test = Test_Variables.extent.createTest(objModel.testcaseTitle, objModel.testcaseDesc);
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Data Template Management; Data Template Management screen opens");
			Test_Variables.preconditions.createNode("5. Click on Create New button, popup opens");
			Test_Variables.steps.createNode(objModel.step1);
			Test_Variables.steps.createNode("2. Click on save button");

			Helper.driver.findElement(Test_Elements.popupResetButton).click();
			Helper.driver.findElement(Test_Elements.dtmName).sendKeys(objModel.dtmName);
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.dtmDesc).sendKeys(objModel.dtmDesc);	
			Thread.sleep(1000);

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
			Helper.driver.findElement(Test_Elements.popupSaveButton).click(); 
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(2000);

			if (Helper.driver.findElements(Test_Elements.alertMessage).size() != 0) {
				Actions actions = new Actions(Helper.driver);
				actions.moveToElement(Helper.driver.findElement(Test_Elements.alertMessageClose)).click().perform();
			}


			try {

				if (objModel.chkMandatoryFieldsS1)
				{
					if ( objModel.dtmName.isEmpty())
					{
						Assert.assertEquals(Helper.driver.findElements(Test_Elements.dtmNameValidation).size(), 1); 
					}

					if ( objModel.dtmDesc.isEmpty())
					{
						Assert.assertEquals(Helper.driver.findElements(Test_Elements.dtmDescValidation).size(), 1); 
					}

					Test_Variables.test.pass(objModel.passScenario);
					Test_Variables.results.createNode(objModel.passScenario);
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.DataTemplateManagementReportPath, null);
					continue;
				}

			}catch(AssertionError er) {
				Test_Variables.test.fail("Mandatory check failed");
				Test_Variables.results.createNode("Mandatory check failed");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, new Exception(er));
			}
			catch(Exception ex) {
				Test_Variables.test.fail("Mandatory check failed");
				Test_Variables.results.createNode("Mandatory check failed");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, ex);
			}


			if (objModel.chkAlert)
			{
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
				Thread.sleep(1000);
				String actual = Helper.driver.findElement(By.id("message")).getText();
				String expected = "Please select one column as key field";

				try{
					Assert.assertEquals(actual, expected); 
					Test_Variables.test.pass("Not able to add template without atleast one column successfully");
					Test_Variables.results.createNode(objModel.passScenario);
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.DataTemplateManagementReportPath, null);
				}catch(AssertionError e){
					Test_Variables.test.fail("Not Able to add template without atleast one column failed");
				}
			}

			try {
				if (objModel.chkClm)
				{

					Thread.sleep(1000);
					Helper.driver.findElement(By.id("btn-reset-field")).click();
					Helper.driver.findElement(Test_Elements.dtmClmName).sendKeys(objModel.clmName);
					Thread.sleep(1000);

					if(objModel.clmType) {
						Helper.driver.findElement(By.id("ColTypeId")).click();
						Thread.sleep(1000);
						Helper.driver.findElement(By.cssSelector(".ng-option:nth-child(1)")).click();			
					}

					Helper.driver.findElement(By.id("colLengthId")).sendKeys(objModel.clmLength);	
					Thread.sleep(1000);


					Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-add-field")));
					Helper.driver.findElement(By.id("btn-add-field")).click(); 
					Thread.sleep(1000);

				}
				if (objModel.chkS2)
				{

					if ( objModel.clmName.isEmpty())
					{
						Assert.assertEquals(Helper.driver.findElements(Test_Elements.dtmNameValidation).size(), 1); 
					}

					if ( objModel.clmLength.isEmpty())
					{
						Assert.assertEquals(Helper.driver.findElements(Test_Elements.dtmNameValidation).size(), 1); 
					}
					Test_Variables.test.pass(objModel.passScenario);
					Test_Variables.results.createNode(objModel.passScenario);
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.DataTemplateManagementReportPath, null);
					continue;
				}
			}
			catch(AssertionError e){
				Test_Variables.test.fail(objModel.failScenario);
				Test_Variables.results.createNode(objModel.failScenario);	
			}


			if (objModel.verifyClm) {

				WebElement filter_scroll = Helper.driver.findElement(By.id("edit-field-1"));
				((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 

				int actual =	Helper.driver.findElements(By.cssSelector(".dataformat-table-container tr")).size();
				try{
					Assert.assertEquals(actual, 2); 
					Test_Variables.test.pass("Column added successfully");
					Test_Variables.results.createNode(objModel.passScenario);
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.DataTemplateManagementReportPath, null);
				}catch(AssertionError e){
					Test_Variables.test.fail("Column adding failed");
				}

			}		
		}
	}


	@Test (description="Test Case: Create Template Column",enabled= true, priority= 5) 
	public void CreateTemplate() throws InterruptedException, IOException {
		try {		
			Test_Variables.test = Test_Variables.extent.createTest("AN-DTM-10: Verify that user is able to create new templete", "This testcase will verify that user is able to create template");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Data Template Management; Data Template Management screen opens");
			Test_Variables.steps.createNode("1. Verify that the Result Found label shows the right count of the existing templates on the screen");
			Test_Variables.steps.createNode("2. Click on Create New button");
			Test_Variables.steps.createNode("3. Add valid data in all fields");
			Test_Variables.steps.createNode("4. Add a new column");
			Test_Variables.steps.createNode("5. Click on save button");

			SoftAssert softAssert = new SoftAssert();
			Helper.driver.get(Constants.url_dataTemplate);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(2000);
			int rowsActual = Helper.driver.findElements(By.cssSelector("tr")).size();
			String rowsExpected = Helper.driver.findElement(By.id("results-found-count")).getText();
			softAssert.assertEquals(rowsActual-1, Integer.parseInt(rowsExpected), "Does not match the expected rows");

			Helper.driver.findElement(Test_Elements.dtmCreateButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);

			Helper.driver.findElement(Test_Elements.popupResetButton).click();
			Helper.driver.findElement(Test_Elements.dtmName).sendKeys(Test_Variables.TemplateName);
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.dtmDesc).sendKeys("Test Template created by Automation script");

			Helper.driver.findElement(Test_Elements.dtmClmReset).click();
			Helper.driver.findElement(Test_Elements.dtmClmName).sendKeys("ID");
			Helper.driver.findElement(Test_Elements.dtmClmType).click();
			Thread.sleep(1000);

			String[] stringArray = {"Date", "String", "Decimal", "Time", "Integer"};
			List<WebElement> op = Helper.driver.findElements(By.cssSelector(".ng-option-label"));
			int size = op.size();
			for(int i =0; i<size ; i++){
				String options = op.get(i).getText();
				System.out.println("Options: "+options);
				softAssert.assertEquals(options, stringArray[i]);
			}

			Helper.driver.findElement(By.cssSelector(".ng-option:nth-child(1)")).click();
			Helper.driver.findElement(Test_Elements.dtmClmLength).clear();
			Helper.driver.findElement(Test_Elements.dtmClmLength).sendKeys("25");	
			Helper.driver.findElement(Test_Elements.dtmClmAdd).click();
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Thread.sleep(1000);

			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "Please select one column as Key Field");
			Helper.driver.findElement(Test_Elements.dtmClmEdit).click();
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.dtmKeyField).click();
			Helper.driver.findElement(Test_Elements.dtmClmSave).click();
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "New data template created."); 
			softAssert.assertEquals(Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(3) label")).getText(), Test_Variables.dateMMDDYYYY1);
			softAssert.assertAll();
			Test_Variables.test.pass("New Template created successfully");
			Test_Variables.results.createNode("New Template created successfully; displays  an alert message 'New data template created.'");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.DataTemplateManagementReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("New Template failed to create");
			Test_Variables.results.createNode("New Template failed to create");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			Test_Variables.test.fail("New Template failed to create");
			Test_Variables.results.createNode("New Template failed to create");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, ex);
		}
		Helper.driver.findElement(Test_Elements.alertMessageClose).click();
	}




	@Test (description="Test Case: Update Template Column",enabled= true, priority= 6) 
	public void UpdateTemplateColumn() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-DTM-11: Verify user can update Template Column", "This test case will verify that user can update templates column");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Data Template Management; Data Template Management screen opens");
			Test_Variables.preconditions.createNode("5. Create a new template");
			Test_Variables.steps.createNode("1. Click on update icon next to created template to open template in update mode");
			Test_Variables.steps.createNode("2. Make changes in template column");
			Test_Variables.steps.createNode("3. Click on save button");

			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(Test_Variables.TemplateName)) {
					WebElement filter_scroll = Helper.driver.findElement(By.id("edit-data-format-"+i));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
					Helper.driver.findElement(By.id("edit-data-format-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(1000);
					break;
				}
			}	

			WebElement filter_scroll = Helper.driver.findElement(Test_Elements.dtmClmEdit);
			((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.dtmClmEdit).click();
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.dtmClmDefaultValue).clear();  
			Helper.driver.findElement(Test_Elements.dtmClmDefaultValue).sendKeys("10");  
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.dtmClmSave).click();
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElement(By.cssSelector(".dataformat-table-container tr:nth-child(1) td:nth-child(5)")).getText(), "10"); 
			Test_Variables.test.pass("Template column updated successfully");
			Test_Variables.results.createNode("Template column updated successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.DataTemplateManagementReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("Template column failed to update");
			Test_Variables.results.createNode("Template column failed to update");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Template column failed to update");
			Test_Variables.results.createNode("Template column failed to update");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, ex);
		}
	}


	@Test (description="Delete Column Template", enabled= true, priority= 7) 
	public void DeleteColumnTemplate() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-DTM-12: Verify user can delete Template Column", "This test case will verify that user can update template column");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Data Template Management; Data Template Management screen opens");
			Test_Variables.preconditions.createNode("5. Create a new template");
			Test_Variables.steps.createNode("1. Click on update icon next to created template to open template in update mode");
			Test_Variables.steps.createNode("2. Click on delete icon next to created template column");
			Test_Variables.steps.createNode("3. Confirmation popup appears");
			Test_Variables.steps.createNode("4. Click on yes button");

			WebElement filter_scroll = Helper.driver.findElement(Test_Elements.dtmClmDelete);
			((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
			Helper.driver.findElement(Test_Elements.dtmClmDelete).click();  //click on edit template column
			Thread.sleep(2000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
			Helper.driver.findElement(Test_Elements.popupYesButton).click();

			Assert.assertEquals(Helper.driver.findElements(By.cssSelector(".dataformat-table-container tr")).size(), 1); 
			Test_Variables.test.pass("Template column updated successfully");
			Test_Variables.results.createNode("Template column updated successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.DataTemplateManagementReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("Template column failed to delete");
			Test_Variables.results.createNode("Template column failed to delete");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Template column failed to delete");
			Test_Variables.results.createNode("Template column failed to delete");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, ex);
		}
	}


	@Test (description="Update Template", enabled= true, priority= 8) 
	public void UpdateTemplate() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-DTM-13: Verify user can update Template", "This test case will verify that user can update template");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Data Template Management; Data Template Management screen opens");
			Test_Variables.preconditions.createNode("5. Create a new template");
			Test_Variables.steps.createNode("1. Click on update icon next to created template to open template in update mode");
			Test_Variables.steps.createNode("2. Make changes in template name or description");
			Test_Variables.steps.createNode("3. Click on save button");

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
			Helper.driver.findElement(Test_Elements.dtmClmReset).click();
			Helper.driver.findElement(Test_Elements.dtmClmName).sendKeys("ID");
			Helper.driver.findElement(Test_Elements.dtmClmType).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector(".ng-option:nth-child(2)")).click();	
			Helper.driver.findElement(Test_Elements.dtmClmLength).sendKeys("25");
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.dtmKeyField).click();
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.dtmClmAdd).click();
			Thread.sleep(1000);

			//add identification column
			Helper.driver.findElement(Test_Elements.dtmClmName).sendKeys("Name");
			Helper.driver.findElement(Test_Elements.dtmClmType).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector(".ng-option:nth-child(2)")).click();	
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.dtmIdentificationField).click();
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.dtmClmAdd).click();
			Thread.sleep(1000);

			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);

			Assert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "Data template details updated."); 
			Test_Variables.test.pass("Template updated successfully");
			Test_Variables.results.createNode("Template updated successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.DataTemplateManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("Template failed to update");
			Test_Variables.results.createNode("Template failed to update");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Template failed to update");
			Test_Variables.results.createNode("Template failed to update");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, ex);
		}
	}


	@SuppressWarnings("resource")
	@Test (description="Test Case: ExportTemplate",enabled= true, priority= 9) 
	public void ExportTemplate() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-DTM-14: Verify user can Export Template", "This test case will verify that user can export a Template");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Data Template Management; Data Template Management screen opens");
			Test_Variables.preconditions.createNode("5. Create a new template");
			Test_Variables.steps.createNode("1. Click on update icon next to created template to open template in update mode");
			Test_Variables.steps.createNode("2. Click on export button");

			int totalRows = Helper.driver.findElements(By.cssSelector("tr")).size();
			for (int i=1; i<totalRows; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(Test_Variables.TemplateName)) {
					WebElement filter_scroll = Helper.driver.findElement(By.id("delete-data-format-"+i));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
					Helper.driver.findElement(By.id("edit-data-format-"+i)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector(".export-csv-flock")).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("export-records")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(4000);

			File downloadFolder = new File(Test_Variables.fileDownloadPath);
			List<String> namesOfFiles = Arrays.asList(downloadFolder.list());
			if(namesOfFiles.contains(Test_Variables.TemplateName+".xlsx")) {	
				System.out.println("Success");
				Assert.assertTrue(true);
				Test_Variables.test.pass("Template exported successfully");
				Test_Variables.results.createNode("Template exporteed successfully");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.DataTemplateManagementReportPath, null);
			}


			/////////////////////////////////////////////////////////////

			File file = new File(Test_Variables.fileDownloadPath+"\\"+Test_Variables.TemplateName+".xlsx");
			if(file.exists()){
				System.out.println("File Exists");
			}	

			SoftAssert softAssert = new SoftAssert();
			String path = Test_Variables.fileDownloadPath+"\\"+Test_Variables.TemplateName+".xlsx";
			FileInputStream fs = new FileInputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(0);
			Cell cell = row.getCell(0);
			softAssert.assertEquals(cell.toString(), "ID");
			cell = row.getCell(1);
			softAssert.assertEquals(cell, null);		
			softAssert.assertAll();
			///////////////////////////////////////////////////////////////////////////


		}catch(AssertionError er){
			Test_Variables.test.fail("Template failed to export");
			Test_Variables.results.createNode("Template failed to export");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Template failed to export");
			Test_Variables.results.createNode("Template failed to export");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, ex);
		}
		finally {
			File file = new File(Test_Variables.fileDownloadPath+"\\"+Test_Variables.TemplateName+".xlsx"); 
			if(file.delete())
				System.out.println("CSV file deleted");
		}
	}


	@Test (description="Test Case: IdentificationColumn",enabled= true, priority= 10) 
	public void IdentificationColumnTest() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-DTM-15: Verify identification column is not mandatory", "This test case will verify that identification column is not mandatory");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Data Template Management; Data Template Management screen opens");
			Test_Variables.preconditions.createNode("5. Create a new template");
			Test_Variables.preconditions.createNode("6. Add identification column");
			Test_Variables.steps.createNode("1. Go to data upload screen");
			Test_Variables.steps.createNode("2. Verify uploading template with no value in identification column is allowed");

			Helper.driver.get(Constants.url_dataUpload);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);

			Helper.driver.findElement(By.id("OrgnTypeID")).click();
			Helper.driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("Ancera");
			Helper.driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("DataFormatId")).click();
			Helper.driver.findElement(By.cssSelector("#DataFormatId input")).sendKeys(Test_Variables.TemplateName);
			Helper.driver.findElement(By.cssSelector("#DataFormatId input")).sendKeys(Keys.ENTER);
			SoftAssert softAssert = new SoftAssert();
			Helper.driver.findElement(By.id("file-input")).sendKeys(Test_Variables.fileAbsolutePath+"Excel\\IndentificationFieldCheck.xlsx");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "IndentificationFieldCheck.xlsx loaded successfully.", "File failed to load");
			Helper.driver.findElement(By.cssSelector(".fa-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "IndentificationFieldCheck.xlsx saved successfully.", "File failed to save; message not appeared");
			softAssert.assertAll();
			Test_Variables.test.pass("Identification field check verified successfully");
			Test_Variables.results.createNode("Identification field check verified successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.DataTemplateManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("Identification field check failed to verify");
			Test_Variables.results.createNode("Identification field check failed to verify");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Identification field check failed to verify");
			Test_Variables.results.createNode("Identification field check failed to verify");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, ex);
		}
	}


	@Test (description="Test Case: Inactivate Template",enabled= true, priority= 11) 
	public void InActivateTemplate() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-DTM-16: Verify inactivated template is not displayed in data upload screen", "This test case will verify that inactivated template is not displayed in data upload screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Data Template Management; Data Template Management screen opens");
			Test_Variables.preconditions.createNode("5. Create a new template");
			Test_Variables.steps.createNode("1. Inactivate the template");
			Test_Variables.steps.createNode("2. Go to data upload screen to verify that template is not visible there");

			Helper.driver.get(Constants.url_dataTemplate);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
			Thread.sleep(1000);

			int totalRows = Helper.driver.findElements(By.cssSelector("tr")).size();
			for (int i=1; i<totalRows; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(Test_Variables.TemplateName)) {
					WebElement filter_scroll = Helper.driver.findElement(By.id("delete-data-format-"+i));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
					Helper.driver.findElement(By.id("edit-data-format-"+i)).click();
					break;
				}
			}

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.dtmInactivateTemplate).click();
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "Data template details updated."); 

			Helper.driver.get(Constants.url_dataUpload);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
			Thread.sleep(1000);

			Helper.driver.findElement(By.id("OrgnTypeID")).click();
			Helper.driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("Ancera");
			Helper.driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("DataFormatId")).click();
			Helper.driver.findElement(By.cssSelector("#DataFormatId input")).sendKeys(Test_Variables.TemplateName);
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElements(By.cssSelector(".ng-option-disabled")).size(), 1);
			Test_Variables.test.pass("Template was inactivated successfully");
			Test_Variables.results.createNode("Template was inactivated successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));	
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.DataTemplateManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("Template was not inactivated");
			Test_Variables.results.createNode("Template was not inactivated");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Template was not inactivated");
			Test_Variables.results.createNode("Template was not inactivated");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, ex);
		}
	}

	@Test (description="Test Case: Delete Template",enabled= true, priority= 12) 
	public void DeleteTemplate() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-DTM-17: Verify user can delete Template", "This test case will verify that user can delete a Template");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Data Template Management; Data Template Management screen opens");
			Test_Variables.preconditions.createNode("5. Create a new template");
			Test_Variables.steps.createNode("1. Click on delete icon next to created template; confirmation box popups");
			Test_Variables.steps.createNode("2. Click on yes button");

			Helper.driver.get(Constants.url_dataTemplate);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
			Thread.sleep(1000);

			int totalRows = Helper.driver.findElements(By.cssSelector("tr")).size();
			for (int i=1; i<totalRows; i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(1) label")).getText().equals(Test_Variables.TemplateName)) {
					WebElement filter_scroll = Helper.driver.findElement(By.id("delete-data-format-"+i));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
					Helper.driver.findElement(By.id("delete-data-format-"+i)).click();
					break;
				}
			}

			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.popupYesButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Elements.alertMessage));
			Thread.sleep(1000);
			Assert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "Data template details deleted."); 
			Test_Variables.test.pass("Template deleted successfully");
			Test_Variables.results.createNode("Template deleted successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));	
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.DataTemplateManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("Failed to delete the Template");
			Test_Variables.results.createNode("Failed to delete the Template");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Failed to delete the Template");
			Test_Variables.results.createNode("Failed to delete the Template");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority= 13) 
	public void VerifyClientMappingPopup() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-DTM-18: Verify client mapping popup opens and user is able to see all template list", "This test case will verify that client mapping popup opens and user is able to see all template list");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Data Template Management; Data Template Management screen opens");
			Test_Variables.preconditions.createNode("5. Create a new template");
			Test_Variables.steps.createNode("1. Open Client mapping popup");
			Test_Variables.steps.createNode("2. Verify created template along with all other template are displayed in the list");

			SoftAssert softAssert = new SoftAssert();
			Helper.driver.get(Constants.url_dataTemplate);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
			Thread.sleep(1000);

			List<WebElement> templateNamesTable = Helper.driver.findElements(By.cssSelector("tr td:nth-child(1) label"));

			Helper.driver.findElement(Test_Elements.dtmClientMappingOpenButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));	
			List<WebElement> templateNamesClientMapping = Helper.driver.findElements(By.cssSelector(".popup-content tr td:nth-child(2) label"));
			int size = templateNamesClientMapping.size();
			for(int i =0; i<size ; i++){
				String options = templateNamesClientMapping.get(i).getText();
				String options1 = templateNamesTable.get(i+1).getText();		
			//	System.out.println("Table: " +options1+" -- Popup: "+options);
				
				softAssert.assertEquals(options, options1);
			}
			softAssert.assertAll();
			Test_Variables.test.pass("Client mapping popup opened successfully and displayed all templates");
			Test_Variables.results.createNode("Client mapping popup opened successfully and displayed all templates");	
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.DataTemplateManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("Client mapping popup didnot opened successfully or did not displayed all templates");
			Test_Variables.results.createNode("Client mapping popup didnot opened successfully or did not displayed all templates");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Client mapping popup didnot opened successfully or did not displayed all templates");
			Test_Variables.results.createNode("Client mapping popup didnot opened successfully or did not displayed all templates");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority= 14) 
	public void VerifyClientMappingList() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-DTM-19: Verify client dropdown in client mapping displays all clients assigned to user", "This test case will verify that client dropdown in client mapping displays all clients assigned to user");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Data Template Management; Data Template Management screen opens");
			Test_Variables.preconditions.createNode("5. Create a new template");
			Test_Variables.steps.createNode("1. Open Client mapping popup");
			Test_Variables.steps.createNode("2. Verify client dropdown in client mapping displays all clients assigned to user");

			SoftAssert softAssert = new SoftAssert();
			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			for (int i=1;i<Helper.driver.findElements(By.cssSelector("tr")).size(); i++) {
				if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+Test_Elements.userEmailCol+" label")).getText().equals(Test_Variables.login_email)) {
					WebElement scroll = Helper.driver.findElement(By.id("edit-user-"+i));
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
					Thread.sleep(1000); 
					Helper.driver.findElement(By.id("edit-user-"+i)).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					break;
				}
			}	

			Thread.sleep(6000);
			Helper.driver.findElement(By.id("btn-next")).click();
			Thread.sleep(750);
			Helper.driver.findElement(By.id("btn-next")).click();
			Thread.sleep(750);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));	
			int userClientSites = Helper.driver.findElements(By.cssSelector(".site-tree-card")).size() - 1;
			System.out.println("User client sites: "+userClientSites);
			
			Helper.driver.get(Constants.url_dataTemplate);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			List<WebElement> templateNamesTable = Helper.driver.findElements(By.cssSelector("tr td:nth-child(1) label"));
			Helper.driver.findElement(Test_Elements.dtmClientMappingOpenButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
			Thread.sleep(1000);
			Helper.driver.findElement(Test_Elements.dtmClientMappingClientDropdown).click();
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));	
			int MappingClientSites =Helper.driver.findElements(By.cssSelector(".ng-option")).size();
			//check if all clients displays in dropdown which are assign to user
			
			softAssert.assertEquals(userClientSites, MappingClientSites);
			Helper.driver.findElement(Test_Elements.dtmClientMappingClientDropdown).click();
			Thread.sleep(1000);

			for (int i =0; i<MappingClientSites; i++) {
				Helper.driver.findElement(Test_Elements.dtmClientMappingClientDropdown).click();
				Thread.sleep(1000);
				int j = i+1;
				Helper.driver.findElement(By.cssSelector(".ng-option:nth-child("+j+") .ng-option-label")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
				Thread.sleep(1000);
				List<WebElement> templateNamesClientMapping = Helper.driver.findElements(By.cssSelector(".popup-content tr td:nth-child(2) label"));
				String options = templateNamesClientMapping.get(i).getText();
				String options1 = templateNamesTable.get(j).getText();
				softAssert.assertEquals(options, options1);
			}

			if (Helper.driver.findElement(By.id("isCreate0")).isSelected() == false) {
				Helper.driver.findElement(By.cssSelector(".popup-content tr:nth-child(1) td:nth-child(3) .custom-checkbox")).click();
			}
			if (Helper.driver.findElement(By.id("isCreate1")).isSelected() == false) {
				Helper.driver.findElement(By.cssSelector(".popup-content tr:nth-child(2) td:nth-child(3) .custom-checkbox")).click();
			}
			Helper.driver.findElement(Test_Elements.popupSaveButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
			Thread.sleep(1000);
			softAssert.assertEquals(Helper.driver.findElement(Test_Elements.alertMessage).getText(), "Client mapping details has been updated successfully.");

			softAssert.assertAll();
			Test_Variables.test.pass("Client dropdown in client mapping displayed all clients assigned to user successfully");
			Test_Variables.results.createNode("Client dropdown in client mapping displayed all clients assigned to user successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));	
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.DataTemplateManagementReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("Client dropdown in client mapping did not displayed all clients assigned to user");
			Test_Variables.results.createNode("Client dropdown in client mapping did not displayed all clients assigned to user");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Client dropdown in client mapping did not displayed all clients assigned to user");
			Test_Variables.results.createNode("Client dropdown in client mapping did not displayed all clients assigned to user");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, ex);
		}
	}


	@Test (enabled= true, priority= 15) 
	public void VerifyClientUploadScreen() throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-DTM-20: Verify templates assigned to client are displayed in data upload screen", "This test case will verify that templates assigned to client are displayed in data upload screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on MetaData and select Data Template Management; Data Template Management screen opens");
			Test_Variables.preconditions.createNode("5. Create a new template");
			Test_Variables.steps.createNode("1. Open Client mapping popup");
			Test_Variables.steps.createNode("2. Assign templates to client");
			Test_Variables.steps.createNode("3. Go to data upload screen and verify that assign templates are displayed against that client");

			Helper.driver.get(Constants.url_dataTemplate);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(1000);

			String rows = Helper.driver.findElement(By.id("results-found-count")).getText();


			Helper.driver.findElement(Test_Elements.dtmClientMappingOpenButton).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	


			Helper.driver.findElement(Test_Elements.dtmClientMappingClientDropdown).click();
			Thread.sleep(1000);
			String clientName = Helper.driver.findElement(By.cssSelector(".ng-option:nth-child(1) .ng-option-label")).getText();
			Helper.driver.findElement(By.cssSelector(".ng-option:nth-child(1) .ng-option-label")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
			Thread.sleep(1000);

			int templateRows = Integer.parseInt(rows) - 1;
			int count = 0;
			for (int i=0; i< templateRows; i++) {
				
				if (Helper.driver.findElement(By.id("isCreate"+i)).isSelected() == true) {		
					count = count + 1;
					if (i == templateRows-1) {
						Helper.driver.get(Constants.url_dataUpload);
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
						Thread.sleep(1000);
						Helper.driver.findElement(By.id("OrgnTypeID")).click();
						Helper.driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("Client");
						Helper.driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						Helper.driver.findElement(By.id("ClientId")).click();
						Helper.driver.findElement(By.cssSelector("#ClientId input")).sendKeys(clientName);
						Helper.driver.findElement(By.cssSelector("#ClientId input")).sendKeys(Keys.ENTER);
						Helper.driver.findElement(By.id("DataFormatId")).click();
						
						Assert.assertEquals(Helper.driver.findElements(By.cssSelector(".ng-option")).size(), count);
						Test_Variables.test.pass("Template assigned to user displayed in data upload screen successfully");
						Test_Variables.results.createNode("Template assigned to user displayed in data upload screen successfully");
						Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));	
						Helper.saveResultNew(ITestResult.SUCCESS, Constants.DataTemplateManagementReportPath, null);
					}	
				}
			}


		}catch(AssertionError er){
			Test_Variables.test.fail("Template assigned to user failed to display in data upload screen");
			Test_Variables.results.createNode("Template assigned to user failed to display in data upload screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Template assigned to user failed to display in data upload screen");
			Test_Variables.results.createNode("Template assigned to user failed to display in data upload screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataTemplateManagementReportPath, ex);
		}
	}


	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
	}

}

