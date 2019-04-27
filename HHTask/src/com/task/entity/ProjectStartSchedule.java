package com.task.entity;

import java.io.Serializable;

public class ProjectStartSchedule  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;//名字
	private String startDate;//开始时间
	private String endDate;//结束时间
	private String description;//说明 
	private ProjectStart root;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ProjectStart getRoot() {
		return root;
	}
	public void setRoot(ProjectStart root) {
		this.root = root;
	}

}