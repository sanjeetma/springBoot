package com.bridgelabz.fundoo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.model.Note;
import com.bridgelabz.fundoo.repository.UserNotesDao;

@Service
public class NoteServiceImpl implements INoteService {
	@Autowired
	private UserNotesDao notesdao;

	@Override
	public String createNote(Note note) {
		notesdao.save(note);
		return null;
	}

	@Override
	public String deleteNoteById(long id) {
       notesdao.deleteById(id);
		return null;
	}

	@Override
	public Optional<Note> findNoteById(long id) {
		Optional<Note> list=notesdao.findById(id);
			return list;	
	}

	@Override
	public List<Note> allNotes() {
		List<Note> noteList=notesdao.findAll();
		return noteList;
	}

}
