package com.bridgelabz.fundoo.exception;

public class UserNotFoundException extends RuntimeException{

//	private static final long serialVersionUID = 1L;
//	
//	private String message;
//	private Map<String,String> reason;
	public UserNotFoundException() {
		super("user Not Found");
	}
	
	
}
