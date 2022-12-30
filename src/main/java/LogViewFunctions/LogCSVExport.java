package LogViewFunctions;

import static MiscFunctions.Constants.url_login;
import static MiscFunctions.ExtentVariables.PreConditions;
import static MiscFunctions.ExtentVariables.Results;
import static MiscFunctions.ExtentVariables.Steps;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.fileDownloadPath;
import static MiscFunctions.ExtentVariables.preconditions;
import static MiscFunctions.ExtentVariables.results;
import static MiscFunctions.ExtentVariables.steps;
import static MiscFunctions.ExtentVariables.test;
import static MiscFunctions.Methods.getScreenshot;
import static MiscFunctions.Methods.waitElementInvisible;
import static PageObjects.BasePage.ResultsCount;
import static PageObjects.BasePage.loading_cursor;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import Config.BaseTest;
import MiscFunctions.ClickElement;
import MiscFunctions.DownloadFileCheck;

public class LogCSVExport {

	@SuppressWarnings({ "unused", "resource" })
	@Test (enabled= true) 
	public static void CSVExport(String name, String CSVFileName, String tablename, int filterNumber) throws InterruptedException, IOException {
		BaseTest driver = new BaseTest();
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
			
			driver.getDriver().findElement(By.cssSelector("#"+tablename+" th:nth-child("+filterNumber+") .log-header__filter-icon")).click();	
			waitElementInvisible(loading_cursor);	
			Thread.sleep(1000);						
			ClickElement.clickByCss(driver.getDriver(), "#"+tablename+" th:nth-child("+filterNumber+") li:nth-child(3) label");

			ClickElement.clickByCss(driver.getDriver(), "#"+tablename+" th:nth-child("+filterNumber+") .filter-popup__footer--apply");
			waitElementInvisible(loading_cursor);	
			Thread.sleep(2000);
			String getRowText = driver.getDriver().findElement(By.id(ResultsCount)).getText();

			driver.getDriver().findElement(By.cssSelector("#"+tablename+" #csv-action img")).click();
			Thread.sleep(1000);
			getScreenshot();
			ClickElement.clickById(driver.getDriver(), "export-csv");
			waitElementInvisible(loading_cursor);	
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			//	SalmonellaLog fr= new SalmonellaLog();
			File newfile = DownloadFileCheck.getTheNewestFile(fileDownloadPath, "csv");
			String filename= newfile.getName();
			softAssert.assertEquals(filename, CSVFileName+date+".csv");
			test.pass("CSV file downloaded successfully");
			results.createNode("CSV file downloads successfully");
			driver.saveResult(ITestResult.SUCCESS, null);

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
					int rows = driver.getDriver().findElements(By.cssSelector("tr")).size();
					if (rowsCount < rows) {
						int totalColumns = driver.getDriver().findElements(By.cssSelector("#"+tablename+" tr:nth-child(1) td")).size() - 1;
						int columnsCount = columnsCountTotal+1;
						if (driver.getDriver().findElements(By.cssSelector("#"+tablename+" tr:nth-child("+rowsCount+") td:nth-child("+rowsCount+")")).size() != 0 && columnsCount<=totalColumns) {
							softAssert.assertEquals(data[i].trim(), driver.getDriver().findElement(By.cssSelector("#"+tablename+" tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).getText().trim(), "data not matched");
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
			driver.saveResult(ITestResult.SUCCESS, null);
		}
		catch(AssertionError er) {
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			driver.saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			driver.saveResult(ITestResult.FAILURE, ex);
		}
		Thread.sleep(1000);
	}



	@SuppressWarnings({ "unused", "resource" })
	@Test (enabled= true) 
	public static void CSVExport1(String name, String CSVFileName, String tablename, int filterNumber, int skipColumns) throws InterruptedException, IOException {
		BaseTest driver = new BaseTest();
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
			driver.getDriver().findElement(By.cssSelector("#"+tablename+" th:nth-child("+filterNumber+") .log-header__filter-icon")).click();	
			waitElementInvisible(loading_cursor);	
			Thread.sleep(1000);						
			ClickElement.clickByCss(driver.getDriver(), "#"+tablename+" th:nth-child("+filterNumber+") li:nth-child(3) label");

			ClickElement.clickByCss(driver.getDriver(), "#"+tablename+" th:nth-child("+filterNumber+") .filter-popup__footer--apply");
			waitElementInvisible(loading_cursor);	
			Thread.sleep(2000);
			String getRowText = driver.getDriver().findElement(By.id(ResultsCount)).getText();

			driver.getDriver().findElement(By.cssSelector("#"+tablename+" #csv-action img")).click();
			Thread.sleep(1000);
			getScreenshot();
			ClickElement.clickById(driver.getDriver(), "export-csv");
			waitElementInvisible(loading_cursor);	
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			//	SalmonellaLog fr= new SalmonellaLog();
			File newfile = DownloadFileCheck.getTheNewestFile(fileDownloadPath, "csv");
			String filename= newfile.getName();
			softAssert.assertEquals(filename, CSVFileName+date+".csv");
			test.pass("CSV file downloaded successfully");
			results.createNode("CSV file downloads successfully");
			driver.saveResult(ITestResult.SUCCESS, null);

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
					int rows = driver.getDriver().findElements(By.cssSelector("tr")).size();
					if (rowsCount < rows) {
						int totalColumns = driver.getDriver().findElements(By.cssSelector("#"+tablename+" tr:nth-child(1) td")).size() - 1;
						//	int columnsCount = columnsCountTotal+3;
						int columnsCount = columnsCountTotal+2;
						if (driver.getDriver().findElements(By.cssSelector("#"+tablename+" tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).size() != 0 && columnsCount<=totalColumns) {
							softAssert.assertEquals(data[i].trim(), driver.getDriver().findElement(By.cssSelector("#"+tablename+" tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).getText().trim(), "data not matched");
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
			driver.saveResult(ITestResult.SUCCESS, null);
		}
		catch(AssertionError er) {
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			driver.saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			System.out.println("Failure");
			test.fail("CSV file failed to download");
			results.createNode("CSV file failed to download");
			driver.saveResult(ITestResult.FAILURE, ex);
		}
		Thread.sleep(1000);
	}

}
