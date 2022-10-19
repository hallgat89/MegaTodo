package com.github.hallgat89.megatodo.configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class SpringTestConfig {

    @Bean
    public WebDriver webDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }


}
