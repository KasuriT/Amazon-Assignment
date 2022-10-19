package Test.Ancera;

import static Test.Ancera.Helper.driver;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
public class DateUtil {

    public static String getCurrentDay() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
        String todayStr = Integer.toString(todayInt);
        return todayStr;
    }
    
    
    public static String getCurrentDayPlus(int days) {
        LocalDate currentDate = LocalDate.now();
        int dayOfWeekPlus = currentDate.getDayOfWeek().plus(days).getValue();
        return Integer.toString(dayOfWeekPlus);
    }
    
    
    public static String getFirstDay() {
        String todayStr = "01";
        return todayStr;
    }
    
    
    public static String clickDay(String day) {
		List<WebElement> selectDate = driver.findElements(By.cssSelector(".dp-calendar-wrapper button"));
		for (int i =0;i<=selectDate.size();i++) {
			if (selectDate.get(i).getText().equals("day")) {
				selectDate.get(i).click();
				break;
			}
		}
		return null;
    }
    
    
    public static String getDay(String day) {
        return day;
    }

    public static void clickGivenDay(List<WebElement> elementList, String day) {
        elementList.stream()
            .filter(element -> element.getText().contains(day))
            .findFirst()
            .ifPresent(WebElement::click);
    }
}