package com.nibir.hossain.todo.services;

/*
 * Created by Nibir Hossain on 21.11.20
 */

import com.nibir.hossain.todo.domain.Todo;
import com.nibir.hossain.todo.domain.User;
import com.nibir.hossain.todo.repositories.TodoRepository;
import com.nibir.hossain.todo.repositories.UserRepository;
import com.nibir.hossain.todo.web.exceptions.NotFoundException;
import com.nibir.hossain.todo.web.mappers.TodoMapper;
import com.nibir.hossain.todo.web.mappers.UserMapper;
import com.nibir.hossain.todo.web.model.TodoDto;
import com.nibir.hossain.todo.web.model.TodoPagedList;
import com.nibir.hossain.todo.web.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDto> findAll() {
        return this.userRepository.findAll()
                .stream()
                .map(userMapper::user2UserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) {
        return this.userRepository.findById(id).map(userMapper::user2UserDto).orElseThrow(NotFoundException::new);
    }

    @Override
    public UserDto save(UserDto object) {
        return userMapper.user2UserDto(userRepository.save(userMapper.userDto2User(object)));
    }

    @Override
    public UserDto updateById(Long id, UserDto object) {
        User user = this.userRepository.findById(id).orElseThrow(NotFoundException::new);
        user.setEmail(object.getEmail());
        user.setUsername(object.getUsername());
        user.setPassword(object.getPassword());

        return this.userMapper.user2UserDto(this.userRepository.save(user));

    }

    @Override
    public UserDto deleteById(Long id) {
        User user = this.userRepository.findById(id).orElse(null);
        if(user != null) {
            this.userRepository.deleteById(id);
        }

        return this.userMapper.user2UserDto(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }
}
