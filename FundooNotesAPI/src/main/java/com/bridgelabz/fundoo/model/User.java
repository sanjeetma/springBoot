package com.bridgelabz.fundoo.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy = GenerationType.AUTO)
	@javax.persistence.Id
	@NotNull
	private Long Id;
	@Column
	@NotEmpty
	@Length(min = 5, max = 20)
	private String name;
	@Column
	@NotEmpty
	@Email
	private String email;
	@Column
	private boolean status;
	@Column
	@NotEmpty
	@Length(min = 5, max = 255, message = "password must be 8 character")
	private String password;

	@Column
	@NotNull
	@Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number invalid")
	private String mobile;

//	@Column
//	private LocalDateTime localdatetime;
	
	@OneToMany(targetEntity = Note.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="Id")
	private List<Note> note;

	
	@OneToMany(targetEntity=LabelModel.class,cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private List<LabelModel> label;

	public User(UserDto userdto) {
		this.email = userdto.getEmail();
		this.password = userdto.getPassword();
	}
	

}