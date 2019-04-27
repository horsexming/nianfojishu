/**
 * 
 */
package com.task.entity.expresscabinet;

import java.io.Serializable;

/**
 * @author Administrator 快递公司信息表
 */
public class CourierCompany implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id; // 快递公司id
	private String couCpanyName; // 快递公司名称

	public CourierCompany() {
		super();
	}

	public CourierCompany(Integer id, String couCpanyName) {
		super();
		this.id = id;
		this.couCpanyName = couCpanyName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCouCpanyName() {
		return couCpanyName;
	}

	public void setCouCpanyName(String couCpanyName) {
		this.couCpanyName = couCpanyName;
	}
}
