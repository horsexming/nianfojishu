package com.task.entity;

import java.io.Serializable;

/*
 * 流程节点表
 */
public class Processnode implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;// ID
	private String name;// 姓名
	private String number;// 工号
	private String status;// 状态
	private String executiontime;// 执行时间

	private Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExecutiontime() {
		return executiontime;
	}

	public void setExecutiontime(String executiontime) {
		this.executiontime = executiontime;
	}

}
