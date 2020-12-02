package com.money.custom.service;

import com.money.custom.entity.Banner;
import com.money.custom.entity.Goods;
import com.money.custom.entity.request.ChangeStatusRequest;
import com.money.custom.entity.request.QueryGoodsRequest;
import com.money.custom.entity.request.QueryGridRequestBase;
import com.money.framework.base.service.BaseService;

import java.util.List;

public interface GoodsService extends BaseService {

    List<Goods> selectSearchList(QueryGoodsRequest request);

    int selectSearchListCount(QueryGoodsRequest request);

    Goods findById(String id);

    String add(Goods item);

    String edit(Goods item);

    List<String> changeStatus(ChangeStatusRequest request);

}
