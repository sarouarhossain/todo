package com.example.todo.model;

import lombok.Data;

@Data
public class TodoForm {
  private String name;
  private String deadline;
  private boolean status;
}
