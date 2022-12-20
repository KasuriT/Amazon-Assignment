package Factories;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import Config.DriverManager;
import Enums.ConfigProperties;
import Utilities.PropertyUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public final class DriverFactory {

	private DriverFactory() {}

	public static WebDriver getDriver() throws MalformedURLException {

		WebDriver driver=null;

	//	String runmode = PropertyUtils.get(ConfigProperties.RUNMODE);
	//	if(browser.equalsIgnoreCase("chrome")) {
	//		if(runmode.equalsIgnoreCase("remote")) {
	//			DesiredCapabilities cap = new DesiredCapabilities();
	//			cap.setBrowserName(BrowserType.CHROME);
			//	cap.setVersion(version);
	//			driver =new RemoteWebDriver(new URL(PropertyUtils.get(ConfigProperties.SELENIUMGRIDURL)), cap);
	//		}
	//		else {
		ChromeOptions options = new ChromeOptions();
		driver = new ChromeDriver(options); 
		System.out.println(7);
				WebDriverManager.chromedriver().setup();
				System.out.println(8);
				driver = new ChromeDriver(options);
				System.out.println(9);
	//			driver.get("www.ggoel.com");
	//		}
	//	}
//		else if(browser.equalsIgnoreCase("firefox")) {
//
//			if(runmode.equalsIgnoreCase("remote")) {
//				DesiredCapabilities cap = new DesiredCapabilities();
//				cap.setBrowserName(BrowserType.FIREFOX);
//			//	cap.setVersion(version);
//				driver =new RemoteWebDriver(new URL(PropertyUtils.get(ConfigProperties.SELENIUMGRIDURL)), cap);
//			} else {
//				WebDriverManager.firefoxdriver().setup();
//				driver = new FirefoxDriver();
//			}
//		}
		return driver;
	}

}
