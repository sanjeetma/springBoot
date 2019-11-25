package com.bridgelabz.fundoo.exception;

public class RegisterUnSuccesFullException extends RuntimeException {

	public RegisterUnSuccesFullException() {
		super("same Email ID Exist..!!");
	}
}
