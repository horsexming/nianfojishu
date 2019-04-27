package com.task.entity;

import java.io.Serializable;
import java.util.List;

public class VProductPrice implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private List<VProductPrice> children;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public List<VProductPrice> getChildren() {
		return children;
	}
	public void setChildren(List<VProductPrice> children) {
		this.children = children;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}