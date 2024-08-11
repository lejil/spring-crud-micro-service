package com.emp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.exception.EmployeeServiceException;
import com.emp.model.Employee;
import com.emp.repository.EmployeeRepository;

/**
 * @author Lejil
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(Long id) {
		return Optional.ofNullable(employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeServiceException(id)));
	}

	@Override
	public Optional<Employee> updateEmployee(Long id, Employee employee) {
		return employeeRepository.findById(id).map(existingEmployee -> {
			existingEmployee.setEmail(employee.getEmail());
			existingEmployee.setSalary(employee.getSalary());
			existingEmployee.setTitle(employee.getTitle());
			existingEmployee.setDepartment(employee.getDepartment());
			return employeeRepository.save(existingEmployee);
		});
	}

	@Override
	public boolean deleteEmployee(Long id) {
		return employeeRepository.findById(id).map(employee -> {
			employeeRepository.delete(employee);
			return true;
		}).orElse(false);
	}
}
