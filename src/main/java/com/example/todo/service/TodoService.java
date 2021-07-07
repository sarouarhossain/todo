package com.example.todo.service;

import com.example.todo.model.TodoDTO;
import com.example.todo.model.TodoForm;

import java.text.ParseException;
import java.util.List;

public interface TodoService {
  void createTodo(TodoForm todoForm) throws Exception;

  List<TodoDTO> getTodoList();

  TodoDTO getTodoById(Long id);

  TodoDTO updateTodo(Long id, TodoForm updateForm) throws ParseException;

  List<TodoDTO> searchTodo(String name);
}
