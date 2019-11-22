package com.bridgelabz.fundoo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.bridgelabz.fundoo.repository.UserDao;
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
		boolean  message = userservice.login(email, password);
		assertEquals(true, message);
	}
	@Test
	public void registerTest() {
		String name="raju";
		String email="raju007@gmail.com";
		String password="raju";
		
	}

}
