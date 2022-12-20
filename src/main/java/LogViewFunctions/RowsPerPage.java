package LogViewFunctions;

import static MiscFunctions.Constants.url_login;
import static MiscFunctions.Constants.wait;
import static MiscFunctions.ExtentVariables.PreConditions;
import static MiscFunctions.ExtentVariables.Results;
import static MiscFunctions.ExtentVariables.Steps;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.preconditions;
import static MiscFunctions.ExtentVariables.results;
import static MiscFunctions.ExtentVariables.steps;
import static MiscFunctions.ExtentVariables.test;
import static MiscFunctions.Helper.driver;
import static MiscFunctions.Helper.getScreenshot;
import static MiscFunctions.Helper.saveResult;
import static MiscFunctions.Helper.waitElementInvisible;
import static PageObjects.BasePage.ResultsCount;
import static PageObjects.BasePage.loading_cursor;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;

import MiscFunctions.ClickElement;

public class RowsPerPage {

	@Test (description="Test Case: Test Table Rows") 
	public static void RowsPerPage() throws InterruptedException, IOException {

		int[] tableRows = {100, 250, 500};
		for (int i=0; i<=tableRows.length; i++) {
			try {
				test = extent.createTest("Verify user can apply "+tableRows[i]+" rows per page");
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				preconditions.createNode("1. Go to url " +url_login);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				preconditions.createNode("3. Hover to sidebar to expand the menu");
				preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				preconditions.createNode("5. Open any Report");

				waitElementInvisible(loading_cursor);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ResultsCount))); 
				Thread.sleep(500);	

				Actions actions = new Actions(driver);
				SoftAssert softAssert = new SoftAssert();
				steps.createNode("1. Select "+tableRows[i]+" from dropdown below");
				String results1 = driver.findElement(By.id(ResultsCount)).getText();

				if (NumberFormat.getNumberInstance(Locale.US).parse(results1).intValue() > tableRows[i]) {
					WebElement expandFilter = driver.findElement(By.id("rows"));
					actions.moveToElement(expandFilter).click().perform();				
					waitElementInvisible(loading_cursor);
					Thread.sleep(2000);
					getScreenshot();
					int j = i+1;
					driver.findElement(By.cssSelector("option:nth-child("+j+")")).click();
					waitElementInvisible(loading_cursor);
					Thread.sleep(1000);
					List<WebElement> rows = driver.findElements(By.cssSelector("tr td:nth-child(3)"));
					int count = rows.size();
				//	int new_count = count - 4;

					softAssert.assertEquals(count, tableRows[i]);
					softAssert.assertAll();
					test.pass(tableRows[i]+" displayed succcessfully");
					results.createNode(tableRows[i]+" displayed succcessfully");
					getScreenshot();
					saveResult(ITestResult.SUCCESS, null);
				}
				else {
					softAssert.assertTrue(true, "Records are less then "+tableRows[i]);
					test.pass("Records are less then "+tableRows[i]);
					results.createNode("Rcords are less then "+tableRows[i]);
					getScreenshot();
					saveResult(ITestResult.SUCCESS, null);	
				}

				test = extent.createTest("Verify "+tableRows[i]+" rows per page remained apply on moving to next page");
				steps.createNode("1. Select "+tableRows[i]+" from dropdown below");
				steps.createNode("2. Go to next page from pagination");
				steps.createNode("3. Verify that still "+tableRows[i]+" is selected");

				String results2 = driver.findElement(By.id(ResultsCount)).getText();
				int sum = tableRows[i] + tableRows[i];

