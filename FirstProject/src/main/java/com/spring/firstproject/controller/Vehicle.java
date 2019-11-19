package com.spring.firstproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/check")
@Component
public class Vehicle {
	
	private car car;

	public car getCar() {
		return car;
	}

	@Autowired
	public void setCar(car car) {
		this.car = car;
	}
	@GetMapping("/hello")
	public String VehcileName() {
		return car.show();
	}

}
