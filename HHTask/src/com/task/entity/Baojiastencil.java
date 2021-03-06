package com.task.entity;

import java.io.Serializable;

/*
 * 项目管理 报价资料模版
 */
public class Baojiastencil implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; // 序号 int
	private String baojiastencilprojectname; // 项目名称 varchar
	private String baojiastencilprojectjynumber; // 项目建议书编号 varchar
	private String baojiastencilusername; // 客户名称 varchar
	private String baojiastencilstatus;// 状态
	private String baojiastencildept; // 选择填写报价资料部门 varchar
	private String baojiastenciltime; // 创建时间 varchar

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBaojiastencilprojectname() {
		return baojiastencilprojectname;
	}

	public void setBaojiastencilprojectname(String baojiastencilprojectname) {
		this.baojiastencilprojectname = baojiastencilprojectname;
	}

	public String getBaojiastencilprojectjynumber() {
		return baojiastencilprojectjynumber;
	}

	public void setBaojiastencilprojectjynumber(String baojiastencilprojectjynumber) {
		this.baojiastencilprojectjynumber = baojiastencilprojectjynumber;
	}

	public String getBaojiastencilusername() {
		return baojiastencilusername;
	}

	public void setBaojiastencilusername(String baojiastencilusername) {
		this.baojiastencilusername = baojiastencilusername;
	}

	public String getBaojiastencildept() {
		return baojiastencildept;
	}

	public void setBaojiastencildept(String baojiastencildept) {
		this.baojiastencildept = baojiastencildept;
	}

	public String getBaojiastenciltime() {
		return baojiastenciltime;
	}

	public void setBaojiastenciltime(String baojiastenciltime) {
		this.baojiastenciltime = baojiastenciltime;
	}

	public String getBaojiastencilstatus() {
		return baojiastencilstatus;
	}

	public void setBaojiastencilstatus(String baojiastencilstatus) {
		this.baojiastencilstatus = baojiastencilstatus;
	}

}
