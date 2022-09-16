package com.github.hallgat89.megatodo.controller;

import com.github.hallgat89.megatodo.domain.TodoEntity;
import com.github.hallgat89.megatodo.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoController {

    TodoRepository repository;

    @Autowired
    public TodoController(TodoRepository repo) {
        this.repository = repo;
    }

    @GetMapping("/api/todolist")
    public List<TodoEntity> getTodos() {
        return repository.findAll();
    }
}
