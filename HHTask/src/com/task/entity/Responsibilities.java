package com.task.entity;

import java.io.Serializable;

/*
 * 
 * 修理人
 */
public class Responsibilities  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;// ID
	private String employeenumber;// 工号
	private String repairname;// 修理人
	private String repairdepartment;// 维修部门
	private String repaircategory;// 类别
	private String repairresponsibilitiesl;// 职责
	private String phone;// 手机号
	private String mailbox;// 邮箱

	public Integer getId() {
		return id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMailbox() {
		return mailbox;
	}

	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}

	public String getEmployeenumber() {
		return employeenumber;
	}

	public String getRepairname() {
		return repairname;
	}

	public String getRepairdepartment() {
		return repairdepartment;
	}

	public String getRepaircategory() {
		return repaircategory;
	}

	public String getRepairresponsibilitiesl() {
		return repairresponsibilitiesl;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setEmployeenumber(String employeenumber) {
		this.employeenumber = employeenumber;
	}

	public void setRepairname(String repairname) {
		this.repairname = repairname;
	}

	public void setRepairdepartment(String repairdepartment) {
		this.repairdepartment = repairdepartment;
	}

	public void setRepaircategory(String repaircategory) {
		this.repaircategory = repaircategory;
	}

	public void setRepairresponsibilitiesl(String repairresponsibilitiesl) {
		this.repairresponsibilitiesl = repairresponsibilitiesl;
	}

}
