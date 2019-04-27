package com.task.entity;

import java.io.Serializable;

/**
 * @author 贾辉辉     每天出勤率汇总表
 * @Date 2014-04-26
 *	ta_hr_AttendanceCount
 * 
 */
public class AttendanceCount  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;// 考勤统计Id
	private String cardDateTime;// 考勤日期 yyyy-MM-dd
	private Integer normalAM;// 上午正常人次
	private Integer normalPM;// 下午正常人次
	private Integer normalDate;// 当天正常打卡人次
	private Integer lateCount;// 迟到
	private Integer leaveEarlyCount;// 早退
	private Integer askForLeaveCount;// 请假
	private Integer notCardCount;// 未打卡
	private Integer kuangGongCount;//旷工人次
	private Integer totalAttendance;// 当天在职总人数
	
	private Float attendance;// 正常出勤率（上午正常人次/总数人）
	private Integer banci_id;// 班次id
	private String banci_name;//班次名称
	private String operationDate;//操作时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCardDateTime() {
		return cardDateTime;
	}
	public void setCardDateTime(String cardDateTime) {
		this.cardDateTime = cardDateTime;
	}
	public Integer getNormalAM() {
		return normalAM;
	}
	public void setNormalAM(Integer normalAM) {
		this.normalAM = normalAM;
	}
	
	public Integer getLateCount() {
		return lateCount;
	}
	public void setLateCount(Integer lateCount) {
		this.lateCount = lateCount;
	}
	public Integer getLeaveEarlyCount() {
		return leaveEarlyCount;
	}
	public void setLeaveEarlyCount(Integer leaveEarlyCount) {
		this.leaveEarlyCount = leaveEarlyCount;
	}
	public Integer getAskForLeaveCount() {
		return askForLeaveCount;
	}
	public void setAskForLeaveCount(Integer askForLeaveCount) {
		this.askForLeaveCount = askForLeaveCount;
	}
	public Integer getNotCardCount() {
		return notCardCount;
	}
	public void setNotCardCount(Integer notCardCount) {
		this.notCardCount = notCardCount;
	}
	public Integer getKuangGongCount() {
		return kuangGongCount;
	}
	public void setKuangGongCount(Integer kuangGongCount) {
		this.kuangGongCount = kuangGongCount;
	}
	public Integer getTotalAttendance() {
		return totalAttendance;
	}
	public void setTotalAttendance(Integer totalAttendance) {
		this.totalAttendance = totalAttendance;
	}
	public Float getAttendance() {
		if(null!=attendance){
			return Float.parseFloat(String.format("%.4f", attendance));
		}
		return this.attendance;
	}
	public void setAttendance(Float attendance) {
		this.attendance = attendance;
	}
	public String getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}
	public Integer getNormalPM() {
		return normalPM;
	}
	public void setNormalPM(Integer normalPM) {
		this.normalPM = normalPM;
	}
	public Integer getNormalDate() {
		return normalDate;
	}
	public void setNormalDate(Integer normalDate) {
		this.normalDate = normalDate;
	}
	public Integer getBanci_id() {
		return banci_id;
	}
	public void setBanci_id(Integer banciId) {
		banci_id = banciId;
	}
	public String getBanci_name() {
		return banci_name;
	}
	public void setBanci_name(String banciName) {
		banci_name = banciName;
	}
	

	
	
	// getter和setter方法
	
}