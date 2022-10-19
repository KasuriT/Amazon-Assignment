package Test.Ancera.MetaData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
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

import Models.DataUploadModel;
import Models.ReportFilters;
import Test.Ancera.ConfigureLogin;

import static Test.Ancera.Helper.*;
import static Test.Ancera.Test_Elements.*;
import static Test.Ancera.Test_Variables.*;
import static Test.Ancera.Constants.*;

public class DataUpload {

	public static String flockFileName = "Flock Metadata.xlsx";
	public static String sitePerformanceFileName = "Weekly Site Performance.xlsx";
	public static String sampleMetadataFileName = "Sample Metadata Upload Template.xlsx";

	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		spark = new ExtentSparkReporter("target/Reports/MetaData_Upload"+date+".html");
		spark.config().setReportName("Data Upload Test Report"); 
		config();
		ConfigureLogin.login();
	}


	@Test (enabled= false, priority = 2) 
	public void FlockMetadata() throws InterruptedException, IOException {
		
		lstDataUploadFlock = DataUploadModel.FillData();
		for (DataUploadModel objModel : lstDataUploadFlock) { 
			try {
				Thread.sleep(2000);
				test = extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);
				preconditions.createNode("1. Go to url " +url_login);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				steps.createNode("1. Navigate to Data Upload screen");
				steps.createNode("2. Select Ancera from 'Upload For' dropdown and Flock Metadata from 'Data Template'");

				driver.get(url_dataUpload);
				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);	
				driver.findElement(By.id("OrgnTypeID")).click();
				driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("Ancera");
				driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
				Thread.sleep(1000);
				driver.findElement(By.id("DataFormatId")).click();
				driver.findElement(By.cssSelector("#DataFormatId input")).sendKeys("Flock Metadata");
				driver.findElement(By.cssSelector("#DataFormatId input")).sendKeys(Keys.ENTER);
				
				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {

						int chkCounter = 0;
						for (int i = 0; chkCounter < objFilter.LstColumnID.size() && i < 100; i++) {
						
						FileInputStream fsIP= new FileInputStream(new File("./Excel/Flock Metadata.xlsx"));
						@SuppressWarnings("resource")
						XSSFWorkbook wb = new XSSFWorkbook(fsIP);
						XSSFSheet worksheet = wb.getSheetAt(0);
						Cell cell = null;
						
						cell=worksheet.getRow(1).createCell(objFilter.LstColumnID.get(i)); 
						cell.setCellValue(objFilter.LstColumnValues.get(i));   //1033011
						fsIP.close();

						FileOutputStream output_file =new FileOutputStream(new File("./Excel/Flock Metadata.xlsx"));
						wb.write(output_file);
						output_file.close();  
						
						chkCounter++;
						}
						
						steps.createNode("3. "+objModel.steps);
						driver.findElement(By.id("file-input")).sendKeys(fileAbsolutePath+"Excel\\"+flockFileName);
						waitElementInvisible(loading_cursor);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))); 
						Thread.sleep(2000);
						test.addScreenCaptureFromPath(getScreenshot("Data Upload", DataUploadReportPath));
						SoftAssert softAssert = new SoftAssert();
						softAssert.assertEquals(driver.findElement(By.id("message")).getText(), objModel.AlertMessage);
						
						if (objModel.ErrorCase) {
							int i =1;
							if (i==1) {
							driver.findElement(By.id("ErrorBtn")).click();
							Thread.sleep(2000);
							}
							i=i+1;
							WebElement ele = driver.findElement(By.cssSelector("tr:nth-child(1) .tooltipBlock"));
							Actions action = new Actions(driver);
							action.moveToElement(ele).perform();
							Thread.sleep(1000);			
							String tooltipText = driver.findElement(By.cssSelector(".tooltip-inner")).getText();
					//		System.out.println(tooltipText);
							softAssert.assertEquals(tooltipText, objModel.ErrorMessage);
							}
						
						softAssert.assertAll();
						test.pass(objModel.passStep);
						results.createNode(objModel.passStep);
						saveResultNew(ITestResult.SUCCESS, DataUploadReportPath, null);
					
					}
					catch(AssertionError er) {
						test.fail(objModel.failStep);
						results.createNode(objModel.failStep);
						saveResultNew(ITestResult.FAILURE, DataUploadReportPath, new Exception(er));
					}
					catch(Exception ex) {
						test.fail(objModel.failStep);
						results.createNode(objModel.failStep);
						saveResultNew(ITestResult.FAILURE, DataUploadReportPath, ex);
					}
				//	driver.findElement(By.cssSelector("#alrt button span")).click();
				}
			}	
			catch(Exception ex) {
			}
		}
	}


	@Test (enabled= true, priority = 3) 
	public void SitePerformance() throws InterruptedException, IOException {
		
		driver.get(url_dataUpload);
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		
		lstDataUploadSitePerformance = DataUploadModel.FillDataSitePerformance();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("OrgnTypeID"))); 
		Thread.sleep(1000);
		driver.findElement(By.id("OrgnTypeID")).click();
		driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("Ancera");
		driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		driver.findElement(By.id("DataFormatId")).click();
		driver.findElement(By.cssSelector("#DataFormatId input")).sendKeys("Site Performance");
		driver.findElement(By.cssSelector("#DataFormatId input")).sendKeys(Keys.ENTER);
		
		for (DataUploadModel objModel : lstDataUploadSitePerformance) { 
			try {
				Thread.sleep(2000);
				test = extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);
				preconditions.createNode("1. Go to url " +url_login);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				steps.createNode("1. Navigate to Data Upload screen");
				steps.createNode("2. Select Ancera from 'Upload For' dropdown and Sample Metadata from 'Data Template'");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						int chkCounter = 0;
						for (int i = 0; chkCounter < objFilter.LstColumnID.size() && i < 100; i++) {
						
						FileInputStream fsIP= new FileInputStream(new File("./Excel/"+sitePerformanceFileName));
						@SuppressWarnings("resource")
						XSSFWorkbook wb = new XSSFWorkbook(fsIP);
						XSSFSheet worksheet = wb.getSheetAt(0);
						Cell cell = null;
						
						cell=worksheet.getRow(1).createCell(objFilter.LstColumnID.get(i)); 
						cell.setCellValue(objFilter.LstColumnValues.get(i));   //1033011
						fsIP.close();

						FileOutputStream output_file =new FileOutputStream(new File("./Excel/"+sitePerformanceFileName));
						wb.write(output_file);
						output_file.close();  			
						chkCounter++;
						}
						
						steps.createNode("3. "+objModel.steps);
						driver.findElement(By.id("file-input")).sendKeys(fileAbsolutePath+"Excel\\"+sitePerformanceFileName);
						waitElementInvisible(loading_cursor);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))); 
						Thread.sleep(750);
						Assert.assertEquals(driver.findElement(By.id("message")).getText(), objModel.AlertMessage); 
						driver.findElement(By.cssSelector("#alrt button span")).click();
						
						test.pass(objModel.passStep);
						results.createNode(objModel.passStep);
						test.addScreenCaptureFromPath(getScreenshot("Data Upload", DataUploadReportPath));
						saveResultNew(ITestResult.SUCCESS, DataUploadReportPath, null);
					}
					catch(AssertionError er) {
						test.fail(objModel.failStep);
						results.createNode(objModel.failStep);
						saveResultNew(ITestResult.FAILURE, DataUploadReportPath, new Exception(er));
					}
					catch(Exception ex) {
						test.fail(objModel.failStep);
						results.createNode(objModel.failStep);
						saveResultNew(ITestResult.FAILURE, DataUploadReportPath, ex);
					}
				}
			}	
			catch(Exception ex) {
			}
		}
	}


	@Test (enabled= true, priority = 4) 
	public void SampleMetaData() throws InterruptedException, IOException {
	
		lstDataUploadSampleMetadata = DataUploadModel.FillDataSampleMetaData();
		for (DataUploadModel objModel : lstDataUploadSampleMetadata) { 
			try {
				test = extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);
				preconditions.createNode("1. Go to url " +url_login);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				steps.createNode("1. Navigate to Data Upload screen");
				steps.createNode("2. Select Ancera from 'Upload For' dropdown and Sample Metadata from 'Data Template'");	
				
				driver.get(url_dataUpload);
				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);		
				driver.findElement(By.id("OrgnTypeID")).click();
				driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("Ancera");
				driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
				Thread.sleep(1000);
				driver.findElement(By.id("DataFormatId")).click();
				driver.findElement(By.cssSelector("#DataFormatId input")).sendKeys("Sample Metadata");
				driver.findElement(By.cssSelector("#DataFormatId input")).sendKeys(Keys.ENTER);
				
				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
				
						int chkCounter = 0;
						for (int i = 0; chkCounter < objFilter.LstColumnID.size() && i < 100; i++) {
						
						FileInputStream fsIP= new FileInputStream(new File("./Excel/"+sampleMetadataFileName));
						@SuppressWarnings("resource")
						XSSFWorkbook wb = new XSSFWorkbook(fsIP);
						XSSFSheet worksheet = wb.getSheetAt(0);
						Cell cell = null;
						
						cell=worksheet.getRow(i+1).createCell(objFilter.LstColumnID.get(i)); 
						cell.setCellValue(objFilter.LstColumnValues.get(i));   
						fsIP.close();

						FileOutputStream output_file =new FileOutputStream(new File("./Excel/"+sampleMetadataFileName));
						wb.write(output_file);
						output_file.close();  				
						chkCounter++;
						}
						
						steps.createNode("3. "+objModel.steps);
						driver.findElement(By.id("file-input")).sendKeys(fileAbsolutePath+"Excel\\"+sampleMetadataFileName);
						waitElementInvisible(loading_cursor);
						Thread.sleep(3500);
						SoftAssert softAssert = new SoftAssert();
						softAssert.assertEquals(driver.findElement(By.id("message")).getText(), objModel.AlertMessage); 

						if (objModel.ErrorCase) {
							int i =1;
							if (i==1) {
							driver.findElement(By.id("ErrorBtn")).click();
							Thread.sleep(2000);
							}
							i=i+1;
							WebElement ele = driver.findElement(By.cssSelector("tr:nth-child(1) .tooltipBlock"));
							Actions action = new Actions(driver);
							action.moveToElement(ele).perform();
							Thread.sleep(1000);			
							String tooltipText = driver.findElement(By.cssSelector(".tooltip-inner")).getText();
							System.out.println(tooltipText);
							softAssert.assertEquals(tooltipText, objModel.ErrorMessage);
							}
	
						softAssert.assertAll();
						test.pass(objModel.passStep);
						results.createNode(objModel.passStep);
						test.addScreenCaptureFromPath(getScreenshot("Data Upload", DataUploadReportPath));
						saveResultNew(ITestResult.SUCCESS, DataUploadReportPath, null);
					}
					catch(AssertionError er) {
						test.fail(objModel.failStep);
						results.createNode(objModel.failStep);
						saveResultNew(ITestResult.FAILURE, DataUploadReportPath, new Exception(er));
					}
					catch(Exception ex) {
						test.fail(objModel.failStep);
						results.createNode(objModel.failStep);
						saveResultNew(ITestResult.FAILURE, DataUploadReportPath, ex);
					}
					driver.findElement(By.cssSelector("#alrt button span")).click();
				}
			}	
			catch(Exception ex) {
			}
		}
	}
	
	
	
	@Test
	public void as() {
		String myHomePath= System.getProperty("user.home");
		System.out.println(myHomePath);
	}
	


	@Test (enabled= true, priority = 5) 
	public void saveTemplates() throws InterruptedException, IOException {
		
		lstDataUploadSaveTemplate = DataUploadModel.FillDataSaveTemplate();	
		for (DataUploadModel objModel : lstDataUploadSaveTemplate) { 
			try {
				Thread.sleep(2000);
				test = extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);
				preconditions.createNode("1. Go to url " +url_login);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				steps.createNode("1. Navigate to Data Upload screen");
				steps.createNode("2. Select Ancera from 'Upload For' dropdown and "+objModel.templateName+" from 'Data Template'");

				for (@SuppressWarnings("unused") ReportFilters objFilter : objModel.lstFilters) {	
					try {	
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("OrgnTypeID"))); 
						Thread.sleep(1000);
						driver.findElement(By.id("OrgnTypeID")).click();
						driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys("Ancera");
						driver.findElement(By.cssSelector("#OrgnTypeID input")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						driver.findElement(By.id("DataFormatId")).click();
						driver.findElement(By.cssSelector("#DataFormatId input")).sendKeys(objModel.templateName);
						driver.findElement(By.cssSelector("#DataFormatId input")).sendKeys(Keys.ENTER);

						steps.createNode("3. "+objModel.steps);
						driver.findElement(By.id("file-input")).sendKeys(fileAbsolutePath+"Excel\\"+objModel.fileName);
						waitElementInvisible(loading_cursor);
						Thread.sleep(1000);
			//			if (driver.findElement(By.id("message")).getText().equals(objModel.fileName+" loaded successfully.")) {
						driver.findElement(By.cssSelector(".fa-save")).click();
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))); 
						Thread.sleep(1000);
						Assert.assertEquals(driver.findElement(By.id("message")).getText(), objModel.fileName+" saved successfully.");
						test.pass(objModel.templateName+" saved successfully");
						results.createNode(objModel.templateName+" saved successfully");
						test.addScreenCaptureFromPath(getScreenshot("Data Upload", DataUploadReportPath));
						saveResultNew(ITestResult.SUCCESS, DataUploadReportPath, null);
			//		}			
					}
					catch(AssertionError er) {
						test.fail(objModel.templateName+" failed to save");
						results.createNode(objModel.templateName+" failed to save");
						saveResultNew(ITestResult.FAILURE, DataUploadReportPath, new Exception(er));
					}
					catch(Exception ex) {
						test.fail(objModel.templateName+" failed to save");
						results.createNode(objModel.templateName+" failed to save");
						saveResultNew(ITestResult.FAILURE, DataUploadReportPath, ex);
					}
				}
			}	
			catch(Exception ex) {
			}
		}
	}

