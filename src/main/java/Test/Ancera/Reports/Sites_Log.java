package Test.Ancera.Reports;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

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

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static Test.Ancera.Helper.*;
import static Test.Ancera.Test_Elements.*;
import static Test.Ancera.Test_Variables.*;
import static Test.Ancera.Test_Functions.*;
import static Test.Ancera.Constants.*;

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
		NavigateToScreen(url_SitesLog, "Sites Log", SitesLogReportPath, sitesLogTitle);
	}


	@Test (priority = 2, enabled = true) 
	public void LockFilter() throws InterruptedException, IOException {
		Lock(sitesLogTable, "Sites Log", SitesLogReportPath, 2);
	}


	@Test (priority = 3) 
	public void Wildcard() throws InterruptedException, IOException {
		driver.get(url_SitesLog);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		Thread.sleep(3000);
		Wildcard1(sitesLogTable, "Sites Log", SitesLogReportPath, 2);
	}
	
	
	@Test(priority= 4)
	public void FilterSorting() throws InterruptedException, IOException {
		Sorting1(sitesLogTable, "Sites Log", SitesLogReportPath, 2);
	}
	
	@Test(priority= 5, enabled = true)
	public void RowsPerPage1() throws InterruptedException, IOException {
		RowsPerPage1();
	}	
	
	@Test(priority= 6, enabled = true)
	public void PaginationSites() throws InterruptedException, IOException {
		driver.get(url_SitesLog);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		Thread.sleep(3000);
		Pagination(sitesLogTable, "Sites Log", SitesLogReportPath);
	}
	

	@Test (enabled= true, priority =7) 
	public void AllignmentTest() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-Sites-72: Verify that int data type columns are right alligned", "This testcase will verify that int data type columns are right alligned");

			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on Sites Log; Sites Log reports open");
			steps.createNode("1. Verify int data type columns are right alligned");

			SoftAssert softAssert = new SoftAssert();
			softAssert.assertNotEquals(driver.findElements(By.cssSelector("#col-"+sitesLatitudeCol+" .text-right")).size(), 0, "Latitude column is not right alligned");
			softAssert.assertNotEquals(driver.findElements(By.cssSelector("#col-"+sitesLongitudeCol+" .text-right")).size(), 0, "Longitude column is not right alligned");

			test.pass("Int data type columns are right alligned");
			results.createNode("Int data type columns are right alligned");
			test.addScreenCaptureFromPath(getScreenshot("Sites Log", SitesLogReportPath));
			saveResultNew(ITestResult.SUCCESS, SitesLogReportPath, null);
			softAssert.assertAll();	
		}catch(AssertionError er) {
			test.fail("Int data type columns are not right alligned");
			results.createNode("Int data type columns are not right alligned");
			saveResultNew(ITestResult.FAILURE, SitesLogReportPath, new Exception(er));
		}catch(Exception ex){
			test.fail("Int data type columns are not right alligned");
			results.createNode("Int data type columns are not right alligned");
			saveResultNew(ITestResult.FAILURE, SitesLogReportPath, ex);
		}
	}


	@Test (enabled= true, priority = 8) 
	public void FieldAccess() throws InterruptedException, IOException {
		
		lstSitesLogFieldAccess = SitesLogModel.FieldAccess();

		for (SitesLogModel objModel : lstSitesLogFieldAccess) { 	
			try {
				test = extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);

				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				preconditions.createNode("1. Go to url " +url_login);
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
						Assert.assertEquals(driver.findElement(By.id("message")).getText(), "Report Settings Saved");

						if(objModel.viewAccess) {
							steps.createNode("3. Verify selected column is displayed in the table");
							Assert.assertEquals(driver.findElements(By.id(objModel.FilterUnHideID)).size(), 1);
						}	
						if(objModel.unviewAccess) {
							steps.createNode("4. Verify unselected column is hidden in the table");
							Assert.assertEquals(driver.findElements(By.id(objModel.FilterHideID)).size(), 0);
						}
						steps.createNode("5. Open audit log and verify that unselected column is hidden while selected column is displayed");
						driver.findElement(By.id("audit-trial-0")).click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);

						if(objModel.viewAccess) {
							Assert.assertEquals(driver.findElements(By.cssSelector("#audit-"+objModel.FilterUnHideID+".table-header-report")).size(), 1);	
						}

						if(objModel.unviewAccess) {
							Assert.assertEquals(driver.findElements(By.cssSelector("#audit-"+objModel.FilterHideID+".table-header-report")).size(), 0);
						}

						driver.findElement(By.cssSelector(closeAudit)).click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
						Thread.sleep(1000);

						test.pass("Column was hidden successfully");
						results.createNode("Column was hidden successfully");
						test.addScreenCaptureFromPath(getScreenshot("Sites Log", SitesLogReportPath));
						saveResultNew(ITestResult.SUCCESS, SitesLogReportPath, null);
					}
					catch(AssertionError er) {
						test.fail(objFilter.FilterName+" column failed to hide");
						results.createNode(objFilter.FilterName+" column failed to shide");
						saveResultNew(ITestResult.FAILURE, SitesLogReportPath, new Exception(er));
					}catch(Exception ex){
						test.fail(objFilter.FilterName+" column failed to hide");
						results.createNode(objFilter.FilterName+" column failed to shide");
						saveResultNew(ITestResult.FAILURE, SitesLogReportPath, ex);
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

	
	
	@SuppressWarnings({ "resource", "unused" })
	@Test (description="Test Case: Test Sites CSV Download",enabled= true, priority = 15) 
	public void CSVExportNew() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-Sites-216: Verify user can download Sites CSV file", "This test case will verify that user can download Sites CSV file");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on Sites Log; Sites Log reports open");
			
			driver.get(url_SitesLog);
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);	
			
			steps.createNode("1. Hover mouse towards table");
			steps.createNode("2. Export file button becomes visible");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			
			
			driver.findElement(By.id("siteId_show-filter")).click();
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);						
			ClickElement.clickByCss(driver, "#"+clSortFilter+""+sitesSiteID+" li:nth-child(3) label");

			ClickElement.clickById(driver, sitesSiteID+""+sitesApplyFilter);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(800);
			
			String getRowText = driver.findElement(By.id("results-found-count")).getText();

			steps.createNode("3. Click on the button");
			steps.createNode("4. Dropdown cloud pop ups");
			driver.findElement(By.cssSelector("#csv-action img")).click();
			test.addScreenCaptureFromPath(getScreenshot("Sites Log", SitesLogReportPath));
			Thread.sleep(1000);
			steps.createNode("5. Click on Export as CSV");
			driver.findElement(By.xpath("//*[text() = ' Export as CSV ']")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			SalmonellaLog fr= new SalmonellaLog();
			File newfile = fr.getTheNewestFile(fileDownloadPath, "csv");
			String filename= newfile.getName();
			Assert.assertEquals(filename, sitesCSVFileName+date+".csv");
			test.pass("CSV file downloaded successfully");
			results.createNode("CSV file downloads successfully");
			saveResultNew(ITestResult.SUCCESS, SitesLogReportPath, null);

			File file = new File(fileDownloadPath+"\\"+filename);
			if(file.exists()){
				System.out.println("File Exists");
			}	

			SoftAssert softAssert = new SoftAssert();
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
						int totalColumns = driver.findElements(By.cssSelector("tr:nth-child(1) td")).size() - 1;
						int columnsCount = columnsCountTotal+3;
						if (driver.findElements(By.cssSelector("tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).size() != 0 && columnsCount<=totalColumns) {
							softAssert.assertEquals(data[i].trim(), driver.findElement(By.cssSelector("tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).getText().trim());
						}
						else {
							rowsCount = rowsCount+1;
							columnsCount =0;
							columnsCountTotal = -1;
						}
						columnsCountTotal++;
					}
				}
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
			Assert.assertEquals(s, str);

			file = new File(fileDownloadPath+"\\"+filename); 
			if(file.delete())
				System.out.println("CSV file deleted");
		}
		catch(AssertionError er) {
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			saveResultNew(ITestResult.FAILURE, SitesLogReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			saveResultNew(ITestResult.FAILURE, SitesLogReportPath, ex);
		}
	}
	
	
	
	
	@SuppressWarnings({ "unused", "resource" })
	@Test (description="Test Case: Test Sites CSV Download",enabled= false, priority =9) 
	public void CSVExport() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-Sites-83: Verify user can download Sites CSV file and verify the records", "This test case will verify that user can download Sites CSV file");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on Sites Log; Sites Log reports open");

			steps.createNode("1. Hover mouse towards table");
			steps.createNode("2. Export file button becomes visible");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));

			driver.get(url_SitesLog);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
			Thread.sleep(3000);
			
		//	driver.findElement(By.id(sitesSiteID+""+sitesShowFilter)).click();	
			driver.findElement(By.id("siteId_show-filter")).click();
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(1000);						
			ClickElement.clickByCss(driver, "#"+clSortFilter+""+sitesSiteID+" li:nth-child(3) label");

			ClickElement.clickById(driver, sitesSiteID+""+sitesApplyFilter);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(800);

			String getRowText = driver.findElement(By.id("results-found-count")).getText();

			steps.createNode("3. Click on the button");
			steps.createNode("4. Dropdown cloud pop ups");
			steps.createNode("5. Click on Export as CSV");	
			steps.createNode("6. Verify Site ID is same in table and CSV");
			steps.createNode("8. Verify Site Type is same in table and CSV");
			steps.createNode("9. Verify Address is same in table and CSV");
			steps.createNode("10. Verify Date Created is same in table and CSV");
			steps.createNode("11. Verify Created By is same in table and CSV");

			Thread.sleep(1000);
			driver.findElement(By.cssSelector("#csv-action img")).click();
			Thread.sleep(1000);
			test.addScreenCaptureFromPath(getScreenshot("Sites Log", SitesLogReportPath));
			ClickElement.clickById(driver, "export-csv");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			SalmonellaLog fr= new SalmonellaLog();
			File newfile = fr.getTheNewestFile(fileDownloadPath, "csv");
			String filename= newfile.getName();
			Assert.assertEquals(filename, sitesCSVFileName+date+".csv");
			test.pass("CSV file downloaded successfully");
			results.createNode("CSV file downloads successfully");
			saveResultNew(ITestResult.SUCCESS, SitesLogReportPath, null);

			File file = new File(fileDownloadPath+"\\"+filename);
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

					int a = driver.findElements(By.cssSelector("tr")).size();
					if (k < a) {
						System.out.print(data[i] + " ");

						int l = j+3;
						if (driver.findElements(By.cssSelector("tr:nth-child("+k+") td:nth-child("+l+")")).size() != 0 && l<=14) {
							//	System.out.print(driver.findElement(By.cssSelector("tr:nth-child("+k+") td:nth-child("+l+")")).getText()+" ");
							softAssert.assertEquals(data[i], driver.findElement(By.cssSelector("tr:nth-child("+k+") td:nth-child("+l+")")).getText());
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
			Assert.assertEquals(s, str);

			if(file.delete()) {
				System.out.println("CSV file deleted");  
			}
			softAssert.assertAll();
		}
		catch(AssertionError er) {
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			saveResultNew(ITestResult.FAILURE, SitesLogReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			saveResultNew(ITestResult.FAILURE, SitesLogReportPath, ex);
		}
		Thread.sleep(1000);
	}
	


	@Test (description="Test Case: Test Sites Audit Download",enabled= true, priority = 11) 
	public void CSVAuditExport() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-Sites-84: Verify user can download Sites Audit file", "This test case will verify that user can download Sites Audit file");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
			preconditions.createNode("5. Click on Sites Log; Coccidia Log reports open");

			steps.createNode("1. Hover mouse towards table");
			steps.createNode("2. Export file button becomes visible");
			ClickElement.clickByCss(driver, "#select-site-0 .custom-checkbox");
			Thread.sleep(1000);

			//String getRowCount = driver.findElement(By.id("results-found-count")).getText();

			steps.createNode("3. Click on the button");
			steps.createNode("4. Dropdown cloud pop ups");
			driver.findElement(By.cssSelector("#csv-action img")).click();
			test.addScreenCaptureFromPath(getScreenshot("Sites Log", SitesLogReportPath));
			Thread.sleep(1500);
			steps.createNode("5. Click on Export with Audit as CSV");
			ClickElement.clickById(driver, "export-audit-csv");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
			Thread.sleep(3000);
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			SalmonellaLog fr= new SalmonellaLog();
			File newfile = fr.getTheNewestFile(fileDownloadPath, "csv");
			String filename= newfile.getName();
			//System.out.println("Latest CSV file is = "+filename);
			Assert.assertEquals(filename, sitesCSVAuditFileName+date+".csv");
			test.pass("CSV file downloaded successfully");
			results.createNode("CSV file downloads successfully");
			saveResultNew(ITestResult.SUCCESS, SitesLogReportPath, null);

			File file = new File(fileDownloadPath+"\\"+filename); 
			if(file.delete())
				System.out.println("CSV Audit file deleted");
		}
		catch(AssertionError er) {
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			saveResultNew(ITestResult.FAILURE, SitesLogReportPath, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			saveResultNew(ITestResult.FAILURE, SitesLogReportPath, ex);
		}
	}


	@AfterTest
	public static void endreport() {
		extent.flush();
		//	driver.close();
	}

}

