package com.github.hallgat89.megatodo.api;

import com.github.hallgat89.megatodo.service.TodoService;
import com.github.hallgat89.megatodo.view.TodoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoApi {

    public static final String APIROOT = "/api";
    TodoService service;

    @Autowired
    public TodoApi(TodoService service) {
        this.service = service;
    }

    @GetMapping(APIROOT + "/all")
    public List<TodoView> getAll() {
        return service.getAllTodos();
    }

    @GetMapping(APIROOT + "/get")
    public TodoView getById(@RequestParam(value = "id", required = true) Long id) {
        return service.getTodo(id);
    }

    @PostMapping(APIROOT + "/new")
    public Long deleteTodo(@RequestParam(value = "message", required = true) String message) {
        return service.addNew(message);
    }

    @DeleteMapping(APIROOT + "/delete")
    public void deleteTodo(@RequestParam(value = "id", required = true) Long id) {
        service.delete(id);
    }

}
