package com.bridgelabz.fundoo.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
public class NoteDto {
  private String title;
  private String desc;
}
