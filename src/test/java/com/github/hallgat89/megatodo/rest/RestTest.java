package com.github.hallgat89.megatodo.rest;

import com.github.hallgat89.megatodo.rest.model.NewTodoEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;

// NOTE run the application before test
@SpringBootTest
@Import(TestConfiguration.class)
public class RestTest {
    public static final String GET_URL = "http://localhost:8080/api/all";
    public static final String CREATE_URL = "http://localhost:8080/api/new";
    public static final String DELETE_URL = "http://localhost:8080/api/delete";
    private RestTemplate template;

    @Autowired
    public RestTest(RestTemplate template) {
        this.template = template;
    }

    @Test
    public void testAddAndDelete() {

    }

}
