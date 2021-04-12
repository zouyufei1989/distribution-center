package com.money.custom.service.impl;

import com.money.custom.dao.EmployeeCustomerDao;
import com.money.custom.dao.EmployeeDao;
import com.money.custom.entity.Customer;
import com.money.custom.entity.Employee;
import com.money.custom.entity.EmployeeCustomer;
import com.money.custom.entity.dto.TreeNodeDto;
import com.money.custom.entity.enums.*;
import com.money.custom.entity.request.*;
import com.money.custom.service.CustomerService;
import com.money.custom.service.EmployeeService;
import com.money.custom.service.UtilsService;
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
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao dao;
    @Autowired
    EmployeeCustomerDao employeeCustomerDao;
    @Autowired
    UtilsService utilsService;
    @Autowired
    CustomerService customerService;

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
        employee.setSerialNumber(utilsService.generateSerialNumber(SerialNumberEnum.EM));
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
        if (request.getStatus().equals(EmployeeStatusEnum.DELETED.getValue())) {
            QueryEmployeeCustomerRequest queryEmployeeCustomerRequest = new QueryEmployeeCustomerRequest();
            queryEmployeeCustomerRequest.setStatus(CommonStatusEnum.ENABLE.getValue());
            queryEmployeeCustomerRequest.setEmployeeIds(request.getIds());
            final int bindingCnt = employeeCustomerDao.selectSearchListCount(queryEmployeeCustomerRequest);
            Assert.isTrue(bindingCnt == 0, "选中员工下仍有股东信息");
        }
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
        Set<Integer> customerGroupIdsBinded = employeeCustomers.stream().map(EmployeeCustomer::getCustomerGroupId).collect(Collectors.toSet());
        List<Integer> customerGroupIdsToBind = request.getCustomerGroupIds().stream().filter(i -> !customerGroupIdsBinded.contains(i)).collect(Collectors.toList());

        transfer(request, employeeCustomers);
        newBinding(request, customerGroupIdsToBind);
    }

    @Override
    public TreeNodeDto buildEmployeeRelationships(String employeeId) {
        Assert.hasText(employeeId, "请指定员工ID");
        TreeNodeDto root = new TreeNodeDto();

        final Employee employee = findById(employeeId);
        Assert.notNull(employee, "未查询到员工信息");
        root.setId("emp_" + employee.getId());
        root.setTitle(employee.getName());

        QueryCustomerRequest queryCustomerRequest = new QueryCustomerRequest();
        queryCustomerRequest.setGroupId(employee.getGroupId());
        final List<Customer> customers = customerService.selectSearchList(queryCustomerRequest);

        customers.stream()
                .filter(c -> Objects.nonNull(c.getEmployeeId()))
                .filter(c -> StringUtils.equals(employeeId, c.getEmployeeId().toString()))
                .forEach(c -> {
                    TreeNodeDto child = new TreeNodeDto(c);
                    root.getChildren().add(child);
                    buildTreeNodeChildren(child, customers);
                });

        return root;
    }

    void buildTreeNodeChildren(TreeNodeDto node, List<Customer> customers) {
        customers.stream()
                .filter(c -> Objects.nonNull(c.getCustomerGroup().getParentId()))
                .filter(c -> StringUtils.equals(c.getCustomerGroup().getParentId().toString(), node.getId()))
                .forEach(c -> {
                    TreeNodeDto child = new TreeNodeDto(c);
                    node.getChildren().add(child);
                    buildTreeNodeChildren(child, customers);
                });
    }

    private void newBinding(BindCustomer4EmployeeRequest request, List<Integer> customerGroupIdsToBind) {
        getLogger().info("待新绑定{}人", customerGroupIdsToBind.size());
        if (CollectionUtils.isNotEmpty(customerGroupIdsToBind)) {
            List<EmployeeCustomer> newBinding = new ArrayList<>();
            for (Integer customerGroupId : customerGroupIdsToBind) {
                EmployeeCustomer item = new EmployeeCustomer();
                item.copyOperationInfo(request);
                item.setCustomerGroupId(customerGroupId);
                item.setEmployeeId(request.getEmployeeId());
                item.setStatus(CommonStatusEnum.ENABLE.getValue());
                newBinding.add(item);
            }
            employeeCustomerDao.addBatch(newBinding);
        }
    }

    private void transfer(BindCustomer4EmployeeRequest request, List<EmployeeCustomer> employeeCustomers) {
        Assert.isTrue(employeeCustomers.stream().noneMatch(i -> i.getEmployeeId().equals(request.getEmployeeId())), "转移员工和原员工相同");
        getLogger().info("转移股东{}人", employeeCustomers.size());
        if (CollectionUtils.isNotEmpty(employeeCustomers)) {

            employeeCustomers.forEach(i -> {
                i.setParentId(i.getId());
                i.setParentEmployeeId(i.getEmployeeId());

                if (Objects.isNull(i.getInheritChain())) {
                    i.setInheritChain(StringUtils.EMPTY);
                }
                if (StringUtils.isNotEmpty(i.getInheritChain())) {
                    i.setInheritChain(i.getInheritChain() + ",");
                }
                i.setInheritChain(i.getInheritChain() + i.getId());
                i.setEmployeeId(request.getEmployeeId());
                i.setStatus(CommonStatusEnum.ENABLE.getValue());
                i.copyOperationInfo(request);
            });

            ChangeStatusRequest changeStatusRequest = new ChangeStatusRequest(employeeCustomers.stream().map(i -> i.getId().toString()).collect(Collectors.toList()), CommonStatusEnum.DISABLE.getValue());
            employeeCustomerDao.changeStatus(changeStatusRequest);
            employeeCustomerDao.addBatch(employeeCustomers);
        }
    }
}
