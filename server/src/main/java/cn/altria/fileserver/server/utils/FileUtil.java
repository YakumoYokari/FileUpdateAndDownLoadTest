package cn.altria.fileserver.server.utils;

import cn.altria.fileserver.server.pojo.MyFile;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

    public static MyFile Transform(MultipartFile file) {
        String fileName = file.getOriginalFilename();//获取文件名
        String type = fileName.substring(fileName.indexOf(".")+1);//获取扩展名
        long size = file.getSize();//获取文件大小
        MyFile myFile = new MyFile(null,size,type,fileName,null,null);
        return myFile;
    }
}
