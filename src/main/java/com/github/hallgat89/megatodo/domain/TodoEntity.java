package com.github.hallgat89.megatodo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TodoEntity {
    @Id
    Long id;
    String description;
}
