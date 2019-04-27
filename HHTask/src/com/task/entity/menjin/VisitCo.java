package com.task.entity.menjin;

import java.io.Serializable;

/**
 * 
 * 来访公司列表
 * 
 * @author fy
 *
 */
public class VisitCo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name; // 公司名称
	private String contactNumber; // 电话
	private String contactPerson; // 联系人

	public Integer getId() {
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

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
}
