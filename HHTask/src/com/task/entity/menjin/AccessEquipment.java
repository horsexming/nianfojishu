package com.task.entity.menjin;

import java.io.Serializable;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.task.entity.UserFacialFeatures;
import com.task.entity.UserFacialInfor;
import com.task.entity.Users;
import com.task.entity.onemark.OneLight;

/**
 * 门禁设备管理表
 * 
 * @author Li_Cong 表名 ta_mj_AccessEquipment 添加门禁设备管理设备
 */
public class AccessEquipment implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String equipmentName;// 设备名称
	private String equipmentNum;// 设备编号
	private String equipmentSupplier;// 设备供应商
	private String equipmentOutIn;// 设备用途(进门/出门/其他)
	private String equipmentIP;// 设备IP equipmentIP
	private String equipmentPort;// 设备端口
	private String equipmentLocation;// 设备摆放位置
	private String equipmentDaoType;// (人行道/车行道/档案/男/女/档案柜/灯/库位/四色灯/卷帘门/智能水阀/面部识别/NB-IoT*/指纹设备)
	private Integer simCardNum;// SIM卡唯一编号（档案柜专用）
	private String equipmentType;// 门类型（标识）
	private String equipmentSecurity;// 是否需要验证码（是/否）
	private String addTime;// 添加时间
	private String updateTime;// 修改时间
	private String adminCardId;// 管理员卡号
	private String adminStatus;// 管理员卡绑定状态(待添加/待刷卡/已设置)
	private String linYanZ;// 临时进出验证码
	private String state;// 当前状态(打开/关闭)
	private String operationNote;// 操作记录（名字，操作，时间）；

	private Set<Users> users;// 用户（多对多）
	private Set<OneLight> oneLights;// 灯泡（多对一）
	private Set<AccessTime> accessTimes;// 可进时间段(多对一)
	private Set<UserFacialInfor> facialInfors;// 用户（多对多）
	private Set<UserFacialFeatures> facialFeatures;// 考勤机用户（多对多）
	private Integer fingId;// 指纹ID
	private String isTrueKao;// 是否允许刷卡考勤(是/否)
	private String isXungeng;// 是否巡更(是/否)
	private Integer nbStage;// UDP通讯状态
	private String guiType;// 1:选择柜子类型(A0/A1/A2)、3:当前开的柜子开柜指令(1~45)
							// 档案柜时候记录："1"为取档
	// 关联设备上传出入记录
	// get set

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getGuiType() {
		return guiType;
	}

	public void setGuiType(String guiType) {
		this.guiType = guiType;
	}

	public Integer getNbStage() {
		return nbStage;
	}

	public void setNbStage(Integer nbStage) {
		this.nbStage = nbStage;
	}

	@JSONField(serialize = false)
	public Set<UserFacialInfor> getFacialInfors() {
		return facialInfors;
	}

	public void setFacialInfors(Set<UserFacialInfor> facialInfors) {
		this.facialInfors = facialInfors;
	}

	@JSONField(serialize = false)
	public Set<UserFacialFeatures> getFacialFeatures() {
		return facialFeatures;
	}

	public void setFacialFeatures(Set<UserFacialFeatures> facialFeatures) {
		this.facialFeatures = facialFeatures;
	}

	public String getOperationNote() {
		return operationNote;
	}

	public void setOperationNote(String operationNote) {
		this.operationNote = operationNote;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsTrueKao() {
		return isTrueKao;
	}

	public void setIsTrueKao(String isTrueKao) {
		this.isTrueKao = isTrueKao;
	}

	public Integer getFingId() {
		return fingId;
	}

	public void setFingId(Integer fingId) {
		this.fingId = fingId;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getEquipmentNum() {
		return equipmentNum;
	}

	public void setEquipmentNum(String equipmentNum) {
		this.equipmentNum = equipmentNum;
	}

	public String getEquipmentSupplier() {
		return equipmentSupplier;
	}

	public void setEquipmentSupplier(String equipmentSupplier) {
		this.equipmentSupplier = equipmentSupplier;
	}

	public String getEquipmentOutIn() {
		return equipmentOutIn;
	}

	public void setEquipmentOutIn(String equipmentOutIn) {
		this.equipmentOutIn = equipmentOutIn;
	}

	public String getEquipmentIP() {
		return equipmentIP;
	}

	public void setEquipmentIP(String equipmentIP) {
		this.equipmentIP = equipmentIP;
	}

	public String getEquipmentPort() {
		return equipmentPort;
	}

	public void setEquipmentPort(String equipmentPort) {
		this.equipmentPort = equipmentPort;
	}

	public String getEquipmentLocation() {
		return equipmentLocation;
	}

	public void setEquipmentLocation(String equipmentLocation) {
		this.equipmentLocation = equipmentLocation;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getEquipmentDaoType() {
		return equipmentDaoType;
	}

	public void setEquipmentDaoType(String equipmentDaoType) {
		this.equipmentDaoType = equipmentDaoType;
	}

	@JSONField(serialize = false)
	public Set<Users> getUsers() {
		return users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public String getEquipmentSecurity() {
		return equipmentSecurity;
	}

	public void setEquipmentSecurity(String equipmentSecurity) {
		this.equipmentSecurity = equipmentSecurity;
	}

	public Integer getSimCardNum() {
		return simCardNum;
	}

	public void setSimCardNum(Integer simCardNum) {
		this.simCardNum = simCardNum;
	}

	public String getAdminCardId() {
		return adminCardId;
	}

	public void setAdminCardId(String adminCardId) {
		this.adminCardId = adminCardId;
	}

	public String getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(String adminStatus) {
		this.adminStatus = adminStatus;
	}

	@JSONField(serialize = false)
	public Set<AccessTime> getAccessTimes() {
		return accessTimes;
	}

	public void setAccessTimes(Set<AccessTime> accessTimes) {
		this.accessTimes = accessTimes;
	}

	public String getLinYanZ() {
		return linYanZ;
	}

	public void setLinYanZ(String linYanZ) {
		this.linYanZ = linYanZ;
	}

	@JSONField(serialize = false)
	public Set<OneLight> getOneLights() {
		return oneLights;
	}

	public void setOneLights(Set<OneLight> oneLights) {
		this.oneLights = oneLights;
	}

	public String getIsXungeng() {
		return isXungeng;
	}

	public void setIsXungeng(String isXungeng) {
		this.isXungeng = isXungeng;
	}

}
