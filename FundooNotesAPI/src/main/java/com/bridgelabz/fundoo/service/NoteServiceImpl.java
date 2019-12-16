package com.bridgelabz.fundoo.service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.configure.JwtProvider;
import com.bridgelabz.fundoo.model.Note;
import com.bridgelabz.fundoo.model.NoteDto;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repository.UserDao;
import com.bridgelabz.fundoo.repository.UserNotesDao;

@Service
public class NoteServiceImpl implements INoteService {
	@Autowired
	private UserNotesDao notesdao;
	
	@Autowired
	private JwtProvider jwtprovider;
	
	
	private ModelMapper modelmapper=new ModelMapper();
	
	@Autowired
	private UserDao userdao;
	
	
	@Override
	public String createNote(String token,NoteDto notedto) {
		Note note=new Note(notedto);
		Long id=jwtprovider.verifyToken(token);
		System.out.println(id);
		Optional<User> userModel = userdao.findById(id);

		Note noteModel = modelmapper.map(note, Note.class);
		userModel.get().getNote().add(noteModel);
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
	
	
	@Transactional
	public List<Note> allNotesi(String token) {
		Long id=jwtprovider.verifyToken(token);
		List<Note> noteList=(List<Note>) notesdao.findAllNote((long)id);
		return noteList;
	}

}
