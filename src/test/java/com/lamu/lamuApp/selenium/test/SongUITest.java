package com.lamu.lamuApp.selenium.test;

import com.lamu.lamuApp.selenium.framework.view.SongView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SongUITest {

    SongView songView;

    @Before
    public void init() {
        this.songView = new SongView();
    }

    //Validar url
    @Test
    public void caseTest1() {
        songView.fillUrl("5648");
        songView.fillTittle("Hello");
        songView.fillArtist("U2");
        songView.fillGenre("Pop");
        songView.fillTrack("5");
        songView.fillYear("1995");

        songView.uploadButtonClick();
        String result = songView.getResponseText();

        Assert.assertEquals("Canción subida con éxito", result);
    }

    @Test
    public void CaseTest2() {
        songView.fillUrl("C:\\Users\\Casa\\Music\\Musica");
        songView.fillTittle("Hello");
        songView.fillArtist("Adele");
        songView.fillAlbum("Skyfall21");
        songView.fillTrack("5");
        songView.fillYear("1995");

        songView.uploadButtonClick();
        String result = songView.getResponseText();

        Assert.assertEquals("Canción subida con éxito", result);

    }
}
