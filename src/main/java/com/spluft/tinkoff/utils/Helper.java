package com.spluft.tinkoff.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {
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
}
