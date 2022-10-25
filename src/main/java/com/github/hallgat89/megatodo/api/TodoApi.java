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


    @PostMapping(APIROOT + "/new")
    public void deleteTodo(@RequestParam("message") String message) {
        service.addNew(message);
    }

    @DeleteMapping(APIROOT + "/delete")
    public void deleteTodo(@RequestParam("id") Long id) {
        service.delete(id);
    }

}
