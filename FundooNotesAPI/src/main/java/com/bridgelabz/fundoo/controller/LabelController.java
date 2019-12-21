package com.bridgelabz.fundoo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.exception.ExceptionResolve;
import com.bridgelabz.fundoo.model.LabelDTO;
import com.bridgelabz.fundoo.model.LabelModel;
import com.bridgelabz.fundoo.service.LabelServiceImpl;

							

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:4200")
public class LabelController {

	@Autowired
	private LabelServiceImpl labelImplemetation;
	
	@PostMapping("labels/create")
	public LabelModel createLabel(@RequestHeader String token, @RequestBody LabelDTO labelDto) {
		return labelImplemetation.createLabel(token, labelDto);
	}
	@PutMapping("labels/update")
	public ResponseEntity<ExceptionResolve> updateLabel(@RequestHeader String token, @RequestParam Long id, @RequestBody LabelDTO labelDto) {
		String message=labelImplemetation.updateLabel(token, id, labelDto);
		ExceptionResolve response=new ExceptionResolve(HttpStatus.ACCEPTED.value(), message);
		return new ResponseEntity<ExceptionResolve>(response,HttpStatus.OK);
	}
	@DeleteMapping("labels/delete")
	public ResponseEntity<ExceptionResolve> deleteLabels(@RequestParam Long id,@RequestHeader String token) {
		String message=labelImplemetation.deleteLabels(token, id);
		ExceptionResolve response=new ExceptionResolve(HttpStatus.ACCEPTED.value(), message);
		return new ResponseEntity<ExceptionResolve>(response,HttpStatus.OK);

	}
	@GetMapping("/labels")
	public List<LabelModel> retrieveAllNode(@RequestHeader String token){
		System.err.println(token);
		return labelImplemetation.retrieveAllLabel(token);
	}
}