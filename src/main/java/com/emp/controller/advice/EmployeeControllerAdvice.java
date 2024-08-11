package com.emp.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.emp.exception.EmployeeServiceException;
import com.emp.exception.ErrorResponse;

/**
 * @author Lejil
 *
 */

@RestControllerAdvice
public class EmployeeControllerAdvice {

	// Handle other exceptions globally
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneralException(Exception ex) {
		return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(EmployeeServiceException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(EmployeeServiceException ex) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<>(errorResponse, HttpStatus.OK);
	}
}
