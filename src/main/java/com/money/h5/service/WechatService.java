package com.money.h5.service;

import com.gexin.fastjson.JSON;
import com.money.custom.entity.enums.HttpLogTypeEnum;
import com.money.custom.entity.enums.RedisKeyEnum;
import com.money.custom.entity.request.HttpRequest;
import com.money.framework.base.service.impl.BaseServiceImpl;
import com.money.framework.util.HttpUtils;
import com.money.framework.util.RedisUtils;
import com.money.framework.util.WechatDecryptDataUtil;
import com.money.h5.entity.dto.WechatPhoneResponse;
import com.money.h5.entity.request.TransWechatInfo2CustomerRequest;
import com.money.h5.entity.response.WechatLoginResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WechatService extends BaseServiceImpl {

    @Value("${wechat.login.url}")
    String LOGIN_URL;

    @Autowired
    HttpUtils httpUtils;
    @Autowired
    RedisUtils redisUtils;

    public WechatLoginResponse jscode2session(String jsCode) {
        String url = String.format(LOGIN_URL, jsCode);
        HttpRequest request = new HttpRequest(url, HttpLogTypeEnum.WECHAT_LOGIN);
        String result = httpUtils.httpGet(request);
        getLogger().info("get sessionkey [{}] by jscode {}", result, jsCode);
        return JSON.parseObject(result, WechatLoginResponse.class);
    }

    public WechatPhoneResponse gainPhone(TransWechatInfo2CustomerRequest request) {
        String sessionKey = redisUtils.getObject(RedisKeyEnum.WECHAT_SESSION_KEY.getName() + request.getPhone(), String.class);
        if (StringUtils.isEmpty(sessionKey)) {
            getLogger().info("no sessionKey in redis, try get it by jsCode [{}]", request.getJsCode());
            sessionKey = jscode2session(request.getJsCode()).getSession_key();
        }
        String srcData = WechatDecryptDataUtil.decryptData(request.getRawData(), sessionKey, request.getIv());
        getLogger().info("src phone data:" + srcData);
        return JSON.parseObject(srcData, WechatPhoneResponse.class);
    }
}
