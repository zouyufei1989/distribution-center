package com.money.h5.controller;

import com.money.framework.base.annotation.SkipUserLoginCheck;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * H5 相关请求处理
 * 去掉user 登录校验
 * 添加方法签名校验
 */
@SkipUserLoginCheck
@RequestMapping(value = "h5")
@Controller
public class H5Controller {

}
