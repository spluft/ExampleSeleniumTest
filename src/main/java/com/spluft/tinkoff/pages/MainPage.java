package com.spluft.tinkoff.pages;

import com.spluft.tinkoff.utils.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Helper {

    @FindBy(css = "#mainMenu *[data-qa-file='MenuItem'] *[href='/payments/']")
    private WebElement paymentsBtn;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public PaymentPage getPaymentPage() {
        clickElement(paymentsBtn);
        return new PaymentPage(driver);
    }

}
