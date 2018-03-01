package com.spluft.tinkoff.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ChooseCityPage extends CommonPage {
    //TODO: replace hardcode city
    private String choosingCitySelector = "//*[text()='%s']/..";
    @FindBy(css = ".ui-link.ui-menu__link.ui-menu__link_logo .ui-link__text")
    private List<WebElement> listOfHousings;
    @FindBy(css = "*[role='button']")
    private WebElement cityName;

    WebElement city;
    private String region;

    public ChooseCityPage(WebDriver driver, String region) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.region = region;
    }

    public void setCity(){
        String str = String.format(choosingCitySelector, region);
        city = driver.findElement(By.xpath(String.format(choosingCitySelector, region)));
        city.click();
    }

    private boolean isCityNameSameAs(String region) {
        return isPresent(city) && cityName.getText().equals(region);
    }
}
