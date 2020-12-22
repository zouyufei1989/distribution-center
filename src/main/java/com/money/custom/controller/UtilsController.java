package com.money.custom.controller;

import com.money.custom.entity.dto.FileUploaded;
import com.money.custom.entity.enums.*;
import com.money.custom.entity.response.UploadResponse;
import com.money.custom.service.UtilsService;
import com.money.framework.base.annotation.SkipUserLoginCheck;
import com.money.framework.base.entity.GridResponseBase;
import com.money.framework.base.entity.ResponseBase;
import com.money.framework.base.web.controller.BaseController;
import com.money.framework.util.DateUtils;
import com.money.framework.util.EnumUtils;
import com.money.framework.util.UploadUtils;
import com.money.framework.util.upyun.UpYunUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

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
    @RequestMapping(value = "sleep")
    public String sleep(int sec) throws InterruptedException {
        Thread.sleep(sec * 1000);
        return "sleep " + sec;
    }

    @ResponseBody
    @RequestMapping(value = "selectOpenCities")
    public GridResponseBase selectOpenCities() {
        return new GridResponseBase(this.utilsService.selectOpenCities());
    }

    @ResponseBody
    @RequestMapping(value = "selectRoles")
    public GridResponseBase selectRoles() {
        return new GridResponseBase(this.utilsService.selectRoles());
    }

    @ResponseBody
    @RequestMapping(value = "selectStatus")
    public GridResponseBase selectStatus() {
        return new GridResponseBase(EnumUtils.getEnumEntriesVN(CommonStatusEnum.class));
    }

    @ResponseBody
    @RequestMapping(value = "generateBonusSerialNumber")
    public ResponseBase generateBonusSerialNumber() {
        return ResponseBase.success(utilsService.generateSerialNumber(SerialNumberEnum.BP));
    }

    @ResponseBody
    @RequestMapping(value = "generateCustomerSerialNumber")
    public ResponseBase generateCustomerSerialNumber() {
        return ResponseBase.success(utilsService.generateSerialNumber(SerialNumberEnum.CS));
    }

    @ResponseBody
    @RequestMapping(value = "generateActivitySerialNumber")
    public ResponseBase generateActivitySerialNumber() {
        return ResponseBase.success(utilsService.generateSerialNumber(SerialNumberEnum.GA));
    }

    @ResponseBody
    @RequestMapping(value = "selectGoodsShowPrice")
    public GridResponseBase selectGoodsShowPrice() {
        return new GridResponseBase(EnumUtils.getEnumEntriesVN(GoodsShowPriceEnum.class));
    }

    @ResponseBody
    @RequestMapping(value = "selectCustomerType")
    public GridResponseBase selectCustomerType() {
        return new GridResponseBase(EnumUtils.getEnumEntriesVN(CustomerTypeEnum.class));
    }

    @ResponseBody
    @RequestMapping(value = "selectPayType")
    public GridResponseBase selectPayType() {
        return new GridResponseBase(EnumUtils.getEnumEntriesVN(PayTypeEnum.class));
    }

    @ResponseBody
    @RequestMapping(value = "selectGoodsType")
    public GridResponseBase selectGoodsType() {
        return new GridResponseBase(EnumUtils.getEnumEntriesVN(GoodsTypeEnum.class));
    }

    @ResponseBody
    @RequestMapping(value = "selectActivityScope")
    public GridResponseBase selectActivityScope() {
        return new GridResponseBase(EnumUtils.getEnumEntriesVN(ActivityScopeEnum.class));
    }

    @ResponseBody
    @RequestMapping(value = "uploadFileToUpyun", method = RequestMethod.POST)
    public UploadResponse uploadFileToUpyun(@RequestParam("file") MultipartFile file) throws IOException {
        FileUploaded fileUploaded = uploadUtils.saveFileToUpyun(file);
        return UploadResponse.success(fileUploaded);
    }

    @SkipUserLoginCheck
    @RequestMapping(value = "/getFile")
    public void getFile(String fileName, HttpServletResponse response) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            getLogger().error("文件不存在:" + fileName);
            throw new FileNotFoundException();
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
    public ResponseBase convertWeekByDate() {
        return ResponseBase.success(DateUtils.convertWeekByDate(new Date()));
    }

    @ResponseBody
    @RequestMapping(value = "convertMonthByDate")
    public ResponseBase convertMonthByDate() {
        return ResponseBase.success(DateUtils.convertMonthByDate(new Date()));
    }

    @ResponseBody
    @RequestMapping(value = "convertYearByDate")
    public ResponseBase convertYearByDate() {
        return ResponseBase.success(DateUtils.convertYearByDate(new Date()));
    }
}
