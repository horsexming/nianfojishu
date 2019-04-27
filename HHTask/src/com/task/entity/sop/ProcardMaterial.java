package com.task.entity.sop;
/**
 * 流水卡需要领的材料
 * @author txb
 *
 */
public class ProcardMaterial  implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private String markId;
	private String selfCard;//批次
	private String trademark;// 牌号
	private String specification;// 规格
	private String name;//名称
	private Float zaiCount;//这次叫料时有在制品可用，在制品抵扣数量
	private Float yzaiCount;//这次叫料时有原材料在制品可用，在原材料制品抵扣数量
	private Float wlZaiCount;//未领在制品数量
	private Float wlYzaiCount;//未领原材料在制品数量
	private Float thecount;//数量
	private Float wlcount;//未领数量
	private String unit;//单位
	private Integer procardId;//流水卡Id
	private Float procardCount;//流水数量
	private String type;//类型（外购件，原材料）
	private Float bili;//比例
	private Integer headId;//ProcardMaterialHead
	private String lingliaoStatus;//领料状态（未领，已领）
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public String getTrademark() {
		return trademark;
	}
	public void setTrademark(String trademark) {
		this.trademark = trademark;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Float getThecount() {
		return thecount;
	}
	public void setThecount(Float thecount) {
		this.thecount = thecount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Integer getProcardId() {
		return procardId;
	}
	public void setProcardId(Integer procardId) {
		this.procardId = procardId;
	}
	public Float getProcardCount() {
		return procardCount;
	}
	public void setProcardCount(Float procardCount) {
		this.procardCount = procardCount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Float getBili() {
		return bili;
	}
	public void setBili(Float bili) {
		this.bili = bili;
	}
	public Integer getHeadId() {
		return headId;
	}
	public void setHeadId(Integer headId) {
		this.headId = headId;
	}
	public String getLingliaoStatus() {
		return lingliaoStatus;
	}
	public void setLingliaoStatus(String lingliaoStatus) {
		this.lingliaoStatus = lingliaoStatus;
	}
	public Float getZaiCount() {
		return zaiCount;
	}
	public void setZaiCount(Float zaiCount) {
		this.zaiCount = zaiCount;
	}
	public Float getYzaiCount() {
		return yzaiCount;
	}
	public void setYzaiCount(Float yzaiCount) {
		this.yzaiCount = yzaiCount;
	}
	public Float getWlZaiCount() {
		return wlZaiCount;
	}
	public void setWlZaiCount(Float wlZaiCount) {
		this.wlZaiCount = wlZaiCount;
	}
	public Float getWlYzaiCount() {
		return wlYzaiCount;
	}
	public void setWlYzaiCount(Float wlYzaiCount) {
		this.wlYzaiCount = wlYzaiCount;
	}
	public Float getWlcount() {
		return wlcount;
	}
	public void setWlcount(Float wlcount) {
		this.wlcount = wlcount;
	}
	public String getSelfCard() {
		return selfCard;
	}
	public void setSelfCard(String selfCard) {
		this.selfCard = selfCard;
	}

	
	
}
