package com.money.framework.base.service.impl;

import com.money.custom.dao.DeleteDao;
import com.money.custom.entity.request.DeleteByIdsRequest;
import com.money.framework.base.entity.OperationalEntity;
import com.money.framework.base.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BaseServiceImpl implements BaseService, ApplicationContextAware {

    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }

    protected ApplicationContext applicationContext = null;

    @Autowired
    DeleteDao deleteDao;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public <T extends OperationalEntity> boolean isFamilyMember(List<T> items, T item, Integer groupId) {
        if (Objects.isNull(groupId)) {
            return true;
        }

        while (Objects.nonNull(item)) {
            if (groupId.equals(item.getGroupId())) {
                return true;
            }
            if (Objects.isNull(item.getParentGroupId())) {
                return false;
            }
            Integer parentId = item.getParentGroupId();
            item = items.stream().filter(i -> parentId.equals(i.getGroupId())).findFirst().orElse(null);
        }

        return false;
    }

    @Override
    public <T extends OperationalEntity> List<T> pickupFamilyMembers(List<T> items, Integer groupId) {
        return items.stream().filter(i -> isFamilyMember(items, i, groupId)).collect(Collectors.toList());
    }

    @Override
    public void deleteByIds(DeleteByIdsRequest request) {
        canDeleteByIds(request);
        deleteDao.deleteByIds(request);
    }

    protected void canDeleteByIds(DeleteByIdsRequest request) {
        Assert.notNull(request.getTableNameEnum(), "未指定要删除的数据源");
    }
}
