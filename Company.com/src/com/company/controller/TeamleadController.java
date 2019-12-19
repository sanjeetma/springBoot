package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/team")
public class TeamleadController {

	@ResponseBody
	@RequestMapping("/head")
	public String show() {
		return "I am Team Leader and My name is sanjeet";
	}
	@ResponseBody
	@RequestMapping("/status")
	public String teamDetails() {
		return "Team is Working fine";
	}
}
