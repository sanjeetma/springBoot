package com.bridgelabz.fundoo.service;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.fundoo.model.Note;

public interface INoteService {

	public String createNote(Note note);

	public String deleteNoteById(long id);

	public Optional<Note> findNoteById(long id);
	
	public List<Note> allNotes();

}
