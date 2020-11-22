package com.nibir.hossain.todo.services;

/*
 * Created by Nibir Hossain on 21.11.20
 */

import com.nibir.hossain.todo.repositories.TodoRepository;
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

    private static List<TodoDto> todoDtoList = new ArrayList<>();
    private static long idCounter = 0;
    static {
        todoDtoList.add(new TodoDto(++idCounter, "Phone Contract", "Cancellation mobile contract", new Date(), false));
        todoDtoList.add(new TodoDto(++idCounter, "Rechtschutz", "Cancellation Rechtschutz contract", new Date(), false));
        todoDtoList.add(new TodoDto(++idCounter, "Hausratversicherung", "Cancellation Hausratversicherung contract", new Date(), false));
    }

    @Override
    public List<TodoDto> findAll() {
        return this.todoRepository.findAll()
                .stream()
                .map(todoMapper::todo2TodoDto)
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto findById(Long id) {
        return todoDtoList.stream().filter(todoDto -> todoDto.getId() == id).findFirst().orElse(null);
    }

    @Override
    public TodoDto updateById(Long id, TodoDto object) {
        if(object.getId() == -1 || object.getId() == 0) {
            object.setId(++idCounter);
        } else {
            deleteById(object.getId());
        }
        todoDtoList.add(object);
        return object;
    }

    @Override
    public TodoDto deleteById(Long id) {
        TodoDto todoDto = findById(id);

        if(todoDto == null) return null;
        if(todoDtoList.remove(todoDto)) {
            return todoDto;
        }

        return null;
    }
}
