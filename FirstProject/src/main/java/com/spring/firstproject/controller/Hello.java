package com.spring.firstproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sanjeet")
public class Hello {
	
	public static String ret() {
		return "ray "+"vivek "+"sanjeet "+"gautam ";
		
	}
	
	@GetMapping("/hellos")
	public String show() {
		return ret();
	}

}
