package com.task.entity;

import java.io.Serializable;

/**
 * 报价单_模具成本实体类
 * @author 马凯
 */
public class ProjectQuotationToolingCost  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//序号
	private String name;//模具,检具名称
	private Double quantity;//数量
	private Double price;//单价 RMB
	private Double subTotal;//金额
	private Double lifeTimeVolume;//项目产量
	private Double unitCost;//单价金额
	private String mouldType;//模具类型
	private String note;//备注
	private ProjectQuotation root;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
	public Double getLifeTimeVolume() {
		return lifeTimeVolume;
	}
	public void setLifeTimeVolume(Double lifeTimeVolume) {
		this.lifeTimeVolume = lifeTimeVolume;
	}
	public Double getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}
	public String getMouldType() {
		return mouldType;
	}
	public void setMouldType(String mouldType) {
		this.mouldType = mouldType;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public ProjectQuotation getRoot() {
		return root;
	}
	public void setRoot(ProjectQuotation root) {
		this.root = root;
	}
	
}
