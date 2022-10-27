package com.github.hallgat89.megatodo.unit;

import com.github.hallgat89.megatodo.controller.PageController;
import com.github.hallgat89.megatodo.domain.TodoEntity;
import com.github.hallgat89.megatodo.repositories.TodoRepository;
import com.github.hallgat89.megatodo.service.TodoService;
import com.github.hallgat89.megatodo.view.TodoView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TodoServiceTest {


    @InjectMocks
    TodoService underTest;

    @Mock
    TodoRepository repo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findByIdTest() {
        //given
        final Long id = 5L;
        TodoEntity entity = new TodoEntity(id, "todoText");
        when(repo.findById(id)).thenReturn(Optional.of(entity));
        // when
        TodoView view = underTest.getTodo(id);
        // then
        verify(repo, times(1)).findById(id);
        assertEquals(entity.getId(), view.getId());
        assertEquals(entity.getDescription(), view.getDescription());
    }

    @Test
    public void findAllTest() {
        //given
        final Long id = 5L;
        TodoEntity entity = new TodoEntity(id, "todoText");
        when(repo.findAll()).thenReturn(Arrays.asList(entity));
        // when
        List<TodoView> view = underTest.getAllTodos();
        // then
        verify(repo, times(1)).findAll();
        assertEquals(entity.getId(), view.get(0).getId());
        assertEquals(entity.getDescription(), view.get(0).getDescription());
    }

    @Test
    public void addNewTest() {
        //given
        final Long id = 5L;
        final String message = "message";
        TodoEntity newEntity = new TodoEntity(id, message);
        when(repo.save(any(TodoEntity.class))).thenReturn(newEntity);
        // when
        Long newId = underTest.addNew(message);
        // then
        verify(repo, times(1)).save(any());
        assertEquals(id, newId);
    }

    @Test
    public void addNewNullTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            underTest.addNew(null);
        });
    }

    @Test
    public void addNewEmptyStringTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            underTest.addNew("");
        });
    }

    @Test
    public void deleteTest() {
        //given
        final Long id = 5L;
        // when
        underTest.delete(id);
        // then
        verify(repo, times(1)).deleteById(id);
    }
}
