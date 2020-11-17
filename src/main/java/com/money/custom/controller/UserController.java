package com.money.custom.controller;

import com.money.custom.entity.User;
import com.money.custom.entity.request.ChangeStatusRequest;
import com.money.custom.entity.request.QueryUserRequest;
import com.money.custom.service.UserService;
import com.money.framework.base.annotation.VisitLogFlag;
import com.money.framework.base.entity.VisitLogTypeEnum;
import com.money.framework.base.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户管理
 */
@VisitLogFlag(module = "系统管理", resource = "用户管理")
@Controller
@RequestMapping(value = "/system/users/*")
public class UserController extends BaseController {
    final static String MODUAL_NAME = "/system_users";

    @Autowired
    UserService userService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        return MODUAL_NAME + "/index";
    }

    @RequestMapping(value = "update", method = RequestMethod.GET)
    public String saveOrUpdate() {
        return MODUAL_NAME + "/update";
    }

    /**
     * 查询系统用户列表
     *
     * @param request 列表查询分页参数
     * @return 页面视图和用户列表
     * @
     */
    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @RequestMapping(value = "list/search")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listSearch(QueryUserRequest request) {
        Map<String, Object> result = new HashMap<>();
        List<User> users = this.userService.selectSearchList(request);
        users.stream().forEach(user -> user.setPassword(StringUtils.EMPTY));
        result.put("rows", users);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 新建系统用户
     *
     * @param item 系统用户
     * @return
     * @
     */
    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> add(@RequestBody User item) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException {
        Map<String, Object> result = new HashMap<>();
        this.userService.add(item);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 编辑系统用户
     *
     * @param user 系统用户
     * @return
     * @
     */
    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> edit(@RequestBody User user) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException {
        Map<String, Object> result = new HashMap<>();
        this.userService.edit(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 根据id 查询系统用户
     *
     * @param id 系统用户ID
     * @return 页面视图和查询到的系统用户
     * @
     */
    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "findById", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> findById(String id) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", this.userService.findById(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 启用、禁用
     *
     * @param request 要操作的系统用户ID和状态
     * @return
     * @
     */
    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "changeStatus", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> edit(@RequestBody ChangeStatusRequest request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<String, Object> result = new HashMap<>();
        this.userService.changeStatus(request);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @RequestMapping(value = "changePwd", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> changePwd(@RequestBody User user) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException {
        Map<String, Object> result = new HashMap<>();
        this.userService.changePwd(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
