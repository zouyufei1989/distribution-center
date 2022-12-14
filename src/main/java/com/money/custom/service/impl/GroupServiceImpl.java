package com.money.custom.service.impl;

import com.money.custom.dao.GroupDao;
import com.money.custom.entity.Group;
import com.money.custom.entity.enums.GroupReserveFlagEnum;
import com.money.custom.entity.enums.HistoryEntityEnum;
import com.money.custom.entity.enums.RedisKeyEnum;
import com.money.custom.entity.request.ChangeStatusRequest;
import com.money.custom.entity.request.QueryGridRequestBase;
import com.money.custom.service.GroupService;
import com.money.framework.base.annotation.AddHistoryLog;
import com.money.framework.base.annotation.RedisDel;
import com.money.framework.base.service.impl.BaseServiceImpl;
import com.money.framework.util.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class GroupServiceImpl extends BaseServiceImpl implements GroupService {

    @Autowired
    GroupDao dao;
    @Autowired
    UploadUtils uploadUtils;


    @Override
    public List<Group> selectSearchList(QueryGridRequestBase request) {
        return dao.selectSearchList(request);
    }

    @Override
    public int selectSearchListCount(QueryGridRequestBase request) {
        return dao.selectSearchListCount(request);
    }

    @Override
    public Group findById(String id) {
        return dao.findById(id);
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.GROUP)
    @Override
    public String add(Group item) {
        item.setReserveFlag(GroupReserveFlagEnum.NO.getValue());
        dao.add(item);
        return item.getId().toString();
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.GROUP)
    @Override
    public String edit(Group item) {
        dao.edit(item);
        return item.getId().toString();
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.GROUP)
    @Override
    public List<String> changeStatus(ChangeStatusRequest request) {
        dao.changeStatus(request);
        return request.getIds();
    }

}
