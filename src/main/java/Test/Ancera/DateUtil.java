package Test.Ancera;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import org.openqa.selenium.WebElement;
public class DateUtil {

    public static String getCurrentDay() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
    //    System.out.println("Today Int: " + todayInt + "\n");
        String todayStr = Integer.toString(todayInt);
    //    System.out.println("Today Str: " + todayStr + "\n");
        return todayStr;
    }
    
    
    public static String getCurrentDayPlus(int days) {
        LocalDate currentDate = LocalDate.now();
     //   System.out.println("1: "+currentDate);
        int dayOfWeekPlus = currentDate.getDayOfWeek().plus(days).getValue();
     //   System.out.println("Today Int: " + dayOfWeekPlus + "\n");
        return Integer.toString(dayOfWeekPlus);
    }

    public static void clickGivenDay(List<WebElement> elementList, String day) {
        elementList.stream()
            .filter(element -> element.getText().contains(day))
            .findFirst()
            .ifPresent(WebElement::click);
    }
}