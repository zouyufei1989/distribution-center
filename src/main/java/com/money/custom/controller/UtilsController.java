package com.money.custom.controller;

import com.money.custom.entity.dto.FileUploaded;
import com.money.custom.entity.enums.CommonStatusEnum;
import com.money.custom.entity.enums.GoodsShowPriceEnum;
import com.money.custom.entity.enums.SerialNumberEnum;
import com.money.custom.entity.request.QueryGoodsTagRequest;
import com.money.custom.entity.request.QueryGroupRequest;
import com.money.custom.service.UtilsService;
import com.money.framework.base.annotation.SkipUserLoginCheck;
import com.money.framework.base.exception.PandabusSpecException;
import com.money.framework.base.web.controller.BaseController;
import com.money.framework.util.DateUtils;
import com.money.framework.util.EnumUtils;
import com.money.framework.util.UploadUtils;
import com.money.framework.util.upyun.UpYunUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/utils/*")
public class UtilsController extends BaseController {

    @Autowired
    private UtilsService utilsService;
    @Autowired
    UpYunUtil upYunUtil;
    @Autowired
    UploadUtils uploadUtils;
    @Value("${upload.folder}")
    String uploadFolder;

    @ResponseBody
    @RequestMapping(value = "selectOpenCities")
    public ResponseEntity<Map<String, Object>> selectOpenCities() {
        Map<String, Object> result = new HashMap<>();
        result.put("rows", this.utilsService.selectOpenCities());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "selectRoles")
    public ResponseEntity<Map<String, Object>> selectRoles() {
        Map<String, Object> result = new HashMap<>();
        result.put("rows", this.utilsService.selectRoles());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "selectStatus")
    public ResponseEntity<Map<String, Object>> selectStatus() {
        Map<String, Object> result = new HashMap<>();
        result.put("rows", EnumUtils.getEnumEntriesVN(CommonStatusEnum.class));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "generateBonusSerialNumber")
    public ResponseEntity<Map<String, Object>> generateBonusSerialNumber() {
        Map<String, Object> result = new HashMap<>();
        result.put("serialNumber", utilsService.generateSerialNumber(SerialNumberEnum.BONUS_PLAN));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "selectGoodsShowPrice")
    public ResponseEntity<Map<String, Object>> selectGoodsShowPrice() {
        Map<String, Object> result = new HashMap<>();
        result.put("rows", EnumUtils.getEnumEntriesVN(GoodsShowPriceEnum.class));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "uploadFileToUpyun", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> uploadFileToUpyun(@RequestParam("file") MultipartFile file) throws IOException {
        Map<String, Object> result = new HashMap<>();
        FileUploaded fileUploaded = uploadUtils.saveFileToUpyun(file);
        result.put("success", true);
        result.put("fileUrl", fileUploaded.getUrl());
        result.put("fileUploaded", fileUploaded);
        result.put("message", "上传成功！");

        result.put("errno", 0);
        result.put("data", new String[]{fileUploaded.getUrl()});

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "uploadFileToUpyunCompress", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> uploadFileToUpyunCompress(@RequestParam("uploadFile") MultipartFile uploadFile, Integer percent) throws IOException {
        Map<String, Object> result = new HashMap<>();
        String url = uploadUtils.saveFileToUpyunCompress(uploadFile, percent);
        result.put("success", true);
        result.put("fileUrl", url);
        result.put("message", "上传成功！");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "uploadFile", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        Map<String, Object> result = new HashMap<>();
        FileUploaded fileUploaded = uploadUtils.saveFile(file, true);
        result.put("success", true);
        result.put("imgUrl", fileUploaded.getUrl());
        result.put("fileUploaded", fileUploaded);
        result.put("message", "上传成功！");

        result.put("errno", 0);
        result.put("data", new String[]{fileUploaded.getUrl()});
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "uploadFile4Balance")
    public ResponseEntity<Map<String, Object>> uploadFile4Balance(@RequestParam("file") MultipartFile file, String fileName) throws Exception {
        Map<String, Object> result = new HashMap<>();
        String url = uploadUtils.saveFile(file, fileName, false);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @SkipUserLoginCheck
    @RequestMapping(value = "/getFile")
    public void getFile(String fileName, HttpServletResponse response) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            getLogger().error("文件不存在:" + fileName);
            throw new PandabusSpecException("file not found.");
        }

        try (FileInputStream fis = new FileInputStream(file); OutputStream os = response.getOutputStream();) {
            byte[] buffer = new byte[1024 * 1024];
            //设置文件ContentType类型，这样设置，会自动判断下载文件类型
            response.setContentType("multipart/form-data");
            //设置文件头：最后一个参数是设置下载文件名（设置编码格式防止下载的文件名乱码）
            response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
            int b = 0;
            while (b != -1) {
                b = fis.read(buffer);
                //写到输出流(out)中
                os.write(buffer, 0, b);
            }
        }
    }


    @ResponseBody
    @RequestMapping(value = "convertWeekByDate")
    public ResponseEntity<Map<String, String>> convertWeekByDate() {
        return new ResponseEntity<>(DateUtils.convertWeekByDate(new Date()), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "convertMonthByDate")
    public ResponseEntity<Map<String, String>> convertMonthByDate() {
        return new ResponseEntity<>(DateUtils.convertMonthByDate(new Date()), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "convertYearByDate")
    public ResponseEntity<Map<String, String>> convertYearByDate() {
        return new ResponseEntity<>(DateUtils.convertYearByDate(new Date()), HttpStatus.OK);
    }
}
