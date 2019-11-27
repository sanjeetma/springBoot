package com.bridgelabz.fundoo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.configure.JmsProvider;
import com.bridgelabz.fundoo.configure.JwtProvider;
import com.bridgelabz.fundoo.exception.RegisterUnSuccesFullException;
import com.bridgelabz.fundoo.exception.UserNotFoundAtGivenIndex;
import com.bridgelabz.fundoo.exception.UserNotFoundException;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.model.UserNote;
import com.bridgelabz.fundoo.repository.UserDao;
import com.bridgelabz.fundoo.repository.UserNotesDao;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDao dao;

	 @Autowired
	 private UserNotesDao notesdao;

	@Autowired
	private static PasswordEncoder bcryptPassword = new BCryptPasswordEncoder();

	//private final Log logger = LogFactory.getLog(getClass());

	// private Pattern BCRYPT_PATTERN =
	// Pattern.compile("\\A\\$2(a|y|b)?\\$(\\d\\d)\\$[./0-9A-Za-z]{53}");

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
				// return true;
				throw new RegisterUnSuccesFullException();
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
		List<User> list = (List<User>) dao.findAll();
		if (list.size() > 0) {
			return list;
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
		throw new UserNotFoundException();

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
	public boolean forgetPassword(String email) {
		List<User> list = (List<User>) dao.findAll();
		for (User user : list) {
			User obj = user;
			String emailid = obj.getEmail();
			if (email.compareToIgnoreCase(emailid) == 0) {
				String url = "http://localhost:8080/users/update-password";
				JmsProvider.sendEmail(email, "for update password", url);
				return true;

			}
		}
		throw new UserNotFoundException();

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
		throw new UserNotFoundException();
	}

	@Override
	public String createNote(UserNote note) {
		notesdao.save(note);
		return null;
	}

	@Override
	public String deleteNotes(long id) {
       notesdao.deleteById(id);
		return null;
	}

}
