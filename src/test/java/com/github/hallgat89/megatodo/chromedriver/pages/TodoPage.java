package com.github.hallgat89.megatodo.chromedriver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TodoPage {

    private WebDriver driver;

    By messageField = new By.ByXPath("//input[@id='message']");
    By submitButton = new By.ByXPath("//input[@id='submitTodo']");
    By todoDescriptions = new By.ByXPath("//div[@id='todolist']//span[@class='todoDescription']");

    By todoElements = new By.ByXPath("//div[@id='todolist']//div[@class='todoelement']");
    By elementDescription = new By.ByXPath("./span[@class='todoDescription']");
    By elementId = new By.ByXPath("./span[@class='todoId']");
    By todoDeleteButton = new By.ByXPath(".//input[@value='Delete']");

    String todoElementById = "//div[@id='todolist']//span[@class='todoId' and text()='%d']/parent::div";

    public TodoPage(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void addTodo(String todo) {
        driver.findElement(messageField).sendKeys(todo);
        driver.findElement(submitButton).click();
    }

    public List<String> getTodoDescriptions() {
        ArrayList<String> descriptions = new ArrayList<>();
        driver.findElements(todoDescriptions).stream().map(e -> e.getText()).forEach(descriptions::add);
        return descriptions;
    }

    public void deleteTodo(Long id) {
        driver.findElement(By.xpath(String.format(todoElementById, id))).findElement(todoDeleteButton).click();
    }

    public List<TodoElement> getTodoElements() {
        ArrayList<TodoElement> descriptions = new ArrayList<>();
        List<WebElement> elems = driver.findElements(todoElements);
        for (WebElement e : elems) {
            Long id = Long.valueOf(e.findElement(elementId).getText());
            String descr = e.findElement(elementDescription).getText();
            descriptions.add(new TodoElement(id, descr));
        }
        return descriptions;
    }

}
