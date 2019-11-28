package com.bridgelabz.fundoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
//@EnableCaching   //implementation of radis cache
public class FundooNotesAPI {

	public static void main(String[] args) {
		SpringApplication.run(FundooNotesAPI.class, args);
	}

}
