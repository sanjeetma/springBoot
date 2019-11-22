package com.bridgelabz.fundoo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.model.User;
@Repository
public class UserDaoImpl  {


	@Autowired
	private EntityManager entityManager;

	
	public User register(User user) {
	Session currentSession =entityManager.unwrap(Session.class);
	currentSession.save(user);
	return user;
	}
	public List<User> getAllUser() {
	Session currentSession=entityManager.unwrap(Session.class);
	Query query=(Query) currentSession.createQuery("from User",User.class);
	List<User> userList=query.getResultList();
	return userList;
	}

	
	public boolean isVarified(User user) {
	List<User> userList =getAllUser();
	for(User userObj : userList) {
	if(userObj.isStatus()) {
	return true;
	}

	}
	return false;

	}

	
	public User getUserById(Integer id) {
	List<User> userList=getAllUser();
	User user=null;
	for(User userObj:userList) {
	if(userObj.getId().equals(id)) {
	user=userObj;
	}
	}
	return user;
	}
		
}
