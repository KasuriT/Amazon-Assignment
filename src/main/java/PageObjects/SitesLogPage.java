package PageObjects;

import static MiscFunctions.Constants.url_reports;
import static MiscFunctions.Methods.click;
import static MiscFunctions.Methods.getText;
import static MiscFunctions.Methods.waitElementInvisible;
import static PageObjects.BasePage.loading_cursor;
import org.openqa.selenium.By;
import org.testng.Assert;

import Config.BaseTest;

public class SitesLogPage {

	private static By sitesLogBox = By.cssSelector("img[alt='Sites Log']"); 
	private static By sitesLogTitle = By.id("Sites Log");

	public static String sitesLogTable = "sites-data-log";
	
	public static String sitesSiteIDCol = "0";
	public static String sitesSiteNameCol = "1";
	public static String sitesSiteTypeCol = "2";
	public static String sitesStreetAddressCol = "3";
	public static String sitesStateCol = "4";
	public static String sitesZipCodeCol = "5";
	public static String sitesCountryCol = "6";
	public static String sitesLatitudeCol = "7";
	public static String sitesLongitudeCol = "8";
	public static String sitesDateCreatedCol = "9";
	public static String sitesCreatedByCol = "10";
	public static String sitesFieldAccessCol = "11";
	
	public static String sitesAuditSiteIDCol = "0";
	public static String sitesAuditSiteNameCol = "0";
	public static String sitesAuditSiteTypeCol = "1";
	public static String sitesAuditStreetAddressCol = "2";
	public static String sitesAuditStateCol = "3";
	public static String sitesAuditZipCodeCol = "4";
	public static String sitesAuditCountryCol = "5";
	public static String sitesAuditLatitudeCol = "6";
	public static String sitesAuditLongitudeCol = "7";
	public static String sitesAuditDateCreatedCol = "8";
	public static String sitesAuditCreatedByCol = "9";
	
	public static String sitesShowFilter = "_show-filter";
	public static String sitesSortFilter = "sort-";
	public static String sitesApplyFilter = "_apply";
	public static String sitesClearFilter = "_clear-filter";

	public static String sitesSiteID = "siteId";
	public static String sitesSiteName = "siteName";
	public static String sitesSiteType = "siteType"; 
	public static String sitesStreetAddress = "streetAddress";
	public static String sitesState = "state";
	public static String sitesZipCode = "zipCode"; 
	public static String sitesCountry = "country";
	public static String sitesLatitude = "latitude";
	public static String sitesLongitude = "longitude"; 
	public static String sitesDateCreated = "createdDate"; 
	public static String sitesCreatedBy = "createdBy"; 
	
	public static String sitesTotalPages = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[15]/p";

	public static String sitesCSVFileName = "Sites Log - ";
	public static String sitesCSVAuditFileName = "Sites Audit Log - ";
	
	public static void openSitesLogPage() {
		BaseTest driver = new BaseTest();
		driver.getDriver().get(url_reports);
		waitElementInvisible(loading_cursor);
		click(sitesLogBox);
		waitElementInvisible(loading_cursor);
		Assert.assertEquals(getText(sitesLogTitle), "Sites Log"); 	
	}
	
}
