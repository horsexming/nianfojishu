package com.hhu.count.vo;

import java.util.Date;

/*
 * 为model提供返回类型
 * 查询读者阅读记录
 * */
public class UserReadVo {
	private String id; // 课程id
	private String rid; // 阅读记录id
	private String coursename; // 课程名称
	private String writer; // 课程作者
	private String uid; // 用户id
	private Date publishTime; // 发布时间
	private String courseRead; // 课程量
	private String reading; // 每日应读量
	private String state; // 课程状态
	private String CoursePower; // 权限绑定
	private Integer myrDa; //天数
	private Date myrDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getCourseRead() {
		return courseRead;
	}

	public void setCourseRead(String courseRead) {
		this.courseRead = courseRead;
	}

	public String getReading() {
		return reading;
	}

	public void setReading(String reading) {
		this.reading = reading;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCoursePower() {
		return CoursePower;
	}

	public void setCoursePower(String coursePower) {
		CoursePower = coursePower;
	}

	public Integer getMyrDa() {
		return myrDa;
	}

	public void setMyrDa(Integer myrDa) {
		this.myrDa = myrDa;
	}

	public Date getMyrDate() {
		return myrDate;
	}

	public void setMyrDate(Date myrDate) {
		this.myrDate = myrDate;
	}

	

		

}
