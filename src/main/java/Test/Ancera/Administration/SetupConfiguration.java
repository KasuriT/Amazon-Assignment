package Test.Ancera.Administration;

import static Test.Ancera.Test_Functions.NavigateToScreen;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Test.Ancera.ConfigureLogin;
import Test.Ancera.Constants;
import Test.Ancera.Helper;
import Test.Ancera.Test_Elements;
import Test.Ancera.Test_Variables;
import static Test.Ancera.Test_Variables.*;
import static Test.Ancera.Helper.*;
import static Test.Ancera.Test_Elements.*;
import static Test.Ancera.Constants.*;
import static Test.Ancera.ConfigureLogin.*;

public class SetupConfiguration {

	@BeforeTest
	public void extent() throws InterruptedException, IOException {
		spark = new ExtentSparkReporter("target/Reports/Organization_Setup_Configuration"+date+".html");
		spark.config().setReportName("Organization Setup Configurations Test Report"); 
		config();
		login();
	}
	
	@Test(priority= 1)
	public void Navigate() throws InterruptedException, IOException {
		NavigateToScreen(url_programManagement, "Program Management", ProgramManagementReportPath, programManagementTitle);
	}	
	
	@AfterTest
	public static void endreport() {
		Test_Variables.extent.flush();
		Helper.driver.close();
	}
	
}
