package com.bridgelabz.fundoo.model;

import javax.persistence.Column;

import lombok.Data;

@Data
public class LabelDTO 			{
	@Column(nullable = false)
	private String label;
}