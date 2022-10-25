package com.github.hallgat89.megatodo.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hallgat89.megatodo.repositories.TodoRepository;
import com.github.hallgat89.megatodo.view.TodoView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// NOTE run the application before test
@SpringBootTest
@AutoConfigureMockMvc
public class RestTest {
    public static final String GETALL_URL = "/api/all";
    public static final String GET_URL = "/api/get";
    public static final String CREATE_URL = "/api/new";
    public static final String DELETE_URL = "/api/delete";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TodoRepository repository;

    @Test
    public void testAddAndDelete() throws Exception {
        final String todoMessage = "testAddAndDelete";
        assertTodoCount(0);
        String newId = createNewTodo(todoMessage);
        assertTodoCount(1);
        assertEquals(todoMessage, fetchById(newId).getDescription());
        deleteTodo(newId);
        assertTodoCount(0);
    }

    private void deleteTodo(String newId) throws Exception {
        mockMvc.perform(delete(DELETE_URL).param("id", newId)).andExpect(status().isOk());
    }

    private String createNewTodo(String todoMessage) throws Exception {
        return mockMvc.perform(post(CREATE_URL).param("message", todoMessage)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    }

    private TodoView fetchById(String id) throws Exception {
        MvcResult result = mockMvc.perform(get(GET_URL).param("id", id)).andExpect(status().isOk()).andReturn();
        TodoView view = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });
        return view;
    }


    private void assertTodoCount(int count) throws Exception {
        MvcResult getResult = mockMvc.perform(get(GETALL_URL)).andReturn();
        List<TodoView> todoList = objectMapper
                .readValue(getResult.getResponse().getContentAsString(), new TypeReference<>() {
                });
        assertEquals(count, todoList.size());
    }

}
