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
import com.tyrell.martin.staffdirectory.dao.EmployeeDAO;
import com.tyrell.martin.staffdirectory.model.Employee;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController("EmployeeController")
@RequestMapping(path = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class EmployeeController {
	
	private EmployeeDAO employeeDAO;
	
	@GetMapping(value = "allEmployees")
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	public List<Employee> getAllEmployees() {

		return employeeDAO.getAllEmployees();
	}

	@GetMapping(value = "employee")
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	public Employee getEmployeeByName(
			@NotBlank(message = "Invalid Employee Name") 
			@SQLInjectionSafe(message = "SQL Injection Detected") 
			@Valid 
			@RequestParam String employeeName) {

		return employeeDAO.getEmployeeByName(employeeName);
	}

	@PutMapping(value = "removeEmployee")
	@ResponseStatus(code = HttpStatus.OK)
	public void removeEmployeeByName(
			@NotBlank(message = "Invalid Employee Name") 
			@SQLInjectionSafe(message = "SQL Injection Detected") 
			@Valid 
			@RequestParam String employeeName) {

		employeeDAO.deleteEmployeeByName(employeeName);
	}

	@PostMapping(value = "updateEmployee")
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	public Employee updateEmployee(
			@NotBlank(message = "Invalid Employee") 
			@Valid 
			@RequestBody Employee employee) {

		return employeeDAO.updateEmployee(employee);
	}

	@PutMapping(value = "addEmployee")
	@ResponseStatus(code = HttpStatus.OK)
	public Employee createEmployee(
			@NotBlank(message = "Invalid Employee") 
			@Valid 
			@RequestBody Employee employee) {

		return employeeDAO.createEmployee(employee);
	}


}
