package com.green.todoapp;

import com.green.todoapp.model.*;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService service;




    @PostMapping//(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public Long postTodo( @RequestBody TodoInsDto dto){

        return service.insTodo(dto);
    }
    @GetMapping
     public List<TodoSelVo>getTodo(){
//        @RequestParam (defaultValue = "1") int page,@RequestParam (defaultValue = "20") int row
//        TodoSelDto dto = new TodoSelDto();
//        dto.setRow(row);
//        dto.setPage(page);
        return service.selTodo();
    }

    @PatchMapping
    public int updTodo(@RequestBody TodoUpdDto dto){

        return service.updTodo(dto);
    }

    @PatchMapping("/del")
    public int delTodo(@RequestBody TodoUpdDto dto){

        return service.delTodo(dto);
    }


}

