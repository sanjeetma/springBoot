package com.bridgelabz.fundoo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler{
	static final String validation_Failure_Message="validation failure";

	@ExceptionHandler(value=UserNotFoundException.class)
	public ResponseEntity<Object> generalException(Exception e){
		
		//<String, String> errors=new HashMap<String,String>() ;
		return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		
		
	}
}
