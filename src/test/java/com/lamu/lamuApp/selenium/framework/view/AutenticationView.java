package com.lamu.lamuApp.selenium.framework.view;

import com.lamu.lamuApp.selenium.framework.config.ChromeBrowser;
import org.openqa.selenium.WebDriver;

public class AutenticationView {

    private WebDriver driver;

    public AutenticationView() {
        ChromeBrowser setUp = new ChromeBrowser();
        driver = setUp.getDriver();
    }
}
