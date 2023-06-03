package TestCases;

import Config.BaseTest;
import PageObjects.AmazonPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;


public class AmazonTest extends BaseTest {

    @BeforeClass
    public void BrowserSetup() {
        BaseTest base = new BaseTest();
        base.config();
    }

    @Test(priority = 1, enabled = true)
    public void MerchandiseSelectFromAmazon() throws IOException, InterruptedException {
        AmazonPage amz = new AmazonPage(getDriver());
        amz.searchItem();
    }

}