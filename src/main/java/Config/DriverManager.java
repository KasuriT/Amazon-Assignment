package Config;

import java.util.Objects;

import org.openqa.selenium.WebDriver;

public final class DriverManager {

	private DriverManager() {}

	private static ThreadLocal<WebDriver> dr = new ThreadLocal<>() ;

	public static WebDriver getDriver() {
		System.out.println(10);
		return dr.get();
	}

	static void setDriver(WebDriver driverref) {
		System.out.println(12);
		if(Objects.nonNull(driverref)) {
			dr.set(driverref);
		}
		System.out.println(13);
	}

	static void unload() {
		dr.remove();
	}

}

