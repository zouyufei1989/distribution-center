package com.money.custom.service;

import com.money.custom.entity.User;
import com.money.custom.entity.request.ChangeStatusRequest;
import com.money.custom.entity.request.QueryUserRequest;
import com.money.framework.base.service.BaseService;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public interface UserService extends BaseService {

    List<User> selectSearchList(QueryUserRequest request);

    User findById(String id);

    void add(User item) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException;

    void edit(User item) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException;

    void changePwd(User item) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException;

    void changeStatus(ChangeStatusRequest request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

}
