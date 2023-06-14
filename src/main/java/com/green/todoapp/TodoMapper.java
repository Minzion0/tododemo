package com.green.todoapp;

import com.green.todoapp.model.TodoEntity;
import com.green.todoapp.model.TodoSelDto;
import com.green.todoapp.model.TodoSelVo;
import com.green.todoapp.model.TodoUpdDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoMapper {

   int insTodo(TodoEntity entity);
    List<TodoSelVo>selTodo();
    int findFinsh(TodoUpdDto dto);
    Long updTodo(TodoEntity entity);
    Long delTodo(TodoEntity entity);
}
