package com.bridgelabz.fundoo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.bridgelabz.fundoo.model.User;

public interface UserDao  extends CrudRepository<User, Long> {

//	public List<User> findAll();
//
//	public User save(User user);
//
//	public User findById(long id);
//
//	public void deleteById(long id);

}