package cn.altria.fileserver.server.mapper;

import cn.altria.fileserver.server.pojo.MyFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FileMapper {
    boolean add(MyFile myFile);
    boolean createNewTable();
    MyFile search(String UUid);
}
