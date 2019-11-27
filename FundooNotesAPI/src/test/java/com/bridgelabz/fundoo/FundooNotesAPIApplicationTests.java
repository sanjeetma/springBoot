                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               package com.bridgelabz.fundoo;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.service.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
//@AutoConfigureTestDatabase
class FundooNotesAPIApplicationTests {

	@Autowired
	UserServiceImpl userservice;//=Mockito.mock(UserServiceImpl.class);
	

	@Test
	public void loginTest() {
		String email = "bridgelab123@gmail.com";
		String password = "sanjeetkumar";
		boolean  message = userservice.login(email, password);
	//	assertEquals(true, message);
		assertTrue(message);
	}
	@Test
	public void listTest() {
    	List<User>	list=userservice.GetUser();
    	int len=list.size();
    	assertEquals(2, len);
	}
	@Test
	public void findTest() {
		Optional<User> list=userservice.find((long)63);
		boolean status=list.isPresent();
		assertEquals(true, status);
	}

}
