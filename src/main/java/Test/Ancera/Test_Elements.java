package Test.Ancera;

import java.util.Random;


import org.openqa.selenium.support.ui.WebDriverWait;

public class Test_Elements {

	public static WebDriverWait wait = new WebDriverWait(Helper.driver,60);

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

	public static String firstNameValidation = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[1]/div[1]/div";
	public static String lastNameValidation = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[1]/div[2]/div";
	public static String phoneCodeValidation= "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[1]/div[5]/div/div[1]/div/div";
	public static String phoneNumberValidation = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[1]/div[5]/div/div[2]/div/div";
	public static String emailValidation = "/html/body/app-root/div/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[2]/div[1]/div";
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
	public static String userSearch = "/html/body/app-root/div[1]/app-manage-user-component/div/div/div[1]/div[2]/div/input";
	public static String userSearchResult = "/html/body/app-root/div[1]/app-manage-user-component/div/div/div[2]/div[1]/div/div[2]/div/label/span[1]";
	public static String userCloseSearch = "/html/body/app-root/div[1]/app-manage-user-component/div/div/div[1]/div[3]/span/i";   
	public static String userExpandAnceraTab = "/html/body/app-root/div[1]/app-manage-user-component/div/div/div[2]/div[1]/div/div[1]/label";
	public static String userExpandAnceraSite = "/html/body/app-root/div[1]/app-manage-user-component/div/div/div[2]/div[1]/div[2]/div/div/ul/li[1]/div[1]/label";
	public static String userDeleteIcon = "/html/body/app-root/div[1]/app-manage-user-component/div/div/div[2]/div[1]/div[2]/div/div/ul/li[1]/div[2]/div/div/table/tbody/tr/td[6]/img[2]";
	public static String userDeletePopup = "/html/body/app-root/div[1]/app-manage-user-component/app-confirmation-component/div/div/div[3]/div/button[1]";
	public static String userEmail = "//*[@id=\"emailId\"]";
	public static String userUpdateRoleInput = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-update-user-component/form/div[1]/div[2]/div/div[3]/div[3]/div/div/ng-select/div/div/div[6]/input";
	public static String userUpdateRoleResult = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-update-user-component/form/div[1]/div[2]/div/div[3]/div[3]/div/div/ng-select/ng-dropdown-panel/div[2]/div[2]/div";
	public static String userResetButton2 = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[2]/div/button[2]";

	public static String userOrganizationType = "//*[@id=\"orgTypeId\"]/div/span";
	public static String userOrganizationTypeSelect = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[2]/div[2]/ng-select/ng-dropdown-panel/div/div[2]/div[1]";
	public static String userOrganization = "//*[@id=\"organizationId\"]/div/span";
	public static String userOrganizationInput = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[2]/div[3]/ng-select/div/div/div[2]/input";
	public static String userOrganizationSelect = "/html/body/app-root/div[1]/app-manage-user-component/app-popup-component/div/div/div/div[3]/app-create-user-component/form/div[1]/div[2]/div/div[2]/div[3]/ng-select/ng-dropdown-panel/div/div[2]/div[1]";
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
	public static String orgSiteNameValidation = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[2]/div/div/div[2]/div";
	public static String orgSiteTypeValidation = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[2]/div/div/div[1]/div/div";
	public static String orgSiteResetButton = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[2]/div/div/div[11]/button[2]";

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
	public static String orgCreateSiteButton = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[1]/div/ul/div/li/div/i";

	public static String orgNewSiteButton = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[1]/div/ul/div/li/div/i";
	public static String orgSiteType = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[2]/div/div/div[1]/div/ng-select/div/span";
	public static String orgSiteTypeSelect = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[2]/div/div/div[1]/div/ng-select/ng-dropdown-panel/div/div[2]/div[1]";
	public static String orgSiteCountry = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[2]/div/div/div[4]/ng-select/div/span";
	public static String orgSiteCountrySelect = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[2]/div/div/div[4]/ng-select/ng-dropdown-panel/div/div[2]/div";
	public static String orgSiteSaveButton = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[2]/div/div/div[11]/button[1]";
	public static String orgSiteClose = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[1]";
	public static String orgCreatedSite = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[1]/div/ul/div/li/ul/li/div/div[2]/span";
	public static String orgStreetAddress = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[2]/div/div/div[3]/input";
	public static String orgSiteDeleteButton = "/html/body/app-root/div[1]/app-manage-organization/app-popup-component/div/div/div/div[3]/app-create-site-component/form/div[1]/div/div[1]/div/ul/div/li/ul/li/div/i[2]";
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
	public static String rmbeforeXpath = "/html/body/app-root/div[1]/app-manage-reportrole/div/div/div[2]/div/table/tbody/tr[";
	public static String rmafterXpath = "]/td[1]";					
	public static String rmafterXpath1 = "]/td[3]/img[1]";

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
	public static String dtmClmGetText = "/html/body/app-root/div[1]/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/div[2]/div/table/tbody/tr/td[1]/label";
	public static String dtmClmUpdateGetText = "/html/body/app-root/div/app-manage-dataformat/app-popup-component/div/div/div/div[3]/app-create-dataformat/form/div/div[1]/div/div/div[2]/div/table/tbody/tr/td[6]/label/span";

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


	///////////////////////////////////////////////Salmonella Log Elements/////////////////////////////////////////////////////////

	public static String reportbeforeXpath = "/html/body/app-root/div[1]/app-report-viewer/div/div/div[1]/div[2]/div[";   
	public static String reportafterXpath = "]/div/div[2]/label";					
	public static String reportafterXpath1 = "]/div/div[1]/img";
	public static String reportGetTitle = "/html/body/app-root/app-layout-component/div[3]/div[3]/p";

	public static String slDateOpen = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/i";
	public static String slDateFrom = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/form/div/div[1]/input";
	public static String slDateTo = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/form/div/div[2]/input";
	public static String slToday = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[1]/button";
	public static String slLast24Hours = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[2]/button";
	public static String slLast7Days = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[3]/button";
	public static String slLast30Days = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[4]/button";
	public static String slThisMonth = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[5]/button";
	public static String slLastMonth = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[6]/button";

