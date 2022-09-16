package com.github.hallgat89.megatodo.controller;

import com.github.hallgat89.megatodo.repositories.TodoRepository;
import com.github.hallgat89.megatodo.view.TodoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;

@Controller
public class PageController {

    TodoRepository repository;

    @Autowired
    public PageController(TodoRepository repo) {
        this.repository = repo;
    }

    @GetMapping("/")
    public String mainPage() {
        return "hello";
    }

    @GetMapping("/todo")
    public String todoPage(Model model) {
        model.addAttribute("todoList", repository.findAll().stream().map(e->new TodoView(e)).collect(Collectors.toList()));
        return "todo";
    }

}
