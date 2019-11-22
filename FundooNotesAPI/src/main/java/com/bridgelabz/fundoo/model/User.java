package com.bridgelabz.fundoo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@GeneratedValue
	@javax.persistence.Id
	private Long Id;
	@Column
	private String name;
	@Column
	private String email;
	@Column
	private boolean status;
	@Column
	private String password;
		
	public User(UserDto userdto) {
		this.email=userdto.getEmail();	
		this.password=userdto.getPassword();
	}
	
	
	
}