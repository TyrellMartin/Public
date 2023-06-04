package com.tyrell.martin.staffdirectory.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tyrell.martin.staffdirectory.dao.EmployeeDAO;
import com.tyrell.martin.staffdirectory.model.Employee;
import com.tyrell.martin.staffdirectory.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EmployeeDAOImpl implements EmployeeDAO {
	
	EmployeeRepository repository;

	@Override
	public List<Employee> getAllEmployees() {

		return repository.findAll();
	}

	@Override
	public Employee getEmployeeByName(String name) {

		return repository.getById(name);
	}

	@Override
	public void deleteEmployeeByName(String name) {

		repository.deleteById(name);;
	}

	@Override
	public Employee updateEmployee(Employee employee) {

		return repository.save(employee);
	}

	@Override
	public Employee createEmployee(Employee employee) {

		return repository.save(employee);
	}

}
