package Test.Ancera;

import static Test.Ancera.Helper.driver;
import static Test.Ancera.Helper.getScreenshot;
import static Test.Ancera.Helper.waitElementInvisible;
import static Test.Ancera.Test_Elements.ResultsCount;
import static Test.Ancera.Test_Elements.firstPagePagination;
import static Test.Ancera.Test_Elements.lastPagePagination;
import static Test.Ancera.Test_Elements.loading_cursor;
import static Test.Ancera.Test_Elements.nextPagePagination;
import static Test.Ancera.Test_Elements.orgNameCol;
import static Test.Ancera.Test_Elements.previousPagePagination;
import static Test.Ancera.Test_Variables.PreConditions;
import static Test.Ancera.Test_Variables.Results;
import static Test.Ancera.Test_Variables.Steps;
import static Test.Ancera.Test_Variables.extent;
import static Test.Ancera.Test_Variables.lstOrganizationCreate;
import static Test.Ancera.Test_Variables.preconditions;
import static Test.Ancera.Test_Variables.results;
import static Test.Ancera.Test_Variables.steps;
import static Test.Ancera.Test_Variables.test;

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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

import Models.CoccidiaModel;
import Models.ReportFilters;
import Test.Ancera.Reports.SalmonellaLog;

public class Test_Functions {

