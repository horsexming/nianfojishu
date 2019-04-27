package com.task.entity;

import java.io.Serializable;

public class ProjectStart  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Project root;
	private Boolean closed;//是否关闭

	public Boolean getClosed() {
		return closed;
	}
	public void setClosed(Boolean closed) {
		this.closed = closed;
	}
	public Project getRoot() {
		return root;
	}
	public void setRoot(Project root) {
		this.root = root;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

}
