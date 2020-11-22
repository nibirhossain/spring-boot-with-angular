package com.nibir.hossain.todo.services;

/*
 * Created by Nibir Hossain on 21.11.20
 */

import com.nibir.hossain.todo.web.model.TodoDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private static List<TodoDto> todoDtoList = new ArrayList<>();
    private static long idCounter = 0;
    static {
        todoDtoList.add(new TodoDto(++idCounter, "Phone Contract", "Cancellation mobile contract", new Date(), false));
        todoDtoList.add(new TodoDto(++idCounter, "Rechtschutz", "Cancellation Rechtschutz contract", new Date(), false));
        todoDtoList.add(new TodoDto(++idCounter, "Hausratversicherung", "Cancellation Hausratversicherung contract", new Date(), false));
    }

    @Override
    public List<TodoDto> findAll() {
        return todoDtoList;
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
