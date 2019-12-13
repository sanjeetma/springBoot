package com.bridgelabz.fundoo.service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.model.Note;
import com.bridgelabz.fundoo.model.NoteDto;
import com.bridgelabz.fundoo.repository.UserNotesDao;

@Service
public class NoteServiceImpl implements INoteService {
	@Autowired
	private UserNotesDao notesdao;

	
	@Override
	public String createNote(NoteDto notedto) {
		Note note = new Note();
		note.setTittle(notedto.getTitle());
		note.setDescription(notedto.getDesc());
		note.setCreatedtime(LocalDateTime.now());
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
	@Transactional
	public List<Note> allNotes() {
		List<Note> noteList=notesdao.findAll();
		return noteList;
	}

}
