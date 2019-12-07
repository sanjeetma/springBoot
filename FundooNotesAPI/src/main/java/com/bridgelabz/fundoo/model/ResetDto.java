package com.bridgelabz.fundoo.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetDto {
	
	@NotEmpty
	@Size(min=3,max=20,message="password must be 8 character")
	private String password;
}
