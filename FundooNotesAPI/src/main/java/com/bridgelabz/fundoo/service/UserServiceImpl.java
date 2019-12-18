package com.bridgelabz.fundoo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.configure.JmsProvider;
import com.bridgelabz.fundoo.configure.JwtProvider;
import com.bridgelabz.fundoo.exception.RegisterUnSuccesFullException;
import com.bridgelabz.fundoo.exception.UserNotFoundAtGivenIndex;
import com.bridgelabz.fundoo.exception.UserNotFoundException;
import com.bridgelabz.fundoo.model.RabbitMessageProvider;
import com.bridgelabz.fundoo.model.ResetDto;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repository.UserDao;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDao dao;
	
	RabbitMessageProvider messageRabbit=new RabbitMessageProvider();

	@Autowired
	private static PasswordEncoder bcryptPassword = new BCryptPasswordEncoder();


	@Autowired
	JwtProvider jwtProvider;

	private String EncryptPassword(String plainTextPassword) {

		return bcryptPassword.encode(plainTextPassword);

	}

	@Override
	public boolean register(User user) {
		List<User> userlist = dao.findAll();
		boolean status = false;
		String email = user.getEmail();
		for (User userList : userlist) {
			String emailid = userList.getEmail();
			if (email.compareToIgnoreCase(emailid) == 0) {
				status = true;
				
				throw new RegisterUnSuccesFullException();
			}
		}
		if (status == false) {
			String plainTextPassword = user.getPassword();
			String encryptpassword = EncryptPassword(plainTextPassword);
			user.setPassword(encryptpassword);
			String jwtString = jwtProvider.generateToken(email);
			String Url = "http://localhost:8080/users/verify/";
			dao.save(user);
//			messageRabbit.setEmail(user.getEmail());
//			messageRabbit.setLink(Url);
//			messageRabbit.setToken(jwtString);		
//			logger.info(jwtToken.generateToken(model.getUserId()));
//			JmsProvider.sendRabbit(messageRabbit);
//			JmsProvider.sendEmail(user.getEmail(),Url, jwtString);
			JmsProvider.sendEmail(email, "for Authorization", Url + jwtString);

		}
		return status;
	}

	@Override
	@Transactional
	public List<User> GetUser() {
		List<User> userlist = dao.findUsers();
		if (!userlist.isEmpty()) {
			return userlist;
		} else {
			throw new UserNotFoundException();
		}

	}

	@Override
	public String delete(Long Id) {
		dao.deleteById(Id);
		return "deleted";
	}

	@Override
	public Optional<User> find(Long Id) {
		Optional<User> user = dao.findById(Id);
		if (user.isPresent()) {
			return user;
		}
		throw new UserNotFoundAtGivenIndex(Id);
	}

	@Override
	public String login(String email, String password) {
		System.out.println("i am login");
		List<User> userlist = (List<User>) dao.findAll();
		for (User user : userlist) {
			String emailid = user.getEmail();
			String pass = user.getPassword();
			System.out.println(pass);
			System.out.println(password);
			
			if (email.compareToIgnoreCase(emailid) == 0) {

				if (bcryptPassword.matches(password, pass)) {
					
					System.out.println(bcryptPassword.matches(password, pass));
					if (user.isStatus()) {
						Long id=user.getId();
						String token=jwtProvider.generateToken(id);

						return token;
					}

				}
			}
		}
		throw new UserNotFoundException();

	}

	public boolean parseToken(String token) {
		String email = jwtProvider.parseToken(token);
		System.out.println(email);
		List<User> userlist = (List<User>) dao.findAll();
		for (User user : userlist) {
			String emailid =user.getEmail();
			if (email.compareToIgnoreCase(emailid) == 0) {

				user.setStatus(true);
				dao.save(user);
				return true;

			}
		}
		return false;
	}

	@Override
	public String forgetPassword(String email) {
		List<User> userlist = (List<User>) dao.findAll();
		for (User user : userlist) {
			String emailid = user.getEmail();
			if (email.compareToIgnoreCase(emailid) == 0) {
				String url = "http://localhost:4200/resetPassword/";
				String token=jwtProvider.generateToken(email);
				JmsProvider.sendEmail(email, "for update password", url+token);
				return token;

			}
		}
		throw new UserNotFoundException();
     
	}

	@Override
	public boolean updatePassword( String token,ResetDto password) {
		List<User> userlist = dao.findAll();
		String email=jwtProvider.parseToken(token);
		for (User user : userlist) {
			String emailid = user.getEmail();
			if (email.compareToIgnoreCase(emailid) == 0) {
				String plainTextPassword = password.getPassword();
				String encryptpassword = EncryptPassword(plainTextPassword);
				user.setPassword(encryptpassword);
				dao.save(user);
				return true;
			}

		}
		throw new UserNotFoundException();
	}

}
