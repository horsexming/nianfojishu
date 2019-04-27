package com.task.entity;

import java.io.Serializable;

/**
 * 表名:ta_unitmanager
 * @author Administrator
 *
 */
public class UnitManager implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;//主键id
	private String unitname;//单位名称
	private String unitdate;//添加时间
	private String type;//类型（unit,material）
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUnitname() {
		return unitname;
	}
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
	public String getUnitdate() {
		return unitdate;
	}
	public void setUnitdate(String unitdate) {
		this.unitdate = unitdate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	

}
