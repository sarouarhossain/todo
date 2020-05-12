package com.example.todo.service;

import com.example.todo.model.Todo;
import com.example.todo.model.TodoDTO;
import com.example.todo.model.TodoForm;
import com.example.todo.repositories.TodoRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    Date deadLine = format.parse(todoForm.getDeadline());

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

  private TodoDTO convertTodoToTodoDTO(Todo todo) {
    TodoDTO todoDTO = new TodoDTO();

    todoDTO.setId(todo.getId());
    todoDTO.setName(todo.getName());
    todoDTO.setDeadline(todo.getDeadline());
    todoDTO.setStatus(todo.isStatus());

    return todoDTO;
  }
}
