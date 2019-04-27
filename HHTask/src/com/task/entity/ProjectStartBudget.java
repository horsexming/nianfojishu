package com.task.entity;

import java.io.Serializable;

/**
 * 项目预算
 * @author 马凯
 *
 */
public class ProjectStartBudget  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String module;// 零件/组合件/总成
	private Integer toolsNumber;//工装数量
	private Double toolsPrice;//工装价格
	private Double beenUsed;//已使用的钱
	private Double tobeUsed;//待投入的钱
	private String newEquipment;//所需新增设备
	private Double newEquipmentPrice;//新增设备的钱数

	private ProjectStart root;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public Integer getToolsNumber() {
		return toolsNumber;
	}
	public void setToolsNumber(Integer toolsNumber) {
		this.toolsNumber = toolsNumber;
	}
	public Double getToolsPrice() {
		return toolsPrice;
	}
	public void setToolsPrice(Double toolsPrice) {
		this.toolsPrice = toolsPrice;
	}
	public Double getBeenUsed() {
		return beenUsed;
	}
	public void setBeenUsed(Double beenUsed) {
		this.beenUsed = beenUsed;
	}
	public Double getTobeUsed() {
		return tobeUsed;
	}
	public void setTobeUsed(Double tobeUsed) {
		this.tobeUsed = tobeUsed;
	}
	public String getNewEquipment() {
		return newEquipment;
	}
	public void setNewEquipment(String newEquipment) {
		this.newEquipment = newEquipment;
	}
	public Double getNewEquipmentPrice() {
		return newEquipmentPrice;
	}
	public void setNewEquipmentPrice(Double newEquipmentPrice) {
		this.newEquipmentPrice = newEquipmentPrice;
	}
	public ProjectStart getRoot() {
		return root;
	}
	public void setRoot(ProjectStart root) {
		this.root = root;
	}
	

}
