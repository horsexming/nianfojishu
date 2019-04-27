package com.task.entity;

/***
 * 个人每天考勤汇总表
 * @author 贾辉辉
 * @Date 2014-04-17
 * ta_hr_Attendance
 */
import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.task.util.FieldMeta;

public class Attendance  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer attendanceId;// 考勤Id主键.
	private Integer atPersonId;// person表Id
	private Integer userId;// Users表Id
	@FieldMeta(name = "姓名")
	private String personName;// 员工姓名
	@FieldMeta(name = "部门")
	private String deptName;// 部门名称
	@FieldMeta(name = "卡号")
	private String cardNo;// 卡号
	@FieldMeta(name = "工号")
	private String code;// 工号
	@FieldMeta(name = "打卡日期")
	private String dateTime;// 打卡日期 yyyy-MM-dd

	@FieldMeta(name = "申请上班时间")
	private String workDateTime;// 上午上班时间  (申请上班时间)
	private String workDateTimeXia;// 上午下班时间
	private String closingDateTimeShang;// 下午上班时间
	@FieldMeta(name = "申请下班时间")
	private String closingDateTime;// 下午下班时间 (申请下班时间)
	
	private String morningStatus;// 上午打卡状态（正常、迟到、未打卡、请假）
	private String afternoonStatus;// 下午打卡状态（正常、早退、未打卡、请假）
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
	
	private String tags;//标记(区分新旧算法，(新为1/旧为NUll/最新算法6))
	private String ExceptTags;//汇总异常标记(0：无异常，系统自动计算   1:有异常，需手动修改    2：已处理，不在重新计算此条信息 3:晚班需重新计算的5:自动汇总无请假加班算法)
	private String ExceptTagsNei;//异常原因(累加)
	private String mingXiLiu;//明细浏览(小时)
	private String mingXiJi;//明细计算(分钟)

	@FieldMeta(name = "当天签到信息")
	private String timeAll;//有效打卡时间
	private String show;//显示有效时间
	@FieldMeta(name = "审批状态")
	private String status;//审批状态
	private Integer epId;//流程Id
	@FieldMeta(name = "参考")
	private String moreJiesi;//当月第几次申请（特殊需求）
	private List<AttendanceFu> fu;//有效打卡时间
	private List<AttendanceTow> tow;//打卡记录
	
	private String qingjiaNumber;//请假单号(yt)
	
	@JSONField(serialize = false)
	public List<AttendanceFu> getFu() {
		return fu;
	}

	public void setFu(List<AttendanceFu> fu) {
		this.fu = fu;
	}
	
	public String getQingjiaNumber() {
		return qingjiaNumber;
	}

	public void setQingjiaNumber(String qingjiaNumber) {
		this.qingjiaNumber = qingjiaNumber;
	}

	public String getMoreJiesi() {
		return moreJiesi;
	}

	public void setMoreJiesi(String moreJiesi) {
		this.moreJiesi = moreJiesi;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}

	@JSONField(serialize = false)
	public List<AttendanceTow> getTow() {
		return tow;
	}

	public void setTow(List<AttendanceTow> tow) {
		this.tow = tow;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public Attendance() {
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
	 */
	public Attendance(Integer userId, String code, String personName, String deptName,
			String cardNo, String dateTime, String operationDate, String attendanceStatus,
			Integer lateTime, Integer earlyTime, Integer qijiaTime,
			Integer jiaBTime, Integer workTime, Integer workBiaoTime,
			Integer queqinTime, Integer banciId, String banciName, String tags,
			String timeAll) {
		super();
		this.userId = userId;
		this.code = code;
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
	}

//	添加所有
	public Attendance(Integer atPersonId, Integer userId,
			String personName, String deptName, String cardNo, String dateTime,
			String workDateTime, String workDateTimeXia,
			String closingDateTimeShang, String closingDateTime,
			String morningStatus, String afternoonStatus,
			String attendanceStatus, String operationDate, String more,
			Integer lateTime, Integer earlyTime, Integer qijiaTime,
			Integer jiaBTime, Integer workTime, Integer workBiaoTime,
			Integer queqinTime, Integer banciId, String banciName, String tags,
			String exceptTags, String exceptTagsNei, String mingXiLiu,
			String mingXiJi, String timeAll) {
		super();
		this.atPersonId = atPersonId;
		this.userId = userId;
		this.personName = personName;
		this.deptName = deptName;
		this.cardNo = cardNo;
		this.dateTime = dateTime;
		this.workDateTime = workDateTime;
		this.workDateTimeXia = workDateTimeXia;
		this.closingDateTimeShang = closingDateTimeShang;
		this.closingDateTime = closingDateTime;
		this.morningStatus = morningStatus;
		this.afternoonStatus = afternoonStatus;
		this.attendanceStatus = attendanceStatus;
		this.operationDate = operationDate;
		this.more = more;
		this.lateTime = lateTime;
		this.earlyTime = earlyTime;
		this.qijiaTime = qijiaTime;
		this.jiaBTime = jiaBTime;
		this.workTime = workTime;
		this.workBiaoTime = workBiaoTime;
		this.queqinTime = queqinTime;
		banci_Id = banciId;
		banci_Name = banciName;
		this.tags = tags;
		ExceptTags = exceptTags;
		ExceptTagsNei = exceptTagsNei;
		this.mingXiLiu = mingXiLiu;
		this.mingXiJi = mingXiJi;
		this.timeAll = timeAll;
	}

	// getter和setter方法
	public Integer getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Integer attendanceId) {
		this.attendanceId = attendanceId;
	}
	
	public Integer getAtPersonId() {
		return atPersonId;
	}

	public void setAtPersonId(Integer atPersonId) {
		this.atPersonId = atPersonId;
	}

	public String getTimeAll() {
		return timeAll;
	}

	public void setTimeAll(String timeAll) {
		this.timeAll = timeAll;
	}

	public String getWorkDateTimeXia() {
		return workDateTimeXia;
	}

	public void setWorkDateTimeXia(String workDateTimeXia) {
		this.workDateTimeXia = workDateTimeXia;
	}

	public String getClosingDateTimeShang() {
		return closingDateTimeShang;
	}

	public void setClosingDateTimeShang(String closingDateTimeShang) {
		this.closingDateTimeShang = closingDateTimeShang;
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

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Integer getWorkTime() {
		return workTime;
	}
	
	public void setWorkTime(Integer workTime) {
		this.workTime = workTime;
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

//	public Integer getAtPersonId() {
//		return atPersonId;
//	}
//
//	public void setAtPersonId(Integer atPersonId) {
//		this.atPersonId = atPersonId;
//	}

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

	public String getMorningStatus() {
		return morningStatus;
	}

	public void setMorningStatus(String morningStatus) {
		this.morningStatus = morningStatus;
	}

	public String getAfternoonStatus() {
		return afternoonStatus;
	}

	public void setAfternoonStatus(String afternoonStatus) {
		this.afternoonStatus = afternoonStatus;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

}
