package com.nibir.hossain.todo.web.model;

import java.util.Date;
import java.util.Objects;

/*
 * Created by Nibir Hossain on 21.11.20
 */
public class TodoDto {
    private long id;
    private String name;
    private String description;
    private Date targetDate;
    private boolean isDone;

    public TodoDto() {

    }

    public TodoDto(long id, String name, String description, Date targetDate, boolean isDone) {
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

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoDto todoDto = (TodoDto) o;
        return id == todoDto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
