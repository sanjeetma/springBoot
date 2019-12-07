package com.bridgelabz.fundoo.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Data
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ExceptionResolve implements Serializable {
 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer staus;
	private String message;
	private Object user;
	
	
	
//	public ExceptionResolve(Integer staus,String message,Object user) {
//		
//		this.staus = staus;
//		this.message=message;
//		this.user=user;
//		
//	}
//
public ExceptionResolve(Integer staus,String message) {
		
		this.staus = staus;
		this.message=message;
}

	
	
}
