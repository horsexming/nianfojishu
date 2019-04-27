package com.task.entity;

import java.util.Set;
import java.util.TreeSet;

import com.task.comparator.DetailSeqComparator;

/**
 * BOM
 * @author 马凯
 *
 */
public class Tdetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String xunhao;//序号
	private String partNumber;//件号
	private String name;//名称
	private double singleNumber;//单台数量
	private String unit;//单位
	private String type;//类型
	private String material;//牌号
	private String standard;//标准
	private String specification;//规格
	private int seq;//排序
	private Tdetail detail;
	private Project project;
	private Set<Tdetail> details = new TreeSet<Tdetail>(new DetailSeqComparator());

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getXunhao() {
		return xunhao;
	}
	public void setXunhao(String xunhao) {
		this.xunhao = xunhao;
	}
	public String getPartNumber() {
		return partNumber;
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
	public double getSingleNumber() {
		return singleNumber;
	}
	public void setSingleNumber(double singleNumber) {
		this.singleNumber = singleNumber;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public Tdetail getDetail() {
		return detail;
	}
	public void setDetail(Tdetail detail) {
		this.detail = detail;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public Set<Tdetail> getDetails() {
		return details;
	}
	public void setDetails(Set<Tdetail> details) {
		this.details = details;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}

}
