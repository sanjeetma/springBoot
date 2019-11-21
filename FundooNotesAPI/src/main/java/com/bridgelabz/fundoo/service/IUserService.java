package com.bridgelabz.fundoo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;

import com.bridgelabz.fundoo.model.User;

public interface IUserService {

	boolean save(User user);

	public List<User> GetUser();

	public String delete(Long Id);

	public Optional<User> find(Long Id);

	public String login(String email, String password);

	public String parseToken(String token);

	public void forgetPassword(String email);

	public void updatePassword(String password, String Email);
}
