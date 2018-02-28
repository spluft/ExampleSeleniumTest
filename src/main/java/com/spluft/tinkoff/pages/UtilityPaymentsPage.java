package com.spluft.tinkoff.pages;

import com.spluft.tinkoff.utils.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class UtilityPaymentsPage extends Helper {

    public UtilityPaymentsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
