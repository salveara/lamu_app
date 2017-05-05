package com.lamu.lamuApp.selenium.framework.config;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public final class ChromeBrowser {

    private static ChromeDriver chromeDriver;

    private static final String CHROME_APPLICATION_RUTE = "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe";
    /**
     * This method implements the required configurations to executeSearch the driver in the chrome browser
     * @return the configured webdriver for chrome
     */
    public static ChromeDriver getDriver() {
        if (chromeDriver == null) {
            // You have to download the chromedriver and copy the route to executeSearch it
            // https://chromedriver.storage.googleapis.com/index.html?path=2.28/
            // If it was saved in the main folder of the project you don't have to modified this
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
                    + System.getProperty("file.separator")
                    + "chromedriver.exe");
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("test-type");
            capabilities.setCapability("chrome.binary", CHROME_APPLICATION_RUTE);
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            chromeDriver = new ChromeDriver(capabilities);
        }
        return chromeDriver;
    }
}
