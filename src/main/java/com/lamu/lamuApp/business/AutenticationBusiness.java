package com.lamu.lamuApp.business;

import com.lamu.lamuApp.dao.EmployeeDao;
import com.lamu.lamuApp.model.Employee;
import com.lamu.lamuApp.util.WebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AutenticationBusiness {

    private EmployeeDao employeeDao;

    @Autowired
    public AutenticationBusiness(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void checkData(String givenUser, String givenPassword) throws WebException {
        List<Employee> lista = employeeDao.findByEmail(givenUser);
        if (!lista.isEmpty()) {
            Employee employee = lista.get(0);
            if (!givenPassword.equals(employee.getPassword())) {
                WebException webEx = new WebException();
                webEx.setUserMessage("Los datos ingresados no coinciden");
                webEx.setTechnicalMessage("!givenPassword.equals(validPassword) && !givenClient.equals(employee.getClient() is true");
                throw webEx;
            }
        } else {
            WebException webEx = new WebException();
            webEx.setUserMessage("No se encontró ningún empleado con los datos ingresados");
            webEx.setTechnicalMessage("lista.isEmpty()");
            throw webEx;
        }
    }

    public void CheckEmail(String email) throws WebException {
        Pattern pattern = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(email);

        if (!matcher.find()) {
            WebException webEx = new WebException();
            webEx.setUserMessage("El correo electronico debe tener un formato valido");
            webEx.setTechnicalMessage("The email does not have a correct format");
            throw webEx;
        }
    }
}
