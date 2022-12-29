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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Config.BaseTest;
import MiscFunctions.Constants;
import MiscFunctions.DB_Config;
import MiscFunctions.Methods;
import MiscFunctions.Queries;
import Models.ProgramManagementModel;

import static PageObjects.ProgramManagementPage.*;
import static PageObjects.BasePage.*;
import static LogViewFunctions.FilterLock.*;
import static LogViewFunctions.FilterWildcard.*;
import static LogViewFunctions.FilterSort.*;
import static LogViewFunctions.Pagination.*;
import static LogViewFunctions.RowsPerPage.*;
import static MiscFunctions.Constants.*;
import static MiscFunctions.DateUtil.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import static LogViewFunctions.LogCSVExport.*;
import static Models.ProgramManagementModel.*;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class ProgramManagement extends BaseTest {


	@BeforeTest 
	public void extent() throws InterruptedException, IOException {
		spark = new ExtentSparkReporter("target/Reports/Administration_Program_Management"+date+".html");
		spark.config().setReportName("Program Management Test Report"); 
		DB_Config.test();
	}


	//	@Test(priority= 1)
	//	public void Navigate() throws InterruptedException, IOException {
	//		NavigateToScreen(url_programManagement, "Program Management",programManagementTitle);
	//	}	


	@Test (enabled= true, priority= 2) 
	public void CreatePrograms() throws InterruptedException, IOException, SQLException {
		lstProgramManagementSearch = ProgramManagementModel.FillData();

		getDriver().get(Constants.url_programManagement);
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


				getDriver().findElement(By.xpath("//*[text()= 'Vaccine Programs ']")).click();
				waitElementInvisible(loading_cursor);
				getDriver().findElement(By.xpath("//*[text()=' Register New Program']")).click();
				waitElementInvisible(loading_cursor);

				//Program Name
				softAssert.assertEquals(getDriver().findElements(By.cssSelector("#btn-save.disabled-v2")).size(), 2, "Mandatory check failed");
				getDriver().findElement(programName).sendKeys(objModel.ProgramName);
				softAssert.assertEquals(getDriver().findElements(By.cssSelector("#btn-save.disabled-v2")).size(), 2, "Mandatory check failed");

				//Target Pathogen
				getDriver().findElement(programTargetPathogen).click();
				Thread.sleep(500);
				softAssert.assertEquals(getDriver().findElement(By.cssSelector(".ng-option-label")).getText(), "Coccidia");
				getDriver().findElement(programTargetPathogen).sendKeys(Keys.ENTER);
				Thread.sleep(500);
				softAssert.assertEquals(getDriver().findElements(By.cssSelector("#btn-save.disabled-v2")).size(), 2, "Mandatory check failed");

				//Program Type
				getDriver().findElement(programProgramType).sendKeys(objModel.ProgramType);
				Thread.sleep(500);	
				softAssert.assertEquals(getDriver().findElements(By.cssSelector("#btn-save.disabled-v2")).size(), 2, "Mandatory check failed");
				getDriver().findElement(programProgramType).sendKeys(Keys.ENTER);

				//Supplier
				if (!objModel.ProgramType.equals("Feed")) {    //creating feed program without supplier
					getDriver().findElement(programSupplier).sendKeys(ProgramManagementModel.SupplierName);
					Thread.sleep(500);
					if (getDriver().findElements(By.xpath("//*[text()='Add New + ']")).size() != 0) {
						getDriver().findElement(By.xpath("//*[text()='Add New + ']")).click();
					}
					else {
						getDriver().findElement(By.cssSelector(".list-item")).click();		
					}
					Thread.sleep(500);
				}

				//Complex
				//		softAssert.assertEquals(size(programComplexMandatoryCheck), 1, "Complex should be mandatory field");			
				click(programComplexList);
				if (Constants.config.url().contains("qa") || Constants.config.url().contains("dev")) {
					//	getDriver().findElement(programComplexSearch).sendKeys(ProgramManagementModel.ComplexNameQA);
					ResultSet getComplexNameResults = DB_Config.getStmt().executeQuery(Queries.getComplexName);
					while (getComplexNameResults.next()) {
						String complexName = getComplexNameResults.getString("siteName");
						System.out.println("Complex Name: "+complexName);
						type(programComplexSearch, complexName);
					}
				}
				if (Constants.config.url().contains("uat")) {
					getDriver().findElement(programComplexSearch).sendKeys(ProgramManagementModel.ComplexNameUAT);
				}
	
				Thread.sleep(1500);
				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);
				click(clickSearchItemFromHierarchy);

				//Start Date
				click(programStartDateIcon);
				waitElementInvisible(loading_cursor);
				Thread.sleep(500);
				Methods method = new Methods();
				WebElement dateWidgetTo = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#startDate .dp-popup"))).get(0);
				List<WebElement> columns1 = dateWidgetTo.findElements(By.tagName("button"));
				clickGivenDay(columns1, getFirstDay());
				Thread.sleep(500);

				//End Date
				getDriver().findElement(By.cssSelector("#endDate img")).click();
				waitElementInvisible(loading_cursor);
				Thread.sleep(500);
				WebElement dateWidgetToEnd = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#endDate .dp-popup"))).get(0);
				List<WebElement> columns2 = dateWidgetToEnd.findElements(By.tagName("button"));
				clickGivenDay(columns2, getDay("30"));
				Thread.sleep(700);

				//Program Description
				getDriver().findElement(programDescription).sendKeys(ProgramManagementModel.DescriptionName);

				//Vaccine Number of Applications on Flock
				if (objModel.ProgramType.startsWith("Vaccine")) {
					getDriver().findElement(programNoApplicationFlock).sendKeys("64");
					softAssert.assertEquals(getDriver().findElements(By.cssSelector("#numOfApplicationId-error-container svg")).size(), 1, "Mandatory check failed on No of Application Flock");
					Thread.sleep(500);
					clear(programNoApplicationFlock);

					String NoApplicationFlock = "2";
					getDriver().findElement(programNoApplicationFlock).sendKeys(NoApplicationFlock);
					Thread.sleep(500);

					for(int i=1; i<=Integer.parseInt(NoApplicationFlock); i++) {
						getDriver().findElement(By.id(programDaysApplicationFlock+"-"+i)).sendKeys(""+i);
					}
				}

				//Feed Details
				if (objModel.ProgramType.equals("Feed")) {
					getDriver().findElement(programFeedTypeDropdown).click();
					Thread.sleep(500);	
					getDriver().findElement(programFeedTypeDropdown).sendKeys(Keys.ENTER);

					getDriver().findElement(programFlockDayStart).sendKeys("1");

					WebElement EndDay = getDriver().findElement(programFlockDayStart);
					getDriver().findElement(with(By.tagName("input")).toRightOf(EndDay)).sendKeys("10");

					WebElement ingredient = getDriver().findElement(programFeedTypeDropdown);
					getDriver().findElement(with(By.tagName("input")).below(ingredient)).sendKeys("Sugar");

					WebElement ingredientCategory = getDriver().findElement(programFlockDayStart);
					getDriver().findElement(with(By.tagName("input")).below(ingredientCategory)).click();
					List<WebElement> ingredientCategories = getDriver().findElements(By.cssSelector(".ng-option-label"));
					softAssert.assertEquals(ingredientCategories.get(0).getText(), "Antibiotic");
					softAssert.assertEquals(ingredientCategories.get(1).getText(), "Coccidia Stat");
					softAssert.assertEquals(ingredientCategories.get(2).getText(), "Natural");
					ingredientCategories.get(0).click();
					getDriver().findElement(By.xpath(("//*[text()='Add Ingredient']"))).click();
				}

				//Treatment Details
				if(objModel.ProgramType.equals("Treatment")) {
					getDriver().findElement(By.xpath("//div[2]/div[1]/div[1]/app-anc-input-box/div/input[1]")).sendKeys("Treatment Program");

				}

				//Bioshuttle Details
				if(objModel.ProgramType.equals("Bioshuttle")) {
					getDriver().findElement(By.xpath("//div[2]/div[1]/app-anc-input-box/div/input[1]")).sendKeys("BioShuttle Vaccine");	//bioshuttle name field

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

				getScreenshot();
				getDriver().findElement(By.xpath(("//button[text() = ' Submit ']"))).click();

				waitElementInvisible(loading_cursor);
				Thread.sleep(1500);
				softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New program has been created successfully"); 

				//Feed Verification
				if (objModel.ProgramType.equals("Feed")) {
					waitElementVisible(By.cssSelector("tr:nth-child(1) "+programFeedProgramNameCol));
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programFeedProgramNameCol)).getText(), ProgramManagementModel.FeedProgramName);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programFeedSupplierNameCol)).getText(), "");
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programFeedDescriptionCol)).getText(), ProgramManagementModel.DescriptionName);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programFeedFeedTypesCol)).getText(), "Feed Type 1");
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programFeedStartDateCol)).getText(), dateMM+"/01/"+dateYYYY);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programFeedEndDateCol)).getText(), dateMM+"/30/"+dateYYYY);			
				}

				//Treatment Verification
				if (objModel.ProgramType.equals("Treatment")) {
					waitElementVisible(By.cssSelector("tr:nth-child(1) "+programTreatmentProgramNameCol));
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentProgramNameCol)).getText(), ProgramManagementModel.TreatmentProgramName);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentDescriptionCol)).getText(), ProgramManagementModel.DescriptionName);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentStartDateCol)).getText(), dateMM+"/01/"+dateYYYY);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentEndDateCol)).getText(), dateMM+"/30/"+dateYYYY);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentNameCol)).getText(), "Treatment Program");			
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentFlockDayStartCol)).getText(), "");			
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentFlockDayEndCol)).getText(), "");			
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentRouteCol)).getText(), "");
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentTreatmentDescriptionCol)).getText(), "");					
				}

				//Vaccine Verification
				if (objModel.ProgramType.equals("Vaccine")) {
					waitElementVisible(By.cssSelector("tr:nth-child(1) "+programVaccineProgramNameCol));
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programVaccineProgramNameCol)).getText(), ProgramManagementModel.VaccineProgramName);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programVaccineSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programVaccineNumberOfApplicationFlockCol)).getText(), "2");
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programVaccineDescriptionCol)).getText(), ProgramManagementModel.DescriptionName);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programVaccineStartDateCol)).getText(), dateMM+"/01/"+dateYYYY);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programVaccineEndDateCol)).getText(), dateMM+"/30/"+dateYYYY);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programVaccineFlockDayApplicationCol)).getText(), "1,2");								
				}

				//Vaccine Bioshuttle Verification
				if (objModel.ProgramType.equals("Bioshuttle")) {
					waitElementVisible(By.cssSelector("tr:nth-child(1) "+programBioshuttleProgramNameCol));
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleProgramNameCol)).getText(), ProgramManagementModel.BioshuttleProgramName);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleNumberOfApplicationFlockCol)).getText(), "2");
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleDescriptionCol)).getText(), ProgramManagementModel.DescriptionName);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleStartDateCol)).getText(), dateMM+"/01/"+dateYYYY);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleEndDateCol)).getText(), dateMM+"/30/"+dateYYYY);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleFlockDayApplicationCol)).getText(), "1,2");	
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleFlockDayStartCol)).getText(), "1");	
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleFlockDayEndCol)).getText(), "10");	
				}

				softAssert.assertAll();
				test.pass("New Program created successfully");
				results.createNode("New Program created successfully");
				getScreenshot();
				saveResult(ITestResult.SUCCESS, null);
			}
			catch(AssertionError er) {
				test.fail("New Program failed to create");
				results.createNode("New Program failed to create");
				saveResult(ITestResult.FAILURE, new Exception(er));
			}catch(Exception ex) {
				test.fail("New Program failed to create");
				results.createNode("New Program failed to create");
				saveResult(ITestResult.FAILURE, ex);
			}
		}
		DB_Config.getStmt().close();
	}


	@Test (enabled= true, priority= 3) 
	public void UpdatePrograms() throws InterruptedException, IOException {
		lstProgramManagementSearch = ProgramManagementModel.FillData();

		getDriver().get(Constants.url_programManagement);
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

				getDriver().findElement(By.xpath("//*[text()= '"+objModel.Program+" ']")).click();
				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);

				List<WebElement> programsName = getDriver().findElements(By.cssSelector(objModel.ProgramName_CSS));

				for (int i = 1; i <= programsName.size(); i++) {
					if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") "+objModel.ProgramName_CSS)).getText().equals(objModel.ProgramName)) {
						scroll(By.xpath("//*[@id='"+objModel.ProgramTable+"'] //*[text()='Action']"));
						waitElementClickable(By.id(objModel.EditButtonPre+""+i+"-"+objModel.ButtonPost));
						Thread.sleep(1000);
						getDriver().findElement(By.id(objModel.EditButtonPre+""+i+"-"+objModel.ButtonPost)).click();		
						break;
					}
					else {
						Assert.assertTrue(false);
					}
				}

				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);
				//Program Description
				getDriver().findElement(programDescription).sendKeys(" Updated");
				Thread.sleep(500);	
				getScreenshot();
				click(popupSaveButton);

				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);

				softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Program details updated"); 

				//Feed Verification
				if (objModel.ProgramType.equals("Feed")) {
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programFeedProgramNameCol)).getText(), ProgramManagementModel.FeedProgramName);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programFeedSupplierNameCol)).getText(), "");
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programFeedDescriptionCol)).getText(), ProgramManagementModel.DescriptionName+" Updated");
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programFeedFeedTypesCol)).getText(), "Feed Type 1");
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programFeedStartDateCol)).getText(), dateMM+"/01/"+dateYYYY);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programFeedEndDateCol)).getText(), dateMM+"/30/"+dateYYYY);			
				}

				//Treatment Verification
				if (objModel.ProgramType.equals("Treatment")) {
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programTreatmentProgramNameCol)).getText(), ProgramManagementModel.TreatmentProgramName);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programTreatmentSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programTreatmentDescriptionCol)).getText(), ProgramManagementModel.DescriptionName+" Updated");
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programTreatmentStartDateCol)).getText(), dateMM+"/01/"+dateYYYY);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programTreatmentEndDateCol)).getText(), dateMM+"/30/"+dateYYYY);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programTreatmentNameCol)).getText(), "Treatment Program");			
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programTreatmentFlockDayStartCol)).getText(), "");			
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programTreatmentFlockDayEndCol)).getText(), "");			
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programTreatmentRouteCol)).getText(), "");
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programTreatmentTreatmentDescriptionCol)).getText(), "");					
				}

				//Vaccine Verification
				if (objModel.ProgramType.equals("Vaccine")) {
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programVaccineProgramNameCol)).getText(), ProgramManagementModel.VaccineProgramName);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programVaccineSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programVaccineNumberOfApplicationFlockCol)).getText(), "2");
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programVaccineDescriptionCol)).getText(), ProgramManagementModel.DescriptionName+" Updated");
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programVaccineStartDateCol)).getText(), dateMM+"/01/"+dateYYYY);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programVaccineEndDateCol)).getText(), dateMM+"/30/"+dateYYYY);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programVaccineFlockDayApplicationCol)).getText(), "1,2");								
				}

				//Vaccine Bioshuttle Verification
				if (objModel.ProgramType.equals("Bioshuttle")) {
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programBioshuttleProgramNameCol)).getText(), ProgramManagementModel.BioshuttleProgramName);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programBioshuttleSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programBioshuttleNumberOfApplicationFlockCol)).getText(), "2");
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programBioshuttleDescriptionCol)).getText(), ProgramManagementModel.DescriptionName+" Updated");
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programBioshuttleStartDateCol)).getText(), dateMM+"/01/"+dateYYYY);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programBioshuttleEndDateCol)).getText(), dateMM+"/30/"+dateYYYY);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programBioshuttleFlockDayApplicationCol)).getText(), "1,2");	
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programBioshuttleFlockDayStartCol)).getText(), "1");	
					softAssert.assertEquals(getDriver().findElement(By.cssSelector(programBioshuttleFlockDayEndCol)).getText(), "10");	
				}
				softAssert.assertAll();
				test.pass("New Program updated successfully");
				results.createNode("New Program updated successfully");
				getScreenshot();
				saveResult(ITestResult.SUCCESS,  null);
			}
			catch(AssertionError er) {
				test.fail("New Program failed to update");
				results.createNode("New Program failed to update");
				saveResult(ITestResult.FAILURE, new Exception(er));
			}catch(Exception ex) {
				test.fail("New Program failed to update");
				results.createNode("New Program failed to update");
				saveResult(ITestResult.FAILURE, ex);
			}
		}
	}



	@Test (enabled= true, priority= 4) 
	public void DuplicatePrograms() throws InterruptedException, IOException {
		lstProgramManagementSearch = ProgramManagementModel.FillData();

		getDriver().get(Constants.url_programManagement);
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

				getDriver().findElement(By.xpath("//*[text()= '"+objModel.Program+" ']")).click();
				waitElementInvisible(loading_cursor);

				List<WebElement> programsName = getDriver().findElements(By.cssSelector(objModel.ProgramName_CSS));

				for (int i = 1; i <= programsName.size(); i++) {
					if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") "+objModel.ProgramName_CSS)).getText().equals(objModel.ProgramName)) {
						System.out.println(1);
						scroll(By.xpath("//*[@id='"+objModel.ProgramTable+"'] //*[text()='Action']"));
						waitElementClickable(By.id(objModel.EditButtonPre+""+i+"-"+objModel.ButtonPost));
						Thread.sleep(1000);
						//	getDriver().findElement(By.id(objModel.CopyButtonPre+""+i+"-"+objModel.ButtonPost)).click();		
						getDriver().findElement(By.xpath("//*[@id = '"+objModel.CopyButtonPre+""+i+"-"+objModel.ButtonPost+"']")).click();		

						break;        
					}
					else {
						Assert.assertTrue(false, "Program not found");
					}
				}

				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);

				type(programName, objModel.ProgramName+"_Copy");
				softAssert.assertEquals(getDriver().findElement(programTargetPathogenValue).getText(), "Coccidia", "Target Pathogen is empty ");
				softAssert.assertEquals(getDriver().findElement(programProgramTypeValue).getText(), objModel.ProgramType, " Program Type is empty ");
				softAssert.assertEquals(size(programProgramTypeDisabledCheck), 1, "Program Type is not disabled");


				//Supplier
				if (!objModel.ProgramType.equals("Feed")) { 
					softAssert.assertEquals(getDriver().findElement(programSupplier).getAttribute("value"), ProgramManagementModel.SupplierName, "Supplier name is empty ");
				}

				//	softAssert.assertEquals(size(programComplexMandatoryCheck), 1, "Complex should be mandatory field");			
				softAssert.assertEquals(size(programComplexSelected), 1);
				softAssert.assertNotEquals(getDriver().findElement(By.id("startDate")).getAttribute("value"), "", "Start Date is empty");
				softAssert.assertNotEquals(getDriver().findElement(By.id("endDate")).getAttribute("value"), "", "End Date is empty");
				softAssert.assertNotEquals(getDriver().findElement(programDescription).getAttribute("value"), "", "Description is empty");
				clear(programDescription);				
				type(programDescription, ProgramManagementModel.DescriptionName+"_Copy");

				//Vaccine Number of Applications on Flock
				if (objModel.ProgramType.startsWith("Vaccine")) {
					String NoApplicationFlock = "2";
					softAssert.assertEquals(getDriver().findElement(programNoApplicationFlock).getAttribute("value"), NoApplicationFlock, "No of Application Flock empty");

					for(int i=1; i<=Integer.parseInt(NoApplicationFlock); i++) {
						softAssert.assertEquals(Integer.parseInt(getDriver().findElement(By.id(programDaysApplicationFlock+"-"+i)).getAttribute("value")), i, "No of Application Flock Days empty");
					}
				}

				//Feed Details
				if (objModel.ProgramType.equals("Feed")) {
					softAssert.assertEquals(getDriver().findElement(programFeedTypeValue).getText(), "Feed Type 1");
					//					softAssert.assertNotEquals(getDriver().findElement(programFlockDayStart).getAttribute("value"), "");

					WebElement EndDay = getDriver().findElement(programFlockDayStart);
					softAssert.assertNotEquals(getDriver().findElement(with(By.tagName("input")).toRightOf(EndDay)).getAttribute("value"), "");

					//					WebElement ingredient = getDriver().findElement(programFeedTypeDropdown);
					//					softAssert.assertEquals(getDriver().findElement(with(By.tagName("input")).below(ingredient)).getAttribute("value"), "Sugar");
				}

				//Treatment Details
				if(objModel.ProgramType.equals("Treatment")) {
					softAssert.assertEquals(getDriver().findElement(By.xpath("//div[2]/div[1]/div[1]/app-anc-input-box/div/input[1]")).getAttribute("value"), "Treatment Program");
				}

				//Bioshuttle Details
				if(objModel.ProgramType.equals("Bioshuttle")) {
					softAssert.assertEquals(getDriver().findElement(By.xpath("//div[2]/div[1]/app-anc-input-box/div/input[1]")).getAttribute("value"), "BioShuttle Vaccine");
				}

				getScreenshot();
				getDriver().findElement(By.xpath(("//button[text() = ' Submit ']"))).click();

				waitElementInvisible(loading_cursor);
				Thread.sleep(1000);

				softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "New program has been created successfully"); 

				//Feed Verification
				if (objModel.ProgramType.equals("Feed")) {
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programFeedProgramNameCol)).getText(), ProgramManagementModel.FeedProgramName+"_Copy");
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programFeedSupplierNameCol)).getText(), "");
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programFeedDescriptionCol)).getText(), ProgramManagementModel.DescriptionName+"_Copy");
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programFeedFeedTypesCol)).getText(), "Feed Type 1");
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programFeedStartDateCol)).getText(), dateMM+"/01/"+dateYYYY);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programFeedEndDateCol)).getText(), dateMM+"/30/"+dateYYYY);			
				}

				//Treatment Verification
				if (objModel.ProgramType.equals("Treatment")) {
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentProgramNameCol)).getText(), ProgramManagementModel.TreatmentProgramName+"_Copy");
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentDescriptionCol)).getText(), ProgramManagementModel.DescriptionName+"_Copy");
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentStartDateCol)).getText(), dateMM+"/01/"+dateYYYY);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentEndDateCol)).getText(), dateMM+"/30/"+dateYYYY);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentNameCol)).getText(), "Treatment Program");			
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentFlockDayStartCol)).getText(), "");			
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentFlockDayEndCol)).getText(), "");			
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentRouteCol)).getText(), "");
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programTreatmentTreatmentDescriptionCol)).getText(), "");					
				}

				//Vaccine Verification
				if (objModel.ProgramType.equals("Vaccine")) {
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programVaccineProgramNameCol)).getText(), ProgramManagementModel.VaccineProgramName+"_Copy");
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programVaccineSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programVaccineNumberOfApplicationFlockCol)).getText(), "2");
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programVaccineDescriptionCol)).getText(), ProgramManagementModel.DescriptionName+"_Copy");
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programVaccineStartDateCol)).getText(), dateMM+"/01/"+dateYYYY);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programVaccineEndDateCol)).getText(), dateMM+"/30/"+dateYYYY);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programVaccineFlockDayApplicationCol)).getText(), "1,2");								
				}

				//Vaccine Bioshuttle Verification
				if (objModel.ProgramType.equals("Bioshuttle")) {
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleProgramNameCol)).getText(), ProgramManagementModel.BioshuttleProgramName+"_Copy");
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleNumberOfApplicationFlockCol)).getText(), "2");
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleDescriptionCol)).getText(), ProgramManagementModel.DescriptionName+"_Copy");
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleStartDateCol)).getText(), dateMM+"/01/"+dateYYYY);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleEndDateCol)).getText(), dateMM+"/30/"+dateYYYY);
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleFlockDayApplicationCol)).getText(), "1,2");	
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleFlockDayStartCol)).getText(), "1");	
					softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) "+programBioshuttleFlockDayEndCol)).getText(), "10");	
				}

				softAssert.assertAll();
				test.pass("New Program copied successfully");
				results.createNode("New Program copied successfully");
				getScreenshot();
				saveResult(ITestResult.SUCCESS, null);
			}
			catch(AssertionError er) {
				test.fail("New Program failed to copy");
				results.createNode("New Program failed to copy");
				saveResult(ITestResult.FAILURE, new Exception(er));
			}catch(Exception ex) {
				test.fail("New Program failed to copy");
				results.createNode("New Program failed to copy");
				saveResult(ITestResult.FAILURE, ex);
			}
		}
	}


	@Test (enabled= true, priority= 5, dependsOnMethods = {"CreatePrograms"}) 
	public void DeleteDuplicatePrograms() throws InterruptedException, IOException {
		lstProgramManagementSearch = ProgramManagementModel.FillData();

		getDriver().get(Constants.url_programManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);

		for (ProgramManagementModel objModel : lstProgramManagementSearch) { 	
			try {
				test = extent.createTest(objModel.TestCaseNameDelete);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				steps.createNode("1. Delete the created program");

				SoftAssert softAssert = new SoftAssert();
				getDriver().findElement(By.xpath("//*[text()= '"+objModel.Program+" ']")).click();
				waitElementInvisible(loading_cursor);

				List<WebElement> programsName = getDriver().findElements(By.cssSelector(objModel.ProgramName_CSS));

				for (int i = 1; i <= programsName.size(); i++) {
					if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") "+objModel.ProgramName_CSS)).getText().equals(objModel.ProgramName+"_Copy")) {
						System.out.println("1");
						scroll(By.xpath("//*[@id='"+objModel.ProgramTable+"'] //*[text()='Action']"));
						waitElementClickable(By.id(objModel.DeleteButtonPre+""+i+"-"+objModel.ButtonPost));
						Thread.sleep(1000);
						getDriver().findElement(By.id(objModel.DeleteButtonPre+""+i+"-"+objModel.ButtonPost)).click();		
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

				softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Program details deleted"); 			
				softAssert.assertAll();
				test.pass("New Program deleted successfully");
				results.createNode("New Program deleted successfully");
				getScreenshot();
				saveResult(ITestResult.SUCCESS, null);
			}
			catch(AssertionError er) {
				test.fail("New Program failed to delete");
				results.createNode("New Program failed to delete");
				saveResult(ITestResult.FAILURE, new Exception(er));
			}catch(Exception ex) {
				test.fail("New Program failed to delete");
				results.createNode("New Program failed to delete");
				saveResult(ITestResult.FAILURE, ex);
			}
		}
	}

	@Test (enabled= true, priority= 5, dependsOnMethods = {"CreatePrograms"}) 
	public void DeletePrograms() throws InterruptedException, IOException {
		lstProgramManagementSearch = ProgramManagementModel.FillData();

		getDriver().get(Constants.url_programManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);

		for (ProgramManagementModel objModel : lstProgramManagementSearch) { 	
			try {
				test = extent.createTest(objModel.TestCaseNameDelete);
				steps = test.createNode(Scenario.class, Steps);
				results = test.createNode(Scenario.class, Results);

				steps.createNode("1. Delete the created program");

				SoftAssert softAssert = new SoftAssert();
				getDriver().findElement(By.xpath("//*[text()= '"+objModel.Program+" ']")).click();
				waitElementInvisible(loading_cursor);

				List<WebElement> programsName = getDriver().findElements(By.cssSelector(objModel.ProgramName_CSS));

				for (int i = 1; i <= programsName.size(); i++) {
					if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") "+objModel.ProgramName_CSS)).getText().equals(objModel.ProgramName)) {
						System.out.println("1");
						scroll(By.xpath("//*[@id='"+objModel.ProgramTable+"'] //*[text()='Action']"));
						waitElementClickable(By.id(objModel.DeleteButtonPre+""+i+"-"+objModel.ButtonPost));
						Thread.sleep(1000);
						getDriver().findElement(By.id(objModel.DeleteButtonPre+""+i+"-"+objModel.ButtonPost)).click();		
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

				softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Program details deleted"); 			
				softAssert.assertAll();
				test.pass("New Program deleted successfully");
				results.createNode("New Program deleted successfully");
				getScreenshot();
				saveResult(ITestResult.SUCCESS, null);
			}
			catch(AssertionError er) {
				test.fail("New Program failed to delete");
				results.createNode("New Program failed to delete");
				saveResult(ITestResult.FAILURE, new Exception(er));
			}catch(Exception ex) {
				test.fail("New Program failed to delete");
				results.createNode("New Program failed to delete");
				saveResult(ITestResult.FAILURE, ex);
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

			getDriver().get(Constants.url_programManagement);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			String[] feedColumnNamesExpected = {"Program Name", "Supplier Name", "Description", "Feed Types", "Start Date", "End Date", "Complex", "Action"};
			String[] treatmentColumnNamesExpected = {"Program Name", "Supplier Name", "Description", "Start Date", "End Date", "Treatment Name", "Flock Day Start", "Flock Day End", "Route", "Treatment Description", "Complex", "Action"};
			String[] vaccineColumnNamesExpected = {"Program Name", "Supplier Name", "No. Of Applications On Flock", "Description", "Start Date", "End Date", "Flock Day Applications", "Complex", "Action"};
			String[] bioshuttleColumnNamesExpected = {"Program Name", "Supplier Name", "No. Of Applications On Flock", "Description", "Start Date", "End Date", "Flock Day Applications", "Bioshuttle Name", "Bioshuttle Flock Day Start", "Bioshuttle Flock Day End", "Complex", "Action"};
			String[] utilizationColumnNamesExpected = {"Flock ID", "Integrator Flock ID", "Program Type", "Program Name", "Complex", "Farm", "House Placement", "Start Date", "End Date"};


			List<WebElement> feedColumnNamesActual = getDriver().findElements(By.cssSelector("#"+programFeedTable+" th"));
			int sizeFeedTable = feedColumnNamesActual.size();

			for(int i =0; i<sizeFeedTable ; i++){
				softAssert.assertEquals(feedColumnNamesActual.get(i).getText(), feedColumnNamesExpected[i]);
			}

			softAssert.assertEquals(size(By.xpath("//*[@id = '"+programFeedTable+"'] //*[contains(@id, '_show-filter')]")), 5, "All filter now displaying filter not displaying for feed tab");


			click(programTreatmentProgramTab);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			List<WebElement> treatmentColumnNamesActual = getDriver().findElements(By.cssSelector("#"+programTreatmentTable+" th"));
			int sizeTreatmentTable = treatmentColumnNamesActual.size();
			for(int i =0; i<sizeTreatmentTable ; i++){
				softAssert.assertEquals(treatmentColumnNamesActual.get(i).getText(), treatmentColumnNamesExpected[i]);
			}

			softAssert.assertEquals(size(By.xpath("//*[@id = '"+programTreatmentTable+"'] //*[contains(@id, '_show-filter')]")), 9, "All filter now displaying filter not displaying for treatment tab");


			click(programVaccineProgramTab);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			List<WebElement> vaccineColumnNamesActual = getDriver().findElements(By.cssSelector("#"+programVaccineTable+" th"));
			int sizeVaccineTable = vaccineColumnNamesActual.size();
			for(int i =0; i<sizeVaccineTable ; i++){
				softAssert.assertEquals(vaccineColumnNamesActual.get(i).getText(), vaccineColumnNamesExpected[i]);
			}

			softAssert.assertEquals(size(By.xpath("//*[@id = '"+programVaccineTable+"'] //*[contains(@id, '_show-filter')]")), 6, "All filter now displaying filter not displaying in vaccine tab");


			click(programBioshuttleProgramTab);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			List<WebElement> bioshuttleColumnNamesActual = getDriver().findElements(By.cssSelector("#"+programBioshuttleTable+" th"));
			int sizeBioshuttleTable = vaccineColumnNamesActual.size();
			for(int i =0; i<sizeBioshuttleTable ; i++){
				softAssert.assertEquals(bioshuttleColumnNamesActual.get(i).getText(), bioshuttleColumnNamesExpected[i]);
			}

			softAssert.assertEquals(size(By.xpath("//*[@id = '"+programBioshuttleTable+"'] //*[contains(@id, '_show-filter')]")), 9, "All filter now displaying filter not displaying in bioshuttle tab");


			click(programProgramUtilizationTab);
			waitElementInvisible(loading_cursor);
			Thread.sleep(1000);

			List<WebElement> utilizationColumnNamesActual = getDriver().findElements(By.cssSelector("#"+programUtilizationTable+" th"));
			int sizeUtilizationTable = vaccineColumnNamesActual.size();
			for(int i =0; i<sizeUtilizationTable ; i++){
				softAssert.assertEquals(utilizationColumnNamesActual.get(i).getText(), utilizationColumnNamesExpected[i]);
			}

			softAssert.assertEquals(size(By.xpath("//*[@id = '"+programUtilizationTable+"'] //*[contains(@id, '_show-filter')]")), 8, "All filter now displaying filter not displaying in bioshuttle tab");

			softAssert.assertAll();
			test.pass("All columns displayed successfully");
			results.createNode("All columns displayed successfully");
			getScreenshot();
			saveResult(ITestResult.SUCCESS,null);
		}catch(AssertionError er) {
			test.fail("All columns did not displayed");
			results.createNode("All columns did not displayed");
			saveResult(ITestResult.FAILURE,new Exception(er));
		}catch(Exception ex) {
			test.fail("All columns did not displayed");
			results.createNode("All columns did not displayed");
			saveResult(ITestResult.FAILURE,ex);
		}
	}



	@Test (enabled= true, priority = 9) 
	public void ComplexFilterTest() throws InterruptedException, IOException {
		lstProgramManagementSearch = ProgramManagementModel.FillData();

		getDriver().get(Constants.url_programManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		SoftAssert softAssert = new SoftAssert();

		for (ProgramManagementModel objModel : lstProgramManagementSearch) { 	
			try {
				test = extent.createTest("AN-Program: Verify Site Name Filter Functionality");
				results = test.createNode(Scenario.class, Results);

				getDriver().findElement(By.xpath("//*[text()= '"+objModel.Program+" ']")).click();
				waitElementInvisible(loading_cursor);


				String recordsBefore = getDriver().findElement(By.cssSelector("#"+objModel.ProgramTable+" #"+ResultsCount)).getText();

				scroll(By.cssSelector("#"+objModel.ProgramTable+" #complex"+""+ShowFilter));
				Thread.sleep(500);
				click(By.cssSelector("#"+objModel.ProgramTable+" #complex"+""+ShowFilter));
				waitElementInvisible(loading_cursor);
				Thread.sleep(700);	

				if (getDriver().findElements(By.cssSelector("#sort-complex-"+objModel.ButtonPost+" tr")).size() >=2) {

					scroll(By.cssSelector("#sort-complex-"+objModel.ButtonPost+" tr:nth-child(2) label:nth-child(1)"));
					click(By.cssSelector("#sort-complex-"+objModel.ButtonPost+" tr:nth-child(2) label:nth-child(1)"));

					waitElementInvisible(loading_cursor);
					scroll(By.cssSelector("#"+objModel.ProgramTable+" #list-title_apply"));
					click(By.cssSelector("#"+objModel.ProgramTable+" #list-title_apply"));

					waitElementInvisible(loading_cursor);
					Thread.sleep(1000);
					softAssert.assertNotEquals(getDriver().findElement(By.cssSelector("#"+objModel.ProgramTable+" #"+ResultsCount)).getText(), recordsBefore);


					test.pass("Checkbox selected successfully");
					getScreenshot();
					saveResult(ITestResult.SUCCESS,null);
				}

			}
			catch(AssertionError er) {
				test.fail("Filer lock functionality failed");
				saveResult(ITestResult.FAILURE,new Exception(er));
			}
			catch(Exception ex) {
				test.fail("Filer lock functionality failed");
				saveResult(ITestResult.FAILURE,ex);
			}	
		}
	}


	@Test (priority = 10) 
	public void LockFeed() throws InterruptedException, IOException {
		getDriver().get(url_programManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		getDriver().findElement(programFeedProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Lock(programFeedTable, "Program Management",0);
	}


	@Test (priority = 11) 
	public void LockTreatment() throws InterruptedException, IOException {
		getDriver().findElement(programTreatmentProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Lock(programTreatmentTable, "Program Management",0);
	}


	@Test (priority = 12) 
	public void LockVaccine() throws InterruptedException, IOException {
		getDriver().findElement(programVaccineProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Lock(programVaccineTable, "Program Management",0);
	}


	@Test (priority = 13) 
	public void LockBioshuttle() throws InterruptedException, IOException {
		getDriver().findElement(programBioshuttleProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Lock(programBioshuttleTable, "Program Management",0);
	}


	@Test (priority = 14) 
	public void LockUtilization() throws InterruptedException, IOException {
		getDriver().findElement(programProgramUtilizationTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Lock(programUtilizationTable, "Program Management",0);
	}


	@Test (priority = 15) 
	public void WildcardFeed() throws InterruptedException, IOException {
		getDriver().get(url_programManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		getDriver().findElement(programFeedProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Wildcard(programFeedTable, "Program Management",0);
	}


	@Test (priority = 16) 
	public void WildcardTreatment() throws InterruptedException, IOException {
		getDriver().findElement(programTreatmentProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Wildcard(programTreatmentTable, "Program Management",0);
	}


	@Test (priority = 17) 
	public void WildcardVaccine() throws InterruptedException, IOException {
		getDriver().findElement(programVaccineProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Wildcard(programVaccineTable, "Program Management",0);
	}


	@Test (priority = 18) 
	public void WildcardBioshuttle() throws InterruptedException, IOException {
		getDriver().findElement(programBioshuttleProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Wildcard(programBioshuttleTable, "Program Management",0);
	}


	@Test (priority = 19) 
	public void WildcardUtilization() throws InterruptedException, IOException {
		getDriver().findElement(programProgramUtilizationTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Wildcard(programUtilizationTable, "Program Management",0);
	}


	@Test(priority= 20)
	public void sortingFeed() throws InterruptedException, IOException {
		getDriver().get(url_programManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		getDriver().findElement(programFeedProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Sorting(programFeedTable, "Program Management",0);
	}


	@Test(priority= 21)
	public void sortingTreatment() throws InterruptedException, IOException {
		getDriver().findElement(programTreatmentProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Sorting(programTreatmentTable, "Program Management",0);
	}


	@Test(priority= 22)
	public void sortingVaccine() throws InterruptedException, IOException {
		getDriver().findElement(programVaccineProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Sorting(programVaccineTable, "Program Management",0);
	}


	@Test(priority= 23)
	public void sortingBioshuttle() throws InterruptedException, IOException {
		getDriver().findElement(programBioshuttleProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Sorting(programBioshuttleTable, "Program Management",0);
	}


	@Test(priority= 24)
	public void sortingUtilization() throws InterruptedException, IOException {
		getDriver().findElement(programProgramUtilizationTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Sorting(programUtilizationTable, "Program Management",0);
	}


	@Test(priority= 25, enabled = true)
	public void RowsPerPageFeed() throws InterruptedException, IOException {
		getDriver().get(url_programManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		getDriver().findElement(programFeedProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		RowsPerPage1(programFeedTable);
	}


	@Test(priority= 26, enabled = true)
	public void RowsPerPageTreatment() throws InterruptedException, IOException {
		getDriver().findElement(programTreatmentProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		RowsPerPage1(programTreatmentTable);
	}


	@Test(priority= 27, enabled = true)
	public void RowsPerPageVaccine() throws InterruptedException, IOException {
		getDriver().findElement(programVaccineProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		RowsPerPage1(programVaccineTable);
	}


	@Test(priority= 28, enabled = true)
	public void RowsPerPageBioshuttle() throws InterruptedException, IOException {
		getDriver().findElement(programBioshuttleProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		RowsPerPage1(programBioshuttleTable);
	}


	@Test(priority= 29, enabled = true)
	public void RowsPerPageUtilization() throws InterruptedException, IOException {
		getDriver().findElement(programProgramUtilizationTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		RowsPerPage1(programUtilizationTable);
	}


	@Test (priority = 30) 
	public void PaginationFeed() throws InterruptedException, IOException {
		getDriver().get(url_programManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		getDriver().findElement(programFeedProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Pagination(programFeedTable, "Program Management", ReportFilePath);
	}

	@Test (priority = 31) 
	public void PaginationTreatment() throws InterruptedException, IOException {
		getDriver().findElement(programTreatmentProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Pagination(programTreatmentTable, "Program Management", ReportFilePath);
	}

	@Test (priority = 32) 
	public void PaginationVaccine() throws InterruptedException, IOException {
		getDriver().findElement(programVaccineProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Pagination(programVaccineTable, "Program Management", ReportFilePath);
	}

	@Test (priority = 33) 
	public void PaginationBioshuttle() throws InterruptedException, IOException {
		getDriver().findElement(programBioshuttleProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Pagination(programBioshuttleTable, "Program Management", ReportFilePath);
	}

	@Test (priority = 34) 
	public void PaginationUtilization() throws InterruptedException, IOException {
		getDriver().findElement(programProgramUtilizationTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		Pagination(programUtilizationTable, "Program Management", ReportFilePath);
	}


	@Test(priority= 35)
	public void ExportCSVFeed() throws InterruptedException, IOException {
		getDriver().get(url_programManagement);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		getDriver().findElement(programFeedProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		CSVExport("Program Management",programFeedCSVFileName, programFeedTable, 1);
	}


	@Test(priority= 36)
	public void ExportCSVTreatment() throws InterruptedException, IOException {
		getDriver().findElement(programTreatmentProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		CSVExport("Program Management",programTreatmentCSVFileName, programTreatmentTable, 1);
	}

	@Test(priority= 37)
	public void ExportCSVVaccine() throws InterruptedException, IOException {
		getDriver().findElement(programVaccineProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		CSVExport("Program Management",programVaccineCSVFileName, programVaccineTable, 1);
	}

	@Test(priority= 38)
	public void ExportCSVBioshuttle() throws InterruptedException, IOException {
		getDriver().findElement(programBioshuttleProgramTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		CSVExport("Program Management",programBioshuttleCSVFileName, programBioshuttleTable, 1);
	}

	@Test(priority= 39)
	public void ExportCSVUtilization() throws InterruptedException, IOException {
		getDriver().findElement(programProgramUtilizationTab).click();
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		CSVExport("Program Management",programUtilizationCSVFileName, programUtilizationTable, 1);
	}
}
