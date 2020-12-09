package com.money.custom.service.impl;

import com.money.custom.dao.CustomerGroupDao;
import com.money.custom.entity.CustomerGroup;
import com.money.custom.entity.enums.HistoryEntityEnum;
import com.money.custom.entity.request.AssignBonusPlanRequest;
import com.money.custom.entity.request.ChangeStatusRequest;
import com.money.custom.service.CustomerGroupService;
import com.money.framework.base.annotation.AddHistoryLog;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerGroupServiceImpl extends BaseServiceImpl implements CustomerGroupService {

    @Autowired
    CustomerGroupDao dao;

    @Override
    public CustomerGroup findById(String id) {
        return dao.findById(id);
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.CUSTOMER_GRUOP)
    @Override
    public String add(CustomerGroup item) {
        dao.add(item);
        return item.getId().toString();
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.CUSTOMER_GRUOP)
    @Override
    public String edit(CustomerGroup item) {
        dao.edit(item);
        return item.getId().toString();
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.CUSTOMER_GRUOP)
    @Override
    public List<String> changeStatus(ChangeStatusRequest request) {
        dao.changeStatus(request);
        return request.getIds();
    }

}
