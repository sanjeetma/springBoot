package com.springboot.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.crud.configure.JmsProvider;
import com.springboot.crud.configure.JwtProvider;
import com.springboot.crud.dao.UserDao;
import com.springboot.crud.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;
	
	@Autowired
	private static PasswordEncoder bcryptPassword = new BCryptPasswordEncoder();
	
	@Autowired
	JwtProvider jwtProvider;

	@Override
	public void save(User user) {
		String email=user.getEmail();
		String name=user.getName();
		String plainTextPassword=user.getPassword();
		String encryptpassword=EncryptPassword(plainTextPassword);
		user.setPassword(encryptpassword);
		String jwtString=jwtProvider.generateToken(email);
		String Url="http://localhost:8080/users/verify/";
		dao.save(user);
		JmsProvider.sendEmail(email, "for Authorization", Url+jwtString);

	}

	@Override
	public List<User> GetUser() {
		return (List<User>) dao.findAll();

	}

	@Override
	public String delete(Long Id) {
		dao.deleteById(Id);
		return "deleted";
	}

	@Override
	public Optional<User> find(Long Id) {
		Optional<User> list = dao.findById(Id);
		return list;
	}

	@Override
	public String search(String email, String password) {
		List<User> list = (List<User>) dao.findAll();
		for (User user : list) {
			User obj=user;
			String emailid = obj.getEmail();
			if (email.compareToIgnoreCase(emailid) == 0) {
				if(user.isStatus()) {
					return "Welcome to Disney Land";
				}
				else {
					return "you are not Authorized yet";
				}
			}
		}
		return "Your  Email is Invalid";

	}
	
	private String EncryptPassword(String plainTextPassword) {


		return bcryptPassword.encode(plainTextPassword);

		}
	
	public void parseToken(String token) {
		String email=jwtProvider.parseToken(token);
		List<User> list = (List<User>) dao.findAll();
		for (User user : list) {
			User obj = user;
			String emailid = obj.getEmail();
			if (email.compareToIgnoreCase(emailid) ==0) {

				user.setStatus(true);
				dao.save(user);

			}
		}
		
		
	}

}
