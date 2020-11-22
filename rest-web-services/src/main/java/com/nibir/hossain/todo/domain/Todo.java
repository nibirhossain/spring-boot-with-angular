package com.nibir.hossain.todo.domain;

import java.time.OffsetDateTime;

/*
 * Created by Nibir Hossain on 21.11.20
 */
public class Todo {
    private long id;
    private String name;
    private String description;
    private OffsetDateTime targetDate;
    private boolean isDone;

    public Todo() {

    }

    public Todo(long id, String name, String description, OffsetDateTime targetDate, boolean isDone) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.targetDate = targetDate;
        this.isDone = isDone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OffsetDateTime getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(OffsetDateTime targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
