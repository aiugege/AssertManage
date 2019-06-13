package com.zjhl.pad.moudle.entity.req;

import java.io.Serializable;

/**
 * @desc: UploadFileReq
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.req
 * @author: pluto
 * @create: 2018/5/8 11:58
 * @projectname: nnkj
 **/
public class UploadFileReq implements Serializable {
    private String extensionName;
    private String fileData;
    private String fileSize;

    public String getExtensionName() {
        return extensionName;
    }

    public void setExtensionName(String extensionName) {
        this.extensionName = extensionName;
    }

    public String getFileData() {
        return fileData;
    }

    public void setFileData(String fileData) {
        this.fileData = fileData;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }
}
