package com.money.custom.entity;

import com.money.framework.base.entity.BaseEntity;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Resource extends BaseEntity {

    /**
     *
     */
    private Integer id;
    private String url;
    private Integer level;
    private String name;
    private String icon;
    private Integer parentId;
    private Integer resorder;
    private Integer enable;
    private Long resourceFuncIds;
    private Long roleFuncIds;

    public TreeNode convertToTreeNode() {
        TreeNode node = new TreeNode();
        node.setId(id);
        node.setText(name);
        node.setOrder(resorder);
        node.setResourceFuncIds(resourceFuncIds);
        node.setRoleFuncIds(roleFuncIds);

        TreeNode.Attributes att = node.new Attributes();
        att.setUrl(url);
        node.setAttributes(att);

        if (StringUtils.isNotEmpty(this.icon)) {
            node.setIconCls(this.icon);
        }

        if (CollectionUtils.isNotEmpty(children)) {
            List<TreeNode> childrenNode = getChildren().stream().map(Resource::convertToTreeNode).collect(Collectors.toList());
            node.getChildren().addAll(childrenNode);
        }

        return node;
    }

    private List<Resource> children = new ArrayList<>();

    public List<Resource> getChildren() {
        children.sort(Comparator.comparing(Resource::getResorder));
        return children;
    }

    public void addChildren(Resource resource) {
        this.children.add(resource);
    }

    public void setChildren(List<Resource> children) {
        this.children = children;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getResourceFuncIds() {
        return resourceFuncIds;
    }

    public void setResourceFuncIds(Long resourceFuncIds) {
        this.resourceFuncIds = resourceFuncIds;
    }

    public Long getRoleFuncIds() {
        return roleFuncIds;
    }

    public void setRoleFuncIds(Long roleFuncIds) {
        this.roleFuncIds = roleFuncIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getResorder() {
        return resorder;
    }

    public void setResorder(Integer resorder) {
        this.resorder = resorder;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }
}
