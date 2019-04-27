package com.task.entity;

import java.io.Serializable;


/***
 * 加班申请明细表
 * 
 * @表名 overtime
 * @author WCY
 * 
 */
public class OvertimeDetail  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;// 主键
	
	private String type; //加班类型
	private String startTime; //加班开始时间
	private String endTime;  //加班结束时间
	private Integer xiuxi;  //中途休息时长（分钟）
	private Integer hour;  //加班总小时数
	private Integer minutes;//加班总分钟数
	
	private String oldStart;//旧的加班开始时间
	private String oldEnd;//旧的加班结束时间
	private Overtime overtime;//加班表
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getXiuxi() {
		return xiuxi;
	}
	public void setXiuxi(Integer xiuxi) {
		this.xiuxi = xiuxi;
	}
	public Integer getHour() {
		return hour;
	}
	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Integer getMinutes() {
		return minutes;
	}
	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}
	public Overtime getOvertime() {
		return overtime;
	}
	public void setOvertime(Overtime overtime) {
		this.overtime = overtime;
	}
	public String getOldStart() {
		return oldStart;
	}
	public void setOldStart(String oldStart) {
		this.oldStart = oldStart;
	}
	public String getOldEnd() {
		return oldEnd;
	}
	public void setOldEnd(String oldEnd) {
		this.oldEnd = oldEnd;
	}
	
}
