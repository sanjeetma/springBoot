package com.bridgelabz.fundoo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.bridgelabz.fundoo.dao.UserDao;
import com.bridgelabz.fundoo.service.IUserService;
import com.bridgelabz.fundoo.service.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class FundooNotesAPIApplicationTests {

	@Autowired
	IUserService userservice=Mockito.mock(UserServiceImpl.class);
	
	

	@Test
	public void loginTest() {
		String expected = "Welcome to Disney Land";
		String email = "sanjeetjr047@gmail.com";
		String password = "sanjeet";
		String message = userservice.login(email, password);
		assertEquals(expected, message);
	}

}
