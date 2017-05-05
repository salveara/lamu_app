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
public class AutenticationBusinessTest {

    private AutenticationBusiness autenticationBusiness;
    private Employee employee;

    @Mock
    private EmployeeDao employeeDao;

    @Before
    public void init() {
        this.autenticationBusiness = new AutenticationBusiness(employeeDao);
        this.employee = new Employee("Daisy J", "gatito3", "Daisy J");
    }

    @Test(expected = WebException.class)
    public void ifTheUserAndPasswordDoesNotMatchThrowsError() throws WebException {
        List<Employee> employees = new ArrayList<>();
        Employee employeeBD = new Employee("Daisy J", "perrito3", "Daisy");
        employees.add(employeeBD);
        when(employeeDao.findByUser(employee.getUser())).thenReturn(employees);
        autenticationBusiness.checkData(employee.getUser(), employee.getPassword(), employee.getClient());
    }

    @Test(expected = WebException.class)
    public void ifTheUseDoesNotExistThrowsError() throws WebException {
        List<Employee> employees = new ArrayList<>();
        when(employeeDao.findByUser(employee.getUser())).thenReturn(employees);
        autenticationBusiness.checkData(employee.getUser(), employee.getPassword(), employee.getClient());
    }


}
