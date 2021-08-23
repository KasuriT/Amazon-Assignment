package Test.Ancera;

import java.util.Random;


import org.openqa.selenium.support.ui.WebDriverWait;

public class Test_Elements {

	public static WebDriverWait wait = new WebDriverWait(Helper.driver,90);

	//////////////////////////////////////////////////Login Elements//////////////////////////////////////////////////////////

	public static String getTitle = "/html/body/app-root/app-layout-component/div[3]/div[1]/p";
	public static String getHeadingTitle = "/html/body/app-root/app-layout-component/div[3]/div[3]/p";  

	///////////////////////////////////////////////End Login Elements/////////////////////////////////////////////////////////


	/////////////////////////////////////////////ForgotPassword Elements//////////////////////////////////////////////////////

	public static String gmailEmail = "/html/body/div[1]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div/form/span/section/div/div/div[1]/div/div[1]/div/div[1]/input";
	public static String gmailPassword = "/html/body/div[1]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div/form/span/section/div/div/div[1]/div[1]/div/div/div/div/div[1]/div/div[1]/input";
	public static String gmailSecurityCheck = "/html/body/div[1]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div/form/span/section/div/div/div/ul/li[1]/div";
	public static String gmailSecurityEmail = "/html/body/div[1]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div/form/span/section/div/div/div[2]/div[1]/div/div[1]/div/div[1]/input";

	///////////////////////////////////////////End ForgotPassword Elements////////////////////////////////////////////////////


	/////////////////////////////////////////////User Management Elements////////////////////////////////////////////////////

	public static String userCreateButton = "/html/body/app-root/div/app-manage-user-component/button";
	public static String userPopupNextButton1Update = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-update-user-component/form/div[2]/div/button";
	public static String userPopupNextButton2Update = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-update-user-component/form/div[2]/div/button[2]";
	public static String userPopupSaveButtonUpdate = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-update-user-component/form/div[2]/div/button[2]";
	public static String userPopupNextButton1 = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[2]/div/button[2]";
	public static String userPopupNextButton2 = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[2]/div/button[3]";
	public static String userPopupSaveButton = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[2]/div/button[2]";
	public static String userPopupCloseButton = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[1]/p";
	public static String userPopupResetButton = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[2]/div/button[1]";
	public static String userPopupGetTitle = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[2]/div";

	public static String firstNameValidation = "/html/body/app-root/div/app-manage-user-v2/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[1]/div[1]/div";
	public static String lastNameValidation = "/html/body/app-root/div/app-manage-user-v2/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[1]/div[2]/div";
	public static String phoneCodeValidation= "/html/body/app-root/div/app-manage-user-v2/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[1]/div[5]/div/div[1]/div/div";
	public static String phoneNumberValidation = "/html/body/app-root/div/app-manage-user-v2/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[1]/div[5]/div/div[2]/div/div";
	public static String emailValidation = "/html/body/app-root/div/app-manage-user-v2/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[2]/div[1]/div";
	public static String piperValidation = "/html/body/app-root/div/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[3]/div[5]/div[2]/div/div[2]";

	public static String firstnameexpected = "First name is required";
	public static String lastnameexpected = "Last name is required";
	public static String phonecodeexpected = "Select country code";
	public static String phonenoexpected = "Phone number is required";
	public static String emailexpected = "Email is required";
	public static String organizationTypeexpected = "Select organization type";
	public static String organizationexpected = "Select organization";
	public static String invalidemailexpected = "Invalid email";
	public static String piperexpected = "Select PIPER";

	public static String userFirstName = "//*[@id=\"firstNameId\"]";
	public static String userLastName = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[1]/div[2]/input";
	public static String userLastNameUpdate = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-update-user-component/form/div[1]/div[2]/div/div[1]/div[2]/input";
	public static String userPhoneCode = "//*[@id=\"phoneCodeId\"]/div";
	public static String userPhoneCodeSelect = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[1]/div[5]/div/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div";
	public static String userOrganizationInActiveText = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[2]/div[3]/ng-select/ng-dropdown-panel/div/div[2]/div";
	public static String userPhoneNo = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[1]/div[5]/div/div[2]/input";
	public static String userSearch = "/html/body/app-root/div/app-manage-user-v2/div/div[1]/div[2]/app-search-grid/div/form/input";
	public static String userSearchResult = "/html/body/app-root/div[1]/app-manage-user-component/div/div/div[2]/div[1]/div/div[2]/div/label/span[1]";
	public static String userCloseSearch = "/html/body/app-root/div[1]/app-manage-user-component/div/div/div[1]/div[3]/span/i";   
	public static String userExpandAnceraTab = "/html/body/app-root/div[1]/app-manage-user-component/div/div/div[2]/div[1]/div/div[1]/label";
	public static String userExpandAnceraSite = "/html/body/app-root/div[1]/app-manage-user-component/div/div/div[2]/div[1]/div[2]/div/div/ul/li[1]/div[1]/label";
	public static String userDeleteIcon = "/html/body/app-root/div[1]/app-manage-user-component/div/div/div[2]/div[1]/div[2]/div/div/ul/li[1]/div[2]/div/div/table/tbody/tr/td[6]/img[2]";
	public static String userDeletePopup = "/html/body/app-root/div[1]/app-manage-user-component/app-confirmation-component/div/div/div[3]/div/button[1]";
	public static String userEmail = "//*[@id=\"emailId\"]";
	public static String userUpdateRoleInput = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-update-user-component/form/div[1]/div[2]/div/div[3]/div[3]/div/div/ng-select/div/div/div[6]/input";
	public static String userUpdateRoleResult = "/html/body/app-root/div/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-update-user-component/form/div[1]/div[2]/div/div[3]/div[3]/div/div/ng-select/ng-dropdown-panel/div[2]/div[2]/div";
	public static String userResetButton2 = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[2]/div/button[2]";

