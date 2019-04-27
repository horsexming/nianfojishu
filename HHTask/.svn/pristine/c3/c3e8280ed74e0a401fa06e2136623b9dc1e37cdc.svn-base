package com.task.entity.menjin;

import java.io.Serializable;

/**
 * 绑定关系表 2016-01-18
 * 
 * @author Li_Cong 表名 ta_mj_DoorBangDing 管理内部员工绑定门权限
 */
public class DoorBangDing implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer fk_user_id;// userID
	private Integer fk_acEq_id;// 门禁设备名称ID
	private Integer fk_security_id;// 使用记录ID(指纹记录ID)
	private Integer number;// 设备number
	private String status;// 内外状态（内/外）指纹机（待采集/待下发/已下发/待更新）
	private String timeIsTrue;// 是否有时间段限制

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getFk_user_id() {
		return fk_user_id;
	}

	public void setFk_user_id(Integer fkUserId) {
		fk_user_id = fkUserId;
	}

	public Integer getFk_acEq_id() {
		return fk_acEq_id;
	}

	public void setFk_acEq_id(Integer fkAcEqId) {
		fk_acEq_id = fkAcEqId;
	}

	public Integer getFk_security_id() {
		return fk_security_id;
	}

	public void setFk_security_id(Integer fkSecurityId) {
		fk_security_id = fkSecurityId;
	}

	public String getTimeIsTrue() {
		return timeIsTrue;
	}

	public void setTimeIsTrue(String timeIsTrue) {
		this.timeIsTrue = timeIsTrue;
	}
}
