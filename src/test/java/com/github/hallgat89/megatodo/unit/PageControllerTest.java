package com.github.hallgat89.megatodo.unit;

import com.github.hallgat89.megatodo.controller.PageController;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PageControllerTest {

    @InjectMocks
    PageController underTest;

    @Mock
    TodoService service;

    @Mock
    Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void defaultViewTest() {
        String returnedView = underTest.mainPage();
        assertEquals("hello", returnedView);
    }

    @Test
    public void defaultTodoViewTest() {
        //given
        List<TodoView> todoList = Arrays.asList();
        when(service.getAllTodos()).thenReturn(todoList);
        //when
        String returnedView = underTest.todoPage(model);
        // then
        assertEquals("todo", returnedView);
        verify(service, times(1)).getAllTodos();
        verify(model, times(1)).addAttribute("todoList", todoList);
    }

    @Test
    public void addTodoViewTest() {
        //given
        List<TodoView> todoList = Arrays.asList();
        String todoMsg = "todo";
        when(service.getAllTodos()).thenReturn(todoList);
        //when
        String returnedView = underTest.newTodo(model, todoMsg);
        // then
        assertEquals("todo", returnedView);
        verify(service, times(1)).getAllTodos();
        verify(service, times(1)).addNew(todoMsg);
        verify(model, times(1)).addAttribute("todoList", todoList);
    }

    @Test
    public void addTodoEmptyViewTest() {
        //given
        List<TodoView> todoList = Arrays.asList();
        String todoMsg = "";
        //when
        String returnedView = underTest.newTodo(model, todoMsg);
        // then
        assertEquals("todo", returnedView);
        verify(service, times(1)).getAllTodos();
        verify(service, times(0)).addNew(todoMsg);
        verify(model, times(1)).addAttribute("todoList", todoList);
    }

    @Test
    public void deleteTodoViewTest() {
        //given
        List<TodoView> todoList = Arrays.asList();
        Long todoId = 89L;
        when(service.getAllTodos()).thenReturn(todoList);
        //when
        String returnedView = underTest.deleteTodo(model, todoId);
        // then
        assertEquals("todo", returnedView);
        verify(service, times(1)).getAllTodos();
        verify(service, times(1)).delete(todoId);
        verify(model, times(1)).addAttribute("todoList", todoList);
    }

}
