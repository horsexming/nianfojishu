package com.task.entity;

import java.io.Serializable;

/**
 * @author 曾建森
 * @FileNam Person.java
 * @Date 2012-10-9 卡信息表 ST_Person
 */
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int deptId; // 部门ID
	private String cardNo; // 卡号
	private String personNo;// 员工编号
	private String name; // 姓名
	private String type;
	private String duty;
	private String sex;
	private int isMaryy;
	private String onWorkDate;
	private int isDel;
	private AttendancePersonInformation attendancePersonInformation;// 一对一关联，对应AttendancePersonInformation实体

	private Department department;// 单向多对一关联，部门对应多个员工即Department---->Person

	// getter和setter方法
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getPersonNo() {
		return personNo;
	}

	public void setPersonNo(String personNo) {
		this.personNo = personNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getIsMaryy() {
		return isMaryy;
	}

	public void setIsMaryy(int isMaryy) {
		this.isMaryy = isMaryy;
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	public AttendancePersonInformation getAttendancePersonInformation() {
		return attendancePersonInformation;
	}

	public void setAttendancePersonInformation(AttendancePersonInformation attendancePersonInformation) {
		this.attendancePersonInformation = attendancePersonInformation;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getOnWorkDate() {
		return onWorkDate;
	}

	public void setOnWorkDate(String onWorkDate) {
		this.onWorkDate = onWorkDate;
	}

}
