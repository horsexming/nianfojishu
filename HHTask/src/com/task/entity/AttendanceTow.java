package com.task.entity;

import java.io.Serializable;

/***
 * 刷卡表
 * @author Li_Cong
 * @Date 2016-01-02
 * ta_kq_AttendanceTow
 */

public class AttendanceTow  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;// 考勤Id主键.
	private String name;// 员工姓名
	private String dept;// 部门名称
	private String cardId;// 卡号
	private String code;// 工号
	private Integer userId;// userID
	private String outInDoor;// 进门/出门
	private String dateTime;// 打卡日期
	private String time;// 打卡时间时分00:00
	private String addTime;// 添加时间
	private String downType;//打卡类型(正常/出差)
	private String downAddress;//打卡地址(手机发送地址/门禁设备)
	public String getDownType() {
		return downType;
	}
	public void setDownType(String downType) {
		this.downType = downType;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDownAddress() {
		return downAddress;
	}
	public void setDownAddress(String downAddress) {
		this.downAddress = downAddress;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOutInDoor() {
		return outInDoor;
	}
	public void setOutInDoor(String outInDoor) {
		this.outInDoor = outInDoor;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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


}
