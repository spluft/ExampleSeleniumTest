package com.spluft.tinkoff.pages;

import com.spluft.tinkoff.utils.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HcsMoscowPage extends Helper {
    @FindBy(xpath = "//*[text()='Оплатить ЖКУ в Москве']")
    private WebElement payHCSmoscowTab;
    @FindBy(name = "provider-payerCode")
    private WebElement payerCodeField;
    @FindBy(id = "period")
    private WebElement periodField;
    @FindBy(css = "*[class='Input__value_2Kx90'] *[type='text']")
    //@FindBy(xpath = "/html/body/div[1]/div/div[2]/div[1]/div[2]/div[1]/div[3]/div/div[2]/div[1]/div/div/div/div[4]/div[1]/form/div[4]/div/div/div/div/div/div/div/div[1]/label/div/input")
    private WebElement sumField;
    @FindBy(css = ".ui-form__row_text.ui-form__row_default-error-view-visible *[data-qa-file='UIFormRowError']")
    private WebElement payerCodeErrMsg;
    @FindBy(css = ".ui-form__row_date *[data-qa-file='UIFormRowError']")
    private WebElement periodErrMsg;
    @FindBy(css = ".ui-form__row_combination *[data-qa-file='UIFormRowError']")
    private WebElement sumErrMsg;
    @FindBy(css = ".ui-button.ui-button_failure")
    private WebElement payBtn;

    public HcsMoscowPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void clickPayHCSMoscowTab() {
        payHCSmoscowTab.click();
    }

    public String getInputErrorPayerCode(String code){
        clearPayerCodeField();
        if (isPresent(payerCodeField)) {
            payerCodeField.sendKeys(code);
        }
        clickSubmit();
        return payerCodeErrMsg.getText();
    }

    public String getInputErrorPeriodCode(String code) {
        clearPayerPeriodField();
        if (isPresent(periodField)) {
            periodField.sendKeys(code);
        }
        clickSubmit();
        return periodErrMsg.getText();
    }

    public String getInputErrorSumCode(String code) {
        clearPayerSumField();
        if (isPresent(sumField)) {
            sumField.sendKeys(code);
        }
        clickSubmit();
        return sumErrMsg.getText();
    }

    public void clearPayerSumField() {
        if (!sumField.getAttribute("value").equals(""))
            sumField.clear();
    }

    public void clearPayerPeriodField() {
        if (!periodField.getAttribute("value").equals(""))
            periodField.clear();
    }

    public void clearPayerCodeField() {
        if (!payerCodeField.getAttribute("value").equals(""))
            payerCodeField.clear();
    }

    private void clickSubmit() {
        if (isPresent(payBtn))
            payBtn.click();
    }
}
