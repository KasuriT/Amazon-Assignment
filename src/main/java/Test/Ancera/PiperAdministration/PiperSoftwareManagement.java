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

import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;

public class PiperSoftwareManagement {

	@BeforeTest 
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new 	ExtentSparkReporter("target/Reports/Administration_Piper_Software_Management"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Piper Software Management Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}
	
	
	@Test (description="Test Case: Navigate to Piper Software Management Screen",enabled= true, priority = 1) 
	public void NavigatePSM() throws InterruptedException, IOException {

		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-PSM-01: Verify user can navigate to Piper Software Management Screen", "This test case will verify user can navigate to Piper Software Management Screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.steps.createNode("1. Click on Piper Administration and select Piper Software Management");

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Helper.driver.get(Constants.url_piperSoftware);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			String actual = Helper.driver.findElement(By.id("PIPER Software Management")).getText();
			String expected = "PIPER Software Management";

			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("User navigated successfully to PIPER Software Management screen");
			Test_Variables.results.createNode("User navigated successfully to PIPER Software Management screen");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Software Management", Constants.PiperSoftwareReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PiperSoftwareReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("PIPER Software Management failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperSoftwareReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("PIPER Software Management failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperSoftwareReportPath, ex);
		}
	}
	
	
	@Test (description="Test Case: Invalid File Upload",enabled= true, priority = 2) 
	public void InvalidFileUpload() throws InterruptedException, IOException {

		for (int i=0; i<Test_Variables.lstPSManagement.size(); i++) {
			try {
				Test_Variables.test = Test_Variables.extent.createTest(Test_Variables.lstPSManagement.get(i).testCaseTitle, Test_Variables.lstPSManagement.get(i).testCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Piper Administration and select Piper Software Management");

				Thread.sleep(2000);
				Test_Variables.steps.createNode("1. Click on dotted box; file explorer opens");
				Test_Variables.steps.createNode("2. Upload "+Test_Variables.lstPSManagement.get(i).fileType+"and verify the file is uploaded and visible in box");
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Software Management", Constants.PiperSoftwareReportPath));
			
				Helper.driver.findElement(By.cssSelector(Test_Variables.lstPSManagement.get(i).improcButton)).click();
				
				Helper.driver.findElement(By.cssSelector("#file-"+Test_Variables.lstPSManagement.get(i).uploadButton)).sendKeys(System.getProperty("user.dir")+Test_Variables.lstPSManagement.get(i).fileName);

				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("alrt")));
				Thread.sleep(1000);
				Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), Test_Variables.lstPSManagement.get(i).alertMessage);

				Test_Variables.test.pass(Test_Variables.lstPSManagement.get(i).passMessage);
				Test_Variables.results.createNode(Test_Variables.lstPSManagement.get(i).passMessage);
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Agreement Management", Constants.PiperSoftwareReportPath));
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.PiperSoftwareReportPath, null);	
				Helper.driver.findElement(By.cssSelector("#alrt > button")).click();
			}
			catch(AssertionError er) {
				Test_Variables.test.fail(Test_Variables.lstPSManagement.get(i).failMessage);
				Test_Variables.results.createNode(Test_Variables.lstPSManagement.get(i).failMessage);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperSoftwareReportPath, new Exception(er));
			}
			catch(Exception ex) {
				Test_Variables.test.fail(Test_Variables.lstPSManagement.get(i).failMessage);
				Test_Variables.results.createNode(Test_Variables.lstPSManagement.get(i).failMessage);
				Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperSoftwareReportPath, ex);
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
			Test_Variables.test = Test_Variables.extent.createTest("AN-PSM-01: Upload valid .msi file", "This test case will verify that user can upload valid .msi file");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Piper Administration and select Piper Software Management");

			String a = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(2)")).getText();
			String lastFourDigits = "";
			lastFourDigits = a.substring(a.length() - 2);
			int i = Integer.parseInt(lastFourDigits) + 1; 

			File newfile = new File("./PSM_Files/PiperUserApp_Installer."+a+".msi");
			newfile.renameTo(new File("./PSM_Files/PiperUserApp_Installer.08.11.10."+i+".msi"));
			Thread.sleep(1000);
		//	String filename= newfile.getName();

			Helper.driver.findElement(By.cssSelector("#file-userapp")).sendKeys(System.getProperty("user.dir")+"\\PSM_Files\\PiperUserApp_Installer.08.11.10."+i+".msi");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("btn-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			Helper.driver.findElement(By.id("btn-save")).click();
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(3000);
			String b = Helper.driver.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(2)")).getText();
			Assert.assertEquals(b, "08.11.10."+i+"");
			Test_Variables.test.pass("Valid .msi file uploaded successfully");
			Test_Variables.results.createNode("Valid .msi file uploaded successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Software Management", Constants.PiperSoftwareReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.PiperSoftwareReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("Valid .msi file failed to upload");
			Test_Variables.results.createNode("Valid .msi file failed to upload");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperSoftwareReportPath, new Exception(er));
		}
		catch(Exception ex) {
			Test_Variables.test.fail("Valid .msi file failed to upload");
			Test_Variables.results.createNode("Valid .msi file failed to upload");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.PiperSoftwareReportPath, ex);
		}
	}
	
	
	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
	}
	
}
