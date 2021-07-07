package com.example.todo.model;

import lombok.Data;

import java.util.Date;

@Data
public class TodoDTO {
  private Long id;
  private String name;
  private Date deadline;
  private Boolean status;
}
