package com.spluft.tinkoff.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonPage {
    //@FindBy(css = "#mainMenu *[data-qa-file='MenuItem'] *[href='/payments/']")
    @FindBy(xpath = "//*[@id=\"mainMenu\"]/li[3]/span/a")
    private WebElement paymentsBtn;

    protected WebDriver driver;

    private boolean isElementEnabled(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement.isDisplayed();
    }

    public void clickElement(WebElement webElement){
        if (isElementEnabled(webElement)) {
            webElement.click();
        } else {
            throw new NoSuchElementException("Cannot click on the element: " + webElement);
        }
    }

    protected boolean isPresent(WebElement element) {
        try {
            for (int i = 0; i < 5; i++) {
                new WebDriverWait(driver, 1).until(
                        ExpectedConditions.visibilityOf(element));
                if (element.isDisplayed()) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public PaymentPage getPaymentPage() {
        clickElement(paymentsBtn);
        return new PaymentPage(driver);
    }
}
