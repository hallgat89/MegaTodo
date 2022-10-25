package com.github.hallgat89.megatodo.chromedriver;

import com.github.hallgat89.megatodo.chromedriver.pages.TodoElement;
import com.github.hallgat89.megatodo.chromedriver.pages.TodoPage;
import com.github.hallgat89.megatodo.configuration.SpringTestConfig;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;


import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Import(SpringTestConfig.class)
public class ChromeDriverTest {

    public static final String HTTP_TARGET = "http://localhost:8080/todo";
    WebDriver webDriver;
    private long PAGE_RELOAD_TIME = 2000;

    @Autowired
    public ChromeDriverTest(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Test
    public void contextRuns() {
    }

    @Test
    public void AddAndRemoveTodo() throws InterruptedException {
        webDriver.get(HTTP_TARGET);
        TodoPage page = new TodoPage(webDriver);
        final String todoMessage = "This is a test todo: " + LocalTime.now().toString();
        page.addTodo(todoMessage);

        Optional<TodoElement> myTodo = page.getTodoElements().stream().filter(e -> e.getDescription().equals(todoMessage)).findFirst();
        assertTrue(myTodo.isPresent(), "The new message is NOT present!");

        page.deleteTodo(myTodo.get().getId());
        page = new TodoPage(webDriver);

        Thread.sleep(PAGE_RELOAD_TIME);
        boolean todoDeleted = page.getTodoElements().stream().noneMatch(e -> e.getDescription().equals(todoMessage));
        assertTrue(todoDeleted, "The new message is STILL present!");
    }

}
