package com.nibir.hossain.todo.services;

import com.nibir.hossain.todo.web.model.TodoDto;

import java.util.List;

/*
 * Created by Nibir Hossain on 21.11.20
 */
public interface BaseService <T, V> {
    List<T> findAll();
    T findById(V id);
    T updateById(V id, T object);
    T deleteById(V id);

}
