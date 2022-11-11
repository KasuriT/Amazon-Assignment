package Test.Ancera.Administration;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Models.ProgramManagementModel;
import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.DB_Config;
import Test.Ancera.DateUtil;
import Test.Ancera.Queries;

import static Test.Ancera.Test_Variables.*;
import static Test.Ancera.Helper.*;
import static Test.Ancera.Test_Elements.*;
import static Test.Ancera.Constants.*;
import static Test.Ancera.Test_Functions.*;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class ProgramManagement extends DB_Config {


	@BeforeTest 
	public void extent() throws InterruptedException, IOException {
		spark = new ExtentSparkReporter("target/Reports/Administration_Program_Management"+date+".html");
		spark.config().setReportName("Program Management Test Report"); 
		config();
		ConfigureLogin.login();
	}


	//	@Test(priority= 1)
	//	public void Navigate() throws InterruptedException, IOException {
	//		NavigateToScreen(url_programManagement, "Program Management", ProgramManagementReportPath, programManagementTitle);
	//	}	


	@Test (enabled= true, priority= 2) 
	public void CreatePrograms() throws InterruptedException, IOException, SQLException {
		lstProgramManagementSearch = ProgramManagementModel.FillData();

		driver.get(Constants.url_programManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);

		for (ProgramManagementModel objModel : lstProgramManagementSearch) { 	
			try {
				test = extent.createTest(objModel.TestCaseNameCreate);
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				preconditions.createNode("1. Go to url " +Constants.url_login);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				preconditions.createNode("3. Hover to sidebar to expand the menu");
				preconditions.createNode("4. Click on Administration and select Program Management; Program Management screen opens");
				steps.createNode("1. Click on Create New Program button");
				steps.createNode("2. Add valid data in all fields");
				steps.createNode("3. Click on save button");

				SoftAssert softAssert = new SoftAssert();


				driver.findElement(By.xpath("//*[text()= 'Vaccine Programs ']")).click();
				waitElementInvisible(loading_cursor);
				driver.findElement(By.xpath("//*[text()=' Register New Program']")).click();
				waitElementInvisible(loading_cursor);

				//Program Name
				softAssert.assertEquals(driver.findElements(By.cssSelector("#btn-save.disabled-v2")).size(), 2, "Mandatory check failed");
				driver.findElement(programName).sendKeys(objModel.ProgramName);
				softAssert.assertEquals(driver.findElements(By.cssSelector("#btn-save.disabled-v2")).size(), 2, "Mandatory check failed");

				//Target Pathogen
				driver.findElement(programTargetPathogen).click();
				Thread.sleep(500);
				softAssert.assertEquals(driver.findElement(By.cssSelector(".ng-option-label")).getText(), "Coccidia");
				driver.findElement(programTargetPathogen).sendKeys(Keys.ENTER);
				Thread.sleep(500);
				softAssert.assertEquals(driver.findElements(By.cssSelector("#btn-save.disabled-v2")).size(), 2, "Mandatory check failed");

				//Program Type
				driver.findElement(programProgramType).sendKeys(objModel.ProgramType);
				Thread.sleep(500);	
				softAssert.assertEquals(driver.findElements(By.cssSelector("#btn-save.disabled-v2")).size(), 2, "Mandatory check failed");
				driver.findElement(programProgramType).sendKeys(Keys.ENTER);

				//Supplier
				if (!objModel.ProgramType.equals("Feed")) {    //creating feed program without supplier
					driver.findElement(programSupplier).sendKeys(ProgramManagementModel.SupplierName);
					Thread.sleep(500);
					if (driver.findElements(By.xpath("//*[text()='Add New + ']")).size() != 0) {
						driver.findElement(By.xpath("//*[text()='Add New + ']")).click();
					}
					else {
						driver.findElement(By.cssSelector(".list-item")).click();		
					}
					Thread.sleep(500);
				}

				//Complex
				//		softAssert.assertEquals(size(programComplexMandatoryCheck), 1, "Complex should be mandatory field");			
				click(programComplexList);
				if (Constants.url.contains("qa") || Constants.url.contains("dev")) {
					//	driver.findElement(programComplexSearch).sendKeys(ProgramManagementModel.ComplexNameQA);
					ResultSet getComplexNameResults = getStmt().executeQuery(Queries.getComplexName);
					while (getComplexNameResults.next()) {
						String complexName = getComplexNameResults.getString("siteName");
						System.out.println("Complex Name: "+complexName);
						type(programComplexSearch, complexName);
					}
				}
				if (Constants.url.contains("uat")) {
					driver.findElement(programComplexSearch).sendKeys(ProgramManagementModel.ComplexNameUAT);
				}
	
				Thread.sleep(1500);
				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);
				click(clickSearchItemFromHierarchy);

				//Start Date
				click(programStartDateIcon);
				waitElementInvisible(loading_cursor);
				Thread.sleep(500);
				WebElement dateWidgetTo = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#startDate .dp-popup"))).get(0);
				List<WebElement> columns1 = dateWidgetTo.findElements(By.tagName("button"));
				DateUtil.clickGivenDay(columns1, DateUtil.getFirstDay());
				Thread.sleep(500);

				//End Date
				driver.findElement(By.cssSelector("#endDate img")).click();
				waitElementInvisible(loading_cursor);
				Thread.sleep(500);
				WebElement dateWidgetToEnd = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#endDate .dp-popup"))).get(0);
				List<WebElement> columns2 = dateWidgetToEnd.findElements(By.tagName("button"));
				DateUtil.clickGivenDay(columns2, DateUtil.getDay("30"));
				Thread.sleep(700);

				//Program Description
				driver.findElement(programDescription).sendKeys(ProgramManagementModel.DescriptionName);

				//Vaccine Number of Applications on Flock
				if (objModel.ProgramType.startsWith("Vaccine")) {
					driver.findElement(programNoApplicationFlock).sendKeys("64");
					softAssert.assertEquals(driver.findElements(By.cssSelector("#numOfApplicationId-error-container svg")).size(), 1, "Mandatory check failed on No of Application Flock");
					Thread.sleep(500);
					clear(programNoApplicationFlock);

					String NoApplicationFlock = "2";
					driver.findElement(programNoApplicationFlock).sendKeys(NoApplicationFlock);
					Thread.sleep(500);

					for(int i=1; i<=Integer.parseInt(NoApplicationFlock); i++) {
						driver.findElement(By.id(programDaysApplicationFlock+"-"+i)).sendKeys(""+i);
					}
				}

				//Feed Details
				if (objModel.ProgramType.equals("Feed")) {
					driver.findElement(programFeedTypeDropdown).click();
					Thread.sleep(500);	
					driver.findElement(programFeedTypeDropdown).sendKeys(Keys.ENTER);

					driver.findElement(programFlockDayStart).sendKeys("1");

					WebElement EndDay = driver.findElement(programFlockDayStart);
					driver.findElement(with(By.tagName("input")).toRightOf(EndDay)).sendKeys("10");

					WebElement ingredient = driver.findElement(programFeedTypeDropdown);
					driver.findElement(with(By.tagName("input")).below(ingredient)).sendKeys("Sugar");

					WebElement ingredientCategory = driver.findElement(programFlockDayStart);
					driver.findElement(with(By.tagName("input")).below(ingredientCategory)).click();
					List<WebElement> ingredientCategories = driver.findElements(By.cssSelector(".ng-option-label"));
					softAssert.assertEquals(ingredientCategories.get(0).getText(), "Antibiotic");
					softAssert.assertEquals(ingredientCategories.get(1).getText(), "Coccidia Stat");
					softAssert.assertEquals(ingredientCategories.get(2).getText(), "Natural");
					ingredientCategories.get(0).click();
					driver.findElement(By.xpath(("//*[text()='Add Ingredient']"))).click();
				}

				//Treatment Details
				if(objModel.ProgramType.equals("Treatment")) {
					driver.findElement(By.xpath("//div[2]/div[1]/div[1]/app-anc-input-box/div/input[1]")).sendKeys("Treatment Program");

				}

				//Bioshuttle Details
				if(objModel.ProgramType.equals("Vaccine with Bioshuttle")) {
					driver.findElement(By.xpath("//div[2]/div[1]/app-anc-input-box/div/input[1]")).sendKeys("BioShuttle Vaccine");	

					type(programBioshuttleFlockDayStart, "64");
					softAssert.assertEquals(size(programBioshuttleFlockDayStartError), 0, "Error did not appeared");
					clear(programBioshuttleFlockDayStart);
					type(programBioshuttleFlockDayStart, "1");
					softAssert.assertEquals(size(programBioshuttleFlockDayStartError), 1, "Error did not hiden");

					type(programBioshuttleFlockDayEnd, "64");
					softAssert.assertEquals(size(programBioshuttleFlockDayEndError), 0, "Error did not appeared");
					clear(programBioshuttleFlockDayEnd);
					type(programBioshuttleFlockDayEnd, "10");
				}

				test.addScreenCaptureFromPath(getScreenshot("Program Management", Constants.ProgramManagementReportPath));
				driver.findElement(By.xpath(("//button[text() = ' Submit ']"))).click();

				waitElementInvisible(loading_cursor);
				Thread.sleep(1500);
				softAssert.assertEquals(driver.findElement(alertMessage).getText(), "New program has been created successfully"); 

				//Feed Verification
				if (objModel.ProgramType.equals("Feed")) {
					waitElementVisible(By.cssSelector("tr:nth-child(1) "+programFeedProgramNameCol));
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programFeedProgramNameCol)).getText(), ProgramManagementModel.FeedProgramName);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programFeedSupplierNameCol)).getText(), "");
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programFeedDescriptionCol)).getText(), ProgramManagementModel.DescriptionName);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programFeedFeedTypesCol)).getText(), "Feed Type 1");
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programFeedStartDateCol)).getText(), dateMM+"/01/"+dateYYYY);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programFeedEndDateCol)).getText(), dateMM+"/30/"+dateYYYY);			
				}

				//Treatment Verification
				if (objModel.ProgramType.equals("Treatment")) {
					waitElementVisible(By.cssSelector("tr:nth-child(1) "+programTreatmentProgramNameCol));
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentProgramNameCol)).getText(), ProgramManagementModel.TreatmentProgramName);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentDescriptionCol)).getText(), ProgramManagementModel.DescriptionName);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentStartDateCol)).getText(), dateMM+"/01/"+dateYYYY);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentEndDateCol)).getText(), dateMM+"/30/"+dateYYYY);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentNameCol)).getText(), "Treatment Program");			
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentFlockDayStartCol)).getText(), "");			
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentFlockDayEndCol)).getText(), "");			
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentRouteCol)).getText(), "");
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentTreatmentDescriptionCol)).getText(), "");					
				}

				//Vaccine Verification
				if (objModel.ProgramType.equals("Vaccine")) {
					waitElementVisible(By.cssSelector("tr:nth-child(1) "+programVaccineProgramNameCol));
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programVaccineProgramNameCol)).getText(), ProgramManagementModel.VaccineProgramName);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programVaccineSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programVaccineNumberOfApplicationFlockCol)).getText(), "2");
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programVaccineDescriptionCol)).getText(), ProgramManagementModel.DescriptionName);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programVaccineStartDateCol)).getText(), dateMM+"/01/"+dateYYYY);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programVaccineEndDateCol)).getText(), dateMM+"/30/"+dateYYYY);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programVaccineFlockDayApplicationCol)).getText(), "1,2");								
				}

				//Vaccine Bioshuttle Verification
				if (objModel.ProgramType.equals("Vaccine with Bioshuttle")) {
					waitElementVisible(By.cssSelector("tr:nth-child(1) "+programBioshuttleProgramNameCol));
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleProgramNameCol)).getText(), ProgramManagementModel.BioshuttleProgramName);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleNumberOfApplicationFlockCol)).getText(), "2");
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleDescriptionCol)).getText(), ProgramManagementModel.DescriptionName);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleStartDateCol)).getText(), dateMM+"/01/"+dateYYYY);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleEndDateCol)).getText(), dateMM+"/30/"+dateYYYY);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleFlockDayApplicationCol)).getText(), "1,2");	
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleFlockDayStartCol)).getText(), "1");	
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleFlockDayEndCol)).getText(), "10");	
				}

				softAssert.assertAll();
				test.pass("New Program created successfully");
				results.createNode("New Program created successfully");
				test.addScreenCaptureFromPath(getScreenshot("Program Management", Constants.ProgramManagementReportPath));
				saveResultNew(ITestResult.SUCCESS, Constants.ProgramManagementReportPath, null);
			}
			catch(AssertionError er) {
				test.fail("New Program failed to create");
				results.createNode("New Program failed to create");
				saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, new Exception(er));
			}catch(Exception ex) {
				test.fail("New Program failed to create");
				results.createNode("New Program failed to create");
				saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, ex);
			}
		}
		getStmt().close();
	}


	@Test (enabled= true, priority= 3) 
	public void UpdatePrograms() throws InterruptedException, IOException {
		lstProgramManagementSearch = ProgramManagementModel.FillData();

		driver.get(Constants.url_programManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);

		for (ProgramManagementModel objModel : lstProgramManagementSearch) { 	
			try {
				test = extent.createTest(objModel.TestCaseNameUpdate);
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				preconditions.createNode("1. Go to url " +Constants.url_login);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				preconditions.createNode("3. Hover to sidebar to expand the menu");
				preconditions.createNode("4. Click on Administration and select Program Management; Program Management screen opens");
				steps.createNode("1. Click on Create New Program button");
				steps.createNode("2. Add valid data in all fields");
				steps.createNode("3. Click on save button");

				SoftAssert softAssert = new SoftAssert();

				driver.findElement(By.xpath("//*[text()= '"+objModel.Program+" ']")).click();
				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);

				List<WebElement> programsName = driver.findElements(By.cssSelector(objModel.ProgramName_CSS));

				for (int i = 1; i <= programsName.size(); i++) {
					if (driver.findElement(By.cssSelector("tr:nth-child("+i+") "+objModel.ProgramName_CSS)).getText().equals(objModel.ProgramName)) {
						scroll(By.xpath("//*[@id='"+objModel.ProgramTable+"'] //*[text()='Action']"));
						waitElementClickable(By.id(objModel.EditButtonPre+""+i+"-"+objModel.ButtonPost));
						Thread.sleep(1000);
						driver.findElement(By.id(objModel.EditButtonPre+""+i+"-"+objModel.ButtonPost)).click();		
						break;
					}
					else {
						Assert.assertTrue(false);
					}
				}

				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);
				//Program Description
				driver.findElement(programDescription).sendKeys(" Updated");
				Thread.sleep(500);	
				test.addScreenCaptureFromPath(getScreenshot("Program Management", Constants.ProgramManagementReportPath));
				click(popupSaveButton);

				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);

				softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Program details updated"); 

				//Feed Verification
				if (objModel.ProgramType.equals("Feed")) {
					softAssert.assertEquals(driver.findElement(By.cssSelector(programFeedProgramNameCol)).getText(), ProgramManagementModel.FeedProgramName);
					softAssert.assertEquals(driver.findElement(By.cssSelector(programFeedSupplierNameCol)).getText(), "");
					softAssert.assertEquals(driver.findElement(By.cssSelector(programFeedDescriptionCol)).getText(), ProgramManagementModel.DescriptionName+" Updated");
					softAssert.assertEquals(driver.findElement(By.cssSelector(programFeedFeedTypesCol)).getText(), "Feed Type 1");
					softAssert.assertEquals(driver.findElement(By.cssSelector(programFeedStartDateCol)).getText(), dateMM+"/01/"+dateYYYY);
					softAssert.assertEquals(driver.findElement(By.cssSelector(programFeedEndDateCol)).getText(), dateMM+"/30/"+dateYYYY);			
				}

				//Treatment Verification
				if (objModel.ProgramType.equals("Treatment")) {
					softAssert.assertEquals(driver.findElement(By.cssSelector(programTreatmentProgramNameCol)).getText(), ProgramManagementModel.TreatmentProgramName);
					softAssert.assertEquals(driver.findElement(By.cssSelector(programTreatmentSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
					softAssert.assertEquals(driver.findElement(By.cssSelector(programTreatmentDescriptionCol)).getText(), ProgramManagementModel.DescriptionName+" Updated");
					softAssert.assertEquals(driver.findElement(By.cssSelector(programTreatmentStartDateCol)).getText(), dateMM+"/01/"+dateYYYY);
					softAssert.assertEquals(driver.findElement(By.cssSelector(programTreatmentEndDateCol)).getText(), dateMM+"/30/"+dateYYYY);
					softAssert.assertEquals(driver.findElement(By.cssSelector(programTreatmentNameCol)).getText(), "Treatment Program");			
					softAssert.assertEquals(driver.findElement(By.cssSelector(programTreatmentFlockDayStartCol)).getText(), "");			
					softAssert.assertEquals(driver.findElement(By.cssSelector(programTreatmentFlockDayEndCol)).getText(), "");			
					softAssert.assertEquals(driver.findElement(By.cssSelector(programTreatmentRouteCol)).getText(), "");
					softAssert.assertEquals(driver.findElement(By.cssSelector(programTreatmentTreatmentDescriptionCol)).getText(), "");					
				}

				//Vaccine Verification
				if (objModel.ProgramType.equals("Vaccine")) {
					softAssert.assertEquals(driver.findElement(By.cssSelector(programVaccineProgramNameCol)).getText(), ProgramManagementModel.VaccineProgramName);
					softAssert.assertEquals(driver.findElement(By.cssSelector(programVaccineSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
					softAssert.assertEquals(driver.findElement(By.cssSelector(programVaccineNumberOfApplicationFlockCol)).getText(), "2");
					softAssert.assertEquals(driver.findElement(By.cssSelector(programVaccineDescriptionCol)).getText(), ProgramManagementModel.DescriptionName+" Updated");
					softAssert.assertEquals(driver.findElement(By.cssSelector(programVaccineStartDateCol)).getText(), dateMM+"/01/"+dateYYYY);
					softAssert.assertEquals(driver.findElement(By.cssSelector(programVaccineEndDateCol)).getText(), dateMM+"/30/"+dateYYYY);
					softAssert.assertEquals(driver.findElement(By.cssSelector(programVaccineFlockDayApplicationCol)).getText(), "1,2");								
				}

				//Vaccine Bioshuttle Verification
				if (objModel.ProgramType.equals("Vaccine with Bioshuttle")) {
					softAssert.assertEquals(driver.findElement(By.cssSelector(programBioshuttleProgramNameCol)).getText(), ProgramManagementModel.BioshuttleProgramName);
					softAssert.assertEquals(driver.findElement(By.cssSelector(programBioshuttleSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
					softAssert.assertEquals(driver.findElement(By.cssSelector(programBioshuttleNumberOfApplicationFlockCol)).getText(), "2");
					softAssert.assertEquals(driver.findElement(By.cssSelector(programBioshuttleDescriptionCol)).getText(), ProgramManagementModel.DescriptionName+" Updated");
					softAssert.assertEquals(driver.findElement(By.cssSelector(programBioshuttleStartDateCol)).getText(), dateMM+"/01/"+dateYYYY);
					softAssert.assertEquals(driver.findElement(By.cssSelector(programBioshuttleEndDateCol)).getText(), dateMM+"/30/"+dateYYYY);
					softAssert.assertEquals(driver.findElement(By.cssSelector(programBioshuttleFlockDayApplicationCol)).getText(), "1,2");	
					softAssert.assertEquals(driver.findElement(By.cssSelector(programBioshuttleFlockDayStartCol)).getText(), "1");	
					softAssert.assertEquals(driver.findElement(By.cssSelector(programBioshuttleFlockDayEndCol)).getText(), "10");	
				}

				softAssert.assertAll();
				test.pass("New Program updated successfully");
				results.createNode("New Program updated successfully");
				test.addScreenCaptureFromPath(getScreenshot("Program Management", Constants.ProgramManagementReportPath));
				saveResultNew(ITestResult.SUCCESS, Constants.ProgramManagementReportPath, null);
			}
			catch(AssertionError er) {
				test.fail("New Program failed to update");
				results.createNode("New Program failed to update");
				saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, new Exception(er));
			}catch(Exception ex) {
				test.fail("New Program failed to update");
				results.createNode("New Program failed to update");
				saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, ex);
			}
		}
	}



	@Test (enabled= true, priority= 4) 
	public void DuplicatePrograms() throws InterruptedException, IOException {
		lstProgramManagementSearch = ProgramManagementModel.FillData();

		driver.get(Constants.url_programManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);

		for (ProgramManagementModel objModel : lstProgramManagementSearch) { 	
			try {
				test = extent.createTest(objModel.TestCaseNameDuplicate);
				preconditions = test.createNode(Scenario.class, PreConditions);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				preconditions.createNode("1. Go to url " +Constants.url_login);
				preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				preconditions.createNode("3. Hover to sidebar to expand the menu");
				preconditions.createNode("4. Click on Administration and select Program Management; Program Management screen opens");
				steps.createNode("1. Click on Create New Program button");
				steps.createNode("2. Add valid data in all fields");
				steps.createNode("3. Click on save button");

				SoftAssert softAssert = new SoftAssert();

				driver.findElement(By.xpath("//*[text()= '"+objModel.Program+" ']")).click();
				waitElementInvisible(loading_cursor);

				List<WebElement> programsName = driver.findElements(By.cssSelector(objModel.ProgramName_CSS));

				for (int i = 1; i <= programsName.size(); i++) {
					if (driver.findElement(By.cssSelector("tr:nth-child("+i+") "+objModel.ProgramName_CSS)).getText().equals(objModel.ProgramName)) {
						System.out.println(1);
						scroll(By.xpath("//*[@id='"+objModel.ProgramTable+"'] //*[text()='Action']"));
						waitElementClickable(By.id(objModel.EditButtonPre+""+i+"-"+objModel.ButtonPost));
						Thread.sleep(1000);
						//	driver.findElement(By.id(objModel.CopyButtonPre+""+i+"-"+objModel.ButtonPost)).click();		
						driver.findElement(By.xpath("//*[@id = '"+objModel.CopyButtonPre+""+i+"-"+objModel.ButtonPost+"']")).click();		

						break;        
					}
					else {
						Assert.assertTrue(false, "Program not found");
					}
				}

				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);

				type(programName, objModel.ProgramName+"_Copy");
				softAssert.assertEquals(driver.findElement(programTargetPathogenValue).getText(), "Coccidia", "Target Pathogen is empty ");
				softAssert.assertEquals(driver.findElement(programProgramTypeValue).getText(), objModel.ProgramType, " Program Type is empty ");
				softAssert.assertEquals(size(programProgramTypeDisabledCheck), 1, "Program Type is not disabled");


				//Supplier
				if (!objModel.ProgramType.equals("Feed")) { 
					softAssert.assertEquals(driver.findElement(programSupplier).getAttribute("value"), ProgramManagementModel.SupplierName, "Supplier name is empty ");
				}

				//	softAssert.assertEquals(size(programComplexMandatoryCheck), 1, "Complex should be mandatory field");			
				softAssert.assertEquals(size(programComplexSelected), 1);
				softAssert.assertNotEquals(driver.findElement(By.id("startDate")).getAttribute("value"), "", "Start Date is empty");
				softAssert.assertNotEquals(driver.findElement(By.id("endDate")).getAttribute("value"), "", "End Date is empty");
				softAssert.assertNotEquals(driver.findElement(programDescription).getAttribute("value"), "", "Description is empty");
				clear(programDescription);				
				type(programDescription, ProgramManagementModel.DescriptionName+"_Copy");

				//Vaccine Number of Applications on Flock
				if (objModel.ProgramType.startsWith("Vaccine")) {
					String NoApplicationFlock = "2";
					softAssert.assertEquals(driver.findElement(programNoApplicationFlock).getAttribute("value"), NoApplicationFlock, "No of Application Flock empty");

					for(int i=1; i<=Integer.parseInt(NoApplicationFlock); i++) {
						softAssert.assertEquals(Integer.parseInt(driver.findElement(By.id(programDaysApplicationFlock+"-"+i)).getAttribute("value")), i, "No of Application Flock Days empty");
					}
				}

				//Feed Details
				if (objModel.ProgramType.equals("Feed")) {
					softAssert.assertEquals(driver.findElement(programFeedTypeValue).getText(), "Feed Type 1");
					//					softAssert.assertNotEquals(driver.findElement(programFlockDayStart).getAttribute("value"), "");

					WebElement EndDay = driver.findElement(programFlockDayStart);
					softAssert.assertNotEquals(driver.findElement(with(By.tagName("input")).toRightOf(EndDay)).getAttribute("value"), "");

					//					WebElement ingredient = driver.findElement(programFeedTypeDropdown);
					//					softAssert.assertEquals(driver.findElement(with(By.tagName("input")).below(ingredient)).getAttribute("value"), "Sugar");
				}

				//Treatment Details
				if(objModel.ProgramType.equals("Treatment")) {
					softAssert.assertEquals(driver.findElement(By.xpath("//div[2]/div[1]/div[1]/app-anc-input-box/div/input[1]")).getAttribute("value"), "Treatment Program");
				}

				//Bioshuttle Details
				if(objModel.ProgramType.equals("Vaccine with Bioshuttle")) {
					softAssert.assertEquals(driver.findElement(By.xpath("//div[2]/div[1]/app-anc-input-box/div/input[1]")).getAttribute("value"), "BioShuttle Vaccine");
				}

				test.addScreenCaptureFromPath(getScreenshot("Program Management", Constants.ProgramManagementReportPath));
				driver.findElement(By.xpath(("//button[text() = ' Submit ']"))).click();

				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);

				softAssert.assertEquals(driver.findElement(alertMessage).getText(), "New program has been created successfully"); 

				//Feed Verification
				if (objModel.ProgramType.equals("Feed")) {
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programFeedProgramNameCol)).getText(), ProgramManagementModel.FeedProgramName+"_Copy");
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programFeedSupplierNameCol)).getText(), "");
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programFeedDescriptionCol)).getText(), ProgramManagementModel.DescriptionName+"_Copy");
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programFeedFeedTypesCol)).getText(), "Feed Type 1");
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programFeedStartDateCol)).getText(), dateMM+"/01/"+dateYYYY);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programFeedEndDateCol)).getText(), dateMM+"/30/"+dateYYYY);			
				}

				//Treatment Verification
				if (objModel.ProgramType.equals("Treatment")) {
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentProgramNameCol)).getText(), ProgramManagementModel.TreatmentProgramName+"_Copy");
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentDescriptionCol)).getText(), ProgramManagementModel.DescriptionName+"_Copy");
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentStartDateCol)).getText(), dateMM+"/01/"+dateYYYY);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentEndDateCol)).getText(), dateMM+"/30/"+dateYYYY);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentNameCol)).getText(), "Treatment Program");			
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentFlockDayStartCol)).getText(), "");			
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentFlockDayEndCol)).getText(), "");			
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentRouteCol)).getText(), "");
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentTreatmentDescriptionCol)).getText(), "");					
				}

				//Vaccine Verification
				if (objModel.ProgramType.equals("Vaccine")) {
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programVaccineProgramNameCol)).getText(), ProgramManagementModel.VaccineProgramName+"_Copy");
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programVaccineSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programVaccineNumberOfApplicationFlockCol)).getText(), "2");
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programVaccineDescriptionCol)).getText(), ProgramManagementModel.DescriptionName+"_Copy");
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programVaccineStartDateCol)).getText(), dateMM+"/01/"+dateYYYY);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programVaccineEndDateCol)).getText(), dateMM+"/30/"+dateYYYY);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programVaccineFlockDayApplicationCol)).getText(), "1,2");								
				}

				//Vaccine Bioshuttle Verification
				if (objModel.ProgramType.equals("Vaccine with Bioshuttle")) {
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleProgramNameCol)).getText(), ProgramManagementModel.BioshuttleProgramName+"_Copy");
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleNumberOfApplicationFlockCol)).getText(), "2");
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleDescriptionCol)).getText(), ProgramManagementModel.DescriptionName+"_Copy");
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleStartDateCol)).getText(), dateMM+"/01/"+dateYYYY);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleEndDateCol)).getText(), dateMM+"/30/"+dateYYYY);
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleFlockDayApplicationCol)).getText(), "1,2");	
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleFlockDayStartCol)).getText(), "1");	
					softAssert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleFlockDayEndCol)).getText(), "10");	
				}

				softAssert.assertAll();
				test.pass("New Program copied successfully");
				results.createNode("New Program copied successfully");
				test.addScreenCaptureFromPath(getScreenshot("Program Management", Constants.ProgramManagementReportPath));
				saveResultNew(ITestResult.SUCCESS, Constants.ProgramManagementReportPath, null);
			}
			catch(AssertionError er) {
				test.fail("New Program failed to copy");
				results.createNode("New Program failed to copy");
				saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, new Exception(er));
			}catch(Exception ex) {
				test.fail("New Program failed to copy");
				results.createNode("New Program failed to copy");
				saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, ex);
			}
		}
	}


	@Test (enabled= true, priority= 5, dependsOnMethods = {"CreatePrograms"}) 
	public void DeleteDuplicatePrograms() throws InterruptedException, IOException {
		lstProgramManagementSearch = ProgramManagementModel.FillData();

		driver.get(Constants.url_programManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);

		for (ProgramManagementModel objModel : lstProgramManagementSearch) { 	
			try {
				test = extent.createTest(objModel.TestCaseNameDelete);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				steps.createNode("1. Delete the created program");

				SoftAssert softAssert = new SoftAssert();
				driver.findElement(By.xpath("//*[text()= '"+objModel.Program+" ']")).click();
				waitElementInvisible(loading_cursor);

				List<WebElement> programsName = driver.findElements(By.cssSelector(objModel.ProgramName_CSS));

				for (int i = 1; i <= programsName.size(); i++) {
					if (driver.findElement(By.cssSelector("tr:nth-child("+i+") "+objModel.ProgramName_CSS)).getText().equals(objModel.ProgramName+"_Copy")) {
						System.out.println("1");
						scroll(By.xpath("//*[@id='"+objModel.ProgramTable+"'] //*[text()='Action']"));
						waitElementClickable(By.id(objModel.DeleteButtonPre+""+i+"-"+objModel.ButtonPost));
						Thread.sleep(1000);
						driver.findElement(By.id(objModel.DeleteButtonPre+""+i+"-"+objModel.ButtonPost)).click();		
						break;
					}
					else {
						Assert.assertTrue(false);
					}
				}

				waitElementVisible(popupYesButton);
				click(popupYesButton);	
				waitElementInvisible(loading_cursor);				
				Thread.sleep(1000);

				softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Program details deleted"); 			
				softAssert.assertAll();
				test.pass("New Program deleted successfully");
				results.createNode("New Program deleted successfully");
				test.addScreenCaptureFromPath(getScreenshot("Program Management", Constants.ProgramManagementReportPath));
				saveResultNew(ITestResult.SUCCESS, Constants.ProgramManagementReportPath, null);
			}
			catch(AssertionError er) {
				test.fail("New Program failed to delete");
				results.createNode("New Program failed to delete");
				saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, new Exception(er));
			}catch(Exception ex) {
				test.fail("New Program failed to delete");
				results.createNode("New Program failed to delete");
				saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, ex);
			}
		}
	}

	@Test (enabled= true, priority= 5, dependsOnMethods = {"CreatePrograms"}) 
	public void DeletePrograms() throws InterruptedException, IOException {
		lstProgramManagementSearch = ProgramManagementModel.FillData();

		driver.get(Constants.url_programManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);

		for (ProgramManagementModel objModel : lstProgramManagementSearch) { 	
			try {
				test = extent.createTest(objModel.TestCaseNameDelete);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				steps.createNode("1. Delete the created program");

				SoftAssert softAssert = new SoftAssert();
				driver.findElement(By.xpath("//*[text()= '"+objModel.Program+" ']")).click();
				waitElementInvisible(loading_cursor);

				List<WebElement> programsName = driver.findElements(By.cssSelector(objModel.ProgramName_CSS));

				for (int i = 1; i <= programsName.size(); i++) {
					if (driver.findElement(By.cssSelector("tr:nth-child("+i+") "+objModel.ProgramName_CSS)).getText().equals(objModel.ProgramName)) {
						System.out.println("1");
						scroll(By.xpath("//*[@id='"+objModel.ProgramTable+"'] //*[text()='Action']"));
						waitElementClickable(By.id(objModel.DeleteButtonPre+""+i+"-"+objModel.ButtonPost));
						Thread.sleep(1000);
						driver.findElement(By.id(objModel.DeleteButtonPre+""+i+"-"+objModel.ButtonPost)).click();		
						break;
					}
					else {
						Assert.assertTrue(false);
					}
				}

				waitElementVisible(popupYesButton);
				click(popupYesButton);	
				waitElementInvisible(loading_cursor);				
				Thread.sleep(1000);

				softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Program details deleted"); 			
				softAssert.assertAll();
				test.pass("New Program deleted successfully");
				results.createNode("New Program deleted successfully");
				test.addScreenCaptureFromPath(getScreenshot("Program Management", Constants.ProgramManagementReportPath));
				saveResultNew(ITestResult.SUCCESS, Constants.ProgramManagementReportPath, null);
			}
			catch(AssertionError er) {
				test.fail("New Program failed to delete");
				results.createNode("New Program failed to delete");
				saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, new Exception(er));
			}catch(Exception ex) {
				test.fail("New Program failed to delete");
				results.createNode("New Program failed to delete");
				saveResultNew(ITestResult.FAILURE, Constants.ProgramManagementReportPath, ex);
			}
		}
	}


	@Test (enabled= true, priority= 6) 
	public void VerifyColumns() throws InterruptedException, IOException {
		try {		
			test = extent.createTest("AN-Program-09: Verify that all columns appear", "This testcase will verify that all columns appear in table");
			preconditions = test.createNode(Scenario.class, PreConditions);
			steps = test.createNode(Scenario.class, Steps);
			results = test.createNode(Scenario.class, Results);

			preconditions.createNode("1. Go to url " +Constants.url_login);
			preconditions.createNode("2. Login with valid credentials; user navigates to home page");
			preconditions.createNode("3. Hover to sidebar to expand the menu");
			preconditions.createNode("4. Click on Administration and select Program Management; Program Management screen opens");
			preconditions.createNode("5. Create Feed Program");
			steps.createNode("1. Verify all columns appear and columns have filter icon with them");

			SoftAssert softAssert = new SoftAssert();

			driver.get(Constants.url_programManagement);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			String[] feedColumnNamesExpected = {"Program Name", "Supplier Name", "Description", "Feed Types", "Start Date", "End Date", "Complex", "Action"};
			String[] treatmentColumnNamesExpected = {"Program Name", "Supplier Name", "Description", "Start Date", "End Date", "Treatment Name", "Flock Day Start", "Flock Day End", "Route", "Treatment Description", "Complex", "Action"};
			String[] vaccineColumnNamesExpected = {"Program Name", "Supplier Name", "No. Of Applications On Flock", "Description", "Start Date", "End Date", "Flock Day Applications", "Complex", "Action"};
			String[] bioshuttleColumnNamesExpected = {"Program Name", "Supplier Name", "No. Of Applications On Flock", "Description", "Start Date", "End Date", "Flock Day Applications", "Bioshuttle Name", "Bioshuttle Flock Day Start", "Bioshuttle Flock Day End", "Complex", "Action"};
			String[] utilizationColumnNamesExpected = {"Flock ID", "Integrator Flock ID", "Program Type", "Program Name", "Complex", "Farm", "House Placement", "Start Date", "End Date"};


			List<WebElement> feedColumnNamesActual = driver.findElements(By.cssSelector("#"+programFeedTable+" th"));
			int sizeFeedTable = feedColumnNamesActual.size();

			for(int i =0; i<sizeFeedTable ; i++){
				softAssert.assertEquals(feedColumnNamesActual.get(i).getText(), feedColumnNamesExpected[i]);
			}

			softAssert.assertEquals(size(By.xpath("//*[@id = '"+programFeedTable+"'] //*[contains(@id, '_show-filter')]")), 5, "All filter now displaying filter not displaying for feed tab");


			click(programTreatmentProgramTab);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			List<WebElement> treatmentColumnNamesActual = driver.findElements(By.cssSelector("#"+programTreatmentTable+" th"));
			int sizeTreatmentTable = treatmentColumnNamesActual.size();
			for(int i =0; i<sizeTreatmentTable ; i++){
				softAssert.assertEquals(treatmentColumnNamesActual.get(i).getText(), treatmentColumnNamesExpected[i]);
			}

			softAssert.assertEquals(size(By.xpath("//*[@id = '"+programTreatmentTable+"'] //*[contains(@id, '_show-filter')]")), 9, "All filter now displaying filter not displaying for treatment tab");


			click(programVaccineProgramTab);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			List<WebElement> vaccineColumnNamesActual = driver.findElements(By.cssSelector("#"+programVaccineTable+" th"));
			int sizeVaccineTable = vaccineColumnNamesActual.size();
			for(int i =0; i<sizeVaccineTable ; i++){
				softAssert.assertEquals(vaccineColumnNamesActual.get(i).getText(), vaccineColumnNamesExpected[i]);
			}

			softAssert.assertEquals(size(By.xpath("//*[@id = '"+programVaccineTable+"'] //*[contains(@id, '_show-filter')]")), 6, "All filter now displaying filter not displaying in vaccine tab");


			click(programBioshuttleProgramTab);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			List<WebElement> bioshuttleColumnNamesActual = driver.findElements(By.cssSelector("#"+programBioshuttleTable+" th"));
			int sizeBioshuttleTable = vaccineColumnNamesActual.size();
			for(int i =0; i<sizeBioshuttleTable ; i++){
				softAssert.assertEquals(bioshuttleColumnNamesActual.get(i).getText(), bioshuttleColumnNamesExpected[i]);
			}

			softAssert.assertEquals(size(By.xpath("//*[@id = '"+programBioshuttleTable+"'] //*[contains(@id, '_show-filter')]")), 9, "All filter now displaying filter not displaying in bioshuttle tab");


			click(programProgramUtilizationTab);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			List<WebElement> utilizationColumnNamesActual = driver.findElements(By.cssSelector("#"+programUtilizationTable+" th"));
			int sizeUtilizationTable = vaccineColumnNamesActual.size();
			for(int i =0; i<sizeUtilizationTable ; i++){
				softAssert.assertEquals(utilizationColumnNamesActual.get(i).getText(), utilizationColumnNamesExpected[i]);
			}

			softAssert.assertEquals(size(By.xpath("//*[@id = '"+programUtilizationTable+"'] //*[contains(@id, '_show-filter')]")), 8, "All filter now displaying filter not displaying in bioshuttle tab");

			softAssert.assertAll();
			test.pass("All columns displayed successfully");
			results.createNode("All columns displayed successfully");
			test.addScreenCaptureFromPath(getScreenshot("Program Management", ProgramManagementReportPath));
			saveResultNew(ITestResult.SUCCESS, ProgramManagementReportPath, null);
		}catch(AssertionError er) {
			test.fail("All columns did not displayed");
			results.createNode("All columns did not displayed");
			saveResultNew(ITestResult.FAILURE, ProgramManagementReportPath, new Exception(er));
		}catch(Exception ex) {
			test.fail("All columns did not displayed");
			results.createNode("All columns did not displayed");
			saveResultNew(ITestResult.FAILURE, ProgramManagementReportPath, ex);
		}
	}



	@Test (enabled= true, priority = 9) 
	public void ComplexFilterTest() throws InterruptedException, IOException {
		lstProgramManagementSearch = ProgramManagementModel.FillData();

		driver.get(Constants.url_programManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		SoftAssert softAssert = new SoftAssert();

		for (ProgramManagementModel objModel : lstProgramManagementSearch) { 	
			try {
				test = extent.createTest("AN-Program: Verify Site Name Filter Functionality");
				results = test.createNode(Scenario.class, Results);

				driver.findElement(By.xpath("//*[text()= '"+objModel.Program+" ']")).click();
				waitElementInvisible(loading_cursor);


				String recordsBefore = driver.findElement(By.cssSelector("#"+objModel.ProgramTable+" #"+ResultsCount)).getText();

				scroll(By.cssSelector("#"+objModel.ProgramTable+" #complex"+""+ShowFilter));
				Thread.sleep(500);
				click(By.cssSelector("#"+objModel.ProgramTable+" #complex"+""+ShowFilter));
				waitElementInvisible(loading_cursor);
				Thread.sleep(700);	

				if (driver.findElements(By.cssSelector("#sort-complex-"+objModel.ButtonPost+" tr")).size() >=2) {

					scroll(By.cssSelector("#sort-complex-"+objModel.ButtonPost+" tr:nth-child(2) label:nth-child(1)"));
					click(By.cssSelector("#sort-complex-"+objModel.ButtonPost+" tr:nth-child(2) label:nth-child(1)"));

					waitElementInvisible(loading_cursor);
					scroll(By.cssSelector("#"+objModel.ProgramTable+" #list-title_apply"));
					click(By.cssSelector("#"+objModel.ProgramTable+" #list-title_apply"));

					waitElementInvisible(loading_cursor);
					Thread.sleep(1000);
					softAssert.assertNotEquals(driver.findElement(By.cssSelector("#"+objModel.ProgramTable+" #"+ResultsCount)).getText(), recordsBefore);


					test.pass("Checkbox selected successfully");
					test.addScreenCaptureFromPath(getScreenshot("Program Management", ProgramManagementReportPath));
					saveResultNew(ITestResult.SUCCESS, SalmonellaReportPath, null);
				}

			}
			catch(AssertionError er) {
				test.fail("Filer lock functionality failed");
				saveResultNew(ITestResult.FAILURE, SalmonellaReportPath, new Exception(er));
			}
			catch(Exception ex) {
				test.fail("Filer lock functionality failed");
				saveResultNew(ITestResult.FAILURE, SalmonellaReportPath, ex);
			}	
		}
	}


	@Test (priority = 10) 
	public void LockFeed() throws InterruptedException, IOException {
		driver.get(url_programManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		driver.findElement(programFeedProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Lock(programFeedTable, "Program Management", ProgramManagementReportPath, 0);
	}


	@Test (priority = 11) 
	public void LockTreatment() throws InterruptedException, IOException {
		driver.findElement(programTreatmentProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Lock(programTreatmentTable, "Program Management", ProgramManagementReportPath, 0);
	}


	@Test (priority = 12) 
	public void LockVaccine() throws InterruptedException, IOException {
		driver.findElement(programVaccineProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Lock(programVaccineTable, "Program Management", ProgramManagementReportPath, 0);
	}


	@Test (priority = 13) 
	public void LockBioshuttle() throws InterruptedException, IOException {
		driver.findElement(programBioshuttleProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Lock(programBioshuttleTable, "Program Management", ProgramManagementReportPath, 0);
	}


	@Test (priority = 14) 
	public void LockUtilization() throws InterruptedException, IOException {
		driver.findElement(programProgramUtilizationTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Lock(programUtilizationTable, "Program Management", ProgramManagementReportPath, 0);
	}


	@Test (priority = 15) 
	public void WildcardFeed() throws InterruptedException, IOException {
		driver.get(url_programManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		driver.findElement(programFeedProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Wildcard1(programFeedTable, "Program Management", ProgramManagementReportPath, 0);
	}


	@Test (priority = 16) 
	public void WildcardTreatment() throws InterruptedException, IOException {
		driver.findElement(programTreatmentProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Wildcard1(programTreatmentTable, "Program Management", ProgramManagementReportPath, 0);
	}


	@Test (priority = 17) 
	public void WildcardVaccine() throws InterruptedException, IOException {
		driver.findElement(programVaccineProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Wildcard1(programVaccineTable, "Program Management", ProgramManagementReportPath, 0);
	}


	@Test (priority = 18) 
	public void WildcardBioshuttle() throws InterruptedException, IOException {
		driver.findElement(programBioshuttleProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Wildcard1(programBioshuttleTable, "Program Management", ProgramManagementReportPath, 0);
	}


	@Test (priority = 19) 
	public void WildcardUtilization() throws InterruptedException, IOException {
		driver.findElement(programProgramUtilizationTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Wildcard1(programUtilizationTable, "Program Management", ProgramManagementReportPath, 0);
	}


	@Test(priority= 20)
	public void sortingFeed() throws InterruptedException, IOException {
		driver.get(url_programManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		driver.findElement(programFeedProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Sorting1(programFeedTable, "Program Management", ProgramManagementReportPath, 0);
	}


	@Test(priority= 21)
	public void sortingTreatment() throws InterruptedException, IOException {
		driver.findElement(programTreatmentProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Sorting1(programTreatmentTable, "Program Management", ProgramManagementReportPath, 0);
	}


	@Test(priority= 22)
	public void sortingVaccine() throws InterruptedException, IOException {
		driver.findElement(programVaccineProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Sorting1(programVaccineTable, "Program Management", ProgramManagementReportPath, 0);
	}


	@Test(priority= 23)
	public void sortingBioshuttle() throws InterruptedException, IOException {
		driver.findElement(programBioshuttleProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Sorting1(programBioshuttleTable, "Program Management", ProgramManagementReportPath, 0);
	}


	@Test(priority= 24)
	public void sortingUtilization() throws InterruptedException, IOException {
		driver.findElement(programProgramUtilizationTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Sorting1(programUtilizationTable, "Program Management", ProgramManagementReportPath, 0);
	}


	@Test(priority= 25, enabled = true)
	public void RowsPerPageFeed() throws InterruptedException, IOException {
		driver.get(url_programManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		driver.findElement(programFeedProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		RowsPerPage(programFeedTable);
	}


	@Test(priority= 26, enabled = true)
	public void RowsPerPageTreatment() throws InterruptedException, IOException {
		driver.findElement(programTreatmentProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		RowsPerPage(programTreatmentTable);
	}


	@Test(priority= 27, enabled = true)
	public void RowsPerPageVaccine() throws InterruptedException, IOException {
		driver.findElement(programVaccineProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		RowsPerPage(programVaccineTable);
	}


	@Test(priority= 28, enabled = true)
	public void RowsPerPageBioshuttle() throws InterruptedException, IOException {
		driver.findElement(programBioshuttleProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		RowsPerPage(programBioshuttleTable);
	}


	@Test(priority= 29, enabled = true)
	public void RowsPerPageUtilization() throws InterruptedException, IOException {
		driver.findElement(programProgramUtilizationTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		RowsPerPage(programUtilizationTable);
	}


	@Test (priority = 30) 
	public void PaginationFeed() throws InterruptedException, IOException {
		driver.get(url_programManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		driver.findElement(programFeedProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Pagination(programFeedTable, "Program Management", ProgramManagementReportPath);
	}

	@Test (priority = 31) 
	public void PaginationTreatment() throws InterruptedException, IOException {
		driver.findElement(programTreatmentProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Pagination(programTreatmentTable, "Program Management", ProgramManagementReportPath);
	}

	@Test (priority = 32) 
	public void PaginationVaccine() throws InterruptedException, IOException {
		driver.findElement(programVaccineProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Pagination(programVaccineTable, "Program Management", ProgramManagementReportPath);
	}

	@Test (priority = 33) 
	public void PaginationBioshuttle() throws InterruptedException, IOException {
		driver.findElement(programBioshuttleProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Pagination(programBioshuttleTable, "Program Management", ProgramManagementReportPath);
	}

	@Test (priority = 34) 
	public void PaginationUtilization() throws InterruptedException, IOException {
		driver.findElement(programProgramUtilizationTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Pagination(programUtilizationTable, "Program Management", ProgramManagementReportPath);
	}


	@Test(priority= 35)
	public void ExportCSVFeed() throws InterruptedException, IOException {
		driver.get(url_programManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		driver.findElement(programFeedProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		CSVExport("Program Management", ProgramManagementReportPath, programFeedCSVFileName, programFeedTable, 1);
	}


	@Test(priority= 36)
	public void ExportCSVTreatment() throws InterruptedException, IOException {
		driver.findElement(programTreatmentProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		CSVExport("Program Management", ProgramManagementReportPath, programTreatmentCSVFileName, programTreatmentTable, 1);
	}

	@Test(priority= 37)
	public void ExportCSVVaccine() throws InterruptedException, IOException {
		driver.findElement(programVaccineProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		CSVExport("Program Management", ProgramManagementReportPath, programVaccineCSVFileName, programVaccineTable, 1);
	}

	@Test(priority= 38)
	public void ExportCSVBioshuttle() throws InterruptedException, IOException {
		driver.findElement(programBioshuttleProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		CSVExport("Program Management", ProgramManagementReportPath, programBioshuttleCSVFileName, programBioshuttleTable, 1);
	}

	@Test(priority= 39)
	public void ExportCSVUtilization() throws InterruptedException, IOException {
		driver.findElement(programProgramUtilizationTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		CSVExport("Program Management", ProgramManagementReportPath, programUtilizationCSVFileName, programUtilizationTable, 1);
	}

	@AfterTest
	public static void endreport() {
		extent.flush();
	//	driver.close();
	}
}
