package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/team")
public class TeamleadController {

	@ResponseBody
	@RequestMapping("/teamhead")
	public String show() {
		return "I am Team Leader and My name is sanjeet";
	}
	@ResponseBody
	@RequestMapping("/teamstatus")
	public String teamDetails() {
		return "Team is Working fine";
	}
}
