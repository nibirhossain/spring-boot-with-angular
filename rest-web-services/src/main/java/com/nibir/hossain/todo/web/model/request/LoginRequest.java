package com.nibir.hossain.todo.web.model.request;

/*
 * Created by Nibir Hossain on 13.12.20
 */
public class LoginRequest {
    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}