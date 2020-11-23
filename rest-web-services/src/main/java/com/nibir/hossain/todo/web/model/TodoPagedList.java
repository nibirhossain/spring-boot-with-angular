package com.nibir.hossain.todo.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/*
 * Created by Nibir Hossain on 10.08.20
 */
public class TodoPagedList extends PageImpl<TodoDto> implements Serializable {

    static final long serialVersionUID = 1114715135625836949L;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public TodoPagedList(@JsonProperty("content") List<TodoDto> content,
                         @JsonProperty("number") int number,
                         @JsonProperty("size") int size,
                         @JsonProperty("totalElements") Long totalElements,
                         @JsonProperty("pageable") JsonNode pageable,
                         @JsonProperty("last") boolean last,
                         @JsonProperty("totalPages") int totalPages,
                         @JsonProperty("sort") JsonNode sort,
                         @JsonProperty("first") boolean first,
                         @JsonProperty("numberOfElements") int numberOfElements) {

        super(content, PageRequest.of(number, size), totalElements);
    }

    public TodoPagedList(List<TodoDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public TodoPagedList(List<TodoDto> content) {
        super(content);
    }
}
