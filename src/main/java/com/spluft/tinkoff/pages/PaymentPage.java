package com.spluft.tinkoff.pages;

import com.spluft.tinkoff.utils.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends Helper {
    @FindBy(css = ".ui-menu__item:nth-child(2)")
    private WebElement utilityBtn;

    public PaymentPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public UtilityPaymentsPage getUtilityPaymentsPage() {
        clickElement(utilityBtn);
        return new UtilityPaymentsPage(driver);
    }
}
