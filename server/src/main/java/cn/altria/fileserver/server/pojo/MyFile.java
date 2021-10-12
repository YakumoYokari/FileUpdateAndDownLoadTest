package cn.altria.fileserver.server.pojo;

public class MyFile {
    //uuid
    String UUid;
    //文件大小、
    long size;
    //文件类型
    String type;
    //原始文件名
    String fileName;
    //创建时间
    String buildTime;
    //文件保存目录地址
    String address;

    public MyFile(String UUid, long size, String type, String fileName, String buildTime, String address) {
        this.UUid = UUid;
        this.size = size;
        this.type = type;
        this.fileName = fileName;
        this.buildTime = buildTime;
        this.address = address;
    }

    public String getUUid() {
        return UUid;
    }

    public void setUUid(String UUid) {
        this.UUid = UUid;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
