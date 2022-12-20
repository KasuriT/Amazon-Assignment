package Test.Ancera.Login;

import java.io.IOException;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import MiscFunctions.ClickElement;
import MiscFunctions.Constants;
import MiscFunctions.DateUtil;
import MiscFunctions.ExtentVariables;
import MiscFunctions.Helper;
import Models.AutoLoginModel;
import Models.ReportFilters;

public class AutoLogin {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		ExtentVariables.spark = new ExtentSparkReporter("target/Reports/AutoLogin"+DateUtil.date+".html");
		ExtentVariables.spark.config().setReportName("Auto Login Test Report"); 

		Helper.config();
		LoginTest.login();
	}


	@Test (description="Test Case: Auto Login Test",enabled= true, priority = 1) 
	public void AutoLoginTest() throws InterruptedException, IOException {
		try {
			AutoLoginModel.lstAutoLoginCheck = AutoLoginModel.FillData();

			WebElement csvHover = Helper.driver.findElement(By.id("menu-administration"));
			Actions builder = new Actions(Helper.driver);
			builder.moveToElement(csvHover).build().perform();

			ClickElement.clickByCss(Helper.driver, "#menu-administration img");
			Thread.sleep(1000);
			ClickElement.clickByCss(Helper.driver, "#menu-piper img");
			Thread.sleep(1000);
			ClickElement.clickByCss(Helper.driver, "#menu-metadata label"); 
			Thread.sleep(1000);
			ClickElement.clickByCss(Helper.driver, "#menu-reports label");
			Thread.sleep(1000);


			for (AutoLoginModel objModel : AutoLoginModel.lstAutoLoginCheck) { 

				ExtentVariables.test = ExtentVariables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);

				ExtentVariables.preconditions = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.PreConditions);
				ExtentVariables.steps = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Steps);
				ExtentVariables.results = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Results);

				ExtentVariables.preconditions.createNode("1. Go to url " +Constants.url_login);
				ExtentVariables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				ExtentVariables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				ExtentVariables.steps.createNode("1. "+objModel.precondition);		

				for (ReportFilters objFilter : objModel.lstFilters) {
					try {
						builder.moveToElement(csvHover).build().perform();
						Thread.sleep(1000);

						WebElement filter_scroll = Helper.driver.findElement(By.id(objFilter.FilterXPath));
						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 

						String clicklnk = Keys.chord(Keys.CONTROL,Keys.ENTER);
						Helper.driver.findElement(By.id(objFilter.FilterXPath)). sendKeys(clicklnk);
						Thread.sleep(1000);

						ArrayList<String> tabs2 = new ArrayList<String> (Helper.driver.getWindowHandles());
						Helper.driver.switchTo().window(tabs2.get(1));
						Thread.sleep(2000);

						Assert.assertTrue(Helper.driver.findElement(By.id(objFilter.FilterName)).isDisplayed());		
						ExtentVariables.test.pass(objFilter.FilterName+ " screen successfully opens in new tab");
						ExtentVariables.results.createNode(objFilter.FilterName+ " screen successfully opens in new tab");
						Helper.get_Screenshot();
						Helper.saveResult(ITestResult.SUCCESS, null);

						Helper.driver.close();
						Helper.driver.switchTo().window(tabs2.get(0));
					}
					catch(AssertionError er) {
						ExtentVariables.test.fail(objFilter.FilterName+" screen failed to auto login on opening page in new tab");
						ExtentVariables.results.createNode(objFilter.FilterName+" screen failed to auto login on opening page in new tab");
						Helper.saveResult(ITestResult.FAILURE, new Exception(er));
					}
					catch(Exception ex) {
						ExtentVariables.test.fail(objFilter.FilterName+" screen failed to auto login on opening page in new tab");
						ExtentVariables.results.createNode(objFilter.FilterName+" screen failed to auto login on opening page in new tab");
						Helper.saveResult(ITestResult.FAILURE, ex);
					}	
				}
			}
		}
		catch(AssertionError er) {
		}
	}

	@AfterTest
	public static void endreport() {
		ExtentVariables.extent.flush();
		//	Helper.driver.close();
	}


}
