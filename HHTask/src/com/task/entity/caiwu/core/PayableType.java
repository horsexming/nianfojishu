package com.task.entity.caiwu.core;

import java.io.Serializable;

/**
 * 非主营业务应付类型表
 * @author licong 
 * 表名 ta_PayableType
 *
 */
public class PayableType implements Serializable{
	private Integer id;
	private String type;//类型
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
