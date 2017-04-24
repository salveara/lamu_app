package com.lamu.lamuApp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lamu.lamuApp.business.SongBusiness;
import com.lamu.lamuApp.dao.SongDao;
import com.lamu.lamuApp.model.Client;
import com.lamu.lamuApp.model.Song;
import com.lamu.lamuApp.util.WebException;

@RestController
public class SongController {

	@Autowired
	SongDao songDao;
	@Autowired
	SongBusiness SongBusiness;

	@RequestMapping(value = "/songs", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Song>> allSongs(
			@RequestParam(value = "name", required = false) String tittle) {
		if (tittle == null) {
			return new ResponseEntity<List<Song>>(songDao.findAll(), HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Song>>(songDao.findByTittleContaining("tittle"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/songs", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Song> addSong(HttpServletRequest request,
			@RequestParam(value = "url") String url, 
			@RequestParam(value = "tittle") String tittle,
			@RequestParam(value = "artist", required = false) String artist,
			@RequestParam(value = "album", required = false) String album,
			@RequestParam(value = "genre", required = false) String genre,
			@RequestParam(value = "track", required = false) String track,
			@RequestParam(value = "year", required = false) String year) {
		
		if (url != null) {
			
			try {
				
				SongBusiness.CheckDuplicateUrl(url);
				
				Song song = new Song(url, tittle, artist, album, genre, track, year);
				songDao.save(song);
				return new ResponseEntity<Song>(song, HttpStatus.CREATED);
				
			} catch (WebException webEx) {
				System.out.println(webEx.getTechnicalMessage());
				//esto hay que cambiarlo por un mensaje de error!!
				return new ResponseEntity<Song>(HttpStatus.BAD_REQUEST);
			}
			
		} else {
			return new ResponseEntity<Song>(HttpStatus.BAD_REQUEST);
		}
		
	}
}
