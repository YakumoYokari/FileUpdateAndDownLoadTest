package cn.altria.fileserver.server.service.imp;

import cn.altria.fileserver.server.mapper.FileMapper;
import cn.altria.fileserver.server.pojo.MyFile;
import cn.altria.fileserver.server.service.FileService;
import cn.altria.fileserver.server.utils.FileUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.UUID;

@Service("FileService")
public class FileServiceImp implements FileService {

    FileMapper fileMapper;

    @Autowired
    public void setFileMapper(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public String Update(MultipartFile file) {
        String UUid = UUID.randomUUID().toString();//生成UUID

        //保存在files下的yyyyMMdd文件夹中
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dirName = sdf.format(System.currentTimeMillis());
        File dest = new File("files/"+dirName+"/"+UUid);
        if(!dest.exists()){
            dest.getParentFile().mkdirs();
            try {
                dest.createNewFile(); //创建文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            file.transferTo(dest.getAbsoluteFile()); //写入文件
        }catch (Exception e){
            e.printStackTrace();
            return "服务器保存失败";
        }

        //MyFile用于数据库保存
        MyFile myFile = FileUtil.Transform(file);

        //获取创建时间
        BasicFileAttributes attr = null;
        try {
            Path path =  dest.toPath();
            attr = Files.readAttributes(path, BasicFileAttributes.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Instant instant = attr.creationTime().toInstant();

        //生成数据库行
        myFile.setUUid(UUid);
        myFile.setBuildTime(instant.toString());
        myFile.setAddress(dest.getAbsolutePath());
        System.out.println(dest.getAbsolutePath());

        //写入
        fileMapper.createNewTable();//如果不存在就自动创建表
        fileMapper.add(myFile);

        return UUid;
    }

    @Override
    public byte[] DownLoad(String UUid) {
        //查询数据库
        MyFile find = fileMapper.search(UUid);
        if(find == null){
            return null;
        }
        //获得文件位置
        String address = find.getAddress();
        //生成文件流存入字节数组
        File file = new File(address);
        byte[] bytes = new byte[0];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStream inputStream = new BufferedInputStream(fileInputStream);
            bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    @Override
    public String Search(String UUid) {
        //查询数据库
        MyFile find = fileMapper.search(UUid);
        if(find == null){
            return null;
        }
        return JSON.toJSONString(find);
    }
}
