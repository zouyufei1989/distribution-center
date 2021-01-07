package com.money.custom.controller;

import com.money.custom.dao.UtilsDao;
import com.money.custom.entity.enums.ResponseCodeEnum;
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

}
