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

    public TodoView getTodo(long id) {
        return repository.findById(id).map(e->new TodoView(e)).get();
    }
    public List<TodoView> getAllTodos() {
        return repository.findAll().stream().map(e -> new TodoView(e)).collect(Collectors.toList());
    }

    public Long addNew(String message) {
        if (message != null && message.length() > 0) {
            TodoEntity saved = repository.save(new TodoEntity(message));
            return saved.getId();
        }
        throw new IllegalArgumentException("Message cannot be null or empty!");
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
