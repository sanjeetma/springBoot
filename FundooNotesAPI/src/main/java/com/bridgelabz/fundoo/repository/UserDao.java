package com.bridgelabz.fundoo.repository;

import org.springframework.data.repository.CrudRepository;

import com.bridgelabz.fundoo.model.User;

public interface UserDao extends CrudRepository<User, Long> {

	
	
	

}