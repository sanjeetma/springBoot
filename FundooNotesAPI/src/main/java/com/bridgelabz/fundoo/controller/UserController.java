package com.bridgelabz.fundoo.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.configure.JmsProvider;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.model.UserDto;
import com.bridgelabz.fundoo.service.IUserService;


@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUserService iUserService;

	@PostMapping("/register")
	public String saveuser(@RequestBody User user) throws Exception {

		boolean bool = iUserService.save(user);
		if (bool == false) {

			return "saved";

		} else {
			return "email Exist";
		}

	}

	@GetMapping("/list")
	@Cacheable(value = "list1")
	public List<User> GetUser() {
		List<User> list = iUserService.GetUser();
		System.out.println("i am called");
		return list;
	}

	@DeleteMapping("/delete/{Id}")
	public String delete(@PathVariable(name = "Id") Long Id) {
		iUserService.delete(Id);
		return "deleted";

	}

	@GetMapping("/get/{Id}")
	@Cacheable(value = "id") //implemented radis cache
	public Optional<User> find(@PathVariable(name = "Id") Long Id) {
		Optional<User> list = iUserService.find(Id);
		return list;

	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserDto userdto) {

		String email = userdto.getEmail();
		String password=userdto.getPassword();
		String status = iUserService.login(email, password);
		return ResponseEntity.ok(status);
	}

	@GetMapping("/verify/{token}")
	public String verify(@PathVariable(name = "token") String tokenurl) {
		iUserService.parseToken(tokenurl);
		return "you are Authorized Now";

	}

	@GetMapping("/forgetpassword")
	public void forgetPassword(@RequestBody UserDto userdto) {
		String email = userdto.getEmail();
		iUserService.forgetPassword(email);

	}

	@GetMapping("/update-password")
	public String updatePassword(@RequestBody UserDto userdto) {
		String email = userdto.getEmail();
		String password = userdto.getPassword();
		iUserService.updatePassword(password, email);
		return "updated";
	}

}