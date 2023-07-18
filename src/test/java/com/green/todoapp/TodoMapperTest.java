package com.green.todoapp;

import com.green.todoapp.model.TodoEntity;
import com.green.todoapp.model.TodoSelVo;
import com.green.todoapp.model.TodoUpdDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE )
class TodoMapperTest {

    @Autowired
    private TodoMapper mapper;

    @Test
    @DisplayName("TodoMapper 글만들기")
    void insTodo() {
        //given
        TodoEntity entity = new TodoEntity();
        entity.setCtnt("테스트");
        //when
        int result = mapper.insTodo(entity);
        //
        assertEquals(1,result);
        assertEquals(4,entity.getItodo());
    }

    @Test
    @DisplayName("TodoMapper 글목록")
    void selTodo() {
        List<TodoSelVo> todoSelVos = mapper.selTodo();
        assertEquals(2,todoSelVos.size());
        TodoSelVo vo = todoSelVos.get(0);
        assertEquals(2, vo.getItodo());
        assertEquals(3,todoSelVos.get(1).getItodo());

    }

    @Test
    @DisplayName("TodoMapper 성공")
    void updTodo(){
        TodoEntity entity = new TodoEntity();
        entity.setItodo(2L);
        int i = mapper.updTodo(entity);
        assertEquals(1,i);
        int finishYn = entity.getFinishYn();
        assertEquals(0,finishYn);

    }

    @Test
    @DisplayName("TodoMapper 삭제")
    void delTodo(){
        TodoEntity entity = new TodoEntity();
        entity.setItodo(2L);
         mapper.delTodo(entity);
        System.out.println("entity = " + entity.getDelYn());

    }
}