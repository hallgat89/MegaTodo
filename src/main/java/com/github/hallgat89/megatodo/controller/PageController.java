package com.github.hallgat89.megatodo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String mainPage() {
        return "hello";
    }

}
