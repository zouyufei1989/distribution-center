package com.money.custom.service.impl;

import com.money.custom.dao.BannerDao;
import com.money.custom.dao.EmployeeDao;
import com.money.custom.entity.Banner;
import com.money.custom.entity.Employee;
import com.money.custom.entity.enums.HistoryEntityEnum;
import com.money.custom.entity.request.*;
import com.money.custom.service.BannerService;
import com.money.custom.service.EmployeeService;
import com.money.framework.base.annotation.AddHistoryLog;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao dao;

    @Override
    public List<Employee> selectSearchList(QueryEmployeeRequest request) {
        return dao.selectSearchList(request);
    }

    @Override
    public int selectSearchListCount(QueryEmployeeRequest request) {
        return dao.selectSearchListCount(request);
    }

    @Override
    public Employee findById(String id) {
        return dao.findById(id);
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.EMPLOYEE)
    @Override
    public String add(MoAEmployeeRequest request) {
        Employee employee = Employee.build4Add(request);
        dao.add(employee);
        return employee.getId().toString();
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.EMPLOYEE)
    @Override
    public String edit(MoAEmployeeRequest request) {
        Employee employee = Employee.build4Edit(request);
        dao.edit(employee);
        return employee.getId().toString();
    }

    @Override
    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.EMPLOYEE)
    public List<String> changeStatus(ChangeEmployeeStatusRequest request) {
        dao.changeStatus(request);
        return request.getIds();
    }

}
