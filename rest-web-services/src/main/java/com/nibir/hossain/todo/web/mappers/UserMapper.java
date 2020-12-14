package com.nibir.hossain.todo.web.mappers;

import com.nibir.hossain.todo.domain.Todo;
import com.nibir.hossain.todo.domain.User;
import com.nibir.hossain.todo.web.model.TodoDto;
import com.nibir.hossain.todo.web.model.UserDto;
import org.springframework.stereotype.Component;

import java.util.Date;

/*
 * Created by Nibir Hossain on 22.11.20
 */
@Component
public class UserMapper {
    public UserDto user2UserDto(User user) {
        if(user == null) return null;

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());

        return userDto;
    }

    public User userDto2User(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        return user;
    }
}
