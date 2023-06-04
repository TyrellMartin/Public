package com.tyrell.martin.staffdirectory.controller.v1;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tyrell.martin.staffdirectory.dao.DepartmentDAO;
import com.tyrell.martin.staffdirectory.model.Department;

@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest {

	private DepartmentDAO departmentDAO = mock(DepartmentDAO.class);
	private final DepartmentController departmentController = new DepartmentController(departmentDAO);

	private String departmentName = "Department Name";
	private Department department;

	@Before
	public void setUp() throws Exception {
		department = Department.builder().name(departmentName).build();

	}

	@Test
	public void testGetAllDepartments() {
		when(departmentDAO.getAllDepartments()).thenReturn(Collections.singletonList(department));

		List<Department> returnedDepartments = departmentController.getAllDepartments();
		
		assertEquals(department, returnedDepartments.get(0));
	}

	@Test
	public void testGetDepartmentByName() {
		when(departmentDAO.getDepartmentByName(departmentName)).thenReturn(department);

		Department returnedDepartment = departmentController.getDepartmentByName(departmentName);
		
		assertEquals(department, returnedDepartment);
	}

	@Test
	public void testUpdateDepartment() {
		when(departmentDAO.updateDepartment(department)).thenReturn(department);

		Department updateDepartment = departmentController.updateDepartment(department);
		
		assertEquals(department, updateDepartment);
	}

	@Test
	public void tesCreateDepartment() {
		when(departmentDAO.createDepartment(department)).thenReturn(department);

		Department returnedDepartment = departmentController.createDepartment(department);
		
		assertEquals(department, returnedDepartment);
	}

	
}
