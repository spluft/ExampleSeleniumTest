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

public class BaseTest {
    private final static String PAYMENT_RECIPIENT = "ЖКУ-Москва";
    private final static String BASE_URL = ReaderProperties.getBaseUrl();

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
    }

    @AfterMethod
    public void refreshPage() {
        driver.close();
    }
}
