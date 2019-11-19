package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class Accountant {
	
//	@ResponseBody
	@RequestMapping("/account")
	public String show() {
		return "index";
		
	}

}
