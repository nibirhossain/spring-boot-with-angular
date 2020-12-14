package com.nibir.hossain.todo.services;

import com.nibir.hossain.todo.domain.User;
import com.nibir.hossain.todo.web.model.UserDto;

import java.util.Optional;

/*
 * Created by Nibir Hossain on 13.12.20
 */
public interface UserService extends BaseService<UserDto, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
