package com.task.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 消耗
 * @author 马凯
 *
 */
public class Tconsumption implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String type;// 类型
	private String gytype;//工艺类型
	private double clength;//长
	private double cwidth;//宽 
	private double chigh;//高
	
	private int molecular;// 能出多少件
	private double partQuota;// 单件材料消耗定额
	private double netWeight;// 单件零件净重(公斤)
	private double utilization;// 材料利用率
	private String specifications;// 材料规格
	private String notes;// 备注
	private double density;//密度

	private Tdetail detail;//对应哪一个自制件
	private Tconsumption consumption;//合出或者连弯
	private List<Tconsumption> consumptions = new ArrayList<Tconsumption>();//子

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGytype() {
		return gytype;
	}
	public void setGytype(String gytype) {
		this.gytype = gytype;
	}
	public double getClength() {
		return clength;
	}
	public void setClength(double clength) {
		this.clength = clength;
	}
	public double getCwidth() {
		return cwidth;
	}
	public void setCwidth(double cwidth) {
		this.cwidth = cwidth;
	}
	public double getChigh() {
		return chigh;
	}
	public void setChigh(double chigh) {
		this.chigh = chigh;
	}
	public int getMolecular() {
		return molecular;
	}
	public void setMolecular(int molecular) {
		this.molecular = molecular;
	}
	public double getPartQuota() {
		return partQuota;
	}
	public void setPartQuota(double partQuota) {
		this.partQuota = partQuota;
	}
	public double getNetWeight() {
		return netWeight;
	}
	public void setNetWeight(double netWeight) {
		this.netWeight = netWeight;
	}
	public double getUtilization() {
		return utilization;
	}
	public void setUtilization(double utilization) {
		this.utilization = utilization;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public double getDensity() {
		return density;
	}
	public void setDensity(double density) {
		this.density = density;
	}
	public Tdetail getDetail() {
		return detail;
	}
	public void setDetail(Tdetail detail) {
		this.detail = detail;
	}
	public Tconsumption getConsumption() {
		return consumption;
	}
	public void setConsumption(Tconsumption consumption) {
		this.consumption = consumption;
	}
	public List<Tconsumption> getConsumptions() {
		return consumptions;
	}
	public void setConsumptions(List<Tconsumption> consumptions) {
		this.consumptions = consumptions;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
