package com.money.framework.util;

import com.google.common.collect.Sets;
import com.money.custom.entity.dto.FileUploaded;
import com.money.framework.base.entity.TempFile;
import com.money.framework.util.upyun.UpYunUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashSet;

@Component
public class UploadUtils {

    public final static Logger logger = org.slf4j.LoggerFactory.getLogger(UploadUtils.class);

    @Value("${server.upload.url}")
    String serverIp;
    @Value("${server.upload.port}")
    String serverPort;
    @Value("${server.servlet.context-path}")
    String contextPath;

    @Value("${upload.folder}")
    String uploadFolder;

    @Value("${download.file}")
    String getFileUrl;
    @Value("${upload.balance.url}")
    String upload4Balance;

    @Autowired
    UpYunUtil upYunUtil;

    private UploadUtils() {}

    public FileUploaded saveFileToUpyun(MultipartFile file) throws IOException {
        FileUploaded fileUploaded = new FileUploaded();

        File tmpFile = saveTmpFile(file);
        String url  = upYunUtil.saveFile(tmpFile);
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

    public TempFile getFileFromIp(String ip, String fileName) throws IllegalStateException, IOException {
        File targetFolder = new File(System.getProperty("java.io.tmpdir"), "pathUpload");
        if (!targetFolder.exists()) {
            targetFolder.mkdirs();
        }
        File file = new File(targetFolder.getPath() + "/" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(fileName));
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpget = new HttpGet(String.format("http://%s:%s/geely/utils/getFile.json?fileName=%s", ip, serverPort, fileName));
            httpget.setConfig(RequestConfig.custom().setConnectTimeout(5000).build());
            try (CloseableHttpResponse response = httpclient.execute(httpget)) {
                org.apache.http.HttpEntity entity = response.getEntity();
                try (InputStream is = entity.getContent(); //
                     OutputStream os = new FileOutputStream(file)) {
                    StreamUtils.copy(is, os);
                }
            }
        }
        return new TempFile(file);
    }

    public String saveFile(MultipartFile multipartFile, String fileName, boolean balance) throws IOException {
        File folder = new File(uploadFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String fileFullName = uploadFolder + "/" + fileName;
        File file = new File(fileFullName);
        multipartFile.transferTo(file);

        if (balance) {
            loadbalanceFile(file, fileName);
        }

        return String.format("%s?fileName=%s", getFileUrl, fileFullName);
    }

    public FileUploaded saveFile(MultipartFile multipartFile, boolean balance) throws IOException {
        String fileName = generateFileName(multipartFile);
        String url = saveFile(multipartFile, fileName, balance);

        FileUploaded fileUploaded = new FileUploaded();
        fileUploaded.setUrl(url);
        fileUploaded.setUploadDateTime(new Date());
        fileUploaded.setSrcFileName(multipartFile.getOriginalFilename());

        return fileUploaded;
    }

    private String generateFileName(MultipartFile file) {
        return System.currentTimeMillis() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
    }


    void loadbalanceFile(File file, String fileName) throws IOException {
        InetAddress addr = InetAddress.getLocalHost();
        String[] ips = serverIp.split(";");
        HashSet<String> ipSet = Sets.newHashSet(ips);
        ipSet.remove(addr.getHostAddress());

        for (String ip : ipSet) {
            String url = String.format(upload4Balance, ip, serverPort);
            logger.info("balance file to :" + url);

            HttpPost httpPost = new HttpPost(url);
            CloseableHttpClient client = HttpClientBuilder.create().build();
            CloseableHttpResponse resp = null;
            String respondBody = null;
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(200000000).build();
            httpPost.setConfig(requestConfig);

            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            multipartEntityBuilder.addBinaryBody("file", file);
            multipartEntityBuilder.addTextBody("fileName", fileName);
            HttpEntity httpEntity = multipartEntityBuilder.build();
            httpPost.setEntity(httpEntity);
            resp = client.execute(httpPost);
            respondBody = EntityUtils.toString(resp.getEntity());
            logger.info("balance file result:" + respondBody);
        }
    }
}
