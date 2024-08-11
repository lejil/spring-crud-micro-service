package com.emp.exception;

/**
 * @author Lejil
 *
 */
@SuppressWarnings("serial")
public class EmployeeServiceException extends RuntimeException {

    public EmployeeServiceException(Long id) {
        super("Employee with ID " + id + " not found.");
    }
    
    public EmployeeServiceException(String message) {
        super(message);
    }
}