	public static String userOrganizationType = "//*[@id=\"orgTypeId\"]/div/span";
	public static String userOrganizationTypeSelect = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[2]/div[2]/ng-select/ng-dropdown-panel/div/div[2]/div[1]";
	public static String userOrganization = "//*[@id=\"organizationId\"]/div/span";
	public static String userOrganizationInput = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[2]/div[3]/ng-select/div/div/div[2]/input";
	public static String userOrganizationSelect = "/html/body/app-root/div/app-manage-user-v2/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[2]/div[3]/ng-select/ng-dropdown-panel/div/div[2]/div[1]";
	public static String userPiperUser = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[3]/div[5]/div[1]/div/app-custom-radio-button";
	public static String userPiperUserDD = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[3]/div[5]/div[2]/div/div/div/ng-select/div/span";
	public static String userPiperUserSelect = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[3]/div[5]/div[2]/div/div[1]/div/ng-select/ng-dropdown-panel/div[2]/div[2]/div[1]/div"; 
	public static String userAssignRole = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[3]/div[1]/div/div/ng-select/div";
	public static String userAssignRoleSelect = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[3]/div[1]/div/div/ng-select/ng-dropdown-panel/div[2]/div[2]/div[1]";
	public static String userCloseDropdown = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[3]/div[1]/div/div/ng-select/div/span[2]";
	public static String userEnterPassword = "/html/body/app-root/div[1]/app-reset-password/div/div[3]/form/div[1]/div[1]/input";
	public static String userReEnterPassword = "/html/body/app-root/div[1]/app-reset-password/div/div[3]/form/div[2]/div/input";
	public static String userSubmitButton = "/html/body/app-root/div[1]/app-reset-password/div/div[3]/form/button";

	public static String userNextBtn = "btn-next";
	public static String userClientInput = "#clientId > div > div > div.ng-input > input[type=text]";
	public static String userClientInputSelect = "/html/body/app-root/div/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-update-user-component/form/div[1]/div[2]/div/div[3]/div[2]/div/div[1]/ng-select/ng-dropdown-panel/div[2]/div[2]/div";
	public static String userClientSiteBtn = "btn-show-tree";
	public static String userClientInput2 = "#clientId2 > div > div > div.ng-input > input[type=text]";
	public static String userClientInput2Select = "/html/body/app-root/div/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-update-user-component/form/div[3]/div/div[2]/ng-select/ng-dropdown-panel/div/div[2]/div";
	public static String userClientCheckbox = "#sites-tree > div > div > div > ngx-treeview > div:nth-child(2) > div > ngx-treeview-item > div > div.form-inline.row-item.ng-star-inserted > div > label";
	public static String userClientOKBtn = "btn-ok-sites";
	public static String userSaveBtn = "btn-save";

	public static String alertbox = "/html/body/app-root/app-notification-component/ngb-alert/label[2]";
	public static String alertCloseButton = "/html/body/app-root/app-notification-component/ngb-alert/button/span";
	public static String userEditIcon = "/html/body/app-root/div[1]/app-manage-user-component/div/div/div[2]/div[1]/div[2]/div/div/ul/li/div[2]/div/div/table/tbody/tr[1]/td[6]/img[1]";
	public static String alertClose = "//*[@id=\"alrt\"]/button";

	///////////////////////////////////////////End User Management Elements////////////////////////////////////////////////////



	/////////////////////////////////////////Organization Management Elements////////////////////////////////////////////////

	public static String orgGetTitle = "/html/body/app-root/app-layout-component/div[3]/div[3]/p";
	public static String orgCreateButton = "/html/body/app-root/div/app-manage-organization/button";
	public static String orgPopupResetButton = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-organization/form/div/div[3]/div/button[1]";
	public static String orgType = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-organization/form/div/div[2]/div/div[1]/div[2]/ng-select/div";
	static Random rand = new Random();
	static int index = rand.nextInt(4)+1;
	public static String orgTypeSelect = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-organization/form/div/div[2]/div/div[1]/div[2]/ng-select/ng-dropdown-panel/div/div[2]/div[1]";
	public static String orgCloseButton = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[1]";
	public static String orgPopupGetTitle = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[2]/div";

