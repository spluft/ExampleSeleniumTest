package com.spluft.tinkoff.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReaderProperties {
    private static Properties properties;
    private static File file;
    private static FileInputStream fileInput;

    static {
        final String propertyFile = System.getProperty("user.dir")
                + "\\src\\main\\resources\\properties\\base.properties";
        file = new File(propertyFile);
        fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        properties = new Properties();
        try {
            properties.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("baseURL");
    }

    public static String getBrowser() {
        return properties.getProperty("browser");
    }
}
