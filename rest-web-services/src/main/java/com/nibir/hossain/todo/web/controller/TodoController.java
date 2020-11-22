package com.nibir.hossain.todo.web.controller;

/*
 * Created by Nibir Hossain on 21.11.20
 */

import com.nibir.hossain.todo.services.TodoService;
import com.nibir.hossain.todo.web.model.TodoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(value = "http://localhost:4200")
@RestController
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping(path = "/todos")
    public List<TodoDto> listTodos() {
        return this.todoService.findAll();
    }

    @GetMapping(path = "/todos/{id}")
    public TodoDto getTodoById(@PathVariable long id) {
        return this.todoService.findById(id);
    }

    @PutMapping(path = "/todos/{id}")
    public TodoDto updateTodoById(@PathVariable long id, @Validated @RequestBody TodoDto todoDto) {
        return this.todoService.updateById(id, todoDto);
    }

    @DeleteMapping(path = "/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable long id) {
        TodoDto todoDto = this.todoService.deleteById(id);

        if(todoDto != null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
