package com.tyrell.martin.staffdirectory.dao;

import java.util.List;

import com.tyrell.martin.staffdirectory.model.Department;

public interface DepartmentDAO {
	
	public List<Department> getAllDepartments();
	
	public Department getDepartmentByName(String name);
	
	public void deleteDepartmentByName(String name);
	
	public Department updateDepartment(Department department);
	
	public Department createDepartment(Department department);

}
