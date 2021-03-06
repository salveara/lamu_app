package com.lamu.lamuApp.business;

import com.lamu.lamuApp.dao.EmployeeDao;
import com.lamu.lamuApp.model.Employee;
import com.lamu.lamuApp.util.WebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordMailBusiness {

    private EmployeeDao employeeDao;

    @Autowired
    public PasswordMailBusiness(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void sendMail(String email) throws WebException {
        List<Employee> lista = employeeDao.findByEmail(email);
        if (!lista.isEmpty()) {
            Employee employee = lista.get(0);
            String correo = employee.getEmail();
            //TODO send mail
        } else {
            WebException webEx = new WebException();
            webEx.setUserMessage("No se encontró ningún empleado con los datos ingresados");
            webEx.setTechnicalMessage("lista.isEmpty()");
            throw webEx;
        }
    }
}
