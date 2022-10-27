package com.github.hallgat89.megatodo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "todo")
@Getter
@Setter
@AllArgsConstructor
public class TodoEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "message")
    String description;

    public TodoEntity(){}
    public TodoEntity(String description) {
        this.description = description;
    }

}
