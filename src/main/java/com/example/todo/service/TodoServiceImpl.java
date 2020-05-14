package com.example.todo.service;

import com.example.todo.model.Todo;
import com.example.todo.model.TodoDTO;
import com.example.todo.model.TodoForm;
import com.example.todo.repositories.TodoRepository;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {
  private final TodoRepository todoRepository;

  TodoServiceImpl(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  @Override
  public void createTodo(TodoForm todoForm) throws Exception {
    Todo todo = new Todo();
    todo.setName(todoForm.getName());
    todo.setStatus(todoForm.isStatus());

    Date deadLine = this.convertDateFromString(todoForm.getDeadline());

    todo.setDeadline(deadLine);

    todo.setCreatedAt(new Date());
    todo.setUpdatedAt(new Date());

    todoRepository.save(todo);
  }

  @Override
  public List<TodoDTO> getTodoList() {
    List<Todo> todoList = this.todoRepository.findAll();

    List<TodoDTO> responseList = new ArrayList<>();

    for (Todo todo : todoList) {
      TodoDTO todoDTO = this.convertTodoToTodoDTO(todo);
      responseList.add(todoDTO);
    }

    return responseList;
  }

  @Override
  public TodoDTO getTodoById(Long id) {
    Optional<Todo> optionalTodo = todoRepository.findById(id);

    if (optionalTodo.isPresent()) {
      Todo todo = optionalTodo.get();
      return this.convertTodoToTodoDTO(todo);
    } else {
      // TODO: error handle
      return null;
    }
  }

  @Override
  public TodoDTO updateTodo(Long id, TodoForm updateForm) throws ParseException {
    Optional<Todo> optionalTodo = todoRepository.findById(id);

    if (optionalTodo.isPresent()) {
      Todo todo = optionalTodo.get();

      todo.setName(updateForm.getName());
      Date deadLine = this.convertDateFromString(updateForm.getDeadline());
      todo.setDeadline(deadLine);
      todo.setStatus(updateForm.isStatus());
      todo.setUpdatedAt(new Date());

      Todo todoSaved = todoRepository.save(todo);

      return this.convertTodoToTodoDTO(todoSaved);

    } else {
      // TODO: error handle
      return null;
    }
  }

  @Override
  public List<TodoDTO> searchTodo(String name) {
    List<Todo> searchTodoResult = todoRepository.searchTodo(name);
    if (searchTodoResult.isEmpty() || searchTodoResult == null) {
      return new ArrayList<>();
    } else {
      List<TodoDTO> responseList = new ArrayList<>();

      for (Todo todo : searchTodoResult) {
        TodoDTO todoDTO = this.convertTodoToTodoDTO(todo);
        responseList.add(todoDTO);
      }

      return responseList;
    }
  }

  private TodoDTO convertTodoToTodoDTO(Todo todo) {
    TodoDTO todoDTO = new TodoDTO();

    todoDTO.setId(todo.getId());
    todoDTO.setName(todo.getName());
    todoDTO.setDeadline(todo.getDeadline());
    todoDTO.setStatus(todo.isStatus());

    return todoDTO;
  }

  private Date convertDateFromString(String stringDate) throws ParseException {
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    return format.parse(stringDate);
  }
}
