package com.springboot.crud.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.crud.configure.JmsProvider;
import com.springboot.crud.model.User;
import com.springboot.crud.model.UserDto;
import com.springboot.crud.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public String saveuser(@RequestBody User user) throws Exception {

		boolean bool = userService.save(user);
		if (bool == false) {

			return "saved";

		} else {
			return "email Exist";
		}

	}

	@GetMapping("/list")
	@Cacheable(value = "list")
	public List<User> GetUser() {
		List<User> list = userService.GetUser();
		System.out.println("i am called");
		return list;
	}

	@DeleteMapping("/delete/{Id}")
	public String delete(@PathVariable(name = "Id") Long Id) {
		userService.delete(Id);
		return "deleted";

	}

	@GetMapping("/get/{Id}")
	@Cacheable(value = "id") //implemented radis cache
	public Optional<User> find(@PathVariable(name = "Id") Long Id) {
		Optional<User> list = userService.find(Id);
		return list;

	}

	@GetMapping("/login")
	public String login(@RequestBody UserDto userdto) {

		String email = userdto.getEmail();
		String password=userdto.getPassword();
		String status = userService.login(email, password);
		return status;
	}

	@GetMapping("/verify/{token}")
	public String verify(@PathVariable(name = "token") String tokenurl) {
		userService.parseToken(tokenurl);
		return "you are Authorized Now";

	}

	@GetMapping("/forgetpassword")
	public void forgetPassword(HttpServletRequest req) {
		String email = req.getParameter("email");
		userService.forgetPassword(email);

	}

	@GetMapping("/update-password")
	public String updatePassword(HttpServletRequest req) {
		String email = req.getParameter("email");
		String password = req.getParameter("newpassword");
		userService.updatePassword(password, email);
		return "updated";
	}

}