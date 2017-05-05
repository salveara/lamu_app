package com.lamu.lamuApp.business;

import com.lamu.lamuApp.dao.SongDao;
import com.lamu.lamuApp.model.Song;
import com.lamu.lamuApp.util.WebException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SongBusinessTest {

    private SongBusiness songBusiness;
    private Song song;

    @Mock
    private SongDao songDao;

    @Before
    public void init() {
        this.songBusiness = new SongBusiness(songDao);
        this.song = new Song("5648", "Hello", "U2", "", "", "", "");
    }

    @Test(expected = WebException.class)
    public void ifThereIsASongWithTheSameUrlThrowsError() throws WebException {
        List<Song> songs = new ArrayList<>();
        songs.add(song);
        when(songDao.findByUrl(song.getUrl())).thenReturn(songs);
        songBusiness.CheckDuplicateUrl(song.getUrl());
    }

    @Test(expected = WebException.class)
    public void ifThereIsASongWithTheSameTittleAndArtistThrowsError() throws WebException {
        List<Song> songs = new ArrayList<>();
        songs.add(song);
        when(songDao.findByTittle(song.getTittle())).thenReturn(songs);
        songBusiness.CheckDuplicateTittleArtist(song.getTittle(), song.getArtist());
    }

    @Test(expected = WebException.class)
    public void ifTheTrackContainsNumbersAndLettersThrowsErrorCase() throws WebException {
        this.song.setYear("hola05");
        songBusiness.CheckTrackOnlyContainsNumbers(song.getTrack());
    }

    @Test(expected = WebException.class)
    public void ifTheTrackContainsJustLettersThrowsErrorCase() throws WebException {
        this.song.setTrack("tryrt");
        songBusiness.CheckTrackOnlyContainsNumbers(song.getTrack());
    }

    @Test(expected = WebException.class)
    public void theYearIsADecimalThrowsError() throws WebException {
        this.song.setYear("1,5");
        songBusiness.CheckYearFormat(song.getYear());
    }

    @Test(expected = WebException.class)
    public void theYearHasLessThanFourCharacteresThrowsError() throws WebException {
        this.song.setYear("95");
        songBusiness.CheckYearFormat(song.getYear());
    }
}
