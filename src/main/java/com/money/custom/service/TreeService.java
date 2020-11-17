package com.money.custom.service;

import com.money.custom.entity.TreeNode;
import com.money.custom.entity.User;
import com.money.framework.base.service.BaseService;

import java.util.List;

public interface TreeService extends BaseService {

    List<TreeNode> buildCategory(User loginUser);

}
