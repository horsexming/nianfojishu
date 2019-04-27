package com.task.entity;

/***
 * 考勤汇总时段表(考勤附表)
 * @author 李聪
 * @Date 2017-10-24
 * ta_hr_AttendanceFu
 */
import java.io.Serializable;

public class AttendanceFu  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;// 考勤Id主键
	private Integer attendanceId;// attendance表Id
	private Integer userId;// Users表Id
	private String personName;// 员工姓名
	private String deptName;// 部门名称
	private String cardNo;// 卡号
	private String dateTime;// 打卡日期 yyyy-MM-dd
	
	private String workDateTime;// 上班时间
	private String closingDateTime;// 下班时间
	
	private String attendanceStatus;// 全天打卡状态（正常、迟到、早退、迟退、迟未、正未、入职、未刷卡、请假、旷工）
	
	private String operationDate;// 最后一次操作时间(计算时间、修改时间)
	private String more;// 备注说明
	
	private Integer lateTime;//迟到时长
	private Integer earlyTime;//早退时长
	private Integer qijiaTime;//请假时长
	private Integer jiaBTime;//加班时长
	private Integer workTime;//实际工作时长
	private Integer workBiaoTime;//当天班次工作时长(用于计算天数)
	private Integer queqinTime;//旷工时长
	
	private Integer banci_Id;//班次Id
	private String banci_Name;//班次名称
	
	private String tags;//标记(区分新旧算法，(新为1/旧为NUll))
	private String ExceptTags;//汇总异常标记(0：无异常，系统自动计算   1:有异常，需手动修改    2：已处理，不在重新计算此条信息 3:晚班需重新计算的5:自动汇总无请假加班算法)
	private String ExceptTagsNei;//异常原因(累加)
	private String mingXiLiu;//明细浏览(小时)
	private String mingXiJi;//明细计算(分钟)
	
	private String timeAll;//有效打卡时间
	
	private Integer duan;//班次第几时段
	private String dayType;//当日/次日

	public AttendanceFu() {
		super();
	}

	/**
	 * 添加汇总信息 标识 6
	 * @param userId 用户ID
	 * @param personName 用户姓名
	 * @param deptName 用户部门
	 * @param cardNo 用户卡号
	 * @param dateTime 考勤日期
	 * @param operationDate 最新一次计算时间
	 * @param attendanceStatus 当天状态
	 * @param lateTime 迟到时间
	 * @param earlyTime 早退时间
	 * @param qijiaTime 请假时间
	 * @param jiaBTime 加班时间
	 * @param workTime 工作时间
	 * @param workBiaoTime 工作标准时间
	 * @param queqinTime 缺勤时间
	 * @param banciId 班次ID
	 * @param banciName 班次名称
	 * @param tags 算法类型
	 * @param timeAll 打卡时间（每次）
	 * @param duan 第几段
	 * @param dayType 当日/次日
	 */
	public AttendanceFu(Integer attendanceId, Integer userId, String personName, String deptName,
			String cardNo, String dateTime, String operationDate, String attendanceStatus,
			Integer lateTime, Integer earlyTime, Integer qijiaTime,
			Integer jiaBTime, Integer workTime, Integer workBiaoTime,
			Integer queqinTime, Integer banciId, String banciName, String tags,
			String timeAll,Integer duan,String dayType) {
		super();
		this.attendanceId = attendanceId;
		this.userId = userId;
		this.userId = userId;
		this.personName = personName;
		this.deptName = deptName;
		this.cardNo = cardNo;
		this.dateTime = dateTime;
		this.operationDate = operationDate;
		this.attendanceStatus = attendanceStatus;
		this.lateTime = lateTime;
		this.earlyTime = earlyTime;
		this.qijiaTime = qijiaTime;
		this.jiaBTime = jiaBTime;
		this.workTime = workTime;
		this.workBiaoTime = workBiaoTime;
		this.queqinTime = queqinTime;
		this.banci_Id = banciId;
		this.banci_Name = banciName;
		this.tags = tags;
		this.timeAll = timeAll;
		this.duan = duan;
		this.dayType = dayType;
	} 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Integer attendanceId) {
		this.attendanceId = attendanceId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getWorkDateTime() {
		return workDateTime;
	}

	public void setWorkDateTime(String workDateTime) {
		this.workDateTime = workDateTime;
	}

	public String getClosingDateTime() {
		return closingDateTime;
	}

	public void setClosingDateTime(String closingDateTime) {
		this.closingDateTime = closingDateTime;
	}

	public String getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	public String getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}

	public Integer getLateTime() {
		return lateTime;
	}

	public void setLateTime(Integer lateTime) {
		this.lateTime = lateTime;
	}

	public Integer getEarlyTime() {
		return earlyTime;
	}

	public void setEarlyTime(Integer earlyTime) {
		this.earlyTime = earlyTime;
	}

	public Integer getQijiaTime() {
		return qijiaTime;
	}

	public void setQijiaTime(Integer qijiaTime) {
		this.qijiaTime = qijiaTime;
	}

	public Integer getJiaBTime() {
		return jiaBTime;
	}

	public void setJiaBTime(Integer jiaBTime) {
		this.jiaBTime = jiaBTime;
	}

	public Integer getWorkTime() {
		return workTime;
	}

	public void setWorkTime(Integer workTime) {
		this.workTime = workTime;
	}

	public Integer getWorkBiaoTime() {
		return workBiaoTime;
	}

	public void setWorkBiaoTime(Integer workBiaoTime) {
		this.workBiaoTime = workBiaoTime;
	}

	public Integer getQueqinTime() {
		return queqinTime;
	}

	public void setQueqinTime(Integer queqinTime) {
		this.queqinTime = queqinTime;
	}

	public Integer getBanci_Id() {
		return banci_Id;
	}

	public void setBanci_Id(Integer banciId) {
		banci_Id = banciId;
	}

	public String getBanci_Name() {
		return banci_Name;
	}

	public void setBanci_Name(String banciName) {
		banci_Name = banciName;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getExceptTags() {
		return ExceptTags;
	}

	public void setExceptTags(String exceptTags) {
		ExceptTags = exceptTags;
	}

	public String getExceptTagsNei() {
		return ExceptTagsNei;
	}

	public void setExceptTagsNei(String exceptTagsNei) {
		ExceptTagsNei = exceptTagsNei;
	}

	public String getMingXiLiu() {
		return mingXiLiu;
	}

	public void setMingXiLiu(String mingXiLiu) {
		this.mingXiLiu = mingXiLiu;
	}

	public String getMingXiJi() {
		return mingXiJi;
	}

	public void setMingXiJi(String mingXiJi) {
		this.mingXiJi = mingXiJi;
	}

	public String getTimeAll() {
		return timeAll;
	}

	public void setTimeAll(String timeAll) {
		this.timeAll = timeAll;
	}

	public Integer getDuan() {
		return duan;
	}

	public void setDuan(Integer duan) {
		this.duan = duan;
	}

	public String getDayType() {
		return dayType;
	}

	public void setDayType(String dayType) {
		this.dayType = dayType;
	}

}