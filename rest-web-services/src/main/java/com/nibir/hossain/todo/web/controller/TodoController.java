package com.nibir.hossain.todo.web.controller;

/*
 * Created by Nibir Hossain on 21.11.20
 */

import com.nibir.hossain.todo.services.TodoService;
import com.nibir.hossain.todo.web.model.TodoDto;
import com.nibir.hossain.todo.web.model.TodoPagedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = {"/api/v1/"})
public class TodoController {
    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 2;

    @Autowired
    private TodoService todoService;

    @GetMapping(produces = { "application/json" }, path = "todos")
    public ResponseEntity<TodoPagedList> findAllTodosWithPagination(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", required = false) Integer pageSize)
    {
        System.out.println("PageNumber " + pageNumber + ", PageSize " + pageSize);
        if (pageNumber == null || pageNumber < 0){
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        TodoPagedList todoPagedList = todoService.findAllWithPagination(PageRequest.of(pageNumber, pageSize));

        return new ResponseEntity<>(todoPagedList, HttpStatus.OK);
    }

    @GetMapping(path = "/todos-normal")
    public List<TodoDto> findAllTodos() {
        return this.todoService.findAll();
    }

    @GetMapping(path = "/todos/{id}")
    public TodoDto findTodoById(@PathVariable long id) {
        return this.todoService.findById(id);
    }

    @PostMapping(path = "/todos")
    public TodoDto createNewTodo(@RequestBody @Validated TodoDto todoDto) {
        return this.todoService.save(todoDto);
    }

    @PutMapping(path = "/todos/{id}")
    public TodoDto updateTodoById(@PathVariable long id, @Validated @RequestBody TodoDto todoDto) {
        return this.todoService.updateById(id, todoDto);
    }

    @DeleteMapping(path = "/todos/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable long id) {
        TodoDto todoDto = this.todoService.deleteById(id);

        if(todoDto != null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
