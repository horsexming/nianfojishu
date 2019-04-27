package com.task.entity.menjin;

import java.io.Serializable;

/**
 * 门禁历史记录表
 * 
 * @author Li_Cong 表名 ta_mj_AccessRecords 所有进出记录(后台自动添加)
 */
public class AccessRecords implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String recordType;// 开门验证类型（车牌/验证码/员工卡）
	private String recordContents;// 开门验证内容(记录的验证码，卡号或车牌)
	private String recordisIn;// 是否内部车辆（内部/来访/常访）
	private String recordStatus;// 开门状态（已识别/已开门/已通过）
	private String openTime;// 开门时间
	private String enterTime;// 通过时间
	private String openType;// 开门类型（进门/出门）
	private String equipmentDaoType;// (人行道/车行道)
	private String outOfPosition;// 进出位置
	private Integer asWeam_id;// 开门摄像头ID
	private Integer asEqt_id;// 对应设备ID
	private String asWeam_ip;// 开门摄像头IP
	private String asEqt_ip;// 对应设备IP
	private String addTime;// 添加时间
	private String recordPass;// 通过方式（由外向内/由内向外）（CC/DD）
	private String inCode;// 员工工号
	private String inDept;// 员工部门
	private Integer inId;// 内部员工Id
	private String inName;// 员工姓名
	private String inmarkId;// 进出内部员工卡号
	private String waitCheck;// 待检（待检查，已检查）(紧急)
	private String checkName;// 检车人名称卡号
	private String urgentCar;// (紧急)
	private Integer banciId;// 是否要添加考勤(不为空代表有班次，有班次就代表要添加考勤)
	private String isKong;// 此车卡号需要控制灯
	// 请假加班id
	private String entityName;// 对应实体类名称
	private Integer entityId;// 对应实体类id

	// get set
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getRecordContents() {
		return recordContents;
	}

	public void setRecordContents(String recordContents) {
		this.recordContents = recordContents;
	}

	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(String enterTime) {
		this.enterTime = enterTime;
	}

	public String getOpenType() {
		return openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}

	public String getIsKong() {
		return isKong;
	}

	public void setIsKong(String isKong) {
		this.isKong = isKong;
	}

	public String getOutOfPosition() {
		return outOfPosition;
	}

	public void setOutOfPosition(String outOfPosition) {
		this.outOfPosition = outOfPosition;
	}

	public Integer getAsWeam_id() {
		return asWeam_id;
	}

	public void setAsWeam_id(Integer asWeamId) {
		asWeam_id = asWeamId;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getAsEqt_id() {
		return asEqt_id;
	}

	public void setAsEqt_id(Integer asEqtId) {
		asEqt_id = asEqtId;
	}

	public String getAsWeam_ip() {
		return asWeam_ip;
	}

	public void setAsWeam_ip(String asWeamIp) {
		asWeam_ip = asWeamIp;
	}

	public String getAsEqt_ip() {
		return asEqt_ip;
	}

	public void setAsEqt_ip(String asEqtIp) {
		asEqt_ip = asEqtIp;
	}

	public String getRecordisIn() {
		return recordisIn;
	}

	public void setRecordisIn(String recordisIn) {
		this.recordisIn = recordisIn;
	}

	public String getEquipmentDaoType() {
		return equipmentDaoType;
	}

	public void setEquipmentDaoType(String equipmentDaoType) {
		this.equipmentDaoType = equipmentDaoType;
	}

	public String getRecordPass() {
		return recordPass;
	}

	public void setRecordPass(String recordPass) {
		this.recordPass = recordPass;
	}

	public String getInCode() {
		return inCode;
	}

	public void setInCode(String inCode) {
		this.inCode = inCode;
	}

	public String getInDept() {
		return inDept;
	}

	public void setInDept(String inDept) {
		this.inDept = inDept;
	}

	public Integer getInId() {
		return inId;
	}

	public void setInId(Integer inId) {
		this.inId = inId;
	}

	public String getInName() {
		return inName;
	}

	public void setInName(String inName) {
		this.inName = inName;
	}

	public String getWaitCheck() {
		return waitCheck;
	}

	public void setWaitCheck(String waitCheck) {
		this.waitCheck = waitCheck;
	}

	public String getUrgentCar() {
		return urgentCar;
	}

	public void setUrgentCar(String urgentCar) {
		this.urgentCar = urgentCar;
	}

	public String getInmarkId() {
		return inmarkId;
	}

	public void setInmarkId(String inmarkId) {
		this.inmarkId = inmarkId;
	}

	public Integer getBanciId() {
		return banciId;
	}

	public void setBanciId(Integer banciId) {
		this.banciId = banciId;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Integer getEntityId() {
		return entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

}
