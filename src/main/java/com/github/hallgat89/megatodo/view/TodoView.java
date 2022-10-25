package com.github.hallgat89.megatodo.view;

import com.github.hallgat89.megatodo.domain.TodoEntity;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class TodoView implements Serializable {

    private Long id;
    private String description;

    public TodoView(TodoEntity entity) {
        this.id = entity.getId();
        this.description = entity.getDescription();
    }

}
