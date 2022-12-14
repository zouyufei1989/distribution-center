package com.money.custom.controller;

import com.money.custom.entity.dto.FileUploaded;
import com.money.custom.entity.enums.*;
import com.money.custom.entity.response.UploadResponse;
import com.money.custom.service.UtilsService;
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
    @RequestMapping(value = "selectReservationStatus")
    public GridResponseBase selectReservationStatus() {
        return new GridResponseBase(EnumUtils.getEnumEntriesVN(ReservationStatusEnum.class));
    }

    @ResponseBody
    @RequestMapping(value = "selectGoodsType")
    public GridResponseBase selectGoodsType() {
        return new GridResponseBase(EnumUtils.getEnumEntriesVN(GoodsTypeEnum.class));
    }

    @ResponseBody
    @RequestMapping(value = "selectFirstCashback")
    public GridResponseBase selectFirstCashback() {
        return new GridResponseBase(EnumUtils.getEnumEntriesVN(FirstCashBackTypeEnum.class));
    }

    @ResponseBody
    @RequestMapping(value = "selectActivityScope")
    public GridResponseBase selectActivityScope() {
        return new GridResponseBase(EnumUtils.getEnumEntriesVN(ActivityScopeEnum.class));
    }

    @ResponseBody
    @RequestMapping(value = "selectEmployeeStatus")
    public GridResponseBase selectEmployeeStatus() {
        return new GridResponseBase(EnumUtils.getEnumEntriesVN(EmployeeStatusEnum.class));
    }

    @ResponseBody
    @RequestMapping(value = "selectEmployeeLevel")
    public GridResponseBase selectEmployeeLevel() {
        return new GridResponseBase(EnumUtils.getEnumEntriesVN(EmployeeLevelEnum.class));
    }

    @ResponseBody
    @RequestMapping(value = "selectGender")
    public GridResponseBase selectGender() {
        return new GridResponseBase(EnumUtils.getEnumEntriesVN(GenderEnum.class));
    }

    @ResponseBody
    @RequestMapping(value = "uploadFileToUpyun", method = RequestMethod.POST)
    public UploadResponse uploadFileToUpyun(@RequestParam("file") MultipartFile file, Integer width, Integer height) throws IOException {
        FileUploaded fileUploaded = uploadUtils.saveFileToUpyun(file, width, height);
        return UploadResponse.success(fileUploaded);
    }

    @ResponseBody
    @RequestMapping(value = "getUpyunParams", method = RequestMethod.POST)
    public ResponseBase getUpyunParams(String fileName) throws Exception {
        return UploadResponse.success(upYunUtil.getParams4FormUpload(fileName));
    }

    @RequestMapping(value = "/getFile")
    public void getFile(String fileName, HttpServletResponse response) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            getLogger().error("???????????????:" + fileName);
            throw new FileNotFoundException();
        }

        try (FileInputStream fis = new FileInputStream(file); OutputStream os = response.getOutputStream();) {
            byte[] buffer = new byte[1024 * 1024];
            //????????????ContentType?????????????????????????????????????????????????????????
            response.setContentType("multipart/form-data");
            //??????????????????????????????????????????????????????????????????????????????????????????????????????????????????
            response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
            int b = 0;
            while (b != -1) {
                b = fis.read(buffer);
                //???????????????(out)???
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
