package LogViewFunctions;

import static Config.BaseTest.saveResult;
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
import static MiscFunctions.Methods.*;
import static PageObjects.BasePage.*;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
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
			saveResult(ITestResult.SUCCESS, null);

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
			saveResult(ITestResult.SUCCESS, null);
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



	@SuppressWarnings({ "unused", "resource" })
	@Test (enabled= true) 
	public static void CSVExport1(String name, String CSVFileName, String tablename, int filterNumber, int skipColumns) throws InterruptedException, IOException {
		BaseTest driver = new BaseTest();
		try {
			test = extent.createTest("AN-CSVExport: Verify user can download CSV file and verify the records");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			SoftAssert softAssert = new SoftAssert();
			
			driver.getDriver().findElement(By.cssSelector("#"+tablename+" th:nth-child("+filterNumber+") .log-header__filter-icon")).click();	//open filter at index 'filternumber'
			waitElementInvisible(loading_cursor);	
			Thread.sleep(1000);						
			ClickElement.clickByCss(driver.getDriver(), "#"+tablename+" th:nth-child("+filterNumber+") li:nth-child(3) label");    //select checkbox 

			ClickElement.clickByCss(driver.getDriver(), "#"+tablename+" th:nth-child("+filterNumber+") .filter-popup__footer--apply");   //click on apply button
			waitElementInvisible(loading_cursor);	
			Thread.sleep(2000);
			String getRowsCount = driver.getDriver().findElement(By.cssSelector("#"+tablename+" #"+ResultsCount)).getText();    //get results count after applying filter

			driver.getDriver().findElement(By.cssSelector("#"+tablename+" #csv-action img")).click();    //click on csv export popup icon
			Thread.sleep(1000);
			getScreenshot();
			ClickElement.clickById(driver.getDriver(), "export-csv");    //click on export csv icon
			waitElementInvisible(loading_cursor);	
			
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");    //get date in this format (date concats in downloaded filename)
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);

			File newfile = DownloadFileCheck.getTheNewestFile(fileDownloadPath, "csv");
			String filename= newfile.getName();   							 //get the name of downloaded file
			
			softAssert.assertEquals(filename, CSVFileName+date+".csv");     //compare the actual and expected file name
			test.pass("CSV file downloaded successfully");
			saveResult(ITestResult.SUCCESS, null);

			File file = new File(fileDownloadPath+"\\"+filename);
			if(file.exists()){       //check if file exists
				System.out.println("File Exists");
			}	


			FileReader filereader = new FileReader(file);   //read content of downloaded file
			CSVReader reader = new CSVReader(filereader);
			reader = new CSVReaderBuilder(filereader).withSkipLines(1).build();    //skip header in excel file
			StringBuffer buffer = new StringBuffer();
			String data[];		    				

			int columnsCountTotal = 0;           //start from first column in IE
			int rowsCount = 1;               //start from second row (skip header row in IE)
			int csvColumns = 0;


			while((data = reader.readNext()) != null) {
				for (int i = 0; i<data.length; i++) {
					int rows = driver.getDriver().findElements(By.cssSelector("tr")).size();   //get total rows in IE
					
					if (rowsCount < rows) {
						int totalColumns = driver.getDriver().findElements(By.cssSelector("#"+tablename+" tr:nth-child(1) td")).size() - 1;  //get total columns (- 1  is done for skipping last column (Actions))

						int columnsCount = columnsCountTotal+skipColumns;   //skip the initial columns (audit view and/or checkbox)
						
						if (driver.getDriver().findElements(By.cssSelector("#"+tablename+" tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).size() != 0 && columnsCount<=totalColumns) {
							softAssert.assertEquals(data[csvColumns].trim(), driver.getDriver().findElement(By.cssSelector("#"+tablename+" tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).getText().trim(), "data not matched");
						}
						else {
							rowsCount = rowsCount+1;   //move to next row
							columnsCount = 0;     //move to first column again
							csvColumns = -1;
							columnsCountTotal = skipColumns;   //for salmonella and coccidia -1 else 0
						}
						columnsCountTotal++;
						csvColumns++;
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
			String getExcelFileRowsCount = String.valueOf(excludeHeader);
			String getIERowsCount = getRowsCount.replace(",", "");   //remove comma from results count
			
			softAssert.assertEquals(getExcelFileRowsCount, getIERowsCount, "Rows does not match in IE and downloaded Excel file");

			if(file.delete()) {
				System.out.println("CSV file deleted");  
			}

			softAssert.assertAll();
			test.pass("Column data exported successfully");
			results.createNode("Column data exported successfully");
			saveResult(ITestResult.SUCCESS, null);
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




	@Test (enabled= true)
	public static void CSVAuditExport(String name, String CSVAuditFileName, String tablename, boolean clickCheckbox) throws InterruptedException, IOException {
		BaseTest driver = new BaseTest();
		try {
			test = extent.createTest("AN-CSVExport: Verify user can download CSV Audit file for "+name+" and verify the records");
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			SoftAssert softAssert = new SoftAssert();

			steps.createNode("1. Hover mouse towards table");
			steps.createNode("2. Export file button becomes visible");

			if(clickCheckbox == true) {
				ClickElement.clickByCss(driver.getDriver(), "#" + tablename + " tr:nth-child(1) td:nth-child(1) .custom-checkbox");
				Thread.sleep(1000);
			}

			steps.createNode("3. Click on the button");
			steps.createNode("4. Dropdown cloud pop ups");
			driver.getDriver().findElement(By.cssSelector("#"+tablename+" #csv-action img")).click();
			getScreenshot();
			Thread.sleep(1500);
			steps.createNode("5. Click on Export with Audit as CSV");
			ClickElement.clickByCss(driver.getDriver(), "#"+tablename+" #export-audit-csv");
			waitElementInvisible(loading_cursor);
			Thread.sleep(3000);
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			Date date1 = new Date();
			String date= dateFormat.format(date1);
			Thread.sleep(1500);


			File newfile = DownloadFileCheck.getTheNewestFile(fileDownloadPath, "csv");
			String filename= newfile.getName();
			softAssert.assertEquals(filename, CSVAuditFileName+date+".csv");     //compare the actual and expected file name
			test.pass("CSV Audit file downloaded successfully");
			saveResult(ITestResult.SUCCESS, null);

			File file = new File(fileDownloadPath+"\\"+filename);
			if(file.exists()){       //check if file exists
				System.out.println("File Exists");
			}

			click(By.cssSelector("#"+tablename+ " #audit-trial-0"));   //open audit trail popup
			waitElementInvisible(loading_cursor);

			FileReader filereader = new FileReader(file);   //read content of downloaded file
			CSVReader reader = new CSVReader(filereader);
			reader = new CSVReaderBuilder(filereader).withSkipLines(1).build();    //skip header in excel file
			StringBuffer buffer = new StringBuffer();
			String data[];

			int columnsCount = 1;           //start from first column in IE
			int rowsCount = 1;               //start from second row (skip header row in IE)
			int auditColumn = 0;

			while((data = reader.readNext()) != null) {
				for (int i = 0; i<data.length; i++) {
					int rows = driver.getDriver().findElements(By.cssSelector(".popup-card tr")).size();   //get total rows in Audit popup

					if (rowsCount < rows) {
						int totalColumns = driver.getDriver().findElements(By.cssSelector(".popup-card tr:nth-child(1) td")).size();

						if (driver.getDriver().findElements(By.cssSelector(".popup-card tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).size() != 0 && columnsCount<=totalColumns) {

						System.out.println(data[auditColumn].trim()+"--->"+driver.getDriver().findElement(By.cssSelector(".popup-card tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).getText().trim());
						softAssert.assertEquals(data[auditColumn].trim(), driver.getDriver().findElement(By.cssSelector(".popup-card tr:nth-child("+rowsCount+") td:nth-child("+columnsCount+")")).getText().trim(), "data not matched");
						}
						else {
							rowsCount = rowsCount+1;   //move to next row
							columnsCount = 0;     //move to first column again
							auditColumn = -1;
						}
						columnsCount++;
						auditColumn++;

					}
				}
			}


			if(file.delete()) {
				System.out.println("CSV file deleted");
			}

			if (size(popupCloseButton)!=0) {
				click(popupCloseButton);
			}

			if (size(popupCloseButton2)!=0) {
				click(popupCloseButton2);
			}

			softAssert.assertAll();
			test.pass("Column data exported successfully");
			results.createNode("Column data exported successfully");
			saveResult(ITestResult.SUCCESS, null);
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













}
