package com.money.custom.service.impl;

import com.money.custom.dao.SecurityDao;
import com.money.custom.entity.Resource;
import com.money.custom.entity.User;
import com.money.custom.entity.enums.ConstsEnum;
import com.money.custom.entity.request.LoginRequest;
import com.money.custom.entity.request.QueryUserRequest;
import com.money.custom.service.LoginService;
import com.money.custom.service.UserService;
import com.money.framework.base.service.impl.BaseServiceImpl;
import com.money.framework.util.RSAUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {

    @Autowired
    private UserService userService;
    @Autowired
    private SecurityDao securityDao;
    @Value("${RSA.PRIVATE.KEY}")
    String RSA_PRI_KEY;

    @Override
    public User findUserByName(String name) {
        User user = new User();
        user.setUsername(name);
        QueryUserRequest request = new QueryUserRequest();
        request.setUser(user);

        User result = userService.selectSearchList(request).stream().filter(i -> StringUtils.equals(name, i.getUsername())).findFirst().orElse(null);
        return result;
    }

    @Override
    public List<Resource> getUserResources(User user) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("value", user.getId());
        List<Resource> res = securityDao.selectList("getUserResources", paramMap);
        return res;
    }

    @Override
    public void addLog(String machineName, String ipAddress, User user, int systemType) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", user.getId());
        paramMap.put("machineName", machineName);
        paramMap.put("ipAddress", ipAddress);
        securityDao.insert("insertLog", paramMap);
    }

    @Override
    public User login(LoginRequest request, HttpSession session) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException {
        String verifyCode = request.getVerifyCode();
        if (Objects.isNull(session.getAttribute(ConstsEnum.LOGIN_VERIFY_CODE.getName()))) {
            getLogger().warn("验证码为空");
            return null;
        }
        String srcCode = session.getAttribute(ConstsEnum.LOGIN_VERIFY_CODE.getName()).toString();
        if (!StringUtils.equals(verifyCode.toLowerCase(), srcCode.toLowerCase())) {
            getLogger().warn("验证码错误:{} - src:{}", verifyCode, srcCode);
            return null;
        }

        User user = findUserByName(request.getUserName());
        if (Objects.isNull(user)) {
            getLogger().warn("用户不存在");
            return null;
        }

        getLogger().info("web:" + request.getPassword());
        getLogger().info("user:" + user.getPassword());
        try {
            String pwd = RSAUtils.privateDecrypt(request.getPassword(), RSA_PRI_KEY);
            String userPwd = RSAUtils.privateDecrypt(user.getPassword(), RSA_PRI_KEY);
            if (!StringUtils.equals(pwd, userPwd)) {
                getLogger().warn("密码错误");
                return null;
            }
        } catch (Exception ex) {
            getLogger().error("密码解析错误", ex);
            return null;
        }

        return user;
    }
}
