package com.emp.controller;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.model.Employee;
import com.emp.service.EmployeeService;

/**
 * @author Lejil
 *
 */


@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeService;

	// Create a new employee
	@PostMapping("employee")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		Employee savedEmployee = employeService.createEmployee(employee);
		return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
	}

	// Get all employees
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeService.getAllEmployees().stream().collect(Collectors.toList());
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	// Get all employees
	@GetMapping("/employeesByDept")
	public ResponseEntity<?> getAllEmployeesByGrouping() {
		Map<String, List<Employee>> employees = employeService.getAllEmployees().stream()
				.collect(Collectors.groupingBy(emp -> emp.getDepartment()));
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	// Get all employees
	@GetMapping("/employeesMultiGroup")
	public ResponseEntity<?> getAllEmployeesByMultiGrouping() {
		Map<Object, List<Employee>> employees = employeService.getAllEmployees().stream()
				.collect(Collectors.groupingBy(emp -> new SimpleEntry<>(emp.getDepartment(), emp.getTitle())));
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}


	// Get all employees
	@GetMapping("/employeesUnique")
	public ResponseEntity<?> getAllEmployeesUnique() {
		List<Employee> employees = employeService.getAllEmployees().stream().distinct().collect(Collectors.toList());
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	// Get an employee by ID
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Optional<Employee> employee = employeService.getEmployeeById(id);
		return employee.map(emp -> new ResponseEntity<>(emp, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	// Update an employee by ID
	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		Optional<Employee> updatedEmployee = employeService.updateEmployee(id, employee);
		return updatedEmployee.map(emp -> new ResponseEntity<>(emp, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	// Delete an employee by ID
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		if (employeService.deleteEmployee(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
