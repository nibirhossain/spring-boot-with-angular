package com.nibir.hossain.todo.bootstrap;

import com.nibir.hossain.todo.services.UserService;
import com.nibir.hossain.todo.web.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/*
 * Created by Nibir Hossain on 14.12.20
 */
@Component
public class Bootstrap implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        insertUserData();
    }

    private void insertUserData() {
        UserDto nibir = new UserDto("nibir", "nibir.hossain@hossain.de", passwordEncoder.encode("hossain"));
        this.userService.save(nibir);

        UserDto sajib = new UserDto("sajib", "sajib.mohammad@hossain.de", passwordEncoder.encode("mohammad"));
        this.userService.save(sajib);
    }
}
