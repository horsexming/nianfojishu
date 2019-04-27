package com.task.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.task.entity.singlecar.SingleCar;
import com.task.util.FieldMeta;

/***
 * 
 * @author 刘晓霆（表名:TA_AskForLeave）
 * 请假表
 * 
 */
public class AskForLeave  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer leaveId;// 请假Id编号
	@FieldMeta(name="请假类型")
	private String leaveType;// 请假类型(个人，代理)
	@FieldMeta(name=" 请假人")
	private String leavePerson;// 请假人
	@FieldMeta(name="请假人所在部门")
	private String leavePersonDept;// 请假人所在部门
	@FieldMeta(name="请假人工号")
	private String leavePersonCode;// 请假人工号
	private String leaveUserCardId;//出门人卡号
	@FieldMeta(name="请假开始时间")
	private String leaveStartDate;// 请假开始时间
	@FieldMeta(name="请假结束时间")
	private String leaveEndDate;// 请假结束时间
	@FieldMeta(name="请假天数")
	private Integer leaveDays;// 请假天数  
	@FieldMeta(name="请假小时数")
	private Float leaveHours;// 请假共计小时数
	@FieldMeta(name="假事类型")
	private String leaveTypeOf;// 假事类型
	@FieldMeta(name="请假缘由")
	private String leaveReason;// 请假缘由
	@FieldMeta(name="提交人")
	private String submitPerson;// 提交人
	private String submitPersonDept;// 提交人所在部门
	private String submitPersonCode;// 提交人工号
	@FieldMeta(name="申请时间")
	private String operationDate;// 操作时间
	@FieldMeta(name="审批状态")
	private String approvalStatus;// 审批状态
	private String approvalResult;// 出门结果（出门刷卡 未出门，请假出门，结束）
	@FieldMeta(name="出门时间")
	private String exitTime;//出门时间
	@FieldMeta(name="回门时间")
	private String returnTime;//回门时间
	private Integer epId;       //审批ID
	private String appayTag;//申请人级别（个人A，经理B，副总C，总经理D）
	private String gongchuPlace;//公出目的地
	
	private String huanxiustatus;//换休状态
	private Integer singleCarId;//用车单Id
	private String needCar;//是否用车（是,否）（临时存储）
	private SingleCar singleCar;//用车单（临时存储）
	
	private String leaveObjectType;//请假项目类型： 项目/生产/KVP/其他
	private String  leaveObjectNeirong;//请假项目内容
	private String  accessStatus;//门禁记录生成状态
	private String  updateTime;//修改时间
	@FieldMeta(name="费用承担部门")
	private String freeDepts;//费用承担部门
	private String freeDeptIds;
	@FieldMeta(name="预计人工成本")
	private Float rgcost;//人工费
	private String sqStatus;//销假状态;(每个请假只能申请销假一次);QxAskForLeave
	private String carPaiNum;//  请假进出门车牌
	
	private BigDecimal lat;//latitude;公出纬度
	private BigDecimal lng;//公出经度
	
	// getter和setter方法
	public Integer getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getLeavePerson() {
		return leavePerson;
	}

	public void setLeavePerson(String leavePerson) {
		this.leavePerson = leavePerson;
	}

	public String getLeavePersonDept() {
		return leavePersonDept;
	}

	public void setLeavePersonDept(String leavePersonDept) {
		this.leavePersonDept = leavePersonDept;
	}

	public String getLeavePersonCode() {
		return leavePersonCode;
	}

	public void setLeavePersonCode(String leavePersonCode) {
		this.leavePersonCode = leavePersonCode;
	}

	public String getLeaveStartDate() {
		return leaveStartDate;
	}

	public void setLeaveStartDate(String leaveStartDate) {
		this.leaveStartDate = leaveStartDate;
	}

	public String getLeaveEndDate() {
		return leaveEndDate;
	}

	public void setLeaveEndDate(String leaveEndDate) {
		this.leaveEndDate = leaveEndDate;
	}

	public Integer getLeaveDays() {
		return leaveDays;
	}

	public void setLeaveDays(Integer leaveDays) {
		this.leaveDays = leaveDays;
	}

	public Float getLeaveHours() {
		if(null!=leaveHours){
			return Float.parseFloat(String.format("%.1f", leaveHours));
		}
		return leaveHours;
	}

	public void setLeaveHours(Float leaveHours) {
		this.leaveHours = leaveHours;
	}

	public String getLeaveTypeOf() {
		return leaveTypeOf;
	}

	public void setLeaveTypeOf(String leaveTypeOf) {
		this.leaveTypeOf = leaveTypeOf;
	}

	public String getLeaveReason() {
		return leaveReason;
	}

	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}

	public String getSubmitPerson() {
		return submitPerson;
	}

	public void setSubmitPerson(String submitPerson) {
		this.submitPerson = submitPerson;
	}

	public String getSubmitPersonDept() {
		return submitPersonDept;
	}

	public void setSubmitPersonDept(String submitPersonDept) {
		this.submitPersonDept = submitPersonDept;
	}

	public String getSubmitPersonCode() {
		return submitPersonCode;
	}

	public void setSubmitPersonCode(String submitPersonCode) {
		this.submitPersonCode = submitPersonCode;
	}

	

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getApprovalResult() {
		return approvalResult;
	}

	public void setApprovalResult(String approvalResult) {
		this.approvalResult = approvalResult;
	}

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}

	public String getAppayTag() {
		return appayTag;
	}

	public void setAppayTag(String appayTag) {
		this.appayTag = appayTag;
	}

	public String getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}

	public String getGongchuPlace() {
		return gongchuPlace;
	}

	public void setGongchuPlace(String gongchuPlace) {
		this.gongchuPlace = gongchuPlace;
	}

	public String getExitTime() {
		return exitTime;
	}

	public void setExitTime(String exitTime) {
		this.exitTime = exitTime;
	}

	public String getLeaveUserCardId() {
		return leaveUserCardId;
	}

	public void setLeaveUserCardId(String leaveUserCardId) {
		this.leaveUserCardId = leaveUserCardId;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public String getHuanxiustatus() {
		return huanxiustatus;
	}

	public void setHuanxiustatus(String huanxiustatus) {
		this.huanxiustatus = huanxiustatus;
	}

	public String getNeedCar() {
		return needCar;
	}

	public void setNeedCar(String needCar) {
		this.needCar = needCar;
	}

	public Integer getSingleCarId() {
		return singleCarId;
	}

	public void setSingleCarId(Integer singleCarId) {
		this.singleCarId = singleCarId;
	}

	public SingleCar getSingleCar() {
		return singleCar;
	}

	public void setSingleCar(SingleCar singleCar) {
		this.singleCar = singleCar;
	}

	public String getLeaveObjectType() {
		return leaveObjectType;
	}

	public void setLeaveObjectType(String leaveObjectType) {
		this.leaveObjectType = leaveObjectType;
	}

	public String getLeaveObjectNeirong() {
		return leaveObjectNeirong;
	}

	public void setLeaveObjectNeirong(String leaveObjectNeirong) {
		this.leaveObjectNeirong = leaveObjectNeirong;
	}

	public String getAccessStatus() {
		return accessStatus;
	}

	public void setAccessStatus(String accessStatus) {
		this.accessStatus = accessStatus;
	}

	public String getCarPaiNum() {
		return carPaiNum;
	}

	public void setCarPaiNum(String carPaiNum) {
		this.carPaiNum = carPaiNum;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Float getRgcost() {
		return rgcost;
	}

	public void setRgcost(Float rgcost) {
		this.rgcost = rgcost;
	}

	public String getFreeDepts() {
		return freeDepts;
	}

	public void setFreeDepts(String freeDepts) {
		this.freeDepts = freeDepts;
	}

	public String getFreeDeptIds() {
		return freeDeptIds;
	}

	public void setFreeDeptIds(String freeDeptIds) {
		this.freeDeptIds = freeDeptIds;
	}

	public String getSqStatus() {
		return sqStatus;
	}

	public void setSqStatus(String sqStatus) {
		this.sqStatus = sqStatus;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLng() {
		return lng;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}

}
