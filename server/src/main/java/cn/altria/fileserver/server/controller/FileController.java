package cn.altria.fileserver.server.controller;

import cn.altria.fileserver.server.pojo.MyFile;
import cn.altria.fileserver.server.service.FileService;
import cn.altria.fileserver.server.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.UUID;

@Controller
@ResponseBody
public class FileController {

    FileService service;

    @Autowired
    public void setService(FileService service) {
        this.service = service;
    }

    //功能一
    @PostMapping("update")
    public String Updata(@RequestParam("file") MultipartFile file){
        System.out.println("进入controller");
        if(file == null){
            System.out.println("上传失败");
            return "上传失败";
        }
        return service.Update(file);
    }

    //功能二
    @GetMapping("download")
    public byte[] DownLoad(@RequestParam("UUid") String UUid){
        if (UUid == null){
            return null;
        }
        return service.DownLoad(UUid);
    }

    //功能三
    @GetMapping("search")
    public String Search(@RequestParam("UUid") String UUid){
        if (UUid == null){
            return "UUid错误";
        }
        return service.Search(UUid);
    }

}
