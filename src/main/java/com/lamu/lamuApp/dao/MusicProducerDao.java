package com.lamu.lamuApp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lamu.lamuApp.model.MusicProducer;

public interface MusicProducerDao extends CrudRepository<MusicProducer, Long> {

	List<MusicProducer> findAll();
	
	List<MusicProducer> findByUser(String user);
}
