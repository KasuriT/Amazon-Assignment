package PageObjects;

import org.openqa.selenium.By;

public class ForgotPasswordPage {

	public static By forgotPasswordButton = By.id("forgot-pass");
	public static String gmailEmail = "//input[@type = 'email']";
	public static String gmailPassword = "//input[@type = 'password']";
	public static By gmailSecurityCheck = By.xpath("//*[contains(text(), 'Confirm your recovery email')]");
	public static By gmailSecurityEmail = By.cssSelector("input[type='email']");
	public static By gmailNotNow = By.xpath("//*[contains(text(), 'Not now')]");

	
}
