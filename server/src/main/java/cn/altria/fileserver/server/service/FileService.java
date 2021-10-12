package cn.altria.fileserver.server.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface FileService {
    //功能1接口 上传
    public String Update(MultipartFile file);
    //功能2接口 下载
    public byte[] DownLoad(String UUid);
    //功能3接口 查询
    public String Search(String UUid);
}
