package com.money.custom.service.impl;

import com.money.custom.dao.EmployeeCustomerDao;
import com.money.custom.dao.EmployeeDao;
import com.money.custom.entity.Employee;
import com.money.custom.entity.EmployeeCustomer;
import com.money.custom.entity.enums.CommonStatusEnum;
import com.money.custom.entity.enums.HistoryEntityEnum;
import com.money.custom.entity.request.*;
import com.money.custom.service.EmployeeService;
import com.money.framework.base.annotation.AddHistoryLog;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao dao;
    @Autowired
    EmployeeCustomerDao employeeCustomerDao;

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

    @Transactional
    @Override
    public void bindCustomers(BindCustomer4EmployeeRequest request) {
        QueryEmployeeCustomerRequest queryEmployeeCustomerRequest = new QueryEmployeeCustomerRequest();
        queryEmployeeCustomerRequest.setCustomerGroupIds(request.getCustomerGroupIds());
        queryEmployeeCustomerRequest.setStatus(CommonStatusEnum.ENABLE.getValue());
        List<EmployeeCustomer> employeeCustomers = employeeCustomerDao.selectSearchList(queryEmployeeCustomerRequest);
        Set<Integer> customerGroupIdsBinded = employeeCustomers.stream().map(EmployeeCustomer::getCustomGroupId).collect(Collectors.toSet());
        List<Integer> customerGroupIdsToBind = request.getCustomerGroupIds().stream().filter(i -> !customerGroupIdsBinded.contains(i)).collect(Collectors.toList());

        transfer(request, employeeCustomers);
        newBinding(request, customerGroupIdsToBind);


    }

    private void newBinding(BindCustomer4EmployeeRequest request, List<Integer> customerGroupIdsToBind) {
        getLogger().info("待新绑定{}人", customerGroupIdsToBind.size());
        if (CollectionUtils.isNotEmpty(customerGroupIdsToBind)) {
            List<EmployeeCustomer> newBinding = new ArrayList<>();
            for (Integer customerGroupId : customerGroupIdsToBind) {
                EmployeeCustomer item = new EmployeeCustomer();
                item.copyOperationInfo(request);
                item.setCustomGroupId(customerGroupId);
                item.setEmployeeId(request.getEmployeeId());
                newBinding.add(item);
            }
            dao.addBatch(newBinding);
        }
    }

    private void transfer(BindCustomer4EmployeeRequest request, List<EmployeeCustomer> employeeCustomers) {
        getLogger().info("转移股东{}人", employeeCustomers.size());
        if (CollectionUtils.isNotEmpty(employeeCustomers)) {

            employeeCustomers.forEach(i -> {
                i.setParentId(i.getId());
                i.setParentEmployeeId(i.getEmployeeId());
                if (StringUtils.isNotEmpty(i.getInheritChain())) {
                    i.setInheritChain(i.getInheritChain() + ",");
                }
                i.setInheritChain(i.getInheritChain() + i.getId());
                i.setEmployeeId(request.getEmployeeId());
                i.setStatus(CommonStatusEnum.ENABLE.getValue());
                i.copyOperationInfo(request);
            });
            dao.addBatch(employeeCustomers);

            ChangeStatusRequest changeStatusRequest = new ChangeStatusRequest(employeeCustomers.stream().map(i -> i.getId().toString()).collect(Collectors.toList()), CommonStatusEnum.DISABLE.getValue());
            employeeCustomerDao.changeStatus(changeStatusRequest);
        }
    }

}
