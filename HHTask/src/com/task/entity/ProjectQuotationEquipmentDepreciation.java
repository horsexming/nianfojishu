package com.task.entity;

import java.io.Serializable;

/**
 * 项目管理_报价单_设备折旧
 * @author 马凯
 *
 */
public class ProjectQuotationEquipmentDepreciation  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String process;//工序名称
	private String equipment;//设备名称
	private Double equipmentValue;//设备价值(RMB)
	private Double endLifeValue;//设备残值(RMB)
	private Double lifeTime;//折旧年限
	private Double workTime;//标准工时(H)
	private Double workDay;//工作日/年
	private Double tTS;//利用率
	private Double equipmentDepreciation;//折旧费用
	private ProjectQuotation root;
	private ProjectQuotationDirectLaborCost directLaborCost;
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	public Double getEquipmentValue() {
		return equipmentValue;
	}
	public void setEquipmentValue(Double equipmentValue) {
		this.equipmentValue = equipmentValue;
	}
	public Double getEndLifeValue() {
		return endLifeValue;
	}
	public void setEndLifeValue(Double endLifeValue) {
		this.endLifeValue = endLifeValue;
	}
	public Double getLifeTime() {
		return lifeTime;
	}
	public void setLifeTime(Double lifeTime) {
		this.lifeTime = lifeTime;
	}
	public Double getWorkTime() {
		return workTime;
	}
	public void setWorkTime(Double workTime) {
		this.workTime = workTime;
	}
	public Double getWorkDay() {
		return workDay;
	}
	public void setWorkDay(Double workDay) {
		this.workDay = workDay;
	}
	public Double gettTS() {
		return tTS;
	}
	public void settTS(Double tTS) {
		this.tTS = tTS;
	}
	public Double getEquipmentDepreciation() {
		return equipmentDepreciation;
	}
	public void setEquipmentDepreciation(Double equipmentDepreciation) {
		this.equipmentDepreciation = equipmentDepreciation;
	}
	public ProjectQuotation getRoot() {
		return root;
	}
	public void setRoot(ProjectQuotation root) {
		this.root = root;
	}
	public ProjectQuotationDirectLaborCost getDirectLaborCost() {
		return directLaborCost;
	}
	public void setDirectLaborCost(ProjectQuotationDirectLaborCost directLaborCost) {
		this.directLaborCost = directLaborCost;
	}
	
	
}