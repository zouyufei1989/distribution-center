package com.money.custom.entity.response;

import com.money.custom.entity.dto.FileUploaded;
import com.money.framework.base.entity.ResponseBase;

public class UploadResponse extends ResponseBase {

    private String fileUrl;
    private FileUploaded fileUploaded;
    private Integer errno;

    public static UploadResponse success(FileUploaded fileUploaded) {
        UploadResponse response = new UploadResponse();
        response.errno = 0;
        response.fileUploaded = fileUploaded;
        response.fileUrl = fileUploaded.getUrl();
        response.setData(new String[]{fileUploaded.getUrl()});

        return response;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public FileUploaded getFileUploaded() {
        return fileUploaded;
    }

    public void setFileUploaded(FileUploaded fileUploaded) {
        this.fileUploaded = fileUploaded;
    }

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }
}
