package com.lamu.lamuApp.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.lamu.lamuApp.dao.ClientDao;
import com.lamu.lamuApp.dao.MusicProducerDao;
import com.lamu.lamuApp.model.Client;
import com.lamu.lamuApp.model.MusicProducer;
import com.lamu.lamuApp.util.WebException;

@RestController
public class MainBusiness {
	
	@Autowired
	private ClientDao clientDao;
	@Autowired
	private MusicProducerDao musicProducerDao;
	
	public void checkPassword(String givenUser, String givenPassword, String userType) throws WebException{
		
		String validPassword = findPassword(givenUser, userType);
			
			if(!givenPassword.equals(validPassword)){
				
				WebException webEx = new WebException();
				webEx.setUserMessage("Contraseña invalida");
				webEx.setTechnicalMessage("!givenPassword.equals(validPassword) es true");
				throw webEx;
			}
	}
	
	public String findPassword(String givenUser, String userType) throws WebException{
		List<Client> clientsByUser = new ArrayList<>();
		List<Client> clientsByEmail = new ArrayList<>();
		List<MusicProducer> producersByUser = new ArrayList<>();;
		
		if (userType.equals("Cliente")){
			clientsByUser = clientDao.findByUser(givenUser);
			clientsByEmail = clientDao.findByEmail(givenUser);
		}
		if (userType.equals("Productor musical")){
			producersByUser = musicProducerDao.findByUser(givenUser);
		}
		

		if(clientsByUser.isEmpty() && clientsByEmail.isEmpty() && producersByUser.isEmpty()){
			WebException webEx = new WebException();
			webEx.setUserMessage("Nombre de usuario o email invalido");
			webEx.setTechnicalMessage("la lista clientDao.findByUser(user) y clientDao.findByEmail(user) está vacia");
			throw webEx;
		}else{
			
			if(!clientsByUser.isEmpty()){
				return clientsByUser.get(0).getPassword();
			}else{
				if(!clientsByEmail.isEmpty()){
					return clientsByEmail.get(0).getPassword();
				}else{
					return producersByUser.get(0).getPassword();
				}
			}
		}
	}
	
}
