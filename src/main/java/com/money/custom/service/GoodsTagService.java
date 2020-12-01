package com.money.custom.service;

import com.money.custom.entity.GoodsTag;
import com.money.custom.entity.request.ChangeStatusRequest;
import com.money.custom.entity.request.QueryGoodsTagRequest;
import com.money.framework.base.service.BaseService;

import java.util.List;

public interface GoodsTagService extends BaseService {

    List<GoodsTag> selectSearchList(QueryGoodsTagRequest request);

    int selectSearchListCount(QueryGoodsTagRequest request);

    GoodsTag findById(String id);

    String add(GoodsTag item);

    String edit(GoodsTag item);

    List<String> changeStatus(ChangeStatusRequest request);

}
