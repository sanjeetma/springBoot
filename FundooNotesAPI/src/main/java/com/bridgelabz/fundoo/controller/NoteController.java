package com.bridgelabz.fundoo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.model.Note;
import com.bridgelabz.fundoo.service.INoteService;

@RestController
@RequestMapping("/")
public class NoteController {
	
	
	
	@Autowired
	INoteService noteservice; 
	
	@PostMapping("/notes/create")
	public String createNote(@RequestBody Note note) {
		noteservice.createNote(note);
		return "Note is created";
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
	public List<Note> getallnote(){
		List<Note> list=noteservice.allNotes();
		return list;
	}
}
