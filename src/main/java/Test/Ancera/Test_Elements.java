package Test.Ancera;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test_Elements {

	public static WebDriverWait wait = new WebDriverWait(Helper.driver, Duration.ofSeconds(90, 1));

	//////////////////////////////////////////////////Login Elements//////////////////////////////////////////////////////////

	public static By loginEmail = By.id("email");
	public static By loginPassword = By.id("pwd");
	public static By loginButton = By.id("btn-sign-in");
	public static By logoutButton = By.id("logout");

	///////////////////////////////////////////////End Login Elements/////////////////////////////////////////////////////////

	
	/////////////////////////////////////////////ForgotPassword Elements//////////////////////////////////////////////////////

	public static String gmailEmail = "//input[@type = 'email']";
	public static String gmailPassword = "//input[@type = 'password']";
	public static String gmailSecurityCheck = "//*[@id=\"view_container\"]/div/div/div[2]/div/div[1]/div/form/span/section/div/div/div/ul/li[1]/div/div[2]";
	public static String gmailSecurityEmail = "//*[@id=\"view_container\"]/div";

	///////////////////////////////////////////End ForgotPassword Elements////////////////////////////////////////////////////


	///////////////////////////////////////////////////Static Elements//////////////////////////////////////////////////////////////
	
	public static String loading_cursor = "notification-loading";
	public static By loader = By.id("notification-loading");
	public static By popupNextButton = By.id("btn-next");
	public static By popupResetButton = By.id("btn-reset");
	public static By popupSaveButton = By.id("btn-save");
	public static By popupYesButton = By.id("btn-yes");
	public static By popupOKButton = By.id("btn-ok");
	public static By popupCloseButton = By.cssSelector("#close-popup-modal img");
	public static By alertMessage = By.id("message");
	//public static By alertMessageClose = By.cssSelector("button.close span");
	public static By alertMessageClose = By.xpath("/html/body/app-root/app-notification-component/ngb-alert/button/span");
	public static By confirmationYes = By.cssSelector("button.mr-4");
	
	public static String columnCount = "#table-header-vaccine-log th .log-header .mb-0";
	
	public static String ShowFilter = "_show-filter";
	public static String SortFilter = "sort-";
	public static String ApplyFilter = "_apply";
	public static String ClearFilter = "_clear-filter";
	public static String SearchInput = "_search-input";
	public static String SelectAll = "_cust-cb-lst-txt_selectAll";
	public static String LockFilter = "save-filters";
	public static String UnlockFilter = "remove-filters";
	public static String ResultsCount = "results-found-count";
	public static String ResetFilters = "reset-all-filters";
	public static String FieldAccess = "edit-field-access";
	public static String CSVButton = "";
	
	public static String lastPagePagination = "last-page";
	public static String previousPagePagination ="previous-page";
	public static String firstPagePagination = "first-page";
	public static String nextPagePagination = "next-page";
	
	//////////////////////////////////////////////Static Elements///////////////////////////////////////////////////////////
		
	/////////////////////////////////////////////User Management Elements///////////////////////////////////////////////////

	public static By usercreateButton = By.id("create-user");
	public static By userFirstNameInput = By.id("firstNameId");
	public static By userLastNameInput = By.id("lastNameId");
	public static By userEmailInput = By.id("emailId");
	public static By userOrgTypeDropDownExpand = By.cssSelector("#orgTypeId .ng-arrow-wrapper");
	public static By userOrgTypeInput = By.cssSelector("#orgTypeId input");
	public static By userOrgDropDownExpand = By.cssSelector("#organizationId .ng-arrow-wrapper");
	public static By userOrgInput = By.cssSelector("#organizationId input");
	public static By siteAdministratorToggle = By.cssSelector("#site-administrator .row");
	public static By systemRolesExpand = By.cssSelector("#rolesId .ng-arrow-wrapper");
	public static By systemRoleSelect1 = By.cssSelector(".ng-dropdown-panel .ng-option:nth-child(2) label");
	public static By systemRolesSelect = By.xpath("//*[@id=\"rolesId\"]//div[2]/input");
	public static By systemRolesSelected = By.cssSelector("#rolesId .ng-option-selected");
	public static By reportRoleExpand = By.cssSelector("#reportRoleId .ng-arrow-wrapper");
	public static By reportRoleSelect = By.xpath("//*[@id=\"reportRoleId\"]//input");
	public static By reportRoleGetValue = By.cssSelector("#reportRoleId .ng-value-label");
	public static By AgreeementExpand = By.cssSelector("#euladdl .ng-arrow-wrapper");
	public static By AgreementSelect = By.xpath("//*[@id=\"euladdl\"]//div[2]/input");
	
	
	public static By openUserSites = By.cssSelector(".btn-sites");
	public static By selectTestingSites = By.id("select-testing-sites");
	public static By selectColletionSites = By.id("select-collection-sites");
	
	public static By saveUserSites = By.id("btn-ok-sites");
	
	public static By editSearchedUser = By.id("edit-user-1");
	public static By deleteSearchedUser = By.cssSelector("#delete-user-1 img");
	public static By agreeementSearchedUser = By.id("view-agreements-1");
	public static By agreementList = By.xpath("//*[@id=\"manage-user\"]//app-user-license-log//tbody/tr[1]");
	public static By enterNewPassword = By.id("passwordId");
	public static By enterConfirmPassword = By.id("rePassordId");
	public static By clickPasswordButton = By.cssSelector("button.apl-btn");
	
	public static String userFirstNameCol = "0";
	public static String userLastNameCol = "1";
	public static String userMobileNoCol = "2";
	public static String userEmailCol = "3";
	public static String userOrgTypeCol = "4";
	public static String userOrgCol = "5";
	public static String userRoleCol = "6";
	public static String userReportingCol = "7";
	public static String userSiteAccessCol = "8";
	
	public static String userManagementTable = "manage-user";
	public static String userCSVFileName = "Users Log - ";
	public static By userTitle = By.id("User Management");
	public static String userFirstName = "userFirstName";
	public static String userLastName = "userLastName";
	public static String userMobileNo = "cfCellPhoneNo";
	public static String userEmail = "userEmail";
	public static String userOrgType = "orgnTypeName";
	public static String userOrg = "orgnName";
	public static String userRole = "cfRoles";
	public static String userReporting = "reportingRole";
	public static String userSiteAccess = "sites";
	public static By userSitesSearch = By.xpath("//*[@placeholder='Filter']");
	public static By userSitesButton = By.cssSelector(".btn-sites");
	public static By userSitesSaveButton = By.id("btn-ok-sites");
	
	public static By alertClose = By.xpath("//*[@id=\"alrt\"]/button/span");
	
	///////////////////////////////////////////End User Management Elements//////////////////////////////////////////////////

	/////////////////////////////////////////Organization Management Elements////////////////////////////////////////////////
	
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
	
	public static By editSearchedOrg = By.cssSelector("#edit-orgn-sites-1 img");
	
	public static By orgAddSite1 = By.xpath("/html/body/app-root/div/app-manage-organization-v2/div/div[2]/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[2]/div/div[1]/div/ul/div/li/div/div[4]/div/img");
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

	////////////////////////////////////////End Organization Management Elements//////////////////////////////////////////////



	//////////////////////////////////////////// Access Management Elements//////////////////////////////////////////////////

	public static By accessCreateButton = By.id("create-role");
	public static By accessName = By.xpath("//div[1]/app-anc-input-box/div/input[1]");
	public static By accessDesc = By.xpath("//div[2]/app-anc-input-box/div/input[1]");

	public static By accessNameDescValidation = By.cssSelector(".has-error");
	public static String accessInActive = "/html/body/app-root/div[1]/app-manage-role/app-popup-component/div/div/div/div[3]/app-update-role/form/div[1]/div/div/div[3]/div/app-custom-radio-button/div/div";



	//////////////////////////////////////////End Access Management Elements////////////////////////////////////////////////
	
	
	//////////////////////////////////////////// Barcode Management Elements//////////////////////////////////////////////////

	public static By barcodeManagmentTitle = By.id("Barcode Management");

	//////////////////////////////////////////End BArcode Management Elements////////////////////////////////////////////////


	/////////////////////////////////////////////Alert Management Elements/////////////////////////////////////////////////

	public String createButton = "/html/body/app-root/div[1]/app-manage-alert-v2/div[1]/button";
	public String org_dd = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[1]/div[2]/div[1]/div[1]/ng-select/div/span";
	public String org_select = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[1]/div[2]/div[1]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[1]";
	public String alertName = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[1]/div[2]/div[3]/input";
	public String alertDesc = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[1]/div[3]/div/textarea";
	public String nextButton = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[3]/button";
	public static String dataSourceExpand = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div/div[1]/ng-select/div/span";

	public static String farmPerformance = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div[1]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[1]";	
	public static String fpAvgWeight = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div[2]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[1]";
	public static String fpFeedConversion = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div[2]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[2]/span";
	public static String fpMortality = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div[2]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[3]";

	public static String piperLog = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div[1]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[2]";
	public static String plReportExpand = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div[2]/div[1]/ng-select/div/span";
	public static String plCoccidia = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div[2]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[1]";
	public static String plAlertVariabeExpand = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div[3]/div[1]/ng-select/div/span";
	public static String plCoccidiaOPGLarge = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div[3]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[1]";
	public static String plCoccidiaOPGMedium = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div[3]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[2]";
	public static String plCoccidiaOPGSmall = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div[3]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[3]";
	public static String plCoccidiaOPGTotal = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div[3]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[4]";
	public static String plSalmonella = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div[2]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[2]/span";
	public static String plSalmonellaW2Count = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div[3]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div/span";

	public static String weather = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div[1]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[3]";
	public static String wAlertVariableExpand = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div[2]/div[1]/ng-select/div/span";
	public static String wHumidity = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div[2]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[1]";		
	public static String wPrecipitation = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div[2]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[2]";
	public static String wTemperature = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div[2]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[3]";
	public static String wdaysExpand = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div[3]/div[1]/ng-select/div/span";
	public static String wDay1 = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div[3]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[1]";
	public static String wDay2 = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div[3]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[2]";
	public static String wDay3 = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div[3]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[3]";
	public static String wDay4 = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div[3]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[4]";
	public static String wDay5 = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div[3]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[5]";
	public static String wDay6 = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div[3]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[6]";
	public static String wDay7 = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[2]/div[3]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[7]";

	public static String alertModeAbsence = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[3]/div[2]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[1]";
	public static String notifyExpand = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[3]/div[3]/div[1]/ng-select/div/span";
	public static String notifyCustom = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[3]/div[3]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[1]";
	public static String notifyCustomDays = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[3]/div[4]/input";
	public static String notifyDaily = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[3]/div[3]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[1]";
	public static String notifyOneTime = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[3]/div[3]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[2]";
	public static String notifyWeekly = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[3]/div[3]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[3]";

	public static String absenceDuration = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[4]/div[1]/input";
	public static String absenceDaysExpand = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[4]/div[2]/div[1]/ng-select/div/span";
	public static String absenceDays = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[4]/div[2]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[1]";
	public static String absenceMonths = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[4]/div[2]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[2]";
	public static String absenceWeeks = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[4]/div[2]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[3]";

	public static String alertModeAggregate = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[3]/div[2]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[2]";
	public static String aggregateModeExpand = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[4]/div[1]/div[1]/ng-select/div/span";
	public static String aggregateMovingAverage = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[4]/div[1]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[1]";
	public static String aggregateStdDeviation = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[4]/div[1]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[2]";

	public static String aggregateConditionExpand = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[4]/div[2]/div[1]/ng-select/div/span[2]";	
	public static String aggregateConditionGreater = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[4]/div[2]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[1]";
	public static String aggregateConditionGreaterEqual = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[4]/div[2]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[2]";
	public static String aggregateConditionLess = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[4]/div[2]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[3]";
	public static String aggregateDays = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[4]/div[3]/input";
	public static String aggregateDeviations = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[4]/div[4]/input";	
	public static String alertModeThreshold = "/html/body/app-root/div[1]/app-manage-alert-v2/app-general-modal/div/div/div/div[2]/app-create-alert-v2/form/div/div/div[2]/div[3]/div[2]/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[3]";

	/////////////////////////////////////////End Alert Management Elements///////////////////////////////////////////////////


	//////////////////////////////////////////Report Management Elements////////////////////////////////////////////////////

	public static By rmCreateButton = By.id("create-report-role");
	public static By rmName = By.id("nameId");
	public static By rmDesc = By.id("DescId");
	public static By rmRoleFilterShow = By.id("reportRoleName_show-filter");
	public static By rmRoleFilterApply = By.id("reportRoleName_apply");
	public static By rmRoleFilterSearch = By.id("reportRoleName_search-input");
	public static By rmRoleFilterWildcardToggle = By.cssSelector(".filter-popup__action--wildcard");
	public static By rmReportGroupPopupOpen = By.id("manage-report-group");
	public static By rmReportGroupsCreateButton = By.id("add-group");
	public static By rmReportGroupsName = By.id("groupNameId");
	public static By rmReportGroupsDesc = By.id("groupDescId");
	public static By rmReportGroupsresetButton = By.id("btn-reset-2");
	
	/////////////////////////////////////////End Report Management Elements////////////////////////////////////////////////////


	/////////////////////////////////////////Agreement Management Elements////////////////////////////////////////////////////
	
	public static String amBeforelist = "/html/body/app-root/div/app-manage-user-license-component/div[1]/div[2]/div[2]/form/div/div/div/div/table/tbody/tr[";
	public static String amAfterList = "]/td[1]";
	public static String amAfterList2 = "]/td[2]/label";
	                                    
	public static String amDropdownSelect = "/html/body/app-root/div/app-manage-user-license-component/div[1]/div[3]/form/div/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div";
	public static String amSearchNo = "/html/body/app-root/div/app-manage-user-license-component/div[1]/div[3]/div/div[1]/div[3]/label/span[1]";
	
	public static String amLicensePopup = "/html/body/app-root/div/app-sign-in/div[2]/app-general-modal/div/div/div/div[2]/app-view-user-license/div[2]/button[1]";
	
	public static String amBeforeGrid = "/html/body/app-root/div/app-manage-user-license-component/div[1]/div[2]/div[1]/div/div/div/div/div/div[";
	public static String amExpandOrg = "/html/body/app-root/div/app-manage-user-license-component/div[1]/div[3]/div/div[2]/div[1]/div/div[1]/label";
	public static String amExpandOrgType = "/html/body/app-root/div/app-manage-user-license-component/div[1]/div[3]/div/div[2]/div[1]/div[2]/div/div/ul/li/div/div[2]/label[1]";
	
	//////////////////////////////////////End Agreement Management Elements///////////////////////////////////////////////////
	
	
	/////////////////////////////////////////Program Management Elements////////////////////////////////////////////////////
	public static By programManagementTitle = By.id("Program Management");
	
	public static By programCreateButton = By.id("create-program");
	public static By programName = By.id("programNameId");
	public static By programColumn1 = By.cssSelector("#col-0 label");
	public static By programTargetPathogen = By.cssSelector("#targetPathogenId input");
	public static By programProgramType = By.cssSelector("#programTypeId input");
	public static By programSupplier = By.cssSelector("#supplierId input");
	public static By programDescription = By.id("descriptionId");
	public static By programStartDate = By.id("");
	public static By programNoApplicationFlock = By.id("num-numOfApplicationId");
	public static String programDaysApplicationFlock = "num-flockDayApplicationId";
	
	public static By programFeedTypeDropdown = By.cssSelector("#feedTypeId-1 input");
	public static By programFlockDayStart  = By.id("num-flockDayStartId-1");
	public static By programFlockDayEnd  = By.id("num-flockDayEndId-1");
	
	public static By programAddFeedType = By.xpath("//*[text() = 'Add Feed Type']");
	public static By programFeedType2Dropdown = By.cssSelector("#feedTypeId-2 input");
	public static By programFlockDayStart2  = By.id("num-flockDayStartId-2");
	public static By programFlockDayEnd2  = By.id("num-flockDayEndId-2");
	public static By programFeedCategory = By.xpath("//*[text() = 'Feed Category']");
	
	public static By programFeedProgramTab = By.xpath("//*[text() = 'Feed Programs ']");
	public static By programTreatmentProgramTab = By.xpath("//*[text() = 'Treatment ']");
	public static By programVaccineProgramTab = By.xpath("//*[text() = 'Vaccine Programs ']");
	public static By programBioshuttleProgramTab = By.xpath("//*[text() = 'Vaccine with Bioshuttle ']");
	public static By programProgramUtilizationTab = By.xpath("//*[text() = 'Program Utilization ']");
	
	public static String programEditVaccineButton = "edit-vaccine-program-";
	public static String programEditFeedButton = "edit-feed-program-";
	public static String programDeleteVaccineButton = "delete-vaccine-program-";
	public static String programDeleteFeedButton = "delete-feed-program-";
	

	public static String programFeedTable = "feed-program-log";
	public static String programTreatmentTable = "treatment-log";
	public static String programVaccineTable = "vaccine-log";
	public static String programBioshuttleTable = "vaccine-bio-log"; 
	public static String programUtilizationTable = "program-util-log";
	
	public static String programFeed_ID = "feedprogram";
	public static String programTreatment_ID = "treatment";
	public static String programVaccine_ID = "vaccine";
	public static String programBioshuttle_ID = "vaccinebio";
	
	public static String programFeedEdit = "edit-feed-program-";
	public static String programTreatmentEdit = "edit-treatment-program-";
	public static String programVaccineEdit = "edit-vaccine-program-";
	public static String programBioshuttleEdit = "edit-vaccine-bio-program-";
	
	public static String programFeedDelete = "delete-feed-program-";
	public static String programTreatmentDelete = "delete-treatment-program-";
	public static String programVaccineDelete = "delete-vaccine-program-";
	public static String programBioshuttleDelete = "delete-vaccine-bio-program-";
	
	public static String programVaccineProgramTab_XPATH = "//*[text() = 'Vaccine Programs ']";
	public static String programFeedProgramTab_XPATH = "//*[text() = 'Feed Programs ']";
	public static String programVaccine_ProgramName_FilterIcon_CSS = "#"+programVaccineTable+" #programName_show-filter";
	
	public static String programFeedProgramNameCol = "#col-0-feedprogram label";
	public static String programFeedSupplierNameCol = "#col-1-feedprogram label";
	public static String programFeedDescriptionCol = "#col-2-feedprogram label";
	public static String programFeedFeedTypesCol = "#col-3-feedprogram label";
	public static String programFeedStartDateCol = "#col-4-feedprogram label";
	public static String programFeedEndDateCol = "#col-5-feedprogram label";
	public static String programFeedComplexCol = "#col-6-feedprogram label";
	
	public static String programTreatmentProgramNameCol = "#col-0-treatment label";
	public static String programTreatmentSupplierNameCol = "#col-1-treatment label";
	public static String programTreatmentDescriptionCol = "#col-2-treatment label";
	public static String programTreatmentStartDateCol = "#col-3-treatment label";
	public static String programTreatmentEndDateCol = "#col-4-treatment label";
	public static String programTreatmentNameCol = "#col-5-treatment label";
	public static String programTreatmentFlockDayStartCol = "#col-6-treatment label";
	public static String programTreatmentFlockDayEndCol = "#col-7-treatment label";
	public static String programTreatmentRouteCol = "#col-8-treatment label";
	public static String programTreatmentTreatmentDescriptionCol = "#col-9-treatment label";
	public static String programTreatmentComplexCol = "#col-10-treatment label";
	
	public static String programVaccineProgramNameCol = "#col-0-vaccine label";
	public static String programVaccineSupplierNameCol = "#col-1-vaccine label";
	public static String programVaccineNumberOfApplicationFlockCol = "#col-1-vaccine label";
	public static String programVaccineDescriptionCol = "#col-2-vaccine label";
	public static String programVaccineStartDateCol = "#col-3-vaccine label";
	public static String programVaccineEndDateCol = "#col-4-vaccine label";
	public static String programVaccineFlockDayApplicationCol = "#col-5-vaccine label";

	public static String programBioshuttleProgramNameCol = "#col-0-vaccine-bio label";
	public static String programBioshuttleSupplierNameCol = "#col-1-vaccine-bio label";
	public static String programBioshuttleNumberOfApplicationFlockCol = "#col-1-vaccine-bio label";
	public static String programBioshuttleDescriptionCol = "#col-2-vaccine-bio label";
	public static String programBioshuttleStartDateCol = "#col-3-vaccine-bio label";
	public static String programBioshuttleEndDateCol = "#col-4-vaccine-bio label";
	public static String programBioshuttleFlockDayApplicationCol = "#col-5-vaccine-bio label";
	public static String programBioshuttleNameCol = "#col-6-vaccine-bio label";
	public static String programBioshuttleFlockDayStartCol = "#col-7-vaccine-bio label";
	public static String programBioshuttleFlockDayEndCol = "#col-8-vaccine-bio label";

	
//	public static String programVaccineName = "vaccine_programName";
	public static String programVaccineCSVFileName = "Vaccine Log - ";
	public static String programFeedCSVFileName = "Feed Program Log - ";
	
	//////////////////////////////////////End Program Management Elements///////////////////////////////////////////////////
	

	/////////////////////////////////////Complex Configuration Elements////////////////////////////////////////////////////
	
	public static By complexTitle = By.id("Complex OPG Range Config");
	public static By complexCreateButton = By.id("create-complex-cocci-intervention");
	public static By complexSelectComplexDropdown = By.cssSelector("#compleSiteId .down");
	public static By complexSearchComplex = By.id("compleSiteId_search");
	public static By complexSelectComplexSite = By.cssSelector("#compleSiteId tr b");
	public static By complexSelectProgramType = By.cssSelector("#programTypeId input");
	public static By complexSelectProgramId = By.cssSelector("#programId input");
	public static By complexSelectVaccine = By.cssSelector("#vaccineId input");
	public static By complexSelectFeed = By.cssSelector("#feedProgramId input");
	public static By complexOPGType = By.id("opgTypeId");
	public static By complexBirdSize = By.id("birdSizeId"); 
	public static By complexSamplingInterval = By.id("cyclingIntervalId");
	public static By complexSelectValueFromDropdown = By.cssSelector(".ng-option-label");
	public static By complexComplexThreshold = By.id("complexCyclingThreshold");
	public static By complexHouseThreshold = By.id("houseCyclingThreshold");
	public static By complexLowerLimit = By.id("lowerLimit");
	public static By complexUpperLimit = By.id("upperLimitId");
	//public static By complexAddButton = By.xpath("//button[text() = 'Add']");
	public static By complexSubmitButton = By.xpath("//button[text() = ' Submit']");
	
	/////////////////////////////////////End Complex Configuration Elements////////////////////////////////////////////////
	
	
	/////////////////////////////////////Piper Management Elements////////////////////////////////////////////////////////7
	public static By piperManagementTitle = By.id("PIPER Management");
	public static String piperManagementTable = "manage-piper-log";
	/////////////////////////////////////End Piper Management Elements/////////////////////////////////////////////////////
	
	
	/////////////////////////////////////////Data Template Management Elements/////////////////////////////////////////////

	public static By dataTemplateTitle = By.id("Data Template Management");
	public static By dtmCreateButton = By.id("create-data-format");
	public static String dtmCreatePopupGetTitle = "/html/body/app-root/div[1]/app-manage-dataformat/app-popup-component/div/div/div/div[2]/div";
	public static String dtmResetButton = "/html/body/app-root/div[1]/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[2]/div/button[1]";
	public static String dtmSaveButton = "/html/body/app-root/div[1]/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[2]/div/button[2]";
	public static By dtmName = By.id("nameId");
	public static By dtmDesc = By.id("DescId");
	
	
	public static By dtmClmName = By.id("ColNameID");
	public static By dtmClmType = By.id("ColTypeId");
	public static By dtmClmDefaultValue = By.id("defaultValueId");
	public static By dtmClmLength = By.id("num-colLengthId");
	public static By dtmKeyField = By.cssSelector("#key-field div");
	public static By dtmIdentificationField = By.id("identity-field");
	public static By dtmClmReset = By.id("btn-reset-field");
	public static By dtmClmAdd = By.id("btn-add-field");
	public static By dtmClmSave = By.id("btn-save-field");
	public static By dtmClmEdit = By.id("edit-field-1");
	public static By dtmClmDelete = By.id("delete-field-1");
	public static By dtmClientMappingOpenButton = By.id("create-client-mapping");
	public static By dtmClientMappingClientDropdown = By.cssSelector("#ClientId .ng-arrow-wrapper");
	public static By dtmInactivateTemplate = By.cssSelector("#status-data-format  div");
	
	public static By dtmNameValidation = By.id("nameId-error-container");
	public static By dtmDescValidation = By.id("DescId-error-container");
	public static By dtmClmNameValidation = By.id("ColNameID-error-container");
	public static By dtmClmTypeValidation = By.cssSelector(".floating-error");
	public static By dtmClmLengthValidation = By.cssSelector("colLengthId-error-container");

	///////////////////////////////////////End Data Template Management Elements////////////////////////////////////////////////////


	/////////////////////////////////////////////////Data Upload////////////////////////////////////////////////////////////////////
	
	public static By dataUploadTitle = By.id("Data Upload");
	public static String duUploadDropdown = "#OrgnTypeID > div > span";
	public static String duUploadDropdownSelectClient = "/html/body/app-root/div/app-manage-dataupload/div[1]/form/div/div[2]/div[1]/div/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[2]";
	public static String duClientInput = "#ClientId > div > div > div.ng-input > input[type=text]";
	public static String duClientInputSelect = "/html/body/app-root/div/app-manage-dataupload/div[1]/form/div/div[2]/div[1]/div/div[2]/ng-select/ng-dropdown-panel/div/div[2]/div";
	
	///////////////////////////////////////////////End Data Upload /////////////////////////////////////////////////////////////////

	
	/////////////////////////////////////////////Poultry Management/////////////////////////////////////////////////////////////////
	
	public static String poultryStartDateSelect = "/html/body/app-root/div/app-manage-poultry/div[3]/app-popup-component/div/div/div/div[3]/app-create-intervention/form[2]/div/div/div/div[4]/div[1]/div[1]/app-custom-date-picker/div/div[2]/dp-date-picker/div[2]/div/dp-day-calendar/div/div/div[4]/button[4]";
	public static String poultryStartDateSelect1 =  "/html/body/app-root/div/app-manage-poultry/div[3]/app-popup-component/div/div/div/div[3]/app-create-intervention/form[1]/div/div/div/div[4]/div[1]/div[1]/app-custom-date-picker/div/div[2]/dp-date-picker/div[2]/div/dp-day-calendar/div/div/div[4]/button[4]";
	public static String poultryStartDateVaccine = "//app-create-cocci-intervention/div/div[3]/form/div/div/div/div[1]/div/div[1]/app-custom-date-picker/div/div[2]/dp-date-picker/div[2]/div/dp-day-calendar/div/div/div[4]/button[4]";
	
	public static String poultryBrandName = "/html/body/app-root/div/app-manage-poultry/div[3]/app-popup-component/div/div/div/div[3]/app-create-intervention/form[1]/div/div/div/div[6]/div[1]/div/app-custom-input-dropdown/div/img";
	public static String poultryBrandNameSelect = "/html/body/app-root/div/app-manage-poultry/div[3]/app-popup-component/div/div/div/div[3]/app-create-intervention/form[1]/div/div/div/div[6]/div[1]/div/app-custom-input-dropdown/div/div[2]/label[1]";
	public static String poultryAdminMethod = "/html/body/app-root/div/app-manage-poultry/div[3]/app-popup-component/div/div/div/div[3]/app-create-intervention/form[1]/div/div/div/div[4]/div[4]/div/app-custom-input-dropdown/div/img";
	public static String poultryAdminMethodSelect = "/html/body/app-root/div/app-manage-poultry/div[3]/app-popup-component/div/div/div/div[3]/app-create-intervention/form[1]/div/div/div/div[4]/div[4]/div/app-custom-input-dropdown/div/div[2]/label[1]";
	
	///////////////////////////////////////////End Poultry Management///////////////////////////////////////////////////////////////
	
	

	///////////////////////////////////////////////Salmonella Log Elements/////////////////////////////////////////////////////////

	public static By salmonellaLogTitle = By.id("Salmonella Log");
	public static String salmonellaLogTable = "salmonella-data-log";
	
	public static String slLaneCol = "0";
	public static String slSampleIDCol = "1";
	public static String slQCCodeCol = "2";
	public static String slResultStatusCol = "3";
	public static String slResultCol = "4";
	public static String slAssayCol = "5";
	public static String slResultIDCol = "6";
	public static String slDateCol = "7";
	public static String slTimeCol = "8";
	public static String slSiteNameCol = "9";
	public static String slSiteIDCol = "10";
	public static String slSiteTypeCol = "11";
	public static String slSampleMatrixCol = "12";
	public static String slDilutionFactorCol = "13";
	public static String slCSampleIDCol = "14";
	public static String slReceivedDateCol = "15";
	public static String slCartridgeIDCol = "16";
	public static String slInstrumentIDCol = "17";
	public static String slW1CellCountCol = "18";
	public static String slW1PCCountCol = "19";
	public static String slW1MeanIntensityCol = "20";
	public static String slW2CellCountCol = "21";
	public static String slW2CPCCountCol = "22";
	public static String slW2MeanIntensityCol = "23";
	public static String slPiperUserCol = "24";
	public static String slKitLotCol = "25";
	public static String slImprocIDCol = "26";
	public static String slTestSiteIDCol = "27";
	public static String slTestSiteNameCol = "28";
	public static String slRequestedAssayCol = "29";
	public static String slFlockIDCol = "30";
	public static String slRunTypeCol = "31";
	public static String slCollectionDateCol = "32";
	public static String slFarmCol = "33";
	public static String slComplexCol = "34";
	public static String slFieldAccessCol = "35";
	public static String footerCount = ".filter-popup__footer--count";
	
	public static String slAuditLaneColCss = "5";
	public static String slAuditLaneCol = "0";
	public static String slAuditSampleIDCol = "1";
	public static String slAuditQCCodeCol = "2";
	public static String slAuditResultStatusCol = "3";
	public static String slAuditResultCol = "4";
	public static String slAuditAssayCol = "5";
	public static String slAuditDateCol = "6";
	public static String slAuditTimeCol = "7";
	public static String slAuditSiteNameCol = "8";
	public static String slAuditSiteIDCol = "9";
	public static String slAuditSiteTypeCol = "10";
	public static String slAuditSampleMatrixCol = "11";
	public static String slAuditDilutionFactorCol = "12";
	public static String slAuditCSampleIDCol = "13";
	public static String slAuditReceivedDateCol = "14";
	public static String slAuditCartridgeIDCol = "15";
	public static String slAuditInstrumentIDCol = "16";
	public static String slAuditW1CellCountCol = "17";
	public static String slAuditW1PCCountCol = "18";
	public static String slAuditW1MeanIntensityCol = "19";
	public static String slAuditW2CellCountCol = "20";
	public static String slAuditW2CPCCountCol = "21";
	public static String slAuditW2MeanIntensityCol = "22";
	public static String slAuditPiperUserCol = "23";
	public static String slAuditKitLotCol = "24";
	public static String slAuditImprocIDCol = "25";
	public static String slAuditTestSiteIDCol = "26";
	public static String slAuditTestSiteNameCol = "27";
	public static String slAuditRequestedAssayCol = "28";
	public static String slAuditFlockIDCol = "29";
	public static String slAuditRunTypeCol = "30";
	public static String slAuditCollectionDateCol = "31";
	public static String slAuditFarmCol = "32";
	public static String slAuditComplexCol = "33";
	
	public static String slToday = "#list-title_range-0";
	public static String slLast24Hours = "#list-title_range-1";
	public static String slLast7Days = "#list-title_range-2";
	public static String slLast30Days = "#list-title_range-3";
	public static String slThisMonth = "#list-title_range-4";
	public static String slLastMonth = "#list-title_range-5";	
					
	public static String slShowFilter = "_show-filter";
	public static String slSortFilter = "sort-";
	public static String slApplyFilter = "_apply";
	public static String slClearFilter = "_clear-filter";
	public static String slSearchInput = "_search-input";
	
	public static String slLane = "laneNum";
	public static String slSampleID = "sampleId";
	public static String slQCCode = "countOutcome";
	public static String slResultStatus = "result_status";
	public static String slResult = "outcome";
	public static String slAssay = "pathogen";
	public static String slResultID = "runId";
	public static String slResultTime = "time";
	public static String slResultDate = "scanDateTime";
	public static String slCollectionSiteID = "collection_site_id";
	public static String slCollectionSiteName = "site_id";
	public static String slCollectionSiteType = "collection_site_type"; 
	public static String slSampleMatrix = "sample_matrix";
	public static String slDilutionFactor = "dilution_factor";
	public static String slCustomerSampleID = "customer_sample_id";
	public static String slDateReceived = "metadata_date_recieved";
	public static String slCartridgeID = "cartridgeId";
	public static String slInstrumentID = "instrumentId";
	public static String slW1CellCount = "w1CellCount";
	public static String slW1PCCount = "w1PCCount";
	public static String slW1MeanIntensity = "w1CellMeanIntensity";
	public static String slW2CellCount = "w2CellCount";
	public static String slW2PCCount = "w2PCCount";
	public static String slW2MeanIntensity = "w2CellMeanIntensity";
	public static String slPiperUser = "user_name";
	public static String slkitLot = "kit_lot";
	public static String slImprocVersion = "version";
	public static String slTestSiteID = "testSiteId";
	public static String slTestSiteName = "testSiteName";
	public static String slRequestedAssay = "requested_assay";
	public static String slFlockID = "flock_id";
	public static String slRunType = "runType";
	public static String slCollectionDate = "collectionDate";
	public static String slFarm = "farm";
	public static String slComplex = "complex";
	
	public static String slLoadRow = "/html/body/app-root/div/app-salmonella-log/div[1]/div/div[2]/div[2]/div[5]/div/table/tbody/tr[1]/td[1]";
	public static String slLoadRed = ".red";  
	public static String slLoadYellow = ".yellow"; 
	public static String slLoadGreen = ".green";

	public static String slTotalPages = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[15]/p";

	public static String closeAudit = "#close-gen-modal";
	
	public static String slPngHover = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[1]/div[1]";
	public static String slCsvHover = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[2]";
	public static String slPng = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[1]/div[1]/span/app-custom-export-png/i";
	public static String slDownloadButton = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/span/i";
	public static String slCsv = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/span/i";	
	public static String slExportDataTemplate = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/div/ul/div/li/label";
	public static String slSampleMetaDataExport = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/div[1]/ul/li/label";

	public static String slTable = "salmonella-data-log";
	public static String slCSVFileName = "Salmonella Log - ";
	

	/////////////////////////////////////////////////End Salmonella Log Elements////////////////////////////////////////////////////////////


	//////////////////////////////////////////////////Coccidia Log Elements/////////////////////////////////////////////////////////
	
	public static By coccidiaLogTitle = By.id("Coccidia Log");
	public static String coccidiaLogTable = "coccidia-data-log";
	public static String clLaneCol = "0";
	public static String clSampleIDCol = "1";
	public static String clQCCodeCol = "2";
	public static String clResultStatusCol = "3";
	public static String clTotalOPGCol = "4";
	public static String clSmallOPGCol = "5";
	public static String clMediumOPGCol = "6";
	public static String clLargeOPGCol = "7";
	public static String clResultCol = "8";
	public static String clAssayCol = "9";
	public static String clResultIDCol = "10";
	public static String clDateCol = "11";
	public static String clTimeCol = "12";
	public static String clSiteNameCol = "13";
	public static String clSampleMatrixCol = "14";
	public static String clCSampleIDCol = "15";
	public static String clReceivedDateCol = "16";
	public static String clCatridgeIDCol = "17";
	public static String clInstrumentIDCol = "18";
	public static String clTotalCountCol = "19";
	public static String clSmallCountCol = "20";
	public static String clMediumCountCol = "21";
	public static String clLargeCountCol = "22";
	public static String clMeanIntensityCol = "23";
	public static String clPiperUserCol = "24";
	public static String clKitLotCol = "25";
	public static String clImprocIDCol = "26";
	public static String clTestSiteIDCol = "27";
	public static String clTestSiteNameCol = "28";
	public static String clCollectionSiteTypeCol = "29";
	public static String clRequestedAssayCol = "30";
	public static String clFlockIDCol = "31";
	public static String clRunTypeCol = "32";
	public static String clCollectionDateCol = "33";
	public static String clCollectionSiteIDCol = "34";
	public static String clFarmCol = "35";
	public static String clComplexCol = "36";
	public static String clVaccinceProgramCol = "37";
	public static String clFeedProgramCol = "38";
	public static String clFlockDayCol = "39";
	public static String clFieldAccessCol = "40";
	
	public static String clAuditLaneCol = "0";
	public static String clAuditSampleIDCol = "1";
	public static String clAuditQCCodeCol = "2";
	public static String clAuditResultStatusCol = "3";
	public static String clAuditAssayCol = "9";
	public static String clAuditDateCol = "10";
	public static String clAuditTimeCol = "11";
	public static String clAuditSiteNameCol = "12";
	public static String clAuditSampleMatrixCol = "13";
	public static String clAuditCSampleIDCol = "14";
	public static String clAuditReceivedDateCol = "15";
	public static String clAuditCartridgeIDCol = "16";
	public static String clAuditInstrumentIDCol = "17";
	public static String clAuditTotalCountCol = "18";
	public static String clAuditSmallCountCol = "19";
	public static String clAuditMediumCountCol = "20";
	public static String clAuditLargeCountCol = "21";
	public static String clAuditMeanIntensityCol = "22";
	public static String clAuditPiperUserCol = "23";
	public static String clAuditKitLotCol = "24";
	public static String clAuditImprocIDCol = "25";
	public static String clAuditTestSiteIDCol = "26";
	public static String clAuditTestSiteNameCol = "27";
	public static String clAuditCSiteTypeCol = "28";
	public static String clAuditRequestedAssayCol = "29";
	public static String clAuditFlockIDCol = "30";
	public static String clAuditRunTypeCol = "31";
	public static String clAuditCollectionDateCol = "32";
	public static String clAuditCollectionSiteIDCol = "33";
	public static String clAuditFarmCol = "34";
	public static String clAuditComplexCol = "35";
	public static String clAuditVaccinceProgramCol = "36";
	public static String clAuditFeedProgramCol = "37";
	public static String clAuditFlockDayCol = "38";
	
	public static String clShowFilter = "_show-filter";
	public static String clSortFilter = "sort-";
	public static String clApplyFilter = "_apply";
	public static String clClearFilter = "_clear-filter";
	public static String clSearchInput = "_search-input";
	
	public static String clLane = "laneNum";
	public static String clSampleID = "sampleId";
	public static String clQCCode = "countOutcome";
	public static String clResultStatus = "result_status";
	public static String clTotalOPG = "totalOPG";
	public static String clSmallOPG = "totalSmallOPG";
	public static String clMediumOPG = "totalMediumOPG";
	public static String clLargeOPG = "totalLargeOPG";
	public static String clResult = "outcome";
	public static String clAssay = "pathogen";
	public static String clResultID = "runId";
	public static String clResultTime = "time";
	public static String clResultDate = "scanDateTime";
	public static String clCollectionSiteName = "site_id";
	public static String clSampleMatrix = "sample_matrix";
	public static String clCustomerSampleID = "customer_sample_id";
	public static String clDateReceived = "metadata_date_recieved";
	public static String clCartridgeID = "cartridgeId";
	public static String clInstrumentID = "instrumentId";
	public static String clTotalCount = "oocystTotalCount";
	public static String clSmallCount = "oocystSmallCount";
	public static String clMediumCount = "oocystMediumCount";
	public static String clLargeCount = "oocystLargeCount";
	public static String clMeanIntensity = "oocystMeanIntensity";
	public static String clPiperUser = "user_name";
	public static String clkitLot = "kit_lot";
	public static String clImprocVersion = "version";
	public static String clTestSiteID = "testSiteId";
	public static String clTestSiteName = "testSiteName";
	public static String clCollectionSiteType = "collection_site_type";
	public static String clRequestedAssay = "requested_assay";
	public static String clFlockID = "flock_id";
	public static String clRunType = "runType";
	public static String clCollectionDate = "collectionDate";
	public static String clCollectionSiteID = "collection_site_id";
	public static String clFarm = "farm";
	public static String clComplex = "complex";
	public static String clVaccineProgram = "vaccineProgram";
	public static String clFeedProgram = "feedProgram";
	public static String clFlockDay = "flockDay";
	
	public static String clToday = "#list-title_range-0";
	public static String clLast24Hours = "#list-title_range-1";
	public static String clLast7Days = "#list-title_range-2";
	public static String clLast30Days = "#list-title_range-3";
	public static String clThisMonth = "#list-title_range-4";
	public static String clLastMonth = "#list-title_range-5";	

	
	
//	public static String clToday = "div[class='table-responsive apl-data-log-table apl-scrollbar'] p:nth-child(1)";
//	public static String clLast24Hours = "body app-root p:nth-child(2)";
//	public static String clLast7Days = "body app-root p:nth-child(3)";
//	public static String clLast30Days = "body app-root p:nth-child(4)";
//	public static String clThisMonth = "body app-root p:nth-child(5)";
//	public static String clLastMonth = "body app-root p:nth-child(6)";	
	
	public static String clTotalPages = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[15]/p";

	public static String clTable = "coccidia-data-log";

	//////////////////////////////////////////////////End Coccidia Log Elements///////////////////////////////////////////////////////

	
	///////////////////////////////////////////////Sites Log Elements/////////////////////////////////////////////////////////

	public static By sitesLogTitle = By.id("Sites Log");
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

	public static String sitesPngHover = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[1]/div[1]";
	public static String sitesCsvHover = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[2]";
	public static String sitesPng = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[1]/div[1]/span/app-custom-export-png/i";
	public static String sitesDownloadButton = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/span/i";
	public static String sitesCsv = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/span/i";	
	public static String sitesExportDataTemplate = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/div/ul/div/li/label";
	public static String sitesSampleMetaDataExport = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/div[1]/ul/li/label";


	/////////////////////////////////////////////////End Sites Log Elements////////////////////////////////////////////////////////////
	
	
	///////////////////////////////////////////////Flock Registration Elements/////////////////////////////////////////////////////////
	public static By FlockRegistrationTitle = By.id("Flock Management");
	public static String FlockRegistrationTable = "manage-flock-registration";
	public static String flockIntegratorIDCol = "0";
	public static String flockSiteIDCol = "1";
	public static String flockPlacementDateCol = "2";
	public static String flockNumBirdsPlacedCol = "3";
	public static String flockBirdSexCol = "4";
	public static String flockBirdBreedCol = "5";
	public static String flockProcessingDateCol = "6";
	public static String flockProcessingSiteIDCol = "7";
	public static String flockNumBirdsDOACol = "8";
	public static String flockNumBirdsProcessedCol = "9";
	public static String flockTotalWeightProcessedLBCol = "10";
	public static String flockTotalWeightProcessedKGCol = "11";
	public static String flockTotalFeedWeightLBCol = "12";
	public static String flockTotalFeedWeightKGCol = "13";
	public static String flockTotalWeightCondemnedLBCol = "14";
	public static String flockTotalWeightCondemnedKGCol = "15";
	public static String flockNumBirdsCondemnedWholeCol = "16";
//	public static String flockBirdsWeightCondemnedLBCol = "";
//	public static String flockBirdsWeightCondemnedKGCol = "";
	public static String flockPartsWeightCondemnedLBCol = "17";
	public static String flockPartsWeightCondemnedKGCol = "18";
//	public static String flockTotalAmountPaidCol = "";
	public static String flockTotalGrowerPayCol = "19";
	public static String flockAmountPaidPePoundCol = "20";
//	public static String flockTotalAmountPaidWeightLBCol = "";
	public static String flockTotalCostPerWeightCol = "21";
	public static String flockLiveabilityCol = "22";
	public static String flockMortalityCol = "23";
	public static String flockAvgDailyWeightGainLBCol = "24";
	public static String flockAvgAgeBirdsProcessedCol = "25";
	public static String flockAvgBirdWeightLBCol = "26";
	public static String flockAvgBirdWeightKGCol = "27";
	public static String flockFCRCol = "28";
	public static String flockNumBirdsSoldCol = "29";
	public static String flockAdjFCRCol = "30";
	public static String flockAGradePawsCol = "31";
//	public static String flockLast7DaysCol = "";
	public static String flockOutTimeDaysCol = "32";
	public static String flockBirdSizeCol = "33";
	public static String flockMarketingProgramCol = "34";
	public static String flockUSDAPlantIDCol = "35";
	public static String flockDowngradePawsPercCol = "36";
	public static String flockTotalMortalityCol = "37";
	public static String flockTimeFrameCol = "38";
	public static String flockUniqueFlockIDCol = "39";
	public static String flockFieldAccessCol = "40";
	
	
	public static String flockAuditIntegratorIDCol = "";
	public static String flockAuditSiteIDCol = "0";
	public static String flockAuditPlacementDateCol = "1";
	public static String flockAuditNumBirdsPlacedCol = "2";
	public static String flockAuditBirdSexCol = "3";
	public static String flockAuditBirdBreedCol = "4";
	public static String flockAuditProcessingDateCol = "5";
	public static String flockAuditProcessingSiteIDCol = "6";
	public static String flockAuditNumBirdsDOACol = "7";
	public static String flockAuditNumBirdsProcessedCol = "8";
	public static String flockAuditTotalWeightProcessedLBCol = "9";
	public static String flockAuditTotalWeightProcessedKGCol = "10";
	public static String flockAuditTotalFeedWeightLBCol = "11";
	public static String flockAuditTotalFeedWeightKGCol = "12";
	public static String flockAuditTotalWeightCondemnedLBCol = "13";
	public static String flockAuditTotalWeightCondemnedKGCol = "14";
	public static String flockAuditNumBirdsCondemnedWholeCol = "15";
	public static String flockAuditPartsWeightCondemnedLBCol = "16";
	public static String flockAuditPartsWeightCondemnedKGCol = "17";
	public static String flockAuditTotalGrowerPayCol = "18";
	public static String flockAuditAmountPaidPerPoundCol = "19";
	public static String flockAuditTotalCostPerWeightCol = "20";
	public static String flockAuditLiveabilityCol = "21";
	public static String flockAuditMortalityCol = "22";
	public static String flockAuditAvgDailyWeightGainLBCol = "23";
	public static String flockAuditAvgAgeBirdsProcessedCol = "24";
	public static String flockAuditAvBirdWeightLBCol = "25";
	public static String flockAuditAvBirdWeightKGCol = "26";
	public static String flockAuditFCRCol = "27";
	public static String flockAuditNumBirdsSoldCol = "28";
	public static String flockAuditAdjFCRCol = "29";
	public static String flockAuditAGradePawsCol = "30";
	public static String flockAuditOutTimeDaysCol = "31";
	public static String flockAuditBirdSizeCol = "32";
	public static String flockAuditMarketingProgramCol = "33";
	public static String flockAuditUSDAPlantIDCol = "34";
	public static String flockAuditDowngradePawsPercCol = "35";
	public static String flockAuditTotalMortalityCol = "36";
	public static String flockAuditTimeFrameCol = "37";	
	public static String flockAuditUniqueFlockIDCol = "38";	
	
	public static String flockShowFilter = "_show-filter";
	public static String flockSortFilter = "sort-";
	public static String flockApplyFilter = "_apply";
	public static String flockClearFilter = "_clear-filter";

	public static String flockIntegratorID = "integratorFlockId";
	public static String flockSiteID = "siteId";
	public static String flockHatchDate = "hatchDate";
	public static String flockPlacementDate = "placementDate";
	public static String flockNumBirdsPlaced = "numOfBirdsPlaced";
	public static String flockBirdType = "birdType";
	public static String flockBirdSex = "birdSex";
	public static String flockBirdBreed = "birdBreed";
	public static String flockProcessingDate = "processingDate";
	public static String flockProcessingSiteID = "processingSiteId";
	public static String flockNumBirdsDOA = "numBirdsDoaPlant";
	public static String flockNumBirdsProcessed = "numBirdsProcessed";
	public static String flockTotalWeightProcessedLB = "totalWeightProcessedLB";
	public static String flockTotalWeightProcessedKG = "totalWeightProcessedKG";
	public static String flockTotalFeedWeightLB = "totalFeedWeightLB";
	public static String flockTotalFeedWeightKG = "totalFeedWeightKG";
	public static String flockTotalWeightCondemendLB  = "totalWeightCondemnedLB";
	public static String flockTotalWeightCondemendKG  = "totalWeightCondemnedKG";
	public static String flockNumBirdsCondemnedWhole = "numBirdsCondemnedWhole";
	public static String flockBirdsWeightCondemnedLB = "birdWeightCondemnedLB";
	public static String flockBirdsWeightCondemnedKG = "birdWeightCondemnedKG";
	public static String flockPartsWeightCondemnedLB = "partsWeightCondemnedLB";
	public static String flockPartsWeightCondemnedKG = "partsWeightCondemnedKG";
	public static String flockTotalGrowerPay = "totalAmountPaidUSD";
	public static String flockAmountPaidPerPound = "totalAmountPaidUSDWeightLB";
	public static String flockTotalCostUSDPerWeight = "totalCostUSDPerWeightLB";
	public static String flockLiveability = "livabilityPerc";
	public static String flockMortality = "mortalityPerc";
	public static String flockAvgDailyWeightGainLB = "avgDailyWeightGainLB";
	public static String flockAvgAgeBirdsProcessed = "avgAgeOfBirdProcessedDays";
	public static String flockAvgBirdWeightLB = "avgBirdWeightLB";
	public static String flockAvgBirdWeightKG = "avgBirdWeightKG";
	public static String flockFCR = "fcr";
	public static String flockNumBirdsSold = "numBirdsSold";
	public static String flockAdjFCR = "adjFcr";
	public static String flockAGradePaws = "aGradePawsPerc";
	public static String flockOutTimeDays = "outTimeDays";
	public static String flockBirdSize = "birdSize";
	public static String flockMarketingProgram = "marketingProgram";
	public static String flockUSDAPlantID = "usdaPlantId";
	public static String flockDowngradePawsPerc = "downgradePawsPerc";
	public static String flockTotalMortality = "totalMortality";
	public static String flockTimeFrame = "timeFrame";
	public static String flockUniqueFlockID = "uniqueFlockId";
	
	public static By flockCreateButton = By.id("create-flock");
	public static By flockIntegratorFlockID = By.cssSelector("#integratorFlockId input");
	public static By flockBirdSizeExpandDropDown = By.cssSelector("#birdSizeId input");
	public static By flockFarmExpandDropdown = By.cssSelector("#farmSiteId .down");
	public static By flockFarmSeach = By.id("farmSiteId_search");
	public static By flockHousePlacementDots = By.xpath("//div[8]/div/div[2]/i");
	public static By flockProgramDetailsDots = By.xpath("//div[9]/div/div[2]/i");
	public static By flockSelectHouses = By.xpath("//*[text() = 'Select Houses']");
	public static By flockProgramExpandDropDown = By.cssSelector("#programId input");
	public static By flockProgramStartDate = By.cssSelector("#startDate img");
	public static By flockHouseSaveButton = By.id("btn-save-house");
	public static By flockAdministrationMethod = By.cssSelector("#administrationMethodId input");
	public static By flockProgramSaveButton = By.id("btn-save-program");
	
	/////////////////////////////////////////////////End Flock Registration Elements////////////////////////////////////////////////////////////
	
	
	///////////////////////////////////////////////Coccidia Timeline Log Elements/////////////////////////////////////////////////////////

	public static String ctlDateTo = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/form/div/div[2]/input";
	public static String ctlToday = "/html/body/app-root/div/app-coccidia-timeline/div[1]/div/div/div[2]/div[1]/div/div/div[2]/div[2]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[1]/button";
	public static String ctlLast24Hours = "/html/body/app-root/div/app-coccidia-timeline/div[1]/div/div/div[2]/div[1]/div/div/div[2]/div[2]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[2]/button";
	public static String ctlLast7Days = "/html/body/app-root/div/app-coccidia-timeline/div[1]/div/div/div[2]/div[1]/div/div/div[2]/div[2]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[3]/button";
	public static String ctlLast30Days = "/html/body/app-root/div/app-coccidia-timeline/div[1]/div/div/div[2]/div[1]/div/div/div[2]/div[2]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[4]/button";
	public static String ctlThisMonth = "/html/body/app-root/div/app-coccidia-timeline/div[1]/div/div/div[2]/div[1]/div/div/div[2]/div[2]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[5]/button";
	public static String ctlLastMonth = "/html/body/app-root/div/app-coccidia-timeline/div[1]/div/div/div[2]/div[1]/div/div/div[2]/div[2]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[6]/button";
	
	public static String ctlRecordNumber = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[2]/div[1]/span[1]";
	public static String ctlSampleIDFirstRecord = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[1]/div/div[2]/div[2]/ul/li[3]";

	public static String ctlTimelinePngHover = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[1]/div[1]";
	public static String ctlTimelinePng = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[1]/div[1]/span/app-custom-export-png/i";

	public static String ctlOCountPngHover = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[2]/div/div[2]/div/div[1]";
	public static String ctlOCountPng = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[2]/div/div[2]/div/div[1]/div/div[1]/span/app-custom-export-png/i";

	public static String ctlLast10PngHover = "/html/body/app-root/div/app-coccidia-timeline/div[1]/div/div/div[2]/div[2]/div/div[1]/div/div[1]";
	public static String ctlLast10Png = "/html/body/app-root/div/app-coccidia-timeline/div[1]/div/div/div[2]/div[2]/div/div[1]/div/div[1]/div/div/span/app-custom-export-png/i";

	public static String ctlOverTimePngHover = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[2]/div/div[3]/div/div[1]";
	public static String ctlOverTimePng = "/html/body/app-root/div/app-coccidia-timeline/div[1]/div/div/div[2]/div[2]/div/div[3]/div/div[1]/div/div/span/app-custom-export-png/i";

	////////////////////////////////////////////////End Coccidia Timeline Elements///////////////////////////////////////////////////////


	////////////////////////////////////////////////////Profile Setting Elements/////////////////////////////////////////////////////////

	public static String profileButton = "/html/body/app-root/app-layout-component/div[3]/div[2]/button";   //uat
	public static String profileGetTitle = "  /html/body/app-root/app-layout-component/div[2]/div[1]/p";    //uat
	public static String profileBackButton = "/html/body/app-root/app-layout-component/div[3]/div[2]/button";  //uat

	public static String profileFirstName = "/html/body/app-root/div[1]/app-manage-user-component/app-manage-profile-component/div/form/div/div/div/div[2]/div[3]/div[1]/div/input";
	public static String profileLastName = "/html/body/app-root/div[1]/app-manage-user-component/app-manage-profile-component/div/form/div/div/div/div[2]/div[3]/div[2]/div/input";
	public static String profileCellCode = "/html/body/app-root/div[1]/app-manage-user-component/app-manage-profile-component/div/form/div/div/div/div[2]/div[4]/div[1]/div/div[1]/ng-select/div/span";
	public static String profileCellCodeSelect = "/html/body/app-root/div[1]/app-manage-user-component/app-manage-profile-component/div/form/div/div/div/div[2]/div[4]/div[1]/div/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div";
	public static String profileCellNo = "/html/body/app-root/div[1]/app-manage-user-component/app-manage-profile-component/div/form/div/div/div/div[2]/div[4]/div[1]/div/div[2]/input";
	public static String profilePhoneCode = "/html/body/app-root/div[1]/app-manage-user-component/app-manage-profile-component/div/form/div/div/div/div[2]/div[4]/div[2]/div/div[1]/ng-select/div/span";
	public static String profilePhoneCodeSelect = "/html/body/app-root/div[1]/app-manage-user-component/app-manage-profile-component/div/form/div/div/div/div[2]/div[4]/div[2]/div/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div";
	public static String profilePhoneNo = "/html/body/app-root/div[1]/app-manage-user-component/app-manage-profile-component/div/form/div/div/div/div[2]/div[4]/div[2]/div/div[2]/input";
	public static String profileSaveButton = "/html/body/app-root/div[1]/app-manage-user-component/app-manage-profile-component/div/form/div/div/div/div[2]/div[5]/button";

	public static String profileFirstNameValidation = "/html/body/app-root/div[1]/app-manage-user-component/app-manage-profile-component/div/form/div/div/div/div[2]/div[3]/div[1]/div/div"; 
	public static String profileLastNameValidation = "/html/body/app-root/div[1]/app-manage-user-component/app-manage-profile-component/div/form/div/div/div/div[2]/div[3]/div[2]/div/div";
	public static String profileCellNoValidation = "/html/body/app-root/div[1]/app-manage-user-component/app-manage-profile-component/div/form/div/div/div/div[2]/div[4]/div[1]/div/div[2]/div/div";
	public static String profilePhoneNoValidation = "/html/body/app-root/div[1]/app-manage-user-component/app-manage-profile-component/div/form/div/div/div/div[2]/div[4]/div[2]/div/div[2]/div/div";

	public static String profileFirstNameExpected = "First name is required";
	public static String profileLastNameExpected = "Last name is required";
	public static String profileCellCodeExpected = "";
	public static String profileCellNoExpected = "Cell number is required";

	public static String profilePhoneCodeExpected = "";
	public static String profilePhoneNoExpected = "Phone number is required";

	///////////////////////////////////////////////End Profile Setting Elements///////////////////////////////////////////////////////
	
	
	/////////////////////////////////////////////////SampleMetadata Column////////////////////////////////////////////////////////////////
	
	public static int metadata_ResultID = 0;
	public static int metadata_CollectionSiteID = 1;
	public static int metadata_KitLot = 2;
	public static int metadata_SampleMatrix = 3;
	public static int metadata_LabSampleID = 4;
	public static int metadata_DateReceived = 5;
	public static int metadata_CustomerSampleID = 6;
	public static int metadata_CollectedBy = 7;
	public static int metadata_RequestedAssay = 8;
	public static int metadata_FlockID = 9;
	public static int metadata_Complex = 12;
	public static int metadata_Farm = 13; 
	public static int metadata_Lane = 16;
	public static int metadata_ResultDate = 17;
	public static int metadata_ResultTime = 18;
	public static int metadata_CartridgeID = 19;
	public static int metadata_InstrumentID = 20;
	public static int metadata_PiperUser = 21;
	public static int metadata_CollectionDate = 23;
	

	//////////////////////////////////////////////End SampleMetadata Column///////////////////////////////////////////////////////////////
}
