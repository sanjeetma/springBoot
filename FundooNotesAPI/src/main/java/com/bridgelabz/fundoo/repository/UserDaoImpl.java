//package com.bridgelabz.fundoo.repository;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
// 
//import org.hibernate.Session;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//
//import com.bridgelabz.fundoo.model.User;
//
//@Repository
//public class UserDaoImpl {//implements UserDao {
//
//	@Autowired
//	private EntityManager entityManager;
//
//	@Override
//	public User save(User user) {
//		Session currentSession = entityManager.unwrap(Session.class);
//		currentSession.save(user);
//		return user;
//	}
//
//	@Override
//	public List<User> findAll() {
//		Session currentSession = entityManager.unwrap(Session.class);
//		Query query = (Query) currentSession.createQuery("from User", User.class);
//		List<User> userList = query.getResultList();
//		return userList;
//	}
//
//	public boolean isVarified(User user) {
//		List<User> userList = findAll();
//		for (User userObj : userList) {
//			if (userObj.isStatus()) {
//				return true;
//			}
//
//		}
//		return false;
//
//	}
//	@Override
//	public User findById(long id) {
//		Session currentSession = entityManager.unwrap(Session.class);
//		User user = currentSession.get(User.class, id);
//		return user;
//	}
//
//	@Override
//	public void deleteById(long id) {
//		Session currentSession = entityManager.unwrap(Session.class);
//		User user = currentSession.get(User.class, id);
//		currentSession.delete(user);
//	}
//
//}