	public static String slLoad = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[1]/div/div[1]/label/span";
	public static String slLoadInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[1]/div/div[2]/div[1]/input";
	public static String slLoadbeforeXpath = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[1]/div/div[2]/div[2]/ul/li[";   
	public static String slLoadafterXpath = "]";					
	public static String slLoadafterXpath1 = "]/label/p/b";	
	public static String slLoadClearInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[1]/div/div[2]/div[1]/span";
	public static String slLoadRow = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[1]";
	public static String slLoadRed = "dc-table-column _0 red text-right";
	public static String slLoadYellow = "dc-table-column _0 yellow text-right";
	public static String slLoadGreen = "dc-table-column _0 green text-right";

	public static String slSampleID = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[2]/div/div[1]/label/span";
	public static String slSampleIDInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[2]/div/div[2]/div[1]/input";
	public static String slSampleIDbeforeXpath = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[2]/div/div[2]/div[2]/ul/li[";   
	public static String slSampleIDafterXpath = "]";					
	public static String slSampleIDafterXpath1 = "]/label/p/b";	
	public static String slSampleIDGetText = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[2]";
	public static String slSampleIDClearInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[2]/div/div[2]/div[1]/span";
	public static String slSampleIDRow = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[2]";

	public static String slInstrumentID = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[3]/div/div[1]/label/span";
	public static String slInstrumentIDInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[3]/div/div[2]/div[1]/input";
	public static String slInstrumentIDbeforeXpath = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[2]/app-custom-checkbox-list[3]/div/div[2]/div[2]/ul/li[";
	public static String slInstrumentIDafterXpath = "]";					
	public static String slInstrumentIDafterXpath1 = "]/label/p/b";	
	public static String slInstrumentIDClearInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[3]/div/div[2]/div[1]/span";
	public static String slInstrumentIDRow = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[17]";

	public static String slCatridgeID = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[4]/div/div[1]/label/span";
	public static String slCatridgeIDInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[4]/div/div[2]/div[1]/input";
	public static String slCatridgeIDbeforeXpath = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[4]/div/div[2]/div[2]/ul/li[";   
	public static String slCatridgeIDafterXpath = "]";					
	public static String slCatridgeIDafterXpath1 = "]/label/p/b";	
	public static String slCatridgeIDClearInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[4]/div/div[2]/div[1]/span";
	public static String slCatridgeIDRow = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[16]";

	public static String slLaneID = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[5]/div/div[1]/label/span";
	public static String slLaneInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[5]/div/div[2]/div[1]/input";
	public static String slLanebeforeXpath = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[5]/div/div[2]/div[2]/ul/li[";   
	public static String slLaneafterXpath = "]";					
	public static String slLaneafterXpath1 = "]/label/p/b";	
	public static String slLaneClearInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[5]/div/div[2]/div[1]/span";
	public static String slLaneIDRow = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[1]";

	public static String slAssayID = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[6]/div/div[1]/label/span";
	public static String slAssayInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[6]/div/div[2]/div[1]/input";
	public static String slAssaybeforeXpath = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[6]/div/div[2]/div[2]/ul/li[";   
	public static String slAssayafterXpath = "]";					
	public static String slAssayafterXpath1 = "]/label/p/b";
	public static String slAssayClearInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[6]/div/div[2]/div[1]/span";
	public static String slAssayRow = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[5]";

	public static String slQCCodeID = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[7]/div/div[1]/label/span";
	public static String slQCCodeInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[7]/div/div[2]/div[1]/input";
	public static String slQCCodebeforeXpath = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[7]/div/div[2]/div[2]/ul/li[";   
	public static String slQCCodeafterXpath = "]";					
	public static String slQCCodeafterXpath1 = "]/label/p/b";	
	public static String slQCCodeClearInput = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[7]/div/div[2]/div[1]/span";
	public static String slQCCodeRow = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[3]";

	public static String slImprocID = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[8]/div/div[1]/label/span";
	public static String slImprocInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[8]/div/div[2]/div[1]/input";
	public static String slImprocbeforeXpath = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[8]/div/div[2]/div[2]/ul/li[";   
	public static String slImprocafterXpath = "]";					
	public static String slImprocafterXpath1 = "]/label/p/b";	
	public static String slImprocClearInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[8]/div/div[2]/div[1]/span";
	public static String slImprocIDRow = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[26]";

	public static String slSiteNameID = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[9]/div/div[1]/label/span";
	public static String slSiteNameInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[9]/div/div[2]/div[1]/input";
	public static String slSiteNamebeforeXpath = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[9]/div/div[2]/div[2]/ul/li[";   
	public static String slSiteNameafterXpath = "]";					
	public static String slSiteNameafterXpath1 = "]/label/p/b";	
	public static String slSiteNameClearInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[9]/div/div[2]/div[1]/span";
	public static String slSiteNameRow = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[9]";

	public static String slSiteIDID = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[10]/div/div[1]/label/span";
	public static String slSiteIDInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[10]/div/div[2]/div[1]/input";
	public static String slSiteIDbeforeXpath = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[10]/div/div[2]/div[2]/ul/li[";   
	public static String slSiteIDafterXpath = "]";					
	public static String slSiteIDafterXpath1 = "]/label/p/b";	
	public static String slSiteIDClearInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[10]/div/div[2]/div[1]/span";
	public static String slSiteIDRow = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[10]";

	public static String slSiteTypeID = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[11]/div/div[1]/label/span";
	public static String slSiteTypeInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[11]/div/div[2]/div[1]/input";
	public static String slSiteTypebeforeXpath = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[11]/div/div[2]/div[2]/ul/li[";   
	public static String slSiteTypeafterXpath = "]";					
	public static String slSiteTypeafterXpath1 = "]/label/p/b";	
	public static String slSiteTypeClearInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[11]/div/div[2]/div[1]/span";
	public static String slSiteTypeRow = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[11]";

	public static String slSampleMatrixID = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[12]/div/div[1]/label/span";
	public static String slSampleMatrixInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[12]/div/div[2]/div[1]/input";
	public static String slSampleMatrixbeforeXpath = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[12]/div/div[2]/div[2]/ul/li[";   
	public static String slSampleMatrixafterXpath = "]";					
	public static String slSampleMatrixafterXpath1 = "]/label/p/b";	
	public static String slSampleMatrixClearInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[12]/div/div[2]/div[1]/span";
	public static String slSampleMatrixRow = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[12]";

	public static String slCSampleIDID = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[13]/div/div[1]/label/span";
	public static String slCSampleIDInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[13]/div/div[2]/div[1]/input";
	public static String slCSampleIDbeforeXpath = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[13]/div/div[2]/div[2]/ul/li[";   
	public static String slCSampleIDafterXpath = "]";					
	public static String slCSampleIDafterXpath1 = "]/label/p/b";	
	public static String slCSampleIDClearInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[13]/div/div[2]/div[1]/span";
	public static String slCSampleIDRow = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[14]";

	public static String slDateReceivedID = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[14]/div/div[1]/label/span";
	public static String slDateReceivedInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[14]/div/div[2]/div[1]/input";
	public static String slDateReceivedbeforeXpath = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[14]/div/div[2]/div[2]/ul/li[";   
	public static String slDateReceivedafterXpath = "]";					
	public static String slDateReceivedafterXpath1 = "]/label/p/b";
	public static String slDateReceivedClearInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[14]/div/div[2]/div[1]/span";
	public static String slDateReceivedRow = "//*[@id=\"dc-table-graph\"]/tbody/tr[1]/td[15]";

	public static String slKitLotID = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[15]/div/div[1]/label/span";
	public static String slKitLotInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[15]/div/div[2]/div[1]/input";
	public static String slKitLotbeforeXpath = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[15]/div/div[2]/div[2]/ul/li[";   
	public static String slKitLotafterXpath = "]";					
	public static String slKitLotafterXpath1 = "]/label/p/b";
	public static String slKitLotClearInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[15]/div/div[2]/div[1]/span";
	public static String slKitLotRow = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[25]";

	public static String slPiperUserID = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[16]/div/div[1]/label/span";
	public static String slPiperUserInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[16]/div/div[2]/div[1]/input";
	public static String slPiperUserbeforeXpath = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[16]/div/div[2]/div[2]/ul/li[";   
	public static String slPiperUserafterXpath = "]";					
	public static String slPiperUserafterXpath1 = "]/label/p/b";
	public static String slPiperUserClearInput = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[16]/div/div[2]/div[1]/span";
	public static String slPiperUserRow = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[24]";

	public static String slApplyFilter = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[1]/app-filter-buttons/div/span[4]";
	public static String slResetButton = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[1]/app-filter-buttons/div/span[3]";
	public static String slLockButton = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[1]/app-filter-buttons/div/span[2]/i";
	public static String slUnLockButton = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[1]/app-filter-buttons/div/span[1]/i";

	public static String slRecordNumberTop = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[2]/div[1]/span[1]";
	public static String slRecordNumber = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[1]/p";
	public static String slSampleIDFirstRecord = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[2]/div/div[2]/div[2]/ul/li[1]/label/p";

	public static String slRowsDropdown = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[16]/select";
	public static String slRowsSelect100 = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[16]/select/option[1]";
	public static String slRowsSelect250 = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[16]/select/option[2]";
	public static String slRowsSelect500 = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[16]/select/option[3]";
	public static String slNextpage = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[13]/i";
	public static String slLastpage = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[14]/i";
	public static String slFirstpage= "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[1]/i";
	public static String slPreviouspage = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[2]/i";
	public static String slTotalPages = "/html/body/app-root/div/app-salmonella-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[15]/p";

	public static String slPngHover = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[1]/div[1]";
	public static String slCsvHover = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[2]";
	public static String slPng = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[1]/div[1]/span/app-custom-export-png/i";
	public static String slDownloadButton = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/span/i";
	public static String slCsv = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/div/ul/li/label";	
	public static String slExportDataTemplate = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/div/ul/div/li/label";
	public static String slSampleMetaDataExport = "/html/body/app-root/div[1]/app-salmonella-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/div[1]/ul/li/label";


	/////////////////////////////////////////////////End Salmonella Log Elements////////////////////////////////////////////////////////////


	///////////////////////////////////////////////External Salmonella Log Elements/////////////////////////////////////////////////////////

	public static String eslDateOpen = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/i";
	public static String eslDateFrom = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/form/div/div[1]/input";
	public static String eslDateTo = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/form/div/div[2]/input";
	public static String eslToday = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[1]/button";
	public static String eslLast24Hours = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[2]/button";
	public static String eslLast7Days = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[3]/button";
	public static String eslLast30Days = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[4]/button";
	public static String eslThisMonth = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[5]/button";
	public static String eslLastMonth = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[6]/button";

	public static String eslLoad = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[1]/div/div[1]/label/span";
	public static String eslLoadInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[1]/div/div[2]/div[1]/input";
	public static String eslLoadbeforeXpath = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[1]/div/div[2]/div[2]/ul/li[";   
	public static String eslLoadafterXpath = "]";					
	public static String eslLoadafterXpath1 = "]/label/p/b";	
	public static String eslLoadClearInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[1]/div/div[2]/div[1]/span";
	public static String eslLoadRow = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[1]";
	public static String eslLoadRed = "dc-table-column _0 red text-right";
	public static String eslLoadYellow = "dc-table-column _0 yellow text-right";
	public static String eslLoadGreen = "dc-table-column _0 green text-right";

	public static String eslSampleID = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[2]/div/div[1]/label/span";
	public static String eslSampleIDInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[2]/div/div[2]/div[1]/input";
	public static String eslSampleIDbeforeXpath = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[2]/div/div[2]/div[2]/ul/li[";   
	public static String eslSampleIDafterXpath = "]";					
	public static String eslSampleIDafterXpath1 = "]/label/p/b";	
	public static String eslSampleIDGetText = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[2]";
	public static String eslSampleIDClearInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[2]/div/div[2]/div[1]/span";
	public static String eslSampleIDRow = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[2]";

	public static String eslInstrumentID = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[3]/div/div[1]/label/span";
	public static String eslInstrumentIDInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[3]/div/div[2]/div[1]/input";
	public static String eslInstrumentIDbeforeXpath = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[2]/app-custom-checkbox-list[3]/div/div[2]/div[2]/ul/li[";   
	public static String eslInstrumentIDafterXpath = "]";					
	public static String eslInstrumentIDafterXpath1 = "]/label/p/b";	
	public static String eslInstrumentIDClearInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[3]/div/div[2]/div[1]/span";
	public static String eslInstrumentIDRow = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[19]";

	public static String eslCatridgeID = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[4]/div/div[1]/label/span";
	public static String eslCatridgeIDInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[4]/div/div[2]/div[1]/input";
	public static String eslCatridgeIDbeforeXpath = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[2]/app-custom-checkbox-list[4]/div/div[2]/div[2]/ul/li[";   
	public static String eslCatridgeIDafterXpath = "]";					
	public static String eslCatridgeIDafterXpath1 = "]/label/p/b";	
	public static String eslCatridgeIDClearInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[4]/div/div[2]/div[1]/span";
	public static String eslCatridgeIDRow = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[18]";

	public static String eslLaneID = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[5]/div/div[1]/label/span";
	public static String eslLaneInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[5]/div/div[2]/div[1]/input";
	public static String eslLanebeforeXpath = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[5]/div/div[2]/div[2]/ul/li[";   
	public static String eslLaneafterXpath = "]";					
	public static String eslLaneafterXpath1 = "]/label/p/b";	
	public static String eslLaneClearInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[5]/div/div[2]/div[1]/span";
	public static String eslLaneIDRow = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[1]";

	public static String eslAssayID = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[6]/div/div[1]/label/span";
	public static String eslAssayInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[6]/div/div[2]/div[1]/input";
	public static String eslAssaybeforeXpath = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[6]/div/div[2]/div[2]/ul/li[";   
	public static String eslAssayafterXpath = "]";					
	public static String eslAssayafterXpath1 = "]/label/p/b";
	public static String eslAssayClearInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[6]/div/div[2]/div[1]/span";
	public static String eslAssayRow = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[5]";

	public static String eslQCCodeID = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[7]/div/div[1]/label/span";
	public static String eslQCCodeInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[7]/div/div[2]/div[1]/input";
	public static String eslQCCodebeforeXpath = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[7]/div/div[2]/div[2]/ul/li[";   
	public static String eslQCCodeafterXpath = "]";					
	public static String eslQCCodeafterXpath1 = "]/label/p/b";	
	public static String eslQCCodeClearInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[7]/div/div[2]/div[1]/span";
	public static String eslQCCodeRow = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[3]";

	public static String eslImprocID = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[8]/div/div[1]/label/span";
	public static String eslImprocInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[8]/div/div[2]/div[1]/input";
	public static String eslImprocbeforeXpath = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[8]/div/div[2]/div[2]/ul/li[";   
	public static String eslImprocafterXpath = "]";					
	public static String eslImprocafterXpath1 = "]/label/p/b";	
	public static String eslImprocClearInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[8]/div/div[2]/div[1]/span";
	public static String eslImprocIDRow = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[22]";

	public static String eslSiteNameID = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[9]/div/div[1]/label/span";
	public static String eslSiteNameInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[9]/div/div[2]/div[1]/input";
	public static String eslSiteNamebeforeXpath = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[9]/div/div[2]/div[2]/ul/li[";   
	public static String eslSiteNameafterXpath = "]";					
	public static String eslSiteNameafterXpath1 = "]/label/p/b";	
	public static String eslSiteNameClearInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[9]/div/div[2]/div[1]/span";
	public static String eslSiteNameRow = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[9]";

	public static String eslSiteIDID = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[10]/div/div[1]/label/span";
	public static String eslSiteIDInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[10]/div/div[2]/div[1]/input";
	public static String eslSiteIDbeforeXpath = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[10]/div/div[2]/div[2]/ul/li[";   
	public static String eslSiteIDafterXpath = "]";					
	public static String eslSiteIDafterXpath1 = "]/label/p/b";	
	public static String eslSiteIDClearInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[10]/div/div[2]/div[1]/span";
	public static String eslSiteIDRow = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[10]";

	public static String eslSiteTypeID = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[11]/div/div[1]/label/span";
	public static String eslSiteTypeInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[11]/div/div[2]/div[1]/input";
	public static String eslSiteTypebeforeXpath = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[11]/div/div[2]/div[2]/ul/li[";   
	public static String eslSiteTypeafterXpath = "]";					
	public static String eslSiteTypeafterXpath1 = "]/label/p/b";	
	public static String eslSiteTypeClearInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[11]/div/div[2]/div[1]/span";
	public static String eslSiteTypeRow = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[11]";

	public static String eslSampleMatrixID = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[12]/div/div[1]/label/span";
	public static String eslSampleMatrixInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[12]/div/div[2]/div[1]/input";
	public static String eslSampleMatrixbeforeXpath = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[12]/div/div[2]/div[2]/ul/li[";   
	public static String eslSampleMatrixafterXpath = "]";					
	public static String eslSampleMatrixafterXpath1 = "]/label/p/b";	
	public static String eslSampleMatrixClearInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[12]/div/div[2]/div[1]/span";
	public static String eslSampleMatrixRow = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[14]";

	public static String eslCSampleIDID = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[13]/div/div[1]/label/span";
	public static String eslCSampleIDInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[13]/div/div[2]/div[1]/input";
	public static String eslCSampleIDbeforeXpath = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[13]/div/div[2]/div[2]/ul/li[";   
	public static String eslCSampleIDafterXpath = "]";					
	public static String eslCSampleIDafterXpath1 = "]/label/p/b";	
	public static String eslCSampleIDClearInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[13]/div/div[2]/div[1]/span";
	public static String eslCSampleIDRow = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[16]";

	public static String eslDateReceivedID = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[14]/div/div[1]/label/span";
	public static String eslDateReceivedInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[14]/div/div[2]/div[1]/input";
	public static String eslDateReceivedbeforeXpath = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[14]/div/div[2]/div[2]/ul/li[";   
	public static String eslDateReceivedafterXpath = "]";					
	public static String eslDateReceivedafterXpath1 = "]/label/p/b";
	public static String eslDateReceivedClearInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[14]/div/div[2]/div[1]/span";
	public static String eslDateReceivedRow = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[17]";

	public static String eslKitLotID = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[15]/div/div[1]/label/span";
	public static String eslKitLotInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[15]/div/div[2]/div[1]/input";
	public static String eslKitLotbeforeXpath = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[15]/div/div[2]/div[2]/ul/li[";   
	public static String eslKitLotafterXpath = "]";					
	public static String eslKitLotafterXpath1 = "]/label/p/b";
	public static String eslKitLotClearInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[15]/div/div[2]/div[1]/span";
	public static String eslKitLotRow = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[21]";

	public static String eslPiperUserID = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[16]/div/div[1]/label/span";
	public static String eslPiperUserInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[16]/div/div[2]/div[1]/input";
	public static String eslPiperUserbeforeXpath = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[16]/div/div[2]/div[2]/ul/li[";   
	public static String eslPiperUserafterXpath = "]";					
	public static String eslPiperUserafterXpath1 = "]/label/p/b";
	public static String eslPiperUserClearInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[16]/div/div[2]/div[1]/span";
	public static String eslPiperUserRow = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[20]";

	public static String eslRequestedAssay = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[17]/div/div[1]/label/span";
	public static String eslRequestedAssayInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[17]/div/div[2]/div[1]/input";
	public static String eslRequestedAssaybeforeXpath = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[17]/div/div[2]/div[2]/ul/li[";   
	public static String eslRequestedAssayafterXpath = "]";					
	public static String eslRequestedAssayafterXpath1 = "]/label/p/b";
	public static String eslRequestedAssayClearInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[17]/div/div[2]/div[1]/span";
	public static String eslRequestedAssayRow = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[12]";

	public static String eslFlockID = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[18]/div/div[1]/label/span";
	public static String eslFlockIDInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[18]/div/div[2]/div[1]/input";
	public static String eslFlockIDbeforeXpath = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[18]/div/div[2]/div[2]/ul/li[";   
	public static String eslFlockIDafterXpath = "]";					
	public static String eslFlockIDafterXpath1 = "]/label/p/b";
	public static String eslFlockIDClearInput = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[18]/div/div[2]/div[1]/span";
	public static String eslFlockIDRow = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[13]";

	public static String eslApplyFilter = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[1]/app-filter-buttons/div/span[4]";
	public static String eslResetButton = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[1]/app-filter-buttons/div/span[3]";
	public static String eslLockButton = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[1]/app-filter-buttons/div/span[2]/i";
	public static String eslUnLockButton = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[1]/app-filter-buttons/div/span[1]/i";

	public static String eslRecordNumberTop = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[2]/div[1]/span[1]";
	public static String eslRecordNumber = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[1]/p";

	public static String eslSampleIDFirstRecord = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[2]/div/div[2]/div[2]/ul/li[1]/label/p";

	public static String eslRowsDropdown = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[16]/select";
	public static String eslRowsSelect100 = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[16]/select/option[1]";
	public static String eslRowsSelect250 = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[16]/select/option[2]";
	public static String eslRowsSelect500 = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[16]/select/option[3]";
	public static String eslNextpage = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[13]/i";
	public static String eslLastpage = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[14]/i";
	public static String eslFirstpage= "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[1]/i";
	public static String eslPreviouspage = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[2]/i";
	public static String eslTotalPages = "/html/body/app-root/div/app-external-salmonella-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[15]/p";

	public static String eslPngHover = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[1]/div[1]";
	public static String eslCsvHover = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[2]";
	public static String eslPng = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[1]/div[1]/span/app-custom-export-png/i";
	public static String eslDownloadButton = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/span/i";
	public static String eslCsv = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/div/ul/li/label";	
	public static String eslExportDataTemplate = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/div/ul/div/li/label";
	public static String eslSampleMetaDataExport = "/html/body/app-root/div[1]/app-external-salmonella-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/div[1]/ul/li/label";


	///////////////////////////////////////////////End External Salmonella Log Elements/////////////////////////////////////////////


	//////////////////////////////////////////////////Coccidia Log Elements/////////////////////////////////////////////////////////

	public static String clSampleIDRow = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[2]";
	public static String clInstrumentIDRow = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[17]";
	public static String clCatridgeIDRow = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[16]";
	public static String clLaneIDRow = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[1]";
	public static String clAssayRow = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[8]";
	public static String clImprocIDRow = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[26]";
	public static String clSiteNameRow = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[12]";
	public static String clSampleMatrixRow = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[13]";
	public static String clCSampleIDRow = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[14]";
	public static String clDateReceivedRow = "//*[@id=\"dc-table-graph\"]/tbody/tr[1]/td[15]";
	public static String clKitLotRow = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[25]";
	public static String clPiperUserRow = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[24]";

	public static String clDateOpen = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/i";
	public static String clDateFrom = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/form/div/div[1]/input";
	public static String clDateTo = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/form/div/div[2]/input";
	public static String clToday = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[1]/button";
	public static String clLast24Hours = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[2]/button";
	public static String clLast7Days = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[3]/button";
	public static String clLast30Days = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[4]/button";
	public static String clThisMonth = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[5]/button";
	public static String clLastMonth = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[6]/button";

	public static String clSampleID = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[1]/div/div[1]/label/span";
	public static String clSampleIDInput = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[1]/div/div[2]/div[1]/input";
	public static String clSampleIDbeforeXpath = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[1]/div/div[2]/div[2]/ul/li[";   
	public static String clSampleIDafterXpath = "]";					
	public static String clSampleIDafterXpath1 = "]/label/p/b";	
	public static String clSampleIDClearInput = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[1]/div/div[2]/div[1]/span";

	public static String clInstrumentID = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[2]/div/div[1]/label/span";
	public static String clInstrumentIDInput = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[2]/div/div[2]/div[1]/input";
	public static String clInstrumentIDbeforeXpath = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[2]/div/div[2]/div[2]/ul/li[";   
	public static String clInstrumentIDafterXpath = "]";					
	public static String clInstrumentIDafterXpath1 = "]/label/p/b";	
	public static String clInstrumentIDClearInput = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[2]/div/div[2]/div[1]/span";

	public static String clCatridgeID = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[3]/div/div[1]/label/span";
	public static String clCatridgeIDInput = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[3]/div/div[2]/div[1]/input";
	public static String clCatridgeIDbeforeXpath = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[3]/div/div[2]/div[2]/ul/li[";   
	public static String clCatridgeIDafterXpath = "]";					
	public static String clCatridgeIDafterXpath1 = "]/label/p/b";	
	public static String clCatridgeIDClearInput = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[3]/div/div[2]/div[1]/span";

	public static String clLaneID = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[4]/div/div[1]/label/span";
	public static String clLaneInput = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[4]/div/div[2]/div[1]/input";
	public static String clLanebeforeXpath = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[4]/div/div[2]/div[2]/ul/li[";   
	public static String clLaneafterXpath = "]";					
	public static String clLaneafterXpath1 = "]/label/p/b";	
	public static String clLaneClearInput = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[4]/div/div[2]/div[1]/span";

	public static String clAssayID = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[5]/div/div[1]/label/span";
	public static String clAssayInput = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[5]/div/div[2]/div[1]/input";
	public static String clAssaybeforeXpath = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[2]/app-custom-checkbox-list[5]/div/div[2]/div[2]/ul/li[";   
	public static String clAssayafterXpath = "]";					
	public static String clAssayafterXpath1 = "]/label/p/b";
	public static String clAssayClearInput = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[5]/div/div[2]/div[1]/span";

	public static String clImprocID = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[6]/div/div[1]/label/span";
	public static String clImprocInput = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[6]/div/div[2]/div[1]/input";
	public static String clImprocbeforeXpath = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[6]/div/div[2]/div[2]/ul/li[";   
	public static String clImprocafterXpath = "]";					
	public static String clImprocafterXpath1 = "]/label/p/b";	
	public static String clImprocClearInput = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[6]/div/div[2]/div[1]/span";

	public static String clSiteNameID = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[7]/div/div[1]/label/span";
	public static String clSiteNameInput = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[7]/div/div[2]/div[1]/input";
	public static String clSiteNamebeforeXpath = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[7]/div/div[2]/div[2]/ul/li[";   
	public static String clSiteNameafterXpath = "]";					
	public static String clSiteNameafterXpath1 = "]/label/p/b";	
	public static String clSiteNameClearInput = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[7]/div/div[2]/div[1]/span";

	public static String clSampleMatrixID = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[8]/div/div[1]/label/span";
	public static String clSampleMatrixInput = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[8]/div/div[2]/div[1]/input";
	public static String clSampleMatrixbeforeXpath = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[8]/div/div[2]/div[2]/ul/li[";   
	public static String clSampleMatrixafterXpath = "]";					
	public static String clSampleMatrixafterXpath1 = "]/label/p/b";	
	public static String clSampleMatrixClearInput = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[8]/div/div[2]/div[1]/span";

	public static String clCSampleIDID = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[9]/div/div[1]/label/span";
	public static String clCSampleIDInput = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[9]/div/div[2]/div[1]/input";
	public static String clCSampleIDbeforeXpath = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[9]/div/div[2]/div[2]/ul/li[";   
	public static String clCSampleIDafterXpath = "]";					
	public static String clCSampleIDafterXpath1 = "]/label/p/b";	
	public static String clCSampleIDClearInput = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[9]/div/div[2]/div[1]/span";

	public static String clDateReceivedID = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[10]/div/div[1]/label/span";
	public static String clDateReceivedInput = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[10]/div/div[2]/div[1]/input";
	public static String clDateReceivedbeforeXpath = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[10]/div/div[2]/div[2]/ul/li[";   
	public static String clDateReceivedafterXpath = "]";					
	public static String clDateReceivedafterXpath1 = "]/label/p/b";
	public static String clDateReceivedClearInput = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[10]/div/div[2]/div[1]/span";

	public static String clKitLotID = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[2]/app-custom-checkbox-list[11]/div/div[1]/label/span";
	public static String clKitLotInput = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[2]/app-custom-checkbox-list[11]/div/div[2]/div[1]/input";
	public static String clKitLotbeforeXpath = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[2]/app-custom-checkbox-list[11]/div/div[2]/div[2]/ul/li[";   
	public static String clKitLotafterXpath = "]";					
	public static String clKitLotafterXpath1 = "]/label/p/b";
	public static String clKitLotClearInput = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[2]/app-custom-checkbox-list[11]/div/div[2]/div[1]/span";

	public static String clPiperUserID = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[12]/div/div[1]/label/span";
	public static String clPiperUserInput = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[12]/div/div[2]/div[1]/input";
	public static String clPiperUserbeforeXpath = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[12]/div/div[2]/div[2]/ul/li[";   
	public static String clPiperUserafterXpath = "]";					
	public static String clPiperUserafterXpath1 = "]/label/p/b";
	public static String clPiperUserClearInput = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[12]/div/div[2]/div[1]/span";

	public static String clApplyFilter = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[1]/app-filter-buttons/div/span[4]";
	public static String clResetButton = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[1]/app-filter-buttons/div/span[3]";
	public static String clLockButton = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[1]/app-filter-buttons/div/span[2]/i";
	public static String clUnLockButton = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[1]/app-filter-buttons/div/span[1]/i";

	public static String clRecordNumberTop = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[2]/div[1]/span[1]";
	public static String clRecordNumber = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[1]/p";
	public static String clSampleIDFirstRecord = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[1]/div/div[2]/div[2]/ul/li[3]";

	public static String clRowsDropdown = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[16]/select";
	public static String clRowsSelect100 = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[16]/select/option[1]";
	public static String clRowsSelect250 = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[16]/select/option[2]";
	public static String clRowsSelect500 = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[16]/select/option[3]";
	public static String clNextpage = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[13]/i";
	public static String clLastpage = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[14]/i";
	public static String clFirstpage= "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[1]/i";
	public static String clPreviouspage = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[2]/i";
	public static String clTotalPages = "/html/body/app-root/div/app-coccidia-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[15]/p";

	public static String clPngHover = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[1]/div[1]";
	public static String clCsvHover = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[2]";
	public static String clPng = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[1]/div[1]/span/app-custom-export-png/i";
	public static String clDownloadButton = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/span/i";
	public static String clCsv = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/div/ul/li/label";	
	public static String clExportDataTemplate = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/div/ul/div/li/label";
	public static String clSampleMetaDataExport = "/html/body/app-root/div[1]/app-coccidia-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/div[1]/ul/li/label";

	//////////////////////////////////////////////////End Coccidia Log Elements///////////////////////////////////////////////////////


	////////////////////////////////////////////////External Coccidia Log Elements////////////////////////////////////////////////////

	public static String eclSampleIDRow = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[2]";
	public static String eclInstrumentIDRow = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[2]/div[3]/div/table/thead/tr/th[21]";
	public static String eclCatridgeIDRow = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[20]";
	public static String eclLaneIDRow = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[1]";
	public static String eclAssayRow = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[8]";
	public static String eclImprocIDRow = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[24]";
	public static String eclSiteNameRow = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[12]";
	public static String eclSampleMatrixRow = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[17]";
	public static String eclCSampleIDRow = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[18]";
	public static String eclDateReceivedRow = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[19]";
	public static String eclKitLotRow = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[23]";
	public static String eclPiperUserRow = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[22]";
	public static String eclCSiteIDRow = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[13]";
	public static String eclRequestedAssayRow = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[15]";
	public static String eclFlockIDRow = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[16]";
	public static String eclCSiteTypeRow = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[2]/div[3]/div/table/tbody/tr[1]/td[14]";

	public static String eclDateOpen = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/i";
	public static String eclDateFrom = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/form/div/div[1]/input";
	public static String eclDateTo = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/form/div/div[2]/input";
	public static String eclToday = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[1]/button";
	public static String eclLast24Hours = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[2]/button";
	public static String eclLast7Days = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[3]/button";
	public static String eclLast30Days = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[4]/button";
	public static String eclThisMonth = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[5]/button";
	public static String eclLastMonth = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[6]/button";

	public static String eclSampleID = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[1]/div/div[1]/label/span";
	public static String eclSampleIDInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[1]/div/div[2]/div[1]/input";
	public static String eclSampleIDbeforeXpath = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[1]/div/div[2]/div[2]/ul/li[";   
	public static String eclSampleIDafterXpath = "]";					
	public static String eclSampleIDafterXpath1 = "]/label/p/b";	
	public static String eclSampleIDClearInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[1]/div/div[2]/div[1]/span";

	public static String eclInstrumentID = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[2]/div/div[1]/label/span";
	public static String eclInstrumentIDInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[2]/div/div[2]/div[1]/input";
	public static String eclInstrumentIDbeforeXpath = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[2]/div/div[2]/div[2]/ul/li[";   
	public static String eclInstrumentIDafterXpath = "]";					
	public static String eclInstrumentIDafterXpath1 = "]/label/p/b";	
	public static String eclInstrumentIDClearInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[2]/div/div[2]/div[1]/span";

	public static String eclCatridgeID = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[3]/div/div[1]/label/span";
	public static String eclCatridgeIDInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[3]/div/div[2]/div[1]/input";
	public static String eclCatridgeIDbeforeXpath = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[3]/div/div[2]/div[2]/ul/li[";   
	public static String eclCatridgeIDafterXpath = "]";					
	public static String eclCatridgeIDafterXpath1 = "]/label/p/b";	
	public static String eclCatridgeIDClearInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[3]/div/div[2]/div[1]/span";

	public static String eclLaneID = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[4]/div/div[1]/label/span";
	public static String eclLaneInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[4]/div/div[2]/div[1]/input";
	public static String eclLanebeforeXpath = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[4]/div/div[2]/div[2]/ul/li[";   
	public static String eclLaneafterXpath = "]";					
	public static String eclLaneafterXpath1 = "]/label/p/b";	
	public static String eclLaneClearInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[4]/div/div[2]/div[1]/span";

	public static String eclAssayID = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[5]/div/div[1]/label/span";
	public static String eclAssayInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[5]/div/div[2]/div[1]/input";
	public static String eclAssaybeforeXpath = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[1]/div[1]/div/div[2]/div[2]/app-custom-checkbox-list[5]/div/div[2]/div[2]/ul/li[";   
	public static String eclAssayafterXpath = "]";					
	public static String eclAssayafterXpath1 = "]/label/p/b";
	public static String eclAssayClearInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[5]/div/div[2]/div[1]/span";


	public static String eclImprocID = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[6]/div/div[1]/label/span";
	public static String eclImprocInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[6]/div/div[2]/div[1]/input";
	public static String eclImprocbeforeXpath = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[6]/div/div[2]/div[2]/ul/li[";   
	public static String eclImprocafterXpath = "]";					
	public static String eclImprocafterXpath1 = "]/label/p/b";	
	public static String eclImprocClearInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[6]/div/div[2]/div[1]/span";

	public static String eclSiteNameID = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[7]/div/div[1]/label/span";
	public static String eclSiteNameInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[7]/div/div[2]/div[1]/input";
	public static String eclSiteNamebeforeXpath = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[7]/div/div[2]/div[2]/ul/li[";   
	public static String eclSiteNameafterXpath = "]";					
	public static String eclSiteNameafterXpath1 = "]/label/p/b";	
	public static String eclSiteNameClearInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[7]/div/div[2]/div[1]/span";

	public static String eclSampleMatrixID = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[8]/div/div[1]/label/span";
	public static String eclSampleMatrixInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[8]/div/div[2]/div[1]/input";
	public static String eclSampleMatrixbeforeXpath = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[8]/div/div[2]/div[2]/ul/li[";   
	public static String eclSampleMatrixafterXpath = "]";					
	public static String eclSampleMatrixafterXpath1 = "]/label/p/b";	
	public static String eclSampleMatrixClearInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[8]/div/div[2]/div[1]/span";

	public static String eclCSampleID = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[9]/div/div[1]/label/span";
	public static String eclCSampleIDInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[9]/div/div[2]/div[1]/input";
	public static String eclCSampleIDbeforeXpath = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[9]/div/div[2]/div[2]/ul/li[";   
	public static String eclCSampleIDafterXpath = "]";					
	public static String eclCSampleIDafterXpath1 = "]/label/p/b";
	public static String eclCSampleIDClearInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[10]/div/div[2]/div[1]/span";

	public static String eclDateReceivedID = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[10]/div/div[1]/label/span";
	public static String eclDateReceivedInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[10]/div/div[2]/div[1]/input";
	public static String eclDateReceivedbeforeXpath = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[10]/div/div[2]/div[2]/ul/li[";   
	public static String eclDateReceivedafterXpath = "]";					
	public static String eclDateReceivedafterXpath1 = "]/label/p/b";
	public static String eclDateReceivedClearInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[10]/div/div[2]/div[1]/span";

	public static String eclKitLotID = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[11]/div/div[1]/label/span";
	public static String eclKitLotInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[11]/div/div[2]/div[1]/input";
	public static String eclKitLotbeforeXpath = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[11]/div/div[2]/div[2]/ul/li[";   
	public static String eclKitLotafterXpath = "]";					
	public static String eclKitLotafterXpath1 = "]/label/p/b";
	public static String eclKitLotClearInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[11]/div/div[2]/div[1]/span";

	public static String eclPiperUserID = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[12]/div/div[1]/label/span";
	public static String eclPiperUserInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[12]/div/div[2]/div[1]/input";
	public static String eclPiperUserbeforeXpath = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[12]/div/div[2]/div[2]/ul/li[";   
	public static String eclPiperUserafterXpath = "]";					
	public static String eclPiperUserafterXpath1 = "]/label/p/b";
	public static String eclPiperUserClearInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[12]/div/div[2]/div[1]/span";

	public static String eclCollectionSiteID = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[13]/div/div[1]/label/span";
	public static String eclCollectionSiteInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[13]/div/div[2]/div[1]/input";
	public static String eclCollectionSitebeforeXpath = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[13]/div/div[2]/div[2]/ul/li[";   
	public static String eclCollectionSiteafterXpath = "]";					
	public static String eclCollectionSiteafterXpath1 = "]/label/p/b";
	public static String eclCollectionSiteClearInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[13]/div/div[2]/div[1]/span";

	public static String eclRequestedAssayID = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[14]/div/div[1]/label/span";
	public static String eclRequestedAssayInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[14]/div/div[2]/div[1]/input";
	public static String eclRequestedAssaybeforeXpath = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[14]/div/div[2]/div[2]/ul/li[";   
	public static String eclRequestedAssayafterXpath = "]";					
	public static String eclRequestedAssayafterXpath1 = "]/label/p/b";
	public static String eclRequestedAssayClearInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[14]/div/div[2]/div[1]/span";

	public static String eclFlockID = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[15]/div/div[1]/label/span";
	public static String eclFlockIDInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[15]/div/div[2]/div[1]/input";
	public static String eclFlockIDbeforeXpath = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[15]/div/div[2]/div[2]/ul/li[";   
	public static String eclFlockIDafterXpath = "]";					
	public static String eclFlockIDafterXpath1 = "]/label/p/b";
	public static String eclFlockIDClearInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[15]/div/div[2]/div[1]/span";

	public static String eclSiteTypeID = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[16]/div/div[1]/label/span";
	public static String eclSiteTypeInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[16]/div/div[2]/div[1]/input";
	public static String eclSiteTypebeforeXpath = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[16]/div/div[2]/div[2]/ul/li[";   
	public static String eclSiteTypeafterXpath = "]";					
	public static String eclSiteTypeafterXpath1 = "]/label/p/b";
	public static String eclSiteTypeClearInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[17]/div/div[2]/div[1]/span";

	public static String eclResultStatus = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[17]/div/div[1]/label/span";
	public static String eclResultStatusInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[17]/div/div[2]/div[1]/input";
	public static String eclResultStatusbeforeXpath = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[17]/div/div[2]/div[2]/ul/li[";   
	public static String eclResultStatusafterXpath = "]";					
	public static String eclResultStatusafterXpath1 = "]/label/p/b";
	public static String eclResultStatusClearInput = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[17]/div/div[2]/div[1]/span";

	public static String eclApplyFilter = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[1]/app-filter-buttons/div/span[4]";
	public static String eclResetButton = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[1]/app-filter-buttons/div/span[3]";
	public static String eclLockButton = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[1]/app-filter-buttons/div/span[2]/i";
	public static String eclUnLockButton = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[1]/app-filter-buttons/div/span[1]/i";

	public static String eclRecordNumberTop = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[2]/div[1]/span[1]";
	public static String eclRecordNumber = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[1]/p";
	public static String eclSampleIDFirstRecord = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[1]/div/div[2]/div[2]/ul/li[3]";

	public static String eclRowsDropdown = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[16]/select";
	public static String eclRowsSelect100 = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[16]/select/option[1]";
	public static String eclRowsSelect250 = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[16]/select/option[2]";
	public static String eclRowsSelect500 = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[16]/select/option[3]";
	public static String eclNextpage = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[13]/i";
	public static String eclLastpage = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[14]/i";
	public static String eclFirstpage= "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[1]/i";
	public static String eclPreviouspage = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[2]/i";
	public static String eclTotalPages = "/html/body/app-root/div/app-external-coccidia-log/div/div/div[2]/div[2]/div[3]/app-pagination-component/div/div[2]/div[1]/div[15]/p";

	public static String eclPngHover = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[1]/div[1]";
	public static String eclCsvHover = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[2]";
	public static String eclPng = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[1]/div[1]/span/app-custom-export-png/i";
	public static String eclDownloadButton = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/span/i";
	public static String eclCsv = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/div/ul/li/label";	
	public static String eclExportDataTemplate = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/div/ul/div/li/label";
	public static String eclSampleMetaDataExport = "/html/body/app-root/div[1]/app-external-coccidia-log/div/div/div[2]/div[2]/div[2]/app-custom-export-file-xlsx/div/div[1]/ul/li/label";


	///////////////////////////////////////////////End External Coccidia Log Elements///////////////////////////////////////////////////////


	///////////////////////////////////////////////Coccidia Timeline Log Elements/////////////////////////////////////////////////////////

	public static String ctlDateTo = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/form/div/div[2]/input";
	public static String ctlToday = "/html/body/app-root/div/app-coccidia-timeline/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[1]/button";
	public static String ctlLast24Hours = "/html/body/app-root/div/app-coccidia-timeline/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[2]/button";
	public static String ctlLast7Days = "/html/body/app-root/div/app-coccidia-timeline/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[3]/button";
	public static String ctlLast30Days = "/html/body/app-root/div/app-coccidia-timeline/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[4]/button";
	public static String ctlThisMonth = "/html/body/app-root/div/app-coccidia-timeline/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[5]/button";
	public static String ctlLastMonth = "/html/body/app-root/div/app-coccidia-timeline/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/div/ngx-daterangepicker-material/div/div[1]/ul/li[6]/button";
	
	public static String ctlSampleID = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[2]/div[3]/app-custom-checkbox-list[1]/div/div[1]/label/span";
	public static String ctlSampleIDInput = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[2]/div[3]/app-custom-checkbox-list[1]/div/div[2]/div[1]/input";
	public static String ctlSampleIDbeforeXpath = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[2]/div[3]/app-custom-checkbox-list[1]/div/div[2]/div[2]/ul/li[";   
	public static String ctlSampleIDafterXpath = "]";					
	public static String ctlSampleIDafterXpath1 = "]/label/p/b";	
	public static String ctlSampleIDClearInput = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[2]/div[3]/app-custom-checkbox-list[1]/div/div[2]/div[1]/span";

	public static String ctlInstrumentID = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[2]/div[3]/app-custom-checkbox-list[2]/div/div[1]/label/span";
	public static String ctlInstrumentIDInput = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[2]/div[3]/app-custom-checkbox-list[2]/div/div[2]/div[1]/input";
	public static String ctlInstrumentIDbeforeXpath = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[2]/div[3]/app-custom-checkbox-list[2]/div/div[2]/div[2]/ul/li[";   
	public static String ctlInstrumentIDafterXpath = "]";					
	public static String ctlInstrumentIDafterXpath1 = "]/label/p/b";	
	public static String ctlInstrumentIDClearInput = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[2]/div[3]/app-custom-checkbox-list[2]/div/div[2]/div[1]/span";

	public static String ctlCatridgeID = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[2]/div[3]/app-custom-checkbox-list[3]/div/div[1]/label/span";
	public static String ctlCatridgeIDInput = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[2]/div[3]/app-custom-checkbox-list[3]/div/div[2]/div[1]/input";
	public static String ctlCatridgeIDbeforeXpath = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[2]/div[3]/app-custom-checkbox-list[3]/div/div[2]/div[2]/ul/li[";   
	public static String ctlCatridgeIDafterXpath = "]";					
	public static String ctlCatridgeIDafterXpath1 = "]/label/p/b";	
	public static String ctlCatridgeIDClearInput = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[2]/div[3]/app-custom-checkbox-list[3]/div/div[2]/div[1]/span";

	public static String ctlLaneID = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[2]/div[3]/app-custom-checkbox-list[4]/div/div[1]/label/span";
	public static String ctlLaneInput = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[2]/div[3]/app-custom-checkbox-list[4]/div/div[2]/div[1]/input";
	public static String ctlLanebeforeXpath = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[2]/div[3]/app-custom-checkbox-list[4]/div/div[2]/div[2]/ul/li[";   
	public static String ctlLaneafterXpath = "]";					
	public static String ctlLaneafterXpath1 = "]/label/p/b";	
	public static String ctlLaneClearInput = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[2]/div[3]/app-custom-checkbox-list[4]/div/div[2]/div[1]/span";

	public static String ctlPathogenID = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[2]/div[3]/app-custom-checkbox-list[5]/div/div[1]/label/span";
	public static String ctlPathogenInput = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[2]/div[3]/app-custom-checkbox-list[5]/div/div[2]/div[1]/input";
	public static String ctlPathogenbeforeXpath = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[2]/div[3]/app-custom-checkbox-list[5]/div/div[2]/div[2]/ul/li[";   
	public static String ctlPathogenafterXpath = "]";					
	public static String ctlPathogenafterXpath1 = "]/label/p/b";
	public static String ctlPathogenClearInput = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[2]/div[3]/app-custom-checkbox-list[5]/div/div[2]/div[1]/span";

	public static String ctlImprocID = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[2]/div[3]/app-custom-checkbox-list[6]/div/div[1]/label/span";
	public static String ctlImprocInput = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[2]/div[3]/app-custom-checkbox-list[6]/div/div[2]/div[1]/input";
	public static String ctlImprocbeforeXpath = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[2]/div[3]/app-custom-checkbox-list[6]/div/div[2]/div[2]/ul/li[";   
	public static String ctlImprocafterXpath = "]";					
	public static String ctlImprocafterXpath1 = "]/label/p/b";	
	public static String ctlImprocClearInput = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[2]/div[3]/app-custom-checkbox-list[6]/div/div[2]/div[1]/span";

	public static String ctlApplyFilter = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[1]/app-filter-buttons/div/span[4]";
	public static String ctlResetButton = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[1]/app-filter-buttons/div/span[3]";
	public static String ctlLockButton = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[1]/app-filter-buttons/div/span[2]/i";
	public static String ctlUnLockButton = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[1]/app-filter-buttons/div/span[1]/i";

	public static String ctlRecordNumber = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[2]/div[1]/span[1]";
	public static String ctlSampleIDFirstRecord = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[1]/div/div/div[2]/div[2]/app-custom-checkbox-list[1]/div/div[2]/div[2]/ul/li[3]";

	public static String ctlTimelinePngHover = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[1]/div[1]";
	public static String ctlTimelinePng = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[1]/div[1]/span/app-custom-export-png/i";

	public static String ctlOCountPngHover = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[2]/div/div[2]/div/div[1]";
	public static String ctlOCountPng = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[2]/div/div[2]/div/div[1]/div/div[1]/span/app-custom-export-png/i";

	public static String ctlLast10PngHover = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[2]/div/div[1]/div/div[1]";
	public static String ctlLast10Png = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[2]/div/div[1]/div/div[1]/div/div/span/app-custom-export-png/i";

	public static String ctlOverTimePngHover = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[2]/div/div[3]/div/div[1]";
	public static String ctlOverTimePng = "/html/body/app-root/div[1]/app-coccidia-timeline/div/div/div[2]/div[2]/div/div[3]/div/div[1]/div/div/span/app-custom-export-png/i";

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