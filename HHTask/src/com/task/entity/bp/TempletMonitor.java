package com.task.entity.bp;

import java.io.Serializable;


public class TempletMonitor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;//无用序号
	private int tId;//原本的序号
	private int parentId;//父序号
	private String name;//名称
	private float advPosition;//单件数量
	private String directions;//说明
	private String models;//车型
	private String partsNumber;//件号
	private String specification;//规格
	private String category;//类别
	private String unit;//单位
	private String resPerson;//负责人 responsiblePerson
	private String reason;//什么操作
	
	private String trademark;//牌号
	private Float thickness;//厚度
	private Float thicknessT;//厚度
	private Float width;//宽度
	private Float widthT;//宽度
	private Float length;//长度
	private Float lengthT;//长度
	private Float diameter;//直径
	private String standards;//执行标准

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getAdvPosition() {
		return advPosition;
	}
	public void setAdvPosition(float advPosition) {
		this.advPosition = advPosition;
	}
	public String getDirections() {
		return directions;
	}
	public void setDirections(String directions) {
		this.directions = directions;
	}
	public String getModels() {
		return models;
	}
	public void setModels(String models) {
		this.models = models;
	}
	public String getPartsNumber() {
		return partsNumber;
	}
	public void setPartsNumber(String partsNumber) {
		this.partsNumber = partsNumber;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getResPerson() {
		return resPerson;
	}
	public void setResPerson(String resPerson) {
		this.resPerson = resPerson;
	}
	public int gettId() {
		return tId;
	}
	public void settId(int tId) {
		this.tId = tId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getTrademark() {
		return trademark;
	}
	public void setTrademark(String trademark) {
		this.trademark = trademark;
	}
	public Float getThickness() {
		return thickness;
	}
	public void setThickness(Float thickness) {
		this.thickness = thickness;
	}
	public Float getThicknessT() {
		return thicknessT;
	}
	public void setThicknessT(Float thicknessT) {
		this.thicknessT = thicknessT;
	}
	public Float getWidth() {
		return width;
	}
	public void setWidth(Float width) {
		this.width = width;
	}
	public Float getWidthT() {
		return widthT;
	}
	public void setWidthT(Float widthT) {
		this.widthT = widthT;
	}
	public Float getLength() {
		return length;
	}
	public void setLength(Float length) {
		this.length = length;
	}
	public Float getLengthT() {
		return lengthT;
	}
	public void setLengthT(Float lengthT) {
		this.lengthT = lengthT;
	}
	public Float getDiameter() {
		return diameter;
	}
	public void setDiameter(Float diameter) {
		this.diameter = diameter;
	}
	public String getStandards() {
		return standards;
	}
	public void setStandards(String standards) {
		this.standards = standards;
	}

	
}