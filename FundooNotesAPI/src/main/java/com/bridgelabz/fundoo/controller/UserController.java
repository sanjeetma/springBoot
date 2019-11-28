package com.bridgelabz.fundoo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.bridgelabz.fundoo.exception.ExceptionResolve;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.model.UserDto;
import com.bridgelabz.fundoo.model.Note;
import com.bridgelabz.fundoo.service.IUserService;

@RestController
@RequestMapping("")
public class UserController {

	@Autowired
	private IUserService iUserService;

//	@Autowired
//	User user;

	@PostMapping("/users/register")
	public ResponseEntity<ExceptionResolve> saveuser(@Valid @RequestBody User user) throws Exception {

		boolean bool = iUserService.register(user);
		if (bool == false) {
			return new ResponseEntity<>(
					new ExceptionResolve(HttpStatus.OK.value(), "you are succesfully registered", user), HttpStatus.OK);
		}
		return null;
	}

	@GetMapping("/users")
	// @Cacheable(value = "list1")
	public ResponseEntity<ExceptionResolve> GetUser() {
		List<User> users = iUserService.GetUser();
		System.out.println("i am called");

		return new ResponseEntity<>(new ExceptionResolve(HttpStatus.OK.value(), "All Users details", users),
				HttpStatus.OK);

		// return list;
	}

	@DeleteMapping("/users/delete/{Id}")
	public String delete(@PathVariable(name = "Id") Long Id) {
		iUserService.delete(Id);
		return "deleted";

	}

	@GetMapping("/users/{Id}")
	// @Cacheable(value = "id") //implemented radis cache
	public ResponseEntity<ExceptionResolve> find(@PathVariable(name = "Id") Long Id) {
		Optional<User> list = iUserService.find(Id);

		return new ResponseEntity<>(new ExceptionResolve(HttpStatus.OK.value(), "User detail", list), HttpStatus.OK);
	}

	@PostMapping("/users/login")
	@ResponseBody
	public ResponseEntity<ExceptionResolve> login(@RequestBody UserDto userdto) {

		String email = userdto.getEmail();
		String password = userdto.getPassword();
		boolean bool = iUserService.login(email, password);

		return new ResponseEntity<>(
				new ExceptionResolve(HttpStatus.OK.value(), "you are succesfully logged in", userdto), HttpStatus.OK);

	}

	@GetMapping("/users/verify/{token}")
	public ResponseEntity<ExceptionResolve> verify(@PathVariable(name = "token") String tokenurl) {
		boolean status = iUserService.parseToken(tokenurl);
		if (status) {
			return new ResponseEntity<>(new ExceptionResolve(HttpStatus.OK.value(), "you are authorized"),
					HttpStatus.OK);
		} else
			return new ResponseEntity<>(new ExceptionResolve(HttpStatus.BAD_REQUEST.value(), "you are not authorized"),
					HttpStatus.OK);

	}

	@GetMapping("/users/forgetpassword")
	public ResponseEntity<ExceptionResolve> forgetPassword(@RequestBody UserDto userdto) {
		String email = userdto.getEmail();
		iUserService.forgetPassword(email);
		return new ResponseEntity<>(new ExceptionResolve(HttpStatus.OK.value(), "email sent to your email id"),
				HttpStatus.OK);

	}

	@PutMapping("/users/update-password")
	public ResponseEntity<ExceptionResolve> updatePassword(@RequestBody UserDto userdto) {
		String email = userdto.getEmail();
		String password = userdto.getPassword();
		boolean status = iUserService.updatePassword(password, email);
		return new ResponseEntity<>(new ExceptionResolve(HttpStatus.OK.value(), "password Updated"), HttpStatus.OK);

	}

	
}
