package com.money.custom.controller;

import com.money.custom.entity.Consts;
import com.money.custom.entity.enums.ConstsEnum;
import com.money.custom.service.LoginService;
import com.money.custom.service.RoleService;
import com.money.custom.service.UserService;
import com.money.custom.utils.VerifyCodeUtils;
import com.money.custom.utils.VerifyImage;
import com.money.custom.utils.VerifyImgUtils;
import com.money.framework.base.web.controller.BaseController;
import com.money.framework.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class LoginController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    RedisUtils redisUtils;

    @Value("${beian}")
    String BEIAN;


    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        request.setAttribute("beian", BEIAN);
        return "/login/index";
    }

    @RequestMapping(value = "/login")
    public String showLoginPage(HttpServletRequest request) {
        request.setAttribute("beian", BEIAN);
        return "/login/index";
    }

    @RequestMapping(value = "/timeOut", method = RequestMethod.GET)
    public String timeOut() {
        return "/timeOut";
    }

    @RequestMapping(value = "/notUniqueLogin", method = RequestMethod.GET)
    public String notUniqueLogin() {
        return "/notUniqueLogin";
    }

    @RequestMapping(value = "/forceQuite")
    public String forceQuite() {
        return "/login/forceQuite";
    }


    @RequestMapping(value = "/isTimeout")
    @ResponseBody
    public boolean isTimeout(HttpSession session) {
        return Objects.isNull(session.getAttribute(Consts.LOGIN_USER));
    }

    @RequestMapping(value = "/verifyCode")
    public void verifyCode(HttpServletResponse response, HttpSession session) throws IOException {
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        session.setAttribute(ConstsEnum.LOGIN_VERIFY_CODE.getName(), verifyCode.toLowerCase());
        // 生成图片
        int w = 100, h = 30;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    }

    @ResponseBody
    @RequestMapping(value = "/getVerifyImg")
    public VerifyImage getVerifyImg() throws IOException {
        Map<String, Object> result = new HashMap<>();
        return VerifyImgUtils.getVerifyImage("verifyImg/3b5d9eaa6b80cf4983b709a28662975c_r.jpg");
    }

}