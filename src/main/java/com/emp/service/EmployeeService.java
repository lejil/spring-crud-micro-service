package com.emp.service;

import java.util.List;
import java.util.Optional;

import com.emp.model.Employee;

/**
 * @author Lejil
 *
 */
public interface EmployeeService {

	Employee createEmployee(Employee employee);

	List<Employee> getAllEmployees();

	Optional<Employee> getEmployeeById(Long id);

	Optional<Employee> updateEmployee(Long id, Employee employee);

	boolean deleteEmployee(Long id);
}
