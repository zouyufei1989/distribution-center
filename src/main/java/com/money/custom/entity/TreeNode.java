package com.money.custom.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songruimin on 14/12/16.
 */
public class TreeNode {

    private int id;
    private String text;
    private String iconCls;
    private String state;
    private Attributes attributes;
    private boolean checked;
    private int order;
    private Long resourceFuncIds;
    private Long roleFuncIds;

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

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    private List<TreeNode> children = new ArrayList<TreeNode>();

    public class Attributes {

        private String url;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        private String price = "100";

    }
}
