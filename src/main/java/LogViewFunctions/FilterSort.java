package LogViewFunctions;

import static MiscFunctions.Constants.url_login;
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
import static PageObjects.BasePage.alertMessage;
import static PageObjects.BasePage.loading_cursor;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;

public class FilterSort {

	@Test (enabled= true) 
	public static void Sorting(String tablename, String name, int skipColumns) throws InterruptedException, IOException {
		int totalNumberofColumns = driver.findElements(By.cssSelector("#"+tablename+" th .log-header .mb-0")).size() + skipColumns;   //get total columns and skip irrelevant columns
		for (int i=1;i<=totalNumberofColumns; i++) {
			try {
				SoftAssert softAssert = new SoftAssert();

				if (driver.findElements(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header .mb-0")).size() !=0) {
					WebElement column = driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header .mb-0"));
					test = extent.createTest("AN-Sorting-"+i+": Apply Sorting on "+column.getText()+" column", "This test case will verify that user can apply sorting on "+column.getText()+ " column");
					preconditions = test.createNode(Scenario.class, PreConditions);
					steps = test.createNode(Scenario.class, Steps);
					results = test.createNode(Scenario.class, Results);

					preconditions.createNode("1. Go to url " +url_login);
					preconditions.createNode("2. Login with valid credentials; user navigates to home page");
					preconditions.createNode("3. Hover to sidebar to expand the menu");
					preconditions.createNode("4. Click on "+name+"; "+name+" screen opens");

					if (column.getText().contains("Time") || column.getText().contains("Date") || column.getText().contains("DATE") || column.getText().contains("CREATED")) {
						test.skip("Column sorted successfully");
						results.createNode("Column sorted successfully");
						saveResult(ITestResult.SKIP, null);
					}

					else {
						WebElement filter_scroll = column;
						((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
						steps.createNode("1. Click on "+column.getText()+" column header");

						column.click();
						waitElementInvisible(loading_cursor);					
						Thread.sleep(1000);
						if (driver.findElements(By.cssSelector("#"+tablename+" .fa-sort-amount-down")).size() != 0) {
							softAssert.assertEquals(driver.findElements(By.cssSelector("#"+tablename+" th:nth-child("+i+").sort_desc .log-header .mb-0")).size(), 1, "Did not sorted in descending order");
							softAssert.assertEquals(driver.findElements(alertMessage).size(), 0, "Exception message occured");
							getScreenshot();			
						}

						column.click();
						waitElementInvisible(loading_cursor);					
						Thread.sleep(1000);
						if (driver.findElements(By.cssSelector("#"+tablename+" .fa-sort-amount-down")).size() != 0) {
							softAssert.assertEquals(driver.findElements(By.cssSelector("#"+tablename+" th:nth-child("+i+").sort_asc .log-header .mb-0")).size(), 1, "Did not sorted in descending order");
							softAssert.assertEquals(driver.findElements(alertMessage).size(), 0, "Exception message occured");
							getScreenshot();
						}
						softAssert.assertAll();
						test.pass("Column sorted successfully");
						results.createNode("Column sorted successfully");
						saveResult(ITestResult.SUCCESS, null);
					}
				}
			}
			catch(AssertionError er) {
				test.fail("Column failed to sort");
				results.createNode("Column failed to sort");
				saveResult(ITestResult.FAILURE, new Exception(er));
			}
		}
	}
	
}
