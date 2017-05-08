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
    }

    public void fillTittle(String tittle) {
        driver.findElement(By.id("txtTittleSong")).sendKeys(tittle);
    }

    public void fillUrl(String url) {
        driver.findElement(By.id("txtUrlSong")).sendKeys(url);
    }

    public void fillArtist(String artist) {
        driver.findElement(By.id("txtArtistSong")).sendKeys(artist);
    }

    public void fillAlbum(String album) {
        driver.findElement(By.id("txtAlbumSong")).sendKeys(album);
    }

    public void fillGenre(String genre) {
        driver.findElement(By.id("txtGenreSong")).sendKeys(genre);
    }

    public void fillTrack(String track) {
        driver.findElement(By.id("txtTrackSong")).sendKeys(track);
    }

    public void fillYear(String year) {
        driver.findElement(By.id("txtYearSong")).sendKeys(year);
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
