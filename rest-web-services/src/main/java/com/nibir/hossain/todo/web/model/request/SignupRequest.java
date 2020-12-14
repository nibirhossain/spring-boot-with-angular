package com.nibir.hossain.todo.web.model.request;

import java.util.Set;

/*
 * Created by Nibir Hossain on 13.12.20
 */
public class SignupRequest {
    private String username;

    private String email;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}