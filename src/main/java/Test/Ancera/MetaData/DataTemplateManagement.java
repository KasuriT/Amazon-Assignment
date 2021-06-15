package Test.Ancera.MetaData;
import java.io.File;
import java.io.IOException;

import java.util.Arrays;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/MetaData_Data_Template_Management"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Data TemplateManagement Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}


	@Test (description="Test Case: Navigate to Data Template Management Template",enabled=true, priority= 1) 
	public void Navigate() throws InterruptedException, IOException {

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
		Thread.sleep(2000);
		
		try{
			Assert.assertEquals(Helper.driver.findElement(By.xpath(Test_Elements.getHeadingTitle)).getText(), "Data Template Management"); 
			Test_Variables.test.pass("User navigated successfully");
			Test_Variables.results.createNode("User navigated to Data Template Management Screen successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
		}catch(AssertionError e){
			Test_Variables.test.fail("User failed to navigate");
			Test_Variables.results.createNode("User failed to navigate to Data Template Management Screen");
		}	
	}
	
	
	
	@Test (description="Exceptional Flow: Reset field check", enabled= true, priority= 2) 
	public void ResetFieldCheck() throws InterruptedException, IOException {
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
			
		Thread.sleep(1000);
		Helper.driver.findElement(By.xpath(Test_Elements.dtmCreateButton)).click();
		Thread.sleep(1500);
		Helper.driver.findElement(By.xpath(Test_Elements.dtmName)).sendKeys("Test");
		Thread.sleep(1000);
		Helper.driver.findElement(By.xpath(Test_Elements.dtmDesc)).sendKeys("Description");
		Thread.sleep(1000);
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
		Helper.driver.findElement(By.xpath(Test_Elements.dtmResetButton)).click();
		Thread.sleep(1500);

		String nameActual = Helper.driver.findElement(By.xpath(Test_Elements.dtmName)).getText();
		String descActual = Helper.driver.findElement(By.xpath(Test_Elements.dtmDesc)).getText();	
		
		if (nameActual.isEmpty() && descActual.isEmpty())
		{
			Test_Variables.test.pass("Template fields reset successfully");
			Test_Variables.results.createNode("Template fields reset successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
		}else {
			Test_Variables.test.fail("Template fields failed to reset");
			Test_Variables.results.createNode("Template fields failed to reset");
		}
	}	
	
	
	@Test (description="Exceptional Flow: Column Reset field check", enabled= true, priority= 3) 
	public void ClmResetFieldCheck() throws InterruptedException, IOException {
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
		
		Thread.sleep(1000);
		Helper.driver.findElement(By.id("ColNameID")).sendKeys("Test");
		Thread.sleep(1000);
		Helper.driver.findElement(By.id("defaultValueId")).sendKeys("100");
		Helper.driver.findElement(By.id("colLengthId")).sendKeys("100");
		Thread.sleep(1000);
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
		Helper.driver.findElement(By.id("btn-reset-field")).click();
		Thread.sleep(1000);

		String nameActual = Helper.driver.findElement(By.id("ColNameID")).getText();
		String defaultValueActual = Helper.driver.findElement(By.id("defaultValueId")).getText();	
		String lengthActual = Helper.driver.findElement(By.id("colLengthId")).getText();
		Thread.sleep(1000);
		if (nameActual.isEmpty() && defaultValueActual.isEmpty() && lengthActual.isEmpty())
		{
			Test_Variables.test.pass("Template column fields reset successfully");
			Test_Variables.results.createNode("Template column fields reset successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
		}else{
			Test_Variables.test.fail("Template column fields failed to reset");
			Test_Variables.results.createNode("Template fields failed to reset");
		}

	}	
	
	

	@Test (description="Exceptional Flow: Mandatory field check", enabled= true, priority= 4) 
	public void MandatoryFieldCheck() throws InterruptedException, IOException {
		String NameError;
		String DescError;
		Helper.driver.get(Constants.url_dataTemplate);	
		Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1500);

		for (DataTemplateModel objModel : Test_Variables.lstDTMMandatoryCheck) {
			NameError = "";
			DescError = "";

			if (objModel.isOpenPopUp)
			{
				Test_Variables.test = Test_Variables.extent.createTest("AN-DTM-04: Verify user can open Create Create Template Popup", "This test case will verify that user is able to open create template popup");
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on MetaData and select Data Template Management; Data Template Management screen opens");
				Test_Variables.steps.createNode("1. Click on Create New button");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));

				Helper.driver.findElement(By.id("create-data-format")).click();
				Thread.sleep(1500);

				try{
					Assert.assertEquals(Helper.driver.findElement(By.xpath(Test_Elements.dtmCreatePopupGetTitle)).getText(), "Create Data Template"); 
					Test_Variables.test.pass("Template popup window opened successfully");
					Test_Variables.results.createNode("Template popup window opened successfully");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
				}catch(AssertionError e){
					Test_Variables.test.fail("Template popup window failed to open");
					Test_Variables.results.createNode("Template popup window failed to open");
					break;
				}	
			}

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

			Thread.sleep(1000);

			Helper.driver.findElement(By.id("btn-reset")).click();
			Helper.driver.findElement(By.id("nameId")).sendKeys(objModel.dtmName);
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("DescId")).sendKeys(objModel.dtmDesc);	
			Thread.sleep(1000);

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.dtmSaveButton)));
			Helper.driver.findElement(By.id("btn-save")).click(); 
			Thread.sleep(2000);

			if (Helper.driver.findElements(By.id("message")).size() != 0) {
			
				Actions actions = new Actions(Helper.driver);
				actions.moveToElement(Helper.driver.findElement(By.xpath(Test_Elements.alertClose))).click().perform();
			
			}
			
			
			try {

				if (objModel.chkMandatoryFieldsS1)
				{
					if ( objModel.dtmName.isEmpty())
					{
						if(Helper.driver.findElement(By.xpath(Test_Elements.dtmNameValidation)).isDisplayed()) {
							NameError = Helper.driver.findElement(By.xpath(Test_Elements.dtmNameValidation)).getText();
						}
						Assert.assertEquals(NameError, Test_Elements.dtmNameExpected); 
					}


					if ( objModel.dtmDesc.isEmpty())
					{
						if(Helper.driver.findElement(By.xpath(Test_Elements.dtmDescValidation)).isDisplayed()) {
							DescError = Helper.driver.findElement(By.xpath(Test_Elements.dtmDescValidation)).getText();
						}
						Assert.assertEquals(DescError, Test_Elements.dtmDescExpected); 
					}

					Test_Variables.test.pass(objModel.passScenario);
					Test_Variables.results.createNode(objModel.passScenario);
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));

					continue;
				}
			}
			catch(AssertionError e){
				Test_Variables.test.fail(objModel.failScenario);
				Test_Variables.results.createNode(objModel.failScenario);	
			}


			if (objModel.chkAlert)
			{
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
				Thread.sleep(1000);
				String actual = Helper.driver.findElement(By.id("message")).getText();
				String expected = "Atleast one column is required in Data Template.";
				
				try{
					Assert.assertEquals(actual, expected); 
					Test_Variables.test.pass("Not able to add template without atleast one column successfully");
					Test_Variables.results.createNode(objModel.passScenario);
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
	
				}catch(AssertionError e){
					Test_Variables.test.fail("Not Able to add template without atleast one column failed");
				}
			}

			try {
				if (objModel.chkClm)
				{

					Thread.sleep(1000);
					Helper.driver.findElement(By.id("btn-reset-field")).click();
					Helper.driver.findElement(By.id("ColNameID")).sendKeys(objModel.clmName);
					Thread.sleep(1000);

					if(objModel.clmType) {
						Helper.driver.findElement(By.id("ColTypeId")).click();
						Thread.sleep(1000);
						Helper.driver.findElement(By.cssSelector(".ng-option:nth-child(1)")).click();			
					}

					Helper.driver.findElement(By.id("colLengthId")).sendKeys(objModel.clmLength);	
					Thread.sleep(1000);
					
					Helper.driver.findElement(By.xpath("/html/body/app-root/div/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/fieldset/div[1]/div[3]/div/div/app-custom-radio-button/div/div")).click();

					Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-add-field")));
					Helper.driver.findElement(By.id("btn-add-field")).click(); 
					Thread.sleep(1000);

				}
				if (objModel.chkS2)
				{

					if ( objModel.clmName.isEmpty())
					{
						if(Helper.driver.findElement(By.xpath("//div[contains(text(), 'Field name is required ')]")).isDisplayed()) {
							NameError = Helper.driver.findElement(By.xpath("//div[contains(text(), 'Field name is required ')]")).getText();
						}
						Assert.assertEquals(NameError, Test_Elements.dtmClmNameExpected); 
					}

					if ( objModel.clmLength.isEmpty())
					{
						if(Helper.driver.findElement(By.xpath("//div[contains(text(), 'Field length is required ')]")).isDisplayed()) {
							DescError = Helper.driver.findElement(By.xpath("//div[contains(text(), 'Field length is required ')]")).getText();
						}
						Assert.assertEquals(DescError, Test_Elements.dtmClmLengthExpected); 
					}
					Test_Variables.test.pass(objModel.passScenario);
					Test_Variables.results.createNode(objModel.passScenario);
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
				continue;
				}
			}
			catch(AssertionError e){
				Test_Variables.test.fail(objModel.failScenario);
				Test_Variables.results.createNode(objModel.failScenario);	
			}


			if (objModel.verifyClm) {

				WebElement filter_scroll = Helper.driver.findElement(By.xpath(Test_Elements.dtmClmGetText));
				((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
				
				String actual =	Helper.driver.findElement(By.xpath(Test_Elements.dtmClmGetText)).getText();
				try{
					Assert.assertEquals(actual, "1"); 
					Test_Variables.test.pass("Column added successfully");
					Test_Variables.results.createNode(objModel.passScenario);
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
				}catch(AssertionError e){
					Test_Variables.test.fail("Column adding failed");
				}

			}		
		}
		
		Test_Variables.test = Test_Variables.extent.createTest("AN-DTM-14: Verify that user is able to create new templete", "This testcase will verify that user is able to create template");
		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

		Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
		Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
		Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
		Test_Variables.preconditions.createNode("4. Click on MetaData and select Data Template Management; Data Template Management screen opens");
		Test_Variables.steps.createNode("1. Click on Create New button");
		Test_Variables.steps.createNode("2. Add valid data in all fields");
		Test_Variables.steps.createNode("3. Add a new column");
		Test_Variables.steps.createNode("4. Click on save button");
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));

		Helper.driver.findElement(By.id("btn-save")).click();
		Thread.sleep(2000);
		String act = Helper.driver.findElement(By.id("message")).getText();
		String exp = "New data template created.";

		try{
			Assert.assertEquals(act, exp); 
			Test_Variables.test.pass("New Template created successfully");
			Test_Variables.results.createNode("New Template created successfully; displays  an alert message 'New data template created.'");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
			Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();
		}catch(AssertionError e){
			Test_Variables.test.fail("New Template failed to create");
			Test_Variables.results.createNode("New Template failed to create");
		}
		Thread.sleep(2000);
	}
	
	

	@Test (description="Test Case: Update Template Column",enabled= true, priority= 5) 
	public void UpdateTemplateColumn() throws InterruptedException, IOException {
		try{
		Test_Variables.test = Test_Variables.extent.createTest("AN-DTM-15: Verify user can update Template Column", "This test case will verify that user can update templates column");
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
		
		Helper.driver.get(Constants.url_dataTemplate);
		Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Test_Elements.dtmCreateButton)));
		Thread.sleep(2000);
		Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		String beforeXpath = "/html/body/app-root/div[1]/app-manage-dataformat/div[1]/div/div[2]/div/div/table/tbody/tr[";
		String afterXpath = "]/td[1]";					
		String afterXpath1 = "]/td[4]/label/img[1]";	

		Thread.sleep(1500);

		for (int i=1; i<=30; i++) {
			String actualXpath = beforeXpath+i+afterXpath;
			WebElement element = Helper.driver.findElement(By.xpath(actualXpath));

			Thread.sleep(1500);

			if (element.getText().equals(Test_Variables.TemplateName)) {
				Helper.driver.findElement(By.xpath(beforeXpath+i+afterXpath1)).click();
				break;
			}

		}

		WebDriverWait wait = new WebDriverWait(Helper.driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-field-1")));
		WebElement filter_scroll = Helper.driver.findElement(By.id("edit-field-1"));
		((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
		

		Thread.sleep(2000);
		Helper.driver.findElement(By.id("edit-field-1")).click();  //click on edit template column
		Thread.sleep(2000);
		Helper.driver.findElement(By.id("defaultValueId")).clear();  //enter default value
		Helper.driver.findElement(By.id("defaultValueId")).sendKeys(Test_Variables.lstDTMColumnUpdation.get(0));  //enter default value
		Thread.sleep(2000);
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
		Helper.driver.findElement(By.id("btn-save-field")).click();

	
			WebElement filter_scroll1 = Helper.driver.findElement(By.xpath(Test_Elements.dtmClmUpdateGetText));
			((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll1); 
			
			Assert.assertEquals(Helper.driver.findElement(By.xpath(Test_Elements.dtmClmUpdateGetText)).getText(), "20"+Test_Variables.lstDTMColumnUpdation.get(0)); 
			Test_Variables.test.pass("Template column updated successfully");
			Test_Variables.results.createNode("Template column updated successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
		}catch(AssertionError e){
			Test_Variables.test.fail("Template column failed to update");
			Test_Variables.results.createNode("Template column failed to update");
		}	
	}


	@Test (description="Delete Column Template", enabled= true, priority= 6) 
	public void DeleteColumnTemplate() throws InterruptedException, IOException {
		
		Test_Variables.test = Test_Variables.extent.createTest("AN-DTM-16: Verify user can delete Template Column", "This test case will verify that user can update template column");
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
		
		Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Thread.sleep(2000);
		WebElement filter_scroll = Helper.driver.findElement(By.id("delete-field-1"));
		((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
		Helper.driver.findElement(By.id("delete-field-1")).click();  //click on edit template column
		Thread.sleep(2000);
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
		Helper.driver.findElement(By.id("btn-yes")).click();

		if(Helper.driver.findElements(By.xpath(Test_Elements.dtmClmGetText)).size() ==0)
		{
			Test_Variables.test.pass("Template column deleted successfully");
			Test_Variables.results.createNode("Template column deleted successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
		}
		else {
			Test_Variables.test.fail("Template column failed to delete");
			Test_Variables.results.createNode("Template column failed to delete");
		}		
	}
	
		
	@Test (description="Update Template", enabled= true, priority= 7) 
	public void UpdateTemplate() throws InterruptedException, IOException {
		try {
		Test_Variables.test = Test_Variables.extent.createTest("AN-DTM-17: Verify user can update Template", "This test case will verify that user can update template");
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
		
		Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
		
		Helper.driver.findElement(By.id("ColNameID")).sendKeys("test");
		Thread.sleep(1000);
		Helper.driver.findElement(By.id("ColTypeId")).click();
		Thread.sleep(1000);
		Helper.driver.findElement(By.cssSelector(".ng-option:nth-child(1)")).click();		
		Thread.sleep(1000);
		Helper.driver.findElement(By.id("colLengthId")).sendKeys("10");
		Thread.sleep(1000);
		Helper.driver.findElement(By.xpath("/html/body/app-root/div/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/fieldset/div[1]/div[3]/div/div/app-custom-radio-button/div/div")).click();

		Thread.sleep(1000);
		Helper.driver.findElement(By.id("btn-add-field")).click();
		Thread.sleep(1000);
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));

		Helper.driver.findElement(By.id("btn-save")).click();
		Thread.sleep(1000);
		
			Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Data template details updated."); 
			Test_Variables.test.pass("Template updated successfully");
			Test_Variables.results.createNode("Template updated successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));

		}catch(AssertionError e){
			Test_Variables.test.fail("Template failed to update");
			Test_Variables.results.createNode("Template failed to update");
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Template failed to update");
			Test_Variables.results.createNode("Template failed to update");
		}
	}
	

	@Test (description="Test Case: ExportTemplate",enabled= true, priority= 8) 
	public void ExportTemplate() throws InterruptedException, IOException {

		Test_Variables.test = Test_Variables.extent.createTest("AN-DTM-18: Verify user can Export Template", "This test case will verify that user can export a Template");
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
		
		Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Helper.driver.get(Constants.url_dataTemplate);
		Test_Elements.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Test_Elements.dtmCreateButton)));
		Thread.sleep(2000);
		String beforeXpath = "/html/body/app-root/div/app-manage-dataformat/div[1]/div/div[2]/div/div/table/tbody/tr[";
		String afterXpath = "]/td[1]";					
		String afterXpath1 = "]/td[4]/label/img[1]";	

		Thread.sleep(1500);

		for (int i=1; i<=20; i++) {
			String actualXpath = beforeXpath+i+afterXpath;
			WebElement element = Helper.driver.findElement(By.xpath(actualXpath));
			Thread.sleep(1500);

			if (element.getText().equals(Test_Variables.TemplateName)) {
				Helper.driver.findElement(By.xpath(beforeXpath+i+afterXpath1)).click();
				break;
			}}

		Thread.sleep(2000);
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));

		Helper.driver.findElement(By.xpath("/html/body/app-root/div/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[2]/div/button[2]")).click();
		Thread.sleep(4000);

		File downloadFolder = new File(Test_Variables.fileDownloadPath);
		List<String> namesOfFiles = Arrays.asList(downloadFolder.list());
		if(namesOfFiles.contains(Test_Variables.TemplateName+".xlsx")) {	
			System.out.println("Success");
			Test_Variables.test.pass("Template exported successfully");
			Test_Variables.results.createNode("Template exporteed successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));
		}
		else {	
			System.out.println("Failure");
			Test_Variables.test.fail("Template failed to export");
			Test_Variables.results.createNode("Template failed to export");
		} 

		Thread.sleep(2000);
		Helper.driver.findElement(By.xpath("/html/body/app-root/div[1]/app-manage-dataformat/app-popup-component/div/div/div/div[1]/p")).click();

	}

	

	@Test (description="Test Case: Delete Template",enabled= true, priority= 9) 
	public void DeleteTemplate() throws InterruptedException, IOException {
		try{
		Test_Variables.test = Test_Variables.extent.createTest("AN-DTM-19: Verify user can delete Template", "This test case will verify that user can delete a Template");
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
		Thread.sleep(2000);
		Helper.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
		String beforeXpath = "/html/body/app-root/div/app-manage-dataformat/div[1]/div/div[2]/div/div/table/tbody/tr[";
		String afterXpath = "]/td[1]";
		String afterXpath1 = "]/td[4]/label/img[2]";					

		Thread.sleep(1000);

		for (int i=1; i<=20; i++) {
			String actualXpath = beforeXpath+i+afterXpath;
			WebElement element = Helper.driver.findElement(By.xpath(actualXpath));

			Thread.sleep(1500);

			if (element.getText().equals(Test_Variables.TemplateName)) {

				WebElement deleteInput = Helper.driver.findElement(By.xpath(beforeXpath+i+afterXpath1));
				JavascriptExecutor jse = (JavascriptExecutor)Helper.driver;
				jse.executeScript("arguments[0].click()", deleteInput);
				Thread.sleep(3000);
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));		

				WebElement cnfrm = Helper.driver.findElement(By.xpath("/html/body/app-root/div/app-manage-dataformat/div[1]/app-confirmation-component/div/div/div[3]/div/button[1]"));
				jse.executeScript("arguments[0].click()", cnfrm);

				break;
			}
			Thread.sleep(1500);
		}

		Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.alertbox)));

	
			Assert.assertEquals(Helper.driver.findElement(By.xpath(Test_Elements.alertbox)).getText(), "Data template details deleted."); 
			Test_Variables.test.pass("Template deleted successfully");
			Test_Variables.results.createNode("Template deleted successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Template Management", Constants.DataTemplateManagementReportPath));		
			Helper.driver.findElement(By.xpath(Test_Elements.alertClose)).click();
		}catch(AssertionError e){
			Test_Variables.test.fail("Failed to delete the Template");
			Test_Variables.results.createNode("Failed to delete the Template");
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Failed to delete the Template");
			Test_Variables.results.createNode("Failed to delete the Template");
		}
	}
	

	@AfterMethod
	public void saveResult(ITestResult result) throws IOException {
		Helper.saveResult(result, Constants.DataTemplateManagementReportPath);
	}

	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
	}

}

