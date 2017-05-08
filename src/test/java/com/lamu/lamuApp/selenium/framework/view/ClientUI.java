package com.lamu.lamuApp.selenium.framework.view;

import com.lamu.lamuApp.selenium.framework.config.ChromeBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ClientUI {

    private WebDriver driver;

    public ClientUI() {
        ChromeBrowser chromeBrowser = new ChromeBrowser();
        driver = chromeBrowser.getDriver();
        driver.get("http://localhost:8090/#!client");
    }

    public void fillUser(String user) {
        driver.findElement(By.id("txtUserClient")).sendKeys(user);
    }

    public void fillName(String name) {
        driver.findElement(By.id("txtNombreClient")).sendKeys(name);
    }

    public void fillEmail(String email) {
        driver.findElement(By.id("txtEmailClient")).sendKeys(email);
    }

    public void fillPassword(String password) {
        driver.findElement(By.id("txtPasswordClient")).sendKeys(password);
    }

    public void fillPhone(String phone) {
        driver.findElement(By.id("txtPhoneClient")).sendKeys(phone);
    }

    public void registerButtonClick() {
        driver.findElement(By.id("btnRegister")).click();
    }

    public String getResponseText() {
        return driver.findElement(By.id("label")).getText();
    }

    public void closeDriver() {
        driver.close();
    }
}
