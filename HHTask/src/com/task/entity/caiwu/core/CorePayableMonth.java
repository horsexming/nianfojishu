package com.task.entity.caiwu.core;

import java.io.Serializable;

import com.task.util.FieldMeta;

/**
 * 供应商月度对账单(ta_fin_caiwu_CorePayableMonth)
 * 
 * @author 刘培
 * 
 */
public class CorePayableMonth implements Serializable{
	private Integer id;
	private String supplierName;// 收款单位(供应商名称)
	private Integer supplierId;// 供应商id

	private String recNumber;// 对账编号(RyyyyMM00001)

	private Float yufukuanJine;// 预付款金额
	@FieldMeta(name = "应付款金额")
	private Double yingfukuanJine;// 应付款金额
	private Double realfukuanJine;// 已付款金额
	private Double weifukuanJine;// 未付款金额

	private String jzMonth;// 记账月份(需要结合公司账期表时间内)
	private Integer fukuanZq;// 付款周期(即付、30天、60天、90天)
	@FieldMeta(name = "应付款日期")
	private String fukuanDate;// 应付款日期(根据添加时间+付款周期自动计算)
	private String lateDate;// 实际付款日期

	private String status;// 状态(对账、开票、付款、完成)

	@FieldMeta(name = "添加时间")
	private String saveTime;// 添加时间(入库时间)
	private String saveUser;// 添加人(入库操作人员)
	private Integer epId;// 发票审批流程id
	@FieldMeta(name = "审批")
	private String auditStatus;// 审批状态(未审批/已审批)

	private String more;// 备注

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getRecNumber() {
		return recNumber;
	}

	public void setRecNumber(String recNumber) {
		this.recNumber = recNumber;
	}

	public Float getYufukuanJine() {
		return yufukuanJine;
	}

	public void setYufukuanJine(Float yufukuanJine) {
		this.yufukuanJine = yufukuanJine;
	}

	public Double getYingfukuanJine() {
		return yingfukuanJine;
	}

	public void setYingfukuanJine(Double yingfukuanJine) {
		this.yingfukuanJine = yingfukuanJine;
	}

	public Double getRealfukuanJine() {
		return realfukuanJine;
	}

	public void setRealfukuanJine(Double realfukuanJine) {
		this.realfukuanJine = realfukuanJine;
	}

	public Double getWeifukuanJine() {
		return weifukuanJine;
	}

	public void setWeifukuanJine(Double weifukuanJine) {
		this.weifukuanJine = weifukuanJine;
	}

	public String getSaveTime() {
		return saveTime;
	}

	public void setSaveTime(String saveTime) {
		this.saveTime = saveTime;
	}

	public String getSaveUser() {
		return saveUser;
	}

	public void setSaveUser(String saveUser) {
		this.saveUser = saveUser;
	}

	public String getJzMonth() {
		return jzMonth;
	}

	public void setJzMonth(String jzMonth) {
		this.jzMonth = jzMonth;
	}


	public String getFukuanDate() {
		return fukuanDate;
	}

	public void setFukuanDate(String fukuanDate) {
		this.fukuanDate = fukuanDate;
	}

	public String getLateDate() {
		return lateDate;
	}

	public void setLateDate(String lateDate) {
		this.lateDate = lateDate;
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

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}

	public Integer getFukuanZq() {
		return fukuanZq;
	}

	public void setFukuanZq(Integer fukuanZq) {
		this.fukuanZq = fukuanZq;
	}

}
