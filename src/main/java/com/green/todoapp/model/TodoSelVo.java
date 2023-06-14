package com.green.todoapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@AllArgsConstructor
public class TodoSelVo {
    private Long itodo;
    private String ctnt;
    private String createdAt;
    private String pic;
    private int finishYn;
    private String finishedAt;
}
