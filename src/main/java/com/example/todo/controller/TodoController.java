package com.example.todo.controller;

import com.example.todo.model.Todo;
import com.example.todo.model.TodoForm;
import com.example.todo.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
  private final TodoRepository todoRepository;

  TodoController(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  @GetMapping("/test/{name}")
  public String test(@PathVariable String name) {
    String x = "Hello ";
    return x + name;
  }

  @PostMapping("")
  public String createTodo(@RequestBody TodoForm todoForm) throws Exception {
    Todo todo = new Todo();
    todo.setName(todoForm.getName());
    todo.setStatus(todoForm.isStatus());

    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    Date deadLine = format.parse(todoForm.getDeadline());

    todo.setDeadline(deadLine);
    todo.setCreatedAt(new Date());
    todo.setUpdatedAt(new Date());

    todoRepository.save(todo);

    return "OK";
  }
}
