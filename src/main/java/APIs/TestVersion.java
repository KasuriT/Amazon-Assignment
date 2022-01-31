package APIs;


import java.io.IOException;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Models.APIVersionModel;
import Models.ReportFilters;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Variables;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestVersion {

	
	@BeforeTest
	public void extent() throws InterruptedException, IOException {

		Test_Variables.spark = new ExtentSparkReporter("target/Reports/API Version Test"+Test_Variables.date+".html");
		Test_Variables.spark.config().setReportName("API Version Test Report"); 
		Helper.config();
	}
	
	
	@SuppressWarnings({ "unused", "unchecked" })
	@Test (description="Test Case: Test API Versions", enabled= true, priority= 1) 
	public void APIVersion() throws InterruptedException, IOException	{

		Test_Variables.lstTestAPIVersion = APIVersionModel.FillData(); 
		for (APIVersionModel objModel : Test_Variables.lstTestAPIVersion) { 	
			try {
				Test_Variables.test = Test_Variables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
				Test_Variables.preconditions = Test_Variables.test.createNode(Scenario.class, Test_Variables.PreConditions);
				Test_Variables.steps = Test_Variables.test.createNode(Scenario.class, Test_Variables.Steps);
				Test_Variables.results = Test_Variables.test.createNode(Scenario.class, Test_Variables.Results);

				for (ReportFilters objFilter : objModel.lstFilters) {

					RequestSpecification request = RestAssured.given();
					request.header("Content-Type", "application/json");
					JSONObject json = new JSONObject();
					json.put("piperid", Test_Variables.piperId);
					json.put("password", Test_Variables.piperPassword);
					json.put("DISAPIVersion", objModel.version);
					request.body(json.toString());
					Response response = request.post(Constants.api_login);
					int code = response.getStatusCode();
					SoftAssert softAssert = new SoftAssert();
					System.out.println("StatusCode:"+ code);
					softAssert.assertEquals(code, objModel.statusCodeResponseLogin);

					String data = response.asString();
					System.out.println(data);

					JsonPath jsonPathEvaluator = response.jsonPath();
					String token = jsonPathEvaluator.get("token");	
					int versionUpgrade = jsonPathEvaluator.get("versionUpgrade");	
					String message = jsonPathEvaluator.get("message");
				//	System.out.println(versionUpgrade);
	
					softAssert.assertEquals(versionUpgrade, objModel.versionUpgradeResponse); 
					softAssert.assertEquals(message, objModel.messageResponse);		
					
					/////////////////////////////////FileRequest/////////////////////////////////////
					Test_Variables.test = Test_Variables.extent.createTest("Response of FileRequest API");
					
					RequestSpecification request_filerequest = RestAssured.given();
					request_filerequest.header("Content-Type", "application/json");
					request_filerequest.header("Authorization", "bearer " +token);
					HttpGet postRequest = new HttpGet(Constants.api_FileRequest);
					postRequest.addHeader("Content-Type", "application/json");
					postRequest.addHeader("Authorization", "Bearer "+token);
					JSONObject json1 = new JSONObject();
					json1.put("piperid", "PSN0001");
					json1.put("fileName", "PiperUserApp_Installer.08.11.10.15.msi");
					request_filerequest.body(json1.toString());
					Response response1 = request_filerequest.post(Constants.api_FileRequest);
					String data1 = response1.asString();
					System.out.println("FileRequest API: "+data1);
					Test_Variables.results.createNode("API Response: "+data1);
					softAssert.assertEquals(data1.isEmpty(), objModel.responseBody);
					
					if (!data1.isEmpty()) {
						message = response1.jsonPath().get("message");
						softAssert.assertEquals(message, "Successful Response.");
					}
					softAssert.assertAll();
					///////////////////////////////////////////////////////////////////////   
					
					//////////////////////////////////MPNSettings////////////////////////////////////
					Test_Variables.test = Test_Variables.extent.createTest("Response of MPNSetting API");
					
					RequestSpecification request_mpnrequest = RestAssured.given();
					request_mpnrequest.header("Content-Type", "application/json");
					request_mpnrequest.header("Authorization", "bearer " +token);
					HttpGet postRequest1 = new HttpGet(Constants.api_MPNSettingRequest);
					postRequest1.addHeader("Content-Type", "application/json");
					postRequest1.addHeader("Authorization", "Bearer "+token);
					JSONObject json2 = new JSONObject();
					json2.put("piperid", "PSN0001");
					json2.put("RequestType", "1");
					json2.put("MPNSettingsVersion", "1");
					json2.put("Improc", "ImprocSalm01");
					json2.put("ImprocVersion", "4.9.2.0");
					request_mpnrequest.body(json2.toString());
					Response response2 = request_mpnrequest.post(Constants.api_MPNSettingRequest);
					String data2 = response2.asString();
					System.out.println("MPN Setting API: "+data2);
					Test_Variables.results.createNode("API Response: "+data2);
					softAssert.assertEquals(data2.isEmpty(), objModel.responseBody);
					
					if (!data2.isEmpty()) {
						message = response2.jsonPath().get("message");
						softAssert.assertEquals(message, objModel.messageResponseAPI);
					}
					softAssert.assertAll();
					///////////////////////////////////////////////////////////////////////
					
					//////////////////////////////////Userlist////////////////////////////////////
					Test_Variables.test = Test_Variables.extent.createTest("Response of Userlist API");
					
					RequestSpecification request_userlistrequest = RestAssured.given();
					request_userlistrequest.header("Content-Type", "application/json");
					request_userlistrequest.header("Authorization", "bearer " +token);
					HttpGet postRequest2 = new HttpGet(Constants.api_UserListRequest);
					postRequest2.addHeader("Content-Type", "application/json");
					postRequest2.addHeader("Authorization", "Bearer "+token);
					JSONObject json3 = new JSONObject();
					json3.put("piperid", "PSN0005");
					json3.put("RequestType", "1");
					json3.put("userListVersion", "V-4");
					request_userlistrequest.body(json3.toString());
					Response response3 = request_userlistrequest.post(Constants.api_UserListRequest);
					String data3 = response3.asString();
					System.out.println("Userlist API: "+data3);
					Test_Variables.results.createNode("API Response: "+data3);
					softAssert.assertEquals(data3.isEmpty(), objModel.responseBody);
					
					if (!data3.isEmpty()) {
						message = response3.jsonPath().get("message");
						softAssert.assertEquals(message, objModel.messageResponseAPI);
					}
					softAssert.assertAll();
					///////////////////////////////////////////////////////////////////////
					
					////////////////////////////////////Heartbeat//////////////////////////////////
					Test_Variables.test = Test_Variables.extent.createTest("Response of Heartbeat API");
					
					RequestSpecification request_heartbeat = RestAssured.given();
					request_heartbeat.header("Content-Type", "application/json");
					request_heartbeat.header("Authorization", "bearer " +token);

					HttpGet postRequest3 = new HttpGet(Constants.api_HeartBeat);
					postRequest3.addHeader("Content-Type", "application/json");
					postRequest3.addHeader("Authorization", "Bearer "+token);

					JSONObject json4 = new JSONObject();
					JSONObject json5 = new JSONObject();
					JSONObject json6 = new JSONObject();
					JSONObject json7 = new JSONObject();
					JSONArray list = new JSONArray();

					json4.put("piperid", "PSN0009");
					json4.put("dateTime", Test_Variables.dateRIY+"T"+Test_Variables.dateRIT+".000Z");
					json4.put("userListVersion",  "4");

					json5.put("name", "PiperUserApp");
					json5.put("version", "1.0.04");
					
					json6.put("name", "ImprocSalm01");
					json6.put("version", "1.0.04");
					json6.put("MPNsettingsVersion", "0");
					
					json7.put("name", "ImprocCocc01");
					json7.put("version", "1.0.04" );
					json7.put("MPNsettingsVersion", "2");

					list.add(json5);
					list.add(json6);
					list.add(json7);
					json4.put("packages", list);

					request_heartbeat.body(json4.toString());
					Response response4 = request_heartbeat.post(Constants.api_HeartBeat);

					String data4 = response4.asString();
					System.out.println("Heartbeat API: "+data4);
					Test_Variables.results.createNode("API Response: "+data4);
					softAssert.assertEquals(data4.isEmpty(), objModel.responseBody);
					
					String statusCodeAnnouncement = jsonPathEvaluator.get("statusCode");
		
					if (!data4.isEmpty()) {
						message = response4.jsonPath().get("message");
						softAssert.assertEquals(message, objModel.messageResponseAPI);
					}
					softAssert.assertAll();
					///////////////////////////////////////////////////////////////////////////
		
					Test_Variables.test.pass("API Version test verified successfully");
					Test_Variables.results.createNode("API Version test verified successfully");
					Helper.saveResultNew(ITestResult.SUCCESS, Constants.NormalIngestionReportPath, null);			
				}
			}
			catch(AssertionError er) {
				Test_Variables.test.fail("API Version test failed");
				Test_Variables.results.createNode("API Version test failed");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, new Exception(er));
			}catch(Exception ex){
				Test_Variables.test.fail("API Version test failed");
				Test_Variables.results.createNode("API Version test failed");
				Helper.saveResultNew(ITestResult.FAILURE, Constants.NormalIngestionReportPath, ex);
			}
		}
	}
	
	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		Helper.driver.close();
	}
}




