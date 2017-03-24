package com.lamu.lamuApp.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamu.lamuApp.dao.ClientDao;
import com.lamu.lamuApp.model.Client;
import com.lamu.lamuApp.util.WebException;

@Service
public class ClientBusiness {
	
	@Autowired
	private ClientDao clientDao;
	
	public void CheckPassword(String password) throws WebException{
		if(password.length() < 7){
			WebException webEx = new WebException();
			webEx.setUserMessage("La contraseña debe ser mayor de 7 caracteres");
			webEx.setTechnicalMessage("password.lenght menor a 7 caracteres");
			throw webEx;
		}
		
	}
	
	public void CheckDuplicateEmail(String email) throws WebException{
		List<Client> list = clientDao.findByEmail(email);

		if(!list.isEmpty()){
			WebException webEx = new WebException();
			webEx.setUserMessage("El correo ya se encuentra registrado");
			webEx.setTechnicalMessage("la lista clientDao.findByEmail(email) no está vacia");
			throw webEx;
			
		}
	}
	
	public void CheckDuplicateUser(String user) throws WebException{
		List<Client> list = clientDao.findByUser(user);

		if(!list.isEmpty()){
			WebException webEx = new WebException();
			webEx.setUserMessage("El usuario ya se encuentra registrado");
			webEx.setTechnicalMessage("la lista clientDao.findByUser(user) no está vacia");
			throw webEx;
		}
	}
	
	public void SaveClient(Client client){
		clientDao.save(client);
	}
}
