package Models;

import static MiscFunctions.Constants.url_user;
import static MiscFunctions.Constants.wait;
import static MiscFunctions.DateUtil.date0;
import static MiscFunctions.Helper.driver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Config.ReadPropertyFile;
import PageObjects.SalmonellaLogPage;

public class AccessManagementModel {

	public boolean isOpenPopUp;
	public String accessName;
	public String accessDesc;
	public String step1;
	public String testcase;
	public boolean step;
	
	public String rgName;
	public String rgDesc;
	public boolean rgReport;
	public String rgstep1;
	public boolean rgsave;
	public String rgtestcase;
	public boolean rgstep;
	
	public String TestCaseName;
	public String TestCaseDescription;
	public ArrayList<ReportFilters> lstFilters;

	public static ArrayList<String> lstAccessCreate = new ArrayList<>(
			Arrays.asList("Administrator"+date0, 
					"Role Description"));
	
	
	public static ArrayList<String> lstAccessAlertMessages = new ArrayList<>(
			Arrays.asList("New Reporting role created.", 
					"Role has been updated successfully."));
	
	public static ArrayList<AccessManagementModel> lstUserManagementAccessRole = new ArrayList<>();
	
	public AccessManagementModel (boolean _isOpenPopup, String _accessName, String _accessDesc, String _step1, String _testcase, boolean _step )
	
	{
		this.isOpenPopUp = _isOpenPopup;
		this.accessName = _accessName;
		this.accessDesc = _accessDesc;
		this.step1 = _step1;
		this.testcase = _testcase;
		this.step = _step;
	}
	
	public AccessManagementModel (String _rgName, String _rgDesc, boolean _rgReports, String _rgstep1, boolean _rgsave, String _rgtestcase, boolean _rgstep )
	
	{
		this.rgName = _rgName;
		this.rgDesc = _rgDesc;
		this.rgReport = _rgReports;
		this.rgstep1 = _rgstep1;
		this.rgsave = _rgsave;
		this.rgtestcase = _rgtestcase;
		this.rgstep = _rgstep;
	}
	
	
	public AccessManagementModel() {
	}

	public static ArrayList<AccessManagementModel> UserManagementAccessData() {
		ArrayList<AccessManagementModel> lstAccessModel = new ArrayList<AccessManagementModel>();
		AccessManagementModel objTmp = new AccessManagementModel();
		ReportFilters  objFilter = new ReportFilters();
		objFilter.FilterName = "Lane Filter";
		objTmp.TestCaseName = "AN-SL-11: ";
		objTmp.TestCaseDescription = "";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.LstFilterXpath = new ArrayList<>(Arrays.asList("#"+SalmonellaLogPage.slSortFilter+""+SalmonellaLogPage.slLane));
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("2", "3")); 
		objTmp.lstFilters.add(objFilter);
		lstAccessModel.add(objTmp);	

		return lstAccessModel;
	}
	
	
	public static void getUserAccess() throws InterruptedException, IOException {
		driver.get(url_user);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);
		Thread.sleep(1000);
		for(int i=1; i<=500; i++) {
			if (driver.findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(4) label")).getText().equals(config.ie_username())) {
				WebElement scroll = driver.findElement(By.id("edit-user-"+i));
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
				Thread.sleep(1000);
				driver.findElement(By.id("edit-user-"+i)).click();
				break;
			}
		}
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-loading")));
		Thread.sleep(4000);
		driver.findElement(By.id("btn-next")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("btn-next")).click();
		Thread.sleep(1000);
	}
}
