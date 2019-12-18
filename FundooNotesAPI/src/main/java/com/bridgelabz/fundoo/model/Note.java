package com.bridgelabz.fundoo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="note")
public class Note {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "note_id")
	private Long noteId;

	@Column(name="tittle")
	private String tittle;
	
	
	@Column(name = "description")
	private String description;
	
	@Column
	private boolean isArchieved;
	
	@Column
	private boolean isPin;
	
	@Column
	private String color;
	
	@Column
	private LocalDateTime createdtime;
	
	@Column
	private String collabrator;
	
	
	@Column
	private LocalDateTime reminderTime;
	
	public Note(NoteDto notedto){
		this.tittle=notedto.getTitle();
		this.description=notedto.getDesc();
	}
	
	
}
