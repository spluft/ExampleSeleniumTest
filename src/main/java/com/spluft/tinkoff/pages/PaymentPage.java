package com.spluft.tinkoff.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends CommonPage {
    @FindBy(css = ".ui-menu__item:nth-child(2)")
    private WebElement utilityBtn;
    @FindBy(css = ".Input__field_2XFoD")
    private WebElement searchFiled;
    @FindBy(css = ".SearchSuggest__entry_highlighted_1SPg3 .Text__text_sizeDesktop_17_1mlTq")
    private WebElement firstElementHCSList;

    public PaymentPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public UtilityPaymentsPage getUtilityPaymentsPage() {
        clickElement(utilityBtn);
        return new UtilityPaymentsPage(driver);
    }

    public void hcsSearch(String searchString) {
        hcsSearchIput(searchString);
        if (isHCSPresent(searchString)){
            firstElementHCSList.click();
        }
    }

    public boolean isHCSPresent(String searchString) {
        if (isPresent(firstElementHCSList) && firstElementHCSList.isEnabled() &&
                firstElementHCSList.getText().equals(searchString)){
            return true;
        }
        return false;
    }

    public void hcsSearchIput(String searchString) {
        if (isSearchFiledFieldEnable()){
            searchFiled.sendKeys(searchString);
        }
    }

    private boolean isSearchFiledFieldEnable() {
        return isPresent(searchFiled) && searchFiled.isEnabled();
    }
}
