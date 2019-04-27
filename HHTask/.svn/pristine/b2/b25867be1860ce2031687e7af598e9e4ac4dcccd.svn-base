package com.task.entity.bp;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.task.util.StrutsTreeITreeNode;
import com.task.util.StrutsTreeNode;


public class Templet implements StrutsTreeITreeNode,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Float advPosition;//权值
	private int belongLayer;// 当前层
	private String category;//类别
	private Set<Templet> childs = new HashSet<Templet>();//子节点
	private String directions;//说明
	private Integer id;//序号
	private String models;//车型
	private String name;//名称
	private Templet parent;//父节点
	private Integer parentId;//父序号，数据库存储用
	private Integer parentId2;//传输用
	private String partsNumber;//件号
	private String resPerson;//负责人
	private String monitorType;//监控类型
	
	private String specification;//规格
	private String unit;//单位

	private String trademark;//牌号
	private Float thickness;//厚度
	private Float thicknessT;//厚度
	private Float width;//宽度
	private Float widthT;//宽度
	private Float length;//长度
	private Float lengthT;//长度
	private Float diameter;//直径
	private String standards;//执行标准
	

	public Float getAdvPosition() {
		return advPosition;
	}


	public int getBelongLayer() {
		return belongLayer;
	}


	public String getCategory() {
		return category;
	}


	public Set<Templet> getChilds() {
		return childs;
	}


	public Float getDiameter() {
		return diameter;
	}


	public String getDirections() {
		return directions;
	}


	public Integer getId() {
		return id;
	}


	public Float getLength() {
		return length;
	}


	public String getModels() {
		return models;
	}


	public String getMonitorType() {
		return monitorType;
	}


	public String getName() {
		return name;
	}


	public Templet getParent() {
		return parent;
	}


	public Integer getParentId() {
		return parentId;
	}


	public Integer getParentId2() {
		return parentId2;
	}


	public String getPartsNumber() {
		return partsNumber;
	}


	public String getResPerson() {
		return resPerson;
	}


	public String getSpecification() {
		return specification;
	}


	public Float getThickness() {
		return thickness;
	}


	public String getTrademark() {
		return trademark;
	}


	public String getUnit() {
		return unit;
	}


	public Float getWidth() {
		return width;
	}


	public void setAdvPosition(Float advPosition) {
		this.advPosition = advPosition;
	}


	public void setBelongLayer(int belongLayer) {
		this.belongLayer = belongLayer;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public void setChilds(Set<Templet> childs) {
		this.childs = childs;
	}


	public void setDiameter(Float diameter) {
		this.diameter = diameter;
	}


	public void setDirections(String directions) {
		this.directions = directions;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public void setLength(Float length) {
		this.length = length;
	}


	public void setModels(String models) {
		this.models = models;
	}


	public void setMonitorType(String monitorType) {
		this.monitorType = monitorType;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setParent(Templet parent) {
		this.parent = parent;
	}


	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}


	public void setParentId2(Integer parentId2) {
		this.parentId2 = parentId2;
	}


	public void setPartsNumber(String partsNumber) {
		this.partsNumber = partsNumber;
	}


	public void setResPerson(String resPerson) {
		this.resPerson = resPerson;
	}


	public void setSpecification(String specification) {
		this.specification = specification;
	}


	public void setThickness(Float thickness) {
		this.thickness = thickness;
	}


	public void setTrademark(String trademark) {
		this.trademark = trademark;
	}


	public void setUnit(String unit) {
		this.unit = unit;
	}


	public void setWidth(Float width) {
		this.width = width;
	}


	// 转换为节点类
	public StrutsTreeNode toTreeNode() {
		StrutsTreeNode treenode = new StrutsTreeNode();
		treenode.setId(getId());// 对象id
		treenode.setParentId(getParentId2());// 父类id
		if("update".equals(monitorType) || "add".equals(monitorType) || "delete".equals(monitorType)){
			treenode.setName(getName() + "(审核中)");// 对象名称
		} else if("overrule".equals(monitorType)){
			treenode.setName(getName() + "(驳回)");// 对象名称
		}else {
			treenode.setName(getName());// 对象名称
		}
		treenode.setLayers(getBelongLayer());// 对象所在层
		return treenode;
	}


	public Float getThicknessT() {
		return thicknessT;
	}


	public void setThicknessT(Float thicknessT) {
		this.thicknessT = thicknessT;
	}


	public Float getWidthT() {
		return widthT;
	}


	public void setWidthT(Float widthT) {
		this.widthT = widthT;
	}


	public Float getLengthT() {
		return lengthT;
	}


	public void setLengthT(Float lengthT) {
		this.lengthT = lengthT;
	}


	public String getStandards() {
		return standards;
	}


	public void setStandards(String standards) {
		this.standards = standards;
	}



}
