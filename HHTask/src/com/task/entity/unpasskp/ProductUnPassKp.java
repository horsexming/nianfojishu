package com.task.entity.unpasskp;

import java.io.Serializable;

/**
 * 开票失败数量（ta_ProductUnPassKp）
 * @author txb
 *
 */
public class ProductUnPassKp  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 private Integer id;
 private String cusName;//客户名称
 private String markId;//件号
 private String selfCard;//批次
 private String proName;//零件名称
 private Integer hkSellStaId;//开票明细id
 private String odrerNumber;//订单编号
 private String hkSellSendId;//送货单号
 private Float totalCount;//总数量
 private Float reworkCount;//返修数量
 private Float rebuildCount;//重新生产数量
 private Float cutCount;//废除订单数量
 private Float okCount;//合格数量（因为开票要数量全时开所以有些合格产品需要等数量补齐才可以一起开票）
 private String status;//状态（初始,已确认）
 private String addTime;//添加时间
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getHkSellStaId() {
	return hkSellStaId;
}
public void setHkSellStaId(Integer hkSellStaId) {
	this.hkSellStaId = hkSellStaId;
}
public String getOdrerNumber() {
	return odrerNumber;
}
public void setOdrerNumber(String odrerNumber) {
	this.odrerNumber = odrerNumber;
}
public Float getTotalCount() {
	return totalCount;
}
public void setTotalCount(Float totalCount) {
	this.totalCount = totalCount;
}
public Float getReworkCount() {
	return reworkCount;
}
public void setReworkCount(Float reworkCount) {
	this.reworkCount = reworkCount;
}
public Float getRebuildCount() {
	return rebuildCount;
}
public void setRebuildCount(Float rebuildCount) {
	this.rebuildCount = rebuildCount;
}
public Float getCutCount() {
	return cutCount;
}
public void setCutCount(Float cutCount) {
	this.cutCount = cutCount;
}
public Float getOkCount() {
	return okCount;
}
public void setOkCount(Float okCount) {
	this.okCount = okCount;
}
public String getAddTime() {
	return addTime;
}
public void setAddTime(String addTime) {
	this.addTime = addTime;
}
public String getMarkId() {
	return markId;
}
public void setMarkId(String markId) {
	this.markId = markId;
}
public String getCusName() {
	return cusName;
}
public void setCusName(String cusName) {
	this.cusName = cusName;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getProName() {
	return proName;
}
public void setProName(String proName) {
	this.proName = proName;
}
public String getSelfCard() {
	return selfCard;
}
public void setSelfCard(String selfCard) {
	this.selfCard = selfCard;
}
public String getHkSellSendId() {
	return hkSellSendId;
}
public void setHkSellSendId(String hkSellSendId) {
	this.hkSellSendId = hkSellSendId;
}
 
}
