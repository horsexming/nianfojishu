package com.task.entity;

import java.io.Serializable;

import com.task.util.FieldMeta;

/**
 * 转正申请表:(ta_Becoming)
 * 
 * @author 王晓飞
 *
 */

public class Becoming implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer userId;// 待转正人UsersId
	@FieldMeta(name = "待转正人工号")
	private String code;// 待转正人工号
	@FieldMeta(name = "待转正人姓名")
	private String name;// 待转正人姓名
	@FieldMeta(name = "待转正人部门")
	private String dept;// 待转正人部门
	@FieldMeta(name = "当前状态")
	private String befroestatus;// 当前状态
	@FieldMeta(name = "转正后状态")
	private String status;// 转正后状态
	@FieldMeta(name = "申请人姓名")
	private String sqname;// 申请人姓名
	@FieldMeta(name = "申请人部门")
	private String sqdept;// 申请人部门
	@FieldMeta(name = "申请日期")
	private String sqdate;// 申请日期
	private String zzdate;// 转正日期
	private Integer ep_Id;
	private String ep_status;

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

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getSqname() {
		return sqname;
	}

	public void setSqname(String sqname) {
		this.sqname = sqname;
	}

	public String getSqdept() {
		return sqdept;
	}

	public void setSqdept(String sqdept) {
		this.sqdept = sqdept;
	}

	public String getSqdate() {
		return sqdate;
	}

	public void setSqdate(String sqdate) {
		this.sqdate = sqdate;
	}

	public String getZzdate() {
		return zzdate;
	}

	public void setZzdate(String zzdate) {
		this.zzdate = zzdate;
	}

	public Integer getEp_Id() {
		return ep_Id;
	}

	public void setEp_Id(Integer epId) {
		ep_Id = epId;
	}

	public String getEp_status() {
		return ep_status;
	}

	public void setEp_status(String epStatus) {
		ep_status = epStatus;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBefroestatus() {
		return befroestatus;
	}

	public void setBefroestatus(String befroestatus) {
		this.befroestatus = befroestatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
