package com.spluft.tinkoff;

import com.spluft.tinkoff.pages.MainPage;
import com.spluft.tinkoff.pages.PaymentPage;
import com.spluft.tinkoff.pages.UtilityPaymentsPage;
import com.spluft.tinkoff.utils.ReaderProperties;
import com.spluft.tinkoff.utils.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BaseTest {
    private final String BASE_URL = ReaderProperties.getBaseUrl();
    private final String REGION = ReaderProperties.getRegion();
    private final String HCS_MOSCOW = "ЖКУ-Москва";

    private WebDriver driver;

    @BeforeClass
    public void preconditions() {
        WebDriverFactory webDriverFactory = new WebDriverFactory();
        driver = webDriverFactory.getDriver();
    }

    @Test
    public void test01GetMoscowTab() {

        driver.get(BASE_URL);

        final MainPage mainPage = new MainPage(driver);
        final PaymentPage paymentPage = mainPage.getPaymentPage();
        final UtilityPaymentsPage utilityPaymentsPage = paymentPage.getUtilityPaymentsPage();

        utilityPaymentsPage.setRegion(REGION);

        assertEquals(utilityPaymentsPage.getHcsRecipient(), HCS_MOSCOW);
    }

    @AfterMethod
    public void refreshPage() {
        driver.close();
    }
}
