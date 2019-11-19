package com.springboot.crud.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.crud.model.User;

public interface UserDao extends CrudRepository<User, Long> {

	
	
	

}