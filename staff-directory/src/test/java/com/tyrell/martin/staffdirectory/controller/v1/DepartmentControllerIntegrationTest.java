package com.tyrell.martin.staffdirectory.controller.v1;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.NestedServletException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tyrell.martin.staffdirectory.dao.DepartmentDAO;
import com.tyrell.martin.staffdirectory.model.Department;

public class DepartmentControllerIntegrationTest {

	private DepartmentDAO departmentDAO = mock(DepartmentDAO.class);
	private final DepartmentController departmentController = new DepartmentController(departmentDAO);
	private final MockMvc mockMVC = MockMvcBuilders.standaloneSetup(departmentController).build();
	
	private String departmentName = "Department Name";
	private Department department;
	private String departmentJSON;

	private static final String API_V1 = "/api/v1";
	private static final String GET_ALL_DEPARTMENTS = "/allDepartments";
	private static final String GET_DEPARTMENT = "/department";
	private static final String REMOVE_DEPARTMENT = "/removeDepartment";
	private static final String UPDATE_DEPARTMENT = "/updateDepartment";
	private static final String ADD_DEPARTMENT = "/addDepartment";

	@Before
	public void setUp() throws Exception {
		department = Department.builder().name(departmentName).build();
		
		ObjectMapper mapper = new ObjectMapper();
		departmentJSON = mapper.writeValueAsString(department);

	}

	@Test
	public void testGetAllDepartments_should_succeed() throws Exception {
		when(departmentDAO.getAllDepartments()).thenReturn(Collections.EMPTY_LIST);

		mockMVC.perform(get(API_V1 + GET_ALL_DEPARTMENTS).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test(expected = NestedServletException.class)
	public void testGetAllDepartments_should_fail() throws Exception {
		when(departmentDAO.getAllDepartments()).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Failure"));

		mockMVC.perform(get(API_V1 + GET_ALL_DEPARTMENTS).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void testGetDepartmentByName_should_succeed() throws Exception {
		when(departmentDAO.getDepartmentByName(departmentName)).thenReturn(department);

		mockMVC.perform(get(API_V1 + GET_DEPARTMENT).contentType(MediaType.APPLICATION_JSON)
				.param("departmentName", departmentName)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test(expected = NestedServletException.class)
	public void testGetDepartmentByName_should_fail() throws Exception {
		when(departmentDAO.getDepartmentByName(departmentName)).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Failure"));

		mockMVC.perform(get(API_V1 + GET_DEPARTMENT).contentType(MediaType.APPLICATION_JSON)
				.param("departmentName", departmentName)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void testRemoveDepartmentByName_should_succeed() throws Exception {
//		when(departmentDAO.deleteDepartmentByName(departmentName)).thenReturn(department);

		mockMVC.perform(put(API_V1 + REMOVE_DEPARTMENT).contentType(MediaType.APPLICATION_JSON)
				.param("departmentName", departmentName)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test(expected = NestedServletException.class)
	public void testRemoveDepartmentByName_should_fail() throws Exception {
		doThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Failure")).when(departmentDAO).deleteDepartmentByName(departmentName);
		mockMVC.perform(put(API_V1 + REMOVE_DEPARTMENT).contentType(MediaType.APPLICATION_JSON)
				.param("departmentName", departmentName)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void testUpdateDepartment_should_succeed() throws Exception {
		when(departmentDAO.updateDepartment(department)).thenReturn(department);

		mockMVC.perform(post(API_V1 + UPDATE_DEPARTMENT).contentType(MediaType.APPLICATION_JSON)
				.content(departmentJSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test(expected = NestedServletException.class)
	public void testUpdateDepartment_should_fail() throws Exception {
		when(departmentDAO.updateDepartment(department)).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Failure"));

		mockMVC.perform(post(API_V1 + UPDATE_DEPARTMENT).contentType(MediaType.APPLICATION_JSON)
				.content(departmentJSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void testCreateDepartment_should_succeed() throws Exception {
		when(departmentDAO.createDepartment(department)).thenReturn(department);

		mockMVC.perform(put(API_V1 + ADD_DEPARTMENT).contentType(MediaType.APPLICATION_JSON)
				.content(departmentJSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test(expected = NestedServletException.class)
	public void testCreateDepartment_should_fail() throws Exception {
		when(departmentDAO.createDepartment(department)).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Failure"));

		
		mockMVC.perform(put(API_V1 + ADD_DEPARTMENT).contentType(MediaType.APPLICATION_JSON)
				.content(departmentJSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

}
