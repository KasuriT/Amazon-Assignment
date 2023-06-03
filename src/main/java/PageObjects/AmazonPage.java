package PageObjects;

import com.aventstack.extentreports.gherkin.model.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import static Config.BaseTest.saveResult;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import static MiscFunctions.Methods.getScreenshot;


public class AmazonPage {

    public static By searchBox = By.id("twotabsearchtextbox");
    public static By searchIcon = By.id("nav-search-submit-button");
    public static By sortByDropdown = By.id("a-autoid-0-announce");
    public static By sortHightoLow = By.id("s-result-sort-select_2");
    public static By productSelect = By.xpath("(//div[contains(@class, 'a-spacing-small a-spacing-top-small')])[4]");
    public static By productTitle = By.id("productTitle");
    public String searchedText = "Nikon";

    private WebDriver driver;

    //Create object of Intervention Management Class
    //Constructor
    public AmazonPage(WebDriver driver) {
        this.driver = driver;
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void searchItem() throws InterruptedException, IOException {

        try {
            SoftAssert softAssert = new SoftAssert();

            waitElementVisible(searchBox);
            type(searchBox, searchedText);
            Thread.sleep(1000);
            click(searchIcon);
            Thread.sleep(1000);
            waitElementVisible(sortByDropdown);
            click(sortByDropdown);
            click(sortHightoLow);
            waitElementVisible(productSelect);
            click(productSelect);
            String productText = getText(productTitle);
            System.out.println("the product title is " + productText);
            softAssert.assertTrue(productText.contains("Nikon D3X"), "The product title does not contains the Nikon D3X");

            softAssert.assertAll();
            saveResult(ITestResult.SUCCESS, null);
        }catch (AssertionError er) {
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            saveResult(ITestResult.FAILURE, ex);
        }
    }

}

