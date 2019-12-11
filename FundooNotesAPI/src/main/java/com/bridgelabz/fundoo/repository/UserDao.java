package com.bridgelabz.fundoo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.bridgelabz.fundoo.model.User;
@Repository
@Component
public interface UserDao  extends JpaRepository<User, Long> {

//	public List<User> findAll();
//
//	public User save(User user);
//
//	public User findById(long id);
//
//	public void deleteById(long id);
	
 @Query(value="select u from User u")
  public List<User> findUsers();

}