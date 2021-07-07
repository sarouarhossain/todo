package com.example.todo.controller;

import com.example.todo.model.TodoDTO;
import com.example.todo.model.TodoForm;
import com.example.todo.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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
    return this.todoService.getTodoList();
  }

  @GetMapping("/{id}")
  public TodoDTO getTodoById(@PathVariable Long id) {
    return this.todoService.getTodoById(id);
  }

  @PutMapping("/{id}")
  public TodoDTO updateTodo(@PathVariable Long id, @RequestBody TodoForm updateForm)
      throws ParseException {
    return this.todoService.updateTodo(id, updateForm);
  }

  @GetMapping("/search")
  public List<TodoDTO> searchTodo(@RequestParam("name") String name) {
    return this.todoService.searchTodo(name);
  }
}
