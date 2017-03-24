package com.lamu.lamuApp.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.lamu.lamuApp.dao.EmployeeDao;
import com.lamu.lamuApp.model.Employee;
import com.lamu.lamuApp.util.WebException;

@RestController
public class AutenticationBusiness {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	public void checkData(String givenUser, String givenPassword, String givenClient) throws WebException{
		
		List<Employee> lista = employeeDao.findByUser(givenUser);
		
		if (!lista.isEmpty()){
			Employee employee = lista.get(0);
			
			if(!givenPassword.equals(employee.getPassword()) && !givenClient.equals(employee.getClient())){
				
				WebException webEx = new WebException();
				webEx.setUserMessage("Los datos ingresados no coinciden");
				webEx.setTechnicalMessage("!givenPassword.equals(validPassword) && !givenClient.equals(employee.getClient() es true");
				throw webEx;
			}
		}else{
			WebException webEx = new WebException();
			webEx.setUserMessage("No se encontró ningún empleado con los datos ingresados");
			webEx.setTechnicalMessage("lista.isEmpty()");
			throw webEx;
		}
		
			
			
	}
	
}
