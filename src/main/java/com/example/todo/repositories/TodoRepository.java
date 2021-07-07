package com.example.todo.repositories;

import com.example.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
  @Query(value = "SELECT * FROM todo td WHERE td.name LIKE %?1%", nativeQuery = true)
  List<Todo> searchTodo(String name);
}
