package com.nibir.hossain.todo.web.model.response;

/*
 * Created by Nibir Hossain on 13.12.20
 */
public class SignupResponse {
    private String message;

    public SignupResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
