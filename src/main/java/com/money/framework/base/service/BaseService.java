package com.money.framework.base.service;

import com.money.custom.entity.request.DeleteByIdsRequest;
import com.money.framework.base.entity.OperationalEntity;

import java.util.List;

public interface BaseService {

    /**
     * item 是否属于groupId 或其子公司
     *
     * @param items
     * @param item
     * @param groupId
     * @param <T>
     * @return
     */
    <T extends OperationalEntity> boolean isFamilyMember(List<T> items, T item, Integer groupId);

    <T extends OperationalEntity> List<T> pickupFamilyMembers(List<T> items, Integer groupId);

    void deleteByIds(DeleteByIdsRequest request);
}
