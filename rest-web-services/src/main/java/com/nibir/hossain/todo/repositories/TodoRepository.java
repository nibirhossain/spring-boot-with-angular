package com.nibir.hossain.todo.repositories;

import com.nibir.hossain.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * Created by Nibir Hossain on 22.11.20
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
