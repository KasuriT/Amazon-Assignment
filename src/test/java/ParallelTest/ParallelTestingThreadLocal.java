package ParallelTest;

import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.spark;
import static MiscFunctions.ExtentVariables.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.ResultSet;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import MiscFunctions.DB_Config;
import MiscFunctions.DateUtil;
import Models.OrganizationManagementModel;
public class ParallelTestingThreadLocal extends BaseTest{

//	
//	@BeforeTest
//	public void bt() {
//		BaseTest.setup();
//	}
//
	
	@BeforeTest
	public void extent() throws MalformedURLException {
		spark = new ExtentSparkReporter("target/Reports/Test"+DateUtil.date+".html");
		spark.config().setReportName("Login Test Report");
		DB_Config.test();
	}
	
	
	@Test
	public void test1() throws IOException {
		try {
		test = extent.createTest("Class1-1: Verify user can login into the IE portal");
		System.out.println("Test 1: "+Thread.currentThread().getId());
		System.out.println("String: "+OrganizationManagementModel.OrganizationName);
		System.out.println("Link: "+links.url_login);
		Assert.assertTrue(true);
		Methods.getScreenshot();
		saveResult(ITestResult.SUCCESS, null);
		}
		
		catch(Exception ex) {
			saveResult(ITestResult.FAILURE, ex);
		}
	}
	
	@Test
	public void test2() throws IOException {
		try {
		test = extent.createTest("Class1-2: Verify user can login into the IE portal");
		System.out.println("Test 1: "+Thread.currentThread().getId());
		System.out.println("String: "+OrganizationManagementModel.OrganizationName);
		System.out.println("Link: "+links.url_login);
		Assert.assertTrue(false);
		saveResult(ITestResult.SUCCESS, null);
		}
	catch(AssertionError er) {
		saveResult(ITestResult.FAILURE, new Exception(er));
	}}
	
	
	
	@Test
	public static void test11() {
		try{
			test = extent.createTest("Class1-3: Verify user can login into the IE portal");
			String selectQuery = "select status from salmonella_output where RUN_ID = '20221223-Salm-2710'";
			ResultSet rs = DB_Config.getStmt().executeQuery(selectQuery);
			while (rs.next()) {
				System.out.println("Status: "+rs.getString("status"));
			}
			DB_Config.getStmt().close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
//	@AfterClass
//	public static void endDb() throws Exception {
//		extent.flush();
//		getDriver().close();
//		DB_Config.tearDown();
//	}

}
