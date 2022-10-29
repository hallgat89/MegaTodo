package com.github.hallgat89.megatodo.api;

import com.github.hallgat89.megatodo.service.TodoService;
import com.github.hallgat89.megatodo.view.TodoView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "todoApi", description = "the TODO API")
public class TodoApiController {

    TodoService service;

    @Autowired
    public TodoApiController(TodoService service) {
        this.service = service;
    }

    @GetMapping("/all")
    @Operation(summary = "Get all Todo Views", description = "Get all Todo Views")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = TodoView.class))))
    })
    public List<TodoView> getAll() {
        return service.getAllTodos();
    }

    @GetMapping("/get")
    @Operation(summary = "Gets 1 Todo by ID", description = "Accepts an ID of an existing Todo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = TodoView.class)))
    })
    public TodoView getById(@RequestParam(value = "id", required = true) Long id) {
        return service.getTodo(id);
    }

    @PostMapping("/new")
    @Operation(summary = "Creates a new todo from a message", description = "Accepts a String, returns an ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Long.class)))
    })
    public Long createTodo(@RequestParam(value = "message", required = true) String message) {
        return service.addNew(message);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Deletes a todo", description = "Accepts an ID")
    public void deleteTodo(@RequestParam(value = "id", required = true) Long id) {
        service.delete(id);
    }

}
