package com.javarnd.hrmis.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.javarnd.hrmis.dto.ErrorMessage;
import com.javarnd.hrmis.exception.ExistingEmployeeCodeException;

@ControllerAdvice
public class GlobalExceptionHandler {

	static {
		System.out.println("GlobalExceptionHandler class static block");
	}
	
	public GlobalExceptionHandler() {
		System.out.println("GlobalExceptionHandler class default constructor");
	}
	
	@ExceptionHandler(ExistingEmployeeCodeException.class)
	public ResponseEntity<ErrorMessage> existingEmployeeCodeExceptionHandler(ExistingEmployeeCodeException ex) {
		ex.printStackTrace();
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> exceptionHandler(Exception ex) {
		ex.printStackTrace();
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setErrorMessage("Please contact your administrator");
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}