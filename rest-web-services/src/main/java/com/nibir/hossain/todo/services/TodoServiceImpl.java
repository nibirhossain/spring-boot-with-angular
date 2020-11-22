package com.nibir.hossain.todo.services;

/*
 * Created by Nibir Hossain on 21.11.20
 */

import com.nibir.hossain.todo.repositories.TodoRepository;
import com.nibir.hossain.todo.web.exceptions.NotFoundException;
import com.nibir.hossain.todo.web.mappers.TodoMapper;
import com.nibir.hossain.todo.web.model.TodoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private TodoMapper todoMapper;

    @Override
    public List<TodoDto> findAll() {
        return this.todoRepository.findAll()
                .stream()
                .map(todoMapper::todo2TodoDto)
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto findById(Long id) {
        return this.todoRepository.findById(id).map(todoMapper::todo2TodoDto).orElseThrow(NotFoundException::new);
    }

    @Override
    public TodoDto updateById(Long id, TodoDto object) {
        if(object.getId() == -1 || object.getId() == 0) {
        } else {
            deleteById(object.getId());
        }
        return object;
    }

    @Override
    public TodoDto deleteById(Long id) {
        TodoDto todoDto = findById(id);


        return todoDto;
    }
}
