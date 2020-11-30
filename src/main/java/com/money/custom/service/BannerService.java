package com.money.custom.service;

import com.money.custom.entity.Banner;
import com.money.custom.entity.Group;
import com.money.custom.entity.request.ChangeStatusRequest;
import com.money.custom.entity.request.QueryGridRequestBase;
import com.money.framework.base.service.BaseService;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface BannerService extends BaseService {

    List<Banner> selectSearchList(QueryGridRequestBase request);

    int selectSearchListCount(QueryGridRequestBase request);

    Banner findById(String id);

    String add(Banner item);

    String edit(Banner item);

    List<String> changeStatus(ChangeStatusRequest request);

}
