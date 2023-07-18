package com.green.todoapp;

import com.green.todoapp.model.TodoEntity;
import com.green.todoapp.model.TodoInsDto;
import com.green.todoapp.model.TodoSelVo;
import com.green.todoapp.model.TodoUpdDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import({TodoService.class})
class TodoServiceTest {


    @MockBean
    private TodoMapper mapper;

    @Autowired
    private TodoService service;

    @Test
    @DisplayName("ToDoService - 등록")
    void insTodo() {
        when(mapper.insTodo(any(TodoEntity.class))).thenReturn(1);

        TodoInsDto dto = new TodoInsDto();
        dto.setCtnt("내용입력");
        Long result = service.insTodo(dto);

        assertEquals(null,result);

        verify(mapper).insTodo(any(TodoEntity.class));

    }

    @Test
    @DisplayName("TodoService - 리스트 가져오기")
    void selTodo() {
       List<TodoSelVo> mockList= new ArrayList<>();
        mockList.add(new TodoSelVo(1L,"테스트","2023",null,1,"2024-05-11"));
        mockList.add(new TodoSelVo(2L,"테스트2","2024",null,0,null));
        mockList.add(new TodoSelVo(3L,"테스트4","2024",null,0,null));
        when(mapper.selTodo()).thenReturn(mockList);

        List<TodoSelVo> todoSelVos = service.selTodo();
        int size = todoSelVos.size();
        assertEquals(3,size);
        assertEquals("테스트",todoSelVos.get(0).getCtnt());


       verify(mapper).selTodo();
    }

    @Test
    @DisplayName("TODOService 완료체크")
    void updTodo(){

        when(mapper.updTodo(any(TodoEntity.class))).thenReturn(0);
        TodoUpdDto dto = new TodoUpdDto();
        dto.setItodo(1L);
        int i = service.updTodo(dto);
        assertEquals(0,i);


        verify(mapper).updTodo(any(TodoEntity.class));
    }
    @Test
    @DisplayName("Todo 삭제")
    void delTodo(){

        when(mapper.delTodo(any(TodoEntity.class))).thenReturn(1);
        TodoUpdDto dto = new TodoUpdDto();
        dto.setItodo(1L);
        int i = service.delTodo(dto);
        assertEquals(1,i);

        verify(mapper).delTodo(any(TodoEntity.class));
    }
}