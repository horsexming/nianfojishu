package com.task.entity;

import java.io.Serializable;

/**
 * 工装
 * 
 * @author 马凯
 * 
 */
public class Ttooling implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private int numb;// 工装号
	private String name;// 工装名称
	private String state;// 状态
	private String descrption;// 备注
	private int amount;// 金额
	private String specification;// 规格
	private Project project;// 对应项目
	private String notes;//备注
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNumb() {
		return numb;
	}

	public void setNumb(int numb) {
		this.numb = numb;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
