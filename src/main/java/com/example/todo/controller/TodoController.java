package com.example.todo.controller;

import com.example.todo.model.TodoDTO;
import com.example.todo.model.TodoForm;
import com.example.todo.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
  private final TodoService todoService;

  TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  @GetMapping("/test/{name}")
  public String test(@PathVariable String name) {
    String x = "Hello ";
    return x + name;
  }

  @PostMapping("")
  public String createTodo(@RequestBody TodoForm todoForm) throws Exception {
    this.todoService.createTodo(todoForm);
    return "OK";
  }

  @GetMapping("")
  public List<TodoDTO> getTodoList() {
    List<TodoDTO> res = this.todoService.getTodoList();
    return res;
  }
}
