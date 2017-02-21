package com.lamu.lamuApp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lamu.lamuApp.dao.ClientDao;
import com.lamu.lamuApp.dao.MusicProducerDao;
import com.lamu.lamuApp.dao.SongDao;
import com.lamu.lamuApp.model.Client;
import com.lamu.lamuApp.model.MusicProducer;
import com.lamu.lamuApp.model.Song;

@Component
public class AplicationSetUp {

	@Autowired
	ClientDao clientDao;
	@Autowired
	SongDao songDao;
	@Autowired
	MusicProducerDao musicProducerDao;

	@PostConstruct
	public void setUp() {
		clientDao.save(new Client("santiago123", "123", "santiago", "santiago@mi.com", "3125845"));
		clientDao.save(new Client("yeny123", "123", "yeny", "yeny@mi.com", "5365845"));
		clientDao.save(new Client("laura123", "123", "laura", "laura@mi.com", "4525845"));
		clientDao.save(new Client("miguel123", "123", "miguel", "miguel@mi.com", "9545845"));
		clientDao.save(new Client("ana123", "123", "ana", "ana@mi.com", "6985845"));
		songDao.save(new Song("www.mymusic/yesterday", "yesterday", "the beatles", "white album", "rock", "21", 1962));
		songDao.save(new Song("www.mymusic/let it be", "let it be", "the beatles", "white album", "rock", "25", 1962));
		songDao.save(new Song("www.mymusic/triller", "triller", "Michael Jackson", "this is it", "pop", "24", 1990));
		songDao.save(new Song("www.mymusic/beat it", "beat it", "Michael Jackson", "this is it", "pop", "1", 1985));
		songDao.save(new Song("www.mymusic/Like a prayer", "Like a prayer", "Madonna", "madonna", "pop", "0", 1998));
		musicProducerDao.save(new MusicProducer("musicStore", "3165"));
	}
}
