package com.tyrell.martin.staffdirectory.dao;

import java.util.List;

import com.tyrell.martin.staffdirectory.model.Employee;

public interface EmployeeDAO {
	
	public List<Employee> getAllEmployees();
	
	public Employee getEmployeeByName(String name);
	
	public void deleteEmployeeByName(String name);
	
	public Employee updateEmployee(Employee employee);
	
	public Employee createEmployee(Employee employee);

}
