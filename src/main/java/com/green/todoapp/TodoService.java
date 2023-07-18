package com.green.todoapp;

import com.green.todoapp.model.*;
import com.green.todoapp.utils.FileUtils;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoMapper mapper;

    @Value("${file.dir}")
    private String fileDir;


    public Long insTodo(TodoInsDto dto){
        TodoEntity entity = new TodoEntity();
        entity.setCtnt(dto.getCtnt());

//            String uuid = FileUtils.makeRandomFileNm(img.getOriginalFilename());
//            entity.setPic(uuid);

//            File file = new File(fileDir);
//            if (!file.exists()){
//                file.mkdirs();
//            }
//            File file1 = new File(fileDir + uuid);
//            try {
//                img.transferTo(file1);
//            }catch (Exception e){
//                e.printStackTrace();
//                return 0L;
//            }
            mapper.insTodo(entity);

        return  entity.getItodo();

    }

    public List<TodoSelVo> selTodo(){
//        int page = dto.getPage() - 1;
//        dto.setStaridx(page*dto.getRow());


        return mapper.selTodo();
    }
    public int updTodo(TodoUpdDto dto){
        TodoEntity entity = new TodoEntity();
        entity.setItodo(dto.getItodo());
        mapper.updTodo(entity);

        System.out.println(entity.getFinishYn());
        return entity.getFinishYn();
    }
    public int delTodo(TodoUpdDto dto){
        TodoEntity entity = new TodoEntity();
        entity.setItodo(dto.getItodo());

        return mapper.delTodo(entity);
    }
}
