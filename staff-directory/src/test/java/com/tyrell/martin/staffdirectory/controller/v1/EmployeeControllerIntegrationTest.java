package com.tyrell.martin.staffdirectory.controller.v1;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.NestedServletException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tyrell.martin.staffdirectory.dao.EmployeeDAO;
import com.tyrell.martin.staffdirectory.model.Employee;

public class EmployeeControllerIntegrationTest {

	private EmployeeDAO employeeDAO = mock(EmployeeDAO.class);
	private final EmployeeController employeeController = new EmployeeController(employeeDAO);
	private final MockMvc mockMVC = MockMvcBuilders.standaloneSetup(employeeController).build();
	
	private String employeeName = "Employee Name";
	private Employee employee;
	private String employeeJSON;

	private static final String API_V1 = "/api/v1";
	private static final String GET_ALL_EMPLOYEES = "/allEmployees";
	private static final String GET_EMPLOYEE = "/employee";
	private static final String REMOVE_EMPLOYEE = "/removeEmployee";
	private static final String UPDATE_EMPLOYEE = "/updateEmployee";
	private static final String ADD_EMPLOYEE = "/addEmployee";

	@Before
	public void setUpBeforeClass() throws Exception {
		employee = Employee.builder().name(employeeName).build();
		
		ObjectMapper mapper = new ObjectMapper();
		employeeJSON = mapper.writeValueAsString(employee);

	}

	@Test
	public void testGetAllEmployees_should_succeed() throws Exception {
		when(employeeDAO.getAllEmployees()).thenReturn(Collections.EMPTY_LIST);

		mockMVC.perform(get(API_V1 + GET_ALL_EMPLOYEES).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test(expected = NestedServletException.class)
	public void testGetAllEmployees_should_fail() throws Exception {
		when(employeeDAO.getAllEmployees()).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Failure"));

		mockMVC.perform(get(API_V1 + GET_ALL_EMPLOYEES).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void testGetEmployeeByName_should_succeed() throws Exception {
		when(employeeDAO.getEmployeeByName(employeeName)).thenReturn(employee);

		mockMVC.perform(get(API_V1 + GET_EMPLOYEE).contentType(MediaType.APPLICATION_JSON)
				.param("employeeName", employeeName)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test(expected = NestedServletException.class)
	public void testGetEmployeeByName_should_fail() throws Exception {
		when(employeeDAO.getEmployeeByName(employeeName)).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Failure"));

		mockMVC.perform(get(API_V1 + GET_EMPLOYEE).contentType(MediaType.APPLICATION_JSON)
				.param("employeeName", employeeName)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void testRemoveEmployeeByName_should_succeed() throws Exception {
//		when(employeeDAO.deleteEmployeeByName(employeeName)).thenReturn(employee);

		mockMVC.perform(put(API_V1 + REMOVE_EMPLOYEE).contentType(MediaType.APPLICATION_JSON)
				.param("employeeName", employeeName)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test(expected = NestedServletException.class)
	public void testRemoveEmployeeByName_should_fail() throws Exception {
		doThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Failure")).when(employeeDAO).deleteEmployeeByName(employeeName);
		mockMVC.perform(put(API_V1 + REMOVE_EMPLOYEE).contentType(MediaType.APPLICATION_JSON)
				.param("employeeName", employeeName)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void testUpdateEmployee_should_succeed() throws Exception {
		when(employeeDAO.updateEmployee(employee)).thenReturn(employee);

		mockMVC.perform(post(API_V1 + UPDATE_EMPLOYEE).contentType(MediaType.APPLICATION_JSON)
				.content(employeeJSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test(expected = NestedServletException.class)
	public void testUpdateEmployee_should_fail() throws Exception {
		when(employeeDAO.updateEmployee(employee)).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Failure"));

		mockMVC.perform(post(API_V1 + UPDATE_EMPLOYEE).contentType(MediaType.APPLICATION_JSON)
				.content(employeeJSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void testCreateEmployee_should_succeed() throws Exception {
		when(employeeDAO.createEmployee(employee)).thenReturn(employee);

		mockMVC.perform(put(API_V1 + ADD_EMPLOYEE).contentType(MediaType.APPLICATION_JSON)
				.content(employeeJSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test(expected = NestedServletException.class)
	public void testCreateEmployee_should_fail() throws Exception {
		when(employeeDAO.createEmployee(employee)).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Failure"));

		
		mockMVC.perform(put(API_V1 + ADD_EMPLOYEE).contentType(MediaType.APPLICATION_JSON)
				.content(employeeJSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

}
