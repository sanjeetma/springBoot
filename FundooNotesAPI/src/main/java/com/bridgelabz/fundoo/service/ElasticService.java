package com.bridgelabz.fundoo.service;

import java.util.List;

import com.bridgelabz.fundoo.model.Note;

interface  ElasticService {

	String createNote(Note note);

	String updateNote(Note note);

	String deleteNote(Note note);

	List<Note> searchbyTitle(String title);


}