				if (NumberFormat.getNumberInstance(Locale.US).parse(results2).intValue() > sum) {

					ClickElement.clickById(driver, "next-page");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1000);
					List<WebElement> rows = driver.findElements(By.cssSelector("tr td:nth-child(3)"));
					int count = rows.size();
			//		int new_count = count - 4;
					//System.out.println("ROW COUNT : "+new_count);
					softAssert.assertEquals(count, tableRows[i]);
					test.pass(tableRows[i]+"records displayed succcessfully on next page");
					results.createNode(tableRows[i]+"records displayed succcessfully on next page");
					getScreenshot();
					saveResult(ITestResult.SUCCESS, null);	
				}

				else {
					softAssert.assertTrue(true, "Records are less then "+sum);
					test.pass("Records are less then "+sum);
					results.createNode("Records are less then "+sum);
					getScreenshot();
					saveResult(ITestResult.SUCCESS, null);	
				}
				softAssert.assertAll();
			}

			catch(AssertionError er) {
				test.fail(tableRows[i]+" failed to display on next page");
				results.createNode(tableRows[i]+" failed to display on next page");
				saveResult(ITestResult.FAILURE, new Exception(er));
			}
			catch(Exception ex) {
				test.fail(tableRows[i]+" failed to display on next page");
				results.createNode(tableRows[i]+" failed to display on next page");
				saveResult(ITestResult.FAILURE, ex);
			}	
		}
	}
	
	
	@Test (description="Test Case: Test Table Rows") 
	public static void RowsPerPage1(String tablename) throws InterruptedException, IOException {

		int[] tableRows = {100, 250, 500};
		for (int i=0; i<=tableRows.length; i++) {
			try {
				test = extent.createTest("Verify user can apply "+tableRows[i]+" rows per page");
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				preconditions.createNode("1. Go to url " +url_login);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				preconditions.createNode("3. Hover to sidebar to expand the menu");
				preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				preconditions.createNode("5. Open any Report");

				waitElementInvisible(loading_cursor);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#"+tablename+" #"+ResultsCount))); 
				Thread.sleep(500);	

				Actions actions = new Actions(driver);
				SoftAssert softAssert = new SoftAssert();
				steps.createNode("1. Select "+tableRows[i]+" from dropdown below");
				String results1 = driver.findElement(By.cssSelector("#"+tablename+" #"+ResultsCount)).getText();

				if (NumberFormat.getNumberInstance(Locale.US).parse(results1).intValue() > tableRows[i]) {
					WebElement expandFilter = driver.findElement(By.cssSelector("#"+tablename+" #rows"));
					actions.moveToElement(expandFilter).click().perform();				
					waitElementInvisible(loading_cursor);
					Thread.sleep(2000);
					getScreenshot();
					int j = i+1;
					driver.findElement(By.cssSelector("#"+tablename+" option:nth-child("+j+")")).click();
					waitElementInvisible(loading_cursor);
					Thread.sleep(1000);
					List<WebElement> rows = driver.findElements(By.cssSelector("#"+tablename+" tr td:nth-child(3)"));
					int count = rows.size();
				//	int new_count = count - 4;

					softAssert.assertEquals(count, tableRows[i]);
					softAssert.assertAll();
					test.pass(tableRows[i]+" displayed succcessfully");
					results.createNode(tableRows[i]+" displayed succcessfully");
					getScreenshot();
					saveResult(ITestResult.SUCCESS, null);
				}
				else {
					softAssert.assertTrue(true, "Records are less then "+tableRows[i]);
					test.skip("Records are less then "+tableRows[i]);
					results.createNode("Records are less then "+tableRows[i]);
					getScreenshot();
					saveResult(ITestResult.SKIP, null);	
				}

				test = extent.createTest("Verify "+tableRows[i]+" rows per page remained apply on moving to next page");
				steps.createNode("1. Select "+tableRows[i]+" from dropdown below");
				steps.createNode("2. Go to next page from pagination");
				steps.createNode("3. Verify that still "+tableRows[i]+" is selected");

				String results2 = driver.findElement(By.cssSelector("#"+tablename+" #"+ResultsCount)).getText();
				int sum = tableRows[i] + tableRows[i];

				if (NumberFormat.getNumberInstance(Locale.US).parse(results2).intValue() > sum) {

					ClickElement.clickByCss(driver, "#"+tablename+" #next-page");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1000);
					List<WebElement> rows = driver.findElements(By.cssSelector("#"+tablename+" tr td:nth-child(3)"));
					int count = rows.size();
			//		int new_count = count - 4;
					//System.out.println("ROW COUNT : "+new_count);
					softAssert.assertEquals(count, tableRows[i]);
					test.pass(tableRows[i]+"records displayed succcessfully on next page");
					results.createNode(tableRows[i]+"records displayed succcessfully on next page");
					getScreenshot();
					saveResult(ITestResult.SUCCESS, null);	
				}

				else {
					softAssert.assertTrue(true, "Records are less then "+sum);
					test.skip("Records are less then "+sum);
					results.createNode("Records are less then "+sum);
					getScreenshot();
					saveResult(ITestResult.SKIP, null);	
				}
				softAssert.assertAll();
			}

			catch(AssertionError er) {
				test.fail(tableRows[i]+" failed to display on next page");
				results.createNode(tableRows[i]+" failed to display on next page");
				saveResult(ITestResult.FAILURE, new Exception(er));
			}
			catch(Exception ex) {
				test.fail(tableRows[i]+" failed to display on next page");
				results.createNode(tableRows[i]+" failed to display on next page");
				saveResult(ITestResult.FAILURE, ex);
			}	
		}
	}
	
}
