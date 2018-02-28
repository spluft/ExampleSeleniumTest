package com.spluft.tinkoff.utils;

import io.github.bonigarcia.wdm.Architecture;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebDriverFactory {

    public static final int DEFAULT_TIMEOUT = 15;
    private WebDriver driver;
    private String browser = ReaderProperties.getBrowser();

    public WebDriver getDriver() {
        if (driver == null & browser.equals("chrome")) {
            driver = getChrome();
        } else if (driver == null & browser.equals("ie")) {
            driver = getIE();
        }
        try {
            driver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS).pageLoadTimeout(DEFAULT_TIMEOUT * 4, TimeUnit.SECONDS).setScriptTimeout(DEFAULT_TIMEOUT * 4, TimeUnit.SECONDS);
        } catch (NullPointerException e) {
            Reporter.log("Something went wrong on setting default timeout");
            Reporter.log(e.toString());
        }
        return driver;
    }

    private WebDriver getChrome() {
        ChromeDriverManager.getInstance().setup();
        Reporter.log("Getting Chrome driver " + ChromeDriverManager.getInstance().getDownloadedVersion(), 1, true);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        Dimension ws = driver.manage().window().getSize();
        Logger.getAnonymousLogger().log(Level.INFO,
                String.format("Size of initialized window: w=%d, h=%d", ws.getWidth(), ws.getHeight()));
        return driver;
    }

    private WebDriver getIE() {
        InternetExplorerDriverManager.getInstance().architecture(Architecture.X32).setup();
        Reporter.log("Getting IE driver " + InternetExplorerDriverManager.getInstance().getDownloadedVersion(), 1, true);
        WebDriver driver = new InternetExplorerDriver();
        return driver;
    }

}