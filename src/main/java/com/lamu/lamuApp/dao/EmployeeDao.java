package com.lamu.lamuApp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lamu.lamuApp.model.Employee;

public interface EmployeeDao extends CrudRepository<Employee, Long>{
	
	List<Employee> findByEmail(String email);
}
