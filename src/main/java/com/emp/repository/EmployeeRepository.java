package com.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emp.model.Employee;

/**
 * @author Lejil
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
