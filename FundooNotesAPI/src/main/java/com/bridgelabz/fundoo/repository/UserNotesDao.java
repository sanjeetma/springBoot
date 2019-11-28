package com.bridgelabz.fundoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.bridgelabz.fundoo.model.Note;

public interface  UserNotesDao extends JpaRepository<Note, Long>{//CrudRepository<UserNote, Long>{

}
