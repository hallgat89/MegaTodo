package com.github.hallgat89.megatodo.chromedriver.pages;

public class TodoElement {

    Long id;
    String description;

    public TodoElement(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

}
