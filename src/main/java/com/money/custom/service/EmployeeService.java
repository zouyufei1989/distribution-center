package com.money.custom.service;

import com.money.custom.entity.Employee;
import com.money.custom.entity.request.ChangeEmployeeStatusRequest;
import com.money.custom.entity.request.MoAEmployeeRequest;
import com.money.custom.entity.request.QueryEmployeeRequest;
import com.money.framework.base.service.BaseService;

import java.util.List;

public interface EmployeeService extends BaseService {

    List<Employee> selectSearchList(QueryEmployeeRequest request);

    int selectSearchListCount(QueryEmployeeRequest request);

    Employee findById(String id);

    String add(MoAEmployeeRequest item);

    String edit(MoAEmployeeRequest item);

    List<String> changeStatus(ChangeEmployeeStatusRequest request);

}
