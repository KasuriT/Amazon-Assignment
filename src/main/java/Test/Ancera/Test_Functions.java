package Test.Ancera;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import Models.FlockManagementModel;
import Test.Ancera.Reports.SalmonellaLog;

import static Test.Ancera.Test_Variables.*;
import static Test.Ancera.Helper.*;
import static Test.Ancera.Test_Elements.*;
import static Test.Ancera.Constants.*;

public class Test_Functions {

	@Test (enabled=true) 
	public static void NavigateToScreen(String url,  String name, String ReportPath, By id) throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-01: Navigate to "+name+" Screen", "This test case will navigate to "+name+" screen");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			steps.createNode("1. Hover to sidebar to expand the menu");
			steps.createNode("2. Select "+name+" from side bar menu");					

			driver.get(url);
			waitElementInvisible(loading_cursor);
			wait.until(ExpectedConditions.visibilityOfElementLocated(id));
			Thread.sleep(3000);	
			Assert.assertEquals(driver.findElement(id).getText(), ""+name); 			
			test.pass("User navigated to "+name+" screen successfully");
			results.createNode("User navigated to "+name+" screen successfully");
			test.addScreenCaptureFromPath(getScreenshot(""+name, ReportPath));
			saveResultNew(ITestResult.SUCCESS, ReportPath, null);
		}catch(AssertionError er){
			test.fail("User failed to navigate to "+name+" screen");
			results.createNode("User failed to navigate to "+name+" screen");
			saveResultNew(ITestResult.FAILURE, ReportPath, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("User failed to navigate to "+name+" screen");
			results.createNode("User failed to navigate to "+name+" screen");
			saveResultNew(ITestResult.FAILURE, ReportPath, ex);
		}		
	}


	@Test (enabled= true) 
	public static void Lock(String tablename, String name, String ReportPath, int skipColumns) throws InterruptedException, IOException {
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
					test.addScreenCaptureFromPath(getScreenshot(name, ReportPath));
					driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+")  .log-header__filter-icon")).click();
					waitElementInvisible(loading_cursor);	
					Thread.sleep(1000);

			//		System.out.println(columnName.getText());
					//	if (!columnName.getText().equals("Test Site") || columnName.getText().equals("Result Date") || columnName.getText().equals("Collection Site Name") || columnName.getText().equals("Farm") || columnName.getText().equals("Complex")) {
					if (driver.findElements(By.cssSelector("#"+tablename+" th:nth-child("+i+") "+footerCount)).size() != 0) {
						if (driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") "+footerCount)).getText().equals("Showing 1 - 1 Results")) {
							test.skip("Values not enough to test lock filter functionality");
							results.createNode("Values not enough to test lock filter functionality");
							saveResultNew(ITestResult.SKIP, ReportPath, null);
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
							test.addScreenCaptureFromPath(getScreenshot(name, ReportPath));
							Thread.sleep(1000);

							String recordsafterfilter = driver.findElement(By.cssSelector("#"+tablename+" #"+ResultsCount)).getText();
							softAssert.assertNotEquals(recordsafterfilter, recordBefore, "Filter failed to apply");
							steps.createNode("3. Close "+name+" screen");
							steps.createNode("4. Reopen "+name+" screen");
							driver.navigate().refresh();
							waitElementInvisible(loading_cursor);
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#"+tablename+" #"+ResultsCount)));
							Thread.sleep(3000);

							if (tablename.equals(programFeedTable)) {
								driver.findElement(programFeedProgramTab).click();
								waitElementInvisible(loading_cursor);
								Thread.sleep(2000);
							}

							steps.createNode("5. Verify lock filter remains applied");
							test.addScreenCaptureFromPath(getScreenshot(name, ReportPath));
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
						saveResultNew(ITestResult.SKIP, ReportPath, null);
						driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__filter-icon")).click();
					}
					softAssert.assertAll();
					test.pass("Lock functionality verified successfully");
					results.createNode("Lock functionality verified successfully");
					test.addScreenCaptureFromPath(getScreenshot(name, ReportPath));
					saveResultNew(ITestResult.SUCCESS, ReportPath, null);
				}
			}
			catch(AssertionError er) {
				test.fail("Column failed to Lock");
				results.createNode("Column failed to Lock");
				saveResultNew(ITestResult.FAILURE, ReportPath, new Exception(er));
			}
			catch(Exception ex) {
				test.fail("Column failed to Lock");
				results.createNode("Column failed to Lock");
				saveResultNew(ITestResult.FAILURE, ReportPath, new Exception(ex));
			}
		}
	}


	@Test (enabled= true) 
	public static void Wildcard1(String tablename, String name, String ReportPath, int skipColumns) throws InterruptedException, IOException {
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
							test.addScreenCaptureFromPath(getScreenshot("+name+", ReportPath));
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
							test.addScreenCaptureFromPath(getScreenshot("+name+", ReportPath));

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
							test.addScreenCaptureFromPath(getScreenshot("+name+", ReportPath));
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
						saveResultNew(ITestResult.SKIP, ReportPath, null);
					}

					Thread.sleep(700);

					softAssert.assertAll();
					test.pass("Wildcards tested successfully");
					results.createNode("Wildcards tested successfully");
					saveResultNew(ITestResult.SUCCESS, ReportPath, null);
				}	
			}
			catch(AssertionError er) {
				test.fail("Wildcards failed to test successfully");
				results.createNode("Wildcards failed to test successfully");
				saveResultNew(ITestResult.FAILURE, ReportPath, new Exception(er));
			}
			catch(Exception ex) {
				test.fail("Wildcards failed to test successfully");
				results.createNode("Wildcards failed to test successfully");
				saveResultNew(ITestResult.FAILURE, ReportPath, new Exception(ex));
			}
		}			
	}



	@Test 
	public static void Pagination(String tablename, String name, String ReportPath) throws InterruptedException, IOException {
		for (int i=0; i<=3;i++) {
			try {
				String[] paginationButtons = {lastPagePagination, previousPagePagination, firstPagePagination, nextPagePagination};
				test = extent.createTest("AN-Pagination-"+name+"-"+i+": Verify pagination functionality on clicking "+paginationButtons[i]);
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				SoftAssert softAssert = new SoftAssert();
				String recordBefore = driver.findElement(By.cssSelector("#"+tablename+" #"+ResultsCount)).getText();   //get result count
				test.addScreenCaptureFromPath(getScreenshot(name, ReportPath));
				test.addScreenCaptureFromPath(getScreenshot(name, ReportPath));


				String removeComma = recordBefore.replace(",", "");
				double x = Double.parseDouble(removeComma);
				double y = 100;
				double divide = Math.ceil(Math.abs(x/y));
				final int totalPages = (int)divide;
				waitElementInvisible(loading_cursor);
				String results = driver.findElement(By.cssSelector("#"+tablename+" #"+ResultsCount)).getText();   //get result count

				waitElementInvisible(loading_cursor);	
				steps.createNode("1. Verify pagination exists");
				Assert.assertTrue(driver.findElements(By.cssSelector("#"+tablename+" #activePageNumber")).size() != 0, "Pagination not displaying");

				if (NumberFormat.getNumberInstance(Locale.US).parse(results).intValue() > 100) {
					driver.findElement(By.cssSelector("#"+tablename+" #"+paginationButtons[i])).click();
					waitElementInvisible(loading_cursor);
					String pageCount =	driver.findElement(By.cssSelector("#"+tablename+" #activePageNumber")).getText();

					if (driver.findElements(By.id("message")).size() !=0) {
						Thread.sleep(500);
						test.addScreenCaptureFromPath(getScreenshot(name, ReportPath));	
						softAssert.fail("An error alert message displayed");
					}	

					if (paginationButtons[i].equals(lastPagePagination)) {
						steps.createNode("1. Click on '>>' icon in pagination");
						softAssert.assertEquals(pageCount, totalPages+"/"+totalPages);
					}

					if (paginationButtons[i].equals(previousPagePagination)) {	
						steps.createNode("1. Click on '<' icon in pagination");
						softAssert.assertEquals(pageCount, (totalPages-1)+"/"+totalPages);
					}

					if (paginationButtons[i].equals(firstPagePagination)) {	
						steps.createNode("1. Click on '<<' icon in pagination");
						softAssert.assertEquals(pageCount, 1+"/"+totalPages);
					}

					if (paginationButtons[i].equals(nextPagePagination)) {	
						steps.createNode("1. Click on '>' icon in pagination");
						softAssert.assertEquals(pageCount, 2+"/"+totalPages);
					}

					softAssert.assertAll();
					test.pass("Navigated to next page successfully");
					Test_Variables.results.createNode("Navigated to next page successfully");
					test.addScreenCaptureFromPath(getScreenshot(name, ReportPath));
					saveResultNew(ITestResult.SUCCESS, ReportPath, null);
				}
				else {
					Assert.assertTrue(true, "Records are less then 100; pagination cannot be tested");
					test.skip("Records are less then 100; pagination cannot be tested");
					Test_Variables.results.createNode("Records are less then 100; pagination cannot be tested");
					test.addScreenCaptureFromPath(getScreenshot(name, ReportPath));
					saveResultNew(ITestResult.SKIP, ReportPath, null);	
				}
			}
			catch(AssertionError er) {
				test.fail("Failed to get desired results on clicking button");
				results.createNode("Failed to get desired results on clicking button");
				saveResultNew(ITestResult.FAILURE, ReportPath, new Exception(er));
			}
			catch(Exception ex) {
				test.fail("Failed to get desired results on clicking button");
				results.createNode("Failed to get desired results on clicking button");
				saveResultNew(ITestResult.FAILURE, ReportPath, ex);
			}
		}
	}
	

	@Test (enabled= true) 
	public static void Sorting1(String tablename, String name, String ReportPath, int skipColumns) throws InterruptedException, IOException {
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
						saveResultNew(ITestResult.SKIP, ReportPath, null);
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
							test.addScreenCaptureFromPath(getScreenshot(name, ReportPath));			
						}

						column.click();
						waitElementInvisible(loading_cursor);					
						Thread.sleep(1000);
						if (driver.findElements(By.cssSelector("#"+tablename+" .fa-sort-amount-down")).size() != 0) {
							softAssert.assertEquals(driver.findElements(By.cssSelector("#"+tablename+" th:nth-child("+i+").sort_asc .log-header .mb-0")).size(), 1, "Did not sorted in descending order");
							softAssert.assertEquals(driver.findElements(alertMessage).size(), 0, "Exception message occured");
							test.addScreenCaptureFromPath(getScreenshot(name, ReportPath));
						}
						softAssert.assertAll();
						test.pass("Column sorted successfully");
						results.createNode("Column sorted successfully");
						saveResultNew(ITestResult.SUCCESS, ReportPath, null);
					}
				}
			}
			catch(AssertionError er) {
				test.fail("Column failed to sort");
				results.createNode("Column failed to sort");
				saveResultNew(ITestResult.FAILURE, ReportPath, new Exception(er));
			}
		}
	}
	
	
	@Test (description="Test Case: Test Table Rows") 
	public static void RowsPerPage1() throws InterruptedException, IOException {

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
					test.addScreenCaptureFromPath(getScreenshot("Coccidia Log", CoccidiaReportPath));
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
					test.addScreenCaptureFromPath(getScreenshot("Coccidia Log", CoccidiaReportPath));
					saveResultNew(ITestResult.SUCCESS, CoccidiaReportPath, null);
				}
				else {
					softAssert.assertTrue(true, "Records are less then "+tableRows[i]);
					test.pass("Records are less then "+tableRows[i]);
					results.createNode("Rcords are less then "+tableRows[i]);
					test.addScreenCaptureFromPath(getScreenshot("Coccidia Log", CoccidiaReportPath));
					saveResultNew(ITestResult.SUCCESS, CoccidiaReportPath, null);	
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
					test.addScreenCaptureFromPath(getScreenshot("Coccidia Log", CoccidiaReportPath));
					saveResultNew(ITestResult.SUCCESS, CoccidiaReportPath, null);	
				}

				else {
					softAssert.assertTrue(true, "Records are less then "+sum);
					test.pass("Records are less then "+sum);
					results.createNode("Records are less then "+sum);
					test.addScreenCaptureFromPath(getScreenshot("Coccidia Log", CoccidiaReportPath));
					saveResultNew(ITestResult.SUCCESS, CoccidiaReportPath, null);	
				}
				softAssert.assertAll();
			}

			catch(AssertionError er) {
				test.fail(tableRows[i]+" failed to display on next page");
				results.createNode(tableRows[i]+" failed to display on next page");
				saveResultNew(ITestResult.FAILURE, CoccidiaReportPath, new Exception(er));
			}
			catch(Exception ex) {
				test.fail(tableRows[i]+" failed to display on next page");
				results.createNode(tableRows[i]+" failed to display on next page");
				saveResultNew(ITestResult.FAILURE, CoccidiaReportPath, ex);
			}	
		}
	}

	
	@Test (description="Test Case: Test Table Rows") 
	public static void RowsPerPage(String tablename) throws InterruptedException, IOException {

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
					test.addScreenCaptureFromPath(getScreenshot("Coccidia Log", CoccidiaReportPath));
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
					test.addScreenCaptureFromPath(getScreenshot("Coccidia Log", CoccidiaReportPath));
					saveResultNew(ITestResult.SUCCESS, CoccidiaReportPath, null);
				}
				else {
					softAssert.assertTrue(true, "Records are less then "+tableRows[i]);
					test.skip("Records are less then "+tableRows[i]);
					results.createNode("Records are less then "+tableRows[i]);
					test.addScreenCaptureFromPath(getScreenshot("Coccidia Log", CoccidiaReportPath));
					saveResultNew(ITestResult.SKIP, CoccidiaReportPath, null);	
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
					test.addScreenCaptureFromPath(getScreenshot("Coccidia Log", CoccidiaReportPath));
					saveResultNew(ITestResult.SUCCESS, CoccidiaReportPath, null);	
				}

				else {
					softAssert.assertTrue(true, "Records are less then "+sum);
					test.skip("Records are less then "+sum);
					results.createNode("Records are less then "+sum);
					test.addScreenCaptureFromPath(getScreenshot("Coccidia Log", CoccidiaReportPath));
					saveResultNew(ITestResult.SKIP, CoccidiaReportPath, null);	
				}
				softAssert.assertAll();
			}

			catch(AssertionError er) {
				test.fail(tableRows[i]+" failed to display on next page");
				results.createNode(tableRows[i]+" failed to display on next page");
				saveResultNew(ITestResult.FAILURE, CoccidiaReportPath, new Exception(er));
			}
			catch(Exception ex) {
				test.fail(tableRows[i]+" failed to display on next page");
				results.createNode(tableRows[i]+" failed to display on next page");
				saveResultNew(ITestResult.FAILURE, CoccidiaReportPath, ex);
			}	
		}
	}
	
	
	

	@SuppressWarnings({ "unused", "resource" })
	@Test (enabled= true) 
	public static void CSVExport(String name, String ReportPath, String CSVFileName, String tablename, int filterNumber) throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-CSVExport: Verify user can download CSV file and verify the records", "This test case will verify that user can download CSV file");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on "+name+"; "+name+" screen opens");

			steps.createNode("1. Hover mouse towards table; Export file button becomes visible");
			steps.createNode("2. Click on the button; Dropdown cloud pop ups");
			steps.createNode("3. Verify the columns are same in table and CSV");
			SoftAssert softAssert = new SoftAssert();
			driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+filterNumber+") .log-header__filter-icon")).click();	
			waitElementInvisible(loading_cursor);	
			Thread.sleep(1000);						
			ClickElement.clickByCss(driver, "#"+tablename+" th:nth-child("+filterNumber+") li:nth-child(3) label");

			ClickElement.clickByCss(driver, "#"+tablename+" th:nth-child("+filterNumber+") .filter-popup__footer--apply");
			waitElementInvisible(loading_cursor);	
			Thread.sleep(2000);
			String getRowText = driver.findElement(By.id(ResultsCount)).getText();

			driver.findElement(By.cssSelector("#"+tablename+" #csv-action img")).click();
			Thread.sleep(1000);
			test.addScreenCaptureFromPath(getScreenshot(name, ReportPath));
			ClickElement.clickById(driver, "export-csv");
			waitElementInvisible(loading_cursor);	
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			SalmonellaLog fr= new SalmonellaLog();
			File newfile = fr.getTheNewestFile(fileDownloadPath, "csv");
			String filename= newfile.getName();
			softAssert.assertEquals(filename, CSVFileName+date+".csv");
			test.pass("CSV file downloaded successfully");
			results.createNode("CSV file downloads successfully");
			saveResultNew(ITestResult.SUCCESS, ReportPath, null);

			File file = new File(fileDownloadPath+"\\"+filename);
			if(file.exists()){
				System.out.println("File Exists");
			}	


			FileReader filereader = new FileReader(file);
			CSVReader reader = new CSVReader(filereader);
			reader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
			StringBuffer buffer = new StringBuffer();
			String data[];		    				

			int columnsCountTotal = 0;
			int rowsCount = 1;
			while((data = reader.readNext()) != null) {
				for (int i = 0; i<data.length; i++) {
					int rows = driver.findElements(By.cssSelector("tr")).size();
					if (rowsCount < rows) {
						int totalColumns = driver.findElements(By.cssSelector("#"+tablename+" tr:nth-child(1) td")).size() - 1;
						int columnsCount = columnsCountTotal+1;
						if (driver.findElements(By.cssSelector("#"+tablename+" tr:nth-child("+rowsCount+") td:nth-child("+rowsCount+")")).size() != 0 && columnsCount<=totalColumns) {
							softAssert.assertEquals(data[i].trim(), driver.findElement(By.cssSelector("#"+tablename+" tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).getText().trim(), "data not matched");
						}
						else {
							rowsCount = rowsCount+1;
							columnsCount =0;
							columnsCountTotal = 0;
						}
						columnsCountTotal++;
					}
				}
				//System.out.println(" ");
			}

			Path path = Paths.get(fileDownloadPath+"\\"+filename);
			long lines = 0;
			try {
				lines = Files.lines(path).count();
			} catch (IOException e) {
				e.printStackTrace();
			}

			long excludeHeader = lines - 1;
			String s = String.valueOf(excludeHeader);

			String str = getRowText;
			str = str.replace(",", "");
			softAssert.assertEquals(s, str);

			if(file.delete()) {
				System.out.println("CSV file deleted");  
			}
			softAssert.assertAll();
			test.pass("Column data exported successfully");
			results.createNode("Column data exported successfully");
			saveResultNew(ITestResult.SUCCESS, ReportPath, null);
		}
		catch(AssertionError er) {
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			saveResultNew(ITestResult.FAILURE, ReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			saveResultNew(ITestResult.FAILURE, ReportPath, ex);
		}
		Thread.sleep(1000);
	}
	
	
	
	@SuppressWarnings({ "unused", "resource" })
	@Test (enabled= true) 
	public static void CSVExport1(String name, String ReportPath, String CSVFileName, String tablename, int filterNumber, int skipColumns) throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-CSVExport: Verify user can download CSV file and verify the records", "This test case will verify that user can download CSV file");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on "+name+"; "+name+" screen opens");

			steps.createNode("1. Hover mouse towards table; Export file button becomes visible");
			steps.createNode("2. Click on the button; Dropdown cloud pop ups");
			steps.createNode("3. Verify the columns are same in table and CSV");
			SoftAssert softAssert = new SoftAssert();

			driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+filterNumber+") .log-header__filter-icon")).click();	
			waitElementInvisible(loading_cursor);	
			Thread.sleep(1000);						
			ClickElement.clickByCss(driver, "#"+tablename+" th:nth-child("+filterNumber+") li:nth-child(3) label");

			ClickElement.clickByCss(driver, "#"+tablename+" th:nth-child("+filterNumber+") .filter-popup__footer--apply");
			waitElementInvisible(loading_cursor);	
			Thread.sleep(2000);
			String getRowText = driver.findElement(By.id(ResultsCount)).getText();

			driver.findElement(By.cssSelector("#"+tablename+" #csv-action img")).click();
			Thread.sleep(1000);
			test.addScreenCaptureFromPath(getScreenshot(name, ReportPath));
			ClickElement.clickById(driver, "export-csv");
			waitElementInvisible(loading_cursor);	
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			SalmonellaLog fr= new SalmonellaLog();
			File newfile = fr.getTheNewestFile(fileDownloadPath, "csv");
			String filename= newfile.getName();
			softAssert.assertEquals(filename, CSVFileName+date+".csv");
			test.pass("CSV file downloaded successfully");
			results.createNode("CSV file downloads successfully");
			saveResultNew(ITestResult.SUCCESS, ReportPath, null);

			File file = new File(fileDownloadPath+"\\"+filename);
			if(file.exists()){
				System.out.println("File Exists");
			}	


			FileReader filereader = new FileReader(file);
			CSVReader reader = new CSVReader(filereader);
			reader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
			StringBuffer buffer = new StringBuffer();
			String data[];		    				

			int columnsCountTotal = 0;
			int rowsCount = 1;
			while((data = reader.readNext()) != null) {
				for (int i = 0; i<data.length; i++) {
					int rows = driver.findElements(By.cssSelector("tr")).size();
					if (rowsCount < rows) {
						int totalColumns = driver.findElements(By.cssSelector("#"+tablename+" tr:nth-child(1) td")).size() - 1;
					//	int columnsCount = columnsCountTotal+3;
						int columnsCount = columnsCountTotal+2;
						if (driver.findElements(By.cssSelector("#"+tablename+" tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).size() != 0 && columnsCount<=totalColumns) {
							softAssert.assertEquals(data[i].trim(), driver.findElement(By.cssSelector("#"+tablename+" tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).getText().trim(), "data not matched");
						}
						else {
							rowsCount = rowsCount+1;
							columnsCount =0;
							columnsCountTotal = skipColumns;   //for salmonella and coccidia -1 else 0
						}
						columnsCountTotal++;
					}
				}
				//System.out.println(" ");
			}

			Path path = Paths.get(fileDownloadPath+"\\"+filename);
			long lines = 0;
			try {
				lines = Files.lines(path).count();
			} catch (IOException e) {
				e.printStackTrace();
			}

			long excludeHeader = lines - 1;
			String s = String.valueOf(excludeHeader);

			String str = getRowText;
			str = str.replace(",", "");
			softAssert.assertEquals(s, str);

			if(file.delete()) {
				System.out.println("CSV file deleted");  
			}
			softAssert.assertAll();
			test.pass("Column data exported successfully");
			results.createNode("Column data exported successfully");
			saveResultNew(ITestResult.SUCCESS, ReportPath, null);
		}
		catch(AssertionError er) {
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			saveResultNew(ITestResult.FAILURE, ReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			saveResultNew(ITestResult.FAILURE, ReportPath, ex);
		}
		Thread.sleep(1000);
	}


	public static void openEditUserPopup(String emailAddress) throws InterruptedException, IOException {
		driver.get(url_user);
		waitElementInvisible(loading_cursor);
		waitElementVisible(usercreateButton);
		Thread.sleep(3000);
		driver.findElement(By.id("userEmail_show-filter")).click();
		waitElementInvisible(loading_cursor);
		driver.findElement(By.id("userEmail_search-input")).sendKeys(emailAddress);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("th:nth-child(4) li:nth-child(1) label")).click();					  
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		driver.findElement(By.id("userEmail_apply")).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		scroll(By.id("edit-user-1"));
		Thread.sleep(1000); 
		try {
		driver.findElement(By.cssSelector("#edit-user-1 img")).click();
		}
		catch (StaleElementReferenceException ex) {
			driver.findElement(By.cssSelector("#edit-user-1 img")).click();
		}
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);

	}


	public static void openEditOrgPopup(String orgName) throws InterruptedException, IOException {
		for (int i=1;i<driver.findElements(By.cssSelector("tr")).size(); i++) {
			if (driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+orgNameCol+" label")).getText().equals(orgName)) {
				driver.findElement(By.id("edit-orgn-"+i)).click();
				waitElementInvisible(loading_cursor);
				break;
			}
		}
	}
	

	////////////////////////////////Flock Management//////////////////////////////////////////////

	public static void openFlockAudit() throws InterruptedException, IOException {
		for(int i=1; i<driver.findElements(By.cssSelector("tr")).size(); i++) {
			if (getText(By.cssSelector("tr:nth-child("+i+") #col-"+flockIntegratorFlockIDCol+" label")).equals(FlockManagementModel.flockIntegratorID) && getText(By.cssSelector("tr:nth-child("+i+") #col-"+flockBirdSizePlacementCol+" label")).equals(FlockManagementModel.flockBirdSize)) {
				
				int j = i-1;
			
			System.out.println(1);
				driver.findElement(By.id("audit-trial-"+j)).click();
		
				waitElementInvisible(loading_cursor);	
				Thread.sleep(1500);	
				break;
			}
		}
	}

	
	public static void openEditFlock() throws InterruptedException, IOException {
		for(int i=1; i<driver.findElements(By.cssSelector("tr")).size(); i++) {
			if (driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+flockIntegratorCol+" label")).getText().equals(FlockManagementModel.flockIntegratorID)) {
				driver.findElement(By.id("edit-feed-program-"+i)).click();
				waitElementInvisible(loading_cursor);	
				Thread.sleep(1500);	
				break;
			}
		}
	}
