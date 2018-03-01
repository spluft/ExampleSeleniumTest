package com.spluft.tinkoff.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UtilityPaymentsPage extends CommonPage {
    private final String PAYMENT_RECIPIENT = "ЖКУ-Москва";

    @FindBy(css = "*[role='button']")
    private WebElement cityChooseBtn;
    @FindBy(css = ".ui-menu__item:first-child")
    private WebElement firstHcsRecipient;

    public UtilityPaymentsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public UtilityPaymentsPage setRegion(String region) {
        clickElement(cityChooseBtn);
        ChooseCityPage chooseCityPage = new ChooseCityPage(driver, region);
        chooseCityPage.setCity();
        return new UtilityPaymentsPage(driver);
    }

    public String getHcsRecipient() {
        isPresent(firstHcsRecipient);
        return firstHcsRecipient.getText();
    }

    public HcsMoscowPage getHcsMoscowPage() {
        clickElement(firstHcsRecipient);
        return new HcsMoscowPage(driver);
    }
}
