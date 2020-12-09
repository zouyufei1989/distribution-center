package com.money.custom.service.impl;

import com.money.custom.dao.UserDao;
import com.money.custom.entity.User;
import com.money.custom.entity.enums.CommonStatusEnum;
import com.money.custom.entity.enums.ResponseCodeEnum;
import com.money.custom.entity.request.ChangeStatusRequest;
import com.money.custom.entity.request.QueryUserRequest;
import com.money.custom.service.UserService;
import com.money.framework.base.exception.PandabusSpecException;
import com.money.framework.base.service.impl.BaseServiceImpl;
import com.money.framework.util.RSAUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Value("${RSA.PRIVATE.KEY}")
    String RSA_PRI_KEY;


    @Override
    public List<User> selectSearchList(QueryUserRequest request) {
        List<User> users = userDao.selectSearchList(request);
        return pickupFamilyMembers(users, request.getGroupId());
    }


    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public void add(User item) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException {
        checkPassword(item.getPassword());

        QueryUserRequest queryUserRequest = new QueryUserRequest();
        queryUserRequest.setUsername(item.getUsername());
        int listCount = userDao.selectSearchList(queryUserRequest).size();
        Assert.isTrue(listCount == 0, "用户名已存在");

        item.setStatus(CommonStatusEnum.ENABLE.getValue());
        userDao.add(item);
    }

    @Override
    public void edit(User item) {
        userDao.edit(item);
    }

    @Override
    public void changePwd(User item) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException {
        checkPassword(item.getPassword());
        userDao.edit(item);
    }

    @Override
    public void changeStatus(ChangeStatusRequest request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        userDao.changeStatus(request);
    }

    void checkPassword(String password) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException, InvalidKeyException, IOException {
        if (StringUtils.isNotEmpty(password)) {
            String srcPwd = RSAUtils.privateDecrypt(password, RSA_PRI_KEY);
            Pattern pattern = Pattern.compile("(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[^a-zA-Z0-9]).{8,30}");
            if (!pattern.matcher(srcPwd).matches()) {
                throw  PandabusSpecException.businessError(ResponseCodeEnum.WEEK_PASSWORD);
            }
        }
    }

}
