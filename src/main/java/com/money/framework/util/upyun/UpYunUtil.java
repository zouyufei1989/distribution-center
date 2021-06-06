package com.money.framework.util.upyun;

import com.alibaba.fastjson.JSONObject;
import com.gexin.fastjson.JSON;
import com.money.custom.entity.enums.ResponseCodeEnum;
import com.money.framework.base.exception.CustomSpecException;
import com.money.framework.util.MD5Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class UpYunUtil {

    @Value("${web.image.upyun.bucket.name}")
    private String BUCKET_NAME;
    @Value("${web.image.upyun.user.name}")
    private String USER_NAME;
    @Value("${web.image.upyun.user.pwd}")
    private String USER_PWD;
    @Value("${web.image.upyun.form.pwd}")
    private String FORM_PWD;
    @Value("${web.image.upyun.large.catalogue}")
    private String LARGE_CATALOGUE;
    @Value("${web.image.upyun.thumbnail.catalogue}")
    private String THUMBNAIL_CATALOGUE;
    @Value("${web.image.upyun.middle.catalogue}")
    private String MIDDLE_CATALOGUE;
    @Value("${web.image.upyun.host}")
    private String URL;
    @Value("${project.name}")
    private String UPLOAD_FOLDER;

    private static final String PATH_SEPARATOR = "/";

    /**
     * 根目录
     */
    private static final String DIR_ROOT = "/";

    private static UpYun upyun = null;


    public Map<String, Object> getParams4FormUpload(String fileName) throws Exception {
        Assert.hasText(fileName, "文件名不可为空");
        String filePath = DIR_ROOT + UPLOAD_FOLDER + PATH_SEPARATOR + fileName;

        JSONObject options = new JSONObject();
        options.put("bucket", BUCKET_NAME);
        options.put("save-key", filePath);
        options.put("expiration", String.valueOf(System.currentTimeMillis() / 1000 + 30 * 60));
        String policy = Base64Utils.encodeToString(JSON.toJSONString(options).getBytes("UTF-8"));
        String signature = MD5Utils.getMD5(policy + "&" + FORM_PWD);

        Map<String, Object> params = new HashMap<>();
        params.put("url", "http://v0.api.upyun.com/" + BUCKET_NAME);
        params.put("policy", policy);
        params.put("signature", signature);
        params.put("prefix", URL);

        return params;
    }

    public String saveFile(File picFile) throws IOException {
        return saveFile(picFile, null);
    }

    public String saveFileCompress(File picFile, Integer percent) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("x-gmkerl-thumb", "/quality/" + percent);
        return saveFile(picFile, params);
    }

    public String saveFile(File picFile, Map<String, String> params) throws IOException {
        // 初始化空间
        upyun = new UpYun(BUCKET_NAME, USER_NAME, USER_PWD);
        String filePath = DIR_ROOT + UPLOAD_FOLDER + PATH_SEPARATOR + picFile.getName();
        upyun.setContentMD5(UpYun.md5(picFile));

        boolean result = true;
        if (Objects.isNull(params)) {
            result = upyun.writeFile(filePath, picFile, true);
        } else {
            result = upyun.writeFile(filePath, picFile, true, params);
        }

        if (result) {
            return URL + filePath;
        } else {
            throw CustomSpecException.businessError(ResponseCodeEnum.UPLOAD_FAIL);
        }
    }

    public String saveFile(File picFile, Map<String, String> params, Integer width, Integer height) throws IOException {
        // 初始化空间
        upyun = new UpYun(BUCKET_NAME, USER_NAME, USER_PWD);
        String filePath = DIR_ROOT + UPLOAD_FOLDER + PATH_SEPARATOR + picFile.getName();
        upyun.setContentMD5(UpYun.md5(picFile));

        boolean result = true;
        if (Objects.isNull(params)) {
            result = upyun.writeFile(filePath, picFile, true);
        } else {
            result = upyun.writeFile(filePath, picFile, true, params);
        }

        if (result) {
            if (Objects.nonNull(width) && Objects.nonNull(height)) {
                return URL + filePath + "!/both/" + width + "x" + height;
            }
            if (Objects.nonNull(width)) {
                return URL + filePath + "!/fw/" + width;
            }
            if (Objects.nonNull(height)) {
                return URL + filePath + "!/fh/" + height;
            }

            return URL + filePath;
        } else {
            throw CustomSpecException.businessError(ResponseCodeEnum.UPLOAD_FAIL);
        }

    }

    public boolean deleteFile(String filePath) throws IOException {
        // 初始化空间
        upyun = new UpYun(BUCKET_NAME, USER_NAME, USER_PWD);
        if (upyun.deleteFile(getRelativeUpYunPath(filePath))) {
            return true;
        } else {
            throw new IOException();
        }
    }

    public String getRelativeUpYunPath(String filePath) {

        if (filePath.length() > 0 && filePath.indexOf(URL) >= 0) {
            return filePath.substring(URL.length());
        }
        return filePath;
    }

    public boolean deleteFolder(String filePath) throws IOException {
        // 初始化空间
        upyun = new UpYun(BUCKET_NAME, USER_NAME, USER_PWD);
        String path = DIR_ROOT + filePath;
        if (upyun.rmDir(path)) {
            return true;
        } else {
            throw new IOException();
        }
    }

    public String writePicIcon(File picFile) throws IOException {
        // 初始化空间
        upyun = new UpYun(BUCKET_NAME, USER_NAME, USER_PWD);
        String filePath = DIR_ROOT + UPLOAD_FOLDER + picFile.getName();

        // 上传文件，并自动创建父级目录（最多10级）
        boolean result = upyun.writeFileIcon(filePath, picFile, true);

        if (result) {
            return URL + filePath;
        } else {
            throw new IOException();
        }

    }

}
