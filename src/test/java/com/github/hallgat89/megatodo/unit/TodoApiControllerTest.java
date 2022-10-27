package com.github.hallgat89.megatodo.unit;

import com.github.hallgat89.megatodo.api.TodoApiController;
import com.github.hallgat89.megatodo.service.TodoService;
import com.github.hallgat89.megatodo.view.TodoView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TodoApiControllerTest {

    public static final TodoView TODO = new TodoView(1L, "Testmsg");
    public static final long ID = 89L;
    @InjectMocks
    TodoApiController underTest;

    @Mock
    TodoService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createTodoTest() {
        final String message = "todo";
        given(service.addNew(message)).willReturn(ID);
        // when
        Long receivedId = underTest.createTodo(message);
        // then
        assertEquals(ID, receivedId);
        verify(service, times(1)).addNew(message);
    }

    @Test
    public void getAllTest() {
        List<TodoView> todoList = Arrays.asList(TODO);
        // given
        given(service.getAllTodos()).willReturn(todoList);
        // when
        TodoView result = underTest.getAll().get(0);
        // then
        verify(service, times(1)).getAllTodos();
        assertEquals(TODO.getDescription(), result.getDescription());
        assertEquals(TODO.getId(), result.getId());
    }

    @Test
    public void getByIdTest() {
        // given
        given(service.getTodo(ID)).willReturn(TODO);
        // when
        TodoView result = underTest.getById(ID);
        // then
        verify(service, times(1)).getTodo(ID);
        assertEquals(TODO.getDescription(), result.getDescription());
        assertEquals(TODO.getId(), result.getId());
    }

    @Test
    public void deleteTodoTest() {
        // when
        underTest.deleteTodo(ID);
        // then
        verify(service, times(1)).delete(ID);
    }

}
