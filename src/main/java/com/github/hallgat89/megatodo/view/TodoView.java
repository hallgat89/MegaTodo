package com.github.hallgat89.megatodo.view;

import com.github.hallgat89.megatodo.domain.TodoEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TodoView implements Serializable {

    private Long id;
    private String description;

    public TodoView(){} // for serialization

    public TodoView(TodoEntity entity) {
        this.id = entity.getId();
        this.description = entity.getDescription();
    }

}
