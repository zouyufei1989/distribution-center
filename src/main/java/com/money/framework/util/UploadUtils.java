package com.money.framework.util;

import com.money.custom.entity.dto.FileUploaded;
import com.money.framework.util.upyun.UpYunUtil;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Component
public class UploadUtils {

    public final static Logger logger = org.slf4j.LoggerFactory.getLogger(UploadUtils.class);

    @Autowired
    UpYunUtil upYunUtil;

    private UploadUtils() {}

    public FileUploaded saveFileToUpyun(MultipartFile file, Integer height, Integer width) throws IOException {
        FileUploaded fileUploaded = new FileUploaded();

        File tmpFile = saveTmpFile(file);
        String url = upYunUtil.saveFile(tmpFile, null, height, width);
        fileUploaded.setUrl(url);
        fileUploaded.setUploadDateTime(new Date());
        fileUploaded.setSrcFileName(file.getOriginalFilename());

        return fileUploaded;
    }

    public String saveFileToUpyunCompress(MultipartFile file, Integer percent) throws IOException {
        File tmpFile = saveTmpFile(file);
        return upYunUtil.saveFileCompress(tmpFile, percent);
    }

    private File saveTmpFile(MultipartFile file) throws IllegalStateException, IOException {
        File targetFolder = new File(System.getProperty("java.io.tmpdir"), "pathUpload");
        if (!targetFolder.exists()) {
            targetFolder.mkdirs();
        }
        File picFile = new File(targetFolder.getPath() + "/" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(file.getOriginalFilename()));
        file.transferTo(picFile);
        return picFile;
    }

    private String generateFileName(MultipartFile file) {
        return System.currentTimeMillis() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
    }

}
