package com.bridgelabz.fundoo.service;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.fundoo.model.Note;
import com.bridgelabz.fundoo.model.NoteDto;

public interface INoteService {

	public String createNote(String token,NoteDto notedto );

	public String deleteNoteById(long id);

	public Optional<Note> findNoteById(long id);
	
	public List<Note> allNotes();
	
	List<Note> allNotesi(String token);

}
