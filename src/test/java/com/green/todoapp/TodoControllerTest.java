package com.green.todoapp;

import com.green.todoapp.model.TodoInsDto;
import com.green.todoapp.model.TodoSelVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    private MockMvc mvc;//소스에서 요청할때 필요함

    @MockBean
    private TodoService service;

    @Test
    @DisplayName("TODO - 등록")
    void postTodo() throws Exception{
        //given
        given(service.insTodo(any(TodoInsDto.class))).willReturn(3L);
        //when
        String json="{\"ctnt\":\"빨래 개기\"}";
        ResultActions ra = mvc.perform(post("/api/todo")
                        .content(json)//json 타입을 보낸다
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk()) //200을 기대
                        .andExpect(content().string("3"))//값은 3을 기대
                        .andDo(print());//


        //then

        verify(service).insTodo(any());//실행 확인
    }

    @Test
    @DisplayName("TODO- 리스트")
    void getTodo()throws Exception{
        List<TodoSelVo> mockList= new ArrayList<>();
        mockList.add(new TodoSelVo(1L,"테스트","2023",null,1,"2024-05-11"));
        mockList.add(new TodoSelVo(2L,"테스트2","2024",null,0,null));
        given(service.selTodo()).willReturn(mockList);

        mvc.perform(get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(mockList.size())))
                .andExpect(jsonPath("$[*].itodo").exists())
                .andExpect(jsonPath("$[0].itodo").value(1))
                .andExpect(jsonPath("$[0].ctnt").value("테스트"))
                .andDo(print());

        verify(service).selTodo();
    }
}