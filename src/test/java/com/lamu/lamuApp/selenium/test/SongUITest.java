package com.lamu.lamuApp.selenium.test;

import com.lamu.lamuApp.selenium.framework.view.SongView;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SongUITest {

    SongView songView;

    @Before
    public void init() {
        this.songView = new SongView();
    }

    @After
    public void backToInicialState() {

    }

    @Test
    public void testCase1_urlCanNotBeNumbers() {
        songView.fillUrl("5648");
        songView.fillTittle("Hello");
        songView.fillArtist("U2");
        songView.fillGenre("Pop");
        songView.fillTrack("5");
        songView.fillYear("1995");

        songView.uploadButtonClick();
        String result = songView.getResponseText();

        Assert.assertEquals("La URL no puede contener solo números", result);
    }

    @Test
    public void testCase2_songUploadSuccesfullWithoutSomeNotRequeriedFields() {
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

    @Test
    public void testCase3_tittleIsARequiredField() {
        songView.fillUrl("C:\\Users\\Casa\\Music\\Musica");
        songView.fillArtist("U2");
        songView.fillAlbum("Skyfall");
        songView.fillGenre("Pop");
        songView.fillTrack("5");
        songView.fillYear("1995");

        songView.uploadButtonClick();
        String result = songView.getResponseText();

        Assert.assertEquals("Llenar los campos requeridos", result);
    }

    @Test
    public void testCase4_artisturlCanNotBeNumbers() {
        songView.fillUrl("C:\\Users\\Casa\\Music\\Musica");
        songView.fillTittle("Hello");
        songView.fillArtist("3425");
        songView.fillAlbum("Skyfall21");
        songView.fillGenre("Pop");
        songView.fillTrack("5");
        songView.fillYear("1995");

        songView.uploadButtonClick();
        String result = songView.getResponseText();

        Assert.assertEquals("Artista no puede contener solo números", result);
    }

    @Test
    public void testCase5_songUploadSuccesfullWithoutNotRequeridFields() {
        songView.fillUrl("C:\\Users\\Casa\\Music\\Musica");
        songView.fillTittle("Hello");
        songView.fillArtist("Adele");
        songView.fillYear("1995");

        songView.uploadButtonClick();
        String result = songView.getResponseText();

        Assert.assertEquals("Canción subida con éxito", result);
    }

    @Test
    public void testCase6_urlIsARequiredField() {
        songView.fillTittle("Hello");
        songView.fillArtist("U2");
        songView.fillAlbum("21");
        songView.fillTrack("5");
        songView.fillYear("1995");

        songView.uploadButtonClick();
        String result = songView.getResponseText();

        Assert.assertEquals("Llenar los campos requeridos", result);
    }

    @Test
    public void testCase7_artistIsARequiredField() {
        songView.fillUrl("C:\\Users\\Casa\\Music\\Musica");
        songView.fillTittle("Hello");
        songView.fillAlbum("Skyfall");
        songView.fillGenre("Pop");
        songView.fillYear("1995");

        songView.uploadButtonClick();
        String result = songView.getResponseText();

        Assert.assertEquals("Llenar los campos requeridos", result);
    }

    @Test
    public void testCase8_trackDoedNotMatchWithTheFormat() {
        songView.fillUrl("C:\\Users\\Casa\\Music\\Musica");
        songView.fillTittle("Hello");
        songView.fillArtist("Adele");
        songView.fillGenre("Pop");
        songView.fillTrack("tryrt");

        songView.uploadButtonClick();
        String result = songView.getResponseText();

        Assert.assertEquals("El track de la cancion solo debe contener caracteres numericos", result);
    }

    @Test
    public void testCase9_songUploadSuccesfull() {
        songView.fillUrl("C:\\Users\\Casa\\Music\\Musica");
        songView.fillTittle("Hello");
        songView.fillArtist("U2");
        songView.fillAlbum("Skyfall");
        songView.fillGenre("Pop");
        songView.fillTrack("5");
        songView.fillYear("1995");

        songView.uploadButtonClick();
        String result = songView.getResponseText();

        Assert.assertEquals("Canción subida con éxito", result);
    }

    @Test
    public void testCase10_requiredFieldsAreNeccesary() {
        songView.fillYear("1995");

        songView.uploadButtonClick();
        String result = songView.getResponseText();

        Assert.assertEquals("Llenar los campos requeridos", result);
    }

    @Test
    public void testCase11_requiredFieldsAreNeccesary() {
        songView.fillUrl("5648");
        songView.fillArtist("Adele");
        songView.fillAlbum("21");
        songView.fillTrack("5");
        songView.fillYear("1995");

        songView.uploadButtonClick();
        String result = songView.getResponseText();

        Assert.assertEquals("Llenar los campos requeridos", result);
    }

    @Test
    public void testCase12_requiredFieldsAreNeccesary() {
        songView.fillUrl("5648");
        songView.fillArtist("3425");
        songView.fillAlbum("Skyfall21");
        songView.fillGenre("Pop");
        songView.fillYear("1995");

        songView.uploadButtonClick();
        String result = songView.getResponseText();

        Assert.assertEquals("Llenar los campos requeridos", result);
    }

    @Test
    public void testCase13_urlCanNotContainsJustNumbers() {
        songView.fillUrl("5648");
        songView.fillTittle("Hello");
        songView.fillArtist("3425");

        songView.uploadButtonClick();
        String result = songView.getResponseText();

        Assert.assertEquals("La URL no puede contener solo números", result);
    }

    @Test
    public void testCase14_artistCanNotBeNumbers() {
        songView.fillUrl("C:\\Users\\Casa\\Music\\Musica");
        songView.fillTittle("Hello");
        songView.fillArtist("Adele");
        songView.fillGenre("23123");
        songView.fillYear("1995");

        songView.uploadButtonClick();
        String result = songView.getResponseText();

        Assert.assertEquals("Genero no puede contener solo números", result);
    }

    @Test
    public void testCase15_trackCanOnlyContainsNumbers() {
        songView.fillUrl("C:\\Users\\Casa\\Music\\Musica");
        songView.fillTittle("Hello");
        songView.fillArtist("Adele");
        songView.fillAlbum("21");
        songView.fillGenre("Pop");
        songView.fillTrack("hola05");
        songView.fillYear("1,5");

        songView.uploadButtonClick();
        String result = songView.getResponseText();

        Assert.assertEquals("El track de la cancion solo debe contener caracteres numericos", result);
    }

    @Test
    public void testCase16_albumIsNotRequired() {
        songView.fillUrl("C:\\Users\\Casa\\Music\\Musica");
        songView.fillTittle("Hello");
        songView.fillArtist("Adele");
        songView.fillGenre("Pop");
        songView.fillTrack("5");
        songView.fillYear("1995");

        songView.uploadButtonClick();
        String result = songView.getResponseText();

        Assert.assertEquals("Canción subida con éxito", result);
    }

    @Test
    public void testCase17_genreIsNotARequired() {
        songView.fillUrl("C:\\Users\\Casa\\Music\\Musica");
        songView.fillTittle("Hello");
        songView.fillArtist("Adele");
        songView.fillAlbum("Skyfall");
        songView.fillTrack("5");
        songView.fillYear("1995");

        songView.uploadButtonClick();
        String result = songView.getResponseText();

        Assert.assertEquals("Canción subida con éxito", result);
    }

    @Test
    public void testCase18_trackIsNotRequired() {
        songView.fillUrl("C:\\Users\\Casa\\Music\\Musica");
        songView.fillTittle("Hello");
        songView.fillArtist("Adele");
        songView.fillAlbum("Skyfall21");
        songView.fillGenre("Pop");
        songView.fillYear("1995");

        songView.uploadButtonClick();
        String result = songView.getResponseText();

        Assert.assertEquals("Canción subida con éxito", result);
    }

    @Test public void testCase19_requieredFieldAreNeccesaryAndANotRequiredFieldIsNot() {
        songView.fillUrl("C:\\Users\\Casa\\Music\\Musica");
        songView.fillGenre("Pop");
        songView.fillTrack("5");
        songView.fillYear("1995");

        songView.uploadButtonClick();
        String result = songView.getResponseText();

        Assert.assertEquals("Llenar los campos requeridos", result);
    }

    @Test
    public void testCase20_allRequieredFieldAreNeccesaryAndANotRequiredFieldIsNot() {
        songView.fillAlbum("21");
        songView.fillGenre("Pop");
        songView.fillTrack("5");

        songView.uploadButtonClick();
        String result = songView.getResponseText();

        Assert.assertEquals("Llenar los campos requeridos", result);
    }

    @Test
    public void testCase21_yearDoedNotMatchTheFormat() {
        songView.fillUrl("C:\\Users\\Casa\\Music\\Musica");
        songView.fillTittle("Hello");
        songView.fillArtist("Adele");
        songView.fillAlbum("Skyfall21");
        songView.fillGenre("Pop");
        songView.fillYear("95");

        songView.uploadButtonClick();
        String result = songView.getResponseText();

        Assert.assertEquals("El año de la cancion debe estar en formato aaaa", result);
    }

    @Test
    public void testCase22_artisturlCanNotBeNumbers() {
        songView.fillUrl("C:\\Users\\Casa\\Music\\Musica");
        songView.fillTittle("Hello");
        songView.fillArtist("Adele");
        songView.fillAlbum("Skyfall21");
        songView.fillYear("1,5");

        songView.uploadButtonClick();
        String result = songView.getResponseText();

        Assert.assertEquals("El año de la cancion debe estar en formato aaaa", result);
    }
}
