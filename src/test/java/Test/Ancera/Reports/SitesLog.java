package Test.Ancera.Reports;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import Config.BaseTest;
import MiscFunctions.ClickElement;
import MiscFunctions.DateUtil;
import MiscFunctions.DownloadFileCheck;
import Models.SitesLogModel;
import PageObjects.SalmonellaLogPage;
import PageObjects.SitesLogPage;
import Models.ReportFilters;
import Test.Ancera.Login.LoginTest;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static LogViewFunctions.RowsPerPage.RowsPerPage;
import static PageObjects.SitesLogPage.*;
import static PageObjects.BasePage.*;
import static LogViewFunctions.FilterLock.*;
import static LogViewFunctions.FilterWildcard.*;
import static LogViewFunctions.FilterSort.*;
import static LogViewFunctions.Pagination.*;
import static MiscFunctions.Constants.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;

public class SitesLog extends BaseTest {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		spark = new ExtentSparkReporter("target/Reports/Sites_Log"+DateUtil.date+".html");
		spark.config().setReportName("Sites Log Test Report"); 
	}
	
	@BeforeClass
	public void Login() throws InterruptedException, IOException {
		LoginTest.login();
	}
	
	@Test (priority = 1) 
	public void NavigateSites() throws InterruptedException, IOException {
		test = extent.createTest("SL-AN-01: Verify user can navigate to Sites Log Screen");
		SitesLogPage.openSitesLogPage();
	}


	@Test (priority = 2, enabled = true) 
	public void LockFilter() throws InterruptedException, IOException {
		Lock(sitesLogTable, "Sites Log",  2);
	}


	@Test (priority = 3) 
	public void WildcardFilter() throws InterruptedException, IOException {
		SitesLogPage.openSitesLogPage();
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		Wildcard(sitesLogTable, "Sites Log", 2);
	}
	
	
	@Test(priority= 4)
	public void FilterSorting() throws InterruptedException, IOException {
		Sorting(sitesLogTable, "Sites Log",  2);
	}
	
	@Test(priority= 5, enabled = true)
	public void RowsPerPage() throws InterruptedException, IOException {
		RowsPerPage();
	}	
	
	@Test(priority= 6, enabled = true)
	public void PaginationSites() throws InterruptedException, IOException {
		getDriver().navigate().refresh();
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		Pagination(sitesLogTable, "Sites Log", ReportFilePath);
	}
	

	@Test (enabled= true, priority =7) 
	public void AllignmentTest() throws InterruptedException, IOException {
		try{
			test = extent.createTest("AN-Sites-72: Verify that int data type columns are right alligned");

			SoftAssert softAssert = new SoftAssert();
			softAssert.assertNotEquals(getDriver().findElements(By.cssSelector("#col-"+sitesLatitudeCol+" .text-right")).size(), 0, "Latitude column is not right alligned");
			softAssert.assertNotEquals(getDriver().findElements(By.cssSelector("#col-"+sitesLongitudeCol+" .text-right")).size(), 0, "Longitude column is not right alligned");

			test.pass("Int data type columns are right alligned");
			getScreenshot();
			saveResult(ITestResult.SUCCESS, null);
			softAssert.assertAll();	
		}catch(AssertionError er) {
			test.fail("Int data type columns are not right alligned");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}catch(Exception ex){
			test.fail("Int data type columns are not right alligned");
			saveResult(ITestResult.FAILURE, ex);
		}
	}


	@Test (enabled= true, priority = 8) 
	public void FieldAccess() throws InterruptedException, IOException {
		
		SitesLogModel.lstSitesLogFieldAccess = SitesLogModel.FieldAccess();

		for (SitesLogModel objModel : SitesLogModel.lstSitesLogFieldAccess) { 	
			try {
				test = extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);
				steps.createNode("1. Click on filed access icon; popup appears");

				for (ReportFilters objFilter : objModel.lstFilters) {	
					try {
						waitElementInvisible(loading_cursor);
						Thread.sleep(1000);     
						getDriver().findElement(By.id("edit-field-access")).click();
						waitElementInvisible(loading_cursor);
						Thread.sleep(2000);
						steps.createNode("2. Unselect "+objFilter.FilterName+" column and Select previously unselected filter column and click on save button");

						if(objModel.viewAccess) {
							if (getDriver().findElement(By.cssSelector("tr:nth-child("+objFilter.FilterID+") td:nth-child(4) input")).isSelected() == false) {
								getDriver().findElement(By.cssSelector("tr:nth-child("+objFilter.FilterID+") td:nth-child(4) label .rpt-fields")).click();
							}
							Thread.sleep(1000);
						}	

						if(objModel.unviewAccess) {
							int inc = Integer.parseInt(objFilter.FilterID) + 1;
							if (getDriver().findElement(By.cssSelector("tr:nth-child("+inc+") td:nth-child(4) input")).isSelected() == true) {
								getDriver().findElement(By.cssSelector("tr:nth-child("+inc+") td:nth-child(4) label .rpt-fields")).click();
							}
							Thread.sleep(1000);
						}

						getDriver().findElement(By.id("btn-save")).click();
						waitElementInvisible(loading_cursor);
						waitElementVisible(alertMessage);
						Thread.sleep(1000);
						Assert.assertEquals(getDriver().findElement(By.id("message")).getText(), "Report Settings Saved");

						if(objModel.viewAccess) {
							steps.createNode("3. Verify selected column is displayed in the table");
							Assert.assertEquals(getDriver().findElements(By.id(objModel.FilterUnHideID)).size(), 1);
						}	
						if(objModel.unviewAccess) {
							steps.createNode("4. Verify unselected column is hidden in the table");
							Assert.assertEquals(getDriver().findElements(By.id(objModel.FilterHideID)).size(), 0);
						}
						steps.createNode("5. Open audit log and verify that unselected column is hidden while selected column is displayed");
						getDriver().findElement(By.id("audit-trial-0")).click();
						waitElementInvisible(loading_cursor);
						Thread.sleep(1000);

						if(objModel.viewAccess) {
							Assert.assertEquals(getDriver().findElements(By.cssSelector("#audit-"+objModel.FilterUnHideID+".table-header-report")).size(), 1);	
						}

						if(objModel.unviewAccess) {
							Assert.assertEquals(getDriver().findElements(By.cssSelector("#audit-"+objModel.FilterHideID+".table-header-report")).size(), 0);
						}

						getDriver().findElement(By.cssSelector(SalmonellaLogPage.closeAudit)).click();
						waitElementInvisible(loading_cursor);
						Thread.sleep(1000);

						test.pass("Column was hidden successfully");
						getScreenshot();
						saveResult(ITestResult.SUCCESS, null);
					}
					catch(AssertionError er) {
						test.fail(objFilter.FilterName+" column failed to hide");
						saveResult(ITestResult.FAILURE, new Exception(er));
					}catch(Exception ex){
						test.fail(objFilter.FilterName+" column failed to hide");
						saveResult(ITestResult.FAILURE, ex);
					}
				}
			}
			catch(Exception ex) {
			}
		}
	}


	
	@SuppressWarnings({ "resource", "unused" })
	@Test (description="Test Case: Test Sites CSV Download",enabled= true, priority = 15) 
	public void CSVExportNew() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-Sites-216: Verify user can download Sites CSV file");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			getDriver().navigate().refresh();
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);	
			
			steps.createNode("1. Hover mouse towards table");
			steps.createNode("2. Export file button becomes visible");
			waitElementInvisible(loading_cursor);
			
			getDriver().findElement(By.id("siteId_show-filter")).click();
			
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);						
			ClickElement.clickByCss(getDriver(), "#"+SalmonellaLogPage.slSortFilter+""+sitesSiteID+" li:nth-child(3) label");

			ClickElement.clickById(getDriver(), sitesSiteID+""+sitesApplyFilter);
			waitElementInvisible(loading_cursor);
			Thread.sleep(800);
			
			String getRowText = getDriver().findElement(By.id("results-found-count")).getText();

			steps.createNode("3. Click on the button");
			steps.createNode("4. Dropdown cloud pop ups");
			getDriver().findElement(By.cssSelector("#csv-action img")).click();
			getScreenshot();
			Thread.sleep(1000);
			steps.createNode("5. Click on Export as CSV");
			getDriver().findElement(By.xpath("//*[text() = ' Export as CSV ']")).click();
			waitElementInvisible(loading_cursor);

			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			File newfile = DownloadFileCheck.getTheNewestFile(fileDownloadPath, "csv");
			String filename= newfile.getName();
			Assert.assertEquals(filename, sitesCSVFileName+date+".csv");
			test.pass("CSV file downloaded successfully");
			results.createNode("CSV file downloads successfully");
			saveResult(ITestResult.SUCCESS, null);

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

					int rows = getDriver().findElements(By.cssSelector("tr")).size();
					if (rowsCount < rows) {
						int totalColumns = getDriver().findElements(By.cssSelector("tr:nth-child(1) td")).size() - 1;
						int columnsCount = columnsCountTotal+3;
						if (getDriver().findElements(By.cssSelector("tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).size() != 0 && columnsCount<=totalColumns) {
							softAssert.assertEquals(data[i].trim(), getDriver().findElement(By.cssSelector("tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).getText().trim());
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
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			saveResult(ITestResult.FAILURE, ex);
		}
	}
	
	
	
	
	@SuppressWarnings({ "unused", "resource" })
	@Test (description="Test Case: Test Sites CSV Download",enabled= false, priority =9) 
	public void CSVExport() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-Sites-83: Verify user can download Sites CSV file and verify the records");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Hover mouse towards table");
			steps.createNode("2. Export file button becomes visible");
			waitElementInvisible(loading_cursor);

			getDriver().navigate().refresh();
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			
		//	getDriver().findElement(By.id(sitesSiteID+""+sitesShowFilter)).click();	
			getDriver().findElement(By.id("siteId_show-filter")).click();
			
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);						
			ClickElement.clickByCss(getDriver(), "#"+SalmonellaLogPage.slSortFilter+""+sitesSiteID+" li:nth-child(3) label");

			ClickElement.clickById(getDriver(), sitesSiteID+""+sitesApplyFilter);
			waitElementInvisible(loading_cursor);
			Thread.sleep(800);

			String getRowText = getDriver().findElement(By.id("results-found-count")).getText();

			steps.createNode("3. Click on the button");
			steps.createNode("4. Dropdown cloud pop ups");
			steps.createNode("5. Click on Export as CSV");	
			steps.createNode("6. Verify Site ID is same in table and CSV");
			steps.createNode("8. Verify Site Type is same in table and CSV");
			steps.createNode("9. Verify Address is same in table and CSV");
			steps.createNode("10. Verify Date Created is same in table and CSV");
			steps.createNode("11. Verify Created By is same in table and CSV");

			Thread.sleep(1000);
			getDriver().findElement(By.cssSelector("#csv-action img")).click();
			Thread.sleep(1000);
			getScreenshot();
			ClickElement.clickById(getDriver(), "export-csv");
			waitElementInvisible(loading_cursor);
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

		
			File newfile = DownloadFileCheck.getTheNewestFile(fileDownloadPath, "csv");
			String filename= newfile.getName();
			Assert.assertEquals(filename, sitesCSVFileName+date+".csv");
			test.pass("CSV file downloaded successfully");
			results.createNode("CSV file downloads successfully");
			saveResult(ITestResult.SUCCESS, null);

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

					int a = getDriver().findElements(By.cssSelector("tr")).size();
					if (k < a) {
						System.out.print(data[i] + " ");

						int l = j+3;
						if (getDriver().findElements(By.cssSelector("tr:nth-child("+k+") td:nth-child("+l+")")).size() != 0 && l<=14) {
							//	System.out.print(getDriver().findElement(By.cssSelector("tr:nth-child("+k+") td:nth-child("+l+")")).getText()+" ");
							softAssert.assertEquals(data[i], getDriver().findElement(By.cssSelector("tr:nth-child("+k+") td:nth-child("+l+")")).getText());
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
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			saveResult(ITestResult.FAILURE, ex);
		}
		Thread.sleep(1000);
	}
	


	@Test (description="Test Case: Test Sites Audit Download",enabled= true, priority = 11) 
	public void CSVAuditExport() throws InterruptedException, IOException {
		try {
			test = extent.createTest("AN-Sites-84: Verify user can download Sites Audit file");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			steps.createNode("1. Hover mouse towards table");
			steps.createNode("2. Export file button becomes visible");
			ClickElement.clickByCss(getDriver(), "#select-site-0 .custom-checkbox");
			Thread.sleep(1000);

			//String getRowCount = getDriver().findElement(By.id("results-found-count")).getText();

			steps.createNode("3. Click on the button");
			steps.createNode("4. Dropdown cloud pop ups");
			getDriver().findElement(By.cssSelector("#csv-action img")).click();
			getScreenshot();
			Thread.sleep(1500);
			steps.createNode("5. Click on Export with Audit as CSV");
			ClickElement.clickById(getDriver(), "export-audit-csv");
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

		
			File newfile = DownloadFileCheck.getTheNewestFile(fileDownloadPath, "csv");
			String filename= newfile.getName();
			//System.out.println("Latest CSV file is = "+filename);
			Assert.assertEquals(filename, sitesCSVAuditFileName+date+".csv");
			test.pass("CSV file downloaded successfully");
			results.createNode("CSV file downloads successfully");
			saveResult(ITestResult.SUCCESS, null);

			File file = new File(fileDownloadPath+"\\"+filename); 
			if(file.delete())
				System.out.println("CSV Audit file deleted");
		}
		catch(AssertionError er) {
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			saveResult(ITestResult.FAILURE, ex);
		}
	}

	@AfterTest
	public static void endreport() {
		extent.flush();
	}
}

