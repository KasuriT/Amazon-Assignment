package ParallelTest;

import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.spark;
import static MiscFunctions.ExtentVariables.test;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import MiscFunctions.DateUtil;
import Models.OrganizationManagementModel;
public class ParallelTestingThreadLocal2 extends BaseTest{

	
	@BeforeTest
	public void extent() throws MalformedURLException {
		spark = new ExtentSparkReporter("target/Reports/Test111"+DateUtil.date+".html");
		spark.config().setReportName("Login Test Report");
	}
	
	
	
	@Test
	public void test1() throws IOException {
		try {
		test = extent.createTest("Class 2-1: Verify user can login into the IE portal");
		System.out.println("Test 2: "+Thread.currentThread().getId());
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
		test = extent.createTest("Class 2-2: Verify user can login into the IE portal 2");
		System.out.println("Test 2: "+Thread.currentThread().getId());
		System.out.println("String: "+OrganizationManagementModel.OrganizationName);
		System.out.println("Link: "+links.url_login);
		Assert.assertTrue(true);
		saveResult(ITestResult.SUCCESS, null);
		}
	catch(AssertionError er) {
		saveResult(ITestResult.FAILURE, new Exception(er));
	}}
	
	
//	@AfterClass
//	public static void endreport() {
//		extent.flush();
//		getDriver().close();
//	}

}