/*
	@Test (description="Test Case: Client Dropdown List",enabled= false, priority = 6) 
	public void ClientList() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-DU-02: Verify user's own client appear in the client dropdown list", "This test case will verify that user's own client appear in the client dropdown list");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");

			steps.createNode("1. Go to User Management Screen");
			driver.get(url_user);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(userSearch)));
			Thread.sleep(1000);
			steps.createNode("2. Search for own user and open it");
			driver.findElement(By.id("orgnType-1")).click();
			Thread.sleep(800);
			driver.findElement(By.id("outer-0")).click();
			Thread.sleep(800);

			for (int i=1; i<=30; i++) {
				String actualXpath = "//*[@id=\""+i+"\"]/td[4]";
				WebElement element = driver.findElement(By.xpath(actualXpath));

				int j= i+1;
				if (element.getText().equals(login_email)) {
					Thread.sleep(500);
					driver.findElement(By.xpath("//*[@id=\"edit-user-"+j+"\"]")).click();
					break;
				}
			}
			steps.createNode("3. Go to third step of popup");
			Thread.sleep(1500);
			driver.findElement(By.id(userNextBtn)).click();
			Thread.sleep(1000);
			driver.findElement(By.id(userNextBtn)).click();
			Thread.sleep(1000);
			steps.createNode("4. Add a new client and assign a site");
			driver.findElement(By.cssSelector(userClientInput)).sendKeys("Test");
			Thread.sleep(1000);
			driver.findElement(By.xpath(userClientInputSelect)).click();
			Thread.sleep(1000);
			driver.findElement(By.id(userClientSiteBtn)).click();
			Thread.sleep(1500);
			driver.findElement(By.cssSelector(userClientInput2)).sendKeys("Test");
			Thread.sleep(1000);        
			driver.findElement(By.xpath(userClientInput2Select)).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector(userClientCheckbox)).click();
			Thread.sleep(1000);
			driver.findElement(By.id(userClientOKBtn)).click();
			Thread.sleep(1000);
			steps.createNode("5. Click on save button");
			driver.findElement(By.id(userSaveBtn)).click();
			Thread.sleep(1000);

			steps.createNode("6. Go to Data Upload Screen");
			driver.get(url_dataUpload);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(duUploadDropdown)));
			Thread.sleep(1000);
			steps.createNode("7. Select client from Upload For dropdown");
			driver.findElement(By.cssSelector(duUploadDropdown)).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath(duUploadDropdownSelectClient)).click();
			Thread.sleep(1000);
			steps.createNode("8. Expand client dropdown and verify that added client is displayed in Client dropdown");
			driver.findElement(By.cssSelector(duClientInput)).sendKeys("Test");
			Thread.sleep(1000);
			String value = driver.findElement(By.xpath(duClientInputSelect)).getText();

			test.addScreenCaptureFromPath(getScreenshot("Login", LoginReportPath));
			Assert.assertEquals(value, "Test Client");
			test.pass("User's own client appeared successfully");
			results.createNode("User's own client appeared successfully");
			saveResultNew(ITestResult.SUCCESS, DataUploadReportPath, null);
		}
		catch(AssertionError er) {
			test.fail("User's own client failed to appear in dropdown list");
			results.createNode("User's own client failed to appear in dropdown list");
			saveResultNew(ITestResult.FAILURE, DataUploadReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("User's own client failed to appear in dropdown list");
			results.createNode("User's own client failed to appear in dropdown list");
			saveResultNew(ITestResult.FAILURE, DataUploadReportPath, ex);
		}
	}


	@Test (description="Test Case: Remove Client Dropdown List",enabled= false, priority = 7) 
	public void ClientListRemove() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-DU-03: Verify client does not appear in the client dropdown list after removing it", "This test case will verify that client does not appear in the client dropdown list after removing it");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");

			steps.createNode("1. Go to User Management Screen");
			driver.get(url_user);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(userSearch)));
			Thread.sleep(1000);
			steps.createNode("2. Search for own user and open it");
			driver.findElement(By.id("orgnType-1")).click();
			Thread.sleep(800);
			driver.findElement(By.id("outer-0")).click();
			Thread.sleep(800);

			for (int i=1; i<=30; i++) {
				String actualXpath = "//*[@id=\""+i+"\"]/td[4]";
				WebElement element = driver.findElement(By.xpath(actualXpath));

				int j= i+1;
				if (element.getText().equals(login_email)) {
					Thread.sleep(500);
					driver.findElement(By.xpath("//*[@id=\"edit-user-"+j+"\"]")).click();
					break;
				}
			}
			steps.createNode("3. Go to third step of popup");
			Thread.sleep(1500);
			driver.findElement(By.id(userNextBtn)).click();
			Thread.sleep(1000);
			driver.findElement(By.id(userNextBtn)).click();
			Thread.sleep(1000);
			steps.createNode("4. Remove the added client");
			driver.findElement(By.cssSelector(userClientInput)).sendKeys("Test");
			Thread.sleep(1000);
			driver.findElement(By.xpath(userClientInputSelect)).click();
			Thread.sleep(1000);
			steps.createNode("5. Click on save button");
			driver.findElement(By.id(userSaveBtn)).click();
			Thread.sleep(1000);

			steps.createNode("6. Go to Data Upload Screen");
			driver.get(url_dataUpload);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(duUploadDropdown)));
			Thread.sleep(1000);
			steps.createNode("7. Select client from Upload For dropdown");
			driver.findElement(By.cssSelector(duUploadDropdown)).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath(duUploadDropdownSelectClient)).click();
			Thread.sleep(1000);
			steps.createNode("8. Expand client dropdown and verify that deleted client is not displayed in Client dropdown");
			driver.findElement(By.cssSelector(duClientInput)).sendKeys("Test");
			Thread.sleep(1000);
			String value = driver.findElement(By.xpath(duClientInputSelect)).getText();

			test.addScreenCaptureFromPath(getScreenshot("Login", LoginReportPath));
			Assert.assertEquals(value, "No items found");
			test.pass("User's own client was removed from list successfully");
			results.createNode("User's own client was removed from list successfully");
			saveResultNew(ITestResult.SUCCESS, DataUploadReportPath, null);
		}
		catch(AssertionError er) {
			test.fail("User's own client failed to remove from dropdown list");
			results.createNode("User's own client failed to remove from dropdown list");
			saveResultNew(ITestResult.FAILURE, DataUploadReportPath, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("User's own client failed to remove from dropdown list");
			results.createNode("User's own client failed to remove from dropdown list");
			saveResultNew(ITestResult.FAILURE, DataUploadReportPath, ex);
		}
	}
*/

	@AfterTest
	public static void endreport() {
		extent.flush();
		driver.close();
	}



}
