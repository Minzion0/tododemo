package com.green.todoapp.utils;


import java.util.UUID;

public class FileUtils {


    public static String makeRandomFileNm(String fileNm){
        String uuid = UUID.randomUUID().toString();
        String substring = fileNm.substring(fileNm.lastIndexOf("."));
        String RdNm = uuid + substring;
        return RdNm;
    }


    public static String getExt(String fileNm){
        return fileNm.substring(fileNm.lastIndexOf(".")+1);

    }
    public static String getFileNm(String fileNm){
        return fileNm.substring(0,fileNm.lastIndexOf("."));
    }
}