	public static String orgTypeId = "//*[@id=\"orgTypeId\"]/div/span";
	public static String orgName = "//*[@id=\"nameId\"]";
	public static String orgPhoneCode =  "//*[@id=\"phoneCodeId\"]/div";
	public static String orgPhoneCodeSelect = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-organization/form/div/div[2]/div/div[1]/div[4]/div/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div";
	public static String orgPhoneNo = "//*[@id=\"PhoneNumberId\"]";
	public static String orgEmail = "//*[@id=\"emailId\"]";
	public static String orgPopupNextButton = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-organization/form/div/div[3]/div/button[2]";
	public static String orgRolesId = "//*[@id=\"rolesId\"]/div";
	public static String orgRolesIdSelect = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-organization/form/div/div[2]/div/div[2]/div[2]/div/div[1]/ng-select/ng-dropdown-panel/div[2]/div[2]/div[1]";
	public static String orgClickOut = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-organization/form/div/div[2]/div/div[2]/div[2]/div/div[1]/ng-select/div/span";
	public static String orgUpdateRoleInput = "/html/body/app-root/div/app-manage-organization/app-popup-component/div/div/div/div[3]/app-update-organization/form/div/div[2]/div/div[2]/div[2]/div/div/ng-select/div/div/div[5]/input";
	public static String orgUpdateRoleResult = "/html/body/app-root/div/app-manage-organization/app-popup-component/div/div/div/div[3]/app-update-organization/form/div/div[2]/div/div[2]/div[2]/div/div/ng-select/ng-dropdown-panel/div[2]/div[2]/div"; 
	public static String orgUserAgreement = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-organization/form/div/div[2]/div/div[2]/div[3]/div/div[1]/ng-select/div/span";
	public static String orgUserAgreementSelect = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-organization/form/div/div[2]/div/div[2]/div[3]/div/div[1]/ng-select/ng-dropdown-panel/div[2]/div[2]/div/div";

	public static String orgSaveButton = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-organization/form/div/div[3]/div/button[2]";
	public static String orgUploadPhoto = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-organization/form/div/div[2]/div/div[1]/div[1]/div/label/img";
	public static String orgInActiveButton = "/html/body/app-root/div/app-manage-organization/app-popup-component/div/div/div/div[3]/app-update-organization/form/div/div[2]/div/div[2]/div[4]/div/div/app-custom-radio-button/div";
	public static String orgRoles = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-organization/form/div/div[2]/div/div[2]/div[2]/div/div[1]/ng-select/div/div/div[2]/input";
	public static String orgCountryCode = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-organization/form/div/div[2]/div/div[1]/div[4]/div/div[1]/ng-select/div/div/div[3]/input";
	public static String orgtype = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-organization/form/div/div[2]/div/div[1]/div[2]/ng-select/div/div/div[3]/input";

	public static String orgTypeValidation = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-organization/form/div/div[2]/div/div[1]/div[2]/div";
	public static String orgNameValidation = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-organization/form/div/div[2]/div/div[1]/div[3]/div";
	public static String orgEmailValidation= "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-organization/form/div/div[2]/div/div[1]/div[5]/div";
	public static String orgPhoneCodeValidation = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-organization/form/div/div[2]/div/div[1]/div[4]/div/div[1]/div/div";
	public static String orgPhoneNumberValidation = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-organization/form/div/div[2]/div/div[1]/div[4]/div/div[2]/div/div";
	public static String orgMaxUsersValidation = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-organization/form/div/div[2]/div/div[2]/div[1]/div";
	public static String orgRoleValidation = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-organization/form/div/div[2]/div/div[2]/div[2]/div/div[2]";

	public static String orgTypeexpected = "Organization type is required.";
	public static String orgNameexpected = "Organization name is required.";
	public static String orgEmailexpected = "Email is required";
	public static String orgInvalidEmailexpected = "Invalid email";
	public static String orgPhoneCodeexpected = "Select country code";
	public static String orgPhonenoexpected = "Phone number is required";
	public static String orgMaxUsersexpected = "Maximum users value is required";
	public static String orgRolesexpected = "Select assigned role";

	public static String orgSiteTypeexpected = "Site type is required";
	public static String orgSiteNameexpected = "Site name is required";
	public static String orgSiteNameValidation = "/html/body/app-root/div/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[2]/div/div/div[2]/div";
	public static String orgSiteTypeValidation = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[2]/div/div/div[1]/div/div";
	public static String orgSiteResetButton = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[2]/div/div/div[11]/button[2]";
	public static String orgSiteDelete = "/html/body/app-root/div/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[1]/div/ul/div/li/ul/li[1]/div/div[4]/i[2]";
	
	public static String orgSite1Add = "/html/body/app-root/div/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[1]/div/ul/div/li/div/div[4]/i";
	public static String orgSite2Add = "/html/body/app-root/div/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[1]/div/ul/div/li/ul/li/div/div[4]/i[1]";
	public static String orgSite3Add = "/html/body/app-root/div/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[1]/div/ul/div/li/ul/li/ul/li/div/div[4]/i[1]";
	public static String orgSite4Add = "/html/body/app-root/div/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[1]/div/ul/div/li/ul/li/ul/li/ul/li/div/div[4]/i[1]";
	public static String orgSite5Add = "/html/body/app-root/div/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[1]/div/ul/div/li/ul/li/ul/li/ul/li/ul/li/div/div[4]/i[1]";
	public static String orgSite6Add = "/html/body/app-root/div/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[1]/div/ul/div/li/ul/li/ul/li/ul/li[2]/div/div[4]/i[1]";
	public static String orgSite7Add = "/html/body/app-root/div/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[1]/div/ul/div/li/ul/li/ul/li/ul/li[3]/div/div[4]/i[1]";
	
