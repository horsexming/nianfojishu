package com.task.entity.jxkh;

import java.io.Serializable;

/**
 * 年度改善自选(ta_YearImprove)
 * @author wxf
 *
 */
public class YearImprove implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String dept;//部门
	private String name;//姓名
	private String post;//职务
	private String improveInfor;//改善项
	private Integer ndxs;//难度系数
	private Integer jyx;//经营性
	private Integer tzd;//挑战度
	private String months;//月份
	
	
	private String addTime;//添加时间
	private String addUsersName;//添加人
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getImproveInfor() {
		return improveInfor;
	}
	public void setImproveInfor(String improveInfor) {
		this.improveInfor = improveInfor;
	}
	
	public Integer getNdxs() {
		return ndxs;
	}
	public void setNdxs(Integer ndxs) {
		this.ndxs = ndxs;
	}
	public Integer getJyx() {
		return jyx;
	}
	public void setJyx(Integer jyx) {
		this.jyx = jyx;
	}
	public Integer getTzd() {
		return tzd;
	}
	public void setTzd(Integer tzd) {
		this.tzd = tzd;
	}
	public String getMonths() {
		return months;
	}
	public void setMonths(String months) {
		this.months = months;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getAddUsersName() {
		return addUsersName;
	}
	public void setAddUsersName(String addUsersName) {
		this.addUsersName = addUsersName;
	}
	
	
}
