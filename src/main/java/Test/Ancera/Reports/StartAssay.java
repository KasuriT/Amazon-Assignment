package Test.Ancera.Reports;

import java.io.IOException;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StartAssay {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/Start_Assay"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("Start Assay Test Report"); 

		Helper.config();
		ConfigureLogin.login();
	}
	
	
	@Test (description="Test Case: Start Assay API", enabled= false, priority= 1) 
	public void StartAssayAPI() throws InterruptedException, IOException	{

		Test_Variables.test = Test_Variables.extent.createTest("AN-API_Login-01: Verify Login API", "This test case will run login api and verify that token is generated or not");
		Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
		Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

		Test_Variables.steps.createNode("1. Enter valid piperid ("+Test_Variables.piperId+")");
		Test_Variables.steps.createNode("2. Enter valid password (********)");
		Test_Variables.steps.createNode("3. Run the API");

		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		JSONObject json = new JSONObject();   
		json.put("piperid", Test_Variables.piperId);
		json.put("password", Test_Variables.piperPassword);
		json.put("DISAPIVersion", "14.12.1");
		request.body(json.toString());
		Response response = request.post(Constants.api_login);
		int code = response.getStatusCode();
		Assert.assertEquals(code, 200);

		String data = response.asString();
		System.out.println(data);
		JsonPath jsonPathEvaluator = response.jsonPath();
		String token = jsonPathEvaluator.get("token");		

		String statusCode = jsonPathEvaluator.get("statusCode");
		System.out.println(token);

		try{
			Assert.assertEquals(statusCode, "114"); 
			Test_Variables.test.pass("Login Api ran successfully");
			Test_Variables.results.createNode("Login API ran successfully; token was generated successfully");
			Helper.saveResultNew(ITestResult.SUCCESS, Constants.StartAssayReportPath, null);
		}catch(AssertionError er) {
			Test_Variables.test.fail("Login Api failed");
			Test_Variables.results.createNode("Login API failed to run; token was not generated");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.StartAssayReportPath, new Exception(er));
		}catch(Exception ex){
			Test_Variables.test.fail("Login Api failed");
			Test_Variables.results.createNode("Login API failed to run; token was not generated");
			Helper.saveResultNew(ITestResult.FAILURE, Constants.StartAssayReportPath, ex);
		}	

		

		for(int i=0; i<Test_Variables.lstStartAssay.size(); i++)	{
			try{
				Test_Variables.test = Test_Variables.extent.createTest(Test_Variables.lstStartAssay.get(i).testCaseTitle, Test_Variables.lstStartAssay.get(i).testCaseDesc);	
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("Run login API to generate token");
				Test_Variables.steps.createNode(Test_Variables.lstStartAssay.get(i).step);
				JSONObject json3 = new JSONObject();
				Thread.sleep(2000);
				RequestSpecification request_startAssay = RestAssured.given();

				request_startAssay.header("Content-Type", "application/json");
				request_startAssay.header("Authorization", "bearer " +token);

				HttpGet postRequest1 = new HttpGet(Constants.api_StartAssay);
				postRequest1.addHeader("Content-Type", "application/json");
				postRequest1.addHeader("Authorization", "Bearer "+token);

				json3.put("DateTime", Test_Variables.lstStartAssay.get(i).DateTime);
				json3.put("InstrumentId", Test_Variables.lstStartAssay.get(i).InstrumentID);
				json3.put("UserId", Test_Variables.lstStartAssay.get(i).UserID);
				json3.put("CartridgeId", Test_Variables.lstStartAssay.get(i).CartridgeID);
				json3.put("RunId", Test_Variables.lstStartAssay.get(i).RunID);
				json3.put("PathogenName", Test_Variables.lstStartAssay.get(i).PathogenName);				

				request_startAssay.body(json3.toString());

				Response response2 = request_startAssay.post(Constants.api_StartAssay);

				String data3 = response2.asString();
				System.out.println(data3);

				JsonPath jsonPathEvaluator1 = response.jsonPath();
				jsonPathEvaluator1.get("statusCode");

				//	Assert.assertEquals(statusCodeFileUpload, 114); 
				Test_Variables.test.pass("Start Assay API ran successfully");
				Test_Variables.results.createNode("Start Assay API ran successfully");
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.StartAssayReportPath, null);
			}catch(AssertionError er) {
				Test_Variables.test.fail("Start Assay API failed to run successfully; returned an error");
				Test_Variables.results.createNode("Start Assay API failed to run successfully; returned an error");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.StartAssayReportPath, new Exception(er));
			}catch(Exception ex){
				Test_Variables.test.fail("Start Assay API failed to run successfully; returned an error");
				Test_Variables.results.createNode("Start Assay API failed to run successfully; returned an error");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.StartAssayReportPath, ex);
			}	
			Thread.sleep(1000);

			try {
				Test_Variables.test = Test_Variables.extent.createTest(Test_Variables.lstStartAssay.get(i).testCaseTitleVerification, Test_Variables.lstStartAssay.get(i).testCaseDescVerification);	
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				Test_Variables.preconditions.createNode("1. Go to url " +Constants.url_login);
				Test_Variables.preconditions.createNode("2. Login with valid credentials; user navigates to home page");
				Test_Variables.preconditions.createNode("3. Hover to sidebar to expand the menu");
				Test_Variables.preconditions.createNode("4. Click on Analytics and select Reports; Reports page opens");
				Test_Variables.preconditions.createNode("5. Click on "+Test_Variables.lstStartAssay.get(i).PathogenName+"Log");

				Helper.driver.get(Test_Variables.lstStartAssay.get(i).url);
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Test_Elements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("filter-Lab-Sample-ID")));
				Thread.sleep(1000);

				Test_Variables.steps.createNode("1. Click on Lab Sample ID to expand the filter");
				Helper.driver.findElement(By.id("filter-Lab-Sample-ID")).click();
				Thread.sleep(500);
				Test_Variables.steps.createNode("2. Search for the Sample ID against which the data is ingested");
				Helper.driver.findElement(By.id("Lab-Sample-ID-place-holder-search")).sendKeys("Test"+Test_Variables.lstSampleID.get(i));
				Thread.sleep(500);
				Helper.driver.findElement(By.id("Lab-Sample-ID_cust-cb-lst-txt_Test"+Test_Variables.lstSampleID.get(i))).click();;
				Thread.sleep(500);
				Test_Variables.steps.createNode("3. Click on Apply filter button");
				Helper.driver.findElement(By.id("filter-icon")).click();
				Test_Elements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
				Test_Variables.test.addScreenCaptureFromPath(Helper.getScreenshot("Salmonella Log", Constants.SalmonellaReportPath));
				String records = Helper.driver.findElement(By.id("results-found-count")).getText();

				Assert.assertEquals(records, "1"); 
				Test_Variables.test.pass("Ingested Successfully");
				Test_Variables.results.createNode("Data ingestion verified successfully");
				Helper.saveResultNew(ITestResult.SUCCESS, Constants.SalmonellaReportPath, null);
			}catch(AssertionError er) {
				Test_Variables.test.fail("Ingestion failed");
				Test_Variables.results.createNode("Data ingestion verification failed");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, new Exception(er));
			}catch(Exception ex){
				Test_Variables.test.fail("Ingestion failed");
				Test_Variables.results.createNode("Data ingestion verification failed");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.SalmonellaReportPath, ex);
			}
			Thread.sleep(1000);				
		}	
	}


	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		//	Helper.driver.close();
	}
	
}
