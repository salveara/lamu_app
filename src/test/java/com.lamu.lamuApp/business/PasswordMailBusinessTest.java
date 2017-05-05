package com.lamu.lamuApp.business;

import com.lamu.lamuApp.dao.EmployeeDao;
import com.lamu.lamuApp.model.Employee;
import com.lamu.lamuApp.util.WebException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PasswordMailBusinessTest {

    private PasswordMailBusiness passwordMailBusiness;
    private Employee employee;

    @Mock
    private EmployeeDao employeeDao;

    @Before
    public void init() {
        this.passwordMailBusiness = new PasswordMailBusiness(employeeDao);
        this.employee = new Employee("Daisy J", "gatito3", "Daisy J");
    }

    @Test(expected = WebException.class)
    public void ifTheEmployeeEmailDoesNotExistThrowsError() throws WebException {
        List<Employee> employees = new ArrayList<>();
        when(employeeDao.findByEmail(employee.getEmail())).thenReturn(employees);
        passwordMailBusiness.sendMail(employee.getEmail());
    }
}
