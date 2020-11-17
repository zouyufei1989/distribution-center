package com.money.framework.util;

import com.alibaba.fastjson.JSON;
import com.money.custom.entity.request.HttpRequest;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Component
public class HttpUtils {

    final static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public String httpPostWithJson(HttpRequest request) {
        Assert.hasText(request.getUrl(), "url不可为空");
        Assert.notNull(request.getParam(), "param不可为null");

        String result = "";
        HttpPost httpPost = new HttpPost(request.getUrl());
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            BasicResponseHandler handler = new BasicResponseHandler();
            httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
            addHeaders(request.getHeaders(), httpPost);
            StringEntity entity = new StringEntity(JSON.toJSONString(request.getParam()), "utf-8");
            httpPost.setEntity(entity);
            result = httpClient.execute(httpPost, handler);
        } catch (Exception e) {
            logger.error("http 请求失败:" + result, e);
            result = e.getMessage();
        }
        return result;
    }

    public String httpPostForm(HttpRequest request) {
        Assert.hasText(request.getUrl(), "url不可为空");
        Assert.notNull(request.getParamMap(), "paramMap不可为null");

        String result = "";
        try (CloseableHttpClient httpClient = createSSLClientDefault()) {
            HttpPost httPost = new HttpPost(request.getUrl());

            //组织请求参数
            List<NameValuePair> paramList = new ArrayList<>();
            Map<String, String> params = request.getParamMap();
            if (params != null && params.size() > 0) {
                Set<String> keySet = params.keySet();
                for (String key : keySet) {
                    paramList.add(new BasicNameValuePair(key, params.get(key)));
                }
            }

            httPost.setEntity(new UrlEncodedFormEntity(paramList, "utf-8"));
            CloseableHttpResponse httpResponse = httpClient.execute(httPost);
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity, "utf-8");
        } catch (Exception e) {
            logger.error("http 请求失败:" + result, e);
            result = e.getMessage();
        }
        return result;
    }

    public String httpGet(HttpRequest request) {
        Assert.hasText(request.getUrl(), "url不可为空");

        String result = "";
        HttpGet httpGet = new HttpGet(request.getUrl());
        try (CloseableHttpClient httpClient = createSSLClientDefault()) {
            addHeaders(request.getHeaders(), httpGet);
            BasicResponseHandler handler = new BasicResponseHandler();
            result = httpClient.execute(httpGet, handler);
        } catch (Exception e) {
            logger.error("http 请求失败:" + result, e);
            result = e.getMessage();
        }
        return result;
    }

    void addHeaders(Map<String, String> headers, AbstractHttpMessage httpMessage) {
        if (Objects.isNull(headers)) {
            return;
        }

        for (Map.Entry<String, String> kv : headers.entrySet()) {
            httpMessage.addHeader(kv.getKey(), kv.getValue());
        }
    }

    public CloseableHttpClient createSSLClientDefault() {
        try {
            //使用 loadTrustMaterial() 方法实现一个信任策略，信任所有证书
            // 信任所有
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (chain, authType) -> true).build();
            //NoopHostnameVerifier类:  作为主机名验证工具，实质上关闭了主机名验证，它接受任何
            //有效的SSL会话并匹配到目标主机。
            HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
            logger.error(e.getMessage(), e);
        }
        return HttpClients.createDefault();

    }
}
