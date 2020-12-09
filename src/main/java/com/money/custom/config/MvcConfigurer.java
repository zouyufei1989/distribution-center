package com.money.custom.config;

import com.google.common.collect.Sets;
import com.money.custom.convertor.MultipartFileConvertor;
import com.money.custom.interceptor.AuthInterceptor;
import com.money.custom.interceptor.VisitLogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Configuration
public class MvcConfigurer implements WebMvcConfigurer {

    @Autowired
    AuthInterceptor authInterceptor;
    @Autowired
    VisitLogInterceptor visitLogInterceptor;
    @Autowired
    MultipartFileConvertor multipartFileConvertor;

    final static String PAGE_URL_SPL_METHOD_URL = "->";

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/script/**", "/resource/**", "/css/**");
        registry.addInterceptor(visitLogInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/script/**", "/resource/**", "/css/**");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(multipartFileConvertor);
    }

    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(5242880000L);
        return commonsMultipartResolver;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/h5/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("*")
                .maxAge(30 * 1200);
        //option 探测请求的有效期，有效期内不会重复发送探测请求
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        Map<String, Set<String>> urlPattMap = new HashMap<>();
        urlPattMap.put("/statistics/", Sets.newHashSet("visitLogStatistics"));
        urlPattMap.put("/exceptionLog/", Sets.newHashSet("index"));
        urlPattMap.put("/group/", Sets.newHashSet("index", "update"));
        urlPattMap.put("/banner/", Sets.newHashSet("index"));
        urlPattMap.put("/goodsTag/", Sets.newHashSet("index"));
        urlPattMap.put("/goods/", Sets.newHashSet("index"));
        urlPattMap.put("/bonusPlan/", Sets.newHashSet("index"));
        urlPattMap.put("/customer/", Sets.newHashSet("index"));
        urlPattMap.put("/keyValue/", Sets.newHashSet("index", "update"));
        urlPattMap.put("/scheduleConfig/", Sets.newHashSet("index", "update"));
        urlPattMap.put("/history/", Sets.newHashSet("index"));
        urlPattMap.put("/visitLog/", Sets.newHashSet("index"));

        for (Map.Entry<String, Set<String>> kv : urlPattMap.entrySet()) {
            String controllerMapping = kv.getKey();
            kv.getValue().forEach(methodMapping -> {
                if (methodMapping.contains(PAGE_URL_SPL_METHOD_URL)) {
                    String[] mappings = methodMapping.split(PAGE_URL_SPL_METHOD_URL);
                    registry.addViewController(controllerMapping + mappings[0]).setViewName(controllerMapping + mappings[1]);
                } else {
                    registry.addViewController(controllerMapping + methodMapping).setViewName(controllerMapping + methodMapping);
                }

            });
        }
    }

}