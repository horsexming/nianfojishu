package com.task.entity;

import java.io.Serializable;

/**
 * 索赔单_记录
 * 
 * @author 马凯
 * 
 */
public class TclaimsRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String partNumber;// 件号
	private String name;// 名称
	private int quantity;// 数量
	private String description;// 故障描述
	private String cause;// 原因分析
	private String status;// 状态
	private String model;//车型
	private String productionDate;//生产日期
	private Tclaimform root;//
	
	private String reason;//我们公司分析的事故原因
	private String reasonPerson;//分析事故原因的负责人
	private String reasonFilename;//整改文件
	private String handle;//整改
	private String handleFilename;//整改文件
	private String handlePerson;//整改人
	private String responsibility;//责任方
	

	public String getPartNumber() {
		return partNumber;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}

	public Tclaimform getRoot() {
		return root;
	}

	public void setRoot(Tclaimform root) {
		this.root = root;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReasonPerson() {
		return reasonPerson;
	}

	public void setReasonPerson(String reasonPerson) {
		this.reasonPerson = reasonPerson;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public String getHandleFilename() {
		return handleFilename;
	}

	public void setHandleFilename(String handleFilename) {
		this.handleFilename = handleFilename;
	}

	public String getHandlePerson() {
		return handlePerson;
	}

	public void setHandlePerson(String handlePerson) {
		this.handlePerson = handlePerson;
	}

	public String getReasonFilename() {
		return reasonFilename;
	}

	public void setReasonFilename(String reasonFilename) {
		this.reasonFilename = reasonFilename;
	}

	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}

}
