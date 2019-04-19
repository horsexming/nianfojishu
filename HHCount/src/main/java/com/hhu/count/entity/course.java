package com.hhu.count.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/*
 * 课程表
 * */
public class course {

	private String id;
	private String coursename; // 课程名称
	private String coursetext; // 课程内容
	private String writer; // 作者
	private String courseRead; // 每日应读量
	private String writerid; // 作者id
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createtime; // 提交时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date publishTime; // 发布时间
	private String state; // 发布状态
	private String CoursePower; // 课程权限
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date stopTime; // 停止时间
	private Date firstTime; // 第一次发布时间
	private String courseState;

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

	public String getCoursetext() {
		return coursetext;
	}

	public void setCoursetext(String coursetext) {
		this.coursetext = coursetext;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getCourseRead() {
		return courseRead;
	}

	public void setCourseRead(String courseRead) {
		this.courseRead = courseRead;
	}

	public String getWriterid() {
		return writerid;
	}

	public void setWriterid(String writerid) {
		this.writerid = writerid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getCoursePower() {
		return CoursePower;
	}

	public void setCoursePower(String coursePower) {
		CoursePower = coursePower;
	}

	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	public String getCourseState() {
		return courseState;
	}

	public void setCourseState(String courseState) {
		this.courseState = courseState;
	}

	public Date getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(Date firstTime) {
		this.firstTime = firstTime;
	}
	
	

	

}
