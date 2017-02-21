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

import com.lamu.lamuApp.AplicationSetUp;
import com.lamu.lamuApp.dao.ClientDao;
import com.lamu.lamuApp.dao.MusicProducerDao;
import com.lamu.lamuApp.dao.ReproductionListDao;
import com.lamu.lamuApp.model.Client;
import com.lamu.lamuApp.model.MusicProducer;
import com.lamu.lamuApp.model.ReproductionList;

@RestController
public class ReproductionListController {

	@Autowired
	ReproductionListDao reproductionListDao;

	@Autowired
	ClientDao clientDao;

	@Autowired
	MusicProducerDao musicProducerDao;

	@RequestMapping(value = "/reproductionlists", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<ReproductionList>> getReproductionList(){
		List<ReproductionList> reproductionList = reproductionListDao.findAll();
		if(reproductionList == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<List<ReproductionList>>(reproductionList, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/reproductionlists", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ReproductionList> addResroductionList(HttpServletRequest request,
			@RequestParam(value = "id") String id) {
		if (id != null) {
			// TODO save music producer in a session
			MusicProducer musicProducer = musicProducerDao.findAll().get(0);
			// TODO send the id of client
			Client client = clientDao.findByName(id).get(0);
			ReproductionList reproductionList = new ReproductionList(musicProducer, client);
			reproductionListDao.save(reproductionList);
			return new ResponseEntity<ReproductionList>(reproductionList, HttpStatus.OK);
		} else {
			return new ResponseEntity<ReproductionList>(HttpStatus.BAD_REQUEST);
		}
	}
}
