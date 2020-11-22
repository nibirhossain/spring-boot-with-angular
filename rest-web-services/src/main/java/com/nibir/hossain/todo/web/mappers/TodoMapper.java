package com.nibir.hossain.todo.web.mappers;

import com.nibir.hossain.todo.domain.Todo;
import com.nibir.hossain.todo.web.model.TodoDto;
import org.springframework.stereotype.Component;

/*
 * Created by Nibir Hossain on 22.11.20
 */
@Component
public class TodoMapper {
    public TodoDto todo2TodoDto(Todo todo) {
        TodoDto todoDto = new TodoDto();
        todoDto.setId(todo.getId());
        todoDto.setName(todo.getName());
        todoDto.setDescription(todo.getDescription());
        todoDto.setTargetDate(todo.getTargetDate());
        todoDto.setDone(todo.isDone());

        return todoDto;
    }
}
