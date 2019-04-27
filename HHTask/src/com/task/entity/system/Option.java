package com.task.entity.system;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author 王晓飞 :(ta_sys_Option)
 *		审批流程意见选项表
 */
public class Option implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private CircuitCustomize circuitCustomize;//对应审批流程
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
	@JSONField(serialize = false)
	public CircuitCustomize getCircuitCustomize() {
		return circuitCustomize;
	}
	public void setCircuitCustomize(CircuitCustomize circuitCustomize) {
		this.circuitCustomize = circuitCustomize;
	}
	
	
}
