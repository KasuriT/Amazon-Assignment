package PageObjects;

import org.openqa.selenium.By;

public class AccessManagementPage {

	public static By accessTitle = By.id("Access Management");
	public static By accessCreateButton = By.id("create-role");
	public static By accessName = By.xpath("//div[1]/app-anc-input-box/div/input[1]");
	public static By accessDesc = By.xpath("//div[2]/app-anc-input-box/div/input[1]");

	public static By accessNameDescValidation = By.cssSelector(".has-error");
	public static String accessInActive = "/html/body/app-root/div[1]/app-manage-role/app-popup-component/div/div/div/div[3]/app-update-role/form/div[1]/div/div/div[3]/div/app-custom-radio-button/div/div";


	
}
