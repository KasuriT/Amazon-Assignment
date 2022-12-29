package PageObjects;

import static MiscFunctions.Constants.url_user;
import static MiscFunctions.Methods.click;
import static MiscFunctions.Methods.type;
import static MiscFunctions.Methods.scroll;
import static MiscFunctions.Methods.waitElementInvisible;
import static MiscFunctions.Methods.waitElementVisible;
import static PageObjects.BasePage.loading_cursor;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import Config.BaseTest;


public class UserManagementPage {


	public static By usercreateButton = By.id("create-user");
	public static By userFirstNameInput = By.id("firstNameId");
	public static By userLastNameInput = By.id("lastNameId");
	public static By userEmailInput = By.id("emailId");
	public static By userOrgTypeDropDownExpand = By.cssSelector("#orgTypeId .ng-arrow-wrapper");
	public static By userOrgTypeInput = By.cssSelector("#orgTypeId input");
	public static By userOrgDropDownExpand = By.cssSelector("#organizationId .ng-arrow-wrapper");
	public static By userOrgInput = By.cssSelector("#organizationId input");
	public static By siteAdministratorToggle = By.cssSelector("#site-administrator .row");
	public static By userRoleCategory = By.id("roleCategoryId");
	public static By systemRolesExpand = By.cssSelector("#rolesId .ng-arrow-wrapper");
	public static By systemRoleSelect1 = By.cssSelector(".ng-dropdown-panel .ng-option:nth-child(2) label");
	public static By systemRolesSelect = By.xpath("//*[@id=\"rolesId\"]//div[2]/input");
	public static By systemRolesSelected = By.cssSelector("#rolesId .ng-option-selected");
	public static By reportRoleExpand = By.cssSelector("#reportRoleId .ng-arrow-wrapper");
	public static By reportRoleSelect = By.xpath("//*[@id=\"reportRoleId\"]//input");
	public static By reportRoleGetValue = By.cssSelector("#reportRoleId .ng-value-label");
	public static By AgreeementExpand = By.cssSelector("#euladdl .ng-arrow-wrapper");
	public static By AgreementSelect = By.xpath("//*[@id=\"euladdl\"]//div[2]/input");
	public static By getOrgName = By.cssSelector("#organizationId .ng-value-label");
	public static By orgDomains = By.cssSelector("#domains");
	public static By enterAllowDomain = By.cssSelector("#domains input[type=text]");
	
	public static By AcceptAgreementonLogin = By.xpath("//*[text()= ' Accept ']");
	
	public static By openUserSites = By.cssSelector(".btn-sites");
	public static By selectTestingSites = By.id("select-testing-sites");
	public static By selectColletionSites = By.id("select-collection-sites");
	
	public static By saveUserSites = By.id("btn-ok-sites");
	
	public static By editSearchedUser = By.id("edit-user-1");
	public static By deleteSearchedUser = By.id("delete-user-1");
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
	
	
	public void clickCreateButton() throws InterruptedException {
	click(usercreateButton);
	Thread.sleep(2000);
	}
	
	public static void openEditUserPopup(String emailAddress) throws InterruptedException, IOException {
		BaseTest driver = new BaseTest();
		driver.getDriver().get(url_user);
		waitElementInvisible(loading_cursor);
		waitElementVisible(UserManagementPage.usercreateButton);
		Thread.sleep(3000);
		click(By.id("userEmail_show-filter"));
		waitElementInvisible(loading_cursor);
		type(By.id("userEmail_search-input"), emailAddress);

		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		click(By.cssSelector("th:nth-child(4) li:nth-child(1) label"));					  
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		click(By.id("userEmail_apply"));
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		scroll(By.id("edit-user-1"));
		Thread.sleep(1000); 
		try {
			click(By.cssSelector("#edit-user-1 img"));
		}
		catch (StaleElementReferenceException ex) {
			click(By.cssSelector("#edit-user-1 img"));
		}
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
	}
}
