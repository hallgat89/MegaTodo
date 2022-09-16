package com.github.hallgat89.megatodo.view;

import com.github.hallgat89.megatodo.domain.TodoEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TodoView {

    public Long id;

    public String description;

    public TodoView(TodoEntity entity) {
        this.id = entity.getId();
        this.description = entity.getDescription();
    }

}
