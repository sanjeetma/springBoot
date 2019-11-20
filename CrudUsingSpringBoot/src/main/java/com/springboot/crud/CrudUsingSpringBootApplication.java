package com.springboot.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableCaching   //implementation of radis cache
public class CrudUsingSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudUsingSpringBootApplication.class, args);
		
	}

}
