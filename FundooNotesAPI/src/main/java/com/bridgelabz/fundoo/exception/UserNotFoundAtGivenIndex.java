package com.bridgelabz.fundoo.exception;

public class UserNotFoundAtGivenIndex extends RuntimeException {
	
	public UserNotFoundAtGivenIndex(Long id)
	{	
		super("user Not Found at"+"-->"+id +"index");
	}
	}
	

