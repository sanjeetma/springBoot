package com.bridgelabz.fundoo.dao;

import org.springframework.data.repository.CrudRepository;

import com.bridgelabz.fundoo.model.User;

public interface UserDao extends CrudRepository<User, Long> {

	
	
	

}