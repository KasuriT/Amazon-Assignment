package Test.Ancera.PiperAdministration;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import MiscFunctions.Constants;
import MiscFunctions.DateUtil;
import MiscFunctions.ExtentVariables;
import MiscFunctions.Helper;
import Test.Ancera.Login.LoginTest;

import static Models.PiperSoftwareModel.*;

public class PiperSoftwareManagement {

	@BeforeTest 
	public void extent() throws InterruptedException, IOException {

		ExtentVariables.spark = new 	ExtentSparkReporter("target/Reports/Administration_Piper_Software_Management"+DateUtil.date+".html");
		ExtentVariables.spark.config().setReportName("Piper Software Management Test Report"); 

		Helper.config();
		LoginTest.login();
	}
	
	
	@Test (description="Test Case: Navigate to Piper Software Management Screen",enabled= true, priority = 1) 
	public void NavigatePSM() throws InterruptedException, IOException {

		try{
			ExtentVariables.test = ExtentVariables.extent.createTest("AN-PSM-01: Verify user can navigate to Piper Software Management Screen", "This test case will verify user can navigate to Piper Software Management Screen");
			ExtentVariables.preconditions = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.PreConditions);
			ExtentVariables.steps = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Steps);
			ExtentVariables.results = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Results);

			ExtentVariables.preconditions.createNode("1. Go to url " +Constants.url_login);
			ExtentVariables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			ExtentVariables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			ExtentVariables.steps.createNode("1. Click on Piper Administration and select Piper Software Management");

			Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Helper.driver.get(Constants.url_piperSoftware);
			Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			String actual = Helper.driver.findElement(By.id("PIPER Software Management")).getText();
			String expected = "PIPER Software Management";

			Assert.assertEquals(actual, expected); 
			ExtentVariables.test.pass("User navigated successfully to PIPER Software Management screen");
			ExtentVariables.results.createNode("User navigated successfully to PIPER Software Management screen");
			Helper.getScreenshot();
			Helper.saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er) {
			ExtentVariables.test.fail("User navigation failed");
			ExtentVariables.results.createNode("PIPER Software Management failed to open");
			Helper.saveResult(ITestResult.FAILURE, new Exception(er));
		}catch(Exception ex){
			ExtentVariables.test.fail("User navigation failed");
			ExtentVariables.results.createNode("PIPER Software Management failed to open");
			Helper.saveResult(ITestResult.FAILURE, ex);
		}
	}
	
	
	@Test (description="Test Case: Invalid File Upload",enabled= true, priority = 2) 
	public void InvalidFileUpload() throws InterruptedException, IOException {

		for (int i=0; i<lstPSManagement.size(); i++) {
			try {
				ExtentVariables.test = ExtentVariables.extent.createTest(lstPSManagement.get(i).testCaseTitle, lstPSManagement.get(i).testCaseDescription);
				ExtentVariables.preconditions = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.PreConditions);
				ExtentVariables.steps = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Steps);
				ExtentVariables.results = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Results);

				ExtentVariables.preconditions.createNode("1. Go to url " +Constants.url_login);
				ExtentVariables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				ExtentVariables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				ExtentVariables.preconditions.createNode("4. Click on Piper Administration and select Piper Software Management");

				Thread.sleep(2000);
				ExtentVariables.steps.createNode("1. Click on dotted box; file explorer opens");
				ExtentVariables.steps.createNode("2. Upload "+lstPSManagement.get(i).fileType+"and verify the file is uploaded and visible in box");
				Helper.getScreenshot();
			
				Helper.driver.findElement(By.cssSelector(lstPSManagement.get(i).improcButton)).click();
				
				Helper.driver.findElement(By.cssSelector("#file-"+lstPSManagement.get(i).uploadButton)).sendKeys(System.getProperty("user.dir")+lstPSManagement.get(i).fileName);

				Constants.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("alrt")));
				Thread.sleep(1000);
				Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), lstPSManagement.get(i).alertMessage);

				ExtentVariables.test.pass(lstPSManagement.get(i).passMessage);
				ExtentVariables.results.createNode(lstPSManagement.get(i).passMessage);
				Helper.getScreenshot();
				Helper.saveResult(ITestResult.SUCCESS, null);	
				Helper.driver.findElement(By.cssSelector("#alrt > button")).click();
			}
			catch(AssertionError er) {
				ExtentVariables.test.fail(lstPSManagement.get(i).failMessage);
				ExtentVariables.results.createNode(lstPSManagement.get(i).failMessage);
				Helper.saveResult(ITestResult.FAILURE, new Exception(er));
			}
			catch(Exception ex) {
				ExtentVariables.test.fail(lstPSManagement.get(i).failMessage);
				ExtentVariables.results.createNode(lstPSManagement.get(i).failMessage);
				Helper.saveResult(ITestResult.FAILURE, ex);
			}
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
	
	
	
	@Test (description="Test Case: Upload file",enabled= true, priority = 3) 
	public void FileUpload() throws InterruptedException, IOException {

		try {
			ExtentVariables.test = ExtentVariables.extent.createTest("AN-PSM-01: Upload valid .msi file", "This test case will verify that user can upload valid .msi file");
			ExtentVariables.preconditions = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.PreConditions);
			ExtentVariables.steps = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Steps);
			ExtentVariables.results = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Results);

			ExtentVariables.preconditions.createNode("1. Go to url " +Constants.url_login);
			ExtentVariables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			ExtentVariables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			ExtentVariables.preconditions.createNode("4. Click on Piper Administration and select Piper Software Management");

			String a = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(2)")).getText();
			String lastFourDigits = "";
			lastFourDigits = a.substring(a.length() - 2);
			int i = Integer.parseInt(lastFourDigits) + 1; 

			File newfile = new File("./PSM_Files/PiperUserApp_Installer."+a+".msi");
			newfile.renameTo(new File("./PSM_Files/PiperUserApp_Installer.08.11.10."+i+".msi"));
			Thread.sleep(1000);
		//	String filename= newfile.getName();

			Helper.driver.findElement(By.cssSelector("#file-userapp")).sendKeys(System.getProperty("user.dir")+"\\PSM_Files\\PiperUserApp_Installer.08.11.10."+i+".msi");
			Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("btn-save")).click();
			Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("btn-save")).click();
			Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(3000);
			String b = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(2)")).getText();
			Assert.assertEquals(b, "08.11.10."+i+"");
			ExtentVariables.test.pass("Valid .msi file uploaded successfully");
			ExtentVariables.results.createNode("Valid .msi file uploaded successfully");
			Helper.getScreenshot();
			Helper.saveResult(ITestResult.SUCCESS, null);
		}
		catch(AssertionError er) {
			ExtentVariables.test.fail("Valid .msi file failed to upload");
			ExtentVariables.results.createNode("Valid .msi file failed to upload");
			Helper.saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			ExtentVariables.test.fail("Valid .msi file failed to upload");
			ExtentVariables.results.createNode("Valid .msi file failed to upload");
			Helper.saveResult(ITestResult.FAILURE, ex);
		}
	}
	
	
	@AfterTest
	public static void endreport() {
		ExtentVariables.extent.flush();
	}
	
}
