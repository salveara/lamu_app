package com.lamu.lamuApp.business;

import com.lamu.lamuApp.dao.SongDao;
import com.lamu.lamuApp.model.Song;
import com.lamu.lamuApp.util.WebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongBusiness {

    private SongDao songDao;

    @Autowired
    public SongBusiness(SongDao songDao) {
        this.songDao = songDao;
    }

    public void CheckDuplicateUrl(String url) throws WebException {
        List<Song> list = songDao.findByUrl(url);

        if (!list.isEmpty()) {
            WebException webEx = new WebException();
            webEx.setUserMessage("La canción ya se encuentra registrada");
            webEx.setTechnicalMessage("la lista songDao.findByUrl(url) no está vacia");
            throw webEx;

        }
    }

    public void CheckDuplicateTittleArtist(String tittle, String artist) throws WebException {
        List<Song> list = songDao.findByTittle(tittle);

        for (Song song : list) {
            if (song.getArtist().equals(artist)) {
                WebException webEx = new WebException();
                webEx.setUserMessage("El titulo y el artista ya se encuentran registrados");
                webEx.setTechnicalMessage("song.getArtist().equals(artist) es verdadero");
                throw webEx;
            }
        }
    }

    public void CheckTrackOnlyContainsNumbers(String track) throws WebException {
        if (track != null && !"".equals(track)) {
            try {
                Integer.valueOf(track);
            } catch (Exception e) {
                WebException webEx = new WebException();
                webEx.setUserMessage("El track de la cancion solo debe contener caracteres numericos");
                webEx.setTechnicalMessage("Track can not be parsed to int");
                throw webEx;
            }
        }
    }

    public void CheckYearFormat(String year) throws WebException {
        try {
            Integer.valueOf(year);
            if (year.length() != 4) {
                throw new WebException();
            }
        } catch (Exception e) {
            WebException webEx = new WebException();
            webEx.setUserMessage("El año de la cancion debe estar en formato aaaa");
            webEx.setTechnicalMessage("The format of the year is not yyyy");
            throw webEx;
        }
    }

    public void SaveSong(Song song) {
        songDao.save(song);
        songDao.delete(song);
    }

    public void CheckUrlCanNotOnlyContainsNumbers(String url) throws WebException {
        if (url != null && !"".equals(url)) {
            String regex = "[0-9]+";
            if (url.matches(regex)) {
                WebException webEx = new WebException();
                webEx.setUserMessage("La URL no puede contener solo números");
                webEx.setTechnicalMessage("URL contains only numbers");
                throw webEx;
            }
        }
    }

    public void CheckFieldOnlyContainsNumbers(String field, String value) throws WebException {
        String regex = "[0-9]+";
        if (value.matches(regex)) {
            WebException webEx = new WebException();
            webEx.setUserMessage(field + " no puede contener solo números");
            webEx.setTechnicalMessage(field + " contains only numbers");
            throw webEx;
        }
    }
}
