package com.bridgelabz.fundoo.repository;

import org.springframework.data.repository.CrudRepository;

import com.bridgelabz.fundoo.model.UserNote;

public interface  UserNotesDao extends CrudRepository<UserNote, Long>{

}
