package Test.Ancera.MetaData;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;

public class DataUpload {


	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/MetaData_Data_Upload"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Data Upload Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}


	@Test (description="Test Case: Navigate to Data Upload Screen",enabled= true, priority = 1) 
	public void NavigateDataUpload() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-DU-01: Verify user can navigate to Data Upload screen", "This test case will verify that user can navigate to Data Upload screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Upload", Constants.DataUploadReportPath));
			Helper.driver.get(Constants.url_dataUpload);
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Data Upload")));
			Thread.sleep(1000);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.steps.createNode("1. Hover to sidebar to expand the menu");
			Test_Variables.steps.createNode("2. Click on MetaData and select Data Upload");

			Assert.assertEquals(Helper.driver.findElement(By.id("Data Upload")).getText(), "Data Upload"); 
			Test_Variables.test.pass("User navigated successfully");
			Test_Variables.results.createNode("User navigates to Data Upload Screen");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Data Upload", Constants.DataUploadReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.DataUploadReportPath, null);	
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("User did not navigate to User Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataUploadReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("User did not navigate to User Management Screen");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataUploadReportPath, ex);
		}
	}



	@Test (description="Test Case: Client Dropdown List",enabled= true, priority = 2) 
	public void ClientList() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-DU-02: Verify user's own client appear in the client dropdown list", "This test case will verify that user's own client appear in the client dropdown list");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
	
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			
			Test_Variables.steps.createNode("1. Go to User Management Screen");
			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.userSearch)));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("2. Search for own user and open it");
			Helper.driver.findElement(By.id("orgnType-1")).click();
			Thread.sleep(800);
			Helper.driver.findElement(By.id("outer-0")).click();
			Thread.sleep(800);

			for (int i=1; i<=30; i++) {
				String actualXpath = "//*[@id=\""+i+"\"]/td[4]";
				WebElement element = Helper.driver.findElement(By.xpath(actualXpath));

				int j= i+1;
				if (element.getText().equals(Test_Variables.login_email)) {
					Thread.sleep(500);
					Helper.driver.findElement(By.xpath("//*[@id=\"edit-user-"+j+"\"]")).click();
					break;
				}
			}
			Test_Variables.steps.createNode("3. Go to third step of popup");
			Thread.sleep(1500);
			Helper.driver.findElement(By.id(Test_Elements.userNextBtn)).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.id(Test_Elements.userNextBtn)).click();
			Thread.sleep(1000);
			Test_Variables.steps.createNode("4. Add a new client and assign a site");
			Helper.driver.findElement(By.cssSelector(Test_Elements.userClientInput)).sendKeys("Test");
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath(Test_Elements.userClientInputSelect)).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.id(Test_Elements.userClientSiteBtn)).click();
			Thread.sleep(1500);
			Helper.driver.findElement(By.cssSelector(Test_Elements.userClientInput2)).sendKeys("Test");
			Thread.sleep(1000);        
			Helper.driver.findElement(By.xpath(Test_Elements.userClientInput2Select)).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector(Test_Elements.userClientCheckbox)).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.id(Test_Elements.userClientOKBtn)).click();
			Thread.sleep(1000);
			Test_Variables.steps.createNode("5. Click on save button");
			Helper.driver.findElement(By.id(Test_Elements.userSaveBtn)).click();
			Thread.sleep(1000);

			Test_Variables.steps.createNode("6. Go to Data Upload Screen");
			Helper.driver.get(Constants.url_dataUpload);

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Test_Elements.duUploadDropdown)));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("7. Select client from Upload For dropdown");
			Helper.driver.findElement(By.cssSelector(Test_Elements.duUploadDropdown)).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath(Test_Elements.duUploadDropdownSelectClient)).click();
			Thread.sleep(1000);
			Test_Variables.steps.createNode("8. Expand client dropdown and verify that added client is displayed in Client dropdown");
			Helper.driver.findElement(By.cssSelector(Test_Elements.duClientInput)).sendKeys("Test");
			Thread.sleep(1000);
			String value = Helper.driver.findElement(By.xpath(Test_Elements.duClientInputSelect)).getText();
			
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Login", Constants.LoginReportPath));
			Assert.assertEquals(value, "Test Client");
			Test_Variables.test.pass("User's own client appeared successfully");
			Test_Variables.results.createNode("User's own client appeared successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.DataUploadReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("User's own client failed to appear in dropdown list");
			Test_Variables.results.createNode("User's own client failed to appear in dropdown list");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataUploadReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("User's own client failed to appear in dropdown list");
			Test_Variables.results.createNode("User's own client failed to appear in dropdown list");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataUploadReportPath, ex);
		}
	}

	
	@Test (description="Test Case: Remove Client Dropdown List",enabled= true, priority = 3) 
	public void ClientListRemove() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-DU-03: Verify client does not appear in the client dropdown list after removing it", "This test case will verify that client does not appear in the client dropdown list after removing it");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);
	
			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			
			Test_Variables.steps.createNode("1. Go to User Management Screen");
			Helper.driver.get(Constants.url_user);
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Test_Elements.userSearch)));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("2. Search for own user and open it");
			Helper.driver.findElement(By.id("orgnType-1")).click();
			Thread.sleep(800);
			Helper.driver.findElement(By.id("outer-0")).click();
			Thread.sleep(800);

			for (int i=1; i<=30; i++) {
				String actualXpath = "//*[@id=\""+i+"\"]/td[4]";
				WebElement element = Helper.driver.findElement(By.xpath(actualXpath));

				int j= i+1;
				if (element.getText().equals(Test_Variables.login_email)) {
					Thread.sleep(500);
					Helper.driver.findElement(By.xpath("//*[@id=\"edit-user-"+j+"\"]")).click();
					break;
				}
			}
			Test_Variables.steps.createNode("3. Go to third step of popup");
			Thread.sleep(1500);
			Helper.driver.findElement(By.id(Test_Elements.userNextBtn)).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.id(Test_Elements.userNextBtn)).click();
			Thread.sleep(1000);
			Test_Variables.steps.createNode("4. Remove the added client");
			Helper.driver.findElement(By.cssSelector(Test_Elements.userClientInput)).sendKeys("Test");
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath(Test_Elements.userClientInputSelect)).click();
			Thread.sleep(1000);
			Test_Variables.steps.createNode("5. Click on save button");
			Helper.driver.findElement(By.id(Test_Elements.userSaveBtn)).click();
			Thread.sleep(1000);

			Test_Variables.steps.createNode("6. Go to Data Upload Screen");
			Helper.driver.get(Constants.url_dataUpload);

			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Test_Elements.duUploadDropdown)));
			Thread.sleep(1000);
			Test_Variables.steps.createNode("7. Select client from Upload For dropdown");
			Helper.driver.findElement(By.cssSelector(Test_Elements.duUploadDropdown)).click();
			Thread.sleep(1000);
			Helper.driver.findElement(By.xpath(Test_Elements.duUploadDropdownSelectClient)).click();
			Thread.sleep(1000);
			Test_Variables.steps.createNode("8. Expand client dropdown and verify that deleted client is not displayed in Client dropdown");
			Helper.driver.findElement(By.cssSelector(Test_Elements.duClientInput)).sendKeys("Test");
			Thread.sleep(1000);
			String value = Helper.driver.findElement(By.xpath(Test_Elements.duClientInputSelect)).getText();
			
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Login", Constants.LoginReportPath));
			Assert.assertEquals(value, "No items found");
			Test_Variables.test.pass("User's own client was removed from list successfully");
			Test_Variables.results.createNode("User's own client was removed from list successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.DataUploadReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("User's own client failed to remove from dropdown list");
			Test_Variables.results.createNode("User's own client failed to remove from dropdown list");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataUploadReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("User's own client failed to remove from dropdown list");
			Test_Variables.results.createNode("User's own client failed to remove from dropdown list");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.DataUploadReportPath, ex);
		}
	}
	
	
	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		Helper.driver.close();
	}



}
