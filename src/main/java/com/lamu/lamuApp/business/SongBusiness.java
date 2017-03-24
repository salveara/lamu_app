package com.lamu.lamuApp.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamu.lamuApp.dao.SongDao;
import com.lamu.lamuApp.model.Song;
import com.lamu.lamuApp.util.WebException;

@Service
public class SongBusiness {
	
	@Autowired
	private SongDao songDao;
	
	public void CheckDuplicateUrl(String url) throws WebException{
		List<Song> list = songDao.findByUrl(url);

		if(!list.isEmpty()){
			WebException webEx = new WebException();
			webEx.setUserMessage("La canción ya se encuentra registrada");
			webEx.setTechnicalMessage("la lista songDao.findByUrl(url) no está vacia");
			throw webEx;
			
		}
	}
	
	public void SaveSong(Song song){
		songDao.save(song);
	}
}
