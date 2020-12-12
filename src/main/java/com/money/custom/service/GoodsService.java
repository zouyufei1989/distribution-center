package com.money.custom.service;

import com.money.custom.entity.Goods;
import com.money.custom.entity.GoodsItem;
import com.money.custom.entity.request.*;
import com.money.framework.base.service.BaseService;

import java.util.List;

public interface GoodsService extends BaseService {

    List<Goods> selectSearchList(QueryGoodsRequest request);

    int selectSearchListCount(QueryGoodsRequest request);

    Goods findById(String id);

    String addSingleItem(MoAGoods4SingleRequest request);

    String editSingleItem(MoAGoods4SingleRequest request);

    List<String> changeStatus(ChangeStatusRequest request);

    String addPackage(MoAGoods4PackageRequest request);

    String editPackage(MoAGoods4PackageRequest request);

    String assignGoods4Package(AssignGoods4PackageRequest request);

    String addActivityWithItem(MoAGoods4ActivityRequest request);

}
