package com.money.custom.entity;

import com.money.framework.base.entity.BaseEntity;

import java.util.List;

public class Role extends BaseEntity {

	/**
	 *
	 */
	private int id;
	private String name;
	private String enable;

	private List<Right> right;

	public List<Right> getRight() {
		return right;
	}

	public void setRight(List<Right> right) {
		this.right = right;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}
}