	public static String orgPopupCloseButton = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[1]/p";
	public static String orgMaxUser = "//*[@id=\"idMaxUsers\"]";
	public static String orgSearch = "//*[@id=\"organSearchId\"]";
	public static String orgSearchResult = "/html/body/app-root/div[1]/app-manage-organization/div/div/div[1]/div[3]/label/span[1]";
	public static String orgCloseSearch = "/html/body/app-root/div[1]/app-manage-organization/div/div/div[1]/div[3]/span/i";
	public static String orgExpandAnceraTab = "/html/body/app-root/div[1]/app-manage-organization/div/div/div[2]/div[1]/div/div[1]";
	public static String orgUpdateButton = "/html/body/app-root/div[1]/app-manage-organization/div/div/div[2]/div[1]/div[2]/div/div/table/tbody/tr/td[7]/label/img[1]";
	public static String orgUpdatePhoneNo = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-update-organization/form/div/div[2]/div/div[1]/div[5]/div/div[2]/input";
	public static String orgUpdateNextButton = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-update-organization/form/div/div[3]/div/button";
	public static String orgUpdateSaveButton = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-update-organization/form/div/div[3]/div/button[2]";
	public static String orgSiteButton = "";
	public static String orgCreateSiteButton = "/html/body/app-root/div/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[1]/div/ul/div/li/div/div[4]/i\r\n";
	
	public static String orgNewSiteButton = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[1]/div/ul/div/li/div/i";
	public static String orgSiteType = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[2]/div/div/div[1]/div/ng-select/div/span";
	public static String orgSiteTypeSelect = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[2]/div/div/div[1]/div/ng-select/ng-dropdown-panel/div/div[2]/div[1]";
	public static String orgSiteCountry = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[2]/div/div/div[4]/ng-select/div/span";
	public static String orgSiteCountrySelect = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[2]/div/div/div[4]/ng-select/ng-dropdown-panel/div/div[2]/div";
	public static String orgSiteSaveButton = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[2]/div/div/div[11]/button[1]";
	public static String orgSiteClose = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[1]";
	public static String orgCreatedSite = "/html/body/app-root/div/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[1]/div/ul/div/li/ul/li/div/div[2]/span";
	public static String orgStreetAddress = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[2]/div/div/div[3]/input";
	public static String orgSiteDeleteButton = "/html/body/app-root/div/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[1]/div/ul/div/li/ul/li[1]/div/i";
	public static String orgSiteDeleteConfirm = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/app-confirmation-component/div/div/div[3]/div/button[1]";
	public static String orgDeleteButton = "/html/body/app-root/div[1]/app-manage-organization/div/div/div[2]/div[1]/div[2]/div/div/table/tbody/tr/td[7]/label/img[2]";
	public static String orgDeleteConfirm = "/html/body/app-root/div[1]/app-manage-organization/app-confirmation-component/div/div/div[3]/div/button[1]";

	////////////////////////////////////////End Organization Management Elements//////////////////////////////////////////////



	//////////////////////////////////////////// Access Management Elements//////////////////////////////////////////////////

	public static String accessCreateButton = "/html/body/app-root/div/app-manage-role/button";
	public static String accessName = "/html/body/app-root/div[1]/app-manage-role/app-popup-component/div/div/div/div[3]/app-create-role/form/div[1]/div/div/div[1]/input";
	public static String accessDesc = "/html/body/app-root/div[1]/app-manage-role/app-popup-component/div/div/div/div[3]/app-create-role/form/div[1]/div/div/div[2]/input";
	public static String accessResetButton = "/html/body/app-root/div[1]/app-manage-role/app-popup-component/div/div/div/div[3]/app-create-role/form/div[2]/div/button[2]";
	public static String accessSaveButton = "/html/body/app-root/div[1]/app-manage-role/app-popup-component/div/div/div/div[3]/app-create-role/form/div[2]/div/button[1]";
	public static String accessGetTitle = "/html/body/app-root/app-layout-component/div[3]/div[3]/p";
	public static String accessGetPopupTitle = "/html/body/app-root/div[1]/app-manage-role/app-popup-component/div/div/div/div[2]/div";

	public static String accessNameValidation = "/html/body/app-root/div[1]/app-manage-role/app-popup-component/div/div/div/div[3]/app-create-role/form/div[1]/div/div/div[1]/div";
	public static String accessDescValidation = "/html/body/app-root/div[1]/app-manage-role/app-popup-component/div/div/div/div[3]/app-create-role/form/div[1]/div/div/div[2]/div";
	public static String accessNameValidationExpected = "Role name is required";
	public static String accessDescValidationExpected = "Role description is required";
	public static String accessInActive = "/html/body/app-root/div[1]/app-manage-role/app-popup-component/div/div/div/div[3]/app-update-role/form/div[1]/div/div/div[3]/div/app-custom-radio-button/div/div";

	public static String accessBeforeXpath = "/html/body/app-root/div[1]/app-manage-role/div/div[2]/div/div/table/tbody/tr[";
	public static String accessAfterXpath = "]/td[1]";					
	public static String accessAfterXpath1 = "]/td[3]/img[1]";
	public static String accessAfterXpath2 = "]/td[3]/img[2]";
	public static String accessAfterXpath3 = "]/td[3]/img[3]";

