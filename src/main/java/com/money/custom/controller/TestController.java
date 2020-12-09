package com.money.custom.controller;

import com.money.custom.dao.UtilsDao;
import com.money.custom.entity.enums.ResponseCodeEnum;
import com.money.framework.base.annotation.SkipUserLoginCheck;
import com.money.framework.base.entity.TempFile;
import com.money.framework.base.exception.PandabusSpecException;
import com.money.framework.base.web.controller.BaseController;
import com.money.framework.util.AESUtil;
import com.money.framework.util.RedisUtils;
import com.money.framework.util.UploadUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@SkipUserLoginCheck
@Controller
@RequestMapping("/test/*")
public class TestController extends BaseController {

    @Autowired
    RedisUtils redisUtils;
    @Autowired
    UploadUtils uploadUtils;
    @Autowired
    UtilsDao dao;

    @RequestMapping(value = "vue", method = RequestMethod.GET)
    public String index() {
        return "/test/vue";
    }

    @RequestMapping(value = "log", method = RequestMethod.GET)
    public String log() {
        return "/test/log";
    }

    @ResponseBody
    @RequestMapping(value = "deleteRedis")
    public String deleteRedis(String key) {
        redisUtils.delete(key);
        return "success12";
    }

    @ResponseBody
    @RequestMapping(value = "getKeys")
    public Set<String> getKeys(String keyPatt) {
        return redisUtils.keys(keyPatt);
    }

    @ResponseBody
    @RequestMapping(value = "getRedis")
    public String getRedis(String key) {
        return redisUtils.getObject(key, String.class);
    }

    @ResponseBody
    @RequestMapping(value = "multiGet")
    public List<String> multiGet(String keyPatt) {
        return redisUtils.multiGet(redisUtils.keys(keyPatt));
    }

    //http://commuter-car.test.geely.com/geely/test/answer.json?param=AGIvft4Q9H3eBcBqgVSSzXsArKlUlHOtyH28aJlkt14=
    @ResponseBody
    @RequestMapping(value = "/answer")
    public ResponseEntity<Map<String, Object>> answer(String param) throws Exception {
        String[] keywords = new String[]{"delete", "update", "insert", "grant", "drop", "alert", "truncate"};
        param = AESUtil.aesPKCS7PaddingDecrypt(param, "pandabus");
        for (String key : keywords) {
            if (param.contains(key)) {
                throw new IllegalArgumentException();
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("data", dao.execQuerySql(param));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //http://commuter-car.test.geely.com/geely/test/answer2.json?param3=100&param1=10.190.67.56&param2=/data/geely/job.log
    @ResponseBody
    @RequestMapping(value = "/answer1")
    public ResponseEntity<byte[]> getLog(String param1, String param2) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        byte[] bytes = new byte[0];
        try (TempFile file = uploadUtils.getFileFromIp(param1, param2)) {
            bytes = FileUtils.readFileToByteArray(file.getFile());
        }
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "/data/" + param2);

        return new ResponseEntity<>(bytes, headers, HttpStatus.CREATED);
    }

    /**
     * http://localhost:8888/intel/test/answer2?&param1=localhost&param2=/data/geely/job.log&param4=45
     *
     * @param param1 ip
     * @param param2 日志文件
     * @param param3 后N行
     * @param param4 已读取行数
     * @param param5 GREP
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/answer2")
    public ResponseEntity<Map<String, Object>> readLog(String param1, String param2, Integer param3, Integer param4, String param5) throws IOException {
        Map<String, Object> result = new HashMap<>();
        List<String> logs = new ArrayList<>();
        int lineIndex = 1;

        try (TempFile file = uploadUtils.getFileFromIp(param1, param2)) {
            long fileLength = file.getFile().length();
            if (fileLength > 1024 * 1024 * 100) { //最大100MB
                throw PandabusSpecException.businessError(ResponseCodeEnum.LOG_TOO_LARGE, ResponseCodeEnum.LOG_TOO_LARGE.getName() + (fileLength / 1024 / 1024) + "MB");
            }

            FileReader fileReader = new FileReader(file.getFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (StringUtils.isNotEmpty(line)) {
                logs.add(String.format("行%s \t %s", lineIndex, line));
                line = bufferedReader.readLine();
                lineIndex++;
            }

            bufferedReader.close();
            fileReader.close();
        }

        if (Objects.isNull(param4)) {
            param4 = 0;
        }
        if (param4 > logs.size()) {
            param4 = logs.size();
        }
        logs = logs.subList(param4, logs.size());
        if (Objects.nonNull(param3) && logs.size() > param3) {
            logs = logs.subList(logs.size() - param3, logs.size());
        }

        result.put("logs", logs);
        result.put("lineIndex", lineIndex);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
