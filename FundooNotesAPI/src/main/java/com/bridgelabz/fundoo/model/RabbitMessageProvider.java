package com.bridgelabz.fundoo.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class RabbitMessageProvider implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String link;
	private String token;


}
