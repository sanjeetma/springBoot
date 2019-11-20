package com.springboot.crud.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

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

	private final Log logger = LogFactory.getLog(getClass());

	private Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2(a|y|b)?\\$(\\d\\d)\\$[./0-9A-Za-z]{53}");

	@Autowired
	JwtProvider jwtProvider;

	private String EncryptPassword(String plainTextPassword) {

		return bcryptPassword.encode(plainTextPassword);

	}

	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		if (encodedPassword == null || encodedPassword.length() == 0) {
			logger.warn("Empty encoded password");
			return false;
		}

		if (!BCRYPT_PATTERN.matcher(encodedPassword).matches()) {
			logger.warn("Encoded password does not look like BCrypt");
			return false;
		}

		return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
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
		return list;
	}

	@Override
	public String login(String email, String password) {
		List<User> list = (List<User>) dao.findAll();
		for (User user : list) {
			String emailid = user.getEmail();
			String pass = user.getPassword();
			if (email.compareToIgnoreCase(emailid) == 0) {

				System.out.println(matches(password, pass));
				if (matches(password, pass)) {
					if (user.isStatus()) {
						return "Welcome to Disney Land";
					}
				} else {
					return "you are not Authorized yet";
				}
			}
		}
		return "Your  Email is Invalid";

	}

	public void parseToken(String token) {
		String email = jwtProvider.parseToken(token);
		List<User> list = (List<User>) dao.findAll();
		for (User user : list) {
			User obj = user;
			String emailid = obj.getEmail();
			if (email.compareToIgnoreCase(emailid) == 0) {

				user.setStatus(true);
				dao.save(user);

			}
		}

	}

	@Override
	public void forgetPassword(String email) {
		List<User> list = (List<User>) dao.findAll();
		for (User user : list) {
			User obj = user;
			String emailid = obj.getEmail();
			if (email.compareToIgnoreCase(emailid) == 0) {
				String url = "http://localhost:8080/users/update-password";
				JmsProvider.sendEmail(email, "for update password", url + "?email=" + email);

			}
		}

	}

	@Override
	public void updatePassword(String password, String email) {
		List<User> list = (List<User>) dao.findAll();
		for (User user : list) {
			User obj = user;
			String emailid = obj.getEmail();
			if (email.compareToIgnoreCase(emailid) == 0) {
				String plainTextPassword = user.getPassword();
				String encryptpassword = EncryptPassword(plainTextPassword);
				user.setPassword(encryptpassword);
				dao.save(user);
			}

		}
	}

}
