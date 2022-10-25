package com.github.hallgat89.megatodo.controller;

import com.github.hallgat89.megatodo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    TodoService service;

    @Autowired
    public PageController(TodoService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String mainPage() {
        return "hello";
    }

    @GetMapping("/todo")
    public String todoPage(Model model) {
        model.addAttribute("todoList", service.getAllTodos());
        return "todo";
    }

    @PostMapping("/new")
    public String newTodo(Model model, @RequestParam("message") String message) {
        service.addNew(message);
        model.addAttribute("todoList", service.getAllTodos());
        return "todo";
    }

    // note: We use POST because form doesn't support delete
    @PostMapping("/delete")
    public String deleteTodo(Model model, @RequestParam("id") Long id) {
        service.delete(id);
        model.addAttribute("todoList", service.getAllTodos());
        return "todo";
    }

}
