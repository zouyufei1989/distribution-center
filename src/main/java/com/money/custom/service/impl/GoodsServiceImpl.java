package com.money.custom.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.money.custom.dao.GoodsDao;
import com.money.custom.dao.GoodsItemDao;
import com.money.custom.entity.Goods;
import com.money.custom.entity.GoodsItem;
import com.money.custom.entity.enums.GoodsTypeEnum;
import com.money.custom.entity.enums.HistoryEntityEnum;
import com.money.custom.entity.request.*;
import com.money.custom.service.GoodsService;
import com.money.framework.base.annotation.AddHistoryLog;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl extends BaseServiceImpl implements GoodsService {

    @Autowired
    GoodsDao dao;
    @Autowired
    GoodsItemDao itemDao;
    @Autowired
    GoodsItemDao goodsItemDao;

    @Override
    public List<Goods> selectSearchList(QueryGoodsRequest request) {
        List<Goods> goods = dao.selectSearchList(request);
        if (CollectionUtils.isEmpty(goods)) {
            return goods;
        }

        Set<String> goodsIdSet = goods.stream().map(g -> g.getId().toString()).collect(Collectors.toSet());
        List<GoodsItem> items = itemDao.selectSearchList(new QueryGoodsItemRequest(goodsIdSet));
        if (CollectionUtils.isNotEmpty(items)) {
            Map<Integer, List<GoodsItem>> map = items.stream().collect(Collectors.groupingBy(GoodsItem::getGoodsId));
            goods.forEach(g -> {
                g.setItems(map.get(g.getId()));
            });
        }

        return goods;
    }

    @Override
    public int selectSearchListCount(QueryGoodsRequest request) {
        return dao.selectSearchListCount(request);
    }

    @Override
    public Goods findById(String id) {
        Goods goods = dao.findById(id);
        List<GoodsItem> goodsItems = itemDao.selectSearchList(new QueryGoodsItemRequest(id));
        goods.setItems(goodsItems);
        return goods;
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.GOODS)
    @Override
    @Transactional
    public String addSingleItem(MoAGoods4SingleRequest request) {
        Goods goods = new Goods(request);
        dao.add(goods);

        itemDao.addBatch(Lists.newArrayList(new GoodsItem(request, goods.getId())));
        return goods.getId().toString();
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.GOODS)
    @Override
    @Transactional
    public String editSingleItem(MoAGoods4SingleRequest request) {
        Goods goodsSrc = findById(request.getId().toString());
        Assert.notNull(goodsSrc, "商品不存在");
        Assert.isTrue(goodsSrc.getType().equals(GoodsTypeEnum.SINGLE.getValue()), "商品不可修改");
        Assert.isTrue(goodsSrc.getItems().size() == 1, "商品无法修改");

        Goods goods = new Goods(request);
        goods.setId(request.getId());
        dao.edit(goods);

        GoodsItem item = new GoodsItem(request, goodsSrc.getId());
        item.setId(goodsSrc.getItems().get(0).getId());
        itemDao.edit(item);

        return request.getId().toString();
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.GOODS)
    @Override
    public List<String> changeStatus(ChangeStatusRequest request) {
        dao.changeStatus(request);
        return request.getIds();
    }

    @Override
    public String addPackageItem(MoAGoods4PackageRequest request) {
        Goods goods = new Goods(request);
        dao.add(goods);
        return goods.getId().toString();
    }

    @Override
    public String editPackageItem(MoAGoods4PackageRequest request) {
        Goods goods = new Goods(request);
        goods.setId(request.getId());
        dao.edit(goods);
        return goods.getId().toString();
    }

    @Transactional
    @Override
    public String assignGoods4Package(AssignGoods4PackageRequest request) {
        Goods packageGoods = findById(request.getGoodsId().toString());
        Assert.notNull(packageGoods, "套餐不存在");
        Assert.isTrue(packageGoods.getType().equals(GoodsTypeEnum.PACKAGE.getValue()), "非套餐，不可操作");

        QueryGoodsItemRequest queryGoodsItemRequest = new QueryGoodsItemRequest();
        queryGoodsItemRequest.setIdSet(Sets.newHashSet(request.getGoodsItemIdList()));
        List<GoodsItem> goodsItems = goodsItemDao.selectSearchList(queryGoodsItemRequest);
        Assert.isTrue(goodsItems.size() == request.getGoodsItemIdList().size(), "商品数量对比失败");
        Assert.isTrue(goodsItems.stream().allMatch(i -> i.getGroupId().equals(packageGoods.getGroupId())), "不可跨公司分配商品");

        goodsItems.forEach(g -> {
            g.setCnt(packageGoods.getCnt());
            g.setGoodsId(packageGoods.getId());
            g.copyOperationInfo(request);
        });
        goodsItemDao.addBatch(goodsItems);

        Goods goods = new Goods();
        goods.setId(packageGoods.getId());
        goods.copyOperationInfo(request);
        dao.edit(goods);

        return request.getGoodsId().toString();
    }

}