	public static String accessUpdateDesc = "/html/body/app-root/div[1]/app-manage-role/app-popup-component/div/div/div/div[3]/app-update-role/form/div[1]/div/div/div[2]/input";
	public static String accessUpdateButton = "/html/body/app-root/div[1]/app-manage-role/app-popup-component/div/div/div/div[3]/app-update-role/form/div[2]/div/button";
	public static String accessGetEditRightsTitle = "/html/body/app-root/div[1]/app-manage-role/app-popup-component/div/div/div/div[2]/div";
	public static String accessEditRights = "/html/body/app-root/div[1]/app-manage-role/app-popup-component/div/div/div/div[3]/app-create-rights/form/div[1]/div/div/table/tbody/tr[1]/td[2]/label/div";
	public static String accessEditRightsSaveButton = "/html/body/app-root/div[1]/app-manage-role/app-popup-component/div/div/div/div[3]/app-create-rights/form/div[2]/div/button";
	public static String accessGetAssignRoleTitle = "/html/body/app-root/div[1]/app-manage-role/app-popup-component/div/div/div/div[2]/div";
	public static String accessCloseAssignRole = "/html/body/app-root/div[1]/app-manage-role/app-popup-component/div/div/div/div[1]";

	//////////////////////////////////////////End Access Management Elements////////////////////////////////////////////////


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

	public static String rmGetTitle = "/html/body/app-root/app-layout-component/div[3]/div[3]/p";
	public static String rmCreateButton = "/html/body/app-root/div/app-manage-reportrole/button[1]";
	public static String rmPopupGetTitle = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[2]/div";
	public static String rmName = "nameId";
	public static String rmDesc = "DescId";
	public static String rmResetButton = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-create-reportrole/form/div[2]/div/button[2]";
	public static String rmSaveButton = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-create-reportrole/form/div[2]/div/button[1]";
	public static String rmUpdateButton = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-update-reportrole/form/div[2]/div/button";

	public static String rmToggleInactive = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-update-reportrole/form/div[1]/div/div/div[3]/div[1]/div/app-custom-radio-button/div";
	public static String rmToggleDataSecurity = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-create-reportrole/form/div[1]/div/div/div[3]/div[2]/div/app-custom-radio-button";
	public static String rmNameValidation = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-create-reportrole/form/div[1]/div/div/div[1]/div";
	public static String rmDescValidation = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-create-reportrole/form/div[1]/div/div/div[2]/div";
	
	
	public static String rmbeforeXpath = "/html/body/app-root/div/app-manage-reportrole/div/div/div[2]/div/table/tbody/tr[";
	public static String rmafterXpath = "]/td[1]";					
	public static String rmafterXpath1 = "]/td[3]/img[1]";
	public static String rmRoleFind = "/html/body/app-root/div/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-update-user-component/form/div[1]/div[2]/div/div[3]/div[3]/div/div/ng-select/div/div/div[6]/input";

	public static String rmReportGroupsCreateButton =  "/html/body/app-root/div/app-manage-reportrole/button[2]";
	public static String rmReportGroupsPopupGetTitle =  "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[2]/div";
	public static String rmReportGroupsDetailsGetTitle = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-manage-role/div/div/div[2]/form/div/div/fieldset/legend";
	public static String rmReportGroupsAddbutton = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-manage-role/div/div/div/div/fieldset/div[1]/i[2]";
	public static String rmReportGroupsGrpName = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-manage-role/div/div/div[2]/form/div/div/fieldset/div/div[1]/input";
	public static String rmReportGroupsGrpDesc = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-manage-role/div/div/div[2]/form/div/div/fieldset/div/div[2]/input";

	public static String rmReportGroupsReports = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-manage-role/div/div/div[2]/form/div/div/fieldset/div/div[3]/div[1]/ng-select/div/span";
	public static String rmReportGroupsReportsSelect = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-manage-role/div/div/div[2]/form/div/div/fieldset/div/div[3]/div/ng-select/ng-dropdown-panel/div[2]/div[2]/div[1]";
	public static String rmReportGroupsReportsInput = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-manage-role/div/div/div[2]/form/div/div/fieldset/div/div[3]/div/ng-select/div/div/div[2]/input";
	public static String rmReportGroupsReportsClose = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-manage-role/div/div/div[2]/form/div/div/fieldset/div/div[3]/div/ng-select/div/span[2]";
	public static String rmReportGroupsInActive = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-manage-role/div/div/div[2]/form/div/div/fieldset/div/div[4]/div[1]/div/app-custom-radio-button/div";
	public static String rmReportGroupsUpdateDesc = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-manage-role/div/div/div[2]/form/div/div/fieldset/div/div[2]/input";
	public static String rmReportGroupsUpdateButton = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-manage-role/div/div/div[2]/form/div/div/fieldset/div/div[4]/div[2]/div/button[1]";
	public static String rmUserButton1 = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-update-user-component/form/div[2]/div/button";
	public static String rmUserButton2 = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-update-user-component/form/div[2]/div/button[2]";
	public static String rmorgButton1 = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-update-organization/form/div/div[3]/div/button";
	public static String rmOrgUpdateRole = "/html/body/app-root/div/app-manage-organization/app-popup-component/div/div/div/div[3]/app-update-organization/form/div/div[2]/div/div[2]/div[2]/div/div/ng-select/div/div/div[5]/input";
	public static String rmOrgUpdateRoleSelect = "/html/body/app-root/div/app-manage-organization/app-popup-component/div/div/div/div[3]/app-update-organization/form/div/div[2]/div/div[2]/div[2]/div/div/ng-select/ng-dropdown-panel/div[2]/div[2]/div";

