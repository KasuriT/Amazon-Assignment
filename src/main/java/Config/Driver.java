package Config;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import Constants.FrameworkConstants;
import Enums.ConfigProperties;
import Exceptions.BrowserInvocationFailedException;
import Factories.DriverFactory;
import Utilities.PropertyUtils;

import io.github.bonigarcia.wdm.WebDriverManager;


public final class Driver {

	private Driver() {}

	public static void initDriver()  {

		System.out.println(2);
		if(DriverManager.getDriver() == null) {
			try {
				System.out.println(3);
				DriverManager.setDriver(DriverFactory.getDriver());
			
			} catch (MalformedURLException e) {
				System.out.println(4);
				throw new BrowserInvocationFailedException("Please check the capabilities of browser");
			}
			System.out.println(5);
			DriverManager.getDriver().get("www.google.com");
		}
	}

	public static void quitDriver() {
		if(Objects.nonNull(DriverManager.getDriver())) {
			DriverManager.getDriver().quit();
			DriverManager.unload();
		}
	}

}
