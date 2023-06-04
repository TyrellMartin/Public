package com.tyrell.martin.staffdirectory.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tyrell.martin.staffdirectory.dao.DepartmentDAO;
import com.tyrell.martin.staffdirectory.model.Department;
import com.tyrell.martin.staffdirectory.repository.DepartmentRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DepartmentDAOImpl implements DepartmentDAO {
	
	DepartmentRepository repository;

	@Override
	public List<Department> getAllDepartments() {

		return repository.findAll();
	}

	@Override
	public Department getDepartmentByName(String name) {

		return repository.getById(name);
	}

	@Override
	public void deleteDepartmentByName(String name) {

		repository.deleteById(name);;
	}

	@Override
	public Department updateDepartment(Department department) {

		return repository.save(department);
	}

	@Override
	public Department createDepartment(Department department) {

		return repository.save(department);
	}

}
