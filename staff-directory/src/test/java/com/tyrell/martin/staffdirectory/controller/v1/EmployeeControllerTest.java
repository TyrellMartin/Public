package com.tyrell.martin.staffdirectory.controller.v1;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tyrell.martin.staffdirectory.dao.EmployeeDAO;
import com.tyrell.martin.staffdirectory.model.Employee;

public class EmployeeControllerTest {

	private EmployeeDAO employeeDAO = mock(EmployeeDAO.class);
	private final EmployeeController employeeController = new EmployeeController(employeeDAO);

	private String employeeName = "Employee Name";
	private Employee employee;

	@Before
	public void setUp() throws Exception {
		employee = Employee.builder().name(employeeName).build();

	}

	@Test
	public void testGetAllEmployees() {
		when(employeeDAO.getAllEmployees()).thenReturn(Collections.singletonList(employee));

		List<Employee> returnedEmployees = employeeController.getAllEmployees();
		
		assertEquals(employee, returnedEmployees.get(0));
	}

	@Test
	public void testGetEmployeeByName() {
		when(employeeDAO.getEmployeeByName(employeeName)).thenReturn(employee);

		Employee returnedEmployee = employeeController.getEmployeeByName(employeeName);
		
		assertEquals(employee, returnedEmployee);
	}

	@Test
	public void testUpdateEmployee() {
		when(employeeDAO.updateEmployee(employee)).thenReturn(employee);

		Employee updateEmployee = employeeController.updateEmployee(employee);
		
		assertEquals(employee, updateEmployee);
	}

	@Test
	public void tesCreateEmployee() {
		when(employeeDAO.createEmployee(employee)).thenReturn(employee);

		Employee returnedEmployee = employeeController.createEmployee(employee);
		
		assertEquals(employee, returnedEmployee);
	}

	
}
