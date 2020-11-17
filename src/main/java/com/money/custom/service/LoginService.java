package com.money.custom.service;

import com.money.custom.entity.Resource;
import com.money.custom.entity.User;
import com.money.custom.entity.request.LoginRequest;
import com.money.framework.base.service.BaseService;

import javax.servlet.http.HttpSession;
import java.util.List;


public interface LoginService extends BaseService {

    User findUserByName(String name) ;

    List<Resource> getUserResources(User user) ;

    void addLog(String machineName, String ipAddress, User user, int systemType) ;

    User login(LoginRequest request, HttpSession session) throws Exception;

}
