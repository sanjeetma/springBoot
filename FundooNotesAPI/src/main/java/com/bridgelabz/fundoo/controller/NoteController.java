package com.bridgelabz.fundoo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.configure.JwtProvider;
import com.bridgelabz.fundoo.exception.ExceptionResolve;
import com.bridgelabz.fundoo.model.Note;
import com.bridgelabz.fundoo.model.NoteDto;
import com.bridgelabz.fundoo.service.INoteService;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200")
public class NoteController {
	
	
	
	@Autowired
	INoteService noteservice; 
	
	@Autowired
	JwtProvider jwt;
	
	@PostMapping("/notes/create")
	public ResponseEntity<ExceptionResolve> createNote(@RequestHeader String token ,@RequestBody NoteDto notedto) {
	Long id=jwt.verifyToken(token);
	System.out.println(id);
	noteservice.createNote(token,notedto);

	return new ResponseEntity<>(
			new ExceptionResolve(HttpStatus.OK.value(), "note created"), HttpStatus.OK);
}
	
	@GetMapping("/notes/delete/{note_id}")
	public String deleteNote(@PathVariable(name="note_id") long id) {
		noteservice.deleteNoteById(id);
		return "Note is deleted";
	}
	@GetMapping("/notes/{note_id}")
	public Optional<Note> getnotes(@PathVariable(name="note_id") long id) {
		Optional<Note> list=noteservice.findNoteById(id);
		return list;
	}
	@GetMapping("/notes")
	public List<Note> getallnote(@RequestParam(name="id") String token){
		List<Note> list=noteservice.allNotesi(token);
		return list;
	}
}
