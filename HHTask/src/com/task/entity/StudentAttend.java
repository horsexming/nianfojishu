package com.task.entity;

import java.io.Serializable;

/**
 * 学员登记表:(ta_StudentAttend)
 * @author 王晓飞
 *
 */
public class StudentAttend implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer userId;//userId即学员Id
	private String userName;//学员名称
	private String usercode;//学号
	private Integer lId;//课时Id;
	private String ldate;//课时日期;
	private String addtime;//登记时间;
	private Integer lnumber;//课时序号;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public Integer getlId() {
		return lId;
	}
	public void setlId(Integer lId) {
		this.lId = lId;
	}
	public String getLdate() {
		return ldate;
	}
	public void setLdate(String ldate) {
		this.ldate = ldate;
	}
	
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public Integer getLnumber() {
		return lnumber;
	}
	public void setLnumber(Integer lnumber) {
		this.lnumber = lnumber;
	}

	
	
	
	
}
