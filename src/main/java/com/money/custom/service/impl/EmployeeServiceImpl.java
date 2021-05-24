package com.money.custom.service.impl;

import com.google.common.collect.Lists;
import com.money.custom.dao.EmployeeCustomerDao;
import com.money.custom.dao.EmployeeDao;
import com.money.custom.entity.Customer;
import com.money.custom.entity.Employee;
import com.money.custom.entity.EmployeeCustomer;
import com.money.custom.entity.dto.TreeNodeDto;
import com.money.custom.entity.enums.CommonStatusEnum;
import com.money.custom.entity.enums.EmployeeStatusEnum;
import com.money.custom.entity.enums.HistoryEntityEnum;
import com.money.custom.entity.enums.SerialNumberEnum;
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
        queryEmployeeCustomerRequest.setEmployeeId(request.getEmployeeId());
        queryEmployeeCustomerRequest.setStatus(CommonStatusEnum.ENABLE.getValue());
        final List<EmployeeCustomer> empCusBound = employeeCustomerDao.selectSearchList(queryEmployeeCustomerRequest);
        final Set<Integer> customerGroupBound = empCusBound.stream().map(EmployeeCustomer::getCustomerGroupId).collect(Collectors.toSet());

        final Set<String> unBindIds = empCusBound.stream().filter(e -> !request.getCustomerGroupIds().contains(e.getCustomerGroupId())).map(e -> e.getId().toString()).collect(Collectors.toSet());
        Set<Integer> customerGroupIdsToBind = request.getCustomerGroupIds().stream().filter(i -> !customerGroupBound.contains(i)).collect(Collectors.toSet());

        unbind(unBindIds);
        newBinding(request, customerGroupIdsToBind);
    }

    private void newBinding(BindCustomer4EmployeeRequest request, Set<Integer> customerGroupIdsToBind) {
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

    private void unbind(Set<String> unBindIds) {
        getLogger().info("解绑{}人", unBindIds.size());
        if (CollectionUtils.isNotEmpty(unBindIds)) {
            ChangeStatusRequest changeStatusRequest = new ChangeStatusRequest(Lists.newArrayList(unBindIds), CommonStatusEnum.DISABLE.getValue());
            employeeCustomerDao.changeStatus(changeStatusRequest);
        }
    }

    @Override
    public void transferCustomers(TransferCustomer4EmployeeRequest request) {
        Assert.isTrue(!request.getSrcEmployeeId().equals(request.getEmployeeId()), "转移员工和原员工相同");
        QueryEmployeeCustomerRequest queryEmployeeCustomerRequest = new QueryEmployeeCustomerRequest();
        queryEmployeeCustomerRequest.setEmployeeId(request.getSrcEmployeeId());
        queryEmployeeCustomerRequest.setStatus(CommonStatusEnum.ENABLE.getValue());
        List<EmployeeCustomer> employeeCustomers = employeeCustomerDao.selectSearchList(queryEmployeeCustomerRequest);
        Assert.notEmpty(employeeCustomers, "原员工无所属股东");

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

    @Override
    public TreeNodeDto buildEmployeeRelationships(String employeeId) {
        TreeNodeDto root = new TreeNodeDto();

        if (StringUtils.isEmpty(employeeId)) {
            getLogger().error("employeeId不可为空");
            return null;
        }

        final Employee employee = findById(employeeId);
        if (Objects.isNull(employee)) {
            getLogger().error("未查询到员工信息");
            return null;
        }
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

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.EMPLOYEE)
    @Override
    public void edit(Employee employee) {
        dao.edit(employee);
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

    @Override
    protected void canDeleteByIds(DeleteByIdsRequest request) {
        QueryEmployeeCustomerRequest queryEmployeeCustomerRequest = new QueryEmployeeCustomerRequest();
        queryEmployeeCustomerRequest.setStatus(CommonStatusEnum.ENABLE.getValue());
        queryEmployeeCustomerRequest.setEmployeeIds(request.getIds());
        final int bindingCnt = employeeCustomerDao.selectSearchListCount(queryEmployeeCustomerRequest);
        Assert.isTrue(bindingCnt == 0, "选中员工下仍有股东信息");
        super.canDeleteByIds(request);
    }
}
