package com.task.entity.menjin;

import java.io.Serializable;

/**
 * 库位凭证表(用于开仓区门)
 * 
 * @author Li_Cong 2016-11-17 表名 ta_mj_WarehouseCertificate 记录宾客来访记录以及员工请假记录
 */
public class WarehouseCertificate implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer wareAppId;// 申请Id
	private Integer warehouseId;// 库位ID
	private String warehouseName;// 库位编号
	private String useStatus;// 使用状态(待开门/待关门/待确认(确认实际数量)//已失效)(检验：如果合格数量不等于最大数，状态为：不合格(申请不合格流程))
	private String depositTake;// 存、取、检验
	private String type;// 操作类型(成品/零件/工装)
	private Float number;// 此仓区最大可操作数量
	private Float actualNumber;// 实际操作数量
	private String addTime;// 添加时间
	private String openTime;// 开门时间
	private String closeTime;// 关门时间
	private String querenTime;// 确认时间

	// get set
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getActualNumber() {
		return actualNumber;
	}

	public void setActualNumber(Float actualNumber) {
		this.actualNumber = actualNumber;
	}

	public String getQuerenTime() {
		return querenTime;
	}

	public void setQuerenTime(String querenTime) {
		this.querenTime = querenTime;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	public Integer getWareAppId() {
		return wareAppId;
	}

	public void setWareAppId(Integer wareAppId) {
		this.wareAppId = wareAppId;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public String getDepositTake() {
		return depositTake;
	}

	public void setDepositTake(String depositTake) {
		this.depositTake = depositTake;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Float getNumber() {
		return number;
	}

	public void setNumber(Float number) {
		this.number = number;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

}