	public static String rmReportGroupsResetButton = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-manage-role/div/div/div[2]/form/div/div/fieldset/div/div[4]/div[2]/div/button[2]";
	public static String rmReportGroupsSaveButton = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-manage-role/div/div/div[2]/form/div/div/fieldset/div/div[4]/div[2]/div/button[1]";

	public static String rmReportGroupsGrpNameValidation = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-manage-role/div/div/div[2]/form/div/div/fieldset/div/div[1]/div";
	public static String rmReportGroupsGrpDescValidation = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-manage-role/div/div/div[2]/form/div/div/fieldset/div/div[2]/div";
	public static String rmReportGroupsGrpReportValidation = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-manage-role/div/div/div[2]/form/div/div/fieldset/div/div[3]/div[2]";

	public static String rgbeforeXpath = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-manage-role/div/div/div/div/fieldset/div[2]/ul[";
	public static String rgafterXpath = "]/li/label/span";					
	public static String rgafterXpathExpand = "]/li/i[1]";
	public static String rgafterXpathExpandDelete = "]/div/ul[";
	public static String rgafterXpathExpandDelete2 = "]/li/label";
	public static String rgafterXpathExpandDelete3 = "]/li/i";
	public static String rgafterXpathDelete = "]/li/i[2]";

	public static String rmReportGroupsConfigDeleteCnfrm = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-manage-role/app-confirmation-component/div/div/div[3]/div/button[1]";
	public static String rmReportGroupsDelete = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-manage-role/div/div/div/div/fieldset/div[2]/ul[4]/li/i[2]";
	public static String rmReportGroupsDeleteConfrm = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-manage-role/app-confirmation-component/div/div/div[3]/div/button[1]";

	public static String rmEditRightsButton = "/html/body/app-root/div[1]/app-manage-reportrole/div/div/div[2]/div/table/tbody/tr[1]/td[3]/img[2]";	
	public static String rmEditRightsGetTitle = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[2]/div"; 
	public static String rmEditRightsReportName = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-create-reportrights/form/div[1]/div/div[2]/div/fieldset/table/tbody/tr/td[1]/label";

	public static String rmEditRightsbeforeXpath = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-create-reportrights/form/div[1]/div/div[1]/div/fieldset/ul[";
	public static String rmEditRightsafterXpath = "]/li/label[1]";					
	public static String rmEditRightsafterXpath1 = "]/li/div/app-custom-radio-button/div";
	public static String rmEditRightsSaveButton = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-create-reportrights/form/div[2]/div/button";
	public static String rmEditRightsView = "/html/body/app-root/div[1]/app-manage-reportrole/app-popup-component/div/div/div/div[3]/app-create-reportrights/form/div[1]/div/div[2]/div/fieldset/table/tbody/tr/td[4]/label/div";

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
	

	/////////////////////////////////////////Data Template Management Elements////////////////////////////////////////////////////

	public static String dtmGetTitle = "/html/body/app-root/app-layout-component/div[3]/div[3]/p";
	public static String dtmCreateButton = "/html/body/app-root/div/app-manage-dataformat/div[1]/button[1]";
	public static String dtmCreatePopupGetTitle = "/html/body/app-root/div[1]/app-manage-dataformat/app-popup-component/div/div/div/div[2]/div";
	public static String dtmResetButton = "/html/body/app-root/div[1]/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[2]/div/button[1]";
	public static String dtmSaveButton = "/html/body/app-root/div[1]/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[2]/div/button[2]";
	public static String dtmName = "/html/body/app-root/div[1]/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/div[1]/div[1]/input";
	public static String dtmDesc = "/html/body/app-root/div[1]/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/div[1]/div[2]/input";

	public static String dtmClmName = "/html/body/app-root/div[1]/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/fieldset/div[1]/div[1]/div/input";
	public static String dtmClmType = "/html/body/app-root/div[1]/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/fieldset/div[1]/div[2]/div/ng-select/div/span";
	public static String dtmClmTypeSelect = "/html/body/app-root/div[1]/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/fieldset/div[1]/div[2]/div/ng-select/ng-dropdown-panel/div/div[2]/div[1]";
	public static String dtmClmFieldLength = "/html/body/app-root/div[1]/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/fieldset/div[1]/div[4]/div/input";
	public static String dtmClmDefaultValue = "/html/body/app-root/div[1]/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/fieldset/div[1]/div[3]/div/input";
	public static String dtmClmResetButton = "/html/body/app-root/div[1]/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/fieldset/div[2]/div[2]/button[1]";
	public static String dtmClmSaveButton = "/html/body/app-root/div[1]/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/fieldset/div[2]/div[2]/button[2]";
	public static String dtmUpdateButton = "/html/body/app-root/div/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[2]/div/button[1]";

	public static String dtmClmUpdateButton = "/html/body/app-root/div/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/fieldset/div[2]/div[2]/button[2]";
	public static String dtmNameValidation = "/html/body/app-root/div[1]/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/div[1]/div[1]/div";
	public static String dtmDescValidation = "/html/body/app-root/div[1]/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/div[1]/div[2]/div";
	public static String dtmClmNameValidation = "/html/body/app-root/div[1]/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/fieldset/div[1]/div[1]/div[2]";
	public static String dtmClmTypeValidation = "/html/body/app-root/div[1]/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/fieldset/div[1]/div[2]/div[2]";
	public static String dtmClmLengthValidation = "/html/body/app-root/div[1]/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/fieldset/div[1]/div[4]/div[2]";
	public static String dtmNameExpected = "Template name is required.";
	public static String dtmDescExpected = "Template description is required.";
	public static String dtmClmNameExpected = "Field name is required";
	public static String dtmClmTypeExpected = "Field type is required";
	public static String dtmClmLengthExpected = "Field length is required";
	public static String dtmClmGetText = "/html/body/app-root/div/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/div[3]/div/table/tbody/tr[1]/td[2]";
	public static String dtmClmUpdateGetText = "/html/body/app-root/div/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/div[3]/div/table/tbody/tr[1]/td[6]/label/span";

	public static String dtmClmFieldNameUpdate = "/html/body/app-root/div[1]/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/fieldset/div[1]/div[1]/div[1]/input";
	public static String dtmClmFieldTypeUpdate = "/html/body/app-root/div[1]/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/fieldset/div[1]/div[2]/div[1]/ng-select/div/span";
	public static String dtmClmFieldTypeSelectUpdate = "/html/body/app-root/div[1]/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/fieldset/div[1]/div[2]/div/ng-select/ng-dropdown-panel/div/div[2]/div[1]";
	public static String dtmClmFieldLengthUpdate = "";
	public static String dtmClmAddButtonUpdate = "/html/body/app-root/div[1]/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/fieldset/div[2]/div[2]/button[2]";
	public static String dtmClmDeleteButton = "/html/body/app-root/div/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/div[2]/div/table/tbody/tr/td[11]/label/img[2]";
	public static String dtmClmDeleteCnfrm = "/html/body/app-root/div/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/app-confirmation-component/div/div/div[3]/div/button[1]";
	public static String dtmClmFieldLengthValidation = "/html/body/app-root/div[1]/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/fieldset/div[1]/div[4]/div[2]";
	public static String dtmClmEditIcon = "/html/body/app-root/div/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/div[2]/div/table/tbody/tr/td[11]/label/img[1]";
	public static String dtmClmUpdateDefaultValue = "/html/body/app-root/div/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/fieldset/div[1]/div[3]/div/input";


	///////////////////////////////////////End Data Template Management Elements////////////////////////////////////////////////////


	/////////////////////////////////////////////////Data Upload////////////////////////////////////////////////////////////////////
	
	public static String duUploadDropdown = "#OrgnTypeID > div > span";
	public static String duUploadDropdownSelectClient = "/html/body/app-root/div/app-manage-dataupload/div[1]/form/div/div[2]/div[1]/div/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[2]";
	public static String duClientInput = "#ClientId > div > div > div.ng-input > input[type=text]";
	public static String duClientInputSelect = "/html/body/app-root/div/app-manage-dataupload/div[1]/form/div/div[2]/div[1]/div/div[2]/ng-select/ng-dropdown-panel/div/div[2]/div";
	
	///////////////////////////////////////////////End Data Upload /////////////////////////////////////////////////////////////////

	
	/////////////////////////////////////////////Poultry Management/////////////////////////////////////////////////////////////////
	
	public static String poultryStartDateSelect = "/html/body/app-root/div/app-manage-poultry/div[3]/app-popup-component/div/div/div/div[3]/app-create-intervention/form[1]/div/div/div/div[4]/div[1]/div[1]/app-custom-date-picker/div/div[2]/dp-date-picker/div[2]/div/dp-day-calendar/div/div/div[3]/button[3]";
	public static String poultryBrandName = "/html/body/app-root/div/app-manage-poultry/div[3]/app-popup-component/div/div/div/div[3]/app-create-intervention/form[1]/div/div/div/div[6]/div[1]/div/app-custom-input-dropdown/div/img";
	public static String poultryBrandNameSelect = "/html/body/app-root/div/app-manage-poultry/div[3]/app-popup-component/div/div/div/div[3]/app-create-intervention/form[1]/div/div/div/div[6]/div[1]/div/app-custom-input-dropdown/div/div[2]/label[1]";
	public static String poultryAdminMethod = "/html/body/app-root/div/app-manage-poultry/div[3]/app-popup-component/div/div/div/div[3]/app-create-intervention/form[1]/div/div/div/div[4]/div[4]/div/app-custom-input-dropdown/div/img";
	public static String poultryAdminMethodSelect = "/html/body/app-root/div/app-manage-poultry/div[3]/app-popup-component/div/div/div/div[3]/app-create-intervention/form[1]/div/div/div/div[4]/div[4]/div/app-custom-input-dropdown/div/div[2]/label[1]";
	
	///////////////////////////////////////////End Poultry Management///////////////////////////////////////////////////////////////
	
	

	///////////////////////////////////////////////Salmonella Log Elements/////////////////////////////////////////////////////////

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
	public static String slFieldAccessCol = "33";
	public static String footerCount = ".filter-popup__footer--count";
	
	public static String slAuditLaneCol = "0";
	public static String slAuditSampleIDCol = "1";
	public static String slAuditQCCodeCol = "2";
	public static String slAuditResultStatusCol = "3";
	public static String slAuditResultCol = "4";
	public static String slAuditAssayCol = "5";
	public static String slAuditDateCol = "6";
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
	public static String slAuditCollectionDate = "31";
	
	public static String slToday = "div[class='table-responsive apl-data-log-table apl-scrollbar'] p:nth-child(1)";
	public static String slLast24Hours = "body app-root p:nth-child(2)";
	public static String slLast7Days = "body app-root p:nth-child(3)";
	public static String slLast30Days = "body app-root p:nth-child(4)";
	public static String slThisMonth = "body app-root p:nth-child(5)";
	public static String slLastMonth = "body app-root p:nth-child(6)";
					
	public static String slShowFilter = "_show-filter";
	public static String slSortFilter = "sort-";
	public static String slApplyFilter = "_apply";
	public static String slClearFilter = "_clear-filter";
	
	public static String slLane = "laneNum";
	public static String slSampleID = "sampleId";
	public static String slQCCode = "countOutcome";
	public static String slResultStatus = "result_status";
	public static String slResult = "outcome";
	public static String slAssay = "pathogen";
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
	
	public static String slLoadRow = "/html/body/app-root/div/app-salmonella-log/div[1]/div/div[2]/div[2]/div[5]/div/table/tbody/tr[1]/td[1]";
	public static String slLoadRed = ".red";  
	public static String slLoadYellow = ".yellow"; 
	public static String slLoadGreen = ".green";

	public static String slTotalPages = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[15]/p";

	public static String closeAudit = ".u-report-modal-close-icon";
	
	public static String slPngHover = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[1]/div[1]";
	public static String slCsvHover = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[2]";
	public static String slPng = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[1]/div[1]/span/app-custom-export-png/i";
	public static String slDownloadButton = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/span/i";
	public static String slCsv = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/span/i";	
	public static String slExportDataTemplate = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/div/ul/div/li/label";
	public static String slSampleMetaDataExport = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/div[1]/ul/li/label";


	/////////////////////////////////////////////////End Salmonella Log Elements////////////////////////////////////////////////////////////


	//////////////////////////////////////////////////Coccidia Log Elements/////////////////////////////////////////////////////////

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
	public static String clRequestedAssayCol = "30";
	public static String clFlockIDCol = "31";
	public static String clRunTypeCol = "32";
	public static String clCollectionDateCol = "33";
	public static String clFieldAccessCol = "34";
	
	public static String clAuditLaneCol = "0";
	public static String clAuditSampleIDCol = "1";
	public static String clAuditQCCodeCol = "2";
	public static String clAuditResultStatusCol = "3";
	public static String clAuditResultCol = "4";
	public static String clAuditAssayCol = "5";
	public static String clAuditDateCol = "6";
	public static String clAuditTimeCol = "7";
	public static String clAuditSiteNameCol = "8";
	public static String clAuditSiteIDCol = "9";
	public static String clAuditSiteTypeCol = "10";
	public static String clAuditSampleMatrixCol = "11";
	public static String clAuditDilutionFactorCol = "12";
	public static String clAuditCSampleIDCol = "13";
	public static String clAuditReceivedDateCol = "14";
	public static String clAuditCartridgeIDCol = "15";
	public static String clAuditInstrumentIDCol = "16";
	public static String clAuditW1CellCountCol = "17";
	public static String clAuditW1PCCountCol = "18";
	public static String clAuditW1MeanIntensityCol = "19";
	public static String clAuditW2CellCountCol = "20";
	public static String clAuditW2CPCCountCol = "21";
	public static String clAuditW2MeanIntensityCol = "22";
	public static String clAuditPiperUserCol = "23";
	public static String clAuditKitLotCol = "24";
	public static String clAuditImprocIDCol = "25";
	public static String clAuditTestSiteIDCol = "26";
	public static String clAuditRequestedAssayCol = "27";
	public static String clAuditFlockIDCol = "28";
	public static String clAuditTestSiteNameCol = "27";
	public static String clAuditRunTypeCol = "31";
	public static String clAuditCollectionDateCol = "32";
	
	public static String clShowFilter = "_show-filter";
	public static String clSortFilter = "sort-";
	public static String clApplyFilter = "_apply";
	public static String clClearFilter = "_clear-filter";
	
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
	public static String clResultTime = "time";
	public static String clResultDate = "scanDateTime";
	public static String clCollectionSiteID = "collection_site_id";
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
	public static String clRequestedAssay = "requested_assay";
	public static String clFlockID = "flock_id";
	public static String clRunType = "runType";
	public static String clCollectionDate = "collectionDate";

	
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


	//////////////////////////////////////////////////End Coccidia Log Elements///////////////////////////////////////////////////////


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
}
