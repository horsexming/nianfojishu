package com.task.entity.dmltry;

import java.io.Serializable;

public class Zhongjian  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String canshu; // 存储用户参数(不能超过3000)
	private String status;// 去判断(已下发/未下发)
	// 关联指纹表因为要获取特征值
	private Integer fingerprintMgid;
	// 关联设备AccessEquipment
	private Integer accessEquipmentid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCanshu() {
		return canshu;
	}

	public void setCanshu(String canshu) {
		this.canshu = canshu;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getFingerprintMgid() {
		return fingerprintMgid;
	}

	public void setFingerprintMgid(Integer fingerprintMgid) {
		this.fingerprintMgid = fingerprintMgid;
	}

	public Integer getAccessEquipmentid() {
		return accessEquipmentid;
	}

	public void setAccessEquipmentid(Integer accessEquipmentid) {
		this.accessEquipmentid = accessEquipmentid;
	}

}