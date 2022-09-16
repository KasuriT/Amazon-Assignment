package Test.Ancera.Reports;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import Models.SitesLogModel;
import Models.ReportFilters;
import Test.Ancera.ClickElement;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Functions;
import Test.Ancera.Test_Variables;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static Test.Ancera.Test_Variables.*;
import static Test.Ancera.Helper.*;
import static Test.Ancera.Test_Elements.*;

public class Sites_Log {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		spark = new ExtentSparkReporter("target/Reports/Sites_Log"+date+".html");
		spark.config().setReportName("Sites Log Test Report"); 
		config();
		ConfigureLogin.login();
	}
	
	
	@Test (priority = 1) 
	public void NavigateSites() throws InterruptedException, IOException {
		Test_Functions.NavigateToScreen(Constants.url_SitesLog, "Sites Log", Constants.SitesLogReportPath, Test_Elements.sitesLogTitle);
	}


	@Test (priority = 2, enabled = true) 
	public void LockFilter() throws InterruptedException, IOException {
		Test_Functions.Lock(Test_Elements.sitesLogTable, "Sites Log", Constants.SitesLogReportPath, 2);
	}


	@Test (priority = 3) 
	public void Wildcard() throws InterruptedException, IOException {
		driver.get(Constants.url_SitesLog);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
		Thread.sleep(3000);
		Test_Functions.Wildcard(Test_Elements.sitesLogTable, "Sites Log", Constants.SitesLogReportPath, 2);
	}
	
	
	@Test(priority= 4)
	public void FilterSorting() throws InterruptedException, IOException {
		Test_Functions.Sorting(Test_Elements.sitesLogTable, "Sites Log", Constants.SitesLogReportPath);
	}
	
	@Test(priority= 5, enabled = true)
	public void RowsPerPage1() throws InterruptedException, IOException {
		Test_Functions.RowsPerPage();
	}	
	

	@Test (description="Test Case: Test Pagination",enabled= true, priority = 6) 
	public void Pagination() throws InterruptedException, IOException {
		lstSitesLogPagination = SitesLogModel.pagination();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("results-found-count"))); 
		Thread.sleep(500);

		for (SitesLogModel objModel : lstSitesLogPagination) { 	
			try {
				test = extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				preconditions = test.createNode(Scenario.class, Test_Variables.PreConditions);
				steps = test.createNode(Scenario.class, Test_Variables.Steps);
				results = test.createNode(Scenario.class, Test_Variables.Results);

				preconditions.createNode("1. Go to url " +Constants.url_login);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				preconditions.createNode("3. Hover to sidebar to expand the menu");
				preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				preconditions.createNode("5. Click on Sites Log; Sites Log reports open");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						String recordNumber = Helper.driver.findElement(By.id("results-found-count")).getText();
						test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));

						String removeComma = recordNumber.replace(",", "");
						double x = Double.parseDouble(removeComma);
						double y = 100;
						double divide = Math.ceil(Math.abs(x/y));
						final int totalPages = (int)divide;
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						String results = Helper.driver.findElement(By.id("results-found-count")).getText();

						if (NumberFormat.getNumberInstance(Locale.US).parse(results).intValue() > 100) {
							Helper.driver.findElement(By.id(objFilter.paginationPage)).click();

							if (objModel.paginationExist) {
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
								steps.createNode("1. Verify pagination exists");
								Assert.assertTrue(Helper.driver.findElements(By.id("activePageNumber")).size() != 0);
								test.pass("Pagination displayed successfully");
								Test_Variables.results.createNode("Pagination displayed successfully");
								test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
								saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);		
							}


							if (objModel.paginationLastPage) {
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
								steps.createNode("1. Click on '>>' icon in pagination");

								if (driver.findElements(By.id("message")).size() !=0) {
									Thread.sleep(500);
									test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));	
									Assert.fail("An error alert message displayed");
								}	
								String pageCount =	Helper.driver.findElement(By.id("activePageNumber")).getText();
								Assert.assertEquals(pageCount, totalPages+"/"+totalPages);
								test.pass("Navigated to last page successfully");
								Test_Variables.results.createNode("Navigated to last page successfully");
								test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
								saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);
							}

							if (objModel.paginationPreviousPage) {
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
								steps.createNode("1. Click on '<' icon in pagination");
								if (driver.findElements(By.id("message")).size() !=0) {
									Thread.sleep(500);
									test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
									Assert.fail("An error alert message displayed");
								}
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								String pageCount =	Helper.driver.findElement(By.id("activePageNumber")).getText();
								Assert.assertEquals(pageCount, (totalPages-1)+"/"+totalPages);
								test.pass("Navigated to previous page successfully");
								Test_Variables.results.createNode("Navigated to previous page successfully");
								test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
								saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);
							}


							if (objModel.paginationFirstPage) {	
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
								steps.createNode("1. Click on '<<' icon in pagination");
								if (Helper.driver.findElements(By.id("message")).size() !=0) {
									Thread.sleep(500);
									test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
									Assert.fail("An error alert message displayed");
								}
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								String pageCount =	driver.findElement(By.id("activePageNumber")).getText();
								Assert.assertEquals(pageCount, 1+"/"+totalPages);
								test.pass("Navigated to first page successfully");
								Test_Variables.results.createNode("Navigated to first page successfully");
								test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
								saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);
							}


							if (objModel.paginationNextPage) {	
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));	
								steps.createNode("1. Click on '>' icon in pagination");
								if (Helper.driver.findElements(By.id("message")).size() !=0) {
									Thread.sleep(500);
									test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
									Assert.fail("An error alert message displayed");
								}
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
								String pageCount =	Helper.driver.findElement(By.id("activePageNumber")).getText();
								Assert.assertEquals(pageCount, 2+"/"+totalPages);
								test.pass("Navigated to next page successfully");
								Test_Variables.results.createNode("Navigated to next page successfully");
								test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
								saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);
							}
						}

						else {
							Assert.assertTrue(true, "Records are less then 100; pagination cannot be tested");
							test.pass("Records are less then 100; pagination cannot be tested");
							Test_Variables.results.createNode("Records are less then 100; pagination cannot be tested");
							test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
							saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);	
						}
					}
					catch(AssertionError er) {
						test.fail("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						Test_Variables.results.createNode("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, new Exception(er));
					}
					catch(Exception ex) {
						test.fail("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						Test_Variables.results.createNode("Failed to get desired results on clicking "+objFilter.FilterName+" button");
						saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, ex);
					}
				}
			}
			catch(Exception ex) {
			}
		}
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Helper.driver.findElement(By.id("first-page")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
	}



	@Test (enabled= true, priority =7) 
	public void AllignmentTest() throws InterruptedException, IOException {
		try{
			test = Test_Variables.extent.createTest("AN-Sites-72: Verify that int data type columns are right alligned", "This testcase will verify that int data type columns are right alligned");

			preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			preconditions.createNode("1. Go to url " +Constants.url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on Sites Log; Sites Log reports open");
			steps.createNode("1. Verify int data type columns are right alligned");

			SoftAssert softAssert = new SoftAssert();
			softAssert.assertNotEquals(Helper.driver.findElements(By.cssSelector("#col-"+sitesLatitudeCol+" .text-right")).size(), 0, "Latitude column is not right alligned");
			softAssert.assertNotEquals(Helper.driver.findElements(By.cssSelector("#col-"+sitesLongitudeCol+" .text-right")).size(), 0, "Longitude column is not right alligned");

			test.pass("Int data type columns are right alligned");
			results.createNode("Int data type columns are right alligned");
			test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
			saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);
			softAssert.assertAll();	
		}catch(AssertionError er) {
			test.fail("Int data type columns are not right alligned");
			Test_Variables.results.createNode("Int data type columns are not right alligned");
			saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, new Exception(er));
		}catch(Exception ex){
			test.fail("Int data type columns are not right alligned");
			Test_Variables.results.createNode("Int data type columns are not right alligned");
			saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 8) 
	public void FieldAccess() throws InterruptedException, IOException {
		
		lstSitesLogFieldAccess = SitesLogModel.FieldAccess();

		for (SitesLogModel objModel : Test_Variables.lstSitesLogFieldAccess) { 	
			try {
				test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);

				preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				preconditions.createNode("1. Go to url " +Constants.url_login);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				preconditions.createNode("3. Hover to sidebar to expand the menu");
				preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				preconditions.createNode("5. Click on Sites Log; Sites Log reports open");
				steps.createNode("1. Click on filed access icon; popup appears");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);     
						driver.findElement(By.id("edit-field-access")).click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(2000);
						steps.createNode("2. Unselect "+objFilter.FilterName+" column and Select previously unselected filter column and click on save button");

						if(objModel.viewAccess) {
							if (driver.findElement(By.cssSelector("tr:nth-child("+objFilter.FilterID+") td:nth-child(4) input")).isSelected() == false) {
								driver.findElement(By.cssSelector("tr:nth-child("+objFilter.FilterID+") td:nth-child(4) label .rpt-fields")).click();
							}
							Thread.sleep(1000);
						}	

						if(objModel.unviewAccess) {
							int inc = Integer.parseInt(objFilter.FilterID) + 1;
							if (driver.findElement(By.cssSelector("tr:nth-child("+inc+") td:nth-child(4) input")).isSelected() == true) {
								driver.findElement(By.cssSelector("tr:nth-child("+inc+") td:nth-child(4) label .rpt-fields")).click();
							}
							Thread.sleep(1000);
						}

						driver.findElement(By.id("btn-save")).click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
						Thread.sleep(1000);
						Assert.assertEquals(Helper.driver.findElement(By.id("message")).getText(), "Report Settings Saved");

						if(objModel.viewAccess) {
							steps.createNode("3. Verify selected column is displayed in the table");
							Assert.assertEquals(Helper.driver.findElements(By.id(objModel.FilterUnHideID)).size(), 1);
						}	
						if(objModel.unviewAccess) {
							steps.createNode("4. Verify unselected column is hidden in the table");
							Assert.assertEquals(Helper.driver.findElements(By.id(objModel.FilterHideID)).size(), 0);
						}
						steps.createNode("5. Open audit log and verify that unselected column is hidden while selected column is displayed");
						Helper.driver.findElement(By.id("audit-trial-0")).click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);

						if(objModel.viewAccess) {
							Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#audit-"+objModel.FilterUnHideID+".table-header-report")).size(), 1);	
						}

						if(objModel.unviewAccess) {
							Assert.assertEquals(Helper.driver.findElements(By.cssSelector("#audit-"+objModel.FilterHideID+".table-header-report")).size(), 0);
						}

						driver.findElement(By.cssSelector(Test_Elements.closeAudit)).click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);

						test.pass("Column was hidden successfully");
						Test_Variables.results.createNode("Column was hidden successfully");
						test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
						saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);
					}
					catch(AssertionError er) {
						test.fail(objFilter.FilterName+" column failed to hide");
						Test_Variables.results.createNode(objFilter.FilterName+" column failed to shide");
						saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, new Exception(er));
					}catch(Exception ex){
						test.fail(objFilter.FilterName+" column failed to hide");
						Test_Variables.results.createNode(objFilter.FilterName+" column failed to shide");
						saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, ex);
					}
				}
			}
			catch(Exception ex) {
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

	@SuppressWarnings({ "unused", "resource" })
	@Test (description="Test Case: Test Sites CSV Download",enabled= true, priority =9) 
	public void CSVExport() throws InterruptedException, IOException {
		try {
			test = Test_Variables.extent.createTest("AN-Sites-83: Verify user can download Sites CSV file and verify the records", "This test case will verify that user can download Sites CSV file");
			preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			preconditions.createNode("1. Go to url " +Constants.url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on Sites Log; Sites Log reports open");

			steps.createNode("1. Hover mouse towards table");
			steps.createNode("2. Export file button becomes visible");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			driver.get(Constants.url_SitesLog);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(Test_Elements.loader));
			Thread.sleep(3000);
			
		//	Helper.driver.findElement(By.id(Test_Elements.sitesSiteID+""+Test_Elements.sitesShowFilter)).click();	
			driver.findElement(By.id("siteId_show-filter")).click();
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);						
			ClickElement.clickByCss(Helper.driver, "#"+Test_Elements.clSortFilter+""+Test_Elements.sitesSiteID+" li:nth-child(3) label");

			ClickElement.clickById(Helper.driver, Test_Elements.sitesSiteID+""+Test_Elements.sitesApplyFilter);
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(800);

			String getRowText = Helper.driver.findElement(By.id("results-found-count")).getText();

			steps.createNode("3. Click on the button");
			steps.createNode("4. Dropdown cloud pop ups");
			steps.createNode("5. Click on Export as CSV");	
			steps.createNode("6. Verify Site ID is same in table and CSV");
			steps.createNode("8. Verify Site Type is same in table and CSV");
			steps.createNode("9. Verify Address is same in table and CSV");
			steps.createNode("10. Verify Date Created is same in table and CSV");
			steps.createNode("11. Verify Created By is same in table and CSV");

			Thread.sleep(1000);
			Helper.driver.findElement(By.cssSelector("#csv-action img")).click();
			Thread.sleep(1000);
			test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
			ClickElement.clickById(Helper.driver, "export-csv");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			SalmonellaLog fr= new SalmonellaLog();
			File newfile = fr.getTheNewestFile(Test_Variables.fileDownloadPath, "csv");
			String filename= newfile.getName();
			Assert.assertEquals(filename, Test_Variables.sitesCSVFileName+date+".csv");
			Test_Variables.test.pass("CSV file downloaded successfully");
			Test_Variables.results.createNode("CSV file downloads successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);

			File file = new File(Test_Variables.fileDownloadPath+"\\"+filename);
			if(file.exists()){
				System.out.println("File Exists");
			}	

			SoftAssert softAssert = new SoftAssert();
			FileReader filereader = new FileReader(file);
			CSVReader reader = new CSVReader(filereader);
			reader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
			StringBuffer buffer = new StringBuffer();
			String data[];		    
			int j = 0;
			int k = 1;
			while((data = reader.readNext()) != null) {
				for (int i = 0; i<data.length; i++) {

					int a = Helper.driver.findElements(By.cssSelector("tr")).size();
					if (k < a) {
						System.out.print(data[i] + " ");

						int l = j+3;
						if (Helper.driver.findElements(By.cssSelector("tr:nth-child("+k+") td:nth-child("+l+")")).size() != 0 && l<=14) {
							//	System.out.print(Helper.driver.findElement(By.cssSelector("tr:nth-child("+k+") td:nth-child("+l+")")).getText()+" ");
							softAssert.assertEquals(data[i], Helper.driver.findElement(By.cssSelector("tr:nth-child("+k+") td:nth-child("+l+")")).getText());
						}
						else {
							k = k+1;
							l =0;
							j = -1;
						}
						j++;
					}
				}
				System.out.println(" ");

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
			Assert.assertEquals(s, str);

			if(file.delete()) {
				System.out.println("CSV file deleted");  
			}
			softAssert.assertAll();
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, ex);
		}
		Thread.sleep(1000);
	}
	


	@Test (description="Test Case: Test Sites Audit Download",enabled= true, priority = 11) 
	public void CSVAuditExport() throws InterruptedException, IOException {
		try {
			Test_Variables.test = Test_Variables.extent.createTest("AN-Sites-84: Verify user can download Sites Audit file", "This test case will verify that user can download Sites Audit file");
			Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
			Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
			Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

			Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
			Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
			Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			Test_Variables.preconditions.createNode("5. Click on Sites Log; Coccidia Log reports open");

			Test_Variables.steps.createNode("1. Hover mouse towards table");
			Test_Variables.steps.createNode("2. Export file button becomes visible");
			ClickElement.clickByCss(Helper.driver, "#select-site-0 .custom-checkbox");
			Thread.sleep(1000);

			//String getRowCount = Helper.driver.findElement(By.id("results-found-count")).getText();

			Test_Variables.steps.createNode("3. Click on the button");
			Test_Variables.steps.createNode("4. Dropdown cloud pop ups");
			Helper.driver.findElement(By.cssSelector("#csv-action img")).click();
			Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Sites Log", Constants.SitesLogReportPath));
			Thread.sleep(1500);
			Test_Variables.steps.createNode("5. Click on Export with Audit as CSV");
			ClickElement.clickById(Helper.driver, "export-audit-csv");
			Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(3000);
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			SalmonellaLog fr= new SalmonellaLog();
			File newfile = fr.getTheNewestFile(Test_Variables.fileDownloadPath, "csv");
			String filename= newfile.getName();
			//System.out.println("Latest CSV file is = "+filename);
			Assert.assertEquals(filename, Test_Variables.sitesCSVAuditFileName+date+".csv");
			Test_Variables.test.pass("CSV file downloaded successfully");
			Test_Variables.results.createNode("CSV file downloads successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.SitesLogReportPath, null);

			File file = new File(Test_Variables.fileDownloadPath+"\\"+filename); 
			if(file.delete())
				System.out.println("CSV Audit file deleted");
		}
		catch(AssertionError er) {
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			Test_Variables.test.fail("CSV file failed to download");
			Test_Variables.results.createNode("CSV file failed to download");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.SitesLogReportPath, ex);
		}
	}


	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		//	Helper.driver.close();
	}

}

