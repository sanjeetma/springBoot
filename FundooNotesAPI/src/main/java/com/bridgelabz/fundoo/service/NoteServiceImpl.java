package com.bridgelabz.fundoo.service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.model.Note;
import com.bridgelabz.fundoo.model.NoteDto;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repository.UserDao;
import com.bridgelabz.fundoo.repository.UserNotesDao;
import com.bridgelabz.fundoo.util.JwtProvider;

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

		//Note noteModel = modelmapper.map(note, Note.class);
		userModel.get().getNote().add(note);
		
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
		List<Note> alllist=new ArrayList<Note>();
		List<Note> noteList=notesdao.findAll();
		for(Note note:noteList) {
			if(!note.isArchieved()) {
				alllist.add(note);
			}
		}
		return alllist;
	}
	
	
	@Transactional
	public List<Note> allNotesi(String token) {
		List<Note> alllist=new ArrayList<Note>();
		Long id=jwtprovider.verifyToken(token);
		List<Note> noteList=(List<Note>) notesdao.findAllNote((long)id);
		for(Note note:noteList) {
			if(!note.isArchieved()) {
				alllist.add(note);
			}
		}
		return alllist;
		
	}
	
	public boolean archived(long id) {
		List<Note> noteList=notesdao.findAll();
		for(Note note:noteList) {
			if(note.getNoteId()==id) {
				if(note.isArchieved()==false) {
				note.setArchieved(true);
				notesdao.save(note);
				return true;
				}
				else {
					note.setArchieved(false);
					notesdao.save(note);
					return false;
				}
			}
		}
		return false;
	}

	@SuppressWarnings("null")
	@Override
	public List<Note> getAllArchieve(String token) {
		List<Note> archeive = new ArrayList<Note>();
		Long id=jwtprovider.verifyToken(token);
		System.out.println(id);
		List<Note> noteList=(List<Note>) notesdao.findAllNote((long)id);
		System.out.println(noteList);
		for(Note note:noteList) {
			if(note.isArchieved()) {
				
				System.out.println("########################################");
				System.out.println(note.isArchieved());
				archeive.add(note);	
				System.out.println(archeive);
			}
		}
		System.out.println(archeive);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2");
		return archeive;
		
	}

}
