package com.green.todoapp;

import com.green.todoapp.model.TodoEntity;
import com.green.todoapp.model.TodoInsDto;
import com.green.todoapp.utils.FileUtils;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class TodoService {
    private final TodoMapper mapper;

    @Value("${file.dir}")
    private String fileDir;
    @Autowired
    public TodoService(TodoMapper mapper) {

        this.mapper = mapper;
    }

    public Long insTodo(TodoInsDto dto, MultipartFile img){
        TodoEntity entity = new TodoEntity();
        entity.setCtnt(dto.getCtnt());

            String uuid = FileUtils.makeRandomFileNm(img.getOriginalFilename());
            entity.setPic(uuid);

            File file = new File(fileDir);
            if (!file.exists()){
                file.mkdirs();
            }
            File file1 = new File(fileDir + uuid);
            try {
                img.transferTo(file1);
            }catch (Exception e){
                e.printStackTrace();
                return 0L;
            }



        mapper.insTodo(entity);
        return entity.getItodo();

    }
}
