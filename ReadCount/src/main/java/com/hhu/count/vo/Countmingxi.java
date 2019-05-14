/**
 * 
 */
package com.hhu.count.vo;

import java.util.Date;

/**
 * @author 年月日统计明细
 */
public class Countmingxi {

	private String readerID;
	private String reading;
	private String coursename;
	private String courseRead;
	private Date myrDate;
	private Date buildFirst;

	public String getReaderID() {
		return readerID;
	}

	public void setReaderID(String readerID) {
		this.readerID = readerID;
	}

	public String getReading() {
		return reading;
	}

	public void setReading(String reading) {
		this.reading = reading;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getCourseRead() {
		return courseRead;
	}

	public void setCourseRead(String courseRead) {
		this.courseRead = courseRead;
	}

	

	public Date getMyrDate() {
		return myrDate;
	}

	public void setMyrDate(Date myrDate) {
		this.myrDate = myrDate;
	}

	public Date getBuildFirst() {
		return buildFirst;
	}

	public void setBuildFirst(Date buildFirst) {
		this.buildFirst = buildFirst;
	}

	

}
