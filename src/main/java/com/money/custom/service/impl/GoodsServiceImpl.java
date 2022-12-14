package com.money.custom.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.money.custom.dao.GoodsDao;
import com.money.custom.dao.GoodsItemDao;
import com.money.custom.entity.Customer;
import com.money.custom.entity.CustomerGroup;
import com.money.custom.entity.Goods;
import com.money.custom.entity.GoodsItem;
import com.money.custom.entity.enums.GoodsTypeEnum;
import com.money.custom.entity.enums.HistoryEntityEnum;
import com.money.custom.entity.enums.SerialNumberEnum;
import com.money.custom.entity.request.*;
import com.money.custom.service.CustomerGroupService;
import com.money.custom.service.CustomerService;
import com.money.custom.service.GoodsService;
import com.money.custom.service.UtilsService;
import com.money.framework.base.annotation.AddHistoryLog;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.Objects;
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
    @Autowired
    UtilsService utilsService;
    @Autowired
    CustomerGroupService customerGroupService;

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
        if (Objects.nonNull(request.getCustomerGroupId())) {
            Integer groupId = customerGroupService.findById(request.getCustomerGroupId().toString()).getGroupId();
            request.setGroupId(groupId);
        }
        return dao.selectSearchListCount(request);
    }

    @Override
    public Goods findById(String id) {
        Goods goods = dao.findById(id);
        Assert.notNull(goods, "???????????????");
        List<GoodsItem> goodsItems = itemDao.selectSearchList(new QueryGoodsItemRequest(id));
        goods.setItems(goodsItems);
        return goods;
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.GOODS)
    @Override
    @Transactional
    public String addSingleItem(MoAGoods4SingleRequest request) {
        Goods goods = Goods.build4SingleAdd(request, utilsService.generateSerialNumber(SerialNumberEnum.GS));
        dao.add(goods);

        itemDao.addBatch(Lists.newArrayList(new GoodsItem(request, goods.getId())));
        return goods.getId().toString();
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.GOODS)
    @Override
    @Transactional
    public String editSingleItem(MoAGoods4SingleRequest request) {
        Goods goodsSrc = findById(request.getId().toString());
        Assert.notNull(goodsSrc, "???????????????");
        Assert.isTrue(goodsSrc.getType().equals(GoodsTypeEnum.SINGLE.getValue()), "??????????????????");
        Assert.isTrue(goodsSrc.getItems().size() == 1, "??????????????????");

        Goods goods = Goods.build4SingleEdit(request);
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

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.GOODS)
    @Override
    public String addPackage(MoAGoods4PackageRequest request) {
        Goods goods = Goods.build4PackageAdd(request, utilsService.generateSerialNumber(SerialNumberEnum.GP));
        dao.add(goods);
        return goods.getId().toString();
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.GOODS)
    @Transactional
    @Override
    public String editPackage(MoAGoods4PackageRequest request) {
        Goods goods = Goods.build4PackageEdit(request);
        goods.setId(request.getId());
        dao.edit(goods);

        List<GoodsItem> items = itemDao.selectSearchList(new QueryGoodsItemRequest(request.getId().toString()));
        if (CollectionUtils.isNotEmpty(items)) {
            GoodsItem item = new GoodsItem();
            items.forEach(i -> {
                item.setId(i.getId());
                item.setCnt(request.getCnt());
                item.copyOperationInfo(request);
                itemDao.edit(item);
            });
        }

        return goods.getId().toString();
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.GOODS)
    @Transactional
    @Override
    public String assignGoods4Package(AssignGoods4PackageRequest request) {
        Goods packageGoods = findById(request.getGoodsId().toString());
        Assert.notNull(packageGoods, "???????????????");
        Assert.isTrue(packageGoods.getType().equals(GoodsTypeEnum.PACKAGE.getValue()), "????????????????????????");

        QueryGoodsItemRequest queryGoodsItemRequest = new QueryGoodsItemRequest();
        queryGoodsItemRequest.setIdSet(Sets.newHashSet(request.getGoodsItemIdList()));
        List<GoodsItem> goodsItems = goodsItemDao.selectSearchList(queryGoodsItemRequest);
        Assert.isTrue(goodsItems.size() == request.getGoodsItemIdList().size(), "????????????????????????");
        Assert.isTrue(goodsItems.stream().allMatch(i -> i.getGroupId().equals(packageGoods.getGroupId())), "???????????????????????????");

        goodsItems.forEach(g -> {
            g.setCnt(packageGoods.getCnt());
            g.setGoodsId(packageGoods.getId());
            g.copyOperationInfo(request);
            g.setParentId(g.getId());
        });
        goodsItemDao.addBatch(goodsItems);

        Goods goods = new Goods();
        goods.setId(packageGoods.getId());
        goods.copyOperationInfo(request);
        dao.edit(goods);

        return request.getGoodsId().toString();
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.GOODS)
    @Transactional
    @Override
    public String addActivityWithItem(MoAGoods4ActivityRequest request) {
        QueryGoodsItemRequest queryGoodsItemRequest = new QueryGoodsItemRequest();
        queryGoodsItemRequest.setIdSet(request.getItems().stream().map(i -> i.getGoodsItemId().toString()).collect(Collectors.toSet()));
        List<GoodsItem> items = goodsItemDao.selectSearchList(queryGoodsItemRequest);
        Assert.isTrue(items.size() == request.getItems().size(), "?????????????????????");

        Goods goods = Goods.build4ActivityAdd(request);
        items.forEach(i -> {
            i.setCnt(request.getItems().stream().filter(r -> r.getGoodsItemId().equals(i.getId())).findAny().get().getCnt());
        });
        goods.setSumPrice(items.stream().mapToLong(i -> i.getPrice() * i.getCnt()).sum());
        dao.add(goods);

        items.forEach(i -> {
            i.setParentId(i.getId());
            i.setGoodsId(goods.getId());
            i.copyOperationInfo(request);
        });
        goodsItemDao.addBatch(items);

        return goods.getId().toString();
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.GOODS)
    @Override
    public String editActivity(MoAGoods4ActivityRequest request) {
        Goods goods = Goods.build4ActivityEdit(request);
        dao.edit(goods);
        return goods.getId().toString();
    }

}
