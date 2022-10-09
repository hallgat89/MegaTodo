package com.github.hallgat89.megatodo.service;

import com.github.hallgat89.megatodo.domain.TodoEntity;
import com.github.hallgat89.megatodo.repositories.TodoRepository;
import com.github.hallgat89.megatodo.view.TodoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {

    TodoRepository repository;

    @Autowired
    public TodoService(TodoRepository repo) {
        this.repository = repo;
    }

    public List<TodoView> getAllTodos() {
        return repository.findAll().stream().map(e -> new TodoView(e)).collect(Collectors.toList());
    }

    public void addNew(String message) {
        if (message.length() > 0) {
            repository.save(new TodoEntity(message));
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
