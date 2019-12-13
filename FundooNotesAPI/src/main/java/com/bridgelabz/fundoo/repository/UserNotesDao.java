package com.bridgelabz.fundoo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import com.bridgelabz.fundoo.model.Note;

import io.lettuce.core.dynamic.annotation.Param;

public interface  UserNotesDao extends JpaRepository<Note, Long>{//CrudRepository<UserNote, Long>{

//	@Query("select n from Note n")
//   public List<Note> findNotes();
	
//	@Query("select n from Note n where id=?")
//	public List<Note> findNotesByUserId(@PathVariable long id);
}
