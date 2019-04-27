package com.task.entity;

import java.io.Serializable;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.task.entity.menjin.AccessEquipment;


/**
 * 用户面部特征表
 * @author 李聪
 * 表名 ta_UserFacialFeatures
 *
 */
public class UserFacialFeatures implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String userNo;//工号
	private Integer userId;//持卡人Id
	private String userName;//姓名
	private String userFeatures;//面部标识
	private String addTime;//添加时间
	private Set<AccessEquipment> accessEquipments;// 门禁设备（多对多）
	
	private double[] processFeatures;//数组
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double[] getProcessFeatures() {
		return processFeatures;
	}
	public void setProcessFeatures(double[] processFeatures) {
		this.processFeatures = processFeatures;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserFeatures() {
		return userFeatures;
	}
	public void setUserFeatures(String userFeatures) {
		this.userFeatures = userFeatures;
	}
	@JSONField(serialize = false)
	public Set<AccessEquipment> getAccessEquipments() {
		return accessEquipments;
	}
	public void setAccessEquipments(Set<AccessEquipment> accessEquipments) {
		this.accessEquipments = accessEquipments;
	}
	
}
