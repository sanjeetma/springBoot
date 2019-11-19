package com.springboot.crud.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.crud.configure.JmsProvider;
import com.springboot.crud.model.User;
import com.springboot.crud.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public String saveuser(@RequestBody User user) throws Exception {

		userService.save(user);

		return "saved";

	}

	@GetMapping("/list")
	public List<User> GetUser() {
		List<User> list = userService.GetUser();
		return list;
	}

	@DeleteMapping("/delete/{Id}")
	public String delete(@PathVariable(name = "Id") Long Id) {
		userService.delete(Id);
		return "deleted";

	}

	@GetMapping("/get/{Id}")
	public Optional<User> find(@PathVariable(name = "Id") Long Id) {
		Optional<User> list = userService.find(Id);
		return list;

	}

	@GetMapping("/login")
	public String login(HttpServletRequest req) {

		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String status = userService.search(email, password);
		return status;
	}

	@GetMapping("/verify/{token}")
	public String verify(@PathVariable(name = "token") String tokenurl) {
		// String url=
		userService.parseToken(tokenurl);
		return "Authorized";

	}

}