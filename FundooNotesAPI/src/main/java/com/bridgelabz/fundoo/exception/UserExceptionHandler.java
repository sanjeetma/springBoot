package com.bridgelabz.fundoo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler{
	//static final String validation_Failure_Message="validation failure";

	@ExceptionHandler(value=UserNotFoundException.class)
	public ResponseEntity<Object> generalException(Exception e){
		
		//<String, String> errors=new HashMap<String,String>() ;
		return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
		
		
	}
	@ExceptionHandler(value=UserNotFoundAtGivenIndex.class)
	public ResponseEntity<Object> userNotFoundException(Exception e){
		
		//<String, String> errors=new HashMap<String,String>() ;
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		
		
	}
	@ExceptionHandler(value=RegisterUnSuccesFullException.class)
	public ResponseEntity<Object> registerUnsuccessFullException(Exception e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.RESET_CONTENT);
	}
}
