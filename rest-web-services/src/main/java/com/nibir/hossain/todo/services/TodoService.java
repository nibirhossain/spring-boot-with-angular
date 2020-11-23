package com.nibir.hossain.todo.services;

import com.nibir.hossain.todo.web.model.TodoDto;
import com.nibir.hossain.todo.web.model.TodoPagedList;
import org.springframework.data.domain.PageRequest;

/*
 * Created by Nibir Hossain on 21.11.20
 */
public interface TodoService extends BaseService<TodoDto, Long> {
    TodoPagedList findAllWithPagination(PageRequest pageRequest);
}
