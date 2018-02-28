package com.spluft.tinkoff.utils;

import org.openqa.selenium.*;

public class Helper {
    protected WebDriver driver;

    private boolean isElementEnabled(WebElement webElement) {
        return webElement.isEnabled();
    }

    public void clickElement(WebElement webElement){
        if (isElementEnabled(webElement)) {
            webElement.click();
        } else {
            throw new NoSuchElementException("Cannot click on the element: " + webElement);
        }
    }
}
