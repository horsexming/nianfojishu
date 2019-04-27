package com.task.entity.menjin;

import java.io.Serializable;

/**
 * 门禁类型表
 * 
 * @author yyhb 表名 ta_DoorType
 *
 */
public class DoorType implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String type;// 类型

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
