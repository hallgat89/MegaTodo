package com.github.hallgat89.megatodo.chromedriver;

import com.github.hallgat89.megatodo.configuration.SpringTestConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.google.common.collect.ImmutableMap;

@SpringBootTest
@Import(SpringTestConfig.class)
public class ChromeDriverTest {

    WebDriver webDriver;

    @Autowired
    public ChromeDriverTest(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Test
    public void contextRuns() {
    }

}