	@Test (enabled=true) 
	public static void NavigateToScreen(String url,  String name, String ReportPath, By id) throws InterruptedException, IOException {
		try{
			Test_Variables.test = Test_Variables.extent.createTest("AN-01: Navigate to "+name+" Screen", "This test case will navigate to "+name+" screen");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.steps.createNode("1. Hover to sidebar to expand the menu");
			Test_Variables.steps.createNode("2. Select "+name+" from side bar menu");					

			Helper.driver.get(url);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(id));
			Thread.sleep(3000);	
			Assert.assertEquals(Helper.driver.findElement(id).getText(), ""+name); 			
			Test_Variables.test.pass("User navigated to "+name+" screen successfully");
			Test_Variables.results.createNode("User navigated to "+name+" screen successfully");
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot(""+name, ReportPath));
			Helper.saveResultNew(ITestResult.SUCCESS, ReportPath, null);
		}catch(AssertionError er){
			Test_Variables.test.fail("User failed to navigate to "+name+" screen");
			Test_Variables.results.createNode("User failed to navigate to "+name+" screen");
			Helper.saveResultNew(ITestResult.FAILURE, ReportPath, new Exception(er));
		}	
		catch(Exception ex){
			Test_Variables.test.fail("User failed to navigate to "+name+" screen");
			Test_Variables.results.createNode("User failed to navigate to "+name+" screen");
			Helper.saveResultNew(ITestResult.FAILURE, ReportPath, ex);
		}		
	}


	@Test (enabled= true) 
	public static void Lock(String tablename, String name, String ReportPath, int skipColumns) throws InterruptedException, IOException {
		int totalNumberofColumns = Helper.driver.findElements(By.cssSelector("#"+tablename+" th .log-header .mb-0")).size() + skipColumns;
		for (int i=1;i<=totalNumberofColumns; i++) {  //get size of columns
			try {
				SoftAssert softAssert = new SoftAssert();
				String recordBefore = Helper.driver.findElement(By.cssSelector("#"+tablename+" #"+Test_Elements.ResultsCount)).getText(); 
				if ( Helper.driver.findElements(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__filter-icon")).size() != 0) {  //check column with ehich filter icon is applied
					WebElement column = Helper.driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__filter-icon"));
					WebElement columnName = Helper.driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header .mb-0"));

					Test_Variables.test = Test_Variables.extent.createTest("AN-Lock-"+i+": Verify user can apply filter on "+columnName.getText()+" column", "This testcase will verify that user can apply filter on column");
					Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
					Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
					Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

					Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
					Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
					Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
					Test_Variables.preconditions.createNode("4. Click on "+name+"; "+name+" screen opens");

					if (Helper.driver.findElements(By.cssSelector("#remove-filters.d-none")).size() == 0) {
						Helper.driver.findElement(By.id(Test_Elements.UnlockFilter)).click();
						Helper.driver.findElement(By.id(Test_Elements.ResetFilters)).click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
						Thread.sleep(2000);
					}

					WebElement filter_scroll = column;
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot(name, ReportPath));
					Helper.driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+")  .log-header__filter-icon")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
					Thread.sleep(1000);

					System.out.println(columnName.getText());
					//	if (!columnName.getText().equals("Test Site") || columnName.getText().equals("Result Date") || columnName.getText().equals("Collection Site Name") || columnName.getText().equals("Farm") || columnName.getText().equals("Complex")) {
					if (Helper.driver.findElements(By.cssSelector("#"+tablename+" th:nth-child("+i+") "+Test_Elements.footerCount)).size() != 0) {
						if (Helper.driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") "+Test_Elements.footerCount)).getText().equals("Showing 1 - 1 Results")) {
							Test_Variables.test.skip("Values not enough to test lock filter functionality");
							Test_Variables.results.createNode("Values not enough to test lock filter functionality");
							Helper.saveResultNew(ITestResult.SKIP, ReportPath, null);
							Helper.driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__filter-icon")).click();
						}
						else {
							Helper.driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") li:nth-child(3) label")).click();
							Thread.sleep(500);
							Test_Variables.steps.createNode("1. Select any filter and click on apply filter button");
							Helper.driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__footer--apply")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
							Test_Variables.steps.createNode("2. Click on lock button");	
							Helper.driver.findElement(By.cssSelector("#"+tablename+" #"+Test_Elements.LockFilter)).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot(name, ReportPath));
							Thread.sleep(1000);

							String recordsafterfilter = Helper.driver.findElement(By.cssSelector("#"+tablename+" #"+Test_Elements.ResultsCount)).getText();
							softAssert.assertNotEquals(recordsafterfilter, recordBefore, "Filter failed to apply");
							Test_Variables.steps.createNode("3. Close "+name+" screen");
							Test_Variables.steps.createNode("4. Reopen "+name+" screen");
							Helper.driver.navigate().refresh();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
							Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#"+tablename+" #"+Test_Elements.ResultsCount)));
							Thread.sleep(3000);

							if (tablename.equals(Test_Elements.programFeedTable)) {
								Helper.driver.findElement(Test_Elements.programFeedProgramTab).click();
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
								Thread.sleep(2000);
							}

							Test_Variables.steps.createNode("5. Verify lock filter remains applied");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot(name, ReportPath));
							softAssert.assertEquals(Helper.driver.findElement(By.cssSelector("#"+tablename+" #"+Test_Elements.ResultsCount)).getText(), recordsafterfilter, "Lock functionality failed");
							Thread.sleep(1000);
							Helper.driver.findElement(By.cssSelector("#"+tablename+" #"+Test_Elements.UnlockFilter)).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
							Helper.driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__clear-filter span")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
						}
					}
					else {
						Test_Variables.test.skip("Heirarchy filter cannot be tested with this method");
						Test_Variables.results.createNode("Heirarchy filter cannot be tested with this method");
						Helper.saveResultNew(ITestResult.SKIP, ReportPath, null);
						Helper.driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__filter-icon")).click();
					}
					softAssert.assertAll();
					Test_Variables.test.pass("Lock functionality verified successfully");
					Test_Variables.results.createNode("Lock functionality verified successfully");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot(name, ReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, ReportPath, null);
				}
			}
			catch(AssertionError er) {
				Test_Variables.test.fail("Column failed to Lock");
				Test_Variables.results.createNode("Column failed to Lock");
				Helper.saveResultNew(ITestResult.FAILURE, ReportPath, new Exception(er));
			}
			catch(Exception ex) {
				Test_Variables.test.fail("Column failed to Lock");
				Test_Variables.results.createNode("Column failed to Lock");
				Helper.saveResultNew(ITestResult.FAILURE, ReportPath, new Exception(ex));
			}
		}
	}


	@Test (enabled= true) 
	public static void Wildcard(String tablename, String name, String ReportPath, int skipColumns) throws InterruptedException, IOException {
		int totalNumberofColumns = Helper.driver.findElements(By.cssSelector("#"+tablename+" th .log-header .mb-0")).size() + skipColumns;   //get total columns and skip irrelevant columns
		for (int i=1;i<=totalNumberofColumns; i++) {
			try {
				String recordBefore = Helper.driver.findElement(By.cssSelector("#"+tablename+" #"+Test_Elements.ResultsCount)).getText();   //get result count
				if ( Helper.driver.findElements(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__filter-icon")).size() != 0) {     //check column has filter icon
					WebElement column = Helper.driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__filter-icon"));
					WebElement columnName = Helper.driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header .mb-0"));
					Test_Variables.test = Test_Variables.extent.createTest("AN_Wildcard-"+i+": Verify user can apply wildcard on "+columnName.getText()+" filter", "This testcase will verify that user can apply wildcard filter");
					Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
					Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
					Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

					Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
					Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
					Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
					Test_Variables.preconditions.createNode("4. Click on "+name+"; "+name+" page opens");

					SoftAssert softAssert = new SoftAssert();
					WebElement filter_scroll = columnName;	//scroll to filter			
					((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll);   
					Helper.driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__filter-icon")).click();   //open filter popup
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
					Thread.sleep(500);

					if (Helper.driver.findElements(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__action--wildcard")).size() != 0) {
						if (Helper.driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__action--wildcard")).isDisplayed()) {  //check if filter has wildcard option
							if (Helper.driver.findElements(By.cssSelector("#"+tablename+" th:nth-child("+i+") .data-log-radio")).size() == 0) {  //check if toggle is selected or not
								Helper.driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__action--wildcard")).click();  //click on toggle button to enable
								Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
							}

							Helper.driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__search-input input")).click();
							Helper.driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__search-input input")).sendKeys("h");
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
							Thread.sleep(800);
							Helper.driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__footer--apply")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
							Thread.sleep(800);
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("+name+", ReportPath));
							List<WebElement> rows1 = Helper.driver.findElements(By.cssSelector("#"+tablename+" [id='dc-table-graph'] td:nth-child(1) label"));
							int count1 = rows1.size();
							//	int count1 = Helper.driver.findElements(By.cssSelector("#coccidia-data-log tr")).size() - 1;
							Thread.sleep(800);
							for (int j = 0; j<count1; j++) {
								int k = i-1;
								int l = k-skipColumns;
								String str = Helper.driver.findElement(By.cssSelector("#"+tablename+" #row-"+j+" #col-"+l+" label")).getText();
								//	System.out.println(1 +": "+str);
								softAssert.assertTrue(str.startsWith("h") || str.startsWith("H"), "WildCard Starts With failed");

							}

							Helper.driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__filter-icon")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
							Thread.sleep(1500);

							Helper.driver.findElement(By.xpath("//*[text() = ' Ends With ']")).click();
							Helper.driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__footer--apply")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
							Thread.sleep(800);
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("+name+", ReportPath));

							List<WebElement> rows2 = Helper.driver.findElements(By.cssSelector("#"+tablename+" [id='dc-table-graph'] td:nth-child(1) label"));
							int count2 = rows2.size();
							for (int j = 0; j<count2; j++) {
								int k = i-1;
								int l = k-skipColumns;
								String str = Helper.driver.findElement(By.cssSelector("#"+tablename+" #row-"+j+" #col-"+l+" label")).getText();
								softAssert.assertTrue(str.endsWith("h") || str.endsWith("H"), "WildCard Ends With failed");
							}
							Helper.driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+")  .log-header__filter-icon")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
							Thread.sleep(800);
							Helper.driver.findElement(By.xpath("//*[text() = ' Contains ']")).click();
							Helper.driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .filter-popup__footer--apply")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
							Thread.sleep(800);
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("+name+", ReportPath));
							List<WebElement> rows3 = Helper.driver.findElements(By.cssSelector("#"+tablename+" [id='dc-table-graph'] td:nth-child(1) label"));
							int count3 = rows3.size();
							//int count3 = Integer.parseInt(Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText());
							Thread.sleep(1000);
							for (int j = 0; j<count3; j++) {
								int k = i-1;
								int l = k-skipColumns;
								String str = Helper.driver.findElement(By.cssSelector("#"+tablename+" #row-"+j+" #col-"+l+" label")).getText();
								softAssert.assertTrue(str.contains("h") || str.contains("H"), "WildCard Contains failed");
							}
							String recordAfter = Helper.driver.findElement(By.cssSelector("#"+tablename+" #"+Test_Elements.ResultsCount)).getText(); 
							softAssert.assertNotEquals(recordAfter, recordBefore);		
							Helper.driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header__clear-filter span")).click();
							Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
						}	
					}
					else {
						Test_Variables.test.skip("Filter does not have wildcard option");
						Test_Variables.results.createNode("Filter does not have wildcard option");
						Helper.saveResultNew(ITestResult.SKIP, ReportPath, null);
					}

					Thread.sleep(700);

					softAssert.assertAll();
					Test_Variables.test.pass("Wildcards tested successfully");
					Test_Variables.results.createNode("Wildcards tested successfully");
					Helper.saveResultNew(ITestResult.SUCCESS, ReportPath, null);
				}	
			}
			catch(AssertionError er) {
				Test_Variables.test.fail("Wildcards failed to test successfully");
				Test_Variables.results.createNode("Wildcards failed to test successfully");
				Helper.saveResultNew(ITestResult.FAILURE, ReportPath, new Exception(er));
			}
			catch(Exception ex) {
				Test_Variables.test.fail("Wildcards failed to test successfully");
				Test_Variables.results.createNode("Wildcards failed to test successfully");
				Helper.saveResultNew(ITestResult.FAILURE, ReportPath, new Exception(ex));
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
				test.addScreenCaptureFromPath(Helper.getScreenshot(name, ReportPath));
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
				Assert.assertTrue(Helper.driver.findElements(By.cssSelector("#"+tablename+" #activePageNumber")).size() != 0, "Pagination not displaying");

				if (NumberFormat.getNumberInstance(Locale.US).parse(results).intValue() > 100) {
					driver.findElement(By.cssSelector("#"+tablename+" #"+paginationButtons[i])).click();
					waitElementInvisible(loading_cursor);
					String pageCount =	Helper.driver.findElement(By.cssSelector("#"+tablename+" #activePageNumber")).getText();

					if (driver.findElements(By.id("message")).size() !=0) {
						Thread.sleep(500);
						test.addScreenCaptureFromPath(Helper.getScreenshot(name, ReportPath));	
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
					test.addScreenCaptureFromPath(Helper.getScreenshot(name, ReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, ReportPath, null);
				}
				else {
					Assert.assertTrue(true, "Records are less then 100; pagination cannot be tested");
					test.skip("Records are less then 100; pagination cannot be tested");
					Test_Variables.results.createNode("Records are less then 100; pagination cannot be tested");
					test.addScreenCaptureFromPath(Helper.getScreenshot(name, ReportPath));
					Helper.saveResultNew(ITestResult.SKIP, ReportPath, null);	
				}
			}
			catch(AssertionError er) {
				test.fail("Failed to get desired results on clicking button");
				Test_Variables.results.createNode("Failed to get desired results on clicking button");
				Helper.saveResultNew(ITestResult.FAILURE, ReportPath, new Exception(er));
			}
			catch(Exception ex) {
				test.fail("Failed to get desired results on clicking button");
				Test_Variables.results.createNode("Failed to get desired results on clicking button");
				Helper.saveResultNew(ITestResult.FAILURE, ReportPath, ex);
			}
		}
	}

	
	@Test (enabled= true) 
	public static void Sorting(String tablename, String name, String ReportPath) throws InterruptedException, IOException {
		for (int i=1;i<=Helper.driver.findElements(By.cssSelector("#"+tablename+" th")).size(); i++) {
			try {
				SoftAssert softAssert = new SoftAssert();

				if (Helper.driver.findElements(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header .mb-0")).size() !=0) {
					if (!Helper.driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header label")).getText().equals("Result Time") || !Helper.driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header label")).getText().equals("Result Date")) {
						WebElement column = Helper.driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header .mb-0"));
						Test_Variables.test = Test_Variables.extent.createTest("AN-Sorting-"+i+": Apply Sorting on "+column.getText()+" column", "This test case will verify that user can apply sorting on "+column.getText()+ " column");
						Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
						Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
						Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

						Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
						Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
						Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
						Test_Variables.preconditions.createNode("4. Click on "+name+"; "+name+" screen opens");

						WebElement filter_scroll = column;
						((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", filter_scroll); 
						Test_Variables.steps.createNode("1. Click on "+column.getText()+" column header");

						column.click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));					
						Thread.sleep(1000);
						if (Helper.driver.findElements(By.cssSelector("#"+tablename+" .fa-sort-amount-down")).size() != 0) {
							softAssert.assertEquals(Helper.driver.findElements(By.cssSelector("#"+tablename+" th:nth-child("+i+").sort_desc .log-header .mb-0")).size(), 1, "Did not sorted in descending order");
							softAssert.assertEquals(Helper.driver.findElements(Test_Elements.alertMessage).size(), 0, "Exception message occured");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot(name, ReportPath));			
						}

						column.click();
						Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));					
						Thread.sleep(1000);
						if (Helper.driver.findElements(By.cssSelector("#"+tablename+" .fa-sort-amount-down")).size() != 0) {
							softAssert.assertEquals(Helper.driver.findElements(By.cssSelector("#"+tablename+" th:nth-child("+i+").sort_asc .log-header .mb-0")).size(), 1, "Did not sorted in descending order");
							softAssert.assertEquals(Helper.driver.findElements(Test_Elements.alertMessage).size(), 0, "Exception message occured");
							Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot(name, ReportPath));
						}
					}
					softAssert.assertAll();
					Test_Variables.test.pass("Column sorted successfully");
					Test_Variables.results.createNode("Column sorted successfully");
					Helper.saveResultNew(ITestResult.SUCCESS, ReportPath, null);
				}
			}
			catch(AssertionError er) {
				Test_Variables.test.fail("Column failed to sort");
				Test_Variables.results.createNode("Column failed to sort");
				Helper.saveResultNew(ITestResult.FAILURE, ReportPath, new Exception(er));
			}
		}
	}

	
	@Test (description="Test Case: Test Table Rows") 
	public static void RowsPerPage() throws InterruptedException, IOException {

		int[] tableRows = {100, 250, 500};
		for (int i=0; i<=tableRows.length; i++) {
			try {
				Test_Variables.test = Test_Variables.extent.createTest("Verify user can apply "+tableRows[i]+" rows per page");
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Open any Report");

				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Test_Elements.ResultsCount))); 
				Thread.sleep(500);	

				Actions actions = new Actions(Helper.driver);
				SoftAssert softAssert = new SoftAssert();
				Test_Variables.steps.createNode("1. Select "+tableRows[i]+" from dropdown below");
				String results1 = Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText();

				if (NumberFormat.getNumberInstance(Locale.US).parse(results1).intValue() > tableRows[i]) {
					WebElement expandFilter = Helper.driver.findElement(By.id("rows"));
					actions.moveToElement(expandFilter).click().perform();				
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(2000);
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
					int j = i+1;
					Helper.driver.findElement(By.cssSelector("option:nth-child("+j+")")).click();
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
					Thread.sleep(1000);
					List<WebElement> rows = Helper.driver.findElements(By.cssSelector("tr"));
					int count = rows.size();
					int new_count = count - 4;
					
					softAssert.assertEquals(new_count, tableRows[i]);
					Test_Variables.test.pass(tableRows[i]+" displayed succcessfully");
					Test_Variables.results.createNode(tableRows[i]+" displayed succcessfully");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);
				}
				else {
					softAssert.assertTrue(true, "Records are less then "+tableRows[i]);
					Test_Variables.test.pass("Records are less then "+tableRows[i]);
					Test_Variables.results.createNode("Rcords are less then "+tableRows[i]);
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);	
				}

				Test_Variables.test = Test_Variables.extent.createTest("Verify "+tableRows[i]+" rows per page remained apply on moving to next page");
				Test_Variables.steps.createNode("1. Select "+tableRows[i]+" from dropdown below");
				Test_Variables.steps.createNode("2. Go to next page from pagination");
				Test_Variables.steps.createNode("3. Verify that still "+tableRows[i]+" is selected");

				String results2 = Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText();
				int sum = tableRows[i] + tableRows[i];

				if (NumberFormat.getNumberInstance(Locale.US).parse(results2).intValue() > sum) {

					ClickElement.clickById(Helper.driver, "next-page");
					Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
					Thread.sleep(1000);
					List<WebElement> rows = Helper.driver.findElements(By.cssSelector("tr"));
					int count = rows.size();
					int new_count = count - 4;
					//System.out.println("ROW COUNT : "+new_count);
					softAssert.assertEquals(new_count, tableRows[i]);
					Test_Variables.test.pass(tableRows[i]+"records displayed succcessfully on next page");
					Test_Variables.results.createNode(tableRows[i]+"records displayed succcessfully on next page");
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);	
				}

				else {
					softAssert.assertTrue(true, "Records are less then "+sum);
					Test_Variables.test.pass("Records are less then "+sum);
					Test_Variables.results.createNode("Records are less then "+sum);
					Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Coccidia Log", Constants.CoccidiaReportPath));
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.CoccidiaReportPath, null);	
				}
				softAssert.assertAll();
			}

			catch(AssertionError er) {
				Test_Variables.test.fail(tableRows[i]+" failed to display on next page");
				Test_Variables.results.createNode(tableRows[i]+" failed to display on next page");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, new Exception(er));
			}
			catch(Exception ex) {
				Test_Variables.test.fail(tableRows[i]+" failed to display on next page");
				Test_Variables.results.createNode(tableRows[i]+" failed to display on next page");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.CoccidiaReportPath, ex);
			}	
		}
	}
	
	

	@SuppressWarnings({ "unused", "resource" })
	@Test (enabled= true) 
	public static void CSVExport(String name, String ReportPath, String CSVFileName, String tablename, int filterNumber) throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-CSVExport: Verify user can download CSV file and verify the records", "This test case will verify that user can download CSV file");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on "+name+"; "+name+" screen opens");

			Test_Variables.steps.createNode("1. Hover mouse towards table; Export file button becomes visible");
			Test_Variables.steps.createNode("2. Click on the button; Dropdown cloud pop ups");
			Test_Variables.steps.createNode("3. Verify the columns are same in table and CSV");
			SoftAssert softAssert = new SoftAssert();
			Helper.driver.findElement(By.cssSelector("#"+tablename+" th:nth-child("+filterNumber+") .log-header__filter-icon")).click();	
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
			Thread.sleep(1000);						
			ClickElement.clickByCss(Helper.driver, "#"+tablename+" th:nth-child("+filterNumber+") li:nth-child(3) label");

			ClickElement.clickByCss(Helper.driver, "#"+tablename+" th:nth-child("+filterNumber+") .filter-popup__footer--apply");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
			Thread.sleep(2000);
			String getRowText = Helper.driver.findElement(By.id(Test_Elements.ResultsCount)).getText();

			Helper.driver.findElement(By.cssSelector("#"+tablename+" #csv-action img")).click();
			Thread.sleep(1000);
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot(name, ReportPath));
			ClickElement.clickById(Helper.driver, "export-csv");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));	
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			SalmonellaLog fr= new SalmonellaLog();
			File newfile = fr.getTheNewestFile(Test_Variables.fileDownloadPath, "csv");
			String filename= newfile.getName();
			softAssert.assertEquals(filename, CSVFileName+date+".csv");
			Test_Variables.test.pass("CSV file downloaded successfully");
			Test_Variables.results.createNode("CSV file downloads successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, ReportPath, null);

			File file = new File(Test_Variables.fileDownloadPath+"\\"+filename);
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
					int rows = Helper.driver.findElements(By.cssSelector("tr")).size();
					if (rowsCount < rows) {
						int totalColumns = Helper.driver.findElements(By.cssSelector("#"+tablename+" tr:nth-child(1) td")).size() - 1;
						int columnsCount = columnsCountTotal+1;
						if (Helper.driver.findElements(By.cssSelector("#"+tablename+" tr:nth-child("+rowsCount+") td:nth-child("+rowsCount+")")).size() != 0 && columnsCount<=totalColumns) {
							softAssert.assertEquals(data[i].trim(), Helper.driver.findElement(By.cssSelector("#"+tablename+" tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).getText().trim(), "data not matched");
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

			Path path = Paths.get(Test_Variables.fileDownloadPath+"\\"+filename);
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
			Test_Variables.test.pass("Column data exported successfully");
			Test_Variables.results.createNode("Column data exported successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, ReportPath, null);
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, ReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, ReportPath, ex);
		}
		Thread.sleep(1000);
	}

	
	public static void openEditUserPopup(String emailAddress) throws InterruptedException, IOException {
		Helper.driver.get(Constants.url_user);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(1000);
		
		Helper.driver.findElement(By.id("userEmail_show-filter")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Helper.driver.findElement(By.id("userEmail_search-input")).sendKeys(emailAddress);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(3000);
		Helper.driver.findElement(By.cssSelector("th:nth-child(4) li:nth-child(1) label")).click();					  
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(1000);
		Helper.driver.findElement(By.id("userEmail_apply")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(1000);
		WebElement scroll = Helper.driver.findElement(By.id("edit-user-1"));
		((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
		Thread.sleep(1000); 
		Helper.driver.findElement(By.cssSelector("#edit-user-1 img")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(3000);
		
	}
	
	
	public static void openEditOrgPopup() throws InterruptedException, IOException {
		for (int i=1;i<driver.findElements(By.cssSelector("tr")).size(); i++) {
			if (driver.findElement(By.cssSelector("tr:nth-child("+i+") #col-"+orgNameCol+" label")).getText().equals(lstOrganizationCreate.get(0))) {
				driver.findElement(By.id("edit-orgn-"+i)).click();
				waitElementInvisible(loading_cursor);
				break;
			}
		}
	}

	public static void searchOrg() throws InterruptedException {
		Helper.driver.get(Constants.url_user);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(4000);
		Helper.driver.findElement(Test_Elements.usercreateButton).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(1500);
		Helper.driver.findElement(Test_Elements.userFirstNameInput).sendKeys("Test");    
		Helper.driver.findElement(Test_Elements.userLastNameInput).sendKeys("User");  
		Helper.driver.findElement(By.cssSelector("#emailId")).sendKeys("test@email.com");
		Thread.sleep(1000);
		Helper.driver.findElement(Test_Elements.popupNextButton).click();
		Thread.sleep(1000);
		Helper.driver.findElement(Test_Elements.userOrgTypeDropDownExpand).click();
		Thread.sleep(500);
		Helper.driver.findElement(Test_Elements.userOrgTypeInput).sendKeys("Ancera");
		Helper.driver.findElement(Test_Elements.userOrgTypeInput).sendKeys(Keys.ENTER);

		Helper.driver.findElement(Test_Elements.userOrgDropDownExpand).click();
		Thread.sleep(500);			
		Helper.driver.findElement(Test_Elements.userOrgInput).sendKeys(Test_Variables.lstOrganizationCreate.get(0));
		Thread.sleep(500);
	}






	public static void fieldLevelReset() throws InterruptedException, IOException {
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		Helper.driver.findElement(By.id("edit-field-access")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		Helper.driver.findElement(By.id("btn-reset")).click();
		Thread.sleep(1000);
		Helper.driver.findElement(By.id("btn-save")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
	}



	public static void login() throws InterruptedException, IOException {
		Helper.driver.findElement(By.id("logout")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		Helper.driver.findElement(By.id("email")).clear();
		Helper.driver.findElement(By.id("email")).sendKeys(Test_Variables.login_email);
		Helper.driver.findElement(By.id("pwd")).clear();
		Helper.driver.findElement(By.id("pwd")).sendKeys(Test_Variables.login_password);
		Helper.driver.findElement(By.id("btn-sign-in")).click();
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
	}


	public static void getUserAccess() throws InterruptedException, IOException {
		Helper.driver.get(Constants.url_user);
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(1000);
		for(int i=1; i<=500; i++) {
			if (Helper.driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(4) label")).getText().equals(Test_Variables.login_email)) {
				WebElement scroll = Helper.driver.findElement(By.id("edit-user-"+i));
				((JavascriptExecutor)Helper.driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
				Thread.sleep(1000);
				Helper.driver.findElement(By.id("edit-user-"+i)).click();
				break;
			}
		}
		Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(4000);
		Helper.driver.findElement(By.id("btn-next")).click();
		Thread.sleep(1000);
		Helper.driver.findElement(By.id("btn-next")).click();
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

/*
	public void Navigate(String url, String id, String expectedid) throws InterruptedException, IOException {

		try{

			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Helper.driver.get(url);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);
			String actual = Helper.driver.findElement(By.id(id)).getText();
			String expected = expectedid;

			Assert.assertEquals(actual, expected); 
		}catch(AssertionError er) {
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("Flock Registration report failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("User navigation failed");
			Test_Variables.results.createNode("Flock Registration report failed to open");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.FlockRegistrationReportPath, ex);
		}
	}
	*/
}
