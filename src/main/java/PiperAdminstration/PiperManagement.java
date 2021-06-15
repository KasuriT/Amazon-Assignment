package PiperAdminstration;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
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

public class PiperManagement {

	@BeforeTest 
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new 	ExtentSparkReporter("target/Reports/Administration_Piper_Management"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Piper Management Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}

	
	@Test (description="Test Case: Navigate to Piper Management Screen",enabled=true, priority = 2) 
	public void NavigatePM() throws InterruptedException, IOException {

		Test_Variables.test = Test_Variables.extent.createTest("AN-PM-01: Verify user can navigate to Piper Management Screen", "This test case will verify that user can navigate to Piper Managament screen);																											Reports Managment Screen");

		Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, "Pre_Conditions");
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, "Steps");
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, "Result");
		Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Management", Constants.PiperManagementReportPath));

		Helper.driver.get(Constants.url_piperManagement);
		Thread.sleep(2000);
		String actual = Helper.driver.findElement(By.xpath(Test_Elements.getHeadingTitle)).getText();
		String expected = "PIPER Management";
        Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login+ "and login with valid credentials");
		Test_Variables.steps.createNode("1. Hover to sidebar to expand menu");
		Test_Variables.steps.createNode("2. Expand Administration and click on Piper Managment");

		try{
			Assert.assertEquals(actual, expected); 
			Test_Variables.test.pass("User navigated successfully to Piper Management Screen");
			Test_Variables.results.createNode("User navigates to Piper Management page");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Piper Management", Constants.PiperManagementReportPath));
		}catch(AssertionError e){
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("User did not navigated to Piper Management page");
		}	
	}
	
	
	@AfterMethod
	public void saveResult(ITestResult result) throws IOException {
		Helper.saveResult(result, Constants.PiperManagementReportPath);
	}

	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
	}
}
