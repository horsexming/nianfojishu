package com.task.entity.menjin;

import java.io.Serializable;

/**
 * 门禁来访记录表
 * 
 * @author Li_Cong 2016-04-13 表名 ta_mj_AccessTime 添加可进出时间段
 */
public class AccessTime implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String startTime;// 开始时间
	private String endTime;// 结束时间
	private String addTime;// 添加时间
	private String updateTime;// 修改时间
	private AccessEquipment accessEquipment;// 设备表
	// get set

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public AccessEquipment getAccessEquipment() {
		return accessEquipment;
	}

	public void setAccessEquipment(AccessEquipment accessEquipment) {
		this.accessEquipment = accessEquipment;
	}

}