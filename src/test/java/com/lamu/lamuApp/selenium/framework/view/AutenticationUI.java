package com.lamu.lamuApp.selenium.framework.view;

import com.lamu.lamuApp.selenium.framework.config.ChromeBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AutenticationUI {

    private WebDriver driver;

    public AutenticationUI() {
        ChromeBrowser chromeBrowser = new ChromeBrowser();
        driver = chromeBrowser.getDriver();
        driver.get("http://localhost:8090/");
    }

    public void fillEmail(String email) {
        driver.findElement(By.id("gwt-uid-5")).sendKeys(email);
    }

    public void fillPassword(String password) {
        driver.findElement(By.id("gwt-uid-7")).sendKeys(password);
    }

    public void uploadButtonLoginClick() {
        driver.findElement(By.id("btnLogin")).click();
    }

    public void uploadButtonPasswordClick() {
        driver.findElement(By.id("btnPassword")).click();
    }

    public String getResponseText() {
        return driver.findElement(By.id("label")).getText();
    }

    public void closeDriver() {
        driver.close();
    }

}
