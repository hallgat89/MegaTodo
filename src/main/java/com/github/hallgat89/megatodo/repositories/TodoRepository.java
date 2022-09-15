package com.github.hallgat89.megatodo.repositories;

import com.github.hallgat89.megatodo.domain.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity,Long> {

    List<TodoEntity> findAll();
}
