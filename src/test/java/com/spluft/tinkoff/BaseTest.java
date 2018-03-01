package com.spluft.tinkoff;

import com.spluft.tinkoff.pages.HcsMoscowPage;
import com.spluft.tinkoff.pages.MainPage;
import com.spluft.tinkoff.pages.PaymentPage;
import com.spluft.tinkoff.pages.UtilityPaymentsPage;
import com.spluft.tinkoff.utils.ReaderProperties;
import com.spluft.tinkoff.utils.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

public class BaseTest {
    private final String BASE_URL = ReaderProperties.getBaseUrl();
    private final String REGION = ReaderProperties.getRegion();
    private final String HCS_MOSCOW = "ЖКУ-Москва";

    private final static String WRONG_FIELD_CODE = "Поле неправильно заполнено";
    private final static String NOT_ALLOW_FIELD_CODE = "Поле обязательное";
    private final static String WRONG_FIELD_PERIOD = "Поле заполнено некорректно";
    private final static String MIN_FIELD_VALUE = "Минимальная сумма перевода - 10";
    private final static String MAX_FIELD_VALUE = "Максимальная сумма перевода — 15 000";

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

        final HcsMoscowPage hcsMoscowPage = utilityPaymentsPage.getHcsMoscowPage();
        hcsMoscowPage.clickPayHCSMoscowTab();
    }

    @Test(dataProvider = "payerCodeData")
    public void test02PayerCode(final String data,
                                              final String errorMessage) {
        final HcsMoscowPage hcsMoscowPage = new HcsMoscowPage(driver);
        final String message = hcsMoscowPage.getInputErrorPayerCode(data);
        assertEquals(message, errorMessage);
    }

    @Test(dataProvider = "periodData")
    public void test03Period(final String data,
                                           final String errorMessage) {
        final HcsMoscowPage hcsMoscowPage = new HcsMoscowPage(driver);
        final String message = hcsMoscowPage.getInputErrorPeriodCode(data);
        assertEquals(message, errorMessage);
    }

    @Test(dataProvider = "sumData")
    public void test04Sum(final String data,
                                               final String errorMessage) {
        final HcsMoscowPage hcsMoscowPage = new HcsMoscowPage(driver);
        final String message = hcsMoscowPage.getInputErrorSumCode(data);
        assertEquals(message, errorMessage);
    }

    @AfterClass
    public void postconditions() {
        driver.close();
    }

    @DataProvider
    private Object[] payerCodeData() {
        return new String[][] { { "123", WRONG_FIELD_CODE },
                { "shsdjs", NOT_ALLOW_FIELD_CODE }, { "&^%#", NOT_ALLOW_FIELD_CODE },
                { " ", NOT_ALLOW_FIELD_CODE } };
    }

    @DataProvider
    private Object[] periodData() {
        return new String[][] { { "987123", WRONG_FIELD_PERIOD },
                { "123", WRONG_FIELD_PERIOD },
                { "11.195", WRONG_FIELD_PERIOD } };
    }

    @DataProvider
    private Object[] sumData() {
        return new String[][] { { "20000", MAX_FIELD_VALUE },
                { "0", MIN_FIELD_VALUE } };
    }
}
