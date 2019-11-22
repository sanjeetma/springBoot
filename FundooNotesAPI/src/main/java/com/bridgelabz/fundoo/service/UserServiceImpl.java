package com.bridgelabz.fundoo.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.configure.JmsProvider;
import com.bridgelabz.fundoo.configure.JwtProvider;
import com.bridgelabz.fundoo.exception.UserNotFoundException;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repository.UserDao;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDao dao;

	@Autowired
	private static PasswordEncoder bcryptPassword = new BCryptPasswordEncoder();

	private final Log logger = LogFactory.getLog(getClass());

	//private Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2(a|y|b)?\\$(\\d\\d)\\$[./0-9A-Za-z]{53}");

	@Autowired
	JwtProvider jwtProvider;

	private String EncryptPassword(String plainTextPassword) {

		return bcryptPassword.encode(plainTextPassword);

	}

	@Override
	public boolean save(User user) {
		List<User> list = (List<User>) dao.findAll();
		boolean bool = false;
		String email = user.getEmail();
		for (User userlist : list) {
			String emailid = userlist.getEmail();
			if (email.compareToIgnoreCase(emailid) == 0) {
				bool = true;
			}
		}
		if (bool == false) {
			String plainTextPassword = user.getPassword();
			String encryptpassword = EncryptPassword(plainTextPassword);
			user.setPassword(encryptpassword);
			String jwtString = jwtProvider.generateToken(email);
			String Url = "http://localhost:8080/users/verify/";
			dao.save(user);
			JmsProvider.sendEmail(email, "for Authorization", Url + jwtString);

		}
		return bool;
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
		if(list.isEmpty())
		{
			throw new UserNotFoundException();
		}
		return list;
	}

	
	@Override
	public boolean login(String email, String password) {
		List<User> list = (List<User>) dao.findAll();
		for (User user : list) {
			String emailid = user.getEmail();
			String pass = user.getPassword();
			System.out.println(pass);
			System.out.println(password);
			if (email.compareToIgnoreCase(emailid) == 0) {

				if (bcryptPassword.matches(password, pass)) {
					System.out.println(bcryptPassword.matches(password, pass));
					if (user.isStatus()) {
						
						return true;
					}
					
				}
			}
		}
			return false;		
				
	}

	public boolean parseToken(String token) {
		String email = jwtProvider.parseToken(token);
		System.out.println(email);
		List<User> list = (List<User>) dao.findAll();
		for (User user : list) {
			User obj = user;
			String emailid = obj.getEmail();
			if (email.compareToIgnoreCase(emailid) == 0) {

				user.setStatus(true);
				dao.save(user);
				return true;
               
			}
		}
		return false;
	}

	@Override
	public void forgetPassword(String email) {
		List<User> list = (List<User>) dao.findAll();
		for (User user : list) {
			User obj = user;
			String emailid = obj.getEmail();
			if (email.compareToIgnoreCase(emailid) == 0) {
				String url = "http://localhost:8080/users/update-password";
				JmsProvider.sendEmail(email, "for update password", url);

			}
		}

	}

	@Override
	public boolean updatePassword(String password, String email) {
		List<User> list = (List<User>) dao.findAll();
		for (User user : list) {
			User obj = user;
			String emailid = obj.getEmail();
			if (email.compareToIgnoreCase(emailid) == 0) {
				String plainTextPassword = user.getPassword();
				String encryptpassword = EncryptPassword(plainTextPassword);
				user.setPassword(encryptpassword);
				dao.save(user);
				return true;
			}

		}
		return false;
	}
	

}
