package com.bridgelabz.fundoo.service;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.model.Note;
import com.bridgelabz.fundoo.model.ResetDto;

public interface IUserService {

	boolean register(User user);

	public List<User> GetUser();

	public String delete(Long Id);

	public Optional<User> find(Long Id);

	public String login(String email, String password);

	public boolean parseToken(String token);

	public String forgetPassword(String email);

	public boolean updatePassword(String token,ResetDto password);

}
