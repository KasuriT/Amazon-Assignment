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
import static PageObjects.BasePage.ResultsCount;
import static PageObjects.BasePage.loading_cursor;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;

public class FilterWildcard {

	@Test (enabled= true) 
	public static void Wildcard(String tablename, String name, int skipColumns) throws InterruptedException, IOException {
		int totalNumberofColumns = driver.findElements(By.cssSelector("#"+tablename+" th .log-header .mb-0")).size() + skipColumns;   //get total columns and skip irrelevant columns
		for (int i=1;i<=totalNumberofColumns; i++) {
			try {
				String recordBefore = driver.findElement(By.cssSelector("#"+tablename+" #"+ResultsCount)).getText();   //get result count
				if ( driver.findElements(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__filter-icon")).size() != 0) {     //check column has filter icon
			//		WebElement column = driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__filter-icon"));
					WebElement columnName = driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header .mb-0"));
					test = extent.createTest("AN_Wildcard-"+i+": Verify user can apply wildcard on "+columnName.getText()+" filter", "This testcase will verify that user can apply wildcard filter");
					preconditions = test.createNode(Scenario.class, PreConditions);
					steps = test.createNode(Scenario.class, Steps);
					results = test.createNode(Scenario.class, Results);

					preconditions.createNode("1. Go to url " +url_login);
					preconditions.createNode("2. Login with valid credentials; user navigates to home page");
					preconditions.createNode("3. Hover to sidebar to expand the menu");
					preconditions.createNode("4. Click on "+name+"; "+name+" page opens");

					SoftAssert softAssert = new SoftAssert();
					WebElement filter_scroll = columnName;	//scroll to filter			
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll);   
					driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__filter-icon")).click();   //open filter popup
					waitElementInvisible(loading_cursor);	
					Thread.sleep(500);

					if (driver.findElements(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__action--wildcard")).size() != 0) {
						if (driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__action--wildcard")).isDisplayed()) {  //check if filter has wildcard option
							if (driver.findElements(By.cssSelector("#"+tablename+" th:nth-child("+i+") .data-log-radio")).size() == 0) {  //check if toggle is selected or not
								driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__action--wildcard")).click();  //click on toggle button to enable
								waitElementInvisible(loading_cursor);
							}

							driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__search-input input")).click();
							driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__search-input input")).sendKeys("h");
							waitElementInvisible(loading_cursor);
							Thread.sleep(800);
							driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__footer--apply")).click();
							waitElementInvisible(loading_cursor);
							Thread.sleep(800);
							getScreenshot();
							//List<WebElement> rows1 = driver.findElements(By.cssSelector("#"+tablename+" [id='dc-table-graph'] td:nth-child(1) label"));
							//int count1 = rows1.size();
							String recordAfter = driver.findElement(By.cssSelector("#"+tablename+" #"+ResultsCount)).getText(); 

							int count1 = driver.findElements(By.id("col-0")).size();
							//	int count1 = driver.findElements(By.cssSelector("#coccidia-data-log tr")).size() - 1;
							Thread.sleep(800);
							for (int j = 0; j<count1; j++) {
								int k = i-1;
								int l = k-skipColumns;
								if (Integer.parseInt(recordAfter) > 0) {
								String str = driver.findElement(By.cssSelector("#"+tablename+" #row-"+j+" #col-"+l+" label")).getText();
								
								//	System.out.println(1 +": "+str);
								softAssert.assertTrue(str.startsWith("h") || str.startsWith("H"), "WildCard Starts With failed");
								}
							}

							driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__filter-icon")).click();
							waitElementInvisible(loading_cursor);	
							Thread.sleep(1500);

							driver.findElement(By.xpath("//*[text() = ' Ends With ']")).click();
							driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__footer--apply")).click();
							waitElementInvisible(loading_cursor);
							Thread.sleep(800);
							getScreenshot();

							//	List<WebElement> rows2 = driver.findElements(By.cssSelector("#"+tablename+" [id='dc-table-graph'] td:nth-child(1) label"));
							//	int count2 = rows2.size();
							int count2 = driver.findElements(By.id("col-0")).size();
							for (int j = 0; j<count2; j++) {
								int k = i-1;
								int l = k-skipColumns;
								if (Integer.parseInt(recordAfter) > 0) {
								String str = driver.findElement(By.cssSelector("#"+tablename+" #row-"+j+" #col-"+l+" label")).getText();
								softAssert.assertTrue(str.endsWith("h") || str.endsWith("H"), "WildCard Ends With failed");
								}
							}
							driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+")  .log-header__filter-icon")).click();
							waitElementInvisible(loading_cursor);	
							Thread.sleep(800);
							driver.findElement(By.xpath("//*[text() = ' Contains ']")).click();
							driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__footer--apply")).click();
							waitElementInvisible(loading_cursor);
							Thread.sleep(800);
							getScreenshot();
							//List<WebElement> rows3 = driver.findElements(By.cssSelector("#"+tablename+" [id='dc-table-graph'] td:nth-child(1) label"));
							//int count3 = rows3.size();
							int count3 = driver.findElements(By.id("col-0")).size();
							Thread.sleep(1000);
							for (int j = 0; j<count3; j++) {
								int k = i-1;
								int l = k-skipColumns;
								if (Integer.parseInt(recordAfter) > 0) {
								String str = driver.findElement(By.cssSelector("#"+tablename+" #row-"+j+" #col-"+l+" label")).getText();
								softAssert.assertTrue(str.contains("h") || str.contains("H"), "WildCard Contains failed");
								}
							}
					//		String recordAfter = driver.findElement(By.cssSelector("#"+tablename+" #"+ResultsCount)).getText(); 
							softAssert.assertNotEquals(recordAfter, recordBefore);		
							driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__clear-filter span")).click();
							waitElementInvisible(loading_cursor);
						}	
					}
					else {
						test.skip("Filter does not have wildcard option");
						results.createNode("Filter does not have wildcard option");
						saveResult(ITestResult.SKIP, null);
					}

					Thread.sleep(700);

					softAssert.assertAll();
					test.pass("Wildcards tested successfully");
					results.createNode("Wildcards tested successfully");
					saveResult(ITestResult.SUCCESS, null);
				}	
			}
			catch(AssertionError er) {
				test.fail("Wildcards failed to test successfully");
				results.createNode("Wildcards failed to test successfully");
				saveResult(ITestResult.FAILURE, new Exception(er));
			}
			catch(Exception ex) {
				test.fail("Wildcards failed to test successfully");
				results.createNode("Wildcards failed to test successfully");
				saveResult(ITestResult.FAILURE, new Exception(ex));
			}
		}			
	}
	
}