////////////////////////////////End Flock Management//////////////////////////////////////////////

	public static void fieldLevelReset() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		driver.findElement(By.id("edit-field-access")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		driver.findElement(By.id("btn-reset")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("btn-save")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
	}



	public static void login() throws InterruptedException, IOException {
		driver.findElement(By.id("logout")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(login_email);
		driver.findElement(By.id("pwd")).clear();
		driver.findElement(By.id("pwd")).sendKeys(login_password);
		driver.findElement(By.id("btn-sign-in")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
	}


	public static void getUserAccess() throws InterruptedException, IOException {
		driver.get(url_user);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		for(int i=1; i<=500; i++) {
			if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(4) label")).getText().equals(login_email)) {
				WebElement scroll = driver.findElement(By.id("edit-user-"+i));
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
				Thread.sleep(1000);
				driver.findElement(By.id("edit-user-"+i)).click();
				break;
			}
		}
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(4000);
		driver.findElement(By.id("btn-next")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("btn-next")).click();
		Thread.sleep(1000);
	}



	public static boolean isFileDownload(String downloadPath, String fileName) {
		File dir = new File(downloadPath);
		File[] dirContents = dir.listFiles();

		for (int i = 0; i < dirContents.length; i++) {
			if (dirContents[i].getName().equals(fileName)) {
				// File has been found, it can now be deleted:
				dirContents[i].delete();
				return true;
			}
		}
		return false;
	}


	public static boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();

		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().equals(fileName))
				return flag=true;
		}
		return flag;
	}


}
