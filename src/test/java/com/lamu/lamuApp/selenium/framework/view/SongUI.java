package com.lamu.lamuApp.selenium.framework.view;

import com.lamu.lamuApp.selenium.framework.config.ChromeBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class SongUI {

    private WebDriver driver;

    public SongUI() {
        ChromeBrowser chromeBrowser = new ChromeBrowser();
        driver = chromeBrowser.getDriver();
        driver.get("http://localhost:8090/#!song");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void fillTittle(String tittle) {
        driver.findElement(By.id("gwt-uid-5")).sendKeys(tittle);
    }

    public void fillUrl(String url) {
        driver.findElement(By.id("gwt-uid-7")).sendKeys(url);
    }

    public void fillArtist(String artist) {
        driver.findElement(By.id("gwt-uid-9")).sendKeys(artist);
    }

    public void fillAlbum(String album) {
        driver.findElement(By.id("gwt-uid-11")).sendKeys(album);
    }

    public void fillGenre(String genre) {
        driver.findElement(By.id("gwt-uid-13")).sendKeys(genre);
    }

    public void fillTrack(String track) {
        driver.findElement(By.id("gwt-uid-15")).sendKeys(track);
    }

    public void fillYear(String year) {
        driver.findElement(By.id("gwt-uid-17")).sendKeys(year);
    }

    public void uploadButtonClick() {
        driver.findElement(By.id("btnUpload")).click();
    }

    public String getResponseText() {
        return driver.findElement(By.id("label")).getText();
    }

    public void closeDriver() {
        driver.close();
    }
}
