package com.nibir.hossain.todo.web.controller;

/*
 * Created by Nibir Hossain on 13.12.20
 */

import com.nibir.hossain.todo.config.CustomUserDetails;
import com.nibir.hossain.todo.config.JwtTokenUtil;
import com.nibir.hossain.todo.services.UserService;
import com.nibir.hossain.todo.web.model.UserDto;
import com.nibir.hossain.todo.web.model.request.LoginRequest;
import com.nibir.hossain.todo.web.model.request.SignupRequest;
import com.nibir.hossain.todo.web.model.response.LoginResponse;
import com.nibir.hossain.todo.web.model.response.SignupResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = {"/api/v1/auth/"})
public class AuthenticationController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping(path = "signup")
    public ResponseEntity<SignupResponse> basicAuth(@Validated @RequestBody SignupRequest signupRequest) {
        if(userService.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(new SignupResponse("Error: Username is already taken!"));
        }
        else if(userService.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(new SignupResponse("Error: Email is already in use!"));
        }

        UserDto userDto = new UserDto(signupRequest.getUsername(), signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()));
        UserDto userDto1 = userService.save(userDto);
        System.out.println(userDto1);

        return ResponseEntity.ok(new SignupResponse("User registered successfully!"));
    }

    @PostMapping(path = "login")
    public ResponseEntity<LoginResponse> login(@Validated @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(authentication);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();


        LoginResponse loginResponse = new LoginResponse();

        loginResponse.setToken(jwt);
        loginResponse.setTokenType("Bearer");
        loginResponse.setUsername(loginRequest.getUsername());

        return ResponseEntity.ok(loginResponse);
    }
}
