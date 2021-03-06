package com.task.entity;

public class Qexamine implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private int id;
	private String productname;//产品名称
	private String productdraw;//产品图号
	private String customername;//客户名称
	private String auditdate;//审核日期
	private String batchsampling;//抽样批次
	private String referencestandard;//引用标准
	
	private String qtotal;//总计
	private String conclusion;//结论
	private String disposals;//处置方案
	private String checkname;//审核人员
	private String explain;//说明
	private String checkstatus;//审批状态
	private String remarks;//备注
	private String status;//审查表状态
	private String writename;//填写人员
	private String totalstyle;//样品总数
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getProductdraw() {
		return productdraw;
	}
	public void setProductdraw(String productdraw) {
		this.productdraw = productdraw;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getAuditdate() {
		return auditdate;
	}
	public void setAuditdate(String auditdate) {
		this.auditdate = auditdate;
	}
	public String getBatchsampling() {
		return batchsampling;
	}
	public void setBatchsampling(String batchsampling) {
		this.batchsampling = batchsampling;
	}
	public String getReferencestandard() {
		return referencestandard;
	}
	public void setReferencestandard(String referencestandard) {
		this.referencestandard = referencestandard;
	}
	public String getQtotal() {
		return qtotal;
	}
	public void setQtotal(String qtotal) {
		this.qtotal = qtotal;
	}
	public String getConclusion() {
		return conclusion;
	}
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	public String getDisposals() {
		return disposals;
	}
	public void setDisposals(String disposals) {
		this.disposals = disposals;
	}
	public String getCheckname() {
		return checkname;
	}
	public void setCheckname(String checkname) {
		this.checkname = checkname;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public String getCheckstatus() {
		return checkstatus;
	}
	public void setCheckstatus(String checkstatus) {
		this.checkstatus = checkstatus;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setWritename(String writename) {
		this.writename = writename;
	}
	public String getWritename() {
		return writename;
	}
	public void setTotalstyle(String totalstyle) {
		this.totalstyle = totalstyle;
	}
	public String getTotalstyle() {
		return totalstyle;
	}
	
	
}
