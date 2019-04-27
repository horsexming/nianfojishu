package com.task.entity;

import java.io.Serializable;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.task.entity.menjin.AccessEquipment;


/**
 * 用户对接信息
 * @author 李聪
 * 表名 ta_UserFacialInfer
 *
 */
public class UserFacialInfor implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String userNo;//工号
	private String userName;//姓名
	private String userPassword;//密码
	private String addTime;//添加时间
	private Set<AccessEquipment> accessEquipments;// 门禁设备（多对多）
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	@JSONField(serialize = false)
	public Set<AccessEquipment> getAccessEquipments() {
		return accessEquipments;
	}
	public void setAccessEquipments(Set<AccessEquipment> accessEquipments) {
		this.accessEquipments = accessEquipments;
	}
	
}
