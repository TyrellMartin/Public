package com.tyrell.martin.staffdirectory.controller.v1;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import com.tyrell.martin.staffdirectory.dao.DepartmentDAO;
import com.tyrell.martin.staffdirectory.model.Department;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController("DepartmentController")
@RequestMapping(path = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class DepartmentController {
	
	private DepartmentDAO departmentDAO;

	@GetMapping(value = "allDepartments")
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	public List<Department> getAllDepartments() {

		return departmentDAO.getAllDepartments();
	}

	@GetMapping(value = "department")
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	public Department getDepartmentByName(
			@NotBlank(message = "Invalid Department Name") 
			@SQLInjectionSafe(message = "SQL Injection Detected") 
			@Valid 
			@RequestParam String departmentName) {

		return departmentDAO.getDepartmentByName(departmentName);
	}

	@PutMapping(value = "removeDepartment")
	@ResponseStatus(code = HttpStatus.OK)
	public void removeDepartmentByName(
			@NotBlank(message = "Invalid Department Name") 
			@SQLInjectionSafe(message = "SQL Injection Detected") 
			@Valid 
			@RequestParam String departmentName) {

		departmentDAO.deleteDepartmentByName(departmentName);;
	}

	@PostMapping(value = "updateDepartment")
	@ResponseStatus(code = HttpStatus.OK)

	@ResponseBody
	public Department updateDepartment(
			@NotBlank(message = "Invalid Department") 
			@Valid 
			@RequestBody Department department) {

		return departmentDAO.updateDepartment(department);
	}

	@PutMapping(value = "addDepartment")
	@ResponseStatus(code = HttpStatus.OK)
	public Department createDepartment(
			@NotBlank(message = "Invalid Department") 
			@Valid 
			@RequestBody Department department) {

		return departmentDAO.createDepartment(department);
	}

}
