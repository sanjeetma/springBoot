package com.bridgelabz.fundoo.service;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.model.UserNote;

public interface IUserService {

	boolean save(User user);

	public List<User> GetUser();

	public String delete(Long Id);

	public Optional<User> find(Long Id);

	public boolean login(String email, String password);

	public boolean parseToken(String token);

	public boolean forgetPassword(String email);

	public boolean updatePassword(String password, String Email);

	public String createNote(UserNote note);
	
	public String deleteNotes(long id);
}
