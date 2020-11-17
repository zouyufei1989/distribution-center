package com.money.custom.service.impl;

import com.money.custom.dao.SecurityDao;
import com.money.custom.entity.Resource;
import com.money.custom.entity.TreeNode;
import com.money.custom.entity.User;
import com.money.custom.service.LoginService;
import com.money.custom.service.TreeService;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TreeServiceImp extends BaseServiceImpl implements TreeService {

    @Autowired
    SecurityDao securityDao;
    @Autowired
    LoginService loginService;

    @Override
    public List<TreeNode> buildCategory(User loginUser) {
        if (Objects.isNull(loginUser)) {
            return null;
        }

        List<Resource> resources = loginService.getUserResources(loginUser);
        List<Resource> parentResources = securityDao.selectList("getParentResourcesName");

        resources.stream().forEach(resource -> {
            Optional<Resource> resourceOptional = parentResources.stream().filter(parent -> parent.getId().equals(resource.getParentId())).findFirst();
            if (resourceOptional.isPresent()) {
                resourceOptional.get().addChildren(resource);
            }
        });

        List<TreeNode> treeNodes = parentResources.stream()
                .filter(i -> CollectionUtils.isNotEmpty(i.getChildren()))
                .sorted(Comparator.comparing(Resource::getResorder))
                .map(Resource::convertToTreeNode)
                .collect(Collectors.toList());

        return treeNodes;
    }

}
