package PageObjects;

import org.openqa.selenium.By;

public class OrganizationManagementPage {

	public static String orgManagementTable = "orgn-log";
	public static String orgCSVFileName = "Organizations Log - ";
	public static By orgTitle = By.id("Organization Management");
	public static By orgCreateButton = By.id("create-organization");
	public static By orgTypeDropDownExpand = By.cssSelector("#orgTypeId .ng-arrow-wrapper");
	public static By orgTypeInput = By.cssSelector("#orgTypeId input");
	public static By orgTypeError = By.cssSelector("#orgTypeId.ng-invalid");
	public static By orgNameInput = By.id("nameId");
	public static By orgNameError = By.cssSelector("#num-nameId.has-error");
	public static By orgPhoneCodeInput = By.id("phoneCodeId");
	public static By orgPhoneNumberInput = By.id("cellNumberId");
	public static By orgPhoneNumberError = By.cssSelector("#cellNumberId .has-error");
	public static By orgEmailInput = By.id("emailId");
	public static By orgEmailError = By.cssSelector("#emailId.has-error");

	public static By orgAllowDomainsExpand = By.cssSelector("#domains .ng-arrow-wrapper");
	public static By roleCategoryExpand = By.cssSelector("#roleCategoryId .ng-arrow-wrapper");
	public static By selectAllCheckBox = By.xpath("//label[text() = 'Select All']");
	public static By orgMaxUsersInput = By.id("num-idMaxUsers");
	public static By orgMaxUsersError = By.cssSelector("#num-idMaxUsers .has-error");
	public static By orgAgreementDropDownExpand = By.cssSelector("#euladdl .ng-arrow-wrapper");
	public static By orgAgreementDropDownSelect = By.xpath("//label[contains(text(),'Select All')]");
	public static By orgSystemRolesExpand = By.cssSelector("#rolesId .ng-arrow-wrapper");
	public static By orgSystemRolesSelect = By.cssSelector("#rolesId .ng-input");
	public static By orgReportRolesExpand = By.cssSelector("#rptRolesId .ng-arrow-wrapper");
	public static By orgReportRolesSelect = By.cssSelector("#rptRolesId .ng-input");
	
	public static By orgParentSiteClick = By.cssSelector("li .text-ellipsis");
	public static By orgSiteIDField = By.id("num-SiteIDId");
	public static By orgSiteTypeInput = By.id("SiteTypeId");  
	public static By orgSiteTypeInputChild = By.id("SiteTypeId"); 
	public static By orgSiteTypeDropDownValue = By.cssSelector("div .ng-dropdown-panel-items");
	public static By orgSiteNameInput = By.id("SiteNameId");
	public static By orgSiteNameError = By.cssSelector("#SiteNameId.has-error"); 
	public static By orgSiteAddressInput = By.id("streetAddressId");
	public static By orgSiteCountryInput = By.cssSelector("#countryId");
	public static By orgSiteStateInput = By.cssSelector("#stateId");
	public static By orgSiteCityInput = By.cssSelector("#cityId");
	public static By orgSiteZipCodeInput = By.id("num-zipCodeId");
	public static By orgSiteLatitudeInput = By.id("num-LatId");
	public static By orgSiteLongitudeInput = By.id("num-LonId");
	
	public static By orgUploadProductImage = By.id("file");
	public static By orgAddProductName = By.id("nameId");
	public static By orgAddProductDescription = By.id("descriptionId");
	public static By orgRemoveUploadedProduct = By.id("topRightCornerBtn");
	public static By orgDeleteUploadedProduct = By.xpath("(//button[@type = 'button'])[3]");
	public static By orgGetTotalCreatedProducts = By.cssSelector(".fileItemInActive");
	
	
	public static By editSearchedOrg = By.cssSelector("#edit-orgn-sites-1 img");
	
	public static By orgAddSite1 = By.cssSelector("img[alt = 'add']");
	public static By orgSite1Click = By.cssSelector("li:last-child ul:last-child li:last-child span");
	public static By orgSite1Delete = By.cssSelector(".delete img");
	public static By orgAddSite2 = By.xpath("//ul/div/li/ul/li/div/div[4]/div[1]/img");
	public static By orgAddSite3 = By.xpath("//ul/li/ul/li//div[4]/div[1]/img");
	public static By orgAddSite4 = By.xpath("//ul/li//li//li//div[4]/div[1]/img");
	public static By orgAddSite5 = By.xpath("//ul/div/li//li//li/ul/li/ul/li/div/div[4]/div[1]/img");
	public static By orgAddSite6 = By.xpath("/html/body/app-root/div/app-manage-organization-v2/div/div[2]/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[2]/div/div[1]/div/ul/div/li/ul/li/ul/li/ul/li[2]/div/div[4]/div[1]/img");
	public static By orgAddSite7 = By.xpath("/html/body/app-root/div/app-manage-organization-v2/div/div[2]/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[2]/div/div[1]/div/ul/div/li/ul/li/ul/li/ul/li[3]/div/div[4]/div[1]/img");
	public static By orgDeleteSite1 = By.xpath("/html/body/app-root/div/app-manage-organization-v2/div/div[2]/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[2]/div/div[1]/div/ul/div/li/ul/li/div/div[4]/div[2]/img");
	
	public static String orgNameCol = "0";
	public static String orgPhoneNumberCol = "1";
	public static String orgCityCol = "2";
	public static String orgStateCol = "3";
	public static String orgCountryCol = "4";
	public static String orgOrganzationTypeCol = "5";
	
	public static String orgName = "orgnName";
	public static String orgOrganzationType = "orgnTypeName";
	public static String orgPhoneNumber = "phoneNo";
	public static String orgCity = "cityName";
	public static String orgState = "provinceName";
	public static String orgCountry = "countryName";
	
}
