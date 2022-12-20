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
import static PageObjects.BasePage.LockFilter;
import static PageObjects.BasePage.ResetFilters;
import static PageObjects.BasePage.ResultsCount;
import static PageObjects.BasePage.UnlockFilter;
import static PageObjects.BasePage.loading_cursor;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;

import PageObjects.ProgramManagementPage;
import PageObjects.SalmonellaLogPage;

public class FilterLock {

	@Test (enabled= true) 
	public static void Lock(String tablename, String name, int skipColumns) throws InterruptedException, IOException {
		int totalNumberofColumns = driver.findElements(By.cssSelector("#"+tablename+" th .log-header .mb-0")).size() + skipColumns;
		for (int i=1;i<=totalNumberofColumns; i++) {  //get size of columns
			try {
				SoftAssert softAssert = new SoftAssert();
				String recordBefore = driver.findElement(By.cssSelector("#"+tablename+" #"+ResultsCount)).getText(); 
				if ( driver.findElements(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__filter-icon")).size() != 0) {  //check column with ehich filter icon is applied
					WebElement column = driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__filter-icon"));
					WebElement columnName = driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header .mb-0"));

					test = extent.createTest("AN-Lock-"+i+": Verify user can apply filter on "+columnName.getText()+" column", "This testcase will verify that user can apply filter on column");
					preconditions = test.createNode(Scenario.class, PreConditions);
					steps = test.createNode(Scenario.class, Steps);
					results = test.createNode(Scenario.class, Results);

					preconditions.createNode("1. Go to url " +url_login);
					preconditions.createNode("2. Login with valid credentials; user navigates to home page");
					preconditions.createNode("3. Hover to sidebar to expand the menu");
					preconditions.createNode("4. Click on "+name+"; "+name+" screen opens");

					if (driver.findElements(By.cssSelector("#remove-filters.d-none")).size() == 0) {
						driver.findElement(By.id(UnlockFilter)).click();
						driver.findElement(By.id(ResetFilters)).click();
						waitElementInvisible(loading_cursor);	
						Thread.sleep(2000);
					}

					WebElement filter_scroll = column;
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
					getScreenshot();
					driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+")  .log-header__filter-icon")).click();
					waitElementInvisible(loading_cursor);	
					Thread.sleep(1000);

			//		System.out.println(columnName.getText());
					//	if (!columnName.getText().equals("Test Site") || columnName.getText().equals("Result Date") || columnName.getText().equals("Collection Site Name") || columnName.getText().equals("Farm") || columnName.getText().equals("Complex")) {
					if (driver.findElements(By.cssSelector("#"+tablename+" th:nth-child("+i+") "+SalmonellaLogPage.footerCount)).size() != 0) {
						if (driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") "+SalmonellaLogPage.footerCount)).getText().equals("Showing 1 - 1 Results")) {
							test.skip("Values not enough to test lock filter functionality");
							results.createNode("Values not enough to test lock filter functionality");
							saveResult(ITestResult.SKIP, null);
							driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__filter-icon")).click();
						}
						else {
							driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") li:nth-child(3) label")).click();
							Thread.sleep(500);
							steps.createNode("1. Select any filter and click on apply filter button");
							driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__footer--apply")).click();
							waitElementInvisible(loading_cursor);	
							steps.createNode("2. Click on lock button");	
							driver.findElement(By.cssSelector("#"+tablename+" #"+LockFilter)).click();
							waitElementInvisible(loading_cursor);	
							getScreenshot();
							Thread.sleep(1000);

							String recordsafterfilter = driver.findElement(By.cssSelector("#"+tablename+" #"+ResultsCount)).getText();
							softAssert.assertNotEquals(recordsafterfilter, recordBefore, "Filter failed to apply");
							steps.createNode("3. Close "+name+" screen");
							steps.createNode("4. Reopen "+name+" screen");
							driver.navigate().refresh();
							waitElementInvisible(loading_cursor);
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#"+tablename+" #"+ResultsCount)));
							Thread.sleep(3000);

							if (tablename.equals(ProgramManagementPage.programFeedTable)) {
								driver.findElement(ProgramManagementPage.programFeedProgramTab).click();
								waitElementInvisible(loading_cursor);
								Thread.sleep(2000);
							}

							steps.createNode("5. Verify lock filter remains applied");
							getScreenshot();
							softAssert.assertEquals(driver.findElement(By.cssSelector("#"+tablename+" #"+ResultsCount)).getText(), recordsafterfilter, "Lock functionality failed");
							Thread.sleep(1000);
							driver.findElement(By.cssSelector("#"+tablename+" #"+UnlockFilter)).click();
							waitElementInvisible(loading_cursor);
							driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__clear-filter span")).click();
							waitElementInvisible(loading_cursor);	
						}
					}
					else {
						test.skip("Heirarchy filter cannot be tested with this method");
						results.createNode("Heirarchy filter cannot be tested with this method");
						saveResult(ITestResult.SKIP, null);
						driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__filter-icon")).click();
					}
					softAssert.assertAll();
					test.pass("Lock functionality verified successfully");
					results.createNode("Lock functionality verified successfully");
					getScreenshot();
					saveResult(ITestResult.SUCCESS, null);
				}
			}
			catch(AssertionError er) {
				test.fail("Column failed to Lock");
				results.createNode("Column failed to Lock");
				saveResult(ITestResult.FAILURE, new Exception(er));
			}
			catch(Exception ex) {
				test.fail("Column failed to Lock");
				results.createNode("Column failed to Lock");
				saveResult(ITestResult.FAILURE, new Exception(ex));
			}
		}
	}
	
}
