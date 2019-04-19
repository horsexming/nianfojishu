package com.hhu.count.vo;

import java.util.Date;

/* 
 *用户首页显示vo
 * */
public class UserCourseVO {
	private String id; // 课程ID
	private String uid; // 读者ID
	private String coursename; // 课程名
	private String writerid; // 发布ID
	private String writer; // 发布者
	private String courseRead; // 课程量
	private String reading; // 阅读量
	private Date publishTime; // 发布时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getWriterid() {
		return writerid;
	}

	public void setWriterid(String writerid) {
		this.writerid = writerid;
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

	public String getReading() {
		return reading;
	}

	public void setReading(String reading) {
		this.reading = reading;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

}
